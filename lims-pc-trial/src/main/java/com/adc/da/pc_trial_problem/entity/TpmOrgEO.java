package com.adc.da.pc_trial_problem.entity;

public class TpmOrgEO {
	/**
	 * 部门ID(代码)
	 */
	private String deptId;
	
	/**
	 * 部门名称
	 */
	private String fullName;
	/**
	 * 部门类型
	 */
	private String deptType;
	
	
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDeptType() {
		return deptType;
	}
	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}
	
	
}
