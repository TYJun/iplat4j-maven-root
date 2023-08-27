package com.baosight.wilp.mc.fw.service;

import com.alibaba.fastjson.JSONArray;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.wilp.mc.fw.util.PushMasUtil;
import com.baosight.wilp.mc.fw.util.PushMsgUtil;
import com.baosight.wilp.mc.fw.util.PushQytMsgUtil;
import com.baosight.wilp.mc.fw.util.SendingDingMsgUtil;
import com.baosight.wilp.mc.fw.util.SendingMsgUtil;
import com.baosight.wilp.mc.fw.util.SendingWXMsgUtil;
import com.baosight.xservices.xs.util.UserSession;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 对外服务接口.
 * <p>
 * </p>
 *
 * @Title ServiceMCFW01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */

@SuppressWarnings("unchecked")
public class ServiceMCFW01 extends ServiceBase {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 直接发送短信（无需模板和参数）(重新发送调用此方法)
	 * 作者：hcd
	 * 入参：EiInfo
	 * 出参：EiInfo（操作结果）
	 * 操作：
	 * 1.获取入参
	 * 2.发送短信并入库
	 * 3.通过员工工号查询联系电话
	 * 4.将发送日志存入短信日志表
	 * 5.返回操作结果
	 */
	public EiInfo sendDirect(EiInfo inInfo) {

		/**
		 * 1.获取入参
		 */
		if (inInfo.get("templateId") == null || inInfo.get("templateCode") == null || inInfo.get("message") == null
				|| inInfo.get("module") == null || inInfo.get("workNoList") == null) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数不能为空!");
			return inInfo;
		}

		String templateCode = inInfo.get("templateCode").toString();
		String templateId = inInfo.get("templateId").toString();
		String module = inInfo.get("module").toString();
		String message = inInfo.get("message").toString();
		// String deliveryChannel = inInfo.get("deliveryChannel").toString();

		// 员工工号集合
		List<String> workNoList = (List<String>) inInfo.get("workNoList");

		/**
		 * 2.发送短信并入库
		 */
		for (int i = 0; i < workNoList.size(); i++) {

			/**
			 *  3.通过员工工号查询联系电话
			 */
			EiInfo info = new EiInfo();
			info.set("workNo1", workNoList.get(i));
			info.set(EiConstant.serviceName, "ACFW01");
			info.set(EiConstant.methodName, "queryStaffInfo");
			inInfo = XLocalManager.call(info);
			if (inInfo.getStatus() < 0) {
				inInfo.setStatus(-1);
				inInfo.setMsg("员工工号不正确!");
				return inInfo;
			}
			Map<String, Object> staffInfo = (Map<String, Object>) inInfo.get("result");
			String contactTel = staffInfo.get("contactTel").toString();
			String receiver = staffInfo.get("workNo").toString(); // receiver 存放的是工号

			String isSuccess = SendingMsgUtil.sendingMsg(contactTel, message, module);
			inInfo.set("isSuccess", isSuccess);

			/**
			 * 4.将发送日志存入短信日志表
			 */
			EiInfo info1 = new EiInfo();
			info1.set("templateCode", templateCode);
			info1.set("templateId", templateId);
			info1.set("message", message);
			info1.set("module", module);
			// info1.set("deliveryChannel", deliveryChannel);
			info1.set("isSuccess", isSuccess);
			info1.set("receiver", receiver);
			info1.set("sender", "admin");
			inInfo = insert(info1);
			if (inInfo.getStatus() < 0) {
				inInfo.setStatus(-1);
				inInfo.setMsg("消息入库失败!");
				return inInfo;
			}
		}

