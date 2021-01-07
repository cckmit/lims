package com.adc.da.task.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/20 14:58
 * @description：${description}
 */
public class SupplierTaskApplyPageVO {

    @ApiModelProperty("")
    private String id;

    @ApiModelProperty("受托方名称")
    private String beApplyForName;

    @ApiModelProperty("申请时间")
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
    private String frequency;

    @ApiModelProperty("状态（0，草稿；1，进行中,；2，已完成；3，待确认,；4，结束）")
    private Integer status;

    @ApiModelProperty("任务委派人员")
    private String createByName;

    // @ApiModelProperty("车型信息")
    // List<SaCarTypeVO> saCarDataVOList;

    @ApiModelProperty("任务类型")
    private Integer taskType;

    @ApiModelProperty("车辆型号")
    private String carType;

    @ApiModelProperty("底盘号")
    private String underpanNO;

    @ApiModelProperty("试验任务书ID")
    private String trialTaskId;

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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    // public List<SaCarTypeVO> getSaCarDataVOList() {
    //     return saCarDataVOList;
    // }
    //
    // public void setSaCarDataVOList(List<SaCarTypeVO> saCarDataVOList) {
    //     this.saCarDataVOList = saCarDataVOList;
    // }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getTrialTaskId() {
        return trialTaskId;
    }

    public void setTrialTaskId(String trialTaskId) {
        this.trialTaskId = trialTaskId;
    }
}
