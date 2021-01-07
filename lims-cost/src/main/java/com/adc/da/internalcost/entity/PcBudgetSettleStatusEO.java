package com.adc.da.internalcost.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 费用结算状态表
 * @author ：fengzhiwei
 * @date ：Created in 2019/11/20 17:26
 * @description：
 */
public class PcBudgetSettleStatusEO {

    /**
     * 主键
     */
    private String id;
    /**
     * 删除标记
     */
    private Integer delFlag;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 0，结算；1，统计
     */
    private String status;

    @ApiModelProperty("试验任务书ID")
    private String trialId;

    /**
     * 0，对公；1，对私
     */
    private String type;

    @ApiModelProperty("结算时间")
    private String settleTime;
    @ApiModelProperty("结算人ID")
    private String settleUserId;
    @ApiModelProperty("结算人姓名")
    private String settleUserNam;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrialId() {
        return trialId;
    }

    public void setTrialId(String trialId) {
        this.trialId = trialId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(String settleTime) {
        this.settleTime = settleTime;
    }

    public String getSettleUserId() {
        return settleUserId;
    }

    public void setSettleUserId(String settleUserId) {
        this.settleUserId = settleUserId;
    }

    public String getSettleUserNam() {
        return settleUserNam;
    }

    public void setSettleUserNam(String settleUserNam) {
        this.settleUserNam = settleUserNam;
    }
}
