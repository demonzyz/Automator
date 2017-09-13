package TestAutomator.Test;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailDemo {
	
	public static void main(String[] args) throws Exception  {
		
		Properties properties = new Properties();
		
		properties.setProperty("mail.host", "smtp.163.con");
		
		properties.setProperty("mail.transport.protocol", "smtp");
		
		properties.setProperty("mail.smtp.auth", "true");
		
		//ʹ��JavaMail�����ʼ���5������
		
		//1������session
		
		Session session = Session.getInstance(properties);
		
		//����session��debugģʽ�������Ϳ��Բ鿴��������Email������״̬
		
		session.setDebug(true);
		
		//2��ͨ��session�õ�transport����
		
		Transport transport = session.getTransport();
		
		//3��ʹ��������û����������������ʼ��������������ʼ�ʱ����������Ҫ�ύ������û����������smtp���������û��������붼
		
		transport.connect("smtp.163.com", "nnnnnn13", "q12345678");
		
		//4�������ʼ�
		
		Message message = createSimpleMail(session);
		
		//5�������ʼ�
		
		transport.sendMessage(message, message.getAllRecipients());
		
		transport.close();
	}

	private static MimeMessage createSimpleMail(Session session) 
		
			throws Exception {
		
		//�����ʼ�����
		MimeMessage message = new MimeMessage(session);
		
		//�����ʼ��ķ�����
		
		message.setFrom(new InternetAddress("nnnnnn13@163.com"));
		
		//ָ���ʼ����ռ���
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("nnnnnn13@163.com"));
		
		//�ʼ��ı���
		
		message.setSubject("ֻ�����ı��ļ��ʼ�");
		
		//�ʼ����ı�����
		
		message.setContent("��ð�", "text/html;charset=UTF-8");
		
		//���ش����õ��ʼ�����

		return message;
	}
}
