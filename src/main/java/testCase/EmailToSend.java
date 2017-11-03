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
	private MimeMessage mimeMsg; 		//MIME�ʼ�����   
	private Session session; 			//�ʼ��Ự����   
	private Properties props;			//ϵͳ����    
	private Multipart mp; 				//Multipart����,�ʼ�����,����,���������ݾ���ӵ����к�������MimeMessage����   
	private String from = null;			//�����������ַ
	private String senderName = null;	//��������ʾ����
	private String setTo = null;		//�ռ��������ַ�������ַʱ��Ӣ�Ķ��ŷָ�
	private String copyTo = null;		//�����������ַ�������ַʱ��Ӣ�Ķ��ŷָ�
	private String smtp = null;			//������SMTP��������ַ
	private String filePath = null;		//������·��
	private String userName = null;		//�����������û���
	private String password = null;		//��������������
	
	/** 
	* Constructor 
	* @param parameters �ʼ����͵����ò���
	*/  
	public EmailToSend(Parameter parameters){   
		init(parameters);		
	}
	
    @SuppressWarnings("null")
	protected void init(Parameter parameters){
		//����Ƿ�ȱ�ٷ����ʼ��ı�Ҫ����
		String[] argNames= {"smtp", "from", "setTo", "userName", "password","subject"};
		for(String name:argNames){
			String value = parameters.get(name);
			if(value==null || value.equals("")){
				System.out.println("ȱ�ٷ����ʼ��ı�Ҫ����:"+name);
			}
		}
		//ͨ�������ʼ�������б�
		for(String name :parameters.asMap().keySet()){
			String value = parameters.get(name);
			if(value!=null || !value.equals("")){
				try {
					Field field = this.getClass().getDeclaredField(name);
					field.set(this, value);				
				} catch (NoSuchFieldException e) {
					System.out.println("EmailSender���������²�������"+name);
				} catch (SecurityException e) {
					System.out.println("��ʼ��EmailSender �����쳣!");
				} catch (IllegalArgumentException e) {
					System.out.println("EmailSender ���²���������ֵ���Ͳ���ȷ��"+name);
				} catch (IllegalAccessException e) {
					System.out.println("��ʼ��EmailSender �����쳣!");
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
			System.out.println("�ʼ�������������");
			return false;
		}
	}
	
    /**  
     * �ʼ�����
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


    /**  
     * �����ռ���
     * @param 
     */  
	protected boolean setTo() {
		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(setTo));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�ռ��˵�ַ��������");
			return false;
		} 	
	}
	
    /**  
     * ���÷�����
     * @param 
     */  
  	protected boolean setFrom() {   
        try{   
        	if(senderName == null || senderName.equals("")){
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
     * ����MIME���� 
     * @param  
     */  
  	protected boolean createMimeMessage() {
  		try {
			session = Session.getDefaultInstance(props, null);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Session���󴴽�ʧ�ܣ�");
			return false;
		}
  		try {
			mimeMsg = new MimeMessage(session);
			mp = new MimeMultipart();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("message����ʧ��");
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
			System.out.println("�ʼ�����ʧ�ܣ�");
			return false;
		}
  		
		
	}
  	
	/** 
     * ����sendOut��������ʼ�����
     * @throws MessagingException 
     */  
    public boolean send(String text, String encoding){  
    		boolean needCopyto = true;	   
		setSmtpHost();   		//����smtp�����ַ
	    createMimeMessage();  	//�����Ự
        setNeedAuth(true); 		//������֤  
        if(!setSubject()) return false;  	//��������
        if(!setBody(text, encoding))return false;  		//��������
        
        if(!(filePath==null || filePath.equals(""))){		//���ø���
        	if(!addFileAffix()) return false;   
        }
        
        if(!setTo()) return false; 	//�����ռ���
        
        if(copyTo==null || copyTo.equals("")){	//���ó�����
        		needCopyto = false;	        	
        }else{
        	if(!setCopyTo()) return false; 
        }
        
        if(!setFrom()) return false;  	//���÷�����

        if(!sendOut(needCopyto)) return false;  //���÷��ͺ���       
        
        return true;             
    } 	
}
