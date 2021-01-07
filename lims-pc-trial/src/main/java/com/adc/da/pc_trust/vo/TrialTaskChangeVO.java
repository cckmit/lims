package com.adc.da.pc_trust.vo;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.pc_items.entity.TrialItemsEO;
import com.adc.da.pc_person.entity.TrialPersonEO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

public class TrialTaskChangeVO extends BaseEntity {

    @ApiModelProperty("是否获得临牌")
    private String haveTempPlate;
    private String id;
    @ApiModelProperty("删除状态")
    private String delFlag = "0";
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    @ApiModelProperty("试验任务类型 ：0：可靠性 1：性能 2：公告试验")
    private String taskType;
    @ApiModelProperty("任务名称")
    private String taskName;
    @ApiModelProperty("任务编号")
    private String taskCode;
    @ApiModelProperty("执行单位")
    private String trustorOrgId;
    @ApiModelProperty("执行单位名称")
    private String trustorOrgName;
    @ApiModelProperty("计划完成时间")
    private String planFinishTime;
    @ApiModelProperty("试验依据")
    private String according;
    @ApiModelProperty("紧急程度")
    private String urgency;
    @ApiModelProperty("实验目的")
    private String taskPurpose;
    @ApiModelProperty("实验要求")
    private String taskRequirement;
    @ApiModelProperty("试验任务书附件")
    private String taskFileId;
    @ApiModelProperty("试验任务书名称")
    private String taskFileName;
    @ApiModelProperty("是否获得公告")
    private String haveNotice;
    @ApiModelProperty("试验车辆次数")
    private String dealPlateTimes;
    @ApiModelProperty("座位数")
    private String carSeats;
    @ApiModelProperty("任务状态")
    private String taskStatus;
    private String createByName;
    @ApiModelProperty("延期状态")
    private String delyType = "0";
    @ApiModelProperty("延期天数")
    private Integer delyDays;
    private String pvOrcv;
    @ApiModelProperty("多附件ID")
    private String atId;
    @ApiModelProperty("多附件名字")
    private String atName;
    @ApiModelProperty("试验人员ids")
    private String personIds;

    @ApiModelProperty("试验任务版本号")
    private Integer taskVersion;
    @ApiModelProperty("试验任务编号")
    private String taskNumber;
    /**
     * 试验任务申请状态
     * 1-审批中
     * 2-已审批
     * 3-退回
     */
    @ApiModelProperty("试验任务申请状态")
    private String initStatus;
    @ApiModelProperty("主管id")
    private String executive;

    public String getExecutive() {
        return executive;
    }

    public void setExecutive(String executive) {
        this.executive = executive;
    }

    public String getPersonIds() {
        return personIds;
    }

    public void setPersonIds(String personIds) {
        this.personIds = personIds;
    }

    public String getAtId() {
        return atId;
    }

    public void setAtId(String atId) {
        this.atId = atId;
    }

    public String getAtName() {
        return atName;
    }

    public void setAtName(String atName) {
        this.atName = atName;
    }

    public String getPvOrcv() {
        return pvOrcv;
    }

    public void setPvOrcv(String pvOrcv) {
        this.pvOrcv = pvOrcv;
    }

    private List<TrialItemsEO> trialItemsEOList;

    private List<TrialPersonEO> trialPersonEOList;

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getDelyType() {
        return delyType;
    }

    public void setDelyType(String delyType) {
        this.delyType = delyType;
    }

    public Integer getDelyDays() {
        return delyDays;
    }

    public void setDelyDays(Integer delyDays) {
        this.delyDays = delyDays;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public List<TrialItemsEO> getTrialItemsEOList() {
        return trialItemsEOList;
    }

    public void setTrialItemsEOList(List<TrialItemsEO> trialItemsEOList) {
        this.trialItemsEOList = trialItemsEOList;
    }

    public List<TrialPersonEO> getTrialPersonEOList() {
        return trialPersonEOList;
    }

    public void setTrialPersonEOList(List<TrialPersonEO> trialPersonEOList) {
        this.trialPersonEOList = trialPersonEOList;
    }

    public Integer getTaskVersion() {
        return taskVersion;
    }

    public void setTaskVersion(Integer taskVersion) {
        this.taskVersion = taskVersion;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**  **/
    public String getHaveTempPlate() {
        return this.haveTempPlate;
    }

    /**  **/
    public void setHaveTempPlate(String haveTempPlate) {
        this.haveTempPlate = haveTempPlate;
    }

    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /**  **/
    public String getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**  **/
    public String getCreateTime() {
        return this.createTime;
    }

    /**  **/
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**  **/
    public String getCreateBy() {
        return this.createBy;
    }

    /**  **/
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**  **/
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**  **/
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**  **/
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**  **/
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**  **/
    public String getTaskType() {
        return this.taskType;
    }

    /**  **/
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    /**  **/
    public String getTaskName() {
        return this.taskName;
    }

    /**  **/
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**  **/
    public String getTaskCode() {
        return this.taskCode;
    }

    /**  **/
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    /**  **/
    public String getTrustorOrgId() {
        return this.trustorOrgId;
    }

    /**  **/
    public void setTrustorOrgId(String trustorOrgId) {
        this.trustorOrgId = trustorOrgId;
    }

    /**  **/
    public String getTrustorOrgName() {
        return this.trustorOrgName;
    }

    /**  **/
    public void setTrustorOrgName(String trustorOrgName) {
        this.trustorOrgName = trustorOrgName;
    }

    /**  **/
    public String getPlanFinishTime() {
        return this.planFinishTime;
    }

    /**  **/
    public void setPlanFinishTime(String planFinishTime) {
        this.planFinishTime = planFinishTime;
    }

    /**  **/
    public String getAccording() {
        return this.according;
    }

    /**  **/
    public void setAccording(String according) {
        this.according = according;
    }

    /**  **/
    public String getUrgency() {
        return this.urgency;
    }

    /**  **/
    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    /**  **/
    public String getTaskPurpose() {
        return this.taskPurpose;
    }

    /**  **/
    public void setTaskPurpose(String taskPurpose) {
        this.taskPurpose = taskPurpose;
    }

    /**  **/
    public String getTaskRequirement() {
        return this.taskRequirement;
    }

    /**  **/
    public void setTaskRequirement(String taskRequirement) {
        this.taskRequirement = taskRequirement;
    }

    /**  **/
    public String getTaskFileId() {
        return this.taskFileId;
    }

    /**  **/
    public void setTaskFileId(String taskFileId) {
        this.taskFileId = taskFileId;
    }

    /**  **/
    public String getTaskFileName() {
        return this.taskFileName;
    }

    /**  **/
    public void setTaskFileName(String taskFileName) {
        this.taskFileName = taskFileName;
    }

    /**  **/
    public String getHaveNotice() {
        return this.haveNotice;
    }

    /**  **/
    public void setHaveNotice(String haveNotice) {
        this.haveNotice = haveNotice;
    }

    /**  **/
    public String getDealPlateTimes() {
        return this.dealPlateTimes;
    }

    /**  **/
    public void setDealPlateTimes(String dealPlateTimes) {
        this.dealPlateTimes = dealPlateTimes;
    }

    /**  **/
    public String getCarSeats() {
        return this.carSeats;
    }

    /**  **/
    public void setCarSeats(String carSeats) {
        this.carSeats = carSeats;
    }

    public String getInitStatus() {
        return initStatus;
    }

    public void setInitStatus(String initStatus) {
        this.initStatus = initStatus;
    }

}
