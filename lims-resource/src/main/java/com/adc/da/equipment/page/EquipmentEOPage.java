package com.adc.da.equipment.page;

import java.util.List;
import java.util.Map;

import com.adc.da.base.page.BasePage;

import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>RES_EQUIPMENT EquipmentEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-27 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class EquipmentEOPage extends BasePage {

	//设备编号
	private String eqNo;
	//设备名称
	private String eqName;
	//规格型号
	private String eqType;
	//设备使用状态
	private String eqUseStatus;
	//设备状态
	private String eqStatus;
	//管理单位
	private String eqManagerOrg;
	//借用人/报废人
	private String eqUser;
	//类别
	private String eqSort;
	//流程状态;0:无审批流程；1：流程审批中
    private String eqProcedureState;
    //前台传过来的通用查询条件
    private String searchPhrase;
    //解析后的通用查询条件
    private List<Map<String, Object>> searchMap;    
	/*
	 * 根据checkChild和id两个条件进行判断（适用于分页查询和列表查询这两个方法）：
	 * 1、checkChild != "y" && id == null : 只查询主设备（分页查询列表）
	 * 2、checkChild != "y" && id != null : 只查询当前设备（id）的所有子设备（点击“+”查询子设备）
	 * 3、checkChild == "y" && id == null : 只查询可作为子设备的设备列表（新增时绑定子设备）
	 * 4、checkChild == "y" && id != null : 只查询可作为当前设备（id）子设备的设备列表（修改时绑定子设备）
	 */
	private String checkChild = "n";
	private String id;

	public String getEqNo() {
		return eqNo;
	}

	public void setEqNo(String eqNo) {
		this.eqNo = eqNo;
	}

	public String getEqName() {
		return eqName;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	public String getEqType() {
		return eqType;
	}

	public void setEqType(String eqType) {
		this.eqType = eqType;
	}

	public String getEqUseStatus() {
		return eqUseStatus;
	}

	public void setEqUseStatus(String eqUseStatus) {
		this.eqUseStatus = eqUseStatus;
	}

	public String getEqStatus() {
		return eqStatus;
	}

	public void setEqStatus(String eqStatus) {
		this.eqStatus = eqStatus;
	}

	public String getEqManagerOrg() {
		return eqManagerOrg;
	}

	public void setEqManagerOrg(String eqManagerOrg) {
		this.eqManagerOrg = eqManagerOrg;
	}

	public String getEqUser() {
		return eqUser;
	}

	public void setEqUser(String eqUser) {
		this.eqUser = eqUser;
	}

	public String getEqSort() {
		return eqSort;
	}

	public void setEqSort(String eqSort) {
		this.eqSort = eqSort;
	}

	public String getSearchPhrase() {
		return searchPhrase;
	}

	public void setSearchPhrase(String searchPhrase) {
		this.searchPhrase = searchPhrase;
	}

	public List<Map<String, Object>> getSearchMap() {
		return searchMap;
	}

	public void setSearchMap(List<Map<String, Object>> searchMap) {
		this.searchMap = searchMap;
	}

	public String getCheckChild() {
		return checkChild;
	}

	public void setCheckChild(String checkChild) {
		this.checkChild = checkChild;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public String getEqProcedureState() {
        return eqProcedureState;
    }

    public void setEqProcedureState(String eqProcedureState) {
        this.eqProcedureState = eqProcedureState;
    }
}
