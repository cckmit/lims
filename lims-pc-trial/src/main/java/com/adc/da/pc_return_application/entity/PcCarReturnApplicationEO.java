package com.adc.da.pc_return_application.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * <b>功能：</b>PC_CAR_RETURN_APPLICATION PcCarReturnApplicationEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-26 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcCarReturnApplicationEO extends BaseEntity {

    @ApiModelProperty(" 方案号 ")
    private String planCode;
    @ApiModelProperty(" 实验里程 ")
    private String testKm;
    @ApiModelProperty(" 试验路线 ")
    private String testLine;
    @ApiModelProperty(" 车辆类型 ")
    private String carType;
    @ApiModelProperty(" 实验结束时间 ")
    private String testEndTime;
    @ApiModelProperty(" 实验开始时间 ")
    private String testStartTime;
    @ApiModelProperty(" 实验类型 ")
    private String testType;
    @ApiModelProperty(" 试验区域 ")
    private String testArea;
    @ApiModelProperty(" 试验项目类别 ")
    private String insprojectType;
    @ApiModelProperty(" 计划归还日期 ")
    private String planReturnTime;
    @ApiModelProperty(" 样车状态 ")
    private String carStatus;
    @ApiModelProperty(" 购买日期 ")
    private String buyTime;
    @ApiModelProperty(" 生产日期 ")
    private String productTime;
    @ApiModelProperty(" 排放类型 ")
    private String dischargeType;
    @ApiModelProperty(" 车型 ")
    private String carCode;
    @ApiModelProperty(" 用途 ")
    private String useFor;
    @ApiModelProperty(" 申请人电话 ")
    private String createByPhone;
    @ApiModelProperty(" 申请人部门ID ")
    private String createByOrgid;
    @ApiModelProperty(" 申请人部门 ")
    private String createByOrg;
    @ApiModelProperty(" 借车单类型 ")
    private String loanType;
    @ApiModelProperty(" 部门类型    0：cv   1：pv ")
    private String pvorcv;
    @ApiModelProperty(" 流程状态 0:草稿   1：待审批  4：退回  5：已审批")
    private String status;
    @ApiModelProperty(" 发动机号 ")
    private String engineCode;
    @ApiModelProperty(" 品牌型号 ")
    private String brandModel;
    @ApiModelProperty(" 还车单类型 ")
    private String returnType;
    @ApiModelProperty(" 归还日期 ")
    private String returnTime;
    @ApiModelProperty(" 申请日期 ")
    private String createTime;
    @ApiModelProperty(" 底盘号 ")
    private String chassisCode;
    @ApiModelProperty(" 申请人NAME ")
    private String createByName;
    @ApiModelProperty(" 申请人ID ")
    private String createBy;
    @ApiModelProperty(" 删除标识  0：未删除   1：已删除 ")
    private String delFlag;
    @ApiModelProperty(" ID ")
    private String id;
    @ApiModelProperty(" 委托ID ")
    private String taskId;
    @ApiModelProperty(" 车辆主键 ")
    private String carId;
    @ApiModelProperty(" 大梁码数 ")
    private String beamCodeNum;
    @ApiModelProperty(" 焊点数 ")
    private String weldingSpotNum;
    @ApiModelProperty(" 技措单名称 ")
    private String technicalOrderName;
    @ApiModelProperty(" 技措单附件id ")
    private String technicalOrderId;
    @ApiModelProperty(" 换件清单附件名称 ")
    private String changePartsName;
    @ApiModelProperty(" 换件清单附件ID ")
    private String changePartsId;
    @ApiModelProperty(" 外观状态新旧程度 ")
    private String appearanceState;
    @ApiModelProperty(" 随车工具 ")
    private String withTool;
    @ApiModelProperty(" 总里程 ")
    private String totalKm;
    @ApiModelProperty("还车单编号")
    private String returnCarCode;
    @ApiModelProperty("试验任务编号")
    private String taskNumber;
    @ApiModelProperty("借车单编号")
    private String loanCarCode;
	/**
	 * 区分试验任务还是执行计划
	 * 0-试验任务；1-执行计划
	 */
	private String taskOrPlan;
    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>planCode -> plan_code</li>
     * <li>testKm -> test_km</li>
     * <li>testLine -> test_line</li>
     * <li>carType -> car_type</li>
     * <li>testEndTime -> test_end_time</li>
     * <li>testStartTime -> test_start_time</li>
     * <li>testType -> test_type</li>
     * <li>testArea -> test_area</li>
     * <li>insprojectType -> insproject_type</li>
     * <li>planReturnTime -> plan_return_time</li>
     * <li>carStatus -> car_status</li>
     * <li>buyTime -> buy_time</li>
     * <li>productTime -> product_time</li>
     * <li>dischargeType -> discharge_type</li>
     * <li>carCode -> car_code</li>
     * <li>useFor -> use_for</li>
     * <li>createByPhone -> create_by_phone</li>
     * <li>createByOrgid -> create_by_orgid</li>
     * <li>createByOrg -> create_by_org</li>
     * <li>loanType -> loan_type</li>
     * <li>pvorcv -> pvorcv</li>
     * <li>status -> status</li>
     * <li>engineCode -> engine_code</li>
     * <li>brandModel -> brand_model</li>
     * <li>returnType -> return_type</li>
     * <li>returnTime -> return_time</li>
     * <li>createTime -> create_time</li>
     * <li>chassisCode -> chassis_code</li>
     * <li>createByName -> create_by_name</li>
     * <li>createBy -> create_by</li>
     * <li>delFlag -> del_flag</li>
     * <li>id -> id</li>
     * <li>taskId -> task_id</li>
     * <li>carId -> car_id</li>
     * <li>beamCodeNum -> beam_code_num</li>
     * <li>weldingSpotNum -> welding_spot_num</li>
     * <li>technicalOrderName -> technical_order_name</li>
     * <li>technicalOrderId -> technical_order_id</li>
     * <li>changePartsName -> change_parts_name</li>
     * <li>changePartsId -> change_parts_id</li>
     * <li>appearanceState -> appearance_state</li>
     * <li>withTool -> with_tool</li>
     * <li>totalKm -> total_km</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) { return null; }
        switch (fieldName) {
            case "planCode": return "plan_code";
            case "testKm": return "test_km";
            case "testLine": return "test_line";
            case "carType": return "car_type";
            case "testEndTime": return "test_end_time";
            case "testStartTime": return "test_start_time";
            case "testType": return "test_type";
            case "testArea": return "test_area";
            case "insprojectType": return "insproject_type";
            case "planReturnTime": return "plan_return_time";
            case "carStatus": return "car_status";
            case "buyTime": return "buy_time";
            case "productTime": return "product_time";
            case "dischargeType": return "discharge_type";
            case "carCode": return "car_code";
            case "useFor": return "use_for";
            case "createByPhone": return "create_by_phone";
            case "createByOrgid": return "create_by_orgid";
            case "createByOrg": return "create_by_org";
            case "loanType": return "loan_type";
            case "pvorcv": return "pvorcv";
            case "status": return "status";
            case "engineCode": return "engine_code";
            case "brandModel": return "brand_model";
            case "returnType": return "return_type";
            case "returnTime": return "return_time";
            case "createTime": return "create_time";
            case "chassisCode": return "chassis_code";
            case "createByName": return "create_by_name";
            case "createBy": return "create_by";
            case "delFlag": return "del_flag";
            case "id": return "id";
            case "taskId": return "task_id";
            case "carId": return "car_id";
            case "beamCodeNum": return "beam_code_num";
            case "weldingSpotNum": return "welding_spot_num";
            case "technicalOrderName": return "technical_order_name";
            case "technicalOrderId": return "technical_order_id";
            case "changePartsName": return "change_parts_name";
            case "changePartsId": return "change_parts_id";
            case "appearanceState": return "appearance_state";
            case "withTool": return "with_tool";
            case "totalKm": return "total_km";
            case "loanCarCode": return "loan_car_code";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>plan_code -> planCode</li>
     * <li>test_km -> testKm</li>
     * <li>test_line -> testLine</li>
     * <li>car_type -> carType</li>
     * <li>test_end_time -> testEndTime</li>
     * <li>test_start_time -> testStartTime</li>
     * <li>test_type -> testType</li>
     * <li>test_area -> testArea</li>
     * <li>insproject_type -> insprojectType</li>
     * <li>plan_return_time -> planReturnTime</li>
     * <li>car_status -> carStatus</li>
     * <li>buy_time -> buyTime</li>
     * <li>product_time -> productTime</li>
     * <li>discharge_type -> dischargeType</li>
     * <li>car_code -> carCode</li>
     * <li>use_for -> useFor</li>
     * <li>create_by_phone -> createByPhone</li>
     * <li>create_by_orgid -> createByOrgid</li>
     * <li>create_by_org -> createByOrg</li>
     * <li>loan_type -> loanType</li>
     * <li>pvorcv -> pvorcv</li>
     * <li>status -> status</li>
     * <li>engine_code -> engineCode</li>
     * <li>brand_model -> brandModel</li>
     * <li>return_type -> returnType</li>
     * <li>return_time -> returnTime</li>
     * <li>create_time -> createTime</li>
     * <li>chassis_code -> chassisCode</li>
     * <li>create_by_name -> createByName</li>
     * <li>create_by -> createBy</li>
     * <li>del_flag -> delFlag</li>
     * <li>id -> id</li>
     * <li>task_id -> taskId</li>
     * <li>car_id -> carId</li>
     * <li>beam_code_num -> beamCodeNum</li>
     * <li>welding_spot_num -> weldingSpotNum</li>
     * <li>technical_order_name -> technicalOrderName</li>
     * <li>technical_order_id -> technicalOrderId</li>
     * <li>change_parts_name -> changePartsName</li>
     * <li>change_parts_id -> changePartsId</li>
     * <li>appearance_state -> appearanceState</li>
     * <li>with_tool -> withTool</li>
     * <li>total_km -> totalKm</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) { return null; }
        switch (columnName) {
            case "plan_code": return "planCode";
            case "test_km": return "testKm";
            case "test_line": return "testLine";
            case "car_type": return "carType";
            case "test_end_time": return "testEndTime";
            case "test_start_time": return "testStartTime";
            case "test_type": return "testType";
            case "test_area": return "testArea";
            case "insproject_type": return "insprojectType";
            case "plan_return_time": return "planReturnTime";
            case "car_status": return "carStatus";
            case "buy_time": return "buyTime";
            case "product_time": return "productTime";
            case "discharge_type": return "dischargeType";
            case "car_code": return "carCode";
            case "use_for": return "useFor";
            case "create_by_phone": return "createByPhone";
            case "create_by_orgid": return "createByOrgid";
            case "create_by_org": return "createByOrg";
            case "loan_type": return "loanType";
            case "pvorcv": return "pvorcv";
            case "status": return "status";
            case "engine_code": return "engineCode";
            case "brand_model": return "brandModel";
            case "return_type": return "returnType";
            case "return_time": return "returnTime";
            case "create_time": return "createTime";
            case "chassis_code": return "chassisCode";
            case "create_by_name": return "createByName";
            case "create_by": return "createBy";
            case "del_flag": return "delFlag";
            case "id": return "id";
            case "task_id": return "taskId";
            case "car_id": return "carId";
            case "beam_code_num": return "beamCodeNum";
            case "welding_spot_num": return "weldingSpotNum";
            case "technical_order_name": return "technicalOrderName";
            case "technical_order_id": return "technicalOrderId";
            case "change_parts_name": return "changePartsName";
            case "change_parts_id": return "changePartsId";
            case "appearance_state": return "appearanceState";
            case "with_tool": return "withTool";
            case "total_km": return "totalKm";
            case "loan_car_code": return "loanCarCode";
            default: return null;
        }
    }
    
    
    
    public String getTaskOrPlan() {
		return taskOrPlan;
	}

	public void setTaskOrPlan(String taskOrPlan) {
		this.taskOrPlan = taskOrPlan;
	}

	public String getPlanCode() {
        return this.planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getTestKm() {
        return this.testKm;
    }

    public void setTestKm(String testKm) {
        this.testKm = testKm;
    }

    public String getTestLine() {
        return this.testLine;
    }

    public void setTestLine(String testLine) {
        this.testLine = testLine;
    }

    public String getCarType() {
        return this.carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getTestEndTime() {
        return this.testEndTime;
    }

    public void setTestEndTime(String testEndTime) {
        this.testEndTime = testEndTime;
    }

    public String getTestStartTime() {
        return this.testStartTime;
    }

    public void setTestStartTime(String testStartTime) {
        this.testStartTime = testStartTime;
    }

    public String getTestType() {
        return this.testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getTestArea() {
        return this.testArea;
    }

    public void setTestArea(String testArea) {
        this.testArea = testArea;
    }

    public String getInsprojectType() {
        return this.insprojectType;
    }

    public void setInsprojectType(String insprojectType) {
        this.insprojectType = insprojectType;
    }

    public String getCarStatus() {
        return this.carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public String getBuyTime() {
        return this.buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getProductTime() {
        return this.productTime;
    }

    public void setProductTime(String productTime) {
        this.productTime = productTime;
    }

    public String getDischargeType() {
        return this.dischargeType;
    }

    public void setDischargeType(String dischargeType) {
        this.dischargeType = dischargeType;
    }

    public String getCarCode() {
        return this.carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    public String getUseFor() {
        return this.useFor;
    }

    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }

    public String getCreateByPhone() {
        return this.createByPhone;
    }

    public void setCreateByPhone(String createByPhone) {
        this.createByPhone = createByPhone;
    }

    public String getCreateByOrgid() {
        return this.createByOrgid;
    }

    public void setCreateByOrgid(String createByOrgid) {
        this.createByOrgid = createByOrgid;
    }

    public String getCreateByOrg() {
        return this.createByOrg;
    }

    public void setCreateByOrg(String createByOrg) {
        this.createByOrg = createByOrg;
    }

    public String getLoanType() {
        return this.loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getPvorcv() {
        return this.pvorcv;
    }

    public void setPvorcv(String pvorcv) {
        this.pvorcv = pvorcv;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEngineCode() {
        return this.engineCode;
    }

    public void setEngineCode(String engineCode) {
        this.engineCode = engineCode;
    }

    public String getBrandModel() {
        return this.brandModel;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel;
    }

    public String getReturnType() {
        return this.returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getChassisCode() {
        return this.chassisCode;
    }

    public void setChassisCode(String chassisCode) {
        this.chassisCode = chassisCode;
    }

    public String getCreateByName() {
        return this.createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCarId() {
        return this.carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getBeamCodeNum() {
        return this.beamCodeNum;
    }

    public void setBeamCodeNum(String beamCodeNum) {
        this.beamCodeNum = beamCodeNum;
    }

    public String getWeldingSpotNum() {
        return this.weldingSpotNum;
    }

    public void setWeldingSpotNum(String weldingSpotNum) {
        this.weldingSpotNum = weldingSpotNum;
    }

    public String getTechnicalOrderName() {
        return this.technicalOrderName;
    }

    public void setTechnicalOrderName(String technicalOrderName) {
        this.technicalOrderName = technicalOrderName;
    }

    public String getTechnicalOrderId() {
        return this.technicalOrderId;
    }

    public void setTechnicalOrderId(String technicalOrderId) {
        this.technicalOrderId = technicalOrderId;
    }

    public String getChangePartsName() {
        return this.changePartsName;
    }

    public void setChangePartsName(String changePartsName) {
        this.changePartsName = changePartsName;
    }

    public String getChangePartsId() {
        return this.changePartsId;
    }

    public void setChangePartsId(String changePartsId) {
        this.changePartsId = changePartsId;
    }

    public String getAppearanceState() {
        return this.appearanceState;
    }

    public void setAppearanceState(String appearanceState) {
        this.appearanceState = appearanceState;
    }

    public String getWithTool() {
        return this.withTool;
    }

    public void setWithTool(String withTool) {
        this.withTool = withTool;
    }

    public String getTotalKm() {
        return this.totalKm;
    }

    public void setTotalKm(String totalKm) {
        this.totalKm = totalKm;
    }

    public String getPlanReturnTime() {
        return planReturnTime;
    }

    public void setPlanReturnTime(String planReturnTime) {
        this.planReturnTime = planReturnTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReturnCarCode() {
        return returnCarCode;
    }

    public void setReturnCarCode(String returnCarCode) {
        this.returnCarCode = returnCarCode;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getLoanCarCode() {
        return loanCarCode;
    }

    public void setLoanCarCode(String loanCarCode) {
        this.loanCarCode = loanCarCode;
    }
}
