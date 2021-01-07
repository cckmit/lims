package com.adc.da.pc_execute.page;

import java.util.List;

import com.adc.da.base.page.BasePage;

public class PCBaseInitDataEOPage extends BasePage {
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
     */
    private String codeType;
    
    /**
     * 通用查询条件
     */
    private List<String> searchPhrase;

    
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

	public List<String> getSearchPhrase() {
		return searchPhrase;
	}

	public void setSearchPhrase(List<String> searchPhrase) {
		this.searchPhrase = searchPhrase;
	}
    
}
