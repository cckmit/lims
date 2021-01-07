package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;

public class TsAttachmentEO extends BaseEntity {
	/**
	 * 主键
	 */
    private String id;
    /**
     * 原附件名称
     */
    private String attachmentName;
    /**
     * 附件大小(KB)
     */
    private String attachmentSize;
    /**
     * 附件类型(后缀)
     */
    private String attachmentType;
    /**
     * 保存路径
     */
    private String savePath;
    /**
     * 删除标记 1删除 0存在
     */
    private Integer delFlag;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private String createTime;
    
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private String updateTime;
    
    /**
     * 水印保存路径
     */
    private String waterMarkPath;
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    
    public String getAttachmentSize() {
        return attachmentSize;
    }

    public void setAttachmentSize(String attachmentSize) {
        this.attachmentSize = attachmentSize;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
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

	public String getWaterMarkPath() {
		return waterMarkPath;
	}

	public void setWaterMarkPath(String waterMarkPath) {
		this.waterMarkPath = waterMarkPath;
	}
    
}