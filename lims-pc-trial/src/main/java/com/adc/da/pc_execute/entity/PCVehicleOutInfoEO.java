package com.adc.da.pc_execute.entity;

import com.adc.da.base.entity.BaseEntity;

public class PCVehicleOutInfoEO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *删除状态 1删除 0存在
     */
    private String delFlag;

    /**
     *整车出门申请单id
     */
    private String vehicleOutId;

    /**
     *整车样品id
     */
    private String sampleId;

    /**
     *底盘号
     */
    private String chassisNumber;

    /**
     *车型
     */
    private String vehicleType;

    /**
     *单位
     */
    private String unit;

    /**
     *数量
     */
    private String numbers;

    /**
     *借车单号
     */
    private String loanNumber;

    /**
     *运输起点
     */
    private String startPoint;

    /**
     *运输终点
     */
    private String endPoint;

    /**
     *司机名称
     */
    private String driverName;
    
    /**
    *司机id
    */
    private String driverId;

    /**
     *备注
     */
    private String remark;

    
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

	public String getVehicleOutId() {
		return vehicleOutId;
	}

	public void setVehicleOutId(String vehicleOutId) {
		this.vehicleOutId = vehicleOutId;
	}

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public String getChassisNumber() {
		return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}


	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


    
    
    
}