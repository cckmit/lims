package com.adc.da.task.page;

import com.adc.da.base.page.BasePage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/9/2 16:27
 * @description：供应商任务统计分页page
 */
public class SupplierTaskStatisticsPage extends BasePage {

    @ApiModelProperty("受托方名称")
    private String beApplyForName;

    @ApiModelProperty("委托编号")
    private String applyNo;

    @ApiModelProperty("输出物")
    private String outputContent;

    @ApiModelProperty("工作内容")
    private String jobContent;

    @ApiModelProperty("状态（0，草稿；1，审批中；2，进行中；3，已完成,；4，已退回；5，已取消）")
    private Integer status;

    @ApiModelProperty("车辆型号")
    private String carType;

    @ApiModelProperty("底盘号")
    private String underpanNO;

    @ApiModelProperty("供应商")
    private String supId;

    @ApiModelProperty("任务id")
    private String trialTaskId;

    /**
     * 通用查询
     */
    @ApiModelProperty(value = "通用查询")
    private String searchPhrase;

    /**
     * 通过查询条件集合
     */
    @JsonIgnore
    private List<String> keyWords;

    public String getBeApplyForName() {
        return beApplyForName;
    }

    public void setBeApplyForName(String beApplyForName) {
        this.beApplyForName = beApplyForName;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getOutputContent() {
        return outputContent;
    }

    public void setOutputContent(String outputContent) {
        this.outputContent = outputContent;
    }

    public String getJobContent() {
        return jobContent;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getUnderpanNO() {
        return underpanNO;
    }

    public void setUnderpanNO(String underpanNO) {
        this.underpanNO = underpanNO;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public String getTrialTaskId() {
        return trialTaskId;
    }

    public void setTrialTaskId(String trialTaskId) {
        this.trialTaskId = trialTaskId;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }
}
