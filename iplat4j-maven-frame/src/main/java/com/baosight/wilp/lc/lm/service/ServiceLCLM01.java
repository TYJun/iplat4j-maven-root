
package com.baosight.wilp.lc.lm.service;

import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志管理.
 * <p>
 * 界面初始化，查询方法
 * </p>
 *
 * @Title ServiceLCLM01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */

public class ServiceLCLM01 extends ServiceBase {
	/**
	 * 界面初始化
	 * 作者：jzm
	 * 入参：EiInfo
	 * 出参：EiInfo
	 * 处理：返回入参EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return inInfo;
	}

	/**
	 * 查询方法
	 * 作者：jzm
	 * 入参：EiInfo(模块 "module", 方法 "method", 类名 "className".调用时间起"callTimeS",
	 * 调用时间止 "callTimeE")
	 * 出参: EiInfo(日志list)
	 * 处理：
	 * 1.将入参存入map中
	 * 2.调用查询方法查询出符合入参条件的结果集
	 * 3.查询分页count
	 * 4.将结果集和分页消息封装到EiInfo中的result域
	 * @param inInfo
	 * @return
	 */

	@SuppressWarnings({ "unchecked" })
	@Override
	public EiInfo query(EiInfo inInfo) {
		/**
		 *  1.将入参存入map中
		 */
		String projectSchema = PrUtils.getConfigure();
		String[] param = { "module", "method", "className", "callTimeS", "callTimeE" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = PrUtils.changeToMap(inInfo, "result", plist);
		map.put("projectSchema", projectSchema);

		/**
		 * 2.调用查询方法查询出符合入参条件的结果集
		 */
		List<Map<String, Object>> list = dao.query("LCLM01.queryLog", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		/**
		 *  3.查询分页count
		 */
		int count = super.count("LCLM01.queryLogCount", map);

		/**
		 * 4.将结果集和分页消息封装到EiInfo中的result域
		 */
		if (!CollectionUtils.isEmpty(list)) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return inInfo;
		}
	}

	/**
	 * 删除方法（提供给定时任务调用）
	 * 作者：hcd
	 * @param inInfo
	 * @return
	 */
	public EiInfo delete(EiInfo inInfo) {
		String projectSchema = PrUtils.getConfigure();
		Map<String, String> map = new HashMap<String, String>();
		map.put("projectSchema", projectSchema);
		dao.delete("LCLM01.delete", map);
		return inInfo;
	}

}
