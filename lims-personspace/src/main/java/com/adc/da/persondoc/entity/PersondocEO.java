package com.adc.da.persondoc.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>TP_PERSONDOC PersondocEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-07-24 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PersondocEO extends BaseEntity {

    private String id;
    private String createById;
    private String creatTime;
    private String ccExtension;
    private String doctypeId;
    private String fileName;
    private String fileSize;
    private String attachmentid;
    private Integer delFlag;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>createById -> create_by_id</li>
     * <li>creatTime -> creat_time</li>
     * <li>ccExtension -> cc_extension</li>
     * <li>doctypeId -> doctype_id</li>
     * <li>fileName -> file_name</li>
     * <li>fileSize -> file_size</li>
     * <li>attachmentid -> attachmentid</li>
     * <li>delFlag -> del_flag</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "createById": return "create_by_id";
            case "creatTime": return "creat_time";
            case "ccExtension": return "cc_extension";
            case "doctypeId": return "doctype_id";
            case "fileName": return "file_name";
            case "fileSize": return "file_size";
            case "attachmentid": return "attachmentid";
            case "delFlag": return "del_flag";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>create_by_id -> createById</li>
     * <li>creat_time -> creatTime</li>
     * <li>cc_extension -> ccExtension</li>
     * <li>doctype_id -> doctypeId</li>
     * <li>file_name -> fileName</li>
     * <li>file_size -> fileSize</li>
     * <li>attachmentid -> attachmentid</li>
     * <li>del_flag -> delFlag</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "create_by_id": return "createById";
            case "creat_time": return "creatTime";
            case "cc_extension": return "ccExtension";
            case "doctype_id": return "doctypeId";
            case "file_name": return "fileName";
            case "file_size": return "fileSize";
            case "attachmentid": return "attachmentid";
            case "del_flag": return "delFlag";
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

    /**  **/
    public String getCreateById() {
        return this.createById;
    }

    /**  **/
    public void setCreateById(String createById) {
        this.createById = createById;
    }

    /**  **/
    public String getCreatTime() {
        return this.creatTime;
    }

    /**  **/
    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    /**  **/
    public String getCcExtension() {
        return this.ccExtension;
    }

    /**  **/
    public void setCcExtension(String ccExtension) {
        this.ccExtension = ccExtension;
    }

    /**  **/
    public String getDoctypeId() {
        return this.doctypeId;
    }

    /**  **/
    public void setDoctypeId(String doctypeId) {
        this.doctypeId = doctypeId;
    }

    /**  **/
    public String getFileName() {
        return this.fileName;
    }

    /**  **/
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**  **/
    public String getFileSize() {
        return this.fileSize;
    }

    /**  **/
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**  **/
    public String getAttachmentid() {
        return this.attachmentid;
    }

    /**  **/
    public void setAttachmentid(String attachmentid) {
        this.attachmentid = attachmentid;
    }

    /**  **/
    public Integer getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

}
