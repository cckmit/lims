package com.adc.da.materiel.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;


/**
 *物料实体
 * 2019/11/19
 * lcx
 */
public class MaterielEO extends BaseEntity {
    //id
    private String id;
    //物料名称
    private String materielName;
    //物料编码
    private String materielCode;
    //物料类别
    private String materielType;
    //规格型号
    private String normModel;
    //规格型号
    private String manufacturer;
    //品牌
    private String brand;
    //样件数量
    private Double sampleQuantity;
    //单价
    private Double unitPrice;
    //用途
    private String purpose;
    //单位
    private String unit;
    //采购日期
    private String procurementDate;
    //生产日期
    private String manufactureDate;
    //有效期
    private Double termOfValidity;
    //采购流程号
    private String purchaseProcessNo;
    //所在位置
    private String location;
    //库存上限
    private Double inventoryCeiling;
    //库存下限
    private Double inventoryFloor;
    //剩余库存
    private Double inventorySurpius;
    //物料状态（0有效，1报废）
    private String materialStatus;
    //录入人
    private String entryBy;
    //录入时间
    private String entryTime;
    //创建人
    private String createBy;
    //创建时间
    private String createTime;
    //修改人
    private String updateBy;
    //修改时间
    private String updateTime;
    //删除标记
    private Integer delFlag;
    //备注
    private String remarks;

    //下面的字段是为了前台处理数据方便添加的，在数据库中没有该字段
    private List<InventoryRecordEO> inventoryRecordEOList=new ArrayList<>();
    private Double sampleQuantityOfBorrow;
    private String outInTime;
    private String borrowerReturnee;
    private String operationType;
    private Double totalPrice;

    public Double getSampleQuantityOfBorrow() {
        return sampleQuantityOfBorrow;
    }

    public void setSampleQuantityOfBorrow(Double sampleQuantityOfBorrow) {
        this.sampleQuantityOfBorrow = sampleQuantityOfBorrow;
    }

    public String getOutInTime() {
        return outInTime;
    }

    public void setOutInTime(String outInTime) {
        this.outInTime = outInTime;
    }

    public String getBorrowerReturnee() {
        return borrowerReturnee;
    }

