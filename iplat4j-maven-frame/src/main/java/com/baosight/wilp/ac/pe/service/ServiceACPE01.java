package com.baosight.wilp.ac.pe.service;

import com.baosight.iplat4j.common.ed.domain.TEDCM01;
import com.baosight.wilp.ac.jk.service.ServiceACJK01;
import com.baosight.wilp.common.util.ArchivesLog;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.common.util.MaintainUtil;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.wilp.mc.fw.util.SendingDingMsgUtil;
import com.baosight.wilp.mc.fw.util.SendingWXMsgUtil;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
//import lombok.SneakyThrows;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 人员档案信息管理.
 * <p>
 * 页面初始化方法, 查询功能, 启用/停用功能, 一键生成用户, 从 excel 导入数据.
 * </p>
 *
 * @Title ServiceACPE01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */

@SuppressWarnings("unchecked")
public class ServiceACPE01 extends ServiceBase {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 页面初始化方法
	 * 作者：hcd
	 * 入参：EiInfo
	 * 出参：EiInfo
	 * 处理：调用query()方法
	 */

	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 查询功能
	 * 作者：hcd
	 * 入参：EiInfo（员工工号 "workNo", 员工名字 "name", 科室名称 "deptName", 双亲ID "parentId"）
	 * 出参：EiInfo（满足入参条件的人员信息）
	 * 处理：
	 * 1.获取当前用户的院区信息，和工号
	 * 2.调用AUFW01.getUserDepts()方法获取该用户的所属科室信息
	 * 3.查询满足入参条件和所属科室的人员信息
	 * 4.将结果封装在EiInfo中的result域中返回
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {

		/**
		 * 1.获取当前用户的院区信息，和工号
		 */
		String datagroupCode = DatagroupUtil.getDatagroupCode();
		String workNo = UserSession.getLoginName();


		/**
		 * 2.调用AUFW01.getUserDepts()方法获取该用户的所属科室信息
		 */
		EiInfo eiInfo = new EiInfo();
		eiInfo.set("datagroupCode", datagroupCode);
		eiInfo.set("workNo", workNo);
		eiInfo.set(EiConstant.serviceName, "AUFW01");
		eiInfo.set(EiConstant.methodName, "getUserDepts");
		EiInfo info = XLocalManager.call(eiInfo);
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) info.get("result");

		/**
		 * 3.查询满足入参条件和所属科室的人员信息
		 */
		String[] param = { "workNo", "name", "deptName", "parentId", "status" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);

		// 如果status是null 则为默认初始化查询 需要置status为 1（启用）
		map.putIfAbsent("status", "启用");

		map.put("list", list);
		map.put("projectSchema", projectSchema);

		List<Map<String, Object>> deptList = dao.query("ACPE01.queryPerlList", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = super.count("ACPE01.queryPerlListCount", map);


		/**
		 *  4.将结果封装在EiInfo中的result域中返回
		 */
		if (deptList != null && deptList.size() > 0) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(deptList.get(0)), deptList, count);
		} else {
			return inInfo;
		}
	}

	/**
	 * 删除功能 入参：EiInfo（待删除人员的id list） 出参：EiInfo（删除操作结果信息） 处理： 1.从入参EiInfo中获取待删除人员的id
	 * list 2.调用删除方法在数据库中删除满足条件的记录 3.返回删除结果
	 */
