package com.adc.da.instype.entity;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.sys.entity.UserEO;

public class InsTypeEO extends BaseEntity {
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
	 * 检验项目类型名称
	 */
	private String insTypeName;
	/**
	 * 检验项目类型父级id
	 */
	private String insTypeParentId;
	public String getInsTypeName() {
		return insTypeName;
	}
	public void setInsTypeName(String insTypeName) {
		this.insTypeName = insTypeName;
	}
	public String getInsTypeParentId() {
		return insTypeParentId;
	}
	public void setInsTypeParentId(String insTypeParentId) {
		this.insTypeParentId = insTypeParentId;
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
