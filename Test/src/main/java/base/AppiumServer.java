package base;


import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import com.jayway.jsonpath.JsonPath;
import TestAutomator.Test.AdbShell;

public class AppiumServer {
	
	private int port;
	private int max;
	
	public AppiumServer(int port, int max) {
		this.max = max;
		this.port = port;
	}
	
	public URL getUrl(){
		try {
			URL url = new URL(new URIBuilder()
					.setScheme("http")
					.setHost("127.0.0.1")
					.setPort(port)
					.setPath("/wd/hub").build().toString());
			return url;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取URL失败！");
			return null;
		}
	}
	
	public boolean isRunner() {
		boolean flag = false;
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			URI url = new URIBuilder()
							.setScheme("http")
							.setHost("127.0.0.1")
							.setPort(port)
							.setPath("/wd/hub/status").build();
			HttpGet get = new HttpGet(url);
			InputStream inputStream = client.execute(get).getEntity().getContent();
			Object status = JsonPath.read(inputStream, "$..status");
			if (status.toString().equals("[0]")) {
				flag = true;
			}
		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("开启服务请求失败");
		}
		return flag;
	}
	
	public boolean startService(){
		AdbShell.adb("startAppium.sh");
		boolean status = isRunner();
		long start = System.currentTimeMillis();
		while (!status) {
			if (System.currentTimeMillis()-start > max*1000) {
				System.out.println("启动服务【失败】，端口号： " + port);
				return false;
			}
			status = isRunner();
		}
		if (status) {
			System.out.println("启动服务【成功】，端口号： " + port);
		}		
		return true;
	}
	
	public void stopServices() {
		AdbShell.adb("killallTerminal.sh");
	}
	
	public static void main(String[] args) {
//		AdbShell.adb("startAppium.sh");
//		System.out.println(isRunner());
//		stopServices();
		AppiumServer server = new AppiumServer(4723, 30);
		server.startService();
//		server.stopServices();

	}
	
}
