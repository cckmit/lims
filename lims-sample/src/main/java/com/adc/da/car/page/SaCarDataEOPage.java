package com.adc.da.car.page;

import com.adc.da.base.page.BasePage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/19 9:26
 * @description：${description}
 */
public class SaCarDataEOPage extends BasePage {

    @ApiModelProperty("检验委托合同编号")
    private String trialApplyNO;

    @ApiModelProperty("车辆名称")
    private String carName;

    @ApiModelProperty("项目平台")
    private String projectName;

    @ApiModelProperty("vin码")
    private String carVin;

    @ApiModelProperty("生产厂家")
    private String producedFactory;

    @ApiModelProperty("送样人")
    private String sendCarUserName;

    @ApiModelProperty("领养人")
    private String getCarUserName;

    @ApiModelProperty("所在位置")
    private String carSpaceLocation;

    @ApiModelProperty("整车状态")
    private String carStatus;

    @ApiModelProperty("通用查询")
    private String searchPhrase;

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

    @ApiModelProperty(value="底盘号")
    private String chassisNumber;
    /**
     * 通过查询条件集合
     */
    @JsonIgnore
    private List<String> keyWords;

    public String getTrialApplyNO() {
        return trialApplyNO;
    }

    public void setTrialApplyNO(String trialApplyNO) {
        this.trialApplyNO = trialApplyNO;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getSendCarUserName() {
        return sendCarUserName;
    }

    public void setSendCarUserName(String sendCarUserName) {
        this.sendCarUserName = sendCarUserName;
    }

    public String getGetCarUserName() {
        return getCarUserName;
    }

    public void setGetCarUserName(String getCarUserName) {
        this.getCarUserName = getCarUserName;
    }

    public String getCarSpaceLocation() {
        return carSpaceLocation;
    }

    public void setCarSpaceLocation(String carSpaceLocation) {
        this.carSpaceLocation = carSpaceLocation;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
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

	public String getChassisNumber() {
		return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}
    
    
}
