package com.adc.da.pc_drive.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>PC_DRIVING_RECORD PcDrivingRecordEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcDrivingRecordEO extends BaseEntity {

    private String id;
    private String delFlag;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    @ApiModelProperty("日期")
    private String drivingDate;
    @ApiModelProperty("实验道路类型")
    private String lineType;
    @ApiModelProperty("开始地点")
    private String startPlace;
    @ApiModelProperty("结束地点")
    private String endPlace;
    @ApiModelProperty("开始里程")
    private String startMileage;
    @ApiModelProperty("结束里程")
    private String endMileage;
    @ApiModelProperty("单天行驶里程")
    private String singleMileage;
    @ApiModelProperty("实际累积里程")
    private String totalMileage;
    @ApiModelProperty("驾驶员")
    private String driver;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("立项单id")
    private String initTaskId;
    @ApiModelProperty("试验任务id")
    private String trialTaskId;
    @ApiModelProperty("试验任务编号")
    private String taskNumber;



    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>delFlag -> del_flag</li>
     * <li>createTime -> create_time</li>
     * <li>createBy -> create_by</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>drivingDate -> driving_date</li>
     * <li>lineType -> line_type</li>
     * <li>startPlace -> start_place</li>
     * <li>endPlace -> end_place</li>
     * <li>startMileage -> start_mileage</li>
     * <li>endMileage -> end_mileage</li>
     * <li>singleMileage -> single_mileage</li>
     * <li>totalMileage -> total_mileage</li>
     * <li>driver -> driver</li>
     * <li>remark -> remark</li>
     * <li>initTaskId -> init_task_id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "delFlag": return "del_flag";
            case "createTime": return "create_time";
            case "createBy": return "create_by";
            case "updateTime": return "update_time";
            case "updateBy": return "update_by";
            case "drivingDate": return "driving_date";
            case "lineType": return "line_type";
            case "startPlace": return "start_place";
            case "endPlace": return "end_place";
            case "startMileage": return "start_mileage";
            case "endMileage": return "end_mileage";
            case "singleMileage": return "single_mileage";
            case "totalMileage": return "total_mileage";
            case "driver": return "driver";
            case "remark": return "remark";
            case "initTaskId": return "init_task_id";
            case "trialTaskId": return "trial_task_id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>del_flag -> delFlag</li>
     * <li>create_time -> createTime</li>
     * <li>create_by -> createBy</li>
     * <li>update_time -> updateTime</li>
     * <li>update_by -> updateBy</li>
     * <li>driving_date -> drivingDate</li>
     * <li>line_type -> lineType</li>
     * <li>start_place -> startPlace</li>
     * <li>end_place -> endPlace</li>
     * <li>start_mileage -> startMileage</li>
     * <li>end_mileage -> endMileage</li>
     * <li>single_mileage -> singleMileage</li>
     * <li>total_mileage -> totalMileage</li>
     * <li>driver -> driver</li>
     * <li>remark -> remark</li>
     * <li>init_task_id -> initTaskId</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "del_flag": return "delFlag";
            case "create_time": return "createTime";
            case "create_by": return "createBy";
            case "update_time": return "updateTime";
            case "update_by": return "updateBy";
            case "driving_date": return "drivingDate";
            case "line_type": return "lineType";
            case "start_place": return "startPlace";
            case "end_place": return "endPlace";
            case "start_mileage": return "startMileage";
            case "end_mileage": return "endMileage";
            case "single_mileage": return "singleMileage";
            case "total_mileage": return "totalMileage";
            case "driver": return "driver";
            case "remark": return "remark";
            case "init_task_id": return "initTaskId";
            case "trial_task_id":
                return "trialTaskId";
            default: return null;
        }
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
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
    public String getDrivingDate() {
        return this.drivingDate;
    }

    /**  **/
    public void setDrivingDate(String drivingDate) {
        this.drivingDate = drivingDate;
    }

    /**  **/
    public String getLineType() {
        return this.lineType;
    }

    /**  **/
    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    /**  **/
    public String getStartPlace() {
        return this.startPlace;
    }

    /**  **/
    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    /**  **/
    public String getEndPlace() {
        return this.endPlace;
    }

    /**  **/
    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    /**  **/
    public String getStartMileage() {
        return this.startMileage;
    }

    /**  **/
    public void setStartMileage(String startMileage) {
        this.startMileage = startMileage;
    }

    /**  **/
    public String getEndMileage() {
        return this.endMileage;
    }

    /**  **/
    public void setEndMileage(String endMileage) {
        this.endMileage = endMileage;
    }

    /**  **/
    public String getSingleMileage() {
        return this.singleMileage;
    }

    /**  **/
    public void setSingleMileage(String singleMileage) {
        this.singleMileage = singleMileage;
    }

    /**  **/
    public String getTotalMileage() {
        return this.totalMileage;
    }

    /**  **/
    public void setTotalMileage(String totalMileage) {
        this.totalMileage = totalMileage;
    }

    /**  **/
    public String getDriver() {
        return this.driver;
    }

    /**  **/
    public void setDriver(String driver) {
        this.driver = driver;
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
    public String getInitTaskId() {
        return this.initTaskId;
    }

    /**  **/
    public void setInitTaskId(String initTaskId) {
        this.initTaskId = initTaskId;
    }

    public String getTrialTaskId() {
        return trialTaskId;
    }

    public void setTrialTaskId(String trialTaskId) {
        this.trialTaskId = trialTaskId;
    }
}
