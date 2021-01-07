package com.adc.da.acttask.page;

import com.adc.da.base.page.BasePage;

import java.util.List;

/**
 * 待办列表分页查询
 */
public class ActTaskEOPage extends BasePage {

    private String businessId;

    private String title;

    private String businessType;

    private String createTime;

    private String createTime1;

    private String createTime2;

    private String formKey;

    private String proInstId;

    private String usId;

    private String userName;

    private String procDefId;

    private String taskTime;

    private String taskTime1;

    private String taskTime2;

    private String businessKey;

    private List<String> searchPhrase;

    private String currUserId;

    private String currNames;

    private String dealTime;

    private String dealTime1;

    private String dealTime2;

    //具体业务ID
    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    //标题
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //业务类型
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    //业务创建时间
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    //表单类型
    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    //流程实例ID
    public String getProInstId() {
        return proInstId;
    }

    public void setProInstId(String proInstId) {
        this.proInstId = proInstId;
    }

    //创建人ID
    public String getUsId() {
        return usId;
    }

    public void setUsId(String usId) {
        this.usId = usId;
    }

    //创建新人姓名
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    //流程定义key
    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    //任务创建时间
    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    //basebusID  中间表id
    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getCreateTime1() {
        return createTime1;
    }

    public void setCreateTime1(String createTime1) {
        this.createTime1 = createTime1;
    }

    public String getCreateTime2() {
        return createTime2;
    }

    public void setCreateTime2(String createTime2) {
        this.createTime2 = createTime2;
    }

    public String getTaskTime1() {
        return taskTime1;
    }

    public void setTaskTime1(String taskTime1) {
        this.taskTime1 = taskTime1;
    }

    public String getTaskTime2() {
        return taskTime2;
    }

    public void setTaskTime2(String taskTime2) {
        this.taskTime2 = taskTime2;
    }

    public List<String> getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(List<String> searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public String getCurrUserId() {
        return currUserId;
    }

    public void setCurrUserId(String currUserId) {
        this.currUserId = currUserId;
    }

    public String getCurrNames() {
        return currNames;
    }

    public void setCurrNames(String currNames) {
        this.currNames = currNames;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public String getDealTime1() {
        return dealTime1;
    }

    public void setDealTime1(String dealTime1) {
        this.dealTime1 = dealTime1;
    }

    public String getDealTime2() {
        return dealTime2;
    }

    public void setDealTime2(String dealTime2) {
        this.dealTime2 = dealTime2;
    }

}
