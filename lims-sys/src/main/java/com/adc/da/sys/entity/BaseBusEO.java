package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class BaseBusEO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7687606609651598174L;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/22 16:14
     **/
    @ApiModelProperty("id")
    private String id;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/22 16:14
     **/
    @ApiModelProperty("具体业务主键id")
    private String businessId;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/22 16:16
     **/
    @ApiModelProperty("具体业务的标题")
    private String title;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/22 16:16
     **/
    @ApiModelProperty("具体业务类型")
    private String businessType;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/22 16:17
     **/
    @ApiModelProperty("创建人id")
    private String createBy;
    /*
     * @Author syxy_zhangyinghui
     * @Date 2019/8/22 16:17
     **/
    @ApiModelProperty("创建时间")
    private String createTime;

    /**
     * 0:为收藏   1：以收藏
     */
    @ApiModelProperty("收藏状态")
    private String collectionStatus = "0";

    public BaseBusEO(){

    }

    public BaseBusEO(String businessId, String businessType, String createBy, String createTime, String title){
        this.businessId = businessId;
        this.businessType = businessType;
        this.createBy = createBy;
        this.createTime = createTime;
        this.title = title;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(String collectionStatus) {
        this.collectionStatus = collectionStatus;
    }
}
