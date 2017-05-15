package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static Date parse(String date) throws ParseException{
		return format.parse(date);
	}
	
	public static String format(Date date) {
		return format.format(date);
	}
}
