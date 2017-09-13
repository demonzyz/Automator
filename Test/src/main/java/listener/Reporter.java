package listener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;
import base.TestBase;

public class Reporter implements IReporter{

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		String PROJECTNAME = "";
		String ENDTIMESTRING = "";
		int PASSED_NUMBER = 0;
		int FAILED_NUMBER = 0;
		int SKIPPID_NUMBER = 0;
		int TOTAL_NUMBER = 0;
		float PASSRATE = 0;
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:hh:mm:ss");
		PROJECTNAME = xmlSuites.get(0).getName();
		ENDTIMESTRING = "测试结束时间：" + simpleDateFormat.format(new Date());
		
		for (String key : suites.get(0).getResults().keySet()) {
			ITestContext testResultContext = suites.get(0).getResults().get(key).getTestContext();
			PASSED_NUMBER = testResultContext.getPassedTests().size();
			FAILED_NUMBER = testResultContext.getFailedTests().size();
			SKIPPID_NUMBER = testResultContext.getSkippedTests().size();
		}
		
		TOTAL_NUMBER = PASSED_NUMBER + FAILED_NUMBER + SKIPPID_NUMBER;
		PASSRATE = ((float)PASSED_NUMBER/(TOTAL_NUMBER)) * 100;
		
		if (Double.isNaN(PASSRATE)) {
			PASSRATE = 0;
		}
		
		System.out.println("成功：" + PASSED_NUMBER);
		System.out.println("失败：" + FAILED_NUMBER);
		System.out.println("跳过：" + SKIPPID_NUMBER);
		System.out.println("总计：" + TOTAL_NUMBER);
		System.out.println("成功率：" + PASSRATE);
		
		StringBuffer htmlString = new StringBuffer();
		htmlString.append("<html lang=\"en\">");
		htmlString.append("<h1>" + PROJECTNAME + "</h1>");
		htmlString.append("<p>" + ENDTIMESTRING + "</p>");
		htmlString.append("<p>成功：" + PASSED_NUMBER + "</p>");
		htmlString.append("<p>失败：" + FAILED_NUMBER + "</p>");
		htmlString.append("<p>跳过" + SKIPPID_NUMBER + "</p>");
		htmlString.append("<p>总计：" + TOTAL_NUMBER + "</p>");
		htmlString.append("<p>成功率：" + PASSRATE + "%</p>");
		htmlString.append("<p>测试日志：</p>");
		for (String test : TestBase.resultLog) {
			htmlString.append("<p>" + test + "</p>");
		}
		htmlString.append("</html>");
		
		write("test-output/HtmlReport/" + new SimpleDateFormat("YYYYMMddHHmmss").format(new Date()) + ".html"
				, htmlString.toString());
	}
	
	public static void write(String filePath, String data) {
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		BufferedWriter bWriter = null;
		try {
			bWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			bWriter.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bWriter.flush();
				bWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
