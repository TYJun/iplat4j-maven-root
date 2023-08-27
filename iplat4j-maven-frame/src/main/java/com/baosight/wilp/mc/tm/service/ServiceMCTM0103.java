package com.baosight.wilp.mc.tm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.wilp.mc.fw.util.PushMasUtil;
import com.baosight.wilp.mc.fw.util.PushQytMsgUtil;
import com.baosight.wilp.mc.fw.util.SendingMsgUtil;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 试发短信子页面.
 * <p>
 * </p>
 *
 * @Title ServiceMCTM0103.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceMCTM0103 extends ServiceBase {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	final String projectSchema = PrUtils.getConfigure();

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
		/**
		 * 1.从入参中获取模板id
		 */
		String id = inInfo.get("id") == null ? "" : inInfo.getString("id");

		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("projectSchema", projectSchema);

		/**
		 * 2.从数据库中查出对应的模板信息
		 */
		List<Map<String, Object>> templateList = dao.query("MCTM0103.queryTemplate", map);

		/**
		 * 3.判断模板信息是否为空，如果为空则返回错误信息
		 */
		if (!CollectionUtils.isEmpty(templateList)) {
			message = templateList.get(0).get("message").toString();
		} else {
			inInfo.setStatus(-1);
			return inInfo;
		}

		/**
		 *  4.如果非空则获取参数列表
		 */
		inInfo.set("message", message);

		// 获取参数列表
		List<Map<String, Object>> list = SendingMsgUtil.obtainParam(message);

		int count = list.size();

		/**
		 * 5.将参数列表信息封装在EiInfo中的result域中并返回
		 */
		if (!CollectionUtils.isEmpty(list)) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return inInfo;
		}

	}

	/**
	 * 发送短信
	 * 作者：hcd
	 * 入参：EiInfo（电话号码 phone，短信 message，参数集合paramList）
	 * 出参：EiInfo（操作结果）
	 * 操作：
	 * 1.从入参中读取电话号码 phone，短信 message，参数集合paramList
	 * 2.将模板总的参数替换为 参数集合paramList 中的实际参数
	 * 3.调用PushMsgUtil.pushMsg()发送短信
	 * 4.返回操作结果
	 */

	@SuppressWarnings("unchecked")
	public EiInfo send(EiInfo inInfo) {
		/**
		 * 1.从入参中读取电话号码 phone，短信 message，参数集合paramList
		 */
		String contactTel = inInfo.get("phone") == null ? "" : inInfo.getString("phone");
		String message = inInfo.get("message") == null ? "" : inInfo.getString("message");

		/**
		 * 2.将模板总的参数替换为 参数集合paramList 中的实际参数
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
		 * 3.调用PushMsgUtil.pushMsg()发送短信
		 */
		// 发送短信
		String isSuccess = SendingMsgUtil.sendingMsg(contactTel, message, "测试");
//		System.out.println("************************准备发送短信*******************************************");
//		System.out.println("*************************"+"message:"+message);
//		System.out.println("*************************"+"contactTel:"+contactTel);
//		String isSuccess = PushMasUtil.pushMsg(message,contactTel);
//		String isSuccess = PushQytMsgUtil.pushMsg(message,contactTel);

		/**
		 * 4.返回操作结果
		 */
		inInfo.set("isSuccess", isSuccess);
		return inInfo;
	}

}
