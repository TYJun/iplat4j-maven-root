package com.baosight.wilp.common.pr;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 生成随机的字符串的工具类：
 * @author admin
 *
 */
public class UUIDUtils {

	public static String getUUID32(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static String getUUID8(){
		return UUID.randomUUID().toString().substring(0, 8);
	}
	
	public static void main(String[] args) {
		System.out.println(UUIDUtils.getUUID8());
		SimpleDateFormat smf = new SimpleDateFormat("yyyyMMddHHmmss");
		String format = smf.format(new Date());
		System.out.println(format);
	}
}
