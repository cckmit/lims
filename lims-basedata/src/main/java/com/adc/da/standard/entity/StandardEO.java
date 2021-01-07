package com.adc.da.standard.entity;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.sys.entity.UserEO;

public class StandardEO extends BaseEntity {
	/**
	 * 主键
	 */
	private String id;
    /**
     * 删除标记
     */
    private Integer delFlag;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 创建人
     */
    private UserEO createBy;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 更新人
     */
    private UserEO updateBy;
	/**
	 * 标准名称
	 */
	private String stdName;
	/**
	 * 标准编号
	 */
	private String stdNo;
	/**
	 * 标准类型ID
	 */
    private String stdTypeId;
    private String stdTypeName;
    /**
     * 标准解读
     */
    private String stdRead;
    /**
     * 标准状态
     */
    private String stdStatus;
    /**
     * 实施时间
     */
    private String stdImplementDate;
    /**
     * 附件id
     */
    private String stdFileId;
    /**
     * 附件名称
     */
    private String stdFileName;
    
    /**
     * 用于对应vo实体字段
     */
    private String stdId;
    

	public String getStdId() {
		return stdId;
	}
	public void setStdId(String stdId) {
		this.stdId = stdId;
	}
	public String getStdFileName() {
		return stdFileName;
	}
	public void setStdFileName(String stdFileName) {
		this.stdFileName = stdFileName;
	}
    
	public String getStdName() {
		return stdName;
	}
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}
	public String getStdTypeId() {
		return stdTypeId;
	}
	public void setStdTypeId(String stdTypeId) {
		this.stdTypeId = stdTypeId;
	}
	public String getStdTypeName() {
		return stdTypeName;
	}
	public void setStdTypeName(String stdTypeName) {
		this.stdTypeName = stdTypeName;
	}
	public String getStdRead() {
		return stdRead;
	}
	public void setStdRead(String stdRead) {
		this.stdRead = stdRead;
	}
	public String getStdStatus() {
		return stdStatus;
	}
	public void setStdStatus(String stdStatus) {
		this.stdStatus = stdStatus;
	}
	public String getStdImplementDate() {
		return stdImplementDate;
	}
	public void setStdImplementDate(String stdImplementDate) {
		this.stdImplementDate = stdImplementDate;
	}
	public String getStdFileId() {
		return stdFileId;
	}
	public void setStdFileId(String stdFileId) {
		this.stdFileId = stdFileId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public UserEO getCreateBy() {
		return createBy;
	}
	public void setCreateBy(UserEO createBy) {
		this.createBy = createBy;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public UserEO getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(UserEO updateBy) {
		this.updateBy = updateBy;
	}
    
}
