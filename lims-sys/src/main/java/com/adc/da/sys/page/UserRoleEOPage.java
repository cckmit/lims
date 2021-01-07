package com.adc.da.sys.page;

import com.adc.da.base.page.BasePage;

/**
 * <b>功能：</b>TR_USER_ROLE UserRoleEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-11-07 <br>
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 */
public class UserRoleEOPage extends BasePage {

    private String userId;

    private String userIdOperator = "=";

    private String roleId;

    private String roleIdOperator = "=";

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIdOperator() {
        return this.userIdOperator;
    }

    public void setUserIdOperator(String userIdOperator) {
        this.userIdOperator = userIdOperator;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleIdOperator() {
        return this.roleIdOperator;
    }

    public void setRoleIdOperator(String roleIdOperator) {
        this.roleIdOperator = roleIdOperator;
    }

}
