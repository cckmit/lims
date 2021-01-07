package com.adc.da.equipment.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>RES_EQUIPMENT_STATUS_FLAG EquipmentStatusFlagEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-29 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class EquipmentStatusFlagEO extends BaseEntity {

    @ApiModelProperty(" 备注 ")
    private String staRemark;
    @ApiModelProperty(" 变更时间 ")
    private String staChangeTime;
    @ApiModelProperty(" 变更后设备状态 ")
    private String staAfterStatus;
    @ApiModelProperty(" 变更前设备状态 ")
    private String staBeforeStatus;
    @ApiModelProperty(" 设备ID ")
    private String eqId;
    @ApiModelProperty(" 状态标记ID ")
    private String id;
    @ApiModelProperty(" 变更人ID ")
    private String staChangePerson;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>staRemark -> sta_remark</li>
     * <li>staChangeTime -> sta_change_time</li>
     * <li>staAfterStatus -> sta_after_status</li>
     * <li>staBeforeStatus -> sta_before_status</li>
     * <li>eqId -> eq_id</li>
     * <li>id -> id</li>
     * <li>staChangePerson -> sta_change_person</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) { return null; }
        switch (fieldName) {
            case "staRemark": return "sta_remark";
            case "staChangeTime": return "sta_change_time";
            case "staAfterStatus": return "sta_after_status";
            case "staBeforeStatus": return "sta_before_status";
            case "eqId": return "eq_id";
            case "id": return "id";
            case "staChangePerson": return "sta_change_person";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>sta_remark -> staRemark</li>
     * <li>sta_change_time -> staChangeTime</li>
     * <li>sta_after_status -> staAfterStatus</li>
     * <li>sta_before_status -> staBeforeStatus</li>
     * <li>eq_id -> eqId</li>
     * <li>id -> id</li>
     * <li>sta_change_person -> staChangePerson</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) { return null; }
        switch (columnName) {
            case "sta_remark": return "staRemark";
            case "sta_change_time": return "staChangeTime";
            case "sta_after_status": return "staAfterStatus";
            case "sta_before_status": return "staBeforeStatus";
            case "eq_id": return "eqId";
            case "id": return "id";
            case "sta_change_person": return "staChangePerson";
            default: return null;
        }
    }
    
    public String getStaRemark() {
        return this.staRemark;
    }

    public void setStaRemark(String staRemark) {
        this.staRemark = staRemark;
    }

    public String getStaChangeTime() {
        return this.staChangeTime;
    }

    public void setStaChangeTime(String staChangeTime) {
        this.staChangeTime = staChangeTime;
    }

    public String getStaAfterStatus() {
        return this.staAfterStatus;
    }

    public void setStaAfterStatus(String staAfterStatus) {
        this.staAfterStatus = staAfterStatus;
    }

    public String getStaBeforeStatus() {
        return this.staBeforeStatus;
    }

    public void setStaBeforeStatus(String staBeforeStatus) {
        this.staBeforeStatus = staBeforeStatus;
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

    public String getStaChangePerson() {
        return this.staChangePerson;
    }

    public void setStaChangePerson(String staChangePerson) {
        this.staChangePerson = staChangePerson;
    }

}
