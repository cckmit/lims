package com.adc.da.trial_report.dao;

import java.util.List;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.trial_report.entity.TrialReportProcessEO;
import com.adc.da.trial_report.page.TrialReportProcessEOPage;
import com.adc.da.trial_report.vo.TrialScfeduleVO;

public interface TrialReportProcessEODao extends BaseDao<TrialReportProcessEO> {
	/**
	 * 进度日志
	 * @Title: selectScheduleList
	 * @param trialtaskInsproId
	 * @return
	 * @return List<TrialScfeduleVO>
	 * @author: ljy
	 * @date: 2019年10月31日
	 */
	List<TrialScfeduleVO> selectScheduleList(String trialtaskInsproId);

	/**
	 * 分页查询进度日志
	 *
	 * @param page
	 * @return
	 */
	List<TrialScfeduleVO> getTrialReportProcessByPage(TrialReportProcessEOPage page);

	/**
	 * 分页查询进度日志
	 *
	 * @param page
	 * @return
	 */
	Integer getTrialReportProcessByCount(TrialReportProcessEOPage page);
}
