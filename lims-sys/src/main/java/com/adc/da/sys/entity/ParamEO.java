package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;

import java.io.Serializable;

/*
 * @Author syxy_zhangyinghui
 * @Description 表名【TS_PARAM】 功能【系统参数表】
 * @Date 15:23 2019/8/2
 **/
public class ParamEO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 4673568785545510758L;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【主键id】
     * @Date 16:19 2019/8/2
     **/
    private String id;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【创建人用户id】
     * @Date 16:19 2019/8/2
     **/
    private String usId;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【创建人的组织机构id】
     * @Date 16:19 2019/8/2
     **/
    private String orgId;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【创建时间】
     * @Date 16:20 2019/8/2
     **/
    private String createDate;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【参数代码】
     * @Date 16:20 2019/8/2
     **/
    private String paramCode;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【参数名称】
     * @Date 16:20 2019/8/2
     **/
    private String paramName;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【参数值】
     * @Date 16:20 2019/8/2
     **/
    private String paramValue;

    /*
     * @Author syxy_zhangyinghui
     * @Description 【删除状态】
     * @Date 16:20 2019/8/2
     **/
    private String del;
    /*
     * @Author syxy_zhangyinghui
     * @Description 【参数类型 首页LOGO、文字、首页图片、系统LOGO】
     * @Date 10:11 2019/8/12
     **/
    private String paramType;
    
    /**
     * 图片路径
     */
    private String picPath;
    /**
     * 图片名称
     */
    private String attachName;
    
    
    
    public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsId() {
        return usId;
    }

    public void setUsId(String usId) {
        this.usId = usId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }
}
