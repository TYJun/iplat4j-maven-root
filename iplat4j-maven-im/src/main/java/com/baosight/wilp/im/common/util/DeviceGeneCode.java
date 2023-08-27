package com.baosight.wilp.im.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.im.common.domain.DeviceBillType;

/**
 * 生成编码的工具类:生成最大的编码、查询最大的编码(数字)、补0生成工单号
 * <p>1.生成最大的编码 geneCode
 * <p>2.查询最大的编码(数字) getMaxCode
 * <p>3.补0生成工单号 addZero
 * @Title: DeviceGeneCode.java
 * @ClassName: DeviceGeneCode
 * @Package：com.baosight.wilp.di.common.util
 * @author: LENOVO
 * @date: 2021年8月11日 下午4:04:23
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@SuppressWarnings("all")
public class DeviceGeneCode extends ServiceBase	 {
	
	private static Dao dao = (Dao)PlatApplicationContext.getBean("dao");

	//维护最大的编号
	private static Map<String, Integer> maxCodeMap = new HashMap<>();
	
	//校验生成的日期
	private static String valiTime = "";
	
	/**
	 *  生成最大的编码
	 * @param billType 需要生成编码的类型
	 * @return
	 * @throws Exception String
	 * @throws
	 */
	public static synchronized String geneCode(DeviceBillType billType) {
		//当前日期
		String nowTime = new SimpleDateFormat("yyMMdd").format(new Date());
		//map的key
		String key = billType.getKey();
		//如果日期和当前日期不等, 则清空map
		if (!nowTime.equals(valiTime)) {
			maxCodeMap.clear();
			valiTime = nowTime;
		}
		//如果当前key下的 最大编码为空 则查询数据库获取最大编码
		Integer value = maxCodeMap.get(key);
		//前缀
		String prefix = key + valiTime;
		if (value == null) {
			Integer maxCode = getMaxCode(billType, prefix);
			maxCodeMap.put(key, maxCode);
		} else {
			maxCodeMap.put(key, ++value);
		}
		//返回单号
		return prefix+addZero(maxCodeMap.get(key));
	}
	
	/**
	 *  查询最大的编码(数字)
	 * @param billType 单据类型
	 * @param prefix 匹配前缀
	 * @return
	 * @throws Exception Integer
	 * @throws
	 */
	private static Integer getMaxCode(DeviceBillType billType, String prefix) {
		//查询各个模块数据库最大的编码
		String code = "";
		if (billType == DeviceBillType.IM_ITEMCLASS) {
			List l1 = dao.query("IMXM01.getMaxItemClass", prefix);
			if (l1 != null && l1.size() > 0) 
				code = (String) l1.get(0);
		} else if (billType == DeviceBillType.IM_ITEM) {
			List l2 = dao.query("IMXM01.getMaxItem", prefix);
			if (l2 != null && l2.size() > 0) 
				code = (String) l2.get(0);
		} else if (billType == DeviceBillType.IM_SCHEME) {
			List l3 = dao.query("IMXM01.getMaxScheme", prefix);
			if (l3 != null && l3.size() > 0) 
				code = (String) l3.get(0);
		} else if (billType == DeviceBillType.IM_TASK) {
			List l4 = dao.query("IMXM01.getMaxTask", prefix);
			if (l4 != null && l4.size() > 0) 
				code = (String) l4.get(0);
		} 
		if (StringUtil.isEmpty(code)) {
			code = prefix + "00000";
		}
		//提取出后面的五位数字
		Integer num = Integer.valueOf(code.substring(prefix.length(), code.length()));
		return ++num;
	}
	
	/**
	 *  补0生成工单号
	 *  <p>循环补0
	 * @param value
	 * @return String
	 * @throws
	 */
	private static String addZero(Integer value) {
		String valString = String.valueOf(value);
		Integer length = valString.length();
		if (length > 5) {
			throw new RuntimeException("编码数字长度过大!");
		}
		//循环补0
		for (int i = length; i < 5; i++) {
			valString = "0" + valString;
		}
		return valString;
	}
	
}
