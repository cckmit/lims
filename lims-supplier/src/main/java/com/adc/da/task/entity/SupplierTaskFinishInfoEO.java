package com.adc.da.task.entity;

import com.adc.da.base.entity.BaseEntity;


/**
 * <b>功能：</b>SUP_SUPPLIER_TASK_FINISH_INFO SupplierTaskFinishInfoEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-19 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SupplierTaskFinishInfoEO extends BaseEntity {

    private String id;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    private Integer delFlag;
    private String supTaskInfoId;
    private Integer whetherException;
    private String exceptionDescription;
    private Integer taskStatus;
    private String realPeopleNumber;
    private String realWorkHour;
    private String taskStartTime;
    private String taskEndTime;
    private String remark;
    private String attachmentId;
    /**
     * 实际人天   实际人数*实际工时
     */
    private String totalManDays;
    

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>createTime -> create_time</li>
     * <li>createBy -> create_by</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>delFlag -> del_flag</li>
     * <li>supTaskInfoId -> sup_task_info_id</li>
     * <li>whetherException -> whether_exception</li>
     * <li>exceptionDescription -> exception_description</li>
     * <li>taskStatus -> task_status</li>
     * <li>realPeopleNumber -> real_people_number</li>
     * <li>realWorkHour -> real_work_hour</li>
     * <li>taskStartTime -> task_start_time</li>
     * <li>taskEndTime -> task_end_time</li>
     * <li>remark -> remark</li>
     * <li>attachmentId -> attachment_id</li>
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
            case "supTaskInfoId":
                return "sup_task_info_id";
            case "whetherException":
                return "whether_exception";
            case "exceptionDescription":
                return "exception_description";
            case "taskStatus":
                return "task_status";
            case "realPeopleNumber":
                return "real_people_number";
            case "realWorkHour":
                return "real_work_hour";
            case "taskStartTime":
                return "task_start_time";
            case "taskEndTime":
                return "task_end_time";
            case "remark":
                return "remark";
            case "attachmentId":
                return "attachment_id";
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
     * <li>sup_task_info_id -> supTaskInfoId</li>
     * <li>whether_exception -> whetherException</li>
     * <li>exception_description -> exceptionDescription</li>
     * <li>task_status -> taskStatus</li>
     * <li>real_people_number -> realPeopleNumber</li>
     * <li>real_work_hour -> realWorkHour</li>
     * <li>task_start_time -> taskStartTime</li>
     * <li>task_end_time -> taskEndTime</li>
     * <li>remark -> remark</li>
     * <li>attachment_id -> attachmentId</li>
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
            case "sup_task_info_id":
                return "supTaskInfoId";
            case "whether_exception":
                return "whetherException";
            case "exception_description":
                return "exceptionDescription";
            case "task_status":
                return "taskStatus";
            case "real_people_number":
                return "realPeopleNumber";
            case "real_work_hour":
                return "realWorkHour";
            case "task_start_time":
                return "taskStartTime";
            case "task_end_time":
                return "taskEndTime";
            case "remark":
                return "remark";
            case "attachment_id":
                return "attachmentId";
            default:
                return null;
        }
    }

    
    
    
    public String getTotalManDays() {
		return totalManDays;
	}

	public void setTotalManDays(String totalManDays) {
		this.totalManDays = totalManDays;
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
    public String getSupTaskInfoId() {
        return this.supTaskInfoId;
    }

    /**  **/
    public void setSupTaskInfoId(String supTaskInfoId) {
        this.supTaskInfoId = supTaskInfoId;
    }

    /**  **/
    public Integer getWhetherException() {
        return this.whetherException;
    }

    /**  **/
    public void setWhetherException(Integer whetherException) {
        this.whetherException = whetherException;
    }

    /**  **/
    public String getExceptionDescription() {
        return this.exceptionDescription;
    }

    /**  **/
    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }

    /**  **/
    public Integer getTaskStatus() {
        return this.taskStatus;
    }

    /**  **/
    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**  **/
    public String getRealPeopleNumber() {
        return this.realPeopleNumber;
    }

    /**  **/
    public void setRealPeopleNumber(String realPeopleNumber) {
        this.realPeopleNumber = realPeopleNumber;
    }

    /**  **/
    public String getRealWorkHour() {
        return this.realWorkHour;
    }

    /**  **/
    public void setRealWorkHour(String realWorkHour) {
        this.realWorkHour = realWorkHour;
    }

    /**  **/
    public String getTaskStartTime() {
        return this.taskStartTime;
    }

    /**  **/
    public void setTaskStartTime(String taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    /**  **/
    public String getTaskEndTime() {
        return this.taskEndTime;
    }

    /**  **/
    public void setTaskEndTime(String taskEndTime) {
        this.taskEndTime = taskEndTime;
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
    public String getAttachmentId() {
        return this.attachmentId;
    }

    /**  **/
    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

}
