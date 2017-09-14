package util;

import TestAutomator.Test.TestException;

public class ErrorHandler {
	
	/**
	 * 在系统日志和测试报告中记录自定义信息message
	 * @param message  要输出的自定义信息
	 * @param isReport 是否将自定义信息输出到测试报告中
	 */
	public static void continueRunning(String message){
		Log.error(message);
	}
	
	/**
	 * 在程序中捕获到异常后，记录message和异常的堆栈信息到日志。并在报告中输出自定义信息message
	 * @param cause    捕获到的原始异常
	 * @param message  要输出的自定义信息
	 * @param isReport 是否将自定义信息输出到测试报告中
	 */
	public static void continueRunning(Throwable cause, String message){
		Log.error(message, cause);
	}

	/**
	 * 抛出JuiceException，并在系统日志和测试报告中记录自定义信息message
	 * @param message  要输出的自定义信息
	 * @param isReport 是否将自定义信息输出到测试报告中
	 */
	public static void stopRunning(String message){
		Log.error(message);
		throw new TestException(message);
	}

	/**
	 * 在程序中捕获到异常后，记录message和异常的堆栈信息到日志。抛出JuiceException，并在报告中输出自定义信息message
	 * @param cause    捕获到的原始异常
	 * @param message  要输出的自定义信息
	 * @param isReport 是否将自定义信息输出到测试报告中
	 */
	public static void stopRunning(Throwable cause, String message){
		Log.error(message, cause);
		throw new TestException(message);
	}
	
//	private static String createMessage(Throwable cause){	
//		return "异常堆栈信息 ：\n"
//			+ getErrorStack(cause);
//	}
//		
//	private static String getErrorStack(Throwable cause){
//		StackTraceElement[] stackElements  = cause.getStackTrace();
//		StringBuffer sb = new StringBuffer();
//		sb.append(cause+"\n");
//		if (stackElements != null) {
//			for(StackTraceElement stack:stackElements){
//				sb.append(stack.getClassName());
//				sb.append("."+stack.getMethodName());
//				sb.append("(Line:"+stack.getLineNumber()+")");
//				sb.append("\n");
//			}
//		}
//		return sb.toString();
//	}
	
//	private static void logMessage(String message, boolean isReport){
//		Log.error(message);
//		if(isReport){
//			Reporter.record(2, message);
//		}
//	}
//	
//	private static void logMessage(String message, Throwable cause, boolean isReport){
//		Log.error(message, cause);
//		if(isReport){
//			Reporter.record(2, message);
//		}
//	}
	
}
