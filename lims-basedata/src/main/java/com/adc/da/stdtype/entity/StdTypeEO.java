package com.adc.da.stdtype.entity;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.sys.entity.UserEO;

public class StdTypeEO extends BaseEntity{
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
	 * 标准类型名称
	 */
	private String stdTypeName;
	/**
	 * 标准父级节点id
	 */
	private String stdTypeParentId;
	
	// -----getter  setter ----//
	
	public String getStdTypeName() {
		return stdTypeName;
	}
	public void setStdTypeName(String stdTypeName) {
		this.stdTypeName = stdTypeName;
	}
	public String getStdTypeParentId() {
		return stdTypeParentId;
	}
	public void setStdTypeParentId(String stdTypeParentId) {
		this.stdTypeParentId = stdTypeParentId;
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
