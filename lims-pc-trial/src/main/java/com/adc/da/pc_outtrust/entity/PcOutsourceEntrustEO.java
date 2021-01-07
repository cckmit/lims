package com.adc.da.pc_outtrust.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>PC_OUTSOURCE_ENTRUST PcOutsourceEntrustEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-23 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcOutsourceEntrustEO extends BaseEntity {

    @ApiModelProperty("id")
    private String id;
    private String delFlag = "0";
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("创建人id")
    private String createBy;
    @ApiModelProperty("创建人姓名")
    private String createByName;
    @ApiModelProperty("变更时间")
    private String updateTime;
    private String updateBy;
    @ApiModelProperty("委托单编号")
    private String entrustCode;
    @ApiModelProperty("委托项目")
    private String entrustProject;
    @ApiModelProperty("任务编号")
    private String taskCode;
    @ApiModelProperty("立项审批表编号")
    private String opCode;
    @ApiModelProperty("委托检验类型")
    private String entrustType;
    @ApiModelProperty("样品类型")
    private String sampleType;
    @ApiModelProperty("供应商名称")
    private String supName;
    @ApiModelProperty("任务来源编号")
    private String trialTaskCode;
    @ApiModelProperty("计划报告完成时间")
    private String planReportFinishDate;
    @ApiModelProperty("状态")
    private String entrustStatus;
    @ApiModelProperty("费用预算")
    private String planCost;
    @ApiModelProperty("委托单位名称")
    private String entrustCompanyName = "东风柳州汽车有限公司";
    @ApiModelProperty("邮编")
    private String zipCode;
    @ApiModelProperty("委托单位地址")
    private String entrustCompanyAddr = "广西柳州市屏山大道286号";
    @ApiModelProperty("电话")
    private String telPhone;
    @ApiModelProperty("产品名称")
    private String productName;
    @ApiModelProperty("商标")
    private String logo;
    @ApiModelProperty("委托检验依据及要求")
    private String accRequire;
    @ApiModelProperty("委托检验要求完成时间")
    private String requireFinishDate;
    @ApiModelProperty("委托人手机号")
    private String createByPhone;
    @ApiModelProperty("供应商地址")
    private String supAddr;
    @ApiModelProperty("联系人")
    private String supManagerName;
    @ApiModelProperty("联系人电话")
    private String supManagerPhone;
    @ApiModelProperty("委托主键")
    private String trialTaskId;
    @ApiModelProperty("判断流程类型")
    private String pvOrcv;  // 1 :pv   0 :cv

    @ApiModelProperty("附件ID")
    private String fileId;

    
    @ApiModelProperty("供应商Id")
    private String supId;
    
    
    
    
    public String getSupId() {
		return supId;
	}

	public void setSupId(String supId) {
		this.supId = supId;
	}

	public String getPvOrcv() {
        return pvOrcv;
    }

    public void setPvOrcv(String pvOrcv) {
        this.pvOrcv = pvOrcv;
    }

    @ApiModelProperty("委托code")//便于试验申请变更之后的查询
    private String trialCode;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>delFlag -> del_flag</li>
     * <li>createTime -> create_time</li>
     * <li>createBy -> create_by</li>
     * <li>createByName -> create_by_name</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>enttrustCode -> enttrust_code</li>
     * <li>entrustProject -> entrust_project</li>
     * <li>taskCode -> task_code</li>
     * <li>opCode -> op_code</li>
     * <li>entrustType -> entrust_type</li>
     * <li>sampleType -> sample_type</li>
     * <li>supName -> sup_name</li>
     * <li>trialTaskCode -> trial_task_code</li>
     * <li>planReportFinishDate -> plan_report_finish_date</li>
     * <li>entrustStatus -> entrust_status</li>
     * <li>planCost -> plan_cost</li>
     * <li>entrustCompanyName -> entrust_company_name</li>
     * <li>zipCode -> zip_code</li>
     * <li>entrustCompanyAddr -> entrust_company_addr</li>
     * <li>telPhone -> tel_phone</li>
     * <li>productName -> product_name</li>
     * <li>logo -> logo</li>
     * <li>accRequire -> acc_require</li>
     * <li>requireFinishDate -> require_finish_date</li>
     * <li>createByPhone -> create_by_phone</li>
     * <li>supAddr -> sup_addr</li>
     * <li>supManagerName -> sup_manager_name</li>
     * <li>supManagerPhone -> sup_manager_phone</li>
     * <li>trialTaskId -> trial_task_id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "delFlag": return "del_flag";
            case "createTime": return "create_time";
            case "createBy": return "create_by";
            case "createByName": return "create_by_name";
            case "updateTime": return "update_time";
            case "updateBy": return "update_by";
            case "enttrustCode": return "enttrust_code";
            case "entrustProject": return "entrust_project";
            case "taskCode": return "task_code";
            case "opCode": return "op_code";
            case "entrustType": return "entrust_type";
            case "sampleType": return "sample_type";
            case "supName": return "sup_name";
            case "trialTaskCode": return "trial_task_code";
            case "planReportFinishDate": return "plan_report_finish_date";
            case "entrustStatus": return "entrust_status";
            case "planCost": return "plan_cost";
            case "entrustCompanyName": return "entrust_company_name";
            case "zipCode": return "zip_code";
            case "entrustCompanyAddr": return "entrust_company_addr";
            case "telPhone": return "tel_phone";
            case "productName": return "product_name";
            case "logo": return "logo";
            case "accRequire": return "acc_require";
            case "requireFinishDate": return "require_finish_date";
            case "createByPhone": return "create_by_phone";
            case "supAddr": return "sup_addr";
            case "supManagerName": return "sup_manager_name";
            case "supManagerPhone": return "sup_manager_phone";
            case "trialTaskId": return "trial_task_id";
            case "trialCode": return "trial_code";
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
     * <li>create_by_name -> createByName</li>
     * <li>update_time -> updateTime</li>
     * <li>update_by -> updateBy</li>
     * <li>enttrust_code -> enttrustCode</li>
     * <li>entrust_project -> entrustProject</li>
     * <li>task_code -> taskCode</li>
     * <li>op_code -> opCode</li>
     * <li>entrust_type -> entrustType</li>
     * <li>sample_type -> sampleType</li>
     * <li>sup_name -> supName</li>
     * <li>trial_task_code -> trialTaskCode</li>
     * <li>plan_report_finish_date -> planReportFinishDate</li>
     * <li>entrust_status -> entrustStatus</li>
     * <li>plan_cost -> planCost</li>
     * <li>entrust_company_name -> entrustCompanyName</li>
     * <li>zip_code -> zipCode</li>
     * <li>entrust_company_addr -> entrustCompanyAddr</li>
     * <li>tel_phone -> telPhone</li>
     * <li>product_name -> productName</li>
     * <li>logo -> logo</li>
     * <li>acc_require -> accRequire</li>
     * <li>require_finish_date -> requireFinishDate</li>
     * <li>create_by_phone -> createByPhone</li>
     * <li>sup_addr -> supAddr</li>
     * <li>sup_manager_name -> supManagerName</li>
     * <li>sup_manager_phone -> supManagerPhone</li>
     * <li>trial_task_id -> trialTaskId</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "del_flag": return "delFlag";
            case "create_time": return "createTime";
            case "create_by": return "createBy";
            case "create_by_name": return "createByName";
            case "update_time": return "updateTime";
            case "update_by": return "updateBy";
            case "enttrust_code": return "enttrustCode";
            case "entrust_project": return "entrustProject";
            case "task_code": return "taskCode";
            case "op_code": return "opCode";
            case "entrust_type": return "entrustType";
            case "sample_type": return "sampleType";
            case "sup_name": return "supName";
            case "trial_task_code": return "trialTaskCode";
            case "plan_report_finish_date": return "planReportFinishDate";
            case "entrust_status": return "entrustStatus";
            case "plan_cost": return "planCost";
            case "entrust_company_name": return "entrustCompanyName";
            case "zip_code": return "zipCode";
            case "entrust_company_addr": return "entrustCompanyAddr";
            case "tel_phone": return "telPhone";
            case "product_name": return "productName";
            case "logo": return "logo";
            case "acc_require": return "accRequire";
            case "require_finish_date": return "requireFinishDate";
            case "create_by_phone": return "createByPhone";
            case "sup_addr": return "supAddr";
            case "sup_manager_name": return "supManagerName";
            case "sup_manager_phone": return "supManagerPhone";
            case "trial_task_id": return "trialTaskId";
            case "trial_code": return "trialCode";
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
    public String getCreateByName() {
        return this.createByName;
    }

    /**  **/
    public void setCreateByName(String createByName) {
        this.createByName = createByName;
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

    public String getEntrustCode() {
        return entrustCode;
    }

    public void setEntrustCode(String entrustCode) {
        this.entrustCode = entrustCode;
    }

    /**  **/
    public String getEntrustProject() {
        return this.entrustProject;
    }

    /**  **/
    public void setEntrustProject(String entrustProject) {
        this.entrustProject = entrustProject;
    }

    /**  **/
    public String getTaskCode() {
        return this.taskCode;
    }

    /**  **/
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    /**  **/
    public String getOpCode() {
        return this.opCode;
    }

    /**  **/
    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    /**  **/
    public String getEntrustType() {
        return this.entrustType;
    }

    /**  **/
    public void setEntrustType(String entrustType) {
        this.entrustType = entrustType;
    }

    /**  **/
    public String getSampleType() {
        return this.sampleType;
    }

    /**  **/
    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    /**  **/
    public String getSupName() {
        return this.supName;
    }

    /**  **/
    public void setSupName(String supName) {
        this.supName = supName;
    }

    /**  **/
    public String getTrialTaskCode() {
        return this.trialTaskCode;
    }

    /**  **/
    public void setTrialTaskCode(String trialTaskCode) {
        this.trialTaskCode = trialTaskCode;
    }

    /**  **/
    public String getPlanReportFinishDate() {
        return this.planReportFinishDate;
    }

    /**  **/
    public void setPlanReportFinishDate(String planReportFinishDate) {
        this.planReportFinishDate = planReportFinishDate;
    }

    /**  **/
    public String getEntrustStatus() {
        return this.entrustStatus;
    }

    /**  **/
    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    /**  **/
    public String getPlanCost() {
        return this.planCost;
    }

    /**  **/
    public void setPlanCost(String planCost) {
        this.planCost = planCost;
    }

    /**  **/
    public String getEntrustCompanyName() {
        return this.entrustCompanyName;
    }

    /**  **/
    public void setEntrustCompanyName(String entrustCompanyName) {
        this.entrustCompanyName = entrustCompanyName;
    }

    /**  **/
    public String getZipCode() {
        return this.zipCode;
    }

    /**  **/
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**  **/
    public String getEntrustCompanyAddr() {
        return this.entrustCompanyAddr;
    }

    /**  **/
    public void setEntrustCompanyAddr(String entrustCompanyAddr) {
        this.entrustCompanyAddr = entrustCompanyAddr;
    }

    /**  **/
    public String getTelPhone() {
        return this.telPhone;
    }

    /**  **/
    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    /**  **/
    public String getProductName() {
        return this.productName;
    }

    /**  **/
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**  **/
    public String getLogo() {
        return this.logo;
    }

    /**  **/
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**  **/
    public String getAccRequire() {
        return this.accRequire;
    }

    /**  **/
    public void setAccRequire(String accRequire) {
        this.accRequire = accRequire;
    }

    /**  **/
    public String getRequireFinishDate() {
        return this.requireFinishDate;
    }

    /**  **/
    public void setRequireFinishDate(String requireFinishDate) {
        this.requireFinishDate = requireFinishDate;
    }

    /**  **/
    public String getCreateByPhone() {
        return this.createByPhone;
    }

    /**  **/
    public void setCreateByPhone(String createByPhone) {
        this.createByPhone = createByPhone;
    }

    /**  **/
    public String getSupAddr() {
        return this.supAddr;
    }

    /**  **/
    public void setSupAddr(String supAddr) {
        this.supAddr = supAddr;
    }

    /**  **/
    public String getSupManagerName() {
        return this.supManagerName;
    }

    /**  **/
    public void setSupManagerName(String supManagerName) {
        this.supManagerName = supManagerName;
    }

    /**  **/
    public String getSupManagerPhone() {
        return this.supManagerPhone;
    }

    /**  **/
    public void setSupManagerPhone(String supManagerPhone) {
        this.supManagerPhone = supManagerPhone;
    }

    /**  **/
    public String getTrialTaskId() {
        return this.trialTaskId;
    }

    /**  **/
    public void setTrialTaskId(String trialTaskId) {
        this.trialTaskId = trialTaskId;
    }

    public String getTrialCode() {
        return trialCode;
    }

    public void setTrialCode(String trialCode) {
        this.trialCode = trialCode;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
