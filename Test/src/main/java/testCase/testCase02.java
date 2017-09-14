package testCase;


import org.openqa.selenium.remote.DesiredCapabilities;
import base.AppiumServer;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;


public class testCase02 {
	
	public static void main(String[] args) {
		AppiumServer server = new AppiumServer(4723, 30);
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
		AndroidDriver driver = new AndroidDriver(server.getUrl(), capabilities);		
		server.stopServices();
	}

}
