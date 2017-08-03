package cn.likole.TravelBetter.util;

import org.apache.struts2.ServletActionContext;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

/**
 * 用于发送验证邮件
 * Created by likole on 8/2/17.
 */
public class MailUtil {

    public static String myEmailAccount;
    public static String myEmailPassword;
    public static String myEmailSMTPHost = "smtp.163.com";
    public static String serverAddress;

    public static void sendMessage(String content, String receiveMailAccount) throws Exception {
        /*
        读取邮件帐号配置
         */
        Properties properties = new Properties();
        String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/classes/email.properties");
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path), "utf-8");
        properties.load(inputStreamReader);
        myEmailAccount = properties.getProperty("myEmailAccount");
        myEmailPassword = properties.getProperty("myEmailPassword");
        serverAddress = properties.getProperty("serverAddress");
        /*
        设置参数
         */
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        //session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
        Message message = createMimeMessage(session, myEmailAccount, receiveMailAccount, content);
        Transport transport = session.getTransport();
        transport.connect(myEmailAccount, myEmailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    /**
     * 创建邮件
     *
     * @param session
     * @param sendMail
     * @param receiveMail
     * @param content
     * @return
     * @throws Exception
     */
    public static Message createMimeMessage(Session session, String sendMail, String receiveMail, String content) throws Exception {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, "TravelBetter", "UTF-8"));
        message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(sendMail));
        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(receiveMail));
        message.setSubject("激活邮件");
//        message.setContent("您正在绑定此邮箱地址至TravelBetter,点击链接完成验证<a href='"+token+"测试地址"+"'>。如果这不是您的操作，请忽略。", "text/html;charset=UTF-8");
        message.setContent("亲爱的用户,您好<br/><br/>请点击下面的链接完成验证：<br/><br/><a href='"+serverAddress+content+"'>"+serverAddress+content+"</a><br/><br/>如果链接无法点击，请复制上面的链接到您的浏览器地址栏打开<br/><br/>为了保障您的账号安全，请尽快验证。<br/><br/>欢迎使用TravelBetter！<br/><br/>系统自动发送的邮件,请不要直接回复。<br/><br/>如果这不是您的操作，请忽略", "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
}
