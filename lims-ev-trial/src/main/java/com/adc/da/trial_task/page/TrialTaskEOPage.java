package com.adc.da.trial_task.page;

import java.util.List;

import com.adc.da.base.page.BasePage;

import io.swagger.annotations.ApiModelProperty;

public class TrialTaskEOPage extends BasePage {
   /**
    * 通用查询条件
    */
    private List<String> searchPhrase;
    @ApiModelProperty("主键id")
    private String id;
    /**
     * 发动机试验任务书编号
     */
    @ApiModelProperty("发动机试验任务书编号")
    private String evNumber;
    /**
     * 任务名称
     */
    @ApiModelProperty("任务名称")
    private String title;
    
    /**
     * 目的
     */
    @ApiModelProperty("目的")
    private String purpose;
    /**
     * 试验状态
     */
    @ApiModelProperty("试验状态")
    private String trialStatus;
    /**
     * 发起人部门id
     */
    @ApiModelProperty("发起人部门id")
    private String createByDepartId;
    /**
     * 发起人部门名称
     */
    @ApiModelProperty("发起人部门名称")
    private String createByDepartName;
    /**
     * 发起人
     */
    @ApiModelProperty("发起人")
    private String createBy;
    /**
     * 发起人名称
     */
    @ApiModelProperty("发起人名称")
    private String createByName;
    
    /**
     * 当前待办人ID
     */
    @ApiModelProperty("当前待办人ID")
    private String currentWaitUserid;
    /**
     * 当前待办人名称
     */
    @ApiModelProperty("当前待办人名称")
    private String currentWaitUsername;
    /**
     * 计划完成延期天数
     */
    @ApiModelProperty("计划完成延期天数")
    private String finishdateDelydays;
    /**
     * 0:未延期   1：预警   2：延期
     */
    @ApiModelProperty("延期状态")
    private String delyFlag;
    
    /**
     * 0 未完成  1 已完成
     */
    @ApiModelProperty("试验整体状态")
    private String trialReportStatus;
    
    
    /**
     * actProcId 用于传流程图数据
     */
    private String actProcId;
    /**
     * baseBusId
     */
    private String baseBusId;
    /**
     * 用户id集合,用于过滤数据
     */
    private List<String> createByIds;
    
	
    
	public List<String> getCreateByIds() {
		return createByIds;
	}
	public void setCreateByIds(List<String> createByIds) {
		this.createByIds = createByIds;
	}
	public String getBaseBusId() {
		return baseBusId;
	}
	public void setBaseBusId(String baseBusId) {
		this.baseBusId = baseBusId;
	}
	public String getActProcId() {
		return actProcId;
	}
	public void setActProcId(String actProcId) {
		this.actProcId = actProcId;
	}
	public String getTrialReportStatus() {
		return trialReportStatus;
	}
	public void setTrialReportStatus(String trialReportStatus) {
		this.trialReportStatus = trialReportStatus;
	}
	public String getDelyFlag() {
		return delyFlag;
	}
	public void setDelyFlag(String delyFlag) {
		this.delyFlag = delyFlag;
	}
	public String getFinishdateDelydays() {
		return finishdateDelydays;
	}
	public void setFinishdateDelydays(String finishdateDelydays) {
		this.finishdateDelydays = finishdateDelydays;
	}
	public List<String> getSearchPhrase() {
		return searchPhrase;
	}
	public void setSearchPhrase(List<String> searchPhrase) {
		this.searchPhrase = searchPhrase;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEvNumber() {
		return evNumber;
	}
	public void setEvNumber(String evNumber) {
		this.evNumber = evNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getTrialStatus() {
		return trialStatus;
	}
	public void setTrialStatus(String trialStatus) {
		this.trialStatus = trialStatus;
	}
	public String getCreateByDepartId() {
		return createByDepartId;
	}
	public void setCreateByDepartId(String createByDepartId) {
		this.createByDepartId = createByDepartId;
	}
	public String getCreateByDepartName() {
		return createByDepartName;
	}
	public void setCreateByDepartName(String createByDepartName) {
		this.createByDepartName = createByDepartName;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateByName() {
		return createByName;
	}
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	public String getCurrentWaitUserid() {
		return currentWaitUserid;
	}
	public void setCurrentWaitUserid(String currentWaitUserid) {
		this.currentWaitUserid = currentWaitUserid;
	}
	public String getCurrentWaitUsername() {
		return currentWaitUsername;
	}
	public void setCurrentWaitUsername(String currentWaitUsername) {
		this.currentWaitUsername = currentWaitUsername;
	}
    
    
}
