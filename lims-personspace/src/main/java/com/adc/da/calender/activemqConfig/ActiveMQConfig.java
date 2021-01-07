package com.adc.da.calender.activemqConfig;

import com.adc.da.message.entity.MessageEO;
import com.adc.da.util.MailUtils;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;
import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class ActiveMQConfig {

    @Value("${BrokerURL}")
    private String brokerURL;

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("calender.queue");
        connectionFactory.setBrokerURL(brokerURL);
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");
        // 添加信任自定义消息类所在包
        connectionFactory.setTrustedPackages(new ArrayList<>(Arrays.asList(new String[]{MessageEO.class.getPackage().getName(),MailUtils.class.getPackage().getName()})));
        return connectionFactory;
    }
}
