package com.adc.da.equipment.VO;

public class EquipmentBorrowRecordVO {
    private String borrowerId;
    private String borrowerName;
    private String borrowerDepartment;

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerDepartment() {
        return borrowerDepartment;
    }

    public void setBorrowerDepartment(String borrowerDepartment) {
        this.borrowerDepartment = borrowerDepartment;
    }
}
