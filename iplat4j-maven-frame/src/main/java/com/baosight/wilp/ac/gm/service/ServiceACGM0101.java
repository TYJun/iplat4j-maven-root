package com.baosight.wilp.ac.gm.service;

import com.baosight.wilp.common.util.ArchivesLog;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 新增物资分类子页面.
 * <p>
 * 页面初始化方法, 查询, 新增分类.
 * </p>
 *
 * @Title ServiceACGM0101.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceACGM0101 extends ServiceBase {

	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 页面初始化方法
	 * 作者：hcd
	 * 入参：EiInfo
	 * 出参：EiInfo
	 * 处理：调用query()方法
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		HashMap<String, String> map = new HashMap<>();
		map.put("projectSchema", projectSchema);
		map.put("matClassId", inInfo.getString("matClassId"));
		List<Map<String, Object>> mats = dao.query("ACGM01.queryMatClassNameById", map);
		EiInfo out = new EiInfo();
		if (mats.size() > 0) {
			out.setAttr(mats.get(0));
		}
		return out;
	}

	/**
	 * 新增分类
	 * 作者：hcd
	 * 入参：matClassName 物资分类名称，parentId双亲ID
	 * 出参：inInfo（返回新增结果）
	 * 处理：
	 * 1.自动生成主键id，获取matClassName 物资分类名称，parentId双亲ID,
	 * 从UserSession获取当前登录人信息存入recCreater，生成当前时间存入recCreateTime
	 * 2.按规则自动生成物资分类编码
	 * 3.调用dao.insert()方法插入数据
	 * 4.返回操作结果
	 */
	@Override
	@ArchivesLog(model = "AC", sign = "新增物资分类")
	public EiInfo insert(EiInfo inInfo) {
		/**
		 * 1.自动生成主键id，获取matClassName 物资分类名称，parentId双亲ID,
		 * 	 * 从UserSession获取当前登录人信息存入recCreater，生成当前时间存入recCreateTime
		 */
		String id = UUID.randomUUID().toString(); /* 主键 */
		String matClassCode = null; /* 物资分类编码 */
		String matClassName = inInfo.get("matClassName") == null ? "" : inInfo.getString("matClassName"); /* 物资分类名称 */
		String parentId = inInfo.get("parentMatClassName") == null ? "" : inInfo.getString("parentMatClassName"); /* 父ID */

		String recCreator = UserSession.getUser().getUsername();
		String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); /* 创建时间 */

		Map<String, String> map = new HashMap<>();

		map.put("projectSchema", projectSchema);

		/**
		 * 2.按规则自动生成物资分类编码
		 */
		// 先查询出当前最新的 rec_create_time 取出该记录的spotNum
		List<Map<String, String>> list = dao.query("ACGM0101.queryLastMatClassCode", map);
		if (CollectionUtils.isEmpty(list)) { // 如果没有记录则生成一号 MC00001
			matClassCode = "MC00001";
		} else {
			matClassCode = genMatClassCode(list.get(0).get("matClassCode")); // 生成物资分类编码
		}

		map.put("id", id);
		map.put("matClassCode", matClassCode);
		map.put("matClassName", matClassName);
		map.put("parentId", parentId);

		map.put("status", "1");
		map.put("recCreator", recCreator);
		map.put("recCreateTime", recCreateTime);
		/**
		 * 3.调用dao.insert()方法插入数据
		 */
		dao.insert("ACGM0101.insert", map);

		/**
		 * 4.返回操作结果
		 */
		return inInfo;
	}

	/**
	 * 按照 MC00000 方式生成物资分类编号
	 * 作者：jzm
	 * 入参：lastMatClassCode
	 * 出参：nextMatClassCode
	 * 处理：取后五位转整型然后加一，转字符串并在前面拼接前缀返回。
	 */
	public static String genMatClassCode(String lastMatClassCode) {
		return "MC" + String.format("%05d", Integer.parseInt(lastMatClassCode.substring(2)) + 1);
	}
}
