package com.adc.da.pc_budget_cash_out.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>PC_AUTO_PAY_FOR PcAutoPayForEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-29 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcAutoPayForEO extends BaseEntity {

    private String id;
    private Integer delFlag;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    @ApiModelProperty("请款单ID")
    private String bcoId;
    @ApiModelProperty("费用名称")
    private String budgetName;
    @ApiModelProperty("费用编码")
    private String budgetCode;
    @ApiModelProperty("费用")
    private String cashAmount;
    @ApiModelProperty("标准")
    private String stdPrice;
    @ApiModelProperty("单位")
    private String unit;
    @ApiModelProperty("是否选中（0，选中；1，未选中）")
    private String ifCheckout;
    @ApiModelProperty("计划天数")
    private String planDayNum;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>delFlag -> del_flag</li>
     * <li>createTime -> create_time</li>
     * <li>createBy -> create_by</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>bcoId -> bco_id</li>
     * <li>budgetName -> budget_name</li>
     * <li>budgetCode -> budget_code</li>
     * <li>cashAmount -> cash_amount</li>
     * <li>ifCheckout -> if_checkout</li>
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
            case "bcoId": return "bco_id";
            case "budgetName": return "budget_name";
            case "budgetCode": return "budget_code";
            case "cashAmount": return "cash_amount";
            case "ifCheckout": return "if_checkout";
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
     * <li>bco_id -> bcoId</li>
     * <li>budget_name -> budgetName</li>
     * <li>budget_code -> budgetCode</li>
     * <li>cash_amount -> cashAmount</li>
     * <li>if_checkout -> ifCheckout</li>
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
            case "bco_id": return "bcoId";
            case "budget_name": return "budgetName";
            case "budget_code": return "budgetCode";
            case "cash_amount": return "cashAmount";
            case "if_checkout": return "ifCheckout";
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
    public String getBcoId() {
        return this.bcoId;
    }

    /**  **/
    public void setBcoId(String bcoId) {
        this.bcoId = bcoId;
    }

    /**  **/
    public String getBudgetName() {
        return this.budgetName;
    }

    /**  **/
    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    /**  **/
    public String getBudgetCode() {
        return this.budgetCode;
    }

    /**  **/
    public void setBudgetCode(String budgetCode) {
        this.budgetCode = budgetCode;
    }

    /**  **/
    public String getCashAmount() {
        return this.cashAmount;
    }

    /**  **/
    public void setCashAmount(String cashAmount) {
        this.cashAmount = cashAmount;
    }

    /**  **/
    public String getIfCheckout() {
        return this.ifCheckout;
    }

    /**  **/
    public void setIfCheckout(String ifCheckout) {
        this.ifCheckout = ifCheckout;
    }

    public String getStdPrice() {
        return stdPrice;
    }

    public void setStdPrice(String stdPrice) {
        this.stdPrice = stdPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPlanDayNum() {
        return planDayNum;
    }

    public void setPlanDayNum(String planDayNum) {
        this.planDayNum = planDayNum;
    }
}
