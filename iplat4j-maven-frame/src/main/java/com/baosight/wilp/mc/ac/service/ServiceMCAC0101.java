package com.baosight.wilp.mc.ac.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.xservices.xs.util.UserSession;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * APP添加子页面.
 * <p>
 * 界面初始化，新增App
 * </p>
 *
 * @Title ServiceMCAC0101.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceMCAC0101 extends ServiceBase {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 界面初始化
	 * 作者：jzm
	 * 入参：EiInfo 出参：EiInfo 处理：返回入参EiInfo
	 */
	public EiInfo initLoad(EiInfo inInfo) {

		return inInfo;
	}

	/**
	 * 新增App
	 * 作者：jzm
	 * 入参：EiInfo（app编码 appCode,app名称 appName,appKey app_key,appId
	 * app_id,masterSecret master_secret） 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参中获取: app编码
	 * appCode,app名称 appName,appKey app_key,appId app_id,masterSecret master_secret
	 * 2.自动生成主键和APP编号
	 * 3.调用insert()方法向数据库中插入数据
	 * 4.返回操作结果
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo insert(EiInfo inInfo) {

		String projectSchema = PrUtils.getConfigure();

		/**
		 * 1.从入参中获取: app编码
		 * 	 * appCode,app名称 appName,appKey app_key,appId app_id,masterSecret master_secret
		 */
		Map<String, Object> map = inInfo.getAttr();


		/**
		 * 2.自动生成主键和APP编号
		 */
		String id = UUID.randomUUID().toString(); // 主键
		String appCode = null;

		map.put("projectSchema", projectSchema);

		// 先查询出当前最新的 rec_create_time 取出该记录的appCode
		List<Map<String, String>> list = dao.query("MCAC01.queryLastAppCode", map);

		if (CollectionUtils.isEmpty(list)) { // 如果没有记录则生成一号AP00001
			appCode = "AP00001";
		} else {
			appCode = genAppNum(list.get(0).get("appCode")); // 生成App编号
		}

		String recCreateTime = sdf.format(new Date()); // 创建时间
		String recCreater = UserSession.getUser().getUsername();// 创建人

		/**
		 *  3.调用insert()方法向数据库中插入数据
		 */
		map.put("id", id);
		map.put("appCode", appCode);
		map.put("recCreateTime", recCreateTime);
		map.put("recCreater", recCreater);
		map.put("recReviseTime", recCreateTime);
		map.put("recRevisor", recCreater);

		dao.insert("MCAC01.insertApp", map);

		/**
		 * 4.返回操作结果
		 */
		inInfo.setMsg("APP添加成功!");
		return inInfo;
	}

	/**
	 * 按照 AP00000 方式生成APP编号
	 * 作者：jzm
	 * @return String
	 */
	public static String genAppNum(String lastAppNum) {
		return "AP" + String.format("%05d", Integer.parseInt(lastAppNum.substring(2)) + 1);
	}
}
