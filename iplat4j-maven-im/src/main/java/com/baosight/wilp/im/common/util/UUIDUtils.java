package com.baosight.wilp.im.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 生成随机的字符串的工具类：获取32位uuid、获取8位uuid
 * <p>1.获取32位uuid getUUID32
 * <p>2.获取8位uuid getUUID8
 * 
 * @Title: UUIDUtils.java
 * @ClassName: UUIDUtils
 * @Package：com.baosight.wilp.di.common.util
 * @author: LENOVO
 * @date: 2021年8月11日 下午4:10:18
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class UUIDUtils {

    /**
     * 获取32位uuid
     *
     * @Title: getUUID32 
     * @return 
     * @return: String
     */
	public static String getUUID32(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 获取8位uuid
	 *
	 * @Title: getUUID8 
	 * @return 
	 * @return: String
	 */
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
