package com.adc.da.part.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/29 16:01
 * @description：${description}
 */
public class SaPartFlowLogEO extends BaseEntity implements Serializable {
    @ApiModelProperty(value = "null")
    private String id;

    @ApiModelProperty(value = "是否删除（0，未删除；1，已删除）")
    private Integer delFlag;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;


    @ApiModelProperty(value = "零部件序列ID")
    private String partSequenceId;


    @ApiModelProperty(value = "负责人ID")
    private String leaderId;


    @ApiModelProperty(value = "操作时间")
    private String operationDate;


    @ApiModelProperty(value = "操作内容")
    private String operationContent;


    @ApiModelProperty(value = "经办人ID")
    private String operatorId;


    @ApiModelProperty(value = "检测委托合同编号")
    private String trialApplyNo;


    @ApiModelProperty(value = "备注")
    private String remarks;


    @ApiModelProperty(value = "委托人员ID")
    private String entrustUserId;


    @ApiModelProperty(value = "样品管理员ID")
    private String sampleUserId;


    @ApiModelProperty(value = "其他")
    private String others;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 是否删除（0，未删除；1，已删除）
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 零部件序列ID
     */
    public String getPartSequenceId() {
        return partSequenceId;
    }

    public void setPartSequenceId(String partSequenceId) {
        this.partSequenceId = partSequenceId;
    }

    /**
     * 负责人ID
     */
    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    /**
     * 操作时间
     */
    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    /**
     * 操作内容
     */
    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    /**
     * 经办人ID
     */
    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 检测委托合同编号
     */
    public String getTrialApplyNo() {
        return trialApplyNo;
    }

    public void setTrialApplyNo(String trialApplyNo) {
        this.trialApplyNo = trialApplyNo;
    }

    /**
     * 备注
     */
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 委托人员ID
     */
    public String getEntrustUserId() {
        return entrustUserId;
    }

    public void setEntrustUserId(String entrustUserId) {
        this.entrustUserId = entrustUserId;
    }

    /**
     * 样品管理员ID
     */
    public String getSampleUserId() {
        return sampleUserId;
    }

    public void setSampleUserId(String sampleUserId) {
        this.sampleUserId = sampleUserId;
    }

    /**
     * 其他
     */
    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", leaderId=").append(leaderId);
        sb.append(", operationDate=").append(operationDate);
        sb.append(", operationContent=").append(operationContent);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", trialApplyNo=").append(trialApplyNo);
        sb.append(", remarks=").append(remarks);
        sb.append(", entrustUserId=").append(entrustUserId);
        sb.append(", sampleUserId=").append(sampleUserId);
        sb.append(", others=").append(others);
        sb.append("]");
        return sb.toString();
    }
}