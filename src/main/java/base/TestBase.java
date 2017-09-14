package base;



import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Element;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import util.XmlParser;

public class TestBase {
	
	private String filePath = "test-data/login.xml";
	protected static AndroidDriver driver = null;
	protected static AppiumServer server = null;
	private XmlParser xp;
	protected AppLocator locator;
	protected CheckPoint checkPoint = null;
	protected int objectWaitTime = 30;
	public static List<String> resultLog = new ArrayList<String>();
	public static Map<String, String> commonMap;
	


	@BeforeSuite
	public void setup(){
		server = new AppiumServer(4723, 60);
		server.startService();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Redmi Note 3");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.1");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.alibaba.android.rimet");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.alibaba.android.user.login.SignUpWithPwdActivity");
		capabilities.setCapability(MobileCapabilityType.NO_RESET,true);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET,false);
        capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true);
        capabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, true);
		driver = new AndroidDriver(server.getUrl(), capabilities);
	}
	
	@BeforeClass
	public void beforeClass(){
		locator = new AppLocator(driver, AppConfig.objectRespository, objectWaitTime);
		checkPoint = new CheckPoint(this.getClass().getSimpleName());
	}
	
	@AfterSuite
	public void tearDown(){
		driver.quit();
		server.stopServices();
	}
	
	@DataProvider(name = "getDdData")
	public Object[][] getDdData(){
		this.init();
//		XmlParser xp = new XmlParser("config.xml");
		List<Element> elements = xp.getElmentsObject("/login/person");
		Object[][] object = new Object[elements.size()][];
		for (int i = 0; i < elements.size(); i++) {
			Object[ ] temp = new Object[ ]{xp.getChildernInfoByElement(elements.get(i))};
			object[i] = temp;
		}
		return object;
	}
	
//	@DataProvider(name = "getData")
//	public Object[][] getData(){
//		this.init();
//		File file = new File(filePath);
//		List<Element> elements = xp.getElmentsObject("/对象/登录/用户名");
//		Object[][] object = new Object[elements.size()][];
//		if (file.exists() && elements.size() > 0) {
//			for (int i = 0; i < elements.size(); i++) {
//				Map<String, String> mergeCommonMap = this.getMergeData(
//						xp.getChildernInfoByElement(elements.get(i)), commonMap);
//				Object[] temp = new Object[]{mergeCommonMap};
//				object[i] = temp;
//			}
//		}
//		return object;
//	}
	
	private void init(){
		if(xp == null){
			xp = new XmlParser(filePath);
		}
	}
	
	public Map<String, String> getMergeData(Map<String, String> map1,Map<String, String> map2) {
		Iterator<String> iterator = map2.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String value = map2.get(key);
				if (!map1.containsKey(value)) {
					map1.put(key, value);
				}
		}
		return map1;
	}
	
}
