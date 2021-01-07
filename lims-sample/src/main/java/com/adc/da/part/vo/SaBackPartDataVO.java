package com.adc.da.part.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/29 11:07
 * @description：${description}
 */
public class SaBackPartDataVO extends SaPartDataVO {

    @ApiModelProperty("退样人")
    private String backPartUser;

    @ApiModelProperty("退样人部门")
    private String backPartUserOrg;

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

    public String getBackPartUser() {
        return backPartUser;
    }

    public void setBackPartUser(String backPartUser) {
        this.backPartUser = backPartUser;
    }

    public String getBackPartUserOrg() {
        return backPartUserOrg;
    }

    public void setBackPartUserOrg(String backPartUserOrg) {
        this.backPartUserOrg = backPartUserOrg;
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

    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
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
