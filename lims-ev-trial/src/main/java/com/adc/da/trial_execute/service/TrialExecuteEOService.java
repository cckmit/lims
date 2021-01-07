package com.adc.da.trial_execute.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.acttask.service.LimsFileService;
import com.adc.da.base.dao.BaseDao;
import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.dao.OrgEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.RoleEO;
import com.adc.da.trial_execute.dao.TrialExecuteEODao;
import com.adc.da.trial_execute.page.TrialExecuteEOPage;
import com.adc.da.trial_execute.vo.TrialExecuteListVO;
import com.adc.da.trial_execute.vo.TrialExecuteVO;
import com.adc.da.trial_report.dao.TrialReportProcessEODao;
import com.adc.da.trial_report.vo.TrialScfeduleVO;
import com.adc.da.trial_task.dao.TrialtaskSampleEODao;
import com.adc.da.trial_task.entity.TrialTaskEO;
import com.adc.da.trial_task.entity.TrialtaskSampleEO;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.CollectionUtils;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/9/5 15:21
 * @description：${description}
 */
@Service
@Transactional(value = "transactionManager", readOnly = false,
        rollbackFor = Throwable.class)
public class TrialExecuteEOService extends BaseService<TrialTaskEO, String> {

    @Autowired
    private TrialExecuteEODao trialExecuteEODao;

    @Autowired
    private BeanMapper beanMapper;

    @Override
    public BaseDao<TrialTaskEO> getDao() {
        return trialExecuteEODao;
    }
    
    @Autowired
    private TrialReportProcessEODao trialReportProcessEODao;

    
    @Autowired
    private UserEODao userEODao;
    
    @Autowired
    private LimsFileService limsFileService;
    
    
    @Autowired
    private TrialtaskSampleEODao sampleEODao;
    
    @Autowired
    private OrgEODao orgEODao;
    
    /**
     * 发动机试验任务执行-分页查询
     *
     * @param page
     * @return
     */
    public List<TrialExecuteVO> selectByPage(TrialExecuteEOPage page) throws Exception {
        // 获取用户角色
        List<RoleEO> roleList = UserUtils.getRoleList();
        if (roleList != null) {
            for (RoleEO roleEO : roleList) {
                // 如果用户有管理员角色，查询全部
                if ("ADMINISTRATOR".equals(roleEO.getRoleCode())) {
                    page.getPager().setRowCount(trialExecuteEODao.queryByCount(page));
                    List<TrialTaskEO> trialTaskEOS = trialExecuteEODao.queryByPage(page);
                    trialTaskEOS =  setScaffoldingName(trialTaskEOS);
                    return beanMapper.mapList(trialTaskEOS, TrialExecuteVO.class);
                }
              //如果当前登录用户为部长/科长/主管/任务管理员, 则需过滤数据
                if(ConstantUtils.EV_MINISTER_ROLE_CODE.equals(roleEO.getRoleCode()) ||
                		ConstantUtils.EV_SECTIONCHIEF_ROLE_CODE.equals(roleEO.getRoleCode()) ||
                		ConstantUtils.EV_TASKMANAGER_ROLE_CODE.equals(roleEO.getRoleCode()) ||
                		ConstantUtils.EV_CHARGE_ROLE_CODE.equals(roleEO.getRoleCode())) {
         			List<String> orgIds = userEODao.getOrgIdListByUserId(UserUtils.getUserId());
         			if(CollectionUtils.isNotEmpty(orgIds)) {
         				List<String> userIds = new ArrayList<String>();
         				List<OrgEO> orgList = orgEODao.getChildren(orgIds.get(0), "");
         				for (int i = 0; i < orgList.size(); i++) {
         					userIds.addAll(limsFileService.getUserIdsByOrgId(orgList.get(i).getId()));
						}
         				page.setCreateByIds(userIds);
         			}
         			page.getPager().setRowCount(trialExecuteEODao.queryByCount(page));
                    List<TrialTaskEO> trialTaskEOS = trialExecuteEODao.queryByPage(page);
                    trialTaskEOS =  setScaffoldingName(trialTaskEOS);
                    return beanMapper.mapList(trialTaskEOS, TrialExecuteVO.class);
         		}
            }
            // 没有查询发起人为当前登录用户的数据
            page.setUserId(UserUtils.getUserId());
            page.setPersonIds(UserUtils.getUserId());
            page.getPager().setRowCount(trialExecuteEODao.queryByCount(page));
            List<TrialTaskEO> trialTaskEOS = trialExecuteEODao.queryByPage(page);
            trialTaskEOS =  setScaffoldingName(trialTaskEOS);
            return beanMapper.mapList(trialTaskEOS, TrialExecuteVO.class);
        }
        return null;
    }

    
    public List<TrialTaskEO> setScaffoldingName(List<TrialTaskEO> trialTaskEOS){
    	for (TrialTaskEO eo : trialTaskEOS) {
        	List<TrialtaskSampleEO> sampleList = sampleEODao.findTrialtaskSampleByTrialtaskId(eo.getId());
        	if(CollectionUtils.isNotEmpty(sampleList)) {
        		String orgName = sampleList.get(0).getScaffoldingName();
        		eo.setScaffoldingName(orgName);
        	}
		}
    	return trialTaskEOS;
    }
    
    
    /**
     * 发动机试验任务执行-点击“+”查询
     *
     * @param id
     * @return
     */
    public List<TrialExecuteListVO> selectListById(String id) throws Exception {
        List<TrialExecuteListVO> trialExecuteListVOS = trialExecuteEODao.selectListById(id);
        if (trialExecuteListVOS != null) {
            for (TrialExecuteListVO vo : trialExecuteListVOS) {
                // 关联查询试验进度
                List<TrialScfeduleVO> scfeduleVO = trialReportProcessEODao.selectScheduleList(vo.getId());
                if (CollectionUtils.isNotEmpty(scfeduleVO)) {
                    // 试验进度
                    vo.setTrialprojectRate(scfeduleVO.get(0).getTrialprojectRate());
                }
            }
        }
        return trialExecuteListVOS;
    }
}
