package com.baosight.wilp.au.ar.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色修改子页面.
 * <p>
 * 界面初始化, 修改角色.
 * </p>
 *
 * @Title ServiceAURO0102.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceAUAR0102 extends ServiceBase {

	final String projectSchema = PrUtils.getConfigure();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 界面初始化 入参：EiInfo 出参：EiInfo 处理：返回入参EiInfo
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		return queryResourceInfo(inInfo);
	}
	
	/**
	 * 通过ID查询科室详情 入参：EiInfo（科室id）
	 * 出参：EiInfo（科室详情，包括deptNum，deptName，parentId，parentDeptName，finaCode，erpCode，deptDescribe）
	 * 处理： 1.从入参EiInfo中获取科室id 2.查询出该科室id的详细信息 3.将详细信息放入EiInfo中并返回
	 */
	@SuppressWarnings("unchecked")
	public EiInfo queryResourceInfo(EiInfo inInfo) {
		Map<String, String> map = new HashMap<>();
		map.put("projectSchema", projectSchema);
		map.put("id", inInfo.getAttr().get("id").toString());
		EiInfo outInfo = new EiInfo();
		List<Map<String, String>> list = dao.query("AUAR0102.queryResourceInfo", map);
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}
		outInfo.setAttr(list.get(0));
		return outInfo;
	}

	/**
	 * 修改角色 入参：EiInfo（角色id #id#，角色名称 #roleName#,备注 #remark#） 出参：EiInfo（操作结果） 操作：
	 * 1.从入参中读取角色id #id#，角色名称 #roleName#,备注 #remark#存入map中
	 * 2.读取当前时间存入recReviseTime，读取当前登录人存入recRevisor 3.调用update()方法更新数据库相关记录 4.返回操作结果
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public EiInfo update(EiInfo inInfo) {

		Map map = inInfo.getAttr();

		String recReviseTime = sdf.format(new Date()); // 修改时间
		String recRevisor = UserSession.getUser().getUsername();// 修改人

		map.put("recReviseTime", recReviseTime);
		map.put("recRevisor", recRevisor);
		map.put("projectSchema", projectSchema);
		dao.update("AUAR0102.update", map);
		inInfo.setMsg("资源修改成功!");
		return inInfo;
	}

}
