package com.adc.da.line.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * <b>功能：</b>BM_LINE_CASE BmLineCaseEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-16 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class BmLineCaseEO extends BaseEntity {

    private String id;
    private Integer delFlag;
    private String createTime;
    private String createBy;
    @ApiModelProperty("创建人")
    private String createByName;
    private String updateTime;
    private String updateBy;
    @ApiModelProperty("路线编码")
    private String lineCode;
    @ApiModelProperty("路线名称")
    private String lineName;
    @ApiModelProperty("路线描述")
    private String lineDesc;
    @ApiModelProperty("路线详情")
    private List<BmLineCaseInfoEO> bmLineCaseInfoEOList;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>delFlag -> del_flag</li>
     * <li>createTime -> create_time</li>
     * <li>createBy -> create_by</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>lineCode -> line_code</li>
     * <li>lineName -> line_name</li>
     * <li>lineDesc -> line_desc</li>
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
            case "lineCode": return "line_code";
            case "lineName": return "line_name";
            case "lineDesc": return "line_desc";
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
     * <li>line_code -> lineCode</li>
     * <li>line_name -> lineName</li>
     * <li>line_desc -> lineDesc</li>
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
            case "line_code": return "lineCode";
            case "line_name": return "lineName";
            case "line_desc": return "lineDesc";
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

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
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
    public String getLineCode() {
        return this.lineCode;
    }

    /**  **/
    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    /**  **/
    public String getLineName() {
        return this.lineName;
    }

    /**  **/
    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    /**  **/
    public String getLineDesc() {
        return this.lineDesc;
    }

    /**  **/
    public void setLineDesc(String lineDesc) {
        this.lineDesc = lineDesc;
    }

    public List<BmLineCaseInfoEO> getBmLineCaseInfoEOList() {
        return bmLineCaseInfoEOList;
    }

    public void setBmLineCaseInfoEOList(List<BmLineCaseInfoEO> bmLineCaseInfoEOList) {
        this.bmLineCaseInfoEOList = bmLineCaseInfoEOList;
    }
}
