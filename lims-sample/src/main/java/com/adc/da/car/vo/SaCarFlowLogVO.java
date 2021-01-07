package com.adc.da.car.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/15 9:10
 * @description：${description}
 */
@ApiModel(value = "com.adc.da.car.entity.SaCarFlowLogVO")
public class SaCarFlowLogVO implements Serializable {

    @ApiModelProperty(value = "null")
    private String id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private String createBy;

    /**
     * 负责人ID
     */
    @ApiModelProperty(value = "负责人ID")
    private String leaderId;

    /**
     * 负责人姓名
     */
    @ApiModelProperty(value = "负责人姓名")
    private String leaderName;

    /**
     * 负责人部门
     */
    @ApiModelProperty(value = "负责人部门")
    private String leaderOrg;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    private String operationDate;

    /**
     * 操作内容
     */
    @ApiModelProperty(value = "操作内容")
    private String operationContent;

    /**
     * 经办人ID
     */
    @ApiModelProperty(value = "经办人ID")
    private String operatorId;

    /**
     * 经办人姓名
     */
    @ApiModelProperty(value = "经办人姓名")
    private String operatorName;

    /**
     * 经办人部门
     */
    @ApiModelProperty(value = "经办人部门")
    private String operatorOrg;

    /**
     * 检测委托合同编号
     */
    @ApiModelProperty(value = "检测委托合同编号")
    private String trialApplyNo;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

    /**
     * 其他
     */
    @ApiModelProperty(value = "其他")
    private String others;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeaderOrg() {
        return leaderOrg;
    }

    public void setLeaderOrg(String leaderOrg) {
        this.leaderOrg = leaderOrg;
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorOrg() {
        return operatorOrg;
    }

    public void setOperatorOrg(String operatorOrg) {
        this.operatorOrg = operatorOrg;
    }

    public String getTrialApplyNo() {
        return trialApplyNo;
    }

    public void setTrialApplyNo(String trialApplyNo) {
        this.trialApplyNo = trialApplyNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}