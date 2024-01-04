package com.baosight.wilp.ac.jk.util;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 生成随机的字符串的工具类：
 * @author admin
 *
 */
public class UUIDUtils {

	private static String formatStr = "yyyy-MM-dd HH:mm:ss";

	public static SimpleDateFormat dateTimeMsFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	public static String[] st = {"a","b","c","e","f","g","h","i","j","k","l","m",
			"n","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G",
			"H","I","J","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","Z",
			"0","1","2","3","4","5","6","7","8","9"};

	public static String getFormatStr() {
		return formatStr;
	}

	public static void setFormatStr(String formatStr) {
		UUIDUtils.formatStr = formatStr;
	}

	/**
	 * getUUID32：获取32位的uuid
	 * */
	public static String getUUID32(){
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * getUUID8：获取8位的uuid
	 * */
	public static String getUUID8(){
		return UUID.randomUUID().toString().substring(0, 8);
	}

	/**
	 * getUUID(int index)：获取指定长度的uuid，最低4位，最高36位
	 * @param index 位数
	 * */
	public static String getUUID(int index){
		if(index <= 0) {
			index = 4;
		}else if(index > 36) {
			index = 36;
		}
		return UUID.randomUUID().toString().substring(0, index);
	}

	/**
	 * getTimeStamp：获取时间戳
	 * */
	public static long getTimeStamp() {
		long currentTimeMillis = System.currentTimeMillis();
		return currentTimeMillis;
	}

	/**
	 * 格式化日期yyyyMMddHHmmssSSS精确到毫秒
	 */
	public static String getTimeMsFormat(Date ...date) {
		if (date != null && date.length > 0) {
			return dateTimeMsFormat.format(date[0]);
		}else {
			return dateTimeMsFormat.format(new Date());
		}
	}

	/**
	 * getFomatDate：获取格式化的日期字符串，默认为yyyy-MM-dd HH:mm:ss
	 * */
	public static String getFomatDate(String formatStr) {
		if(!StringUtils.isBlank(formatStr)) {
			setFormatStr(formatStr);
		}else {
			setFormatStr("yyyy-MM-dd HH:mm:ss");
		}
		SimpleDateFormat smf = new SimpleDateFormat(getFormatStr());
		String format = smf.format(new Date());
		return format;
	}


	/**
	 * 获取指定长度字符串 数字+字母
	 * @param i
	 * @return
	 */
	public static String fixedStirng(int i) {

		Random random = new Random();
		StringBuffer sbu = new StringBuffer();
        for (int j = 0; j < i; j++) {
        	int choice = random.nextInt(59);
			sbu.append(st[choice]);
		}
        return sbu.toString();
    }

	public static void main(String[] args) {
		System.out.println(UUIDUtils.getUUID8());
		System.out.println(getTimeStamp());
		System.out.println(getTimeMsFormat());
		System.out.println(fixedStirng(4));
	}
}
