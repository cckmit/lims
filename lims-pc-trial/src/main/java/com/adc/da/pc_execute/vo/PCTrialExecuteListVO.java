package com.adc.da.pc_execute.vo;

public class PCTrialExecuteListVO {
	/**
	 * 试验检验项目id
	 */
    private String id;
    /**
     * 检验项目
     */
    private String proName;

    /**
     * 检验标准
     */
    private String insproStandard;

    /**
     * 负责试验室
     */
    private String orgName;
    /**
     * 负责工程师
     */
    private String usName;

    /**
     * 试验开始时间
     */
    private String createTime;
    
    /**
     * 试验结束时间
     */
    private String endTime;

    /**
     * 报告完成时间
     */
    private String reportFinishTime;

    /**
     * 试验进度
     */
    private String trialprojectRate;

    /**
     * 报告状态
     */
    private String reportStatus;

    /**
     * 报告文件名称
     */
    private String attachmentName;

    /**
     * 报告ID
     */
    private String reportId;
    
    /**
     * 报告附件id
     */
    private String reportFileId;

    /**
     * 流程ID
     */
    private String busId;
    
    /**
     * 报告pdf文件id
     */
    private String pdfFileId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getInsproStandard() {
		return insproStandard;
	}

	public void setInsproStandard(String insproStandard) {
		this.insproStandard = insproStandard;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getUsName() {
		return usName;
	}

	public void setUsName(String usName) {
		this.usName = usName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getReportFinishTime() {
		return reportFinishTime;
	}

	public void setReportFinishTime(String reportFinishTime) {
		this.reportFinishTime = reportFinishTime;
	}

	public String getTrialprojectRate() {
		return trialprojectRate;
	}

	public void setTrialprojectRate(String trialprojectRate) {
		this.trialprojectRate = trialprojectRate;
	}

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportFileId() {
		return reportFileId;
	}

	public void setReportFileId(String reportFileId) {
		this.reportFileId = reportFileId;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getPdfFileId() {
		return pdfFileId;
	}

	public void setPdfFileId(String pdfFileId) {
		this.pdfFileId = pdfFileId;
	}
    
	
    
}
