package util;



import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.remote.DesiredCapabilities;

import base.AppConfig;

public class Parameter extends DesiredCapabilities{
 private final Map<String, Object> capabilities = new HashMap<>();

 public void setCapability(String capabilityName, String value) {
  capabilities.put(capabilityName, value);
 }
 
 String[] argNames= {"smtp", "from", "setTo", "userName", "password"};

 
 public void setSmtp() {
//  setCapability("mail.smtp.host", AppConfig.SMTPserver);
  setCapability("mail.smtp.host", "smtp.163.com");
 }
 
 public void setProtocol() {
  setCapability("mail.transport.protocol", "smtp");
 }
 
 public void setSmtpAuth(boolean need) {
  if (need) {
   setCapability("mail.smtp.auth", true);
  }else {
   setCapability("mail.smtp.auth", false);
  }
  
 }
 
 public void setFrom(String from) {
  setCapability("", from);
 }
 
 public void setSetTo() {
  setCapability("mail.smtp.auth", "true");
 }
 
 public void setUserName() {
  setCapability("mail.smtp.auth", "true");
 }
 
 public void setPassWord() {
  setCapability("mail.smtp.auth", "true");
 }
 
 public String getSmtp() {
  Object smtp = capabilities.get("mail.host");
  return smtp == null ? "mail.hostÎª¿Õ":smtp.toString();
  
 }
 
 public static void main(String[] args) {
  Parameter parameter = new Parameter();
  parameter.setSmtp();
  String value = parameter.getSmtp();
  System.out.println(value);
 }
 

}