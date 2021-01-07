package com.adc.da.collection.page;

import com.adc.da.base.page.BasePage;

import java.util.Date;
import java.util.List;

/**
 * <b>功能：</b>TP_COLLECTION CollectionEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-07-23 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class CollectionEOPage extends BasePage {

    private String id;
    private String idOperator = "=";
    private String collectiontime;
    private String collectiontime1;
    private String collectiontime2;
    private String collectiontimeOperator = "=";
    private String sendtime;
    private String sendtime1;
    private String sendtime2;
    private String sendtimeOperator = "=";
    private String senduser;
    private String senduserOperator = "=";
    private String title;
    private String titleOperator = "=";
    private String type;
    private String typeOperator = "=";
    private String sendlink;
    private String sendlinkOperator = "=";
    private String ccCreateById;
    private String ccCreateByIdOperator = "=";
    private String businessid;
    private String businessidOperator = "=";
    private String delFlag;
    private String delFlagOperator = "=";
    /**
     * 通用查询
     */
    private List<String> searchPhrase;

    public List<String> getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(List<String> searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOperator() {
        return this.idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

    public String getCollectiontime() {
        return this.collectiontime;
    }

    public void setCollectiontime(String collectiontime) {
        this.collectiontime = collectiontime;
    }

    public String getCollectiontime1() {
        return this.collectiontime1;
    }

    public void setCollectiontime1(String collectiontime1) {
        this.collectiontime1 = collectiontime1;
    }

    public String getCollectiontime2() {
        return this.collectiontime2;
    }

    public void setCollectiontime2(String collectiontime2) {
        this.collectiontime2 = collectiontime2;
    }

    public String getCollectiontimeOperator() {
        return this.collectiontimeOperator;
    }

    public void setCollectiontimeOperator(String collectiontimeOperator) {
        this.collectiontimeOperator = collectiontimeOperator;
    }

    public String getSendtime() {
        return this.sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getSendtime1() {
        return this.sendtime1;
    }

    public void setSendtime1(String sendtime1) {
        this.sendtime1 = sendtime1;
    }

    public String getSendtime2() {
        return this.sendtime2;
    }

    public void setSendtime2(String sendtime2) {
        this.sendtime2 = sendtime2;
    }

    public String getSendtimeOperator() {
        return this.sendtimeOperator;
    }

    public void setSendtimeOperator(String sendtimeOperator) {
        this.sendtimeOperator = sendtimeOperator;
    }

    public String getSenduser() {
        return this.senduser;
    }

    public void setSenduser(String senduser) {
        this.senduser = senduser;
    }

    public String getSenduserOperator() {
        return this.senduserOperator;
    }

    public void setSenduserOperator(String senduserOperator) {
        this.senduserOperator = senduserOperator;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleOperator() {
        return this.titleOperator;
    }

    public void setTitleOperator(String titleOperator) {
        this.titleOperator = titleOperator;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeOperator() {
        return this.typeOperator;
    }

    public void setTypeOperator(String typeOperator) {
        this.typeOperator = typeOperator;
    }

    public String getSendlink() {
        return this.sendlink;
    }

    public void setSendlink(String sendlink) {
        this.sendlink = sendlink;
    }

    public String getSendlinkOperator() {
        return this.sendlinkOperator;
    }

    public void setSendlinkOperator(String sendlinkOperator) {
        this.sendlinkOperator = sendlinkOperator;
    }

    public String getCcCreateById() {
        return this.ccCreateById;
    }

    public void setCcCreateById(String ccCreateById) {
        this.ccCreateById = ccCreateById;
    }

    public String getCcCreateByIdOperator() {
        return this.ccCreateByIdOperator;
    }

    public void setCcCreateByIdOperator(String ccCreateByIdOperator) {
        this.ccCreateByIdOperator = ccCreateByIdOperator;
    }

    public String getBusinessid() {
        return this.businessid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid;
    }

    public String getBusinessidOperator() {
        return this.businessidOperator;
    }

    public void setBusinessidOperator(String businessidOperator) {
        this.businessidOperator = businessidOperator;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlagOperator() {
        return this.delFlagOperator;
    }

    public void setDelFlagOperator(String delFlagOperator) {
        this.delFlagOperator = delFlagOperator;
    }

}
