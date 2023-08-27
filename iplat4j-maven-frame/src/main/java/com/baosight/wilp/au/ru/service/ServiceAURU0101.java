package com.baosight.wilp.au.ru.service;

import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 添加角色与用户关联.
 * <p>
 * 界面初始化, 添加用户到指定角色.
 * </p>
 *
 * @Title ServiceAURU0101.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceAURU0101 extends ServiceBase {
	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 界面初始化
	 * 作者：hcd
	 * 入参：EiInfo 出参：EiInfo 处理：返回入参EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return inInfo;
	}

	/**
	 * 添加用户到指定角色 SQL中自动过滤已存在关系
	 * 作者：hcd
	 * 入参：EiInfo（待添加人员id list，角色id roleId）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参读取待添加人员id list，角色id roleId
	 * 2.调用insert()方法将数据插入数据库
	 * 3.返回操作结果
	 */
	public EiInfo add(EiInfo info) {
		/**
		 * 1.从入参读取待添加人员id list，角色id roleId
		 */
		List<String> perIds = (List<String>) info.get("list");
		String roleId = info.getString("roleId");

		/**
		 *  2.调用insert()方法将数据插入数据库
		 */
		for (String perId : perIds) {
			Map<String, String> map = new HashMap<String, String>();
			String id = UUID.randomUUID().toString();
			map.put("id", id);
			map.put("perId", perId);
			map.put("roleId", roleId);
			map.put("projectSchema", projectSchema);
			dao.insert("AURU01.insert", map);
		}

		/**
		 * 3.返回操作结果
		 */
		info.setMsg("添加成功");
		return info;
	}

}
