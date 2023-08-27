package com.baosight.wilp.ac.pe.service;

import com.baosight.wilp.common.util.ArchivesLog;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 修改人员子界面.
 * <p>
 * 页面初始化方法, 修改人员.
 * </p>
 *
 * @Title ServiceACPE0102.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceACPE0102 extends ServiceBase {
	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 页面初始化方法
	 * 作者：hcd
	 * 入参：EiInfo
	 * 出参：EiInfo
	 * 处理：调用queryPerlInfo()方法
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		return queryPerlInfo(inInfo);
	}

	/**
	 * 通过ID查询人员详情
	 * 作者：hcd
	 * 入参：EiInfo（人员ID id）
	 * 出参：EiInfo（相关的人员信息）
	 * 处理：
	 * 1.从入参获取id存入map
	 * 2.调用query()方法查询满足条件的人员信息
	 * 3.将结果封装到EiInfo中
	 * 4.返回EiInfo
	 */
	public EiInfo queryPerlInfo(EiInfo inInfo) {
		/**
		 *  1.从入参获取id存入map
		 */
		Map<String, String> map = new HashMap<>();
		map.put("id", inInfo.getAttr().get("id").toString());
		map.put("projectSchema", projectSchema);
		EiInfo outInfo = new EiInfo();

		/**
		 *  2.调用query()方法查询满足条件的人员信息
		 */
		List<Map<String, String>> list = dao.query("ACPE0102.queryPerlInfo", map);
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}

		/**
		 * 3.将结果封装到EiInfo中
		 */
		outInfo.setAttr(list.get(0));

		/**
		 * 4.返回EiInfo
		 */
		return outInfo;
	}

	/**
	 * 修改人员
	 * 作者：hcd
	 * 入参：EiInfo（工号workNo，姓名name，科室ID deptId，电话contactTel，身份证号idNo，类型type，
	 * 性别gender，服务类型isService）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参中获取工号workNo，姓名name，科室ID
	 * deptId，电话contactTel，身份证号idNo，类型type， 性别gender，服务类型isService信息存入map
	 * 2.从UserSession获取当前登陆人存入map
	 * 3.获取当前时间存入map
	 * 4.将map数据传入update()方法执行更新操作
	 * 5.返回操作结果
	 */
	@Override
	@ArchivesLog(model = "AC", sign = "修改人员")
	public EiInfo update(EiInfo inInfo) {

		/**
		 * 1.从入参中获取工号workNo，姓名name，科室IDdeptId，电话contactTel，身份证号idNo，类型type， 性别gender，服务类型isService信息存入map
		 */
		String id = inInfo.get("id") == null ? "" : inInfo.getString("id"); /* 主键 */
//		String workNo = inInfo.get("workNo") == null ? "" : inInfo.getString("workNo");             /* 工号*/
		String name = inInfo.get("name") == null ? "" : inInfo.getString("name"); /* 姓名 */
		String deptId = inInfo.get("deptId") == null ? "" : inInfo.getString("deptId"); /* 所属科室ID */
		String contactTel = inInfo.get("contactTel") == null ? "" : inInfo.getString("contactTel"); /* 联系电话 */
		String idNo = inInfo.get("idNo") == null ? "" : inInfo.getString("idNo"); /* 身份证号 */
		String type = inInfo.get("type") == null ? "" : inInfo.getString("type"); /* 员工类型 */
		String gender = inInfo.get("gender") == null ? "" : inInfo.getString("gender"); /* 性别 */
		String isService = inInfo.get("isService") == null ? "" : inInfo.getString("isService"); /* 服务人员 */

		/**
		 *  2.从UserSession获取当前登陆人存入map
		 */
		String recRevisor = UserSession.getUser().getUsername(); /* 修改人 */


		/**
		 * 3.获取当前时间存入map
		 */
		String recReviseTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); /* 修改时间 */

		/**
		 * 4.将map数据传入update()方法执行更新操作
		 */
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
//        map.put("workNo",workNo);
		map.put("name", name);
		map.put("deptId", deptId);
		map.put("contactTel", contactTel);
		map.put("idNo", idNo);
		map.put("type", type);
		map.put("gender", gender);
		map.put("isService", isService);

		map.put("recRevisor", recRevisor);
		map.put("recReviseTime", recReviseTime);
		map.put("projectSchema", projectSchema);

//		HashMap<String, String> map1 = new HashMap<>();
//		map1.put("projectSchema", projectSchema);
//		map1.put("workNo", map.get("workNo"));
//		int count1 = super.count("ACPE01.countWorkNo", map1);
//		if (count1 != 0 ) {
//			inInfo.setMsg("人员工号重复  ");
//		}else{
//			map.put("projectSchema",projectSchema);
//			dao.update("ACPE0102.update", map);
//		}
		map.put("projectSchema", projectSchema);
		dao.update("ACPE0102.update", map);

		/**
		 *  5.返回操作结果
		 */
		return inInfo;
	}
}
