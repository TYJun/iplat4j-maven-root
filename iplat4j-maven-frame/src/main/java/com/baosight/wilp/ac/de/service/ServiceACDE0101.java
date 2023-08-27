package com.baosight.wilp.ac.de.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.ArchivesLog;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 新增科室子页面.
 * <p>
 * 页面初始化方法, 新增科室, 根据科室ID更新院区编码.
 * </p>
 *
 * @Title ServiceACDE0101.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceACDE0101 extends ServiceBase {

	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 页面初始化方法
	 * 作者：hcd
	 * 入参：EiInfo
	 * 出参：EiInfo
	 * 处理：调用query()方法
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		return inInfo;
	}

	/**
	 * 新增科室
	 * 作者：hcd
	 * 入参：deptName科室名称，parentId双亲ID，finaCode财务编码，erpCodeERP编码，deptDescribe
	 * 科室描述，type类型
	 * 出参：inInfo（返回新增结果）
	 * 处理：
	 * 1.自动生成主键id，从前端获取deptName科室名称，parentId双亲ID，finaCode财务编码，erpCodeERP编码，deptDescribe
	 * 科室描述，type类型从UserSession获取当前登录人信息存入recCreater，生成当前时间存入recCreateTime
	 * 2.按规则自动生成科室编码deptNum
	 * 3.调用dao.insert()方法插入数据
	 * 4.根据科室ID更新院区编码
	 */
	@Override
	@ArchivesLog(model = "AC", sign = "新增科室")
	public EiInfo insert(EiInfo inInfo) {
		/**
		 *  1.自动生成主键id，从前端获取deptName科室名称，parentId双亲ID，finaCode财务编码，erpCodeERP编码，deptDescribe
		 *  科室描述，type类型从UserSession获取当前登录人信息存入recCreater，生成当前时间存入recCreateTime
		 */
		String id = UUID.randomUUID().toString(); /* 主键 */
		String deptNum = null; /* 科室编码 */
		String deptName = inInfo.get("deptName") == null ? "" : inInfo.getString("deptName"); /* 科室名称 */
		String parentId = inInfo.get("parentId") == null ? "" : inInfo.getString("parentId"); /* 父ID */
		String finaCode = inInfo.get("finaCode") == null ? "" : inInfo.getString("finaCode"); /* 财务编码 */
		String erpCode = inInfo.get("erpCode") == null ? "" : inInfo.getString("erpCode"); /* ERP编码 */
		String deptDescribe = inInfo.get("deptDescribe") == null ? "" : inInfo.getString("deptDescribe"); /* 科室描述 */
		String type = inInfo.get("type") == null ? "" : inInfo.getString("type"); /* 科室类型 */
		String status = "1"; // 默认启用

		String recCreater = UserSession.getUser().getUsername();
		String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); /* 创建时间 */

		/**
		 * 2.按规则自动生成科室编码deptNum
		 */
		Map<String, String> map = new HashMap<>();
		map.put("projectSchema", projectSchema);

		// 先查询出当前最新的 rec_create_time 取出该记录的spotNum
		List<Map<String, String>> list = dao.query("ACDE01.queryLastDeptNum", map);
		if (CollectionUtils.isEmpty(list)) { // 如果没有记录则生成一号NO00001
			deptNum = "NO00001";
		} else {
			deptNum = genDeptNum(list.get(0).get("deptNum")); // 生成地点编号
		}

//		EiInfo info = new EiInfo();
//		info.set("id", parentId);
//		info.set(EiConstant.serviceName, "ACFW01");
//		info.set(EiConstant.methodName, "getChildren");
//		info = XLocalManager.call(info);
//		
//		String parentSet = info.get("deptStr") == null ? "" : info.getString("deptStr");

		map.put("id", id);
		map.put("deptNum", deptNum);
		map.put("deptName", deptName);
		map.put("parentId", parentId);
//        map.put("parentSet",parentSet);
		map.put("finaCode", finaCode);
		map.put("erpCode", erpCode);
		map.put("deptDescribe", deptDescribe);
		map.put("type", type);
		map.put("status", status);

		map.put("recCreater", recCreater);
		map.put("recCreateTime", recCreateTime);

		/**
		 *  3.调用dao.insert()方法插入数据
		 */
		dao.insert("ACDE0101.insert", map);

		/**
		 * 4.根据科室ID更新院区编码
		 */
		inInfo.set("id", id);
		updateHosdNum(inInfo);

		return inInfo;
	}

	/**
	 * 按照 NO00000 方式生成科室编号
	 * 作者：jzm
	 * 入参：lastDeptNum上一次最后生成的部门编号
	 * 出参：下一个部门编号
	 * 处理：取后五位转整型然后加一，转字符串并在前面拼接前缀返回。
	 */
	public static String genDeptNum(String lastDeptNum) {
		return "NO" + String.format("%05d", Integer.parseInt(lastDeptNum.substring(2)) + 1);
	}

	/**
	 * 根据科室ID更新院区编码
	 * 作者：hcd
	 * 入参：inInfo（科室ID id）
	 * 出参：inInfo （执行结果）
	 * 处理：
	 * 1.从入参中获取id
	 * 2.执行dao.query()方法查询出该科室的孩子节点list
	 * 3.如果list非空，读出hospitalDistrict
	 * 4.将院区hospitalDistrict插入数据库中
	 */
	public EiInfo updateHosdNum(EiInfo inInfo) {

		/**
		 * 1.从入参中获取id
		 */
		String id = inInfo.get("id") == null ? "" : inInfo.getString("id");

		/**
		 * 2.执行dao.query()方法查询出该科室的孩子节点list
		 */
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("projectSchema", projectSchema);
		List<Map<String, Object>> list = dao.query("ACDE0101.getChildrenList", map);
		
        System.out.println(list);

		/**
		 * 3.如果list非空，读出hospitalDistrict
		 */
		if (!CollectionUtils.isEmpty(list)) {
			String hospitalDistrict = list.get(0).get("hosdNum").toString();
			/**
			 * 4.将院区hospitalDistrict插入数据库中
			 */
			map.put("hospitalDistrict", hospitalDistrict);
			dao.update("ACDE0101.updateHosdNum", map);

		}
		return inInfo;
	}

}
