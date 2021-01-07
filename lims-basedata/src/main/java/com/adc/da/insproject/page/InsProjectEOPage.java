package com.adc.da.insproject.page;

import java.util.List;

import com.adc.da.base.page.BasePage;

public class InsProjectEOPage extends BasePage {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 项目名称
	 */
    private String proName;
    /**
     * 项目代码
     */
    private String proCode;
    /**
	     * 项目类型(强检项目,新能源项目,营运货车项目)
	*/
	private String proType;
    
    /**
     * 负责试验室(组织)
     */
    private String labId;
    
    private String labName;

    /**
     * 试验方法
     */
    private String trialMethod;
    /**
     * 项目说明
     */
    private String proDesc;
    
    /**
     * 项目树形节点id
     */
    private String proTypeId;
    
    /**
     * 关联检验项目
     */
    private String stdNo;
    
    /**
     * 通用查询条件
     */
    private List<String> searchPhrase;
    
    /**
     * 项目代码,用来查询相同项目代码的检验项目
     */
    private String proCode2;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getLabId() {
		return labId;
	}

	public void setLabId(String labId) {
		this.labId = labId;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public String getTrialMethod() {
		return trialMethod;
	}

	public void setTrialMethod(String trialMethod) {
		this.trialMethod = trialMethod;
	}

	public String getProDesc() {
		return proDesc;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}

	public String getProTypeId() {
		return proTypeId;
	}

	public void setProTypeId(String proTypeId) {
		this.proTypeId = proTypeId;
	}

	public String getStdNo() {
		return stdNo;
	}

	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}

	public List<String> getSearchPhrase() {
		return searchPhrase;
	}

	public void setSearchPhrase(List<String> searchPhrase) {
		this.searchPhrase = searchPhrase;
	}
    
	public String getProCode2() {
		return proCode2;
	}

	public void setProCode2(String proCode2) {
		this.proCode2 = proCode2;
	}
	
}
