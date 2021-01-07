package com.adc.da.pc_budget_cash_out.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * <b>功能：</b>PC_BUDGET_CASH_OUT PcBudgetCashOutEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-29 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcBudgetCashOutEO extends BaseEntity {

    private String id;
    private Integer delFlag;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    @ApiModelProperty("PC实验任务ID")
    private String trialId;
    @ApiModelProperty("PC实验任务编码")
    private String trialCode;
    @ApiModelProperty("PC实验任务项目名称")
    private String trialProjectName;
    @ApiModelProperty("编号")
    private String code;
    @ApiModelProperty("请款人ID")
    private String requestPayoutId;
    @ApiModelProperty("请款人名称")
    private String requestPayoutName;
    @ApiModelProperty("请款部门ID")
    private String requestPayoutOrgId;
    @ApiModelProperty("请款部门名称")
    private String requestPayoutOrgName;
    @ApiModelProperty("申请日期")
    private String applyDate;
    @ApiModelProperty("收款人ID")
    private String payeeId;
    @ApiModelProperty("收款人姓名")
    private String payeeName;
    @ApiModelProperty("收款部门ID")
    private String payeeOrgId;
    @ApiModelProperty("收款部门名称")
    private String payeeOrgName;
    @ApiModelProperty("银行账户")
    private String bankAccount;
    @ApiModelProperty("汇入地点")
    private String bankIntoSite;
    @ApiModelProperty("汇入行名称")
    private String bankIntoSiteName;
    @ApiModelProperty("请款金额")
    private String requestPayoutAmount;
    @ApiModelProperty("状态（0,草稿；1，待审批；2已审批；3，撤回中；4，已撤回；5，已完成；6，退回）")
    private String status;
    @ApiModelProperty("当前代办人id")
    private String currentWaitUserId;
    @ApiModelProperty("当前代办人姓名")
    private String currentWaitUserName;
    @ApiModelProperty("自行支付清单")
    private List<PcAutoPayForEO> autoPayForEOList;
    @ApiModelProperty("流程使用")
    private String businessKey;
    @ApiModelProperty("0，CV；1，PV")
    private String pvOrCv;
    @ApiModelProperty("试验任务编号")
    private String taskNumber;
    @ApiModelProperty("区分试验任务还是执行计划:0-试验任务；1-执行计划")
    private String taskOrPlan;

    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /**  **/
    public Integer getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**  **/
    public String getCreateTime() {
        return this.createTime;
    }

    /**  **/
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**  **/
    public String getCreateBy() {
        return this.createBy;
    }

    /**  **/
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**  **/
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**  **/
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**  **/
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**  **/
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**  **/
    public String getTrialId() {
        return this.trialId;
    }

    /**  **/
    public void setTrialId(String trialId) {
        this.trialId = trialId;
    }

    /**  **/
    public String getTrialCode() {
        return this.trialCode;
    }

    /**  **/
    public void setTrialCode(String trialCode) {
        this.trialCode = trialCode;
    }

    /**  **/
    public String getTrialProjectName() {
        return this.trialProjectName;
    }

    /**  **/
    public void setTrialProjectName(String trialProjectName) {
        this.trialProjectName = trialProjectName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**  **/
    public String getRequestPayoutId() {
        return this.requestPayoutId;
    }

    /**  **/
    public void setRequestPayoutId(String requestPayoutId) {
        this.requestPayoutId = requestPayoutId;
    }

    /**  **/
    public String getRequestPayoutName() {
        return this.requestPayoutName;
    }

    /**  **/
    public void setRequestPayoutName(String requestPayoutName) {
        this.requestPayoutName = requestPayoutName;
    }

    /**  **/
    public String getRequestPayoutOrgId() {
        return this.requestPayoutOrgId;
    }

    /**  **/
    public void setRequestPayoutOrgId(String requestPayoutOrgId) {
        this.requestPayoutOrgId = requestPayoutOrgId;
    }

    /**  **/
    public String getRequestPayoutOrgName() {
        return this.requestPayoutOrgName;
    }

    /**  **/
    public void setRequestPayoutOrgName(String requestPayoutOrgName) {
        this.requestPayoutOrgName = requestPayoutOrgName;
    }

    /**  **/
    public String getApplyDate() {
        return this.applyDate;
    }

    /**  **/
    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    /**  **/
    public String getPayeeId() {
        return this.payeeId;
    }

    /**  **/
    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    /**  **/
    public String getPayeeName() {
        return this.payeeName;
    }

    /**  **/
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    /**  **/
    public String getPayeeOrgId() {
        return this.payeeOrgId;
    }

    /**  **/
    public void setPayeeOrgId(String payeeOrgId) {
        this.payeeOrgId = payeeOrgId;
    }

    /**  **/
    public String getPayeeOrgName() {
        return this.payeeOrgName;
    }

    /**  **/
    public void setPayeeOrgName(String payeeOrgName) {
        this.payeeOrgName = payeeOrgName;
    }

    /**  **/
    public String getBankAccount() {
        return this.bankAccount;
    }

    /**  **/
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**  **/
    public String getBankIntoSite() {
        return this.bankIntoSite;
    }

    /**  **/
    public void setBankIntoSite(String bankIntoSite) {
        this.bankIntoSite = bankIntoSite;
    }

    /**  **/
    public String getBankIntoSiteName() {
        return this.bankIntoSiteName;
    }

    /**  **/
    public void setBankIntoSiteName(String bankIntoSiteName) {
        this.bankIntoSiteName = bankIntoSiteName;
    }

    /**  **/
    public String getRequestPayoutAmount() {
        return this.requestPayoutAmount;
    }

    /**  **/
    public void setRequestPayoutAmount(String requestPayoutAmount) {
        this.requestPayoutAmount = requestPayoutAmount;
    }

    public List<PcAutoPayForEO> getAutoPayForEOList() {
        return autoPayForEOList;
    }

    public void setAutoPayForEOList(List<PcAutoPayForEO> autoPayForEOList) {
        this.autoPayForEOList = autoPayForEOList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentWaitUserId() {
        return currentWaitUserId;
    }

    public void setCurrentWaitUserId(String currentWaitUserId) {
        this.currentWaitUserId = currentWaitUserId;
    }

    public String getCurrentWaitUserName() {
        return currentWaitUserName;
    }

    public void setCurrentWaitUserName(String currentWaitUserName) {
        this.currentWaitUserName = currentWaitUserName;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getPvOrCv() {
        return pvOrCv;
    }

    public void setPvOrCv(String pvOrCv) {
        this.pvOrCv = pvOrCv;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getTaskOrPlan() {
        return taskOrPlan;
    }

    public void setTaskOrPlan(String taskOrPlan) {
        this.taskOrPlan = taskOrPlan;
    }
}
