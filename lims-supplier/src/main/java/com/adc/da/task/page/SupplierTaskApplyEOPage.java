package com.adc.da.task.page;

import com.adc.da.base.page.BasePage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * <b>功能：</b>SUP_SUPPLIER_TASK_APPLY SupplierTaskApplyEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-19 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SupplierTaskApplyEOPage extends BasePage {

    @ApiModelProperty("试验任务ID")
    private String trialTaskId;

    @ApiModelProperty("受托方名称")
    private String beApplyForName;

    @ApiModelProperty("申请时间")
    private String applyTime;

    @ApiModelProperty("申请时间结束")
    private String applyTimeEnd;

    @ApiModelProperty("申请时间")
    private String createTime1;

    @ApiModelProperty("申请时间结束")
    private String createTime2;

    @ApiModelProperty("委托编号")
    private String applyNo;

    @ApiModelProperty("任务来源")
    private String taskSource;

    @ApiModelProperty("计划开始时间")
    private String planStartTime;

    @ApiModelProperty("计划开始时间结束")
    private String planStartTimeEnd;

    @ApiModelProperty("计划结束时间")
    private String planEndTime;

    @ApiModelProperty("计划结束时间结束")
    private String planEndTimeEnd;

    @ApiModelProperty("频次")
    private String frequency;

    @ApiModelProperty("状态（0，草稿；1，审批中；2，进行中；3，已完成,；4，已退回；5，已取消）")
    private Integer status;

    @ApiModelProperty("任务委派人员")
    private String createByName;

    @ApiModelProperty("任务委派人员ID")
    private String createBy;

    @ApiModelProperty("车辆型号")
    private String carType;

    @ApiModelProperty("底盘号")
    private String underpanNO;

    /**
     * 通用查询
     */
    @ApiModelProperty(value = "通用查询")
    private String searchPhrase;

    /**
     * 通过查询条件集合
     */
    @JsonIgnore
    private List<String> keyWords;

    public String getTrialTaskId() {
        return trialTaskId;
    }

    public void setTrialTaskId(String trialTaskId) {
        this.trialTaskId = trialTaskId;
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

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }


    public String getUnderpanNO() {
        return underpanNO;
    }

    public String getCreateTime1() {
        return createTime1;
    }

    public void setCreateTime1(String createTime1) {
        this.createTime1 = createTime1;
    }

    public String getCreateTime2() {
        return createTime2;
    }

    public void setCreateTime2(String createTime2) {
        this.createTime2 = createTime2;
    }

    public void setUnderpanNO(String underpanNO) {
        this.underpanNO = underpanNO;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public String getApplyTimeEnd() {
        return applyTimeEnd;
    }

    public void setApplyTimeEnd(String applyTimeEnd) {
        this.applyTimeEnd = applyTimeEnd;
    }

    public String getPlanStartTimeEnd() {
        return planStartTimeEnd;
    }

    public void setPlanStartTimeEnd(String planStartTimeEnd) {
        this.planStartTimeEnd = planStartTimeEnd;
    }

    public String getPlanEndTimeEnd() {
        return planEndTimeEnd;
    }

    public void setPlanEndTimeEnd(String planEndTimeEnd) {
        this.planEndTimeEnd = planEndTimeEnd;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
