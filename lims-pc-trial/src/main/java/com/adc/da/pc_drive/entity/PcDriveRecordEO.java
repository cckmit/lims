package com.adc.da.pc_drive.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>功能：</b>PC_DRIVE_RECORD PcDriveRecordEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2020-07-29 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcDriveRecordEO extends BaseEntity {

    @ApiModelProperty("实际路试总里程（km）")
    private String realRoadTestMil;
    @ApiModelProperty("实际路送总里程（km）")
    private String realRoadMil;
    @ApiModelProperty("计划路试总里程（km）")
    private String planRoadTestMil;
    @ApiModelProperty("计划路送总里程（km）")
    private String planRoadMil;
    @ApiModelProperty("结束时里程表读数（km）")
    private String endMilNumber;
    @ApiModelProperty("开始时里程表读数（km）")
    private String startMilNumber;
    @ApiModelProperty("委托单承接相关方")
    private String entrustRelated;
    @ApiModelProperty("")
    private String taskNumber;
    @ApiModelProperty("")
    private String updateBy;
    @ApiModelProperty("")
    private String updateTime;
    @ApiModelProperty("")
    private String createBy;//创建人ID
    @ApiModelProperty("")
    private String createTime;
    @ApiModelProperty("")
    private String delFlag;
    @ApiModelProperty("")
    private String id;
    //任务书编号
    private String taskCode;
    private String vehicleType;//车型
    private String chassisCode;//底盘号
    private String driverId;//司机ID，多个，用逗号隔开
    private String trialId;//任务ID，变更会发生变化
    private String taskOrPlan; //1是执行计划，0是pv/cv认证
    private String driveState;//保存提交状态 保存是0 提交是1
    private String horsePower;//马力
    private String carId;//车的Id
    private String trialProjectId;//路送路试委托单ID
    private List<RoadLineEO> roadLineEOS = new ArrayList<>();
    private List<RoadLineRealityEO> realityEOS = new ArrayList<>();

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>realRoadTestMil -> real_road_test_mil</li>
     * <li>realRoadMil -> real_road_mil</li>
     * <li>planRoadTestMil -> plan_road_test_mil</li>
     * <li>planRoadMil -> plan_road_mil</li>
     * <li>endMilNumber -> end_mil_number</li>
     * <li>startMilNumber -> start_mil_number</li>
     * <li>entrustRelated -> entrust_related</li>
     * <li>taskNumber -> task_number</li>
     * <li>updateBy -> update_by</li>
     * <li>updateTime -> update_time</li>
     * <li>createBy -> create_by</li>
     * <li>createTime -> create_time</li>
     * <li>delFlag -> del_flag</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) { return null; }
        switch (fieldName) {
            case "realRoadTestMil": return "real_road_test_mil";
            case "realRoadMil": return "real_road_mil";
            case "planRoadTestMil": return "plan_road_test_mil";
            case "planRoadMil": return "plan_road_mil";
            case "endMilNumber": return "end_mil_number";
            case "startMilNumber": return "start_mil_number";
            case "entrustRelated": return "entrust_related";
            case "taskNumber": return "task_number";
            case "updateBy": return "update_by";
            case "updateTime": return "update_time";
            case "createBy": return "create_by";
            case "createTime": return "create_time";
            case "delFlag": return "del_flag";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>real_road_test_mil -> realRoadTestMil</li>
     * <li>real_road_mil -> realRoadMil</li>
     * <li>plan_road_test_mil -> planRoadTestMil</li>
     * <li>plan_road_mil -> planRoadMil</li>
     * <li>end_mil_number -> endMilNumber</li>
     * <li>start_mil_number -> startMilNumber</li>
     * <li>entrust_related -> entrustRelated</li>
     * <li>task_number -> taskNumber</li>
     * <li>update_by -> updateBy</li>
     * <li>update_time -> updateTime</li>
     * <li>create_by -> createBy</li>
     * <li>create_time -> createTime</li>
     * <li>del_flag -> delFlag</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) { return null; }
        switch (columnName) {
            case "real_road_test_mil": return "realRoadTestMil";
            case "real_road_mil": return "realRoadMil";
            case "plan_road_test_mil": return "planRoadTestMil";
            case "plan_road_mil": return "planRoadMil";
            case "end_mil_number": return "endMilNumber";
            case "start_mil_number": return "startMilNumber";
            case "entrust_related": return "entrustRelated";
            case "task_number": return "taskNumber";
            case "update_by": return "updateBy";
            case "update_time": return "updateTime";
            case "create_by": return "createBy";
            case "create_time": return "createTime";
            case "del_flag": return "delFlag";
            case "id": return "id";
            default: return null;
        }
    }

    public List<RoadLineRealityEO> getRealityEOS() {
        return realityEOS;
    }

    public void setRealityEOS(List<RoadLineRealityEO> realityEOS) {
        this.realityEOS = realityEOS;
    }

    public String getRealRoadTestMil() {
        return this.realRoadTestMil;
    }

    public void setRealRoadTestMil(String realRoadTestMil) {
        this.realRoadTestMil = realRoadTestMil;
    }

    public String getRealRoadMil() {
        return this.realRoadMil;
    }

    public void setRealRoadMil(String realRoadMil) {
        this.realRoadMil = realRoadMil;
    }

    public String getPlanRoadTestMil() {
        return this.planRoadTestMil;
    }

    public void setPlanRoadTestMil(String planRoadTestMil) {
        this.planRoadTestMil = planRoadTestMil;
    }

    public String getPlanRoadMil() {
        return this.planRoadMil;
    }

    public void setPlanRoadMil(String planRoadMil) {
        this.planRoadMil = planRoadMil;
    }

    public String getEndMilNumber() {
        return this.endMilNumber;
    }

    public void setEndMilNumber(String endMilNumber) {
        this.endMilNumber = endMilNumber;
    }

    public String getStartMilNumber() {
        return this.startMilNumber;
    }

    public void setStartMilNumber(String startMilNumber) {
        this.startMilNumber = startMilNumber;
    }

    public String getEntrustRelated() {
        return this.entrustRelated;
    }

    public void setEntrustRelated(String entrustRelated) {
        this.entrustRelated = entrustRelated;
    }

    public String getTaskNumber() {
        return this.taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getChassisCode() {
        return chassisCode;
    }

    public void setChassisCode(String chassisCode) {
        this.chassisCode = chassisCode;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTrialId() {
        return trialId;
    }

    public void setTrialId(String trialId) {
        this.trialId = trialId;
    }

    public String getTaskOrPlan() {
        return taskOrPlan;
    }

    public void setTaskOrPlan(String taskOrPlan) {
        this.taskOrPlan = taskOrPlan;
    }

    public String getDriveState() {
        return driveState;
    }

    public void setDriveState(String driveState) {
        this.driveState = driveState;
    }

    public String getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(String horsePower) {
        this.horsePower = horsePower;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public List<RoadLineEO> getRoadLineEOS() {
        return roadLineEOS;
    }

    public void setRoadLineEOS(List<RoadLineEO> roadLineEOS) {
        this.roadLineEOS = roadLineEOS;
    }

    public String getTrialProjectId() {
        return trialProjectId;
    }

    public void setTrialProjectId(String trialProjectId) {
        this.trialProjectId = trialProjectId;
    }
}
