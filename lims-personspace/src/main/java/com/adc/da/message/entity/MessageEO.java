package com.adc.da.message.entity;

import com.adc.da.base.entity.BaseEntity;

import java.io.Serializable;


/**
 * <b>功能：</b>TP_MESSAGE MessageEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-07-23 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class MessageEO extends BaseEntity implements Serializable {

    private String id;
    private String isread;
    private String sendlink;
    private String sendtime;
    private String senduser;
    private String title;
    private String ccCreateById;
    private String businessId;

    public MessageEO(){

    }
    public MessageEO(String id, String isread, String sendlink, String sendtime, String senduser, String title, String ccCreateById, String businessId) {
        this.isread = isread;
        this.sendlink = sendlink;
        this.sendtime = sendtime;
        this.senduser = senduser;
        this.title = title;
        this.ccCreateById = ccCreateById;
        this.businessId = businessId;
        this.id = id;
    }

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>isread -> isread</li>
     * <li>sendlink -> sendlink</li>
     * <li>sendtime -> sendtime</li>
     * <li>senduser -> senduser</li>
     * <li>title -> title</li>
     * <li>ccCreateById -> cc_create_by_id</li>
     * <li>businessId -> business_id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "isread": return "isread";
            case "sendlink": return "sendlink";
            case "sendtime": return "sendtime";
            case "senduser": return "senduser";
            case "title": return "title";
            case "ccCreateById": return "cc_create_by_id";
            case "businessId": return "business_id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>isread -> isread</li>
     * <li>sendlink -> sendlink</li>
     * <li>sendtime -> sendtime</li>
     * <li>senduser -> senduser</li>
     * <li>title -> title</li>
     * <li>cc_create_by_id -> ccCreateById</li>
     * <li>business_id -> businessId</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "isread": return "isread";
            case "sendlink": return "sendlink";
            case "sendtime": return "sendtime";
            case "senduser": return "senduser";
            case "title": return "title";
            case "cc_create_by_id": return "ccCreateById";
            case "business_id": return "businessId";
            default: return null;
        }
    }
    
    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /** 是否已读   0未读   1已读 **/
    public String getIsread() {
        return this.isread;
    }

    /**  **/
    public void setIsread(String isread) {
        this.isread = isread;
    }

    /** url   用于查看 **/
    public String getSendlink() {
        return this.sendlink;
    }

    /**  **/
    public void setSendlink(String sendlink) {
        this.sendlink = sendlink;
    }

    /** 通知时间 **/
    public String getSendtime() {
        return this.sendtime;
    }

    /**  **/
    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    /**发起人  **/
    public String getSenduser() {
        return this.senduser;
    }

    /**  **/
    public void setSenduser(String senduser) {
        this.senduser = senduser;
    }

    /**标题  **/
    public String getTitle() {
        return this.title;
    }

    /**  **/
    public void setTitle(String title) {
        this.title = title;
    }

    /**  接收人**/
    public String getCcCreateById() {
        return this.ccCreateById;
    }

    /**  **/
    public void setCcCreateById(String ccCreateById) {
        this.ccCreateById = ccCreateById;
    }

    /** 业务id **/
    public String getBusinessId() {
        return this.businessId;
    }

    /**  **/
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

}
