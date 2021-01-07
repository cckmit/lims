package com.adc.da.login.security;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 表单验证（包含验证码）过滤类
 */
@Service
public class AdcFormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    /**
     * 验证码
     */
    public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";

    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    public String getCaptchaParam() {
        return captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        //  不知道什么原因，在Spring Boot应用中，无法初始化WebSubject
        WebSubject.Builder builder = new WebSubject.Builder(request, response);
        WebSubject webSubject = builder.buildWebSubject();
        ThreadContext.bind(webSubject);

        String username = getUsername(request);
        String password = getPassword(request);
        String record;
        if (password == null) {
            record = "";
        } else {
            record = password;
        }
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        String captcha = getCaptcha(request);
        return new UsernamePasswordToken(username, record.toCharArray(), rememberMe, host, captcha);
    }

}