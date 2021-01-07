package com.adc.da.persondoc.entity;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.common.PrimaryGenerater;
import com.adc.da.login.util.UserUtils;

import java.security.PrivateKey;
import java.util.List;


/**
 * <b>功能：</b>TP_PERSONDOC_TYPE PersondocTypeEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-07-24 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PersondocTypeEO extends BaseEntity {

    private String id;
    private Long delFlag = 0l;
    private String typeParentid;
    private String sort;
    private String typeCode;
    private String typeName;
    private String createById = UserUtils.getUserId();
    private List<PersondocTypeEO> typeList;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>delFlag -> del_flag</li>
     * <li>typeParentid -> type_parentid</li>
     * <li>sort -> sort</li>
     * <li>typeCode -> type_code</li>
     * <li>typeName -> type_name</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "delFlag": return "del_flag";
            case "typeParentid": return "type_parentid";
            case "sort": return "sort";
            case "typeCode": return "type_code";
            case "typeName": return "type_name";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>del_flag -> delFlag</li>
     * <li>type_parentid -> typeParentid</li>
     * <li>sort -> sort</li>
     * <li>type_code -> typeCode</li>
     * <li>type_name -> typeName</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "del_flag": return "delFlag";
            case "type_parentid": return "typeParentid";
            case "sort": return "sort";
            case "type_code": return "typeCode";
            case "type_name": return "typeName";
            default: return null;
        }
    }

    public String getCreateById() {
        return createById;
    }

    public void setCreateById(String createById) {
        this.createById = createById;
    }

    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /**  **/
    public Long getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(Long delFlag) {
        this.delFlag = delFlag;
    }

    /**  **/
    public String getTypeParentid() {
        return this.typeParentid;
    }

    /**  **/
    public void setTypeParentid(String typeParentid) {
        this.typeParentid = typeParentid;
    }

    /**  **/
    public String getSort() {
        return this.sort;
    }

    /**  **/
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**  **/
    public String getTypeCode() {
        return this.typeCode;
    }

    /**  **/
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**  **/
    public String getTypeName() {
        return this.typeName;
    }

    /**  **/
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<PersondocTypeEO> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<PersondocTypeEO> typeList) {
        this.typeList = typeList;
    }
}
