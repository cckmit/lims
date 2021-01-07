package com.adc.da.task.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/9/2 16:15
 * @description：外包供应商统计模块vo
 */
public class SupplierTaskStatisticsVO {

    @ApiModelProperty("")
    private String id;

    @ApiModelProperty("申请编号")
    private String applyNo;

    @ApiModelProperty("受托方名称")
    private String supName;

    @ApiModelProperty("工作内容")
    private String jobContent;

    @ApiModelProperty("标准人数")
    private String peopleNumber;

    @ApiModelProperty("实际人数")
    private String realPeopleNumber;

    @ApiModelProperty("标准工时")
    private String workHour;

    @ApiModelProperty("实际工时")
    private String realWorkHour;

    @ApiModelProperty("输出物")
    private String outputContent;

    @ApiModelProperty("任务负责人")
    private String usName;

    @ApiModelProperty("任务状态")
    private Integer taskStatus;

    @ApiModelProperty("任务完成时间")
    private String taskEndTime;

    @JsonIgnore
    private String attachmentId;

    @ApiModelProperty("完成凭证附件vo")
    private List<SupplierAttachmentVO> supplierAttachmentVO;

    @ApiModelProperty("车辆型号")
    private String carType;

    @ApiModelProperty("底盘号")
    private String underpanNO;
    @ApiModelProperty("总价")
    private String totalPrice;

    @ApiModelProperty("单价")
    private String unitPrice;
    
    
    
    public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
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

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
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

    public String getRealPeopleNumber() {
        return realPeopleNumber;
    }

    public void setRealPeopleNumber(String realPeopleNumber) {
        this.realPeopleNumber = realPeopleNumber;
    }

    public String getWorkHour() {
        return workHour;
    }

    public void setWorkHour(String workHour) {
        this.workHour = workHour;
    }

    public String getRealWorkHour() {
        return realWorkHour;
    }

    public void setRealWorkHour(String realWorkHour) {
        this.realWorkHour = realWorkHour;
    }

    public String getOutputContent() {
        return outputContent;
    }

    public void setOutputContent(String outputContent) {
        this.outputContent = outputContent;
    }

    public String getUsName() {
        return usName;
    }

    public void setUsName(String usName) {
        this.usName = usName;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(String taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public List<SupplierAttachmentVO> getSupplierAttachmentVO() {
        return supplierAttachmentVO;
    }

    public void setSupplierAttachmentVO(List<SupplierAttachmentVO> supplierAttachmentVO) {
        this.supplierAttachmentVO = supplierAttachmentVO;
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
}
