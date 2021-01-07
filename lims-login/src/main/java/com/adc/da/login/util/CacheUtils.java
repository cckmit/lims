package com.adc.da.login.util;

import com.adc.da.util.utils.SpringContextHolder;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Cache工具类
 */
public class CacheUtils {

    private CacheUtils() {
        throw new IllegalStateException("CacheUtils.java");
    }

    private static CacheManager cacheManager = SpringContextHolder.getBean("ehCacheManagerFactoryBean");

    private static final String SYS_CACHE = "sysCache";

    private static final String ERROR_CACHE = "errorCache";

    public static Object get(String key) {
        return get(SYS_CACHE, key);
    }

    public static void put(String key, Object value) {
        put(SYS_CACHE, key, value);
    }

    public static void remove(String key) {
        remove(SYS_CACHE, key);
    }

    //以下三个方法由丁强添加，配合main模块下resource/cache/ehcache-local.xml
    //<cache name="errorCache" maxElementsInMemory="100"  timeToIdleSeconds="180" timeToLiveSeconds="300" eternal="false" overflowToDisk="true"/>
    public static Object getErrorCache(String key) {
        return get(ERROR_CACHE, key);
    }

    public static void putErrorCache(String key, Object value) {
        put(ERROR_CACHE, key, value);
    }

    public static void removeErrorCache(String key) {
        remove(ERROR_CACHE, key);
    }




    public static Object get(String cacheName, String key) {
        Element element = getCache(cacheName).get(key);
        return element == null ? null : element.getObjectValue();
    }

    public static void put(String cacheName, String key, Object value) {
        Element element = new Element(key, value);
        getCache(cacheName).put(element);
    }

    public static void remove(String cacheName, String key) {
        getCache(cacheName).remove(key);
    }

    /**
     * 获得一个Cache，没有则创建一个。
     *
     * @param cacheName
     * @return
     */
    private static Cache getCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            cacheManager.addCache(cacheName);
            cache = cacheManager.getCache(cacheName);
            cache.getCacheConfiguration().setEternal(true);
        }
        return cache;
    }

    public static CacheManager getCacheManager() {
        return cacheManager;
    }

}
