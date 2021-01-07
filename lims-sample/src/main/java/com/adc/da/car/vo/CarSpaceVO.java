package com.adc.da.car.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/9 8:41
 * @description：车位vo
 */
public class CarSpaceVO {

    @ApiModelProperty(value = "")
    private String id;

    /**
     * 车位关联停车场id
     */
    @ApiModelProperty(value = "车位关联停车场id")
    private String carDepotId;

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
     * 已占用车位
     */
    @ApiModelProperty(value = "已占用车位")
    private String usedCarSpace;

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

    public String getUsedCarSpace() {
        return usedCarSpace;
    }

    public void setUsedCarSpace(String usedCarSpace) {
        this.usedCarSpace = usedCarSpace;
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
