package com.adc.da.trial_execute.page;

import com.adc.da.base.page.BasePage;


/**
 * <b>功能：</b>EV_TRIAL_COST TrialCostEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-09-17 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class TrialCostEOPage extends BasePage {

    private String id;
    private String idOperator = "=";
    private String trialtaskId;
    private String trialtaskIdOperator = "=";
    private String scaffoldingCost;
    private String scaffoldingCostOperator = "=";
    private String upkeepCost;
    private String upkeepCostOperator = "=";
    private String scaffoldingRunCost;
    private String scaffoldingRunCostOperator = "=";
    private String engineerCost;
    private String engineerCostOperator = "=";
    private String technicianCost;
    private String technicianCostOperator = "=";
    private String kThermocoupleCost;
    private String kThermocoupleCostOperator = "=";
    private String ptCost;
    private String ptCostOperator = "=";
    private String gasolineCost;
    private String gasolineCostOperator = "=";
    private String waterCost;
    private String waterCostOperator = "=";
    private String electricityCost;
    private String electricityCostOperator = "=";
    private String coolantCost;
    private String coolantCostOperator = "=";
    private String motoroilCost;
    private String motoroilCostOperator = "=";
    private String managementCost;
    private String managementCostOperator = "=";
    private String engineDestuffingCost;
    private String engineDestuffingCostOperator = "=";
    private String engineCost;
    private String engineCostOperator = "=";
    private String wickingCost;
    private String wickingCostOperator = "=";
    private String ecvCost;
    private String ecvCostOperator = "=";
    private String frockCost;
    private String frockCostOperator = "=";
    private String reportCost;
    private String reportCostOperator = "=";
    private String logisticsCost;
    private String logisticsCostOperator = "=";
    private String totalCost;
    private String totalCostOperator = "=";
    private String createBy;
    private String createByOperator = "=";
    private String createTime1;
    private String createTime2;
    private String createTimeOperator = "=";
    private String updateBy;
    private String updateByOperator = "=";
    private String updateTime;
    private String updateTimeOperator = "=";
    private String delFlag;
    private String delFlagOperator = "=";

    private String createByName;

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

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

    public String getTrialtaskId() {
        return this.trialtaskId;
    }

    public void setTrialtaskId(String trialtaskId) {
        this.trialtaskId = trialtaskId;
    }

    public String getTrialtaskIdOperator() {
        return this.trialtaskIdOperator;
    }

    public void setTrialtaskIdOperator(String trialtaskIdOperator) {
        this.trialtaskIdOperator = trialtaskIdOperator;
    }

    public String getScaffoldingCost() {
        return this.scaffoldingCost;
    }

    public void setScaffoldingCost(String scaffoldingCost) {
        this.scaffoldingCost = scaffoldingCost;
    }

    public String getScaffoldingCostOperator() {
        return this.scaffoldingCostOperator;
    }

    public void setScaffoldingCostOperator(String scaffoldingCostOperator) {
        this.scaffoldingCostOperator = scaffoldingCostOperator;
    }

    public String getUpkeepCost() {
        return this.upkeepCost;
    }

    public void setUpkeepCost(String upkeepCost) {
        this.upkeepCost = upkeepCost;
    }

    public String getUpkeepCostOperator() {
        return this.upkeepCostOperator;
    }

    public void setUpkeepCostOperator(String upkeepCostOperator) {
        this.upkeepCostOperator = upkeepCostOperator;
    }

    public String getScaffoldingRunCost() {
        return this.scaffoldingRunCost;
    }

    public void setScaffoldingRunCost(String scaffoldingRunCost) {
        this.scaffoldingRunCost = scaffoldingRunCost;
    }

    public String getScaffoldingRunCostOperator() {
        return this.scaffoldingRunCostOperator;
    }

    public void setScaffoldingRunCostOperator(String scaffoldingRunCostOperator) {
        this.scaffoldingRunCostOperator = scaffoldingRunCostOperator;
    }

    public String getEngineerCost() {
        return this.engineerCost;
    }

    public void setEngineerCost(String engineerCost) {
        this.engineerCost = engineerCost;
    }

    public String getEngineerCostOperator() {
        return this.engineerCostOperator;
    }

    public void setEngineerCostOperator(String engineerCostOperator) {
        this.engineerCostOperator = engineerCostOperator;
    }

    public String getTechnicianCost() {
        return this.technicianCost;
    }

    public void setTechnicianCost(String technicianCost) {
        this.technicianCost = technicianCost;
    }

    public String getTechnicianCostOperator() {
        return this.technicianCostOperator;
    }

    public void setTechnicianCostOperator(String technicianCostOperator) {
        this.technicianCostOperator = technicianCostOperator;
    }

    public String getKThermocoupleCost() {
        return this.kThermocoupleCost;
    }

    public void setKThermocoupleCost(String kThermocoupleCost) {
        this.kThermocoupleCost = kThermocoupleCost;
    }

    public String getKThermocoupleCostOperator() {
        return this.kThermocoupleCostOperator;
    }

    public void setKThermocoupleCostOperator(String kThermocoupleCostOperator) {
        this.kThermocoupleCostOperator = kThermocoupleCostOperator;
    }

    public String getPtCost() {
        return this.ptCost;
    }

    public void setPtCost(String ptCost) {
        this.ptCost = ptCost;
    }

    public String getPtCostOperator() {
        return this.ptCostOperator;
    }

    public void setPtCostOperator(String ptCostOperator) {
        this.ptCostOperator = ptCostOperator;
    }

    public String getGasolineCost() {
        return this.gasolineCost;
    }

    public void setGasolineCost(String gasolineCost) {
        this.gasolineCost = gasolineCost;
    }

    public String getGasolineCostOperator() {
        return this.gasolineCostOperator;
    }

    public void setGasolineCostOperator(String gasolineCostOperator) {
        this.gasolineCostOperator = gasolineCostOperator;
    }

    public String getWaterCost() {
        return this.waterCost;
    }

    public void setWaterCost(String waterCost) {
        this.waterCost = waterCost;
    }

    public String getWaterCostOperator() {
        return this.waterCostOperator;
    }

    public void setWaterCostOperator(String waterCostOperator) {
        this.waterCostOperator = waterCostOperator;
    }

    public String getElectricityCost() {
        return this.electricityCost;
    }

    public void setElectricityCost(String electricityCost) {
        this.electricityCost = electricityCost;
    }

    public String getElectricityCostOperator() {
        return this.electricityCostOperator;
    }

    public void setElectricityCostOperator(String electricityCostOperator) {
        this.electricityCostOperator = electricityCostOperator;
    }

    public String getCoolantCost() {
        return this.coolantCost;
    }

    public void setCoolantCost(String coolantCost) {
        this.coolantCost = coolantCost;
    }

    public String getCoolantCostOperator() {
        return this.coolantCostOperator;
    }

    public void setCoolantCostOperator(String coolantCostOperator) {
        this.coolantCostOperator = coolantCostOperator;
    }

    public String getMotoroilCost() {
        return this.motoroilCost;
    }

    public void setMotoroilCost(String motoroilCost) {
        this.motoroilCost = motoroilCost;
    }

    public String getMotoroilCostOperator() {
        return this.motoroilCostOperator;
    }

    public void setMotoroilCostOperator(String motoroilCostOperator) {
        this.motoroilCostOperator = motoroilCostOperator;
    }

    public String getManagementCost() {
        return this.managementCost;
    }

    public void setManagementCost(String managementCost) {
        this.managementCost = managementCost;
    }

    public String getManagementCostOperator() {
        return this.managementCostOperator;
    }

    public void setManagementCostOperator(String managementCostOperator) {
        this.managementCostOperator = managementCostOperator;
    }

    public String getEngineDestuffingCost() {
        return this.engineDestuffingCost;
    }

    public void setEngineDestuffingCost(String engineDestuffingCost) {
        this.engineDestuffingCost = engineDestuffingCost;
    }

    public String getEngineDestuffingCostOperator() {
        return this.engineDestuffingCostOperator;
    }

    public void setEngineDestuffingCostOperator(String engineDestuffingCostOperator) {
        this.engineDestuffingCostOperator = engineDestuffingCostOperator;
    }

    public String getEngineCost() {
        return this.engineCost;
    }

    public void setEngineCost(String engineCost) {
        this.engineCost = engineCost;
    }

    public String getEngineCostOperator() {
        return this.engineCostOperator;
    }

    public void setEngineCostOperator(String engineCostOperator) {
        this.engineCostOperator = engineCostOperator;
    }

    public String getWickingCost() {
        return this.wickingCost;
    }

    public void setWickingCost(String wickingCost) {
        this.wickingCost = wickingCost;
    }

    public String getWickingCostOperator() {
        return this.wickingCostOperator;
    }

    public void setWickingCostOperator(String wickingCostOperator) {
        this.wickingCostOperator = wickingCostOperator;
    }

    public String getEcvCost() {
        return this.ecvCost;
    }

    public void setEcvCost(String ecvCost) {
        this.ecvCost = ecvCost;
    }

    public String getEcvCostOperator() {
        return this.ecvCostOperator;
    }

    public void setEcvCostOperator(String ecvCostOperator) {
        this.ecvCostOperator = ecvCostOperator;
    }

    public String getFrockCost() {
        return this.frockCost;
    }

    public void setFrockCost(String frockCost) {
        this.frockCost = frockCost;
    }

    public String getFrockCostOperator() {
        return this.frockCostOperator;
    }

    public void setFrockCostOperator(String frockCostOperator) {
        this.frockCostOperator = frockCostOperator;
    }

    public String getReportCost() {
        return this.reportCost;
    }

    public void setReportCost(String reportCost) {
        this.reportCost = reportCost;
    }

    public String getReportCostOperator() {
        return this.reportCostOperator;
    }

    public void setReportCostOperator(String reportCostOperator) {
        this.reportCostOperator = reportCostOperator;
    }

    public String getLogisticsCost() {
        return this.logisticsCost;
    }

    public void setLogisticsCost(String logisticsCost) {
        this.logisticsCost = logisticsCost;
    }

    public String getLogisticsCostOperator() {
        return this.logisticsCostOperator;
    }

    public void setLogisticsCostOperator(String logisticsCostOperator) {
        this.logisticsCostOperator = logisticsCostOperator;
    }

    public String getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getTotalCostOperator() {
        return this.totalCostOperator;
    }

    public void setTotalCostOperator(String totalCostOperator) {
        this.totalCostOperator = totalCostOperator;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateByOperator() {
        return this.createByOperator;
    }

    public void setCreateByOperator(String createByOperator) {
        this.createByOperator = createByOperator;
    }

    public String getCreateTimeOperator() {
        return this.createTimeOperator;
    }

    public void setCreateTimeOperator(String createTimeOperator) {
        this.createTimeOperator = createTimeOperator;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateByOperator() {
        return this.updateByOperator;
    }

    public void setUpdateByOperator(String updateByOperator) {
        this.updateByOperator = updateByOperator;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTimeOperator() {
        return this.updateTimeOperator;
    }

    public void setUpdateTimeOperator(String updateTimeOperator) {
        this.updateTimeOperator = updateTimeOperator;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlagOperator() {
        return this.delFlagOperator;
    }

    public void setDelFlagOperator(String delFlagOperator) {
        this.delFlagOperator = delFlagOperator;
    }

    public String getCreateTime1() {
        return createTime1;
    }

    public void setCreateTime1(String createTime1) {
        this.createTime1 = createTime1;
    }

    public String getCreateTime2() {
        return createTime2;
    }

    public void setCreateTime2(String createTime2) {
        this.createTime2 = createTime2;
    }
}
