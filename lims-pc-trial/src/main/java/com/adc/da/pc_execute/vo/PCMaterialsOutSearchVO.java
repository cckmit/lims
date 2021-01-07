package com.adc.da.pc_execute.vo;

public class PCMaterialsOutSearchVO {
    /**
     *主键
     */
    private String id;
    /**
     *创建时间
     */
    private String createTime;
    /**
     *创建人
     */
    private String createBy;
    /**
     *创建人名
     */
    private String createByName;

    /**
     *提货单位
     *0-商用车品质保障部
	 *1-乘用车品质保证部
     */
    private String deliveryUnit;
    /**
     *状态
     * 0-草稿
     * 1-审批中
     * 2-已审批
     * 3-退回
     * 4-撤回
     */
    private String materialsStatus;

    /**
     *物资出门单号
     */
    private String materialsOutNo;
    
    /**
     * baseBusId
     */
    private String baseBusId;
    
    /**
     *试验任务id 
     */
    private String trialTaskId;
    
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

	public String getTrialTaskId() {
		return trialTaskId;
	}

	public void setTrialTaskId(String trialTaskId) {
		this.trialTaskId = trialTaskId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public String getMaterialsStatus() {
		return materialsStatus;
	}

	public void setMaterialsStatus(String materialsStatus) {
		this.materialsStatus = materialsStatus;
	}

	public String getMaterialsOutNo() {
		return materialsOutNo;
	}

	public void setMaterialsOutNo(String materialsOutNo) {
		this.materialsOutNo = materialsOutNo;
	}

	public String getBaseBusId() {
		return baseBusId;
	}

	public void setBaseBusId(String baseBusId) {
		this.baseBusId = baseBusId;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
    
    
}

