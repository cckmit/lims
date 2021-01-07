package com.adc.da.roadcost.entity;

import com.adc.da.base.entity.BaseEntity;

public class RoadCostEo extends BaseEntity {
    private String id;//id
    private String delFlag;//删除标记 0存在，1删除
    private String roadLine;//路线
    private String carType;//车型
    private String horsePower;//马力
    private String cost;//价格
    private String supId;//供应商id
    private String supName;//供应商名称
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

    public String getRoadLine() {
        return roadLine;
    }

    public void setRoadLine(String roadLine) {
        this.roadLine = roadLine;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(String horsePower) {
        this.horsePower = horsePower;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
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
