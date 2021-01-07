package com.adc.da.standard.page;

import java.util.List;

import com.adc.da.base.page.BasePage;

public class StandardEOPage extends BasePage{
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 标准名称
	 */
	private String stdName;
	/**
	 * 标准编号
	 */
	private String stdNo;
	/**
	 * 标准类型id
	 */
	private String stdTypeId;
	 /**
	     *  标准类型名称
	*/
	private String stdTypeName;
	
	/**
	 * 标准实施时间
	 */
    private String stdImplementDate;
	private String stdImplementDate1;
	private String stdImplementDate2;
	/**
    * 标准解读
    */
    private String stdRead;
   /**
    * 标准状态
    */
    private String stdStatus;
    
    /**
     * 附件id
     */
    private String stdFileId;
    /**
     * 附件名称
     */
    private String stdFileName;
   
   /**
    * 通用查询条件
    */
    private List<String> searchPhrase;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getStdName() {
		return stdName;
	}
	
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	
	public String getStdNo() {
		return stdNo;
	}
	
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}
	
	public String getStdTypeName() {
		return stdTypeName;
	}
	
	public void setStdTypeName(String stdTypeName) {
		this.stdTypeName = stdTypeName;
	}
	
	public String getStdImplementDate1() {
		return stdImplementDate1;
	}
	
	public void setStdImplementDate1(String stdImplementDate1) {
		this.stdImplementDate1 = stdImplementDate1;
	}
	
	public String getStdImplementDate2() {
		return stdImplementDate2;
	}
	
	public void setStdImplementDate2(String stdImplementDate2) {
		this.stdImplementDate2 = stdImplementDate2;
	}
	
	public String getStdRead() {
		return stdRead;
	}
	
	public void setStdRead(String stdRead) {
		this.stdRead = stdRead;
	}
	
	public String getStdStatus() {
		return stdStatus;
	}
	
	public void setStdStatus(String stdStatus) {
		this.stdStatus = stdStatus;
	}
	
	public List<String> getSearchPhrase() {
		return searchPhrase;
	}
	
	public void setSearchPhrase(List<String> searchPhrase) {
		this.searchPhrase = searchPhrase;
	}

	public String getStdTypeId() {
		return stdTypeId;
	}

	public void setStdTypeId(String stdTypeId) {
		this.stdTypeId = stdTypeId;
	}

	public String getStdFileId() {
		return stdFileId;
	}

	public void setStdFileId(String stdFileId) {
		this.stdFileId = stdFileId;
	}

	public String getStdImplementDate() {
		return stdImplementDate;
	}

	public void setStdImplementDate(String stdImplementDate) {
		this.stdImplementDate = stdImplementDate;
	}

	public String getStdFileName() {
		return stdFileName;
	}

	public void setStdFileName(String stdFileName) {
		this.stdFileName = stdFileName;
	}
	  
	   
	   
}
