package com.adc.da.pc_execute.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/11/27 11:30
 * @description：试验费用跟踪
 */
public class TrialCostVO {

    @ApiModelProperty("行车记录卡ID")
    private String id;
    @ApiModelProperty("项目")
    private String project;
    @ApiModelProperty("费用单价")
    private String costPrice;
    @ApiModelProperty("单位")
    private String unitPrice;
    @ApiModelProperty("实际金额")
    private String realQuantity;
    @ApiModelProperty("实际累计金额")
    private String realAmount;
    @ApiModelProperty("发生时里程")
    private String startMileage;
    @ApiModelProperty("平均油耗（百公里油数）")
    private String avgFuelConsumption;
    @ApiModelProperty("预计金额")
    private String estimatedAmount;
    @ApiModelProperty("偏差值（元）")
    private String deviationValue;
    @ApiModelProperty("风险评估")
    private String riskAssessment;
    @ApiModelProperty("备注")
    private String remarks;
    @ApiModelProperty("风险评估ID")
    private String riskId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getRealQuantity() {
        return realQuantity;
    }

    public void setRealQuantity(String realQuantity) {
        this.realQuantity = realQuantity;
    }

    public String getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(String realAmount) {
        this.realAmount = realAmount;
    }

    public String getStartMileage() {
        return startMileage;
    }

    public void setStartMileage(String startMileage) {
        this.startMileage = startMileage;
    }

    public String getAvgFuelConsumption() {
        return avgFuelConsumption;
    }

    public void setAvgFuelConsumption(String avgFuelConsumption) {
        this.avgFuelConsumption = avgFuelConsumption;
    }

    public String getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(String estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }

    public String getDeviationValue() {
        return deviationValue;
    }

    public void setDeviationValue(String deviationValue) {
        this.deviationValue = deviationValue;
    }

    public String getRiskAssessment() {
        return riskAssessment;
    }

    public void setRiskAssessment(String riskAssessment) {
        this.riskAssessment = riskAssessment;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }
}
