package com.adc.da.pc_budget_use.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * <b>功能：</b>PC_BUDGET_USE PcBudgetUseEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-06 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcBudgetUseEO extends BaseEntity {

    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("删除标识（0，未删除；1，已删除）")
    private Integer delFlag;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("创建人ID")
    private String createBy;
    @ApiModelProperty("更新时间")
    private String updateTime;
    @ApiModelProperty("更新人ID")
    private String updateBy;
    @ApiModelProperty("试验ID")
    private String trialId;
    @ApiModelProperty("试验任务书编号")
    private String trialCode;
    @ApiModelProperty("实验任务项目名称")
    private String trialProjectName;
    @ApiModelProperty("费用使用申请编号")
    private String code;
    @ApiModelProperty("报销费用总计")
    private String budgetTotal;
    @ApiModelProperty("试验开始时间")
    private String trialStartTime;
    @ApiModelProperty("试验结束时间")
    private String trialEndTime;
    @ApiModelProperty("试验项目地点")
    private String trialLocation;
    @ApiModelProperty("当前代办人id")
    private String currentWaitUserId;
    @ApiModelProperty("当前代办人姓名")
    private String currentWaitUserName;
    @ApiModelProperty("状态（0,草稿；1，待审批；2已审批；3，撤回中；4，已撤回；5，已完成；6，退回）")
    private String status;
    @ApiModelProperty("0，CV；1，PV")
    private String pvOrCv;
    @ApiModelProperty("businessKey")
    private String businessKey;
    @ApiModelProperty("附件ID")
    private String attachmentId;
    @ApiModelProperty("附件名称")
    private String attachmentName;
    @ApiModelProperty("费用使用申请子表")
    private List<PcBudgetUseInfoEO> pcBudgetUseInfoEOList;
    @ApiModelProperty("试验任务编号")
    private String taskNumber;
	/**
	 * 区分试验任务还是执行计划
	 * 0-试验任务；1-执行计划
	 */
    @ApiModelProperty("区分试验任务还是执行计划: 0-试验任务；1-执行计划")
	private String taskOrPlan;

    @ApiModelProperty("费用总计")
    private String total;// 根据需求增加该字段
    @ApiModelProperty("供应商名称")
    private String supName;
    @ApiModelProperty("供应商名称")
    private String supId;
    @ApiModelProperty("项目名称")
    private String buProjectName;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>delFlag -> del_flag</li>
     * <li>createTime -> create_time</li>
     * <li>createBy -> create_by</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>trialId -> trial_id</li>
     * <li>trialCode -> trial_code</li>
     * <li>trialProjectName -> trial_project_name</li>
     * <li>buCode -> bu_code</li>
     * <li>budgetTotal -> budget_total</li>
     * <li>currentWaitUserid -> current_wait_userid</li>
     * <li>currentWaitUsername -> current_wait_username</li>
     * <li>status -> status</li>
     * <li>pcCv -> pc_cv</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id":
                return "id";
            case "delFlag":
                return "del_flag";
            case "createTime":
                return "create_time";
            case "createBy":
                return "create_by";
            case "updateTime":
                return "update_time";
            case "updateBy":
                return "update_by";
            case "trialId":
                return "trial_id";
            case "trialCode":
                return "trial_code";
            case "trialProjectName":
                return "trial_project_name";
            case "buCode":
                return "bu_code";
            case "budgetTotal":
                return "budget_total";
            case "currentWaitUserid":
                return "current_wait_userid";
            case "currentWaitUsername":
                return "current_wait_username";
            case "status":
                return "status";
            case "pcCv":
                return "pc_cv";
            case "attachmentId":
                return "attachment_id";
            case "attachmentName":
                return "attachment_name";
            default:
                return null;
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
     * <li>trial_id -> trialId</li>
     * <li>trial_code -> trialCode</li>
     * <li>trial_project_name -> trialProjectName</li>
     * <li>bu_code -> buCode</li>
     * <li>budget_total -> budgetTotal</li>
     * <li>current_wait_userid -> currentWaitUserid</li>
     * <li>current_wait_username -> currentWaitUsername</li>
     * <li>status -> status</li>
     * <li>pc_cv -> pcCv</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id":
                return "id";
            case "del_flag":
                return "delFlag";
            case "create_time":
                return "createTime";
            case "create_by":
                return "createBy";
            case "update_time":
                return "updateTime";
            case "update_by":
                return "updateBy";
            case "trial_id":
                return "trialId";
            case "trial_code":
                return "trialCode";
            case "trial_project_name":
                return "trialProjectName";
            case "bu_code":
                return "buCode";
            case "budget_total":
                return "budgetTotal";
            case "current_wait_userid":
                return "currentWaitUserid";
            case "current_wait_username":
                return "currentWaitUsername";
            case "status":
                return "status";
            case "pc_cv":
                return "pcCv";
            case "attachment_id":
                return "attachmentId";
            case "attachment_name":
                return "attachmentName";
            default:
                return null;
        }
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getBuProjectName() {
        return buProjectName;
    }

    public void setBuProjectName(String buProjectName) {
        this.buProjectName = buProjectName;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTaskOrPlan() {
		return taskOrPlan;
	}

	public void setTaskOrPlan(String taskOrPlan) {
		this.taskOrPlan = taskOrPlan;
	}

	/**
     *
     **/
    public String getId() {
        return this.id;
    }

    /**
     *
     **/
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     **/
    public Integer getDelFlag() {
        return this.delFlag;
    }

    /**
     *
     **/
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     *
     **/
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     *
     **/
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     *
     **/
    public String getCreateBy() {
        return this.createBy;
    }

    /**
     *
     **/
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     *
     **/
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**
     *
     **/
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     **/
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**
     *
     **/
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     *
     **/
    public String getTrialId() {
        return this.trialId;
    }

    /**
     *
     **/
    public void setTrialId(String trialId) {
        this.trialId = trialId;
    }

    /**
     *
     **/
    public String getTrialCode() {
        return this.trialCode;
    }

    /**
     *
     **/
    public void setTrialCode(String trialCode) {
        this.trialCode = trialCode;
    }

    /**
     *
     **/
    public String getTrialProjectName() {
        return this.trialProjectName;
    }

    /**
     *
     **/
    public void setTrialProjectName(String trialProjectName) {
        this.trialProjectName = trialProjectName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     **/
    public String getBudgetTotal() {
        return this.budgetTotal;
    }

    /**
     *
     **/
    public void setBudgetTotal(String budgetTotal) {
        this.budgetTotal = budgetTotal;
    }

    public String getTrialStartTime() {
        return trialStartTime;
    }

    public void setTrialStartTime(String trialStartTime) {
        this.trialStartTime = trialStartTime;
    }

    public String getTrialEndTime() {
        return trialEndTime;
    }

    public void setTrialEndTime(String trialEndTime) {
        this.trialEndTime = trialEndTime;
    }

    public String getTrialLocation() {
        return trialLocation;
    }

    public void setTrialLocation(String trialLocation) {
        this.trialLocation = trialLocation;
    }

    public String getCurrentWaitUserId() {
        return currentWaitUserId;
    }

    public void setCurrentWaitUserId(String currentWaitUserId) {
        this.currentWaitUserId = currentWaitUserId;
    }

    public String getCurrentWaitUserName() {
        return currentWaitUserName;
    }

    public void setCurrentWaitUserName(String currentWaitUserName) {
        this.currentWaitUserName = currentWaitUserName;
    }

    /**
     *
     **/
    public String getStatus() {
        return this.status;
    }

    /**
     *
     **/
    public void setStatus(String status) {
        this.status = status;
    }

    public String getPvOrCv() {
        return pvOrCv;
    }

    public void setPvOrCv(String pvOrCv) {
        this.pvOrCv = pvOrCv;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public List<PcBudgetUseInfoEO> getPcBudgetUseInfoEOList() {
        return pcBudgetUseInfoEOList;
    }

    public void setPcBudgetUseInfoEOList(List<PcBudgetUseInfoEO> pcBudgetUseInfoEOList) {
        this.pcBudgetUseInfoEOList = pcBudgetUseInfoEOList;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }
}
