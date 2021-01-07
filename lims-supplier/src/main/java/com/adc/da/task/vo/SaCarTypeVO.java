package com.adc.da.task.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/20 15:44
 * @description：${description}
 */
public class SaCarTypeVO {

    @ApiModelProperty("车辆型号")
    private String carType;

    @ApiModelProperty("底盘号")
    private String underpanNO;

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getUnderpanNO() {
        return underpanNO;
    }

    public void setUnderpanNO(String underpanNO) {
        this.underpanNO = underpanNO;
    }

}
