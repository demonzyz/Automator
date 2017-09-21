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
//	private MimeMessage mimeMsg; 		//MIME�ʼ�����
//	
//	private Session session;			//�ʼ��Ự����
//	
//	private Properties properties;		//ϵͳ����
//	
//	private Multipart multipart;		//Multipart�����ʼ����ݣ����������ݾ���ӵ����к�������Mimemessage��Ϣ
//	
//	private String smtp = null;			//������SMTP��������ַ
//	
//	private String subject = null; 		//�ʼ�����
//	
//	private String from = null;			//����������
//	
//	private String senderName = null;	//��������ʾ����
//	
//	private String setTo = null;		//����������ַ�������ַʱ��Ӣ�Ķ��ŷָ�
//	
//	private String copyTo = null;		//�����������ַ�������ַʱ��Ӣ�Ķ��ŷָ�
//	
//	private String userName = null;		//�����������û���
//	
//	private String passWord = null;		//��������������
//	
//	private String filePath = null;		//����·��
//	
//	/*
//	 * Constructor
//	 * @param parameters �ʼ����͵����ò���
//	 */
//	public EmailSender(Parameter parameters) {
//		init(parameters);
//	}
//	
//	/*
//	 * ����sendOut��������ʼ�����
//	 * @throws MessagingException
//	 */
//	public boolean send(String text,String encoding) {
//		boolean needCopyto = true;
//		
//		setSmtpHost();//����SMTP�����ַ 
//		
//		setNeedAuth(true); //������֤
//		
//		if(!setSubject()) return false; //��������
//		
//		if(!setBody(text,encoding)) return false; //��������
//		
//		if(!(filePath == null || filePath.equals(""))) { //���ø���
//			
//			if (!addFileAffix()) return false;
//		}
//		
//		if(!setTo()) return false; //�����ռ���
//		
//		if(copyTo==null || copyTo.equals("")){ //���ó�����
//			
//			needCopyto = false;
//			
//		}else {
//			if (!copyTo()) return false;
//		}
//		
//		if(!setFrom()) return false;	//���÷�����
//		
//		if(!sendOut(needCopyto)) return false; //���÷��ͺ���
//		
//		return true;
//	}
//	
//	@SuppressWarnings 
//	protected void init(Parameter parameters){
//		//����Ƿ�ȱ�ٷ����ʼ��ı�Ҫ����
//		
//		String[] argNames = {"smtp","from","setTo","userName","passWord"};
//		
//		for (String name : argNames) {
//			
//			String value = parameters.get(name);
//			
//			if (value==null || value.equals("")) {
//				
//				System.out.println("ȱ�ٷ����ʼ��ı�Ҫ������" + name);
//				
//			}
//		}
//		
//		//ͨ�������ʼ�������б�
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
//					System.out.println("EmailSender���������²�����" + name);
//				}catch (SecurityException e) {
//					System.out.println("��ʼ��EmailSender�����쳣");
//				}
//				
//				
//			}
//		}
//	}
//	
//	/*
//	 * �����ʼ����ͷ�����
//	 * @param hostName string
//	 */
//	
//	protected void setSmtpHost() {
//		
//		Log.info("����ϵͳ���ԣ�mail.smtp.host = " + smtp);
//		
//		if (properties == null) {
//			
//			properties = System.getProperties();//���ϵͳ���Զ���
//			
//			properties.put("mail.smtp.host", smtp);
//			
//		}
//	}
//	
//	/*
//	 * �ʼ�����
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
//			System.out.println("�����ʼ�����ʱ��������");
//			
//			return false;
//			
//		}
//	}
//	
//	/*
//	 * ��Ӹ���
//	 * @param filename string
//	 */
//	public boolean addFileAffix() {
//		
//		Log.info("�����ʼ�������" + filePath);
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
//			System.out.println("�����ʼ�������" + filePath + "��������!");
//			
//			return false;
//			
//		}
//	}
//	
//	/*
//	 * ����MIME�ʼ�����
//	 * @return
//	 */
//	protected boolean createMimeMessage() {
//		try {
//			Log.info("׼����ȡ�ʼ��Ự����");
//			session = Session.getDefaultInstance(properties,null);
//		} catch (Exception e) {
//			System.out.println("��ȡ�ʼ��Ự����ʱ��������");
//			return false;
//		}
//		return true;
//	}
//	
//	/*
//	 * @throw MessagingException
//	 * �����ʼ�����
//	 */
//	protected boolean sendOut(boolean needCopyto) {
//		try {
//			mimeMsg.setContent(multipart);
//			
//			mimeMsg.saveChanges();
//			
//			Log.info("���ڷ����ʼ�......");
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
//			Log.info("�����ʼ��ɹ���");
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
