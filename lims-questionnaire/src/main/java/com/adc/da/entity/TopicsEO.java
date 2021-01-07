package com.adc.da.entity;

import java.util.ArrayList;
import java.util.List;

import com.adc.da.base.entity.BaseEntity;

public class TopicsEO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *创建者
     */
    private String createBy;

    /**
     *创建时间
     */
    private String createTime;

    /**
     *更新者
     */
    private String updateBy;

    /**
     *更新时间
     */
    private String updateTime;

    /**
     *删除标记  1删除 0存在
     */
    private String delFlag;

    /**
     *主题名称
     */
    private String topicsName;

    /**
     *发布组织id
     */
    private String orgId;
    /**
     *发布组织id及下级ids
     */
    private String orgIds;

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
    
    /**
     * 用户答案列表
     */
    private List<QuestionUserEO> questionUserList = new ArrayList<>();

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

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

    
 
}