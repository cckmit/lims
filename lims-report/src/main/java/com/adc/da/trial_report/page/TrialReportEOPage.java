package com.adc.da.trial_report.page;

import java.util.List;

import com.adc.da.base.page.BasePage;

public class TrialReportEOPage extends BasePage {
   /**
    * 通用查询条件
    */
    private List<String> searchPhrase;
	/**
	 * 主键
	 */
    private String id;
    /**
     * 报告状态 0-无报告(默认)
			1-编辑报告
			2-审核报告
			3-档案室
			4-退回
			5-撤回
     */
    private String reportStatus;
    /**
     * 报告编号
     */
    private String reportNo;
    /**
     * 试验结果
     *  0-合格
     *  1-不合格
     *  2-不做判定
     */
    private String trialTaskResult;
    /**
     * 归档状态
     *  0-未归档(默认)
     *  1-已归档
     */
    private String archiveStatus;
    //-----------冗余字段:VIN码,检验项目名称  用于通用查询---------------//
    /**
     * VIN码
     */
    private String VINCode;
    /**
     * 检验项目名称 多个用英文逗号隔开
     */
    private String insProNames;
    
    //-----------关联查询:用户名,组织机构名,试验委托编号--------------//
    /**
     * 用户姓名(试验工程师)
     */
    private String engineerUserName;
    /**
     * 组织机构名
     */
    private String orgName;
    
    /**
     * 试验任务id
     */
    private String trialtaskId;
    /**
     * 试验结束时间 --start 
     */
    private String trialFinishDate1;
    /**
     * 试验结束时间 --end 
     */
    private String trialFinishDate2;
    
    /**
     * 试验结束时间
     */
    private String updateTime;
    
    /**
     * 创建人
     */
    private String createBy;
    
    /**
     * 委托编码
     */
    private String taskCode;
    
    /**
     * 委托人员
     */
    private String originator;
    
    /**
     * 用户id集合,用于过滤数据
     */
    private List<String> createByIds;
    /**
     * 报告类型
     * PV
     * CV
     * EV
     */
    private String reportType;
    
    
    public String getReportType() {
		return reportType;
	}


	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
    
	public List<String> getCreateByIds() {
		return createByIds;
	}
	public void setCreateByIds(List<String> createByIds) {
		this.createByIds = createByIds;
	}
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}
	
	public String getArchiveStatus() {
		return archiveStatus;
	}
	public void setArchiveStatus(String archiveStatus) {
		this.archiveStatus = archiveStatus;
	}
	public String getVINCode() {
		return VINCode;
	}
	public void setVINCode(String vINCode) {
		VINCode = vINCode;
	}
	public String getInsProNames() {
		return insProNames;
	}
	public void setInsProNames(String insProNames) {
		this.insProNames = insProNames;
	}
	public String getEngineerUserName() {
		return engineerUserName;
	}
	public void setEngineerUserName(String engineerUserName) {
		this.engineerUserName = engineerUserName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getTrialFinishDate1() {
		return trialFinishDate1;
	}
	public void setTrialFinishDate1(String trialFinishDate1) {
		this.trialFinishDate1 = trialFinishDate1;
	}
	public String getTrialFinishDate2() {
		return trialFinishDate2;
	}
	public void setTrialFinishDate2(String trialFinishDate2) {
		this.trialFinishDate2 = trialFinishDate2;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public String getTrialtaskId() {
		return trialtaskId;
	}
	public void setTrialtaskId(String trialtaskId) {
		this.trialtaskId = trialtaskId;
	}
	public String getTrialTaskResult() {
		return trialTaskResult;
	}
	public void setTrialTaskResult(String trialTaskResult) {
		this.trialTaskResult = trialTaskResult;
	}
	public List<String> getSearchPhrase() {
		return searchPhrase;
	}
	public void setSearchPhrase(List<String> searchPhrase) {
		this.searchPhrase = searchPhrase;
	}


	public String getTaskCode() {
		return taskCode;
	}


	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}


	public String getOriginator() {
		return originator;
	}


	public void setOriginator(String originator) {
		this.originator = originator;
	}
    
}
