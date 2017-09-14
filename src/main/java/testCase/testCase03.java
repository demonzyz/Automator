package testCase;

import java.util.Map;
import org.testng.annotations.Test;
import base.TestBase;
public class testCase03 extends TestBase{
	
	
	@Test(dataProvider = "getDdData")
	public void case03(Map<String, String> data){
		driver.findElementById("com.alibaba.android.rimet:id/et_phone_input").clear();
		driver.findElementById("com.alibaba.android.rimet:id/et_phone_input").sendKeys(data.get("µÇÂ¼ÓÃ»§"));
		driver.findElementById("com.alibaba.android.rimet:id/et_pwd_login").sendKeys(data.get("µÇÂ¼ÃÜÂë"));			
		driver.findElementById("com.alibaba.android.rimet:id/btn_next").click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"ÎÒµÄ\")").click();
		String loginName = driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Ð¡ÖÇ\")").getText();
		checkPoint.assertEquals(loginName, "Ð¡ÖÇ");
		int width=driver.manage().window().getSize().width;
        int height=driver.manage().window().getSize().height;  
        driver.swipe(width/2,height*3/4, width/2,height/4, 1000);
		driver.findElementById("com.alibaba.android.rimet:id/rl_setting_text").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"ÍË³öµÇÂ¼\")").click();
		driver.findElementById("android:id/button1").click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		checkPoint.reslut("¶¤¶¤µÇÂ¼³É¹¦");
	}

}
