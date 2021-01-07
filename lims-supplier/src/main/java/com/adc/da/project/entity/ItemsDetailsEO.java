package com.adc.da.project.entity;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.supplier.entity.SupProjectEO;

import java.util.Date;

/**
 * <b>功能：</b>SUP_ITEMS_DETAILS ItemsDetailsEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-28 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class ItemsDetailsEO extends BaseEntity {

    private String id;
    private String trustType;
    private String testStartDate;
    private String testEndDate;
    private String traffic;
    private String schedul;
    private String startPort;
    private String endPort;
    private String planMileage;
    private String status;
    private String projectName;
    private String projectId;
    private String supProjectId;
    private String contractProjectName;
    private String supTrialId;
    //20200604项目委托单变更
    /**
     * 车辆状态    0带上装   1不带上装
     */
    private String carState;
    /**
     * 载重吨位
     */
    private String load;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>trustType -> trust_type</li>
     * <li>testStartDate -> test_start_date</li>
     * <li>testEndDate -> test_end_date</li>
     * <li>traffic -> traffic</li>
     * <li>schedul -> schedul</li>
     * <li>startPort -> start_port</li>
     * <li>endPort -> end_port</li>
     * <li>planMileage -> plan_mileage</li>
     * <li>status -> status</li>
     * <li>projectName -> project_name</li>
     * <li>projectId -> project_id</li>
     * <li>projectCode -> project_code</li>
     * <li>chargeDepart -> charge_depart</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "trustType": return "trust_type";
            case "testStartDate": return "test_start_date";
            case "testEndDate": return "test_end_date";
            case "traffic": return "traffic";
            case "schedul": return "schedul";
            case "startPort": return "start_port";
            case "endPort": return "end_port";
            case "planMileage": return "plan_mileage";
            case "status": return "status";
            case "projectName": return "project_name";
            case "projectId": return "project_id";
            case "projectCode": return "project_code";
            case "chargeDepart": return "charge_depart";
            case "carState": return "car_state";
            case "load": return "load";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>trust_type -> trustType</li>
     * <li>test_start_date -> testStartDate</li>
     * <li>test_end_date -> testEndDate</li>
     * <li>traffic -> traffic</li>
     * <li>schedul -> schedul</li>
     * <li>start_port -> startPort</li>
     * <li>end_port -> endPort</li>
     * <li>plan_mileage -> planMileage</li>
     * <li>status -> status</li>
     * <li>project_name -> projectName</li>
     * <li>project_id -> projectId</li>
     * <li>project_code -> projectCode</li>
     * <li>charge_depart -> chargeDepart</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "trust_type": return "trustType";
            case "test_start_date": return "testStartDate";
            case "test_end_date": return "testEndDate";
            case "traffic": return "traffic";
            case "schedul": return "schedul";
            case "start_port": return "startPort";
            case "end_port": return "endPort";
            case "plan_mileage": return "planMileage";
            case "status": return "status";
            case "project_name": return "projectName";
            case "project_id": return "projectId";
            case "project_code": return "projectCode";
            case "charge_depart": return "chargeDepart";
            case "car_state": return "carState";
            case "load": return "load";
            default: return null;
        }
    }
    
    /** 主键 **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /**委托类型  **/
    public String getTrustType() {
        return this.trustType;
    }

    /**  **/
    public void setTrustType(String trustType) {
        this.trustType = trustType;
    }

    /**实验开始时间  **/
    public String getTestStartDate() {
        return this.testStartDate;
    }

    /**  **/
    public void setTestStartDate(String testStartDate) {
        this.testStartDate = testStartDate;
    }

    /** 结束时间 **/
    public String getTestEndDate() {
        return this.testEndDate;
    }

    /**  **/
    public void setTestEndDate(String testEndDate) {
        this.testEndDate = testEndDate;
    }

    /** 行车状况 **/
    public String getTraffic() {
        return this.traffic;
    }

    /**  **/
    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    /**排版情况  **/
    public String getSchedul() {
        return this.schedul;
    }

    /**  **/
    public void setSchedul(String schedul) {
        this.schedul = schedul;
    }

    /** 行车七点 **/
    public String getStartPort() {
        return this.startPort;
    }

    /**  **/
    public void setStartPort(String startPort) {
        this.startPort = startPort;
    }

    /** 行车终点 **/
    public String getEndPort() {
        return this.endPort;
    }

    /**  **/
    public void setEndPort(String endPort) {
        this.endPort = endPort;
    }

    /**计划里程  **/
    public String getPlanMileage() {
        return this.planMileage;
    }

    /**  **/
    public void setPlanMileage(String planMileage) {
        this.planMileage = planMileage;
    }

    /**状态  **/
    public String getStatus() {
        return this.status;
    }

    /**  **/
    public void setStatus(String status) {
        this.status = status;
    }

    /**项目名  **/
    public String getProjectName() {
        return this.projectName;
    }

    /**  **/
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /** 项目id **/
    public String getProjectId() {
        return this.projectId;
    }

    /**  **/
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * 供应商能力下项目ID
     * @return
     */
    public String getSupProjectId() {
        return supProjectId;
    }

    public void setSupProjectId(String supProjectId) {
        this.supProjectId = supProjectId;
    }

    /**
     * 合同项目名
     * @return
     */
    public String getContractProjectName() {
        return contractProjectName;
    }

    public void setContractProjectName(String contractProjectName) {
        this.contractProjectName = contractProjectName;
    }

    /**
     * 试验委托id
     * @return
     */
    public String getSupTrialId() {
        return supTrialId;
    }

    public void setSupTrialId(String supTrialId) {
        this.supTrialId = supTrialId;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public String getLoad() {
        return load;
    }

    public void setLoad(String load) {
        this.load = load;
    }
}
