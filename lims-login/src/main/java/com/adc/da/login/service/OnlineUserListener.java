package com.adc.da.login.service;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.adc.da.login.entity.OnlineUserEO;

/**
 * 用于记录在线用户
 */
public class OnlineUserListener implements HttpSessionBindingListener , Serializable {
    
    private OnlineUserEO onlineUser;
    
    private static final Map<String, OnlineUserEO> ONLINE_MAP = new ConcurrentHashMap<>();
    
    /**
     * 初始化
     */
    public OnlineUserListener() {
    }
    
    public OnlineUserListener(OnlineUserEO onlineUser) {
        
        this.onlineUser = onlineUser;
    }
    
    /**
     * 用户上线
     */
    @Override
    public void valueBound(HttpSessionBindingEvent e) {
        HttpSession session = e.getSession();
        // 把用户名放入在线列表
        ONLINE_MAP.put(session.getId(), onlineUser);
    }
    
    /**
     * 用户下线
     */
    @Override
    public void valueUnbound(HttpSessionBindingEvent e) {
        HttpSession session = e.getSession();
        // 把用户名移除在线列表
        ONLINE_MAP.remove(session.getId());
    }
    
    public static Map<String, OnlineUserEO> getOnlineMap() {
        return ONLINE_MAP;
    }
    
}
