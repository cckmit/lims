package com.adc.da.sys.vo;

/**
 * 菜单对应VO
 *
 * @author comments created by Lee Kwanho
 * date 2018-08-17
 * @see com.adc.da.sys.controller.MenuEOController
 */
public class MenuVO extends TreeVO<MenuVO> {

    /**
     * 菜单对应网页
     */
    private String href;

    /**
     * 图标
     */
    private String icon;

    /**
     * 序列
     */
    private String sort;

    /**
     * 权限
     */
    private String permission;

    /**
     * 描述，标记
     */
    private String remarks;

    /**
     * 属于
     */
    private Integer belong;

    /**
     * 展示
     */
    private Integer isShow;

    /**
     * 扩展字段
     */

    private String extInfo;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getBelong() {
        return belong;
    }

    public void setBelong(Integer belong) {
        this.belong = belong;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

}