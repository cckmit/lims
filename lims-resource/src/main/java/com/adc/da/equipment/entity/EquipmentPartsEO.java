package com.adc.da.equipment.entity;

import com.adc.da.base.entity.BaseEntity;

import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>设备配件表<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-27 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class EquipmentPartsEO extends BaseEntity {

    @ApiModelProperty(" ID ")
    private String id;
    @ApiModelProperty(" 设备id ")
    private String eqId;
    @ApiModelProperty(" 配件名称 ")
    private String eqPartsName;
    @ApiModelProperty(" 配件数量 ")
    private String eqPartsNumber;
    @ApiModelProperty(" 附件id ")
    private String eqPartsFileId;
    @ApiModelProperty(" 附件名称 ")
    private String eqPartsFileName;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getEqId() {
		return eqId;
	}

	public void setEqId(String eqId) {
		this.eqId = eqId;
	}

	public String getEqPartsName() {
		return eqPartsName;
	}

	public void setEqPartsName(String eqPartsName) {
		this.eqPartsName = eqPartsName;
	}

	public String getEqPartsNumber() {
		return eqPartsNumber;
	}

	public void setEqPartsNumber(String eqPartsNumber) {
		this.eqPartsNumber = eqPartsNumber;
	}

	public String getEqPartsFileId() {
		return eqPartsFileId;
	}

	public void setEqPartsFileId(String eqPartsFileId) {
		this.eqPartsFileId = eqPartsFileId;
	}

	public String getEqPartsFileName() {
		return eqPartsFileName;
	}

	public void setEqPartsFileName(String eqPartsFileName) {
		this.eqPartsFileName = eqPartsFileName;
	}
    
    

}
