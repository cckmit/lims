package com.adc.da.pc_budget_reimbursement.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>PC_TRIAL_WORKDAY PcTrialWorkdayEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-12 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcTrialWorkdayEO extends BaseEntity {

    private String id;
    private Integer delFlag;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    @ApiModelProperty("报销申请单ID")
    private String rId;
    @ApiModelProperty("用户ID")
    private String userUsid;
    @ApiModelProperty("姓名")
    private String userName;
    @ApiModelProperty("主要目的地")
    private String destination;
    @ApiModelProperty("实验开始时间")
    private String trialStartTime;
    @ApiModelProperty("实验结束时间")
    private String trialEndTime;
    @ApiModelProperty("天数")
    private String dayNumber;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>delFlag -> del_flag</li>
     * <li>createTime -> create_time</li>
     * <li>createBy -> create_by</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>rId -> r_id</li>
     * <li>userUsid -> user_usid</li>
     * <li>userName -> user_name</li>
     * <li>destination -> destination</li>
     * <li>trialStartTime -> trial_start_time</li>
     * <li>trialEndTime -> trial_end_time</li>
     * <li>dayNumber -> day_number</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "delFlag": return "del_flag";
            case "createTime": return "create_time";
            case "createBy": return "create_by";
            case "updateTime": return "update_time";
            case "updateBy": return "update_by";
            case "rId": return "r_id";
            case "userUsid": return "user_usid";
            case "userName": return "user_name";
            case "destination": return "destination";
            case "trialStartTime": return "trial_start_time";
            case "trialEndTime": return "trial_end_time";
            case "dayNumber": return "day_number";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>del_flag -> delFlag</li>
     * <li>create_time -> createTime</li>
     * <li>create_by -> createBy</li>
     * <li>update_time -> updateTime</li>
     * <li>update_by -> updateBy</li>
     * <li>r_id -> rId</li>
     * <li>user_usid -> userUsid</li>
     * <li>user_name -> userName</li>
     * <li>destination -> destination</li>
     * <li>trial_start_time -> trialStartTime</li>
     * <li>trial_end_time -> trialEndTime</li>
     * <li>day_number -> dayNumber</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "del_flag": return "delFlag";
            case "create_time": return "createTime";
            case "create_by": return "createBy";
            case "update_time": return "updateTime";
            case "update_by": return "updateBy";
            case "r_id": return "rId";
            case "user_usid": return "userUsid";
            case "user_name": return "userName";
            case "destination": return "destination";
            case "trial_start_time": return "trialStartTime";
            case "trial_end_time": return "trialEndTime";
            case "day_number": return "dayNumber";
            default: return null;
        }
    }
    
    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /**  **/
    public Integer getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**  **/
    public String getCreateTime() {
        return this.createTime;
    }

    /**  **/
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**  **/
    public String getCreateBy() {
        return this.createBy;
    }

    /**  **/
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**  **/
    public String getUpdateTime() {
        return this.updateTime;
    }

    /**  **/
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**  **/
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**  **/
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**  **/
    public String getRId() {
        return this.rId;
    }

    /**  **/
    public void setRId(String rId) {
        this.rId = rId;
    }

    /**  **/
    public String getUserUsid() {
        return this.userUsid;
    }

    /**  **/
    public void setUserUsid(String userUsid) {
        this.userUsid = userUsid;
    }

    /**  **/
    public String getUserName() {
        return this.userName;
    }

    /**  **/
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**  **/
    public String getDestination() {
        return this.destination;
    }

    /**  **/
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**  **/
    public String getTrialStartTime() {
        return this.trialStartTime;
    }

    /**  **/
    public void setTrialStartTime(String trialStartTime) {
        this.trialStartTime = trialStartTime;
    }

    /**  **/
    public String getTrialEndTime() {
        return this.trialEndTime;
    }

    /**  **/
    public void setTrialEndTime(String trialEndTime) {
        this.trialEndTime = trialEndTime;
    }

    /**  **/
    public String getDayNumber() {
        return this.dayNumber;
    }

    /**  **/
    public void setDayNumber(String dayNumber) {
        this.dayNumber = dayNumber;
    }

}
