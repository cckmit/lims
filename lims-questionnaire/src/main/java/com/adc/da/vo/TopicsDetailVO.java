package com.adc.da.vo;

import java.util.ArrayList;
import java.util.List;

import com.adc.da.entity.QuestionEO;

public class TopicsDetailVO {
	/**
     *主键
     */
    private String id;
    /**
     *主题名称
     */
    private String topicsName;

    /**
     *发布组织id
     */
    private String orgId;

    /**
     *发布组织名
     */
    private String orgName;

    /**
     *发布接受人数
     */
    private String publishUserCount;
    
    /**
     * 问题列表
     */
    List<QuestionEO> questionList = new ArrayList<>();
    
    /**
     * 主题发布状态
     * 0-草稿
	   1-已发布
     */
    private String topicsStatus;
    
    
    
	public String getTopicsStatus() {
		return topicsStatus;
	}

	public void setTopicsStatus(String topicsStatus) {
		this.topicsStatus = topicsStatus;
	}

    
    
	public List<QuestionEO> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<QuestionEO> questionList) {
		this.questionList = questionList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTopicsName() {
		return topicsName;
	}

	public void setTopicsName(String topicsName) {
		this.topicsName = topicsName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPublishUserCount() {
		return publishUserCount;
	}

	public void setPublishUserCount(String publishUserCount) {
		this.publishUserCount = publishUserCount;
	}

	
}
