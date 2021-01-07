package com.adc.da.login.security;

import com.adc.da.login.NewHashedCredentialsMatcher;
import com.adc.da.login.util.CacheUtils;
import com.adc.da.sys.entity.UserEO;

import com.adc.da.login.security.exception.CaptchaException;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.utils.Encodes;
import com.adc.da.util.utils.PasswordUtils;
import com.adc.da.util.utils.SpringContextHolder;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统安全认证实现类
 */
@Service("systemAuthorizingRealm")
public class SystemAuthorizingRealm extends AuthorizingRealm {
    
    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(SystemAuthorizingRealm.class);
    
    /**
     * 验证码
     */
    private static final Object VERIFY_CODE = "VerifyCode";
    
    /**
     * 读取验证码模式配置，
     * 1为不开启，2为开启，3为三次输错用户名或密码才开启，
     * 默认为1
     * <p>
     * 若配置文件缺少该参数，将设置为1
     */
    @Value("${verifyCodeMode:1}")
    private int verifyCodeMode;
    
    /**
     * @see UserEOService
     */
    private UserEOService userService;
    
    /**
     * 若userService为空，获取userService
     *
     * @return
     */
    public UserEOService getUserService() {
        if (userService == null) {
            userService = SpringContextHolder.getBean(UserEOService.class);
        }
        return userService;
    }
    
    /**
     * 认证回调函数, 登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        if (verifyCodeMode == 2
            || (verifyCodeMode == 3 && isNeedValidCode(token.getUsername()))) {
            /* verifyCodeMode == 2 或
            如果登录失败超过3次需要验证码 */
            doVerifyCode(token);
        }
        UserEO user = getUserService().getUserByLoginNameNotDeleted(token.getUsername());
        if (user == null) {
            return null;
        }
        
        byte[] salt = Encodes.decodeHex(user.getPassword().substring(0, 16));
        return new SimpleAuthenticationInfo(new Principal(user), user.getPassword().substring(16),
            ByteSource.Util.bytes(salt), getName());
    }
    
    /**
     * 登录失败map
     */
    private static final String LOGIN_FAIL_MAP = "loginFailMap";
    
    /**
     * 10分钟内最大错误次数
     */
    @Value("${maxLoginErrorCount:3}")
    private int maxLoginErrorCount;
    
    /**
     * 判断是否需要验证验证码
     *
     * @param userName 用户名
     * @return
     */
    public boolean isNeedValidCode(String userName) {
        Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils.getErrorCache(LOGIN_FAIL_MAP);
        if (loginFailMap == null) {
            return false;
        }
        
        Integer loginFailNum = loginFailMap.get(userName);
        if (loginFailNum == null) {
            return false;
        }
        
        return loginFailNum >= maxLoginErrorCount;
    }
    
    
    /**
     * 登录失败次数增加一次
     *
     * @param userName 用户名
     */
    public void increaseLoginErrorCount(String userName) {
        Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils.getErrorCache(LOGIN_FAIL_MAP);
        if (loginFailMap == null) {
            loginFailMap = Maps.newHashMap();
            CacheUtils.putErrorCache(LOGIN_FAIL_MAP, loginFailMap);
        }
        
        Integer loginFailNum = loginFailMap.get(userName);
        if (loginFailNum == null) {
            loginFailNum = 1;
        } else {
            loginFailNum++;
        }
        
        loginFailMap.put(userName, loginFailNum);
    }
    
    
    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal) getAvailablePrincipal(principals);
        UserEO user = userService.getUserByLoginNameNotDeleted(principal.getLoginName());
        if (user != null) {
            try {
                return UserUtils.getAuthInfo();
            } catch (NumberFormatException e) {
                
                LOG.error("AuthorizationInfo NumberFormatException", e);
            } catch (Exception e) {
                LOG.error("AuthorizationInfo Exception", e);
                
            }
        } else {
            return null;
        }
        return null;
    }
    
    /**
     * 验证码验证
     */
    private void doVerifyCode(UsernamePasswordToken token) {
        Session session = SecurityUtils.getSubject().getSession();
        String verifyCode = (String) session.getAttribute(VERIFY_CODE);
        /*
         * 忽略大小写改用 equalsIgnoreCase
         * date 2018-08-29
         */
        if (token.getCaptcha() == null || !token.getCaptcha().equalsIgnoreCase(verifyCode)) {
            session.removeAttribute(VERIFY_CODE);
            throw new CaptchaException("验证码错误.");
        }
        // 登录成功也清空验证码
        session.removeAttribute(VERIFY_CODE);
    }
    
    /**
     * 设定密码校验的Hash算法与迭代次数,
     * 将 HASH_ALGORITHM 和 HASH_INTERATIONS 改为util中的参数
     * <p>
     * date 2018-09-06
     *
     * @author Lee Kwanho
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        NewHashedCredentialsMatcher matcher = new NewHashedCredentialsMatcher(PasswordUtils.HASH_ALGORITHM);
        matcher.setHashIterations(PasswordUtils.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }
    
    /**
     * 清空用户关联权限认证，待下次使用时重新加载
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }
    
    /**
     * 清空所有关联认证
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
    
    /**
     * 授权用户信息
     */
    public static class Principal implements Serializable {
        
        private static final long serialVersionUID = 1L;
        
        private String id;
        
        private String loginName;
        
        private String name;
        
        private transient Map<String, Object> cacheMap;
        
        public Principal(UserEO user) {
            this.id = user.getUsid() == null ? "" : String.valueOf(user.getUsid());
            this.loginName = user.getAccount();
            this.name = user.getUsname();
        }
        
        public String getId() {
            return id;
        }
        
        private String getLoginName() {
            return loginName;
        }
        
        public String getName() {
            return name;
        }
        
        public Map<String, Object> getCacheMap() {
            if (cacheMap == null) {
                cacheMap = new HashMap<>();
            }
            return cacheMap;
        }
        
    }
}
