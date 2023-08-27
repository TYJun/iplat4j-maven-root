package com.baosight.wilp.ac.de.service;

import com.baosight.wilp.common.util.ArchivesLog;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 修改科室子界面.
 * <p>
 * 弹窗初始化方法, 通过ID查询科室详情, 修改科室信息.
 * </p>
 *
 * @Title ServiceACDE0101.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceACDE0102 extends ServiceBase {

	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 弹窗初始化方法
	 * 作者：hcd
	 * 入参：EiInfo
	 * 出参：EiInfo
	 * 处理：
	 * 1.调用queryDeptInfo()方法
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		return queryDeptInfo(inInfo);
	}

	/**
	 * 通过ID查询科室详情
	 * 作者：hcd
	 * 入参：EiInfo（科室id）
	 * 出参：EiInfo（科室详情，包括deptNum，deptName，parentId，parentDeptName，finaCode，erpCode，deptDescribe）
	 * 处理：
	 * 1.从入参EiInfo中获取科室id
	 * 2.查询出该科室id的详细信息
	 * 3.将详细信息放入EiInfo中并返回
	 */
	public EiInfo queryDeptInfo(EiInfo inInfo) {

		/**
		 * 1.从入参EiInfo中获取科室id
		 */
		Map<String, String> map = new HashMap<>();
		map.put("projectSchema", projectSchema);
		map.put("id", inInfo.getAttr().get("id").toString());

		/**
		 * 2.查询出该科室id的详细信息
		 */
		EiInfo outInfo = new EiInfo();
		List<Map<String, String>> list = dao.query("ACDE0102.queryDeptInfo", map);
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}

		/**
		 * 3.将详细信息放入EiInfo中并返回
		 */
		outInfo.setAttr(list.get(0));
		return outInfo;
	}

	/**
	 * 修改科室信息
	 * 作者：hcd
	 * 入参 EiInfo(id,deptName,parentId,finaCode,erpCode,deptDescribe,type)
	 * 出参 EiInfo(操作结果)
	 * 处理：
	 * 1.从EiInfo中读取参数
	 * 2.将参数写入数据库中
	 * 3.根据科室ID更新院区编码
	 * 4.返回操作结果
	 * 
	 */
	@Override
	@ArchivesLog(model = "AC", sign = "修改科室")
	public EiInfo update(EiInfo inInfo) {
		/**
		 *  1.从EiInfo中读取参数
		 */
		String id = inInfo.get("id") == null ? "" : inInfo.getString("id"); /* 主键 */
		String deptName = inInfo.get("deptName") == null ? "" : inInfo.getString("deptName"); /* 科室名称 */
		String parentId = inInfo.get("parentId") == null ? "" : inInfo.getString("parentId"); /* 父ID */
		String finaCode = inInfo.get("finaCode") == null ? "" : inInfo.getString("finaCode"); /* 财务编码 */
		String erpCode = inInfo.get("erpCode") == null ? "" : inInfo.getString("erpCode"); /* ERP编码 */
		String deptDescribe = inInfo.get("deptDescribe") == null ? "" : inInfo.getString("deptDescribe"); /* 科室描述 */
		String type = inInfo.get("type") == null ? "" : inInfo.getString("type"); /* 科室类型 */
//		String status = "0";
		String recRevisor = UserSession.getUser().getUsername();
		String recReviseTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); /* 修改时间 */

//        EiInfo info = new EiInfo();
//		info.set("id", parentId);
//		info.set(EiConstant.serviceName, "ACFW01");
//		info.set(EiConstant.methodName, "getChildren");
//		info = XLocalManager.call(info);
//		
//		String parentSet = info.get("deptStr") == null ? "" : info.getString("deptStr");

		Map<String, String> map = new HashMap<>();
		map.put("projectSchema", projectSchema);
		map.put("id", id);
		map.put("deptName", deptName);
		map.put("parentId", parentId);
//        map.put("parentSet",parentSet);
		map.put("finaCode", finaCode);
		map.put("erpCode", erpCode);
		map.put("deptDescribe", deptDescribe);
		map.put("type", type);
//        map.put("status",status);

		map.put("recRevisor", recRevisor);
		map.put("recReviseTime", recReviseTime);

		/**
		 *  2.将参数写入数据库中
		 */
		dao.update("ACDE0102.update", map);

		/**
		 *  3.根据科室ID更新院区编码
		 */
		inInfo.set(EiConstant.serviceName, "ACDE0101");
		inInfo.set(EiConstant.methodName, "updateHosdNum");
		inInfo = XLocalManager.call(inInfo);
		/**
		 * 4.返回操作结果
		 */
		return inInfo;
	}

}
