package com.adc.da.trial_report.dao;

import java.util.List;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.trial_report.entity.ReportArchivefileEO;
/**
 * 报告附件汇总,归档信息
 * @author Administrator
 * @date 2019年8月26日
 */
public interface ReportArchivefileEODao extends BaseDao<ReportArchivefileEO> {
	/**
	 * 通过报告id删除信息
	* @Title：deleteByReportId
	* @param reportId
	* @return: void
	* @author： ljy  
	* @date： 2019年8月26日
	 */
	public void deleteByReportId(String reportId);
	/**
	 * 通过报告id获取该报告信息
	* @Title：findListByReportId
	* @param reportId
	* @return
	* @return: List<ReportArchivefileEO>
	* @author： ljy  
	* @date： 2019年8月28日
	 */
	public List<ReportArchivefileEO> findListByReportId(String reportId);
	/**
	 * 通过附件id删除信息
	 * @Title: deleteByFileId
	 * @param reportFileid
	 * @return void
	 * @author: ljy
	 * @date: 2019年12月12日
	 */
	public void deleteByFileId(String reportFileid);
}