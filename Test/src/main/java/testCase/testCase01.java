package testCase;

import org.dom4j.Element;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import base.TestBase;

public class testCase01 extends TestBase{
	
	@Test
	public void case01(){
		locator.element("��¼", "�û���").clear();;
		locator.element("��¼", "�û���").sendKeys("13581975037");
		locator.element("��¼", "����").sendKeys("5211314");
		locator.element("��¼", "��¼��ť").click();
		try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
		locator.element("�ҵ�", "��").click();
		String loginName = locator.element("�ҵ�", "�û�").getText();
		checkPoint.assertEquals(loginName, "С��");
		locator.swipeActivity("up");
		locator.element("�ҵ�", "����").click();
		locator.element("�ҵ�", "�ǳ�").click();
		locator.element("�ҵ�", "ȷ�ϵǳ�").click();
		try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
		checkPoint.reslut("������¼�ɹ�");
		
//		driver.findElementById("com.alibaba.android.rimet:id/et_phone_input").clear();
//		driver.findElementById("com.alibaba.android.rimet:id/et_phone_input").sendKeys("13581975037");
//		driver.findElementById("com.alibaba.android.rimet:id/et_pwd_login").sendKeys("5211314");			
//		driver.findElementById("com.alibaba.android.rimet:id/btn_next").click();
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		WebElement element = driver.findElementByAndroidUIAutomator("new UiSelector().text(\"�ҵ�\")");
//		element.click();
//		String loginName = driver.findElementByAndroidUIAutomator("new UiSelector().text(\"С��\")").getText();
//		checkPoint.assertEquals(loginName, "С��");
//		int width=driver.manage().window().getSize().width;
//        int height=driver.manage().window().getSize().height;  
//        driver.swipe(width/2,height*3/4, width/2,height/4, 1000);
//		driver.findElementById("com.alibaba.android.rimet:id/rl_setting_text").click();
//		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"�˳���¼\")").click();
//		driver.findElementById("android:id/button1").click();
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		checkPoint.reslut("������¼�ɹ�");
	}

}
