package com.adc.da.sys.vo;

/**
 * 组织对应VO
 *
 * @author 蔡建军
 * @author comments created by Lee Kwanho
 * date 2018-08-17
 * @see com.adc.da.sys.controller.OrgEORestController
 */
public class OrgVO extends TreeVO<OrgVO> {

    /**
     * 组织描述
     */
    private String orgDesc;

    /**
     * 组织编码
     */
    private String orgCode;

    /**
     * 组织类型
     */
    private String orgType;

    /**
     * 显示与否
     */
    private Integer isShow;

    /**
     * 暂未使用，保留字段
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
