package com.adc.da.pc_loan_application.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>PC_CAR_LOAN_INFORMATION PcCarLoanInformationEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-26 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcCarLoanInformationEO extends BaseEntity {

    @ApiModelProperty("样车id")
    private String saCarDataId;
    @ApiModelProperty("主表id")
    private String loadApplicationId;
    @ApiModelProperty("预计归还日期")
    private String returnDate;
    @ApiModelProperty("样车状态")
    private String prototypeStatus;
    @ApiModelProperty("购买日期")
    private String purchaseDate;
    @ApiModelProperty("生产日期")
    private String productionDate;
    @ApiModelProperty("排放类型")
    private String emissionType;
    @ApiModelProperty("品牌")
    private String brand;
    @ApiModelProperty("车型")
    private String vehicleModel;
    @ApiModelProperty("发动机号")
    private String engineNum;
    @ApiModelProperty("底盘号")
    private String chassisCode;
    @ApiModelProperty("主键")
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>saCarDataId -> sa_car_data_id</li>
     * <li>loadApplicationId -> load_application_id</li>
     * <li>returnDate -> return_date</li>
     * <li>prototypeStatus -> prototype_status</li>
     * <li>purchaseDate -> purchase_date</li>
     * <li>productionDate -> production_date</li>
     * <li>emissionType -> emission_type</li>
     * <li>brand -> brand</li>
     * <li>vehicleModel -> vehicle_model</li>
     * <li>engineNum -> engine_num</li>
     * <li>chassisCode -> chassis_code</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) { return null; }
        switch (fieldName) {
            case "saCarDataId": return "sa_car_data_id";
            case "loadApplicationId": return "load_application_id";
            case "returnDate": return "return_date";
            case "prototypeStatus": return "prototype_status";
            case "purchaseDate": return "purchase_date";
            case "productionDate": return "production_date";
            case "emissionType": return "emission_type";
            case "brand": return "brand";
            case "vehicleModel": return "vehicle_model";
            case "engineNum": return "engine_num";
            case "chassisCode": return "chassis_code";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>sa_car_data_id -> saCarDataId</li>
     * <li>load_application_id -> loadApplicationId</li>
     * <li>return_date -> returnDate</li>
     * <li>prototype_status -> prototypeStatus</li>
     * <li>purchase_date -> purchaseDate</li>
     * <li>production_date -> productionDate</li>
     * <li>emission_type -> emissionType</li>
     * <li>brand -> brand</li>
     * <li>vehicle_model -> vehicleModel</li>
     * <li>engine_num -> engineNum</li>
     * <li>chassis_code -> chassisCode</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) { return null; }
        switch (columnName) {
            case "sa_car_data_id": return "saCarDataId";
            case "load_application_id": return "loadApplicationId";
            case "return_date": return "returnDate";
            case "prototype_status": return "prototypeStatus";
            case "purchase_date": return "purchaseDate";
            case "production_date": return "productionDate";
            case "emission_type": return "emissionType";
            case "brand": return "brand";
            case "vehicle_model": return "vehicleModel";
            case "engine_num": return "engineNum";
            case "chassis_code": return "chassisCode";
            case "id": return "id";
            default: return null;
        }
    }
    
    public String getSaCarDataId() {
        return this.saCarDataId;
    }

    public void setSaCarDataId(String saCarDataId) {
        this.saCarDataId = saCarDataId;
    }

    public String getLoadApplicationId() {
        return this.loadApplicationId;
    }

    public void setLoadApplicationId(String loadApplicationId) {
        this.loadApplicationId = loadApplicationId;
    }

    public String getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getPrototypeStatus() {
        return this.prototypeStatus;
    }

    public void setPrototypeStatus(String prototypeStatus) {
        this.prototypeStatus = prototypeStatus;
    }

    public String getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getProductionDate() {
        return this.productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getEmissionType() {
        return this.emissionType;
    }

    public void setEmissionType(String emissionType) {
        this.emissionType = emissionType;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVehicleModel() {
        return this.vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getEngineNum() {
        return this.engineNum;
    }

    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum;
    }

    public String getChassisCode() {
        return this.chassisCode;
    }

    public void setChassisCode(String chassisCode) {
        this.chassisCode = chassisCode;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
