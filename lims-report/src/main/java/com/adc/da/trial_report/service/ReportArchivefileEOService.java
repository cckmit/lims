package com.adc.da.trial_report.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.trial_report.dao.ReportArchivefileEODao;
import com.adc.da.trial_report.entity.ReportArchivefileEO;
import com.adc.da.trial_report.entity.TrialReportEO;
import com.adc.da.trial_report.page.ReportArchivefileEOPage;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;

/**
 * EV-发动机试验管理-试验报告模块
 * @author Administrator
 * @date 2019年8月20日
 */
@Service("reportArchivefileEOService")
@Transactional(value = "transactionManager", readOnly = false, 
	propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ReportArchivefileEOService extends BaseService<ReportArchivefileEO, String> {

	@Autowired
	private ReportArchivefileEODao reportArchivefileEODao;
	
	public ReportArchivefileEODao getDao() {
        return reportArchivefileEODao;
    }
	
	@Autowired
	private TrialReportEOService trialReportEOService;
	
	@Autowired
	private TsAttachmentEODao tsAttachmentEODao;
	
	
	/**
	 * 根据报告id查询该报告查询下的所有附件
	* @Title：findListByReportId
	* @param reportId
	* @return
	* @return: List<ReportArchivefileEO>
	* @author： ljy  
	* @date： 2019年8月28日
	 */
	public List<ReportArchivefileEO> findListByReportId(String reportId){
		return reportArchivefileEODao.findListByReportId(reportId);
	}
	
	/**
	 * 分页查询
	 * @Title: page
	 * @param page
	 * @return
	 * @return List<ReportArchivefileEO>
	 * @author: ljy
	 * @date: 2020年1月14日
	 */
	public List<ReportArchivefileEO> page(ReportArchivefileEOPage page){
		return reportArchivefileEODao.queryByPage(page);
	}
	
	/**
	 * 报告文件归档操作
	* @Title：reportFileArchive
	* @param reportArchivefileVO
	* @return
	* @return: ResponseMessage<ReportArchivefileVO>
	* @author： ljy  
	* @date： 2019年8月29日
	 * @throws Exception 
	 */
	public ReportArchivefileEO reportFileArchive(ReportArchivefileEO reportArchivefileEO) throws Exception {
		//纸质归档
		if(ConstantUtils.EV_REPORT_ARCHIVE_PAPER.
				equals(reportArchivefileEO.getArchiveStatus())) {
			reportArchivefileEO.setIsPaperArchive(ConstantUtils.EV_REPORT_ARCHIVE_YES);
		}else if(ConstantUtils.EV_REPORT_ARCHIVE_ELECTRON.
				equals(reportArchivefileEO.getArchiveStatus())) {
			//电子归档
			reportArchivefileEO.setIsElectronArchive(ConstantUtils.EV_REPORT_ARCHIVE_YES);
		}
		//全部归档(电子+纸质)
		if(ConstantUtils.EV_REPORT_ARCHIVE_YES.
				equals(reportArchivefileEO.getIsElectronArchive()) &&
		   ConstantUtils.EV_REPORT_ARCHIVE_YES.
				equals(reportArchivefileEO.getIsPaperArchive())) {
			reportArchivefileEO.setArchiveStatus(ConstantUtils.EV_REPORT_ARCHIVE_ALL);
		}
		//更新
		reportArchivefileEODao.updateByPrimaryKeySelective(reportArchivefileEO);
		//反向更新报告归档状态
		updateReportArchiveStatus(reportArchivefileEO.getReportId());
		return reportArchivefileEO;
	}
	
	/**
	 * 报告文件取消归档操作
	* @Title：reportFileCancelArchive
	* @param eo
	* @return
	* @throws Exception
	* @return: ReportArchivefileEO
	* @author： ljy  
	* @date： 2019年9月26日
	 */
	public ReportArchivefileEO reportFileCancelArchive(ReportArchivefileEO eo) throws Exception {
		//查询
		ReportArchivefileEO archivefileEO = reportArchivefileEODao.selectByPrimaryKey(eo.getId());
		//取消纸质归档
		if(ConstantUtils.EV_REPORT_ARCHIVE_PAPER.equals(eo.getArchiveStatus())) {
			//是否纸质归档-否
			eo.setIsPaperArchive(ConstantUtils.EV_REPORT_ARCHIVE_NO);
			eo.setStoragePosotion("");
			//判断是否原状态为已归档
			if(ConstantUtils.EV_REPORT_ARCHIVE_ALL.equals(archivefileEO.getArchiveStatus())) {
				eo.setArchiveStatus(ConstantUtils.EV_REPORT_ARCHIVE_ELECTRON);
			}else {
				//未归档
				eo.setArchiveStatus(ConstantUtils.EV_REPORT_ARCHIVE_NOT);
			}
		}else if(ConstantUtils.EV_REPORT_ARCHIVE_ELECTRON.equals(eo.getArchiveStatus())) {
			//取消电子归档
			eo.setIsElectronArchive(ConstantUtils.EV_REPORT_ARCHIVE_NO);
			//判断是否原状态为已归档
			if(ConstantUtils.EV_REPORT_ARCHIVE_ALL.equals(archivefileEO.getArchiveStatus())) {
				eo.setArchiveStatus(ConstantUtils.EV_REPORT_ARCHIVE_PAPER);
			}else {
				//未归档
				eo.setArchiveStatus(ConstantUtils.EV_REPORT_ARCHIVE_NOT);
			}
		}
		//更新
		reportArchivefileEODao.updateByPrimaryKeySelective(eo);
		//反向更新报告归档状态
		updateReportArchiveStatus(eo.getReportId());
		return eo;
	}
	
	
	/**
	 * 根据报告id更新报告归档状态
	* @Title：updateReportArchiveStatus
	* @param reportId
	* @throws Exception
	* @return: void
	* @author： ljy  
	* @date： 2019年9月26日
	 */
	public void  updateReportArchiveStatus(String reportId) throws Exception {
		//反向更新报告归档状态
		List<ReportArchivefileEO> list = reportArchivefileEODao.
				findListByReportId(reportId);
		//循环校验是否报告下的所有文件都已归档
		Boolean isArchive = false;
		for (int i = 0; i < list.size(); i++) {
			if(ConstantUtils.EV_REPORT_ARCHIVE_NOT.equals(list.get(i).getArchiveStatus())) {
				isArchive = false;
				break;
			}
			isArchive = true;
		}
		//如果全部归档, 则更新报告表中的归档状态为"已归档"
		TrialReportEO reportEO = trialReportEOService.selectByPrimaryKey(reportId);
		if(StringUtils.isNotEmpty(reportEO)) {
			if(isArchive) {
				//1-已归档
				reportEO.setArchiveStatus("1");
			}else {
				//1-未归档
				reportEO.setArchiveStatus("0");
			}
			//更新
			trialReportEOService.updateByPrimaryKeySelective(reportEO);
		}
		
	}
	
	/**
	 * 根据附件id 获取路径
	* @Title：getPath
	* @param attachId
	* @param isWaterMark
	* @return
	* @return: String
	* @author： ljy  
	* @date： 2019年9月26日
	 */
	public String getPath(String attachId, String isWaterMark) {
		//定义返回值
    	String path = "";
		//获取附件信息
		TsAttachmentEO attach = tsAttachmentEODao.selectByPrimaryKey(attachId);
		if(StringUtils.isNotEmpty(attach) && ConstantUtils.NO.equals(isWaterMark)) {
			//返回路径
			path = attach.getSavePath();
		}else if(StringUtils.isNotEmpty(attach) && ConstantUtils.YES.equals(isWaterMark)) {
			//返回路径
			path = attach.getWaterMarkPath();
		}
		return path;
	}
	
	
	/**
	 * 保存至报告附件汇总表中
	* @Title：saveReportAchivefile
	* @param reportFiletype
	* @param reportFileno
	* @param trialReportEO
	* @return: void
	* @author： ljy  
	* @date： 2019年8月26日
	 */
	public void saveReportAchivefile(String reportFiletype, String reportFileno, 
			String fileId, TrialReportEO trialReportEO) {
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		//将报告涉及的附件保存至报告附件汇总表(EV_TRIALREPORT_ARCHIVEFILE)
		ReportArchivefileEO archivefileEO = new ReportArchivefileEO();
		archivefileEO.setId(UUID.randomUUID());
		//删除标记  0 未删除;  1删除
		archivefileEO.setDelFlag("0"); 
		archivefileEO.setCreateBy(UserUtils.getUserId());
		archivefileEO.setCreateTime(date);
		archivefileEO.setUpdateBy(UserUtils.getUserId());
		archivefileEO.setUpdateTime(date);
		//文件附件id
		archivefileEO.setReportFileid(fileId);
		//归档状态 0-未归档(默认)
		archivefileEO.setArchiveStatus("0");
		//获取附件信息
		TsAttachmentEO eo = tsAttachmentEODao.selectByPrimaryKey(fileId);
		//文件名称
		archivefileEO.setReportFilename(eo.getAttachmentName());
		//文件编号
		archivefileEO.setReportFileno(reportFileno);
		//文件类型试验数据 - reportData ; PDF - reportPDF
		archivefileEO.setReportFiletype(reportFiletype);
		//报告id
		archivefileEO.setReportId(trialReportEO.getId());
		//试验任务id
		archivefileEO.setTrialtaskId(trialReportEO.getTrialtaskId());
		//保存
		reportArchivefileEODao.insert(archivefileEO);
	}
	
	/**
	 * 将报告附件汇总表(EV_TRIALREPORT_ARCHIVEFILE)中的附件删除
	* @Title：deleteByReportId
	* @param reportId 报告id
	* @return: void
	* @author： ljy  
	* @date： 2019年8月29日
	 */
	public void deleteByReportId(String reportId) {
		reportArchivefileEODao.deleteByReportId(reportId);
	}

	/**
	 * 通过附件id删除信息
	 * @Title: deleteByFileId
	 * @param reportFileid
	 * @return void
	 * @author: ljy
	 * @date: 2019年12月12日
	 */
	public void deleteByFileId(String reportFileid) {
		reportArchivefileEODao.deleteByFileId(reportFileid);
	}
	
}
