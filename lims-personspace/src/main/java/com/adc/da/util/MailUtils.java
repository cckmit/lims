package com.adc.da.util;

import java.io.Serializable;

public class MailUtils implements Serializable {

    private String receiver;

    private String subject;

    private String content;

    private String token;


    public MailUtils() {
    }

    public MailUtils(String receiver, String subject, String content, String token) {
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
        this.token = token;
    }

    /**
     * 邮件接收方邮箱
     * @return
     */
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * 标题
     * @return
     */
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    /**
     * 邮件内容
     * @return
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
