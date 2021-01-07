package com.adc.da.pc_execute.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/11/27 11:31
 * @description：人员、住宿费用跟踪
 */
public class PersonAccommodationCostVO {

    @ApiModelProperty("行车记录卡ID")
    private String id;
    @ApiModelProperty("项目")
    private String project;
    @ApiModelProperty("费用（元）")
    private String costPrice;
    @ApiModelProperty("单位")
    private String unitPrice;
    @ApiModelProperty("实际天数")
    private String realDays;
    @ApiModelProperty("计划天数")
    private String planDays;
    @ApiModelProperty("实际费用")
    private String realCost;
    @ApiModelProperty("计划费用")
    private String planCost;
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

    public String getRealDays() {
        return realDays;
    }

    public void setRealDays(String realDays) {
        this.realDays = realDays;
    }

    public String getPlanDays() {
        return planDays;
    }

    public void setPlanDays(String planDays) {
        this.planDays = planDays;
    }

    public String getRealCost() {
        return realCost;
    }

    public void setRealCost(String realCost) {
        this.realCost = realCost;
    }

    public String getPlanCost() {
        return planCost;
    }

    public void setPlanCost(String planCost) {
        this.planCost = planCost;
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