    public void setBorrowerReturnee(String borrowerReturnee) {
        this.borrowerReturnee = borrowerReturnee;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>materielName -> materiel_name</li>
     * <li>materielCode -> materiel_code</li>
     * <li>materielType -> materiel_type</li>
     * <li>normModel -> norm_model</li>
     * <li>manufacturer -> manufacturer</li>
     * <li>brand -> brand</li>
     * <li>sampleQuantity -> sample_quantity</li>
     * <li>unitPrice -> unit_price</li>
     * <li>purpose -> purpose</li>
     * <li>unit -> unit</li>
     * <li>procurementDate -> procurement_date</li>
     * <li>manufactureDate -> manufacture_date</li>
     * <li>termOfValidity -> term_of_validity</li>
     * <li>purchaseProcessNo -> purchase_process_no</li>
     * <li>location -> location</li>
     * <li>inventoryCeiling -> inventory_ceiling</li>
     * <li>inventoryFloor -> inventory_floor</li>
     * <li>inventorySurpius -> inventory_surpius</li>
     * <li>materialStatus -> material_status</li>
     * <li>entryBy -> entry_by</li>
     * <li>entryTime -> entry_time</li>
     * <li>createBy -> create_by</li>
     * <li>createTime -> create_time</li>
     * <li>updateBy -> update_by</li>
     * <li>updateTime -> update_time</li>
     * <li>delFlag -> del_flag</li>
     * <li>remarks -> remarks</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "materielName": return "materiel_name";
            case "materielCode": return "materiel_code";
            case "materielType": return "materiel_type";
            case "normModel": return "norm_model";
            case "manufacturer": return "manufacturer";
            case "brand": return "brand";
            case "sampleQuantity": return "sample_quantity";
            case "unitPrice": return "unit_price";
            case "purpose": return "purpose";
            case "unit": return "unit";
            case "procurementDate": return "procurement_date";
            case "manufactureDate": return "manufacture_date";
            case "termOfValidity": return "term_of_validity";
            case "purchaseProcessNo": return "purchase_process_no";
            case "location": return "location";
            case "inventoryCeiling": return "inventory_ceiling";
            case "inventoryFloor": return "inventory_floor";
            case "inventorySurpius": return "inventory_surpius";
            case "materialStatus": return "material_status";
            case "entryBy": return "entry_by";
            case "entryTime": return "entry_time";
            case "createBy": return "create_by";
            case "createTime": return "create_time";
            case "updateBy": return "update_by";
            case "updateTime": return "update_time";
            case "delFlag": return "del_flag";
            case "remarks": return "remarks";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>materiel_name -> materielName</li>
     * <li>materiel_code -> materielCode</li>
     * <li>materiel_type -> materielType</li>
     * <li>norm_model -> normModel</li>
     * <li>manufacturer -> manufacturer</li>
     * <li>brand -> brand</li>
     * <li>sample_quantity -> sampleQuantity</li>
     * <li>unit_price -> unitPrice</li>
     * <li>purpose -> purpose</li>
     * <li>unit -> unit</li>
     * <li>procurement_date -> procurementDate</li>
     * <li>manufacture_date -> manufactureDate</li>
     * <li>term_of_validity -> termOfValidity</li>
     * <li>purchase_process_no -> purchaseProcessNo</li>
     * <li>location -> location</li>
     * <li>inventory_ceiling -> inventoryCeiling</li>
     * <li>inventory_floor -> inventoryFloor</li>
     * <li>inventory_surpius -> inventorySurpius</li>
     * <li>material_status -> materialStatus</li>
     * <li>entry_by -> entryBy</li>
     * <li>entry_time -> entryTime</li>
     * <li>create_by -> createBy</li>
     * <li>create_time -> createTime</li>
     * <li>update_by -> updateBy</li>
     * <li>update_time -> updateTime</li>
     * <li>del_flag -> delFlag</li>
     * <li>remarks -> remarks</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "materiel_name": return "materielName";
            case "materiel_code": return "materielCode";
            case "materiel_type": return "materielType";
            case "norm_model": return "normModel";
            case "manufacturer": return "manufacturer";
            case "brand": return "brand";
            case "sample_quantity": return "sampleQuantity";
            case "unit_price": return "unitPrice";
            case "purpose": return "purpose";
            case "unit": return "unit";
            case "procurement_date": return "procurementDate";
            case "manufacture_date": return "manufactureDate";
            case "term_of_validity": return "termOfValidity";
            case "purchase_process_no": return "purchaseProcessNo";
            case "location": return "location";
            case "inventory_ceiling": return "inventoryCeiling";
            case "inventory_floor": return "inventoryFloor";
            case "inventory_surpius": return "inventorySurpius";
            case "material_status": return "materialStatus";
            case "entry_by": return "entryBy";
            case "entry_time": return "entryTime";
            case "create_by": return "createBy";
            case "create_time": return "createTime";
            case "update_by": return "updateBy";
            case "update_time": return "updateTime";
            case "del_flag": return "delFlag";
            case "remarks": return "remarks";
            default: return null;
        }
    }

    public List<InventoryRecordEO> getInventoryRecordEOList() {
        return inventoryRecordEOList;
    }

    public void setInventoryRecordEOList(List<InventoryRecordEO> inventoryRecordEOList) {
        this.inventoryRecordEOList = inventoryRecordEOList;
    }

    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /**  **/
    public String getMaterielName() {
        return this.materielName;
    }

    /**  **/
    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    /**  **/
    public String getMaterielCode() {
        return this.materielCode;
    }

    /**  **/
    public void setMaterielCode(String materielCode) {
        this.materielCode = materielCode;
    }

    /**  **/
    public String getMaterielType() {
        return this.materielType;
    }

    /**  **/
    public void setMaterielType(String materielType) {
        this.materielType = materielType;
    }

    /**  **/
    public String getNormModel() {
        return this.normModel;
    }

    /**  **/
    public void setNormModel(String normModel) {
        this.normModel = normModel;
    }

    /**  **/
    public String getManufacturer() {
        return this.manufacturer;
    }

    /**  **/
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**  **/
    public String getBrand() {
        return this.brand;
    }

    /**  **/
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**  **/
    public Double getSampleQuantity() {
        return this.sampleQuantity;
    }

    /**  **/
    public void setSampleQuantity(Double sampleQuantity) {
        this.sampleQuantity = sampleQuantity;
    }

    /**  **/
    public Double getUnitPrice() {
        return this.unitPrice;
    }

    /**  **/
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**  **/
    public String getPurpose() {
        return this.purpose;
    }

    /**  **/
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**  **/
    public String getUnit() {
        return this.unit;
    }

    /**  **/
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**  **/
    public String getProcurementDate() {
        return this.procurementDate;
    }

    /**  **/
    public void setProcurementDate(String procurementDate) {
        this.procurementDate = procurementDate;
    }

    /**  **/
    public String getManufactureDate() {
        return this.manufactureDate;
    }

    /**  **/
    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    /**  **/
    public Double getTermOfValidity() {
        return this.termOfValidity;
    }

    /**  **/
    public void setTermOfValidity(Double termOfValidity) {
        this.termOfValidity = termOfValidity;
    }

    /**  **/
    public String getPurchaseProcessNo() {
        return this.purchaseProcessNo;
    }

    /**  **/
    public void setPurchaseProcessNo(String purchaseProcessNo) {
        this.purchaseProcessNo = purchaseProcessNo;
    }

    /**  **/
    public String getLocation() {
        return this.location;
    }

    /**  **/
    public void setLocation(String location) {
        this.location = location;
    }

    /**  **/
    public Double getInventoryCeiling() {
        return this.inventoryCeiling;
    }

    /**  **/
    public void setInventoryCeiling(Double inventoryCeiling) {
        this.inventoryCeiling = inventoryCeiling;
    }

    /**  **/
    public Double getInventoryFloor() {
        return this.inventoryFloor;
    }

    /**  **/
    public void setInventoryFloor(Double inventoryFloor) {
        this.inventoryFloor = inventoryFloor;
    }

    /**  **/
    public Double getInventorySurpius() {
        return this.inventorySurpius;
    }

    /**  **/
    public void setInventorySurpius(Double inventorySurpius) {
        this.inventorySurpius = inventorySurpius;
    }

    /**  **/
    public String getMaterialStatus() {
        return this.materialStatus;
    }

    /**  **/
    public void setMaterialStatus(String materialStatus) {
        this.materialStatus = materialStatus;
    }

    /**  **/
    public String getEntryBy() {
        return this.entryBy;
    }

    /**  **/
    public void setEntryBy(String entryBy) {
        this.entryBy = entryBy;
    }

    /**  **/
    public String getEntryTime() {
        return this.entryTime;
    }

    /**  **/
    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    /**  **/
    public String getCreateBy() {
        return this.createBy;
    }

    /**  **/
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**  **/
    public String getCreateTime() {
        return this.createTime;
    }

    /**  **/
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**  **/
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**  **/
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**  **/
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**  **/
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**  **/
    public Integer getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**  **/
    public String getRemarks() {
        return this.remarks;
    }

    /**  **/
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
