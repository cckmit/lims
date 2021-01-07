package com.adc.da.summary.entity;

import com.adc.da.base.entity.BaseEntity;

public class CostSummaryEO extends BaseEntity {
    /**
     *主键
     */
    private String id;
    
    /**
     *删除状态   1删除  0存在
     */
    private String delFlag;

    /**
     *试验任务id
     */
    private String trialTaskId;

    /**
     *数据来源(“0”是PV/CV,“1”是认证)
     */
    private String dataOrigin;

    /**
     *PV/CV试验任务编号,认证计划名称
     */
    private String taskName;

    /**
     *车型(多个用","隔开)
     */
    private String carType;
    
    /**
     * 车辆IDS(多个用","隔开)
     */
    private String carIds;

    /**
     *底盘号(多个用","隔开)
     */
    private String chassisNo;

    /**
     *负责人(试验工程师)
     */
    private String engineer;
	/**
	 *负责人(试验工程师) 根据需求修改为只添加第一个人
	 */
	private String engineerId;
	/**
	 * 提交人
	 */
	private String submitter;
	/**
	 * 提交人id	根据需求添加提交人
	 */
	private String submitterId;
    /**
     *费用类型
     *自行支付/报账 - 1
     *委外/公告试验费 - 2
     *维修费-对公 - 3
     *场地费 - 4
     *场地燃料费 - 5
     *辅助劳务费/外包劳务费 - 6
     *路送路试费 - 7
     */
    private String costType;

    /**
     *费用金额
     */
    private String totalCost;

    /**
     *任务简要
     */
    private String taskDesc;

    /**
     *委托任务单编号
     */
    private String trustNo;

    /**
     *立项审批单编号
     */
    private String itemNo;

    /**
     *编辑状态(驳回1/激活0)
     */
    private String editStatus;

    /**
     *formKey用于跳转对应列表页
     * RoadTestList 路送路试统计
     * pcOutsourceEntrustList  委外试验委托单
     * annPlanProjectList  执行计划的实际费用key
     * PcBudgetUseList  费用使用表单(维修费/燃料费)
     * SupplierTaskApplyList  辅助劳务
     */
    private String formKey;

    /**
     *详情页id
     */
    private String detialId;

    /**
     *供应商id
     */
    private String supId;

    /**
     *供应商名字
     */
    private String supName;

    /**
     *供应商code
     */
    private String supCode;

    /**
     *供应商类型
     */
    private String supType;

    /**
     *结算金额
     */
    private String paymentCost;

    /**
     *结算说明
     */
    private String paymentDesc;

    /**
     *结算日期
     */
    private String paymentDate;

    /**
     *结算编号
     */
    private String paymentNo;

    /**
     *结算id
     */
    private String paymentId;

    /**
     *结算状态
     *(0:未结算  1结算中   2已结算  3退回  4撤回)
     */
    private String paymentStatus;

    /**
     *结算人id
     */
    private String paymentPersonId;

    /**
     *结算人名
     */
    private String paymentPerson;

	public String getEngineerId() {
		return engineerId;
	}

	public void setEngineerId(String engineerId) {
		this.engineerId = engineerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getTrialTaskId() {
		return trialTaskId;
	}

	public void setTrialTaskId(String trialTaskId) {
		this.trialTaskId = trialTaskId;
	}

	public String getDataOrigin() {
		return dataOrigin;
	}

	public void setDataOrigin(String dataOrigin) {
		this.dataOrigin = dataOrigin;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	
	public String getCarIds() {
		return carIds;
	}

	public void setCarIds(String carIds) {
		this.carIds = carIds;
	}

	public String getChassisNo() {
		return chassisNo;
	}

	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}

	public String getEngineer() {
		return engineer;
	}

	public void setEngineer(String engineer) {
		this.engineer = engineer;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getTrustNo() {
		return trustNo;
	}

	public void setTrustNo(String trustNo) {
		this.trustNo = trustNo;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getEditStatus() {
		return editStatus;
	}

	public void setEditStatus(String editStatus) {
		this.editStatus = editStatus;
	}

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public String getDetialId() {
		return detialId;
	}

	public void setDetialId(String detialId) {
		this.detialId = detialId;
	}

	public String getSupId() {
		return supId;
	}

	public void setSupId(String supId) {
		this.supId = supId;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public String getSupCode() {
		return supCode;
	}

	public void setSupCode(String supCode) {
		this.supCode = supCode;
	}

	public String getSupType() {
		return supType;
	}

	public void setSupType(String supType) {
		this.supType = supType;
	}

	public String getPaymentCost() {
		return paymentCost;
	}

	public void setPaymentCost(String paymentCost) {
		this.paymentCost = paymentCost;
	}

	public String getPaymentDesc() {
		return paymentDesc;
	}

	public void setPaymentDesc(String paymentDesc) {
		this.paymentDesc = paymentDesc;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentPersonId() {
		return paymentPersonId;
	}

	public void setPaymentPersonId(String paymentPersonId) {
		this.paymentPersonId = paymentPersonId;
	}

	public String getPaymentPerson() {
		return paymentPerson;
	}

	public void setPaymentPerson(String paymentPerson) {
		this.paymentPerson = paymentPerson;
	}

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public String getSubmitterId() {
		return submitterId;
	}

	public void setSubmitterId(String submitterId) {
		this.submitterId = submitterId;
	}
}