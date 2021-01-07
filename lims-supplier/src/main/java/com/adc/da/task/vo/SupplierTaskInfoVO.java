package com.adc.da.task.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/22 9:52
 * @description：${description}
 */
public class SupplierTaskInfoVO {

    /**
     *
     */
    private String id;

    /**
     *
     */
    private String infoId;

    /**
     * 委托单编号
     */
    @ApiModelProperty("委托单编号")
    private String applyNo;

    /**
     * 类别
     */
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

    @ApiModelProperty("车辆型号")
    private String carType;

    @ApiModelProperty("底盘号")
    private String underpanNO;

    @ApiModelProperty("状态（0，草稿；1，进行中,；2，已完成；3，待确认,；4，结束）")
    private Integer taskStatus;

    @JsonIgnore
    private String attachmentId;

    @ApiModelProperty("交付物文件")
    List<SupplierAttachmentVO> supplierAttachmentVOList;

    @ApiModelProperty("样车ID")
    private String saCarId;

    @ApiModelProperty("任务实施人员ID")
    private String taskImplementerId;

    @ApiModelProperty("任务实施人员Name")
    private String taskImplementerName;

    @ApiModelProperty("领料单编号")
    private String pcPartNo;

    @ApiModelProperty("任务完成反馈信息ID")
    private String taskFinishInfoId;

    @ApiModelProperty("单价")
    private String unitPrice;
    
    
    public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
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

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getUnderpanNO() {
        return underpanNO;
    }

    public void setUnderpanNO(String underpanNO) {
        this.underpanNO = underpanNO;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
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

    public List<SupplierAttachmentVO> getSupplierAttachmentVOList() {
        return supplierAttachmentVOList;
    }

    public void setSupplierAttachmentVOList(List<SupplierAttachmentVO> supplierAttachmentVOList) {
        this.supplierAttachmentVOList = supplierAttachmentVOList;
    }

    public String getTaskImplementerName() {
        return taskImplementerName;
    }

    public void setTaskImplementerName(String taskImplementerName) {
        this.taskImplementerName = taskImplementerName;
    }

    public String getPcPartNo() {
        return pcPartNo;
    }

    public void setPcPartNo(String pcPartNo) {
        this.pcPartNo = pcPartNo;
    }

    public String getTaskFinishInfoId() {
        return taskFinishInfoId;
    }

    public void setTaskFinishInfoId(String taskFinishInfoId) {
        this.taskFinishInfoId = taskFinishInfoId;
    }
}
