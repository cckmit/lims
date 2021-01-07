package com.adc.da.pc_drive.vo;

import java.util.Date;
import java.util.List;

public class PcDrivingRecourdVo {
    /*立项单id*/
    private String initTaskID;
    private String trialTaskId;
    /*底盘编号*/
    private String chassisCode;

    /*车型*/
    private String vehicleType;
    /*路段类型*/
    private List<PcTrialLineVo> pcTrialLineVos;
    /*任务创建时间*/
    private String createTime;
    /*预计完成时间*/
    private String planFinishTime;
    /*任务书编号*/
    private String taskCode;
    //查询行车记录表

    public String getTrialTaskId() {
        return trialTaskId;
    }

    public void setTrialTaskId(String trialTaskId) {
        this.trialTaskId = trialTaskId;
    }

    public String getInitTaskID() {
        return initTaskID;
    }

    public void setInitTaskID(String initTaskID) {
        this.initTaskID = initTaskID;
    }

    public String getChassisCode() {
        return chassisCode;
    }

    public void setChassisCode(String chassisCode) {
        this.chassisCode = chassisCode;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<PcTrialLineVo> getPcTrialLineVos() {
        return pcTrialLineVos;
    }

    public void setPcTrialLineVos(List<PcTrialLineVo> pcTrialLineVos) {
        this.pcTrialLineVos = pcTrialLineVos;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPlanFinishTime() {
        return planFinishTime;
    }

    public void setPlanFinishTime(String planFinishTime) {
        this.planFinishTime = planFinishTime;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }
}
