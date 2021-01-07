package com.adc.da.pc_execute.entity;

import com.adc.da.base.entity.BaseEntity;

public class PCReliableInitTaskEO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *删除标记  0-未删除  1-删除
     */
    private String delFlag;

    /**
     *创建者
     */
    private String createBy;

    /**
     *创建时间
     */
    private String createTime;

    /**
     *更新者
     */
    private String updateBy;

    /**
     *更新时间
     */
    private String updateTime;

    /**
     *项目名称
     */
    private String taskName;

    /**
     *试验任务编号
     */
    private String taskCode;

    /**
     *立项单编号 CV/PV-KKXLX-0001-2019
     */
    private String initCode;

    /**
     *项目类型
	     *1-委外试验(检测所)
	     *2-外包试验(供应商)
	     *3-自行试验
     */
    private String taskType;

    /**
     *负责部门id
     */
    private String chargeOrgId;

    /**
     *负责部门名称
     *0 - 商用车品质保障部
     *1 - 乘用车品质保障部
     */
    private String chargeOrgName;

    /**
     *负责人id
     */
    private String chargeUserId;

    /**
     *负责人名称
     */
    private String chargeUserName;

    /**
     *产品型号
     */
    private String productType;

    /**
     *底盘号
     */
    private String chassisCode;

    /**
     *试验样车状态 
         *1-新车
	     *2-旧车
     */
    private String sampleStatus;

    /**
     *试验说明
	   *1-全新试验
	   *2-搭载试验
     */
    private String taskExplain;

    /**
     *项目简介
     */
    private String taskSynopsis;

    /**
     *项目来源及要求
     */
    private String taskFromRequire;

    /**
     *项目周期及实施计划
     */
    private String taskCyclePlan;

    /**
     *需请款费用小计
     */
    private String selfTotal;

    /**
     *项目费用预算统计
     */
    private String publicTotal;

    /**
     *住宿费
     */
    private String accoCost;

    /**
     *燃油费/充电费
     */
    private String oilChargeCost;

    /**
     *高速费/路桥费
     */
    private String highRoadCost;

    /**
     *修理/保养费
     */
    private String repairCost;

    /**
     *临牌保险费
     */
    private String tempCardCost;

    /**
     *出差补贴
     */
    private String busniessCost;

    /**
     *风险补贴
     */
    private String dangerCost;

    /**
     *(对公)燃油费/充电费
     */
    private String pubOilChargeCost;

    /**
     *委外费(0)
     */
    private String outSourceCost;

    /**
     *(对公)修理/保养费
     */
    private String pubRepairCost;

    /**
     *试验场场地费
     */
    private String testFieldCost;

	/**
     *试验任务id
     */
    private String trialTaskId;

    /**
     *附件id(费用)
     */
    private String attachId;

    /**
     *附件名(费用)
     */
    private String attachName;
    /**
     *附件地址(费用)
     */
    private String attachPath;

    /**
     *申请时间(费用)
     */
    private String applyTime;

    /**
     *申请人名(费用)
     */
    private String applyUserName;

    /**
     *申请人id(费用)
     */
    private String applyUserId;

    /**
     *补贴备注(费用)
     */
    private String subsidyRemark;
    
    /**
     * 可靠性立项单状态
     * 0-草稿
     * 1-审批中
     * 2-已审批
     * 3-退回
     * 4-撤回
     */
    private String initStatus;
    /**
     * 属于pv还是cv
     */
    private String pvorcv;
    
    /**
     * baseBusId
     */
    private String baseBusId;

	//下面6项为修改需求添加
	/**
	 * 过渡费
	 */
	private String transitionCost;
	/**
	 * 装卸费
	 */
	private String transportCost;
	/**
	 * 挂车租赁费
	 */
	private String trailerRentCost;
	/**
	 * 停车费
	 */
	private String parkingCost;
	/**
	 * 路试劳务外包费
	 */
	private String roadTestCost;
	/**
	 * 路送劳务外包费
	 */
	private String roadSendCost;
	
	/**
	 * 试验任务唯一编号
	 */
	private String taskNumber;
	
	/**
	 * 自行其他费用
	 */
	private String otherCost;
	
	
    /**
     *费用预算类型
	     *1-委外试验
	     *2-自行试验
     */
    private String costType;
	
	
	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public String getBaseBusId() {
		return baseBusId;
	}

	public void setBaseBusId(String baseBusId) {
		this.baseBusId = baseBusId;
	}

	public String getAttachPath() {
		return attachPath;
	}

	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}

	public String getPvorcv() {
		return pvorcv;
	}

	public void setPvorcv(String pvorcv) {
		this.pvorcv = pvorcv;
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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getInitCode() {
		return initCode;
	}

	public void setInitCode(String initCode) {
		this.initCode = initCode;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getChargeOrgId() {
		return chargeOrgId;
	}

	public void setChargeOrgId(String chargeOrgId) {
		this.chargeOrgId = chargeOrgId;
	}

	public String getChargeOrgName() {
		return chargeOrgName;
	}

	public void setChargeOrgName(String chargeOrgName) {
		this.chargeOrgName = chargeOrgName;
	}

	public String getChargeUserId() {
		return chargeUserId;
	}

	public void setChargeUserId(String chargeUserId) {
		this.chargeUserId = chargeUserId;
	}

	public String getChargeUserName() {
		return chargeUserName;
	}

	public void setChargeUserName(String chargeUserName) {
		this.chargeUserName = chargeUserName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getChassisCode() {
		return chassisCode;
	}

	public void setChassisCode(String chassisCode) {
		this.chassisCode = chassisCode;
	}

	public String getSampleStatus() {
		return sampleStatus;
	}

	public void setSampleStatus(String sampleStatus) {
		this.sampleStatus = sampleStatus;
	}

	public String getTaskExplain() {
		return taskExplain;
	}

	public void setTaskExplain(String taskExplain) {
		this.taskExplain = taskExplain;
	}

	public String getTaskSynopsis() {
		return taskSynopsis;
	}

	public void setTaskSynopsis(String taskSynopsis) {
		this.taskSynopsis = taskSynopsis;
	}

	public String getTaskFromRequire() {
		return taskFromRequire;
	}

	public void setTaskFromRequire(String taskFromRequire) {
		this.taskFromRequire = taskFromRequire;
	}

	public String getTaskCyclePlan() {
		return taskCyclePlan;
	}

	public void setTaskCyclePlan(String taskCyclePlan) {
		this.taskCyclePlan = taskCyclePlan;
	}

	public String getSelfTotal() {
		return selfTotal;
	}

	public void setSelfTotal(String selfTotal) {
		this.selfTotal = selfTotal;
	}

	public String getPublicTotal() {
		return publicTotal;
	}

	public void setPublicTotal(String publicTotal) {
		this.publicTotal = publicTotal;
	}

	public String getAccoCost() {
		return accoCost;
	}

	public void setAccoCost(String accoCost) {
		this.accoCost = accoCost;
	}

	public String getOilChargeCost() {
		return oilChargeCost;
	}

	public void setOilChargeCost(String oilChargeCost) {
		this.oilChargeCost = oilChargeCost;
	}

	public String getHighRoadCost() {
		return highRoadCost;
	}

	public void setHighRoadCost(String highRoadCost) {
		this.highRoadCost = highRoadCost;
	}

	public String getRepairCost() {
		return repairCost;
	}

	public void setRepairCost(String repairCost) {
		this.repairCost = repairCost;
	}

	public String getTempCardCost() {
		return tempCardCost;
	}

	public void setTempCardCost(String tempCardCost) {
		this.tempCardCost = tempCardCost;
	}

	public String getBusniessCost() {
		return busniessCost;
	}

	public void setBusniessCost(String busniessCost) {
		this.busniessCost = busniessCost;
	}

	public String getDangerCost() {
		return dangerCost;
	}

	public void setDangerCost(String dangerCost) {
		this.dangerCost = dangerCost;
	}

	public String getPubOilChargeCost() {
		return pubOilChargeCost;
	}

	public void setPubOilChargeCost(String pubOilChargeCost) {
		this.pubOilChargeCost = pubOilChargeCost;
	}

	public String getOutSourceCost() {
		return outSourceCost;
	}

	public void setOutSourceCost(String outSourceCost) {
		this.outSourceCost = outSourceCost;
	}

	public String getPubRepairCost() {
		return pubRepairCost;
	}

	public void setPubRepairCost(String pubRepairCost) {
		this.pubRepairCost = pubRepairCost;
	}

	public String getTestFieldCost() {
		return testFieldCost;
	}

	public void setTestFieldCost(String testFieldCost) {
		this.testFieldCost = testFieldCost;
	}

	public String getTrialTaskId() {
		return trialTaskId;
	}

	public void setTrialTaskId(String trialTaskId) {
		this.trialTaskId = trialTaskId;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public String getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}

	public String getSubsidyRemark() {
		return subsidyRemark;
	}

	public void setSubsidyRemark(String subsidyRemark) {
		this.subsidyRemark = subsidyRemark;
	}

	public String getInitStatus() {
		return initStatus;
	}

	public void setInitStatus(String initStatus) {
		this.initStatus = initStatus;
	}

	public String getTransitionCost() {
		return transitionCost;
	}

	public void setTransitionCost(String transitionCost) {
		this.transitionCost = transitionCost;
	}

	public String getTransportCost() {
		return transportCost;
	}

	public void setTransportCost(String transportCost) {
		this.transportCost = transportCost;
	}

	public String getTrailerRentCost() {
		return trailerRentCost;
	}

	public void setTrailerRentCost(String trailerRentCost) {
		this.trailerRentCost = trailerRentCost;
	}

	public String getParkingCost() {
		return parkingCost;
	}

	public void setParkingCost(String parkingCost) {
		this.parkingCost = parkingCost;
	}

	public String getRoadTestCost() {
		return roadTestCost;
	}

	public void setRoadTestCost(String roadTestCost) {
		this.roadTestCost = roadTestCost;
	}

	public String getRoadSendCost() {
		return roadSendCost;
	}

	public void setRoadSendCost(String roadSendCost) {
		this.roadSendCost = roadSendCost;
	}

	public String getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(String otherCost) {
		this.otherCost = otherCost;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}
	
	
}