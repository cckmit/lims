package com.adc.da.trial_execute.page;

import com.adc.da.base.page.BasePage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * <b>功能：</b>EV_TRIAL_CONNECT TrialConnectEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-09-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class TrialConnectEOPage extends BasePage {

    @ApiModelProperty(value = "申请开始日期")
    private String createTimeStart;

    @ApiModelProperty(value = "申请结束日期")
    private String createTimeEnd;

    @ApiModelProperty(value = "试验任务书id")
    private String trialtaskId;

    @ApiModelProperty(value = "申请人")
    private String createBy;

    @ApiModelProperty(value = "日常点检状态")
    private String dailyCheckStatus;

    @ApiModelProperty(value = "发动机运行情况")
    private String engineRunStatus;

    @ApiModelProperty(value = "设备运行情况")
    private String deviceRunStatus;

    @ApiModelProperty(value = "试验变更情况")
    private String trialChangeStatus;

    @ApiModelProperty(value = "是否存在安全隐患")
    private String safety;

    @ApiModelProperty(value = "通用查询")
    private String searchPhrase;

    @JsonIgnore
    private List<String> keyWords;

    public String getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getTrialtaskId() {
        return trialtaskId;
    }

    public void setTrialtaskId(String trialtaskId) {
        this.trialtaskId = trialtaskId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getDailyCheckStatus() {
        return dailyCheckStatus;
    }

    public void setDailyCheckStatus(String dailyCheckStatus) {
        this.dailyCheckStatus = dailyCheckStatus;
    }

    public String getEngineRunStatus() {
        return engineRunStatus;
    }

    public void setEngineRunStatus(String engineRunStatus) {
        this.engineRunStatus = engineRunStatus;
    }

    public String getDeviceRunStatus() {
        return deviceRunStatus;
    }

    public void setDeviceRunStatus(String deviceRunStatus) {
        this.deviceRunStatus = deviceRunStatus;
    }

    public String getTrialChangeStatus() {
        return trialChangeStatus;
    }

    public void setTrialChangeStatus(String trialChangeStatus) {
        this.trialChangeStatus = trialChangeStatus;
    }

    public String getSafety() {
        return safety;
    }

    public void setSafety(String safety) {
        this.safety = safety;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }
}
