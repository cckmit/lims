package com.adc.da.page;

import java.util.List;

import com.adc.da.base.page.BasePage;

public class TopicsEOPage extends BasePage {
    /**
     * 通用查询条件
     */
    private List<String> searchPhrase;
    
    /**
     *主题名称
     */
    private String topicsName;
    
    /**
     *发布组织名
     */
    private String orgName;
    
    /**
     *发布组织id
     */
    private String orgId;
    
    /**
     *发布组织id及下级ids
     */
    private String orgIds;
    
    /**
     * 主题发布状态
     * 0-草稿
	   1-已发布
     */
    private String topicsStatus;
    
    
    /**
     *创建者
     */
    private String createBy;
    
	public String getTopicsStatus() {
		return topicsStatus;
	}
	public void setTopicsStatus(String topicsStatus) {
		this.topicsStatus = topicsStatus;
	}


	public List<String> getSearchPhrase() {
		return searchPhrase;
	}

	public void setSearchPhrase(List<String> searchPhrase) {
		this.searchPhrase = searchPhrase;
	}

	public String getTopicsName() {
		return topicsName;
	}

	public void setTopicsName(String topicsName) {
		this.topicsName = topicsName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

    
}
