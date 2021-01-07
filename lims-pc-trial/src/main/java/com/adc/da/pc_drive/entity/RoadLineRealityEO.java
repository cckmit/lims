package com.adc.da.pc_drive.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * <b>功能：</b>DRIVING_ROAD_LINE_REALITY RoadLineRealityEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2020-11-05 <br>
 */
public class RoadLineRealityEO extends BaseEntity {
    @ApiModelProperty("")
    private String id;
    @ApiModelProperty("")
    private String driverRecordId;
    @ApiModelProperty("")
    private String delFlag;
    //类别
    @ApiModelProperty("类别")
    private String drType;
    //试验工况
    @ApiModelProperty("试验工况")
    private String testConditions;
    @ApiModelProperty("")
    private String endTime;
    //起始时刻
    @ApiModelProperty("")
    private String startLine;
    //结束时刻
    @ApiModelProperty("")
    private String endLine;
    //本工况试验里程累加（km）
    @ApiModelProperty("本工况试验总里程")
    private String milePlus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriverRecordId() {
        return driverRecordId;
    }

    public void setDriverRecordId(String driverRecordId) {
        this.driverRecordId = driverRecordId;
    }


    public String getDrType() {
        return drType;
    }

    public void setDrType(String drType) {
        this.drType = drType;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getTestConditions() {
        return testConditions;
    }

    public void setTestConditions(String testConditions) {
        this.testConditions = testConditions;
    }


    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMilePlus() {
        return milePlus;
    }

    public void setMilePlus(String milePlus) {
        this.milePlus = milePlus;
    }

    public String getEndLine() {
        return endLine;
    }

    public void setEndLine(String endLine) {
        this.endLine = endLine;
    }

    public String getStartLine() {
        return startLine;
    }

    public void setStartLine(String startLine) {
        this.startLine = startLine;
    }
}
