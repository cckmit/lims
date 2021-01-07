package com.adc.da.vo;

import java.util.ArrayList;
import java.util.List;

import com.adc.da.entity.QuestionUserEO;

public class TopicsVO {
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
     * 主题发布状态
     * 0-草稿
	   1-已发布
     */
    private String topicsStatus;
    
    /**
     * 用户答案列表
     */
    private List<QuestionUserEO> questionUserList = new ArrayList<>();

	/**
	 *创建时间
	 */
	private String createTime;

	public List<QuestionUserEO> getQuestionUserList() {
		return questionUserList;
	}

	public void setQuestionUserList(List<QuestionUserEO> questionUserList) {
		this.questionUserList = questionUserList;
	}

	public String getTopicsStatus() {
		return topicsStatus;
	}

	public void setTopicsStatus(String topicsStatus) {
		this.topicsStatus = topicsStatus;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
