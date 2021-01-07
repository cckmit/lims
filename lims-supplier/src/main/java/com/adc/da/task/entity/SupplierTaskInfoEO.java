package com.adc.da.task.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>SUP_SUPPLIER_TASK_INFO SupplierTaskInfoEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-27 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SupplierTaskInfoEO extends BaseEntity {

    private String id;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    private Integer delFlag;
    private String supApplyId;
    private String saCarId;
    private String taskImplementerId;
    private String taskImplementerName;
    private String type;
    private String project;
    private String jobContent;
    private String peopleNumber;
    private String workHour;
    private String workHourUnit;
    private String outputContent;
    private Integer taskStatus;
    private String applyNo;

    @ApiModelProperty("领料单编号")
    private String pcPartNo;
    @ApiModelProperty("单价")
    private String unitPrice;
    
    
    public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>createTime -> create_time</li>
     * <li>createBy -> create_by</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>delFlag -> del_flag</li>
     * <li>supApplyId -> sup_apply_id</li>
     * <li>saCarId -> sa_car_id</li>
     * <li>taskImplementerId -> task_implementer_id</li>
     * <li>type -> type</li>
     * <li>project -> project</li>
     * <li>jobContent -> job_content</li>
     * <li>peopleNumber -> people_number</li>
     * <li>workHour -> work_hour</li>
     * <li>workHourUnit -> work_hour_unit</li>
     * <li>outputContent -> output_content</li>
     * <li>taskStatus -> task_status</li>
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
            case "supApplyId":
                return "sup_apply_id";
            case "saCarId":
                return "sa_car_id";
            case "taskImplementerId":
                return "task_implementer_id";
            case "type":
                return "type";
            case "project":
                return "project";
            case "jobContent":
                return "job_content";
            case "peopleNumber":
                return "people_number";
            case "workHour":
                return "work_hour";
            case "workHourUnit":
                return "work_hour_unit";
            case "outputContent":
                return "output_content";
            case "taskStatus":
                return "task_status";
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
     * <li>sup_apply_id -> supApplyId</li>
     * <li>sa_car_id -> saCarId</li>
     * <li>task_implementer_id -> taskImplementerId</li>
     * <li>type -> type</li>
     * <li>project -> project</li>
     * <li>job_content -> jobContent</li>
     * <li>people_number -> peopleNumber</li>
     * <li>work_hour -> workHour</li>
     * <li>work_hour_unit -> workHourUnit</li>
     * <li>output_content -> outputContent</li>
     * <li>task_status -> taskStatus</li>
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
            case "sup_apply_id":
                return "supApplyId";
            case "sa_car_id":
                return "saCarId";
            case "task_implementer_id":
                return "taskImplementerId";
            case "type":
                return "type";
            case "project":
                return "project";
            case "job_content":
                return "jobContent";
            case "people_number":
                return "peopleNumber";
            case "work_hour":
                return "workHour";
            case "work_hour_unit":
                return "workHourUnit";
            case "output_content":
                return "outputContent";
            case "task_status":
                return "taskStatus";
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
    public String getSupApplyId() {
        return this.supApplyId;
    }

    /**  **/
    public void setSupApplyId(String supApplyId) {
        this.supApplyId = supApplyId;
    }

    /**  **/
    public String getSaCarId() {
        return this.saCarId;
    }

    /**  **/
    public void setSaCarId(String saCarId) {
        this.saCarId = saCarId;
    }

    /**  **/
    public String getTaskImplementerId() {
        return this.taskImplementerId;
    }

    /**  **/
    public void setTaskImplementerId(String taskImplementerId) {
        this.taskImplementerId = taskImplementerId;
    }

    /**  **/
    public String getType() {
        return this.type;
    }

    /**  **/
    public void setType(String type) {
        this.type = type;
    }

    /**  **/
    public String getProject() {
        return this.project;
    }

    /**  **/
    public void setProject(String project) {
        this.project = project;
    }

    /**  **/
    public String getJobContent() {
        return this.jobContent;
    }

    /**  **/
    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    /**  **/
    public String getPeopleNumber() {
        return this.peopleNumber;
    }

    /**  **/
    public void setPeopleNumber(String peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    /**  **/
    public String getWorkHour() {
        return this.workHour;
    }

    /**  **/
    public void setWorkHour(String workHour) {
        this.workHour = workHour;
    }

    /**  **/
    public String getWorkHourUnit() {
        return this.workHourUnit;
    }

    /**  **/
    public void setWorkHourUnit(String workHourUnit) {
        this.workHourUnit = workHourUnit;
    }

    /**  **/
    public String getOutputContent() {
        return this.outputContent;
    }

    /**  **/
    public void setOutputContent(String outputContent) {
        this.outputContent = outputContent;
    }

    /**  **/
    public Integer getTaskStatus() {
        return this.taskStatus;
    }

    /**  **/
    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getPcPartNo() {
        return pcPartNo;
    }

    public void setPcPartNo(String pcPartNo) {
        this.pcPartNo = pcPartNo;
    }

    public String getTaskImplementerName() {
        return taskImplementerName;
    }

    public void setTaskImplementerName(String taskImplementerName) {
        this.taskImplementerName = taskImplementerName;
    }
}
