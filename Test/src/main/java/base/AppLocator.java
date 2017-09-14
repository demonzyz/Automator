package base;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;
import util.XmlParser;

public class AppLocator {
	private XmlParser xp;
	private AndroidDriver driver;
	private String filePath;
	private int objectWaitTime;
	
	public AppLocator(AndroidDriver driver,String filePath,int objectWaitTime) {
		this.driver = driver;
		this.filePath = filePath;
		this.objectWaitTime = objectWaitTime;
		xp = new XmlParser(filePath);
	}
	
	public WebElement element(String pageKey,String objKey) {
		return getElement(pageKey, objKey, true);
	}
	
	public WebElement elementNoWait(String pageKey,String objKey) {
		return getElement(pageKey, objKey, false);
	}
	
	/**
	 * 屏幕滑动操作
	 * @param indicator:滑动方向,可传入的参数值：left,right,up,down
	 */
	public void swipeActivity(String indicator){
		Dimension dimension = driver.manage().window().getSize();
		int height = dimension.getHeight();
		int width = dimension.getWidth();  
		indicator = indicator.toLowerCase().trim();
		switch(indicator){
			case "left":
				driver.swipe(width*3/4, height/2, width/4, height/2, 1000);
				break;	
			case "right":
				driver.swipe(width/4, height/2, width*3/4, height/2, 1000);
				break;		
			case "up":
				driver.swipe(width/2,height*3/4, width/2,height/4, 1000);
				break;	
			case "down":
				driver.swipe(width/2,height/4, width/2,height*3/4, 1000);
				break;
			default:
			System.out.println("屏幕滑动操作,入参类型错误!");
			break;
		}
	}
	
	public By getBy(String type,String value) {
		By by = null;
		switch (type) {
		case "id":
			by = By.id(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "className":
			by = By.className(value);	
			break;
		case "tagName":
			by = By.tagName(value);	
			break;
		case "linkText":
			by = By.linkText(value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;	
		case "cssSelector":
			by = By.cssSelector(value);
			break; 
		case "accessibilityId":
			by = By.id("name=accessibilityId"+" value="+value);
			break;
		case "textName":
			by = By.id("name=textName"+" value="+value);
			break;	
		default:
			System.out.println("元素定位错误! By "+type+"不存在此类型 ");
			break;
		}
		return by;
	}
	
	public WebElement getElement(String pageKey,String objKey,boolean isWait) {
		WebElement element = null;
		if (xp.isExist("/对象/"+pageKey+"/"+objKey)) {
			String type = xp.getElementText("/对象/"+pageKey+"/"+objKey + "/type");
			String value = xp.getElementText("/对象/"+pageKey+"/"+objKey + "/value");
			if ("name".equals(type) || "accessibilityId".equals(type)) {
				try {
					element = driver.findElementByAndroidUIAutomator(value);
				} catch (Exception e) {
					System.out.println("元素定位未找到"+type + value);
				}
			return element;
			}
			By by = getBy(type, value);
			if(isWait){
				
				//等待元素在页面存在，若始终未存在则抛出异常
				element = waitForElement(by, pageKey, objKey);
				//等待元素为可见状态,若始终未可见则输出日志后继续操作
				waitElementToBeDisplayed(element,pageKey,objKey);
				try {
					element = driver.findElement(by);
				} catch (Exception e) {
					System.out.println("元素定位未找到"+type + value);
				}	                           
			}
		}
		
		return element;
	}
	
	
	//	在config.xml配置的对象最长等待时间内，定位指定by的元素
	//	定位成功，existFlag置为true。否则抛出Exception，打印错误日志
	protected WebElement waitForElement(final By by,String pageKey,String objKey){
		WebElement element = null;   
		try {
			element = new WebDriverWait(driver, objectWaitTime)
			 .until(new ExpectedCondition<WebElement>() {
				 @Override
			     public WebElement apply(WebDriver d) {		     	
			     		return d.findElement(by);
			     	}
			 });
		} catch (Exception e) {
			System.out.println("元素定位未找到" + pageKey + objKey);
		}
		return element;			
	}
	
	//	在config.xml配置的对象最长等待时间内，判断页面元素是否可见
	//	元素可见，返回值为true。否则抛出Exception，打印错误日志	
	protected boolean waitElementToBeDisplayed(final WebElement element, 
			String pageKey, String objKey) {
	    boolean wait = false;
	    if (element == null)
	        return wait;
	    try {
	        wait = new WebDriverWait(driver, objectWaitTime)
	                .until(new ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver d) {
	                        return element.isDisplayed();
	                    }
	                });
	    } catch (Exception e) {
	    	System.out.println("元素定位未找到" + pageKey + objKey);
	    }
	    return wait;
	}
	
	public void takeScreenshot(String screenPath) {
		try{		
			File srcFile = driver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(screenPath));
		}catch(IOException e){
			System.out.println("截图异常");
		}
	}
}
