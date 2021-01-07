package com.adc.da.car.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "com.adc.da.car.entity.SaCarDepotEO")
public class SaCarDepotEO extends BaseEntity implements Serializable {
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String id;

    /**
     * 车位关联停车场id
     */
    @ApiModelProperty(value = "车位关联停车场id")
    private String carDepotId;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String depotName;

    /**
     * 停车场编号
     */
    @ApiModelProperty(value = "停车场编号")
    private String depotNumber;

    /**
     * 排数编号：第几排
     */
    @ApiModelProperty(value = "排数编号：第几排")
    private Integer rowNumber;

    /**
     * 车位数
     */
    @ApiModelProperty(value = "车位数")
    private Integer carSpaceNumber;

    /**
     * 起始编号
     */
    @ApiModelProperty(value = "起始编号")
    private Integer startNumber;

    /**
     * 是否为库房（0：库房，1：停车位）
     */
    @ApiModelProperty(value = "是否为库房（0：库房，1：停车位）")
    private Integer carSpaceStatus;

    /**
     * 已占用车位
     */
    @ApiModelProperty(value = "已占用车位")
    private String usedCarSpace;

    /**
     * 未使用车位数
     */
    @ApiModelProperty(value = "未使用车位数")
    private Integer unUseCarSpaceNumber;

    /**
     * 是否删除（0，未删除；1，已删除）
     */
    @ApiModelProperty(value = "是否删除（0，未删除；1，已删除）")
    private Integer delFlag;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarDepotId() {
        return carDepotId;
    }

    public void setCarDepotId(String carDepotId) {
        this.carDepotId = carDepotId;
    }

    public String getDepotName() {
        return depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName;
    }

    public String getDepotNumber() {
        return depotNumber;
    }

    public void setDepotNumber(String depotNumber) {
        this.depotNumber = depotNumber;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer getCarSpaceNumber() {
        return carSpaceNumber;
    }

    public void setCarSpaceNumber(Integer carSpaceNumber) {
        this.carSpaceNumber = carSpaceNumber;
    }

    public Integer getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }

    public Integer getCarSpaceStatus() {
        return carSpaceStatus;
    }

    public void setCarSpaceStatus(Integer carSpaceStatus) {
        this.carSpaceStatus = carSpaceStatus;
    }

    public String getUsedCarSpace() {
        return usedCarSpace;
    }

    public void setUsedCarSpace(String usedCarSpace) {
        this.usedCarSpace = usedCarSpace;
    }

    public Integer getUnUseCarSpaceNumber() {
        return unUseCarSpaceNumber;
    }

    public void setUnUseCarSpaceNumber(Integer unUseCarSpaceNumber) {
        this.unUseCarSpaceNumber = unUseCarSpaceNumber;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}

