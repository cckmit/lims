package com.adc.da.trial_execute.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>EV_TRIAL_CONNECT TrialConnectEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-09-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class TrialConnectEO extends BaseEntity {

    private String createTime;

    private String updateBy;

    private String updateTime;

    private Integer delFlag;

    private String remark;

    private String id;

    @ApiModelProperty(value = "试验任务书id")
    private String trialtaskId;

    @ApiModelProperty(value = "交班人id")
    private String createBy;

    @ApiModelProperty(value = "交班人姓名")
    private String createName;

    @ApiModelProperty(value = "日常点检状态")
    private String dailyCheckStatus;

    @ApiModelProperty(value = "发动机运行情况")
    private String engineRunStatus;

    @ApiModelProperty(value = "设备运行情况")
    private String deviceRunStatus;

    @ApiModelProperty(value = "试验变更情况")
    private String trialChangeStatus;

    @ApiModelProperty(value = "下班注意事项")
    private String nextConnectNotes;

    @ApiModelProperty(value = "是否存在安全隐患")
    private String safety;

    @ApiModelProperty(value = "安全隐患描述")
    private String safetyDescribe;

    @ApiModelProperty(value = "计划台架搭建状态")
    private String planScaffoldingStatus; //0 是否完成对中 1 达到启动状态 2 两个全选  3 全都不选

    @ApiModelProperty(value = "计划当班时间")
    private String planDutyHour;

    @ApiModelProperty(value = "计划当班总时间")
    private String planDutyTotalHour;

    @ApiModelProperty(value = "计划点检其他")
    private String planDailyCheckOthers;

    @ApiModelProperty(value = "实际台架搭建状态")
    private String actualScaffoldingStatus;

    @ApiModelProperty(value = "实际当班时间")
    private String actualDutyHour;

    @ApiModelProperty(value = "实际当班总时间")
    private String actualDutyTotalHour;

    @ApiModelProperty(value = "实际点检其他")
    private String actualDailyCheckOthers;

    @ApiModelProperty(value = "发动机问题总数")
    private String enginerunProblemTotal;

    @ApiModelProperty(value = "发动机问题完成数")
    private String enginerunProblemFinish;

    @ApiModelProperty(value = "发动机问题未完成数")
    private String enginerunProblemUnfinish;

    @ApiModelProperty(value = "设备问题总数")
    private String devicerunProblemTotal;

    @ApiModelProperty(value = "设备问题完成数")
    private String devicerunProblemFinish;

    @ApiModelProperty(value = "设备问题未完成数")
    private String devicerunProblemUnfinish;

    @ApiModelProperty(value = "接班人id")
    private String connectorId;
    @ApiModelProperty(value = "接班人姓名")
    private String connectorName;

    @ApiModelProperty(value = "接班时间")
    private String connectTime;

    @ApiModelProperty(value = "试验任务名称")
    private String trialTaskName;

    @ApiModelProperty(value = "台架号")
    private String scaffoldingName;

    @ApiModelProperty(value = "试验变更附件id")
    private String trialChangeFileid;

    @ApiModelProperty(value = "试验变更附件名称")
    private String attachmentName;

    
    @ApiModelProperty(value = "交接班状态")
    // 0 未接班   1 已接班
    private String exchangeStatus;
    
    @ApiModelProperty(value = "接班人id")
    private String exchangeBy;
    
    @ApiModelProperty(value = "接班人名")
    private String exchangeName;
    
    @ApiModelProperty(value = "接班时间")
    private String exchangeTime;
    
    
    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>createTime -> create_time</li>
     * <li>updateBy -> update_by</li>
     * <li>updateTime -> update_time</li>
     * <li>delFlag -> del_flag</li>
     * <li>remark -> remark</li>
     * <li>id -> id</li>
     * <li>trialtaskId -> trialtask_id</li>
     * <li>createBy -> create_by</li>
     * <li>dailyCheckStatus -> daily_check_status</li>
     * <li>engineRunStatus -> engine_run_status</li>
     * <li>deviceRunStatus -> device_run_status</li>
     * <li>trialChangeStatus -> trial_change_status</li>
     * <li>trialChangeFileid -> trial_change_fileid</li>
     * <li>nextConnectNotes -> next_connect_notes</li>
     * <li>safety -> safety</li>
     * <li>planScaffoldingStatus -> plan_scaffolding_status</li>
     * <li>planDutyHour -> plan_duty_hour</li>
     * <li>planDutyTotalHour -> plan_duty_total_hour</li>
     * <li>planDailyCheckOthers -> plan_daily_check_others</li>
     * <li>actualScaffoldingStatus -> actual_scaffolding_status</li>
     * <li>actualDutyHour -> actual_duty_hour</li>
     * <li>actualDutyTotalHour -> actual_duty_total_hour</li>
     * <li>actualDailyCheckOthers -> actual_daily_check_others</li>
     * <li>enginerunProblemTotal -> enginerun_problem_total</li>
     * <li>enginerunProblemFinish -> enginerun_problem_finish</li>
     * <li>enginerunProblemUnfinish -> enginerun_problem_unfinish</li>
     * <li>devicerunProblemTotal -> devicerun_problem_total</li>
     * <li>devicerunProblemFinish -> devicerun_problem_finish</li>
     * <li>devicerunProblemUnfinish -> devicerun_problem_unfinish</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "createTime": return "create_time";
            case "updateBy": return "update_by";
            case "updateTime": return "update_time";
            case "delFlag": return "del_flag";
            case "remark": return "remark";
            case "id": return "id";
            case "trialtaskId": return "trialtask_id";
            case "createBy": return "create_by";
            case "dailyCheckStatus": return "daily_check_status";
            case "engineRunStatus": return "engine_run_status";
            case "deviceRunStatus": return "device_run_status";
            case "trialChangeStatus": return "trial_change_status";
            case "trialChangeFileid": return "trial_change_fileid";
            case "nextConnectNotes": return "next_connect_notes";
            case "safety": return "safety";
            case "planScaffoldingStatus": return "plan_scaffolding_status";
            case "planDutyHour": return "plan_duty_hour";
            case "planDutyTotalHour": return "plan_duty_total_hour";
            case "planDailyCheckOthers": return "plan_daily_check_others";
            case "actualScaffoldingStatus": return "actual_scaffolding_status";
            case "actualDutyHour": return "actual_duty_hour";
            case "actualDutyTotalHour": return "actual_duty_total_hour";
            case "actualDailyCheckOthers": return "actual_daily_check_others";
            case "enginerunProblemTotal": return "enginerun_problem_total";
            case "enginerunProblemFinish": return "enginerun_problem_finish";
            case "enginerunProblemUnfinish": return "enginerun_problem_unfinish";
            case "devicerunProblemTotal": return "devicerun_problem_total";
            case "devicerunProblemFinish": return "devicerun_problem_finish";
            case "devicerunProblemUnfinish": return "devicerun_problem_unfinish";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>create_time -> createTime</li>
     * <li>update_by -> updateBy</li>
     * <li>update_time -> updateTime</li>
     * <li>del_flag -> delFlag</li>
     * <li>remark -> remark</li>
     * <li>id -> id</li>
     * <li>trialtask_id -> trialtaskId</li>
     * <li>create_by -> createBy</li>
     * <li>daily_check_status -> dailyCheckStatus</li>
     * <li>engine_run_status -> engineRunStatus</li>
     * <li>device_run_status -> deviceRunStatus</li>
     * <li>trial_change_status -> trialChangeStatus</li>
     * <li>trial_change_fileid -> trialChangeFileid</li>
     * <li>next_connect_notes -> nextConnectNotes</li>
     * <li>safety -> safety</li>
     * <li>plan_scaffolding_status -> planScaffoldingStatus</li>
     * <li>plan_duty_hour -> planDutyHour</li>
     * <li>plan_duty_total_hour -> planDutyTotalHour</li>
     * <li>plan_daily_check_others -> planDailyCheckOthers</li>
     * <li>actual_scaffolding_status -> actualScaffoldingStatus</li>
     * <li>actual_duty_hour -> actualDutyHour</li>
     * <li>actual_duty_total_hour -> actualDutyTotalHour</li>
     * <li>actual_daily_check_others -> actualDailyCheckOthers</li>
     * <li>enginerun_problem_total -> enginerunProblemTotal</li>
     * <li>enginerun_problem_finish -> enginerunProblemFinish</li>
     * <li>enginerun_problem_unfinish -> enginerunProblemUnfinish</li>
     * <li>devicerun_problem_total -> devicerunProblemTotal</li>
     * <li>devicerun_problem_finish -> devicerunProblemFinish</li>
     * <li>devicerun_problem_unfinish -> devicerunProblemUnfinish</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "create_time": return "createTime";
            case "update_by": return "updateBy";
            case "update_time": return "updateTime";
            case "del_flag": return "delFlag";
            case "remark": return "remark";
            case "id": return "id";
            case "trialtask_id": return "trialtaskId";
            case "create_by": return "createBy";
            case "daily_check_status": return "dailyCheckStatus";
            case "engine_run_status": return "engineRunStatus";
            case "device_run_status": return "deviceRunStatus";
            case "trial_change_status": return "trialChangeStatus";
            case "trial_change_fileid": return "trialChangeFileid";
            case "next_connect_notes": return "nextConnectNotes";
            case "safety": return "safety";
            case "plan_scaffolding_status": return "planScaffoldingStatus";
            case "plan_duty_hour": return "planDutyHour";
            case "plan_duty_total_hour": return "planDutyTotalHour";
            case "plan_daily_check_others": return "planDailyCheckOthers";
            case "actual_scaffolding_status": return "actualScaffoldingStatus";
            case "actual_duty_hour": return "actualDutyHour";
            case "actual_duty_total_hour": return "actualDutyTotalHour";
            case "actual_daily_check_others": return "actualDailyCheckOthers";
            case "enginerun_problem_total": return "enginerunProblemTotal";
            case "enginerun_problem_finish": return "enginerunProblemFinish";
            case "enginerun_problem_unfinish": return "enginerunProblemUnfinish";
            case "devicerun_problem_total": return "devicerunProblemTotal";
            case "devicerun_problem_finish": return "devicerunProblemFinish";
            case "devicerun_problem_unfinish": return "devicerunProblemUnfinish";
            default: return null;
        }
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
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**  **/
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
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
    public Integer getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /**  **/
    public String getTrialtaskId() {
        return this.trialtaskId;
    }

    /**  **/
    public void setTrialtaskId(String trialtaskId) {
        this.trialtaskId = trialtaskId;
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
    public String getDailyCheckStatus() {
        return this.dailyCheckStatus;
    }

    /**  **/
    public void setDailyCheckStatus(String dailyCheckStatus) {
        this.dailyCheckStatus = dailyCheckStatus;
    }

    /**  **/
    public String getEngineRunStatus() {
        return this.engineRunStatus;
    }

    /**  **/
    public void setEngineRunStatus(String engineRunStatus) {
        this.engineRunStatus = engineRunStatus;
    }

    /**  **/
    public String getDeviceRunStatus() {
        return this.deviceRunStatus;
    }

    /**  **/
    public void setDeviceRunStatus(String deviceRunStatus) {
        this.deviceRunStatus = deviceRunStatus;
    }

    /**  **/
    public String getTrialChangeStatus() {
        return this.trialChangeStatus;
    }

    /**  **/
    public void setTrialChangeStatus(String trialChangeStatus) {
        this.trialChangeStatus = trialChangeStatus;
    }

    /**  **/
    public String getTrialChangeFileid() {
        return this.trialChangeFileid;
    }

    /**  **/
    public void setTrialChangeFileid(String trialChangeFileid) {
        this.trialChangeFileid = trialChangeFileid;
    }

    /**  **/
    public String getNextConnectNotes() {
        return this.nextConnectNotes;
    }

    /**  **/
    public void setNextConnectNotes(String nextConnectNotes) {
        this.nextConnectNotes = nextConnectNotes;
    }

    /**  **/
    public String getSafety() {
        return this.safety;
    }

    /**  **/
    public void setSafety(String safety) {
        this.safety = safety;
    }

    /**  **/
    public String getPlanScaffoldingStatus() {
        return this.planScaffoldingStatus;
    }

    /**  **/
    public void setPlanScaffoldingStatus(String planScaffoldingStatus) {
        this.planScaffoldingStatus = planScaffoldingStatus;
    }

    /**  **/
    public String getPlanDutyHour() {
        return this.planDutyHour;
    }

    /**  **/
    public void setPlanDutyHour(String planDutyHour) {
        this.planDutyHour = planDutyHour;
    }

    /**  **/
    public String getPlanDutyTotalHour() {
        return this.planDutyTotalHour;
    }

    /**  **/
    public void setPlanDutyTotalHour(String planDutyTotalHour) {
        this.planDutyTotalHour = planDutyTotalHour;
    }

    /**  **/
    public String getPlanDailyCheckOthers() {
        return this.planDailyCheckOthers;
    }

    /**  **/
    public void setPlanDailyCheckOthers(String planDailyCheckOthers) {
        this.planDailyCheckOthers = planDailyCheckOthers;
    }

    /**  **/
    public String getActualScaffoldingStatus() {
        return this.actualScaffoldingStatus;
    }

    /**  **/
    public void setActualScaffoldingStatus(String actualScaffoldingStatus) {
        this.actualScaffoldingStatus = actualScaffoldingStatus;
    }

    /**  **/
    public String getActualDutyHour() {
        return this.actualDutyHour;
    }

    /**  **/
    public void setActualDutyHour(String actualDutyHour) {
        this.actualDutyHour = actualDutyHour;
    }

    /**  **/
    public String getActualDutyTotalHour() {
        return this.actualDutyTotalHour;
    }

    /**  **/
    public void setActualDutyTotalHour(String actualDutyTotalHour) {
        this.actualDutyTotalHour = actualDutyTotalHour;
    }

    /**  **/
    public String getActualDailyCheckOthers() {
        return this.actualDailyCheckOthers;
    }

    /**  **/
    public void setActualDailyCheckOthers(String actualDailyCheckOthers) {
        this.actualDailyCheckOthers = actualDailyCheckOthers;
    }

    /**  **/
    public String getEnginerunProblemTotal() {
        return this.enginerunProblemTotal;
    }

    /**  **/
    public void setEnginerunProblemTotal(String enginerunProblemTotal) {
        this.enginerunProblemTotal = enginerunProblemTotal;
    }

    /**  **/
    public String getEnginerunProblemFinish() {
        return this.enginerunProblemFinish;
    }

    /**  **/
    public void setEnginerunProblemFinish(String enginerunProblemFinish) {
        this.enginerunProblemFinish = enginerunProblemFinish;
    }

    /**  **/
    public String getEnginerunProblemUnfinish() {
        return this.enginerunProblemUnfinish;
    }

    /**  **/
    public void setEnginerunProblemUnfinish(String enginerunProblemUnfinish) {
        this.enginerunProblemUnfinish = enginerunProblemUnfinish;
    }

    /**  **/
    public String getDevicerunProblemTotal() {
        return this.devicerunProblemTotal;
    }

    /**  **/
    public void setDevicerunProblemTotal(String devicerunProblemTotal) {
        this.devicerunProblemTotal = devicerunProblemTotal;
    }

    /**  **/
    public String getDevicerunProblemFinish() {
        return this.devicerunProblemFinish;
    }

    /**  **/
    public void setDevicerunProblemFinish(String devicerunProblemFinish) {
        this.devicerunProblemFinish = devicerunProblemFinish;
    }

    /**  **/
    public String getDevicerunProblemUnfinish() {
        return this.devicerunProblemUnfinish;
    }

    /**  **/
    public void setDevicerunProblemUnfinish(String devicerunProblemUnfinish) {
        this.devicerunProblemUnfinish = devicerunProblemUnfinish;
    }

    public String getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(String connectorId) {
        this.connectorId = connectorId;
    }

    public String getConnectTime() {
        return connectTime;
    }

    public void setConnectTime(String connectTime) {
        this.connectTime = connectTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getConnectorName() {
        return connectorName;
    }

    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }

    public String getTrialTaskName() {
        return trialTaskName;
    }

    public void setTrialTaskName(String trialTaskName) {
        this.trialTaskName = trialTaskName;
    }

    public String getScaffoldingName() {
        return scaffoldingName;
    }

    public void setScaffoldingName(String scaffoldingName) {
        this.scaffoldingName = scaffoldingName;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getSafetyDescribe() {
        return safetyDescribe;
    }

    public void setSafetyDescribe(String safetyDescribe) {
        this.safetyDescribe = safetyDescribe;
    }

	public String getExchangeStatus() {
		return exchangeStatus;
	}

	public void setExchangeStatus(String exchangeStatus) {
		this.exchangeStatus = exchangeStatus;
	}

	public String getExchangeBy() {
		return exchangeBy;
	}

	public void setExchangeBy(String exchangeBy) {
		this.exchangeBy = exchangeBy;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getExchangeTime() {
		return exchangeTime;
	}

	public void setExchangeTime(String exchangeTime) {
		this.exchangeTime = exchangeTime;
	}


}
