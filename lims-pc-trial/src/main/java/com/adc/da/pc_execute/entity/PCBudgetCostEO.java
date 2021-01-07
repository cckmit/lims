package com.adc.da.pc_execute.entity;

import com.adc.da.base.entity.BaseEntity;

public class PCBudgetCostEO extends BaseEntity {
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
     *创建者名字
     */
    private String createByName;

    /**
     *补贴备注(费用)
     */
    private String subsidyRemark;

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
	 * 申请流程状态
	 * 0-草稿
	 * 1-审批中
	 * 2-已审批
	 * 3-退回
	 */
	private String flowStatus;

	/**
	 * 试验任务唯一编号
	 */
	private String taskNumber;
	
	
	//下面8项为修改需求添加	
    /**
     *费用预算类型
	     *1-委外试验
	     *2-自行试验
     */
    private String costType;
    
	/**
	 * 自行其他费用
	 */
	private String otherCost;
	
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
	
	
	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
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

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getSubsidyRemark() {
		return subsidyRemark;
	}

	public void setSubsidyRemark(String subsidyRemark) {
		this.subsidyRemark = subsidyRemark;
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

	public String getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(String flowStatus) {
		this.flowStatus = flowStatus;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public String getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(String otherCost) {
		this.otherCost = otherCost;
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
	
	
}