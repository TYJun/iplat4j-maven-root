
package com.baosight.wilp.lc.mm.service;

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
 * 监测管理.
 * <p>
 * 界面初始化，查询方法,开启/关闭监测功能, 开启 / 关闭 参数记录
 * </p>
 *
 * @Title ServiceLCMM01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceLCMM01 extends ServiceBase {
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
	 * 查询方法
	 * 作者：jzm
	 * 入参：EiInfo(模块 "module", 方法 "method", 是否监测 "isMonitoring") 出参:
	 * EiInfo(监测list) 处理： 1.将入参存入map中 2.调用查询方法查询出符合入参条件的结果集 3.查询分页count
	 * 4.将结果集和分页消息封装到EiInfo中的result域
	 *
	 * @param inInfo
	 * @return
	 */

	@SuppressWarnings({ "unchecked" })
	public EiInfo query(EiInfo inInfo) {

		String projectSchema = PrUtils.getConfigure();

		String[] param = { "module", "method", "isMonitoring" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);
		map.put("projectSchema", projectSchema);

		List<Map<String, Object>> list = dao.query("LCMM01.queryMonitoring", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = super.count("LCMM01.queryMonitoringCount", map);

		// 返回
		if (!CollectionUtils.isEmpty(list)) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return inInfo;
		}
	}

	/**
	 * 开启/关闭监测功能
	 * 作者：jzm
	 * 入参：EiInfo(监测列表项id list 和 状态status)
	 * 出参：EiInfo(操作结果) 过程：
	 * 1.从入参中读取监测列表项id list 和 状态status
	 * 2.执行数据库更新语句更新相应的记录status字段值
	 * 3.返回操作结果
	 * 
	 * @param inInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo update(EiInfo inInfo) {
		/**
		 *  1.从入参中读取监测列表项id list 和 状态status
		 */
		String projectSchema = PrUtils.getConfigure();
		List<String> list = (List<String>) inInfo.get("list");
		String status = inInfo.get("status").toString();

		/**
		 * 2.执行数据库更新语句更新相应的记录status字段值
		 */
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("status", status);
		map.put("projectSchema", projectSchema);
		dao.update("LCMM01.update", map);

		/**
		 * 3.返回操作结果
		 */
		if ("1".equals(status)) {
			inInfo.setMsg("开启成功");
		} else if ("0".equals(status)) {
			inInfo.setMsg("关闭成功");
		} else {
			inInfo.setMsg("错误");
		}
		return inInfo;
	}

	/**
	 * 开启 / 关闭 参数记录
	 * 作者：hcd
	 * 入参：EiInfo(list)
	 * 出参：EiInfo(操作结果)
	 * 处理：
	 * 1.从入参读入list
	 * 2.执行数据库更新操作
	 * 3.返回操作结果
	 * @param inInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public EiInfo updateParam(EiInfo inInfo) {

		/**
		 * 1.从入参读入list
		 */
		String projectSchema = PrUtils.getConfigure();
		List<String> list = (List<String>) inInfo.get("list");
		String isParameter = inInfo.get("isParameter").toString();

		/**
		 *  2.执行数据库更新操作
		 */
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("isParameter", isParameter);
		map.put("projectSchema", projectSchema);
		dao.update("LCMM01.updateParam", map);

		/**
		 *  3.返回操作结果
		 */
		if ("1".equals(isParameter)) {
			inInfo.setMsg("开启成功");
		} else if ("0".equals(isParameter)) {
			inInfo.setMsg("关闭成功");
		} else {
			inInfo.setMsg("错误");
		}
		return inInfo;
	}
}
