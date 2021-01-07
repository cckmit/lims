package com.adc.da.trial_execute.entity;

import com.adc.da.base.entity.BaseEntity;

public class ScaffoldingUserEO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *台架变更id
     */
    private String scaffoldingChangeId;

    /**
     *用户id
     */
    private String userId;

    /**
     *用户名
     */
    private String userName;
    
    /**
     * 区别变更前后人员
     */
    private String typeFlag;
    

	public String getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScaffoldingChangeId() {
		return scaffoldingChangeId;
	}

	public void setScaffoldingChangeId(String scaffoldingChangeId) {
		this.scaffoldingChangeId = scaffoldingChangeId;
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

    
}