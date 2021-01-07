package com.adc.da.trial_execute.vo;

import com.adc.da.base.entity.BaseEntity;

public class TrialDataVO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *试验任务id
     */
    private String trialtaskId;

    /**
     *创建者
     */
    private String createBy;

    /**
     *创建时间
     */
    private String createTime;
    
    /**
     * 创建人姓名
     */
    private String createName;

    
    
	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}