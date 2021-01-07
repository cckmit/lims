package com.adc.da.trial_execute.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/9/5 15:25
 * @description：${description}
 */
public class TrialExecuteVO {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "任务编号")
    private String evNumber;

    @ApiModelProperty(value = "实验目的")
    private String purpose;

    @ApiModelProperty(value = "任务名称")
    private String title;

    @ApiModelProperty(value = "任务状态")
    private String trialStatus;

    @ApiModelProperty("计划完成延期天数")
    private String finishdateDelydays;


    @ApiModelProperty(value = "台架号")
    private String scaffoldingName;
    
    @ApiModelProperty("延期状态( 0:未延期   1：预警   2：延期)")
    private String delyFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvNumber() {
        return evNumber;
    }

    public void setEvNumber(String evNumber) {
        this.evNumber = evNumber;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrialStatus() {
        return trialStatus;
    }

    public void setTrialStatus(String trialStatus) {
        this.trialStatus = trialStatus;
    }

    public String getFinishdateDelydays() {
        return finishdateDelydays;
    }

    public void setFinishdateDelydays(String finishdateDelydays) {
        this.finishdateDelydays = finishdateDelydays;
    }

    public String getDelyFlag() {
        return delyFlag;
    }

    public void setDelyFlag(String delyFlag) {
        this.delyFlag = delyFlag;
    }

	public String getScaffoldingName() {
		return scaffoldingName;
	}

	public void setScaffoldingName(String scaffoldingName) {
		this.scaffoldingName = scaffoldingName;
	}
    
    
}
