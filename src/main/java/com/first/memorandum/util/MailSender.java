package com.first.memorandum.util;


import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class MailSender {

    private String host;
    private String protocol;
    private String authflag;
    private Boolean sslenable;
    private String sender;
    private String authcode;
    private Boolean debugflag;
    private String encode;

    public void sendEmail(String receiver,String content,String topic) throws GeneralSecurityException, MessagingException {
        //创建一个配置文件并保存
        Properties properties = new Properties();
        properties.setProperty("mail.host",host);

        properties.setProperty("mail.transport.protocol",protocol);

        properties.setProperty("mail.smtp.auth",authflag);


        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", sslenable);
        properties.put("mail.smtp.ssl.socketFactory", sf);

        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender,authcode);
            }
        });

        //开启debug模式
        session.setDebug(true);

        //获取连接对象
        Transport transport = session.getTransport();

        //连接服务器
        transport.connect(host,sender,authcode);

        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);

        //邮件发送人
        mimeMessage.setFrom(new InternetAddress(sender));

        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(receiver));

        //邮件标题
        mimeMessage.setSubject(topic);

        //邮件内容
        mimeMessage.setContent(content,encode);

        //发送邮件
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

        //关闭连接
        transport.close();
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setAuthflag(String authflag) {
        this.authflag = authflag;
    }

    public void setSslenable(Boolean sslenable) {
        this.sslenable = sslenable;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

    public void setDebugflag(Boolean debugflag) {
        this.debugflag = debugflag;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }
}
