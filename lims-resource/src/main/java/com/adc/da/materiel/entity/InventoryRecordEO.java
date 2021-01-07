package com.adc.da.materiel.entity;

import com.adc.da.base.entity.BaseEntity;


/**
 * <b>功能：</b>RES_INVENTORY_RECORD InventoryRecordEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class InventoryRecordEO extends BaseEntity {
    //id
    private String id;
    //物料ID
    private String materielId;
    //样件数量
    private Double sampleQuantity;
    //出库，入库时间
    private String outInTime;
    //领用人，归还人
    private String borrowerReturnee;
    //操作类别，新增：“0”，编辑：“1”，领用：“2”，报废：“3”，归还：“4”
    private String operationType;
    //总价
    private Double totalPrice;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>materielId -> materiel_id</li>
     * <li>sampleQuantity
     * -> sample_quantity
     * </li>
     * <li>outInTime -> out_in_time</li>
     * <li>borrowerReturnee -> borrower_returnee</li>
     * <li>operationType -> operation_type</li>
     * <li>totalPrice -> total_price</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id":
                return "id";
            case "materielId":
                return "materiel_id";
            case "sampleQuantity ":
                return "sample_quantity ";
            case "outInTime":
                return "out_in_time";
            case "borrowerReturnee":
                return "borrower_returnee";
            case "operationType":
                return "operation_type";
            case "totalPrice":
                return "total_price";
            default:
                return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>materiel_id -> materielId</li>
     * <li>sample_quantity
     * -> sampleQuantity
     * </li>
     * <li>out_in_time -> outInTime</li>
     * <li>borrower_returnee -> borrowerReturnee</li>
     * <li>operation_type -> operationType</li>
     * <li>total_price -> totalPrice</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id":
                return "id";
            case "materiel_id":
                return "materielId";
            case "sample_quantity ":
                return "sampleQuantity ";
            case "out_in_time":
                return "outInTime";
            case "borrower_returnee":
                return "borrowerReturnee";
            case "operation_type":
                return "operationType";
            case "total_price":
                return "totalPrice";
            default:
                return null;
        }
    }

    /**
     *
     **/
    public String getId() {
        return this.id;
    }

    /**
     *
     **/
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     **/
    public String getMaterielId() {
        return this.materielId;
    }

    /**
     *
     **/
    public void setMaterielId(String materielId) {
        this.materielId = materielId;
    }

    /**
     *
     **/
    public Double getSampleQuantity() {
        return this.sampleQuantity
                ;
    }

    /**
     *
     **/
    public void setSampleQuantity(Double sampleQuantity) {
        this.sampleQuantity
                = sampleQuantity
        ;
    }

    /**
     *
     **/
    public String getOutInTime() {
        return this.outInTime;
    }

    /**
     *
     **/
    public void setOutInTime(String outInTime) {
        this.outInTime = outInTime;
    }

    /**
     *
     **/
    public String getBorrowerReturnee() {
        return this.borrowerReturnee;
    }

    /**
     *
     **/
    public void setBorrowerReturnee(String borrowerReturnee) {
        this.borrowerReturnee = borrowerReturnee;
    }

    /**
     *
     **/
    public String getOperationType() {
        return this.operationType;
    }

    /**
     *
     **/
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    /**
     *
     **/
    public Double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     *
     **/
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
