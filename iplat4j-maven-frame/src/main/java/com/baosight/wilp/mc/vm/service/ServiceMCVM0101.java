
package com.baosight.wilp.mc.vm.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.xservices.xs.util.UserSession;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 变量新增功能.
 * <p>
 * </p>
 *
 * @Title ServiceMCVM0101.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceMCVM0101 extends ServiceBase {

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
	 * 变量新增
	 * 作者：jzm
	 * 入参：EiInfo（变量名称 variableName）
	 * 出参：EiInfo（操作结果）
	 * 过程：
	 * 1.从入参中获取变量名称
	 * variableName存入map中
	 * 2.生成主键id
	 * 3.生成变量编码
	 * 4.生成创建人和生成创建时间
	 * 5.返回操作结果
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo insert(EiInfo eiInfo) {
		/**
		 * 1.从入参中获取变量名称
		 * 	 * variableName存入map中
		 */
		String projectSchema = PrUtils.getConfigure();
		String variableName = eiInfo.getString("variableName");
		Map<String, String> map = new HashMap<>();

		map.put("projectSchema", projectSchema);

		/**
		 * 2.生成主键id
		 */
		String id = UUID.randomUUID().toString();

		/**
		 *  3.生成变量编码
		 */
		String variableCode = null;
		// 先查询出当前最新的 rec_create_time 取出该记录的variableCode
		List<Map<String, String>> list = dao.query("MCVM0101.queryLastVarNum", map);

		if (CollectionUtils.isEmpty(list)) { // 如果没有记录则生成一号VA00001
			variableCode = "VA00001";
		} else {
			variableCode = genVarNum(list.get(0).get("variableCode")); // 生成变量编号
		}
		/**
		 * 4.生成创建人和生成创建时间
		 */
		// 生成创建人
		String recCreater = UserSession.getUser().getUsername();

		// 生成创建时间
		String recCreateTime = sdf.format(new Date());

		map.put("id", id);
		map.put("variableCode", variableCode);
		map.put("variableName", variableName);
		map.put("recCreater", recCreater);
		map.put("recCreateTime", recCreateTime);

		dao.insert("MCVM0101.insert", map);

		/**
		 * 5.返回操作结果
		 */
		eiInfo.setMsg("变量添加成功");
		return eiInfo;
	}

	/**
	 * 按照 VA00000 方式生成变量编号
	 * 作者：jzm
	 * 入参：lastVarNum上一次最后生成的变量编号 出参：下一个变量编号
	 *
	 * 处理：取后五位转整型然后加一，转字符串并在前面拼接前缀返回。
	 */
	public static String genVarNum(String lastVarNum) {
		return "VA" + String.format("%05d", Integer.parseInt(lastVarNum.substring(2)) + 1);
	}
}
