package com.adc.da.persondoc.page;

import com.adc.da.base.page.BasePage;


/**
 * <b>功能：</b>TP_PERSONDOC_TYPE PersondocTypeEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-07-24 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PersondocTypeEOPage extends BasePage {

    private String id;
    private String idOperator = "=";
    private String delFlag;
    private String delFlagOperator = "=";
    private String typeParentid;
    private String typeParentidOperator = "=";
    private String sort;
    private String sortOperator = "=";
    private String typeCode;
    private String typeCodeOperator = "=";
    private String typeName;
    private String typeNameOperator = "=";

    private String createById;

    public String getCreateById() {
        return createById;
    }

    public void setCreateById(String createById) {
        this.createById = createById;
    }

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

    public String getTypeParentid() {
        return this.typeParentid;
    }

    public void setTypeParentid(String typeParentid) {
        this.typeParentid = typeParentid;
    }

    public String getTypeParentidOperator() {
        return this.typeParentidOperator;
    }

    public void setTypeParentidOperator(String typeParentidOperator) {
        this.typeParentidOperator = typeParentidOperator;
    }

    public String getSort() {
        return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortOperator() {
        return this.sortOperator;
    }

    public void setSortOperator(String sortOperator) {
        this.sortOperator = sortOperator;
    }

    public String getTypeCode() {
        return this.typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeCodeOperator() {
        return this.typeCodeOperator;
    }

    public void setTypeCodeOperator(String typeCodeOperator) {
        this.typeCodeOperator = typeCodeOperator;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeNameOperator() {
        return this.typeNameOperator;
    }

    public void setTypeNameOperator(String typeNameOperator) {
        this.typeNameOperator = typeNameOperator;
    }

}
