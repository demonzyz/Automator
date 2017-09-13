package TestAutomator.Test;



public class Splittest {

	public static void main(String[] args) {
		String bfName = "zhangsan.lisi.wangwu.zhaoliu";
		String[] afName = bfName.split("\\.");
		
//		for (int i = 0; i < afName.length; i++) {
//			System.out.println(afName[i]);
//		}
		
		for (String string : afName) {
			System.out.println(string);
		}
	}

}
