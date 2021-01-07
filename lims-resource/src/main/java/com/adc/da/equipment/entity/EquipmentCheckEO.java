package com.adc.da.equipment.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>RES_EQUIPMENT_CHECK EquipmentCheckEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-12-02 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class EquipmentCheckEO extends BaseEntity {

    @ApiModelProperty(" 核检Id ")
    private String ckId;
    @ApiModelProperty(" 修改时间 ")
    private String updateTime;
    @ApiModelProperty(" 修改人 ")
    private String updateBy;
    @ApiModelProperty(" 创建时间 ")
    private String createTime;
    @ApiModelProperty(" 创建人 ")
    private String createBy;
    @ApiModelProperty(" 是否删除 ")
    private String delFlag;
    @ApiModelProperty(" 是否完成计划 ")
    private String isFinishFlag;
    @ApiModelProperty(" 附件名称 ")
    private String eqCkAttachName;
    @ApiModelProperty(" 附件ID ")
    private String eqCkAttachId;
    @ApiModelProperty(" 计划时附件名称 ")
    private String eqCkPlanAttachName;
    @ApiModelProperty(" 计划时附件ID ")
    private String eqCkPlanAttachId;
    @ApiModelProperty(" 预警时间 ")
    private String eqCkWarnTime;
    @ApiModelProperty(" 备注 ")
    private String eqCkRemark;
    @ApiModelProperty(" 计划时备注 ")
    private String eqCkPlanRemark;
    @ApiModelProperty(" 金额 ")
    private Long eqCkAmount;
    @ApiModelProperty(" 计划责任人 ")
    private String eqCkPlanOwner;
    @ApiModelProperty(" 实际检定日期 ")
    private String eqCkCheckTimeActual;
    @ApiModelProperty(" 计划检定日期 ")
    private String eqCkCheckTimePlan;
    @ApiModelProperty(" 检定/校准联系方式 ")
    private String eqCkOrgTel;
    @ApiModelProperty(" 检定/校准单位地址 ")
    private String eqCkOrgAddress;
    @ApiModelProperty(" 检定/校准单位 ")
    private String eqCkOrg;
    @ApiModelProperty(" 有效周期单位 ")
    private String eqCkCycleUnit;
    @ApiModelProperty(" 有效周期 ")
    private Long eqCkCycle;
    @ApiModelProperty(" 检定方式 ")
    private String eqCkType;
    @ApiModelProperty(" 设备Id ")
    private String eqId;
    @ApiModelProperty(" id ")
    private String id;

    private String eqNo;//设备编号
    private String eqName;//设备名称
    private String eqCkPlanOwnerName;

    public String getEqCkPlanOwnerName() {
        return eqCkPlanOwnerName;
    }

    public void setEqCkPlanOwnerName(String eqCkPlanOwnerName) {
        this.eqCkPlanOwnerName = eqCkPlanOwnerName;
    }

    public String getEqNo() {
        return eqNo;
    }

    public void setEqNo(String eqNo) {
        this.eqNo = eqNo;
    }

    public String getEqName() {
        return eqName;
    }

    public void setEqName(String eqName) {
        this.eqName = eqName;
    }

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>ckId -> ck_id</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>createTime -> create_time</li>
     * <li>createBy -> create_by</li>
     * <li>delFlag -> del_flag</li>
     * <li>isFinishFlag -> is_finish_flag</li>
     * <li>eqCkAttachName -> eq_ck_attach_name</li>
     * <li>eqCkAttachId -> eq_ck_attach_id</li>
     * <li>eqCkPlanAttachName -> eq_ck_plan_attach_name</li>
     * <li>eqCkPlanAttachId -> eq_ck_plan_attach_id</li>
     * <li>eqCkWarnTime -> eq_ck_warn_time</li>
     * <li>eqCkRemark -> eq_ck_remark</li>
     * <li>eqCkPlanRemark -> eq_ck_plan_remark</li>
     * <li>eqCkAmount -> eq_ck_amount</li>
     * <li>eqCkPlanOwner -> eq_ck_plan_owner</li>
     * <li>eqCkCheckTimeActual -> eq_ck_check_time_actual</li>
     * <li>eqCkCheckTimePlan -> eq_ck_check_time_plan</li>
     * <li>eqCkOrgTel -> eq_ck_org_tel</li>
     * <li>eqCkOrgAddress -> eq_ck_org_address</li>
     * <li>eqCkOrg -> eq_ck_org</li>
     * <li>eqCkCycleUnit -> eq_ck_cycle_unit</li>
     * <li>eqCkCycle -> eq_ck_cycle</li>
     * <li>eqCkType -> eq_ck_type</li>
     * <li>eqId -> eq_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) { return null; }
        switch (fieldName) {
            case "ckId": return "ck_id";
            case "updateTime": return "update_time";
            case "updateBy": return "update_by";
            case "createTime": return "create_time";
            case "createBy": return "create_by";
            case "delFlag": return "del_flag";
            case "isFinishFlag": return "is_finish_flag";
            case "eqCkAttachName": return "eq_ck_attach_name";
            case "eqCkAttachId": return "eq_ck_attach_id";
            case "eqCkPlanAttachName": return "eq_ck_plan_attach_name";
            case "eqCkPlanAttachId": return "eq_ck_plan_attach_id";
            case "eqCkWarnTime": return "eq_ck_warn_time";
            case "eqCkRemark": return "eq_ck_remark";
            case "eqCkPlanRemark": return "eq_ck_plan_remark";
            case "eqCkAmount": return "eq_ck_amount";
            case "eqCkPlanOwner": return "eq_ck_plan_owner";
            case "eqCkCheckTimeActual": return "eq_ck_check_time_actual";
            case "eqCkCheckTimePlan": return "eq_ck_check_time_plan";
            case "eqCkOrgTel": return "eq_ck_org_tel";
            case "eqCkOrgAddress": return "eq_ck_org_address";
            case "eqCkOrg": return "eq_ck_org";
            case "eqCkCycleUnit": return "eq_ck_cycle_unit";
            case "eqCkCycle": return "eq_ck_cycle";
            case "eqCkType": return "eq_ck_type";
            case "eqId": return "eq_id";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>ck_id -> ckId</li>
     * <li>update_time -> updateTime</li>
     * <li>update_by -> updateBy</li>
     * <li>create_time -> createTime</li>
     * <li>create_by -> createBy</li>
     * <li>del_flag -> delFlag</li>
     * <li>is_finish_flag -> isFinishFlag</li>
     * <li>eq_ck_attach_name -> eqCkAttachName</li>
     * <li>eq_ck_attach_id -> eqCkAttachId</li>
     * <li>eq_ck_plan_attach_name -> eqCkPlanAttachName</li>
     * <li>eq_ck_plan_attach_id -> eqCkPlanAttachId</li>
     * <li>eq_ck_warn_time -> eqCkWarnTime</li>
     * <li>eq_ck_remark -> eqCkRemark</li>
     * <li>eq_ck_plan_remark -> eqCkPlanRemark</li>
     * <li>eq_ck_amount -> eqCkAmount</li>
     * <li>eq_ck_plan_owner -> eqCkPlanOwner</li>
     * <li>eq_ck_check_time_actual -> eqCkCheckTimeActual</li>
     * <li>eq_ck_check_time_plan -> eqCkCheckTimePlan</li>
     * <li>eq_ck_org_tel -> eqCkOrgTel</li>
     * <li>eq_ck_org_address -> eqCkOrgAddress</li>
     * <li>eq_ck_org -> eqCkOrg</li>
     * <li>eq_ck_cycle_unit -> eqCkCycleUnit</li>
     * <li>eq_ck_cycle -> eqCkCycle</li>
     * <li>eq_ck_type -> eqCkType</li>
     * <li>eq_id -> eqId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) { return null; }
        switch (columnName) {
            case "ck_id": return "ckId";
            case "update_time": return "updateTime";
            case "update_by": return "updateBy";
            case "create_time": return "createTime";
            case "create_by": return "createBy";
            case "del_flag": return "delFlag";
            case "is_finish_flag": return "isFinishFlag";
            case "eq_ck_attach_name": return "eqCkAttachName";
            case "eq_ck_attach_id": return "eqCkAttachId";
            case "eq_ck_plan_attach_name": return "eqCkPlanAttachName";
            case "eq_ck_plan_attach_id": return "eqCkPlanAttachId";
            case "eq_ck_warn_time": return "eqCkWarnTime";
            case "eq_ck_remark": return "eqCkRemark";
            case "eq_ck_plan_remark": return "eqCkPlanRemark";
            case "eq_ck_amount": return "eqCkAmount";
            case "eq_ck_plan_owner": return "eqCkPlanOwner";
            case "eq_ck_check_time_actual": return "eqCkCheckTimeActual";
            case "eq_ck_check_time_plan": return "eqCkCheckTimePlan";
            case "eq_ck_org_tel": return "eqCkOrgTel";
            case "eq_ck_org_address": return "eqCkOrgAddress";
            case "eq_ck_org": return "eqCkOrg";
            case "eq_ck_cycle_unit": return "eqCkCycleUnit";
            case "eq_ck_cycle": return "eqCkCycle";
            case "eq_ck_type": return "eqCkType";
            case "eq_id": return "eqId";
            case "id": return "id";
            default: return null;
        }
    }
    
    public String getCkId() {
        return this.ckId;
    }

    public void setCkId(String ckId) {
        this.ckId = ckId;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getIsFinishFlag() {
        return this.isFinishFlag;
    }

    public void setIsFinishFlag(String isFinishFlag) {
        this.isFinishFlag = isFinishFlag;
    }

    public String getEqCkAttachName() {
        return this.eqCkAttachName;
    }

    public void setEqCkAttachName(String eqCkAttachName) {
        this.eqCkAttachName = eqCkAttachName;
    }

    public String getEqCkAttachId() {
        return this.eqCkAttachId;
    }

    public void setEqCkAttachId(String eqCkAttachId) {
        this.eqCkAttachId = eqCkAttachId;
    }

    public String getEqCkPlanAttachName() {
        return this.eqCkPlanAttachName;
    }

    public void setEqCkPlanAttachName(String eqCkPlanAttachName) {
        this.eqCkPlanAttachName = eqCkPlanAttachName;
    }

    public String getEqCkPlanAttachId() {
        return this.eqCkPlanAttachId;
    }

    public void setEqCkPlanAttachId(String eqCkPlanAttachId) {
        this.eqCkPlanAttachId = eqCkPlanAttachId;
    }

    public String getEqCkWarnTime() {
        return this.eqCkWarnTime;
    }

    public void setEqCkWarnTime(String eqCkWarnTime) {
        this.eqCkWarnTime = eqCkWarnTime;
    }

    public String getEqCkRemark() {
        return this.eqCkRemark;
    }

    public void setEqCkRemark(String eqCkRemark) {
        this.eqCkRemark = eqCkRemark;
    }

    public String getEqCkPlanRemark() {
        return this.eqCkPlanRemark;
    }

    public void setEqCkPlanRemark(String eqCkPlanRemark) {
        this.eqCkPlanRemark = eqCkPlanRemark;
    }

    public Long getEqCkAmount() {
        return this.eqCkAmount;
    }

    public void setEqCkAmount(Long eqCkAmount) {
        this.eqCkAmount = eqCkAmount;
    }

    public String getEqCkPlanOwner() {
        return this.eqCkPlanOwner;
    }

    public void setEqCkPlanOwner(String eqCkPlanOwner) {
        this.eqCkPlanOwner = eqCkPlanOwner;
    }

    public String getEqCkCheckTimeActual() {
        return this.eqCkCheckTimeActual;
    }

    public void setEqCkCheckTimeActual(String eqCkCheckTimeActual) {
        this.eqCkCheckTimeActual = eqCkCheckTimeActual;
    }

    public String getEqCkCheckTimePlan() {
        return this.eqCkCheckTimePlan;
    }

    public void setEqCkCheckTimePlan(String eqCkCheckTimePlan) {
        this.eqCkCheckTimePlan = eqCkCheckTimePlan;
    }

    public String getEqCkOrgTel() {
        return this.eqCkOrgTel;
    }

    public void setEqCkOrgTel(String eqCkOrgTel) {
        this.eqCkOrgTel = eqCkOrgTel;
    }

    public String getEqCkOrgAddress() {
        return this.eqCkOrgAddress;
    }

    public void setEqCkOrgAddress(String eqCkOrgAddress) {
        this.eqCkOrgAddress = eqCkOrgAddress;
    }

    public String getEqCkOrg() {
        return this.eqCkOrg;
    }

    public void setEqCkOrg(String eqCkOrg) {
        this.eqCkOrg = eqCkOrg;
    }

    public String getEqCkCycleUnit() {
        return this.eqCkCycleUnit;
    }

    public void setEqCkCycleUnit(String eqCkCycleUnit) {
        this.eqCkCycleUnit = eqCkCycleUnit;
    }

    public Long getEqCkCycle() {
        return this.eqCkCycle;
    }

    public void setEqCkCycle(Long eqCkCycle) {
        this.eqCkCycle = eqCkCycle;
    }

    public String getEqCkType() {
        return this.eqCkType;
    }

    public void setEqCkType(String eqCkType) {
        this.eqCkType = eqCkType;
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
