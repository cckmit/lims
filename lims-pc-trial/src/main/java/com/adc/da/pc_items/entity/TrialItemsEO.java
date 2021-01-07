package com.adc.da.pc_items.entity;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.car.entity.SaCarDataEO;
import com.adc.da.trial_report.entity.TrialReportEO;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>PC_TRIAL_ITEMS TrialItemsEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class TrialItemsEO extends BaseEntity {

    private String id;
    private String delFlag;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    @ApiModelProperty("检验项目ID")
    private String insproId;
    @ApiModelProperty("检验项目名")
    private String insproName;
    @ApiModelProperty("检验标准名")
    private String insStdName;
    @ApiModelProperty("执行科室id")
    private String insproOrgId;
    @ApiModelProperty("执行科室名")
    private String insproOrgName;
    @ApiModelProperty("磨合行驶")
    private String trRunning;
    @ApiModelProperty("一般公路")
    private String trGenWay;
    @ApiModelProperty("市区+阻滞")
    private String trCandb;
    @ApiModelProperty("高速")
    private String trHighway;
    @ApiModelProperty("山路")
    private String trMountain;
    @ApiModelProperty("强化环路")
    private String trRing;
    @ApiModelProperty("越野")
    private String trCross;
    @ApiModelProperty("累计里程")
    private String trTotle;
    @ApiModelProperty("试验特殊要求")
    private String insproSpecialRequire;
    @ApiModelProperty("整车样品id")
    private String carId;
    @ApiModelProperty("样品类型")
    private String sampleType;
    @ApiModelProperty("整车样品名称")
    private String carName;
    @ApiModelProperty("零部件样品id")
    private String partId;
    @ApiModelProperty("零部件样品名称")
    private String partName;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("vin码")
    private String vinCode;
    @ApiModelProperty("车型")
    private String vehicleType;
    @ApiModelProperty("零部件/发动机号")
    private String partEngineNo;
    @ApiModelProperty("底盘号")
    private String chassisNumber;
    @ApiModelProperty("任务id")
    private String trialTaskId;
    private String createByName;
    @ApiModelProperty("报告id")
    private String reportId;
    private String itemsStatus = "0";
    private TrialReportEO trialReportEO;  //报告
    @ApiModelProperty("样品实体")
    private SaCarDataEO saCarDataEO;
    @ApiModelProperty("样品编号")
    private String sampleNo;
    @ApiModelProperty("工程师")
    private String trialEngineer;
    @ApiModelProperty("附件id")
    private String attachId;
    @ApiModelProperty("附件名称")
    private String attachName;
    @ApiModelProperty("台架id")
    private String benchOrgId;
    @ApiModelProperty("台架名称")
    private String benchOrgName;

    /** 试验号 */
    private String trialNum;

    @ApiModelProperty("设备名称")
    private String equipmentName;   //设备名称
    @ApiModelProperty("设备id")
    private String equipmentId;   //设备id
    
    public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	public String getBenchOrgId() {
		return benchOrgId;
	}

	public void setBenchOrgId(String benchOrgId) {
		this.benchOrgId = benchOrgId;
	}

	public String getBenchOrgName() {
		return benchOrgName;
	}

	public void setBenchOrgName(String benchOrgName) {
		this.benchOrgName = benchOrgName;
	}

	public String getTrialEngineer() {
        return trialEngineer;
    }

    public void setTrialEngineer(String trialEngineer) {
        this.trialEngineer = trialEngineer;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public SaCarDataEO getSaCarDataEO() {
        return saCarDataEO;
    }

    public void setSaCarDataEO(SaCarDataEO saCarDataEO) {
        this.saCarDataEO = saCarDataEO;
    }

    public TrialReportEO getTrialReportEO() {
        return trialReportEO;
    }

    public void setTrialReportEO(TrialReportEO trialReportEO) {
        this.trialReportEO = trialReportEO;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getItemsStatus() {
        return itemsStatus;
    }

    public void setItemsStatus(String itemsStatus) {
        this.itemsStatus = itemsStatus;
    }

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>delFlag -> del_flag</li>
     * <li>createTime -> create_time</li>
     * <li>createBy -> create_by</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>insproId -> inspro_id</li>
     * <li>insproName -> inspro_name</li>
     * <li>insStdName -> ins_std_name</li>
     * <li>insproOrgId -> inspro_org_id</li>
     * <li>insproOrgName -> inspro_org_name</li>
     * <li>trRunning -> tr_running</li>
     * <li>trGenWay -> tr_gen_way</li>
     * <li>trCandb -> tr_candb</li>
     * <li>trHighway -> tr_highway</li>
     * <li>trMountain -> tr_mountain</li>
     * <li>trRing -> tr_ring</li>
     * <li>trCross -> tr_cross</li>
     * <li>trTotle -> tr_totle</li>
     * <li>insproSpecialRequire -> inspro_special_require</li>
     * <li>sampleId -> sample_id</li>
     * <li>sampleType -> sample_type</li>
     * <li>sampleName -> sample_name</li>
     * <li>remark -> remark</li>
     * <li>vinCode -> vin_code</li>
     * <li>vehicleType -> vehicle_type</li>
     * <li>engineNum -> engine_num</li>
     * <li>trialTaskId -> trial_task_id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "delFlag": return "del_flag";
            case "createTime": return "create_time";
            case "createBy": return "create_by";
            case "updateTime": return "update_time";
            case "updateBy": return "update_by";
            case "insproId": return "inspro_id";
            case "insproName": return "inspro_name";
            case "insStdName": return "ins_std_name";
            case "insproOrgId": return "inspro_org_id";
            case "insproOrgName": return "inspro_org_name";
            case "trRunning": return "tr_running";
            case "trGenWay": return "tr_gen_way";
            case "trCandb": return "tr_candb";
            case "trHighway": return "tr_highway";
            case "trMountain": return "tr_mountain";
            case "trRing": return "tr_ring";
            case "trCross": return "tr_cross";
            case "trTotle": return "tr_totle";
            case "insproSpecialRequire": return "inspro_special_require";
            case "sampleId": return "sample_id";
            case "sampleType": return "sample_type";
/*            case "sampleName": return "sample_name";*/
            case "remark": return "remark";
            case "vinCode": return "vin_code";
            case "vehicleType": return "vehicle_type";
            case "engineNum": return "engine_num";
            case "trialTaskId": return "trial_task_id";
            case "attachId" : return "attach_id";
            case "attachName" : return "attach_name";
            case "benchOrgId" : return "bench_org_id";
            case "benchOrgName" : return "bench_org_name";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>del_flag -> delFlag</li>
     * <li>create_time -> createTime</li>
     * <li>create_by -> createBy</li>
     * <li>update_time -> updateTime</li>
     * <li>update_by -> updateBy</li>
     * <li>inspro_id -> insproId</li>
     * <li>inspro_name -> insproName</li>
     * <li>ins_std_name -> insStdName</li>
     * <li>inspro_org_id -> insproOrgId</li>
     * <li>inspro_org_name -> insproOrgName</li>
     * <li>tr_running -> trRunning</li>
     * <li>tr_gen_way -> trGenWay</li>
     * <li>tr_candb -> trCandb</li>
     * <li>tr_highway -> trHighway</li>
     * <li>tr_mountain -> trMountain</li>
     * <li>tr_ring -> trRing</li>
     * <li>tr_cross -> trCross</li>
     * <li>tr_totle -> trTotle</li>
     * <li>inspro_special_require -> insproSpecialRequire</li>
     * <li>sample_id -> sampleId</li>
     * <li>sample_type -> sampleType</li>
     * <li>sample_name -> sampleName</li>
     * <li>remark -> remark</li>
     * <li>vin_code -> vinCode</li>
     * <li>vehicle_type -> vehicleType</li>
     * <li>engine_num -> engineNum</li>
     * <li>trial_task_id -> trialTaskId</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "del_flag": return "delFlag";
            case "create_time": return "createTime";
            case "create_by": return "createBy";
            case "update_time": return "updateTime";
            case "update_by": return "updateBy";
            case "inspro_id": return "insproId";
            case "inspro_name": return "insproName";
            case "ins_std_name": return "insStdName";
            case "inspro_org_id": return "insproOrgId";
            case "inspro_org_name": return "insproOrgName";
            case "tr_running": return "trRunning";
            case "tr_gen_way": return "trGenWay";
            case "tr_candb": return "trCandb";
            case "tr_highway": return "trHighway";
            case "tr_mountain": return "trMountain";
            case "tr_ring": return "trRing";
            case "tr_cross": return "trCross";
            case "tr_totle": return "trTotle";
            case "inspro_special_require": return "insproSpecialRequire";
            case "sample_id": return "sampleId";
            case "sample_type": return "sampleType";
/*            case "sample_name": return "sampleName";*/
            case "remark": return "remark";
            case "vin_code": return "vinCode";
            case "vehicle_type": return "vehicleType";
            case "engine_num": return "engineNum";
            case "trial_task_id": return "trialTaskId";
            case "attach_id": return "attachId";
            case "attach_name": return "attachName";
            case "bench_org_id": return "benchOrgId";
            case "bench_org_name": return "benchOrgName";
            default: return null;
        }
    }
    
    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /**  **/
    public String getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**  **/
    public String getCreateTime() {
        return this.createTime;
    }

    /**  **/
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**  **/
    public String getCreateBy() {
        return this.createBy;
    }

    /**  **/
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**  **/
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**  **/
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**  **/
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**  **/
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**  **/
    public String getInsproId() {
        return this.insproId;
    }

    /**  **/
    public void setInsproId(String insproId) {
        this.insproId = insproId;
    }

    /**  **/
    public String getInsproName() {
        return this.insproName;
    }

    /**  **/
    public void setInsproName(String insproName) {
        this.insproName = insproName;
    }

    /**  **/
    public String getInsStdName() {
        return this.insStdName;
    }

    /**  **/
    public void setInsStdName(String insStdName) {
        this.insStdName = insStdName;
    }

    /**  **/
    public String getInsproOrgId() {
        return this.insproOrgId;
    }

    /**  **/
    public void setInsproOrgId(String insproOrgId) {
        this.insproOrgId = insproOrgId;
    }

    /**  **/
    public String getInsproOrgName() {
        return this.insproOrgName;
    }

    /**  **/
    public void setInsproOrgName(String insproOrgName) {
        this.insproOrgName = insproOrgName;
    }

    /**  **/
    public String getTrRunning() {
        return this.trRunning;
    }

    /**  **/
    public void setTrRunning(String trRunning) {
        this.trRunning = trRunning;
    }

    /**  **/
    public String getTrGenWay() {
        return this.trGenWay;
    }

    /**  **/
    public void setTrGenWay(String trGenWay) {
        this.trGenWay = trGenWay;
    }

    /**  **/
    public String getTrCandb() {
        return this.trCandb;
    }

    /**  **/
    public void setTrCandb(String trCandb) {
        this.trCandb = trCandb;
    }

    /**  **/
    public String getTrHighway() {
        return this.trHighway;
    }

    /**  **/
    public void setTrHighway(String trHighway) {
        this.trHighway = trHighway;
    }

    /**  **/
    public String getTrMountain() {
        return this.trMountain;
    }

    /**  **/
    public void setTrMountain(String trMountain) {
        this.trMountain = trMountain;
    }

    /**  **/
    public String getTrRing() {
        return this.trRing;
    }

    /**  **/
    public void setTrRing(String trRing) {
        this.trRing = trRing;
    }

    /**  **/
    public String getTrCross() {
        return this.trCross;
    }

    /**  **/
    public void setTrCross(String trCross) {
        this.trCross = trCross;
    }

    /**  **/
    public String getTrTotle() {
        return this.trTotle;
    }

    /**  **/
    public void setTrTotle(String trTotle) {
        this.trTotle = trTotle;
    }

    /**  **/
    public String getInsproSpecialRequire() {
        return this.insproSpecialRequire;
    }

    /**  **/
    public void setInsproSpecialRequire(String insproSpecialRequire) {
        this.insproSpecialRequire = insproSpecialRequire;
    }


/*    public String getSampleId() {
        return this.sampleId;
    }


    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }*/

    /**  **/
    public String getSampleType() {
        return this.sampleType;
    }

    /**  **/
    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }


 /*   public String getSampleName() {
        return this.sampleName;
    }


    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }*/

    /**  **/
    public String getRemark() {
        return this.remark;
    }

    /**  **/
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**  **/
    public String getVinCode() {
        return this.vinCode;
    }

    /**  **/
    public void setVinCode(String vinCode) {
        this.vinCode = vinCode;
    }

    /**  **/
    public String getVehicleType() {
        return this.vehicleType;
    }

    /**  **/
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

/*    public String getEngineNum() {
        return this.engineNum;
    }


    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum;
    }*/


    /**  **/
    public String getTrialTaskId() {
        return this.trialTaskId;
    }

    /**  **/
    public void setTrialTaskId(String trialTaskId) {
        this.trialTaskId = trialTaskId;
    }

    public String getTrialNum() {
        return trialNum;
    }

    public void setTrialNum(String trialNum) {
        this.trialNum = trialNum;
    }

/*
    public String getChassisNum() {
        return chassisNum;
    }

    public void setChassisNum(String chassisNum) {
        this.chassisNum = chassisNum;
    }
*/

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }


    public String getPartEngineNo() {
        return partEngineNo;
    }

    public void setPartEngineNo(String partEngineNo) {
        this.partEngineNo = partEngineNo;
    }


    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }
}
