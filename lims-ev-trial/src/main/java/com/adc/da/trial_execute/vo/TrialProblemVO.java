package com.adc.da.trial_execute.vo;

public class TrialProblemVO {
	 /**
     *主键
     */
    private String id;

    /**
     *试验任务id
     */
    private String trialtaskId;

    /**
     *运行时长
     */
    private String runHours;

    /**
     *故障日期
     */
    private String faultTime;

    /**
     *零部件试验时长
     */
    private String partUseHours;

    /**
     *故障停机时长
     */
    private String faultStopHours;

    /**
     *故障次数
     */
    private String faultNum;

    /**
     *故障类型
     */
    private String faultType;

    /**
     *故障总成部件名称(故障零部件名称)
     */
    private String faultPartName;

    /**
     *故障件图号
     */
    private String faulePartPicno;

    /**
     *台架搭建状态
     */
    private String scaffoldingStatus;

    /**
     *故障描述
     */
    private String faultDescription;
    /**
     * 创建者名
     */
    private String createByName;
    /**
     *创建时间
     */
    private String createTime;
    /**
     *发动机型号 
     */
    private String engineModel;
    /**
     * 发动机开发阶段
     */
    private String engineDevelopStage;
    /**
     * 发动机编号
     */
    private String engineNumber;
    /**
     * ECU状态
     */
    private String ECUStatus;
    /**
     * 台架编号
     */
    private String orgNames;
    /**
     * 试验负责人
     */
    private String trialEngineer;
    /**
     * 试验项目
     */
    private String insProNames;
    /**
     * 试验任务编号
     */
    private String eVNumber;
    /**
     * 试验任务名称
     */
    private String eVTitle;
    /**
     *图片名称
     */
    private String pictureName;
    /**
     *图片id
     */
    private String pictureId;
    
    
    
	public String geteVTitle() {
		return eVTitle;
	}

	public void seteVTitle(String eVTitle) {
		this.eVTitle = eVTitle;
	}
    
	public String geteVNumber() {
		return eVNumber;
	}

	public void seteVNumber(String eVNumber) {
		this.eVNumber = eVNumber;
	}
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTrialtaskId() {
		return trialtaskId;
	}
	public void setTrialtaskId(String trialtaskId) {
		this.trialtaskId = trialtaskId;
	}
	public String getRunHours() {
		return runHours;
	}
	public void setRunHours(String runHours) {
		this.runHours = runHours;
	}
	public String getFaultTime() {
		return faultTime;
	}
	public void setFaultTime(String faultTime) {
		this.faultTime = faultTime;
	}
	public String getPartUseHours() {
		return partUseHours;
	}
	public void setPartUseHours(String partUseHours) {
		this.partUseHours = partUseHours;
	}
	public String getFaultStopHours() {
		return faultStopHours;
	}
	public void setFaultStopHours(String faultStopHours) {
		this.faultStopHours = faultStopHours;
	}
	public String getFaultNum() {
		return faultNum;
	}
	public void setFaultNum(String faultNum) {
		this.faultNum = faultNum;
	}
	public String getFaultType() {
		return faultType;
	}
	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}
	public String getFaultPartName() {
		return faultPartName;
	}
	public void setFaultPartName(String faultPartName) {
		this.faultPartName = faultPartName;
	}
	public String getFaulePartPicno() {
		return faulePartPicno;
	}
	public void setFaulePartPicno(String faulePartPicno) {
		this.faulePartPicno = faulePartPicno;
	}
	public String getScaffoldingStatus() {
		return scaffoldingStatus;
	}
	public void setScaffoldingStatus(String scaffoldingStatus) {
		this.scaffoldingStatus = scaffoldingStatus;
	}
	public String getFaultDescription() {
		return faultDescription;
	}
	public void setFaultDescription(String faultDescription) {
		this.faultDescription = faultDescription;
	}
	public String getCreateByName() {
		return createByName;
	}
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getEngineModel() {
		return engineModel;
	}
	public void setEngineModel(String engineModel) {
		this.engineModel = engineModel;
	}
	public String getEngineDevelopStage() {
		return engineDevelopStage;
	}
	public void setEngineDevelopStage(String engineDevelopStage) {
		this.engineDevelopStage = engineDevelopStage;
	}
	public String getEngineNumber() {
		return engineNumber;
	}
	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}
	public String getECUStatus() {
		return ECUStatus;
	}
	public void setECUStatus(String eCUStatus) {
		ECUStatus = eCUStatus;
	}
	public String getOrgNames() {
		return orgNames;
	}
	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}
	public String getTrialEngineer() {
		return trialEngineer;
	}
	public void setTrialEngineer(String trialEngineer) {
		this.trialEngineer = trialEngineer;
	}
	public String getInsProNames() {
		return insProNames;
	}
	public void setInsProNames(String insProNames) {
		this.insProNames = insProNames;
	}
    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }
}
