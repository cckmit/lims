package com.adc.da.message.page;

import com.adc.da.base.page.BasePage;

import java.util.List;


/**
 * <b>功能：</b>TP_MESSAGE MessageEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-07-23 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class MessageEOPage extends BasePage {

    private List<String> searchPhrase;
    private String id;
    private String idOperator = "=";
    private String isread;
    private String isreadOperator = "=";
    private String sendlink;
    private String sendlinkOperator = "=";
    private String sendtime;
    private String sendtime1;
    private String sendtime2;
    private String sendtimeOperator = "=";
    private String senduser;
    private String senduserOperator = "=";
    private String title;
    private String titleOperator = "=";
    private String ccCreateById;
    private String ccCreateByIdOperator = "=";
    private String businessId;
    private String businessIdOperator = "=";

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

    public String getIsread() {
        return this.isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

    public String getIsreadOperator() {
        return this.isreadOperator;
    }

    public void setIsreadOperator(String isreadOperator) {
        this.isreadOperator = isreadOperator;
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

    public String getSendtime() {
        return this.sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
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

    public String getBusinessId() {
        return this.businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessIdOperator() {
        return this.businessIdOperator;
    }

    public void setBusinessIdOperator(String businessIdOperator) {
        this.businessIdOperator = businessIdOperator;
    }


    public List<String> getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(List<String> searchPhrase) {
        this.searchPhrase = searchPhrase;
    }


    public String getSendtime1() {
        return sendtime1;
    }

    public void setSendtime1(String sendtime1) {
        this.sendtime1 = sendtime1;
    }

    public String getSendtime2() {
        return sendtime2;
    }

    public void setSendtime2(String sendtime2) {
        this.sendtime2 = sendtime2;
    }



}
