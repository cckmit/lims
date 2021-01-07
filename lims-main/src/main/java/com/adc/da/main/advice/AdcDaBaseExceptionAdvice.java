package com.adc.da.main.advice;

import com.adc.da.util.EmailConfig;
import com.adc.da.util.MailUtils;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.ResponseMessageCodeEnum;
import com.adc.da.util.http.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常处理
 */
@ControllerAdvice
@Order(value=2)
public class AdcDaBaseExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(AdcDaBaseExceptionAdvice.class);

    @Autowired
    private EmailConfig emailConfig;

    private MailUtils mailUtils;

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AdcDaBaseException.class)
    @ResponseBody
    public ResponseMessage handlerAdcDaBaseException(AdcDaBaseException exception) {
        logger.warn(exception.getMessage(), exception);
        return Result.error(exception.getErrorCode(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage handlerAdcDaBaseException(Exception exception) {
        logger.error(exception.getMessage(), exception);

//        mailUtils = new MailUtils("adc_fudongjie@163.com","系统异常通知",
////                "错误编码：" +ResponseMessageCodeEnum.ERROR.getCode()
////                + "错误信息定位如下：\r\n" +getStackTrace(exception),"EXCEPTION");
////        emailConfig.sendSimpleMail(mailUtils);
        //在数据库中记录程序异常，这个地方的异常是未处理的异常，需要管理员查看并进行处理以防重复出现
        return Result.error(ResponseMessageCodeEnum.ERROR.getCode(),
                "程序异常，请重试。如果重复出现请联系管理员处理！异常信息：" + exception.getMessage());
    }

    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try {
            t.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }

}
