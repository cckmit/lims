package com.adc.da.main.config;

import com.adc.da.util.filter.CorsFilter;
import com.adc.da.util.filter.HttpCacheFilter;
import com.adc.da.util.filter.RequestInfoFilter;
import com.adc.da.util.xss.XssFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Web配置，用于注入过滤器，拦截器等
 */
@Configuration
public class WebConfig {

    /**
     * 原过滤路径为/*，改为/api/* 不对工作流相关接口进行过滤
     * date 2018-09-05
     */
    @Bean
    public FilterRegistrationBean xssFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/api/*") ;
        registration.addInitParameter("excludedPages","form");
        registration.addInitParameter("excludedPages","activiti");
        registration.setName("xssFilter");
        registration.setOrder(10);
        return registration;
    }

    /**
     * 控制跨站请求
     * @return
     */
//    @Bean
//    public FilterRegistrationBean csrfFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new CsrfFilter());
//        registration.addUrlPatterns("/*");
//        registration.setName("csrfFilter");
//        registration.setOrder(9);
//        return registration;
//    }


    @Bean
    public FilterRegistrationBean requestInfoFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestInfoFilter());
        registration.addUrlPatterns("/*");
        registration.setName("RequestInfoFilter");
        registration.setOrder(8);
        return registration;
    }

    /**
     * 跨站401
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CorsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("corsFilter");
        registration.setOrder(7);
        return registration;
    }


    @Bean
    public FilterRegistrationBean httpCacheFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpCacheFilter());
        registration.addUrlPatterns("/*");
        registration.setName("httpCacheFilter");
        registration.addInitParameter("maxAge", String.valueOf(60 * 60 * 24 * 7));
        registration.setOrder(6);
        return registration;
    }

    /**
     * 防伪造jsessionid
     */
//    @Bean
//    public FilterRegistrationBean fakeJSessionIdFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new FakeJSessionIdFilter());
//        registration.addUrlPatterns("/*");
//        registration.setName("fakeJSessionIdFilter");
//        registration.setOrder(5);
//        return registration;
//    }



}
