package com.adc.da.materiel.page;

import com.adc.da.base.page.BasePage;

import java.util.List;
import java.util.Map;

public class MaterielEOPage extends BasePage {

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
     * 品牌
     */
    private String brand;
    /**
     * 用途
     */
    private String purpose;
    /**
     * 单位
     */
    private String unit;
    /**
     * 所在位置
     */
    private String location;
    /**
     * 剩余库存
     */
    private Double inventorySurpius;
    /**
     * 物料状态
     */
    private String materialStatus;
    /**
     * 录入日期
     */
    private String entryTime;
    //库存上限
    private Double inventoryCeiling;
    //库存下限
    private Double inventoryFloor;
    /**
     * 通用查询条件
     */
    private List<Map<String, Object>> searchMap; 

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getInventorySurpius() {
        return inventorySurpius;
    }

    public void setInventorySurpius(Double inventorySurpius) {
        this.inventorySurpius = inventorySurpius;
    }

    public String getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(String materialStatus) {
        this.materialStatus = materialStatus;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public List<Map<String, Object>> getSearchMap() {
		return searchMap;
	}

	public void setSearchMap(List<Map<String, Object>> searchMap) {
		this.searchMap = searchMap;
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
}
