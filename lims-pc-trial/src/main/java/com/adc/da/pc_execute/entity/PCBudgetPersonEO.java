package com.adc.da.pc_execute.entity;

import com.adc.da.base.entity.BaseEntity;

public class PCBudgetPersonEO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *删除状态  0 - 未删除; 1- 删除
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
     *标准(费用)
     */
    private String stdPrice;

    /**
     *单位
     */
    private String unit;

    /**
     *时间数
     */
    private String daynum;

    /**
     *人数
     */
    private String personCount;

    /**
     *小计
     */
    private String total;

    /**
     *备注
     */
    private String remark;

    /**
     *可靠性立项单id
     */
    private String initTaskId;
    /**
     * 试验申请id
     */
    private String trialTaskId;
    
	/**
	 * 试验任务唯一编号
	 */
	private String taskNumber;
	
	
	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}
    
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

	public String getStdPrice() {
		return stdPrice;
	}

	public void setStdPrice(String stdPrice) {
		this.stdPrice = stdPrice;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDaynum() {
		return daynum;
	}

	public void setDaynum(String daynum) {
		this.daynum = daynum;
	}

	public String getPersonCount() {
		return personCount;
	}

	public void setPersonCount(String personCount) {
		this.personCount = personCount;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInitTaskId() {
		return initTaskId;
	}

	public void setInitTaskId(String initTaskId) {
		this.initTaskId = initTaskId;
	}

	
    
}