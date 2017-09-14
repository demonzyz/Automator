package testCase;

import org.dom4j.Element;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import base.TestBase;

public class testCase01 extends TestBase{
	
	@Test
	public void case01(){
		locator.element("登录", "用户名").clear();;
		locator.element("登录", "用户名").sendKeys("13581975037");
		locator.element("登录", "密码").sendKeys("5211314");
		locator.element("登录", "登录按钮").click();
		try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
		locator.element("我的", "我").click();
		String loginName = locator.element("我的", "用户").getText();
		checkPoint.assertEquals(loginName, "小智");
		locator.swipeActivity("up");
		locator.element("我的", "设置").click();
		locator.element("我的", "登出").click();
		locator.element("我的", "确认登出").click();
		try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
		checkPoint.reslut("钉钉登录成功");
		
//		driver.findElementById("com.alibaba.android.rimet:id/et_phone_input").clear();
//		driver.findElementById("com.alibaba.android.rimet:id/et_phone_input").sendKeys("13581975037");
//		driver.findElementById("com.alibaba.android.rimet:id/et_pwd_login").sendKeys("5211314");			
//		driver.findElementById("com.alibaba.android.rimet:id/btn_next").click();
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		WebElement element = driver.findElementByAndroidUIAutomator("new UiSelector().text(\"我的\")");
//		element.click();
//		String loginName = driver.findElementByAndroidUIAutomator("new UiSelector().text(\"小智\")").getText();
//		checkPoint.assertEquals(loginName, "小智");
//		int width=driver.manage().window().getSize().width;
//        int height=driver.manage().window().getSize().height;  
//        driver.swipe(width/2,height*3/4, width/2,height/4, 1000);
//		driver.findElementById("com.alibaba.android.rimet:id/rl_setting_text").click();
//		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"退出登录\")").click();
//		driver.findElementById("android:id/button1").click();
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		checkPoint.reslut("钉钉登录成功");
	}

}
