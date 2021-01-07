package com.adc.da.sys.page;

import java.util.List;

import com.adc.da.base.page.BasePage;

/**
 * <b>功能：</b>TS_ROLE RoleEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-11-06 <br>
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 */
public class RoleEOPage extends BasePage {
	
	
	private List<String> searchPhrase;
	
	private String rcodeOperator;
	
	private String rcode;

    private String id;

    private String idOperator = "=";

    private String dataScope;

    private String dataScopeOperator = "=";

    private String delFlag;

    private String delFlagOperator = "=";

    private String isDefault;

    private String isDefaultOperator = "=";

    private String name;

    private String nameOperator = "=";

    private String remarks;

    private String remarksOperator = "=";

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOperator() {
        return this.idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

    public String getDataScope() {
        return this.dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public String getDataScopeOperator() {
        return this.dataScopeOperator;
    }

    public void setDataScopeOperator(String dataScopeOperator) {
        this.dataScopeOperator = dataScopeOperator;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlagOperator() {
        return this.delFlagOperator;
    }

    public void setDelFlagOperator(String delFlagOperator) {
        this.delFlagOperator = delFlagOperator;
    }

    public String getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsDefaultOperator() {
        return this.isDefaultOperator;
    }

    public void setIsDefaultOperator(String isDefaultOperator) {
        this.isDefaultOperator = isDefaultOperator;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOperator() {
        return this.nameOperator;
    }

    public void setNameOperator(String nameOperator) {
        this.nameOperator = nameOperator;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarksOperator() {
        return this.remarksOperator;
    }

    public void setRemarksOperator(String remarksOperator) {
        this.remarksOperator = remarksOperator;
    }

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	public String getRcodeOperator() {
		return rcodeOperator;
	}

	public void setRcodeOperator(String rcodeOperator) {
		this.rcodeOperator = rcodeOperator;
	}

	public List<String> getSearchPhrase() {
		return searchPhrase;
	}

	public void setSearchPhrase(List<String> searchPhrase) {
		this.searchPhrase = searchPhrase;
	}


}
