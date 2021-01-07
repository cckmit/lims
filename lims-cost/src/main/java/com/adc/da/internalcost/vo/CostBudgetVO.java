package com.adc.da.internalcost.vo;

import io.swagger.annotations.ApiModelProperty;

public class CostBudgetVO {

    /*
    实际对公、对私费用
     */
    @ApiModelProperty("任务书ID")
    private String trialTaskId;
    @ApiModelProperty("任务书编号")
    private String trialTaskCode;
    @ApiModelProperty("申请人")
    private String applyUser;
    @ApiModelProperty("申请时间")
    private String applyTime;
    @ApiModelProperty("车辆底盘号")
    private String underpanNo;

    @ApiModelProperty("加油/充电/加气费（元）（对公、对私）（实际费用）")
    private String refuelChargeAirEntrappingBudget;
    @ApiModelProperty("维修费（元）（对公、对私）（实际费用）")
    private String maintainBudget;
    @ApiModelProperty("保养费（元）（对公、对私）（实际费用）")
    private String upkeepBudget;
    @ApiModelProperty("装卸费（元）（对公、对私）（实际费用）")
    private String unAssemble;
    @ApiModelProperty("快递费／物流费（元）（对公、对私）（实际费用）")
    private String expressBudget;
    @ApiModelProperty("委外试验费（元）（对公）（实际费用）")
    private String outSourceBudget;
    @ApiModelProperty("外包路送费（元）（对公）（实际费用）")
    private String roadSendBudget;
    @ApiModelProperty("外包路试费（元）（对公）（实际费用）")
    private String roadTestBudget;
    @ApiModelProperty("场地费（元）（对公）（实际费用）")
    private String placeBudget;
    @ApiModelProperty("总计（对公、对私（实际费用））")
    private String totalBudget;
    @ApiModelProperty("过路过桥费（元）（对私）（实际费用）")
    private String passWayBudget;
    @ApiModelProperty("租赁费（元）（对私）（实际费用）")
    private String hireBudget;
    @ApiModelProperty("住宿费（元）（对私）（实际费用）")
    private String accommodationBudget;
    @ApiModelProperty("差旅交通费（元）（对私）（实际费用）")
    private String travelBudget;
    @ApiModelProperty("其他（元）（对私）（实际费用）")
    private String pcOtherBudget;

    @ApiModelProperty("费用结算--供应商名称")
    private String supplierName;
    @ApiModelProperty("过渡费")
    private String transitionFee;
    @ApiModelProperty("保险费")
    private String insuranceCost;
    @ApiModelProperty("临牌费")
    private String temporaryCard;
    @ApiModelProperty("高速费")
    private String highSpeedToll;
    @ApiModelProperty("风险补贴")
    private String riskSubsidy;
    @ApiModelProperty(" 0,已结算；1，未结算")
    private String status;

    /*
    预算对私费用 BudgetPrivate
     */
    @ApiModelProperty("过路过桥费（费用结算统计（对私）中的预算费用）")
    private String passWayBudgetPrivate;
    @ApiModelProperty("加油/充电/加气费（费用结算统计（对私）中的预算费用）")
    private String refuelChargeAirEntrappingBudgetPrivate;
    @ApiModelProperty("维修费（费用结算统计（对私）中的预算费用）")
    private String maintainBudgetPrivate;
    @ApiModelProperty("快递费／物流费（费用结算统计（对私）中的预算费用）")
    private String expressBudgetPrivate;
    @ApiModelProperty("租赁费（费用结算统计（对私）中的预算费用）")
    private String hireBudgetPrivate;
    @ApiModelProperty("住宿费（费用结算统计（对私）中的预算费用）")
    private String accommodationBudgetPrivate;
    @ApiModelProperty("差旅交通费（费用结算统计（对私）中的预算费用）")
    private String travelBudgetPrivate;
    @ApiModelProperty("其他（费用结算统计（对私）中的预算费用）")
    private String pcOtherBudgetPrivate;
    @ApiModelProperty("总计（费用结算统计（对私）中的预算费用）")
    private String totalBudgetPrivate;

