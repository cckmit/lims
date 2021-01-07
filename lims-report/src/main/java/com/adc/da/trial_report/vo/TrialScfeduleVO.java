package com.adc.da.trial_report.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/9/11 13:28
 * @description：${description}
 */
public class TrialScfeduleVO {

    
    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "试验任务检验项目id(外键)")
    private String trialtaskInsproId;

    @ApiModelProperty(value = "试验项目进行试验室")
    private String trialprojectLabId;

    @ApiModelProperty(value = "试验项目完成率")
    private String trialprojectRate;

    @ApiModelProperty(value = "试验检验项目状态")
    private String trialprojectStatus;

    @ApiModelProperty(value = "填写人ID")
    private String createBy;
    
    @ApiModelProperty(value = "填写时间")
    private String createTime;
    
    @ApiModelProperty(value = "填写人姓名")
    private String createName;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "反馈意见")
    private String feedback;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrialtaskInsproId() {
        return trialtaskInsproId;
    }

    public void setTrialtaskInsproId(String trialtaskInsproId) {
        this.trialtaskInsproId = trialtaskInsproId;
    }

    public String getTrialprojectLabId() {
        return trialprojectLabId;
    }

    public void setTrialprojectLabId(String trialprojectLabId) {
        this.trialprojectLabId = trialprojectLabId;
    }

    public String getTrialprojectRate() {
        return trialprojectRate;
    }

    public void setTrialprojectRate(String trialprojectRate) {
        this.trialprojectRate = trialprojectRate;
    }

    public String getTrialprojectStatus() {
        return trialprojectStatus;
    }

    public void setTrialprojectStatus(String trialprojectStatus) {
        this.trialprojectStatus = trialprojectStatus;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
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
