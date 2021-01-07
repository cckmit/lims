package com.adc.da.entity;

import java.util.List;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.vo.AnswerVO;

public class QuestionUserEO extends BaseEntity {
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
     *主题id
     */
    private String topicsId;

    /**
     *主题名称
     */
    private String topicsName;

    /**
     *用户id
     */
    private String userId;

    /**
     *用户名称
     */
    private String userName;

    /**
     *是否回答
     */
    private String isAnswer;

    /**
     *答案
     */
    private String context;
    /**
     * 答案
     */
    private List<AnswerVO> contextObj;
	
	public List<AnswerVO> getContextObj() {
		return contextObj;
	}

	public void setContextObj(List<AnswerVO> contextObj) {
		this.contextObj = contextObj;
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

	public String getTopicsId() {
		return topicsId;
	}

	public void setTopicsId(String topicsId) {
		this.topicsId = topicsId;
	}

	public String getTopicsName() {
		return topicsName;
	}

	public void setTopicsName(String topicsName) {
		this.topicsName = topicsName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIsAnswer() {
		return isAnswer;
	}

	public void setIsAnswer(String isAnswer) {
		this.isAnswer = isAnswer;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

    
}