package util;



import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.remote.DesiredCapabilities;

import base.AppConfig;

public class Parameter extends DesiredCapabilities{
//	private final Map<String, Object> capabilities = new HashMap<>();
//
//	public void setCapability(String capabilityName, String value) {
//		capabilities.put(capabilityName, value);
//	}
// 
//	String[] argNames= {"smtp", "from", "setTo", "userName", "password"};
//
// 
//	public void setSmtp() {
//	//  setCapability("mail.smtp.host", AppConfig.SmtpServer);
//		setCapability("mail.smtp.host", "smtp.163.com");
//	}
// 
//	public void setProtocol() {
//	//  setCapability("mail.smtp.host", AppConfig.SmtpProtocol);
//		setCapability("mail.transport.protocol", "smtp");
//	}
//	
//	public void setAuth() {
//	//  setCapability("mail.smtp.host", AppConfig.SmtpAuth);
//		setCapability("mail.smtp.auth", "true");
//	}
// 
//	public void setSmtpAuth(boolean need) {
//		if (need) {
//			setCapability("mail.smtp.auth", true);
//		}else {
//			setCapability("mail.smtp.auth", false);
//		}
//  
//	}
// 
//	public void setFrom(String from) {
//		setCapability("", from);
//	}
// 
//	public void setSetTo() {
//		setCapability("mail.smtp.auth", "true");
//	}
// 
//	public void setUserName() {
//		setCapability("mail.smtp.auth", "true");
//	}
// 
//	public void setPassWord() {
//		setCapability("mail.smtp.auth", "true");
//	}
// 
//	public String getSmtp() {
//		Object smtp = capabilities.get("mail.smtp.host");
//		return smtp == null ? "mail.hostÎª¿Õ":smtp.toString();
//	}
// 
//	public String getProtocol() {
//		Object smtp = capabilities.get("mail.transport.protocol");
//		return smtp == null ? "mail.transport.protocolÎª¿Õ":smtp.toString();
//	}
//	
//	public String getAuth() {
//		Object smtp = capabilities.get("mail.smtp.auth");
//		return smtp == "true" ? "true":"fasle";
//	}
//	
//	public String get(String name){
//		Object cap = getCapability(name);	
//		if(cap==null){
//			return null;
//		}else return cap.toString();	
//	}
// 
//	public static void main(String[] args) {
//		Parameter parameter = new Parameter();
//		parameter.setSmtp();
//		parameter.setProtocol();
//		parameter.setAuth();
//		String value = parameter.getSmtp();
//		String vString	= parameter.getProtocol();
//		String value1 = parameter.getAuth();
//		System.out.println(value);
//		System.out.println(vString);
//		System.out.println(value1);
//	}
// 
	
	public void set(String name, String value){
		setCapability(name, value);
	}
	
	public void set(String name, boolean value){
		setCapability(name, value);
	}
	
	public void set(String name, Object value){
		setCapability(name, value);
	}
	
	public String get(String name){
		Object cap = getCapability(name);	
		if(cap==null){
			return null;
		}else return cap.toString();	
	}
	
	public boolean isExist(String name){
		Object cap = getCapability(name);
	    if (cap == null) {
	        return false;
	    }else {
	    	return true;  
	    }		
	}
}