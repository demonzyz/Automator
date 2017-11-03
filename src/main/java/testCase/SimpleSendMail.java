package testCase;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SimpleSendMail {
	static String sendMail = "nnnnnn13@163.com";	
	static String receiveMail = "nnnnnn13@163.com";
	
	public static void main(String[] args)  throws Exception{
		
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", "smtp.163.com");
		properties.setProperty("mail.transport.protocol", "smtp");	
		properties.setProperty("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(properties);
		session.setDebug(true);
		
		MimeMessage message = createSimpleMail(session,sendMail,receiveMail);
		Transport transport = session.getTransport();
		transport.connect("smtp.163.com", "nnnnnn13", "q12345678");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
	
	private static MimeMessage createSimpleMail(Session session,String sendMail,String receiveMail) throws Exception {	
		MimeMessage message = new MimeMessage(session); //�����ʼ�����
		message.setFrom(new InternetAddress(sendMail)); //�����ʼ��ķ�����
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail)); //ָ���ʼ����ռ���
		message.setSubject("ֻ�����ı��ļ��ʼ��������һ�"); //�ʼ��ı���
		message.setContent("��ð�,XX�û����, ����ȫ��5��, ��������, ��������ٵ�һ�ꡣ����", "text/html;charset=UTF-8"); //�ʼ����ı�����
		return message; //���ش����õ��ʼ�����
	}

}
