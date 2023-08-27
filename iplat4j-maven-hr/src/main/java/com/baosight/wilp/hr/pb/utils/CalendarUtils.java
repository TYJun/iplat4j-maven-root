package com.baosight.wilp.hr.pb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日历工具类
 */
public class CalendarUtils {

	private static final int FIRST_DAY = Calendar.MONDAY;
	public static String DateyMd = "yyyy-MM-dd";
	public static String DateyMdHms = "yyyy-MM-dd HH:mm:ss";
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat dateTimeMsFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static SimpleDateFormat dateWeekFormat = new SimpleDateFormat("E yyyy-MM-dd",Locale.CHINA);
	public static SimpleDateFormat shortDateFormat = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");

	public static SimpleDateFormat dateTimeShortFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static String dateTimeShortFormat(Date date) {
		if (date != null) {
			return dateTimeShortFormat.format(date);
		}else {
		    return dateTimeShortFormat.format(new Date());
		}
	}
	
	public static Date toShortDate(String dateStr) throws ParseException {
		return dateFormat.parse(dateStr);
	}

	/**
	 * 从numDays的日期数据集合
	 * @param numDays 天数 ,startDay 从何时开始
	 * @return
	 */
	public static List<String> dateDataListAndYear(int numDays,int startDay){
		List<String> lst = new ArrayList<String>();
		Calendar cd            = Calendar.getInstance();
		cd.add(Calendar.DATE, startDay);
		for(int i=0; i< numDays ;i++)
		{
			if(i == 0)
			{
				lst.add(dateFormat(cd.getTime()));
			}
			else
			{
				cd.add(Calendar.DATE, 1);
				lst.add(dateFormat(cd.getTime()));
			}
		}
		
		return lst;
	}
	
	/**
	 * @desc 获取当前时间通过日期格式
	 * @date 2018年6月5日 16点12分
	 * @author yk
	 * @param format
	 * @return	String
	 */
	public static String getDateByFormat(String format) {
		SimpleDateFormat sdf = null;
		//格式为空时使用默认格式
		if(!(format == null || "".equals(format))) {
			sdf = new SimpleDateFormat(format);
		} else {
			sdf = new SimpleDateFormat(DateyMdHms);
		}
		Date date = new Date();
		return sdf.format(date);
	}
	
	/**
	 * 获取当前时间 年-月-日
	 * @param tomorrow  false-获取当天年-月-日、true-获取明天年-月-日
	 * @return
	 */
	public static String getDay(boolean tomorrow){
		Calendar now = Calendar.getInstance();  
		if(tomorrow){
			now.add(Calendar.DAY_OF_MONTH, 1);
		}
        int yesr = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        String value = "";
        value += yesr;
        if(month<10){
        	value += "-0"+month;
        }else{
        	value += "-"+month;
        }
        if(day<10){
        	value += "-0"+day;
        }else{
        	value += "-"+day;
        }
        return value;  
	}
	
	public static String shortDateFormat(Date date) {
		if (date != null) {
			return shortDateFormat.format(date);
		}else {
			return shortDateFormat.format(new Date());
		}
	}

	/**
	 * 获取年份
	 * 
	 * @param bln true/获取本年，false/获取下年
	 * @return
	 */
	public static String getYear(boolean bln) {
		Calendar cd = Calendar.getInstance();
		if (bln) {
			return yearFormat.format(cd.getTime());
		} else {
			cd.add(Calendar.YEAR, 1);
			return yearFormat.format(cd.getTime());
		}
	}

	/**
	 * 格式化日期yyyy-MM-dd HH:mm:ss
	 */
	public static String dateTimeFormat(Date date) {
		if (date != null) {
			return dateTimeFormat.format(date);
		}else {
			return dateTimeFormat.format(new Date());
		}
	}
	
	/**
	 * 格式化日期yyyyMMddHHmmssSSS精确到毫秒
	 */
	public static String dateTimeMsFormat(Date date) {
		if (date != null) {
			return dateTimeMsFormat.format(date);
		}else {
			return dateTimeMsFormat.format(new Date());
		}
	}

