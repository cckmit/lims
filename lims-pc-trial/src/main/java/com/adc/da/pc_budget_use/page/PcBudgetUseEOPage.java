package com.adc.da.pc_budget_use.page;

import com.adc.da.base.page.BasePage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * <b>功能：</b>PC_BUDGET_USE PcBudgetUseEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-06 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcBudgetUseEOPage extends BasePage {

    @ApiModelProperty(value = "试验id")
    private String trialId;
    @ApiModelProperty(value = "试验编号")
    private String trialCode;
    @ApiModelProperty(value = "申请单编号")
    private String code;
    @ApiModelProperty(value = "总计")
    private String budgetTotal;
    @ApiModelProperty(value = "实验开始时间")
    private String trialStartTimeStart;
    @ApiModelProperty(value = "实验开始时间")
    private String trialStartTimeEnd;
    @ApiModelProperty(value = "实验结束时间")
    private String trialEndTimeStart;
    @ApiModelProperty(value = "实验结束时间")
    private String trialEndTimeEnd;
    @ApiModelProperty(value = "试验项目地点")
    private String trialLocation;
    @ApiModelProperty("试验任务编号")
    private String taskNumber;
    @ApiModelProperty("供应商ID")
    private String supId;

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

	/**
	 * 区分试验任务还是执行计划
	 * 0-试验任务；1-执行计划
	 */
    @ApiModelProperty("区分试验任务还是执行计划: 0-试验任务；1-执行计划")
	private String taskOrPlan;
    
    
    
    public String getTaskOrPlan() {
		return taskOrPlan;
	}

	public void setTaskOrPlan(String taskOrPlan) {
		this.taskOrPlan = taskOrPlan;
	}

	public String getTrialId() {
        return trialId;
    }

    public void setTrialId(String trialId) {
        this.trialId = trialId;
    }

    public String getTrialCode() {
        return trialCode;
    }

    public void setTrialCode(String trialCode) {
        this.trialCode = trialCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBudgetTotal() {
        return budgetTotal;
    }

    public void setBudgetTotal(String budgetTotal) {
        this.budgetTotal = budgetTotal;
    }

    public String getTrialStartTimeStart() {
        return trialStartTimeStart;
    }

    public void setTrialStartTimeStart(String trialStartTimeStart) {
        this.trialStartTimeStart = trialStartTimeStart;
    }

    public String getTrialStartTimeEnd() {
        return trialStartTimeEnd;
    }

    public void setTrialStartTimeEnd(String trialStartTimeEnd) {
        this.trialStartTimeEnd = trialStartTimeEnd;
    }

    public String getTrialEndTimeStart() {
        return trialEndTimeStart;
    }

    public void setTrialEndTimeStart(String trialEndTimeStart) {
        this.trialEndTimeStart = trialEndTimeStart;
    }

    public String getTrialEndTimeEnd() {
        return trialEndTimeEnd;
    }

    public void setTrialEndTimeEnd(String trialEndTimeEnd) {
        this.trialEndTimeEnd = trialEndTimeEnd;
    }

    public String getTrialLocation() {
        return trialLocation;
    }

    public void setTrialLocation(String trialLocation) {
        this.trialLocation = trialLocation;
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

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }
}
