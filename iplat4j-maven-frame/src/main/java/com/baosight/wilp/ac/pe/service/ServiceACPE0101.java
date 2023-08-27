package com.baosight.wilp.ac.pe.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.ArchivesLog;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 新增人员子页面.
 * <p>
 * 页面初始化方法, 查询员工类型下拉框数据.
 * </p>
 *
 * @Title ServiceACPE0101.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceACPE0101 extends ServiceBase {
	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 页面初始化方法
	 * 作者：hcd
	 * 入参：EiInfo
	 * 出参：EiInfo
	 * 处理：返回入参inInfo
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		return inInfo;
	}

	/**
	 * 新增人员
	 * 作者：hcd
	 * 入参：EiInfo（工号workNo，姓名name，科室ID deptId，电话contactTel，身份证号idNo，类型type，
	 * 性别gender，服务类型isService）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.自动生成主键
	 * 2.从入参中获取工号workNo，姓名name，科室ID deptId，电话contactTel，身份证号idNo，类型type，
	 * 性别gender，服务类型isService存入map中
	 * 3.验证员工工号是否重复
	 * 4.调用插入方法将数据插入数据库中
	 * 5.返回插入结果
	 */
	@Override
	@ArchivesLog(model = "AC", sign = "新增人员")
	public EiInfo insert(EiInfo inInfo) {
		/**
		 * 1.自动生成主键
		 */
		String id = UUID.randomUUID().toString(); /* 主键 */

		/**
		 * 2.从入参中获取工号workNo，姓名name，科室ID deptId，电话contactTel，身份证号idNo，类型type，
		 * 	 * 性别gender，服务类型isService存入map中
		 */
		String workNo = inInfo.get("workNo") == null ? "" : inInfo.getString("workNo"); /* 工号 */
		String name = inInfo.get("name") == null ? "" : inInfo.getString("name"); /* 姓名 */
		String deptId = inInfo.get("deptId") == null ? "" : inInfo.getString("deptId"); /* 所属科室ID */
		String contactTel = inInfo.get("contactTel") == null ? "" : inInfo.getString("contactTel"); /* 联系电话 */
		String idNo = inInfo.get("idNo") == null ? "" : inInfo.getString("idNo"); /* 身份证号 */
		String type = inInfo.get("type") == null ? "" : inInfo.getString("type"); /* 员工类型 */
		String gender = inInfo.get("gender") == null ? "" : inInfo.getString("gender"); /* 性别 */
		String isService = inInfo.get("isService") == null ? "" : inInfo.getString("isService"); /* 服务人员 */
		String status = "1";

		String recCreater = UserSession.getUser().getUsername();
		String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); /* 创建时间 */

		Map<String, String> map = new HashMap<>();

		map.put("id", id);
		map.put("workNo", workNo);
		map.put("name", name);
		map.put("deptId", deptId);
		map.put("contactTel", contactTel);
		map.put("idNo", idNo);
		map.put("type", type);
		map.put("gender", gender);
		map.put("isService", isService);
		map.put("status", status);

		map.put("recCreater", recCreater);
		map.put("recCreateTime", recCreateTime);

		/**
		 * 3.验证员工工号是否重复
		 */
		HashMap<String, String> map1 = new HashMap<>();
		map1.put("projectSchema", projectSchema);
		map1.put("workNo", map.get("workNo"));
		int count1 = super.count("ACPE01.countWorkNo", map1);
		if (count1 != 0) {
			inInfo.setMsg("人员工号重复  ");
			inInfo.setStatus(-1);
		} else {
			map.put("projectSchema", projectSchema);
			dao.insert("ACPE0101.insert", map);
		}

		/**
		 * 5.返回插入结果
		 */
		return inInfo;
	}

}
