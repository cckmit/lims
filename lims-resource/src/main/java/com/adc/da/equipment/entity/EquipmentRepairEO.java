package com.adc.da.equipment.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>RES_EQUIPMENT_REPAIR EquipmentRepairEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-29 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class EquipmentRepairEO extends BaseEntity {

    @ApiModelProperty(" 附件ID ")
    private String repAttachmentId;
    @ApiModelProperty(" 附件名称 ")
    private String repAttachmentName;
    @ApiModelProperty(" 维修内容 ")
    private String repMaintenanceContent;
    @ApiModelProperty(" 故障内容 ")
    private String repFaultContent;
    @ApiModelProperty(" 维修价格 ")
    private String repMaintenancePrice;
    @ApiModelProperty(" 维修日期 ")
    private String repMaintenanceDate;
    @ApiModelProperty(" 维修人员/厂商 ")
    private String repMaintenanceStaff;
    @ApiModelProperty(" 故障日期 ")
    private String repFaultDate;
    @ApiModelProperty(" 负责人 ")
    private String repPrincipal;
    @ApiModelProperty(" 设备名称 ")
    private String eqName;
    @ApiModelProperty(" 设备编码 ")
    private String eqNo;
    @ApiModelProperty(" 设备Id ")
    private String eqId;
    @ApiModelProperty(" 维修表Id ")
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>repAttachmentId -> rep_attachment_id</li>
     * <li>repAttachmentName -> rep_attachment_name</li>
     * <li>repMaintenanceContent -> rep_maintenance_content</li>
     * <li>repFaultContent -> rep_fault_content</li>
     * <li>repMaintenancePrice -> rep_maintenance_price</li>
     * <li>repMaintenanceDate -> rep_maintenance_date</li>
     * <li>repMaintenanceStaff -> rep_maintenance_staff</li>
     * <li>repFaultDate -> rep_fault_date</li>
     * <li>repPrincipal -> rep_principal</li>
     * <li>eqName -> eq_name</li>
     * <li>eqNo -> eq_no</li>
     * <li>eqId -> eq_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) { return null; }
        switch (fieldName) {
            case "repAttachmentId": return "rep_attachment_id";
            case "repAttachmentName": return "rep_attachment_name";
            case "repMaintenanceContent": return "rep_maintenance_content";
            case "repFaultContent": return "rep_fault_content";
            case "repMaintenancePrice": return "rep_maintenance_price";
            case "repMaintenanceDate": return "rep_maintenance_date";
            case "repMaintenanceStaff": return "rep_maintenance_staff";
            case "repFaultDate": return "rep_fault_date";
            case "repPrincipal": return "rep_principal";
            case "eqName": return "eq_name";
            case "eqNo": return "eq_no";
            case "eqId": return "eq_id";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>rep_attachment_id -> repAttachmentId</li>
     * <li>rep_attachment_name -> repAttachmentName</li>
     * <li>rep_maintenance_content -> repMaintenanceContent</li>
     * <li>rep_fault_content -> repFaultContent</li>
     * <li>rep_maintenance_price -> repMaintenancePrice</li>
     * <li>rep_maintenance_date -> repMaintenanceDate</li>
     * <li>rep_maintenance_staff -> repMaintenanceStaff</li>
     * <li>rep_fault_date -> repFaultDate</li>
     * <li>rep_principal -> repPrincipal</li>
     * <li>eq_name -> eqName</li>
     * <li>eq_no -> eqNo</li>
     * <li>eq_id -> eqId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) { return null; }
        switch (columnName) {
            case "rep_attachment_id": return "repAttachmentId";
            case "rep_attachment_name": return "repAttachmentName";
            case "rep_maintenance_content": return "repMaintenanceContent";
            case "rep_fault_content": return "repFaultContent";
            case "rep_maintenance_price": return "repMaintenancePrice";
            case "rep_maintenance_date": return "repMaintenanceDate";
            case "rep_maintenance_staff": return "repMaintenanceStaff";
            case "rep_fault_date": return "repFaultDate";
            case "rep_principal": return "repPrincipal";
            case "eq_name": return "eqName";
            case "eq_no": return "eqNo";
            case "eq_id": return "eqId";
            case "id": return "id";
            default: return null;
        }
    }
    
    public String getRepAttachmentId() {
        return this.repAttachmentId;
    }

    public void setRepAttachmentId(String repAttachmentId) {
        this.repAttachmentId = repAttachmentId;
    }

    public String getRepAttachmentName() {
        return this.repAttachmentName;
    }

    public void setRepAttachmentName(String repAttachmentName) {
        this.repAttachmentName = repAttachmentName;
    }

    public String getRepMaintenanceContent() {
        return this.repMaintenanceContent;
    }

    public void setRepMaintenanceContent(String repMaintenanceContent) {
        this.repMaintenanceContent = repMaintenanceContent;
    }

    public String getRepFaultContent() {
        return this.repFaultContent;
    }

    public void setRepFaultContent(String repFaultContent) {
        this.repFaultContent = repFaultContent;
    }

    public String getRepMaintenancePrice() {
        return this.repMaintenancePrice;
    }

    public void setRepMaintenancePrice(String repMaintenancePrice) {
        this.repMaintenancePrice = repMaintenancePrice;
    }

    public String getRepMaintenanceDate() {
        return this.repMaintenanceDate;
    }

    public void setRepMaintenanceDate(String repMaintenanceDate) {
        this.repMaintenanceDate = repMaintenanceDate;
    }

    public String getRepMaintenanceStaff() {
        return this.repMaintenanceStaff;
    }

    public void setRepMaintenanceStaff(String repMaintenanceStaff) {
        this.repMaintenanceStaff = repMaintenanceStaff;
    }

    public String getRepFaultDate() {
        return this.repFaultDate;
    }

    public void setRepFaultDate(String repFaultDate) {
        this.repFaultDate = repFaultDate;
    }

    public String getRepPrincipal() {
        return this.repPrincipal;
    }

    public void setRepPrincipal(String repPrincipal) {
        this.repPrincipal = repPrincipal;
    }

    public String getEqName() {
        return this.eqName;
    }

    public void setEqName(String eqName) {
        this.eqName = eqName;
    }

    public String getEqNo() {
        return this.eqNo;
    }

    public void setEqNo(String eqNo) {
        this.eqNo = eqNo;
    }

    public String getEqId() {
        return this.eqId;
    }

    public void setEqId(String eqId) {
        this.eqId = eqId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
