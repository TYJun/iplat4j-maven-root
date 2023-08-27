
package com.baosight.wilp.mc.vm.service;

import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 变量管理功能.
 * <p>
 * </p>
 *
 * @Title ServiceMCVM01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */

public class ServiceMCVM01 extends ServiceBase {
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
	 * 入参：EiInfo（变量编号 "variableCode",变量名称 "variableName",创建人 "recCreater"）
	 * 出参：EiInfo（变量信息list）
	 * 处理：
	 * 1.从入参中读取 变量编号 "variableCode",变量名称 "variableName",创建人
	 * "recCreater" 存入map中
	 * 2.调用query()方法查询出相关的变量列表
	 * 3.将结果封装在EiInfo的resultVar域
	 * 4.返回EiInfo
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		/**
		 *  1.从入参中读取 变量编号 "variableCode",变量名称 "variableName",创建人
		 * 	 * "recCreater" 存入map中
		 */
		String projectSchema = PrUtils.getConfigure();
		String[] param = { "variableCode", "variableName", "recCreater" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = PrUtils.changeToMap(inInfo, "resultVar", plist);

		/**
		 *  2.调用query()方法查询出相关的变量列表
		 */
		map.put("projectSchema", projectSchema);
		List<Map<String, Object>> list = dao.query("MCVM01.queryVar", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = super.count("MCVM01.queryVarCount", map);
		/**
		 *  3.将结果封装在EiInfo的resultVar域
		 */
		if (!CollectionUtils.isEmpty(list)) {
			return PrUtils.BuildOutEiInfo(inInfo, "resultVar", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return inInfo;
		}
	}

	/**
	 * 查询人员
	 * 作者：jzm
	 * 入参：EiInfo（当前变量id curVarId）
	 * 出参：EiInfo（人员信息list）
	 * 处理：
	 * 1.从入参中读取 当前变量id
	 * curVarId 存入map中
	 * 2.调用query()方法查询出相关的人员列表
	 * 3.将结果封装在EiInfo的resultPer域
	 * 4.返回EiInfo
	 */
	public EiInfo queryPer(EiInfo inInfo) {
		/**
		 *  1.从入参中读取 当前变量id
		 * 	 * curVarId 存入map中
		 */
		String projectSchema = PrUtils.getConfigure();
		String[] param = { "curVarId" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = PrUtils.changeToMap(inInfo, "resultPer", plist);

		/**
		 * 2.调用query()方法查询出相关的人员列表
		 */
		map.put("projectSchema", projectSchema);
		List<Map<String, Object>> list = dao.query("MCVM01.queryPer", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = super.count("MCVM01.queryPerCount", map);
		/**
		 * 3.将结果封装在EiInfo的resultPer域
		 */
		if (!CollectionUtils.isEmpty(list)) {
			return PrUtils.BuildOutEiInfo(inInfo, "resultPer", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return inInfo;
		}
	}

	/**
	 * 删除变量
	 * 作者：jzm
	 * 入参：EiInfo（待删除变量的 id list）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参中获取待删除变量存入list中
	 * 2.首先删除变量人员表中的数据
	 * 3.删除变量
	 * 4.返回操作结果
	 */
	@SuppressWarnings("unchecked")
	public EiInfo deleteVar(EiInfo inInfo) {
		/**
		 * 1.从入参中获取待删除变量存入list中
		 */
		String projectSchema = PrUtils.getConfigure();
		List<String> list = (List<String>) inInfo.get("list");

		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("projectSchema", projectSchema);
		/**
		 *  2.首先删除变量人员表中的数据
		 */
		// 先删除变量人员表中的数据
		dao.delete("MCVM01.deleteVarPer", map);

		/**
		 * 3.删除变量
		 */
		// 删除变量
		dao.delete("MCVM01.delete", map);

		/**
		 *  4.返回操作结果
		 */
		inInfo.setStatus(0);
		inInfo.setMsg("删除成功");
		return inInfo;
	}

	/**
	 * 删除变量下的人员
	 * 作者：jzm
	 * 入参：EiInfo（待删除人员的 id list）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参中获取待删除人员存入list中
	 * 2.删除人员
	 * 3.返回操作结果
	 */
	public EiInfo deletePer(EiInfo inInfo) {
		/**
		 * 1.从入参中获取待删除人员存入list中
		 */
		String projectSchema = PrUtils.getConfigure();
		List<String> list = (List<String>) inInfo.get("list");

		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("projectSchema", projectSchema);
		/**
		 *  2.删除人员
		 */
		// 删除变量人员表中的数据
		dao.delete("MCVM01.deletePer", map);

		/**
		 *  3.返回操作结果
		 */
		inInfo.setStatus(0);
		inInfo.setMsg("删除成功");
		return inInfo;
	}
}
