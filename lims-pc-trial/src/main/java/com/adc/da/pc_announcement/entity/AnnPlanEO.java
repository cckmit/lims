package com.adc.da.pc_announcement.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * <b>功能：</b>ANN_PLAN AnnPlanEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-12-23 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class AnnPlanEO extends BaseEntity {

    @ApiModelProperty("删除标记")
    private String delFlag;
    @ApiModelProperty("修改时间")
    private String plUpdateTime;
    @ApiModelProperty("修改人ID")
    private String plUpdateBy;
    @ApiModelProperty("计划状态  0：未开始 1：进行中 2：已完成")
    private String plStatus;
    @ApiModelProperty("附件名称")
    private String plAttachmentName;
    @ApiModelProperty("附件ID")
    private String plAttachmentId;
    @ApiModelProperty("创建日期")
    private String plCreateDate;
    @ApiModelProperty("创建人姓名")
    private String plCreateName;
    @ApiModelProperty("创建人ID")
    private String plCreateBy;
    @ApiModelProperty("申报来源编号")
    private String plNo;
    @ApiModelProperty("计划来源")
    private String plSource;
    @ApiModelProperty("计划名称")
    private String plName;
    @ApiModelProperty("id")
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>delFlag -> del_flag</li>
     * <li>plUpdateTime -> pl_update_time</li>
     * <li>plUpdateBy -> pl_update_by</li>
     * <li>plStatus -> pl_status</li>
     * <li>plAttachmentName -> pl_attachment_name</li>
     * <li>plAttachmentId -> pl_attachment_id</li>
     * <li>plCreateDate -> pl_create_date</li>
     * <li>plCreateName -> pl_create_name</li>
     * <li>plCreateBy -> pl_create_by</li>
     * <li>plNo -> pl_no</li>
     * <li>plSource -> pl_source</li>
     * <li>plName -> pl_name</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) { return null; }
        switch (fieldName) {
            case "delFlag": return "del_flag";
            case "plUpdateTime": return "pl_update_time";
            case "plUpdateBy": return "pl_update_by";
            case "plStatus": return "pl_status";
            case "plAttachmentName": return "pl_attachment_name";
            case "plAttachmentId": return "pl_attachment_id";
            case "plCreateDate": return "pl_create_date";
            case "plCreateName": return "pl_create_name";
            case "plCreateBy": return "pl_create_by";
            case "plNo": return "pl_no";
            case "plSource": return "pl_source";
            case "plName": return "pl_name";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>del_flag -> delFlag</li>
     * <li>pl_update_time -> plUpdateTime</li>
     * <li>pl_update_by -> plUpdateBy</li>
     * <li>pl_status -> plStatus</li>
     * <li>pl_attachment_name -> plAttachmentName</li>
     * <li>pl_attachment_id -> plAttachmentId</li>
     * <li>pl_create_date -> plCreateDate</li>
     * <li>pl_create_name -> plCreateName</li>
     * <li>pl_create_by -> plCreateBy</li>
     * <li>pl_no -> plNo</li>
     * <li>pl_source -> plSource</li>
     * <li>pl_name -> plName</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) { return null; }
        switch (columnName) {
            case "del_flag": return "delFlag";
            case "pl_update_time": return "plUpdateTime";
            case "pl_update_by": return "plUpdateBy";
            case "pl_status": return "plStatus";
            case "pl_attachment_name": return "plAttachmentName";
            case "pl_attachment_id": return "plAttachmentId";
            case "pl_create_date": return "plCreateDate";
            case "pl_create_name": return "plCreateName";
            case "pl_create_by": return "plCreateBy";
            case "pl_no": return "plNo";
            case "pl_source": return "plSource";
            case "pl_name": return "plName";
            case "id": return "id";
            default: return null;
        }
    }
    
    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getPlUpdateTime() {
        return this.plUpdateTime;
    }

    public void setPlUpdateTime(String plUpdateTime) {
        this.plUpdateTime = plUpdateTime;
    }

    public String getPlUpdateBy() {
        return this.plUpdateBy;
    }

    public void setPlUpdateBy(String plUpdateBy) {
        this.plUpdateBy = plUpdateBy;
    }

    public String getPlStatus() {
        return this.plStatus;
    }

    public void setPlStatus(String plStatus) {
        this.plStatus = plStatus;
    }

    public String getPlAttachmentName() {
        return this.plAttachmentName;
    }

    public void setPlAttachmentName(String plAttachmentName) {
        this.plAttachmentName = plAttachmentName;
    }

    public String getPlAttachmentId() {
        return this.plAttachmentId;
    }

    public void setPlAttachmentId(String plAttachmentId) {
        this.plAttachmentId = plAttachmentId;
    }

    public String getPlCreateDate() {
        return this.plCreateDate;
    }

    public void setPlCreateDate(String plCreateDate) {
        this.plCreateDate = plCreateDate;
    }

    public String getPlCreateName() {
        return this.plCreateName;
    }

    public void setPlCreateName(String plCreateName) {
        this.plCreateName = plCreateName;
    }

    public String getPlCreateBy() {
        return this.plCreateBy;
    }

    public void setPlCreateBy(String plCreateBy) {
        this.plCreateBy = plCreateBy;
    }

    public String getPlNo() {
        return this.plNo;
    }

    public void setPlNo(String plNo) {
        this.plNo = plNo;
    }

    public String getPlSource() {
        return this.plSource;
    }

    public void setPlSource(String plSource) {
        this.plSource = plSource;
    }

    public String getPlName() {
        return this.plName;
    }

    public void setPlName(String plName) {
        this.plName = plName;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
