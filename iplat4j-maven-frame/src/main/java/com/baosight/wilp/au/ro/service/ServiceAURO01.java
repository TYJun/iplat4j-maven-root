package com.baosight.wilp.au.ro.service;

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
 * 角色管理.
 * <p>
 * 界面初始化, 查询功能, 删除功能.
 * </p>
 *
 * @Title ServiceAURO01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceAURO01 extends ServiceBase {
	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 界面初始化
	 * 作者：jzm
	 * 入参：EiInfo
	 * 出参：EiInfo
	 * 处理：
	 * 返回入参EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 查询功能
	 * 入参：EiInfo（角色编号 "roleNum", 角色名称 "roleName"）
	 * 出参：EiInfo（角色list）
	 * 处理：
	 * 1.从入参读取角色编号 "roleNum", 角色名称 "roleName"
	 * 2.调用query方法去数据库中查询出符合入参条件的角色list
	 * 3.将角色list封装在EiInfo中的"result"域中并返回
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		/**
		 * 1.从入参读取角色编号 "roleNum", 角色名称 "roleName"
		 */
		String[] param = { "roleNum", "roleName" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);
		map.put("projectSchema", projectSchema);

		/**
		 * 2.调用query方法去数据库中查询出符合入参条件的角色list
		 */
		List<Map<String, Object>> list = dao.query("AURO01.queryRole", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = super.count("AURO01.queryRoleCount", map);

		/**
		 * 3.将角色list封装在EiInfo中的"result"域中并返回
		 */
		if (!CollectionUtils.isEmpty(list)) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return inInfo;
		}

	}

	/**
	 * 删除功能
	 * 作者：jzm
	 * 入参：EiInfo（待删除角色的id list）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.首先判断一下该角色下有无用户，如果有则提示无法删除，结束此方法
	 * 2.判断一下该角色下有无科室，如果有则提示无法删除，结束此方法
	 * 3.执行delete()方法从数据库中删除相关记录
	 * 4.返回操作结果
	 */
	public EiInfo delete(EiInfo inInfo) {
		List<String> list = (List<String>) inInfo.get("list");

		/**
		 *  1.首先判断一下该角色下有无用户，如果有则提示无法删除，结束此方法
		 */
		for (String roleId : list) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("roleId", roleId);
			map.put("projectSchema", projectSchema);
			int num = super.count("AURO01.countRolePer", map);
			if (num > 0) {
				inInfo.setMsg("删除失败,请先删除所选角色下的用户");
				inInfo.setStatus(-1);
				return inInfo;
			}
		}

		/**
		 * 2.判断一下该角色下有无科室，如果有则提示无法删除，结束此方法
		 */
		for (String roleId : list) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("roleId", roleId);
			map.put("projectSchema", projectSchema);
			int num = super.count("AURO01.countRoleDept", map);
			if (num > 0) {
				inInfo.setMsg("删除失败,请先删除所选角色下的科室");
				inInfo.setStatus(-1);
				return inInfo;
			}
		}

		/**
		 * 3.执行delete()方法从数据库中删除相关记录
		 */
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("projectSchema", projectSchema);
		dao.delete("AURO01.delete", map);

		/**
		 * 4.返回操作结果
		 */
		inInfo.setStatus(0);
		inInfo.setMsg("删除成功");
		return inInfo;
	}
}
