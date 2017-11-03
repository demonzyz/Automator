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
		MimeMessage message = new MimeMessage(session); //创建邮件对象
		message.setFrom(new InternetAddress(sendMail)); //创建邮件的发件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail)); //指明邮件的收件人
		message.setSubject("只包含文本的简单邮件【打折钜惠"); //邮件的标题
		message.setContent("你好啊,XX用户你好, 今天全场5折, 快来抢购, 错过今天再等一年。。。", "text/html;charset=UTF-8"); //邮件的文本内容
		return message; //返回创建好的邮件对象
	}

}
