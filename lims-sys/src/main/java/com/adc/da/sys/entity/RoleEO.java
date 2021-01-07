package com.adc.da.sys.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.adc.da.base.entity.BaseEntity;

/**
 * <b>功能：</b>TS_ROLE RoleEOEntity<br>
 * <p>
 * <b>日期：</b> 2017-12-21 <br>
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
 * <p>字段列表：</p>
 * <li>isDefault -> is_default</li>
 * <li>delFlag -> del_flag</li>
 * <li>dataScope -> data_scope</li>
 * <li>id -> id</li>
 * <li>extInfo -> ext_info</li>
 * <li>remarks -> remarks</li>
 * <li>name -> name</li>
 *
 * @author comments created by Lee Kwanho
 * date 2018-08-17
 * @see com.adc.da.sys.controller.RoleEOController
 */
public class RoleEO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1386892031737294731L;

    /**
     *默认字段
     */
    private Integer isDefault;

    /**
     * 删除标识
     */
    private Integer delFlag;

    /**
     * 角色用户数
     */
    private Integer dataScope;

    /**
     * 角色id
     */
    private String id;

    /**
     * 标识
     */
    private String remarks;

    /**
     * 角色名
     */
    private String name;
    
    /**
     * 角儿编码
     */
    private String roleCode;

    /* 以下为扩展字段 */

    /**
     * 拓展字段
     */
    private String extInfo;

    private String extInfo2;

    private String extInfo3;

    private String extInfo4;

    private String extInfo5;

    /**
     * 菜单列表
     */
    private List<MenuEO> menuEOList = new ArrayList<>();

    /**
     * 菜单id列表
     */
    private List<String> menuEOIdList = new ArrayList<>();

    /**  **/
    public Integer getIsDefault() {
        return this.isDefault;
    }

    /**  **/
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
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
    public Integer getDataScope() {
        return this.dataScope;
    }

    /**  **/
    public void setDataScope(Integer dataScope) {
        this.dataScope = dataScope;
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
    public String getExtInfo() {
        return this.extInfo;
    }

    /**  **/
    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public String getExtInfo2() {
        return extInfo2;
    }

    public void setExtInfo2(String extInfo2) {
        this.extInfo2 = extInfo2;
    }

    public String getExtInfo3() {
        return extInfo3;
    }

    public void setExtInfo3(String extInfo3) {
        this.extInfo3 = extInfo3;
    }

    public String getExtInfo4() {
        return extInfo4;
    }

    public void setExtInfo4(String extInfo4) {
        this.extInfo4 = extInfo4;
    }

    public String getExtInfo5() {
        return extInfo5;
    }

    public void setExtInfo5(String extInfo5) {
        this.extInfo5 = extInfo5;
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
    public String getName() {
        return this.name;
    }

    /**  **/
    public void setName(String name) {
        this.name = name;
    }

    public List<MenuEO> getMenuEOList() {
        return menuEOList;
    }

    public void setMenuEOList(List<MenuEO> menuEOList) {
        this.menuEOList = menuEOList;
    }

    public List<String> getMenuEOIdList() {
        return menuEOIdList;
    }

    public void setMenuEOIdList(List<String> menuEOIdList) {
        this.menuEOIdList = menuEOIdList;
    }

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}