    /*
    预算对公费用 BudgetPublic
     */
    @ApiModelProperty("加油/充电/加气费（费用结算统计（对公）中的预算费用）")
    private String refuelChargeAirEntrappingBudgetPublic;
    @ApiModelProperty("维修费（费用结算统计（对公）中的预算费用）")
    private String maintainBudgetPublic;
    @ApiModelProperty("快递费／物流费（费用结算统计（对公）中的预算费用）")
    private String expressBudgetPublic;
    @ApiModelProperty("委外试验费（费用结算统计（对公）中的预算费用）")
    private String outSourceBudgetPublic;
    @ApiModelProperty("场地费（费用结算统计（对公）中的预算费用）")
    private String placeBudgetPublic;
    @ApiModelProperty("总计（费用结算统计（对公）中的预算费用）")
    private String totalBudgetPublic;

    /*
     *费用预算统计（对私）增加字段
     */
    @ApiModelProperty("微丘高速")
    private String hillockHigh;
    @ApiModelProperty("山区重丘高速")
    private String mountainousHilly;
    @ApiModelProperty("人员过渡费")
    private String personTransitionFee;
    @ApiModelProperty("装卸费")
    private String handlingChargesFee;
    @ApiModelProperty("停车费")
    private String parkingRate;
    @ApiModelProperty("试验场燃料费")
    private String testFuelCost;

    /*
     *费用预算统计（对公）增加字段
     */
    @ApiModelProperty("挂车租赁费用")
    private String pubTrailerRentalCost;
    @ApiModelProperty("路送劳务外包费")
    private String roadServiceOutsource;
    @ApiModelProperty("路试劳务外包费")
    private String roadTestLaborOutsource;
    @ApiModelProperty("其他试验场场地费")
    private String otherTestSiteCost;


    public String getTrialTaskId() {
        return trialTaskId;
    }

    public void setTrialTaskId(String trialTaskId) {
        this.trialTaskId = trialTaskId;
    }

    public String getTemporaryCard() {
        return temporaryCard;
    }

    public void setTemporaryCard(String temporaryCard) {
        this.temporaryCard = temporaryCard;
    }

    public String getHighSpeedToll() {
        return highSpeedToll;
    }

    public void setHighSpeedToll(String highSpeedToll) {
        this.highSpeedToll = highSpeedToll;
    }

    public String getRiskSubsidy() {
        return riskSubsidy;
    }

    public void setRiskSubsidy(String riskSubsidy) {
        this.riskSubsidy = riskSubsidy;
    }

    public String getInsuranceCost() {
        return insuranceCost;
    }

    public void setInsuranceCost(String insuranceCost) {
        this.insuranceCost = insuranceCost;
    }

    public String getTransitionFee() {
        return transitionFee;
    }

    public void setTransitionFee(String transitionFee) {
        this.transitionFee = transitionFee;
    }

    public String getTrialTaskCode() {
        return trialTaskCode;
    }

