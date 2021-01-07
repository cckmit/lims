package com.adc.da.car.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/26 15:00
 * @description：${description}
 */
public class SaBackCarDataVO extends SaCarDataVO {

    @ApiModelProperty("退样人")
    private String backCarUser;

    @ApiModelProperty("退样人部门")
    private String backCarUserOrg;

    @ApiModelProperty("经办人")
    private String operator;

    @ApiModelProperty("经办人部门")
    private String operatorOrg;

    @ApiModelProperty("交接时间")
    private String receivTime;

    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("报告结果确认")
    private String reportResult;

    public String getBackCarUser() {
        return backCarUser;
    }

    public void setBackCarUser(String backCarUser) {
        this.backCarUser = backCarUser;
    }

    public String getBackCarUserOrg() {
        return backCarUserOrg;
    }

    public void setBackCarUserOrg(String backCarUserOrg) {
        this.backCarUserOrg = backCarUserOrg;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorOrg() {
        return operatorOrg;
    }

    public void setOperatorOrg(String operatorOrg) {
        this.operatorOrg = operatorOrg;
    }

    public String getReceivTime() {
        return receivTime;
    }

    public void setReceivTime(String receivTime) {
        this.receivTime = receivTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReportResult() {
        return reportResult;
    }

    public void setReportResult(String reportResult) {
        this.reportResult = reportResult;
    }
}
