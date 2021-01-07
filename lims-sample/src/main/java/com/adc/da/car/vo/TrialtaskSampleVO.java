package com.adc.da.car.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/15 16:50
 * @description：${description}
 */
public class TrialtaskSampleVO {

    /**
     * 整车编号
     */
    @ApiModelProperty(value="整车编号")
    private String carNO;

    /**
     * 试验委托ID
     */
    @ApiModelProperty(value="试验委托ID")
    private String trialApplyId;

    /**
     * 试验委托编号
     */
    @ApiModelProperty(value="试验委托编号")
    private String trialApplyNO;

    public String getCarNO() {
        return carNO;
    }

    public void setCarNO(String carNO) {
        this.carNO = carNO;
    }

    public String getTrialApplyId() {
        return trialApplyId;
    }

    public void setTrialApplyId(String trialApplyId) {
        this.trialApplyId = trialApplyId;
    }

    public String getTrialApplyNO() {
        return trialApplyNO;
    }

    public void setTrialApplyNO(String trialApplyNO) {
        this.trialApplyNO = trialApplyNO;
    }
}
