package com.adc.da.pc_loan_application.vo;

import io.swagger.annotations.ApiModelProperty;


public class PcDisPlayVo {
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
    @ApiModelProperty("借车用途")
    private String purpose;
    @ApiModelProperty("底盘号")
    private String chassisCode;
    @ApiModelProperty("流程状态：0:草稿 1：待审批 4：退回 5：已审批")
    private String processStatus;
    @ApiModelProperty("借车单编号")
    private String loanCarCode;
    @ApiModelProperty("试验任务编号")
    private String taskNumber;

    public String getLoanCarCode() {
        return loanCarCode;
    }

    public void setLoanCarCode(String loanCarCode) {
        this.loanCarCode = loanCarCode;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getChassisCode() {
        return chassisCode;
    }

    public void setChassisCode(String chassisCode) {
        this.chassisCode = chassisCode;
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



    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }
}
