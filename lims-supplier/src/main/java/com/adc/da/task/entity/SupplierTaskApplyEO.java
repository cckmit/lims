package com.adc.da.task.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>SUP_SUPPLIER_TASK_APPLY SupplierTaskApplyEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-19 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SupplierTaskApplyEO extends BaseEntity {

    private String id;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    private Integer delFlag;
    private String applyId;
    private String applyName;
    private String beApplyForId;
    private String applyTime;
    private String applyNo;
    private String taskSource;
    private String planStartTime;
    private String planEndTime;
    private Integer frequency;
    private String frequencyUnit;
    private Integer taskType;
    private Integer status;
    private String remark;
    private String idea;
    private String supplierPeopleId;
    private Integer whetherAccept;
    private String trialTaskId;

    @ApiModelProperty("1:乘用车   0：商用车")
    private Integer pvOrEv;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>createTime -> create_time</li>
     * <li>createBy -> create_by</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>delFlag -> del_flag</li>
     * <li>applyId -> apply_id</li>
     * <li>applyName -> apply_name</li>
     * <li>beApplyForId -> be_apply_for_id</li>
     * <li>applyTime -> apply_time</li>
     * <li>applyNo -> apply_no</li>
     * <li>taskSource -> task_source</li>
     * <li>planStartTime -> plan_start_time</li>
     * <li>planEndTime -> plan_end_time</li>
     * <li>frequency -> frequency</li>
     * <li>frequencyUnit -> frequency_unit</li>
     * <li>taskType -> task_type</li>
     * <li>status -> status</li>
     * <li>remark -> remark</li>
     * <li>idea -> idea</li>
     * <li>whetherAccept -> whether_accept</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) {
            return null;
        }
        switch (fieldName) {
            case "id":
                return "id";
            case "createTime":
                return "create_time";
            case "createBy":
                return "create_by";
            case "updateTime":
                return "update_time";
            case "updateBy":
                return "update_by";
            case "delFlag":
                return "del_flag";
            case "applyId":
                return "apply_id";
            case "applyName":
                return "apply_name";
            case "beApplyForId":
                return "be_apply_for_id";
            case "applyTime":
                return "apply_time";
            case "applyNo":
                return "apply_no";
            case "taskSource":
                return "task_source";
            case "planStartTime":
                return "plan_start_time";
            case "planEndTime":
                return "plan_end_time";
            case "frequency":
                return "frequency";
            case "frequencyUnit":
                return "frequency_unit";
            case "taskType":
                return "task_type";
            case "status":
                return "status";
            case "remark":
                return "remark";
            case "idea":
                return "idea";
            case "whetherAccept":
                return "whether_accept";
            default:
                return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>create_time -> createTime</li>
     * <li>create_by -> createBy</li>
     * <li>update_time -> updateTime</li>
     * <li>update_by -> updateBy</li>
     * <li>del_flag -> delFlag</li>
     * <li>apply_id -> applyId</li>
     * <li>apply_name -> applyName</li>
     * <li>be_apply_for_id -> beApplyForId</li>
     * <li>apply_time -> applyTime</li>
     * <li>apply_no -> applyNo</li>
     * <li>task_source -> taskSource</li>
     * <li>plan_start_time -> planStartTime</li>
     * <li>plan_end_time -> planEndTime</li>
     * <li>frequency -> frequency</li>
     * <li>frequency_unit -> frequencyUnit</li>
     * <li>task_type -> taskType</li>
     * <li>status -> status</li>
     * <li>remark -> remark</li>
     * <li>idea -> idea</li>
     * <li>whether_accept -> whetherAccept</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) {
            return null;
        }
        switch (columnName) {
            case "id":
                return "id";
            case "create_time":
                return "createTime";
            case "create_by":
                return "createBy";
            case "update_time":
                return "updateTime";
            case "update_by":
                return "updateBy";
            case "del_flag":
                return "delFlag";
            case "apply_id":
                return "applyId";
            case "apply_name":
                return "applyName";
            case "be_apply_for_id":
                return "beApplyForId";
            case "apply_time":
                return "applyTime";
            case "apply_no":
                return "applyNo";
            case "task_source":
                return "taskSource";
            case "plan_start_time":
                return "planStartTime";
            case "plan_end_time":
                return "planEndTime";
            case "frequency":
                return "frequency";
            case "frequency_unit":
                return "frequencyUnit";
            case "task_type":
                return "taskType";
            case "status":
                return "status";
            case "remark":
                return "remark";
            case "idea":
                return "idea";
            case "whether_accept":
                return "whetherAccept";
            default:
                return null;
        }
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
    public Integer getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**  **/
    public String getApplyId() {
        return this.applyId;
    }

    /**  **/
    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    /**  **/
    public String getApplyName() {
        return this.applyName;
    }

    /**  **/
    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    /**  **/
    public String getBeApplyForId() {
        return this.beApplyForId;
    }

    /**  **/
    public void setBeApplyForId(String beApplyForId) {
        this.beApplyForId = beApplyForId;
    }

    /**  **/
    public String getApplyTime() {
        return this.applyTime;
    }

    /**  **/
    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    /**  **/
    public String getApplyNo() {
        return this.applyNo;
    }

    /**  **/
    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    /**  **/
    public String getTaskSource() {
        return this.taskSource;
    }

    /**  **/
    public void setTaskSource(String taskSource) {
        this.taskSource = taskSource;
    }

    /**  **/
    public String getPlanStartTime() {
        return this.planStartTime;
    }

    /**  **/
    public void setPlanStartTime(String planStartTime) {
        this.planStartTime = planStartTime;
    }

    /**  **/
    public String getPlanEndTime() {
        return this.planEndTime;
    }

    /**  **/
    public void setPlanEndTime(String planEndTime) {
        this.planEndTime = planEndTime;
    }

    /**  **/
    public Integer getFrequency() {
        return this.frequency;
    }

    /**  **/
    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    /**  **/
    public String getFrequencyUnit() {
        return this.frequencyUnit;
    }

    /**  **/
    public void setFrequencyUnit(String frequencyUnit) {
        this.frequencyUnit = frequencyUnit;
    }

    /**  **/
    public Integer getTaskType() {
        return this.taskType;
    }

    /**  **/
    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    /**  **/
    public Integer getStatus() {
        return this.status;
    }

    /**  **/
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**  **/
    public String getRemark() {
        return this.remark;
    }

    /**  **/
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**  **/
    public String getIdea() {
        return this.idea;
    }

    /**  **/
    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getSupplierPeopleId() {
        return supplierPeopleId;
    }

    public void setSupplierPeopleId(String supplierPeopleId) {
        this.supplierPeopleId = supplierPeopleId;
    }

    /**  **/
    public Integer getWhetherAccept() {
        return this.whetherAccept;
    }

    /**  **/
    public void setWhetherAccept(Integer whetherAccept) {
        this.whetherAccept = whetherAccept;
    }

    public String getTrialTaskId() {
        return trialTaskId;
    }

    public void setTrialTaskId(String trialTaskId) {
        this.trialTaskId = trialTaskId;
    }

    public Integer getPvOrEv() {
        return pvOrEv;
    }

    public void setPvOrEv(Integer pvOrEv) {
        this.pvOrEv = pvOrEv;
    }
}
