package com.adc.da.pc_report.page;

import java.util.List;

import com.adc.da.base.page.BasePage;

/**
 * 报告VO
 * @author zhangfeng
 *
 */

public class ReportPage extends BasePage{
	private String id;
	private String trialTaskId;
	private String trialTaskNum;
	private String trialTaskTitle;
	private String trialReportNum;
	private String trialengineerUserName;
	private String trialtaskInsproid;
	
	private String createTime;
	private String reportFileId;
	private String reportPDFid;
	

	/**
	    * 通用查询条件
	    */
	private List<String> searchPhrase;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTrialTaskId() {
		return trialTaskId;
	}
	public void setTrialTaskId(String trialTaskId) {
		this.trialTaskId = trialTaskId;
	}
	public String getTrialTaskNum() {
		return trialTaskNum;
	}
	public void setTrialTaskNum(String trialTaskNum) {
		this.trialTaskNum = trialTaskNum;
	}
	public String getTrialTaskTitle() {
		return trialTaskTitle;
	}
	public void setTrialTaskTitle(String trialTaskTitle) {
		this.trialTaskTitle = trialTaskTitle;
	}
	public String getTrialReportNum() {
		return trialReportNum;
	}
	public void setTrialReportNum(String trialReportNum) {
		this.trialReportNum = trialReportNum;
	}
	public String getTrialengineerUserName() {
		return trialengineerUserName;
	}
	public void setTrialengineerUserName(String trialengineerUserName) {
		this.trialengineerUserName = trialengineerUserName;
	}
	public String getTrialtaskInsproid() {
		return trialtaskInsproid;
	}
	public void setTrialtaskInsproid(String trialtaskInsproid) {
		this.trialtaskInsproid = trialtaskInsproid;
	}
	public List<String> getSearchPhrase() {
		return searchPhrase;
	}
	public void setSearchPhrase(List<String> searchPhrase) {
		this.searchPhrase = searchPhrase;
	}
	
	public String getReportFileId() {
		return reportFileId;
	}
	public void setReportFileId(String reportFileId) {
		this.reportFileId = reportFileId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getReportPDFid() {
		return reportPDFid;
	}
	public void setReportPDFid(String reportPDFid) {
		this.reportPDFid = reportPDFid;
	}

}
