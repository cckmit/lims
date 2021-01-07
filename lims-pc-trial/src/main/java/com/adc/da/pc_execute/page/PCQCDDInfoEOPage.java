package com.adc.da.pc_execute.page;

import com.adc.da.base.page.BasePage;

public class PCQCDDInfoEOPage extends BasePage {
	 /**
     *QCDD编号
     */
    private String qcddNo;

    /**
     *试验申请id
     */
    private String trialTaskId;

    /**
     *试验项目名称
     */
    private String projectName;


    /**
     *负责人名称
     */
    private String projectLeaderName;


    /**
     *业务部门名称
     */
    private String projectOrgName;
    
    /**
     *创建时间
     */
    private String createTime;
    private String createDate1;
    private String createDate2;

    /**
	 * 试验任务唯一编号
	 */
	private String taskNumber;
	
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

	
	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectLeaderName() {
		return projectLeaderName;
	}

	public void setProjectLeaderName(String projectLeaderName) {
		this.projectLeaderName = projectLeaderName;
	}

	public String getProjectOrgName() {
		return projectOrgName;
	}

	public void setProjectOrgName(String projectOrgName) {
		this.projectOrgName = projectOrgName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateDate1() {
		return createDate1;
	}

	public void setCreateDate1(String createDate1) {
		this.createDate1 = createDate1;
	}

	public String getCreateDate2() {
		return createDate2;
	}

	public void setCreateDate2(String createDate2) {
		this.createDate2 = createDate2;
	}
}
