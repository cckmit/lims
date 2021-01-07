package com.adc.da.pc_announcement.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * <b>功能：</b>ANN_PLAN_ATTACH AnnPlanAttachEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-12-30 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
//记录计划生成委托单的记录
public class AnnPlanAttachEO extends BaseEntity {

    @ApiModelProperty("委托单创建时间")
    private String annCreateTime;
    @ApiModelProperty("委托单创建人")
    private String annCreateBy;
    @ApiModelProperty("委托单的相对路径")
    private String annRelativePath;
    @ApiModelProperty("委托单的绝对路径")
    private String annAbsolutePath;
    @ApiModelProperty("委托单名称")
    private String annFileName;
    @ApiModelProperty("计划id")
    private String  annPlId;
    @ApiModelProperty("ID ")
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>annCreateTime -> ann_create_time</li>
     * <li>annCreateBy -> ann_create_by</li>
     * <li>annRelativePath -> ann_relative_path</li>
     * <li>annAbsolutePath -> ann_absolute_path</li>
     * <li>annFileName -> ann_file_name</li>
     * <li> annPlId ->  ann_pl_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) { return null; }
        switch (fieldName) {
            case "annCreateTime": return "ann_create_time";
            case "annCreateBy": return "ann_create_by";
            case "annRelativePath": return "ann_relative_path";
            case "annAbsolutePath": return "ann_absolute_path";
            case "annFileName": return "ann_file_name";
            case " annPlId": return " ann_pl_id";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>ann_create_time -> annCreateTime</li>
     * <li>ann_create_by -> annCreateBy</li>
     * <li>ann_relative_path -> annRelativePath</li>
     * <li>ann_absolute_path -> annAbsolutePath</li>
     * <li>ann_file_name -> annFileName</li>
     * <li> ann_pl_id ->  annPlId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) { return null; }
        switch (columnName) {
            case "ann_create_time": return "annCreateTime";
            case "ann_create_by": return "annCreateBy";
            case "ann_relative_path": return "annRelativePath";
            case "ann_absolute_path": return "annAbsolutePath";
            case "ann_file_name": return "annFileName";
            case " ann_pl_id": return " annPlId";
            case "id": return "id";
            default: return null;
        }
    }
    
    public String getAnnCreateTime() {
        return this.annCreateTime;
    }

    public void setAnnCreateTime(String annCreateTime) {
        this.annCreateTime = annCreateTime;
    }

    public String getAnnCreateBy() {
        return this.annCreateBy;
    }

    public void setAnnCreateBy(String annCreateBy) {
        this.annCreateBy = annCreateBy;
    }

    public String getAnnRelativePath() {
        return this.annRelativePath;
    }

    public void setAnnRelativePath(String annRelativePath) {
        this.annRelativePath = annRelativePath;
    }

    public String getAnnAbsolutePath() {
        return this.annAbsolutePath;
    }

    public void setAnnAbsolutePath(String annAbsolutePath) {
        this.annAbsolutePath = annAbsolutePath;
    }

    public String getAnnFileName() {
        return this.annFileName;
    }

    public void setAnnFileName(String annFileName) {
        this.annFileName = annFileName;
    }

    public String getAnnPlId() {
        return annPlId;
    }

    public void setAnnPlId(String annPlId) {
        this.annPlId = annPlId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
