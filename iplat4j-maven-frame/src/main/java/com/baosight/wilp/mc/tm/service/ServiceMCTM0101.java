package com.baosight.wilp.mc.tm.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.wilp.common.util.MaintainUtil;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.xservices.xs.util.UserSession;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 模板添加子页面.
 * <p>
 * </p>
 *
 * @Title ServiceMCTM0101.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceMCTM0101 extends ServiceBase {

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
	 * 新增模板
	 * 作者：jzm
	 * 入参：EiInfo（角色名称 #roleName#,备注 #remark#） 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参中获取角色名称
	 * #roleName#,备注 #remark#
	 * 2.自动生成主键和角色编号
	 * 3.调用insert()方法向数据库中插入数据
	 * 4.返回操作结果
	 */
	@Override
	public EiInfo insert(EiInfo inInfo) {
		/**
		 * 1.从入参中获取角色名称 #roleName#,备注 #remark#
		 */
		String projectSchema = PrUtils.getConfigure();
		Map map = inInfo.getAttr();
		String id = UUID.randomUUID().toString(); // 主键
		String templateCode = null;

		map.put("projectSchema", projectSchema);

		/**
		 * 2.自动生成主键和角色编号
		 */
		// 先查询出当前最新的 rec_create_time 取出该记录的templateCode
		List<Map<String, String>> list = dao.query("MCTM01.queryLastTemplateCode", map);

		if (CollectionUtils.isEmpty(list)) { // 如果没有记录则生成一号TP00001
			templateCode = "TP00001";
		} else {
			templateCode = genRoleNum(list.get(0).get("templateCode")); // 生成模板编号
		}

		String recCreateTime = sdf.format(new Date()); // 创建时间
		String recCreater = UserSession.getUser().getUsername();// 创建人

		/**
		 * 3.调用insert()方法向数据库中插入数据
		 */
		map.put("id", id);
		map.put("templateCode", templateCode);
		map.put("recCreateTime", recCreateTime);
		map.put("recCreater", recCreater);

		dao.insert("MCTM01.insertTemplate", map);

		/**
		 *  4.返回操作结果
		 */
		inInfo.setMsg("模板添加成功!");
		return inInfo;
	}

	/**
	 * 查询功能
	 * 作者：jzm
	 * 入参：EiInfo（工号 "workNo",姓名 "name",科室名称 "deptName",双亲Id "parentId"）
	 * 出参：EiInfo（人员信息list）
	 * 处理：
	 * 1.从入参中读取工号 "workNo",姓名 "name",科室名称 "deptName",双亲Id
	 * "parentId" 存入map中
	 * 2.调用query()方法查询出相关的人员列表
	 * 3.将结果封装在EiInfo的result域
	 * 4.返回EiInfo
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo query(EiInfo inInfo) {
		/**
		 * 1.从入参中读取工号 "workNo",姓名 "name",科室名称 "deptName",双亲Id "parentId" 存入map中
		 */
		String projectSchema = PrUtils.getConfigure();
		String[] param = { "workNo", "name", "deptName", "parentId" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);
		map.put("projectSchema", projectSchema);
		/**
		 * 2.调用query()方法查询出相关的人员列表
		 */
		List<Map<String, Object>> deptList = dao.query("AURU01.queryPerlList", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = super.count("AURU01.queryPerlListCount", map);
		/**
		 *  3.将结果封装在EiInfo的result域
		 */
		if (deptList != null && deptList.size() > 0) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(deptList.get(0)), deptList, count);
		} else {
			return inInfo;
		}

	}

	/**
	 * 查询变量
	 * 作者：jzm
	 * 入参：EiInfo（inqu_status）
	 * 出参：EiInfo（人员信息list）
	 * 处理：
	 * 1.从入参中读inqu_status
	 * 存入map中
	 * 2.调用query()方法查询出相关的人员列表
	 * 3.将结果封装在EiInfo的result域
	 * 4.返回EiInfo
	 * @param inInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public EiInfo queryVariable(EiInfo inInfo) {

		String projectSchema = PrUtils.getConfigure();
		// 获取参数
		Map<String, Object> map = PrUtils.buildParamter(inInfo, "inqu_status", "dept");
		map.put("projectSchema", projectSchema);
		// 数据查询
		List<Map<String, Object>> list = dao.query("MCVM01.queryVar", map, (Integer) map.get("offset"),
				(Integer) map.get("limit"));
		int count = super.count("MCVM01.queryVarCount", map);
		// 返回
		if (list != null && list.size() > 0) {
			return PrUtils.BuildOutEiInfo(inInfo, "dept", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return inInfo;
		}
	}

	/**
	 * 按照 TP00000 方式生成模板编号
	 * 作者：jzm
	 * @return String
	 */
	public static String genRoleNum(String lastRoleNum) {
		return "TP" + String.format("%05d", Integer.parseInt(lastRoleNum.substring(2)) + 1);
	}
}
