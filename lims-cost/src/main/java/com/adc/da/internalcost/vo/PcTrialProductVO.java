package com.adc.da.internalcost.vo;

import io.swagger.annotations.ApiModelProperty;


/**
 * 产品试验报销明细
 * <b>功能：</b>PC_TRIAL_PRODUCT PcTrialProductEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-13 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcTrialProductVO {

    @ApiModelProperty("报销申请单ID")
    private String rId;
    @ApiModelProperty("开始时间")
    private String startTime;
    @ApiModelProperty("结束时间")
    private String endTime;
    @ApiModelProperty("起点")
    private String startPoint;
    @ApiModelProperty("终点")
    private String endPoint;
    @ApiModelProperty("出发时里程表数")
    private String departOdometer;
    @ApiModelProperty("底盘号")
    private String underpanNo;
    @ApiModelProperty("住宿费预算")
    private String accommodationCost;
    @ApiModelProperty("住宿费申请")
    private String accommodationApply;
    @ApiModelProperty("住宿费累计")
    private String accommodationTotal;
    @ApiModelProperty("住宿天数")
    private String accommodationDayNmber;
    @ApiModelProperty("高速费预算")
    private String highSpeedCost;
    @ApiModelProperty("高速费申请")
    private String highSpeedApply;
    @ApiModelProperty("高速费累计")
    private String highSpeedTotal;
    @ApiModelProperty("过路过桥费预算")
    private String passWayCost;
    @ApiModelProperty("过路过桥费申请")
    private String passWayApply;
    @ApiModelProperty("过路过桥费累计")
    private String passWayTotal;
    @ApiModelProperty("加油费预算")
    private String refuelCost;
    @ApiModelProperty("加油费申请")
    private String refuelApply;
    @ApiModelProperty("加油费累计")
    private String refuelTotal;
    @ApiModelProperty("充电费预算")
    private String chargeCost;
    @ApiModelProperty("充电费申请")
    private String chargeApply;
    @ApiModelProperty("充电费累计")
    private String chargeTotal;
    @ApiModelProperty("加气费预算")
    private String airEntrappingCost;
    @ApiModelProperty("加气费申请")
    private String airEntrappingApply;
    @ApiModelProperty("加气费累计")
    private String airEntrappingTotal;
    @ApiModelProperty("维修费预算")
    private String maintainCost;
    @ApiModelProperty("维修费申请")
    private String maintainApply;
    @ApiModelProperty("维修费累计")
    private String maintainTotal;
    @ApiModelProperty("保养费预算")
    private String upkeepCost;
    @ApiModelProperty("保养费申请")
    private String upkeepApply;
    @ApiModelProperty("保养费累计")
    private String upkeepTotal;
    @ApiModelProperty("停车费预算")
    private String parkCost;
    @ApiModelProperty("停车费申请")
    private String parkApply;
    @ApiModelProperty("停车费累计")
    private String parkTotal;
    @ApiModelProperty("快递费预算")
    private String expressCost;
    @ApiModelProperty("快递费申请")
    private String expressApply;
    @ApiModelProperty("快递费累计")
    private String expressTotal;
    @ApiModelProperty("租赁费预算")
    private String hireCost;
    @ApiModelProperty("租赁费申请")
    private String hireApply;
    @ApiModelProperty("租赁费累计")
    private String hireTotal;
    @ApiModelProperty("其他预算")
    private String pcOtherCost;
    @ApiModelProperty("其他申请")
    private String pcOtherApply;
    @ApiModelProperty("其他累计")
    private String pcOtherTotal;

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getDepartOdometer() {
        return departOdometer;
    }

    public void setDepartOdometer(String departOdometer) {
        this.departOdometer = departOdometer;
    }

    public String getUnderpanNo() {
        return underpanNo;
    }

    public void setUnderpanNo(String underpanNo) {
        this.underpanNo = underpanNo;
    }

    public String getAccommodationCost() {
        return accommodationCost;
    }

    public void setAccommodationCost(String accommodationCost) {
        this.accommodationCost = accommodationCost;
    }

    public String getAccommodationApply() {
        return accommodationApply;
    }

    public void setAccommodationApply(String accommodationApply) {
        this.accommodationApply = accommodationApply;
    }

    public String getAccommodationTotal() {
        return accommodationTotal;
    }

    public void setAccommodationTotal(String accommodationTotal) {
        this.accommodationTotal = accommodationTotal;
    }

    public String getAccommodationDayNmber() {
        return accommodationDayNmber;
    }

    public void setAccommodationDayNmber(String accommodationDayNmber) {
        this.accommodationDayNmber = accommodationDayNmber;
    }

    public String getHighSpeedCost() {
        return highSpeedCost;
    }

    public void setHighSpeedCost(String highSpeedCost) {
        this.highSpeedCost = highSpeedCost;
    }

    public String getHighSpeedApply() {
        return highSpeedApply;
    }

    public void setHighSpeedApply(String highSpeedApply) {
        this.highSpeedApply = highSpeedApply;
    }

    public String getHighSpeedTotal() {
        return highSpeedTotal;
    }

    public void setHighSpeedTotal(String highSpeedTotal) {
        this.highSpeedTotal = highSpeedTotal;
    }

    public String getPassWayCost() {
        return passWayCost;
    }

    public void setPassWayCost(String passWayCost) {
        this.passWayCost = passWayCost;
    }

    public String getPassWayApply() {
        return passWayApply;
    }

    public void setPassWayApply(String passWayApply) {
        this.passWayApply = passWayApply;
    }

    public String getPassWayTotal() {
        return passWayTotal;
    }

    public void setPassWayTotal(String passWayTotal) {
        this.passWayTotal = passWayTotal;
    }

    public String getRefuelCost() {
        return refuelCost;
    }

    public void setRefuelCost(String refuelCost) {
        this.refuelCost = refuelCost;
    }

    public String getRefuelApply() {
        return refuelApply;
    }

    public void setRefuelApply(String refuelApply) {
        this.refuelApply = refuelApply;
    }

    public String getRefuelTotal() {
        return refuelTotal;
    }

    public void setRefuelTotal(String refuelTotal) {
        this.refuelTotal = refuelTotal;
    }

    public String getChargeCost() {
        return chargeCost;
    }

    public void setChargeCost(String chargeCost) {
        this.chargeCost = chargeCost;
    }

    public String getChargeApply() {
        return chargeApply;
    }

    public void setChargeApply(String chargeApply) {
        this.chargeApply = chargeApply;
    }

    public String getChargeTotal() {
        return chargeTotal;
    }

    public void setChargeTotal(String chargeTotal) {
        this.chargeTotal = chargeTotal;
    }

    public String getAirEntrappingCost() {
        return airEntrappingCost;
    }

    public void setAirEntrappingCost(String airEntrappingCost) {
        this.airEntrappingCost = airEntrappingCost;
    }

    public String getAirEntrappingApply() {
        return airEntrappingApply;
    }

    public void setAirEntrappingApply(String airEntrappingApply) {
        this.airEntrappingApply = airEntrappingApply;
    }

    public String getAirEntrappingTotal() {
        return airEntrappingTotal;
    }

    public void setAirEntrappingTotal(String airEntrappingTotal) {
        this.airEntrappingTotal = airEntrappingTotal;
    }

    public String getMaintainCost() {
        return maintainCost;
    }

    public void setMaintainCost(String maintainCost) {
        this.maintainCost = maintainCost;
    }

    public String getMaintainApply() {
        return maintainApply;
    }

    public void setMaintainApply(String maintainApply) {
        this.maintainApply = maintainApply;
    }

    public String getMaintainTotal() {
        return maintainTotal;
    }

    public void setMaintainTotal(String maintainTotal) {
        this.maintainTotal = maintainTotal;
    }

    public String getUpkeepCost() {
        return upkeepCost;
    }

    public void setUpkeepCost(String upkeepCost) {
        this.upkeepCost = upkeepCost;
    }

    public String getUpkeepApply() {
        return upkeepApply;
    }

    public void setUpkeepApply(String upkeepApply) {
        this.upkeepApply = upkeepApply;
    }

    public String getUpkeepTotal() {
        return upkeepTotal;
    }

    public void setUpkeepTotal(String upkeepTotal) {
        this.upkeepTotal = upkeepTotal;
    }

    public String getParkCost() {
        return parkCost;
    }

    public void setParkCost(String parkCost) {
        this.parkCost = parkCost;
    }

    public String getParkApply() {
        return parkApply;
    }

    public void setParkApply(String parkApply) {
        this.parkApply = parkApply;
    }

    public String getParkTotal() {
        return parkTotal;
    }

    public void setParkTotal(String parkTotal) {
        this.parkTotal = parkTotal;
    }

    public String getExpressCost() {
        return expressCost;
    }

    public void setExpressCost(String expressCost) {
        this.expressCost = expressCost;
    }

    public String getExpressApply() {
        return expressApply;
    }

    public void setExpressApply(String expressApply) {
        this.expressApply = expressApply;
    }

    public String getExpressTotal() {
        return expressTotal;
    }

    public void setExpressTotal(String expressTotal) {
        this.expressTotal = expressTotal;
    }

    public String getHireCost() {
        return hireCost;
    }

    public void setHireCost(String hireCost) {
        this.hireCost = hireCost;
    }

    public String getHireApply() {
        return hireApply;
    }

    public void setHireApply(String hireApply) {
        this.hireApply = hireApply;
    }

    public String getHireTotal() {
        return hireTotal;
    }

    public void setHireTotal(String hireTotal) {
        this.hireTotal = hireTotal;
    }

    public String getPcOtherCost() {
        return pcOtherCost;
    }

    public void setPcOtherCost(String pcOtherCost) {
        this.pcOtherCost = pcOtherCost;
    }

    public String getPcOtherApply() {
        return pcOtherApply;
    }

    public void setPcOtherApply(String pcOtherApply) {
        this.pcOtherApply = pcOtherApply;
    }

    public String getPcOtherTotal() {
        return pcOtherTotal;
    }

    public void setPcOtherTotal(String pcOtherTotal) {
        this.pcOtherTotal = pcOtherTotal;
    }
}
