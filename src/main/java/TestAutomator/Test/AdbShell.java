package TestAutomator.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AdbShell {
	
	
	public static void devices() {
		adb("devices.sh");
	}
	
	public static void aapt() {
		adb("aapt.sh");
	}
	
	public static void adb(String code){
		
		String path = "AdbShellScript/" + code; 
		String line = null;
		
		try {
			Process ps = Runtime.getRuntime().exec(path);
			ps.waitFor();
			BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {  
                sb.append(line).append("\n");  
            }
			String result = sb.toString();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) {
//		devices();
		aapt();
//		adb("startAppium.sh");
//		AdbShell adbShell = new AdbShell();
//		adb("startAppium.sh");
	}

}