		/**
		 * 5.返回操作结果
		 */
		return inInfo;
	}
	
	/******************************对外服务:短信，个推，钉钉，企业微信消息推送******************************************/

	/**
	 * 发送短信 (使用模板)
	 * 作者：hcd
	 * 入参：EiInfo（模板编码templateCode 参数集合paramList 工号集合 workNoList）
	 * 出参：EiInfo（操作结果） 处理：
	 * 1. 模板编码templateCode 参数集合paramList 工号集合 workNoList是否为null
	 * 2.如果为null则返回错误消息
	 * 3.通过模板编码查询模板信息
	 * 4.编辑消息内容，将入参传入的参数集合拼接到模板中
	 * 5.通过入参传入的员工工号查出该员工对应的手机号
	 * 6.是否发送抄送人，如果有则将操作人加入
	 * 7.调用sendingMsgUtil.sendingMsg()方法发送短信
	 * 8.将发送记录存入数据库
	 * 9.返回操作结果
	 */
	public EiInfo send(EiInfo inInfo) {
		/**
		 * 1.模板编码templateCode 参数集合paramList 工号集合 workNoList是否为null
		 */
		if (inInfo.get("paramList") == null || inInfo.get("workNoList") == null) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数不能为空!");
			return inInfo;
		}

		/**
		 * 2.通过模板编码查询模板信息
		 */
		EiInfo outInfo = queryTemplate(inInfo);
		if (outInfo.getStatus() < 0) {
			inInfo.setStatus(-1);
			inInfo.setMsg("模板编码不正确!");
			return inInfo;
		}
		Map<String, Object> templateInfo = (Map<String, Object>) outInfo.get("result");
		String templateId = templateInfo.get("templateId").toString();
		String module = templateInfo.get("module").toString();
		String message = templateInfo.get("message").toString();
		// String deliveryChannel = templateInfo.get("deliveryChannel").toString();


		/**
		 *  4.编辑消息内容，将入参传入的参数集合拼接到模板中
		 */
		// 参数集合
		List<String> paramList = (List<String>) inInfo.get("paramList");
		// 编辑消息内容
		message = SendingMsgUtil.editMessage(message, paramList);
		if ("-1".equals(message)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数集合与模板不匹配!");
			return inInfo;
		}

		/**
		 * 5.通过入参传入的员工工号查出该员工对应的手机号
		 */
		// 员工工号集合
		List<String> workNoList = (List<String>) inInfo.get("workNoList");

		/**
		 * 6.是否发送抄送人，如果有则将操作人加入
		 */
		// 是否发送抄送人
		String isCc = templateInfo.get("isCc").toString();
		if ("1".equals(isCc) && (templateInfo.get("variableCode") != null
				&& !"".equals(templateInfo.get("variableCode").toString()))) {
			inInfo.set("variableCode", templateInfo.get("variableCode").toString());
			EiInfo isCcOutInfo = queryVariable(inInfo);
			if (isCcOutInfo.getStatus() < 0) {
				inInfo.setStatus(-1);
				inInfo.setMsg("变量编码不正确!");
				return inInfo;
			}
			List<Map<String, Object>> variableList = (List<Map<String, Object>>) isCcOutInfo.get("result");
			for (int a = 0; a < variableList.size(); a++) {
				workNoList.add(variableList.get(a).get("workNo").toString());
			}
		}

		// 发送短信并入库
		for (int i = 0; i < workNoList.size(); i++) {
			// 通过员工工号查询联系电话
			EiInfo info = new EiInfo();
			info.set("workNo", workNoList.get(i));
			info.set(EiConstant.serviceName, "ACFW01");
			info.set(EiConstant.methodName, "queryStaffInfo");
			inInfo = XLocalManager.call(info);
			if (inInfo.getStatus() < 0) {
				inInfo.setStatus(-1);
				inInfo.setMsg("员工工号不正确!");
				return inInfo;
			}
			Map<String, Object> staffInfo = (Map<String, Object>) inInfo.get("result");
			String contactTel = staffInfo.get("contactTel").toString();
			String receiver = staffInfo.get("workNo").toString();
			/**
			 * 7.sendingMsgUtil.sendingMsg()方法发送短信
			 */
//			String isSuccess = SendingMsgUtil.sendingMsg(contactTel, message, module);
//			String isSuccess = PushMasUtil.pushMsg(message,contactTel);
			String isSuccess = PushQytMsgUtil.pushMsg(message,contactTel);

			/**
			 * 8.将发送记录存入数据库
			 */
			// 将发送日志存入短信日志表
			EiInfo info1 = new EiInfo();
			info1.set("templateId", templateId);
			info1.set("message", message);
			info1.set("isSuccess", isSuccess);
			info1.set("receiver", receiver);
			info1.set("sender", "admin");
			inInfo = insert(info1);
			if (inInfo.getStatus() < 0) {
				inInfo.setStatus(-1);
				inInfo.setMsg("消息入库失败!");
				return inInfo;
			}
		}

		/**
		 * 9.返回操作结果
		 */
		return inInfo;
	}

	/**
	 * App 推送消息（使用模板推送）
	 * 作者：hcd
	 * 入参：EiInfo（paramList，userList，appCode，token）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.验证参数
	 * 2.通过模板编码查询模板信息
	 * 3.生成消息内容
	 * 4.读取app推送配置信息
	 * 5.通过workNo查询cid
	 * 6.推送APP消息
	 * 7.将发送消息存入消息日志表
	 */
	public EiInfo pushMsg1(EiInfo inInfo) {

		/**
		 *  1.验证参数
		 */
		if (inInfo.get("paramList") == null || inInfo.get("workNoList") == null || inInfo.get("appCode") == null) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数不能为空!");
			return inInfo;
		}

		/**
		 * 2.通过模板编码查询模板信息
		 */
		EiInfo outInfo = queryTemplate(inInfo);
		if (outInfo.getStatus() < 0) {
			inInfo.setStatus(-1);
			inInfo.setMsg("模板编码不正确!");
			return inInfo;
		}
		Map<String, Object> templateInfo = (Map<String, Object>) outInfo.get("result");
		String templateId = templateInfo.get("templateId").toString();
		String message = templateInfo.get("message").toString();

		/**
		 * 3.生成消息内容,获取token值
		 */
		List<String> paramList = (List<String>) inInfo.get("paramList");
		// 编辑消息内容
		message = SendingMsgUtil.editMessage(message, paramList);
		if ("-1".equals(message)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数集合与模板不匹配!");
			return inInfo;
		}

		// 获取token值
		EiInfo tokenInfo = obtainToken(inInfo);
		if (tokenInfo.getStatus() < 0) {
			inInfo.setStatus(-1);
			inInfo.setMsg("APP编码不正确!");
			return inInfo;
		}
		String token = tokenInfo.get("token").toString();
		/**
		 * 4.读取app推送配置信息
		 */
		EiInfo appInfo = queryApp(inInfo);
		if (appInfo.getStatus() < 0) {
			inInfo.setStatus(-1);
			inInfo.setMsg("APP编码不正确!");
			return inInfo;
		}
		String appId = appInfo.get("appId").toString();
		// String token = appInfo.get("token").toString();

		/**
		 * 5.通过workNo查询cid
 		 */

		EiInfo cidInfo = queryCid(inInfo);
		if (appInfo.getStatus() < 0) {
			inInfo.setStatus(-1);
			inInfo.setMsg("没有查询到对应工号的CID!");
			return inInfo;
		}
		List<Map<String, Object>> cidList = (List<Map<String, Object>>) cidInfo.get("cidList");
		JSONArray cid = new JSONArray();
		for (int i = 0; i < cidList.size(); i++) {
			String cidStr = cidList.get(i).get("cid").toString();
			cid.add(cidStr);
		}

		// 消息跳转url
		String url = inInfo.get("url") == null ? "" : inInfo.getString("url");

		/**
		 * 6.推送APP消息
 		 */

		String code = PushMsgUtil.pushMsg(message, cid, appId, token, url);

		/**
		 * 7.将发送消息存入消息日志表
 		 */

		EiInfo info1 = new EiInfo();
		info1.set("templateId", templateId);
		info1.set("message", message);
		if ("0".equals(code)) {
			info1.set("isSuccess", "1");
		} else {
			info1.set("isSuccess", "0");
		}
		info1.set("receiver", (List<String>) inInfo.get("workNoList"));
		info1.set("sender", "admin");
		inInfo = addMessage(info1);
		if (inInfo.getStatus() < 0) {
			inInfo.setStatus(-1);
			inInfo.setMsg("消息入库失败!");
			return inInfo;
		}

		if ("0".equals(code)) {
			inInfo.setStatus(0);
			inInfo.setMsg("消息推送成功!");
		} else {
			inInfo.setStatus(-1);
			inInfo.setMsg("消息推送失败!");
		}

		return inInfo;
	}
	
	/**
	 * 推送钉钉
	 * 作者：hcd
	 * 入参：EiInfo（人员工号集合 workNoList，参数集合paramList，app信息编码 appCode ，消息模板编码 templateCode）
	 * 出参：EiInfo（操作结果）
	 * 操作：
	 * 1.验证参数
	 * 2.获取token值
	 * 3.查询APP信息
	 * 4.通过模板编码查询模板信息
	 * 5.编辑消息内容
	 * 6.推送消息
	 * 7.将发送消息存入消息日志表
	 */

	@SuppressWarnings("rawtypes")
	public EiInfo pushDingMsg(EiInfo inInfo) {
		
		/**
		 *  1.验证参数
		 */
		if (inInfo.get("paramList") == null || inInfo.get("workNoList") == null 
				|| inInfo.get("appCode") == null|| inInfo.get("templateCode") == null) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数不能为空!");
			return inInfo;
		}
		
		/**
		 *  2.获取token值
		 */
		EiInfo inInfo1 = getToken(inInfo);
		if (inInfo1.getStatus() < 0) {
			inInfo1.setStatus(-1);
			inInfo1.setMsg("token没有获取到!");
			return inInfo1;
		}
		String token = inInfo1.get("token") == null ? "" : inInfo1.getString("token");

		/**
		 *  3.查询APP信息
		 */
		EiInfo outAppIdInfo = queryApp(inInfo);
		String appId = outAppIdInfo.get("appId").toString();
		
		/**
		 * 4.通过模板编码查询模板信息
		 */
		EiInfo outTemplateInfo = queryTemplate(inInfo);
		if (outTemplateInfo.getStatus() < 0) {
			inInfo.setStatus(-1);
			inInfo.setMsg("模板编码不正确!");
			return inInfo;
		}
		Map<String, Object> templateInfo = (Map<String, Object>) outTemplateInfo.get("result");
		String templateId = templateInfo.get("templateId").toString();
		String message = templateInfo.get("message").toString();

		/**
		 *  5.编辑消息内容
		 */
		List<String> paramList = (List<String>) inInfo.get("paramList");
		message = SendingMsgUtil.editMessage(message, paramList);
		if ("-1".equals(message)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数集合与模板不匹配!");
			return inInfo;
		}

		/**
		 *  6.推送消息
		 */
		EiInfo outUserInfo = queryUserId(inInfo);
		List<Map> userIdList = (List<Map>) outUserInfo.get("userIdList");
		String code = "";
		for(int i = 0 ; i < userIdList.size() ; i++) {
			code = SendingDingMsgUtil.sendMsg(token, appId, userIdList.get(i).get("id").toString(),message);
		}
		
		/**
		 * 7.将发送消息存入消息日志表
 		 */
		EiInfo info1 = new EiInfo();
		info1.set("templateId", templateId);
		info1.set("message", message);
		if ("0".equals(code) || "".equals(code)) {
			info1.set("isSuccess", "1");
		} else {
			info1.set("isSuccess", "0");
		}
		info1.set("receiver", (List<String>) inInfo.get("workNoList"));
		info1.set("sender", "admin");
		inInfo = addMessage(info1);
		if (inInfo.getStatus() < 0) {
			inInfo.setStatus(-1);
			inInfo.setMsg("消息入库失败!");
			return inInfo;
		}
		
		/**
		 * 8.返回
 		 */
		if(!"".equals(code)) {
			inInfo.set("isSuccess", "推送成功!");
		}else {
			inInfo.set("isSuccess", "推送失败!");
		}

		return inInfo;
	}
	
	/**
	 * 推送钉钉
	 * 作者：hcd
	 * 入参：EiInfo（人员工号集合 workNoList，参数集合paramList，app信息编码 appCode ，消息模板编码 templateCode）
	 * 出参：EiInfo（操作结果）
	 * 操作：
	 * 1.验证参数
	 * 2.获取token值
	 * 3.查询APP信息
	 * 4.通过模板编码查询模板信息
	 * 5.编辑消息内容
	 * 6.推送消息
	 * 7.将发送消息存入消息日志表
	 */

	@SuppressWarnings("rawtypes")
	public EiInfo pushWxMsg(EiInfo inInfo) {
		
		/**
		 *  1.验证参数
		 */
		if (inInfo.get("paramList") == null || inInfo.get("workNoList") == null 
				|| inInfo.get("appCode") == null|| inInfo.get("templateCode") == null) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数不能为空!");
			return inInfo;
		}
		
		/**
		 *  2.获取token值
		 */
		EiInfo inInfo1 = getWXToken(inInfo);
		if (inInfo1.getStatus() < 0) {
			inInfo1.setStatus(-1);
			inInfo1.setMsg("token没有获取到!");
			return inInfo1;
		}
		String token = inInfo1.get("token") == null ? "" : inInfo1.getString("token");

		/**
		 *  3.查询APP信息
		 */
		EiInfo outAppIdInfo = queryApp(inInfo);
		String appId = outAppIdInfo.get("appId").toString();
		
		/**
		 * 4.通过模板编码查询模板信息
		 */
		EiInfo outTemplateInfo = queryTemplate(inInfo);
		if (outTemplateInfo.getStatus() < 0) {
			inInfo.setStatus(-1);
			inInfo.setMsg("模板编码不正确!");
			return inInfo;
		}
		Map<String, Object> templateInfo = (Map<String, Object>) outTemplateInfo.get("result");
		String templateId = templateInfo.get("templateId").toString();
		String message = templateInfo.get("message").toString();

		/**
		 *  5.编辑消息内容
		 */
		List<String> paramList = (List<String>) inInfo.get("paramList");
		message = SendingMsgUtil.editMessage(message, paramList);
		if ("-1".equals(message)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数集合与模板不匹配!");
			return inInfo;
		}

		/**
		 *  6.推送消息
		 */
		EiInfo outUserInfo = queryUserId(inInfo);
		List<Map> userIdList = (List<Map>) outUserInfo.get("userIdList");
		String code = "";
		String type = "text";
		for(int i = 0 ; i < userIdList.size() ; i++) {
			code = SendingWXMsgUtil.sendMsg(appId,token,type,userIdList.get(i).get("id").toString(),message);
		}
		
		/**
		 * 7.将发送消息存入消息日志表
 		 */
		EiInfo info1 = new EiInfo();
		info1.set("templateId", templateId);
		info1.set("message", message);
		if ("0".equals(code) || "".equals(code)) {
			info1.set("isSuccess", "1");
		} else {
			info1.set("isSuccess", "0");
		}
		info1.set("receiver", (List<String>) inInfo.get("workNoList"));
		info1.set("sender", "admin");
		inInfo = addMessage(info1);
		if (inInfo.getStatus() < 0) {
			inInfo.setStatus(-1);
			inInfo.setMsg("消息入库失败!");
			return inInfo;
		}
		
		/**
		 * 8.返回
 		 */
		if(!"".equals(code)) {
			inInfo.set("isSuccess", "推送成功!");
		}else {
			inInfo.set("isSuccess", "推送失败!");
		}

		return inInfo;
	}
	
	/*********************************APP登录，查询CID****************************************************/
	
	/**
	 * APP登录，查询CID
	 * 作者：hcd
	 * 入参：EiInfo（人员工号集合 workNoList）
	 * 出参：EiInfo（操作结果）
	 * 操作：
	 */
	public EiInfo queryCid(EiInfo inInfo) {

		// 公号集合
		List<String> workNoList = (List<String>) inInfo.get("workNoList");

		Map<String, Object> map1 = new HashMap<>();
		map1.put("list", workNoList);
		map1.put("projectSchema", projectSchema);

		// 
		List<Map<String, Object>> cidList = dao.query("MCFW01.queryCid", map1);

		if (!CollectionUtils.isEmpty(cidList)) {
			inInfo.setStatus(0);
			inInfo.set("cidList", cidList);
		} else {
			inInfo.setStatus(-1);
			inInfo.setMsg("没有查询到对应工号的CID!");
		}

		return inInfo;
	}
	
	/******************个推，钉钉，企业微信 获取TOKEN值*******************************************************************/
	
	
	/**
	 * App 推送获取TOKEN 入参：EiInfo（appCode） 出参：EiInfo（TOKEN） 处理：
	 */
	public EiInfo obtainToken(EiInfo inInfo) {

		String appCode = inInfo.get("appCode") == null ? "" : inInfo.getString("appCode");
		String projectSchema = PrUtils.getConfigure();

		Map<String, String> map = new HashMap<>();
		map.put("appCode", appCode);
		map.put("projectSchema", projectSchema);

		List<Map<String, Object>> list = dao.query("MCFW01.queryToken", map);

		String token = null;
		if (!CollectionUtils.isEmpty(list)) {
			token = list.get(0).get("token").toString();
			inInfo.set("token", token);
			inInfo.setStatus(0);
		} else {
			List<Map<String, Object>> appList = dao.query("MCFW01.queryApp", map);
			String appkey = appList.get(0).get("appKey").toString();
			String mastersecret = appList.get(0).get("mastersecret").toString();
			String appId = appList.get(0).get("appId").toString();
			token = PushMsgUtil.obtainToken(appkey, appId, mastersecret);
			// 将token值跟新到数据库
			inInfo.set("token", token);
			updateToken(inInfo);
			inInfo.setStatus(0);
		}
		return inInfo;
	}
	
	/**
	 * 钉钉推送获取TOKEN 入参：EiInfo（appCode） 出参：EiInfo（TOKEN） 处理：
	 */
	public EiInfo getToken(EiInfo inInfo) {

		String appCode = inInfo.get("appCode") == null ? "" : inInfo.getString("appCode");
		String projectSchema = PrUtils.getConfigure();

		Map<String, String> map = new HashMap<>();
		map.put("appCode", appCode);
		map.put("projectSchema", projectSchema);

		List<Map<String, Object>> list = dao.query("MCFW01.queryToken", map);

		String token = null;
		// 从数据库获取token值，如果没有重新获取。token值有效期保留100分钟
		if (!CollectionUtils.isEmpty(list)) {
			if (list.get(0).get("token") != null && !"".equals(list.get(0).get("token").toString())) {
				token = list.get(0).get("token").toString();
				inInfo.set("token", token);
				inInfo.setStatus(0);
			} 
		} else {
			List<Map<String, Object>> appList = dao.query("MCFW01.queryApp", map);
			String appkey = appList.get(0).get("appKey").toString();
			String mastersecret = appList.get(0).get("mastersecret").toString();
			String appId = appList.get(0).get("appId").toString();
			//重新获取token值
			token = SendingDingMsgUtil.getToken(appkey, appId, mastersecret);
			// 将token值跟新到数据库
			inInfo.set("token", token);
			updateToken(inInfo);
			inInfo.setStatus(0);
		}
		return inInfo;
	}
	
	/**
	 * 企业微信获取TOKEN 入参：EiInfo（appCode） 出参：EiInfo（TOKEN） 处理：
	 */
	public EiInfo getWXToken(EiInfo inInfo) {

		String appCode = inInfo.get("appCode") == null ? "" : inInfo.getString("appCode");
		String projectSchema = PrUtils.getConfigure();

		Map<String, String> map = new HashMap<>();
		map.put("appCode", appCode);
		map.put("projectSchema", projectSchema);

		List<Map<String, Object>> list = dao.query("MCFW01.queryToken", map);

		String token = null;
		// 从数据库获取token值，如果没有重新获取。token值有效期保留100分钟
		if (!CollectionUtils.isEmpty(list)) {
			if (list.get(0).get("token") != null && !"".equals(list.get(0).get("token").toString())) {
				token = list.get(0).get("token").toString();
				inInfo.set("token", token);
				inInfo.setStatus(0);
			} 
		} else {
			List<Map<String, Object>> appList = dao.query("MCFW01.queryApp", map);
			String appkey = appList.get(0).get("appKey").toString();
			String mastersecret = appList.get(0).get("mastersecret").toString();
//			String appId = appList.get(0).get("appId").toString();
			//重新获取token值
			token = SendingWXMsgUtil.getAccessToken(appkey, mastersecret);
			// 将token值跟新到数据库
			inInfo.set("token", token);
			updateToken(inInfo);
			inInfo.setStatus(0);
		}
		return inInfo;
	}
	
	/**
	 * 更新TOKEN值 入参：EiInfo（appCode） 出参：EiInfo（TOKEN） 处理：
	 */
	public EiInfo updateToken(EiInfo inInfo) {
		
		String recReviseTime = sdf.format(new Date()); // 操作时间
		String recRevisor = UserSession.getUser().getUsername();// 操作人

		String appCode = inInfo.get("appCode") == null ? "" : inInfo.getString("appCode");
		String token = inInfo.get("token") == null ? "" : inInfo.getString("token");

		Map<String, String> map = new HashMap<>();
		map.put("appCode", appCode);
		map.put("projectSchema", projectSchema);
		map.put("token", token);
		map.put("recReviseTime", recReviseTime);
		map.put("recRevisor", recRevisor);
		dao.update("MCFW01.updateToken", map);

		return inInfo;
	}
	
	/*************************************************************************************/
	
	/**
	 * 查询模板信息
	 * 作者：hcd
	 * 入参：EiInfo（模板编码 templateCode）
	 * 出参：EiInfo（入参模板编码对应的模板信息）
	 * 处理：
	 * 1.从入参读取templateCode
	 * 2.从数据库中查询出对应的模板信息
	 * 3.将模板信息存入EiInfo的result域
	 * 4.返回操作结果
	 */
	public EiInfo queryTemplate(EiInfo inInfo) {

		/**
		 * 1.从入参读取templateCode
		 */
		String templateCode = inInfo.get("templateCode") == null ? "" : inInfo.getString("templateCode");
		String projectSchema = PrUtils.getConfigure();

		Map<String, String> map = new HashMap<>();
		map.put("templateCode", templateCode);
		map.put("projectSchema", projectSchema);

		/**
		 * 2.从数据库中查询出对应的模板信息
		 */
		// 通过模板编码查询模板信息
		List<Map<String, Object>> templateList = dao.query("MCFW01.queryTemplateList", map);

		/**
		 *  3.将模板信息存入EiInfo的result域
		 */
		if (!CollectionUtils.isEmpty(templateList)) {
			inInfo.set("result", templateList.get(0));
		} else {
			inInfo.setStatus(-1);
			inInfo.setMsg("模板编码不正确!");
			return inInfo;
		}

		/**
		 * 4.返回操作结果
		 */
		return inInfo;
	}
	
	/**
	 * 查询App信息
	 */
	public EiInfo queryApp(EiInfo inInfo) {
		
		String appCode = inInfo.get("appCode") == null ? "" : inInfo.getString("appCode");
		// String token = null;
		String appId = null;

		Map<String, String> map = new HashMap<>();
		map.put("appCode", appCode);
		map.put("projectSchema", projectSchema);
		List<Map<String, Object>> list = dao.query("MCFW01.queryApp", map);

		if (!CollectionUtils.isEmpty(list)) {

			// 从数据库获取appId
			appId = list.get(0).get("appId").toString();

			inInfo.setStatus(0);
			inInfo.set("appId", appId);

		} else {
			inInfo.setStatus(-1);
			inInfo.setMsg("appCode编码不正确!");
		}

		return inInfo;
	}
	
	/**
	 * 查询变量信息
	 * 作者：hcd
	 * 入参：EiInfo（变量编码 variableCode）
	 * 出参：EiInfo（入参变量编码对应的变量信息）
	 * 处理：
	 * 1.从入参读取
	 * variableCode
	 * 2.从数据库中查询出对应的变量信息
	 * 3.将模板信息存入EiInfo的result域
	 * 4.返回操作结果
	 */
	public EiInfo queryVariable(EiInfo inInfo) {
		/**
		 * 1.从入参读取
		 * 	 * variableCode
		 */
		String variableCode = inInfo.get("variableCode") == null ? "" : inInfo.getString("variableCode");
		String projectSchema = PrUtils.getConfigure();

		Map<String, String> map = new HashMap<>();
		map.put("variableCode", variableCode);
		map.put("projectSchema", projectSchema);

		/**
		 * 2.从数据库中查询出对应的变量信息
		 */
		// 通过模板编码查询模板信息
		List<Map<String, Object>> variableList = dao.query("MCFW01.queryVariableList", map);

		/**
		 *  3.将模板信息存入EiInfo的result域
		 */
		inInfo.set("result", variableList);

		/**
		 * 4.返回操作结果
		 */
		return inInfo;
	}
	
	/**
	 * 将短信发送日志存入日志表
	 * 作者：hcd
	 * 入参：EiInfo（模板ID templateId，消息体message，是否成功isSuccess 接收人receiver
	 * 发送人sender ）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参读取 模板ID
	 * templateId，消息体message，是否成功isSuccess 接收人receiver 发送人sender
	 * 2.将当前时间存入sendingTime
	 * 3.将传入参数和sendingTime存入数据库中
	 * 4.返回操作结果
	 */
	public EiInfo addMessage(EiInfo inInfo) {

		/**
		 * 1.从入参读取 模板ID
		 * 	 * templateId，消息体message，是否成功isSuccess 接收人receiver 发送人sender
		 */
		String id = UUID.randomUUID().toString();
		String templateId = inInfo.getString("templateId");
		String message = inInfo.getString("message");
		String isSuccess = inInfo.getString("isSuccess");
		List<Map<String, Object>> receiver = (List<Map<String, Object>>) inInfo.get("receiver");
		String sender = inInfo.getString("sender");

		/**
		 *  2.将当前时间存入sendingTime
		 */
		String sendingTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		String projectSchema = PrUtils.getConfigure();

		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("templateId", templateId);
		map.put("message", message);
		map.put("isSuccess", isSuccess);
		map.put("list", receiver);
		map.put("sender", sender);
		map.put("sendingTime", sendingTime);
		map.put("projectSchema", projectSchema);

		/**
		 * 3.将传入参数和sendingTime存入数据库中
		 */
		dao.insert("MCFW01.addMessage", map);

		/**
		 * 4.返回操作结果
		 */
		return inInfo;
	}
	
	public EiInfo insert(EiInfo inInfo) {

		String id = UUID.randomUUID().toString();
		String templateId = inInfo.getString("templateId");
		String message = inInfo.getString("message");
		String isSuccess = inInfo.getString("isSuccess");
//        List<Map<String, Object>> receiver = (List<Map<String, Object>>) inInfo.get("receiver");
		String receiver = inInfo.getString("receiver");
		String sender = inInfo.getString("sender");
		String sendingTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		String projectSchema = PrUtils.getConfigure();

		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("templateId", templateId);
		map.put("message", message);
		map.put("isSuccess", isSuccess);
		map.put("receiver", receiver);
		map.put("sender", sender);
		map.put("sendingTime", sendingTime);
		map.put("projectSchema", projectSchema);

		dao.insert("MCFW01.insert", map);

		return inInfo;
	}
	
	/**
	 * 查询userIdList
	 * 作者：hcd
	 * 入参：EiInfo（人员工号集合 workNoList）
	 * 出参：EiInfo（操作结果）
	 * 操作：
	 */
	public EiInfo queryUserId(EiInfo inInfo) {

		// 公号集合
		List<String> workNoList = (List<String>) inInfo.get("workNoList");

		Map<String, Object> map1 = new HashMap<>();
		map1.put("list", workNoList);
		map1.put("projectSchema", projectSchema);

		// 
		List<Map<String, Object>> userIdList = dao.query("MCFW01.queryUserId", map1);

		if (!CollectionUtils.isEmpty(userIdList)) {
			inInfo.setStatus(0);
			inInfo.set("userIdList", userIdList);
		} else {
			inInfo.setStatus(-1);
			inInfo.setMsg("没有查询到对应工号的UserId!");
		}

		return inInfo;
	}
	
	/*************************************************************************************/
	
	
	
}
