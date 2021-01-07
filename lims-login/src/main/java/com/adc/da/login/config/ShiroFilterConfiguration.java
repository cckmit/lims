package com.adc.da.login.config;

import com.adc.da.login.security.AdcFormAuthenticationFilter;
import com.adc.da.util.constant.GlobalConfig;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 权限配置
 * 设置各连接的权限需求
 *
 */
@Configuration
public class ShiroFilterConfiguration {

    private static final String LOGIN = "/login";

    /**
     * 认证用户，需要登录才能使用的接口
     */
    private static final String AUTHC = "authc";

    /**
     * 匿名用户，无需登录
     */
    private static final String ANON = "anon";

    @Autowired
    private Environment env;

    /**
     * 初始化，设置adminPath和restPath
     * 参数在 application.properties 下
     * 预设 adminPath = /a
     * 预设 restPath =/api
     */
    @PostConstruct
    public void init() {
        GlobalConfig.setAdminPath(env.getProperty("adminPath"));
        GlobalConfig.setRestApiPath(env.getProperty("restPath"));
    }

    /**
     * 设置过滤器
     *
     * @param securityManager 必须参数
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        String adminPath = GlobalConfig.getAdminPath();
        String restPath = GlobalConfig.getRestApiPath();

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        /* 必须参数 */
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /* 设置登录页 ，非必须属性*/
        shiroFilterFactoryBean.setLoginUrl(adminPath + LOGIN);
        /* 登陆成功跳转页，可选参数 */
        shiroFilterFactoryBean.setSuccessUrl(adminPath);

        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put(AUTHC, new AdcFormAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put(restPath + LOGIN, ANON);

        /* 登出不需认证 */
        filterChainDefinitionMap.put(restPath + "/logout", ANON);

        /* 验证码，不需要认证 */
        filterChainDefinitionMap.put(restPath + "/verifyCode", ANON);

        /* 验证码预校验，不需要认证 */
        filterChainDefinitionMap.put(restPath + "/judgeVerify", ANON);

        /* 用户信息不用认证 */
        filterChainDefinitionMap.put(restPath + "/userInfo", ANON);
        
        /* 在线用户列表不用验证 */
        filterChainDefinitionMap.put(restPath + "/onlineUser", ANON);

        /* 用户所属菜单信息不需认证 */
        filterChainDefinitionMap.put(restPath + "/userMenu", ANON);
        filterChainDefinitionMap.put(restPath + "/updateUserInfo", ANON);
        filterChainDefinitionMap.put(restPath + "/updatePassword", ANON);
        filterChainDefinitionMap.put(restPath + "/user/supplierRegistry/*", ANON);

        /* 全api一般需要登录才能使用 */
        filterChainDefinitionMap.put("/api/**", AUTHC);

        /* static 不需登录 */
        filterChainDefinitionMap.put("/static/**", ANON);

        /* userfiles 用户文件 不需登录 */
        filterChainDefinitionMap.put("/userfiles/**", ANON);
        filterChainDefinitionMap.put(adminPath + LOGIN, AUTHC);
        filterChainDefinitionMap.put(adminPath + "/logout", "logout");
        filterChainDefinitionMap.put(adminPath + "/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

}
