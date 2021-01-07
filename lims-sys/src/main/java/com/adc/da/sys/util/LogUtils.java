package com.adc.da.sys.util;

import com.adc.da.log.entity.LogEO;
import com.adc.da.log.service.LogEOService;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.util.utils.IpUtil;
import com.adc.da.util.utils.SpringContextHolder;
import com.adc.da.util.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class LogUtils {

    @Autowired
    private static LogEOService logEOService = SpringContextHolder.getBean(LogEOService.class);

    @Autowired
    private static UserEODao userEODao  = SpringContextHolder.getBean(UserEODao.class);

    private LogUtils(){
        throw new IllegalStateException("LogUtils.java");
    }
    /*
     * @Author syxy_zhangyinghui
     * @Description 写日志
     * @Date 9:00 2019/8/1
     * @param className     类名
     * @param methodName    方法名
     * @param logAnnotation 描述
     * @param userId        用户名
     * @param operationTime     操作时间
     * @throws Exception
     * @return
     **/
    public static void writeLog(String className, String methodName,
                                String logAnnotation, String userId,
                          long operationTime,HttpServletRequest request) throws Exception {
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
        logEO.setStartTime(new Date(operationTime));
        logEO.setEndTime(new Date(operationTime));
        logEO.setOperateTime(new Date(operationTime));
        logEOService.insertSelective(logEO);
    }

}
