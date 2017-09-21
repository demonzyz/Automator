//package util;
//
//import java.lang.reflect.Field;
//import java.util.Properties;
//import javax.activation.DataHandler;
//import javax.activation.FileDataSource;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.Multipart;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//
//
//public class EmailSender {
//	
//	public static String SMTP = "smtp";
//	
//	public static String SUBJECT = "subject";
//	
//	public static String FROM = "from";
//	
//	public static String SET_TO = "setTo";
//	
//	public static String COPY_TO = "copyTo";
//	
//	public static String USER_NAME = "userName";
//	
//	public static String PASSWORD = "passWord";
//	
//	public static String FILE_PATH = "filePath";
//	
//	private MimeMessage mimeMsg; 		//MIME邮件对象
//	
//	private Session session;			//邮件会话对象
//	
//	private Properties properties;		//系统属性
//	
//	private Multipart multipart;		//Multipart对象，邮件内容，附件等内容军添加到其中后在生成Mimemessage消息
//	
//	private String smtp = null;			//发件人SMTP服务器地址
//	
//	private String subject = null; 		//邮件主题
//	
//	private String from = null;			//发件人邮箱
//	
//	private String senderName = null;	//发件人显示名称
//	
//	private String setTo = null;		//抽检人邮箱地址，多个地址时以英文逗号分割
//	
//	private String copyTo = null;		//抄送人邮箱地址，多个地址时以英文逗号分割
//	
//	private String userName = null;		//发件人邮箱用户名
//	
//	private String passWord = null;		//发件人邮箱密码
//	
//	private String filePath = null;		//附件路径
//	
//	/*
//	 * Constructor
//	 * @param parameters 邮件发送的配置参数
//	 */
//	public EmailSender(Parameter parameters) {
//		init(parameters);
//	}
//	
//	/*
//	 * 调用sendOut方法完成邮件发送
//	 * @throws MessagingException
//	 */
//	public boolean send(String text,String encoding) {
//		boolean needCopyto = true;
//		
//		setSmtpHost();//设置SMTP服务地址 
//		
//		setNeedAuth(true); //设置验证
//		
//		if(!setSubject()) return false; //设置主题
//		
//		if(!setBody(text,encoding)) return false; //设置正文
//		
//		if(!(filePath == null || filePath.equals(""))) { //设置附件
//			
//			if (!addFileAffix()) return false;
//		}
//		
//		if(!setTo()) return false; //设置收件人
//		
//		if(copyTo==null || copyTo.equals("")){ //设置抄送人
//			
//			needCopyto = false;
//			
//		}else {
//			if (!copyTo()) return false;
//		}
//		
//		if(!setFrom()) return false;	//设置发件人
//		
//		if(!sendOut(needCopyto)) return false; //条用发送函数
//		
//		return true;
//	}
//	
//	@SuppressWarnings 
//	protected void init(Parameter parameters){
//		//检查是否缺少发送邮件的必要参数
//		
//		String[] argNames = {"smtp","from","setTo","userName","passWord"};
//		
//		for (String name : argNames) {
//			
//			String value = parameters.get(name);
//			
//			if (value==null || value.equals("")) {
//				
//				System.out.println("缺少发送邮件的必要参数：" + name);
//				
//			}
//		}
//		
//		//通过反射初始化参数列表
//		
//		for (String name : parameters.asMap().keySet()) {
//			
//			String value = parameters.get(name);
//			
//			if (value==null || value.equals("")) {
//				
//				try {
//					Field field = this.getClass().getDeclaredField(name);
//					
//					field.set(this, value);
//					
//				} catch (NoSuchFieldException e) {
//					System.out.println("EmailSender不存在如下参数名" + name);
//				}catch (SecurityException e) {
//					System.out.println("初始化EmailSender参数异常");
//				}
//				
//				
//			}
//		}
//	}
//	
//	/*
//	 * 设置邮件发送服务器
//	 * @param hostName string
//	 */
//	
//	protected void setSmtpHost() {
//		
//		Log.info("设置系统属性：mail.smtp.host = " + smtp);
//		
//		if (properties == null) {
//			
//			properties = System.getProperties();//获得系统属性对象
//			
//			properties.put("mail.smtp.host", smtp);
//			
//		}
//	}
//	
//	/*
//	 * 邮件正文
//	 * @param mailBody String
//	 */
//	public boolean setBody(String text,String encoding) {
//		
//		try {
//			
//			MimeBodyPart textBody = new MimeBodyPart();
//			
//			textBody.setContent(text,"text/html;charset=gb2312");
//			
//			multipart.addBodyPart(textBody);
//			
//			return true;
//			
//		} catch (Exception e) {
//			
//			System.out.println("设置邮件正文时发生错误！");
//			
//			return false;
//			
//		}
//	}
//	
//	/*
//	 * 添加附件
//	 * @param filename string
//	 */
//	public boolean addFileAffix() {
//		
//		Log.info("增加邮件附件：" + filePath);
//		
//		try {
//			
//			BodyPart bp = new MimeBodyPart();
//			
//			FileDataSource fileds = new FileDataSource(filePath);
//			
//			bp.setDataHandler(new DataHandler(fileds));
//			
//			bp.setFileName(fileds.getName());
//			
//			multipart.addBodyPart(bp);
//			
//			return true;
//			
//		} catch (Exception e) {
//			
//			System.out.println("增加邮件附件：" + filePath + "发生错误!");
//			
//			return false;
//			
//		}
//	}
//	
//	/*
//	 * 创建MIME邮件对象
//	 * @return
//	 */
//	protected boolean createMimeMessage() {
//		try {
//			Log.info("准备获取邮件会话对象！");
//			session = Session.getDefaultInstance(properties,null);
//		} catch (Exception e) {
//			System.out.println("获取邮件会话对象时发生错误！");
//			return false;
//		}
//		return true;
//	}
//	
//	/*
//	 * @throw MessagingException
//	 * 发送邮件函数
//	 */
//	protected boolean sendOut(boolean needCopyto) {
//		try {
//			mimeMsg.setContent(multipart);
//			
//			mimeMsg.saveChanges();
//			
//			Log.info("正在发送邮件......");
//			
//			Session mailsession = Session.getInstance(properties,null);
//			
//			Transport transport = mailsession.getTransport();
//			
//			transport.connect((String) properties.get("mail.smtp.host"), userName, passWord);
//			
//			transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
//			
//			if (needCopyto) {
//				
//				transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.CC));
//			}
//			
//			Log.info("发送邮件成功！");
//			
//			transport.close();
//			
//			return true;
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//	
//	
//	
//}
