package com.adc.da.acttask.entity;

import com.adc.da.base.entity.BaseEntity;

/**
 * 待办实例的VO
 */
public class ActTaskEO extends BaseEntity {

    //具体业务id
     private String businessId;

     //标题
     private String title;

     //业务类型
     private String businessType;

     //流程发起时间
     private String createTime;

     //表单
     private String formKey;

     //流程实例id
     private String proInstId;

     //发起人id
     private String usId;

     //发起人姓名
     private String userName;

     //流程定义ID
     private String procDefId;

     //任务开始时间
     private String taskTime;

     //basebusid
     private String businessKey;

     //当前执行人ids
     private String currIds;

     //当前执行人names
     private String currNames;

     //待办处理时间
     private String dealTime;

     //任务id
     private String taskId;

     //当前处理人(带班表，用来判断任务类型）
    private String assignee;

    private String collectionStatus;

    public String getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(String collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

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

    /**
     * 当前处理人ids
     * @return
     */
    public String getCurrIds() {
        return currIds;
    }

    public void setCurrIds(String currIds) {
        this.currIds = currIds;
    }

    /**
     * 当前处理人names
     * @return
     */
    public String getCurrNames() {
        return currNames;
    }

    public void setCurrNames(String currNames) {
        this.currNames = currNames;
    }

    /**
     * 办理时间
     * @return
     */
    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    /**
     * 任务id
     * @return
     */
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
}
