package com.adc.da.entity;

import com.adc.da.base.entity.BaseEntity;

public class QuestionEO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *删除标记   1删除 0存在
     */
    private String delFlag;

    /**
     *问题内容
     */
    private String queName;

    /**
     *主题id
     */
    private String topicsId;

    /**
     *问题类型
     *  0--单选
		2--多选
		3--简答
     */
    private String queType;

    /**
     *选项内容
     */
    private String optionContent;

    /**
     *排序
     */
    private Integer queSort;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getQueName() {
		return queName;
	}

	public void setQueName(String queName) {
		this.queName = queName;
	}

	public String getTopicsId() {
		return topicsId;
	}

	public void setTopicsId(String topicsId) {
		this.topicsId = topicsId;
	}

	public String getQueType() {
		return queType;
	}

	public void setQueType(String queType) {
		this.queType = queType;
	}

	public String getOptionContent() {
		return optionContent;
	}

	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}

	public Integer getQueSort() {
		return queSort;
	}

	public void setQueSort(Integer queSort) {
		this.queSort = queSort;
	}

    
    
}