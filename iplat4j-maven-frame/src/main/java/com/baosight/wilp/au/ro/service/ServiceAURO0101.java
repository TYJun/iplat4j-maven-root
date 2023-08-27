package com.baosight.wilp.au.ro.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.wilp.common.util.MaintainUtil;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.xservices.xs.util.UserSession;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 角色添加子页面.
 * <p>
 * 界面初始化, 新增角色, 查询功能.
 * </p>
 *
 * @Title ServiceAURO0101.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceAURO0101 extends ServiceBase {
	final String projectSchema = PrUtils.getConfigure();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 界面初始化
	 * 作者：jzm
	 * 入参：EiInfo
	 * 出参：EiInfo
	 * 处理：
	 * 返回入参EiInfo
	 */
	public EiInfo initLoad(EiInfo inInfo) {

		return inInfo;
	}

	/**
	 * 新增角色
	 * 作者：jzm
	 * 入参：EiInfo（角色名称 #roleName#,备注 #remark#）
	 * 出参：EiInfo（操作结果）
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
		 * 1.从入参中获取角色名称  #roleName#,备注 #remark#
		 */
		Map map = inInfo.getAttr();

		/**
		 * 2.自动生成主键和角色编号
		 */
		String id = UUID.randomUUID().toString(); // 主键
		String roleNum = null;

		// 先查询出当前最新的 rec_create_time 取出该记录的spotNum
		HashMap<String, String> map1 = new HashMap<>();
		map1.put("projectSchema", projectSchema);
		List<Map<String, String>> list = dao.query("AURO01.queryLastRoleNum", map1);

		if (CollectionUtils.isEmpty(list)) { // 如果没有记录则生成一号RO00001
			roleNum = "RO00001";
		} else {
			roleNum = genRoleNum(list.get(0).get("roleNum")); // 生成角色编号
		}

		String recCreateTime = sdf.format(new Date()); // 创建时间
		String recCreater = UserSession.getUser().getUsername();// 创建人

		map.put("id", id);
		map.put("roleNum", roleNum);
		map.put("recCreateTime", recCreateTime);
		map.put("recCreater", recCreater);
		map.put("projectSchema", projectSchema);

		/**
		 * 3.调用insert()方法向数据库中插入数据
		 */
		dao.insert("AURO01.insertRole", map);

		/**
		 *  4.返回操作结果
		 */
		inInfo.setMsg("角色添加成功!");
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
	@Override
	public EiInfo query(EiInfo inInfo) {
		/**
		 * 1.从入参中读取工号 "workNo",姓名 "name",科室名称 "deptName",双亲Id
		 * 	 * "parentId" 存入map中
		 */
		String[] param = { "workNo", "name", "deptName", "parentId" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);

		/**
		 * 2.调用query()方法查询出相关的人员列表
		 */
		map.put("projectSchema", projectSchema);
		List<Map<String, Object>> deptList = dao.query("AURU01.queryPerlList", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));
		int count = super.count("AURU01.queryPerlListCount", map);


		/**
		 * 3.将结果封装在EiInfo的result域 返回
		 */
		if (deptList != null && deptList.size() > 0) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(deptList.get(0)), deptList, count);
		} else {
			return inInfo;
		}

	}

	/**
	 * 按照 RO00000 方式生成角色编号
	 * 作者：jzm
	 * @return String
	 */
	public static String genRoleNum(String lastRoleNum) {
		return "RO" + String.format("%05d", Integer.valueOf(lastRoleNum.substring(2)) + 1);
	}
}
