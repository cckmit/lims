package com.adc.da.sys.config;

import com.adc.da.sys.entity.ParamEO;
import com.adc.da.sys.service.ParamEOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

@Configuration
@Component
public class ConfigBeforeInit {
    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    private ParamEOService paramEOService;

    @PostConstruct
    public void initSystemConfig(){
        // 获取系统属性集合
        MutablePropertySources propertySources = environment.getPropertySources();
        //从数据库获取自定义变量列表
        ParamEO param = paramEOService.getParamByName("预登陆账号");
        //将转换后的列表加入属性中
        Properties properties = new Properties();
        properties.put("preUsername",param.getParamCode());
        properties.put("prePassword", param.getParamValue());
        PropertiesPropertySource constants = new PropertiesPropertySource("system-config", properties);

        // 定义寻找属性的正则，该正则为系统默认属性集合的前缀
        Pattern p = compile("^applicationConfig.*");
        // 接收系统默认属性集合的名称
        String name = null;
        // 标识是否找到系统默认属性集合
        boolean flag = false;
        // 遍历属性集合
        for (PropertySource<?> source : propertySources) {
            // 正则匹配  匹配到：OriginTrackedMapPropertySource {name='applicationConfig: [classpath:/application.properties]'}
            if (p.matcher(source.getName()).matches()) {
                // 接收名称
                name = source.getName();
                // 变更标识
                flag = true;

                break;
            }
        }
        if (flag) {
            // 找到则将自定义属性添加到该属性之后，意思就是以application.properties文件配置为准  如果想要以数据库配置为准，就修改为 propertySources.addBefore(name, constants)
            propertySources.addAfter(name, constants);
        } else {
            // 没找到默认添加到最后
            propertySources.addFirst(constants);
        }

    }

}
