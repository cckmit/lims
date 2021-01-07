package com.adc.da.trial_execute.page;

import com.adc.da.base.page.BasePage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/9/5 14:23
 * @description：${description}
 */
public class TrialExecuteEOPage extends BasePage {


    @ApiModelProperty(value = "任务编号")
    private String evNumber;

    @ApiModelProperty(value = "实验目的")
    private String purpose;

    @ApiModelProperty(value = "任务名称")
    private String title;

    @ApiModelProperty(value = "任务状态")
    private String trialStatus;

    @ApiModelProperty(value = "通用查询")
    private String searchPhrase;

    @JsonIgnore
    private List<String> keyWords;

    @JsonIgnore
    private String userId;
    /**
     * 台架对应人员ids
     */
    @ApiModelProperty(value = "台架对应人员ids")
    private String personIds;
    
    /**
     * 用户id集合,用于过滤数据
     */
    @ApiModelProperty(value = "用户id集合")
    private List<String> createByIds;
	
    
	public List<String> getCreateByIds() {
		return createByIds;
	}
	public void setCreateByIds(List<String> createByIds) {
		this.createByIds = createByIds;
	}
    
    
	public String getPersonIds() {
		return personIds;
	}
	public void setPersonIds(String personIds) {
		this.personIds = personIds;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
