package com.adc.da.pc_execute.vo;

import java.util.ArrayList;
import java.util.List;

import com.adc.da.pc_execute.entity.PCTrialLineEO;


public class PCTrialLineSearchVO {
	/**
	 * 可靠性立项单id
	 */
	private String initTaskId;
	/**
	 * 试验任务id
	 */
	private String trialTaskId;
	
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
    
	
	public String getTrialTaskId() {
		return trialTaskId;
	}
	public void setTrialTaskId(String trialTaskId) {
		this.trialTaskId = trialTaskId;
	}
	/**
	 * 路线策划数据
	 */
	private List<PCTrialLineEO> PCTrialLineList = new ArrayList<>();
	
	public String getInitTaskId() {
		return initTaskId;
	}
	public void setInitTaskId(String initTaskId) {
		this.initTaskId = initTaskId;
	}
	public List<PCTrialLineEO> getPCTrialLineList() {
		return PCTrialLineList;
	}
	public void setPCTrialLineList(List<PCTrialLineEO> pCTrialLineList) {
		PCTrialLineList = pCTrialLineList;
	}
	
}
