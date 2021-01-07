package com.adc.da.pc_drive.vo;

/**
 * 路送路试计划里程
 */
public class PlanMileage {
    private Double roadMileage;//路送计划里程
    private Double roadTestMileage;//路试计划里程
    private String operationId;//操作人id
    private String horsePower;//马力
    private String taskCode;//任务书编号
    private String entrustRelated; //委托承接相关方

    public Double getRoadMileage() {
        return roadMileage;
    }

    public void setRoadMileage(Double roadMileage) {
        this.roadMileage = roadMileage;
    }

    public Double getRoadTestMileage() {
        return roadTestMileage;
    }

    public void setRoadTestMileage(Double roadTestMileage) {
        this.roadTestMileage = roadTestMileage;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(String horsePower) {
        this.horsePower = horsePower;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getEntrustRelated() {
        return entrustRelated;
    }

    public void setEntrustRelated(String entrustRelated) {
        this.entrustRelated = entrustRelated;
    }
}

