package com.adc.da.pc_drive.vo;

public class PcCurveVo {
    /*单天里程数*/
    private String drivingDate;
    /*日期*/
    private String singleMileage;

    public PcCurveVo(String drivingDate, String singleMileage) {
        this.drivingDate = drivingDate;
        this.singleMileage = singleMileage;
    }

    public PcCurveVo() {
    }

    public String getDrivingDate() {
        return drivingDate;
    }

    public void setDrivingDate(String drivingDate) {
        this.drivingDate = drivingDate;
    }

    public String getSingleMileage() {
        return singleMileage;
    }

    public void setSingleMileage(String singleMileage) {
        this.singleMileage = singleMileage;
    }
}
