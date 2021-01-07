package com.adc.da.materiel.VO;

import com.adc.da.materiel.entity.InventoryRecordEO;

import java.util.ArrayList;
import java.util.List;

/**
 * 出入库列表VO。用来展示出/入库记录
 * 2019.11.19
 * ly
 */
public class InboundListVO {
    private String materielType;//物料类别
    private String materielCode;//物料编码
    private String materielName;//物料名称
    private String normModel;//规格型号
    private String unit;//单位
    private Long sampleQuantityOfBorrow;//样件数量
    private Long unitPrice;//单价
    private String manufacturer;//生产厂家
    private String purchaseProcessNo;//采购流程号
    private String operationType;//操作类别
    private Long totalPrice;//总价
    private String borrowerReturnee;//领用人,归还人
    private String outInTime;//出库时间，入库时间

    public Long getSampleQuantityOfBorrow() {
        return sampleQuantityOfBorrow;
    }

    public void setSampleQuantityOfBorrow(Long sampleQuantityOfBorrow) {
        this.sampleQuantityOfBorrow = sampleQuantityOfBorrow;
    }

    public String getBorrowerReturnee() {
        return borrowerReturnee;
    }

    public void setBorrowerReturnee(String borrowerReturnee) {
        this.borrowerReturnee = borrowerReturnee;
    }

    public String getOutInTime() {
        return outInTime;
    }

    public void setOutInTime(String outInTime) {
        this.outInTime = outInTime;
    }

    public String getMaterielType() {
        return materielType;
    }

    public void setMaterielType(String materielType) {
        this.materielType = materielType;
    }

    public String getMaterielCode() {
        return materielCode;
    }

    public void setMaterielCode(String materielCode) {
        this.materielCode = materielCode;
    }

    public String getMaterielName() {
        return materielName;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    public String getNormModel() {
        return normModel;
    }

    public void setNormModel(String normModel) {
        this.normModel = normModel;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getSampleQuantity() {
        return sampleQuantityOfBorrow;
    }

    public void setSampleQuantity(Long sampleQuantity) {
        this.sampleQuantityOfBorrow = sampleQuantity;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPurchaseProcessNo() {
        return purchaseProcessNo;
    }

    public void setPurchaseProcessNo(String purchaseProcessNo) {
        this.purchaseProcessNo = purchaseProcessNo;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

}
