package com.adc.da.pc_budget_cash_out.page;

import com.adc.da.base.page.BasePage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * <b>功能：</b>PC_BUDGET_CASH_OUT PcBudgetCashOutEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-29 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcBudgetCashOutEOPage extends BasePage {

    @ApiModelProperty("PC实验任务ID")
    private String trialId;
    @ApiModelProperty("编号")
    private String code;
    @ApiModelProperty("申请日期开始")
    private String applyDateStart;
    @ApiModelProperty("申请日期结束")
    private String applyDateEnd;
    @ApiModelProperty("收款人姓名")
    private String payeeName;
    @ApiModelProperty("收款部门名称")
    private String payeeOrgName;
    @ApiModelProperty("开户银行")
    private String bankAccount;
    @ApiModelProperty("请款金额")
    private String requestPayoutAmount;
    @ApiModelProperty("试验任务编号")
    private String taskNumber;

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

    public String getTrialId() {
        return trialId;
    }

    public void setTrialId(String trialId) {
        this.trialId = trialId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getApplyDateStart() {
        return applyDateStart;
    }

    public void setApplyDateStart(String applyDateStart) {
        this.applyDateStart = applyDateStart;
    }

    public String getApplyDateEnd() {
        return applyDateEnd;
    }

    public void setApplyDateEnd(String applyDateEnd) {
        this.applyDateEnd = applyDateEnd;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeOrgName() {
        return payeeOrgName;
    }

    public void setPayeeOrgName(String payeeOrgName) {
        this.payeeOrgName = payeeOrgName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getRequestPayoutAmount() {
        return requestPayoutAmount;
    }

    public void setRequestPayoutAmount(String requestPayoutAmount) {
        this.requestPayoutAmount = requestPayoutAmount;
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
}
