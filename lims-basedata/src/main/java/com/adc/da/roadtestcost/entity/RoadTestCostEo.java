package com.adc.da.roadtestcost.entity;

import com.adc.da.base.entity.BaseEntity;

public class RoadTestCostEo extends BaseEntity {
    private String id;//id
    private String delFlag;//删除标记 0存在，1删除
    private String testRoadSituation;//路况
    private String carType;//车型
    private String content;//具体内容
    private String cost;//价格
    private String carState;//是否带上装/挂
    private String hasFuelCost;//是否含电费
    private String fuelType;//燃料类型
    private String horsePower;//马力
    private String contract;//合同
    private String contractUrl;//合同地址

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

    public String getTestRoadSituation() {
        return testRoadSituation;
    }

    public void setTestRoadSituation(String testRoadSituation) {
        this.testRoadSituation = testRoadSituation;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public String getHasFuelCost() {
        return hasFuelCost;
    }

    public void setHasFuelCost(String hasFuelCost) {
        this.hasFuelCost = hasFuelCost;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(String horsePower) {
        this.horsePower = horsePower;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }
}
