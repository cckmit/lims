package com.adc.da.car.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author     ：fengzhiwei
 * @date       ：Created in 2019/8/15 10:40
 * @description：${description}
 */
@ApiModel(value="com.adc.da.car.entity.SaCarData")
public class SaCarDataEO extends BaseEntity implements Serializable {
    @ApiModelProperty(value="null")
    private String id;

    /**
    * 删除状态 1删除 0存在
    */
    @ApiModelProperty(value="删除状态 1删除 0存在")
    private Integer delFlag;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private String createTime;

    /**
    * 创建人ID
    */
    @ApiModelProperty(value="创建人ID")
    private String createBy;

    /**
    * 更新时间
    */
    @ApiModelProperty(value="更新时间")
    private String updateTime;

    /**
    * 更新人ID
    */
    @ApiModelProperty(value="更新人ID")
    private String updateBy;

    /**
    * 项目ID
    */
    @ApiModelProperty(value="项目ID")
    private String bmProjectId;

    /**
    * 送样人ID
    */
    @ApiModelProperty(value="送样人ID")
    private String sendCarUserId;

    /**
    * 车辆名称
    */
    @ApiModelProperty(value="车辆名称")
    private String carName;

    /**
    * 乘员数量
    */
    @ApiModelProperty(value="乘员数量")
    private String passengerNumber;

    /**
    * VIN码
    */
    @ApiModelProperty(value="VIN码")
    private String carVin;

    /**
    * 里程数
    */
    @ApiModelProperty(value="里程数")
    private String carMilieage;

    /**
    * 发动机编号
    */
    @ApiModelProperty(value="发动机编号")
    private String carEngineNo;

    /**
    * 发动机型号
    */
    @ApiModelProperty(value="发动机型号")
    private String carEngineType;

    /**
    * 样车阶段
    */
    @ApiModelProperty(value="样车阶段")
    private Integer carStage;

    /**
    * 接收人ID
    */
    @ApiModelProperty(value="接收人ID")
    private String receivedUserId;



/**
    *  配置信息  注：原生产厂家
 */

    @ApiModelProperty(value="配置信息")
    private String producedFactory;


    /**
    * 零部件状态
    */
    @ApiModelProperty(value="零部件状态")
    private String partStatus;

    /**
    * 样车状态 0-退养   1-接收  2-领样  3-在库    4-报废 5-待收样 6-封存
    */
    @ApiModelProperty(value="样车状态 0-退养   1-接收  2-领样  3-在库    4-报废 5-待收样 6-封存")
    private Integer carStatus;

    /**
    * 接受日期
    */
    @ApiModelProperty(value="接受日期")
    private String receivedTime;

    /**
    * 整车库房ID
    */
    @ApiModelProperty(value="整车库房ID")
    private String carDepotId;

    /**
    * 占用车位号
    */
    @ApiModelProperty(value="占用车位号")
    private Integer carSpaceNumber;

    /**
    * 轮胎厂家
    */
    @ApiModelProperty(value="轮胎厂家")
    private String tyreFactory;

    /**
    * 是否借用（0，否；1，是）
    */
    @ApiModelProperty(value="是否借用（0，否；1，是）")
    private Integer whetherUse;

    /**
    * 轮胎型号
    */
    @ApiModelProperty(value="轮胎型号")
    private String tyreType;

    /**
    * 车型
    */
    @ApiModelProperty(value="车型")
    private String carType;

    /**
     * 车型号
     */
    @ApiModelProperty(value = "车型号")
    private String carModel;

    /**
    * 生产日期
    */
    @ApiModelProperty(value="生产日期")
    private String producedTime;

    /**
    * 胎压
    */
    @ApiModelProperty(value="胎压")
    private String tyrePressure;

    /**
    * 借用周期（单位：天）
    */
    @ApiModelProperty(value="借用周期（单位：天）")
    private String useCycle;

