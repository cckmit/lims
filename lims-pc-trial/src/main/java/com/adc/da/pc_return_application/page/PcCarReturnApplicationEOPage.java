package com.adc.da.pc_return_application.page;

import com.adc.da.base.page.BasePage;

import java.util.Date;

/**
 * <b>功能：</b>PC_CAR_RETURN_APPLICATION PcCarReturnApplicationEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-26 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcCarReturnApplicationEOPage extends BasePage {

    private String planCode;
    private String planCodeOperator = "=";
    private String testKm;
    private String testKmOperator = "=";
    private String testLine;
    private String testLineOperator = "=";
    private String carType;
    private String carTypeOperator = "=";
    private String testEndTime;
    private String testEndTimeOperator = "=";
    private String testStartTime;
    private String testStartTimeOperator = "=";
    private String testType;
    private String testTypeOperator = "=";
    private String testArea;
    private String testAreaOperator = "=";
    private String insprojectType;
    private String insprojectTypeOperator = "=";
    private String planReturnTime;
    private String planReturnTime1;
    private String planReturnTime2;
    private String planReturnTimeOperator = "=";
    private String carStatus;
    private String carStatusOperator = "=";
    private String buyTime;
    private String buyTimeOperator = "=";
    private String productTime;
    private String productTimeOperator = "=";
    private String dischargeType;
    private String dischargeTypeOperator = "=";
    private String carCode;
    private String carCodeOperator = "=";
    private String useFor;
    private String useForOperator = "=";
    private String createByPhone;
    private String createByPhoneOperator = "=";
    private String createByOrgid;
    private String createByOrgidOperator = "=";
    private String createByOrg;
    private String createByOrgOperator = "=";
    private String loanType;
    private String loanTypeOperator = "=";
    private String pvorcv;
    private String pvorcvOperator = "=";
    private String status;
    private String statusOperator = "=";
    private String engineCode;
    private String engineCodeOperator = "=";
    private String brandModel;
    private String brandModelOperator = "=";
    private String returnType;
    private String returnTypeOperator = "=";
    private String returnTime;
    private String returnTime1;
    private String returnTime2;
    private String returnTimeOperator = "=";
    private String createTime;
    private String createTime1;
    private String createTime2;
    private String createTimeOperator = "=";
    private String chassisCode;
    private String chassisCodeOperator = "=";
    private String createByName;
    private String createByNameOperator = "=";
    private String createBy;
    private String createByOperator = "=";
    private String delFlag;
    private String delFlagOperator = "=";
    private String id;
    private String idOperator = "=";
    private String taskId;
    private String taskIdOperator = "=";
    private String carId;
    private String carIdOperator = "=";
    private String beamCodeNum;
    private String beamCodeNumOperator = "=";
    private String weldingSpotNum;
    private String weldingSpotNumOperator = "=";
    private String technicalOrderName;
    private String technicalOrderNameOperator = "=";
    private String technicalOrderId;
    private String technicalOrderIdOperator = "=";
    private String changePartsName;
    private String changePartsNameOperator = "=";
    private String changePartsId;
    private String changePartsIdOperator = "=";
    private String appearanceState;
    private String appearanceStateOperator = "=";
    private String withTool;
    private String withToolOperator = "=";
    private String totalKm;
    private String totalKmOperator = "=";
    private String returnCarCode;
    private String taskNumber;
    private String taskNumberOperator = "=";

	/**
	 * 区分试验任务还是执行计划
	 * 0-试验任务；1-执行计划
	 */
	private String taskOrPlan;
    
	
	
    public String getTaskOrPlan() {
		return taskOrPlan;
	}

	public void setTaskOrPlan(String taskOrPlan) {
		this.taskOrPlan = taskOrPlan;
	}

	public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getTaskNumberOperator() {
        return taskNumberOperator;
    }

    public void setTaskNumberOperator(String taskNumberOperator) {
        this.taskNumberOperator = taskNumberOperator;
    }

    public String getReturnCarCode() {
        return returnCarCode;
    }

    public void setReturnCarCode(String returnCarCode) {
        this.returnCarCode = returnCarCode;
    }

    public String getPlanCode() {
        return this.planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getPlanCodeOperator() {
        return this.planCodeOperator;
    }

    public void setPlanCodeOperator(String planCodeOperator) {
        this.planCodeOperator = planCodeOperator;
    }

    public String getTestKm() {
        return this.testKm;
    }

    public void setTestKm(String testKm) {
        this.testKm = testKm;
    }

    public String getTestKmOperator() {
        return this.testKmOperator;
    }

    public void setTestKmOperator(String testKmOperator) {
        this.testKmOperator = testKmOperator;
    }

    public String getTestLine() {
        return this.testLine;
    }

    public void setTestLine(String testLine) {
        this.testLine = testLine;
    }

    public String getTestLineOperator() {
        return this.testLineOperator;
    }

    public void setTestLineOperator(String testLineOperator) {
        this.testLineOperator = testLineOperator;
    }

    public String getCarType() {
        return this.carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarTypeOperator() {
        return this.carTypeOperator;
    }

    public void setCarTypeOperator(String carTypeOperator) {
        this.carTypeOperator = carTypeOperator;
    }

    public String getTestEndTime() {
        return this.testEndTime;
    }

    public void setTestEndTime(String testEndTime) {
        this.testEndTime = testEndTime;
    }

    public String getTestEndTimeOperator() {
        return this.testEndTimeOperator;
    }

    public void setTestEndTimeOperator(String testEndTimeOperator) {
        this.testEndTimeOperator = testEndTimeOperator;
    }

    public String getTestStartTime() {
        return this.testStartTime;
    }

    public void setTestStartTime(String testStartTime) {
        this.testStartTime = testStartTime;
    }

    public String getTestStartTimeOperator() {
        return this.testStartTimeOperator;
    }

    public void setTestStartTimeOperator(String testStartTimeOperator) {
        this.testStartTimeOperator = testStartTimeOperator;
    }

    public String getTestType() {
        return this.testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getTestTypeOperator() {
        return this.testTypeOperator;
    }

    public void setTestTypeOperator(String testTypeOperator) {
        this.testTypeOperator = testTypeOperator;
    }

    public String getTestArea() {
        return this.testArea;
    }

    public void setTestArea(String testArea) {
        this.testArea = testArea;
    }

    public String getTestAreaOperator() {
        return this.testAreaOperator;
    }

    public void setTestAreaOperator(String testAreaOperator) {
        this.testAreaOperator = testAreaOperator;
    }

    public String getInsprojectType() {
        return this.insprojectType;
    }

    public void setInsprojectType(String insprojectType) {
        this.insprojectType = insprojectType;
    }

    public String getInsprojectTypeOperator() {
        return this.insprojectTypeOperator;
    }

    public void setInsprojectTypeOperator(String insprojectTypeOperator) {
        this.insprojectTypeOperator = insprojectTypeOperator;
    }

    public String getPlanReturnTime() {
        return this.planReturnTime;
    }

    public void setPlanReturnTime(String planReturnTime) {
        this.planReturnTime = planReturnTime;
    }

    public String getPlanReturnTime1() {
        return this.planReturnTime1;
    }

    public void setPlanReturnTime1(String planReturnTime1) {
        this.planReturnTime1 = planReturnTime1;
    }

    public String getPlanReturnTime2() {
        return this.planReturnTime2;
    }

    public void setPlanReturnTime2(String planReturnTime2) {
        this.planReturnTime2 = planReturnTime2;
    }

    public String getPlanReturnTimeOperator() {
        return this.planReturnTimeOperator;
    }

    public void setPlanReturnTimeOperator(String planReturnTimeOperator) {
        this.planReturnTimeOperator = planReturnTimeOperator;
    }

    public String getCarStatus() {
        return this.carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public String getCarStatusOperator() {
        return this.carStatusOperator;
    }

    public void setCarStatusOperator(String carStatusOperator) {
        this.carStatusOperator = carStatusOperator;
    }

    public String getBuyTime() {
        return this.buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getBuyTimeOperator() {
        return this.buyTimeOperator;
    }

    public void setBuyTimeOperator(String buyTimeOperator) {
        this.buyTimeOperator = buyTimeOperator;
    }

    public String getProductTime() {
        return this.productTime;
    }

    public void setProductTime(String productTime) {
        this.productTime = productTime;
    }

    public String getProductTimeOperator() {
        return this.productTimeOperator;
    }

    public void setProductTimeOperator(String productTimeOperator) {
        this.productTimeOperator = productTimeOperator;
    }

    public String getDischargeType() {
        return this.dischargeType;
    }

    public void setDischargeType(String dischargeType) {
        this.dischargeType = dischargeType;
    }

    public String getDischargeTypeOperator() {
        return this.dischargeTypeOperator;
    }

    public void setDischargeTypeOperator(String dischargeTypeOperator) {
        this.dischargeTypeOperator = dischargeTypeOperator;
    }

    public String getCarCode() {
        return this.carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    public String getCarCodeOperator() {
        return this.carCodeOperator;
    }

    public void setCarCodeOperator(String carCodeOperator) {
        this.carCodeOperator = carCodeOperator;
    }

    public String getUseFor() {
        return this.useFor;
    }

    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }

    public String getUseForOperator() {
        return this.useForOperator;
    }

    public void setUseForOperator(String useForOperator) {
        this.useForOperator = useForOperator;
    }

    public String getCreateByPhone() {
        return this.createByPhone;
    }

    public void setCreateByPhone(String createByPhone) {
        this.createByPhone = createByPhone;
    }

    public String getCreateByPhoneOperator() {
        return this.createByPhoneOperator;
    }

    public void setCreateByPhoneOperator(String createByPhoneOperator) {
        this.createByPhoneOperator = createByPhoneOperator;
    }

    public String getCreateByOrgid() {
        return this.createByOrgid;
    }

    public void setCreateByOrgid(String createByOrgid) {
        this.createByOrgid = createByOrgid;
    }

    public String getCreateByOrgidOperator() {
        return this.createByOrgidOperator;
    }

    public void setCreateByOrgidOperator(String createByOrgidOperator) {
        this.createByOrgidOperator = createByOrgidOperator;
    }

    public String getCreateByOrg() {
        return this.createByOrg;
    }

    public void setCreateByOrg(String createByOrg) {
        this.createByOrg = createByOrg;
    }

    public String getCreateByOrgOperator() {
        return this.createByOrgOperator;
    }

    public void setCreateByOrgOperator(String createByOrgOperator) {
        this.createByOrgOperator = createByOrgOperator;
    }

    public String getLoanType() {
        return this.loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getLoanTypeOperator() {
        return this.loanTypeOperator;
    }

    public void setLoanTypeOperator(String loanTypeOperator) {
        this.loanTypeOperator = loanTypeOperator;
    }

    public String getPvorcv() {
        return this.pvorcv;
    }

    public void setPvorcv(String pvorcv) {
        this.pvorcv = pvorcv;
    }

    public String getPvorcvOperator() {
        return this.pvorcvOperator;
    }

    public void setPvorcvOperator(String pvorcvOperator) {
        this.pvorcvOperator = pvorcvOperator;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusOperator() {
        return this.statusOperator;
    }

    public void setStatusOperator(String statusOperator) {
        this.statusOperator = statusOperator;
    }

    public String getEngineCode() {
        return this.engineCode;
    }

    public void setEngineCode(String engineCode) {
        this.engineCode = engineCode;
    }

    public String getEngineCodeOperator() {
        return this.engineCodeOperator;
    }

    public void setEngineCodeOperator(String engineCodeOperator) {
        this.engineCodeOperator = engineCodeOperator;
    }

    public String getBrandModel() {
        return this.brandModel;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel;
    }

    public String getBrandModelOperator() {
        return this.brandModelOperator;
    }

    public void setBrandModelOperator(String brandModelOperator) {
        this.brandModelOperator = brandModelOperator;
    }

    public String getReturnType() {
        return this.returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnTypeOperator() {
        return this.returnTypeOperator;
    }

    public void setReturnTypeOperator(String returnTypeOperator) {
        this.returnTypeOperator = returnTypeOperator;
    }

    public String getReturnTime() {
        return this.returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturnTime1() {
        return this.returnTime1;
    }

    public void setReturnTime1(String returnTime1) {
        this.returnTime1 = returnTime1;
    }

    public String getReturnTime2() {
        return this.returnTime2;
    }

    public void setReturnTime2(String returnTime2) {
        this.returnTime2 = returnTime2;
    }

    public String getReturnTimeOperator() {
        return this.returnTimeOperator;
    }

    public void setReturnTimeOperator(String returnTimeOperator) {
        this.returnTimeOperator = returnTimeOperator;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime1() {
        return this.createTime1;
    }

    public void setCreateTime1(String createTime1) {
        this.createTime1 = createTime1;
    }

    public String getCreateTime2() {
        return this.createTime2;
    }

    public void setCreateTime2(String createTime2) {
        this.createTime2 = createTime2;
    }

    public String getCreateTimeOperator() {
        return this.createTimeOperator;
    }

    public void setCreateTimeOperator(String createTimeOperator) {
        this.createTimeOperator = createTimeOperator;
    }

    public String getChassisCode() {
        return this.chassisCode;
    }

    public void setChassisCode(String chassisCode) {
        this.chassisCode = chassisCode;
    }

    public String getChassisCodeOperator() {
        return this.chassisCodeOperator;
    }

    public void setChassisCodeOperator(String chassisCodeOperator) {
        this.chassisCodeOperator = chassisCodeOperator;
    }

    public String getCreateByName() {
        return this.createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getCreateByNameOperator() {
        return this.createByNameOperator;
    }

    public void setCreateByNameOperator(String createByNameOperator) {
        this.createByNameOperator = createByNameOperator;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateByOperator() {
        return this.createByOperator;
    }

    public void setCreateByOperator(String createByOperator) {
        this.createByOperator = createByOperator;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlagOperator() {
        return this.delFlagOperator;
    }

    public void setDelFlagOperator(String delFlagOperator) {
        this.delFlagOperator = delFlagOperator;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOperator() {
        return this.idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskIdOperator() {
        return this.taskIdOperator;
    }

    public void setTaskIdOperator(String taskIdOperator) {
        this.taskIdOperator = taskIdOperator;
    }

    public String getCarId() {
        return this.carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarIdOperator() {
        return this.carIdOperator;
    }

    public void setCarIdOperator(String carIdOperator) {
        this.carIdOperator = carIdOperator;
    }

    public String getBeamCodeNum() {
        return this.beamCodeNum;
    }

    public void setBeamCodeNum(String beamCodeNum) {
        this.beamCodeNum = beamCodeNum;
    }

    public String getBeamCodeNumOperator() {
        return this.beamCodeNumOperator;
    }

    public void setBeamCodeNumOperator(String beamCodeNumOperator) {
        this.beamCodeNumOperator = beamCodeNumOperator;
    }

    public String getWeldingSpotNum() {
        return this.weldingSpotNum;
    }

    public void setWeldingSpotNum(String weldingSpotNum) {
        this.weldingSpotNum = weldingSpotNum;
    }

    public String getWeldingSpotNumOperator() {
        return this.weldingSpotNumOperator;
    }

    public void setWeldingSpotNumOperator(String weldingSpotNumOperator) {
        this.weldingSpotNumOperator = weldingSpotNumOperator;
    }

    public String getTechnicalOrderName() {
        return this.technicalOrderName;
    }

    public void setTechnicalOrderName(String technicalOrderName) {
        this.technicalOrderName = technicalOrderName;
    }

    public String getTechnicalOrderNameOperator() {
        return this.technicalOrderNameOperator;
    }

    public void setTechnicalOrderNameOperator(String technicalOrderNameOperator) {
        this.technicalOrderNameOperator = technicalOrderNameOperator;
    }

    public String getTechnicalOrderId() {
        return this.technicalOrderId;
    }

    public void setTechnicalOrderId(String technicalOrderId) {
        this.technicalOrderId = technicalOrderId;
    }

    public String getTechnicalOrderIdOperator() {
        return this.technicalOrderIdOperator;
    }

    public void setTechnicalOrderIdOperator(String technicalOrderIdOperator) {
        this.technicalOrderIdOperator = technicalOrderIdOperator;
    }

    public String getChangePartsName() {
        return this.changePartsName;
    }

    public void setChangePartsName(String changePartsName) {
        this.changePartsName = changePartsName;
    }

    public String getChangePartsNameOperator() {
        return this.changePartsNameOperator;
    }

    public void setChangePartsNameOperator(String changePartsNameOperator) {
        this.changePartsNameOperator = changePartsNameOperator;
    }

    public String getChangePartsId() {
        return this.changePartsId;
    }

    public void setChangePartsId(String changePartsId) {
        this.changePartsId = changePartsId;
    }

    public String getChangePartsIdOperator() {
        return this.changePartsIdOperator;
    }

    public void setChangePartsIdOperator(String changePartsIdOperator) {
        this.changePartsIdOperator = changePartsIdOperator;
    }

    public String getAppearanceState() {
        return this.appearanceState;
    }

    public void setAppearanceState(String appearanceState) {
        this.appearanceState = appearanceState;
    }

    public String getAppearanceStateOperator() {
        return this.appearanceStateOperator;
    }

    public void setAppearanceStateOperator(String appearanceStateOperator) {
        this.appearanceStateOperator = appearanceStateOperator;
    }

    public String getWithTool() {
        return this.withTool;
    }

    public void setWithTool(String withTool) {
        this.withTool = withTool;
    }

    public String getWithToolOperator() {
        return this.withToolOperator;
    }

    public void setWithToolOperator(String withToolOperator) {
        this.withToolOperator = withToolOperator;
    }

    public String getTotalKm() {
        return this.totalKm;
    }

    public void setTotalKm(String totalKm) {
        this.totalKm = totalKm;
    }

    public String getTotalKmOperator() {
        return this.totalKmOperator;
    }

    public void setTotalKmOperator(String totalKmOperator) {
        this.totalKmOperator = totalKmOperator;
    }

}
