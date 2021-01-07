package com.adc.da.pc_execute.page;

import com.adc.da.base.page.BasePage;

public class PCReliableInitTaskEOPage extends BasePage {
	/**
     *项目名称
     */
    private String taskName;
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
     *试验任务id
     */
    private String trialTaskId;
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
	
	
	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}
	public String getTaskName() {
		return taskName;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
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

	public String getTrialTaskId() {
		return trialTaskId;
	}

	public void setTrialTaskId(String trialTaskId) {
		this.trialTaskId = trialTaskId;
	}
    
	
}
