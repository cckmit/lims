package com.adc.da.pc_drive.entity;

public class RoadLineEO {
    private String id;
    private String driverRecordId;
    private String delFlag;
    //试验日期
    private String trialDate;
    //类别
    private String drType;
//    //车型   根据需求将车型这边注释  对应价格计算暂时不进行处理
//    private String driveCarType;
    //是否含燃油费  2020-10-22 根据修改 进行字段变更
    private String hasFuelCost;
    //试验路线
    private String roadLine;
    //试验状态
    private String trialState;
    //班次
    private String classNum;
    //载荷（T）
    private String load;
    //试验工况
    private String testConditions;
    //试验气象
    private String experimentalMeteorology;
    //道路试验员(姓名)
    private String roadTester;
    //开车地点
    private String drivingLocation;
    //停车地点
    private String stopLocation;
    //起始时刻
    private String startTime;
    //结束时刻
    private String endTime;
    //开车时里程表读数（km）
    private String drivingMilNum;
    //停车时里程表读数(km)
    private String stopMilNum;
    //表里程
    private String meterMile;
    //修正系数
    private String correctionFactor;
    //校正里程
    private String correctedMile;
    //本工况试验里程累加（km）
    private String milePlus;
    //状态
    private String state;

    private String createTime;

    private String countPrice;//路送或路试总价

    private String carState;//是否带上装

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriverRecordId() {
        return driverRecordId;
    }

    public void setDriverRecordId(String driverRecordId) {
        this.driverRecordId = driverRecordId;
    }

    public String getTrialDate() {
        return trialDate;
    }

    public void setTrialDate(String trialDate) {
        this.trialDate = trialDate;
    }

    public String getDrType() {
        return drType;
    }

    public void setDrType(String drType) {
        this.drType = drType;
    }

    public String getRoadLine() {
        return roadLine;
    }

    public void setRoadLine(String roadLine) {
        this.roadLine = roadLine;
    }

    public String getTrialState() {
        return trialState;
    }

    public void setTrialState(String trialState) {
        this.trialState = trialState;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getLoad() {
        return load;
    }

    public void setLoad(String load) {
        this.load = load;
    }

    public String getTestConditions() {
        return testConditions;
    }

    public void setTestConditions(String testConditions) {
        this.testConditions = testConditions;
    }

    public String getExperimentalMeteorology() {
        return experimentalMeteorology;
    }

    public void setExperimentalMeteorology(String experimentalMeteorology) {
        this.experimentalMeteorology = experimentalMeteorology;
    }

    public String getRoadTester() {
        return roadTester;
    }

    public void setRoadTester(String roadTester) {
        this.roadTester = roadTester;
    }

    public String getDrivingLocation() {
        return drivingLocation;
    }

    public void setDrivingLocation(String drivingLocation) {
        this.drivingLocation = drivingLocation;
    }

    public String getStopLocation() {
        return stopLocation;
    }

    public void setStopLocation(String stopLocation) {
        this.stopLocation = stopLocation;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDrivingMilNum() {
        return drivingMilNum;
    }

    public void setDrivingMilNum(String drivingMilNum) {
        this.drivingMilNum = drivingMilNum;
    }

    public String getStopMilNum() {
        return stopMilNum;
    }

    public void setStopMilNum(String stopMilNum) {
        this.stopMilNum = stopMilNum;
    }

    public String getMeterMile() {
        return meterMile;
    }

    public void setMeterMile(String meterMile) {
        this.meterMile = meterMile;
    }

    public String getCorrectionFactor() {
        return correctionFactor;
    }

    public void setCorrectionFactor(String correctionFactor) {
        this.correctionFactor = correctionFactor;
    }

    public String getCorrectedMile() {
        return correctedMile;
    }

    public void setCorrectedMile(String correctedMile) {
        this.correctedMile = correctedMile;
    }

    public String getMilePlus() {
        return milePlus;
    }

    public void setMilePlus(String milePlus) {
        this.milePlus = milePlus;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

//    public String getDriveCarType() {
//        return driveCarType;
//    }
//
//    public void setDriveCarType(String driveCarType) {
//        this.driveCarType = driveCarType;
//    }

    public String getHasFuelCost() {
        return hasFuelCost;
    }

    public void setHasFuelCost(String hasFuelCost) {
        this.hasFuelCost = hasFuelCost;
    }

    public String getCountPrice() {
        return countPrice;
    }

    public void setCountPrice(String countPrice) {
        this.countPrice = countPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
