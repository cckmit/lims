package com.adc.da.car.page;

import com.adc.da.base.page.BasePage;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/17 11:27
 * @description：${description}
 */
public class SaCarFlowLogEOPage extends BasePage {

    @ApiModelProperty(value = "整车ID")
    private String carId;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
}
