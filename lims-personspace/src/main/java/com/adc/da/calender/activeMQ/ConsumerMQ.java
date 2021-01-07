package com.adc.da.calender.activeMQ;

import com.adc.da.common.ConstantUtils;
import com.adc.da.message.entity.MessageEO;
import com.adc.da.message.service.MessageEOService;
import com.adc.da.util.EmailConfig;
import com.adc.da.util.MailUtils;
import com.adc.da.util.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.io.IOException;
import java.util.Date;

@Component
public class ConsumerMQ {

    @Autowired
    private MessageEOService messageEOService;

    @Autowired
    private EmailConfig emailConfig;

    // 使用JmsListener配置消费者监听的队列，其中two是接收到的消息
    @JmsListener(destination = "calender.queue")
    public void send(ObjectMessage msg ) throws Exception {
        //保存消息实体
        MessageEO messageEO = (MessageEO) msg.getObject();
        messageEO.setSendtime(DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
        messageEOService.insertSelective(messageEO);
        //向websocket推送消息
        messageEOService.sendMessage(messageEO);

    }

    /**
     * 延时邮件
     * @param msg
     * @throws JMSException
     */
    @JmsListener(destination = "delaymail.queue")
    public void removeMailId(ObjectMessage msg) throws JMSException {
        String uuid = (String)msg.getObject();
        ConstantUtils.DELAYMAILMAP.remove(uuid);
    }

    /**
     * 发送邮件
     * @param msg
     * @throws JMSException
     */
    @JmsListener(destination = "messagemail.queue")
    public void sendMail(ObjectMessage msg) throws JMSException, IOException {
        MailUtils mailUtils = (MailUtils) msg.getObject();
        emailConfig.sendMailHtml(mailUtils);
    }

}