//	public EiInfo delete(EiInfo inInfo) {
//
//		List<String> list = (List<String>)inInfo.get("list");
//		dao.delete("ACPE01.delete",list);
//		inInfo.setStatus(0);
//		inInfo.setMsg("删除成功");
//		return inInfo;
//	}

	/**
	 * 启用/停用功能
	 * 作者：hcd
	 * 入参：EiInfo（待启用/停用人员的id list）
	 * 出参：EiInfo（启用/停用操作结果信息）
	 * 处理：
	 * 1.从入参EiInfo中获取待启用/停用人员的id list
	 * 2.调用更新方法在数据库中更新满足条件的记录
	 * 3.返回操作结果
	 */
	public EiInfo updateStatus(EiInfo inInfo) {

		String platSchema = PrUtils.getIplatConfigure();

		/**
		 *  1.从入参EiInfo中获取待启用/停用人员的id list
		 */
		List<String> list = (List<String>) inInfo.get("list");
		String status = inInfo.getString("status");
		Map<String, Object> map = new HashMap<>();

		/**
		 *  2.调用更新方法在数据库中更新满足条件的记录
		 */
		map.put("list", list);
		map.put("status", status);
		map.put("projectSchema", projectSchema);
		map.put("platSchema", platSchema);
		dao.update("ACPE01.updateStatus", map);

		if ("0".equals(status)) {
			map.put("status", "-1");
		}
		dao.update("ACPE01.updateStatus1", map);

		/**
		 * 3.返回操作结果
		 */
		inInfo.setStatus(0);
		inInfo.setMsg("操作成功");
		return inInfo;
	}

	/**
	 * 一键生成用户
	 * 作者：hcd
	 * 入参：EiInfo（list{人员工号workNo，人员名字name，电话contactTel}）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参EiInfo中获取待操作用户信息list
	 * 2.处理待操作用户信息list
	 * 3.调用系统服务S_XS_15 生成用户
	 * 4.返回操作结果
	 */
	@ArchivesLog(model = "AC", sign = "一键生成用户")
	public EiInfo generateUsers(EiInfo inInfo) {
		/**
		 * 1.从入参EiInfo中获取待操作用户信息list
		 */
		List<Map<String, Object>> list = (List<Map<String, Object>>) inInfo.get("list");

		// 设置用户数据对象list
		List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();

		/**
		 * 2.处理待操作用户信息list
		 */
		list.forEach(e -> {

			Map<String, Object> map = new HashMap<>();

			map.put("loginName", e.get("workNo"));
			map.put("password", "Abc@123");
			map.put("rePass", "Abc@123");
			map.put("userType", "USER");
			map.put("userName", e.get("name"));
			map.put("mobile", e.get("contactTel"));
			map.put("email", "110@qq.com");
			map.put("recCreator", "admin");

			userList.add(map);

		});

		/**
		 * 3.调用系统服务S_XS_15 生成用户
		 */
		EiInfo eiInfo = new EiInfo();
		eiInfo.set(EiConstant.serviceId, "S_XS_15");
		// 设置参数控制属性，true忽略重复数据
		eiInfo.set("ignoreDuplicate", "false");
		eiInfo.set("list", userList);
		EiInfo outInfo = XServiceManager.call(eiInfo);

		/**
		 * 4.返回操作结果
		 */
		if (outInfo.getStatus() < 0) {
			outInfo.setMsg("已有用户存在,请重新选择!");
		}
		return outInfo;
	}

	/**
	 * 从 excel 导入数据
	 * 作者：jzm
	 * 入参：EiInfo(用户信息 List<Map<String, String>>)
	 * 出参：EiInfo（导入结果，如果有错则包括错误数据和错误原因）
	 * 操作：
	 * 1.获取员工类型编码集合 放入 typeSet中 用于验证
	 * 2.取数据库现有的所有员工工号集合用于验证
	 * 3.拉取数据库现有的所有科室集合
	 * 4.数据非空校验和长度校验,验证人员工号是否重复,验证科室是否存在
	 * 5.处理正确数据和错误数据
	 * 6.返回结果
	 */
	public EiInfo importPersFromExcel(EiInfo eiInfo) {
		HashMap<String, String> checkMap = new HashMap<>();// 用于验证员工工号是否重复
		List<Map<String, String>> inList = (List<Map<String, String>>) eiInfo.get("list");
		List<Map<String, String>> okList = new ArrayList<>();
		List<Map<String, String>> errorList = new ArrayList<>();

		/**
		 *  1.获取员工类型编码集合 放入 typeSet中 用于验证
		 */
		EiInfo ei = new EiInfo();
		ei.set(EiConstant.serviceId, "S_ED_36");
		ei.set("codesetCode", "wilp.ac.pe.type");

		EiInfo out = XServiceManager.call(ei);
		ArrayList<TEDCM01> result = (ArrayList<TEDCM01>) out.getAttr().get("result");
		Set<String> typeSet = new HashSet<>();
		result.forEach(e -> typeSet.add(e.getItemCode()));

		/**
		 *  2.取数据库现有的所有员工工号集合用于验证
		 */
		HashMap<String, String> mp = new HashMap<>();
		mp.put("projectSchema", projectSchema);
		List<String> workNoList = dao.query("ACPE01.queryAllWorkNo", mp,0,-999999);
		Set<String> workNoSet = new HashSet<>(workNoList);

		/**
		 * 3.拉取数据库现有的所有科室集合
		 */
		HashMap<String, String> mp2 = new HashMap<>();
		mp2.put("projectSchema", projectSchema);
		List<String> deptNumList = dao.query("ACDE01.queryAlldeptNum", mp2,0,-999999);
		Set<String> deptNumSet = new HashSet<>(deptNumList);

		/**
		 *  4.数据非空校验和长度校验
		 */
		for (Map<String, String> map : inList) {
			StringBuilder error = new StringBuilder();
			if (StringUtils.isEmpty(map.get("workNo"))) {
				error.append("工号为空  ");
			}
			if (StringUtils.isEmpty(map.get("name"))) {
				error.append("姓名为空  ");
			}
			if (StringUtils.isEmpty(map.get("gender"))) {
				error.append("性别为空  ");
			}
			if (StringUtils.isEmpty(map.get("deptId"))) {
				error.append("所属科室为空  ");
			}
			if (StringUtils.isEmpty(map.get("type"))) {
				error.append("员工类型为空  ");
			}
			if (StringUtils.isEmpty(map.get("isService"))) {
				error.append("服务人员为空  ");
			}

			// 判断字段长度是否超出
			if (map.get("workNo").length() > 50) {
				error.append("工号长度超过50  ");
			}
			if (map.get("name").length() > 30) {
				error.append("姓名长度超过30 ");
			}
			if (map.get("gender").length() > 1) {
				error.append("性别长度超过1  ");
			}
			if (map.get("idNo").length() > 32) {
				error.append("身份证号码长度超过32  ");
			}
			if (map.get("contactTel").length() > 20) {
				error.append("联系电话长度超过20 ");
			}
			if (map.get("deptId").length() > 50) {
				error.append("所属科室长度超过50  ");
			}
			if (map.get("type").length() > 2) {
				error.append("员工类型长度超过2  ");
			}
			if (map.get("isService").length() > 2) {
				error.append("服务人员长度超过2  ");
			}

			if (!("1".equals(map.get("isService")) || "0".equals(map.get("isService")))) {
				error.append("服务人员应填写 1 或 0  ");
			}
			if (!typeSet.contains(map.get("type"))) {
				error.append("员工类型应填写 ").append(typeSet).append("中的一个  ");
			}

			// 验证人员工号是否重复
			if (workNoSet.contains(map.get("workNo")) || checkMap.get(map.get("workNo")) != null) { // deptNum duplicate！
				error.append("人员工号重复  ");
			}

			// 验证所属科室是否存在（如果不存在则错）
			if (!deptNumSet.contains( map.get("deptId"))) {
				error.append("所属科室不存在  ");
			}

			checkMap.put(map.get("workNo"), "1");

			/**
			 *  5.处理正确数据和错误数据
			 */
			// 判断是否存在错误
			if (error.length() == 0) {
				// 无错误，则生成必要信息 然后存入okList
				String id = UUID.randomUUID().toString();
				String recCreateTime = sdf.format(new Date());
				String recCreater = UserSession.getLoginName();
				map.put("id", id);
				map.put("status", "1"); // 默认启用
				map.put("recCreater", recCreater);
				map.put("recCreateTime", recCreateTime);
				// 存入okList
				okList.add(map);

			} else {
				// 有错误 添加错误字段 存入errorList
				map.put("error", error.toString());
				errorList.add(map);
			}

		}
		// 如果 okList 不为空 则插入数据库
		if (CollectionUtils.isNotEmpty(okList)) {
			// 批量导入okList
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("list", okList);
			map1.put("projectSchema", projectSchema);
			dao.insert("ACPE01.importPersFromExcel", map1);
		}

		/**
		 * 6.返回结果
		 */
		EiInfo outInfo = new EiInfo();
		if (!errorList.isEmpty()) {
			outInfo.setStatus(1);
			HashMap<String, Object> map = new HashMap<>();
			map.put("list", errorList);
			outInfo.setAttr(map);
			return outInfo;
		}
		outInfo.setStatus(0);
		return outInfo;
	}


	/**
	 * 查询所有人员信息（人员工号workNo和 人员姓名）（用于二维码全部导出）
	 * 作者：jzm
	 * 入参：EiInfo(空)
	 * 出参：EiInfo（人员集合）
	 * 操作：
	 * 1.获取当前用户的院区信息，和工号
	 * 2.调用AUFW01.getUserDepts()方法获取该用户的所属科室信息
	 * 3.查询满足入参条件和所属科室的人员信息
	 * 4.将结果封装在EiInfo中的result域中返回
	 * @param inInfo
	 * @return
	 */
	public EiInfo queryAllPerl(EiInfo inInfo){

		/**
		 * 1.获取当前用户的院区信息，和工号
		 */
		String datagroupCode = DatagroupUtil.getDatagroupCode();
		String workNo = UserSession.getLoginName();

		/**
		 * 2.调用AUFW01.getUserDepts()方法获取该用户的所属科室信息
		 */
		EiInfo eiInfo = new EiInfo();
		eiInfo.set("datagroupCode", datagroupCode);
		eiInfo.set("workNo", workNo);
		eiInfo.set(EiConstant.serviceName, "AUFW01");
		eiInfo.set(EiConstant.methodName, "getUserDepts");

		EiInfo info = XLocalManager.call(eiInfo);
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) info.get("result");

		/**
		 *  3.查询满足入参条件和所属科室的人员信息
		 */
		String[] param = { "workNo", "name", "deptName", "parentId", "status" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);

		// 如果status是null 则为默认初始化查询 需要置status为 1（启用）
		map.putIfAbsent("status", "启用");

		map.put("list", list);
		map.put("projectSchema", projectSchema);
		map.put("offset", 0);
		map.put("limit", -999999);
		List<Map<String, Object>> deptList = dao.query("ACPE01.queryPerlList", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = super.count("ACPE01.queryPerlListCount", map);


		/**
		 * 4.将结果封装在EiInfo中的result域中返回
		 */
		if (deptList != null && deptList.size() > 0) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(deptList.get(0)), deptList, count);
		} else {
			return inInfo;
		}
	}


	/**
	 * 通过工号集合查询人员信息（人员工号workNo和 人员姓名）（用于二维码部分导出）
	 * 作者：jzm
	 * 入参：EiInfo(空)
	 * 出参：EiInfo（人员集合）
	 * 操作：
	 * 1.从入参中获取 工号 list
	 * 2.调用dao.query()方法查询符合条件的员工信息
	 * 3.返回查结果
	 *
	 */
	public EiInfo queryPerlInfoByNum(EiInfo inInfo) {

		/**
		 * 1.从入参中获取 工号 list
		 */
		List<String> list = (List<String>) inInfo.get("list");

		Map<String, Object> map = new HashMap<>();

		// 如果status是null 则为默认初始化查询 需要置status为 1（启用）
		map.putIfAbsent("status", "启用");

		map.put("list", list);
		map.put("projectSchema", projectSchema);
		map.put("offset", 0);
		map.put("limit", -999999);

		/**
		 * 2.调用dao.query()方法查询符合条件的员工信息
		 */
		List<Map<String, Object>> deptList = dao.query("ACPE01.queryPerlInfoByNum", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = super.count("ACPE01.queryPerlInfoByNumCount", map);

		/**
		 * 3.返回查结果
		 */
		if (!CollectionUtils.isEmpty(deptList)) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(deptList.get(0)), deptList, count);
		} else {
			return inInfo;
		}
	}
	
	/**
	 * 同步钉钉人员信息
	 * 作者：hcd
	 * 入参：EiInfo()
	 * 出参：EiInfo()
	 * 处理：
	 * 1.获取token值
	 * 2.获取所有科室
	 * 3.获取钉钉所有人员
	 * 4.处理人员userList，将已存在的添加到oldList，将不存在的添加到newList
	 * 5.将改数据库所有人员改为停用状态
	 * 6.如果 oldList 不为空 则修改数据库
	 * 7.如果 newList 不为空 则插入数据库
	 * 8.返回操作结果
	 */
	public EiInfo synchronizationDingUser(EiInfo eiInfo) {
		List<Map<String, Object>> userList = new ArrayList<>();
		List<Map<String, Object>> newList = new ArrayList<>();
		List<Map<String, Object>> oldList = new ArrayList<>();
		
		String recCreater = UserSession.getLoginName();
		String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); /* 创建时间 */
		
		/**
		 * 1.获取token值
		 */ 
		eiInfo.set("appCode", "AP00002");
		eiInfo.set(EiConstant.serviceName, "MCFW01");
		eiInfo.set(EiConstant.methodName, "getToken");
		EiInfo inInfo1 = XLocalManager.call(eiInfo);
		if (inInfo1.getStatus() < 0) {
			inInfo1.setStatus(-1);
			inInfo1.setMsg("token没有获取到!");
			return inInfo1;
		}
		String token = inInfo1.get("token") == null ? "" : inInfo1.getString("token");

		/**
		 * 2.获取所有科室
		 */ 
		HashMap<String, Object> map2 = new HashMap<>();
		map2.put("source", "1");
		map2.put("projectSchema", projectSchema);
		List<Map<String, Object>> deptList = dao.query("ACDE01.queryDeptList", map2);
		
		/**
		 * 3.获取钉钉所有人员
		 */ 
		
		List<Map<String, Object>> userList2 = new ArrayList<>();
		for (Map<String, Object> map : deptList) {
//			HashMap<String, Object> map1 = new HashMap<>();
//			map1.put("id", map.get("id"));
			
			List<Map<String, Object>> list = SendingDingMsgUtil.getUserList(map.get("id").toString(),token);
			
			//将科室ID拼入集合，将人员信息存入userList，并去重
			for (Map<String, Object> userMap : list) {
				if(!userList2.contains(userMap)) {
					Map<String, String> userInfoMap = SendingDingMsgUtil.getUserMobile(userMap.get("userid")+"",token);
					if(userInfoMap.get("jobnumber")!=null&&!"".equals(userInfoMap.get("jobnumber"))) {
						HashMap<String, Object> userMap2 = new HashMap<>();
						userMap2.put("deptId", map.get("id"));
						userMap2.put("userid", userMap.get("userid"));
						userMap2.put("workNo", userInfoMap.get("jobnumber"));
						userMap2.put("name", userMap.get("name"));
						userMap2.put("contactTel", userInfoMap.get("mobile"));
						userList.add(userMap2);
					}
				}
				userList2.add(userMap);
			}
			
		}
		
		/**
		 * 4.处理人员userList，将已存在的添加到oldList，将不存在的添加到newList
		 */ 
		for (Map<String, Object> map : userList) {
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("id", map.get("userid"));
			map1.put("source", "1");
			map1.put("projectSchema", projectSchema);
			
			List<Map<String, Object>> list = dao.query("ACPE01.queryPerlList", map1);
			
			map.put("id", map.get("userid"));
//			map.put("workNo", map.get("userid"));
			map.put("status", "1");
			map.put("source", "1");
			
			//将新的数据插入数据库，将旧的数据进行更新
			if(CollectionUtils.isNotEmpty(list)) {
				map.put("recRevisor", recCreater);
				map.put("recReviseTime", recCreateTime);
				oldList.add(map);
			}else {
//				Map<String, String> userInfoMap = SendingDingMsgUtil.getUserMobile(map.get("userid")+"",token);
//				map.put("contactTel", userInfoMap.get(""));
//				map.put("workNo", userInfoMap.get(""));
				map.put("isService", "0");
				map.put("type", "01");
				map.put("recCreater", recCreater);
				map.put("recCreateTime", recCreateTime);
				newList.add(map);
			}
		}
		
		/**
		 * 5.将改数据库所有人员改为停用状态
		 */ 
		HashMap<String, Object> originalMap = new HashMap<>();
		originalMap.put("source", "1");
		originalMap.put("projectSchema", projectSchema);
		List<Map<String, Object>> originalList = dao.query("ACPE01.queryPerlList", originalMap);
		
		if(CollectionUtils.isNotEmpty(originalList)) {
			HashMap<String, Object> usermap = new HashMap<>();
			usermap.put("list", originalList);
			usermap.put("projectSchema", projectSchema);
			dao.update("ACPE01.stopUser", usermap);
		}
		
		/**
		 * 6.如果 oldList 不为空 则修改数据库
		 */ 
		if (CollectionUtils.isNotEmpty(oldList)) {
			// 批量修改oldList
			for (Map<String, Object> map : oldList) {
				HashMap<String, Object> map1 = new HashMap<>();
				map1.put("id", map.get("id")+"");
				map1.put("workNo", map.get("workNo")+"");
				map1.put("name", map.get("name")+"");
				map1.put("deptId", map.get("deptId")+"");
				map1.put("status", map.get("status")+"");
				map1.put("source", map.get("source")+"");
				map1.put("recRevisor", map.get("recRevisor")+"");
				map1.put("recReviseTime", map.get("recReviseTime")+"");
				map1.put("projectSchema", projectSchema);
				
				dao.update("ACPE01.updateDingUser", map1);
			}
		}
		
		/**
		 * 7.如果 newList 不为空 则插入数据库
		 */ 
		if (CollectionUtils.isNotEmpty(newList)) {
			// 批量新增newList
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("list", newList);
			map1.put("projectSchema", projectSchema);

			dao.insert("ACPE01.insertDingUser", map1);
		}
		
		/**
		 * 8.返回操作结果
		 */
		// 返回
		EiInfo outInfo = new EiInfo();
		outInfo.setMsg("同步完成!");
		outInfo.setStatus(0);
		return outInfo;
	}
	
	/**
	 * 同步企业微信人员信息
	 * 作者：hcd
	 * 入参：EiInfo()
	 * 出参：EiInfo()
	 * 处理：
	 * 1.获取token值
	 * 2.获取所有科室
	 * 3.获取钉钉所有人员
	 * 4.处理人员userList，将已存在的添加到oldList，将不存在的添加到newList
	 * 5.将改数据库所有人员改为停用状态
	 * 6.如果 oldList 不为空 则修改数据库
	 * 7.如果 newList 不为空 则插入数据库
	 * 8.返回操作结果
	 */
	public EiInfo synchronizationWXUser(EiInfo eiInfo) {
		List<Map<String, Object>> userList = new ArrayList<>();
		List<Map<String, Object>> newList = new ArrayList<>();
		List<Map<String, Object>> oldList = new ArrayList<>();
		
		String recCreater = UserSession.getLoginName();
		String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); /* 创建时间 */
		
		/**
		 * 1.获取token值
		 */ 
		eiInfo.set("appCode", "AP00003");
		eiInfo.set(EiConstant.serviceName, "MCFW01");
		eiInfo.set(EiConstant.methodName, "getWXToken");
		EiInfo inInfo1 = XLocalManager.call(eiInfo);
		if (inInfo1.getStatus() < 0) {
			inInfo1.setStatus(-1);
			inInfo1.setMsg("token没有获取到!");
			return inInfo1;
		}
		String token = inInfo1.get("token") == null ? "" : inInfo1.getString("token");

		/**
		 * 2.获取所有科室
		 */ 
		HashMap<String, Object> map2 = new HashMap<>();
		map2.put("source", "2");
		map2.put("projectSchema", projectSchema);
		List<Map<String, Object>> deptList = dao.query("ACDE01.queryDeptList", map2);
		
		/**
		 * 3.获取钉钉所有人员
		 */ 
		List<Map<String, Object>> userList2 = new ArrayList<>();
		for (Map<String, Object> map : deptList) {
			
			String id = map.get("id").toString();
			
			if("wx1".equals(id)) {
				id = "1";
			}
			
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("id", map.get("id"));
			
			List<Map<String, Object>> list = SendingWXMsgUtil.getUserList(id,token);
			
			//将科室ID拼入集合
			for (Map<String, Object> userMap : list) {
				if(!userList2.contains(userMap)) {
					HashMap<String, Object> userMap2 = new HashMap<>();
					userMap2.put("deptId", map.get("id"));
					userMap2.put("userid", userMap.get("userMap"));
					userMap2.put("name", userMap.get("name"));
					userList.add(userMap2);
				}
				userList2.add(userMap);
			}
			
//			if (CollectionUtils.isNotEmpty(list)) {
//				userList.addAll(list);
//			}
		}
		
		/**
		 * 4.处理人员userList，将已存在的添加到oldList，将不存在的添加到newList
		 */ 
		for (Map<String, Object> map : userList) {
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("id", map.get("userid"));
			map1.put("source", "2");
			map1.put("projectSchema", projectSchema);
			
			List<Map<String, Object>> list = dao.query("ACPE01.queryPerlList", map1);
			
			map.put("id", map.get("userid"));
			map.put("workNo", map.get("userid"));
			map.put("status", "1");
			map.put("source", "2");
			
			//将新的数据插入数据库，将旧的数据进行更新
			if(CollectionUtils.isNotEmpty(list)) {
				map.put("recRevisor", recCreater);
				map.put("recReviseTime", recCreateTime);
				oldList.add(map);
			}else {
				//String mobile = SendingDingMsgUtil.getUserMobile(map.get("userid")+"",token);
				map.put("contactTel", "");
				map.put("isService", "0");
				map.put("type", "01");
				map.put("recCreater", recCreater);
				map.put("recCreateTime", recCreateTime);
				newList.add(map);
			}
		}
		
		/**
		 * 5.将改数据库所有人员改为停用状态
		 */ 
		HashMap<String, Object> originalMap = new HashMap<>();
		originalMap.put("source", "2");
		originalMap.put("projectSchema", projectSchema);
		List<Map<String, Object>> originalList = dao.query("ACPE01.queryPerlList", originalMap);
		
		if(CollectionUtils.isNotEmpty(originalList)) {
			HashMap<String, Object> usermap = new HashMap<>();
			usermap.put("list", originalList);
			usermap.put("projectSchema", projectSchema);
			dao.update("ACPE01.stopUser", usermap);
		}
		
		/**
		 * 6.如果 oldList 不为空 则修改数据库
		 */ 
		if (CollectionUtils.isNotEmpty(oldList)) {
			// 批量修改oldList
			for (Map<String, Object> map : oldList) {
				HashMap<String, Object> map1 = new HashMap<>();
				map1.put("id", map.get("id")+"");
				map1.put("workNo", map.get("workNo")+"");
				map1.put("name", map.get("name")+"");
				map1.put("deptId", map.get("deptId")+"");
				map1.put("status", map.get("status")+"");
				map1.put("source", map.get("source")+"");
				map1.put("recRevisor", map.get("recRevisor")+"");
				map1.put("recReviseTime", map.get("recReviseTime")+"");
				map1.put("projectSchema", projectSchema);
				
				dao.update("ACPE01.updateDingUser", map1);
			}
		}
		
		/**
		 * 7.如果 newList 不为空 则插入数据库
		 */ 
		if (CollectionUtils.isNotEmpty(newList)) {
			// 批量新增newList
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("list", newList);
			map1.put("projectSchema", projectSchema);

			dao.insert("ACPE01.insertDingUser", map1);
		}
		
		/**
		 * 8.返回操作结果
		 */
		// 返回
		EiInfo outInfo = new EiInfo();
		outInfo.setMsg("同步完成!");
		outInfo.setStatus(0);
		return outInfo;
	}

}
