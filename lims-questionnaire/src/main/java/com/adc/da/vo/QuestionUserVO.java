package com.adc.da.vo;

import java.util.List;

public class QuestionUserVO {
	/**
     *主键
     */
    private String id;
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
