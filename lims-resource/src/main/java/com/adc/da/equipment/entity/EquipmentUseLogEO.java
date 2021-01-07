package com.adc.da.equipment.entity;

import com.adc.da.base.entity.BaseEntity;

import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>设备使用日志表<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-27 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class EquipmentUseLogEO extends BaseEntity {

    @ApiModelProperty(" ID ")
    private String id;
    @ApiModelProperty(" 设备id ")
    private String eqId;
    @ApiModelProperty(" 设备状态 ")
    private String eqUlStatus;
    @ApiModelProperty(" 开始时间 ")
    private String eqUlStartTime;
    @ApiModelProperty(" 结束时间 ")
    private String eqUlEndTime;
    @ApiModelProperty(" 负责人 ")
    private String eqUlResponsiblePeople;
    @ApiModelProperty(" 负责人部门 ")
    private String eqUlResponsiblePeopleOrg;
    @ApiModelProperty(" 备注 ")
    private String eqUlRemark;
    @ApiModelProperty(" 创建人 ")
    private String createBy;
    @ApiModelProperty(" 创建时间 ")
    private String createTime;

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

	public String getEqUlStatus() {
		return eqUlStatus;
	}

	public void setEqUlStatus(String eqUlStatus) {
		this.eqUlStatus = eqUlStatus;
	}

	public String getEqUlStartTime() {
		return eqUlStartTime;
	}

	public void setEqUlStartTime(String eqUlStartTime) {
		this.eqUlStartTime = eqUlStartTime;
	}

	public String getEqUlEndTime() {
		return eqUlEndTime;
	}

	public void setEqUlEndTime(String eqUlEndTime) {
		this.eqUlEndTime = eqUlEndTime;
	}

	public String getEqUlResponsiblePeople() {
		return eqUlResponsiblePeople;
	}

    public String getEqUlResponsiblePeopleOrg() {
        return eqUlResponsiblePeopleOrg;
    }

    public void setEqUlResponsiblePeopleOrg(String eqUlResponsiblePeopleOrg) {
        this.eqUlResponsiblePeopleOrg = eqUlResponsiblePeopleOrg;
    }

    public void setEqUlResponsiblePeople(String eqUlResponsiblePeople) {
		this.eqUlResponsiblePeople = eqUlResponsiblePeople;
	}

	public String getEqUlRemark() {
		return eqUlRemark;
	}

	public void setEqUlRemark(String eqUlRemark) {
		this.eqUlRemark = eqUlRemark;
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

    
    

}
