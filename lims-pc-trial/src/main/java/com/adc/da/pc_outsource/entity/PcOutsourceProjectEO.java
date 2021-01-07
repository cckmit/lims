package com.adc.da.pc_outsource.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>PC_OUTSOURCE_PROJECT PcOutsourceProjectEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcOutsourceProjectEO extends BaseEntity {

    private String id;
    private Integer delFlag;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    @ApiModelProperty("编号")
    private String code;
    @ApiModelProperty("项目名称")
    private String insProject;
    @ApiModelProperty("项目类型")
    private String insType;
    //改动后，只用了“项目ID”这个字段，在数据库中不在存储项目类型和项目名称。用到时再重新查
    @ApiModelProperty("项目ID")
    private String insId;
    @ApiModelProperty("负责部门")
    private String managerOrg;
    @ApiModelProperty("部门ID")
    private String orgId;
    @ApiModelProperty("负责人")
    private String managerUser;
    @ApiModelProperty("负责任人ID")
    private String userId;
    @ApiModelProperty("申请日期")
    private String applyDate;
    @ApiModelProperty("项目简介")
    private String opDesc;
    @ApiModelProperty("项目来源及要求")
    private String opSourceRequire;
    @ApiModelProperty("项目周期及说明")
    private String opCyclePlan;
    @ApiModelProperty("项目费用预算及说明")
    private String opBudgetDesc;
    @ApiModelProperty("附件ID")
    private String fileId;
    @ApiModelProperty("附件名称")
    private String fileName;
    @ApiModelProperty("状态（0,草稿；1，待审批；2已审批；3，撤回中；4，已撤回；5，已完成；6，退回）")
    private String status;
    @ApiModelProperty("0，CV；1，PV")
    private String pvOrCv;
    @ApiModelProperty("供应商名称")
    private String supplierName;
    @ApiModelProperty("供应商ID")
    private String supplierId;
    @ApiModelProperty("单价")
    private String unitPrice;
    @ApiModelProperty("数量")
    private String numbers;
    @ApiModelProperty("总价")
    private String totalPrice;
    @ApiModelProperty("委外立项申请附件ID")
    private String opFileId;
    @ApiModelProperty("委外立项申请附件名称")
    private String opFileName;
    @ApiModelProperty("试验ID")
    private String trialId;
    @ApiModelProperty("当前代办人id")
    private String currentWaitUserId;
    @ApiModelProperty("当前代办人姓名")
    private String currentWaitUserName;
    @ApiModelProperty("业务流程主键")
    private String businessKey;
    @ApiModelProperty("试验code")
    private String trialCode;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**  **/
    public String getInsProject() {
        return this.insProject;
    }

    /**  **/
    public void setInsProject(String insProject) {
        this.insProject = insProject;
    }

    /**  **/
    public String getInsType() {
        return this.insType;
    }

    /**  **/
    public void setInsType(String insType) {
        this.insType = insType;
    }

    /**  **/
    public String getInsId() {
        return this.insId;
    }

    /**  **/
    public void setInsId(String insId) {
        this.insId = insId;
    }

    /**  **/
    public String getManagerOrg() {
        return this.managerOrg;
    }

    /**  **/
    public void setManagerOrg(String managerOrg) {
        this.managerOrg = managerOrg;
    }

    /**  **/
    public String getOrgId() {
        return this.orgId;
    }

    /**  **/
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**  **/
    public String getManagerUser() {
        return this.managerUser;
    }

    /**  **/
    public void setManagerUser(String managerUser) {
        this.managerUser = managerUser;
    }

    /**  **/
    public String getUserId() {
        return this.userId;
    }

    /**  **/
    public void setUserId(String userId) {
        this.userId = userId;
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
    public String getOpDesc() {
        return this.opDesc;
    }

    /**  **/
    public void setOpDesc(String opDesc) {
        this.opDesc = opDesc;
    }

    /**  **/
    public String getOpSourceRequire() {
        return this.opSourceRequire;
    }

    /**  **/
    public void setOpSourceRequire(String opSourceRequire) {
        this.opSourceRequire = opSourceRequire;
    }

    /**  **/
    public String getOpCyclePlan() {
        return this.opCyclePlan;
    }

    /**  **/
    public void setOpCyclePlan(String opCyclePlan) {
        this.opCyclePlan = opCyclePlan;
    }

    /**  **/
    public String getOpBudgetDesc() {
        return this.opBudgetDesc;
    }

    /**  **/
    public void setOpBudgetDesc(String opBudgetDesc) {
        this.opBudgetDesc = opBudgetDesc;
    }

    /**  **/
    public String getFileId() {
        return this.fileId;
    }

    /**  **/
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**  **/
    public String getStatus() {
        return this.status;
    }

    /**  **/
    public void setStatus(String status) {
        this.status = status;
    }

    public String getPvOrCv() {
        return pvOrCv;
    }

    public void setPvOrCv(String pvOrCv) {
        this.pvOrCv = pvOrCv;
    }

    /**  **/
    public String getSupplierName() {
        return this.supplierName;
    }

    /**  **/
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**  **/
    public String getSupplierId() {
        return this.supplierId;
    }

    /**  **/
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    /**  **/
    public String getUnitPrice() {
        return this.unitPrice;
    }

    /**  **/
    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**  **/
    public String getNumbers() {
        return this.numbers;
    }

    /**  **/
    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    /**  **/
    public String getTotalPrice() {
        return this.totalPrice;
    }

    /**  **/
    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**  **/
    public String getOpFileId() {
        return this.opFileId;
    }

    /**  **/
    public void setOpFileId(String opFileId) {
        this.opFileId = opFileId;
    }

    /**  **/
    public String getTrialId() {
        return this.trialId;
    }

    /**  **/
    public void setTrialId(String trialId) {
        this.trialId = trialId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOpFileName() {
        return opFileName;
    }

    public void setOpFileName(String opFileName) {
        this.opFileName = opFileName;
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

    public String getTrialCode() {
        return trialCode;
    }

    public void setTrialCode(String trialCode) {
        this.trialCode = trialCode;
    }
}
