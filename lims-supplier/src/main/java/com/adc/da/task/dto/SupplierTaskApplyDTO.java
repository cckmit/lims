package com.adc.da.task.dto;

import com.adc.da.task.vo.SupplierTaskInfoVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/22 8:16
 * @description：${description}
 */
public class SupplierTaskApplyDTO {

    private String id;

    @ApiModelProperty("实验任务ID")
    private String trialTaskId;

    @ApiModelProperty("委托方id")
    private String applyId;

    @ApiModelProperty("委托方名称")
    private String applyName;

    @ApiModelProperty("受托方ID")
    private String beApplyForId;

    @ApiModelProperty("委托申请时间")
    private String applyTime;

    @ApiModelProperty("委托编号")
    private String applyNo;

    @ApiModelProperty("任务来源")
    private String taskSource;

    @ApiModelProperty("计划开始时间")
    private String planStartTime;

    @ApiModelProperty("计划结束时间")
    private String planEndTime;

    @ApiModelProperty("频次")
    private Integer frequency;

    @ApiModelProperty("单位")
    private String frequencyUnit;

    @ApiModelProperty("任务类型")
    private Integer taskType;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("意见")
    private String idea;

    @ApiModelProperty("是否接受")
    private Integer whetherAccept;

    @ApiModelProperty("1:乘用车   0：商用车")
    private Integer pvOrEv;

    @ApiModelProperty("任务信息")
    private List<SupplierTaskInfoDTO> supplierTaskInfoDTOS;
    private List<SupplierTaskInfoVO> supplierTaskInfoVOS;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrialTaskId() {
        return trialTaskId;
    }

    public void setTrialTaskId(String trialTaskId) {
        this.trialTaskId = trialTaskId;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getBeApplyForId() {
        return beApplyForId;
    }

    public void setBeApplyForId(String beApplyForId) {
        this.beApplyForId = beApplyForId;
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

    public String getTaskSource() {
        return taskSource;
    }

    public void setTaskSource(String taskSource) {
        this.taskSource = taskSource;
    }

    public String getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(String planStartTime) {
        this.planStartTime = planStartTime;
    }

    public String getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(String planEndTime) {
        this.planEndTime = planEndTime;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getFrequencyUnit() {
        return frequencyUnit;
    }

    public void setFrequencyUnit(String frequencyUnit) {
        this.frequencyUnit = frequencyUnit;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public Integer getWhetherAccept() {
        return whetherAccept;
    }

    public void setWhetherAccept(Integer whetherAccept) {
        this.whetherAccept = whetherAccept;
    }

    public Integer getPvOrEv() {
        return pvOrEv;
    }

    public void setPvOrEv(Integer pvOrEv) {
        this.pvOrEv = pvOrEv;
    }

    public List<SupplierTaskInfoVO> getSupplierTaskInfoVOS() {
        return supplierTaskInfoVOS;
    }

    public void setSupplierTaskInfoVOS(List<SupplierTaskInfoVO> supplierTaskInfoVOS) {
        this.supplierTaskInfoVOS = supplierTaskInfoVOS;
    }

    public List<SupplierTaskInfoDTO> getSupplierTaskInfoDTOS() {
        return supplierTaskInfoDTOS;
    }

    public void setSupplierTaskInfoDTOS(List<SupplierTaskInfoDTO> supplierTaskInfoDTOS) {
        this.supplierTaskInfoDTOS = supplierTaskInfoDTOS;
    }
}

