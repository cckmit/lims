package com.adc.da.equipment.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>RES_EQUIPMENT_BORROW_RECORD EquipmentBorrowRecordEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-27 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class EquipmentBorrowRecordEO extends BaseEntity {
    @ApiModelProperty("此设备当前状态：0：已借出；1：已归还")
    private String ifLend;
    @ApiModelProperty(" 此数据是否处于流程中 0：是；1：否")
    private String processState;
    @ApiModelProperty(" 预计归还时间 ")
    private String expectReturnTime;
    //控制借用历史导出时不导出借用流程管理员不同意的记录
    @ApiModelProperty(" 管理员审批流程（0代表设备管理员同意审批，1代表设备管理员未审批或者未同意） ")
    private String brwIsbrw;
    @ApiModelProperty(" 归还时间 ")
    private String brwReturnTime;
    @ApiModelProperty(" 借用时间 ")
    private String brwBorrowerTime
;
    @ApiModelProperty(" 借用人姓名 ")
    private String brwBorrowerName
;
    @ApiModelProperty(" 借用人id ")
    private String brwBorrowerId;
    @ApiModelProperty(" 设备id ")
    private String eqId;
    @ApiModelProperty(" id ")
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>brwIsbrw -> brw_isbrw</li>
     * <li>brwReturnTime -> brw_return_time</li>
     * <li>brwBorrowerTime
 -> brw_borrower_time
</li>
     * <li>brwBorrowerName
 -> brw_borrower_name
</li>
     * <li>brwBorrowerId -> brw_borrower_id</li>
     * <li>eqId -> eq_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) { return null; }
        switch (fieldName) {
            case "ifLend": return "if_lend";
            case "processState": return "process_state";
            case "expectReturnTime": return "expect_return_time";
            case "brwIsbrw": return "brw_isbrw";
            case "brwReturnTime": return "brw_return_time";
            case "brwBorrowerTime ": return "brw_borrower_time ";
            case "brwBorrowerName ": return "brw_borrower_name ";
            case "brwBorrowerId": return "brw_borrower_id";
            case "eqId": return "eq_id";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>brw_isbrw -> brwIsbrw</li>
     * <li>brw_return_time -> brwReturnTime</li>
     * <li>brw_borrower_time
 -> brwBorrowerTime
</li>
     * <li>brw_borrower_name
 -> brwBorrowerName
</li>
     * <li>brw_borrower_id -> brwBorrowerId</li>
     * <li>eq_id -> eqId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) { return null; }
        switch (columnName) {
            case "if_lend": return "ifLend";
            case "process_state": return "processState";
            case "expect_return_time": return "expectReturnTime";
            case "brw_isbrw": return "brwIsbrw";
            case "brw_return_time": return "brwReturnTime";
            case "brw_borrower_time ": return "brwBorrowerTime ";
            case "brw_borrower_name ": return "brwBorrowerName ";
            case "brw_borrower_id": return "brwBorrowerId";
            case "eq_id": return "eqId";
            case "id": return "id";
            default: return null;
        }
    }

    public String getProcessState() {
        return processState;
    }

    public void setProcessState(String processState) {
        this.processState = processState;
    }

    public String getExpectReturnTime() {
        return expectReturnTime;
    }

    public void setExpectReturnTime(String expectReturnTime) {
        this.expectReturnTime = expectReturnTime;
    }

    public String getBrwIsbrw() {
        return this.brwIsbrw;
    }

    public void setBrwIsbrw(String brwIsbrw) {
        this.brwIsbrw = brwIsbrw;
    }

    public String getBrwReturnTime() {
        return this.brwReturnTime;
    }

    public void setBrwReturnTime(String brwReturnTime) {
        this.brwReturnTime = brwReturnTime;
    }

    public String getBrwBorrowerTime
() {
        return this.brwBorrowerTime
;
    }

    public void setBrwBorrowerTime
(String brwBorrowerTime
) {
        this.brwBorrowerTime
 = brwBorrowerTime
;
    }

    public String getBrwBorrowerName
() {
        return this.brwBorrowerName
;
    }

    public void setBrwBorrowerName
(String brwBorrowerName
) {
        this.brwBorrowerName
 = brwBorrowerName
;
    }

    public String getBrwBorrowerId() {
        return this.brwBorrowerId;
    }

    public void setBrwBorrowerId(String brwBorrowerId) {
        this.brwBorrowerId = brwBorrowerId;
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

    public String getIfLend() {
        return ifLend;
    }

    public void setIfLend(String ifLend) {
        this.ifLend = ifLend;
    }
}
