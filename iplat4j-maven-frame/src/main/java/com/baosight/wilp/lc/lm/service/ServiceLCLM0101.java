
package com.baosight.wilp.lc.lm.service;

import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import org.apache.commons.collections.CollectionUtils;

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
public class ServiceLCLM0101 extends ServiceBase {
	/**
	 * 界面初始化
	 * 作者：jzm
	 * 入参：EiInfo
	 * 出参：EiInfo
	 * 处理：返回入参EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 查询方法
	 * 作者：jzm
	 * 入参：EiInfo()
	 * 出参: EiInfo(日志list) 处理：
	 * @param inInfo
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@Override
	public EiInfo query(EiInfo inInfo) {

		String projectSchema = PrUtils.getConfigure();

		Map<String, String> map = new HashMap<>();
		map.put("id", inInfo.getAttr().get("id").toString());
		map.put("projectSchema", projectSchema);

		EiInfo outInfo = new EiInfo();
		List<Map<String, String>> list = dao.query("LCLM0101.queryParameter", map);
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}
		outInfo.setAttr(list.get(0));
		return outInfo;
	}

}
