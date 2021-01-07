package com.adc.da.sys.entity;

import java.io.Serializable;

import com.adc.da.base.entity.TreeEntity;

/**
 * <b>功能：</b>TS_MENU MenuEOEntity<br>
 * <p>
 * <b>日期：</b> 2017-12-21 <br>
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 * <p>字段列表：</p>
 * <li>extInfo -> ext_info</li>
 * <li>sort -> sort</li>
 * <li>remarks -> remarks</li>
 * <li>permission -> permission</li>
 * <li>isShow -> is_show</li>
 * <li>icon -> icon</li>
 * <li>href -> href</li>
 * <li>parentIds -> parent_ids</li>
 * <li>parentId -> parent_id</li>
 * <li>name -> name</li>
 * <li>delFlag -> del_flag</li>
 * <li>id -> id</li>
 *
 * @author comments created by Lee Kwanho
 * date 2018-08-17
 * @see com.adc.da.sys.controller.MenuEOController
 */
public class MenuEO extends TreeEntity implements Serializable {
    private static final long serialVersionUID = 2497292638985614077L;

    /**
     * 路径，网页路径
     */
    private String href;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序字段
     */
    private String sort;

    /**
     * 是否显示
     */
    private Integer isShow;

    /**
     * 权限
     */
    private String permission;

    /**
     * 说明
     */
    private String remarks;

    /**
     * 以下为扩展字段-保留字段
     */
    private String extInfo;

    /**  **/
    public String getExtInfo() {
        return this.extInfo;
    }

    /**  **/
    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    /**  **/
    public String getSort() {
        return this.sort;
    }

    /**  **/
    public void setSort(String sort) {
        this.sort = sort;
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
    public String getPermission() {
        return this.permission;
    }

    /**  **/
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**  **/
    public Integer getIsShow() {
        return this.isShow;
    }

    /**  **/
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**  **/
    public String getIcon() {
        return this.icon;
    }

    /**  **/
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**  **/
    public String getHref() {
        return this.href;
    }

    /**  **/
    public void setHref(String href) {
        this.href = href;
    }

}
