package testCase;

import java.util.Map;
import org.testng.annotations.Test;
import base.TestBase;
public class testCase05 extends TestBase{
	
	
	@Test(dataProvider = "getDdData")
	public void case04(Map<String, String> data){
			locator.element("��¼", "�û���").clear();;
			locator.element("��¼", "�û���").sendKeys(data.get("��¼�û�"));
			locator.element("��¼", "����").sendKeys(data.get("��¼����"));
			locator.element("��¼", "��¼��ť").click();
			try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			driver.findElementByName("�ҵ�").click();;
//			locator.element("�ҵ�", "��").click();
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
	}
}
