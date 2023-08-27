package com.baosight.wilp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * 1.获取当前日期：yyyy-MM-dd HH:mm:ss
 * 2.获取当前日期：yyyy-MM-dd hh:mm:ss
 * 3.获取系统当前时间戳
 * 4.获取当前时间日期 yyyy-MM-dd
 * 5.获得两个时间的时间差
 * 6.获得两个时间差
 * 7.转化long值的日期为yyyy-MM-dd HH:mm:ss格式的日期
 * 8.获取当前日期的一个星期的第几天
 * 9.获取当前时间日期 yyyyMMdd
 */
public class DateUtilsTools {
	private static SimpleDateFormat simpleDateFormat24 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat simpleDateFormat12 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 1.获取当前日期：yyyy-MM-dd HH:mm:ss
	 * 小写的hh是12小时制
	 * 大写的HH是24小时制
	 * @return 2021-10-28 18:53:00
	 */
	public static String getCurrentTime24(){
		// 设置时间模板
		return simpleDateFormat24.format(new Date());
	}

	/**
	 * 2.获取当前日期：yyyy-MM-dd hh:mm:ss
	 * 小写的hh是12小时制
	 * 大写的HH是24小时制
	 * @return 2021-10-28 06:53:00
	 */
	public static String getCurrentTime12(){
		// 设置时间模板
		return simpleDateFormat12.format(new Date());
	}

	/**
	 * 3.获取系统当前时间戳
	 * @return 1566889186583
	 */
	public static String getSystemTime(){
		return String.valueOf(System.currentTimeMillis());
	}

	/**
	 * 4.获取当前时间日期 yyyy-MM-dd
	 * @return 2021-10-28
	 */
	public static String getDate(){
		return dateFormat.format(new Date());
	}

	/**
	 * 5.获得两个时间的时间差
	 * @param start 2021-10-29 10:09:00
	 * @param end 2021-10-29 10:10:00
	 * @return long
	 */
	public static long dateSubtractionString(String start,String end){
		try {
			Date dateStart = simpleDateFormat24.parse(start);
			Date dateSEnd = simpleDateFormat24.parse(end);
			return dateSEnd.getTime() - dateStart.getTime();
		}catch (Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 6.获得两个时间差
	 * @param start 开始时间 Date
	 * @param end 结束时间 Date
	 * @return long
	 */
	public static long dateSubtraction(Date start,Date end){
		return end.getTime() - start.getTime();
	}

	/**
	 * 7.转化long值的日期为yyyy-MM-dd HH:mm:ss格式的日期
	 * @param millSec 日期long值
	 * @return String
	 */
	public static String transferLongToDate(String millSec){
		Date date = new Date(Long.parseLong(millSec));
		return simpleDateFormat24.format(date);
	}

	/**
	 * 8.获取当前日期的一个星期的第几天
	 * @return int
	 */
	public static int getDayOfWeek(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 9.获取当前时间日期 yyyyMMdd
	 * @return 20211028
	 */
	public static String getDatePlus(){
		return simpleDateFormat.format(new Date());
	}

}
