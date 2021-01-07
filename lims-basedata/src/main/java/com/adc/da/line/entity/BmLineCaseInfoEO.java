package com.adc.da.line.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


/**
 * <b>功能：</b>BM_LINE_CASE_INFO BmLineCaseInfoEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-10-16 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class BmLineCaseInfoEO extends BaseEntity {

    private String id;
    private Integer delFlag;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    @ApiModelProperty("第几天")
    private String dayNo;
    @ApiModelProperty("路线起点")
    private String lineStart;
    @ApiModelProperty("路线终点")
    private String lineEnd;
    @ApiModelProperty("路线类型")
    private String lineType;
    @ApiModelProperty("一天行驶里程数")
    private String oneDayKm;
    @ApiModelProperty("备注")
    private String remarks;
    @ApiModelProperty("驾驶天数")
    private String driveDays;
    @ApiModelProperty("循环数")
    private String lineCycle;
    @ApiModelProperty("路线案例ID")
    private String caseId;
    @ApiModelProperty("行驶次序")
    private Integer driveOrder;
    @ApiModelProperty("几天一循环")
    private String cycleNumber;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>delFlag -> del_flag</li>
     * <li>createTime -> create_time</li>
     * <li>createBy -> create_by</li>
     * <li>updateTime -> update_time</li>
     * <li>updateBy -> update_by</li>
     * <li>dayNo -> day_no</li>
     * <li>lineStart -> line_start</li>
     * <li>lineEnd -> line_end</li>
     * <li>lineType -> line_type</li>
     * <li>oneDayKm -> one_day_km</li>
     * <li>remarks -> remarks</li>
     * <li>driveDays -> drive_days</li>
     * <li>lineCycle -> line_cycle</li>
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
            case "dayNo": return "day_no";
            case "lineStart": return "line_start";
            case "lineEnd": return "line_end";
            case "lineType": return "line_type";
            case "oneDayKm": return "one_day_km";
            case "remarks": return "remarks";
            case "driveDays": return "drive_days";
            case "lineCycle": return "line_cycle";
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
     * <li>day_no -> dayNo</li>
     * <li>line_start -> lineStart</li>
     * <li>line_end -> lineEnd</li>
     * <li>line_type -> lineType</li>
     * <li>one_day_km -> oneDayKm</li>
     * <li>remarks -> remarks</li>
     * <li>drive_days -> driveDays</li>
     * <li>line_cycle -> lineCycle</li>
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
            case "day_no": return "dayNo";
            case "line_start": return "lineStart";
            case "line_end": return "lineEnd";
            case "line_type": return "lineType";
            case "one_day_km": return "oneDayKm";
            case "remarks": return "remarks";
            case "drive_days": return "driveDays";
            case "line_cycle": return "lineCycle";
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
    public String getDayNo() {
        return this.dayNo;
    }

    /**  **/
    public void setDayNo(String dayNo) {
        this.dayNo = dayNo;
    }

    /**  **/
    public String getLineStart() {
        return this.lineStart;
    }

    /**  **/
    public void setLineStart(String lineStart) {
        this.lineStart = lineStart;
    }

    /**  **/
    public String getLineEnd() {
        return this.lineEnd;
    }

    /**  **/
    public void setLineEnd(String lineEnd) {
        this.lineEnd = lineEnd;
    }

    /**  **/
    public String getLineType() {
        return this.lineType;
    }

    /**  **/
    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    /**  **/
    public String getOneDayKm() {
        return this.oneDayKm;
    }

    /**  **/
    public void setOneDayKm(String oneDayKm) {
        this.oneDayKm = oneDayKm;
    }

    /**  **/
    public String getRemarks() {
        return this.remarks;
    }

    /**  **/
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**  **/
    public String getDriveDays() {
        return this.driveDays;
    }

    /**  **/
    public void setDriveDays(String driveDays) {
        this.driveDays = driveDays;
    }

    /**  **/
    public String getLineCycle() {
        return this.lineCycle;
    }

    /**  **/
    public void setLineCycle(String lineCycle) {
        this.lineCycle = lineCycle;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public Integer getDriveOrder() {
        return driveOrder;
    }

    public void setDriveOrder(Integer driveOrder) {
        this.driveOrder = driveOrder;
    }

    public String getCycleNumber() {
        return cycleNumber;
    }

    public void setCycleNumber(String cycleNumber) {
        this.cycleNumber = cycleNumber;
    }
}
