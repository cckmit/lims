package com.adc.da.pc_outsource.page;

import com.adc.da.base.page.BasePage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * <b>功能：</b>PC_OUTSOURCE_PROJECT PcOutsourceProjectEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcOutsourceProjectEOPage extends BasePage {

    @ApiModelProperty("编号")
    private String code;
    @JsonIgnore
    private String opCodeOperator = "=";
    @ApiModelProperty("项目名称")
    private String insProject;
    @JsonIgnore
    private String insProjectOperator = "=";
    @ApiModelProperty("项目类型")
    private String insType;
    @JsonIgnore
    private String insTypeOperator = "=";
    @ApiModelProperty("负责部门")
    private String managerOrg;
    @JsonIgnore
    private String managerOrgOperator = "=";
    @ApiModelProperty("负责任人")
    private String managerUser;
    @JsonIgnore
    private String managerUserOperator = "=";
    @ApiModelProperty("申请日期")
    private String applyDateStart;
    @ApiModelProperty("申请日期")
    private String applyDateEnd;
    @JsonIgnore
    private String applyDateOperator = "=";
    @ApiModelProperty("项目简介")
    private String opDesc;
    @JsonIgnore
    private String opDescOperator = "=";
    @ApiModelProperty("试验ID")
    private String trialId;
    @JsonIgnore
    private String trialIdOperator = "=";
    @ApiModelProperty("试验code")
    private String trialCode;
    @JsonIgnore
    private String trialCodeOperator = "=";


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpCodeOperator() {
        return opCodeOperator;
    }

    public void setOpCodeOperator(String opCodeOperator) {
        this.opCodeOperator = opCodeOperator;
    }

    public String getInsProject() {
        return insProject;
    }

    public void setInsProject(String insProject) {
        this.insProject = insProject;
    }

    public String getInsProjectOperator() {
        return insProjectOperator;
    }

    public void setInsProjectOperator(String insProjectOperator) {
        this.insProjectOperator = insProjectOperator;
    }

    public String getInsType() {
        return insType;
    }

    public void setInsType(String insType) {
        this.insType = insType;
    }

    public String getInsTypeOperator() {
        return insTypeOperator;
    }

    public void setInsTypeOperator(String insTypeOperator) {
        this.insTypeOperator = insTypeOperator;
    }

    public String getManagerOrg() {
        return managerOrg;
    }

    public void setManagerOrg(String managerOrg) {
        this.managerOrg = managerOrg;
    }

    public String getManagerOrgOperator() {
        return managerOrgOperator;
    }

    public void setManagerOrgOperator(String managerOrgOperator) {
        this.managerOrgOperator = managerOrgOperator;
    }

    public String getManagerUser() {
        return managerUser;
    }

    public void setManagerUser(String managerUser) {
        this.managerUser = managerUser;
    }

    public String getManagerUserOperator() {
        return managerUserOperator;
    }

    public void setManagerUserOperator(String managerUserOperator) {
        this.managerUserOperator = managerUserOperator;
    }

    public String getApplyDateStart() {
        return applyDateStart;
    }

    public void setApplyDateStart(String applyDateStart) {
        this.applyDateStart = applyDateStart;
    }

    public String getApplyDateEnd() {
        return applyDateEnd;
    }

    public void setApplyDateEnd(String applyDateEnd) {
        this.applyDateEnd = applyDateEnd;
    }

    public String getApplyDateOperator() {
        return applyDateOperator;
    }

    public void setApplyDateOperator(String applyDateOperator) {
        this.applyDateOperator = applyDateOperator;
    }

    public String getOpDesc() {
        return opDesc;
    }

    public void setOpDesc(String opDesc) {
        this.opDesc = opDesc;
    }

    public String getOpDescOperator() {
        return opDescOperator;
    }

    public void setOpDescOperator(String opDescOperator) {
        this.opDescOperator = opDescOperator;
    }

    public String getTrialId() {
        return trialId;
    }

    public void setTrialId(String trialId) {
        this.trialId = trialId;
    }

    public String getTrialIdOperator() {
        return trialIdOperator;
    }

    public void setTrialIdOperator(String trialIdOperator) {
        this.trialIdOperator = trialIdOperator;
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

    public String getTrialCode() {
        return trialCode;
    }

    public void setTrialCode(String trialCode) {
        this.trialCode = trialCode;
    }

    public String getTrialCodeOperator() {
        return trialCodeOperator;
    }

    public void setTrialCodeOperator(String trialCodeOperator) {
        this.trialCodeOperator = trialCodeOperator;
    }
}
