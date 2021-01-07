package com.adc.da.trial_execute.vo;

import java.util.ArrayList;
import java.util.List;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.trial_execute.entity.ScaffoldingUserEO;

public class ScaffoldingChangeDetailVO extends BaseEntity {
    /**
     *主键
     */
    private String id;
    
    /**
     *试验任务id
     */
    private String trialtaskId;

    /**
     *变更后台架id
     */
    private String scaffoldingIdAfter;

    /**
     *变更后台架名称
     */
    private String scaffoldingNameAfter;

    /**
     * 变更后关联用户
     */
    private List<ScaffoldingUserEO> scaffoldingAfterList = new ArrayList<>();
    
    

	public List<ScaffoldingUserEO> getScaffoldingAfterList() {
		return scaffoldingAfterList;
	}

	public void setScaffoldingAfterList(List<ScaffoldingUserEO> scaffoldingAfterList) {
		this.scaffoldingAfterList = scaffoldingAfterList;
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


	public String getScaffoldingIdAfter() {
		return scaffoldingIdAfter;
	}

	public void setScaffoldingIdAfter(String scaffoldingIdAfter) {
		this.scaffoldingIdAfter = scaffoldingIdAfter;
	}

	public String getScaffoldingNameAfter() {
		return scaffoldingNameAfter;
	}

	public void setScaffoldingNameAfter(String scaffoldingNameAfter) {
		this.scaffoldingNameAfter = scaffoldingNameAfter;
	}

	
}