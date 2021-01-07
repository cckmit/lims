package com.adc.da.stdtype.vo;

public class StdTypeVO {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 标准类型名称
	 */
	private String stdTypeName;
	/**
	 * 标准父级节点id
	 */
	private String stdTypeParentId;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStdTypeName() {
		return stdTypeName;
	}
	public void setStdTypeName(String stdTypeName) {
		this.stdTypeName = stdTypeName;
	}
	public String getStdTypeParentId() {
		return stdTypeParentId;
	}
	public void setStdTypeParentId(String stdTypeParentId) {
		this.stdTypeParentId = stdTypeParentId;
	}
	
}
