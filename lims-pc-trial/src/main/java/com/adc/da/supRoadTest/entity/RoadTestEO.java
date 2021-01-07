package com.adc.da.supRoadTest.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * <b>功能：</b>SUP_ROAD_TEST RoadTestEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2020-08-12 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class RoadTestEO extends BaseEntity {

    @ApiModelProperty("费用总计：包含里程费用，停工补贴，出差/异地路送补贴")
    private String total;
    @ApiModelProperty("出差/异地路送补贴金额")
    private String evectionSubsidy;
    @ApiModelProperty("停工补贴金额")
    private String subsidyOff;
    @ApiModelProperty("出差/异地路试天数")
    private String evectionDays;
    @ApiModelProperty("停工天数")
    private String daysOff;
    @ApiModelProperty("行车记录中的试验状态")
    private String trialState;
    @ApiModelProperty("行车记录中的里程费用")
    private String countPrice;
    @ApiModelProperty("行车记录中的里程")
    private String milePlus;
    @ApiModelProperty("行车记录中的行车路况")
    private String testConditions;
    @ApiModelProperty("行车记录中的委托类型")
    private String drType;
    @ApiModelProperty("行车记录中的是否带上装/挂")
    private String carState;
    @ApiModelProperty("行车记录中的载重")
    private String load;
    @ApiModelProperty("行车记录ID")
    private String roadLineId;
    @ApiModelProperty("行车记录表单ID")
    private String trialId;
    @ApiModelProperty("马力")
    private String tower;
    @ApiModelProperty("燃料类型")
    private String fuelType;
    @ApiModelProperty("车辆类型")
    private String carType;
    @ApiModelProperty("车型号")
    private String vehicleType;
    @ApiModelProperty("底盘号")
    private String chassisCode;
    @ApiModelProperty("车辆id")
    private String carId;
    @ApiModelProperty("供应商code")
    private String supCode;
    @ApiModelProperty("供应商名称")
    private String supName;
    @ApiModelProperty("供应商id")
    private String supId;
    @ApiModelProperty("路送路试委托单编号")
    private String trialProjectCode;
    @ApiModelProperty("路送路试委托单ID")
    private String trialProjectId;
    @ApiModelProperty("任务名称")
    private String taskBookName;
    @ApiModelProperty("试验任务书编号")
    private String taskBookCode;
    @ApiModelProperty("试验任务id")
    private String pcId;
    @ApiModelProperty("")
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>total -> total</li>
     * <li>evectionSubsidy -> evection_subsidy</li>
     * <li>subsidyOff -> subsidy_off</li>
     * <li>evectionDays -> evection_days</li>
     * <li>daysOff -> days_off</li>
     * <li>trialState -> trial_state</li>
     * <li>countPrice -> count_price</li>
     * <li>milePlus -> mile_plus</li>
     * <li>testConditions -> test_conditions</li>
     * <li>drType -> dr_type</li>
     * <li>carState -> car_state</li>
     * <li>load -> load</li>
     * <li>roadLineId -> road_line_id</li>
     * <li>trialId -> trial_id</li>
     * <li>tower -> tower</li>
     * <li>fuelType -> fuel_type</li>
     * <li>carType -> car_type</li>
     * <li>vehicleType -> vehicle_type</li>
     * <li>chassisCode -> chassis_code</li>
     * <li>carId -> car_id</li>
     * <li>supCode -> sup_code</li>
     * <li>supName -> sup_name</li>
     * <li>supId -> sup_id</li>
     * <li>trialProjectCode -> trial_project_code</li>
     * <li>trialProjectId -> trial_project_id</li>
     * <li>taskBookName -> task_book_name</li>
     * <li>taskBookCode -> task_book_code</li>
     * <li>pcId -> pc_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) { return null; }
        switch (fieldName) {
            case "total": return "total";
            case "evectionSubsidy": return "evection_subsidy";
            case "subsidyOff": return "subsidy_off";
            case "evectionDays": return "evection_days";
            case "daysOff": return "days_off";
            case "trialState": return "trial_state";
            case "countPrice": return "count_price";
            case "milePlus": return "mile_plus";
            case "testConditions": return "test_conditions";
            case "drType": return "dr_type";
            case "carState": return "car_state";
            case "load": return "load";
            case "roadLineId": return "road_line_id";
            case "trialId": return "trial_id";
            case "tower": return "tower";
            case "fuelType": return "fuel_type";
            case "carType": return "car_type";
            case "vehicleType": return "vehicle_type";
            case "chassisCode": return "chassis_code";
            case "carId": return "car_id";
            case "supCode": return "sup_code";
            case "supName": return "sup_name";
            case "supId": return "sup_id";
            case "trialProjectCode": return "trial_project_code";
            case "trialProjectId": return "trial_project_id";
            case "taskBookName": return "task_book_name";
            case "taskBookCode": return "task_book_code";
            case "pcId": return "pc_id";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>total -> total</li>
     * <li>evection_subsidy -> evectionSubsidy</li>
     * <li>subsidy_off -> subsidyOff</li>
     * <li>evection_days -> evectionDays</li>
     * <li>days_off -> daysOff</li>
     * <li>trial_state -> trialState</li>
     * <li>count_price -> countPrice</li>
     * <li>mile_plus -> milePlus</li>
     * <li>test_conditions -> testConditions</li>
     * <li>dr_type -> drType</li>
     * <li>car_state -> carState</li>
     * <li>load -> load</li>
     * <li>road_line_id -> roadLineId</li>
     * <li>trial_id -> trialId</li>
     * <li>tower -> tower</li>
     * <li>fuel_type -> fuelType</li>
     * <li>car_type -> carType</li>
     * <li>vehicle_type -> vehicleType</li>
     * <li>chassis_code -> chassisCode</li>
     * <li>car_id -> carId</li>
     * <li>sup_code -> supCode</li>
     * <li>sup_name -> supName</li>
     * <li>sup_id -> supId</li>
     * <li>trial_project_code -> trialProjectCode</li>
     * <li>trial_project_id -> trialProjectId</li>
     * <li>task_book_name -> taskBookName</li>
     * <li>task_book_code -> taskBookCode</li>
     * <li>pc_id -> pcId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) { return null; }
        switch (columnName) {
            case "total": return "total";
            case "evection_subsidy": return "evectionSubsidy";
            case "subsidy_off": return "subsidyOff";
            case "evection_days": return "evectionDays";
            case "days_off": return "daysOff";
            case "trial_state": return "trialState";
            case "count_price": return "countPrice";
            case "mile_plus": return "milePlus";
            case "test_conditions": return "testConditions";
            case "dr_type": return "drType";
            case "car_state": return "carState";
            case "load": return "load";
            case "road_line_id": return "roadLineId";
            case "trial_id": return "trialId";
            case "tower": return "tower";
            case "fuel_type": return "fuelType";
            case "car_type": return "carType";
            case "vehicle_type": return "vehicleType";
            case "chassis_code": return "chassisCode";
            case "car_id": return "carId";
            case "sup_code": return "supCode";
            case "sup_name": return "supName";
            case "sup_id": return "supId";
            case "trial_project_code": return "trialProjectCode";
            case "trial_project_id": return "trialProjectId";
            case "task_book_name": return "taskBookName";
            case "task_book_code": return "taskBookCode";
            case "pc_id": return "pcId";
            case "id": return "id";
            default: return null;
        }
    }
    
    public String getTotal() {
        return this.total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getEvectionSubsidy() {
        return this.evectionSubsidy;
    }

    public void setEvectionSubsidy(String evectionSubsidy) {
        this.evectionSubsidy = evectionSubsidy;
    }

    public String getSubsidyOff() {
        return this.subsidyOff;
    }

    public void setSubsidyOff(String subsidyOff) {
        this.subsidyOff = subsidyOff;
    }

    public String getEvectionDays() {
        return this.evectionDays;
    }

    public void setEvectionDays(String evectionDays) {
        this.evectionDays = evectionDays;
    }

    public String getDaysOff() {
        return this.daysOff;
    }

    public void setDaysOff(String daysOff) {
        this.daysOff = daysOff;
    }

    public String getTrialState() {
        return this.trialState;
    }

    public void setTrialState(String trialState) {
        this.trialState = trialState;
    }

    public String getCountPrice() {
        return this.countPrice;
    }

    public void setCountPrice(String countPrice) {
        this.countPrice = countPrice;
    }

    public String getMilePlus() {
        return this.milePlus;
    }

    public void setMilePlus(String milePlus) {
        this.milePlus = milePlus;
    }

    public String getTestConditions() {
        return this.testConditions;
    }

    public void setTestConditions(String testConditions) {
        this.testConditions = testConditions;
    }

    public String getDrType() {
        return this.drType;
    }

    public void setDrType(String drType) {
        this.drType = drType;
    }

    public String getCarState() {
        return this.carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public String getLoad() {
        return this.load;
    }

    public void setLoad(String load) {
        this.load = load;
    }

    public String getRoadLineId() {
        return this.roadLineId;
    }

    public void setRoadLineId(String roadLineId) {
        this.roadLineId = roadLineId;
    }

    public String getTrialId() {
        return this.trialId;
    }

    public void setTrialId(String trialId) {
        this.trialId = trialId;
    }

    public String getTower() {
        return this.tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
    }

    public String getFuelType() {
        return this.fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getCarType() {
        return this.carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getVehicleType() {
        return this.vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getChassisCode() {
        return this.chassisCode;
    }

    public void setChassisCode(String chassisCode) {
        this.chassisCode = chassisCode;
    }

    public String getCarId() {
        return this.carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getSupCode() {
        return this.supCode;
    }

    public void setSupCode(String supCode) {
        this.supCode = supCode;
    }

    public String getSupName() {
        return this.supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getSupId() {
        return this.supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getTrialProjectCode() {
        return this.trialProjectCode;
    }

    public void setTrialProjectCode(String trialProjectCode) {
        this.trialProjectCode = trialProjectCode;
    }

    public String getTrialProjectId() {
        return this.trialProjectId;
    }

    public void setTrialProjectId(String trialProjectId) {
        this.trialProjectId = trialProjectId;
    }

    public String getTaskBookName() {
        return this.taskBookName;
    }

    public void setTaskBookName(String taskBookName) {
        this.taskBookName = taskBookName;
    }

    public String getTaskBookCode() {
        return this.taskBookCode;
    }

    public void setTaskBookCode(String taskBookCode) {
        this.taskBookCode = taskBookCode;
    }

    public String getPcId() {
        return this.pcId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
