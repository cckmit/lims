package com.adc.da.trial_report.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/9/11 10:54
 * @description：${description}
 */
public class TrialScheduleDto {

    @ApiModelProperty("实验任务检验项目ID")
    private String trialtaskInsproId;

    @ApiModelProperty("任务状态(0,进行中；1，已取消；2，已暂停；3，已结束)")
    private String taskStatus;

    @ApiModelProperty("试验地点")
    private String trialLocation;

    @ApiModelProperty("完成率")
    private String finishRate;

    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("反馈")
    private String feedback;

    public String getTrialtaskInsproId() {
        return trialtaskInsproId;
    }

    public void setTrialtaskInsproId(String trialtaskInsproId) {
        this.trialtaskInsproId = trialtaskInsproId;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTrialLocation() {
        return trialLocation;
    }

    public void setTrialLocation(String trialLocation) {
        this.trialLocation = trialLocation;
    }

    public String getFinishRate() {
        return finishRate;
    }

    public void setFinishRate(String finishRate) {
        this.finishRate = finishRate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
