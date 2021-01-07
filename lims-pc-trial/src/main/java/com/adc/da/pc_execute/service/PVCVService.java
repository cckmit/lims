package com.adc.da.pc_execute.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.NextGroupUserVO;

import net.sf.json.JSONObject;

@Service("PVCVService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PVCVService {
	
	@Autowired
    private UserEOService userEOService;
    
    @Autowired
    private OrgEOService orgEOService;
    
    @Autowired
    private DicTypeEOService dicTypeEOService;
    
    @Autowired
    private ActivitiTaskService activitiTaskService;
    
    @Autowired
    private RuntimeService runtimeService;
	
	/**
	 * 启动流程时, 判断下一个节点是否有人
	 * @Title: getStartNextNodeInfo
	 * @param pv
	 * @param cv
	 * @return
	 * @throws Exception
	 * @return JSONObject
	 * @date: 2020年4月1日
	 */
	public JSONObject getStartNextNodeInfo(String pv, String cv) throws Exception {
		JSONObject jsonObj = new JSONObject();
		//判断下一节点是否有审批人
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        String procDefKey = null;
        
        String pvorcv = "";
        if ("1".equals(flag)) {
            pvorcv = pv;
        } else {
            pvorcv = cv;
        }
        procDefKey = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(
                ConstantUtils.PROCESS_CODE, pvorcv).getDicTypeName();
        //设置返回默认值
        jsonObj.put("flag", flag);
        NextGroupUserVO nextNodeInfo = activitiTaskService.getNextNodeInfo(procDefKey, null, null);
        String roleId = nextNodeInfo.getRoleId();
        String departId = nextNodeInfo.getDepartId();
        if(StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(departId) && "1".equals(departId)) {
        	List<Map<String, Object>> usersByRoleAndOrg = userEOService.getUsersByRoleAndOrg(roleId, curOrg.getId());
        	if(usersByRoleAndOrg.isEmpty()) {
        		jsonObj.put("isSuccess", "-1");
        		return jsonObj;
        	}
        }
        jsonObj.put("isSuccess", "0");
		return jsonObj;
	}
	
	
	/**
	 * 流程过程中 判断下个节点是否有人
	 * @Title: getNextNodeInfo
	 * @param requestEO
	 * @return
	 * @return String
	 * @date: 2020年4月1日
	 */
	public String getNextNodeInfo(RequestEO requestEO) {
		//判断下一节点是否有审批人
		String condition = (String)requestEO.getVariables().get("approve");
	    NextGroupUserVO nextNodeInfo = activitiTaskService.getNextNodeInfo(null, requestEO.getTaskId(), condition);
	    String roleId = nextNodeInfo.getRoleId();
	    String departId = nextNodeInfo.getDepartId();
	    if(StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(departId) && "1".equals(departId)) {
	    	String specialOrgId = (String)runtimeService.getVariable(requestEO.getProcId(), "specialOrgId");
	    	List<Map<String, Object>> usersByRoleAndOrg = userEOService.getUsersByRoleAndOrg(roleId, specialOrgId);
	    	if(usersByRoleAndOrg.isEmpty()) {
	    		return "-1";
	    	}
	    }
	    return "0";
	}
	
}
