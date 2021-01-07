package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;

/**
 * <b>功能：</b>TR_USER_ROLE UserRoleEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-11-07 <br>
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 * <p>字段列表：</p>
 * <li>userId -> user_id</li>
 * <li>roleId -> role_id</li>
 * @author comments created by Lee Kwanho
 * date 2018-08-16
 */

public class UserRoleEO extends BaseEntity {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
