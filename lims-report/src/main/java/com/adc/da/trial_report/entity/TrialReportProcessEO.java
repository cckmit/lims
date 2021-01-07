package com.adc.da.trial_report.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "com.adc.da.trial_report.entity.EvTrialreportProcessEO")
public class TrialReportProcessEO  extends BaseEntity {
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String id;

    /**
     * 试验任务检验项目id(外键)
     */
    @ApiModelProperty(value = "试验任务检验项目id(外键)")
    private String trialtaskInsproId;

    /**
     * 试验项目进行试验室
     */
    @ApiModelProperty(value = "试验项目进行试验室")
    private String trialprojectLabId;

    /**
     * 试验项目完成率
     */
    @ApiModelProperty(value = "试验项目完成率")
    private String trialprojectRate;

    /**
     * 试验检验项目状态
     */
    @ApiModelProperty(value = "试验检验项目状态")
    private String trialprojectStatus;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private String createBy;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private String createTime;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private String updateBy;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private String updateTime;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private String delFlag;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "反馈")
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}

