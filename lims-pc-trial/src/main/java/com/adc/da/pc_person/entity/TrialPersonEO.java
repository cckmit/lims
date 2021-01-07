package com.adc.da.pc_person.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>PC_TRIAL_PERSON TrialPersonEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class TrialPersonEO extends BaseEntity {

    private String id;
    private String delFlag;
    private String createBy;
    private String createTime;
    private String updateTime;
    private String updateBy;
    @ApiModelProperty("人员id")
    private String personId;
    @ApiModelProperty("人员名称")
    private String personName;
    @ApiModelProperty("人员角色Id")
    private String personRoleId;
    @ApiModelProperty("角色名称")
    private String personRoleName;
    @ApiModelProperty("人员状态： 0：激活 1：禁止")
    private String personStatus;
    @ApiModelProperty("变更原因")
    private String changeReason;
    @ApiModelProperty("试验任务Id")
    private String trialTaskId;
    @ApiModelProperty("台架id")
    private String benchOrgId;
    @ApiModelProperty("台架名称")
    private String benchOrgName;
    @ApiModelProperty("样品id")
    private String sampleId;
    @ApiModelProperty("样品名称")
    private String sampleName;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>delFlag -> del_flag</li>
     * <li>createBy -> create_by</li>
     * <li>createTime -> create_time</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>personId -> person_id</li>
     * <li>personName -> person_name</li>
     * <li>personRoleId -> person_role_id</li>
     * <li>personRoleName -> person_role_name</li>
     * <li>personStatus -> person_status</li>
     * <li>changeReason -> change_reason</li>
     * <li>trialTaskId -> trial_task_id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "delFlag": return "del_flag";
            case "createBy": return "create_by";
            case "createTime": return "create_time";
            case "updateTime": return "update_time";
            case "updateBy": return "update_by";
            case "personId": return "person_id";
            case "personName": return "person_name";
            case "personRoleId": return "person_role_id";
            case "personRoleName": return "person_role_name";
            case "personStatus": return "person_status";
            case "changeReason": return "change_reason";
            case "trialTaskId": return "trial_task_id";
            case "benchOrgId" : return "bench_org_id";
            case "benchOrgName" : return "bench_org_name";
            case "sampleId" : return "sample_id";
            case "sampleName" : return "sample_name";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>del_flag -> delFlag</li>
     * <li>create_by -> createBy</li>
     * <li>create_time -> createTime</li>
     * <li>update_time -> updateTime</li>
     * <li>update_by -> updateBy</li>
     * <li>person_id -> personId</li>
     * <li>person_name -> personName</li>
     * <li>person_role_id -> personRoleId</li>
     * <li>person_role_name -> personRoleName</li>
     * <li>person_status -> personStatus</li>
     * <li>change_reason -> changeReason</li>
     * <li>trial_task_id -> trialTaskId</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "del_flag": return "delFlag";
            case "create_by": return "createBy";
            case "create_time": return "createTime";
            case "update_time": return "updateTime";
            case "update_by": return "updateBy";
            case "person_id": return "personId";
            case "person_name": return "personName";
            case "person_role_id": return "personRoleId";
            case "person_role_name": return "personRoleName";
            case "person_status": return "personStatus";
            case "change_reason": return "changeReason";
            case "trial_task_id": return "trialTaskId";
            case "bench_org_id": return "benchOrgId";
            case "bench_org_name": return "benchOrgName";
            case "sample_id": return "sampleId";
            case "sample_name": return "sampleName";
            default: return null;
        }
    }
    
    public String getBenchOrgId() {
		return benchOrgId;
	}

	public void setBenchOrgId(String benchOrgId) {
		this.benchOrgId = benchOrgId;
	}

	public String getBenchOrgName() {
		return benchOrgName;
	}

	public void setBenchOrgName(String benchOrgName) {
		this.benchOrgName = benchOrgName;
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
    public String getCreateBy() {
        return this.createBy;
    }

    /**  **/
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
    public String getPersonId() {
        return this.personId;
    }

    /**  **/
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    /**  **/
    public String getPersonName() {
        return this.personName;
    }

    /**  **/
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**  **/
    public String getPersonRoleId() {
        return this.personRoleId;
    }

    /**  **/
    public void setPersonRoleId(String personRoleId) {
        this.personRoleId = personRoleId;
    }

    /**  **/
    public String getPersonRoleName() {
        return this.personRoleName;
    }

    /**  **/
    public void setPersonRoleName(String personRoleName) {
        this.personRoleName = personRoleName;
    }

    /**  **/
    public String getPersonStatus() {
        return this.personStatus;
    }

    /**  **/
    public void setPersonStatus(String personStatus) {
        this.personStatus = personStatus;
    }

    /**  **/
    public String getChangeReason() {
        return this.changeReason;
    }

    /**  **/
    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    /**  **/
    public String getTrialTaskId() {
        return this.trialTaskId;
    }

    /**  **/
    public void setTrialTaskId(String trialTaskId) {
        this.trialTaskId = trialTaskId;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }
}
