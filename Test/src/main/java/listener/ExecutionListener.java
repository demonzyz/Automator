package listener;

import java.util.Iterator;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;


public class ExecutionListener implements ITestListener{

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		
		System.out.println("测试开始执行时，为所有测试用例添加失败重跑机制"); 
		
		for (ITestNGMethod method : context.getAllTestMethods()) {
			method.setRetryAnalyzer(new TestRetryAnalyzer());
			System.out.println(method.getMethodName() + "->set retry");
		}
		
	}

	public void onFinish(ITestContext context) {
		
		Iterator<ITestResult> listOfFailedTests = context.getFailedTests().getAllResults().iterator();
		
		while (listOfFailedTests.hasNext()) {
			ITestResult failedTest = listOfFailedTests.next();
			ITestNGMethod method = failedTest.getMethod();
			if (context.getFailedTests().getResults(method).size() > 1) {
				listOfFailedTests.remove();
			}
			
		}
		System.out.println("成功的用例数：" + context.getPassedTests().size());
		System.out.println("失败的用例数：" + context.getFailedTests().size());
		System.out.println("跳过的用例数：" + context.getSkippedTests().size());
	}

}
