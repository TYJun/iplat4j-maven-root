package com.baosight.wilp.df.common.util;

import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.df.common.domain.ParamKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * 设备档案工具类
 * 
 * @Title: ParamKeyCode.java
 * @ClassName: ParamKeyCode
 * @Package：com.baosight.wilp.df.common.util
 * @author: zhaow
 * @date: 2021年8月11日 下午2:40:50
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ParamKeyCode {
    // 获取Spring容器中已初始化的bean
	private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");
	
	// 维护最大编号
	private static Map<String,Integer> maxCodeMap = new HashMap<>();
	
	// 校验生成的日期
	private static String valiTime = "";
	
	/**
	 * 生成最大的编码
	 */
	public static synchronized String geneCode(ParamKey paramKey) {
		
		// 当前日期
		String nowTime = new SimpleDateFormat("yyMMdd").format(new Date());
		// map的key
		String key = paramKey.getKey();
		// 如果日期与当前日期不等，则清空map
		if(!nowTime.equals(valiTime)) {
		    // 清空map
			maxCodeMap.clear();
			// 赋值当前时间
			valiTime = nowTime;
		}
		// 如果当前key下的最大编码为空，则查询数据库获得最大编码
		Integer value = maxCodeMap.get(key);
		// 前缀
		String prefix = key + valiTime;
		// 如果最大编码为空
		if (value == null) {
		    // 调用查询最大的编码方法
			Integer maxCode = getMaxCode(paramKey, prefix);
			// 设置最大编码
			maxCodeMap.put(key, maxCode);
		} else {
		    // 设置最大编码
			maxCodeMap.put(key, ++value);
		}
		//返回单号
		return prefix+addZero(maxCodeMap.get(key));
	}
	
	/**
	 *  查询最大的编码(数字)
	 */
	private static Integer getMaxCode(ParamKey paramKey, String prefix) {
		//查询各个模块数据库最大的编码
		String code = "";
		// 如果关键字是DF
		if (paramKey == ParamKey.DF_CLASSFYPARAM) {
		    // 通过前缀查询编码
			List l1 = dao.query("DFFL10.getMaxItemClass", prefix);
			// 如果list不为空
			if (l1 != null && l1.size() > 0) 
			    // 获取list下标为一的值
				code = (String) l1.get(0);
		} 
		// 如果不存在编码
		if (StringUtil.isEmpty(code)) {
		    // 编码为前缀拼接00000
			code = prefix + "00000";
		}
		//提取出后面的五位数字
		Integer num = Integer.valueOf(code.substring(prefix.length(), code.length()));
		// 对后五位数字进行+1操作
		return ++num;
	}
	
	/**
	 *  补0生成工单号
	 */
	private static String addZero(Integer value) {
	    // String类型转换
		String valString = String.valueOf(value);
		// 统计String类型长度
		Integer length = valString.length();
		// 如果长度大于5
		if (length > 5) {
		    // 抛出异常
			throw new RuntimeException("编码数字长度过大!");
		}
		// 循环
		for (int i = length; i < 5; i++) {
		    // 拼接工号
			valString = "0" + valString;
		}
		// 返回工单号
		return valString;
	}
	
	// 创建编码头
	public static String createTop() {
	    // 设置时间转换格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        // 将当前时间按格式转换
        String head = sdf.format(new Date());
        // 返回转换头
        return head;
    }
}