    /**
    * 整备质量
    */
    @ApiModelProperty(value="整备质量")
    private String carWeight;

    /**
    * 样车所在地
    */
    @ApiModelProperty(value="样车所在地")
    private String carLocation;

    /**
    * 样车所在地外场管理责任人ID
    */
    @ApiModelProperty(value="样车所在地外场管理责任人ID")
    private String carLocationManagerId;

    /**
    * 考核件清单ID(TS_ATTACHMENT表ID)
    */
    @ApiModelProperty(value="考核件清单ID(TS_ATTACHMENT表ID)")
    private String assessAttachmentId;

    /**
    * 装机报告考核件清单ID(TS_ATTACHMENT表ID)
    */
    @ApiModelProperty(value="装机报告考核件清单ID(TS_ATTACHMENT表ID)")
    private String reportAssessAttachmentId;

    /**
    * 退样单ID（TS_ATTACHMENT表ID）
    */
    @ApiModelProperty(value="退样单ID（TS_ATTACHMENT表ID）")
    private String returnCarAttachmentId;

    /**
    * 接收样车单ID(TS_ATTACHMENT表ID)
    */
    @ApiModelProperty(value="接收样车单ID(TS_ATTACHMENT表ID)")
    private String receiveCarAttachmentId;

    /**
    * 领样人ID
    */
    @ApiModelProperty(value="领样人ID")
    private String getCarUserId;

    /**
    * 整车所在位置
    */
    @ApiModelProperty(value="整车所在位置")
    private String carSpaceLocation;

    /**
    * 送样人姓名
    */
    @ApiModelProperty(value="送样人姓名")
    private String sendCarUserName;

    /**
    * 送样人电话
    */
    @ApiModelProperty(value="送样人电话")
    private String sendCarUserPhone;

    /**
    * 送样人部门
    */
    @ApiModelProperty(value="送样人部门")
    private String sendCarUserOrg;

    /**
    * 接收人姓名
    */
    @ApiModelProperty(value="接收人姓名")
    private String receivedUserName;

    /**
    * 样车所在地外场管理责任人姓名
    */
    @ApiModelProperty(value="样车所在地外场管理责任人姓名")
    private String carLocationManagerName;

    /**
    * 项目名称
    */
    @ApiModelProperty(value="项目名称")
    private String bmProjectName;

    /**
     * 创建人姓名
     */
    @ApiModelProperty(value="创建人姓名")
    private String createByName;

    /**
     * 试验委托ID
     */
    @ApiModelProperty(value="试验委托ID")
    private String trialApplyId;

    /**
     * 试验委托编号
     */
    @ApiModelProperty(value="试验委托编号")   ////逻辑变更：在任务申请时选用这个车子后，就把对应的任务书编号带来这里，没有被选中的就留空，任务完成后编号取消
    private String trialApplyNO;

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

    @ApiModelProperty(value = "创建人部门")
    private String createByDepart;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getBmProjectId() {
        return bmProjectId;
    }

    public void setBmProjectId(String bmProjectId) {
        this.bmProjectId = bmProjectId;
    }

    public String getSendCarUserId() {
        return sendCarUserId;
    }

