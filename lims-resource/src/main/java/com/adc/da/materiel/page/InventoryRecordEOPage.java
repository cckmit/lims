package com.adc.da.materiel.page;

import com.adc.da.base.page.BasePage;


/**
 * <b>功能：</b>RES_INVENTORY_RECORD InventoryRecordEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class InventoryRecordEOPage extends BasePage {

    private String id;
    private String idOperator = "=";
    private String materielId;
    private String materielIdOperator = "=";
    private String sampleQuantity;
    private String sampleQuantityOperator = "=";
    private String outInTime;
    private String outInTimeOperator = "=";
    private String borrowerReturnee;
    private String borrowerReturneeOperator = "=";
    private String operationType;
    private String operationTypeOperator = "=";
    private String totalPrice;
    private String totalPriceOperator = "=";

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOperator() {
        return this.idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

    public String getMaterielId() {
        return this.materielId;
    }

    public void setMaterielId(String materielId) {
        this.materielId = materielId;
    }

    public String getMaterielIdOperator() {
        return this.materielIdOperator;
    }

    public void setMaterielIdOperator(String materielIdOperator) {
        this.materielIdOperator = materielIdOperator;
    }

    public String getSampleQuantity
            () {
        return this.sampleQuantity;
    }

    public void setSampleQuantity(String sampleQuantity) {
        this.sampleQuantity = sampleQuantity;
    }

    public String getSampleQuantityOperator() {
        return this.sampleQuantityOperator;
    }

    public void setSampleQuantityOperator(String sampleQuantityOperator) {
        this.sampleQuantityOperator = sampleQuantityOperator;
    }

    public String getOutInTime() {
        return this.outInTime;
    }

    public void setOutInTime(String outInTime) {
        this.outInTime = outInTime;
    }

    public String getOutInTimeOperator() {
        return this.outInTimeOperator;
    }

    public void setOutInTimeOperator(String outInTimeOperator) {
        this.outInTimeOperator = outInTimeOperator;
    }

    public String getBorrowerReturnee() {
        return this.borrowerReturnee;
    }

    public void setBorrowerReturnee(String borrowerReturnee) {
        this.borrowerReturnee = borrowerReturnee;
    }

    public String getBorrowerReturneeOperator() {
        return this.borrowerReturneeOperator;
    }

    public void setBorrowerReturneeOperator(String borrowerReturneeOperator) {
        this.borrowerReturneeOperator = borrowerReturneeOperator;
    }

    public String getOperationType() {
        return this.operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationTypeOperator() {
        return this.operationTypeOperator;
    }

    public void setOperationTypeOperator(String operationTypeOperator) {
        this.operationTypeOperator = operationTypeOperator;
    }

    public String getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalPriceOperator() {
        return this.totalPriceOperator;
    }

    public void setTotalPriceOperator(String totalPriceOperator) {
        this.totalPriceOperator = totalPriceOperator;
    }

}
