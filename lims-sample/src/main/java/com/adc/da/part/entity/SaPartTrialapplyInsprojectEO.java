package com.adc.da.part.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author     ：fengzhiwei
 * @date       ：Created in 2019/7/29 16:02
 * @description：${description}
 */
public class SaPartTrialapplyInsprojectEO extends BaseEntity implements Serializable {
    @ApiModelProperty(value="null")
    private String id;

    /**
    * 是否删除（0，未删除；1，已删除）
    */
    @ApiModelProperty(value="是否删除（0，未删除；1，已删除）")
    private Short delFlag;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private String createTime;

    /**
    * 创建人id
    */
    @ApiModelProperty(value="创建人id")
    private String createBy;

    /**
    * 零部件ID
    */
    @ApiModelProperty(value="零部件ID")
    private String partDataId;

    /**
    * 零部件编号
    */
    @ApiModelProperty(value="零部件编号")
    private String partNo;

    /**
    * 试验委托ID
    */
    @ApiModelProperty(value="试验委托ID")
    private String trialApplyId;

    /**
    * 试验委托编号
    */
    @ApiModelProperty(value="试验委托编号")
    private String trialApplyNo;

    /**
    * 检验项目ID
    */
    @ApiModelProperty(value="检验项目ID")
    private String inspectProjectId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Short getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Short delFlag) {
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

    public String getPartDataId() {
        return partDataId;
    }

    public void setPartDataId(String partDataId) {
        this.partDataId = partDataId;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getTrialApplyId() {
        return trialApplyId;
    }

    public void setTrialApplyId(String trialApplyId) {
        this.trialApplyId = trialApplyId;
    }

    public String getTrialApplyNo() {
        return trialApplyNo;
    }

    public void setTrialApplyNo(String trialApplyNo) {
        this.trialApplyNo = trialApplyNo;
    }

    public String getInspectProjectId() {
        return inspectProjectId;
    }

    public void setInspectProjectId(String inspectProjectId) {
        this.inspectProjectId = inspectProjectId;
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
        sb.append(", partDataId=").append(partDataId);
        sb.append(", partNo=").append(partNo);
        sb.append(", trialApplyId=").append(trialApplyId);
        sb.append(", trialApplyNo=").append(trialApplyNo);
        sb.append(", inspectProjectId=").append(inspectProjectId);
        sb.append("]");
        return sb.toString();
    }
}