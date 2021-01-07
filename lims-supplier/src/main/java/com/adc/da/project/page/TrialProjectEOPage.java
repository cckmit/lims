package com.adc.da.project.page;

import com.adc.da.base.page.BasePage;

import java.util.Date;
import java.util.List;

/**
 * <b>功能：</b>SUP_TRIAL_PROJECT TrialProjectEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-19 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class TrialProjectEOPage extends BasePage {

    private List<String> searchPhrase;
    private String taskbookcode;
    private String taskbookcodeOperator = "=";
    private String atTaskbook;
    private String atTaskbookOperator = "=";
    private String atDriveRecord;
    private String atDriveRecordOperator = "=";
    private String atCheck;
    private String atCheckOperator = "=";
    private String atTorqueRecord;
    private String atTorqueRecordOperator = "=";
    private String atHubTemperature;
    private String atHubTemperatureOperator = "=";
    private String atLuqiaoTicket;
    private String atLuqiaoTicketOperator = "=";
    private String atRepairUpdown;
    private String atRepairUpdownOperator = "=";
    private String atFourwheelPosition;
    private String atFourwheelPositionOperator = "=";
    private String atTrialReport;
    private String atTrialReportOperator = "=";
    private String atTrialSummary;
    private String atTrialSummaryOperator = "=";
    private String approvalcode;
    private String approvalcodeOperator = "=";
    private String approvalcost;
    private String approvalcostOperator = "=";
    private String trustcode;
    private String trustcodeOperator = "=";
    private String id;
    private String idOperator = "=";
    private String supid;
    private String supidOperator = "=";
    private String supname;
    private String supnameOperator = "=";
    private String delegater;
    private String delegaterOperator = "=";
    private String applydate;
    private String applydate1;
    private String applydate2;
    private String applydateOperator = "=";
    private String tasksource;
    private String tasksourceOperator = "=";
    private String frequency;
    private String frequencyOperator = "=";
    private String tasktype;
    private String tasktypeOperator = "=";
    private String taskstartdate;
    private String taskstartdate1;
    private String taskstartdate2;
    private String taskstartdateOperator = "=";
    private String taskenddate;
    private String taskenddate1;
    private String taskenddate2;
    private String taskenddateOperator = "=";
    private String vehicletype;
    private String vehicletypeOperator = "=";
    private String chassiscode;
    private String chassiscodeOperator = "=";
    private String status;
    private String statusOperator = "=";
    private String createById;

    private String pcId;

    public String getPcId() {
        return pcId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    public String getTaskbookcode() {
        return this.taskbookcode;
    }

    public void setTaskbookcode(String taskbookcode) {
        this.taskbookcode = taskbookcode;
    }

    public String getTaskbookcodeOperator() {
        return this.taskbookcodeOperator;
    }

    public void setTaskbookcodeOperator(String taskbookcodeOperator) {
        this.taskbookcodeOperator = taskbookcodeOperator;
    }

    public String getAtTaskbook() {
        return this.atTaskbook;
    }

    public void setAtTaskbook(String atTaskbook) {
        this.atTaskbook = atTaskbook;
    }

    public String getAtTaskbookOperator() {
        return this.atTaskbookOperator;
    }

    public void setAtTaskbookOperator(String atTaskbookOperator) {
        this.atTaskbookOperator = atTaskbookOperator;
    }

    public String getAtDriveRecord() {
        return this.atDriveRecord;
    }

    public void setAtDriveRecord(String atDriveRecord) {
        this.atDriveRecord = atDriveRecord;
    }

    public String getAtDriveRecordOperator() {
        return this.atDriveRecordOperator;
    }

    public void setAtDriveRecordOperator(String atDriveRecordOperator) {
        this.atDriveRecordOperator = atDriveRecordOperator;
    }

    public String getAtCheck() {
        return this.atCheck;
    }

    public void setAtCheck(String atCheck) {
        this.atCheck = atCheck;
    }

    public String getAtCheckOperator() {
        return this.atCheckOperator;
    }

    public void setAtCheckOperator(String atCheckOperator) {
        this.atCheckOperator = atCheckOperator;
    }

    public String getAtTorqueRecord() {
        return this.atTorqueRecord;
    }

    public void setAtTorqueRecord(String atTorqueRecord) {
        this.atTorqueRecord = atTorqueRecord;
    }

    public String getAtTorqueRecordOperator() {
        return this.atTorqueRecordOperator;
    }

    public void setAtTorqueRecordOperator(String atTorqueRecordOperator) {
        this.atTorqueRecordOperator = atTorqueRecordOperator;
    }

    public String getAtHubTemperature() {
        return this.atHubTemperature;
    }

    public void setAtHubTemperature(String atHubTemperature) {
        this.atHubTemperature = atHubTemperature;
    }

    public String getAtHubTemperatureOperator() {
        return this.atHubTemperatureOperator;
    }

    public void setAtHubTemperatureOperator(String atHubTemperatureOperator) {
        this.atHubTemperatureOperator = atHubTemperatureOperator;
    }

    public String getAtLuqiaoTicket() {
        return this.atLuqiaoTicket;
    }

    public void setAtLuqiaoTicket(String atLuqiaoTicket) {
        this.atLuqiaoTicket = atLuqiaoTicket;
    }

    public String getAtLuqiaoTicketOperator() {
        return this.atLuqiaoTicketOperator;
    }

    public void setAtLuqiaoTicketOperator(String atLuqiaoTicketOperator) {
        this.atLuqiaoTicketOperator = atLuqiaoTicketOperator;
    }

    public String getAtRepairUpdown() {
        return this.atRepairUpdown;
    }

    public void setAtRepairUpdown(String atRepairUpdown) {
        this.atRepairUpdown = atRepairUpdown;
    }

    public String getAtRepairUpdownOperator() {
        return this.atRepairUpdownOperator;
    }

    public void setAtRepairUpdownOperator(String atRepairUpdownOperator) {
        this.atRepairUpdownOperator = atRepairUpdownOperator;
    }

    public String getAtFourwheelPosition() {
        return this.atFourwheelPosition;
    }

    public void setAtFourwheelPosition(String atFourwheelPosition) {
        this.atFourwheelPosition = atFourwheelPosition;
    }

    public String getAtFourwheelPositionOperator() {
        return this.atFourwheelPositionOperator;
    }

    public void setAtFourwheelPositionOperator(String atFourwheelPositionOperator) {
        this.atFourwheelPositionOperator = atFourwheelPositionOperator;
    }

    public String getAtTrialReport() {
        return this.atTrialReport;
    }

    public void setAtTrialReport(String atTrialReport) {
        this.atTrialReport = atTrialReport;
    }

    public String getAtTrialReportOperator() {
        return this.atTrialReportOperator;
    }

    public void setAtTrialReportOperator(String atTrialReportOperator) {
        this.atTrialReportOperator = atTrialReportOperator;
    }

    public String getAtTrialSummary() {
        return this.atTrialSummary;
    }

    public void setAtTrialSummary(String atTrialSummary) {
        this.atTrialSummary = atTrialSummary;
    }

    public String getAtTrialSummaryOperator() {
        return this.atTrialSummaryOperator;
    }

    public void setAtTrialSummaryOperator(String atTrialSummaryOperator) {
        this.atTrialSummaryOperator = atTrialSummaryOperator;
    }

    public String getApprovalcode() {
        return this.approvalcode;
    }

    public void setApprovalcode(String approvalcode) {
        this.approvalcode = approvalcode;
    }

    public String getApprovalcodeOperator() {
        return this.approvalcodeOperator;
    }

    public void setApprovalcodeOperator(String approvalcodeOperator) {
        this.approvalcodeOperator = approvalcodeOperator;
    }

    public String getApprovalcost() {
        return this.approvalcost;
    }

    public void setApprovalcost(String approvalcost) {
        this.approvalcost = approvalcost;
    }

    public String getApprovalcostOperator() {
        return this.approvalcostOperator;
    }

    public void setApprovalcostOperator(String approvalcostOperator) {
        this.approvalcostOperator = approvalcostOperator;
    }

    public String getTrustcode() {
        return this.trustcode;
    }

    public void setTrustcode(String trustcode) {
        this.trustcode = trustcode;
    }

    public String getTrustcodeOperator() {
        return this.trustcodeOperator;
    }

    public void setTrustcodeOperator(String trustcodeOperator) {
        this.trustcodeOperator = trustcodeOperator;
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

    public String getSupid() {
        return this.supid;
    }

    public void setSupid(String supid) {
        this.supid = supid;
    }

    public String getSupidOperator() {
        return this.supidOperator;
    }

    public void setSupidOperator(String supidOperator) {
        this.supidOperator = supidOperator;
    }

    public String getSupname() {
        return this.supname;
    }

    public void setSupname(String supname) {
        this.supname = supname;
    }

    public String getSupnameOperator() {
        return this.supnameOperator;
    }

    public void setSupnameOperator(String supnameOperator) {
        this.supnameOperator = supnameOperator;
    }

    public String getDelegater() {
        return this.delegater;
    }

    public void setDelegater(String delegater) {
        this.delegater = delegater;
    }

    public String getDelegaterOperator() {
        return this.delegaterOperator;
    }

    public void setDelegaterOperator(String delegaterOperator) {
        this.delegaterOperator = delegaterOperator;
    }

    public String getApplydate() {
        return this.applydate;
    }

    public void setApplydate(String applydate) {
        this.applydate = applydate;
    }

    public String getApplydate1() {
        return this.applydate1;
    }

    public void setApplydate1(String applydate1) {
        this.applydate1 = applydate1;
    }

    public String getApplydate2() {
        return this.applydate2;
    }

    public void setApplydate2(String applydate2) {
        this.applydate2 = applydate2;
    }

    public String getApplydateOperator() {
        return this.applydateOperator;
    }

    public void setApplydateOperator(String applydateOperator) {
        this.applydateOperator = applydateOperator;
    }

    public String getTasksource() {
        return this.tasksource;
    }

    public void setTasksource(String tasksource) {
        this.tasksource = tasksource;
    }

    public String getTasksourceOperator() {
        return this.tasksourceOperator;
    }

    public void setTasksourceOperator(String tasksourceOperator) {
        this.tasksourceOperator = tasksourceOperator;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getFrequencyOperator() {
        return this.frequencyOperator;
    }

    public void setFrequencyOperator(String frequencyOperator) {
        this.frequencyOperator = frequencyOperator;
    }

    public String getTasktype() {
        return this.tasktype;
    }

    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
    }

    public String getTasktypeOperator() {
        return this.tasktypeOperator;
    }

    public void setTasktypeOperator(String tasktypeOperator) {
        this.tasktypeOperator = tasktypeOperator;
    }

    public String getTaskstartdate() {
        return this.taskstartdate;
    }

    public void setTaskstartdate(String taskstartdate) {
        this.taskstartdate = taskstartdate;
    }

    public String getTaskstartdate1() {
        return this.taskstartdate1;
    }

    public void setTaskstartdate1(String taskstartdate1) {
        this.taskstartdate1 = taskstartdate1;
    }

    public String getTaskstartdate2() {
        return this.taskstartdate2;
    }

    public void setTaskstartdate2(String taskstartdate2) {
        this.taskstartdate2 = taskstartdate2;
    }

    public String getTaskstartdateOperator() {
        return this.taskstartdateOperator;
    }

    public void setTaskstartdateOperator(String taskstartdateOperator) {
        this.taskstartdateOperator = taskstartdateOperator;
    }

    public String getTaskenddate() {
        return this.taskenddate;
    }

    public void setTaskenddate(String taskenddate) {
        this.taskenddate = taskenddate;
    }

    public String getTaskenddate1() {
        return this.taskenddate1;
    }

    public void setTaskenddate1(String taskenddate1) {
        this.taskenddate1 = taskenddate1;
    }

    public String getTaskenddate2() {
        return this.taskenddate2;
    }

    public void setTaskenddate2(String taskenddate2) {
        this.taskenddate2 = taskenddate2;
    }

    public String getTaskenddateOperator() {
        return this.taskenddateOperator;
    }

    public void setTaskenddateOperator(String taskenddateOperator) {
        this.taskenddateOperator = taskenddateOperator;
    }

    public String getVehicletype() {
        return this.vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public String getVehicletypeOperator() {
        return this.vehicletypeOperator;
    }

    public void setVehicletypeOperator(String vehicletypeOperator) {
        this.vehicletypeOperator = vehicletypeOperator;
    }

    public String getChassiscode() {
        return this.chassiscode;
    }

    public void setChassiscode(String chassiscode) {
        this.chassiscode = chassiscode;
    }

    public String getChassiscodeOperator() {
        return this.chassiscodeOperator;
    }

    public void setChassiscodeOperator(String chassiscodeOperator) {
        this.chassiscodeOperator = chassiscodeOperator;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusOperator() {
        return this.statusOperator;
    }

    public void setStatusOperator(String statusOperator) {
        this.statusOperator = statusOperator;
    }

    public List<String> getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(List<String> searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public String getCreateById() {
        return createById;
    }

    public void setCreateById(String createById) {
        this.createById = createById;
    }
}
