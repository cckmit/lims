package com.adc.da.sys.vo;

import java.util.ArrayList;
import java.util.List;

import com.adc.da.sys.entity.MenuEO;

/**
 * 角色对应VO
 *
 * @author comments created by Lee Kwanho
 * date 2018-08-17
 * @see com.adc.da.sys.controller.RoleEOController
 */
public class RoleVO {
	/**
	 * 通用查询
	 */
	private String searchPhrase;
	
    /**
     * 角色id
     */
    private String rid;

    /**
     * 角色名称
     */
    private String rname;

    /**
     * 角色描述
     */
    private String rdesc;
    /**
     * 角色bianma 
     */
    private String rcode;

    /**
     * 删除标记
     */
    private int enabled;

    /**
     * 用户数
     */
    private int usercount;

    /**
     * 属于与否
     */
    private int belong;

    // 扩展字段
    private String extInfo;

    private String extInfo2;

    private String extInfo3;

    private String extInfo4;

    private String extInfo5;

    private List<MenuEO> menus = new ArrayList<>();

    private List<String> menusstr = new ArrayList<>();

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public int getUsercount() {
        return usercount;
    }

    public void setUsercount(int usercount) {
        this.usercount = usercount;
    }

    public int getBelong() {
        return belong;
    }

    public void setBelong(int belong) {
        this.belong = belong;
    }

    public String getExtInfo() {
        return extInfo;
    }

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

    public List<MenuEO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuEO> menus) {
        this.menus = menus;
    }

    public List<String> getMenusstr() {
        return menusstr;
    }

    public void setMenusstr(List<String> menusstr) {
        this.menusstr = menusstr;
    }

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	public String getSearchPhrase() {
		return searchPhrase;
	}

	public void setSearchPhrase(String searchPhrase) {
		this.searchPhrase = searchPhrase;
	}
}
