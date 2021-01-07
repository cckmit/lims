package com.adc.da.equipment.VO;

import java.util.List;

import com.adc.da.equipment.entity.EquipmentEO;
import com.adc.da.equipment.entity.EquipmentPartsEO;

import io.swagger.annotations.ApiModelProperty;

/**
 * <b>功能：</b>设备分页查询、列表查询、详情查询结果封装<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-27 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class EquipmentVO extends EquipmentEO {

	@ApiModelProperty(" 设备状态 ")
    private String eqStatusStr;
	
	@ApiModelProperty(" 设备使用状态 ")
    private String eqUseStatusStr;
	
	@ApiModelProperty(" 类别 ")
	private String eqSortStr;
	
	@ApiModelProperty(" 重要度 ")
	private String eqImportanceStr;
	
	@ApiModelProperty(" 点检完成日期 ")
	private String pointCompleteDate;
	
	@ApiModelProperty(" 点检有效期 ")
	private String pointValidDate;
	
	@ApiModelProperty(" 核查完成日期 ")
	private String checkCompleteDate;
	
	@ApiModelProperty(" 核查有效期 ")
	private String checkValidDate;
	
	@ApiModelProperty(" 校准完成日期 ")
	private String calibrateCompleteDate;
	
	@ApiModelProperty(" 校准有效期 ")
	private String calibrateValidDate;
	
	@ApiModelProperty(" 维护保养完成日期 ")
	private String maintainCompleteDate;
	
	@ApiModelProperty(" 维护保养有效期 ")
	private String maintainValidDate;
	
	@ApiModelProperty(" 配件列表 ")
	private List<EquipmentPartsEO> partsList;
	
	@ApiModelProperty(" 子设备列表 ")
	private List<EquipmentVO> children;
	
	@ApiModelProperty(" 是否使用中 0-否  1-是 ")
	private String ifUsing;

	public String getEqStatusStr() {
		return eqStatusStr;
	}

	public void setEqStatusStr(String eqStatusStr) {
		this.eqStatusStr = eqStatusStr;
	}

	public String getEqUseStatusStr() {
		return eqUseStatusStr;
	}

	public void setEqUseStatusStr(String eqUseStatusStr) {
		this.eqUseStatusStr = eqUseStatusStr;
	}

	public String getEqSortStr() {
		return eqSortStr;
	}

	public void setEqSortStr(String eqSortStr) {
		this.eqSortStr = eqSortStr;
	}

	public String getEqImportanceStr() {
		return eqImportanceStr;
	}

	public void setEqImportanceStr(String eqImportanceStr) {
		this.eqImportanceStr = eqImportanceStr;
	}

	public String getPointCompleteDate() {
		return pointCompleteDate;
	}

	public void setPointCompleteDate(String pointCompleteDate) {
		this.pointCompleteDate = pointCompleteDate;
	}

	public String getPointValidDate() {
		return pointValidDate;
	}

	public void setPointValidDate(String pointValidDate) {
		this.pointValidDate = pointValidDate;
	}

	public String getCalibrateCompleteDate() {
		return calibrateCompleteDate;
	}

	public void setCalibrateCompleteDate(String calibrateCompleteDate) {
		this.calibrateCompleteDate = calibrateCompleteDate;
	}

	public String getCalibrateValidDate() {
		return calibrateValidDate;
	}

	public void setCalibrateValidDate(String calibrateValidDate) {
		this.calibrateValidDate = calibrateValidDate;
	}

	public String getMaintainCompleteDate() {
		return maintainCompleteDate;
	}

	public void setMaintainCompleteDate(String maintainCompleteDate) {
		this.maintainCompleteDate = maintainCompleteDate;
	}

	public String getMaintainValidDate() {
		return maintainValidDate;
	}

	public void setMaintainValidDate(String maintainValidDate) {
		this.maintainValidDate = maintainValidDate;
	}

	public String getCheckCompleteDate() {
		return checkCompleteDate;
	}

	public void setCheckCompleteDate(String checkCompleteDate) {
		this.checkCompleteDate = checkCompleteDate;
	}

	public String getCheckValidDate() {
		return checkValidDate;
	}

	public void setCheckValidDate(String checkValidDate) {
		this.checkValidDate = checkValidDate;
	}

	public List<EquipmentPartsEO> getPartsList() {
		return partsList;
	}

	public void setPartsList(List<EquipmentPartsEO> partsList) {
		this.partsList = partsList;
	}

	public List<EquipmentVO> getChildren() {
		return children;
	}

	public void setChildren(List<EquipmentVO> children) {
		this.children = children;
	}

	public String getIfUsing() {
		return ifUsing;
	}

	public void setIfUsing(String ifUsing) {
		this.ifUsing = ifUsing;
	}

	

}