    public void setTrialTaskCode(String trialTaskCode) {
        this.trialTaskCode = trialTaskCode;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getUnderpanNo() {
        return underpanNo;
    }

    public void setUnderpanNo(String underpanNo) {
        this.underpanNo = underpanNo;
    }

    public String getRefuelChargeAirEntrappingBudget() {
        return refuelChargeAirEntrappingBudget;
    }

    public void setRefuelChargeAirEntrappingBudget(String refuelChargeAirEntrappingBudget) {
        this.refuelChargeAirEntrappingBudget = refuelChargeAirEntrappingBudget;
    }

    public String getMaintainBudget() {
        return maintainBudget;
    }

    public void setMaintainBudget(String maintainBudget) {
        this.maintainBudget = maintainBudget;
    }

    public String getUnAssemble() {
        return unAssemble;
    }

    public void setUnAssemble(String unAssemble) {
        this.unAssemble = unAssemble;
    }

    public String getExpressBudget() {
        return expressBudget;
    }

    public void setExpressBudget(String expressBudget) {
        this.expressBudget = expressBudget;
    }

    public String getOutSourceBudget() {
        return outSourceBudget;
    }

    public void setOutSourceBudget(String outSourceBudget) {
        this.outSourceBudget = outSourceBudget;
    }

    public String getRoadSendBudget() {
        return roadSendBudget;
    }

    public void setRoadSendBudget(String roadSendBudget) {
        this.roadSendBudget = roadSendBudget;
    }

    public String getRoadTestBudget() {
        return roadTestBudget;
    }

    public void setRoadTestBudget(String roadTestBudget) {
        this.roadTestBudget = roadTestBudget;
    }

    public String getPlaceBudget() {
        return placeBudget;
    }

    public void setPlaceBudget(String placeBudget) {
        this.placeBudget = placeBudget;
    }

    public String getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(String totalBudget) {
        this.totalBudget = totalBudget;
    }

    public String getPassWayBudget() {
        return passWayBudget;
    }

    public void setPassWayBudget(String passWayBudget) {
        this.passWayBudget = passWayBudget;
    }

    public String getHireBudget() {
        return hireBudget;
    }

    public void setHireBudget(String hireBudget) {
        this.hireBudget = hireBudget;
    }

    public String getAccommodationBudget() {
        return accommodationBudget;
    }

    public void setAccommodationBudget(String accommodationBudget) {
        this.accommodationBudget = accommodationBudget;
    }

    public String getTravelBudget() {
        return travelBudget;
    }

    public void setTravelBudget(String travelBudget) {
        this.travelBudget = travelBudget;
    }

    public String getPcOtherBudget() {
        return pcOtherBudget;
    }

    public void setPcOtherBudget(String pcOtherBudget) {
        this.pcOtherBudget = pcOtherBudget;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getUpkeepBudget() {
        return upkeepBudget;
    }

    public void setUpkeepBudget(String upkeepBudget) {
        this.upkeepBudget = upkeepBudget;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassWayBudgetPrivate() {
        return passWayBudgetPrivate;
    }

    public void setPassWayBudgetPrivate(String passWayBudgetPrivate) {
        this.passWayBudgetPrivate = passWayBudgetPrivate;
    }

    public String getRefuelChargeAirEntrappingBudgetPrivate() {
        return refuelChargeAirEntrappingBudgetPrivate;
    }

    public void setRefuelChargeAirEntrappingBudgetPrivate(String refuelChargeAirEntrappingBudgetPrivate) {
        this.refuelChargeAirEntrappingBudgetPrivate = refuelChargeAirEntrappingBudgetPrivate;
    }

    public String getMaintainBudgetPrivate() {
        return maintainBudgetPrivate;
    }

    public void setMaintainBudgetPrivate(String maintainBudgetPrivate) {
        this.maintainBudgetPrivate = maintainBudgetPrivate;
    }

    public String getExpressBudgetPrivate() {
        return expressBudgetPrivate;
    }

    public void setExpressBudgetPrivate(String expressBudgetPrivate) {
        this.expressBudgetPrivate = expressBudgetPrivate;
    }

    public String getHireBudgetPrivate() {
        return hireBudgetPrivate;
    }

    public void setHireBudgetPrivate(String hireBudgetPrivate) {
        this.hireBudgetPrivate = hireBudgetPrivate;
    }

    public String getAccommodationBudgetPrivate() {
        return accommodationBudgetPrivate;
    }

    public void setAccommodationBudgetPrivate(String accommodationBudgetPrivate) {
        this.accommodationBudgetPrivate = accommodationBudgetPrivate;
    }

    public String getTravelBudgetPrivate() {
        return travelBudgetPrivate;
    }

    public void setTravelBudgetPrivate(String travelBudgetPrivate) {
        this.travelBudgetPrivate = travelBudgetPrivate;
    }

    public String getPcOtherBudgetPrivate() {
        return pcOtherBudgetPrivate;
    }

    public void setPcOtherBudgetPrivate(String pcOtherBudgetPrivate) {
        this.pcOtherBudgetPrivate = pcOtherBudgetPrivate;
    }

    public String getTotalBudgetPrivate() {
        return totalBudgetPrivate;
    }

    public void setTotalBudgetPrivate(String totalBudgetPrivate) {
        this.totalBudgetPrivate = totalBudgetPrivate;
    }

    public String getRefuelChargeAirEntrappingBudgetPublic() {
        return refuelChargeAirEntrappingBudgetPublic;
    }

    public void setRefuelChargeAirEntrappingBudgetPublic(String refuelChargeAirEntrappingBudgetPublic) {
        this.refuelChargeAirEntrappingBudgetPublic = refuelChargeAirEntrappingBudgetPublic;
    }

    public String getMaintainBudgetPublic() {
        return maintainBudgetPublic;
    }

    public void setMaintainBudgetPublic(String maintainBudgetPublic) {
        this.maintainBudgetPublic = maintainBudgetPublic;
    }

    public String getExpressBudgetPublic() {
        return expressBudgetPublic;
    }

    public void setExpressBudgetPublic(String expressBudgetPublic) {
        this.expressBudgetPublic = expressBudgetPublic;
    }

    public String getOutSourceBudgetPublic() {
        return outSourceBudgetPublic;
    }

    public void setOutSourceBudgetPublic(String outSourceBudgetPublic) {
        this.outSourceBudgetPublic = outSourceBudgetPublic;
    }

    public String getPlaceBudgetPublic() {
        return placeBudgetPublic;
    }

    public void setPlaceBudgetPublic(String placeBudgetPublic) {
        this.placeBudgetPublic = placeBudgetPublic;
    }

    public String getTotalBudgetPublic() {
        return totalBudgetPublic;
    }

    public void setTotalBudgetPublic(String totalBudgetPublic) {
        this.totalBudgetPublic = totalBudgetPublic;
    }

    public String getHillockHigh() {
        return hillockHigh;
    }

    public void setHillockHigh(String hillockHigh) {
        this.hillockHigh = hillockHigh;
    }

    public String getMountainousHilly() {
        return mountainousHilly;
    }

    public void setMountainousHilly(String mountainousHilly) {
        this.mountainousHilly = mountainousHilly;
    }

    public String getPersonTransitionFee() {
        return personTransitionFee;
    }

    public void setPersonTransitionFee(String personTransitionFee) {
        this.personTransitionFee = personTransitionFee;
    }

    public String getHandlingChargesFee() {
        return handlingChargesFee;
    }

    public void setHandlingChargesFee(String handlingChargesFee) {
        this.handlingChargesFee = handlingChargesFee;
    }

    public String getParkingRate() {
        return parkingRate;
    }

    public void setParkingRate(String parkingRate) {
        this.parkingRate = parkingRate;
    }

    public String getTestFuelCost() {
        return testFuelCost;
    }

    public void setTestFuelCost(String testFuelCost) {
        this.testFuelCost = testFuelCost;
    }

    public String getPubTrailerRentalCost() {
        return pubTrailerRentalCost;
    }

    public void setPubTrailerRentalCost(String pubTrailerRentalCost) {
        this.pubTrailerRentalCost = pubTrailerRentalCost;
    }

    public String getRoadServiceOutsource() {
        return roadServiceOutsource;
    }

    public void setRoadServiceOutsource(String roadServiceOutsource) {
        this.roadServiceOutsource = roadServiceOutsource;
    }

    public String getRoadTestLaborOutsource() {
        return roadTestLaborOutsource;
    }

    public void setRoadTestLaborOutsource(String roadTestLaborOutsource) {
        this.roadTestLaborOutsource = roadTestLaborOutsource;
    }

    public String getOtherTestSiteCost() {
        return otherTestSiteCost;
    }

    public void setOtherTestSiteCost(String otherTestSiteCost) {
        this.otherTestSiteCost = otherTestSiteCost;
    }
}