	/**
	 * 格式化日期yyyy-MM-dd
	 */
	public static String dateFormat(Date date) {
		if (date != null) {
			return dateFormat.format(date);
		}
		return dateFormat.format(new Date());
	}

	/**
	 * @desc 按日期长度获取一段时间内的日期集合
	 * @param num    增加日期长度
	 * @param format 返回日期格式
	 * @param flag   true -- 包含当前日期的日期长度 / false -- 不包含当前日期的日期长度
	 * @return List<String>
	 */
	public static List<String> getDateCalender(int num, String format, boolean flag) {
		// 返回日期集合
		List<String> dateList = new ArrayList<String>();
		// true包含当前日期的日期长度，false不包含当前日期的日期长度
		if (flag) {
			for (int iCyc = 0; iCyc < num; iCyc++) {
				String date = getDateCalendar(iCyc, format);
				dateList.add(date);
			}
		} else {
			for (int iCyc = 1; iCyc <= num; iCyc++) {
				String date = getDateCalendar(iCyc, format);
				dateList.add(date);
			}
		}
		return dateList;
	}

	/**
	 * @desc 按日期长度增加日期
	 * @param num    增加日期长度
	 * @param format 返回日期格式
	 * @return String
	 */
	public static String getDateCalendar(int num, String format) {
		SimpleDateFormat sdf = null;
		Calendar instance = GregorianCalendar.getInstance();
		instance.add(Calendar.DATE, num);
		if (!(format == null || "".equals(format))) {
			sdf = new SimpleDateFormat(format);
		} else {
			sdf = new SimpleDateFormat(DateyMd);
		}
		return sdf.format(instance.getTime());
	}

	/**
	 * 1 获取本周的所有日期
	 * 
	 * @return ArrayList
	 */
	public static ArrayList<String> getThisWeekdays() {
		ArrayList<String> list = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
			calendar.add(Calendar.DATE, -1);
		}
		for (int i = 0; i < 7; i++) {
			list.add(dateWeekFormat.format(calendar.getTime()).replace("星期", "周"));
			calendar.add(Calendar.DATE, 1);
		}
		return list;
	}

	/**
	 * 2 获取下周的所有日期
	 * 
	 * @return ArrayList
	 */
	public static ArrayList<String> getNextWeekDates() {
		ArrayList<String> list = new ArrayList<String>();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(new Date());
		// 获得当前日期是一个星期的第几天
		int dayWeek = calendar1.get(Calendar.DAY_OF_WEEK);
		// 获取下周的第一天
		if (dayWeek == 1) {
			calendar1.add(Calendar.DAY_OF_MONTH, 1);
		} else {
			calendar1.add(Calendar.DAY_OF_MONTH, 1 - dayWeek + 8);
		}
		// 设置起始时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(calendar1.getTime());

		// 此日期是否在指定日期之后
		for (int i = 0; i < 7; i++) {
			list.add(dateWeekFormat.format(calendar.getTime()).replace("星期", "周"));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		return list;
	}

	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}

	/**
	 * 日期转字符串
	 * 
	 * @param
	 * @return
	 * @throws ParseException
	 */
	public static String Date2String(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = "";
		Calendar now = Calendar.getInstance();
		now.setTime(date);

		str = sdf.format(now.getTime());
		return str;
	}

	public static Date String2Date(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {

			return sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getYeastody() {
	    Date date = new Date();
	    date.setTime(date.getTime() - 24*60*60*1000);
	    return dateFormat(date);
	}
	
	public static void main(String[] args) throws Exception {
	    System.out.println(getYeastody());
        /*LinkedHashMap<String,Integer> lhm = new LinkedHashMap<>();
        lhm.put("q",1);
        lhm.put("w",3);
        lhm.put("e",4);
        lhm.put("r",5);
        lhm.put("f",6);
        lhm.put("h",7);
        System.out.println(lhm.size());*/
	}
}
