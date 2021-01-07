package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;

import java.io.Serializable;

/*
 * @Author syxy_zhangyinghui
 * @Description 表名【TR_USER_INTERNAL_TRAINING】 功能【用户内部培训表】
 * @Date 8:31 2019/7/26
 **/
public class UserInternalTrainingEO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -8688860505235120385L;


    /*
     * @Author syxy_zhangyinghui
     * @Description 【主键Id】
     * @Date 8:35 2019/7/26
     **/
    private String id;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【用户id】
     * @Date 8:35 2019/7/26
     **/
    private String usid;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【课程编号】
     * @Date 8:35 2019/7/26
     **/
    private String courseNumber;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【课程名称】
     * @Date 8:36 2019/7/26
     **/
    private String courseName;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【培训讲师】
     * @Date 8:36 2019/7/26
     **/
    private String trainingTeacher;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【培训对象】
     * @Date 8:36 2019/7/26
     **/
    private String trainingObject;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【培训对象】
     * @Date 8:36 2019/7/26
     **/
    private String trainingTime;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【培训工时】
     * @Date 8:36 2019/7/26
     **/
    private String trainingHours;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【删除状态】
     * @Date 8:37 2019/7/26
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

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTrainingTeacher() {
        return trainingTeacher;
    }

    public void setTrainingTeacher(String trainingTeacher) {
        this.trainingTeacher = trainingTeacher;
    }

    public String getTrainingObject() {
        return trainingObject;
    }

    public void setTrainingObject(String trainingObject) {
        this.trainingObject = trainingObject;
    }

    public String getTrainingTime() {
        return trainingTime;
    }

    public void setTrainingTime(String trainingTime) {
        this.trainingTime = trainingTime;
    }

    public String getTrainingHours() {
        return trainingHours;
    }

    public void setTrainingHours(String trainingHours) {
        this.trainingHours = trainingHours;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
