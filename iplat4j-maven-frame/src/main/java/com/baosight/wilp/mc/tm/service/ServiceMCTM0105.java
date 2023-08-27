package com.baosight.wilp.mc.tm.service;

import com.alibaba.fastjson.JSONArray;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.wilp.mc.fw.util.PushMsgUtil;
import com.baosight.wilp.mc.fw.util.SendingDingMsgUtil;
import com.baosight.wilp.mc.fw.util.SendingMsgUtil;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 试推App子页面.
 * <p>
 * </p>
 *
 * @Title ServiceMCTM0104.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceMCTM0105 extends ServiceBase {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 界面初始化
	 * 作者：hcd
	 * 入参：EiInfo 出参：EiInfo 处理：返回入参EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 查询
	 * 作者：hcd
	 * 入参：EiInfo（模板id）
	 * 出参：EiInfo（对应模板的参数列表信息）
	 * 处理：
	 * 1.从入参中获取模板id
	 * 2.从数据库中查出对应的模板信息
	 * 3.判断模板信息是否为空，如果为空则返回错误信息
	 * 4.如果非空则获取参数列表
	 * 5.将参数列表信息封装在EiInfo中的result域中并返回
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo query(EiInfo inInfo) {

		String message = "";

		String id = inInfo.get("id") == null ? "" : inInfo.getString("id");

		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("projectSchema", projectSchema);

		List<Map<String, Object>> templateList = dao.query("MCTM0103.queryTemplate", map);

		if (!CollectionUtils.isEmpty(templateList)) {
			message = templateList.get(0).get("message").toString();
		} else {
			inInfo.setStatus(-1);
			return inInfo;
		}

		inInfo.set("message", message);

		// 获取参数列表
		List<Map<String, Object>> list = SendingMsgUtil.obtainParam(message);

		int count = list.size();
		// 返回
		if (!CollectionUtils.isEmpty(list)) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return inInfo;
		}

	}

	
	
	/**
	 * 推送钉钉
	 * 作者：hcd
	 * 入参：EiInfo（电话号码 phone，短信 message，参数集合paramList）
	 * 出参：EiInfo（操作结果）
	 * 操作：
	 * 1.获取token值
	 * 2.查询APP信息
	 * 3.编辑消息内容
	 * 4.查询CID
	 * 5.推送消息
	 */

	@SuppressWarnings("unchecked")
	public EiInfo sendDing(EiInfo inInfo) {

		// 1.获取token值
		inInfo.set(EiConstant.serviceName, "MCFW01");
		inInfo.set(EiConstant.methodName, "getToken");
		EiInfo inInfo1 = XLocalManager.call(inInfo);
		if (inInfo1.getStatus() < 0) {
			inInfo1.setStatus(-1);
			inInfo1.setMsg("token没有获取到!");
			return inInfo1;
		}
		String token = inInfo1.get("token") == null ? "" : inInfo1.getString("token");

		// 2.查询APP信息
		String appCode = inInfo.get("appCode") == null ? "" : inInfo.getString("appCode");
		String appId = null;
		Map<String, String> map = new HashMap<>();
		map.put("appCode", appCode);
		map.put("projectSchema", projectSchema);
		List<Map<String, Object>> list = dao.query("MCFW01.queryApp", map);
		if (!CollectionUtils.isEmpty(list)) {
			appId = list.get(0).get("appId").toString();
		} else {
			inInfo.setStatus(-1);
			inInfo.setMsg("appCode编码不正确!");
			return inInfo;
		}

		// 3.编辑消息内容
		String message = inInfo.get("message") == null ? "" : inInfo.getString("message");
		List<String> paramList = (List<String>) inInfo.get("paramList");
		message = SendingMsgUtil.editMessage(message, paramList);
		if ("-1".equals(message)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数集合与模板不匹配!");
			return inInfo;
		}

		// 4.查询uesrID
		String phone = inInfo.get("phone") == null ? "" : inInfo.getString("phone");
		String UserId = SendingDingMsgUtil.getDingUser(phone,token);
		
		
		System.out.println(" ############################################### message=" + message);
		System.out.println(" ############################################### UserId=" + UserId);
		System.out.println(" ############################################### appId=" + appId);
		System.out.println(" ############################################### token=" + token);

		// 5.推送消息
		String code = SendingDingMsgUtil.sendMsg(token, appId, UserId,message);

		
		if(!"".equals(code)) {
			inInfo.set("isSuccess", "成功!");
		}else {
			inInfo.set("isSuccess", "失败!");
		}

		return inInfo;
	}


}
