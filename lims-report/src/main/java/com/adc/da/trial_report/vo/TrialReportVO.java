package com.adc.da.trial_report.vo;

public class TrialReportVO {

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
     * 报告模板附件id
     */
    private String reportFileid;
    /**
     * 附件id 只允许上传PDF格式
     */
    private String accessoryFileid;
    /**
     * PDF附件id
     */
    private String pdfFileid;
    /**
     * 试验报告检验项目id
     */
    private String trialtaskInsproids;
    /**
     * 报告编号
     */
    private String reportNo;
    /**
     * 试验任务id
     */
    private String trialtaskId;
    /**
     * 试验结果
     *  0-合格
     *  1-不合格
     *  2-不做判定
     */
    private String trialTaskResult;
    /**
     * 备注
     */
    private String remark;

    
    
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

	public String getReportFileid() {
		return reportFileid;
	}

	public void setReportFileid(String reportFileid) {
		this.reportFileid = reportFileid;
	}

	public String getAccessoryFileid() {
		return accessoryFileid;
	}

	public void setAccessoryFileid(String accessoryFileid) {
		this.accessoryFileid = accessoryFileid;
	}

	public String getPdfFileid() {
		return pdfFileid;
	}

	public void setPdfFileid(String pdfFileid) {
		this.pdfFileid = pdfFileid;
	}

	public String getTrialtaskInsproids() {
		return trialtaskInsproids;
	}

	public void setTrialtaskInsproids(String trialtaskInsproids) {
		this.trialtaskInsproids = trialtaskInsproids;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
}
