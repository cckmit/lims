package com.adc.da.task.page;

import com.adc.da.base.page.BasePage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/23 16:47
 * @description：${description}
 */
public class SupplierTaskExeutionEOPage extends BasePage {

    @ApiModelProperty("申请时间")
    private String applyTime;

    private String applyTime1;

    private String applyTime2;

    @ApiModelProperty("委托编号")
    private String applyNo;

    @ApiModelProperty("委托编号")
    private String trustcode;


    private String supplierPeopleId;

    @ApiModelProperty("供应商ID")
    private String beApplyForId;


    @ApiModelProperty("状态（0，草稿；1，进行中,；2，已完成；3，待确认,；4，结束）")
    private Integer status;

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

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getTrustcode() {
        return trustcode;
    }

    public void setTrustcode(String trustcode) {
        this.trustcode = trustcode;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public String getSupplierPeopleId() {
        return supplierPeopleId;
    }

    public void setSupplierPeopleId(String supplierPeopleId) {
        this.supplierPeopleId = supplierPeopleId;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public String getBeApplyForId() {
        return beApplyForId;
    }

    public void setBeApplyForId(String beApplyForId) {
        this.beApplyForId = beApplyForId;
    }

    public String getApplyTime1() {
        return applyTime1;
    }

    public void setApplyTime1(String applyTime1) {
        this.applyTime1 = applyTime1;
    }

    public String getApplyTime2() {
        return applyTime2;
    }

    public void setApplyTime2(String applyTime2) {
        this.applyTime2 = applyTime2;
    }
}
