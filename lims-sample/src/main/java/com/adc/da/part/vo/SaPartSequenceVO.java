package com.adc.da.part.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/5 16:11
 * @description：${description}
 */
public class SaPartSequenceVO {

    @ApiModelProperty(value="null")
    private String sid;

    /**
     * 样品序列号
     */
    @ApiModelProperty(value = "样品序列号")
    private String sampleSequence;

    /**
     * 状态 接收:0, 领样：1, 归还:2, 退样: 3, 报废:4 , 封存：5， 拆机：6， 待收样:7
     */
    @ApiModelProperty(value = "状态 接收:0, 领样：1, 归还:2, 退样: 3, 报废:4 , 封存：5， 拆机：6， 待收样:7")
    private Integer status;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSampleSequence() {
        return sampleSequence;
    }

    public void setSampleSequence(String sampleSequence) {
        this.sampleSequence = sampleSequence;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
