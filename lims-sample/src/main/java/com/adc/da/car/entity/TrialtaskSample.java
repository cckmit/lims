package com.adc.da.car.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/10/11 16:25
 * @description：
 */
public class TrialtaskSample extends BaseEntity {

    private static final long serialVersionUID = -8030808266139037000L;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 17:00
     **/
    @ApiModelProperty("主键id")
    private String id;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 17:01
     **/
    @ApiModelProperty("试验任务书id")
    private String trialtaskId;

    @ApiModelProperty("试验项目id")
    private String itemsId;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 17:04
     **/
    @ApiModelProperty("样品id")
    private String sampleId;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 17:04
     * car-整车   part-零部件
     **/
    @ApiModelProperty("样品类型")
    private String sampleType;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 17:05
     **/
    @ApiModelProperty("负责台架id")
    private String scaffoldingId;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 17:05
     **/
    @ApiModelProperty("零部件/发动机号")    //针对零部件
    private String sampleEngineType;

    @ApiModelProperty("底盘号")          //针对整车
    private String sampleChassisNumber;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 17:05
     **/
    @ApiModelProperty("样品生产日期")
    private String sampleProduceTime;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/19 17:05
     **/
    @ApiModelProperty("样品编号")
    private String sampleNo;

    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/20 10:39
     * 负责台架信息  ----关联组织机构
     **/


    @ApiModelProperty("台架名称")
    private String scaffoldingName;

    @ApiModelProperty("备注")
    private String remark;


    public String getScaffoldingName() {
        return scaffoldingName;
    }

    public void setScaffoldingName(String scaffoldingName) {
        this.scaffoldingName = scaffoldingName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrialtaskId() {
        return trialtaskId;
    }

    public void setTrialtaskId(String trialtaskId) {
        this.trialtaskId = trialtaskId;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public String getScaffoldingId() {
        return scaffoldingId;
    }

    public void setScaffoldingId(String scaffoldingId) {
        this.scaffoldingId = scaffoldingId;
    }

    public String getSampleEngineType() {
        return sampleEngineType;
    }

    public void setSampleEngineType(String sampleEngineType) {
        this.sampleEngineType = sampleEngineType;
    }

/*
    public String getPartEngineNo() {
        return partEngineNo;
    }

    public void setPartEngineNo(String partEngineNo) {
        this.partEngineNo = partEngineNo;
    }
*/

    public String getSampleChassisNumber() {
        return sampleChassisNumber;
    }

    public void setSampleChassisNumber(String sampleChassisNumber) {
        this.sampleChassisNumber = sampleChassisNumber;
    }

    public String getSampleProduceTime() {
        return sampleProduceTime;
    }

    public void setSampleProduceTime(String sampleProduceTime) {
        this.sampleProduceTime = sampleProduceTime;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getItemsId() {
        return itemsId;
    }

    public void setItemsId(String itemsId) {
        this.itemsId = itemsId;
    }
}
