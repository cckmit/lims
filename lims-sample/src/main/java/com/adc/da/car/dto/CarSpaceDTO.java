package com.adc.da.car.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/9 9:33
 * @description：整车库房dto
 */
public class CarSpaceDTO {

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
}
