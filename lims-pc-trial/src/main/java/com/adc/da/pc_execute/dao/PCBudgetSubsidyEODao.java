package com.adc.da.pc_execute.dao;

import java.util.List;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_execute.entity.PCBudgetSubsidyEO;


/**
 * PV/CV试验执行模块--可靠性立项单(试验补贴预算)
 * @author Administrator
 * @date 2019年10月23日
 */
public interface PCBudgetSubsidyEODao extends BaseDao<PCBudgetSubsidyEO> {
	/**
	 *获取可靠性立项单中的  试验补贴预算
	 * @Title: findListByInitTaskId
	 * @param initTaskId
	 * @return
	 * @return List<PCBudgetSubsidyEO>
	 * @author: ljy
	 * @date: 2019年10月23日
	 */
	public List<PCBudgetSubsidyEO> findListByInitTaskId(String initTaskId);
	
	/**
	 * 删除可靠性立项单中的  试验补贴预算
	 * @Title: deleteByInitTaskId
	 * @param initTaskId
	 * @return void
	 * @author: ljy
	 * @date: 2019年10月23日
	 */
	public void deleteByInitTaskId(String initTaskId);
	
	/**
	 * 根据试验任务id,查询试验人员及住宿预算
	 * @Title: findListByTrialTaskId
	 * @param trialTaskId
	 * @return
	 * @return List<PCBudgetPersonEO>
	 * @author: ljy
	 * @date: 2019年12月23日
	 */
	public List<PCBudgetSubsidyEO> findListByTrialTaskId(String trialTaskId);
	
	/**
	 * 根据试验任务id,删除试验人员及住宿预算
	 * @Title: deleteByTrialTaskId
	 * @param trialTaskId
	 * @return void
	 * @author: ljy
	 * @date: 2019年12月23日
	 */
	public void deleteByTrialTaskId(String trialTaskId);
	
	/**
	 * 根据试验任务唯一code,查询试验人员及住宿预算
	 * @Title: findListByTrialTaskNumber
	 * @param trialTaskId
	 * @return
	 * @return List<PCBudgetSubsidyEO>
	 * @author: ljy
	 * @date: 2020年2月28日
	 */
	public List<PCBudgetSubsidyEO> findListByTrialTaskNumber(String trialTaskId);
	
	/**
	 * 根据试验任务唯一code,删除试验人员及住宿预算
	 * @Title: deleteByTrialTaskNumber
	 * @param taskNumber
	 * @return void
	 * @author: ljy
	 * @date: 2020年2月28日
	 */
	public void deleteByTrialTaskNumber(String taskNumber);
	
}