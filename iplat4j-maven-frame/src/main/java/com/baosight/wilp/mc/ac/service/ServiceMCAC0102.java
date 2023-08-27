package com.baosight.wilp.mc.ac.service;

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
 * APP修改子页面.
 * <p>
 * 界面初始化，修改App配置，
 * </p>
 *
 * @Title ServiceMCAC0102.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceMCAC0102 extends ServiceBase {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 界面初始化 入参：EiInfo 出参：EiInfo 处理：返回入参EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 查询方法
	 * 作者：jzm
	 * 入参：EiInfo（主键id） 出参：EiInfo（满足条件的App配置信息） 处理： 1.从入参中读取主键id存入map中
	 * 2.调用数据库查询方法，查询满足条件的结果集 3.判断结果集是否为空，如果空则返回错误信息 4.将App配置信息存入EiInfo并返回
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo query(EiInfo inInfo) {
		String projectSchema = PrUtils.getConfigure();
		Map<String, String> map = new HashMap<>();
		map.put("id", inInfo.getAttr().get("id").toString());
		map.put("projectSchema", projectSchema);
		EiInfo outInfo = new EiInfo();
		List<Map<String, String>> list = dao.query("MCAC01.queryApp", map);
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}
		outInfo.setAttr(list.get(0));
		return outInfo;
	}

	/**
	 * 修改App配置
	 * 作者：jzm
	 * 入参：EiInfo（主键 id, app编码 appCode, app名称 appName,appKey app_key,appId
	 * app_id,masterSecret master_secret)
	 * 出参：EiInfo（操作结果）
	 * 操作：
	 * 1.从入参中读取主键 id, app编码
	 * appCode, app名称 appName,appKey app_key,appId app_id,masterSecret master_secret
	 * 2.读取当前时间存入recReviseTime，读取当前登录人存入recRevisor
	 * 3.调用update()方法更新数据库相关记录 4.返回操作结果
	 */
	@Override
	public EiInfo update(EiInfo inInfo) {

		String projectSchema = PrUtils.getConfigure();
		Map map = inInfo.getAttr();

		String recReviseTime = sdf.format(new Date()); // 修改时间
		String recRevisor = UserSession.getUser().getUsername();// 修改人

//		map.put("recReviseTime", recReviseTime);
		map.put("recRevisor", recRevisor);
		map.put("projectSchema", projectSchema);

		dao.update("MCAC01.update", map);
		inInfo.setMsg("App配置改成功!");
		return inInfo;
	}

}
