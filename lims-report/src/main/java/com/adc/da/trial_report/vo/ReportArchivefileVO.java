package com.adc.da.trial_report.vo;

public class ReportArchivefileVO {
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
     *文件附件id
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
		1-纸归档
		2-电子归档
		3-已归档(纸质归档+电子归档)
     */
    private String archiveStatus;

    /**
     *存放位置
     */
    private String storagePosotion;
    
    /**
     * 是否为纸质归档
     */
    private String isPaperArchive;
    /**
     * 是否为电子归档
     */
    private String isElectronArchive;
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
