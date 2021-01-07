package com.adc.da.pc_execute.vo;

import com.adc.da.base.entity.BaseEntity;

public class PCBudgetSubsidyVO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *删除状态 1删除 0存在
     */
    private String delFlag;

    /**
     *名称
     */
    private String itemsName;

    /**
     *名称code
     */
    private String itemsCode;

    /**
     *试验里程
     */
    private String trialKm;

    /**
     *试验路线
     */
    private String trialLine;

    /**
     *标准
     */
    private String stdPrice;

    /**
     *车辆数
     */
    private String carCount;

    /**
     *补助额
     */
    private String subsidyMoney;

    /**
     *人数
     */
    private String personCount;

    /**
     *形式天数
     */
    private String driveDays;

    /**
     *出差天数
     */
    private String businessDays;

    /**
     *可靠性立项单id
     */
    private String initTaskId;

    /**
     * 试验申请id
     */
    private String trialTaskId;
    
    
    
	public String getTrialTaskId() {
		return trialTaskId;
	}

	public void setTrialTaskId(String trialTaskId) {
		this.trialTaskId = trialTaskId;
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

	public String getItemsName() {
		return itemsName;
	}

	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}

	public String getItemsCode() {
		return itemsCode;
	}

	public void setItemsCode(String itemsCode) {
		this.itemsCode = itemsCode;
	}

	public String getTrialKm() {
		return trialKm;
	}

	public void setTrialKm(String trialKm) {
		this.trialKm = trialKm;
	}

	public String getTrialLine() {
		return trialLine;
	}

	public void setTrialLine(String trialLine) {
		this.trialLine = trialLine;
	}

	public String getStdPrice() {
		return stdPrice;
	}

	public void setStdPrice(String stdPrice) {
		this.stdPrice = stdPrice;
	}

	public String getCarCount() {
		return carCount;
	}

	public void setCarCount(String carCount) {
		this.carCount = carCount;
	}

	public String getSubsidyMoney() {
		return subsidyMoney;
	}

	public void setSubsidyMoney(String subsidyMoney) {
		this.subsidyMoney = subsidyMoney;
	}

	public String getPersonCount() {
		return personCount;
	}

	public void setPersonCount(String personCount) {
		this.personCount = personCount;
	}

	public String getDriveDays() {
		return driveDays;
	}

	public void setDriveDays(String driveDays) {
		this.driveDays = driveDays;
	}

	public String getBusinessDays() {
		return businessDays;
	}

	public void setBusinessDays(String businessDays) {
		this.businessDays = businessDays;
	}

	public String getInitTaskId() {
		return initTaskId;
	}

	public void setInitTaskId(String initTaskId) {
		this.initTaskId = initTaskId;
	}

 
    
    
}