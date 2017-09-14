package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHandler {
	
	public static String getTimeStamp() {
		return getFormatDate(new Date(),"yyyyMMddHHmmssSSS");
	}
	
	public static String getNowDay() {
		return getFormatDate(new Date(),"yyyy-MM-dd");
	}
	
	@SuppressWarnings("unused")
	private static String getForwardDay(String date,int increment) {
		Calendar AddDay = Calendar.getInstance();
		AddDay.setTime(ParseDate(date,"yyyy-MM-dd"));
		AddDay.add(Calendar.DATE, increment);
		return getFormatDate(AddDay.getTime(),"yyyy-MM-dd");
	}
	
	private static String getFormatDate(Date date,String reg) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(reg);
		return dateFormat.format(date);
	}
	
	private static Date ParseDate(String date,String reg) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(reg);
		try {
			return dateFormat.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
