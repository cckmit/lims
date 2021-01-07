package com.adc.da.pc_loan_application.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>PC_CAR_LOAN_APPLICATION PcCarLoanApplicationEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-13 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcCarLoanApplicationEO extends BaseEntity {

     @ApiModelProperty("试验任务id")
    private String taskId;
     @ApiModelProperty("流程状态：0:草稿 1：待审批 4：退回 5：已审批")
    private String processStatus;
     @ApiModelProperty("pv/cv开关，1为pv,0为cv")
    private String pcvStatus;
     @ApiModelProperty("主键")
    private String id;
     @ApiModelProperty("借车单类型")
    private String listType;
     @ApiModelProperty("申请人")
    private String applicant;
    @ApiModelProperty("申请人ID")
    private String applicantId;
     @ApiModelProperty("申请部门")
    private String department;
    @ApiModelProperty("申请部门ID")
    private String departmentId;
     @ApiModelProperty("申请联系方式")
    private String contactInfo;
     @ApiModelProperty("借车用途")
    private String purpose;
     @ApiModelProperty("试验项目类别")
    private String itemCategory;
     @ApiModelProperty("试验区域")
    private String experimentalArea;
     @ApiModelProperty("试验类型")
    private String experimentalType;
     @ApiModelProperty("开始日期")
    private String startDate;
     @ApiModelProperty("结束日期")
    private String endDate;
     @ApiModelProperty("车型")
    private String vehicleType;
     @ApiModelProperty("试验路线")
    private String experimentalRoute;
     @ApiModelProperty("方案号")
    private String projectNumber;
    @ApiModelProperty("借车单编号")
    private String loanCarCode;
    @ApiModelProperty("底盘号")
    private String chassisCode;
    @ApiModelProperty("试验任务编号")
    private String taskNumber;
    @ApiModelProperty("区分试验任务还是执行计划:0-试验任务；1-执行计划")
    private String taskOrPlan;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>taskId -> task_id</li>
     * <li>processStatus -> process_status</li>
     * <li>pcvStatus -> pcv_status</li>
     * <li>id -> id</li>
     * <li>listType -> list_type</li>
     * <li>applicant -> applicant</li>
     * <li>department -> department</li>
     * <li>contactInfo -> contact_info</li>
     * <li>purpose -> purpose</li>
     * <li>itemCategory -> item_category</li>
     * <li>experimentalArea -> experimental_area</li>
     * <li>experimentalType -> experimental_type</li>
     * <li>startDate -> start_date</li>
     * <li>endDate -> end_date</li>
     * <li>vehicleType -> vehicle_type</li>
     * <li>experimentalRoute -> experimental_route</li>
     * <li>projectNumber -> project_number</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "taskId": return "task_id";
            case "processStatus": return "process_status";
            case "pcvStatus": return "pcv_status";
            case "id": return "id";
            case "listType": return "list_type";
            case "applicant": return "applicant";
            case "department": return "department";
            case "contactInfo": return "contact_info";
            case "purpose": return "purpose";
            case "itemCategory": return "item_category";
            case "experimentalArea": return "experimental_area";
            case "experimentalType": return "experimental_type";
            case "startDate": return "start_date";
            case "endDate": return "end_date";
            case "vehicleType": return "vehicle_type";
            case "experimentalRoute": return "experimental_route";
            case "projectNumber": return "project_number";
            case "applicantId":
                return "applicantId";
            case "departmentId":
                return "departmentId";
            case "loanCarCode":
                return "loan_car_code";
            case "chassisCode":
                return "CHASSIS_CODE";
            case "taskOrPlan":
                return "TASK_OR_PLAN";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>task_id -> taskId</li>
     * <li>process_status -> processStatus</li>
     * <li>pcv_status -> pcvStatus</li>
     * <li>id -> id</li>
     * <li>list_type -> listType</li>
     * <li>applicant -> applicant</li>
     * <li>department -> department</li>
     * <li>contact_info -> contactInfo</li>
     * <li>purpose -> purpose</li>
     * <li>item_category -> itemCategory</li>
     * <li>experimental_area -> experimentalArea</li>
     * <li>experimental_type -> experimentalType</li>
     * <li>start_date -> startDate</li>
     * <li>end_date -> endDate</li>
     * <li>vehicle_type -> vehicleType</li>
     * <li>experimental_route -> experimentalRoute</li>
     * <li>project_number -> projectNumber</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "task_id": return "taskId";
            case "process_status": return "processStatus";
            case "pcv_status": return "pcvStatus";
            case "id": return "id";
            case "list_type": return "listType";
            case "applicant": return "applicant";
            case "department": return "department";
            case "contact_info": return "contactInfo";
            case "purpose": return "purpose";
            case "item_category": return "itemCategory";
            case "experimental_area": return "experimentalArea";
            case "experimental_type": return "experimentalType";
            case "start_date": return "startDate";
            case "end_date": return "endDate";
            case "vehicle_type": return "vehicleType";
            case "experimental_route": return "experimentalRoute";
            case "project_number": return "projectNumber";
            case "applicantId":
                return "applicantId";
            case "departmentId":
                return "departmentId";
            case "loan_car_code":
                return "loanCarCode";
            case "CHASSIS_CODE":
                return "chassisCode";
            case "TASK_OR_PLAN":
                return "taskOrPlan";
            default: return null;
        }
    }
    
    /**  **/
    public String getTaskId() {
        return this.taskId;
    }

    /**  **/
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**  **/
    public String getProcessStatus() {
        return this.processStatus;
    }

    /**  **/
    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    /**  **/
    public String getPcvStatus() {
        return this.pcvStatus;
    }

    /**  **/
    public void setPcvStatus(String pcvStatus) {
        this.pcvStatus = pcvStatus;
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
    public String getListType() {
        return this.listType;
    }

    /**  **/
    public void setListType(String listType) {
        this.listType = listType;
    }

    /**  **/
    public String getApplicant() {
        return this.applicant;
    }

    /**  **/
    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    /**  **/
    public String getDepartment() {
        return this.department;
    }

    /**  **/
    public void setDepartment(String department) {
        this.department = department;
    }

    /**  **/
    public String getContactInfo() {
        return this.contactInfo;
    }

    /**  **/
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
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
    public String getItemCategory() {
        return this.itemCategory;
    }

    /**  **/
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    /**  **/
    public String getExperimentalArea() {
        return this.experimentalArea;
    }

    /**  **/
    public void setExperimentalArea(String experimentalArea) {
        this.experimentalArea = experimentalArea;
    }

    /**  **/
    public String getExperimentalType() {
        return this.experimentalType;
    }

    /**  **/
    public void setExperimentalType(String experimentalType) {
        this.experimentalType = experimentalType;
    }

    /**  **/
    public String getStartDate() {
        return this.startDate;
    }

    /**  **/
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**  **/
    public String getEndDate() {
        return this.endDate;
    }

    /**  **/
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**  **/
    public String getVehicleType() {
        return this.vehicleType;
    }

    /**  **/
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**  **/
    public String getExperimentalRoute() {
        return this.experimentalRoute;
    }

    /**  **/
    public void setExperimentalRoute(String experimentalRoute) {
        this.experimentalRoute = experimentalRoute;
    }

    /**  **/
    public String getProjectNumber() {
        return this.projectNumber;
    }

    /**  **/
    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }


    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getLoanCarCode() {
        return loanCarCode;
    }

    public void setLoanCarCode(String loanCarCode) {
        this.loanCarCode = loanCarCode;
    }

    public String getChassisCode() {
        return chassisCode;
    }

    public void setChassisCode(String chassisCode) {
        this.chassisCode = chassisCode;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getTaskOrPlan() {
        return taskOrPlan;
    }

    public void setTaskOrPlan(String taskOrPlan) {
        this.taskOrPlan = taskOrPlan;
    }
}
