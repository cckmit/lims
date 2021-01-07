package com.adc.da.main.advice;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class MultipartExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(MultipartExceptionAdvice.class);

    @Value("${spring.http.multipart.max-file-size}")
    private String maxFileSize;

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    public ResponseMessage handlerAdcDaBaseException(MultipartException exception) {
        logger.warn(exception.getMessage(), exception);
        return Result.error("文件大小超过限制(" + maxFileSize.toUpperCase() + ")");
    }

}
