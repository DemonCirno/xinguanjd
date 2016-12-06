package com.xinguan.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @ClassName: Sendmail
 * @Description: ����Email
 * @author: �°�����
 * @date: 2015-1-12 ����9:42:56
 *
 */ 
public class SendEmailUtil {
	
    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.163.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //ʹ��JavaMail�����ʼ���5������
        
        //1������session
        Session session = Session.getInstance(prop);
        
        //����Session��debugģʽ�������Ϳ��Բ鿴��������Email������״̬
        session.setDebug(true);
        
        //2��ͨ��session�õ�transport����
        Transport ts = session.getTransport();
        
        //3��ʹ��������û��������������ʼ��������������ʼ�ʱ����������Ҫ�ύ������û����������smtp���������û��������붼ͨ����֤֮����ܹ����������ʼ����ռ��ˡ�
        ts.connect("smtp.163.com", "m15204696480_1@163.com", "weiyao1996");
        
        //4�������ʼ�
        Message message = createSimpleMail(session);
        
        //5�������ʼ�
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }
    
    /**
    * @Method: createSimpleMail
    * @Description: ����һ��ֻ�����ı����ʼ�
    * @Anthor:�°�����
    *
    * @param session
    * @return
    * @throws Exception
    */ 
    public static MimeMessage createSimpleMail(Session session)
            throws Exception {
    	
        //�����ʼ�����
        MimeMessage message = new MimeMessage(session);
        
        //ָ���ʼ��ķ�����
        message.setFrom(new InternetAddress("m15204696480_1@163.com"));
        
        //ָ���ʼ����ռ��ˣ����ڷ����˺��ռ�����һ���ģ��Ǿ����Լ����Լ���
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("demon.cirno@outlook.com"));
        //�ʼ��ı���
        message.setSubject("ֻ�����ı��ļ��ʼ�");
        //�ʼ����ı�����
        message.setContent("��ð���", "text/html;charset=UTF-8");
        //���ش����õ��ʼ�����
        return message;
    }
}