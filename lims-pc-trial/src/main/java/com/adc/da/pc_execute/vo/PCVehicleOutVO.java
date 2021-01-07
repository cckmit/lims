package com.adc.da.pc_execute.vo;

import java.util.ArrayList;
import java.util.List;

import com.adc.da.pc_execute.entity.PCVehicleOutInfoEO;

public class PCVehicleOutVO {
    /**
     *主键
     */
    private String id;

    /**
     *删除状态 1删除 0存在
     */
    private String delFlag;

    /**
     *创建时间
     */
    private String createTime;

    /**
     *创建者
     */
    private String createBy;

    /**
     *更新时间
     */
    private String updateTime;

    /**
     *更新者
     */
    private String updateBy;

    /**
     *试验任务id
     */
    private String trialTaskId;

    /**
     *创建者名称
     */
    private String createByName;

    /**
     *提货单位
     *0-商用车品质保障部
	 *1-乘用车品质保证部
     */
    private String deliveryUnit;
   
    /**
     * 状态
     * 0-草稿
     * 1-审批中
     * 2-已审批
     * 3-退回
     * 4-撤回
     */
    private String vehicleStatus;
    
    /**
     * 编号
     */
    private String vehicleNo;
    
    /**
     * 整车出门详情
     */
    private List<PCVehicleOutInfoEO> infoList = new ArrayList<>();

    /**
	 * 试验任务唯一编号
	 */
	private String taskNumber;
	
	/**
	 * 区分试验任务还是执行计划
	 * 0-试验任务；1-执行计划
	 */
	private String taskOrPlan;
	
	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
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

	public String getDeliveryUnit() {
		return deliveryUnit;
	}

	public void setDeliveryUnit(String deliveryUnit) {
		this.deliveryUnit = deliveryUnit;
	}

	
	public String getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public List<PCVehicleOutInfoEO> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<PCVehicleOutInfoEO> infoList) {
		this.infoList = infoList;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getTaskOrPlan() {
		return taskOrPlan;
	}

	public void setTaskOrPlan(String taskOrPlan) {
		this.taskOrPlan = taskOrPlan;
	}
    
    
}
