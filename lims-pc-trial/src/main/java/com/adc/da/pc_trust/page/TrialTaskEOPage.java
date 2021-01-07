package com.adc.da.pc_trust.page;

import com.adc.da.base.page.BasePage;

import java.util.List;


/**
 * <b>功能：</b>PC_TRIAL_TASK TrialTaskEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-17 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class TrialTaskEOPage extends BasePage {

    private String haveTempPlate;
    private String haveTempPlateOperator = "=";
    private String id;
    private String idOperator = "=";
    private String delFlag;
    private String delFlagOperator = "=";
    private String createTime;
    private String createTimeOperator = "=";
    private String createBy;
    private String createByOperator = "=";
    private String updateTime;
    private String updateTimeOperator = "=";
    private String updateBy;
    private String updateByOperator = "=";
    private String taskType;
    private String taskTypeOperator = "=";
    private String taskName;
    private String taskNameOperator = "=";
    private String taskCode;
    private String taskCodeOperator = "=";
    private String trustorOrgId;
    private String trustorOrgIdOperator = "=";
    private String trustorOrgName;
    private String trustorOrgNameOperator = "=";
    private String planFinishTime;
    private String planFinishTimeOperator = "=";
    private String according;
    private String accordingOperator = "=";
    private String urgency;
    private String urgencyOperator = "=";
    private String taskPurpose;
    private String taskPurposeOperator = "=";
    private String taskRequirement;
    private String taskRequirementOperator = "=";
    private String taskFileId;
    private String taskFileIdOperator = "=";
    private String taskFileName;
    private String taskFileNameOperator = "=";
    private String haveNotice;
    private String haveNoticeOperator = "=";
    private String dealPlateTimes;
    private String dealPlateTimesOperator = "=";
    private String carSeats;
    private String carSeatsOperator = "=";
    private String searchField;
    private String createByName;
    private List<String> searchPhrase;
    private String taskStatus;
    private String aOre;
    private String pvOrcv;

    /**
     * 用户id集合,用于过滤数据
     */
    private List<String> createByIds;
    /**
     * 用于过于试验人员
     */
    private String personIds;
    
    private String taskNumber;
    
    /**
     * 供应商司机ids
     */
    public String supPersonIds;
    
    
    public String getSupPersonIds() {
		return supPersonIds;
	}

	public void setSupPersonIds(String supPersonIds) {
		this.supPersonIds = supPersonIds;
	}
    
    
    public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public String getPersonIds() {
		return personIds;
	}

	public void setPersonIds(String personIds) {
		this.personIds = personIds;
	}

	public List<String> getCreateByIds() {
        return createByIds;
    }

    public void setCreateByIds(List<String> createByIds) {
        this.createByIds = createByIds;
    }

    public String getPvOrcv() {
        return pvOrcv;
    }

    public void setPvOrcv(String pvOrcv) {
        this.pvOrcv = pvOrcv;
    }

    public String getaOre() {
        return aOre;
    }

    public void setaOre(String aOre) {
        this.aOre = aOre;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public List<String> getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(List<String> searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public String getHaveTempPlate() {
        return this.haveTempPlate;
    }

    public void setHaveTempPlate(String haveTempPlate) {
        this.haveTempPlate = haveTempPlate;
    }

    public String getHaveTempPlateOperator() {
        return this.haveTempPlateOperator;
    }

    public void setHaveTempPlateOperator(String haveTempPlateOperator) {
        this.haveTempPlateOperator = haveTempPlateOperator;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOperator() {
        return this.idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlagOperator() {
        return this.delFlagOperator;
    }

    public void setDelFlagOperator(String delFlagOperator) {
        this.delFlagOperator = delFlagOperator;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeOperator() {
        return this.createTimeOperator;
    }

    public void setCreateTimeOperator(String createTimeOperator) {
        this.createTimeOperator = createTimeOperator;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateByOperator() {
        return this.createByOperator;
    }

    public void setCreateByOperator(String createByOperator) {
        this.createByOperator = createByOperator;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTimeOperator() {
        return this.updateTimeOperator;
    }

    public void setUpdateTimeOperator(String updateTimeOperator) {
        this.updateTimeOperator = updateTimeOperator;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateByOperator() {
        return this.updateByOperator;
    }

    public void setUpdateByOperator(String updateByOperator) {
        this.updateByOperator = updateByOperator;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskTypeOperator() {
        return this.taskTypeOperator;
    }

    public void setTaskTypeOperator(String taskTypeOperator) {
        this.taskTypeOperator = taskTypeOperator;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskNameOperator() {
        return this.taskNameOperator;
    }

    public void setTaskNameOperator(String taskNameOperator) {
        this.taskNameOperator = taskNameOperator;
    }

    public String getTaskCode() {
        return this.taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskCodeOperator() {
        return this.taskCodeOperator;
    }

    public void setTaskCodeOperator(String taskCodeOperator) {
        this.taskCodeOperator = taskCodeOperator;
    }

    public String getTrustorOrgId() {
        return this.trustorOrgId;
    }

    public void setTrustorOrgId(String trustorOrgId) {
        this.trustorOrgId = trustorOrgId;
    }

    public String getTrustorOrgIdOperator() {
        return this.trustorOrgIdOperator;
    }

    public void setTrustorOrgIdOperator(String trustorOrgIdOperator) {
        this.trustorOrgIdOperator = trustorOrgIdOperator;
    }

    public String getTrustorOrgName() {
        return this.trustorOrgName;
    }

    public void setTrustorOrgName(String trustorOrgName) {
        this.trustorOrgName = trustorOrgName;
    }

    public String getTrustorOrgNameOperator() {
        return this.trustorOrgNameOperator;
    }

    public void setTrustorOrgNameOperator(String trustorOrgNameOperator) {
        this.trustorOrgNameOperator = trustorOrgNameOperator;
    }

    public String getPlanFinishTime() {
        return this.planFinishTime;
    }

    public void setPlanFinishTime(String planFinishTime) {
        this.planFinishTime = planFinishTime;
    }

    public String getPlanFinishTimeOperator() {
        return this.planFinishTimeOperator;
    }

    public void setPlanFinishTimeOperator(String planFinishTimeOperator) {
        this.planFinishTimeOperator = planFinishTimeOperator;
    }

    public String getAccording() {
        return this.according;
    }

    public void setAccording(String according) {
        this.according = according;
    }

    public String getAccordingOperator() {
        return this.accordingOperator;
    }

    public void setAccordingOperator(String accordingOperator) {
        this.accordingOperator = accordingOperator;
    }

    public String getUrgency() {
        return this.urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getUrgencyOperator() {
        return this.urgencyOperator;
    }

    public void setUrgencyOperator(String urgencyOperator) {
        this.urgencyOperator = urgencyOperator;
    }

    public String getTaskPurpose() {
        return this.taskPurpose;
    }

    public void setTaskPurpose(String taskPurpose) {
        this.taskPurpose = taskPurpose;
    }

    public String getTaskPurposeOperator() {
        return this.taskPurposeOperator;
    }

    public void setTaskPurposeOperator(String taskPurposeOperator) {
        this.taskPurposeOperator = taskPurposeOperator;
    }

    public String getTaskRequirement() {
        return this.taskRequirement;
    }

    public void setTaskRequirement(String taskRequirement) {
        this.taskRequirement = taskRequirement;
    }

    public String getTaskRequirementOperator() {
        return this.taskRequirementOperator;
    }

    public void setTaskRequirementOperator(String taskRequirementOperator) {
        this.taskRequirementOperator = taskRequirementOperator;
    }

    public String getTaskFileId() {
        return this.taskFileId;
    }

    public void setTaskFileId(String taskFileId) {
        this.taskFileId = taskFileId;
    }

    public String getTaskFileIdOperator() {
        return this.taskFileIdOperator;
    }

    public void setTaskFileIdOperator(String taskFileIdOperator) {
        this.taskFileIdOperator = taskFileIdOperator;
    }

    public String getTaskFileName() {
        return this.taskFileName;
    }

    public void setTaskFileName(String taskFileName) {
        this.taskFileName = taskFileName;
    }

    public String getTaskFileNameOperator() {
        return this.taskFileNameOperator;
    }

    public void setTaskFileNameOperator(String taskFileNameOperator) {
        this.taskFileNameOperator = taskFileNameOperator;
    }

    public String getHaveNotice() {
        return this.haveNotice;
    }

    public void setHaveNotice(String haveNotice) {
        this.haveNotice = haveNotice;
    }

    public String getHaveNoticeOperator() {
        return this.haveNoticeOperator;
    }

    public void setHaveNoticeOperator(String haveNoticeOperator) {
        this.haveNoticeOperator = haveNoticeOperator;
    }

    public String getDealPlateTimes() {
        return this.dealPlateTimes;
    }

    public void setDealPlateTimes(String dealPlateTimes) {
        this.dealPlateTimes = dealPlateTimes;
    }

    public String getDealPlateTimesOperator() {
        return this.dealPlateTimesOperator;
    }

    public void setDealPlateTimesOperator(String dealPlateTimesOperator) {
        this.dealPlateTimesOperator = dealPlateTimesOperator;
    }

    public String getCarSeats() {
        return this.carSeats;
    }

    public void setCarSeats(String carSeats) {
        this.carSeats = carSeats;
    }

    public String getCarSeatsOperator() {
        return this.carSeatsOperator;
    }

    public void setCarSeatsOperator(String carSeatsOperator) {
        this.carSeatsOperator = carSeatsOperator;
    }

}
