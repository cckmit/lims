package com.adc.da.login.entity;

import java.io.Serializable;
import java.util.Date;

public class OnlineUserEO implements Serializable {
    
    private static final long serialVersionUID = 5949014199725915501L;
    
    private String ip;
    
    private String account;
    
    private Date loginTime;
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getAccount() {
        return account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }
    
    public Date getLoginTime() {
        return loginTime;
    }
    
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}