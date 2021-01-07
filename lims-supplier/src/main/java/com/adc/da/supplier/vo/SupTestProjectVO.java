package com.adc.da.supplier.vo;

public class SupTestProjectVO {
	/**
	 * 主键
	 */
	private String id;
    /**
     * 试验项目
     */
    private String testProject;
    /**
     * 供应商管理id
     */
    private String supManagerId;
    /**
     * 供应商名
     */
    private String supName;
    
    /**
     * 单价
     */
    private String supPrice;
    /**
     * 折扣
     */
    private String supDiscount;
    /**
     * 折扣后的钱
     */
    private	String supDiscountPrice;
    
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
     * 系数
     */
    private String ratio;
    
    /**
     * 备注
     */
    private String remark;
    
    
    
	public String getRatio() {
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTestProject() {
		return testProject;
	}
	public void setTestProject(String testProject) {
		this.testProject = testProject;
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
	public String getSupPrice() {
		return supPrice;
	}
	public void setSupPrice(String supPrice) {
		this.supPrice = supPrice;
	}
	public String getSupDiscount() {
		return supDiscount;
	}
	public void setSupDiscount(String supDiscount) {
		this.supDiscount = supDiscount;
	}
	public String getSupDiscountPrice() {
		return supDiscountPrice;
	}
	public void setSupDiscountPrice(String supDiscountPrice) {
		this.supDiscountPrice = supDiscountPrice;
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
    
    
}
