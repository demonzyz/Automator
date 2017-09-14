package util;

import TestAutomator.Test.TestException;

public class ErrorHandler {
	
	/**
	 * ��ϵͳ��־�Ͳ��Ա����м�¼�Զ�����Ϣmessage
	 * @param message  Ҫ������Զ�����Ϣ
	 * @param isReport �Ƿ��Զ�����Ϣ��������Ա�����
	 */
	public static void continueRunning(String message){
		Log.error(message);
	}
	
	/**
	 * �ڳ����в����쳣�󣬼�¼message���쳣�Ķ�ջ��Ϣ����־�����ڱ���������Զ�����Ϣmessage
	 * @param cause    ���񵽵�ԭʼ�쳣
	 * @param message  Ҫ������Զ�����Ϣ
	 * @param isReport �Ƿ��Զ�����Ϣ��������Ա�����
	 */
	public static void continueRunning(Throwable cause, String message){
		Log.error(message, cause);
	}

	/**
	 * �׳�JuiceException������ϵͳ��־�Ͳ��Ա����м�¼�Զ�����Ϣmessage
	 * @param message  Ҫ������Զ�����Ϣ
	 * @param isReport �Ƿ��Զ�����Ϣ��������Ա�����
	 */
	public static void stopRunning(String message){
		Log.error(message);
		throw new TestException(message);
	}

	/**
	 * �ڳ����в����쳣�󣬼�¼message���쳣�Ķ�ջ��Ϣ����־���׳�JuiceException�����ڱ���������Զ�����Ϣmessage
	 * @param cause    ���񵽵�ԭʼ�쳣
	 * @param message  Ҫ������Զ�����Ϣ
	 * @param isReport �Ƿ��Զ�����Ϣ��������Ա�����
	 */
	public static void stopRunning(Throwable cause, String message){
		Log.error(message, cause);
		throw new TestException(message);
	}
	
//	private static String createMessage(Throwable cause){	
//		return "�쳣��ջ��Ϣ ��\n"
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
