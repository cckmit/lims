package com.adc.da.materiel.entity;

import com.adc.da.base.entity.BaseEntity;


/**
 * 领用归还表
 */
public class BorrowRecordEO extends BaseEntity {
    /*
     *使用科室
     */
    private String useDepartment;
    /*
     *领用数量
     */
    private String borrowingQuantity;
    /*
     *领用时间
     */
    private String borrowingTime;
    /*
     *领用备注
     */
    private String borrowingRemakes;
    /*
     *归还人
     */
    private String returner;
    /*
     *归还科室
     */
    private String returnDepartment;
    /*
     *归还数量
     */
    private String returnQuantity;
    /*
     *归还日期
     */
    private String returnTime;
    /*
     *归还备注
     */
    private String returnRemakes;
    /*
     *id
     */
    private String id;
    /*
     *物料id
     */
    private String materielId;
    /*
     *领用人id
     */
    private String borrowerId;
    /*
     *领用人
     */
    private String borrower;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>useDepartment -> use_department</li>
     * <li>borrowingQuantity -> borrowing_quantity</li>
     * <li>borrowingTime -> borrowing_time</li>
     * <li>borrowingRemakes -> borrowing_remakes</li>
     * <li>returner -> returner</li>
     * <li>returnDepartment -> return_department</li>
     * <li>returnQuantity -> return_quantity</li>
     * <li>returnTime -> return_time</li>
     * <li>returnRemakes -> return_remakes</li>
     * <li>id -> id</li>
     * <li>materielId -> materiel_id</li>
     * <li>borrowerId -> borrower_id</li>
     * <li>borrower -> borrower</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "useDepartment": return "use_department";
            case "borrowingQuantity": return "borrowing_quantity";
            case "borrowingTime": return "borrowing_time";
            case "borrowingRemakes": return "borrowing_remakes";
            case "returner": return "returner";
            case "returnDepartment": return "return_department";
            case "returnQuantity": return "return_quantity";
            case "returnTime": return "return_time";
            case "returnRemakes": return "return_remakes";
            case "id": return "id";
            case "materielId": return "materiel_id";
            case "borrowerId": return "borrower_id";
            case "borrower": return "borrower";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>use_department -> useDepartment</li>
     * <li>borrowing_quantity -> borrowingQuantity</li>
     * <li>borrowing_time -> borrowingTime</li>
     * <li>borrowing_remakes -> borrowingRemakes</li>
     * <li>returner -> returner</li>
     * <li>return_department -> returnDepartment</li>
     * <li>return_quantity -> returnQuantity</li>
     * <li>return_time -> returnTime</li>
     * <li>return_remakes -> returnRemakes</li>
     * <li>id -> id</li>
     * <li>materiel_id -> materielId</li>
     * <li>borrower_id -> borrowerId</li>
     * <li>borrower -> borrower</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "use_department": return "useDepartment";
            case "borrowing_quantity": return "borrowingQuantity";
            case "borrowing_time": return "borrowingTime";
            case "borrowing_remakes": return "borrowingRemakes";
            case "returner": return "returner";
            case "return_department": return "returnDepartment";
            case "return_quantity": return "returnQuantity";
            case "return_time": return "returnTime";
            case "return_remakes": return "returnRemakes";
            case "id": return "id";
            case "materiel_id": return "materielId";
            case "borrower_id": return "borrowerId";
            case "borrower": return "borrower";
            default: return null;
        }
    }
    
    /**  **/
    public String getUseDepartment() {
        return this.useDepartment;
    }

    /**  **/
    public void setUseDepartment(String useDepartment) {
        this.useDepartment = useDepartment;
    }

    /**  **/
    public String getBorrowingQuantity() {
        return this.borrowingQuantity;
    }

    /**  **/
    public void setBorrowingQuantity(String borrowingQuantity) {
        this.borrowingQuantity = borrowingQuantity;
    }

    /**  **/
    public String getBorrowingTime() {
        return this.borrowingTime;
    }

    /**  **/
    public void setBorrowingTime(String borrowingTime) {
        this.borrowingTime = borrowingTime;
    }

    /**  **/
    public String getBorrowingRemakes() {
        return this.borrowingRemakes;
    }

    /**  **/
    public void setBorrowingRemakes(String borrowingRemakes) {
        this.borrowingRemakes = borrowingRemakes;
    }

    /**  **/
    public String getReturner() {
        return this.returner;
    }

    /**  **/
    public void setReturner(String returner) {
        this.returner = returner;
    }

    /**  **/
    public String getReturnDepartment() {
        return this.returnDepartment;
    }

    /**  **/
    public void setReturnDepartment(String returnDepartment) {
        this.returnDepartment = returnDepartment;
    }

    /**  **/
    public String getReturnQuantity() {
        return this.returnQuantity;
    }

    /**  **/
    public void setReturnQuantity(String returnQuantity) {
        this.returnQuantity = returnQuantity;
    }

    /**  **/
    public String getReturnTime() {
        return this.returnTime;
    }

    /**  **/
    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    /**  **/
    public String getReturnRemakes() {
        return this.returnRemakes;
    }

    /**  **/
    public void setReturnRemakes(String returnRemakes) {
        this.returnRemakes = returnRemakes;
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
    public String getMaterielId() {
        return this.materielId;
    }

    /**  **/
    public void setMaterielId(String materielId) {
        this.materielId = materielId;
    }

    /**  **/
    public String getBorrowerId() {
        return this.borrowerId;
    }

    /**  **/
    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    /**  **/
    public String getBorrower() {
        return this.borrower;
    }

    /**  **/
    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

}