    public void setSendCarUserId(String sendCarUserId) {
        this.sendCarUserId = sendCarUserId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getPassengerNumber() {
        return passengerNumber;
    }

    public void setPassengerNumber(String passengerNumber) {
        this.passengerNumber = passengerNumber;
    }

    public String getCarVin() {
        return carVin;
    }

    public void setCarVin(String carVin) {
        this.carVin = carVin;
    }

    public String getCarMilieage() {
        return carMilieage;
    }

    public void setCarMilieage(String carMilieage) {
        this.carMilieage = carMilieage;
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

    public Integer getCarStage() {
        return carStage;
    }

    public void setCarStage(Integer carStage) {
        this.carStage = carStage;
    }

    public String getReceivedUserId() {
        return receivedUserId;
    }

    public void setReceivedUserId(String receivedUserId) {
        this.receivedUserId = receivedUserId;
    }


    public String getProducedFactory() {
        return producedFactory;
    }

    public void setProducedFactory(String producedFactory) {
        this.producedFactory = producedFactory;
    }

    public String getPartStatus() {
        return partStatus;
    }

    public void setPartStatus(String partStatus) {
        this.partStatus = partStatus;
    }

    public Integer getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
    }

    public String getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(String receivedTime) {
        this.receivedTime = receivedTime;
    }

    public String getCarDepotId() {
        return carDepotId;
    }

    public void setCarDepotId(String carDepotId) {
        this.carDepotId = carDepotId;
    }

    public Integer getCarSpaceNumber() {
        return carSpaceNumber;
    }

    public void setCarSpaceNumber(Integer carSpaceNumber) {
        this.carSpaceNumber = carSpaceNumber;
    }

    public String getTyreFactory() {
        return tyreFactory;
    }

    public void setTyreFactory(String tyreFactory) {
        this.tyreFactory = tyreFactory;
    }

    public Integer getWhetherUse() {
        return whetherUse;
    }

    public void setWhetherUse(Integer whetherUse) {
        this.whetherUse = whetherUse;
    }

    public String getTyreType() {
        return tyreType;
    }

    public void setTyreType(String tyreType) {
        this.tyreType = tyreType;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getProducedTime() {
        return producedTime;
    }

    public void setProducedTime(String producedTime) {
        this.producedTime = producedTime;
    }

    public String getTyrePressure() {
        return tyrePressure;
    }

    public void setTyrePressure(String tyrePressure) {
        this.tyrePressure = tyrePressure;
    }

    public String getUseCycle() {
        return useCycle;
    }

    public void setUseCycle(String useCycle) {
        this.useCycle = useCycle;
    }

    public String getCarWeight() {
        return carWeight;
    }

    public void setCarWeight(String carWeight) {
        this.carWeight = carWeight;
    }

    public String getCarLocation() {
        return carLocation;
    }

    public void setCarLocation(String carLocation) {
        this.carLocation = carLocation;
    }

    public String getCarLocationManagerId() {
        return carLocationManagerId;
    }

    public void setCarLocationManagerId(String carLocationManagerId) {
        this.carLocationManagerId = carLocationManagerId;
    }

    public String getAssessAttachmentId() {
        return assessAttachmentId;
    }

    public void setAssessAttachmentId(String assessAttachmentId) {
        this.assessAttachmentId = assessAttachmentId;
    }

    public String getReportAssessAttachmentId() {
        return reportAssessAttachmentId;
    }

    public void setReportAssessAttachmentId(String reportAssessAttachmentId) {
        this.reportAssessAttachmentId = reportAssessAttachmentId;
    }

    public String getReturnCarAttachmentId() {
        return returnCarAttachmentId;
    }

    public void setReturnCarAttachmentId(String returnCarAttachmentId) {
        this.returnCarAttachmentId = returnCarAttachmentId;
    }

    public String getReceiveCarAttachmentId() {
        return receiveCarAttachmentId;
    }

    public void setReceiveCarAttachmentId(String receiveCarAttachmentId) {
        this.receiveCarAttachmentId = receiveCarAttachmentId;
    }

    public String getGetCarUserId() {
        return getCarUserId;
    }

    public void setGetCarUserId(String getCarUserId) {
        this.getCarUserId = getCarUserId;
    }

    public String getCarSpaceLocation() {
        return carSpaceLocation;
    }

    public void setCarSpaceLocation(String carSpaceLocation) {
        this.carSpaceLocation = carSpaceLocation;
    }

    public String getSendCarUserName() {
        return sendCarUserName;
    }

    public void setSendCarUserName(String sendCarUserName) {
        this.sendCarUserName = sendCarUserName;
    }

    public String getSendCarUserPhone() {
        return sendCarUserPhone;
    }

    public void setSendCarUserPhone(String sendCarUserPhone) {
        this.sendCarUserPhone = sendCarUserPhone;
    }

    public String getSendCarUserOrg() {
        return sendCarUserOrg;
    }

    public void setSendCarUserOrg(String sendCarUserOrg) {
        this.sendCarUserOrg = sendCarUserOrg;
    }

    public String getReceivedUserName() {
        return receivedUserName;
    }

    public void setReceivedUserName(String receivedUserName) {
        this.receivedUserName = receivedUserName;
    }

    public String getCarLocationManagerName() {
        return carLocationManagerName;
    }

    public void setCarLocationManagerName(String carLocationManagerName) {
        this.carLocationManagerName = carLocationManagerName;
    }

    public String getBmProjectName() {
        return bmProjectName;
    }

    public void setBmProjectName(String bmProjectName) {
        this.bmProjectName = bmProjectName;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
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

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCreateByDepart() {
        return createByDepart;
    }

    public void setCreateByDepart(String createByDepart) {
        this.createByDepart = createByDepart;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", bmProjectId=").append(bmProjectId);
        sb.append(", sendCarUserId=").append(sendCarUserId);
        sb.append(", carName=").append(carName);
        sb.append(", passengerNumber=").append(passengerNumber);
        sb.append(", carVin=").append(carVin);
        sb.append(", carMilieage=").append(carMilieage);
        sb.append(", carEngineNo=").append(carEngineNo);
        sb.append(", carEngineType=").append(carEngineType);
        sb.append(", carStage=").append(carStage);
        sb.append(", receivedUserId=").append(receivedUserId);
        sb.append(", producedFactory=").append(producedFactory);
        sb.append(", partStatus=").append(partStatus);
        sb.append(", carStatus=").append(carStatus);
        sb.append(", receivedTime=").append(receivedTime);
        sb.append(", carDepotId=").append(carDepotId);
        sb.append(", carSpaceNumber=").append(carSpaceNumber);
        sb.append(", tyreFactory=").append(tyreFactory);
        sb.append(", whetherUse=").append(whetherUse);
        sb.append(", tyreType=").append(tyreType);
        sb.append(", carType=").append(carType);
        sb.append(", producedTime=").append(producedTime);
        sb.append(", tyrePressure=").append(tyrePressure);
        sb.append(", useCycle=").append(useCycle);
        sb.append(", carWeight=").append(carWeight);
        sb.append(", carLocation=").append(carLocation);
        sb.append(", carLocationManagerId=").append(carLocationManagerId);
        sb.append(", assessAttachmentId=").append(assessAttachmentId);
        sb.append(", reportAssessAttachmentId=").append(reportAssessAttachmentId);
        sb.append(", returnCarAttachmentId=").append(returnCarAttachmentId);
        sb.append(", receiveCarAttachmentId=").append(receiveCarAttachmentId);
        sb.append(", getCarUserId=").append(getCarUserId);
        sb.append(", carSpaceLocation=").append(carSpaceLocation);
        sb.append(", sendCarUserName=").append(sendCarUserName);
        sb.append(", sendCarUserPhone=").append(sendCarUserPhone);
        sb.append(", sendCarUserOrg=").append(sendCarUserOrg);
        sb.append(", receivedUserName=").append(receivedUserName);
        sb.append(", carLocationManagerName=").append(carLocationManagerName);
        sb.append(", bmProjectName=").append(bmProjectName);
        sb.append(", chassisNumber=").append(chassisNumber);
        sb.append(", motorNumber=").append(motorNumber);
        sb.append(", batteryPack=").append(batteryPack);
        sb.append(", carCondition=").append(carCondition);
        sb.append(", carStatusE=").append(carStatusE);
        sb.append(", userName=").append(userName);
        sb.append(", carModel=").append(carModel);
        sb.append(", createByDepart=").append(createByDepart);
        sb.append("]");
        return sb.toString();
    }

}