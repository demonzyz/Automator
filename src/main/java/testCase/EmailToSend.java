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
	static String subject = "只包含文本的简单邮件【打折钜惠";
	private Properties props;			//系统属性    
	
	private MimeMessage mimeMsg;
	private Multipart mp; 
	private String from = null;			//发件人邮箱地址
	private String senderName = null;	//发件人显示名称
	private String setTo = null;		//收件人邮箱地址，多个地址时以英文逗号分隔
	private String copyTo = null;		//抄送人邮箱地址，多个地址时以英文逗号分隔
	private String smtp = null;			//发件人SMTP服务器地址
	private String filePath = null;		//附件的路径
	
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
		
		//创建邮件对象
		MimeMessage message = new MimeMessage(session);
		
		//创建邮件的发件人
		
		message.setFrom(new InternetAddress(sendMail));
		
		//指明邮件的收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail));
		
		//邮件的标题
		
		message.setSubject("只包含文本的简单邮件【打折钜惠");
		
		//邮件的文本内容
		
		message.setContent("你好啊,XX用户你好, 今天全场5折, 快来抢购, 错过今天再等一年。。。", "text/html;charset=UTF-8");
		
		//返回创建好的邮件对象

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
			System.out.println("邮件标题输入有误！");
			return false;
		}
	}
	
    /**  
     * 邮件正文
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
			System.out.println("邮件正文输入有误！");
		}
		return false;
	}
	
    /**  
     * 添加附件 
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
        	System.out.println("增加邮件附件："+filePath+"发生错误！");
            return false;   
        }   
    } 


	
	protected boolean setTo() {
		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(setTo));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("收件人地址设置有误！");
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
        System.out.println("设置发信人异常 ！");
            return false;   
        }   
    }  
	
    /**  
     * 设置抄送人 
     * @param copyto String   
     */   
  	protected boolean setCopyTo()   
    {    
        try{   
        mimeMsg.setRecipients(Message.RecipientType.CC,InternetAddress.parse(copyTo));   
        return true;   
        }   
        catch(Exception e) {
        System.out.println("设置抄送人 异常 ！ ");
        	return false;
        } 
    } 
  	
	/** 
     * 设置邮件发送服务器 
     * @param hostName String  
     */  
  	protected void setSmtpHost() {   
        if(props == null)  
            props = System.getProperties(); //获得系统属性对象    
        props.put("mail.smtp.host",smtp); //设置SMTP主机   
    }
	
    /** 
     * 设置SMTP是否需要验证 
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
