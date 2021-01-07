package com.adc.da.log.constant;

public class LogConstants {
    private LogConstants() {
        throw new IllegalStateException("LogConstants class");
    }

    /**
     * 开发模式不拦截方法记日志
     */
    public static final String LOG_TYPE_DEV = "DEV";

    /**
     * 客户自定义需要拦截记日志的方法
     */
    public static final String LOG_TYPE_CUSTOM = "CUSTOM";

    /**
     * 系统原设需要拦截记日志的方法
     */
    public static final String LOG_TYPE_SYS = "SYS";

}
