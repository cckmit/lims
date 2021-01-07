package com.adc.da.pc_report.dao;

import com.adc.da.acttask.entity.ActTaskEO;
import com.adc.da.acttask.page.ActTaskEOPage;
import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_report.page.ReportPage;

import java.util.List;

public interface PcReportEODao extends BaseDao<ActTaskEO> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<ActTaskEO> reportTaskPage(ActTaskEOPage page);

    /**
     * 总数查询
     * @param page
     * @return
     */
    int reportTaskCount(ActTaskEOPage page);

    /**
     * 分页查询
     * @param page
     * @return
     */
	List<ReportPage> queryReportByPage(ReportPage page);

	/**
	 * 查询总数
	 * @param page
	 * @return
	 */
	int queryCount(ReportPage page);



}
