package com.adc.da.pc_drive.vo;

import com.adc.da.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * <b>功能：</b>SUP_ABILITY AbilityEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-15 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbilityVO extends BaseEntity {

    private String supName;
    private String id;
    private String supType;
    private String supQualifications;
    private String supIndate;
    private String supChangelog;
    private String supContractCode;
    private String supConStartdate;
    private String supConEnddate;
    private String supManagerName;
    private String supTelephone;
    private String supMail;
    private String supManagerId;
    private String delFlag;
    private String attachmentId;
    private String attachmentName;
    private String supCode;


    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>supName -> sup_name</li>
     * <li>id -> id</li>
     * <li>supType -> sup_type</li>
     * <li>supQualifications -> sup_qualifications</li>
     * <li>supIndate -> sup_indate</li>
     * <li>supChangelog -> sup_changelog</li>
     * <li>supContractCode -> sup_contract_code</li>
     * <li>supConStartdate -> sup_con_startdate</li>
     * <li>supConEnddate -> sup_con_enddate</li>
     * <li>supManagerName -> sup_manager_name</li>
     * <li>supTelphone -> sup_telphone</li>
     * <li>supMail -> sup_mail</li>
     * <li>supManagerId -> sup_manager_id</li>
     * <li>delFlag -> del_flag</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "supName": return "sup_name";
            case "id": return "id";
            case "supType": return "sup_type";
            case "supQualifications": return "sup_qualifications";
            case "supIndate": return "sup_indate";
            case "supChangelog": return "sup_changelog";
            case "supContractCode": return "sup_contract_code";
            case "supConStartdate": return "sup_con_startdate";
            case "supConEnddate": return "sup_con_enddate";
            case "supManagerName": return "sup_manager_name";
            case "supTelephone": return "sup_telephone";
            case "supMail": return "sup_mail";
            case "supManagerId": return "sup_manager_id";
            case "delFlag": return "del_flag";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>sup_name -> supName</li>
     * <li>id -> id</li>
     * <li>sup_type -> supType</li>
     * <li>sup_qualifications -> supQualifications</li>
     * <li>sup_indate -> supIndate</li>
     * <li>sup_changelog -> supChangelog</li>
     * <li>sup_contract_code -> supContractCode</li>
     * <li>sup_con_startdate -> supConStartdate</li>
     * <li>sup_con_enddate -> supConEnddate</li>
     * <li>sup_manager_name -> supManagerName</li>
     * <li>sup_telphone -> supTelphone</li>
     * <li>sup_mail -> supMail</li>
     * <li>sup_manager_id -> supManagerId</li>
     * <li>del_flag -> delFlag</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "sup_name": return "supName";
            case "id": return "id";
            case "sup_type": return "supType";
            case "sup_qualifications": return "supQualifications";
            case "sup_indate": return "supIndate";
            case "sup_changelog": return "supChangelog";
            case "sup_contract_code": return "supContractCode";
            case "sup_con_startdate": return "supConStartdate";
            case "sup_con_enddate": return "supConEnddate";
            case "sup_manager_name": return "supManagerName";
            case "sup_telephone": return "supTelephone";
            case "sup_mail": return "supMail";
            case "sup_manager_id": return "supManagerId";
            case "del_flag": return "delFlag";
            default: return null;
        }
    }
    
    /** 供应商名 **/
    public String getSupName() {
        return this.supName;
    }

    /**  **/
    public void setSupName(String supName) {
        this.supName = supName;
    }

    /** id **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /** 类型  **/
    public String getSupType() {
        return this.supType;
    }

    /**  **/
    public void setSupType(String supType) {
        this.supType = supType;
    }

    /** 资质 **/
    public String getSupQualifications() {
        return this.supQualifications;
    }

    /**  **/
    public void setSupQualifications(String supQualifications) {
        this.supQualifications = supQualifications;
    }

    /** 入库时间 **/
    public String getSupIndate() {
        return this.supIndate;
    }

    /**  **/
    public void setSupIndate(String supIndate) {
        this.supIndate = supIndate;
    }

    /** 变更日志 **/
    public String getSupChangelog() {
        return this.supChangelog;
    }

    /**  **/
    public void setSupChangelog(String supChangelog) {
        this.supChangelog = supChangelog;
    }

    /** 合同编号 **/
    public String getSupContractCode() {
        return this.supContractCode;
    }

    /**  **/
    public void setSupContractCode(String supContractCode) {
        this.supContractCode = supContractCode;
    }

    /** 合同开始时间 **/
    public String getSupConStartdate() {
        return this.supConStartdate;
    }

    /**  **/
    public void setSupConStartdate(String supConStartdate) {
        this.supConStartdate = supConStartdate;
    }

    /** 合同结束时间 **/
    public String getSupConEnddate() {
        return this.supConEnddate;
    }

    /**  **/
    public void setSupConEnddate(String supConEnddate) {
        this.supConEnddate = supConEnddate;
    }

    /** 供应商管理人名**/
    public String getSupManagerName() {
        return this.supManagerName;
    }

    /**  **/
    public void setSupManagerName(String supManagerName) {
        this.supManagerName = supManagerName;
    }

    /** 联系电话 **/
    public String getSupTelephone() {
        return this.supTelephone;
    }

    /**  **/
    public void setSupTelephone(String supTelephone) {
        this.supTelephone = supTelephone;
    }

    /** 邮件 **/
    public String getSupMail() {
        return this.supMail;
    }

    /**  **/
    public void setSupMail(String supMail) {
        this.supMail = supMail;
    }

    /**供应商管理id  **/
    public String getSupManagerId() {
        return this.supManagerId;
    }

    /**  **/
    public void setSupManagerId(String supManagerId) {
        this.supManagerId = supManagerId;
    }

    /** 删除标志  0：存在    1：删除**/
    public String getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 供应商编码
     * @return
     */
    public String getSupCode() {
        return supCode;
    }

    public void setSupCode(String supCode) {
        this.supCode = supCode;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
}
