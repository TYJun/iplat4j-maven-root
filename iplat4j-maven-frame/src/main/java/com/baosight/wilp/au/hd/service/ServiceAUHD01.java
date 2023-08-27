package com.baosight.wilp.au.hd.service;

import com.baosight.wilp.common.util.MaintainUtil;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * 数据权限管理.
 * <p>
 * 界面初始化, 查询功能, 解除角色.
 * </p>
 *
 * @Title ServiceAUHD01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceAUHD01 extends ServiceBase {
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
		return inInfo;
	}

	/**
	 * 查询功能
	 * 作者：jzm
	 * 入参：EiInfo（工号 "workNo",姓名 "name",科室名称 "deptName",当前科室Id "curDeptId"）
	 * 出参：EiInfo（角色列表）
	 * 处理：
	 * 1.从入参中读取"workNo", "name", "deptName", "curDeptId"
	 * 2.调用query()方法查询满足条件的角色结果roleList
	 * 3.将查询结果roleList封装到EiInfo中的result区域
	 * 4.返回EiInfo
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {

		/**
		 *  1.从入参中读取"workNo", "name", "deptName", "curDeptId"
		 */
		String[] param = { "roleNum", "roleName", "curDeptId" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);
		map.put("projectSchema", projectSchema);

		// 初始化列表不显示数据
		if (map.get("curDeptId") == null || "".equals(map.get("curDeptId").toString())) {
			return inInfo;
		}
		/**
		 * 2.调用query()方法查询满足条件的角色结果roleList
		 */
		List<Map<String, Object>> roleList = dao.query("AUHD01.queryRoleList", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = super.count("AUHD01.queryRoleCount", map);

		/**
		 * 3.将查询结果roleList封装到EiInfo中的result区域
		 */
		/**
		 * 4.返回EiInfo
		 */
		if (!CollectionUtils.isEmpty(roleList)) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(roleList.get(0)), roleList, count);
		} else {
			return inInfo;
		}
	}

	/**
	 * 解除角色
	 * 作者：hcd
	 * 入参：EiInfo（科室deptId 角色id list）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参获取科室id
	 * deptId和角色list
	 * 2.查询出上级机构deptList
	 * 3.如果存在上级机构则查询上级机构是否绑定角色
	 * 4.如果上级机构绑定角色则提示该角色存在父级权限,请先解除父级权限！并返回EiInfo
	 * 5.如果上级机构未绑定角色信息则调用delete()方法去数据库中删除对应的数据并返回删除结果
	 */
	public EiInfo relieveRole(EiInfo inInfo) {
		/**
		 * 1.从入参获取科室id  deptId和角色list
		 */
		List<String> roleList = (List<String>) inInfo.get("list");

		String deptId = inInfo.get("deptId") == null ? "" : inInfo.getString("deptId");

		/**
		 *  2.查询出上级机构deptList
		 */
		// 查询上级机构
		HashMap<String, String> map1 = new HashMap<>();
		map1.put("projectSchema", projectSchema);
		map1.put("deptId", deptId);
		List<Map<String, Object>> deptList = dao.query("AUHD01.getChildrenList", map1);

		/**
		 * 3.如果存在上级机构则查询上级机构是否绑定角色
		 */
		if (deptList.size() > 0) {
			List<Map<String, Object>> parmeList = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < deptList.size(); i++) {
				for (int j = 0; j < roleList.size(); j++) {
					Map<String, Object> dpMap = new HashMap<>();
					dpMap.put("roleId", roleList.get(j));
					dpMap.put("deptId", deptList.get(i).get("id"));
					parmeList.add(dpMap);
				}
			}

			/**
			 * 4.如果上级机构绑定角色则提示该角色存在父级权限,请先解除父级权限！并返回EiInfo
			 */
			// 查询上级机构是否绑定角色
			HashMap<String, Object> map = new HashMap<>();
			map.put("parmeList", parmeList);
			map.put("projectSchema", projectSchema);
			List<Map<String, Object>> dpList = dao.query("AUHD01.queryDpList", map);
			if (dpList.size() > 0) {
				inInfo.setMsg("该角色存在父级权限,请先解除父级权限!");
				return inInfo;
			}
		}

		/**
		 * 5.如果上级机构未绑定角色信息则调用delete()方法去数据库中删除对应的数据并返回删除结果
		 */
		// 查询所有子结构
		EiInfo info = new EiInfo();
		info.set("id", deptId);
		info.set(EiConstant.serviceName, "ACDE01");
		info.set(EiConstant.methodName, "getDeptList");
		info = XLocalManager.call(info);

		List<Map<String, String>> deptList2 = (List<Map<String, String>>) info.get("result");

		//
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < roleList.size(); i++) {
			for (int j = 0; j < deptList2.size(); j++) {
				Map<String, Object> map2 = new HashMap<>();
				map2.put("roleId", roleList.get(i));
				map2.put("deptId", deptList2.get(j).get("id"));
				list.add(map2);
			}
		}

		Map<String, Object> map3 = new HashMap<>();

		map3.put("list", list);
		map3.put("projectSchema", projectSchema);

		dao.delete("AUHD01.relieveRole", map3);

		inInfo.setMsg("解除成功");
		return inInfo;
	}

}
