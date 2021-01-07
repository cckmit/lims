package com.adc.da.calender.activeMQ;


import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ScheduledMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service("producerMQ")
public class ProducerMQ {

    //工作日历队列
    public static final Destination ONEQUEUE = new ActiveMQQueue("calender.queue");

    //延时邮件队列
    public static final Destination MAILQUEUE = new ActiveMQQueue("delaymail.queue");

    //延时邮件队列
    public static final Destination MESSAGEQUEUE = new ActiveMQQueue("messagemail.queue");

    @Autowired
    private JmsMessagingTemplate jmsTemplate;
    /**
     * 发送消息
     * @param destination destination是发送到的队列
     * @param message message是待发送的消息
     */
    public <T extends Serializable> void send(Destination destination, T message){
        jmsTemplate.convertAndSend(destination, message);
    }

    /**
     * 延时发送
     * @param destination 发送的队列
     * @param data 发送的消息
     * @param time 延迟时间
     */
    public <T extends Serializable> void delaySend(Destination destination, T data, Long time){
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        // 获取连接工厂
        ConnectionFactory connectionFactory = jmsTemplate.getConnectionFactory();
        try {
            // 获取连接
            connection = connectionFactory.createConnection();
            connection.start();
            // 获取session，true开启事务，false关闭事务
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            producer = session.createProducer(destination);
            producer.setDeliveryMode(JmsProperties.DeliveryMode.PERSISTENT.getValue());
            ObjectMessage message = session.createObjectMessage();
            message.setObject((Serializable) data);
            //设置延迟时间
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
            // 发送消息
            producer.send(message);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (producer != null){
                    producer.close();
                }
                if (session != null){
                    session.close();
                }
                if (connection != null){
                    connection.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
