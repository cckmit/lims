package com.adc.da.main.config;

import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 各个模块的dozer配置文件
 */
@Configuration
public class DozerConfig {

	@Bean(name = "org.dozer.Mapper")
	public DozerBeanMapper dozer() {
		List<String> mappingFiles = Arrays.asList("dozer/dozer-mappings-sys.xml");
		DozerBeanMapper dozerBean = new DozerBeanMapper();
		dozerBean.setMappingFiles(mappingFiles);
		return dozerBean;
	}

}
