package com.baosight.wilp.cm.util;


import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.cm.domain.CMType;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建合同工单号
 */
public class CreateNoUtils {

	private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");
	// 时间转换
	private static String dateForm = "YYYYMMdd";

	/**
	 * 创建合同工单号
	 * @author zhaowei
	 * @date 2022/2/11 13:52
	 * @param type
	 * @return java.lang.String
	 */
	public synchronized static String createNo(String type) {
		// 实例化Map
		Map<String,Object> map = new HashMap();
		// 定义合同编号
		String cmNo = "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateForm);
		String format = simpleDateFormat.format(new Date());
		map.put("today",format);
		// 通过合同类型找到对应的sql方法
		String cmType = CMType.valueOf(type).getCmType();
		// 查询数据库中对应的查询方法
		List<Map<String, Object>> queryList = dao.query("CMType." + cmType, map);
		// 判断结果是否为空
		if (CollectionUtils.isNotEmpty(queryList)) {
			String result = (String) queryList.get(0).get("cmNo");
			String substring = result.substring(10, 13);
			int i = Integer.valueOf(substring) + 1;
			if (0 < i && i < 10) {
				cmNo = type + format + "00" + String.valueOf(i);
			} else if (9 < i && i < 100) {
				cmNo = type + format + "0" + String.valueOf(i);
			} else if (99 < i) {
				cmNo = type + format + String.valueOf(i);
			}
		} else {
			cmNo = type + format + "001";
		}
		return cmNo;
	}
}
