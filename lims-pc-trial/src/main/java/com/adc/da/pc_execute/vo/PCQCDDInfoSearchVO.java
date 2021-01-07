package com.adc.da.pc_execute.vo;

public class PCQCDDInfoSearchVO {
    /**
     *主键
     */
    private String id;

    /**
     *创建人
     */
    private String createBy;

    /**
     *创建时间
     */
    private String createTime;


    /**
     *备注
     */
    private String remark;

    /**
     *QCDD编号
     *QCDD对比的编号规则：QCDD-序号-年份
     */
    private String qcddNo;

    /**
     *试验申请id
     */
    private String trialTaskId;

    /**
     *试验项目id
     */
    private String projectId;

    /**
     *试验项目名称
     */
    private String projectName;

    /**
     *负责人id
     */
    private String projectLeaderId;

    /**
     *负责人名称
     */
    private String projectLeaderName;

    /**
     *业务部门id
     */
    private String projectOrgId;

    /**
     *业务部门名称
     */
    private String projectOrgName;

    /**
     *结论
     */
    private String conclusion;

    /**
     * baseBusId
     */
    private String baseBusId;
    
    /**
     * 流程状态
     * 0-草稿
     * 1-审批中
     * 2-已审批
     * 3-退回
     */
    private String qcddStatus;
    
    /**
     *最后供应商id
     */
    private String supIdEnd;

    /**
     *最后供应商名称
     */
    private String supNameEnd;
    /**
     *最后成本
     */
    private String costEnd;
    
    /**
	 * 区分试验任务还是执行计划
	 * 0-试验任务；1-执行计划
	 */
	private String taskOrPlan;
	
	
	
	
	
	public String getTaskOrPlan() {
		return taskOrPlan;
	}

	public void setTaskOrPlan(String taskOrPlan) {
		this.taskOrPlan = taskOrPlan;
	}

    
	public String getQcddStatus() {
		return qcddStatus;
	}

	public void setQcddStatus(String qcddStatus) {
		this.qcddStatus = qcddStatus;
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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getQcddNo() {
		return qcddNo;
	}

	public void setQcddNo(String qcddNo) {
		this.qcddNo = qcddNo;
	}

	public String getTrialTaskId() {
		return trialTaskId;
	}

	public void setTrialTaskId(String trialTaskId) {
		this.trialTaskId = trialTaskId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectLeaderId() {
		return projectLeaderId;
	}

	public void setProjectLeaderId(String projectLeaderId) {
		this.projectLeaderId = projectLeaderId;
	}

	public String getProjectLeaderName() {
		return projectLeaderName;
	}

	public void setProjectLeaderName(String projectLeaderName) {
		this.projectLeaderName = projectLeaderName;
	}

	public String getProjectOrgId() {
		return projectOrgId;
	}

	public void setProjectOrgId(String projectOrgId) {
		this.projectOrgId = projectOrgId;
	}

	public String getProjectOrgName() {
		return projectOrgName;
	}

	public void setProjectOrgName(String projectOrgName) {
		this.projectOrgName = projectOrgName;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getSupIdEnd() {
		return supIdEnd;
	}

	public void setSupIdEnd(String supIdEnd) {
		this.supIdEnd = supIdEnd;
	}

	public String getSupNameEnd() {
		return supNameEnd;
	}

	public void setSupNameEnd(String supNameEnd) {
		this.supNameEnd = supNameEnd;
	}

	public String getCostEnd() {
		return costEnd;
	}

	public void setCostEnd(String costEnd) {
		this.costEnd = costEnd;
	}

    
}