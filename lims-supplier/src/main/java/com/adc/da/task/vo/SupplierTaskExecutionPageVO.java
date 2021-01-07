package com.adc.da.task.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/23 16:38
 * @description：${description}
 */
public class SupplierTaskExecutionPageVO {

    @ApiModelProperty("")
    private String id;

    @ApiModelProperty("申请时间")
    private String applyTime;

    @ApiModelProperty("委托编号")
    private String applyNo;

    @ApiModelProperty("状态（0，草稿；1，进行中,；2，已完成；3，待确认,；4，结束）")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
