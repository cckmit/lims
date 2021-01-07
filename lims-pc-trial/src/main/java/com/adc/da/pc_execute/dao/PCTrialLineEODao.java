package com.adc.da.pc_execute.dao;

import java.util.List;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_execute.entity.PCTrialLineEO;

public interface PCTrialLineEODao extends BaseDao<PCTrialLineEO> {
	/**
	 *获取可靠性立项单中的 路线策划数据
	 * @Title: findByList
	 * @param initTaskId
	 * @return
	 * @return List<PCTrialLineEO>
	 * @author: ljy
	 * @date: 2019年10月21日
	 */
	public List<PCTrialLineEO> findByList(String initTaskId);
	/**
	 * 删除可靠性立项单中的 路线策划数据
	 * @Title: deleteByInitTaskId
	 * @param initTaskId
	 * @return void
	 * @author: ljy
	 * @date: 2019年10月21日
	 */
	public void deleteByInitTaskId(String initTaskId);
	
	/**
	 * 根据试验任务唯一code删除路线策划数据
	 * @Title: deleteByTaskNumber
	 * @param taskNumber
	 * @return void
	 * @author: ljy
	 * @date: 2020年2月28日
	 */
	public void deleteByTaskNumber(String taskNumber);
	
	/**
	 * 根据试验任务id,查询路线策划
	 * @Title: findListByTrialTaskId
	 * @param trialTaskId
	 * @return
	 * @return List<PCBudgetPersonEO>
	 * @author: ljy
	 * @date: 2019年12月23日
	 */
	public List<PCTrialLineEO> findListByTrialTaskId(String trialTaskId);
	
	/**
	 * 根据试验唯一code,查询路线策划
	 * @Title: findListByTrialTaskNumber
	 * @param taskNumber
	 * @return
	 * @return List<PCTrialLineEO>
	 * @author: ljy
	 * @date: 2020年2月28日
	 */
	public List<PCTrialLineEO> findListByTrialTaskNumber(String taskNumber);
	
	/**
	 * 根据试验任务id,删除试验人员及住宿预算
	 * @Title: deleteByTrialTaskId
	 * @param trialTaskId
	 * @return void
	 * @author: ljy
	 * @date: 2019年12月23日
	 */
	public void deleteByTrialTaskId(String trialTaskId);
}