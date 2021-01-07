package com.adc.da.login.vo;

import java.util.List;

import com.adc.da.login.entity.OnlineUserEO;

public class OnlineUserVO {
    
    private List<OnlineUserEO> onlineUsers;
    
    private Integer total;
    
    public List<OnlineUserEO> getOnlineUsers() {
        return onlineUsers;
    }
    
    public void setOnlineUsers(List<OnlineUserEO> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }
    
    public Integer getTotal() {
        return total;
    }
    
    public void setTotal(Integer total) {
        this.total = total;
    }
    
}
