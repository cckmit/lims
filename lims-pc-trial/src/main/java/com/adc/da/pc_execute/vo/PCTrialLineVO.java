package com.adc.da.pc_execute.vo;

import com.adc.da.base.entity.BaseEntity;

public class PCTrialLineVO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *删除标记 1删除 0存在
     */
    private Short delFlag;

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
     *第几天
     */
    private String dayNo;

    /**
     *起点
     */
    private String lineStart;

    /**
     *终点
     */
    private String lineEnd;

    /**
          *类型
     *	磨合行驶
		一般公路试验
		市区+阻滞试验
		高速路试验
		山区公路试验
		(强化)环路试验
		越野路试试验
     */
    private String lineType;

    /**
     *一天公里数
     */
    private String oneDayKm;

    /**
     *备注
     */
    private String remarks;

    /**
     *行驶天数
     */
    private String driveDays;
    /**
     * []天一个循环
     */
    private String oneCycleDays;
    
    /**
     *循环数
     */
    private String lineCycle;

    /**
     *住宿天数
     */
    private String accoDays;

    /**
     *节假日
     */
    private String holidays;

    /**
     *车辆换件预计
     */
    private String altPartsDays;

    /**
     *现车会预计
     */
    private String currCarDays;

    /**
     *可靠性立项单id
     */
    private String initTaskId;

    /**
     * 试验申请id
     */
    private String trialTaskId;
    /**
     * 行驶次序
     */
    private Integer driveOrder;
    
    /**
	 * 试验任务唯一编号
	 */
	private String taskNumber;
	
	
	/**
	 * 循环序号
	 */
	private String cycleNo;
	
	/**
	 * 投入人数
	 */
	private String inputPersonNum;
	
	/**
	 * 合计里程
	 */
	private String mileageTotal;
	
	
	
	public String getCycleNo() {
		return cycleNo;
	}

	public void setCycleNo(String cycleNo) {
		this.cycleNo = cycleNo;
	}

	

	public String getInputPersonNum() {
		return inputPersonNum;
	}

	public void setInputPersonNum(String inputPersonNum) {
		this.inputPersonNum = inputPersonNum;
	}

	public String getMileageTotal() {
		return mileageTotal;
	}

	public void setMileageTotal(String mileageTotal) {
		this.mileageTotal = mileageTotal;
	}

	
	
	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}
    
    
	public Integer getDriveOrder() {
		return driveOrder;
	}

	public void setDriveOrder(Integer driveOrder) {
		this.driveOrder = driveOrder;
	}
    
    
	public String getTrialTaskId() {
		return trialTaskId;
	}

	public void setTrialTaskId(String trialTaskId) {
		this.trialTaskId = trialTaskId;
	}


	public String getOneCycleDays() {
		return oneCycleDays;
	}

	public void setOneCycleDays(String oneCycleDays) {
		this.oneCycleDays = oneCycleDays;
	}
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Short getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Short delFlag) {
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

	public String getDayNo() {
		return dayNo;
	}

	public void setDayNo(String dayNo) {
		this.dayNo = dayNo;
	}

	public String getLineStart() {
		return lineStart;
	}

	public void setLineStart(String lineStart) {
		this.lineStart = lineStart;
	}

	public String getLineEnd() {
		return lineEnd;
	}

	public void setLineEnd(String lineEnd) {
		this.lineEnd = lineEnd;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public String getOneDayKm() {
		return oneDayKm;
	}

	public void setOneDayKm(String oneDayKm) {
		this.oneDayKm = oneDayKm;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDriveDays() {
		return driveDays;
	}

	public void setDriveDays(String driveDays) {
		this.driveDays = driveDays;
	}

	public String getLineCycle() {
		return lineCycle;
	}

	public void setLineCycle(String lineCycle) {
		this.lineCycle = lineCycle;
	}

	public String getAccoDays() {
		return accoDays;
	}

	public void setAccoDays(String accoDays) {
		this.accoDays = accoDays;
	}

	public String getHolidays() {
		return holidays;
	}

	public void setHolidays(String holidays) {
		this.holidays = holidays;
	}

	public String getAltPartsDays() {
		return altPartsDays;
	}

	public void setAltPartsDays(String altPartsDays) {
		this.altPartsDays = altPartsDays;
	}

	public String getCurrCarDays() {
		return currCarDays;
	}

	public void setCurrCarDays(String currCarDays) {
		this.currCarDays = currCarDays;
	}

	public String getInitTaskId() {
		return initTaskId;
	}

	public void setInitTaskId(String initTaskId) {
		this.initTaskId = initTaskId;
	}

    
}