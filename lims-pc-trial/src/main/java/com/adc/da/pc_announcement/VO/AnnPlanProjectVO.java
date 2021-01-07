package com.adc.da.pc_announcement.VO;

import io.swagger.annotations.ApiModelProperty;

public class AnnPlanProjectVO {
    //申报项目ID
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("公告计划ID")
    private String planId;
    @ApiModelProperty("车型")
    private String pjModel;
    @ApiModelProperty("类别")
    private String pjType;
    @ApiModelProperty("任务来源")
    private String pjTaskSource;
    @ApiModelProperty("立项审批表")
    private String pjApprovalForm;
    @ApiModelProperty("试验工程师ID")
    private String pjEngineerId;
    @ApiModelProperty("试验工程师姓名")
    private String pjEngineerName;
    @ApiModelProperty("公告批次")
    private String pjAnnBatch;
    @ApiModelProperty("车辆计划到达时间")
    private String pjCarTimePlan;
    @ApiModelProperty("车辆实际到达时间")
    private String pjCarTimeActual;
    @ApiModelProperty("技术参数计划获取时间")
    private String pjParamTimePlan;
    @ApiModelProperty("技术参数实际获取时间")
    private String pjParamTimeActual;
    @ApiModelProperty("状态  0：未开始 1：进行中 2：已完成 3：已取消")
    private String pjStatus;
    @ApiModelProperty("供应商")
    private String pjSupplier;
    @ApiModelProperty("报告附件ID")
    private String pjReportId;
    @ApiModelProperty("报告附件名称")
    private String pjReportName;
    
    @ApiModelProperty("任务说明")
    private String pjTaskExplain;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPjModel() {
        return pjModel;
    }

    public void setPjModel(String pjModel) {
        this.pjModel = pjModel;
    }

    public String getPjType() {
        return pjType;
    }

    public void setPjType(String pjType) {
        this.pjType = pjType;
    }

    public String getPjTaskSource() {
        return pjTaskSource;
    }

    public void setPjTaskSource(String pjTaskSource) {
        this.pjTaskSource = pjTaskSource;
    }

    public String getPjApprovalForm() {
        return pjApprovalForm;
    }

    public void setPjApprovalForm(String pjApprovalForm) {
        this.pjApprovalForm = pjApprovalForm;
    }

    public String getPjEngineerId() {
        return pjEngineerId;
    }

    public void setPjEngineerId(String pjEngineerId) {
        this.pjEngineerId = pjEngineerId;
    }

    public String getPjEngineerName() {
        return pjEngineerName;
    }

    public void setPjEngineerName(String pjEngineerName) {
        this.pjEngineerName = pjEngineerName;
    }

    public String getPjAnnBatch() {
        return pjAnnBatch;
    }

    public void setPjAnnBatch(String pjAnnBatch) {
        this.pjAnnBatch = pjAnnBatch;
    }

    public String getPjCarTimePlan() {
        return pjCarTimePlan;
    }

    public void setPjCarTimePlan(String pjCarTimePlan) {
        this.pjCarTimePlan = pjCarTimePlan;
    }

    public String getPjCarTimeActual() {
        return pjCarTimeActual;
    }

    public void setPjCarTimeActual(String pjCarTimeActual) {
        this.pjCarTimeActual = pjCarTimeActual;
    }

    public String getPjParamTimePlan() {
        return pjParamTimePlan;
    }

    public void setPjParamTimePlan(String pjParamTimePlan) {
        this.pjParamTimePlan = pjParamTimePlan;
    }

    public String getPjParamTimeActual() {
        return pjParamTimeActual;
    }

    public void setPjParamTimeActual(String pjParamTimeActual) {
        this.pjParamTimeActual = pjParamTimeActual;
    }

    public String getPjStatus() {
        return pjStatus;
    }

    public void setPjStatus(String pjStatus) {
        this.pjStatus = pjStatus;
    }

    public String getPjSupplier() {
        return pjSupplier;
    }

    public void setPjSupplier(String pjSupplier) {
        this.pjSupplier = pjSupplier;
    }

    public String getPjReportId() {
        return pjReportId;
    }

    public void setPjReportId(String pjReportId) {
        this.pjReportId = pjReportId;
    }

    public String getPjReportName() {
        return pjReportName;
    }

    public void setPjReportName(String pjReportName) {
        this.pjReportName = pjReportName;
    }
}
