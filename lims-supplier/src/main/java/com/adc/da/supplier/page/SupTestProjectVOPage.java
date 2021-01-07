package com.adc.da.supplier.page;

import com.adc.da.base.page.BasePage;

public class SupTestProjectVOPage extends BasePage {
    /**
     * 试验项目
     */
    private String testProject;
    /**
     * 供应商名
     */
    private String supName;
    /**
     * 供应商能力主键id
     */
    private String supAbilityId;
    
    /**
     * 项目代号
     */
    private String projectCode;
    
	public String getTestProject() {
		return testProject;
	}
	public void setTestProject(String testProject) {
		this.testProject = testProject;
	}
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
	public String getSupAbilityId() {
		return supAbilityId;
	}
	public void setSupAbilityId(String supAbilityId) {
		this.supAbilityId = supAbilityId;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
    
}
