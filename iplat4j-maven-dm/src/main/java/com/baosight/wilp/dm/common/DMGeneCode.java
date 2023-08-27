package com.baosight.wilp.dm.common;

import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.dm.common.domain.DMGeneCodeType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成编码的工具类:生成最大的编码、查询最大的编码(数字)、补0生成工单号
 * <p>1.生成最大的编码 geneCode
 * <p>2.查询最大的编码(数字) getMaxCode
 * <p>3.补0生成工单号 addZero
 * @Title: DMGeneCode.java
 * @ClassName: DMGeneCode
 * @Package：com.baosight.wilp.dm.common
 * @author: fangzekai
 * @date: 2022年3月25日 下午4:04:23
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
@SuppressWarnings("all")
public class DMGeneCode extends ServiceBase	 {
	
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
	public static synchronized String geneCode(DMGeneCodeType geneCodeType) {
		//当前日期
		String nowTime = new SimpleDateFormat("yyMMdd").format(new Date());
		//map的key
		String key = geneCodeType.getKey();
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
			Integer maxCode = getMaxCode(geneCodeType, prefix);
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
	private static Integer getMaxCode(DMGeneCodeType geneCodeType, String prefix) {
		//查询各个模块数据库最大的编码
		String code = "";
		if (geneCodeType == DMGeneCodeType.DM_ITEMCLASS) {
			List l1 = dao.query("DMPJ02.getMaxItemClass", prefix);
			if (l1 != null && l1.size() > 0) 
				code = (String) l1.get(0);
		}else if (geneCodeType == DMGeneCodeType.DM_ITEM) {
			List l2 = dao.query("DMPJ02.getMaxItem", prefix);
			if (l2 != null && l2.size() > 0) 
				code = (String) l2.get(0);
		}
		if (StringUtil.isEmpty(code)) {
			code = prefix + "0000";
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
