package com.adc.da.pc_execute.entity;

import com.adc.da.base.entity.BaseEntity;

public class PCMaterialsOutInfoEO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *删除状态 1删除 0存在
     */
    private String delFlag;

    /**
     *物资出门申请单id
     */
    private String materialsOutId;

    /**
     *零部件样品id
     */
    private String sampleId;

    /**
     *物资型号
     */
    private String materialsType;

    /**
     *物资名称
     */
    private String materialsName;

    /**
     *数量
     */
    private String numbers;

    /**
     *备注
     */
    private String remark;

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

	public String getMaterialsOutId() {
		return materialsOutId;
	}

	public void setMaterialsOutId(String materialsOutId) {
		this.materialsOutId = materialsOutId;
	}

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public String getMaterialsType() {
		return materialsType;
	}

	public void setMaterialsType(String materialsType) {
		this.materialsType = materialsType;
	}

	public String getMaterialsName() {
		return materialsName;
	}

	public void setMaterialsName(String materialsName) {
		this.materialsName = materialsName;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    
 
}