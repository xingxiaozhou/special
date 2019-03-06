package cn.vp.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件工具类
 */
public class MailUtil {
    private static final String  PROTOCAL="SMTP";
    private static final String HOST = "smtp.126.com";
    private static final String PORT = "25";
    private static final String AUTH = "true";
    private static final String TIMEOUT = "25000";
    private static final String USERNAME = "xingyizhouxyz@126.com";
    private static final String PASSWORD = "xingyizhou123";
    private static final String EMAILFORM = "xingyizhouxyz@126.com";
    /**
     * 发送邮件
     *
     * @param to   给谁发
     * @param code 发送内容
     */
    //String code, String to
    public static boolean send(String code, String to) {
        // 1.创建一个程序与邮件服务器会话对象 Session
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", PROTOCAL);
        props.setProperty("mail.smtp.host",HOST);
        props.setProperty("mail.smtp.port", PORT);
        // 指定验证为true
        props.setProperty("mail.smtp.auth", AUTH);
        props.setProperty("mail.smtp.timeout",TIMEOUT);
        // 验证账号及密码，密码需要是第三方授权码
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);

        // 2.创建一个Message，它相当于是邮件内容
        Message message = new MimeMessage(session);
        try {
            // 设置发送者
            message.setFrom(new InternetAddress(EMAILFORM));
            // 设置发送方式与接收者
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            // 设置主题
            message.setSubject("农特电商注册");
           // 设置内容
            message.setContent(code, "text/html;charset=utf-8");
            // 3.创建 Transport用于将邮件发送
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }
}