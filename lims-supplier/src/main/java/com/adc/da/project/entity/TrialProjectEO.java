package com.adc.da.project.entity;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.pc_drive.entity.RoadLineRealityEO;

import java.util.Date;
import java.util.List;

/**
 * <b>功能：</b>SUP_TRIAL_PROJECT TrialProjectEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-19 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class TrialProjectEO extends BaseEntity {

    //业务变更：增加了实际交付物（和所有交付物字段一致），故原有所有交付物字段值为0,1,2,3
    // 为0时表示此交付物一栏和实际交付物一栏的选项均未选择
    //为1时表示此字段仅交付物被勾选
    //为2时表示此字段仅实际交付物一栏被勾选
    //为3时表示此字段两栏均被勾选
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
    private String atFuelTicket;
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
    private String status;  //-1 草稿 1 已确认 2 不通过 3 审批中 4 已退回 5 已审批 6 撤回 7 完成反馈
    private String remark;
    private String delFlag;
    private String pvOrcv;
    private String createById;
    private String createDate;
    private String confirmPerson;
    private String carId;

    private String whetherFinish;    //是否按节点要求完成委托项目
    private String postponeNum;     //延期天数
    private String whetherStandard;            //实际交付物是否规范
    private String standardNum;          //不规范数
    private String malfunctionNum;              //试验检出故障数量
    private String reviewNum;           //评审故障漏检数

    private String manyFileId;          //多文件上传，以符号分割
    private String manyFileName;

    //pc模块的外键
    private String pcId;
    private List<ItemsDetailsEO> itemsDetailsEOList;

    private String operationId;//操作人ID
    private String operationName;//操作人姓名
    private String tower;//马力
    private String carType;//对应具体车辆类型：如：牵引车
    private String fuelType;//燃油类型
    private List<OperationPerson> operationList ;
    private List<RoadLineRealityEO> roadLineRealityEOList;
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

    public List<ItemsDetailsEO> getItemsDetailsEOList() {
        return itemsDetailsEOList;
    }

    public void setItemsDetailsEOList(List<ItemsDetailsEO> itemsDetailsEOList) {
        this.itemsDetailsEOList = itemsDetailsEOList;
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

    public List<OperationPerson> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<OperationPerson> operationList) {
        this.operationList = operationList;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }


    public String getAtFuelTicket() {
        return atFuelTicket;
    }

    public void setAtFuelTicket(String atFuelTicket) {
        this.atFuelTicket = atFuelTicket;
    }

    public String getWhetherFinish() {
        return whetherFinish;
    }

    public void setWhetherFinish(String whetherFinish) {
        this.whetherFinish = whetherFinish;
    }

    public String getPostponeNum() {
        return postponeNum;
    }

    public void setPostponeNum(String postponeNum) {
        this.postponeNum = postponeNum;
    }

    public String getWhetherStandard() {
        return whetherStandard;
    }

    public void setWhetherStandard(String whetherStandard) {
        this.whetherStandard = whetherStandard;
    }

    public String getStandardNum() {
        return standardNum;
    }

    public void setStandardNum(String standardNum) {
        this.standardNum = standardNum;
    }

    public String getMalfunctionNum() {
        return malfunctionNum;
    }

    public void setMalfunctionNum(String malfunctionNum) {
        this.malfunctionNum = malfunctionNum;
    }

    public String getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(String reviewNum) {
        this.reviewNum = reviewNum;
    }

    public String getManyFileId() {
        return manyFileId;
    }

    public void setManyFileId(String manyFileId) {
        this.manyFileId = manyFileId;
    }

    public String getManyFileName() {
        return manyFileName;
    }

    public void setManyFileName(String manyFileName) {
        this.manyFileName = manyFileName;
    }

    public List<RoadLineRealityEO> getRoadLineRealityEOList() {
        return roadLineRealityEOList;
    }

    public void setRoadLineRealityEOList(List<RoadLineRealityEO> roadLineRealityEOList) {
        this.roadLineRealityEOList = roadLineRealityEOList;
    }
}
