package com.adc.da.login.security;

import com.adc.da.login.LoginType;

/**
 * 用户和密码（包含验证码）令牌类
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

    private String captcha;

    private LoginType type;

    public UsernamePasswordToken() {
        super();
    }

    public UsernamePasswordToken(String username, char[] password) {
        super(username, password);
    }

    /**
     * 账号密码登录
     */
    public UsernamePasswordToken(String username, char[] password, String captcha) {
        super(username, password);
        this.captcha = captcha;
        this.type = LoginType.PASSWORD;
    }

    /**
     * 免密登录
     */
    public UsernamePasswordToken(String username,String captcha) {
        super(username, "");
        this.captcha = captcha;
        this.type = LoginType.NOPASSWD;
    }

    public UsernamePasswordToken(String username, char[] password, boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public LoginType getType() {
        return type;
    }

    public void setType(LoginType type) {
        this.type = type;
    }
}