package com.baosight.wilp.au.ar.service;

import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.xservices.xs.util.UserSession;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 角色添加子页面.
 * <p>
 * 界面初始化, 新增角色, 查询功能.
 * </p>
 *
 * @Title ServiceAURO0101.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceAUAR0101 extends ServiceBase {
	final String projectSchema = PrUtils.getConfigure();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 界面初始化 入参：EiInfo 出参：EiInfo 处理：返回入参EiInfo
	 */
	public EiInfo initLoad(EiInfo inInfo) {

		return inInfo;
	}

	/**
	 * 新增资源 入参：EiInfo（4.返回操作结果
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public EiInfo insert(EiInfo inInfo) {

		Map map = inInfo.getAttr();
		String id = UUID.randomUUID().toString(); // 主键

		String recCreateTime = sdf.format(new Date()); // 创建时间
		String recCreator = UserSession.getUser().getUsername();// 创建人

		map.put("id", id);
		map.put("status", "1");
		map.put("recCreator", recCreator);
		map.put("recCreateTime", recCreateTime);
		map.put("projectSchema", projectSchema);

		dao.insert("AUAR0101.insertResource", map);
		inInfo.setMsg("资源添加成功!");
		return inInfo;
	}
	
}
