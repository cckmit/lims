package com.adc.da.login.util;

import com.adc.da.sys.entity.MenuEO;
import com.adc.da.sys.entity.RoleEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.login.security.SystemAuthorizingRealm;
import com.adc.da.sys.service.MenuEOService;
import com.adc.da.sys.service.RoleEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.ObjectUtils;
import com.adc.da.util.utils.SpringContextHolder;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 用户信息工具类
 *
 * @author comments created by Lee Kwanho
 * date 2018-09-05
 **/
public class UserUtils {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(UserUtils.class);
    
    /**
     * 当前登陆用户
     */
    public static final String CURRENT_USER = "currentUser";
    
    /**
     * 角色信息
     */
    public static final String CACHE_ROLE_LIST = "roleList";
    
    /**
     * 菜单信息
     */
    public static final String CACHE_MENU_LIST = "menuList";
    
    /**
     * 菜单树
     */
    public static final String CACHE_MENU_TREE = "menuTree";
    
    /**
     * 保留
     */
    public static final String CACHE_AREA_LIST = "areaList";
    
    /**
     * 保留
     */
    public static final String CACHE_OFFICE_LIST = "officeList";
    
    /**
     * @see UserEOService
     */
    private static UserEOService userService = SpringContextHolder.getBean(UserEOService.class);
    
    /**
     * @see MenuEOService
     */
    private static MenuEOService menuService = SpringContextHolder.getBean(MenuEOService.class);
    
    /**
     * @see RoleEOService
     */
    private static RoleEOService roleEOService = SpringContextHolder.getBean(RoleEOService.class);
    
    private UserUtils() {
        throw new IllegalStateException("UserUtils.java");
    }
    
    /**
     * 退出
     */
    public static void logout() {
        try {
            SecurityUtils.getSubject().logout();
        } catch (UnavailableSecurityManagerException e) {
            logger.error("logout UnavailableSecurityManagerException", e);
        } catch (InvalidSessionException e) {
            logger.error("logout InvalidSessionException", e);
        }
    }
    
    /**
     * 获取当前登录用户ID
     *
     * @return userId
     */
    public static String getUserId() {
        String userId = null;
        try {
            Subject subject = SecurityUtils.getSubject();
            SystemAuthorizingRealm.Principal principal = (SystemAuthorizingRealm.Principal) subject.getPrincipal();
            if (principal != null) {
                userId = principal.getId();
            }
        } catch (UnavailableSecurityManagerException e) {
            logger.error("getUserId UnavailableSecurityManagerException", e);
        } catch (InvalidSessionException e) {
            logger.error("getUserId InvalidSessionException", e);
            
        }
        return userId;
    }
    
    /**
     * 获取当前登录用户信息
     */
    public static UserEO getUser() throws Exception {
        UserEO user = (UserEO) CacheUtils.getCache(CURRENT_USER);
        if (user == null) {
            String userId = getUserId();
            
            if (StringUtils.isNotEmpty(userId)) {
                UserEO userInDb = userService.selectByPrimaryKey(userId);
                user = ObjectUtils.clone(userInDb);
                user.setPassword(null);
                CacheUtils.putCache(CURRENT_USER, user);
            }
        }
        return user;
    }
    
    /**
     * 修改当前用户信息
     *
     * @param userVo 用户信息
     * @throws Exception 异常信息
     */
    public static void updateUserInfo(UserEO userVo) throws Exception {
        UserEO user = (UserEO) CacheUtils.getCache(CURRENT_USER);
        if (user != null) {
            String userId = getUserId();
            
            if (StringUtils.isNotEmpty(userId)) {
                UserEO userInDb = userService.selectByPrimaryKey(userId);
                userInDb.setUsname(userVo.getUsname());
                userService.save(userInDb);
                
                CacheUtils.removeCache(CURRENT_USER);
            }
        }
    }
    
    /**
     * 获取当前登录用户角色列表
     *
     * @return 角色信息
     * @throws Exception
     */
    public static List<RoleEO> getRoleList() throws Exception {
        List<RoleEO> roleList = (List<RoleEO>) CacheUtils.getCache(CACHE_ROLE_LIST);
        if (roleList == null) {
            UserEO user = getUser();
            if (user != null) {
                roleList = roleEOService.getSysRoleListByUserId(user.getUsid());
            }
            CacheUtils.putCache(CACHE_ROLE_LIST, roleList);
        }
        return roleList;
    }
    
