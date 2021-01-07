package com.adc.da.calender.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>TP_PERSON_CALENDER PersonCalenderEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-01 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PersonCalenderEO extends BaseEntity {

    private String id;
    private String endremind;
    private String endtime;
    private String startremind;
    private String starttime;
    private String title;
    private String ccCreateById;
    private String isEndre;
    private String isStartre;
    private String content;

    public PersonCalenderEO() {
    }

    public PersonCalenderEO(String id, String starttime, String title, String ccCreateById, String content) {
        this.id = id;
        this.starttime = starttime;
        this.title = title;
        this.ccCreateById = ccCreateById;
        this.content = content;
    }

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>endremind -> endremind</li>
     * <li>endtime -> endtime</li>
     * <li>startremind -> startremind</li>
     * <li>starttime -> starttime</li>
     * <li>title -> title</li>
     * <li>ccCreateById -> cc_create_by_id</li>
     * <li>isEndre -> is_endre</li>
     * <li>isStartre -> is_startre</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "endremind": return "endremind";
            case "endtime": return "endtime";
            case "startremind": return "startremind";
            case "starttime": return "starttime";
            case "title": return "title";
            case "ccCreateById": return "cc_create_by_id";
            case "isEndre": return "is_endre";
            case "isStartre": return "is_startre";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>endremind -> endremind</li>
     * <li>endtime -> endtime</li>
     * <li>startremind -> startremind</li>
     * <li>starttime -> starttime</li>
     * <li>title -> title</li>
     * <li>cc_create_by_id -> ccCreateById</li>
     * <li>is_endre -> isEndre</li>
     * <li>is_startre -> isStartre</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "endremind": return "endremind";
            case "endtime": return "endtime";
            case "startremind": return "startremind";
            case "starttime": return "starttime";
            case "title": return "title";
            case "cc_create_by_id": return "ccCreateById";
            case "is_endre": return "isEndre";
            case "is_startre": return "isStartre";
            default: return null;
        }
    }
    
    /** 主键 **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /**结束前提醒时间  **/
    public String getEndremind() {
        return this.endremind;
    }

    /**  **/
    public void setEndremind(String endremind) {
        this.endremind = endremind;
    }

    /** 结束时间 **/
    public String getEndtime() {
        return this.endtime;
    }

    /**  **/
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    /**开始前提醒时间  **/
    public String getStartremind() {
        return this.startremind;
    }

    /**  **/
    public void setStartremind(String startremind) {
        this.startremind = startremind;
    }

    /** 开始时间 **/
    public String getStarttime() {
        return this.starttime;
    }

    /**  **/
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    /**标题  **/
    public String getTitle() {
        return this.title;
    }

    /**  **/
    public void setTitle(String title) {
        this.title = title;
    }

    /**接收人  **/
    public String getCcCreateById() {
        return this.ccCreateById;
    }

    /**  **/
    public void setCcCreateById(String ccCreateById) {
        this.ccCreateById = ccCreateById;
    }

    /**是否提醒  **/
    public String getIsEndre() {
        return this.isEndre;
    }

    /**  **/
    public void setIsEndre(String isEndre) {
        this.isEndre = isEndre;
    }

    /**是否提醒  **/
    public String getIsStartre() {
        return this.isStartre;
    }

    /**  **/
    public void setIsStartre(String isStartre) {
        this.isStartre = isStartre;
    }

    /**
     * 内容
     * @return
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
