package com.adc.da.trial_report.entity;

import com.adc.da.base.entity.BaseEntity;

/**
 * 报告附件汇总,归档信息
 * @author Administrator
 * @date 2019年8月26日
 */
public class ReportArchivefileEO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *报告id
     */
    private String reportId;

    /**
     *文件编号
     */
    private String reportFileno;

    /**
     *文件附近id
     */
    private String reportFileid;

    /**
     *文件名称
     */
    private String reportFilename;

    /**
     *文件类型
     *	试验数据 - reportData
		PDF - reportPDF
     */
    private String reportFiletype;

    /**
     *试验任务id
     */
    private String trialtaskId;

    /**
     *归档状态
     *  0-未归档(默认)
     *  1-已归档(纸质归档+电子归档)
		2-纸归档
		3-电子归档
     */
    private String archiveStatus;
    /**
     * 是否为纸质归档
     */
    private String isPaperArchive;
    /**
     * 是否为电子归档
     */
    private String isElectronArchive;
    
    /**
     *存放位置
     */
    private String storagePosotion;

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
     *删除标记   1删除 0存在
     */
    private String delFlag;

    /**
     *备注
     */
    private String remark;
    
    /**
     * 检验项目(试验任务标题)
     */
    private String trialTitle;
    
    
    
    public String getTrialTitle() {
		return trialTitle;
	}

	public void setTrialTitle(String trialTitle) {
		this.trialTitle = trialTitle;
	}

	public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportFileno() {
        return reportFileno;
    }

    public void setReportFileno(String reportFileno) {
        this.reportFileno = reportFileno;
    }

    public String getReportFileid() {
        return reportFileid;
    }

    public void setReportFileid(String reportFileid) {
        this.reportFileid = reportFileid;
    }

    public String getReportFilename() {
        return reportFilename;
    }

    public void setReportFilename(String reportFilename) {
        this.reportFilename = reportFilename;
    }

    public String getReportFiletype() {
        return reportFiletype;
    }

    public void setReportFiletype(String reportFiletype) {
        this.reportFiletype = reportFiletype;
    }

    public String getTrialtaskId() {
        return trialtaskId;
    }

    public void setTrialtaskId(String trialtaskId) {
        this.trialtaskId = trialtaskId;
    }

    public String getArchiveStatus() {
        return archiveStatus;
    }

    public void setArchiveStatus(String archiveStatus) {
        this.archiveStatus = archiveStatus;
    }

    public String getStoragePosotion() {
        return storagePosotion;
    }

    public void setStoragePosotion(String storagePosotion) {
        this.storagePosotion = storagePosotion;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getIsPaperArchive() {
		return isPaperArchive;
	}

	public void setIsPaperArchive(String isPaperArchive) {
		this.isPaperArchive = isPaperArchive;
	}

	public String getIsElectronArchive() {
		return isElectronArchive;
	}

	public void setIsElectronArchive(String isElectronArchive) {
		this.isElectronArchive = isElectronArchive;
	}
    
}