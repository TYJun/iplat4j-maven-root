
package com.baosight.wilp.mc.ac.service;

import com.baosight.wilp.common.util.MaintainUtil;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息中心.
 * <p>
 * 界面初始化，查询方法,APP配置删除功能
 * </p>
 *
 * @Title ServiceMCAC01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceMCAC01 extends ServiceBase {
	/**
	 * 界面初始化
	 * 作者：jzm
	 * 入参：EiInfo 出参：EiInfo 处理：返回入参EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 查询功能
	 * 作者：jzm
	 * 入参：EiInfo（App编号 "appCode", 模板名称 "appName"） 出参：EiInfo（app信息list）
	 * 处理：
	 * 1.从入参中读取 App编号 "appCode", 模板名称 "appName" 存入map中
	 * 2.调用query()方法查询出相关的App配置列表
	 * 3.将结果封装在EiInfo的result域 4.返回EiInfo
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo query(EiInfo inInfo) {

		String projectSchema = PrUtils.getConfigure();
		String[] param = { "appCode", "appName" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);
		map.put("projectSchema", projectSchema);

		List<Map<String, Object>> list = dao.query("MCAC01.queryApp", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = super.count("MCAC01.queryAppCount", map);
		// 返回
		if (!CollectionUtils.isEmpty(list)) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return inInfo;
		}
	}

	/**
	 * APP配置删除功能
	 * 作者：jzm
	 * 入参：EiInfo（待删除APP的id list）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参中读取待删除App的id
	 * list
	 * 2.调用数据库删除方法删除相关数据
	 * 3.返回操作结果
	 * 
	 * @param inInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public EiInfo delete(EiInfo inInfo) {

		List<String> list = (List<String>) inInfo.get("list");
		String projectSchema = PrUtils.getConfigure();

		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("projectSchema", projectSchema);

		dao.delete("MCAC01.delete", map);
		inInfo.setStatus(0);
		inInfo.setMsg("删除成功");
		return inInfo;
	}
}
