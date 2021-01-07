package com.adc.da.pc_execute.vo;

import com.adc.da.base.entity.BaseEntity;

public class PCBudgetTestVO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *删除标记     0-未删除,  1-已删除
     */
    private String delFlag;

    /**
     *备注
     */
    private String remark;

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
     *单位数
     */
    private String countnum;

    /**
     *车辆台数
     */
    private String carCount;

    /**
     *小计
     */
    private String total;

    /**
     *可靠性立项单
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getCountnum() {
		return countnum;
	}

	public void setCountnum(String countnum) {
		this.countnum = countnum;
	}

	public String getCarCount() {
		return carCount;
	}

	public void setCarCount(String carCount) {
		this.carCount = carCount;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getInitTaskId() {
		return initTaskId;
	}

	public void setInitTaskId(String initTaskId) {
		this.initTaskId = initTaskId;
	}


    
    
}