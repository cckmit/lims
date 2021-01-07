package com.adc.da.trial_report.entity;

import com.adc.da.base.entity.BaseEntity;

public class TrialReportEO extends BaseEntity {

	/**
	 * 主键
	 */
    private String id;
    /**
     * 报告状态 0-无报告(默认)
			1-编辑报告
			3-档案室
			4-退回
			5-撤回
            6-待校对
         	7-待审核
            8-待批准
     */
    private String reportStatus;
    /**
     * 报告模板附件id
     */
    private String reportFileid;
    /**
     * 附件id 只允许上传PDF格式
     */
    private String accessoryFileid;
    /**
     * PDF附件id
     */
    private String pdfFileid;
    /**
     * 试验报告检验项目id
     */
    private String trialtaskInsproids;
    /**
     * 报告编号
     */
    private String reportNo;
    /**
     * 试验任务id
     */
    private String trialtaskId;
    /**
     * 试验结果
     *  0-合格
     *  1-不合格
     *  2-不做判定
     */
    private String trialTaskResult;
    
    /**
     * 归档状态
     *  0-未归档(默认)
     *  1-已归档
     */
    private String archiveStatus;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 删除标记  1删除 0存在
     */
    private String delFlag;
    /**
     * 备注
     */
    private String remark;
    
    //-----------冗余字段:VIN码,检验项目名称  用于通用查询---------------//
    /**
     * VIN码
     */
    private String VINCode;
    /**
     * 检验项目名称 多个用英文逗号隔开
     */
    private String insProNames;
    
    //-----------关联查询:用户名,组织机构名,试验委托编号--------------//
    /**
     * 用户姓名(试验工程师)
     */
    private String engineerUserName;
    /**
     * 组织机构名
     */
    private String orgName;
    
    /**
     * 报告类型
     * PV
     * CV
     * EV
     */
    private String reportType;
    
    /**
     * 委托编码
     */
    private String taskCode;
    
    /**
     * 委托人员
     */
    private String originator;
    
    
    
    public String getReportType() {
		return reportType;
	}


	public void setReportType(String reportType) {
		this.reportType = reportType;
	}


    
    public String getRemark() {
        return this.remark;
    }

    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    
    public String getId() {
        return this.id;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    
    public String getReportStatus() {
        return this.reportStatus;
    }

    
    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    
    public String getReportFileid() {
        return this.reportFileid;
    }

    
    public void setReportFileid(String reportFileid) {
        this.reportFileid = reportFileid;
    }

    
    public String getAccessoryFileid() {
        return this.accessoryFileid;
    }

    
    public void setAccessoryFileid(String accessoryFileid) {
        this.accessoryFileid = accessoryFileid;
    }

    
    public String getPdfFileid() {
        return this.pdfFileid;
    }

    
    public void setPdfFileid(String pdfFileid) {
        this.pdfFileid = pdfFileid;
    }

    
    public String getTrialtaskInsproids() {
        return this.trialtaskInsproids;
    }

    
    public void setTrialtaskInsproids(String trialtaskInsproids) {
        this.trialtaskInsproids = trialtaskInsproids;
    }

    
    public String getReportNo() {
        return this.reportNo;
    }

    
    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    
    public String getTrialtaskId() {
        return this.trialtaskId;
    }

    
    public void setTrialtaskId(String trialtaskId) {
        this.trialtaskId = trialtaskId;
    }

    
    public String getCreateBy() {
        return this.createBy;
    }

    
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    
    public String getCreateTime() {
        return this.createTime;
    }

    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    
    public String getUpdateBy() {
        return this.updateBy;
    }

    
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    
    public String getUpdateTime() {
        return this.updateTime;
    }

    
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    
    public String getDelFlag() {
        return this.delFlag;
    }

    
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }


	public String getTrialTaskResult() {
		return trialTaskResult;
	}


	public void setTrialTaskResult(String trialTaskResult) {
		this.trialTaskResult = trialTaskResult;
	}


	public String getArchiveStatus() {
		return archiveStatus;
	}


	public void setArchiveStatus(String archiveStatus) {
		this.archiveStatus = archiveStatus;
	}


	public String getVINCode() {
		return VINCode;
	}


	public void setVINCode(String vINCode) {
		VINCode = vINCode;
	}


	public String getInsProNames() {
		return insProNames;
	}


	public void setInsProNames(String insProNames) {
		this.insProNames = insProNames;
	}


	public String getEngineerUserName() {
		return engineerUserName;
	}


	public void setEngineerUserName(String engineerUserName) {
		this.engineerUserName = engineerUserName;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getTaskCode() {
		return taskCode;
	}


	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}


	public String getOriginator() {
		return originator;
	}


	public void setOriginator(String originator) {
		this.originator = originator;
	}

}
