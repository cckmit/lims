package com.adc.da.pc_budget_reimbursement.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>PC_TRIAL_PRODUCT PcTrialProductEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-13 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcTrialProductEO extends BaseEntity {

    private String id;
    private Integer delFlag;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    @ApiModelProperty("报销申请单ID")
    private String rId;
    @ApiModelProperty("开始时间")
    private String startTime;
    @ApiModelProperty("结束时间")
    private String endTime;
    @ApiModelProperty("起点")
    private String startPoint;
    @ApiModelProperty("终点")
    private String endPoint;
    @ApiModelProperty("出发时里程表数")
    private String departOdometer;
    @ApiModelProperty("底盘号")
    private String underpanNo;
    @ApiModelProperty("住宿费预算")
    private String accommodationCost;
    @ApiModelProperty("住宿费申请")
    private String accommodationApply;
    @ApiModelProperty("住宿费累计")
    private String accommodationTotal;
    @ApiModelProperty("住宿天数")
    private String accommodationDayNmber;
    @ApiModelProperty("高速费预算")
    private String highSpeedCost;
    @ApiModelProperty("高速费申请")
    private String highSpeedApply;
    @ApiModelProperty("高速费累计")
    private String highSpeedTotal;
    @ApiModelProperty("过路过桥费预算")
    private String passWayCost;
    @ApiModelProperty("过路过桥费申请")
    private String passWayApply;
    @ApiModelProperty("过路过桥费累计")
    private String passWayTotal;
    @ApiModelProperty("加油费预算")
    private String refuelCost;
    @ApiModelProperty("加油费申请")
    private String refuelApply;
    @ApiModelProperty("加油费累计")
    private String refuelTotal;
    @ApiModelProperty("充电费预算")
    private String chargeCost;
    @ApiModelProperty("充电费申请")
    private String chargeApply;
    @ApiModelProperty("充电费累计")
    private String chargeTotal;
    @ApiModelProperty("加气费预算")
    private String airEntrappingCost;
    @ApiModelProperty("加气费申请")
    private String airEntrappingApply;
    @ApiModelProperty("加气费累计")
    private String airEntrappingTotal;
    @ApiModelProperty("维修费预算")
    private String maintainCost;
    @ApiModelProperty("维修费申请")
    private String maintainApply;
    @ApiModelProperty("维修费累计")
    private String maintainTotal;
    @ApiModelProperty("保养费预算")
    private String upkeepCost;
    @ApiModelProperty("保养费申请")
    private String upkeepApply;
    @ApiModelProperty("保养费累计")
    private String upkeepTotal;
    @ApiModelProperty("停车费预算")
    private String parkCost;
    @ApiModelProperty("停车费申请")
    private String parkApply;
    @ApiModelProperty("停车费累计")
    private String parkTotal;
    @ApiModelProperty("快递费预算")
    private String expressCost;
    @ApiModelProperty("快递费申请")
    private String expressApply;
    @ApiModelProperty("快递费累计")
    private String expressTotal;
    @ApiModelProperty("租赁费预算")
    private String hireCost;
    @ApiModelProperty("租赁费申请")
    private String hireApply;
    @ApiModelProperty("租赁费累计")
    private String hireTotal;
    @ApiModelProperty("其他预算")
    private String pcOtherCost;
    @ApiModelProperty("其他申请")
    private String pcOtherApply;
    @ApiModelProperty("其他累计")
    private String pcOtherTotal;
    @ApiModelProperty("小计")
    private String subtotal;
    @ApiModelProperty("票数")
    private String pollNmber;
    @ApiModelProperty("状态（0，已确认；1，未确认）")
    private String status;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>delFlag -> del_flag</li>
     * <li>createTime -> create_time</li>
     * <li>createBy -> create_by</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>rId -> r_id</li>
     * <li>startTime -> start_time</li>
     * <li>endTime -> end_time</li>
     * <li>startPoint -> start_point</li>
     * <li>endPoint -> end_point</li>
     * <li>departOdometer -> depart_odometer</li>
     * <li>underpanNo -> underpan_no</li>
     * <li>accommodationCost -> accommodation_cost</li>
     * <li>accommodationApply -> accommodation_apply</li>
     * <li>accommodationTotal -> accommodation_total</li>
     * <li>accommodationDayNmber -> accommodation_day_nmber</li>
     * <li>highSpeedCost -> high_speed_cost</li>
     * <li>highSpeedApply -> high_speed_apply</li>
     * <li>highSpeedTotal -> high_speed_total</li>
     * <li>passWayCost -> pass_way_cost</li>
     * <li>passWayApply -> pass_way_apply</li>
     * <li>passWayTotal -> pass_way_total</li>
     * <li>refuelCost -> refuel_cost</li>
     * <li>refuelApply -> refuel_apply</li>
     * <li>refuelTotal -> refuel_total</li>
     * <li>chargeCost -> charge_cost</li>
     * <li>chargeApply -> charge_apply</li>
     * <li>chargeTotal -> charge_total</li>
     * <li>airEntrappingCost -> air_entrapping_cost</li>
     * <li>airEntrappingApply -> air_entrapping_apply</li>
     * <li>airEntrappingTotal -> air_entrapping_total</li>
     * <li>maintainCost -> maintain_cost</li>
     * <li>maintainApply -> maintain_apply</li>
     * <li>maintainTotal -> maintain_total</li>
     * <li>upkeepCost -> upkeep_cost</li>
     * <li>upkeepApply -> upkeep_apply</li>
     * <li>upkeepTotal -> upkeep_total</li>
     * <li>parkCost -> park_cost</li>
     * <li>parkApply -> park_apply</li>
     * <li>parkTotal -> park_total</li>
     * <li>expressCost -> express_cost</li>
     * <li>expressApply -> express_apply</li>
     * <li>expressTotal -> express_total</li>
     * <li>hireCost -> hire_cost</li>
     * <li>hireApply -> hire_apply</li>
     * <li>hireTotal -> hire_total</li>
     * <li>pcOther -> pc_other</li>
     * <li>subtotal -> subtotal</li>
     * <li>pollNmber -> poll_nmber</li>
     * <li>status -> status</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "delFlag": return "del_flag";
            case "createTime": return "create_time";
            case "createBy": return "create_by";
            case "updateTime": return "update_time";
            case "updateBy": return "update_by";
            case "rId": return "r_id";
            case "startTime": return "start_time";
            case "endTime": return "end_time";
            case "startPoint": return "start_point";
            case "endPoint": return "end_point";
            case "departOdometer": return "depart_odometer";
            case "underpanNo": return "underpan_no";
            case "accommodationCost": return "accommodation_cost";
            case "accommodationApply": return "accommodation_apply";
            case "accommodationTotal": return "accommodation_total";
            case "accommodationDayNmber": return "accommodation_day_nmber";
            case "highSpeedCost": return "high_speed_cost";
            case "highSpeedApply": return "high_speed_apply";
            case "highSpeedTotal": return "high_speed_total";
            case "passWayCost": return "pass_way_cost";
            case "passWayApply": return "pass_way_apply";
            case "passWayTotal": return "pass_way_total";
            case "refuelCost": return "refuel_cost";
            case "refuelApply": return "refuel_apply";
            case "refuelTotal": return "refuel_total";
            case "chargeCost": return "charge_cost";
            case "chargeApply": return "charge_apply";
            case "chargeTotal": return "charge_total";
            case "airEntrappingCost": return "air_entrapping_cost";
            case "airEntrappingApply": return "air_entrapping_apply";
            case "airEntrappingTotal": return "air_entrapping_total";
            case "maintainCost": return "maintain_cost";
            case "maintainApply": return "maintain_apply";
            case "maintainTotal": return "maintain_total";
            case "upkeepCost": return "upkeep_cost";
            case "upkeepApply": return "upkeep_apply";
            case "upkeepTotal": return "upkeep_total";
            case "parkCost": return "park_cost";
            case "parkApply": return "park_apply";
            case "parkTotal": return "park_total";
            case "expressCost": return "express_cost";
            case "expressApply": return "express_apply";
            case "expressTotal": return "express_total";
            case "hireCost": return "hire_cost";
            case "hireApply": return "hire_apply";
            case "hireTotal": return "hire_total";
            case "pcOther": return "pc_other";
            case "subtotal": return "subtotal";
            case "pollNmber": return "poll_nmber";
            case "status": return "status";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>del_flag -> delFlag</li>
     * <li>create_time -> createTime</li>
     * <li>create_by -> createBy</li>
     * <li>update_time -> updateTime</li>
     * <li>update_by -> updateBy</li>
     * <li>r_id -> rId</li>
     * <li>start_time -> startTime</li>
     * <li>end_time -> endTime</li>
     * <li>start_point -> startPoint</li>
     * <li>end_point -> endPoint</li>
     * <li>depart_odometer -> departOdometer</li>
     * <li>underpan_no -> underpanNo</li>
     * <li>accommodation_cost -> accommodationCost</li>
     * <li>accommodation_apply -> accommodationApply</li>
     * <li>accommodation_total -> accommodationTotal</li>
     * <li>accommodation_day_nmber -> accommodationDayNmber</li>
     * <li>high_speed_cost -> highSpeedCost</li>
     * <li>high_speed_apply -> highSpeedApply</li>
     * <li>high_speed_total -> highSpeedTotal</li>
     * <li>pass_way_cost -> passWayCost</li>
     * <li>pass_way_apply -> passWayApply</li>
     * <li>pass_way_total -> passWayTotal</li>
     * <li>refuel_cost -> refuelCost</li>
     * <li>refuel_apply -> refuelApply</li>
     * <li>refuel_total -> refuelTotal</li>
     * <li>charge_cost -> chargeCost</li>
     * <li>charge_apply -> chargeApply</li>
     * <li>charge_total -> chargeTotal</li>
     * <li>air_entrapping_cost -> airEntrappingCost</li>
     * <li>air_entrapping_apply -> airEntrappingApply</li>
     * <li>air_entrapping_total -> airEntrappingTotal</li>
     * <li>maintain_cost -> maintainCost</li>
     * <li>maintain_apply -> maintainApply</li>
     * <li>maintain_total -> maintainTotal</li>
     * <li>upkeep_cost -> upkeepCost</li>
     * <li>upkeep_apply -> upkeepApply</li>
     * <li>upkeep_total -> upkeepTotal</li>
     * <li>park_cost -> parkCost</li>
     * <li>park_apply -> parkApply</li>
     * <li>park_total -> parkTotal</li>
     * <li>express_cost -> expressCost</li>
     * <li>express_apply -> expressApply</li>
     * <li>express_total -> expressTotal</li>
     * <li>hire_cost -> hireCost</li>
     * <li>hire_apply -> hireApply</li>
     * <li>hire_total -> hireTotal</li>
     * <li>pc_other -> pcOther</li>
     * <li>subtotal -> subtotal</li>
     * <li>poll_nmber -> pollNmber</li>
     * <li>status -> status</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "del_flag": return "delFlag";
            case "create_time": return "createTime";
            case "create_by": return "createBy";
            case "update_time": return "updateTime";
            case "update_by": return "updateBy";
            case "r_id": return "rId";
            case "start_time": return "startTime";
            case "end_time": return "endTime";
            case "start_point": return "startPoint";
            case "end_point": return "endPoint";
            case "depart_odometer": return "departOdometer";
            case "underpan_no": return "underpanNo";
            case "accommodation_cost": return "accommodationCost";
            case "accommodation_apply": return "accommodationApply";
            case "accommodation_total": return "accommodationTotal";
            case "accommodation_day_nmber": return "accommodationDayNmber";
            case "high_speed_cost": return "highSpeedCost";
            case "high_speed_apply": return "highSpeedApply";
            case "high_speed_total": return "highSpeedTotal";
            case "pass_way_cost": return "passWayCost";
            case "pass_way_apply": return "passWayApply";
            case "pass_way_total": return "passWayTotal";
            case "refuel_cost": return "refuelCost";
            case "refuel_apply": return "refuelApply";
            case "refuel_total": return "refuelTotal";
            case "charge_cost": return "chargeCost";
            case "charge_apply": return "chargeApply";
            case "charge_total": return "chargeTotal";
            case "air_entrapping_cost": return "airEntrappingCost";
            case "air_entrapping_apply": return "airEntrappingApply";
            case "air_entrapping_total": return "airEntrappingTotal";
            case "maintain_cost": return "maintainCost";
            case "maintain_apply": return "maintainApply";
            case "maintain_total": return "maintainTotal";
            case "upkeep_cost": return "upkeepCost";
            case "upkeep_apply": return "upkeepApply";
            case "upkeep_total": return "upkeepTotal";
            case "park_cost": return "parkCost";
            case "park_apply": return "parkApply";
            case "park_total": return "parkTotal";
            case "express_cost": return "expressCost";
            case "express_apply": return "expressApply";
            case "express_total": return "expressTotal";
            case "hire_cost": return "hireCost";
            case "hire_apply": return "hireApply";
            case "hire_total": return "hireTotal";
            case "pc_other": return "pcOther";
            case "subtotal": return "subtotal";
            case "poll_nmber": return "pollNmber";
            case "status": return "status";
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
    public Integer getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**  **/
    public String getCreateTime() {
        return this.createTime;
    }

    /**  **/
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**  **/
    public String getCreateBy() {
        return this.createBy;
    }

    /**  **/
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**  **/
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**  **/
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**  **/
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**  **/
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**  **/
    public String getRId() {
        return this.rId;
    }

    /**  **/
    public void setRId(String rId) {
        this.rId = rId;
    }

    /**  **/
    public String getStartTime() {
        return this.startTime;
    }

    /**  **/
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**  **/
    public String getEndTime() {
        return this.endTime;
    }

    /**  **/
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**  **/
    public String getStartPoint() {
        return this.startPoint;
    }

    /**  **/
    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    /**  **/
    public String getEndPoint() {
        return this.endPoint;
    }

    /**  **/
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    /**  **/
    public String getDepartOdometer() {
        return this.departOdometer;
    }

    /**  **/
    public void setDepartOdometer(String departOdometer) {
        this.departOdometer = departOdometer;
    }

    /**  **/
    public String getUnderpanNo() {
        return this.underpanNo;
    }

    /**  **/
    public void setUnderpanNo(String underpanNo) {
        this.underpanNo = underpanNo;
    }

    /**  **/
    public String getAccommodationCost() {
        return this.accommodationCost;
    }

    /**  **/
    public void setAccommodationCost(String accommodationCost) {
        this.accommodationCost = accommodationCost;
    }

    /**  **/
    public String getAccommodationApply() {
        return this.accommodationApply;
    }

    /**  **/
    public void setAccommodationApply(String accommodationApply) {
        this.accommodationApply = accommodationApply;
    }

    /**  **/
    public String getAccommodationTotal() {
        return this.accommodationTotal;
    }

    /**  **/
    public void setAccommodationTotal(String accommodationTotal) {
        this.accommodationTotal = accommodationTotal;
    }

    /**  **/
    public String getAccommodationDayNmber() {
        return this.accommodationDayNmber;
    }

    /**  **/
    public void setAccommodationDayNmber(String accommodationDayNmber) {
        this.accommodationDayNmber = accommodationDayNmber;
    }

    /**  **/
    public String getHighSpeedCost() {
        return this.highSpeedCost;
    }

    /**  **/
    public void setHighSpeedCost(String highSpeedCost) {
        this.highSpeedCost = highSpeedCost;
    }

    /**  **/
    public String getHighSpeedApply() {
        return this.highSpeedApply;
    }

    /**  **/
    public void setHighSpeedApply(String highSpeedApply) {
        this.highSpeedApply = highSpeedApply;
    }

    /**  **/
    public String getHighSpeedTotal() {
        return this.highSpeedTotal;
    }

    /**  **/
    public void setHighSpeedTotal(String highSpeedTotal) {
        this.highSpeedTotal = highSpeedTotal;
    }

    /**  **/
    public String getPassWayCost() {
        return this.passWayCost;
    }

    /**  **/
    public void setPassWayCost(String passWayCost) {
        this.passWayCost = passWayCost;
    }

    /**  **/
    public String getPassWayApply() {
        return this.passWayApply;
    }

    /**  **/
    public void setPassWayApply(String passWayApply) {
        this.passWayApply = passWayApply;
    }

    /**  **/
    public String getPassWayTotal() {
        return this.passWayTotal;
    }

    /**  **/
    public void setPassWayTotal(String passWayTotal) {
        this.passWayTotal = passWayTotal;
    }

    /**  **/
    public String getRefuelCost() {
        return this.refuelCost;
    }

    /**  **/
    public void setRefuelCost(String refuelCost) {
        this.refuelCost = refuelCost;
    }

    /**  **/
    public String getRefuelApply() {
        return this.refuelApply;
    }

    /**  **/
    public void setRefuelApply(String refuelApply) {
        this.refuelApply = refuelApply;
    }

    /**  **/
    public String getRefuelTotal() {
        return this.refuelTotal;
    }

    /**  **/
    public void setRefuelTotal(String refuelTotal) {
        this.refuelTotal = refuelTotal;
    }

    /**  **/
    public String getChargeCost() {
        return this.chargeCost;
    }

    /**  **/
    public void setChargeCost(String chargeCost) {
        this.chargeCost = chargeCost;
    }

    /**  **/
    public String getChargeApply() {
        return this.chargeApply;
    }

    /**  **/
    public void setChargeApply(String chargeApply) {
        this.chargeApply = chargeApply;
    }

    /**  **/
    public String getChargeTotal() {
        return this.chargeTotal;
    }

    /**  **/
    public void setChargeTotal(String chargeTotal) {
        this.chargeTotal = chargeTotal;
    }

    /**  **/
    public String getAirEntrappingCost() {
        return this.airEntrappingCost;
    }

    /**  **/
    public void setAirEntrappingCost(String airEntrappingCost) {
        this.airEntrappingCost = airEntrappingCost;
    }

    /**  **/
    public String getAirEntrappingApply() {
        return this.airEntrappingApply;
    }

    /**  **/
    public void setAirEntrappingApply(String airEntrappingApply) {
        this.airEntrappingApply = airEntrappingApply;
    }

    /**  **/
    public String getAirEntrappingTotal() {
        return this.airEntrappingTotal;
    }

    /**  **/
    public void setAirEntrappingTotal(String airEntrappingTotal) {
        this.airEntrappingTotal = airEntrappingTotal;
    }

    /**  **/
    public String getMaintainCost() {
        return this.maintainCost;
    }

    /**  **/
    public void setMaintainCost(String maintainCost) {
        this.maintainCost = maintainCost;
    }

    /**  **/
    public String getMaintainApply() {
        return this.maintainApply;
    }

    /**  **/
    public void setMaintainApply(String maintainApply) {
        this.maintainApply = maintainApply;
    }

    /**  **/
    public String getMaintainTotal() {
        return this.maintainTotal;
    }

    /**  **/
    public void setMaintainTotal(String maintainTotal) {
        this.maintainTotal = maintainTotal;
    }

    /**  **/
    public String getUpkeepCost() {
        return this.upkeepCost;
    }

    /**  **/
    public void setUpkeepCost(String upkeepCost) {
        this.upkeepCost = upkeepCost;
    }

    /**  **/
    public String getUpkeepApply() {
        return this.upkeepApply;
    }

    /**  **/
    public void setUpkeepApply(String upkeepApply) {
        this.upkeepApply = upkeepApply;
    }

    /**  **/
    public String getUpkeepTotal() {
        return this.upkeepTotal;
    }

    /**  **/
    public void setUpkeepTotal(String upkeepTotal) {
        this.upkeepTotal = upkeepTotal;
    }

    /**  **/
    public String getParkCost() {
        return this.parkCost;
    }

    /**  **/
    public void setParkCost(String parkCost) {
        this.parkCost = parkCost;
    }

    /**  **/
    public String getParkApply() {
        return this.parkApply;
    }

    /**  **/
    public void setParkApply(String parkApply) {
        this.parkApply = parkApply;
    }

    /**  **/
    public String getParkTotal() {
        return this.parkTotal;
    }

    /**  **/
    public void setParkTotal(String parkTotal) {
        this.parkTotal = parkTotal;
    }

    /**  **/
    public String getExpressCost() {
        return this.expressCost;
    }

    /**  **/
    public void setExpressCost(String expressCost) {
        this.expressCost = expressCost;
    }

    /**  **/
    public String getExpressApply() {
        return this.expressApply;
    }

    /**  **/
    public void setExpressApply(String expressApply) {
        this.expressApply = expressApply;
    }

    /**  **/
    public String getExpressTotal() {
        return this.expressTotal;
    }

    /**  **/
    public void setExpressTotal(String expressTotal) {
        this.expressTotal = expressTotal;
    }

    /**  **/
    public String getHireCost() {
        return this.hireCost;
    }

    /**  **/
    public void setHireCost(String hireCost) {
        this.hireCost = hireCost;
    }

    /**  **/
    public String getHireApply() {
        return this.hireApply;
    }

    /**  **/
    public void setHireApply(String hireApply) {
        this.hireApply = hireApply;
    }

    /**  **/
    public String getHireTotal() {
        return this.hireTotal;
    }

    /**  **/
    public void setHireTotal(String hireTotal) {
        this.hireTotal = hireTotal;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getPcOtherCost() {
        return pcOtherCost;
    }

    public void setPcOtherCost(String pcOtherCost) {
        this.pcOtherCost = pcOtherCost;
    }

    public String getPcOtherApply() {
        return pcOtherApply;
    }

    public void setPcOtherApply(String pcOtherApply) {
        this.pcOtherApply = pcOtherApply;
    }

    public String getPcOtherTotal() {
        return pcOtherTotal;
    }

    public void setPcOtherTotal(String pcOtherTotal) {
        this.pcOtherTotal = pcOtherTotal;
    }

    /**  **/
    public String getSubtotal() {
        return this.subtotal;
    }

    /**  **/
    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    /**  **/
    public String getPollNmber() {
        return this.pollNmber;
    }

    /**  **/
    public void setPollNmber(String pollNmber) {
        this.pollNmber = pollNmber;
    }

    /**  **/
    public String getStatus() {
        return this.status;
    }

    /**  **/
    public void setStatus(String status) {
        this.status = status;
    }

}
