package com.adc.da.instype.vo;

public class InsTypeVO {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 检验项目类型名称
	 */
	private String insTypeName;
	/**
	 * 检验项目类型父级id
	 */
	private String insTypeParentId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInsTypeName() {
		return insTypeName;
	}
	public void setInsTypeName(String insTypeName) {
		this.insTypeName = insTypeName;
	}
	public String getInsTypeParentId() {
		return insTypeParentId;
	}
	public void setInsTypeParentId(String insTypeParentId) {
		this.insTypeParentId = insTypeParentId;
	}
}
