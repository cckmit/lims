package com.adc.da.login.vo;

import com.adc.da.base.entity.BaseEntity;

/**
 * <b>功能：</b>LoginVO<br>
 * <b>作者：</b>Alex<br>
 * <b>日期：</b> 2018-7-31 <br>
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 */
public class LoginVO extends BaseEntity {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */

    private String password;

    /**
     * 验证码
     */
    private String verifyCode;

    /**
     * 设备ID，用于移动端推送
     */
    private String clientId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

}