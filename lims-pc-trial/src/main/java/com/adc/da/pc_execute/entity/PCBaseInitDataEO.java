package com.adc.da.pc_execute.entity;

import com.adc.da.base.entity.BaseEntity;

public class PCBaseInitDataEO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *删除标记  0-未删除; 1-已删除
     */
    private String delFlag;

    /**
     *名称
     */
    private String itemsName;

    /**
     *单价(标准)
     */
    private String price;

    /**
     *单位
     */
    private String unit;

    /**
     *类型code  
     *PCBudgetPerson  试验人员及住宿预算
     *PCBudgetSubsidy  试验补贴预算
     *PCBudgetTest  试验费用预算
     */
    private String codeType;

    /**
     *序号
     */
    private Long initSort;

    /**
     *名称code
     */
    private String itemsCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getItemsName() {
		return itemsName;
	}

	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public Long getInitSort() {
		return initSort;
	}

	public void setInitSort(Long initSort) {
		this.initSort = initSort;
	}

	public String getItemsCode() {
		return itemsCode;
	}

	public void setItemsCode(String itemsCode) {
		this.itemsCode = itemsCode;
	}

    
    
    
}