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
		
		System.out.println("���Կ�ʼִ��ʱ��Ϊ���в����������ʧ�����ܻ���"); 
		
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
		System.out.println("�ɹ�����������" + context.getPassedTests().size());
		System.out.println("ʧ�ܵ���������" + context.getFailedTests().size());
		System.out.println("��������������" + context.getSkippedTests().size());
	}

}
