package com.adc.da.main.config;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.Properties;

@Configuration
@MapperScan("com.adc.da.**.dao")
public class MybatisConfig {

//    @Autowired
//    @Qualifier("dataSource")
//    private DataSource dataSource;

//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        factoryBean.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/mapper/**/*.xml"));
//        factoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
//        return factoryBean.getObject();
//    }

//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
//        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory());
//        return template;
//    }
    
    
    
    
    
    
    /**
     *
     *
     * 瀹炵幇澶氭暟鎹簱绫诲瀷鍏煎锛屽埄鐢╠atabaseid鍖哄垎
     * Mapper鏂囦欢鍐欐硶濡備笅
     * <select id="queryByPage" ......  databaseId="mysql">
     *
     * 瀹炵幇濡備笅configuration.xml
     * <databaseIdProvider type="DB_VENDOR">
     * <property name="DB2" value="db2" />
     * <property name="Oracle" value="oracle" />
     * <property name="Adaptive Server Enterprise" value="sybase" />
     * <property name="MySQL" value="mysql" />
     * </databaseIdProvider>
     *
     *
     * bean xml 鍐欐硶 濡備笅
     */
    //<bean id="vendorProperties" class="org.springframework.beans.factory.config.PropertiesFactoryBean">
    //    <property name="properties">
    //        <props>
    //            <prop key="SQL Server">sqlserver</prop>
    //            <prop key="DB2">db2</prop>
    //            <prop key="Oracle">oracle</prop>
    //            <prop key="MySQL">mysql</prop>
    //        </props>
    //    </property>
    //</bean>
    //
    //<bean id="databaseIdProvider" class="org.apache.ibatis.mapping.VendorDatabaseIdProvider">
    //    <property name="properties" ref="vendorProperties"/>
    //</bean>
    //
    //<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    //    <property name="dataSource" ref="dataSource" />
    //    <property name="mapperLocations" value="classpath*:sample/config/mappers/**/*.xml" />
    //    <property name="databaseIdProvider" ref="databaseIdProvider"/>

    @Bean
    public DatabaseIdProvider getDatabaseIdProvider() {
        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties p = new Properties();
        p.setProperty("Oracle", "oracle");
        p.setProperty("MySQL", "mysql");
        databaseIdProvider.setProperties(p);
        return databaseIdProvider;
    }
}
