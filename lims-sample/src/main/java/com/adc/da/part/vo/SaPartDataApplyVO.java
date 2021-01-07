package com.adc.da.part.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/27 14:00
 * @description：${description}
 */
public class SaPartDataApplyVO {

    /**
     * 整车ID
     */
    @ApiModelProperty(value = "整车ID")
    private String partId;

    /**
     * 整车编号
     */
    @ApiModelProperty(value = "整车编号")
    private String partNO;

    /**
     * 试验委托ID
     */
    @ApiModelProperty(value = "试验委托ID")
    private String trialApplyId;

    /**
     * 试验委托编号
     */
    @ApiModelProperty(value = "试验委托编号")
    private String trialApplyNO;

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getPartNO() {
        return partNO;
    }

    public void setPartNO(String partNO) {
        this.partNO = partNO;
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
