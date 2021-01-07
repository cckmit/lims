package com.adc.da.util;

import com.adc.da.base.page.BasePage;
import com.adc.da.common.ConstantUtils;
import com.adc.da.persondoc.page.PersondocEOPage;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFieldUtil {

    public static BasePage pageSet(String pageName, String searchPhrase) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = Class.forName(pageName);
        BasePage page = (BasePage) clazz.newInstance();
        Method method = clazz.getMethod("setSearchPhrase",List.class);
        if (StringUtils.isNotEmpty(searchPhrase)) {
            searchPhrase = searchPhrase.trim();
            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
            Matcher dateMatcher = datePattern.matcher(searchPhrase);
            List<String> list = new ArrayList();
            while (dateMatcher.find()) {
                String search = dateMatcher.group();
                list.add(search);
            }
            method.invoke(page,list);
        }
        return page;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        pageSet(PersondocEOPage.class.getName(),"888 ii ih");

    }

}
