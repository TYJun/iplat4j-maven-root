package com.baosight.wilp.au.ro.service;

import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.xservices.xs.util.UserSession;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ServiceAURO0102 extends ServiceBase {

	final String projectSchema = PrUtils.getConfigure();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 界面初始化
	 * 作者：jzm
	 * 入参：EiInfo
	 * 出参：EiInfo
	 * 处理：
	 * 返回入参EiInfo
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		return inInfo;
	}

	/**
	 * 修改角色
	 * 作者：jzm
	 * 入参：EiInfo（角色id #id#，角色名称 #roleName#,备注 #remark#）
	 * 出参：EiInfo（操作结果）
	 * 操作：
	 * 1.从入参中读取角色id #id#，角色名称 #roleName#,备注 #remark#存入map中
	 * 2.读取当前时间存入recReviseTime，读取当前登录人存入recRevisor
	 * 3.调用update()方法更新数据库相关记录
	 * 4.返回操作结果
	 */
	@Override
	public EiInfo update(EiInfo inInfo) {
		/**
		 * 1.从入参中读取角色id #id#，角色名称 #roleName#,备注 #remark#存入map中
		 */
		Map map = inInfo.getAttr();

		/**
		 *  2.读取当前时间存入recReviseTime，读取当前登录人存入recRevisor
		 */
		String recReviseTime = sdf.format(new Date()); // 修改时间
		String recRevisor = UserSession.getUser().getUsername();// 修改人

		/**
		 * 3.调用update()方法更新数据库相关记录
		 */
		map.put("recReviseTime", recReviseTime);
		map.put("recRevisor", recRevisor);
		map.put("projectSchema", projectSchema);
		dao.update("AURO01.update", map);

		/**
		 * 4.返回操作结果
		 */
		inInfo.setMsg("角色修改成功!");
		return inInfo;
	}

}
