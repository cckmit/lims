package com.adc.da.internalcost.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/11/15 9:13
 * @description：费用申请统计
 */
public class CostApplyVO {

    @ApiModelProperty("任务书编号")
    private String trialTaskCode;
    @ApiModelProperty("车辆底盘号")
    private String underpanNo;
    @ApiModelProperty("项目")
    private String project;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("单价（元）")
    private String unitPrice;
    @ApiModelProperty("数量")
    private String pcNumber;
    @ApiModelProperty("价格（元）")
    private String priceTotal;
    @ApiModelProperty("服务方")
    private String supplierName;
    @ApiModelProperty("申请日期")
    private String applyTime;
    @ApiModelProperty("申请人")
    private String applyUser;

    public String getTrialTaskCode() {
        return trialTaskCode;
    }

    public void setTrialTaskCode(String trialTaskCode) {
        this.trialTaskCode = trialTaskCode;
    }

    public String getUnderpanNo() {
        return underpanNo;
    }

    public void setUnderpanNo(String underpanNo) {
        this.underpanNo = underpanNo;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPcNumber() {
        return pcNumber;
    }

    public void setPcNumber(String pcNumber) {
        this.pcNumber = pcNumber;
    }

    public String getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(String priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }
}
