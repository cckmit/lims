package com.adc.da.sys.entity;

import java.io.Serializable;

import com.adc.da.base.entity.TreeEntity;

/**
 * 组织机构实体类
 * <p>
 * <p>字段列表：</p>
 * <li>id -> id</li>
 * <li>name -> org_name</li>
 * <li>orgDesc -> org_desc</li>
 * <li>orgCode -> org_code</li>
 * <li>orgType -> org_type</li>
 * <li>parentIds -> parent_ids</li>
 * <li>parentId -> parent_id</li>
 * <li>isShow -> is_show</li>
 * <li>delFlag -> del_flag</li>
 * <li>remarks -> remarks</li>
 *
 * @author 蔡建军
 * @author comments created by Lee Kwanho
 * date 2018-08-17
 * @see com.adc.da.sys.controller.OrgEORestController
 */
public class OrgEO extends TreeEntity implements Serializable {

    private static final long serialVersionUID = 2497292638985614077L;

    /**
     * 组织描述
     */
    private String orgDesc;

    /**
     * 组织码
     */
    private String orgCode;

    /**
     * 组织类型
     */
    private String orgType;

    /**
     * 是否显示
     */
    private Integer isShow;

    /**
     * 描述_保留字段
     */
    private String remarks;

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
