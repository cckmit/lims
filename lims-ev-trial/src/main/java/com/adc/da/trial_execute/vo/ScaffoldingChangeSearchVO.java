package com.adc.da.trial_execute.vo;

import com.adc.da.base.entity.BaseEntity;

public class ScaffoldingChangeSearchVO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *创建时间
     */
    private String createTime;
    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


}