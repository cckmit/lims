package com.adc.da.task.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/22 8:18
 * @description：${description}
 */
public class SupplierTaskInfoDTO {

    /**
     *
     */
    private String id;

    /**
     * 任务id
     */
    private String infoId;

    @ApiModelProperty("类别")
    private String type;

    /**
     * 项目
     */
    @ApiModelProperty("项目")
    private String project;

    /**
     * 工作内容
     */
    @ApiModelProperty("工作内容")
    private String jobContent;

    /**
     * 标准人数
     */
    @ApiModelProperty("标准人数")
    private String peopleNumber;

    /**
     * 标准工时
     */
    @ApiModelProperty("标准工时")
    private String workHour;

    /**
     * 标准工时单位
     */
    @ApiModelProperty("标准工时单位")
    private String workHourUnit;

    /**
     * 输出物
     */
    @ApiModelProperty("输出物")
    private String outputContent;

    @ApiModelProperty("任务状态")
    private Integer taskStatus;

    @ApiModelProperty("样车ID")
    private String saCarId;

    @ApiModelProperty("任务实施人员ID")
    private String taskImplementerId;

    @ApiModelProperty("领料单编号")
    private String pcPartNo;

    
    @ApiModelProperty("单价")
    private String unitPrice;
    
    
    public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
    
    public String getPcPartNo() {
        return pcPartNo;
    }

    public void setPcPartNo(String pcPartNo) {
        this.pcPartNo = pcPartNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getJobContent() {
        return jobContent;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    public String getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(String peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public String getWorkHour() {
        return workHour;
    }

    public void setWorkHour(String workHour) {
        this.workHour = workHour;
    }

    public String getWorkHourUnit() {
        return workHourUnit;
    }

    public void setWorkHourUnit(String workHourUnit) {
        this.workHourUnit = workHourUnit;
    }

    public String getOutputContent() {
        return outputContent;
    }

    public void setOutputContent(String outputContent) {
        this.outputContent = outputContent;
    }

    public String getSaCarId() {
        return saCarId;
    }

    public void setSaCarId(String saCarId) {
        this.saCarId = saCarId;
    }

    public String getTaskImplementerId() {
        return taskImplementerId;
    }

    public void setTaskImplementerId(String taskImplementerId) {
        this.taskImplementerId = taskImplementerId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }
}
