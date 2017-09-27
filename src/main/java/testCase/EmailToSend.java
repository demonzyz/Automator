package testCase;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;



public class EmailToSend {
	
	static String sendMail = "nnnnnn13@163.com";
	
	static String receiveMail = "nnnnnn13@163.com";
	static String subject = "ֻ�����ı��ļ��ʼ��������һ�";
	private Properties props;			//ϵͳ����    
	
	private MimeMessage mimeMsg;
	private Multipart mp; 
	private String from = null;			//�����������ַ
	private String senderName = null;	//��������ʾ����
	private String setTo = null;		//�ռ��������ַ�������ַʱ��Ӣ�Ķ��ŷָ�
	private String copyTo = null;		//�����������ַ�������ַʱ��Ӣ�Ķ��ŷָ�
	private String smtp = null;			//������SMTP��������ַ
	private String filePath = null;		//������·��
	
	public static void main(String[] args)  throws Exception{
		Properties properties = new Properties();
		properties.setProperty("smtp", "smtp.163.com");
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
	
	private static MimeMessage createSimpleMail(Session session,String sendMail,String receiveMail) 
	
			throws Exception {
		
		//�����ʼ�����
		MimeMessage message = new MimeMessage(session);
		
		//�����ʼ��ķ�����
		
		message.setFrom(new InternetAddress(sendMail));
		
		//ָ���ʼ����ռ���
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail));
		
		//�ʼ��ı���
		
		message.setSubject("ֻ�����ı��ļ��ʼ��������һ�");
		
		//�ʼ����ı�����
		
		message.setContent("��ð�,XX�û����, ����ȫ��5��, ��������, ��������ٵ�һ�ꡣ����", "text/html;charset=UTF-8");
		
		//���ش����õ��ʼ�����

		return message;
	}
	
	protected boolean setSubject() {
		try {
			if (subject != null || !subject.equals("")) {
				mimeMsg.setSubject(subject);
			}else mimeMsg.setSubject(" ");
				return true;		
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("�ʼ�������������");
			return false;
		}
	}
	
    /**  
     * �ʼ�����
     * @param String text,String encoding   
     */ 
	protected boolean setBody(String text,String encoding) {
		MimeBodyPart textBody = new MimeBodyPart();
		try {
			textBody.setContent(text,encoding);
			mp.addBodyPart(textBody);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("�ʼ�������������");
		}
		return false;
	}
	
    /**  
     * ��Ӹ��� 
     * @param filename String  
     */   
  	protected boolean addFileAffix() {   	      
 
        try{   
            BodyPart bp = new MimeBodyPart();   
            FileDataSource fileds = new FileDataSource(filePath);   
            bp.setDataHandler(new DataHandler(fileds));   
            bp.setFileName(fileds.getName());   
            mp.addBodyPart(bp);   	              
            return true;   
        } catch(Exception e){  
        	System.out.println("�����ʼ�������"+filePath+"��������");
            return false;   
        }   
    } 


	
	protected boolean setTo() {
		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(setTo));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�ռ��˵�ַ��������");
		} 
		return false;
	}
	
  	protected boolean setFrom() {   
        try{   
        	if(senderName ==null || senderName.equals("")){
        		mimeMsg.setFrom(new InternetAddress(from)); 
        	}else mimeMsg.setFrom(new InternetAddress(from, senderName));
            return true;   
        } catch(Exception e) {
        System.out.println("���÷������쳣 ��");
            return false;   
        }   
    }  
	
    /**  
     * ���ó����� 
     * @param copyto String   
     */   
  	protected boolean setCopyTo()   
    {    
        try{   
        mimeMsg.setRecipients(Message.RecipientType.CC,InternetAddress.parse(copyTo));   
        return true;   
        }   
        catch(Exception e) {
        System.out.println("���ó����� �쳣 �� ");
        	return false;
        } 
    } 
  	
	/** 
     * �����ʼ����ͷ����� 
     * @param hostName String  
     */  
  	protected void setSmtpHost() {   
        if(props == null)  
            props = System.getProperties(); //���ϵͳ���Զ���    
        props.put("mail.smtp.host",smtp); //����SMTP����   
    }
	
    /** 
     * ����SMTP�Ƿ���Ҫ��֤ 
     * @param need 
     */  
  	protected void setNeedAuth(boolean need) {   
        if(props == null) props = System.getProperties();   
        if(need){   
            props.put("mail.smtp.auth","true");   
        }else{   
            props.put("mail.smtp.auth","false");   
        }   
    }  	

  	
}
