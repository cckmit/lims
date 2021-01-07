package com.adc.da.equipment.entity;

import com.adc.da.base.entity.BaseEntity;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;


/**
 * <b>功能：</b>设备表<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-27 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class EquipmentEO extends BaseEntity {

    @ApiModelProperty(" 是否删除 0-有效  1-删除 ")
    private String delFlag;
    @ApiModelProperty(" 修改时间 ")
    private String updateTime;
    @ApiModelProperty(" 修改人 ")
    private String updateBy;
    @ApiModelProperty(" 创建时间 ")
    private String createTime;
    @ApiModelProperty(" 创建人 ")
    private String createBy;
    @ApiModelProperty(" 备注 ")
    private String eqRemark;
    @ApiModelProperty(" 设备使用状态 ")
    private String eqUseStatus;
    @ApiModelProperty(" 设备状态 ")
    private String eqStatus;
    @ApiModelProperty(" 父设备ID ")
    private String eqParentId;
    @ApiModelProperty(" 父设备名称 ")
    private String eqParentName;
    @ApiModelProperty(" 类别 ")
    private String eqSort;
    @ApiModelProperty(" 质保期结束日期 ")
    private String eqPeriodArrivalDate;
    @ApiModelProperty(" 最终验收日期 ")
    private String eqTerminationDate;
    @ApiModelProperty(" 调试验收日期 ")
    private String eqAcceptanceDate;
    @ApiModelProperty(" 开箱验收日期 ")
    private String eqOpeningDate;
    @ApiModelProperty(" 设备存放地点 ")
    private String eqStorageLocation;
    @ApiModelProperty(" 重要度 ")
    private String eqImportance;
    @ApiModelProperty(" 生产厂家 ")
    private String eqCompany;
    @ApiModelProperty(" 供应商 ")
    private String eqSupplier;
    @ApiModelProperty(" 管理单位 ")
    private String eqManagerOrg;
    @ApiModelProperty(" 管理人 ")
    private String eqManager;
    @ApiModelProperty(" 使用单位 ")
    private String eqUserOrg;
    @ApiModelProperty(" 使用人 ")
    private String eqUser;
    @ApiModelProperty(" 测量范围 ")
    private String eqSpecification;
    @ApiModelProperty(" 出厂编号 ")
    private String eqPlantNo;
    @ApiModelProperty(" 固定资产编号 ")
    private String eqAssetsNo;
    @ApiModelProperty(" 规格型号 ")
    private String eqType;
    @ApiModelProperty(" 设备名称 ")
    private String eqName;
    @ApiModelProperty(" 设备编号 ")
    private String eqNo;
    @ApiModelProperty(" ID ")
    private String id;
    @ApiModelProperty("核检记录")
    private List<EquipmentCheckEO> equipmentCheckList = new ArrayList<>();
    @ApiModelProperty("使用日志")
    private List<EquipmentUseLogEO> equipmentUseLogList = new ArrayList<>();
    //以下字段用作导出核检信息以及使用记录，请勿删除
    @ApiModelProperty(" 金额 ")
    private Long eqCkAmount;
    @ApiModelProperty(" 计划检定日期 ")
    private String eqCkCheckTimePlan;
    @ApiModelProperty(" 检定/校准单位联系方式 ")
    private String eqCkOrgTel;
    @ApiModelProperty(" 检定/校准单位地址 ")
    private String eqCkOrgAddress;
    @ApiModelProperty(" 检定/校准单位 ")
    private String eqCkOrg;
    @ApiModelProperty(" 检定/校准周期单位 ")
    private String eqCkCycleUnit;
    @ApiModelProperty(" 检定/校准周期 ")
    private Long eqCkCycle;
    @ApiModelProperty(" 检定方式 1-点检  2-核查  3-校准  4-保养 ")
    private String eqCkType;
    @ApiModelProperty(" 计划责任人 ")
    private String eqCkPlanOwner;
    @ApiModelProperty(" 实际检定日期 ")
    private String eqCkCheckTimeActual;
    @ApiModelProperty(" 备注 ")//---核检备注
    private String eqCkRemark;
    //设备导出使用记录字段
    @ApiModelProperty(" 设备状态 ")
    private String eqUlStatus;
    @ApiModelProperty(" 开始时间 ")
    private String eqUlStartTime;
    @ApiModelProperty(" 结束时间 ")
    private String eqUlEndTime;
    @ApiModelProperty(" 负责人 ")
    private String eqUlResponsiblePeople;
    @ApiModelProperty(" 负责人部门 ")
    private String eqUlResponsiblePeopleOrg;
    @ApiModelProperty(" 备注 ")
    private String eqUlRemark;
    @ApiModelProperty(" 签呈 ")
    private String eqFileId1;
    @ApiModelProperty(" 采购合同 ")
    private String eqFileId2;
    @ApiModelProperty(" 技术协议 ")
    private String eqFileId3;
    @ApiModelProperty(" 出厂文件 ")
    private String eqFileId4;
    @ApiModelProperty(" 验收文件 ")
    private String eqFileId5;
    @ApiModelProperty(" 说明书 ")
    private String eqFileId6;
    @ApiModelProperty(" 作业指导书 ")
    private String eqFileId7;
    @ApiModelProperty(" 培训记录 ")
    private String eqFileId8;
    @ApiModelProperty(" 其他文件 ")
    private String eqFileId9;
    @ApiModelProperty(" 签呈 ")
    private String eqFileName1;
    @ApiModelProperty(" 采购合同 ")
    private String eqFileName2;
    @ApiModelProperty(" 技术协议 ")
    private String eqFileName3;
    @ApiModelProperty(" 出厂文件 ")
    private String eqFileName4;
    @ApiModelProperty(" 验收文件 ")
    private String eqFileName5;
    @ApiModelProperty(" 说明书 ")
    private String eqFileName6;
    @ApiModelProperty(" 作业指导书 ")
    private String eqFileName7;
    @ApiModelProperty(" 培训记录 ")
    private String eqFileName8;
    @ApiModelProperty(" 其他文件 ")
    private String eqFileName9;
    @ApiModelProperty(" 设备借用记录 ")
    private List<EquipmentBorrowRecordEO> BorrowRecordList = new ArrayList<>();
    @ApiModelProperty(" 归还时间 ")
    private String brwReturnTime;
    @ApiModelProperty(" 借用时间 ")
    private String brwBorrowerTime
            ;
    @ApiModelProperty(" 借用人姓名 ")
    private String brwBorrowerName;
    @ApiModelProperty(" 流程状态;0:无审批流程；1：流程审批中前台好展示流程按钮；2：前台展示归还按钮 ")
    private String eqProcedureState;
    @ApiModelProperty(" 主要用途 ")
    private String eqMainPurpose;
    @ApiModelProperty(" 主要技术参数 ")
    private String eqMainParameter;
    @ApiModelProperty(" 设备分辨率 ")
    private String eqResolution;
    @ApiModelProperty(" 示值误差要求 ")
    private String eqErrorRequirement;
    @ApiModelProperty(" 设备原值（不含税） ")
    private String eqOriginalValue;
    @ApiModelProperty(" 合同编号 ")
    private String eqContractNumber;
    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>delFlag -> del_flag</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>createTime -> create_time</li>
     * <li>createBy -> create_by</li>
     * <li>eqRemark -> eq_remark</li>
     * <li>eqUseStatus -> eq_use_status</li>
     * <li>eqStatus -> eq_status</li>
     * <li>eqParentId -> eq_parent_id</li>
     * <li>eqParentName -> eq_parent_name</li>
     * <li>eqSort -> eq_sort</li>
     * <li>eqPeriodArrivalDate -> eq_period_arrival_date</li>
     * <li>eqTerminationDate -> eq_termination_date</li>
     * <li>eqAcceptanceDate -> eq_acceptance_date</li>
     * <li>eqOpeningDate -> eq_opening_date</li>
     * <li>eqStorageLocation -> eq_storage_location</li>
     * <li>eqSampleNumber -> eq_sample_number</li>
     * <li>eqImportance -> eq_importance</li>
     * <li>eqCompany -> eq_company</li>
     * <li>eqSupplier -> eq_supplier</li>
     * <li>eqManagerOrg -> eq_manager_org</li>
     * <li>eqManager -> eq_manager</li>
     * <li>eqUserOrg -> eq_user_org</li>
     * <li>eqUser -> eq_user</li>
     * <li>eqSpecification -> eq_specification</li>
     * <li>eqPlantNo -> eq_plant_no</li>
     * <li>eqAssetsNo -> eq_assets_no</li>
     * <li>eqType -> eq_type</li>
     * <li>eqName -> eq_name</li>
     * <li>eqNo -> eq_no</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) { return null; }
        switch (fieldName) {
            case "eqProcedureState": return "eq_Procedure_State";
            case "delFlag": return "del_flag";
            case "updateTime": return "update_time";
            case "updateBy": return "update_by";
            case "createTime": return "create_time";
            case "createBy": return "create_by";
            case "eqRemark": return "eq_remark";
            case "eqUseStatus": return "eq_use_status";
            case "eqStatus": return "eq_status";
            case "eqParentId": return "eq_parent_id";
            case "eqParentName": return "eq_parent_name";
            case "eqSort": return "eq_sort";
            case "eqPeriodArrivalDate": return "eq_period_arrival_date";
            case "eqTerminationDate": return "eq_termination_date";
            case "eqAcceptanceDate": return "eq_acceptance_date";
            case "eqOpeningDate": return "eq_opening_date";
            case "eqStorageLocation": return "eq_storage_location";
            case "eqSampleNumber": return "eq_sample_number";
            case "eqImportance": return "eq_importance";
            case "eqCompany": return "eq_company";
            case "eqSupplier": return "eq_supplier";
            case "eqManagerOrg": return "eq_manager_org";
            case "eqManager": return "eq_manager";
            case "eqUserOrg": return "eq_user_org";
            case "eqUser": return "eq_user";
            case "eqSpecification": return "eq_specification";
            case "eqPlantNo": return "eq_plant_no";
            case "eqAssetsNo": return "eq_assets_no";
            case "eqType": return "eq_type";
            case "eqName": return "eq_name";
            case "eqNo": return "eq_no";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>del_flag -> delFlag</li>
     * <li>update_time -> updateTime</li>
     * <li>update_by -> updateBy</li>
     * <li>create_time -> createTime</li>
     * <li>create_by -> createBy</li>
     * <li>eq_remark -> eqRemark</li>
     * <li>eq_use_status -> eqUseStatus</li>
     * <li>eq_status -> eqStatus</li>
     * <li>eq_parent_id -> eqParentId</li>
     * <li>eq_parent_name -> eqParentName</li>
     * <li>eq_sort -> eqSort</li>
     * <li>eq_period_arrival_date -> eqPeriodArrivalDate</li>
     * <li>eq_termination_date -> eqTerminationDate</li>
     * <li>eq_acceptance_date -> eqAcceptanceDate</li>
     * <li>eq_opening_date -> eqOpeningDate</li>
     * <li>eq_storage_location -> eqStorageLocation</li>
     * <li>eq_sample_number -> eqSampleNumber</li>
     * <li>eq_importance -> eqImportance</li>
     * <li>eq_company -> eqCompany</li>
     * <li>eq_supplier -> eqSupplier</li>
     * <li>eq_manager_org -> eqManagerOrg</li>
     * <li>eq_manager -> eqManager</li>
     * <li>eq_user_org -> eqUserOrg</li>
     * <li>eq_user -> eqUser</li>
     * <li>eq_specification -> eqSpecification</li>
     * <li>eq_plant_no -> eqPlantNo</li>
     * <li>eq_assets_no -> eqAssetsNo</li>
     * <li>eq_type -> eqType</li>
     * <li>eq_name -> eqName</li>
     * <li>eq_no -> eqNo</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) { return null; }
        switch (columnName) {
            case "eq_Procedure_State": return "eqProcedureState";
            case "del_flag": return "delFlag";
            case "update_time": return "updateTime";
            case "update_by": return "updateBy";
            case "create_time": return "createTime";
            case "create_by": return "createBy";
            case "eq_remark": return "eqRemark";
            case "eq_use_status": return "eqUseStatus";
            case "eq_status": return "eqStatus";
            case "eq_parent_id": return "eqParentId";
            case "eq_parent_name": return "eqParentName";
            case "eq_sort": return "eqSort";
            case "eq_period_arrival_date": return "eqPeriodArrivalDate";
            case "eq_termination_date": return "eqTerminationDate";
            case "eq_acceptance_date": return "eqAcceptanceDate";
            case "eq_opening_date": return "eqOpeningDate";
            case "eq_storage_location": return "eqStorageLocation";
            case "eq_sample_number": return "eqSampleNumber";
            case "eq_importance": return "eqImportance";
            case "eq_company": return "eqCompany";
            case "eq_supplier": return "eqSupplier";
            case "eq_manager_org": return "eqManagerOrg";
            case "eq_manager": return "eqManager";
            case "eq_user_org": return "eqUserOrg";
            case "eq_user": return "eqUser";
            case "eq_specification": return "eqSpecification";
            case "eq_plant_no": return "eqPlantNo";
            case "eq_assets_no": return "eqAssetsNo";
            case "eq_type": return "eqType";
            case "eq_name": return "eqName";
            case "eq_no": return "eqNo";
            case "id": return "id";
            default: return null;
        }
    }
    
    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getEqRemark() {
        return this.eqRemark;
    }

    public void setEqRemark(String eqRemark) {
        this.eqRemark = eqRemark;
    }

    public String getEqUseStatus() {
        return this.eqUseStatus;
    }

    public void setEqUseStatus(String eqUseStatus) {
        this.eqUseStatus = eqUseStatus;
    }

    public String getEqStatus() {
        return this.eqStatus;
    }

    public void setEqStatus(String eqStatus) {
        this.eqStatus = eqStatus;
    }

    public String getEqParentId() {
        return this.eqParentId;
    }

    public void setEqParentId(String eqParentId) {
        this.eqParentId = eqParentId;
    }

    public String getEqParentName() {
        return this.eqParentName;
    }

    public void setEqParentName(String eqParentName) {
        this.eqParentName = eqParentName;
    }

    public String getEqSort() {
        return this.eqSort;
    }

    public void setEqSort(String eqSort) {
        this.eqSort = eqSort;
    }

    public String getEqPeriodArrivalDate() {
        return this.eqPeriodArrivalDate;
    }

    public void setEqPeriodArrivalDate(String eqPeriodArrivalDate) {
        this.eqPeriodArrivalDate = eqPeriodArrivalDate;
    }

    public String getEqTerminationDate() {
        return this.eqTerminationDate;
    }

    public void setEqTerminationDate(String eqTerminationDate) {
        this.eqTerminationDate = eqTerminationDate;
    }

    public String getEqAcceptanceDate() {
        return this.eqAcceptanceDate;
    }

    public void setEqAcceptanceDate(String eqAcceptanceDate) {
        this.eqAcceptanceDate = eqAcceptanceDate;
    }

    public String getEqOpeningDate() {
        return this.eqOpeningDate;
    }

    public void setEqOpeningDate(String eqOpeningDate) {
        this.eqOpeningDate = eqOpeningDate;
    }

    public String getEqStorageLocation() {
        return this.eqStorageLocation;
    }

    public void setEqStorageLocation(String eqStorageLocation) {
        this.eqStorageLocation = eqStorageLocation;
    }

    public String getEqImportance() {
        return this.eqImportance;
    }

    public void setEqImportance(String eqImportance) {
        this.eqImportance = eqImportance;
    }

    public String getEqCompany() {
        return this.eqCompany;
    }

    public void setEqCompany(String eqCompany) {
        this.eqCompany = eqCompany;
    }

    public String getEqSupplier() {
        return this.eqSupplier;
    }

    public void setEqSupplier(String eqSupplier) {
        this.eqSupplier = eqSupplier;
    }

    public String getEqManagerOrg() {
        return this.eqManagerOrg;
    }

    public void setEqManagerOrg(String eqManagerOrg) {
        this.eqManagerOrg = eqManagerOrg;
    }

    public String getEqManager() {
        return this.eqManager;
    }

    public void setEqManager(String eqManager) {
        this.eqManager = eqManager;
    }

    public String getEqUserOrg() {
        return this.eqUserOrg;
    }

    public void setEqUserOrg(String eqUserOrg) {
        this.eqUserOrg = eqUserOrg;
    }

    public String getEqUser() {
        return this.eqUser;
    }

    public void setEqUser(String eqUser) {
        this.eqUser = eqUser;
    }

    public String getEqSpecification() {
        return this.eqSpecification;
    }

    public void setEqSpecification(String eqSpecification) {
        this.eqSpecification = eqSpecification;
    }

    public String getEqPlantNo() {
        return this.eqPlantNo;
    }

    public void setEqPlantNo(String eqPlantNo) {
        this.eqPlantNo = eqPlantNo;
    }

    public String getEqAssetsNo() {
        return this.eqAssetsNo;
    }

    public void setEqAssetsNo(String eqAssetsNo) {
        this.eqAssetsNo = eqAssetsNo;
    }

    public String getEqType() {
        return this.eqType;
    }

    public void setEqType(String eqType) {
        this.eqType = eqType;
    }

    public String getEqName() {
        return this.eqName;
    }

    public void setEqName(String eqName) {
        this.eqName = eqName;
    }

    public String getEqNo() {
        return this.eqNo;
    }

    public void setEqNo(String eqNo) {
        this.eqNo = eqNo;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<EquipmentCheckEO> getEquipmentCheckList() {
        return equipmentCheckList;
    }

    public void setEquipmentCheckList(List<EquipmentCheckEO> equipmentCheckList) {
        this.equipmentCheckList = equipmentCheckList;
    }

    public Long getEqCkAmount() {
        return eqCkAmount;
    }

    public void setEqCkAmount(Long eqCkAmount) {
        this.eqCkAmount = eqCkAmount;
    }

    public String getEqCkCheckTimePlan() {
        return eqCkCheckTimePlan;
    }

    public void setEqCkCheckTimePlan(String eqCkCheckTimePlan) {
        this.eqCkCheckTimePlan = eqCkCheckTimePlan;
    }

    public String getEqCkOrgTel() {
        return eqCkOrgTel;
    }

    public void setEqCkOrgTel(String eqCkOrgTel) {
        this.eqCkOrgTel = eqCkOrgTel;
    }

    public String getEqCkOrgAddress() {
        return eqCkOrgAddress;
    }

    public void setEqCkOrgAddress(String eqCkOrgAddress) {
        this.eqCkOrgAddress = eqCkOrgAddress;
    }

    public String getEqCkOrg() {
        return eqCkOrg;
    }

    public void setEqCkOrg(String eqCkOrg) {
        this.eqCkOrg = eqCkOrg;
    }

    public String getEqCkCycleUnit() {
        return eqCkCycleUnit;
    }

    public void setEqCkCycleUnit(String eqCkCycleUnit) {
        this.eqCkCycleUnit = eqCkCycleUnit;
    }

    public Long getEqCkCycle() {
        return eqCkCycle;
    }

    public void setEqCkCycle(Long eqCkCycle) {
        this.eqCkCycle = eqCkCycle;
    }

    public String getEqCkType() {
        return eqCkType;
    }

    public void setEqCkType(String eqCkType) {
        this.eqCkType = eqCkType;
    }

    public String getEqCkPlanOwner() {
        return eqCkPlanOwner;
    }

    public void setEqCkPlanOwner(String eqCkPlanOwner) {
        this.eqCkPlanOwner = eqCkPlanOwner;
    }

    public String getEqCkCheckTimeActual() {
        return eqCkCheckTimeActual;
    }

    public void setEqCkCheckTimeActual(String eqCkCheckTimeActual) {
        this.eqCkCheckTimeActual = eqCkCheckTimeActual;
    }

    public String getEqCkRemark() {
        return eqCkRemark;
    }

    public void setEqCkRemark(String eqCkRemark) {
        this.eqCkRemark = eqCkRemark;
    }

    public List<EquipmentUseLogEO> getEquipmentUseLogList() {
        return equipmentUseLogList;
    }

    public void setEquipmentUseLogList(List<EquipmentUseLogEO> equipmentUseLogList) {
        this.equipmentUseLogList = equipmentUseLogList;
    }

    public String getEqUlStatus() {
        return eqUlStatus;
    }

    public void setEqUlStatus(String eqUlStatus) {
        this.eqUlStatus = eqUlStatus;
    }

    public String getEqUlStartTime() {
        return eqUlStartTime;
    }

    public void setEqUlStartTime(String eqUlStartTime) {
        this.eqUlStartTime = eqUlStartTime;
    }

    public String getEqUlEndTime() {
        return eqUlEndTime;
    }

    public void setEqUlEndTime(String eqUlEndTime) {
        this.eqUlEndTime = eqUlEndTime;
    }

    public String getEqUlResponsiblePeople() {
        return eqUlResponsiblePeople;
    }

    public void setEqUlResponsiblePeople(String eqUlResponsiblePeople) {
        this.eqUlResponsiblePeople = eqUlResponsiblePeople;
    }

    public String getEqUlResponsiblePeopleOrg() {
        return eqUlResponsiblePeopleOrg;
    }

    public void setEqUlResponsiblePeopleOrg(String eqUlResponsiblePeopleOrg) {
        this.eqUlResponsiblePeopleOrg = eqUlResponsiblePeopleOrg;
    }

    public String getEqUlRemark() {
        return eqUlRemark;
    }

    public void setEqUlRemark(String eqUlRemark) {
        this.eqUlRemark = eqUlRemark;
    }

	public String getEqFileId1() {
		return eqFileId1;
	}

	public void setEqFileId1(String eqFileId1) {
		this.eqFileId1 = eqFileId1;
	}

	public String getEqFileId2() {
		return eqFileId2;
	}

	public void setEqFileId2(String eqFileId2) {
		this.eqFileId2 = eqFileId2;
	}

	public String getEqFileId3() {
		return eqFileId3;
	}

	public void setEqFileId3(String eqFileId3) {
		this.eqFileId3 = eqFileId3;
	}

	public String getEqFileId4() {
		return eqFileId4;
	}

	public void setEqFileId4(String eqFileId4) {
		this.eqFileId4 = eqFileId4;
	}

	public String getEqFileId5() {
		return eqFileId5;
	}

	public void setEqFileId5(String eqFileId5) {
		this.eqFileId5 = eqFileId5;
	}

	public String getEqFileId6() {
		return eqFileId6;
	}

	public void setEqFileId6(String eqFileId6) {
		this.eqFileId6 = eqFileId6;
	}

	public String getEqFileId7() {
		return eqFileId7;
	}

	public void setEqFileId7(String eqFileId7) {
		this.eqFileId7 = eqFileId7;
	}

	public String getEqFileId8() {
		return eqFileId8;
	}

	public void setEqFileId8(String eqFileId8) {
		this.eqFileId8 = eqFileId8;
	}

	public String getEqFileId9() {
		return eqFileId9;
	}

	public void setEqFileId9(String eqFileId9) {
		this.eqFileId9 = eqFileId9;
	}

	public String getEqFileName1() {
		return eqFileName1;
	}

	public void setEqFileName1(String eqFileName1) {
		this.eqFileName1 = eqFileName1;
	}

	public String getEqFileName2() {
		return eqFileName2;
	}

	public void setEqFileName2(String eqFileName2) {
		this.eqFileName2 = eqFileName2;
	}

	public String getEqFileName3() {
		return eqFileName3;
	}

	public void setEqFileName3(String eqFileName3) {
		this.eqFileName3 = eqFileName3;
	}

	public String getEqFileName4() {
		return eqFileName4;
	}

	public void setEqFileName4(String eqFileName4) {
		this.eqFileName4 = eqFileName4;
	}

	public String getEqFileName5() {
		return eqFileName5;
	}

	public void setEqFileName5(String eqFileName5) {
		this.eqFileName5 = eqFileName5;
	}

	public String getEqFileName6() {
		return eqFileName6;
	}

	public void setEqFileName6(String eqFileName6) {
		this.eqFileName6 = eqFileName6;
	}

	public String getEqFileName7() {
		return eqFileName7;
	}

	public void setEqFileName7(String eqFileName7) {
		this.eqFileName7 = eqFileName7;
	}

	public String getEqFileName8() {
		return eqFileName8;
	}

	public void setEqFileName8(String eqFileName8) {
		this.eqFileName8 = eqFileName8;
	}

	public String getEqFileName9() {
		return eqFileName9;
	}

	public void setEqFileName9(String eqFileName9) {
		this.eqFileName9 = eqFileName9;
	}

    public List<EquipmentBorrowRecordEO> getBorrowRecordList() {
        return BorrowRecordList;
    }

    public void setBorrowRecordList(List<EquipmentBorrowRecordEO> borrowRecordList) {
        BorrowRecordList = borrowRecordList;
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

    public String getEqProcedureState() {
        return eqProcedureState;
    }

    public void setEqProcedureState(String eqProcedureState) {
        this.eqProcedureState = eqProcedureState;
    }

	public String getEqMainPurpose() {
		return eqMainPurpose;
	}

	public void setEqMainPurpose(String eqMainPurpose) {
		this.eqMainPurpose = eqMainPurpose;
	}

	public String getEqMainParameter() {
		return eqMainParameter;
	}

	public void setEqMainParameter(String eqMainParameter) {
		this.eqMainParameter = eqMainParameter;
	}

	public String getEqResolution() {
		return eqResolution;
	}

	public void setEqResolution(String eqResolution) {
		this.eqResolution = eqResolution;
	}

	public String getEqErrorRequirement() {
		return eqErrorRequirement;
	}

	public void setEqErrorRequirement(String eqErrorRequirement) {
		this.eqErrorRequirement = eqErrorRequirement;
	}

	public String getEqOriginalValue() {
		return eqOriginalValue;
	}

	public void setEqOriginalValue(String eqOriginalValue) {
		this.eqOriginalValue = eqOriginalValue;
	}

	public String getEqContractNumber() {
		return eqContractNumber;
	}

	public void setEqContractNumber(String eqContractNumber) {
		this.eqContractNumber = eqContractNumber;
	}
    
    
}
