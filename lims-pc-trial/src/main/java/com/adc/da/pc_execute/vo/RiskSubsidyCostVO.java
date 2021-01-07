package com.adc.da.pc_execute.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/11/27 11:29
 * @description：风险补贴费用跟踪
 */
public class RiskSubsidyCostVO {

    @ApiModelProperty("行车记录卡ID")
    private String id;
    @ApiModelProperty("试验道路类型")
    private String lineType;
    @ApiModelProperty("试验里程（公里）")
    private String totalMileage;
    @ApiModelProperty("每公里补助标准（元/公里）")
    private String stdPrice;
    @ApiModelProperty("实际里程（公里）")
    private String singleMileage;
    @ApiModelProperty("行车天数（天）")
    private String driveDays;
    @ApiModelProperty("实际试验项目补助额（元）")
    private String allSubsidy;
    @ApiModelProperty("每天试验项目补助额标准（元）")
    private String daySubsidy;
    @ApiModelProperty("偏差值（元）")
    private String deviationValue;
    @ApiModelProperty("风险评估(-1，红色；0，黄色；1，绿色)")
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

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getTotalMileage() {
        return totalMileage;
    }

    public void setTotalMileage(String totalMileage) {
        this.totalMileage = totalMileage;
    }

    public String getStdPrice() {
        return stdPrice;
    }

    public void setStdPrice(String stdPrice) {
        this.stdPrice = stdPrice;
    }

    public String getSingleMileage() {
        return singleMileage;
    }

    public void setSingleMileage(String singleMileage) {
        this.singleMileage = singleMileage;
    }

    public String getDriveDays() {
        return driveDays;
    }

    public void setDriveDays(String driveDays) {
        this.driveDays = driveDays;
    }

    public String getAllSubsidy() {
        return allSubsidy;
    }

    public void setAllSubsidy(String allSubsidy) {
        this.allSubsidy = allSubsidy;
    }

    public String getDaySubsidy() {
        return daySubsidy;
    }

    public void setDaySubsidy(String daySubsidy) {
        this.daySubsidy = daySubsidy;
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
