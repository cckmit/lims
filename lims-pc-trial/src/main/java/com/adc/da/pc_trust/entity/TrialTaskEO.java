package com.adc.da.pc_trust.entity;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.pc_items.entity.TrialItemsEO;
import com.adc.da.pc_person.entity.TrialPersonEO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * <b>功能：</b>PC_TRIAL_TASK TrialTaskEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-17 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class TrialTaskEO extends BaseEntity {

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
    @ApiModelProperty("试验任务编号")
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
    @ApiModelProperty("")
    private String taskNumber;
    /**
     * 试验任务申请状态
     * 1-审批中
     * 2-已审批
     * 3-退回
     */
    @ApiModelProperty("试验任务申请状态")
    private String initStatus;
    
    /**
     * 用来存放PV/CV试验变更流程businessKey
     */
    private String businessKey;

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

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>haveTempPlate -> have_temp_plate</li>
     * <li>id -> id</li>
     * <li>delFlag -> del_flag</li>
     * <li>createTime -> create_time</li>
     * <li>createBy -> create_by</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>taskType -> task_type</li>
     * <li>taskName -> task_name</li>
     * <li>taskCode -> task_code</li>
     * <li>trustorOrgId -> trustor_org_id</li>
     * <li>trustorOrgName -> trustor_org_name</li>
     * <li>planFinishTime -> plan_finish_time</li>
     * <li>according -> according</li>
     * <li>urgency -> urgency</li>
     * <li>taskPurpose -> task_purpose</li>
     * <li>taskRequirement -> task_requirement</li>
     * <li>taskFileId -> task_file_id</li>
     * <li>taskFileName -> task_file_name</li>
     * <li>haveNotice -> have_notice</li>
     * <li>dealPlateTimes -> deal_plate_times</li>
     * <li>carSeats -> car_seats</li>
     * <li>taskVersion -> task_version</li>
     * <li>taskNumber -> task_number</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "haveTempPlate": return "have_temp_plate";
            case "id": return "id";
            case "delFlag": return "del_flag";
            case "createTime": return "create_time";
            case "createBy": return "create_by";
            case "updateTime": return "update_time";
            case "updateBy": return "update_by";
            case "taskType": return "task_type";
            case "taskName": return "task_name";
            case "taskCode": return "task_code";
            case "trustorOrgId": return "trustor_org_id";
            case "trustorOrgName": return "trustor_org_name";
            case "planFinishTime": return "plan_finish_time";
            case "according": return "according";
            case "urgency": return "urgency";
            case "taskPurpose": return "task_purpose";
            case "taskRequirement": return "task_requirement";
            case "taskFileId": return "task_file_id";
            case "taskFileName": return "task_file_name";
            case "haveNotice": return "have_notice";
            case "dealPlateTimes": return "deal_plate_times";
            case "carSeats": return "car_seats";
            case "taskVersion": return "task_version";
            case "taskNumber": return "task_number";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>have_temp_plate -> haveTempPlate</li>
     * <li>id -> id</li>
     * <li>del_flag -> delFlag</li>
     * <li>create_time -> createTime</li>
     * <li>create_by -> createBy</li>
     * <li>update_time -> updateTime</li>
     * <li>update_by -> updateBy</li>
     * <li>task_type -> taskType</li>
     * <li>task_name -> taskName</li>
     * <li>task_code -> taskCode</li>
     * <li>trustor_org_id -> trustorOrgId</li>
     * <li>trustor_org_name -> trustorOrgName</li>
     * <li>plan_finish_time -> planFinishTime</li>
     * <li>according -> according</li>
     * <li>urgency -> urgency</li>
     * <li>task_purpose -> taskPurpose</li>
     * <li>task_requirement -> taskRequirement</li>
     * <li>task_file_id -> taskFileId</li>
     * <li>task_file_name -> taskFileName</li>
     * <li>have_notice -> haveNotice</li>
     * <li>deal_plate_times -> dealPlateTimes</li>
     * <li>car_seats -> carSeats</li>
     * <li>task_version -> taskVersion</li>
     * <li>task_number -> taskNumber</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "have_temp_plate": return "haveTempPlate";
            case "id": return "id";
            case "del_flag": return "delFlag";
            case "create_time": return "createTime";
            case "create_by": return "createBy";
            case "update_time": return "updateTime";
            case "update_by": return "updateBy";
            case "task_type": return "taskType";
            case "task_name": return "taskName";
            case "task_code": return "taskCode";
            case "trustor_org_id": return "trustorOrgId";
            case "trustor_org_name": return "trustorOrgName";
            case "plan_finish_time": return "planFinishTime";
            case "according": return "according";
            case "urgency": return "urgency";
            case "task_purpose": return "taskPurpose";
            case "task_requirement": return "taskRequirement";
            case "task_file_id": return "taskFileId";
            case "task_file_name": return "taskFileName";
            case "have_notice": return "haveNotice";
            case "deal_plate_times": return "dealPlateTimes";
            case "car_seats": return "carSeats";
            case "task_version": return "taskVersion";
            case "task_number": return "taskNumber";
            default: return null;
        }
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

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
    
    
}
