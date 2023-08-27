
package com.baosight.wilp.mc.vm.service;

import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 变量，人员管理.
 * <p>
 * </p>
 *
 * @Title ServiceMCVM0103.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceMCVM0103 extends ServiceBase {

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
	 * 向指定变量添加人员
	 * 作者：jzm
	 * 入参：EiInfo（当前变量id curVarId，人员id list）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参中获取当前变量id curVarId，人员id list
	 * 2.循环构建记录map并将其存入数据库中
	 * 3.返回操作结果
	 */
	@Override
	public EiInfo insert(EiInfo eiInfo) {
		/**
		 * 1.从入参中获取当前变量id curVarId，人员id list
		 */
		String projectSchema = PrUtils.getConfigure();
		String curVarId = eiInfo.getString("curVarId");
		List<String> perList = (List<String>) eiInfo.get("list");

		/**
		 *  2.循环构建记录map并将其存入数据库中
		 */
		for (String perId : perList) {
			Map<String, String> map = new HashMap<>();
			String id = UUID.randomUUID().toString();
			map.put("id", id);
			map.put("curVarId", curVarId);
			map.put("perId", perId);
			map.put("projectSchema", projectSchema);
			dao.insert("MCVM0103.insert", map);
		}

		/**
		 * 3.返回操作结果
		 */
		eiInfo.setMsg("添加成功");
		return eiInfo;
	}

}
