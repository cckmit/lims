package com.adc.da.login.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import com.adc.da.util.utils.IpUtil;
import com.adc.da.util.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.log.constant.LogConstants;
import com.adc.da.log.entity.LogEO;
import com.adc.da.log.service.LogEOService;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.util.utils.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于记录调用service层日志，
 * 日志会记录在TS_LOG中
 *
 */
@Aspect
@Component
public class BusinessLogAspect {
    /**
     * 匹配Service层的save, update, delete, get, find, page等方法
     */
    @Pointcut(value = "(execution(* com.adc.da.*.controller.*.save*(..)) "
            + "|| execution(* com.adc.da.*.controller.*.update*(..)) "
            + "|| execution(* com.adc.da.*.controller.*.del*(..)) "
            + "|| execution(* com.adc.da.*.controller.*.get*(..)) "
            + "|| execution(* com.adc.da.*.controller.*.find*(..)) "
            + "|| execution(* com.adc.da.*.controller.*.page*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.lock*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.import*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.export*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.download*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.preview*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.start*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.approval*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.export*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.query*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.*Task*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.open*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.list*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.create*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.reCall*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.sup*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.upLoad*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.proc*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.feed*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.send*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.scrap*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.borrow*(..))) "
            + "|| execution(* com.adc.da.*.controller.*.returned*(..))) "
            + "|| execution(* com.adc.da.*.rest.*.logout*(..))) "
            + "|| execution(* com.adc.da.*.rest.*.reset*(..))) "
            + "&& !execution(* com.adc.da.log.service.LogEOService.*(..))")
    private void servicePointcut() {
        throw new UnsupportedOperationException("servicePointcut error");
    }


    /**
     * @see LogEOService
     */
    @Autowired
    private LogEOService logEOService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserEODao userEODao;

    /**
     * 读取配置文件
     * 系统日志类别
     *      dev:开发模式不拦截方法记日志,
     *      custom:客户自定义需要拦截记日志的方法,
     *      sys:系统原设需要拦截记日志的方法
     */
    @Value("${sysLogType}")
    private String sysLogType;

    /*@Around(value = "servicePointcut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        Class cls = joinPoint.getTarget().getClass();
        String signature = joinPoint.getSignature().getName();
        Object result;
        // 调用service层开始时间
        long startTime = System.currentTimeMillis();
        result = joinPoint.proceed();
        // 调用service层结束时间
        long endTime = System.currentTimeMillis();
        // dev模式下不记系统日志
        if (LogConstants.LOG_TYPE_DEV.equalsIgnoreCase(sysLogType)) {
            return result;
        }
        String userId = UserUtils.getUserId();
        // 非登录模式下不记系统日志
        if (userId == null) {
            return result;
        }
        // 业务日志组件开始工作，sys模式
        if (LogConstants.LOG_TYPE_SYS.equalsIgnoreCase(sysLogType)) {
            writeLog(cls.getName(), signature, null, userId, startTime, endTime);
        } else {
            for (Method method : cls.getDeclaredMethods()) {
                BusinessLog logAnnotation = method.getAnnotation(BusinessLog.class);
                if (logAnnotation != null) {
                    String methodName = method.getName();
                    if (signature.equals(methodName)) {
                        writeLog(cls.getName(), signature, logAnnotation.description(), userId, startTime, endTime);
                    }
                }
            }
        }
        //  业务日志组件工作结束
        return result;
    }*/


    @Around(value ="servicePointcut()" )
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        Class cls = joinPoint.getTarget().getClass();
        String signature = joinPoint.getSignature().getName();
        Object result;
        // 调用service层开始时间
        long startTime = System.currentTimeMillis();
        result = joinPoint.proceed();
        // 调用service层结束时间
        long endTime = System.currentTimeMillis();
        // dev模式下不记系统日志
        if (LogConstants.LOG_TYPE_DEV.equalsIgnoreCase(sysLogType)) {
            return result;
        }
        String userId = UserUtils.getUserId();
        // 非登录模式下不记系统日志
        if (userId == null) {
            return result;
        }
        // 业务日志组件开始工作，sys模式
        if (LogConstants.LOG_TYPE_SYS.equalsIgnoreCase(sysLogType)) {
            String methodName = joinPoint.getSignature().getName();
            Method method = currentMethod(joinPoint,methodName);
            BusinessLog businessLog = method.getAnnotation(BusinessLog.class);
            String description = "";
            if(StringUtils.isNotEmpty(businessLog) && StringUtils.isNotEmpty(businessLog.description())){
                description = businessLog.description();
            }
            writeLog(cls.getName(), signature, description, userId, startTime, endTime);
        } else {
            for (Method method : cls.getDeclaredMethods()) {
                BusinessLog logAnnotation = method.getAnnotation(BusinessLog.class);
                if (logAnnotation != null) {
                    String methodName = method.getName();
                    if (signature.equals(methodName)) {
                        writeLog(cls.getName(), signature, logAnnotation.description(), userId, startTime, endTime);
                    }
                }
            }
        }
        return result;
    }

    private Method currentMethod(JoinPoint joinPoint, String methodName) {
        /**
         * 获取目标类的所有方法，找到当前要执行的方法
         */
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        Method resultMethod = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                resultMethod = method;
                break;
            }
        }
        return resultMethod;
    }


    /**
     * 写日志
     *
     * @param className     类名
     * @param methodName    方法名
     * @param logAnnotation 描述
     * @param userId        用户名
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @throws Exception
     */
    private void writeLog(String className, String methodName, String logAnnotation, String userId,
            long startTime, long endTime) throws Exception {
        LogEO logEO = new LogEO();
        logEO.setId(UUID.randomUUID10());

        logEO.setClassName(className);
        logEO.setMethod(methodName);
        if (logAnnotation != null && !"".equals(logAnnotation)) {
            logEO.setDescription(logAnnotation);
        }
        logEO.setUsid(userId);
        UserEO userEO = userEODao.selectByPrimaryKey(userId);
        // 写入ip信息
        logEO.setIpAddress(IpUtil.getIpAddr(request));
        if (userEO != null) {
            logEO.setAccount(userEO.getAccount());
        }
        logEO.setStartTime(new Date(startTime));
        logEO.setEndTime(new Date(endTime));
        logEO.setOperateTime(new Date(startTime));
        logEOService.insertSelective(logEO);
    }
}