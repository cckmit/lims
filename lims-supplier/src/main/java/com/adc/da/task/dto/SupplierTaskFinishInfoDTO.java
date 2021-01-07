package com.adc.da.task.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/27 16:56
 * @description：${description}
 */
public class SupplierTaskFinishInfoDTO {

    private String id;
    @ApiModelProperty("任务ID")
    private String supTaskInfoId;

    @ApiModelProperty("是否异常")
    private Integer whetherException;

    @ApiModelProperty("异常描述")
    private String exceptionDescription;

    @ApiModelProperty("任务状态")
    private String taskStatus;

    @ApiModelProperty("实际人数")
    private String realPeopleNumber;

    @ApiModelProperty("实际工时")
    private String realWorkHour;

    @ApiModelProperty("任务开始时间")
    private String taskStartTime;

    @ApiModelProperty("任务结束时间")
    private String taskEndTime;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("附件ID")
    private String attachmentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupTaskInfoId() {
        return supTaskInfoId;
    }

    public void setSupTaskInfoId(String supTaskInfoId) {
        this.supTaskInfoId = supTaskInfoId;
    }

    public Integer getWhetherException() {
        return whetherException;
    }

    public void setWhetherException(Integer whetherException) {
        this.whetherException = whetherException;
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }

    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getRealPeopleNumber() {
        return realPeopleNumber;
    }

    public void setRealPeopleNumber(String realPeopleNumber) {
        this.realPeopleNumber = realPeopleNumber;
    }

    public String getRealWorkHour() {
        return realWorkHour;
    }

    public void setRealWorkHour(String realWorkHour) {
        this.realWorkHour = realWorkHour;
    }

    public String getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(String taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public String getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(String taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }
}
