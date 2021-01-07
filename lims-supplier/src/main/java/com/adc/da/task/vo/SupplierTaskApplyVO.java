package com.adc.da.task.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/22 9:49
 * @description：${description}
 */
public class SupplierTaskApplyVO {

    private String id;

    @ApiModelProperty("任务委派人员ID")
    private String createBy;

    @ApiModelProperty("任务委派人员")
    private String createByName;

    @ApiModelProperty("委托方ID")
    private String applyId;

    @ApiModelProperty("委托方名称")
    private String applyName;

    @ApiModelProperty("受托方ID")
    private String beApplyForId;

    @ApiModelProperty("受托方名称")
    private String beApplyForName;

    @ApiModelProperty("委托时间")
    private String applyTime;

    @ApiModelProperty("委托编号")
    private String applyNo;

    @ApiModelProperty("任务来源")
    private String taskSource;

    @ApiModelProperty("计划开始时间")
    private String planStartTime;

    @ApiModelProperty("计划结束时间")
    private String planEndTime;

    @ApiModelProperty("频次")
    private Integer frequency;

    @ApiModelProperty("频次单位")
    private String frequencyUnit;

    @ApiModelProperty("任务类型")
    private Integer taskType;

    @ApiModelProperty("状态（0，草稿；1，进行中,；2，已完成；3，待确认,；4，结束）")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("意见")
    private String idea;

    @ApiModelProperty("是否接受任务")
    private Integer whetherAccept;

    @ApiModelProperty("反馈时间")
    private String feddbackTime;

    @ApiModelProperty("外包供应商负责人ID")
    private String supplierPeopleId;

    @ApiModelProperty("外包供应商负责人")
    private String supplierPeopleName;

    @ApiModelProperty("试验任务书ID")
    private String trialTaskId;

    @ApiModelProperty("任务标准信息")
    private List<SupplierTaskInfoVO> supplierTaskInfoVOS;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getBeApplyForId() {
        return beApplyForId;
    }

    public void setBeApplyForId(String beApplyForId) {
        this.beApplyForId = beApplyForId;
    }

    public String getBeApplyForName() {
        return beApplyForName;
    }

    public void setBeApplyForName(String beApplyForName) {
        this.beApplyForName = beApplyForName;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getTaskSource() {
        return taskSource;
    }

    public void setTaskSource(String taskSource) {
        this.taskSource = taskSource;
    }

    public String getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(String planStartTime) {
        this.planStartTime = planStartTime;
    }

    public String getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(String planEndTime) {
        this.planEndTime = planEndTime;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getFrequencyUnit() {
        return frequencyUnit;
    }

    public void setFrequencyUnit(String frequencyUnit) {
        this.frequencyUnit = frequencyUnit;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public Integer getWhetherAccept() {
        return whetherAccept;
    }

    public void setWhetherAccept(Integer whetherAccept) {
        this.whetherAccept = whetherAccept;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getFeddbackTime() {
        return feddbackTime;
    }

    public void setFeddbackTime(String feddbackTime) {
        this.feddbackTime = feddbackTime;
    }

    public String getSupplierPeopleId() {
        return supplierPeopleId;
    }

    public void setSupplierPeopleId(String supplierPeopleId) {
        this.supplierPeopleId = supplierPeopleId;
    }

    public String getSupplierPeopleName() {
        return supplierPeopleName;
    }

    public void setSupplierPeopleName(String supplierPeopleName) {
        this.supplierPeopleName = supplierPeopleName;
    }

    public List<SupplierTaskInfoVO> getSupplierTaskInfoVOS() {
        return supplierTaskInfoVOS;
    }

    public void setSupplierTaskInfoVOS(List<SupplierTaskInfoVO> supplierTaskInfoVOS) {
        this.supplierTaskInfoVOS = supplierTaskInfoVOS;
    }

    public String getTrialTaskId() {
        return trialTaskId;
    }

    public void setTrialTaskId(String trialTaskId) {
        this.trialTaskId = trialTaskId;
    }

}
