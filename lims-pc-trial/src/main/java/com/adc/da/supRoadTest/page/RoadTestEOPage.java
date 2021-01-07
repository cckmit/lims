package com.adc.da.supRoadTest.page;

import com.adc.da.base.page.BasePage;

import java.util.List;
import java.util.Map;


/**
 * <b>功能：</b>SUP_ROAD_TEST RoadTestEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2020-08-12 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class RoadTestEOPage extends BasePage {

    private String total;
    private String totalOperator = "=";
    private String evectionSubsidy;
    private String evectionSubsidyOperator = "=";
    private String subsidyOff;
    private String subsidyOffOperator = "=";
    private String evectionDays;
    private String evectionDaysOperator = "=";
    private String daysOff;
    private String daysOffOperator = "=";
    private String trialState;
    private String trialStateOperator = "=";
    private String countPrice;
    private String countPriceOperator = "=";
    private String milePlus;
    private String milePlusOperator = "=";
    private String testConditions;
    private String testConditionsOperator = "=";
    private String drType;
    private String drTypeOperator = "=";
    private String carState;
    private String carStateOperator = "=";
    private String load;
    private String loadOperator = "=";
    private String roadLineId;
    private String roadLineIdOperator = "=";
    private String trialId;
    private String trialIdOperator = "=";
    private String tower;
    private String towerOperator = "=";
    private String fuelType;
    private String fuelTypeOperator = "=";
    private String carType;
    private String carTypeOperator = "=";
    private String vehicleType;
    private String vehicleTypeOperator = "=";
    private String chassisCode;
    private String chassisCodeOperator = "=";
    private String carId;
    private String carIdOperator = "=";
    private String supCode;
    private String supCodeOperator = "=";
    private String supName;
    private String supNameOperator = "=";
    private String supId;
    private String supIdOperator = "=";
    private String trialProjectCode;
    private String trialProjectCodeOperator = "=";
    private String trialProjectId;
    private String trialProjectIdOperator = "=";
    private String taskBookName;
    private String taskBookNameOperator = "=";
    private String taskBookCode;
    private String taskBookCodeOperator = "=";
    private String pcId;
    private String pcIdOperator = "=";
    private String id;
    private String idOperator = "=";
    /**
     * 通用查询条件
     */
    private List<Map<String, Object>> searchMap;

    public String getTotal() {
        return this.total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalOperator() {
        return this.totalOperator;
    }

    public void setTotalOperator(String totalOperator) {
        this.totalOperator = totalOperator;
    }

    public String getEvectionSubsidy() {
        return this.evectionSubsidy;
    }

    public void setEvectionSubsidy(String evectionSubsidy) {
        this.evectionSubsidy = evectionSubsidy;
    }

    public String getEvectionSubsidyOperator() {
        return this.evectionSubsidyOperator;
    }

    public void setEvectionSubsidyOperator(String evectionSubsidyOperator) {
        this.evectionSubsidyOperator = evectionSubsidyOperator;
    }

    public String getSubsidyOff() {
        return this.subsidyOff;
    }

    public void setSubsidyOff(String subsidyOff) {
        this.subsidyOff = subsidyOff;
    }

    public String getSubsidyOffOperator() {
        return this.subsidyOffOperator;
    }

    public void setSubsidyOffOperator(String subsidyOffOperator) {
        this.subsidyOffOperator = subsidyOffOperator;
    }

    public String getEvectionDays() {
        return this.evectionDays;
    }

    public void setEvectionDays(String evectionDays) {
        this.evectionDays = evectionDays;
    }

    public String getEvectionDaysOperator() {
        return this.evectionDaysOperator;
    }

    public void setEvectionDaysOperator(String evectionDaysOperator) {
        this.evectionDaysOperator = evectionDaysOperator;
    }

    public String getDaysOff() {
        return this.daysOff;
    }

    public void setDaysOff(String daysOff) {
        this.daysOff = daysOff;
    }

    public String getDaysOffOperator() {
        return this.daysOffOperator;
    }

    public void setDaysOffOperator(String daysOffOperator) {
        this.daysOffOperator = daysOffOperator;
    }

    public String getTrialState() {
        return this.trialState;
    }

    public void setTrialState(String trialState) {
        this.trialState = trialState;
    }

    public String getTrialStateOperator() {
        return this.trialStateOperator;
    }

    public void setTrialStateOperator(String trialStateOperator) {
        this.trialStateOperator = trialStateOperator;
    }

    public String getCountPrice() {
        return this.countPrice;
    }

    public void setCountPrice(String countPrice) {
        this.countPrice = countPrice;
    }

    public String getCountPriceOperator() {
        return this.countPriceOperator;
    }

    public void setCountPriceOperator(String countPriceOperator) {
        this.countPriceOperator = countPriceOperator;
    }

    public String getMilePlus() {
        return this.milePlus;
    }

    public void setMilePlus(String milePlus) {
        this.milePlus = milePlus;
    }

    public String getMilePlusOperator() {
        return this.milePlusOperator;
    }

    public void setMilePlusOperator(String milePlusOperator) {
        this.milePlusOperator = milePlusOperator;
    }

    public String getTestConditions() {
        return this.testConditions;
    }

    public void setTestConditions(String testConditions) {
        this.testConditions = testConditions;
    }

    public String getTestConditionsOperator() {
        return this.testConditionsOperator;
    }

    public void setTestConditionsOperator(String testConditionsOperator) {
        this.testConditionsOperator = testConditionsOperator;
    }

    public String getDrType() {
        return this.drType;
    }

    public void setDrType(String drType) {
        this.drType = drType;
    }

    public String getDrTypeOperator() {
        return this.drTypeOperator;
    }

    public void setDrTypeOperator(String drTypeOperator) {
        this.drTypeOperator = drTypeOperator;
    }

    public String getCarState() {
        return this.carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public String getCarStateOperator() {
        return this.carStateOperator;
    }

    public void setCarStateOperator(String carStateOperator) {
        this.carStateOperator = carStateOperator;
    }

    public String getLoad() {
        return this.load;
    }

    public void setLoad(String load) {
        this.load = load;
    }

    public String getLoadOperator() {
        return this.loadOperator;
    }

    public void setLoadOperator(String loadOperator) {
        this.loadOperator = loadOperator;
    }

    public String getRoadLineId() {
        return this.roadLineId;
    }

    public void setRoadLineId(String roadLineId) {
        this.roadLineId = roadLineId;
    }

    public String getRoadLineIdOperator() {
        return this.roadLineIdOperator;
    }

    public void setRoadLineIdOperator(String roadLineIdOperator) {
        this.roadLineIdOperator = roadLineIdOperator;
    }

    public String getTrialId() {
        return this.trialId;
    }

    public void setTrialId(String trialId) {
        this.trialId = trialId;
    }

    public String getTrialIdOperator() {
        return this.trialIdOperator;
    }

    public void setTrialIdOperator(String trialIdOperator) {
        this.trialIdOperator = trialIdOperator;
    }

    public String getTower() {
        return this.tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
    }

    public String getTowerOperator() {
        return this.towerOperator;
    }

    public void setTowerOperator(String towerOperator) {
        this.towerOperator = towerOperator;
    }

    public String getFuelType() {
        return this.fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getFuelTypeOperator() {
        return this.fuelTypeOperator;
    }

    public void setFuelTypeOperator(String fuelTypeOperator) {
        this.fuelTypeOperator = fuelTypeOperator;
    }

    public String getCarType() {
        return this.carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarTypeOperator() {
        return this.carTypeOperator;
    }

    public void setCarTypeOperator(String carTypeOperator) {
        this.carTypeOperator = carTypeOperator;
    }

    public String getVehicleType() {
        return this.vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleTypeOperator() {
        return this.vehicleTypeOperator;
    }

    public void setVehicleTypeOperator(String vehicleTypeOperator) {
        this.vehicleTypeOperator = vehicleTypeOperator;
    }

    public String getChassisCode() {
        return this.chassisCode;
    }

    public void setChassisCode(String chassisCode) {
        this.chassisCode = chassisCode;
    }

    public String getChassisCodeOperator() {
        return this.chassisCodeOperator;
    }

    public void setChassisCodeOperator(String chassisCodeOperator) {
        this.chassisCodeOperator = chassisCodeOperator;
    }

    public String getCarId() {
        return this.carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarIdOperator() {
        return this.carIdOperator;
    }

    public void setCarIdOperator(String carIdOperator) {
        this.carIdOperator = carIdOperator;
    }

    public String getSupCode() {
        return this.supCode;
    }

    public void setSupCode(String supCode) {
        this.supCode = supCode;
    }

    public String getSupCodeOperator() {
        return this.supCodeOperator;
    }

    public void setSupCodeOperator(String supCodeOperator) {
        this.supCodeOperator = supCodeOperator;
    }

    public String getSupName() {
        return this.supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getSupNameOperator() {
        return this.supNameOperator;
    }

    public void setSupNameOperator(String supNameOperator) {
        this.supNameOperator = supNameOperator;
    }

    public String getSupId() {
        return this.supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getSupIdOperator() {
        return this.supIdOperator;
    }

    public void setSupIdOperator(String supIdOperator) {
        this.supIdOperator = supIdOperator;
    }

    public String getTrialProjectCode() {
        return this.trialProjectCode;
    }

    public void setTrialProjectCode(String trialProjectCode) {
        this.trialProjectCode = trialProjectCode;
    }

    public String getTrialProjectCodeOperator() {
        return this.trialProjectCodeOperator;
    }

    public void setTrialProjectCodeOperator(String trialProjectCodeOperator) {
        this.trialProjectCodeOperator = trialProjectCodeOperator;
    }

    public String getTrialProjectId() {
        return this.trialProjectId;
    }

    public void setTrialProjectId(String trialProjectId) {
        this.trialProjectId = trialProjectId;
    }

    public String getTrialProjectIdOperator() {
        return this.trialProjectIdOperator;
    }

    public void setTrialProjectIdOperator(String trialProjectIdOperator) {
        this.trialProjectIdOperator = trialProjectIdOperator;
    }

    public String getTaskBookName() {
        return this.taskBookName;
    }

    public void setTaskBookName(String taskBookName) {
        this.taskBookName = taskBookName;
    }

    public String getTaskBookNameOperator() {
        return this.taskBookNameOperator;
    }

    public void setTaskBookNameOperator(String taskBookNameOperator) {
        this.taskBookNameOperator = taskBookNameOperator;
    }

    public String getTaskBookCode() {
        return this.taskBookCode;
    }

    public void setTaskBookCode(String taskBookCode) {
        this.taskBookCode = taskBookCode;
    }

    public String getTaskBookCodeOperator() {
        return this.taskBookCodeOperator;
    }

    public void setTaskBookCodeOperator(String taskBookCodeOperator) {
        this.taskBookCodeOperator = taskBookCodeOperator;
    }

    public String getPcId() {
        return this.pcId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    public String getPcIdOperator() {
        return this.pcIdOperator;
    }

    public void setPcIdOperator(String pcIdOperator) {
        this.pcIdOperator = pcIdOperator;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOperator() {
        return this.idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

    public List<Map<String, Object>> getSearchMap() {
        return searchMap;
    }

    public void setSearchMap(List<Map<String, Object>> searchMap) {
        this.searchMap = searchMap;
    }
}
