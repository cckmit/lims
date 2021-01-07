package com.adc.da.trial_execute.page;

import com.adc.da.base.page.BasePage;

public class TrialSpotCheckEOPage extends BasePage {
    /**
     *主键
     */
    private String id;

    /**
     *附件id
     */
    private String fileId;

    /**
     *点检类型  1-台架搭建点检
     *		2-试验启动前点检
     *		3-日常点检
     */
    private String type;

    /**
     *试验任务id
     */
    private String trialtaskId;

    /**
     *创建者
     */
    private String createBy;

    /**
     *创建时间
     */
    private String createTime;
    private String createDate1;
    private String createDate2;

    /**
     * 附件名称
     */
    private String attachmentName;
    /**
     * 创建人
     */
    private String createName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTrialtaskId() {
		return trialtaskId;
	}
	public void setTrialtaskId(String trialtaskId) {
		this.trialtaskId = trialtaskId;
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
	public String getCreateDate1() {
		return createDate1;
	}
	public void setCreateDate1(String createDate1) {
		this.createDate1 = createDate1;
	}
	public String getCreateDate2() {
		return createDate2;
	}
	public void setCreateDate2(String createDate2) {
		this.createDate2 = createDate2;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
    
}
