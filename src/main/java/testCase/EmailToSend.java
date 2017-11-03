package testCase;

import java.lang.reflect.Field;
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
import javax.mail.internet.MimeMultipart;
import util.Parameter;

public class EmailToSend {
	
	private String subject = null;
	private MimeMessage mimeMsg; 		//MIME邮件对象   
	private Session session; 			//邮件会话对象   
	private Properties props;			//系统属性    
	private Multipart mp; 				//Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象   
	private String from = null;			//发件人邮箱地址
	private String senderName = null;	//发件人显示名称
	private String setTo = null;		//收件人邮箱地址，多个地址时以英文逗号分隔
	private String copyTo = null;		//抄送人邮箱地址，多个地址时以英文逗号分隔
	private String smtp = null;			//发件人SMTP服务器地址
	private String filePath = null;		//附件的路径
	private String userName = null;		//发件人邮箱用户名
	private String password = null;		//发件人邮箱密码
	
	/** 
	* Constructor 
	* @param parameters 邮件发送的配置参数
	*/  
	public EmailToSend(Parameter parameters){   
		init(parameters);		
	}
	
    @SuppressWarnings("null")
	protected void init(Parameter parameters){
		//检查是否缺少发送邮件的必要参数
		String[] argNames= {"smtp", "from", "setTo", "userName", "password","subject"};
		for(String name:argNames){
			String value = parameters.get(name);
			if(value==null || value.equals("")){
				System.out.println("缺少发送邮件的必要参数:"+name);
			}
		}
		//通过反射初始化参数列表
		for(String name :parameters.asMap().keySet()){
			String value = parameters.get(name);
			if(value!=null || !value.equals("")){
				try {
					Field field = this.getClass().getDeclaredField(name);
					field.set(this, value);				
				} catch (NoSuchFieldException e) {
					System.out.println("EmailSender不存在如下参数名："+name);
				} catch (SecurityException e) {
					System.out.println("初始化EmailSender 参数异常!");
				} catch (IllegalArgumentException e) {
					System.out.println("EmailSender 以下参数，参数值类型不正确："+name);
				} catch (IllegalAccessException e) {
					System.out.println("初始化EmailSender 参数异常!");
				}			   
			}
		}	
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
		try {
			MimeBodyPart textBody = new MimeBodyPart();
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


    /**  
     * 设置收件人
     * @param 
     */  
	protected boolean setTo() {
		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(setTo));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("收件人地址设置有误！");
			return false;
		} 	
	}
	
    /**  
     * 设置发件人
     * @param 
     */  
  	protected boolean setFrom() {   
        try{   
        	if(senderName == null || senderName.equals("")){
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
        if(props == null) {
         	props = System.getProperties(); 
        }
        if(need){   
            props.put("mail.smtp.auth","true");   
        }else{   
            props.put("mail.smtp.auth","false");   
        }   
    }  	
  	
    /** 
     * 创建MIME对象 
     * @param  
     */  
  	protected boolean createMimeMessage() {
  		try {
			session = Session.getDefaultInstance(props, null);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Session对象创建失败！");
			return false;
		}
  		try {
			mimeMsg = new MimeMessage(session);
			mp = new MimeMultipart();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("message创建失败");
			return false;
		}
    } 

  	protected boolean sendOut(boolean needCopyTo) {
  		try {
  			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			Session session = Session.getDefaultInstance(props);
			session.setDebug(true);
			Transport transport = session.getTransport("smtp");
			transport.connect((String)props.getProperty("mail.smtp.host"), userName, password);
			transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
			if (needCopyTo) {
				transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.CC));
			}
			transport.close();
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("邮件发送失败！");
			return false;
		}
  		
		
	}
  	
	/** 
     * 调用sendOut方法完成邮件发送
     * @throws MessagingException 
     */  
    public boolean send(String text, String encoding){  
    		boolean needCopyto = true;	   
		setSmtpHost();   		//设置smtp服务地址
	    createMimeMessage();  	//创建会话
        setNeedAuth(true); 		//设置验证  
        if(!setSubject()) return false;  	//设置主题
        if(!setBody(text, encoding))return false;  		//设置正文
        
        if(!(filePath==null || filePath.equals(""))){		//设置附件
        	if(!addFileAffix()) return false;   
        }
        
        if(!setTo()) return false; 	//设置收件人
        
        if(copyTo==null || copyTo.equals("")){	//设置抄送人
        		needCopyto = false;	        	
        }else{
        	if(!setCopyTo()) return false; 
        }
        
        if(!setFrom()) return false;  	//设置发件人

        if(!sendOut(needCopyto)) return false;  //调用发送函数       
        
        return true;             
    } 	
}
