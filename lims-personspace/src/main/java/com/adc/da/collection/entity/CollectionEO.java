package com.adc.da.collection.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>TP_COLLECTION CollectionEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-07-23 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class CollectionEO extends BaseEntity {

    private String id;
    private String collectiontime;
    private String sendtime;
    private String senduser;
    private String title;
    private String type;
    private String sendlink;
    private String ccCreateById;
    private String businessid;
    private Long delFlag;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>collectiontime -> collectiontime</li>
     * <li>sendtime -> sendtime</li>
     * <li>senduser -> senduser</li>
     * <li>title -> title</li>
     * <li>type -> type</li>
     * <li>sendlink -> sendlink</li>
     * <li>ccCreateById -> cc_create_by_id</li>
     * <li>businessid -> businessid</li>
     * <li>delFlag -> del_flag</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "collectiontime": return "collectiontime";
            case "sendtime": return "sendtime";
            case "senduser": return "senduser";
            case "title": return "title";
            case "type": return "type";
            case "sendlink": return "sendlink";
            case "ccCreateById": return "cc_create_by_id";
            case "businessid": return "businessid";
            case "delFlag": return "del_flag";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>collectiontime -> collectiontime</li>
     * <li>sendtime -> sendtime</li>
     * <li>senduser -> senduser</li>
     * <li>title -> title</li>
     * <li>type -> type</li>
     * <li>sendlink -> sendlink</li>
     * <li>cc_create_by_id -> ccCreateById</li>
     * <li>businessid -> businessid</li>
     * <li>del_flag -> delFlag</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "collectiontime": return "collectiontime";
            case "sendtime": return "sendtime";
            case "senduser": return "senduser";
            case "title": return "title";
            case "type": return "type";
            case "sendlink": return "sendlink";
            case "cc_create_by_id": return "ccCreateById";
            case "businessid": return "businessid";
            case "del_flag": return "delFlag";
            default: return null;
        }
    }
    
    /**id  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /**  收藏时间**/
    public String getCollectiontime() {
        return this.collectiontime;
    }

    /**  **/
    public void setCollectiontime(String collectiontime) {
        this.collectiontime = collectiontime;
    }

    /**开始时间  **/
    public String  getSendtime() {
        return this.sendtime;
    }

    /**  **/
    public void setSendtime(String  sendtime) {
        this.sendtime = sendtime;
    }

    /** 发起人 **/
    public String getSenduser() {
        return this.senduser;
    }

    /**  **/
    public void setSenduser(String senduser) {
        this.senduser = senduser;
    }

    /**  标题**/
    public String getTitle() {
        return this.title;
    }

    /**  **/
    public void setTitle(String title) {
        this.title = title;
    }

    /**类型  **/
    public String getType() {
        return this.type;
    }

    /**  **/
    public void setType(String type) {
        this.type = type;
    }

    /**连接（用于判断前端页面跳转）  **/
    public String getSendlink() {
        return this.sendlink;
    }

    /**  **/
    public void setSendlink(String sendlink) {
        this.sendlink = sendlink;
    }

    /**创建人  **/
    public String getCcCreateById() {
        return this.ccCreateById;
    }

    /**  **/
    public void setCcCreateById(String ccCreateById) {
        this.ccCreateById = ccCreateById;
    }

    /**业务主键（用来判断收藏类型和收藏页面）  **/
    public String getBusinessid() {
        return this.businessid;
    }

    /**  **/
    public void setBusinessid(String businessid) {
        this.businessid = businessid;
    }

    /** 删除标识 **/
    public Long getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(Long delFlag) {
        this.delFlag = delFlag;
    }

}
