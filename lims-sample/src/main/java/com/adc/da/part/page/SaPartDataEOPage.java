package com.adc.da.part.page;

import com.adc.da.base.page.BasePage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/4 22:46
 * @description：${description}
 */
public class SaPartDataEOPage extends BasePage {

    @ApiModelProperty("检验委托合同编号")
    private String trialApplyNO;

    @ApiModelProperty("样品名称")
    private String partName;

    @ApiModelProperty("零部件/发动机号")
    private String partEngineNo;

    @ApiModelProperty("样件数量")
    private String sampleNumber;

    @ApiModelProperty("生产厂家")
    private String producedFactory;

    @ApiModelProperty("送样人")
    private String sendPartUserName;

    @ApiModelProperty("领养人")
    private String getPartUserName;

    @ApiModelProperty("所在位置")
    private String partSpaceLocation;

    @ApiModelProperty("在架")
    private Integer inShelf;

    @ApiModelProperty("通用查询")
    private String searchPhrase;

    @ApiModelProperty("创建时间结束")
    private String createTimeEnd;

    @ApiModelProperty("创建时间开始")
    private String createTimeStart;

    @ApiModelProperty("规格型号")
    private String specificationType;

    /**
     * 通过查询条件集合
     */
    @JsonIgnore
    private List<String> keyWords;

    public String getTrialApplyNO() {
        return trialApplyNO;
    }

    public void setTrialApplyNO(String trialApplyNO) {
        this.trialApplyNO = trialApplyNO;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartEngineNo() {
        return partEngineNo;
    }

    public void setPartEngineNo(String partEngineNo) {
        this.partEngineNo = partEngineNo;
    }

    public String getSampleNumber() {
        return sampleNumber;
    }

    public void setSampleNumber(String sampleNumber) {
        this.sampleNumber = sampleNumber;
    }

    public String getProducedFactory() {
        return producedFactory;
    }

    public void setProducedFactory(String producedFactory) {
        this.producedFactory = producedFactory;
    }

    public String getSendPartUserName() {
        return sendPartUserName;
    }

    public void setSendPartUserName(String sendPartUserName) {
        this.sendPartUserName = sendPartUserName;
    }

    public String getGetPartUserName() {
        return getPartUserName;
    }

    public void setGetPartUserName(String getPartUserName) {
        this.getPartUserName = getPartUserName;
    }

    public String getPartSpaceLocation() {
        return partSpaceLocation;
    }

    public void setPartSpaceLocation(String partSpaceLocation) {
        this.partSpaceLocation = partSpaceLocation;
    }

    public Integer getInShelf() {
        return inShelf;
    }

    public void setInShelf(Integer inShelf) {
        this.inShelf = inShelf;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public String getSpecificationType() {
        return specificationType;
    }

    public void setSpecificationType(String specificationType) {
        this.specificationType = specificationType;
    }
}
