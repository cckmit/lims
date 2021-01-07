package com.adc.da.internalcost.page;

import com.adc.da.base.page.BasePage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/11/15 14:16
 * @description：
 */
public class CostStatisticsPage extends BasePage {

    @ApiModelProperty(value = "任务书编号")
    private String trialTaskCode;

    @ApiModelProperty("申请人")
    private String applyUser;

    @ApiModelProperty("申请时间")
    private String applyStartTime;

    @ApiModelProperty("申请时间")
    private String applyEndTimeStart;
    @ApiModelProperty("申请时间")
    private String applyEndTimeEnd;

    @ApiModelProperty(value = "通用查询")
    private String searchPhrase;

    @JsonIgnore
    private List<String> keyWords;

    public String getTrialTaskCode() {
        return trialTaskCode;
    }

    public void setTrialTaskCode(String trialTaskCode) {
        this.trialTaskCode = trialTaskCode;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyStartTime() {
        return applyStartTime;
    }

    public void setApplyStartTime(String applyStartTime) {
        this.applyStartTime = applyStartTime;
    }

    public String getApplyEndTimeStart() {
        return applyEndTimeStart;
    }

    public void setApplyEndTimeStart(String applyEndTimeStart) {
        this.applyEndTimeStart = applyEndTimeStart;
    }

    public String getApplyEndTimeEnd() {
        return applyEndTimeEnd;
    }

    public void setApplyEndTimeEnd(String applyEndTimeEnd) {
        this.applyEndTimeEnd = applyEndTimeEnd;
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
