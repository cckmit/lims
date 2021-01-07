package com.adc.da.internalcost.vo;

import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>PC_BUDGET_USE_INFO PcBudgetUseInfoEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-06 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcBudgetUseInfoVO {

    @ApiModelProperty("费用使用申请单id")
    private String buId;
    @ApiModelProperty("项目")
    private String buProject;
    @ApiModelProperty("内容")
    private String buContent;
    @ApiModelProperty("单价")
    private String buUnitPrice;
    @ApiModelProperty("数量")
    private String buCount;
    @ApiModelProperty("小计")
    private String buSubtotal;
    @ApiModelProperty("服务方")
    private String buSupplier;

    public String getBuId() {
        return buId;
    }

    public void setBuId(String buId) {
        this.buId = buId;
    }

    public String getBuProject() {
        return buProject;
    }

    public void setBuProject(String buProject) {
        this.buProject = buProject;
    }

    public String getBuContent() {
        return buContent;
    }

    public void setBuContent(String buContent) {
        this.buContent = buContent;
    }

    public String getBuUnitPrice() {
        return buUnitPrice;
    }

    public void setBuUnitPrice(String buUnitPrice) {
        this.buUnitPrice = buUnitPrice;
    }

    public String getBuCount() {
        return buCount;
    }

    public void setBuCount(String buCount) {
        this.buCount = buCount;
    }

    public String getBuSubtotal() {
        return buSubtotal;
    }

    public void setBuSubtotal(String buSubtotal) {
        this.buSubtotal = buSubtotal;
    }

    public String getBuSupplier() {
        return buSupplier;
    }

    public void setBuSupplier(String buSupplier) {
        this.buSupplier = buSupplier;
    }
}
