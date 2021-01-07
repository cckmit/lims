package com.adc.da.equipment.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>RES_EQUIPMENT_SCRAP_RECORD EquipmentScrapRecordEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-28 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class EquipmentScrapRecordEO extends BaseEntity {

    @ApiModelProperty(" 设备状态 ")
    private String scrEquipmentStatus
;
    @ApiModelProperty(" 还原时间 ")
    private String scrRecoverReason
;
    @ApiModelProperty(" 还原时间 ")
    private String scrRecoverTime
;
    @ApiModelProperty(" 报废原因 ")
    private String scrScrapReason
;
    @ApiModelProperty(" 报废时间 ")
    private String scrScrapTime
;
    @ApiModelProperty(" 申请部门 ")
    private String scrApplicantDepartment
;
    @ApiModelProperty(" 申请人姓名 ")
    private String scrApplicantName;
    @ApiModelProperty(" 申请人ID ")
    private String scrApplicantId;
    @ApiModelProperty(" 设备ID ")
    private String eqId;
    @ApiModelProperty(" 报废还原ID ")
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>scrEquipmentStatus
 -> scr_equipment_status
</li>
     * <li>scrRecoverReason
 -> scr_recover_reason
</li>
     * <li>scrRecoverTime
 -> scr_recover_time
</li>
     * <li>scrScrapReason
 -> scr_scrap_reason
</li>
     * <li>scrScrapTime
 -> scr_scrap_time
</li>
     * <li>scrApplicantDepartment
 -> scr_applicant_department
</li>
     * <li>scrApplicantName -> scr_applicant_name</li>
     * <li>scrApplicantId -> scr_applicant_id</li>
     * <li>eqId -> eq_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) { return null; }
        switch (fieldName) {
            case "scrEquipmentStatus": return "scr_equipment_status";
            case "scrRecoverReason": return "scr_recover_reason";
            case "scrRecoverTime": return "scr_recover_time";
            case "scrScrapReason": return "scr_scrap_reason";
            case "scrScrapTime": return "scr_scrap_time";
            case "scrApplicantDepartment": return "scr_applicant_department";
            case "scrApplicantName": return "scr_applicant_name";
            case "scrApplicantId": return "scr_applicant_id";
            case "eqId": return "eq_id";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>scr_equipment_status
 -> scrEquipmentStatus
</li>
     * <li>scr_recover_reason
 -> scrRecoverReason
</li>
     * <li>scr_recover_time
 -> scrRecoverTime
</li>
     * <li>scr_scrap_reason
 -> scrScrapReason
</li>
     * <li>scr_scrap_time
 -> scrScrapTime
</li>
     * <li>scr_applicant_department
 -> scrApplicantDepartment
</li>
     * <li>scr_applicant_name -> scrApplicantName</li>
     * <li>scr_applicant_id -> scrApplicantId</li>
     * <li>eq_id -> eqId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) { return null; }
        switch (columnName) {
            case "scr_equipment_status": return "scrEquipmentStatus";
            case "scr_recover_reason": return "scrRecoverReason";
            case "scr_recover_time": return "scrRecoverTime";
            case "scr_scrap_reason": return "scrScrapReason";
            case "scr_scrap_time": return "scrScrapTime";
            case "scr_applicant_department": return "scrApplicantDepartment";
            case "scr_applicant_name": return "scrApplicantName";
            case "scr_applicant_id": return "scrApplicantId";
            case "eq_id": return "eqId";
            case "id": return "id";
            default: return null;
        }
    }
    
    public String getScrEquipmentStatus
() {
        return this.scrEquipmentStatus
;
    }

    public void setScrEquipmentStatus
(String scrEquipmentStatus
) {
        this.scrEquipmentStatus
 = scrEquipmentStatus
;
    }

    public String getScrRecoverReason
() {
        return this.scrRecoverReason
;
    }

    public void setScrRecoverReason
(String scrRecoverReason
) {
        this.scrRecoverReason
 = scrRecoverReason
;
    }

    public String getScrRecoverTime
() {
        return this.scrRecoverTime
;
    }

    public void setScrRecoverTime
(String scrRecoverTime
) {
        this.scrRecoverTime
 = scrRecoverTime
;
    }

    public String getScrScrapReason
() {
        return this.scrScrapReason
;
    }

    public void setScrScrapReason
(String scrScrapReason
) {
        this.scrScrapReason
 = scrScrapReason
;
    }

    public String getScrScrapTime
() {
        return this.scrScrapTime
;
    }

    public void setScrScrapTime
(String scrScrapTime
) {
        this.scrScrapTime
 = scrScrapTime
;
    }

    public String getScrApplicantDepartment
() {
        return this.scrApplicantDepartment
;
    }

    public void setScrApplicantDepartment
(String scrApplicantDepartment
) {
        this.scrApplicantDepartment
 = scrApplicantDepartment
;
    }

    public String getScrApplicantName() {
        return this.scrApplicantName;
    }

    public void setScrApplicantName(String scrApplicantName) {
        this.scrApplicantName = scrApplicantName;
    }

    public String getScrApplicantId() {
        return this.scrApplicantId;
    }

    public void setScrApplicantId(String scrApplicantId) {
        this.scrApplicantId = scrApplicantId;
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
