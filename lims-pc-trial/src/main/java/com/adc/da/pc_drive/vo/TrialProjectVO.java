package com.adc.da.pc_drive.vo;

import com.adc.da.base.entity.BaseEntity;
import java.util.Date;
import java.util.List;
public class TrialProjectVO extends BaseEntity {

    private String taskbookcode;
    private String atTaskbook;
    private String atDriveRecord;
    private String atCheck;
    private String atTorqueRecord;
    private String atHubTemperature;
    private String atLuqiaoTicket;
    private String atRepairUpdown;
    private String atFourwheelPosition;
    private String atTrialReport;
    private String atTrialSummary;
    private String atTaskbookName;
    private String atDriveRecordName;
    private String atCheckName;
    private String atTorqueRecordName;
    private String atHubTemperatureName;
    private String atLuqiaoTicketName;
    private String atRepairUpdownName;
    private String atFourwheelPositionName;
    private String atTrialReportName;
    private String atTrialSummaryName;
    private String approvalcode;
    private String approvalcost;
    private String trustcode;
    private String id;
    private String supid;
    private String supname;
    private String delegater;
    private String applydate;
    private String tasksource;
    private String frequency;
    private String tasktype;
    private String taskstartdate;
    private String taskenddate;
    private String vehicletype;
    private String chassiscode;
    private String status;  //0 :草稿   1 :审批中   2 :退回  3 :已审批   4：供应商确认中  5：项目执行   6：项目完成
    private String remark;
    private String delFlag;
    private String pvOrcv;
    private String createById;
    private String createDate;
    private String confirmPerson;
    private String carId;
    //pc模块的外键
    private String pcId;
    private String operationId;//操作人ID
    private String operationName;//操作人姓名
    private String tower;//马力
    private String carType;//对应具体车辆类型：如：牵引车
    private String fuelType;//燃油类型


    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>taskbookcode -> taskbookcode</li>
     * <li>atTaskbook -> at_taskbook</li>
     * <li>atDriveRecord -> at_drive_record</li>
     * <li>atCheck -> at_check</li>
     * <li>atTorqueRecord -> at_torque_record</li>
     * <li>atHubTemperature -> at_hub_temperature</li>
     * <li>atLuqiaoTicket -> at_luqiao_ticket</li>
     * <li>atRepairUpdown -> at_repair_updown</li>
     * <li>atFourwheelPosition -> at_fourwheel_position</li>
     * <li>atTrialReport -> at_trial_report</li>
     * <li>atTrialSummary -> at_trial_summary</li>
     * <li>approvalcode -> approvalcode</li>
     * <li>approvalcost -> approvalcost</li>
     * <li>trustcode -> trustcode</li>
     * <li>id -> id</li>
     * <li>supid -> supid</li>
     * <li>supname -> supname</li>
     * <li>delegater -> delegater</li>
     * <li>applydate -> applydate</li>
     * <li>tasksource -> tasksource</li>
     * <li>frequency -> frequency</li>
     * <li>tasktype -> tasktype</li>
     * <li>taskstartdate -> taskstartdate</li>
     * <li>taskenddate -> taskenddate</li>
     * <li>vehicletype -> vehicletype</li>
     * <li>chassiscode -> chassiscode</li>
     * <li>status -> status</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "taskbookcode": return "taskbookcode";
            case "atTaskbook": return "at_taskbook";
            case "atDriveRecord": return "at_drive_record";
            case "atCheck": return "at_check";
            case "atTorqueRecord": return "at_torque_record";
            case "atHubTemperature": return "at_hub_temperature";
            case "atLuqiaoTicket": return "at_luqiao_ticket";
            case "atRepairUpdown": return "at_repair_updown";
            case "atFourwheelPosition": return "at_fourwheel_position";
            case "atTrialReport": return "at_trial_report";
            case "atTrialSummary": return "at_trial_summary";
            case "approvalcode": return "approvalcode";
            case "approvalcost": return "approvalcost";
            case "trustcode": return "trustcode";
            case "id": return "id";
            case "supid": return "supid";
            case "supname": return "supname";
            case "delegater": return "delegater";
            case "applydate": return "applydate";
            case "tasksource": return "tasksource";
            case "frequency": return "frequency";
            case "tasktype": return "tasktype";
            case "taskstartdate": return "taskstartdate";
            case "taskenddate": return "taskenddate";
            case "vehicletype": return "vehicletype";
            case "chassiscode": return "chassiscode";
            case "status": return "status";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>taskbookcode -> taskbookcode</li>
     * <li>at_taskbook -> atTaskbook</li>
     * <li>at_drive_record -> atDriveRecord</li>
     * <li>at_check -> atCheck</li>
     * <li>at_torque_record -> atTorqueRecord</li>
     * <li>at_hub_temperature -> atHubTemperature</li>
     * <li>at_luqiao_ticket -> atLuqiaoTicket</li>
     * <li>at_repair_updown -> atRepairUpdown</li>
     * <li>at_fourwheel_position -> atFourwheelPosition</li>
     * <li>at_trial_report -> atTrialReport</li>
     * <li>at_trial_summary -> atTrialSummary</li>
     * <li>approvalcode -> approvalcode</li>
     * <li>approvalcost -> approvalcost</li>
     * <li>trustcode -> trustcode</li>
     * <li>id -> id</li>
     * <li>supid -> supid</li>
     * <li>supname -> supname</li>
     * <li>delegater -> delegater</li>
     * <li>applydate -> applydate</li>
     * <li>tasksource -> tasksource</li>
     * <li>frequency -> frequency</li>
     * <li>tasktype -> tasktype</li>
     * <li>taskstartdate -> taskstartdate</li>
     * <li>taskenddate -> taskenddate</li>
     * <li>vehicletype -> vehicletype</li>
     * <li>chassiscode -> chassiscode</li>
     * <li>status -> status</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "taskbookcode": return "taskbookcode";
            case "at_taskbook": return "atTaskbook";
            case "at_drive_record": return "atDriveRecord";
            case "at_check": return "atCheck";
            case "at_torque_record": return "atTorqueRecord";
            case "at_hub_temperature": return "atHubTemperature";
            case "at_luqiao_ticket": return "atLuqiaoTicket";
            case "at_repair_updown": return "atRepairUpdown";
            case "at_fourwheel_position": return "atFourwheelPosition";
            case "at_trial_report": return "atTrialReport";
            case "at_trial_summary": return "atTrialSummary";
            case "approvalcode": return "approvalcode";
            case "approvalcost": return "approvalcost";
            case "trustcode": return "trustcode";
            case "id": return "id";
            case "supid": return "supid";
            case "supname": return "supname";
            case "delegater": return "delegater";
            case "applydate": return "applydate";
            case "tasksource": return "tasksource";
            case "frequency": return "frequency";
            case "tasktype": return "tasktype";
            case "taskstartdate": return "taskstartdate";
            case "taskenddate": return "taskenddate";
            case "vehicletype": return "vehicletype";
            case "chassiscode": return "chassiscode";
            case "status": return "status";
            default: return null;
        }
    }

    /** 任务书编号 **/
    public String getTaskbookcode() {
        return this.taskbookcode;
    }

    /**  **/
    public void setTaskbookcode(String taskbookcode) {
        this.taskbookcode = taskbookcode;
    }

    /**  **/
    public String getAtTaskbook() {
        return this.atTaskbook;
    }

    /**试验计划书  **/
    public void setAtTaskbook(String atTaskbook) {
        this.atTaskbook = atTaskbook;
    }

    /**  **/
    public String getAtDriveRecord() {
        return this.atDriveRecord;
    }

    /** 行车记录表 **/
    public void setAtDriveRecord(String atDriveRecord) {
        this.atDriveRecord = atDriveRecord;
    }

    /**  **/
    public String getAtCheck() {
        return this.atCheck;
    }

    /** 日常检查表 **/
    public void setAtCheck(String atCheck) {
        this.atCheck = atCheck;
    }

    /**  **/
    public String getAtTorqueRecord() {
        return this.atTorqueRecord;
    }

    /**扭矩记录  **/
    public void setAtTorqueRecord(String atTorqueRecord) {
        this.atTorqueRecord = atTorqueRecord;
    }

    /**  **/
    public String getAtHubTemperature() {
        return this.atHubTemperature;
    }

    /**轮毂温度记录  **/
    public void setAtHubTemperature(String atHubTemperature) {
        this.atHubTemperature = atHubTemperature;
    }

    /**  **/
    public String getAtLuqiaoTicket() {
        return this.atLuqiaoTicket;
    }

    /** 路桥票 **/
    public void setAtLuqiaoTicket(String atLuqiaoTicket) {
        this.atLuqiaoTicket = atLuqiaoTicket;
    }

    /**  **/
    public String getAtRepairUpdown() {
        return this.atRepairUpdown;
    }

    /** 维修装卸清单 **/
    public void setAtRepairUpdown(String atRepairUpdown) {
        this.atRepairUpdown = atRepairUpdown;
    }

    /**  **/
    public String getAtFourwheelPosition() {
        return this.atFourwheelPosition;
    }

    /** 四轮定位 **/
    public void setAtFourwheelPosition(String atFourwheelPosition) {
        this.atFourwheelPosition = atFourwheelPosition;
    }

    /**  **/
    public String getAtTrialReport() {
        return this.atTrialReport;
    }

    /** 实验报告 **/
    public void setAtTrialReport(String atTrialReport) {
        this.atTrialReport = atTrialReport;
    }

    /**  **/
    public String getAtTrialSummary() {
        return this.atTrialSummary;
    }

    /** 实验总结 **/
    public void setAtTrialSummary(String atTrialSummary) {
        this.atTrialSummary = atTrialSummary;
    }

    /** 立项编号 **/
    public String getApprovalcode() {
        return this.approvalcode;
    }

    /**  **/
    public void setApprovalcode(String approvalcode) {
        this.approvalcode = approvalcode;
    }

    /**立项费用  **/
    public String getApprovalcost() {
        return this.approvalcost;
    }

    /**  **/
    public void setApprovalcost(String approvalcost) {
        this.approvalcost = approvalcost;
    }

    /**委托编号  **/
    public String getTrustcode() {
        return this.trustcode;
    }

    /**  **/
    public void setTrustcode(String trustcode) {
        this.trustcode = trustcode;
    }

    /**id  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /**供应商id  **/
    public String getSupid() {
        return this.supid;
    }

    /**  **/
    public void setSupid(String supid) {
        this.supid = supid;
    }

    /**供应商name  **/
    public String getSupname() {
        return this.supname;
    }

    /**  **/
    public void setSupname(String supname) {
        this.supname = supname;
    }

    /**委托方  **/
    public String getDelegater() {
        return this.delegater;
    }

    /**  **/
    public void setDelegater(String delegater) {
        this.delegater = delegater;
    }

    /** 委托日期 **/
    public String getApplydate() {
        return this.applydate;
    }

    /**  **/
    public void setApplydate(String applydate) {
        this.applydate = applydate;
    }

    /** 任务来源 **/
    public String getTasksource() {
        return this.tasksource;
    }

    /**  **/
    public void setTasksource(String tasksource) {
        this.tasksource = tasksource;
    }

    /** 频率 **/
    public String getFrequency() {
        return this.frequency;
    }

    /**  **/
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    /**任务类型  **/
    public String getTasktype() {
        return this.tasktype;
    }

    /**  **/
    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
    }

    /** 任务开始时间 **/
    public String getTaskstartdate() {
        return this.taskstartdate;
    }

    /**  **/
    public void setTaskstartdate(String taskstartdate) {
        this.taskstartdate = taskstartdate;
    }

    /**任务结束时间  **/
    public String getTaskenddate() {
        return this.taskenddate;
    }

    /**  **/
    public void setTaskenddate(String taskenddate) {
        this.taskenddate = taskenddate;
    }

    /**车型  **/
    public String getVehicletype() {
        return this.vehicletype;
    }

    /**  **/
    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    /**底盘号  **/
    public String getChassiscode() {
        return this.chassiscode;
    }

    /**  **/
    public void setChassiscode(String chassiscode) {
        this.chassiscode = chassiscode;
    }

    /**状态  **/
    public String getStatus() {
        return this.status;
    }

    /**  **/
    public void setStatus(String status) {
        this.status = status;
    }

    /** beizhu**/
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    //试验计划书名
    public String getAtTaskbookName() {
        return atTaskbookName;
    }

    public void setAtTaskbookName(String atTaskbookName) {
        this.atTaskbookName = atTaskbookName;
    }

    //行车记录
    public String getAtDriveRecordName() {
        return atDriveRecordName;
    }

    public void setAtDriveRecordName(String atDriveRecordName) {
        this.atDriveRecordName = atDriveRecordName;
    }

    //日常检查名
    public String getAtCheckName() {
        return atCheckName;
    }

    public void setAtCheckName(String atCheckName) {
        this.atCheckName = atCheckName;
    }

    //扭矩记录
    public String getAtTorqueRecordName() {
        return atTorqueRecordName;
    }

    public void setAtTorqueRecordName(String atTorqueRecordName) {
        this.atTorqueRecordName = atTorqueRecordName;
    }

    //轮毂温度
    public String getAtHubTemperatureName() {
        return atHubTemperatureName;
    }

    public void setAtHubTemperatureName(String atHubTemperatureName) {
        this.atHubTemperatureName = atHubTemperatureName;
    }

    //路桥
    public String getAtLuqiaoTicketName() {
        return atLuqiaoTicketName;
    }

    public void setAtLuqiaoTicketName(String atLuqiaoTicketName) {
        this.atLuqiaoTicketName = atLuqiaoTicketName;
    }

    //维修记录
    public String getAtRepairUpdownName() {
        return atRepairUpdownName;
    }

    public void setAtRepairUpdownName(String atRepairUpdownName) {
        this.atRepairUpdownName = atRepairUpdownName;
    }

    //四轮定位
    public String getAtFourwheelPositionName() {
        return atFourwheelPositionName;
    }

    public void setAtFourwheelPositionName(String atFourwheelPositionName) {
        this.atFourwheelPositionName = atFourwheelPositionName;
    }

    //实验报告
    public String getAtTrialReportName() {
        return atTrialReportName;
    }

    public void setAtTrialReportName(String atTrialReportName) {
        this.atTrialReportName = atTrialReportName;
    }

    //实验总结
    public String getAtTrialSummaryName() {
        return atTrialSummaryName;
    }

    public void setAtTrialSummaryName(String atTrialSummaryName) {
        this.atTrialSummaryName = atTrialSummaryName;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 任务---0：cv   1：pv
     * @return
     */
    public String getPvOrcv() {
        return pvOrcv;
    }

    public void setPvOrcv(String pvOrcv) {
        this.pvOrcv = pvOrcv;
    }

    /**
     * chuangjianren
     * @return
     */
    public String getCreateById() {
        return createById;
    }

    public void setCreateById(String createById) {
        this.createById = createById;
    }

    /**
     * 创建时间
     * @return
     */
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * 确认人
     * @return
     */
    public String getConfirmPerson() {
        return confirmPerson;
    }

    public void setConfirmPerson(String confirmPerson) {
        this.confirmPerson = confirmPerson;
    }

    public String getPcId() {
        return pcId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getTower() {
        return tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
    }



    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
}
