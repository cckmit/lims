package com.adc.da.workflow.listener;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.IdentityLinkType;
import org.springframework.stereotype.Component;

import com.adc.da.sys.service.UserEOService;
import com.adc.da.trial_task.service.TrialTaskEOService;
import com.adc.da.util.utils.SpringContextHolder;
import com.adc.da.util.utils.StringUtils;

/**
 * 动态用户任务分配，根据组织机构和角色分配候选人
 *
 */
@Component("UserTaskListener2")
public class UserTaskListener2 implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4877281425776793463L;
	
    /**
     * 先获取流程设计时设置的受理组（即角色）信息，并删除该设置，
     * 然后查询和指定组织机构的该受理组人员，并设置为候选人
     *
     * @param delegateTask
     */
    @Override
    public void notify(DelegateTask delegateTask) {
    	UserEOService userEOService = SpringContextHolder.getApplicationContext().getBean(UserEOService.class);
    	
        // 获取流程设计时设置的受理组（即角色）信息
        Set<IdentityLink> candidates = delegateTask.getCandidates();
        if (candidates != null && !candidates.isEmpty()) {
            IdentityLink candidate = candidates.iterator().next();
            // 删除受理组设置
            delegateTask.deleteGroupIdentityLink(candidate.getGroupId(), IdentityLinkType.CANDIDATE);
            // 从流程变量里获取指定的组织机构ID
            String specialOrgId = (String) delegateTask.getVariable("specialOrgId");
            if (StringUtils.isNotEmpty(specialOrgId)) {
                // 获取指定组织机构下的该受理组人员
            	List<Map<String, Object>> userList = userEOService.getUsersByRoleAndOrg(candidate.getGroupId(), specialOrgId);
                if (!userList.isEmpty()) {
                    // 重新设置流程候选人
                    Set<String> candidateUsers = new HashSet<>();
                    for (Map<String, Object> user : userList) {
                        candidateUsers.add((String)user.get("usid"));
                    }
                    delegateTask.addCandidateUsers(candidateUsers);
                }
            }
        }
    }
}