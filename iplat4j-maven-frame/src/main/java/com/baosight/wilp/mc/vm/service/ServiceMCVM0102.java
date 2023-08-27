
package com.baosight.wilp.mc.vm.service;

import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.xservices.xs.util.UserSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 变量信息 变量编辑.
 * <p>
 * </p>
 *
 * @Title ServiceMCVM0102.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceMCVM0102 extends ServiceBase {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 界面初始化
	 * 作者：jzm
	 * 入参：EiInfo 出参：EiInfo 处理：返回入参EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		return info;
	}

	/**
	 * 变量修改
	 * 作者：jzm
	 * 入参：EiInfo（变量名称 variableName，变量id id）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参中读取变量名称
	 * variableName，变量id id存入map
	 * 2.生成修改人和修改时间
	 * 3.调用update()方法更新修改
	 * 4.返回操作结果
	 */
	@Override
	public EiInfo update(EiInfo eiInfo) {
		/**
		 * 1.从入参中读取变量名称
		 * 	 * variableName，变量id id存入map
		 */
		String projectSchema = PrUtils.getConfigure();
		String variableName = eiInfo.getString("variableName");
		String id = eiInfo.getString("id");
		Map<String, String> map = new HashMap<>();

		map.put("projectSchema", projectSchema);

		/**
		 * 2.生成修改人和修改时间
		 */
		// 生成修改人
		String recRevisor = UserSession.getUser().getUsername();

		// 生成修改时间
		String recReviseTime = sdf.format(new Date());

		/**
		 *  3.调用update()方法更新修改
		 */
		map.put("id", id);
		map.put("variableName", variableName);
		map.put("recRevisor", recRevisor);
		map.put("recReviseTime", recReviseTime);

		dao.insert("MCVM0102.update", map);

		/**
		 * 4.返回操作结果
		 */
		eiInfo.setMsg("变量修改成功");
		return eiInfo;
	}

}
