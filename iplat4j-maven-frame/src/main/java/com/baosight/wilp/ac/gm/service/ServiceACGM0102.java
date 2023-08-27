package com.baosight.wilp.ac.gm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.ArchivesLog;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 修改物资分类子页面.
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
public class ServiceACGM0102 extends ServiceBase {

	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 弹窗初始化方法(数据回显)
	 * 作者：hcd
	 * 入参：EiInfo（id 主键）
	 * 出参：EiInfo（分类信息）
	 * 处理：
	 * 1.根据id查询该分类的信息并放回
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		String id = inInfo.getString("id");
		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("projectSchema", projectSchema);
		// QUERY CLASS NAME && PARENT INFO（PARENT ID, PARENT NAME）
		List<Map<String, String>> result = (List<Map<String, String>>) dao.query("ACGM0102.initLoad", map);
		EiInfo out = new EiInfo();
		if (CollectionUtils.isEmpty(result)) {
			out.setMsg("未查到数据");
			out.setStatus(-1);
			return out;
		}
		out.setAttr(result.get(0));
		return out;
	}

	/**
	 * 修改物资分类信息
	 * 作者：hcd
	 * 入参：EiInfo（id 主键，matClassName 物资分类名称， parentId 双亲 id）
	 * 出参：EiInfo（操作结果）
	 * 处理
	 * 1. 从 inInfo 中读出id，matClassName，parentId
	 * 2. 获取recRevisor 修改人，
	 * recReviseTime修改时间
	 * 3. 存入数据库
	 * 4.返回数据
	 */
	@Override
	@ArchivesLog(model = "AC", sign = "修改物资分类")
	public EiInfo update(EiInfo inInfo) {
		/**
		 * 1. 从 inInfo 中读出id，matClassName，parentId
		 */
		String id = inInfo.get("id") == null ? "" : inInfo.getString("id"); /* 主键 */
		String matClassName = inInfo.get("matClassName") == null ? "" : inInfo.getString("matClassName"); /* 科室名称 */
		String parentId = inInfo.get("parentMatClassName") == null ? "" : inInfo.getString("parentMatClassName"); /* 父ID */

		/**
		 * 2. 获取recRevisor 修改人，
		 * 	 * recReviseTime修改时间
		 */
		String recRevisor = UserSession.getUser().getUsername();
		String recReviseTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); /* 修改时间 */
		Map<String, String> map = new HashMap<>();
		map.put("projectSchema", projectSchema);
		map.put("id", id);
		map.put("matClassName", matClassName);
		map.put("parentId", parentId);

		map.put("recRevisor", recRevisor);
		map.put("recReviseTime", recReviseTime);

		/**
		 * 3. 存入数据库
		 */
		dao.update("ACGM0102.update", map);

		/**
		 * 4.返回数据
		 */
		return inInfo;
	}
}
