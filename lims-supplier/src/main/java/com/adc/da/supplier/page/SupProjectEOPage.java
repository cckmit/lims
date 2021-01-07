package com.adc.da.supplier.page;

import com.adc.da.base.page.BasePage;

/**
 * <b>功能：</b>SUP_PROJECT ProjectEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-15 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SupProjectEOPage extends BasePage {

	/**
	 * 检验项目
	 */
    private String contractProjectName;
    
    /**
     * 项目代号
     */
    private String projectCode;
    
    /**
     * 试验对象  
     * 整车、零部件
     */
    private String testObj;
    
    /**
     * 试验项目
     */
    private String testProject;
    
    /**
     * 试验标准 号
     */
    private String testStandard;
    
    /**
     * 试验标准 名
     */
    private String testStandardName;
    
    /**
     * 试验方法
     */
    private String testMethod;
    
    /***
     * 类别  
     * 3C，环保，认证
     */
    private String testType;
    /**
     * 折扣后的价格
     */
    private String supDiscountPrice;

    /**
     * 供应商管理id
     */
    private String supManagerId;
    /**
     * 供应商名
     */
    private String supName;
    
    private String delFlag;
    
    
    
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getContractProjectName() {
		return contractProjectName;
	}
	public void setContractProjectName(String contractProjectName) {
		this.contractProjectName = contractProjectName;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getTestObj() {
		return testObj;
	}
	public void setTestObj(String testObj) {
		this.testObj = testObj;
	}
	public String getTestProject() {
		return testProject;
	}
	public void setTestProject(String testProject) {
		this.testProject = testProject;
	}
	public String getTestStandard() {
		return testStandard;
	}
	public void setTestStandard(String testStandard) {
		this.testStandard = testStandard;
	}
	public String getTestStandardName() {
		return testStandardName;
	}
	public void setTestStandardName(String testStandardName) {
		this.testStandardName = testStandardName;
	}
	public String getTestMethod() {
		return testMethod;
	}
	public void setTestMethod(String testMethod) {
		this.testMethod = testMethod;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getSupDiscountPrice() {
		return supDiscountPrice;
	}
	public void setSupDiscountPrice(String supDiscountPrice) {
		this.supDiscountPrice = supDiscountPrice;
	}
	public String getSupManagerId() {
		return supManagerId;
	}
	public void setSupManagerId(String supManagerId) {
		this.supManagerId = supManagerId;
	}
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
    
    
    
}
