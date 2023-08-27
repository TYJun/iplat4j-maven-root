package com.baosight.wilp.au.rr.service;

import com.baosight.wilp.common.util.MaintainUtil;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * 	绑定角色子页面
 */

/**
 * 绑定角色子页面.
 * <p>
 * 界面初始化, 查询功能, 绑定角色.
 * </p>
 *
 * @Title ServiceAUHD0101.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceAURR0101 extends ServiceBase {
	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 界面初始化 入参：EiInfo 出参：EiInfo 处理：返回入参EiInfo
	 */
	public EiInfo initLoad(EiInfo inInfo) {

		return query(inInfo);
	}

	/**
	 * 查询功能 入参：EiInfo（角色编号 "roleNum", 角色名称 "roleName"） 出参：EiInfo（角色list） 处理：
	 * 1.从入参读取角色编号 "roleNum", 角色名称 "roleName" 2.调用query方法去数据库中查询出符合入参条件的角色list
	 * 3.将角色list封装在EiInfo中的"result"域中并返回
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {

		String[] param = { "resourceEname", "resourceName", "type" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);
		map.put("projectSchema", projectSchema);
		List<Map<String, Object>> roleList = dao.query("AURR0101.queryResource", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = super.count("AURR0101.queryResourceCount", map);
		// 返回
		if (!CollectionUtils.isEmpty(roleList)) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(roleList.get(0)), roleList, count);
		} else {
			return inInfo;
		}
	}

	/**
	 * 绑定角色 入参：EiInfo（角色id list，部门id deptId） 出参：EiInfo（操作结果） 处理： 1.从入参中读取角色id
	 * list，部门id deptId 2.查询子级机构 3.移除已经存在的关系 4.绑定新的关系 5.返回操作结果
	 */
	public EiInfo bindingResource(EiInfo inInfo) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		List<String> resourceList = (List<String>) inInfo.get("list");
		String roleId = inInfo.get("roleId") == null ? "" : inInfo.getString("roleId");
	
		for (int i = 0; i < resourceList.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			String id = UUID.randomUUID().toString();
			map.put("id", id);
			map.put("roleId", roleId);
			map.put("resourceId", resourceList.get(i));
			list.add(map);
		}

		if (CollectionUtils.isEmpty(list)) {
			inInfo.setMsg("没有可插入的数据");
			return inInfo;
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("projectSchema", projectSchema);
		// 移除已经存在的关系
		dao.delete("AURR01.deleteRoleResource", map);

		// 绑定新的关系
		dao.insert("AURR0101.bindingResource", map);

		return inInfo;
	}

}
