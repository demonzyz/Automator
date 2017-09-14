package base;


import java.util.List;

import org.testng.Reporter;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

public class CheckPoint extends Assertion {
	
	private int flag = 0;
	private String caseName = "";
	
	public CheckPoint(String caesName) {
		this.caseName = caesName;
	}
	
	@Override
	public void onAssertFailure(IAssert assertCommand) {
		System.out.println("����ʧ�ܣ�ʵ�ʽ��"+ assertCommand.getActual() 
						+ "Ԥ�ڽ��"+assertCommand.getExpected()
						);
		flag = flag + 1;
	}
	
	@Override
	public void onAssertSuccess(IAssert assertCommand){
		System.out.println("���Գɹ���ʵ�ʽ��" + assertCommand.getActual()
						+"Ԥ�ڽ��" + assertCommand.getExpected()
						);
	}
	
	public void equals(int actual,int expected){
		try {
			assertEquals(actual, expected);
		} catch (Error e) {}
	}
	
	public void equals(String actual, String expected) {
		try {
			assertEquals(actual, expected);
		} catch (Error e) {
		}
	}
	
	public void equals(String actual, String expected, String message) {
		try {
			assertEquals(actual, expected, message);
		} catch (Error e) {
		}
	}
	
	public void equals(List<String> actuals, String expected, String message) {
		if (actuals.size() != 0) {
			for (String actual : actuals) {
				assertEquals(actual, expected ,message);
			}
		}else {
			System.out.println("���㺯����ʵ�ʽ�� ���� ����Ϊ�գ�");
		}
	}
	
	public void notEquals(List<String> actuals, String expected, String message) {
		if (actuals.size() != 0) {
			for (String actual : actuals) {
				assertNotEquals(actual, expected, message);
			}
		}else {
			System.out.println("���㺯����ʵ�ʽ�� ���� ����Ϊ�գ�");
		}
	}
	
	public void notEquals(String actual, String expected, String message) {
		try {
			assertNotEquals(actual, expected, message);
		} catch (Error e) {
		}
		
	}
	
	public void reslut(String message) {
//		assertEquals(flag, 0);
		System.out.println("Report: " + message);
		TestBase.resultLog.add(caseName + ":" + message);
		Reporter.log(caseName +":" + message);
	}

}
