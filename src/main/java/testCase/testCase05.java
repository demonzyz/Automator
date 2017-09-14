package testCase;

import java.util.Map;
import org.testng.annotations.Test;
import base.TestBase;
public class testCase05 extends TestBase{
	
	
	@Test(dataProvider = "getDdData")
	public void case04(Map<String, String> data){
			locator.element("登录", "用户名").clear();;
			locator.element("登录", "用户名").sendKeys(data.get("登录用户"));
			locator.element("登录", "密码").sendKeys(data.get("登录密码"));
			locator.element("登录", "登录按钮").click();
			try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			driver.findElementByName("我的").click();;
//			locator.element("我的", "我").click();
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
	}
}
