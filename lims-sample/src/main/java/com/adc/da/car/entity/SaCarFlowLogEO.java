package com.adc.da.car.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/15 9:10
 * @description：${description}
 */
@ApiModel(value = "com.adc.da.car.entity.SaCarFlowLogEO")
public class SaCarFlowLogEO extends BaseEntity implements Serializable {
    @ApiModelProperty(value = "null")
    private String id;

    /**
     * 删除状态 1删除 0存在
     */
    @ApiModelProperty(value = "删除状态 1删除 0存在")
    private Integer delFlag;

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
     * 整车ID
     */
    @ApiModelProperty(value = "整车ID")
    private String carDataId;

    /**
     * 负责人ID
     */
    @ApiModelProperty(value = "负责人ID")
    private String leaderId;

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

    /**
     * 委托人员ID
     */
    @ApiModelProperty(value = "委托人员ID")
    private String entrustUserId;

    /**
     * 样品管理员ID
     */
    @ApiModelProperty(value = "样品管理员ID")
    private String sampleUserId;

    private static final long serialVersionUID = 1L;

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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCarDataId() {
        return carDataId;
    }

    public void setCarDataId(String carDataId) {
        this.carDataId = carDataId;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
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

    public String getEntrustUserId() {
        return entrustUserId;
    }

    public void setEntrustUserId(String entrustUserId) {
        this.entrustUserId = entrustUserId;
    }

    public String getSampleUserId() {
        return sampleUserId;
    }

    public void setSampleUserId(String sampleUserId) {
        this.sampleUserId = sampleUserId;
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
        sb.append(", carDataId=").append(carDataId);
        sb.append(", leaderId=").append(leaderId);
        sb.append(", operationDate=").append(operationDate);
        sb.append(", operationContent=").append(operationContent);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", trialApplyNo=").append(trialApplyNo);
        sb.append(", remarks=").append(remarks);
        sb.append(", others=").append(others);
        sb.append("]");
        return sb.toString();
    }
}