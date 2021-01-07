package com.adc.da.pc_execute.vo;

import com.adc.da.base.entity.BaseEntity;

public class PCBaseInitDataVO extends BaseEntity {
    /**
     *
     */
    private String id;

    /**
     *
     */
    private String delFlag;

    /**
     *
     */
    private String itemsName;

    /**
     *
     */
    private String price;

    /**
     *
     */
    private String unit;

    /**
     *
     */
    private String codeType;

    /**
     *
     */
    private Long initSort;

    /**
     *
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