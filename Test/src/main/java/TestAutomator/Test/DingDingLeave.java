package TestAutomator.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;

public class DingDingLeave {
	
	public static void main(String[] args){
		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");//��䲻�Ǳ����
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "rimet_10006126.apk");
		capabilities.setCapability("deviceName","Redmi Note 3");
		capabilities.setCapability("platformVersion", "6.0.1");
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("appPackage", "com.alibaba.android.rimet");
		capabilities.setCapability("appActivity", "com.alibaba.android.rimet.biz.SplashActivity");
		capabilities.setCapability("noReset","true");
        capabilities.setCapability("fullReset","true");
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);

		try {
			AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			/*
			 С�ǵ��˻�
			 */
			driver.findElementById("com.alibaba.android.rimet:id/et_phone_input").clear();
			driver.findElementById("com.alibaba.android.rimet:id/et_phone_input").sendKeys("13581975037");
			driver.findElementById("com.alibaba.android.rimet:id/et_pwd_login").sendKeys("5211314");			
			driver.findElementById("com.alibaba.android.rimet:id/btn_next").click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.findElementByAndroidUIAutomator("new UiSelector().text(\"����\")").click();
			int width=driver.manage().window().getSize().width;
	        int height=driver.manage().window().getSize().height;  
	        driver.swipe(width/2,height*3/4, width/2,height/4, 1000);
	        driver.swipe(width/2,height*3/4, width/2,height/4, 1000);
	        driver.tap(width, 20, 20, width);
	        
//			driver.findElementById("com.alibaba.android.rimet:id/rl_setting").click();
//			driver.findElementByAndroidUIAutomator("new UiSelector().text(\"�˳���¼\")").click();;  
//			driver.findElementById("android:id/button1").click();
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			/*
			 �������˻�
			 */
//			driver.findElementById("com.alibaba.android.rimet:id/et_phone_input").clear();
//			driver.findElementById("com.alibaba.android.rimet:id/et_phone_input").sendKeys("18513955342");
//			driver.findElementById("com.alibaba.android.rimet:id/et_pwd_login").sendKeys("8913826lxA");	
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			driver.findElementById("com.alibaba.android.rimet:id/btn_next").click();
//			driver.findElementByAndroidUIAutomator("new UiSelector().text(\"�ҵ�\")").click();;  
//			driver.findElementById("com.alibaba.android.rimet:id/rl_setting").click();
//			driver.findElementByAndroidUIAutomator("new UiSelector().text(\"�˳���¼\")").click();;  
//			driver.findElementById("android:id/button1").click();
//			
//			driver.closeApp();
//			driver.quit();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	

}
