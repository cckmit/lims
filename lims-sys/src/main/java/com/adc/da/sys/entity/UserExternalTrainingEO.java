package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;

import java.io.Serializable;

/*
 * @Author syxy_zhangyinghui
 * @Description 表名【TR_USER_EXTERNAL_TRAINING】 功能【用户外部培训表】
 * @Date 13:30 2019/7/25
 **/
public class UserExternalTrainingEO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5495063094069000708L;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【主键id】
     * @Date 16:12 2019/7/25
     **/
    private String id;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【用户id】
     * @Date 13:32 2019/7/25
     **/
    private String usid;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【开始时间】
     * @Date 13:39 2019/7/25
     **/
    private String startTime;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【结束时间】
     * @Date 13:39 2019/7/25
     **/
    private String endTime;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【课程名称】
     * @Date 13:42 2019/7/25
     **/
    private String courseName;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【培训机构】
     * @Date 13:42 2019/7/25
     **/
    private String trainingOrganization;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【培训结果】
     * @Date 13:42 2019/7/25
     **/
    private String trainingResults;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【删除标志 0 正常  1删除】
     * @Date 13:42 2019/7/25
     **/
    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTrainingOrganization() {
        return trainingOrganization;
    }

    public void setTrainingOrganization(String trainingOrganization) {
        this.trainingOrganization = trainingOrganization;
    }

    public String getTrainingResults() {
        return trainingResults;
    }

    public void setTrainingResults(String trainingResults) {
        this.trainingResults = trainingResults;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
