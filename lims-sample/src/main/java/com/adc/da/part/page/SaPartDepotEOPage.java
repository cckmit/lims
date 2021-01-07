package com.adc.da.part.page;

import com.adc.da.base.page.BasePage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/8 11:56
 * @description：${description}
 */
public class SaPartDepotEOPage extends BasePage {

    private String id;

    /**
     * 库房名称
     */
    @ApiModelProperty(value = "库房名称")
    private String depotName;

    /**
     * 库房编号
     */
    @ApiModelProperty(value = "库房编号")
    private String depotNumber;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "起始更新时间")
    private String updateTime1;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "结束更新时间")
    private String updateTime2;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private static final long serialVersionUID = 1L;

    public String getDepotName() {
        return depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName;
    }

    public String getDepotNumber() {
        return depotNumber;
    }

    public void setDepotNumber(String depotNumber) {
        this.depotNumber = depotNumber;
    }

    public String getUpdateTime1() {
        return updateTime1;
    }

    public void setUpdateTime1(String updateTime1) {
        this.updateTime1 = updateTime1;
    }

    public String getUpdateTime2() {
        return updateTime2;
    }

    public void setUpdateTime2(String updateTime2) {
        this.updateTime2 = updateTime2;
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
}
