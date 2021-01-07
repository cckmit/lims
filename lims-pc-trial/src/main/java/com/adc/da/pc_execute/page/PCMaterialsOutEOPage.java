package com.adc.da.pc_execute.page;

import java.util.List;

import com.adc.da.base.page.BasePage;

public class PCMaterialsOutEOPage extends BasePage {
	/**
	 * 通用查询
	 */
	private List<String> searchPhrase;
	
    /**
     *试验任务id
     */
    private String trialTaskId;
    
	 /**
     *创建时间
     */
    private String createTime;
    private String createTime1;
    private String createTime2;

    /**
     *提货单位
     *0-商用车品质保障部
	 *1-乘用车品质保证部
     */
    private String deliveryUnit;
    
    /**
     *物资出门单号
     */
    private String materialsOutNo;
    
    /**
     *创建人名
     */
    private String createByName;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTime1() {
		return createTime1;
	}

	public void setCreateTime1(String createTime1) {
		this.createTime1 = createTime1;
	}

	public String getCreateTime2() {
		return createTime2;
	}

	public void setCreateTime2(String createTime2) {
		this.createTime2 = createTime2;
	}

	public String getDeliveryUnit() {
		return deliveryUnit;
	}

	public void setDeliveryUnit(String deliveryUnit) {
		this.deliveryUnit = deliveryUnit;
	}

	public String getMaterialsOutNo() {
		return materialsOutNo;
	}

	public void setMaterialsOutNo(String materialsOutNo) {
		this.materialsOutNo = materialsOutNo;
	}

	public List<String> getSearchPhrase() {
		return searchPhrase;
	}

	public void setSearchPhrase(List<String> searchPhrase) {
		this.searchPhrase = searchPhrase;
	}

	public String getTrialTaskId() {
		return trialTaskId;
	}

	public void setTrialTaskId(String trialTaskId) {
		this.trialTaskId = trialTaskId;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
    
	
	
}
