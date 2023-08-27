
package com.baosight.wilp.lc.fw.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.PrUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 消息日志.
 * <p>
 * </p>
 *
 * @Title ServiceLCFW01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */

public class ServiceLCFW01 extends ServiceBase {
	/**
	 * 界面初始化
	 * 作者：hcd
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
	 * 查询监测列表
	 * 作者：hcd
	 * 入参：EiInfo（模块module 类名class 方法名 method）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参中读取参数module，class，method
	 * 2.调用查询方法query（）查询符合条件的结果
	 * 3.返回结果
	 */
	@SuppressWarnings("unchecked")
	public EiInfo queryMethod(EiInfo inInfo) {
		/**
		 * 1.从入参中读取参数module，class，method
		 */
		String module = inInfo.get("module") == null ? "" : inInfo.getString("module");// 模块
		String className = inInfo.get("class") == null ? "" : inInfo.getString("class");// 类名
		String method = inInfo.get("method") == null ? "" : inInfo.getString("method");// 方法名
		String projectSchema = PrUtils.getConfigure();

		Map<String, Object> map = new HashMap<>();
		map.put("module", module);
		map.put("className", className);
		map.put("method", method);
		map.put("projectSchema", projectSchema);

		/**
		 *  2.调用查询方法query（）查询符合条件的结果
		 */
		List<Map<String, Object>> list = dao.query("LCFW01.queryMethod", map);

		/**
		 *  3.返回结果
		 */
		inInfo.set("result", list);
		return inInfo;
	}

	/**
	 * 监测列表添加数据
	 * 作者：hcd
	 * 入参：EiInfo（模块module 类名class 方法名 method）
	 * 出参：EiInfo（操作结果）
	 * 操作：
	 * 1.读入参数:module,class,method
	 * 2.写入数据库
	 * 3.返回操作结果
	 */
	public EiInfo insertMethod(EiInfo inInfo) {

		/**
		 * 1.读入参数:module,class,method
		 */
		String module = inInfo.get("module") == null ? "" : inInfo.getString("module");// 模块
		String className = inInfo.get("class") == null ? "" : inInfo.getString("class");// 类名
		String method = inInfo.get("method") == null ? "" : inInfo.getString("method");// 方法名

		/**
		 * 2.写入数据库
		 */
		String projectSchema = PrUtils.getConfigure();

		Map<String, Object> map = new HashMap<>();
		map.put("id", UUID.randomUUID().toString());
		map.put("module", module);
		map.put("className", className);
		map.put("method", method);
		map.put("isMonitoring", "1");
		map.put("isParameter", "0");
		map.put("projectSchema", projectSchema);
		dao.insert("LCFW01.insertMethod", map);

		/**
		 * 3.返回操作结果
		 */
		inInfo.setMsg("添加成功");
		inInfo.setStatus(0);
		return inInfo;
	}

	/**
	 * 日志列表添加数据
	 * 作者：hcd
	 * 入参：EiInfo（模块module 类名class 方法名 method 参数 prames 参数 status 参数 errors）
	 * 出参：EiInfo（操作结果）
	 * 操作：
	 * 1.读入参数
	 * 2.写入数据库
	 * 3，返回操作结果
	 */
	public EiInfo insertLog(EiInfo inInfo) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(new Date());

		/**
		 * 1.读入参数
		 */
		String module = inInfo.get("module") == null ? "" : inInfo.getString("module");// 模块
		String className = inInfo.get("class") == null ? "" : inInfo.getString("class");// 类名
		String method = inInfo.get("method") == null ? "" : inInfo.getString("method");// 方法名
		String parameter = inInfo.get("prames") == null ? "" : inInfo.getString("prames");// 参数
		String status = inInfo.get("status") == null ? "" : inInfo.getString("status");// 状态
		String errors = inInfo.get("errors") == null ? "" : inInfo.getString("errors");// 错误信息

		String projectSchema = PrUtils.getConfigure();
		/**
		 * 2.写入数据库
		 */
		Map<String, Object> map = new HashMap<>();
		map.put("id", UUID.randomUUID().toString());
		map.put("module", module);
		map.put("className", className);
		map.put("method", method);
		map.put("parameter", parameter);
		map.put("status", status);
		map.put("errors", errors);
		map.put("callTime", date);
		map.put("projectSchema", projectSchema);

		dao.insert("LCFW01.insertLog", map);

		/**
		 * 3，返回操作结果
		 */
		inInfo.setMsg("添加成功");
		inInfo.setStatus(0);
		return inInfo;
	}

}
