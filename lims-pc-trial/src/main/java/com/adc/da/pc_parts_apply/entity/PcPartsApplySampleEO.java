package com.adc.da.pc_parts_apply.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>PC_PARTS_APPLY_SAMPLE PcPartsApplySampleEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-13 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcPartsApplySampleEO extends BaseEntity {

    private String id;
    @ApiModelProperty("物资名称")
    private String partName = "";
    @ApiModelProperty("规格型号")
    private String partType = "";
    @ApiModelProperty("单位")
    private String partUnit = "";
    @ApiModelProperty("总数")
    private String partTotal = "0";
    @ApiModelProperty("备注")
    private String remark = "";
    @ApiModelProperty("是否删除")
    private String delFlag;
    @ApiModelProperty("领料单编号")
    private String partApplyId;
    @ApiModelProperty("零部件ID")
    private String partId;


    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>partName -> part_name</li>
     * <li>partType -> part_type</li>
     * <li>partUnit -> part_unit</li>
     * <li>partTotal -> part_total</li>
     * <li>remark -> remark</li>
     * <li>delFlag -> del_flag</li>
     * <li>partApplyId -> part_apply_id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "partName": return "part_name";
            case "partType": return "part_type";
            case "partUnit": return "part_unit";
            case "partTotal": return "part_total";
            case "remark": return "remark";
            case "delFlag": return "del_flag";
            case "partApplyId": return "part_apply_id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>part_name -> partName</li>
     * <li>part_type -> partType</li>
     * <li>part_unit -> partUnit</li>
     * <li>part_total -> partTotal</li>
     * <li>remark -> remark</li>
     * <li>del_flag -> delFlag</li>
     * <li>part_apply_id -> partApplyId</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "part_name": return "partName";
            case "part_type": return "partType";
            case "part_unit": return "partUnit";
            case "part_total": return "partTotal";
            case "remark": return "remark";
            case "del_flag": return "delFlag";
            case "part_apply_id": return "partApplyId";
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
    public String getPartName() {
        return this.partName;
    }

    /**  **/
    public void setPartName(String partName) {
        this.partName = partName;
    }

    /**  **/
    public String getPartType() {
        return this.partType;
    }

    /**  **/
    public void setPartType(String partType) {
        this.partType = partType;
    }

    /**  **/
    public String getPartUnit() {
        return this.partUnit;
    }

    /**  **/
    public void setPartUnit(String partUnit) {
        this.partUnit = partUnit;
    }

    /**  **/
    public String getPartTotal() {
        return this.partTotal;
    }

    /**  **/
    public void setPartTotal(String partTotal) {
        this.partTotal = partTotal;
    }

    /**  **/
    public String getRemark() {
        return this.remark;
    }

    /**  **/
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**  **/
    public String getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**  **/
    public String getPartApplyId() {
        return this.partApplyId;
    }

    /**  **/
    public void setPartApplyId(String partApplyId) {
        this.partApplyId = partApplyId;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }
}
