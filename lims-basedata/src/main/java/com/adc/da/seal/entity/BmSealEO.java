package com.adc.da.seal.entity;

import com.adc.da.base.entity.BaseEntity;

/**
 * <b>功能：</b>BM_SEAL BmSealEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-01 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class BmSealEO extends BaseEntity {

    private String id;
    private String sealName;
    private String sealType;
    private String sealStyle;
    private String sealFileId;
    private String sealFileExtend;
    private String createById;
    private String createTime;
    private String delFlag;
    private String sealAxesx;
    private String sealAxesy;
    private String sealLengths;
    private String sealStatus;
    private String sealWidths;
    private String sealWords;
    private String sealCode;
    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>sealName -> seal_name</li>
     * <li>sealType -> seal_type</li>
     * <li>sealStyle -> seal_style</li>
     * <li>sealFileId -> seal_file_id</li>
     * <li>sealFileExtend -> seal_file_extend</li>
     * <li>createById -> create_by_id</li>
     * <li>createTime -> create_time</li>
     * <li>del -> del</li>
     * <li>sealAxesx -> seal_axesx</li>
     * <li>sealAxesy -> seal_axesy</li>
     * <li>sealLengths -> seal_lengths</li>
     * <li>sealStatus -> seal_status</li>
     * <li>sealWidths -> seal_widths</li>
     * <li>sealWords -> seal_words</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "sealName": return "seal_name";
            case "sealType": return "seal_type";
            case "sealStyle": return "seal_style";
            case "sealFileId": return "seal_file_id";
            case "sealFileExtend": return "seal_file_extend";
            case "createById": return "create_by_id";
            case "createTime": return "create_time";
            case "del": return "del";
            case "sealAxesx": return "seal_axesx";
            case "sealAxesy": return "seal_axesy";
            case "sealLengths": return "seal_lengths";
            case "sealStatus": return "seal_status";
            case "sealWidths": return "seal_widths";
            case "sealWords": return "seal_words";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>seal_name -> sealName</li>
     * <li>seal_type -> sealType</li>
     * <li>seal_style -> sealStyle</li>
     * <li>seal_file_id -> sealFileId</li>
     * <li>seal_file_extend -> sealFileExtend</li>
     * <li>create_by_id -> createById</li>
     * <li>create_time -> createTime</li>
     * <li>del -> del</li>
     * <li>seal_axesx -> sealAxesx</li>
     * <li>seal_axesy -> sealAxesy</li>
     * <li>seal_lengths -> sealLengths</li>
     * <li>seal_status -> sealStatus</li>
     * <li>seal_widths -> sealWidths</li>
     * <li>seal_words -> sealWords</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "seal_name": return "sealName";
            case "seal_type": return "sealType";
            case "seal_style": return "sealStyle";
            case "seal_file_id": return "sealFileId";
            case "seal_file_extend": return "sealFileExtend";
            case "create_by_id": return "createById";
            case "create_time": return "createTime";
            case "del": return "del";
            case "seal_axesx": return "sealAxesx";
            case "seal_axesy": return "sealAxesy";
            case "seal_lengths": return "sealLengths";
            case "seal_status": return "sealStatus";
            case "seal_widths": return "sealWidths";
            case "seal_words": return "sealWords";
            default: return null;
        }
    }
    
    /** id **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /**公章名称  **/
    public String getSealName() {
        return this.sealName;
    }

    /**  **/
    public void setSealName(String sealName) {
        this.sealName = sealName;
    }

    /**公章类型  **/
    public String getSealType() {
        return this.sealType;
    }

    /**  **/
    public void setSealType(String sealType) {
        this.sealType = sealType;
    }

    /**样式  **/
    public String getSealStyle() {
        return this.sealStyle;
    }

    /**  **/
    public void setSealStyle(String sealStyle) {
        this.sealStyle = sealStyle;
    }

    /**图片在附件表的id  **/
    public String getSealFileId() {
        return this.sealFileId;
    }

    /**  **/
    public void setSealFileId(String sealFileId) {
        this.sealFileId = sealFileId;
    }

    /**  拓展名**/
    public String getSealFileExtend() {
        return this.sealFileExtend;
    }

    /**  **/
    public void setSealFileExtend(String sealFileExtend) {
        this.sealFileExtend = sealFileExtend;
    }

    /** 创建人id **/
    public String getCreateById() {
        return this.createById;
    }

    /**  **/
    public void setCreateById(String createById) {
        this.createById = createById;
    }

    /** 创建时间 **/
    public String getCreateTime() {
        return this.createTime;
    }

    /**  **/
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /** 删除状态 **/
    public String getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /** 坐标x **/
    public String getSealAxesx() {
        return this.sealAxesx;
    }

    /**  **/
    public void setSealAxesx(String sealAxesx) {
        this.sealAxesx = sealAxesx;
    }

    /**坐标y  **/
    public String getSealAxesy() {
        return this.sealAxesy;
    }

    /**  **/
    public void setSealAxesy(String sealAxesy) {
        this.sealAxesy = sealAxesy;
    }

    /** 长 **/
    public String getSealLengths() {
        return this.sealLengths;
    }

    /**  **/
    public void setSealLengths(String sealLengths) {
        this.sealLengths = sealLengths;
    }

    /**  启用状态**/
    public String getSealStatus() {
        return this.sealStatus;
    }

    /**  **/
    public void setSealStatus(String sealStatus) {
        this.sealStatus = sealStatus;
    }

    /**宽  **/
    public String getSealWidths() {
        return this.sealWidths;
    }

    /**  **/
    public void setSealWidths(String sealWidths) {
        this.sealWidths = sealWidths;
    }

    /**文字  **/
    public String getSealWords() {
        return this.sealWords;
    }

    /**  **/
    public void setSealWords(String sealWords) {
        this.sealWords = sealWords;
    }

    public String getSealCode() {
        return sealCode;
    }

    public void setSealCode(String sealCode) {
        this.sealCode = sealCode;
    }
}
