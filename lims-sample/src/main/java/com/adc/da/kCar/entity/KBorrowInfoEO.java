package com.adc.da.kCar.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * <b>功能：</b>K_BORROW_INFO KBorrowInfoEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2020-04-17 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class KBorrowInfoEO extends BaseEntity {

    @ApiModelProperty("借车用途")
    private String borrowUse;
    @ApiModelProperty("外借人员")
    private String borrowUser;
    @ApiModelProperty("预计归还日期")
    private String planRevertDate;
    @ApiModelProperty("实际归还日期")
    private String realRevertDate;
    @ApiModelProperty("借车日期")
    private String borrowDate;
    @ApiModelProperty("整车id")
    private String carId;
    @ApiModelProperty("")
    private String id;
    @ApiModelProperty("整车底盘号")
    private String chassisNumber;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>borrowUse -> borrow_use</li>
     * <li>borrowUser -> borrow_user</li>
     * <li>planRevertDate -> plan_revert_date</li>
     * <li>realRevertDate -> real_revert_date</li>
     * <li>borrowDate -> borrow_date</li>
     * <li>carId -> car_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) { return null; }
        switch (fieldName) {
            case "borrowUse": return "borrow_use";
            case "borrowUser": return "borrow_user";
            case "planRevertDate": return "plan_revert_date";
            case "realRevertDate": return "real_revert_date";
            case "borrowDate": return "borrow_date";
            case "carId": return "car_id";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>borrow_use -> borrowUse</li>
     * <li>borrow_user -> borrowUser</li>
     * <li>plan_revert_date -> planRevertDate</li>
     * <li>real_revert_date -> realRevertDate</li>
     * <li>borrow_date -> borrowDate</li>
     * <li>car_id -> carId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) { return null; }
        switch (columnName) {
            case "borrow_use": return "borrowUse";
            case "borrow_user": return "borrowUser";
            case "plan_revert_date": return "planRevertDate";
            case "real_revert_date": return "realRevertDate";
            case "borrow_date": return "borrowDate";
            case "car_id": return "carId";
            case "id": return "id";
            default: return null;
        }
    }
    
    public String getBorrowUse() {
        return this.borrowUse;
    }

    public void setBorrowUse(String borrowUse) {
        this.borrowUse = borrowUse;
    }

    public String getBorrowUser() {
        return this.borrowUser;
    }

    public void setBorrowUser(String borrowUser) {
        this.borrowUser = borrowUser;
    }

    public String getPlanRevertDate() {
        return this.planRevertDate;
    }

    public void setPlanRevertDate(String planRevertDate) {
        this.planRevertDate = planRevertDate;
    }

    public String getRealRevertDate() {
        return this.realRevertDate;
    }

    public void setRealRevertDate(String realRevertDate) {
        this.realRevertDate = realRevertDate;
    }

    public String getBorrowDate() {
        return this.borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getCarId() {
        return this.carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getChassisNumber() {
		return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}

    
}
