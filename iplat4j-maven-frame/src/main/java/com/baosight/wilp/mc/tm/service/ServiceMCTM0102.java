package com.baosight.wilp.mc.tm.service;

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
 * 模板修改子页面.
 * <p>
 * </p>
 *
 * @Title ServiceMCTM0102.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceMCTM0102 extends ServiceBase {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
	 * 查询方法
	 * 作者：jzm
	 * 入参：EiInfo（模板id）
	 * 出参：EiInfo（满足条件的模板信息）
	 * 处理：
	 * 1.从入参中读取模板id存入map中
	 * 2.调用数据库查询方法，查询满足条件的结果集
	 * 3.判断结果集是否为空，如果空则返回错误信息
	 * 4.将模板信息存入EiInfo并返回
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		/**
		 * 1.从入参中读取模板id存入map中
		 */
		String projectSchema = PrUtils.getConfigure();
		String platSchema=PrUtils.getIplatConfigure();
		Map<String, String> map = new HashMap<>();
		map.put("id", inInfo.getAttr().get("id").toString());
		map.put("projectSchema", projectSchema);
		map.put("platSchema", platSchema);
		/**
		 * 2.调用数据库查询方法，查询满足条件的结果集
		 */
		EiInfo outInfo = new EiInfo();
		List<Map<String, String>> list = dao.query("MCTM01.queryTemplate", map);
		/**
		 * 3.判断结果集是否为空，如果空则返回错误信息
		 */
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}
		/**
		 * 4.将模板信息存入EiInfo并返回
		 */
		outInfo.setAttr(list.get(0));
		return outInfo;
	}

	/**
	 * 修改模板
	 * 作者：jzm
	 * 入参：EiInfo（模板id id,模板名称 templateName,调用模块 callModule,发送渠道
	 * deliveryChannel, 是否抄送 isCc, 消息体 message, 变量编码 variableCode）
	 * 出参：EiInfo（操作结果）
	 * 操作：
	 * 1.从入参中读取模板id id,模板名称 templateName,调用模块 callModule,发送渠道 deliveryChannel,
	 * 是否抄送 isCc, 消息体 message, 变量编码 variableCode
	 * 2.读取当前时间存入recReviseTime，读取当前登录人存入recRevisor
	 * 3.调用update()方法更新数据库相关记录
	 * 4.返回操作结果
	 */
	@Override
	public EiInfo update(EiInfo inInfo) {
		/**
		 *  1.从入参中读取模板id id,模板名称 templateName,调用模块 callModule,发送渠道 deliveryChannel,
		 * 	 * 是否抄送 isCc, 消息体 message, 变量编码 variableCode
		 */
		String projectSchema = PrUtils.getConfigure();
		Map map = inInfo.getAttr();

		/**
		 * 2.读取当前时间存入recReviseTime，读取当前登录人存入recRevisor
		 */
		String recReviseTime = sdf.format(new Date()); // 修改时间
		String recRevisor = UserSession.getUser().getUsername();// 修改人

		/**
		 * 3.调用update()方法更新数据库相关记录
		 */
		map.put("recReviseTime", recReviseTime);
		map.put("recRevisor", recRevisor);
		map.put("projectSchema", projectSchema);

		dao.update("MCTM01.update", map);

		/**
		 * 4.返回操作结果
		 */
		inInfo.setMsg("模板修改成功!");
		return inInfo;
	}

}
