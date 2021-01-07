package com.adc.da.sys.vo;

import io.swagger.annotations.ApiModelProperty;

public class BaseBusVO {

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

}
