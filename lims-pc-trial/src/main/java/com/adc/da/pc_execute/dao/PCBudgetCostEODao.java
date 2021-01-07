package com.adc.da.pc_execute.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_execute.entity.PCBudgetCostEO;

public interface PCBudgetCostEODao extends BaseDao<PCBudgetCostEO> {
	/**
	 * 根据试验任务id 获取费用其他信息
	 * @Title: getCostByTrialTaskId
	 * @param trialTaskId
	 * @return
	 * @return PCBudgetCostEO
	 * @author: ljy
	 * @date: 2019年12月23日
	 */
    public PCBudgetCostEO getCostByTrialTaskId(String trialTaskId);
    
    /**
     * 根据试验任务id 删除费用其他信息
     * @Title: deleteByTrialTaskId
     * @param trialTaskId
     * @return void
     * @author: ljy
     * @date: 2019年12月25日
     */
    public void deleteByTrialTaskId(String trialTaskId);
    
    /**
     * 根据试验任务唯一code 获取费用其他信息
     * @Title: getCostByTrialTaskNumber
     * @param trialTaskId
     * @return
     * @return PCBudgetCostEO
     * @author: ljy
     * @date: 2020年2月28日
     */
    public PCBudgetCostEO getCostByTrialTaskNumber(String trialTaskId);
    /**
     * 根据试验任务唯一code 删除费用其他信息
     * @Title: deleteByTrialTaskNumber
     * @param trialTaskId
     * @return void
     * @author: ljy
     * @date: 2020年2月28日
     */
    public void deleteByTrialTaskNumber(String trialTaskId);
}