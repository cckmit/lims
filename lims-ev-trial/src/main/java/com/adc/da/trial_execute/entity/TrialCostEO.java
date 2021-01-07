package com.adc.da.trial_execute.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>EV_TRIAL_COST TrialCostEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-09-17 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class TrialCostEO extends BaseEntity {

    private String id;
    private String trialtaskId;
    private String scaffoldingCost;
    private String upkeepCost;
    private String scaffoldingRunCost;
    private String engineerCost;
    private String technicianCost;
    private String kThermocoupleCost;
    private String ptCost;
    private String gasolineCost;
    private String waterCost;
    private String electricityCost;
    private String coolantCost;
    private String motoroilCost;
    private String managementCost;
    private String engineDestuffingCost;
    private String engineCost;
    private String wickingCost;
    private String ecvCost;
    private String frockCost;
    private String reportCost;
    private String logisticsCost;
    private String totalCost;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private String delFlag;
    private String createByName;
    @ApiModelProperty("发动机试验任务书编号")
    private String evNumber;

    @ApiModelProperty("任务名称")
    private String title;

    public String getEvNumber() {
        return evNumber;
    }

    public void setEvNumber(String evNumber) {
        this.evNumber = evNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>trialtaskId -> trialtask_id</li>
     * <li>scaffoldingCost -> scaffolding_cost</li>
     * <li>upkeepCost -> upkeep_cost</li>
     * <li>scaffoldingRunCost -> scaffolding_run_cost</li>
     * <li>engineerCost -> engineer_cost</li>
     * <li>technicianCost -> technician_cost</li>
     * <li>kThermocoupleCost -> k_thermocouple_cost</li>
     * <li>ptCost -> pt_cost</li>
     * <li>gasolineCost -> gasoline_cost</li>
     * <li>waterCost -> water_cost</li>
     * <li>electricityCost -> electricity_cost</li>
     * <li>coolantCost -> coolant_cost</li>
     * <li>motoroilCost -> motoroil_cost</li>
     * <li>managementCost -> management_cost</li>
     * <li>engineDestuffingCost -> engine_destuffing_cost</li>
     * <li>engineCost -> engine_cost</li>
     * <li>wickingCost -> wicking_cost</li>
     * <li>ecvCost -> ecv_cost</li>
     * <li>frockCost -> frock_cost</li>
     * <li>reportCost -> report_cost</li>
     * <li>logisticsCost -> logistics_cost</li>
     * <li>totalCost -> total_cost</li>
     * <li>createBy -> create_by</li>
     * <li>createTime -> create_time</li>
     * <li>updateBy -> update_by</li>
     * <li>updateTime -> update_time</li>
     * <li>delFlag -> del_flag</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "trialtaskId": return "trialtask_id";
            case "scaffoldingCost": return "scaffolding_cost";
            case "upkeepCost": return "upkeep_cost";
            case "scaffoldingRunCost": return "scaffolding_run_cost";
            case "engineerCost": return "engineer_cost";
            case "technicianCost": return "technician_cost";
            case "kThermocoupleCost": return "k_thermocouple_cost";
            case "ptCost": return "pt_cost";
            case "gasolineCost": return "gasoline_cost";
            case "waterCost": return "water_cost";
            case "electricityCost": return "electricity_cost";
            case "coolantCost": return "coolant_cost";
            case "motoroilCost": return "motoroil_cost";
            case "managementCost": return "management_cost";
            case "engineDestuffingCost": return "engine_destuffing_cost";
            case "engineCost": return "engine_cost";
            case "wickingCost": return "wicking_cost";
            case "ecvCost": return "ecv_cost";
            case "frockCost": return "frock_cost";
            case "reportCost": return "report_cost";
            case "logisticsCost": return "logistics_cost";
            case "totalCost": return "total_cost";
            case "createBy": return "create_by";
            case "createTime": return "create_time";
            case "updateBy": return "update_by";
            case "updateTime": return "update_time";
            case "delFlag": return "del_flag";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>trialtask_id -> trialtaskId</li>
     * <li>scaffolding_cost -> scaffoldingCost</li>
     * <li>upkeep_cost -> upkeepCost</li>
     * <li>scaffolding_run_cost -> scaffoldingRunCost</li>
     * <li>engineer_cost -> engineerCost</li>
     * <li>technician_cost -> technicianCost</li>
     * <li>k_thermocouple_cost -> kThermocoupleCost</li>
     * <li>pt_cost -> ptCost</li>
     * <li>gasoline_cost -> gasolineCost</li>
     * <li>water_cost -> waterCost</li>
     * <li>electricity_cost -> electricityCost</li>
     * <li>coolant_cost -> coolantCost</li>
     * <li>motoroil_cost -> motoroilCost</li>
     * <li>management_cost -> managementCost</li>
     * <li>engine_destuffing_cost -> engineDestuffingCost</li>
     * <li>engine_cost -> engineCost</li>
     * <li>wicking_cost -> wickingCost</li>
     * <li>ecv_cost -> ecvCost</li>
     * <li>frock_cost -> frockCost</li>
     * <li>report_cost -> reportCost</li>
     * <li>logistics_cost -> logisticsCost</li>
     * <li>total_cost -> totalCost</li>
     * <li>create_by -> createBy</li>
     * <li>create_time -> createTime</li>
     * <li>update_by -> updateBy</li>
     * <li>update_time -> updateTime</li>
     * <li>del_flag -> delFlag</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "trialtask_id": return "trialtaskId";
            case "scaffolding_cost": return "scaffoldingCost";
            case "upkeep_cost": return "upkeepCost";
            case "scaffolding_run_cost": return "scaffoldingRunCost";
            case "engineer_cost": return "engineerCost";
            case "technician_cost": return "technicianCost";
            case "k_thermocouple_cost": return "kThermocoupleCost";
            case "pt_cost": return "ptCost";
            case "gasoline_cost": return "gasolineCost";
            case "water_cost": return "waterCost";
            case "electricity_cost": return "electricityCost";
            case "coolant_cost": return "coolantCost";
            case "motoroil_cost": return "motoroilCost";
            case "management_cost": return "managementCost";
            case "engine_destuffing_cost": return "engineDestuffingCost";
            case "engine_cost": return "engineCost";
            case "wicking_cost": return "wickingCost";
            case "ecv_cost": return "ecvCost";
            case "frock_cost": return "frockCost";
            case "report_cost": return "reportCost";
            case "logistics_cost": return "logisticsCost";
            case "total_cost": return "totalCost";
            case "create_by": return "createBy";
            case "create_time": return "createTime";
            case "update_by": return "updateBy";
            case "update_time": return "updateTime";
            case "del_flag": return "delFlag";
            default: return null;
        }
    }
    
    /** id **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /** 试验id **/
    public String getTrialtaskId() {
        return this.trialtaskId;
    }

    /**  **/
    public void setTrialtaskId(String trialtaskId) {
        this.trialtaskId = trialtaskId;
    }

    /** 上下架费用 **/
    public String getScaffoldingCost() {
        return this.scaffoldingCost;
    }

    /**  **/
    public void setScaffoldingCost(String scaffoldingCost) {
        this.scaffoldingCost = scaffoldingCost;
    }

    /** 保养 **/
    public String getUpkeepCost() {
        return this.upkeepCost;
    }

    /**  **/
    public void setUpkeepCost(String upkeepCost) {
        this.upkeepCost = upkeepCost;
    }

    /** 泰加设备运行 **/
    public String getScaffoldingRunCost() {
        return this.scaffoldingRunCost;
    }

    /**  **/
    public void setScaffoldingRunCost(String scaffoldingRunCost) {
        this.scaffoldingRunCost = scaffoldingRunCost;
    }

    /** 工程师费用 **/
    public String getEngineerCost() {
        return this.engineerCost;
    }

    /**  **/
    public void setEngineerCost(String engineerCost) {
        this.engineerCost = engineerCost;
    }

    /** 技师费用 **/
    public String getTechnicianCost() {
        return this.technicianCost;
    }

    /**  **/
    public void setTechnicianCost(String technicianCost) {
        this.technicianCost = technicianCost;
    }

    /** k型热电偶费用
     **/
    public String getKThermocoupleCost() {
        return this.kThermocoupleCost;
    }

    /**  **/
    public void setKThermocoupleCost(String kThermocoupleCost) {
        this.kThermocoupleCost = kThermocoupleCost;
    }

    /** PT费用
     **/
    public String getPtCost() {
        return this.ptCost;
    }

    /**  **/
    public void setPtCost(String ptCost) {
        this.ptCost = ptCost;
    }

    /** 汽油费用
     **/
    public String getGasolineCost() {
        return this.gasolineCost;
    }

    /**  **/
    public void setGasolineCost(String gasolineCost) {
        this.gasolineCost = gasolineCost;
    }

    /** 水费
     **/
    public String getWaterCost() {
        return this.waterCost;
    }

    /**  **/
    public void setWaterCost(String waterCost) {
        this.waterCost = waterCost;
    }

    /** 电费
     **/
    public String getElectricityCost() {
        return this.electricityCost;
    }

    /**  **/
    public void setElectricityCost(String electricityCost) {
        this.electricityCost = electricityCost;
    }

    /**  冷却费用
     **/
    public String getCoolantCost() {
        return this.coolantCost;
    }

    /**  **/
    public void setCoolantCost(String coolantCost) {
        this.coolantCost = coolantCost;
    }

    /**  机油费用
     **/
    public String getMotoroilCost() {
        return this.motoroilCost;
    }

    /**  **/
    public void setMotoroilCost(String motoroilCost) {
        this.motoroilCost = motoroilCost;
    }

    /**  管理费
     **/
    public String getManagementCost() {
        return this.managementCost;
    }

    /**  **/
    public void setManagementCost(String managementCost) {
        this.managementCost = managementCost;
    }

    /** 发动机拆装费
     **/
    public String getEngineDestuffingCost() {
        return this.engineDestuffingCost;
    }

    /**  **/
    public void setEngineDestuffingCost(String engineDestuffingCost) {
        this.engineDestuffingCost = engineDestuffingCost;
    }

    /** 发动机费用
     **/
    public String getEngineCost() {
        return this.engineCost;
    }

    /**  **/
    public void setEngineCost(String engineCost) {
        this.engineCost = engineCost;
    }

    /** 线束费用
     **/
    public String getWickingCost() {
        return this.wickingCost;
    }

    /**  **/
    public void setWickingCost(String wickingCost) {
        this.wickingCost = wickingCost;
    }

    /** ECU费用
     **/
    public String getEcvCost() {
        return this.ecvCost;
    }

    /**  **/
    public void setEcvCost(String ecvCost) {
        this.ecvCost = ecvCost;
    }

    /** 工装费用
     **/
    public String getFrockCost() {
        return this.frockCost;
    }

    /**  **/
    public void setFrockCost(String frockCost) {
        this.frockCost = frockCost;
    }

    /** 试验报告费用
     **/
    public String getReportCost() {
        return this.reportCost;
    }

    /**  **/
    public void setReportCost(String reportCost) {
        this.reportCost = reportCost;
    }

    /** 物流费用
     **/
    public String getLogisticsCost() {
        return this.logisticsCost;
    }

    /**  **/
    public void setLogisticsCost(String logisticsCost) {
        this.logisticsCost = logisticsCost;
    }

    /** 总计费用
     **/
    public String getTotalCost() {
        return this.totalCost;
    }

    /**  **/
    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    /** 创建人 **/
    public String getCreateBy() {
        return this.createBy;
    }

    /**  **/
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /** 创建时间 **/
    public String getCreateTime() {
        return this.createTime;
    }

    /**  **/
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /** 更新热 **/
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**  **/
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**  更新时间**/
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**  **/
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /** 删除 **/
    public String getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

}
