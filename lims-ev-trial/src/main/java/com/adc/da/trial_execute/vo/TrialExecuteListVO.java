package com.adc.da.trial_execute.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/9/10 14:04
 * @description：${description}
 */
public class TrialExecuteListVO {

    private String id;
    @ApiModelProperty("检验项目")
    private String proName;

    @ApiModelProperty("检验标准")
    private String insproStandard;

    @ApiModelProperty("负责试验室")
    private String orgName;

    @ApiModelProperty("负责工程师")
    private String usName;

    @ApiModelProperty("试验开始时间")
    private String createTime;

    @ApiModelProperty("试验结束时间")
    private String endTime;

    @ApiModelProperty("报告完成时间")
    private String reportFinishTime;

    @ApiModelProperty("试验进度")
    private String trialprojectRate;

    @ApiModelProperty("报告状态")
    private String reportStatus;

    @ApiModelProperty("报告文件名称")
    private String attachmentName;

    @ApiModelProperty("报告ID")
    private String reportId;
    
    @ApiModelProperty("报告附件id")
    private String reportFileId;

    @ApiModelProperty("流程ID")
    private String busId;
    
    @ApiModelProperty("PDFID")
    private String pdfFileId;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

	public String getReportFileId() {
		return reportFileId;
	}

	public void setReportFileId(String reportFileId) {
		this.reportFileId = reportFileId;
	}

	public String getPdfFileId() {
		return pdfFileId;
	}

	public void setPdfFileId(String pdfFileId) {
		this.pdfFileId = pdfFileId;
	}
    
    
}
