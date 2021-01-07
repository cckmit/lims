package com.adc.da.pc_execute.entity;

import java.util.List;

import com.adc.da.base.entity.BaseEntity;

public class PCQCDDInfoEO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *删除标记  0未删除  1删除
     */
    private String delFlag;

    /**
     *创建人
     */
    private String createBy;

    /**
     *创建时间
     */
    private String createTime;

    /**
     *更新者
     */
    private String updateBy;

    /**
     *更新时间
     */
    private String updateTime;

    /**
     *备注
     */
    private String remark;

    /**
     *QCDD编号 
     *QCDD-0001-年份
     */
    private String qcddNo;

    /**
     *试验申请id
     */
    private String trialTaskId;

    /**
     *试验项目id
     */
    private String projectId;

    /**
     *试验项目名称
     */
    private String projectName;

    /**
     *负责人id
     */
    private String projectLeaderId;

    /**
     *负责人名称
     */
    private String projectLeaderName;

    /**
     *业务部门id
     */
    private String projectOrgId;

    /**
     *业务部门名称
     */
    private String projectOrgName;

    /**
     *结论
     */
    private String conclusion;

    /**
     *附件id
     */
    private String attachId;

    /**
     *附件名称
     */
    private String attachName;
    
    /**
     * 流程状态
     * 0-草稿
     * 1-审批中
     * 2-已审批
     * 3-退回
     * 4-撤回
     */
    private String qcddStatus;
    
    /**
     * 供应商信息
     */
    private List<PCQCDDSupEO> supList;
    
    /**
     * baseBusId
     */
    private String baseBusId;
    
	/**
	 * 试验任务唯一编号
	 */
	private String taskNumber;

    /**
     *最后供应商id
     */
    private String supIdEnd;

    /**
     *最后供应商名称
     */
    private String supNameEnd;
    /**
     *最后成本
     */
    private String costEnd;
    
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

	public String getQcddStatus() {
		return qcddStatus;
	}

	public void setQcddStatus(String qcddStatus) {
		this.qcddStatus = qcddStatus;
	}

	public String getBaseBusId() {
		return baseBusId;
	}

	public void setBaseBusId(String baseBusId) {
		this.baseBusId = baseBusId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getQcddNo() {
		return qcddNo;
	}

	public void setQcddNo(String qcddNo) {
		this.qcddNo = qcddNo;
	}

	public String getTrialTaskId() {
		return trialTaskId;
	}

	public void setTrialTaskId(String trialTaskId) {
		this.trialTaskId = trialTaskId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectLeaderId() {
		return projectLeaderId;
	}

	public void setProjectLeaderId(String projectLeaderId) {
		this.projectLeaderId = projectLeaderId;
	}

	public String getProjectLeaderName() {
		return projectLeaderName;
	}

	public void setProjectLeaderName(String projectLeaderName) {
		this.projectLeaderName = projectLeaderName;
	}

	public String getProjectOrgId() {
		return projectOrgId;
	}

	public void setProjectOrgId(String projectOrgId) {
		this.projectOrgId = projectOrgId;
	}

	public String getProjectOrgName() {
		return projectOrgName;
	}

	public void setProjectOrgName(String projectOrgName) {
		this.projectOrgName = projectOrgName;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

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

	public List<PCQCDDSupEO> getSupList() {
		return supList;
	}

	public void setSupList(List<PCQCDDSupEO> supList) {
		this.supList = supList;
	}

    public String getSupIdEnd() {
        return supIdEnd;
    }

    public void setSupIdEnd(String supIdEnd) {
        this.supIdEnd = supIdEnd;
    }

    public String getSupNameEnd() {
        return supNameEnd;
    }

    public void setSupNameEnd(String supNameEnd) {
        this.supNameEnd = supNameEnd;
    }

    public String getCostEnd() {
        return costEnd;
    }

    public void setCostEnd(String costEnd) {
        this.costEnd = costEnd;
    }
}