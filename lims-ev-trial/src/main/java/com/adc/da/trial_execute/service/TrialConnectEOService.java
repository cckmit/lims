package com.adc.da.trial_execute.service;

import com.adc.da.trial_execute.page.TrialConnectEOPage;
import com.adc.da.trial_task.dao.TrialtaskSampleEODao;
import com.adc.da.trial_task.entity.TrialtaskSampleEO;
import com.adc.da.util.utils.CollectionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.trial_execute.dao.TrialConnectEODao;
import com.adc.da.trial_execute.entity.TrialConnectEO;

import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>EV_TRIAL_CONNECT TrialConnectEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-09-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("trialConnectEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class TrialConnectEOService extends BaseService<TrialConnectEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(TrialConnectEOService.class);

    @Autowired
    private TrialConnectEODao dao;
    
    @Autowired
    private TrialtaskSampleEODao sampleEODao;

    public TrialConnectEODao getDao() {
        return dao;
    }

    
    
    public List<TrialConnectEO> queryConnectByPage(TrialConnectEOPage page) {
        Integer rowCount = this.getDao().queryByCount(page);
        page.getPager().setRowCount(rowCount);
        List<TrialConnectEO> trialConnectEOList = this.getDao().queryByPage(page);
        
        List<TrialtaskSampleEO> sampleList = sampleEODao.findTrialtaskSampleByTrialtaskId(page.getTrialtaskId());
        if(CollectionUtils.isNotEmpty(sampleList)) {
        	String orgName = sampleList.get(0).getScaffoldingName();
        	for (TrialConnectEO eo : trialConnectEOList) {
				eo.setScaffoldingName(orgName);
			}
        }
        return trialConnectEOList;
    }

    /**
     * 通过实验任务id，查询交接班记录列表
     * @param page
     * @return
     */
    public List<TrialConnectEO> selectListByTaskId(TrialConnectEOPage page) {
        return this.getDao().selectListByTaskId(page);
    }

    /**
     * 查询任务下有多少台架
     * @param taskId
     * @return
     */
    public List<String> selectTrialTaskIds(String taskId) {
        return this.getDao().selectTrialTaskIds(taskId);
    }

    /**
     * 查询当前登录人的组织机构
     * @param userId
     * @return
     */
    public List<String> selectOrgIdsByUserId(String userId) {
        return this.getDao().selectOrgIdsByUserId(userId);
    }
    
    /**
     * 根据试验任务id 获取台架状态
    * @Title：getActualScaffoldingStatus
    * @param trialtaskId
    * @return
    * @return: String
    * @author： ljy  
    * @date： 2019年9月24日
     */
	public String getActualScaffoldingStatus(String trialtaskId) {
		//定义返回变量
		String actualScaffoldingStatus = "";
		//查询状态
		List<String> trialConnectList = this.getDao().selectByTrialTaskId(trialtaskId);
		if(CollectionUtils.isNotEmpty(trialConnectList)) {
			//获取最新状态
			actualScaffoldingStatus = trialConnectList.get(0);
		}
		return actualScaffoldingStatus;
	}
}
