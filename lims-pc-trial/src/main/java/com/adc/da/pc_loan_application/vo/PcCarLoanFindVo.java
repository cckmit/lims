package com.adc.da.pc_loan_application.vo;

import com.adc.da.pc_loan_application.entity.PcCarLoanApplicationEO;
import com.adc.da.pc_loan_application.entity.PcCarLoanInformationEO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class PcCarLoanFindVo {
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
    @ApiModelProperty("区分试验任务还是执行计划:0-试验任务；1-执行计划")
    private String taskOrPlan;

    private List<PcCarLoanInformationEO> list;


    public String getLoanCarCode() {
        return loanCarCode;
    }

    public void setLoanCarCode(String loanCarCode) {
        this.loanCarCode = loanCarCode;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getPcvStatus() {
        return pcvStatus;
    }

    public void setPcvStatus(String pcvStatus) {
        this.pcvStatus = pcvStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getExperimentalArea() {
        return experimentalArea;
    }

    public void setExperimentalArea(String experimentalArea) {
        this.experimentalArea = experimentalArea;
    }

    public String getExperimentalType() {
        return experimentalType;
    }

    public void setExperimentalType(String experimentalType) {
        this.experimentalType = experimentalType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getExperimentalRoute() {
        return experimentalRoute;
    }

    public void setExperimentalRoute(String experimentalRoute) {
        this.experimentalRoute = experimentalRoute;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public List<PcCarLoanInformationEO> getList() {
        return list;
    }

    public void setList(List<PcCarLoanInformationEO> list) {
        this.list = list;
    }

    public String getTaskOrPlan() {
        return taskOrPlan;
    }

    public void setTaskOrPlan(String taskOrPlan) {
        this.taskOrPlan = taskOrPlan;
    }
}
