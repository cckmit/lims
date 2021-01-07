package com.adc.da.util;

import com.adc.da.calender.activeMQ.ProducerMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

@Component
public class EmailConfig {

    @Value("${activiti.mail.server.host}")
    private String host;
    @Value("${activiti.mail.server.default.from}")
    private String account;
    @Value("${activiti.mail.server.password}")
    private String password;

    @Autowired
    private ProducerMQ producerMQ;

    @Bean(name = "JavaMailSenderImpl")
    public JavaMailSenderImpl getMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setUsername(account);
        javaMailSender.setPassword(password);
        Properties properties = new Properties();
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }

    /**
     * 发邮件
     * @param email
     */
    public void sendSimpleMail(MailUtils email) {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom(account);
//        String receiver = email.getReceiver();
//        String receivers[] = receiver.split(";");
//        simpleMailMessage.setTo(receivers);
//        simpleMailMessage.setSubject(email.getSubject());
//        simpleMailMessage.setText(email.getContent());
//        getMailSender().send(simpleMailMessage);
    }


    /**
     * 超链接邮件
     * @param mailUtils
     */
    public void sendMailHtml(MailUtils mailUtils) throws IOException {
//        MimeMessage mimeMessage = getMailSender().createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
//        try {
//            producerMQ.delaySend(ProducerMQ.MAILQUEUE,mailUtils.getToken(),7200000l);
//            helper.setFrom(account);
//            helper.setTo(mailUtils.getReceiver());
//            helper.setSubject(mailUtils.getSubject());
//            mimeMessage.setContent(mailUtils.getContent(), "text/html;charset=gbk");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        getMailSender().send(mimeMessage);
    }
}