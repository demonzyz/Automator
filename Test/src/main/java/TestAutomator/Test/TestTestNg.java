package TestAutomator.Test;


import org.testng.annotations.Test;
import base.CheckPoint;
import util.XmlParser;

public class TestTestNg {
	@Test
	public void test01(){
		System.out.println("testcase01");
		XmlParser xmlParser = new XmlParser("suite.xml");
		
	}
	
	@Test
	public void test02(){
		System.out.println("testcase02");
//		CheckPoint checkPoint = new CheckPoint();
//		checkPoint.equals(0, 0);
//		checkPoint.reslut();
	}

}
