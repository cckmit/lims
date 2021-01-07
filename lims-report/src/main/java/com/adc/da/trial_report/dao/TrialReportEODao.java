package com.adc.da.trial_report.dao;

import java.util.List;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.trial_report.entity.TrialReportEO;
/**
 * EV-发动机试验管理-试验报告模块
 * @author Administrator
 * @date 2019年8月20日
 */
public interface TrialReportEODao extends BaseDao<TrialReportEO> {
	/**
	 * 根据试验任务书id查询所有报告
	* @Title：queryReportByTrialTaskId
	* @param trialtaskId
	* @return
	* @return: List<TrialReportEO>
	* @author： ljy  
	* @date： 2019年9月3日
	 */
	public List<TrialReportEO> queryReportByTrialTaskId(String trialtaskId);
	/**
	 * 根据报告id,查询流程实例id
	 * @Title: selectActProcIdById
	 * @param id
	 * @return
	 * @return String
	 * @author: ljy
	 * @date: 2019年11月27日
	 */
	public String selectActProcIdById(String id);
}
