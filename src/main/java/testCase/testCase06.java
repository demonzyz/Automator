package testCase;

import util.Parameter;

public class testCase06 {
	
	public static void main(String[] args) {
		Parameter  parameter = new Parameter();

//		parameter.set("mail.smtp.host", "smtp.163.com");
//		parameter.set("mail.transport.protocol", "smtp");
//		parameter.set("mail.smtp.auth", "true");
		parameter.set("senderName", "测试邮件的发件人姓名");
		parameter.set("smtp", "smtp.163.com");
		parameter.set("subject", "测试邮件的主题");
		parameter.set("from", "nnnnnn13@163.com");
		parameter.set("setTo", "nnnnnn13@163.com");
		parameter.set("userName", "nnnnnn13");
		parameter.set("password", "q12345678");
		
		EmailToSend emailToSend = new EmailToSend(parameter);
		emailToSend.send("nihao", "text/html;charset=UTF-8");
		
		
	}

}
