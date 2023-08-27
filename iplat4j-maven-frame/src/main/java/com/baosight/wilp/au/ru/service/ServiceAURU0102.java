package com.baosight.wilp.au.ru.service;

import com.baosight.wilp.common.util.MaintainUtil;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 查看选中用户的所有角色信息.
 * <p>
 * 界面初始化, 通过用户查询该用户拥有的角色.
 * </p>
 *
 * @Title ServiceAURU0102.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceAURU0102 extends ServiceBase {
	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 界面初始化
	 * 作者：hcd
	 * 入参：EiInfo 出参：EiInfo 处理：返回入参EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 通过用户查询该用户拥有的角色
	 * 作者：hcd
	 * 入参：EiInfo（员工id）
	 * 出参：EiInfo（角色列表）
	 * 处理：
	 * 1.从入参读取员工id
	 * 2.调用query方法读取该员工的角色信息
	 * 3.将角色信息封装在EiInfo中返回
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		/**
		 * 1.从入参读取员工id
		 */
		String[] param = { "perId" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);

		/**
		 * 2.调用query方法读取该员工的角色信息
		 */
		map.put("projectSchema", projectSchema);
		List<Map<String, Object>> list = dao.query("AURU01.queryRoleListByPerson", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));
		int count = super.count("AURU01.queryRoleListByPersonCount", map);

		/**
		 * 3.将角色信息封装在EiInfo中返回
		 */
		if (!CollectionUtils.isEmpty(list)) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return inInfo;
		}

	}

}
