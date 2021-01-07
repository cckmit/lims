package com.adc.da.pc_execute.vo;

public class PCReliableInitTaskSearchVO {
	 /**
     *主键
     */
    private String id;
    /**
     *立项单编号 CV/PV-KKXLX-0001-2019
     */
    private String initCode;

    /**
     *项目类型
	     *委外试验(检测所)
	     *外包试验(供应商)
	     *自行试验
     */
    private String taskType;
    /**
     *项目名称
     */
    private String taskName;
    /**
     *负责部门id
     */
    private String chargeOrgId;

    /**
     *负责部门名称
     */
    private String chargeOrgName;

    /**
     *负责人id
     */
    private String chargeUserId;

    /**
     *负责人名称
     */
    private String chargeUserName;
    
    /**
     *创建时间
     */
    private String createTime;
    /**
     *项目简介
     */
    private String taskSynopsis;
    
    /**
     * 可靠性立项单状态
     */
    private String initStatus;
    
    /**
     * baseBusId
     */
    private String baseBusId;
    
    /**
     * 创建人
     */
    private String createBy;
    
	/**
     *试验任务id
     */
    private String trialTaskId;
    
    
    
	public String getTrialTaskId() {
		return trialTaskId;
	}

	public void setTrialTaskId(String trialTaskId) {
		this.trialTaskId = trialTaskId;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getBaseBusId() {
		return baseBusId;
	}

	public void setBaseBusId(String baseBusId) {
		this.baseBusId = baseBusId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInitCode() {
		return initCode;
	}

	public void setInitCode(String initCode) {
		this.initCode = initCode;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getChargeOrgId() {
		return chargeOrgId;
	}

	public void setChargeOrgId(String chargeOrgId) {
		this.chargeOrgId = chargeOrgId;
	}

	public String getChargeOrgName() {
		return chargeOrgName;
	}

	public void setChargeOrgName(String chargeOrgName) {
		this.chargeOrgName = chargeOrgName;
	}

	public String getChargeUserId() {
		return chargeUserId;
	}

	public void setChargeUserId(String chargeUserId) {
		this.chargeUserId = chargeUserId;
	}

	public String getChargeUserName() {
		return chargeUserName;
	}

	public void setChargeUserName(String chargeUserName) {
		this.chargeUserName = chargeUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getTaskSynopsis() {
		return taskSynopsis;
	}

	public void setTaskSynopsis(String taskSynopsis) {
		this.taskSynopsis = taskSynopsis;
	}

	public String getInitStatus() {
		return initStatus;
	}

	public void setInitStatus(String initStatus) {
		this.initStatus = initStatus;
	}
    
    
}
