package base;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;

public class ScreenControl {
	public static AndroidDriver driver = null;
	
	public static void wait(int second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void swipeActivity(String indicator){
		wait(2);
		Dimension dimension = ((RemoteWebDriver) driver).manage().window().getSize();
		int height = dimension.getHeight();
		int width = dimension.getWidth();
		indicator = indicator.toLowerCase().trim();
		
		switch(indicator){
			case "left":
				driver.swipe(width-20, height/2, 20, height/2, 300);
				wait(2);
				break;	
			case "right":
				driver.swipe(20, height/2, width-20, height/2, 300);
				wait(2);
				break;		
			case "up":
				driver.swipe(width/2, height-20, width/2, 20, 300);
				wait(2);
				break;	
			case "down":
				driver.swipe(width/2, 20, width/2, height-20, 300);
				wait(2);
				break;
		}
	}
	
	public static void takeScreenShot(Boolean isWait, String pathname) {
		if (isWait) {
			wait(5);
		}
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(pathname));
		} catch (IOException e) {
			System.out.println("ΩÿÕº“Ï≥£");
		}
	}
	
	

}
