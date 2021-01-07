package com.adc.da.common.utils;

import com.adc.da.common.ConstantUtils;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/30 9:54
 * @description：${description}
 */
public class TransformUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformUtil.class);

    private TransformUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 分页查询转换通用查询条件
     *
     * @param searchPhrase
     * @return
     */
    public static List<String> settingSearchPhrase(String searchPhrase) {
        Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
        Matcher dateMatcher = datePattern.matcher(searchPhrase);
        List<String> list = new ArrayList<>();
        while (dateMatcher.find()) {
            String search = dateMatcher.group();
            list.add(search);
        }
        return list;
    }

    /**
     * 校验必填项是否为空
     * o 校验实体
     * args 需要校验的属性
     *
     * @return
     */
    public static ResponseMessage verify(Object o, String... args) {
        try {
            if (StringUtils.isEmpty(o)) {
                return Result.error("-1", "对象不可为空");
            } else {
                Class<?> aClass = o.getClass();
                for (String arg : args) {
                    String getMethodName = "get" + arg.substring(0, 1).toUpperCase() + arg.substring(1);
                    Method getMethod = aClass.getDeclaredMethod(getMethodName);
                    Object value = getMethod.invoke(o);
                    if (value == null) {
                        return Result.error("-1", "参数异常: " + arg + "不能为空");
                    } else {
                        if (StringUtils.isBlank(value.toString())) {
                            return Result.error("-1", "参数异常: " + arg + "不能为空");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 校验必填项是否为空
     * o 校验实体
     * map 需要校验的属性
     *
     * @return
     */
    public static ResponseMessage verifyLength(Object o, Map<String, Integer> map) {
        try {
            if (StringUtils.isEmpty(o)) {
                return Result.error("-1", "对象不可为空");
            } else {
                Class<?> aClass = o.getClass();
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    LOGGER.info("key= {} and value= {}", entry.getKey(), entry.getValue());
                    String getMethodName = "get" + entry.getKey().substring(0, 1).toUpperCase() +
                            entry.getKey().substring(1);
                    Method getMethod = aClass.getDeclaredMethod(getMethodName);
                    Object value = getMethod.invoke(o);
                    if (value == null) {
                        return Result.error("-1", "参数异常: " + entry.getKey() + "不能为空");
                    } else {
                        if (StringUtils.isBlank(value.toString())) {
                            return Result.error("-1", "参数异常: " + entry.getKey() + "不能为空");
                        }
                        if (value.toString().length() > entry.getValue()) {
                            return Result.error("-1", "参数异常: " + entry.getKey() + "长度大于" +
                                    entry.getValue());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 校验必填项是否为空
     * o 校验实体
     * map 需要校验的属性
     *
     * @return
     */
    public static ResponseMessage verifyMatcher(Object o, String... args) {
        try {
            if (StringUtils.isEmpty(o)) {
                return Result.error("-1", "对象不可为空");
            } else {
                Class<?> aClass = o.getClass();
                for (String arg : args) {
                    String getMethodName = "get" + arg.substring(0, 1).toUpperCase() + arg.substring(1);
                    Method getMethod = aClass.getDeclaredMethod(getMethodName);
                    Object value = getMethod.invoke(o);
                    if (value != null && StringUtils.isNotBlank(value.toString())) {
                        if (!matcher(value.toString())) {
                            return Result.error("-1", "参数异常：" + arg + "必须为数字");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean matcher(String arg) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(arg).matches();
    }


}
