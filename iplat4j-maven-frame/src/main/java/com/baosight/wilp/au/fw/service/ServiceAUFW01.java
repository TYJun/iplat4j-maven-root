package com.baosight.wilp.au.fw.service;

import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

/**
 * 权限中心对外服务接口.
 * <p>
 * 1.查询用户院区 2.通过员工ID或者员工工号查询用户所拥有的院区 3.通过员工ID或者员工工号查询用户所拥有的科室权限 4.通过员工工号查询院区编码
 * 5.通过科室编码查询院区编码 .
 * </p>
 *
 * @Title ServiceAUFW01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceAUFW01 extends ServiceBase {

	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 1.查询用户院区
	 * 作者：hcd
	 * 入参：EiInfo（用户工号 workNo）
	 * 出参：EiInfo（院区信息）
	 * 处理：
	 * 1.从入参中读取workNo
	 * 2.调用query()方法从数据库中查出满足条件的院区信息
	 * 3.将院区信息list封装在EiInfo的result域并返回
	 */
	public EiInfo getUserGroups(EiInfo inInfo) {
		/**
		 *  1.从入参中读取workNo
		 */
		String workNo = inInfo.get("workNo") == null ? "" : inInfo.getString("workNo");

		/**
		 * 2.调用query()方法从数据库中查出满足条件的院区信息
		 */
		Map<String, String> map = new HashMap<>();
		map.put("workNo", workNo);
		map.put("projectSchema", projectSchema);
		List<Map<String, Object>> list = dao.query("AUFW01.getUserGroups", map);

		/**
		 * 3.将院区信息list封装在EiInfo的result域并返回
		 */
		inInfo.set("result", list);
		return inInfo;
	}

	/**
	 * 2.通过员工ID或者员工工号查询用户所拥有的院区
	 * 作者：hcd
	 * 入参：EiInfo（院区编号 datagroupCode，员工工号workNo）
	 * 出参：EiInfo（科室信息）
	 * 处理：
	 * 1.从入参中读取workNo和datagroupCode
	 * 2.调用query()方法从数据库中查出满足条件的科室信息
	 * 3.将科室信息list封装在EiInfo的result域并返回
	 */
	public EiInfo getUserDepts(EiInfo inInfo) {

		/**
		 * 1.从入参中读取workNo和datagroupCode
		 */
		String datagroupCode = inInfo.get("datagroupCode") == null ? "" : inInfo.getString("datagroupCode");
		String workNo = inInfo.get("workNo") == null ? "" : inInfo.getString("workNo");

		/**
		 *  2.调用query()方法从数据库中查出满足条件的科室信息
		 */
		Map<String, String> map = new HashMap<>();
		map.put("workNo", workNo);
		map.put("datagroupCode", datagroupCode);
		map.put("projectSchema", projectSchema);
		List<Map<String, Object>> list = dao.query("AUFW01.getUserDepts", map);

		/**
		 * 3.将科室信息list封装在EiInfo的result域并返回
		 */
		inInfo.set("result", list);
		return inInfo;
	}

	/**
	 * 3.通过员工ID或者员工工号查询用户所拥有的科室权限
	 * 作者：hcd
	 * 入参：EiInfo（院区编号 datagroupCode，员工工号workNo）
	 * 出参：EiInfo（用户所拥有的科室权限信息）
	 * 处理：
	 * 1.从入参中读取workNo和datagroupCode
	 * 2.调用query()方法从数据库中查出满足条件的用户所拥有的科室权限
	 * 3.将用户所拥有的科室权限信息list封装在EiInfo的result域并返回
	 */
	public EiInfo getDepts(EiInfo inInfo) {

		/**
		 * 1.从入参中读取workNo和datagroupCode
		 */
		String workNo = inInfo.get("workNo") == null ? "" : inInfo.getString("workNo");
		String datagroupCode = inInfo.get("datagroupCode") == null ? "" : inInfo.getString("datagroupCode");

		/**
		 *  2.调用query()方法从数据库中查出满足条件的用户所拥有的科室权限
		 */
		Map<String, String> map = new HashMap<>();
		map.put("workNo", workNo);
		map.put("datagroupCode", datagroupCode);
		map.put("projectSchema", projectSchema);
		List<Map<String, Object>> list = dao.query("AUFW01.getUserDepts", map);

		/**
		 * 3.将用户所拥有的科室权限信息list封装在EiInfo的result域并返回
		 */
		if (list.size() > 0) {
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("list", list);
			map1.put("projectSchema", projectSchema);
			List<Map<String, Object>> deptList = dao.query("AUFW01.getDepts", map1);

			inInfo.set("result", deptList);
		} else {
			inInfo.set("result", null);
		}

		return inInfo;
	}

	/**
	 * 4.通过员工工号查询院区编码
	 * 作者：hcd
	 * 入参：EiInfo（员工工号workNo）
	 *
	 * 出参：EiInfo（院区编码信息）
	 * 处理：
	 * 1.从入参中读取workNo
	 * 2.调用query()方法从数据库中查出满足条件的院区编码信息
	 * 3.判断上次登录的院区，如果为空查询用户所有院区取第一个
	 * 4.将院区编码信息封装在EiIngo中的datagroupCode域
	 */
	public EiInfo getGroupsToUser(EiInfo inInfo) {

		String platSchema = PrUtils.getIplatConfigure();

		String datagroupCode = null;

		/**
		 *  1.从入参中读取workNo
		 */
		String workNo = inInfo.get("workNo") == null ? "" : inInfo.getString("workNo");

		/**
		 * 2.调用query()方法从数据库中查出满足条件的院区编码信息
		 */
		Map<String, String> map = new HashMap<>();
		map.put("workNo", workNo);
		map.put("projectSchema", projectSchema);
		map.put("platSchema", platSchema);
		List<Map<String, Object>> list = dao.query("AUFW01.getGroupsToUser", map);

		/**
		 * 3.判断上次登录的院区，如果为空查询用户所有院区取第一个
		 */
		// 查询上次登录的院区，如果为空查询用户所有院区取第一个
		if (list.size() > 0) {
			datagroupCode = list.get(0).get("last_data_group_code").toString();
		} else {
			List<Map<String, Object>> dataGroups = dao.query("AUFW01.getUserGroups", map);
			if (dataGroups.size() == 1) {
				datagroupCode = dataGroups.get(0).get("value").toString();
			}
		}

		/**
		 * 4.将院区编码信息封装在EiIngo中的datagroupCode域
		 */
		inInfo.set("datagroupCode", datagroupCode);
		return inInfo;
	}

	/**
	 * 5.通过科室编码查询院区编码
	 * 作者：hcd
	 * 入参：EiInfo（科室编码deptNum）
	 * 出参：EiInfo（院区编码信息）
	 * 处理：
	 * 1.从入参中读取workNo
	 * 2.调用query()方法从数据库中查出满足条件的院区编码信息
	 * 3.判断上次登录的院区，如果为空查询用户所有院区取第一个
	 * 4.将院区编码信息封装在EiIngo中的datagroupCode域
	 */
	public EiInfo getGroupsToDept(EiInfo inInfo) {

		String datagroupCode = null;

		/**
		 * 1.从入参中读取workNo
		 */
		String workNo = inInfo.get("deptNum") == null ? "" : inInfo.getString("deptNum");

		/**
		 * 2.调用query()方法从数据库中查出满足条件的院区编码信息
		 */
		Map<String, String> map = new HashMap<>();
		map.put("deptNum", workNo);
		map.put("projectSchema", projectSchema);
		List<Map<String, Object>> list = dao.query("AUFW01.getGroupsToDept", map);

		/**
		 * 3.判断上次登录的院区，如果为空查询用户所有院区取第一个
		 */
		if (list.size() == 1) {
			datagroupCode = list.get(0).get("hospital_district").toString();
		}

		/**
		 * 4.将院区编码信息封装在EiIngo中的datagroupCode域
		 */
		inInfo.set("datagroupCode", datagroupCode);
		return inInfo;
	}
	
	/**
	 * 6.获取用户APP资源
	 * 作者：hcd
	 * 入参：EiInfo（用户工号 workNo）
	 * 出参：EiInfo（院区信息）
	 * 处理：
	 * 1.从入参中读取workNo
	 * 2.判断userId，workNo是否全为空
	 * 3.调用query()方法从数据库中查出满足条件的院区信息
	 * 4.将院区信息list封装在EiInfo的result域并返回
	 */
	public EiInfo getResource(EiInfo inInfo) {
		/**
		 *  1.从入参中读取workNo
		 */
		String workNo = inInfo.get("workNo") == null ? "" : inInfo.getString("workNo");
		String userId = inInfo.get("userId") == null ? "" : inInfo.getString("userId");
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		/**
		 *  2.判断userId，workNo是否全为空
		 */
		if ("".equals(userId) && "".equals(workNo)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数不能全部为空!");
		} else {
			/**
			 * 3.如果不全为空则从数据库中查询出相关数据存入EiInfo result 域
			 */
			Map<String, String> map = new HashMap<>();
			map.put("workNo", workNo);
			map.put("userId", userId);
			map.put("projectSchema", projectSchema);
			list = dao.query("AUFW01.getResource", map);
		}
		/**
		 * 4.将院区信息list封装在EiInfo的result域并返回
		 */
		inInfo.set("result", list);
		return inInfo;
	}

}
