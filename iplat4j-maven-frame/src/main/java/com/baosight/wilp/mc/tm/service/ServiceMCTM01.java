
package com.baosight.wilp.mc.tm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.MaintainUtil;
import com.baosight.wilp.common.util.PrUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息中心 模板管理.
 * <p>
 * </p>
 *
 * @Title ServiceMCTM01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceMCTM01 extends ServiceBase {
	/**
	 * 界面初始化
	 * 作者：jzm
	 * 入参：EiInfo 出参：EiInfo 处理：返回入参EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 查询功能
	 * 作者：jzm
	 * 入参：EiInfo（模板编号 "templateCode", 模板名称 "templateName", 调用模块 "callModule",
	 * 发送渠道"deliveryChannel"）
	 * 出参：EiInfo（人员信息list）
	 * 处理：
	 * 1.从入参中读取 模板编号 "templateCode",
	 * 模板名称 "templateName", 调用模块 "callModule", 发送渠道"deliveryChannel" 存入map中
	 * 2.调用query()方法查询出相关的人员列表
	 * 3.将结果封装在EiInfo的result域
	 * 4.返回EiInfo
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo query(EiInfo inInfo) {
		/**
		 * 1.从入参中读取 模板编号 "templateCode",
		 * 	 * 模板名称 "templateName", 调用模块 "callModule", 发送渠道"deliveryChannel" 存入map中
		 */
		String projectSchema = PrUtils.getConfigure();
		String platSchema=PrUtils.getIplatConfigure();
		String[] param = { "templateCode", "templateName", "callModule", "deliveryChannel" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);
		map.put("projectSchema", projectSchema);
		map.put("platSchema", platSchema);

		/**
		 * 2.调用query()方法查询出相关的人员列表
		 */
		List<Map<String, Object>> list = dao.query("MCTM01.queryTemplate", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = dao.count("MCTM01.queryTemplate", map);


		/**
		 *  3.将结果封装在EiInfo的result域
		 */
		if (!CollectionUtils.isEmpty(list)) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return inInfo;
		}
	}

	/**
	 * 模板删除功能
	 * 作者：jzm
	 * 入参：EiInfo（待删除模板的id list）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参中读取待删除模板的id list
	 * 2.调用数据库删除方法删除相关数据
	 * 3，返回操作结果
	 * 
	 * @param inInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public EiInfo delete(EiInfo inInfo) {
		String projectSchema = PrUtils.getConfigure();
		List<String> list = (List<String>) inInfo.get("list");

		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("projectSchema", projectSchema);

		dao.delete("MCTM01.delete", map);
		inInfo.setStatus(0);
		inInfo.setMsg("删除成功");
		return inInfo;
	}
}