    /**
     * @return
     * @throws Exception
     */
    public static String getRoleIds() throws Exception {
        List<RoleEO> roleList = getRoleList();
        if (CollectionUtils.isEmpty(roleList)) {
            return "";
        }
        StringBuilder roleIds = new StringBuilder();
        for (RoleEO sysRoleEO : roleList) {
            roleIds.append(sysRoleEO.getId()).append(",");
        }
        return roleIds.substring(0, roleIds.length() - 1);
    }
    
    /**
     * 获取当前登录用户菜单列表
     */
    public static List<MenuEO> getMenuList() throws Exception {
        List<MenuEO> menuList = (List<MenuEO>) CacheUtils.getCache(CACHE_MENU_LIST);
        if (menuList == null) {
            UserEO user = getUser();
            if (user != null) {
                if (isAdmin(user)) {
                    menuList = menuService.findAll();
                } else {
                    menuList = menuService.listMenuEOByUserId(String.valueOf(user.getUsid()));
                }
                CacheUtils.putCache(CACHE_MENU_LIST, menuList);
            }
        }
        return menuList;
    }
    
    /**
     * 获取当前登录用户菜单树
     */
    public static MenuEO getMenuTree() {
        MenuEO menu = (MenuEO) CacheUtils.getCache(CACHE_MENU_TREE);
        if (menu != null) {
            
            CacheUtils.putCache(CACHE_MENU_TREE, menu);
        }
        return menu;
    }
    
    /**
     * 判断用户是否是超级管理员
     *
     * @param userVo 用户信息
     * @return 返回判断
     */
    public static boolean isAdmin(UserEO userVo) {
        return userVo != null &&    "1".equals(userVo.getUsid());
    }
    
    /**
     * 获取用户菜单权限信息
     */
    public static SimpleAuthorizationInfo getAuthInfo() throws Exception {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<MenuEO> list = UserUtils.getMenuList();
        List<RoleEO> roleEOList = UserUtils.getRoleList();
        for (MenuEO menu : list) {
            if (StringUtils.isNotBlank(menu.getPermission())) {
                // 添加基于Permission的权限信息
                for (String permission : StringUtils.split(menu.getPermission(), ",")) {
                    info.addStringPermission(permission);
                }
            }
        }
        for (RoleEO roleEO : roleEOList) {
            if (StringUtils.isNotBlank(roleEO.getName()) && roleEO.getDelFlag() == 0) {
                // 添加角色信息
                info.addRole(roleEO.getName());
            }
        }
        return info;
    }
    
    /**
     * 清空缓存
     */
    public static void flush() {
        CacheUtils.removeCache(CURRENT_USER);
        CacheUtils.removeCache(CACHE_MENU_LIST);
        CacheUtils.removeCache(CACHE_MENU_TREE);
    }
    
    /**
     *
     */
    private static final class CacheUtils {
        /**
         * @param key
         * @return
         */
        public static Object getCache(String key) {
            return getCache(key, null);
        }
        
        /**
         * @param key
         * @param defaultValue
         * @return
         */
        public static Object getCache(String key, Object defaultValue) {
            Object obj = getCacheMap().get(key);
            return obj == null ? defaultValue : obj;
        }
        
        /**
         * @param key
         * @param value
         */
        public static void putCache(String key, Object value) {
            getCacheMap().put(key, value);
        }
        
        /**
         * @param key
         */
        public static void removeCache(String key) {
            getCacheMap().remove(key);
        }
        
        /**
         * @return
         */
        public static Map<String, Object> getCacheMap() {
            Map<String, Object> map = Maps.newHashMap();
            try {
                Subject subject = SecurityUtils.getSubject();
                SystemAuthorizingRealm.Principal principal = (SystemAuthorizingRealm.Principal) subject.getPrincipal();
                return principal != null ? principal.getCacheMap() : map;
            } catch (UnavailableSecurityManagerException e) {
                logger.error("getCacheMap UnavailableSecurityManagerException", e);
            } catch (InvalidSessionException e) {
                logger.error("getCacheMap InvalidSessionException", e);
            }
            return map;
        }
    }
    
}
