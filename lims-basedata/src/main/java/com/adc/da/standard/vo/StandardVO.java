package com.adc.da.standard.vo;

public class StandardVO {
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
	 * 标准类型ID
	 */
    private String stdTypeId;
    /**
     *  标准类型名称
     */
    private String stdTypeName;
    /**
     * 标准解读
     */
    private String stdRead;
    /**
     * 标准状态
     */
    private String stdStatus;
    /**
     * 实施时间
     */
    private String stdImplementDate;
    
    /**
     * 附件id
     */
    private String stdFileId;
    /**
     * 附件名称
     */
    private String stdFileName;
    
    
	public String getStdFileName() {
		return stdFileName;
	}
	public void setStdFileName(String stdFileName) {
		this.stdFileName = stdFileName;
	}
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
	public String getStdTypeId() {
		return stdTypeId;
	}
	public void setStdTypeId(String stdTypeId) {
		this.stdTypeId = stdTypeId;
	}
	public String getStdTypeName() {
		return stdTypeName;
	}
	public void setStdTypeName(String stdTypeName) {
		this.stdTypeName = stdTypeName;
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
	public String getStdImplementDate() {
		return stdImplementDate;
	}
	public void setStdImplementDate(String stdImplementDate) {
		this.stdImplementDate = stdImplementDate;
	}
	public String getStdFileId() {
		return stdFileId;
	}
	public void setStdFileId(String stdFileId) {
		this.stdFileId = stdFileId;
	}
    
}
