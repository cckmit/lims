package com.adc.da.pc_execute.entity;

import com.adc.da.base.entity.BaseEntity;

public class PCQCDDSupEO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *删除标记  0未删除  1删除
     */
    private String delFlag;

    /**
     *QCDD 主键
     */
    private String qcddId;

    /**
     *供应商id
     */
    private String supId;

    /**
     *供应商名称
     */
    private String supName;

    /**
     *质量
     */
    private String quality;

    /**
     *成本
     */
    private String cost;

    /**
     *交付
     */
    private String deliver;

    /**
     *研发
     */
    private String development;

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

	public String getQcddId() {
		return qcddId;
	}

	public void setQcddId(String qcddId) {
		this.qcddId = qcddId;
	}

	public String getSupId() {
		return supId;
	}

	public void setSupId(String supId) {
		this.supId = supId;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getDeliver() {
		return deliver;
	}

	public void setDeliver(String deliver) {
		this.deliver = deliver;
	}

	public String getDevelopment() {
		return development;
	}

	public void setDevelopment(String development) {
		this.development = development;
	}

    
}