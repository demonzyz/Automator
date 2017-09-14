package listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetryAnalyzer implements IRetryAnalyzer {
	
	private int retryCont =1;
	private int maxRetryCont = 3;

	public boolean retry(ITestResult result) {
		if (retryCont <= maxRetryCont) {
			result.setAttribute("RETRY", retryCont);
			System.out.println("用例：" + result.getName() + "正在进行第" + retryCont + "次失败重跑");
			retryCont++;
			return true;
		}
		return false;
	}

}
