package com.adc.da.pc_announcement.VO;

import com.adc.da.base.entity.BaseEntity;

/**
 * <b>功能：</b>SUP_PROJECT ProjectEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-15 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SupProjectVO extends BaseEntity {

    private String id;
    private String supProjectId;
    private String supAbilityId;
    private String supUnit;
    private String supPrice;
    private String supDiscount;
    private String contractProjectName;
//    private InsProjectEO insProjectEO;
    
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
    
    /**
     * 系数
     */
    private String ratio;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>supProjectId -> sup_project_id</li>
     * <li>supAbilityId -> sup_ability_id</li>
     * <li>supUnit -> sup_unit</li>
     * <li>supPrice -> sup_price</li>
     * <li>supDiscount -> sup_discount</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "supProjectId": return "sup_project_id";
            case "supAbilityId": return "sup_ability_id";
            case "supUnit": return "sup_unit";
            case "supPrice": return "sup_price";
            case "supDiscount": return "sup_discount";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>sup_project_id -> supProjectId</li>
     * <li>sup_ability_id -> supAbilityId</li>
     * <li>sup_unit -> supUnit</li>
     * <li>sup_price -> supPrice</li>
     * <li>sup_discount -> supDiscount</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "sup_project_id": return "supProjectId";
            case "sup_ability_id": return "supAbilityId";
            case "sup_unit": return "supUnit";
            case "sup_price": return "supPrice";
            case "sup_discount": return "supDiscount";
            default: return null;
        }
    }
    
    /** id **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /**检验项目id  **/
    public String getSupProjectId() {
        return this.supProjectId;
    }

    /**  **/
    public void setSupProjectId(String supProjectId) {
        this.supProjectId = supProjectId;
    }

    /**供应商id  **/
    public String getSupAbilityId() {
        return this.supAbilityId;
    }

    /**  **/
    public void setSupAbilityId(String supAbilityId) {
        this.supAbilityId = supAbilityId;
    }

    /**单位  **/
    public String getSupUnit() {
        return this.supUnit;
    }

    /**  **/
    public void setSupUnit(String supUnit) {
        this.supUnit = supUnit;
    }

    /** 单价 **/
    public String getSupPrice() {
        return this.supPrice;
    }

    /**  **/
    public void setSupPrice(String supPrice) {
        this.supPrice = supPrice;
    }

    /**折扣  **/
    public String getSupDiscount() {
        return this.supDiscount;
    }

    /**  **/
    public void setSupDiscount(String supDiscount) {
        this.supDiscount = supDiscount;
    }


    /**
     * 检验项目
     * @return
     */


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

	public String getTestStandardName() {
		return testStandardName;
	}

	public void setTestStandardName(String testStandardName) {
		this.testStandardName = testStandardName;
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

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

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
    
    
    
}
