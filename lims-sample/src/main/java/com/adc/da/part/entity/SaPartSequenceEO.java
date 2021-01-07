package com.adc.da.part.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/5 15:20
 * @description：${description}
 */
@ApiModel(value = "com.adc.da.part.entity.SaPartSequenceEO")
public class SaPartSequenceEO implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;


    @ApiModelProperty(value = "样品序列号")
    private String sampleSequence;


    @ApiModelProperty(value = "负责人")
    private String recevier;


    @ApiModelProperty(value = "负责人部门")
    private String recevierDepartment;


    @ApiModelProperty(value = "经办人")
    private String operator;


    @ApiModelProperty(value = "经办人部门")
    private String operatorDepartment;


    @ApiModelProperty(value = "试验申请编号")
    private String trialApplyNo;


    @ApiModelProperty(value = "交接时间")
    private String transferDate;


    @ApiModelProperty(value = "样品状态")
    private String sampleStatus;


    @ApiModelProperty(value = "零部件号id")
    private String partId;


    @ApiModelProperty(value = "状态 接收:0, 领样：1, 在库:2, 退样: 3, 报废:4 , 封存：5， 拆机：6， 待收样:7")
    private Integer status;


    @ApiModelProperty(value = "是否退样 留样：1, 退样:3, 报废:4 ,其他:5")
    private Integer orExit;


    @ApiModelProperty(value = "备注")
    private String remark;


    @ApiModelProperty(value = "报告结果确认")
    private String reportResult;


    @ApiModelProperty(value = "留样截止时间")
    private String endDate;


    @ApiModelProperty(value = "报废时间")
    private String scrapDate;


    @ApiModelProperty(value = "流程号")
    private String processNum;


    @ApiModelProperty(value = "临时字段")
    private String other;


    @ApiModelProperty(value = "操作")
    private String operation;


    @ApiModelProperty(value = "逻辑删除 0-未删除 1-删除")
    private Integer del;


    @ApiModelProperty(value = "拆机时间")
    private String dismantleDate;


    @ApiModelProperty(value = "流转时间")
    private String circulateDate;


    @ApiModelProperty(value = "封存时间")
    private String sealingDate;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 样品序列号
     */
    public String getSampleSequence() {
        return sampleSequence;
    }

    public void setSampleSequence(String sampleSequence) {
        this.sampleSequence = sampleSequence;
    }

    /**
     * 负责人
     */
    public String getRecevier() {
        return recevier;
    }

    public void setRecevier(String recevier) {
        this.recevier = recevier;
    }

    /**
     * 负责人部门
     */
    public String getRecevierDepartment() {
        return recevierDepartment;
    }

    public void setRecevierDepartment(String recevierDepartment) {
        this.recevierDepartment = recevierDepartment;
    }

    /**
     * 经办人
     */
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 经办人部门
     */
    public String getOperatorDepartment() {
        return operatorDepartment;
    }

    public void setOperatorDepartment(String operatorDepartment) {
        this.operatorDepartment = operatorDepartment;
    }

    /**
     * 试验申请编号
     */
    public String getTrialApplyNo() {
        return trialApplyNo;
    }

    public void setTrialApplyNo(String trialApplyNo) {
        this.trialApplyNo = trialApplyNo;
    }

    /**
     * 交接时间
     */
    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    /**
     * 样品状态
     */
    public String getSampleStatus() {
        return sampleStatus;
    }

    public void setSampleStatus(String sampleStatus) {
        this.sampleStatus = sampleStatus;
    }

    /**
     * 零部件号id
     */
    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    /**
     * 状态 接收:0, 领样：1, 在库:2, 退样: 3, 报废:4 , 封存：5， 拆机：6， 待收样:7
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 是否退样 留样：1, 退样:3, 报废:4 ,其他:5
     */
    public Integer getOrExit() {
        return orExit;
    }

    public void setOrExit(Integer orExit) {
        this.orExit = orExit;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 报告结果确认
     */
    public String getReportResult() {
        return reportResult;
    }

    public void setReportResult(String reportResult) {
        this.reportResult = reportResult;
    }

    /**
     * 留样截止时间
     */
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 报废时间
     */
    public String getScrapDate() {
        return scrapDate;
    }

    public void setScrapDate(String scrapDate) {
        this.scrapDate = scrapDate;
    }

    /**
     * 流程号
     */
    public String getProcessNum() {
        return processNum;
    }

    public void setProcessNum(String processNum) {
        this.processNum = processNum;
    }

    /**
     * 临时字段
     */
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    /**
     * 操作
     */
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * 逻辑删除 0-未删除 1-删除
     */
    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    /**
     * 拆机时间
     */
    public String getDismantleDate() {
        return dismantleDate;
    }

    public void setDismantleDate(String dismantleDate) {
        this.dismantleDate = dismantleDate;
    }

    /**
     * 流转时间
     */
    public String getCirculateDate() {
        return circulateDate;
    }

    public void setCirculateDate(String circulateDate) {
        this.circulateDate = circulateDate;
    }

    /**
     * 封存时间
     */
    public String getSealingDate() {
        return sealingDate;
    }

    public void setSealingDate(String sealingDate) {
        this.sealingDate = sealingDate;
    }
}