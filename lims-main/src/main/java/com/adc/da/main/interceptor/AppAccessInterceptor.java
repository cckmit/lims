package com.adc.da.main.interceptor;

import java.lang.reflect.Method;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.adc.da.sys.annotation.EnableAccess;

public class AppAccessInterceptor extends HandlerInterceptorAdapter {
	
	@Value("${environment}")
	private String environment;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (environment == null || !"public".equals(environment)) {
			return true;
		}
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        EnableAccess annotation = method.getAnnotation(EnableAccess.class);
        // 有 @annotation 注解，可以访问
        if (annotation != null) {
        	return true;
        }
		
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-store");
		ServletOutputStream os = response.getOutputStream();
		os.write("{resCode:-1,resMsg:'资源不存在'}".getBytes());
		os.close();
		return false;
	}
}