package com.adc.da.trial_execute.entity;

import java.util.ArrayList;
import java.util.List;

import com.adc.da.base.entity.BaseEntity;

public class ScaffoldingChangeEO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *删除标记
     */
    private String delFlag;

    /**
     *创建者
     */
    private String createBy;

    /**
     *创建时间
     */
    private String createTime;

    /**
     *试验任务id
     */
    private String trialtaskId;

    /**
     *变更前台架id
     */
    private String scaffoldingIdBefore;

    /**
     *变更前台架名称
     */
    private String scaffoldingNameBefore;

    /**
     *变更后台架id
     */
    private String scaffoldingIdAfter;

    /**
     *变更后台架名称
     */
    private String scaffoldingNameAfter;

    /**
     * 变更关联用户
     */
    private List<ScaffoldingUserEO> scaffoldingList = new ArrayList<>();
    
    /**
     * 变更前关联用户
     */
    private List<ScaffoldingUserEO> scaffoldingBeforeList = new ArrayList<>();
    /**
     * 变更后关联用户
     */
    private List<ScaffoldingUserEO> scaffoldingAfterList = new ArrayList<>();
    
    
	public List<ScaffoldingUserEO> getScaffoldingBeforeList() {
		return scaffoldingBeforeList;
	}

	public void setScaffoldingBeforeList(List<ScaffoldingUserEO> scaffoldingBeforeList) {
		this.scaffoldingBeforeList = scaffoldingBeforeList;
	}

	public List<ScaffoldingUserEO> getScaffoldingAfterList() {
		return scaffoldingAfterList;
	}

	public void setScaffoldingAfterList(List<ScaffoldingUserEO> scaffoldingAfterList) {
		this.scaffoldingAfterList = scaffoldingAfterList;
	}
    
	public List<ScaffoldingUserEO> getScaffoldingList() {
		return scaffoldingList;
	}

	public void setScaffoldingList(List<ScaffoldingUserEO> scaffoldingList) {
		this.scaffoldingList = scaffoldingList;
	}

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

	public String getTrialtaskId() {
		return trialtaskId;
	}

	public void setTrialtaskId(String trialtaskId) {
		this.trialtaskId = trialtaskId;
	}

	public String getScaffoldingIdBefore() {
		return scaffoldingIdBefore;
	}

	public void setScaffoldingIdBefore(String scaffoldingIdBefore) {
		this.scaffoldingIdBefore = scaffoldingIdBefore;
	}

	public String getScaffoldingNameBefore() {
		return scaffoldingNameBefore;
	}

	public void setScaffoldingNameBefore(String scaffoldingNameBefore) {
		this.scaffoldingNameBefore = scaffoldingNameBefore;
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