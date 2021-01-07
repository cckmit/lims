package com.adc.da.equipment.VO;

public class BorrowRecordVO {
    //设备编号
    private String eqNo;
   //预计归还时间
    private String expectReturnTime;
    //归还时间
    private String brwReturnTime;
   //借用时间
    private String brwBorrowerTime;
   //借用人姓名
    private String brwBorrowerName;
   //借用人id
    private String brwBorrowerId;

    public String getEqNo() {
        return eqNo;
    }

    public void setEqNo(String eqNo) {
        this.eqNo = eqNo;
    }

    public String getExpectReturnTime() {
        return expectReturnTime;
    }

    public void setExpectReturnTime(String expectReturnTime) {
        this.expectReturnTime = expectReturnTime;
    }

    public String getBrwReturnTime() {
        return brwReturnTime;
    }

    public void setBrwReturnTime(String brwReturnTime) {
        this.brwReturnTime = brwReturnTime;
    }

    public String getBrwBorrowerTime() {
        return brwBorrowerTime;
    }

    public void setBrwBorrowerTime(String brwBorrowerTime) {
        this.brwBorrowerTime = brwBorrowerTime;
    }

    public String getBrwBorrowerName() {
        return brwBorrowerName;
    }

    public void setBrwBorrowerName(String brwBorrowerName) {
        this.brwBorrowerName = brwBorrowerName;
    }

    public String getBrwBorrowerId() {
        return brwBorrowerId;
    }

    public void setBrwBorrowerId(String brwBorrowerId) {
        this.brwBorrowerId = brwBorrowerId;
    }
}
