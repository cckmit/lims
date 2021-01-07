//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.adc.da.login.config;

import com.adc.da.login.security.AdcFormAuthenticationFilter;
import com.adc.da.util.constant.GlobalConfig;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ShiroFilterConfiguration {
    private static final String LOGIN = "/login";
    private static final String AUTHC = "anon";
    private static final String ANON = "anon";
    private static final String LOGOUT = "logout";
    private static final String USER = "user";
    @Autowired
    private Environment env;

    public ShiroFilterConfiguration() {
    }

    @PostConstruct
    public void init() {
        GlobalConfig.setAdminPath(this.env.getProperty("adminPath"));
        GlobalConfig.setRestApiPath(this.env.getProperty("restPath"));
    }

    @Bean(
            name = {"shiroFilter"}
    )
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        String adminPath = GlobalConfig.getAdminPath();
        String restPath = GlobalConfig.getRestApiPath();
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl(adminPath + "/login");
        shiroFilterFactoryBean.setSuccessUrl(adminPath);
        Map<String, Filter> filterMap = new LinkedHashMap();
        filterMap.put("authc", new AdcFormAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap();
        filterChainDefinitionMap.put(restPath + "/login", ANON);
        filterChainDefinitionMap.put(restPath + "/logout", ANON);
        filterChainDefinitionMap.put(restPath + "/api/judgeVerify", ANON);
        filterChainDefinitionMap.put(restPath + "/verifyCode", ANON);
        filterChainDefinitionMap.put(restPath + "/userInfo", ANON);
        filterChainDefinitionMap.put(restPath + "/onlineUser", ANON);
        filterChainDefinitionMap.put(restPath + "/userMenu", ANON);
        filterChainDefinitionMap.put(restPath + "/updateUserInfo", ANON);
        filterChainDefinitionMap.put(restPath + "/updatePassword", ANON);
        filterChainDefinitionMap.put(restPath + "/user/supplierRegistry/*", ANON);
        filterChainDefinitionMap.put("/api/**", AUTHC);
        filterChainDefinitionMap.put("/static/**", ANON);
        filterChainDefinitionMap.put("/userfiles/**", ANON);
        filterChainDefinitionMap.put(adminPath + "/login", AUTHC);
        filterChainDefinitionMap.put(adminPath + "/logout", LOGOUT);
        filterChainDefinitionMap.put(adminPath + "/**", USER);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
