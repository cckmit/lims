package com.adc.da.pc_outsource.entity;

import com.adc.da.base.entity.BaseEntity;

public class PCOutsourceSupProEO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *删除标记
     */
    private String delFlag;

    /**
     *委外立项单id
     */
    private String outsourceId;

    /**
     *供应商A id
     */
    private String supId;

    /**
     *供应商A
     */
    private String supName;

    /**
     *供应商A折后价
     */
    private String discountPrice;

    /**
     *检验项目A名称
     */
    private String testproName;

    /**
     * 标识
     */
    private String groupFlag;

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

	public String getOutsourceId() {
		return outsourceId;
	}

	public void setOutsourceId(String outsourceId) {
		this.outsourceId = outsourceId;
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

	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getTestproName() {
		return testproName;
	}

	public void setTestproName(String testproName) {
		this.testproName = testproName;
	}

	public String getGroupFlag() {
		return groupFlag;
	}

	public void setGroupFlag(String groupFlag) {
		this.groupFlag = groupFlag;
	}
    
    
    
}