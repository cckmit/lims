package com.adc.da.car.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/19 8:20
 * @description：${description}
 */
public class SaCarDataListVO {

    @ApiModelProperty(value = "null")
    private String id;

    /**
     * 整车编号
     */
    @ApiModelProperty(value = "整车编号")
    private String carNO;

    /**
     * 试验委托ID
     */
    @ApiModelProperty(value = "试验委托ID")
    private String trialApplyId;

    /**
     * 试验委托编号
     */
    @ApiModelProperty(value = "试验委托编号")
    private String trialApplyNO;

    @ApiModelProperty("试验任务和整车关系")
    List<TrialtaskSampleVO> trialtaskSampleVOS;


    /**
     * 使用人
     */
    @ApiModelProperty(value="使用人")   ////逻辑变更：任务申请时选中的负责人带过来
    private String userName;

    /**
     * 使用人id
     */
    @ApiModelProperty(value="使用人id")   ////逻辑变更：任务申请时选中的负责人带过来
    private String userId;



    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    private String bmProjectId;

    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String bmProjectName;

    /**
     * 送样人ID
     */
    @ApiModelProperty(value = "送样人ID")
    private String sendCarUserId;

    /**
     * 送样人姓名
     */
    @ApiModelProperty(value = "送样人姓名")
    private String sendCarUserName;

    /**
     * 领样人ID
     */
    @ApiModelProperty(value = "领样人ID")
    private String getCarUserId;

    /**
     * 领样人姓名
     */
    @ApiModelProperty(value = "领样人姓名")
    private String getCarUserName;

    /**
     * 车辆名称
     */
    @ApiModelProperty(value = "车辆名称")
    private String carName;

    /**
     * VIN码
     */
    @ApiModelProperty(value = "VIN码")
    private String carVin;

    /**
     * 生产厂家
     */
    @ApiModelProperty(value = "生产厂家")
    private String producedFactory;

    /**
     * 所在位置
     */
    @ApiModelProperty(value = "所在位置")
    private String carSpaceLocation;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String carStatus;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private String producedTime;

    /**
     * 发动机编号
     */
    @ApiModelProperty(value = "发动机编号")
    private String carEngineNo;

    
    /**
     * 发动机型号
     */
    @ApiModelProperty(value = "发动机型号")
    private String carEngineType;

    /**
     * 发动机编号
     */
    @ApiModelProperty(value = "接收人")
    private String receivedUserId;

    @ApiModelProperty(value="底盘号")
    private String chassisNumber;

    @ApiModelProperty(value="电机号")
    private String motorNumber;

    @ApiModelProperty(value="电池包总成")
    private String batteryPack;

    @ApiModelProperty(value="车况")
    private String carCondition;

    @ApiModelProperty(value = "K库车辆状态")
    private String carStatusE;

    @ApiModelProperty(value="车型")
    private String carType;

    @ApiModelProperty(value = "车型号")
    private String carModel;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarNO() {
        return carNO;
    }

    public void setCarNO(String carNO) {
        this.carNO = carNO;
    }

    public String getTrialApplyId() {
        return trialApplyId;
    }

    public void setTrialApplyId(String trialApplyId) {
        this.trialApplyId = trialApplyId;
    }

    public String getTrialApplyNO() {
        return trialApplyNO;
    }

    public void setTrialApplyNO(String trialApplyNO) {
        this.trialApplyNO = trialApplyNO;
    }

    public List<TrialtaskSampleVO> getTrialtaskSampleVOS() {
        return trialtaskSampleVOS;
    }

    public void setTrialtaskSampleVOS(List<TrialtaskSampleVO> trialtaskSampleVOS) {
        this.trialtaskSampleVOS = trialtaskSampleVOS;
    }

    public String getBmProjectId() {
        return bmProjectId;
    }

    public void setBmProjectId(String bmProjectId) {
        this.bmProjectId = bmProjectId;
    }

    public String getBmProjectName() {
        return bmProjectName;
    }

    public void setBmProjectName(String bmProjectName) {
        this.bmProjectName = bmProjectName;
    }

    public String getSendCarUserId() {
        return sendCarUserId;
    }

    public void setSendCarUserId(String sendCarUserId) {
        this.sendCarUserId = sendCarUserId;
    }

    public String getSendCarUserName() {
        return sendCarUserName;
    }

    public void setSendCarUserName(String sendCarUserName) {
        this.sendCarUserName = sendCarUserName;
    }

    public String getGetCarUserId() {
        return getCarUserId;
    }

    public void setGetCarUserId(String getCarUserId) {
        this.getCarUserId = getCarUserId;
    }

    public String getGetCarUserName() {
        return getCarUserName;
    }

    public void setGetCarUserName(String getCarUserName) {
        this.getCarUserName = getCarUserName;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarVin() {
        return carVin;
    }

    public void setCarVin(String carVin) {
        this.carVin = carVin;
    }

    public String getProducedFactory() {
        return producedFactory;
    }

    public void setProducedFactory(String producedFactory) {
        this.producedFactory = producedFactory;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public String getCarSpaceLocation() {
        return carSpaceLocation;
    }

    public void setCarSpaceLocation(String carSpaceLocation) {
        this.carSpaceLocation = carSpaceLocation;
    }

    public String getProducedTime() {
        return producedTime;
    }

    public void setProducedTime(String producedTime) {
        this.producedTime = producedTime;
    }

    public String getCarEngineNo() {
        return carEngineNo;
    }

    public void setCarEngineNo(String carEngineNo) {
        this.carEngineNo = carEngineNo;
    }

    public String getCarEngineType() {
        return carEngineType;
    }

    public void setCarEngineType(String carEngineType) {
        this.carEngineType = carEngineType;
    }
    public String getReceivedUserId() {
		return receivedUserId;
	}

	public void setReceivedUserId(String receivedUserId) {
		this.receivedUserId = receivedUserId;
	}

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getMotorNumber() {
        return motorNumber;
    }

    public void setMotorNumber(String motorNumber) {
        this.motorNumber = motorNumber;
    }

    public String getBatteryPack() {
        return batteryPack;
    }

    public void setBatteryPack(String batteryPack) {
        this.batteryPack = batteryPack;
    }

    public String getCarCondition() {
        return carCondition;
    }

    public void setCarCondition(String carCondition) {
        this.carCondition = carCondition;
    }

    public String getCarStatusE() {
        return carStatusE;
    }

    public void setCarStatusE(String carStatusE) {
        this.carStatusE = carStatusE;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
