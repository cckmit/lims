package com.adc.da.materiel.VO;

public class MaterielVO {
    /**
     * id
     */
    private String id;
    /**
     * 物料名称
     */
    private String materielName;
    /**
     * 物料编码
     */
    private String materielCode;
    /**
     * 物料类别
     */
    private String materielType;
    /**
     * 规格型号
     */
    private String normModel;
    /**
     * 生产厂家
     */
    private String manufacturer;
    /**
     * 品牌
     */
    private String brand;
    /**
     * 样件数量
     */
    private Double sampleQuantity;
    /**
     * 单价
     */
    private Double unitPrice;
    /**
     * 用途
     */
    private String purpose;
    /**
     * 单位
     */
    private String unit;
    /**
     * 采购日期
     */
    private String procurementDate;
    /**
     * 生产日期
     */
    private String manufactureDate;
    /**
     * 有效期
     */
    private Double termOfValidity;
    /**
     * 采购流程号
     */
    private String purchaseProcessNo;
    /**
     * 所在位置
     */
    private String location;
    /**
     * 库存上限
     */
    private Double inventoryCeiling;
    /**
     * 库存下限
     */
    private Double inventoryFloor;
    /**
     * 录入人
     */
    private String entryBy;
    /**
     *录入时间
     */
    private String entryTime;
    /**
     * 备注
     */
    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterielName() {
        return materielName;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    public String getMaterielCode() {
        return materielCode;
    }

    public void setMaterielCode(String materielCode) {
        this.materielCode = materielCode;
    }

    public String getMaterielType() {
        return materielType;
    }

    public void setMaterielType(String materielType) {
        this.materielType = materielType;
    }

    public String getNormModel() {
        return normModel;
    }

    public void setNormModel(String normModel) {
        this.normModel = normModel;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getSampleQuantity() {
        return sampleQuantity;
    }

    public void setSampleQuantity(Double sampleQuantity) {
        this.sampleQuantity = sampleQuantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProcurementDate() {
        return procurementDate;
    }

    public void setProcurementDate(String procurementDate) {
        this.procurementDate = procurementDate;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public Double getTermOfValidity() {
        return termOfValidity;
    }

    public void setTermOfValidity(Double termOfValidity) {
        this.termOfValidity = termOfValidity;
    }

    public String getPurchaseProcessNo() {
        return purchaseProcessNo;
    }

    public void setPurchaseProcessNo(String purchaseProcessNo) {
        this.purchaseProcessNo = purchaseProcessNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getInventoryCeiling() {
        return inventoryCeiling;
    }

    public void setInventoryCeiling(Double inventoryCeiling) {
        this.inventoryCeiling = inventoryCeiling;
    }

    public Double getInventoryFloor() {
        return inventoryFloor;
    }

    public void setInventoryFloor(Double inventoryFloor) {
        this.inventoryFloor = inventoryFloor;
    }

    public String getEntryBy() {
        return entryBy;
    }

    public void setEntryBy(String entryBy) {
        this.entryBy = entryBy;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
