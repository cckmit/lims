package com.adc.da.trial_report.vo;

public class TrialReportSearchVO {

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
    /**
     * 试验任务id
     */
    private String trialtaskId;
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
     * 试验结束时间
     */
    private String createTime;
    
    /**
     * 委托编码
     */
    private String taskCode;
    
    /**
     * 委托人员
     */
    private String originator;
    
	

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getTrialTaskResult() {
		return trialTaskResult;
	}

	public void setTrialTaskResult(String trialTaskResult) {
		this.trialTaskResult = trialTaskResult;
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

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
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

	public String getTrialtaskId() {
		return trialtaskId;
	}

	public void setTrialtaskId(String trialtaskId) {
		this.trialtaskId = trialtaskId;
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
