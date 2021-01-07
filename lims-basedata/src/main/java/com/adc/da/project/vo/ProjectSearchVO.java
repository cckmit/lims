package com.adc.da.project.vo;

public class ProjectSearchVO {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 项目名称
	 */
	private String name;
	/**
	 * 项目编号
	 */
	private String num;
	/**
	 * 项目经理id
	 */
	private String projectManagerId;
	private String projectManagerName;
	/**
	 * 产品经理id
	 */
	private String produceManagerId;
	private String produceManagerName;
	
	/**
	 * 项目描述
	 */
	private String proDescribe;
	
	/**
	 * 上级项目
	 */
	private String proParentId;
	
	/**
	 * 项目开始时间
	 */
	private String startDate;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getProjectManagerId() {
		return projectManagerId;
	}
	public void setProjectManagerId(String projectManagerId) {
		this.projectManagerId = projectManagerId;
	}
	public String getProjectManagerName() {
		return projectManagerName;
	}
	public void setProjectManagerName(String projectManagerName) {
		this.projectManagerName = projectManagerName;
	}
	public String getProduceManagerId() {
		return produceManagerId;
	}
	public void setProduceManagerId(String produceManagerId) {
		this.produceManagerId = produceManagerId;
	}
	public String getProduceManagerName() {
		return produceManagerName;
	}
	public void setProduceManagerName(String produceManagerName) {
		this.produceManagerName = produceManagerName;
	}
	public String getProDescribe() {
		return proDescribe;
	}
	public void setProDescribe(String proDescribe) {
		this.proDescribe = proDescribe;
	}
	public String getProParentId() {
		return proParentId;
	}
	public void setProParentId(String proParentId) {
		this.proParentId = proParentId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	
}
