package com.adc.da.standard.vo;

public class StandardSearchVO {
	/**
	 * 主键
	 */
	private String stdId;
	/**
	 * 标准名称
	 */
	private String stdName;
	/**
	 * 标准编号
	 */
	private String stdNo;
    /**
     * 附件id
     */
    private String stdFileId;
    
	public String getStdId() {
		return stdId;
	}
	public void setStdId(String stdId) {
		this.stdId = stdId;
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
	public String getStdFileId() {
		return stdFileId;
	}
	public void setStdFileId(String stdFileId) {
		this.stdFileId = stdFileId;
	}
    
}
