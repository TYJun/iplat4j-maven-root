/**
 *@Name ServiceDHRM01.java
 *@Description 宿舍入住申请
 *@Date 2021年5月2日 下午7:19:55
 *@Version 1.0
 **/

package com.baosight.wilp.dm.xw.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.dm.common.DMBaseDockingUtils;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 校外人员宿舍入住登记页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMXW01.java
 * @ClassName: ServiceDMXW01
 * @Package：com.baosight.wilp.dm.xw.service
 * @author: xiezilong
 * @date: 2022年04月29日 上午10:00:00
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMXW01 extends ServiceBase{

	/**
	 * 一、宿舍入住申请查询页面加载.
	 *
	 * @Title: initLoad
	 * @param inInfo
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 校外人员宿舍入住查询
	 * @Title: query
	 * 逻辑处理
	 *     1.获取当前登录人信息，如果登录人不存在，提示错误信息
	 *     2.获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分
	 *     3.调用本地服务DMXW01.queryXWInfoList()方法进行列表数据查询
	 *
	 * @param inInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo query(EiInfo inInfo) {
		/*
		 * 1、获取当前登录人信息，如果登录人不存在，提示错误信息.
		 */
		Map<String, Object> userInfo = DMUtils.getUserInfo(null);
		if(userInfo == null || userInfo.isEmpty()){
			inInfo.setMsg("您的账号存在问题，请联系管理员");
			return inInfo;
		}
		/*
		 * 2、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
		 */
		String role = (String) userInfo.get("role");
		if(role.contains("ADMIN") || role.contains("DMSPR")){
			role = "DMADMIN";
		}else {
			role = "DMZSR";
		}
		inInfo.set("workNo", UserSession.getUser().getUsername());
		inInfo.set("role",role);
		// 调用本地服务DMXW01.queryXWInfoList()方法进行列表数据查询.
		inInfo.set(EiConstant.serviceName, "DMXW01");
		inInfo.set(EiConstant.methodName, "queryXWInfoList");
		EiInfo outInfo = XLocalManager.call(inInfo);
		return outInfo;
	}

	/**
	 * 校外人员宿舍信息查询
	 * @Title: queryXWInfoList
	 * 逻辑处理
	 *     1.将要查询的参数组成数组并调用工具类转换参数
	 *     2.将构建好的map传入DMXW01.queryRZInfoList进行查询并分页，同时查询列表数量
	 *     3.判断是否存在，存在则构建返回对象 并设置状态和返回信息
	 *
	 * @param inInfo
	 * @return
	 */
	public EiInfo queryXWInfoList(EiInfo inInfo) {
		/*
		 * 1、将要查询的参数组成数组并调用工具类转换参数
		 */
		String[] param = {"manNo", "manName","deptName", "employmentNature"};
		List<String> plist = Arrays.asList(param);
		// 调用工具类转换参数
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
		// 额外角色参数进行处理
		String role = inInfo.getString("role");
		if(StringUtils.isNotBlank(role) && role.contains("DMADMIN")) {
			map.put("role", role);
		}else if(StringUtils.isNotBlank(role)){
			map.put("role", role);
			map.put("workNo",inInfo.getString("workNo"));
		}
		/*
		 * 2、将构建好的map传入DMXW01.queryRZInfoList进行查询并分页，同时查询列表数量.
		 *    判断列表对象是否存在，存在则构建返回对象.
		 */
		//查询
		List<Map<String, Object>> list = dao.query("DMXW01.queryXWInfoList",map,
				Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		int count = super.count("DMXW01.queryXWInfoListCount",map);
		// 判断是否存在，存在则构建返回对象
		if(list != null && list.size() > 0){
			return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			inInfo.setMsg("没有查询到数据。");
			return inInfo;
		}
	}

	/**
	 * 更新状态.
	 * 根据指定manId更新状态.
	 * 1、获取inInfo传来的参数.
	 * 2、新建一个map用来存放获取的数据.
	 * 3、以map作为参数执行 DMXW01.updateStatusCode 进行状态的更新,更新人员入住信息表的状态.
	 * 4、返回一个EiInfo.
	 *
	 * @Title: updateStatusCode
	 * @param:  @param inInfo
	 *      taskId : 任务单号id
	 *      statusCode ： 工单状态
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 *
	 */
	public EiInfo updateStatusCode(EiInfo inInfo) {
		/*
		 * 1、获取inInfo传来的参数.
		 */
		String manId = inInfo.getString("manId");
		String statusCode = inInfo.getString("statusCode");
		/*
		 * 2、新建一个map用来存放获取的数据.
		 */
		Map<String, String> map = new HashMap<>();

		map.put("manId", manId);
		map.put("statusCode", statusCode);
		/*
		 * 3、以map作为参数执行 DMXW01.updateStatusCode 进行状态的更新,更新人员入住信息表的状态.
		 */
		dao.update("DMXW01.updateStatusCode", map);
		/*
		 * 4、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.set("manId", manId);
		outInfo.setMsg("操作成功");
		return outInfo;
	}


	/**
	 * 更新状态.
	 * 根据指定manId更新状态.
	 * 1、获取inInfo传来的参数.
	 * 2、对接收的参数 manId字符串 进行一个处理.
	 * 接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作.
	 * 3、新建一个map用来存放获取的数据.
	 * 4、以map作为参数执行 DMXW01.batchUpdateStatusCode 进行状态的批量更新,更新人员入住信息表的状态.
	 * 5、返回一个EiInfo.
	 *
	 * @Title: batchUpdateStatusCode
	 * @param:  @param inInfo
	 *      taskId : 任务单号id
	 *      statusCode ： 工单状态
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 *
	 */
	public EiInfo batchUpdateStatusCode(EiInfo inInfo) {
		/*
		 * 1、获取inInfo传来的参数.
		 */
		String statusCode = inInfo.getString("statusCode");
		/*
		 * 2、对接收的参数 manId字符串 进行一个处理.
		 *  接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作.
		 */
		// 先实例化 manIdList。
		List<Map<String, String>> manIdList = new LinkedList<>();
		// 获取参数的值。
		String manId = inInfo.getString("manIdList");
		// 接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作。
		if (StringUtils.isNotBlank(manId) && manId.split(",").length > 1) {
			// 以一个数组去存分割后的字符串。
			String[] manIdArray = manId.split(",");
			// 遍历该数组的长度。
			for (int i = 0; i < manIdArray.length; i++) {
				// 实例化一个Map<String,String>类型的manIdInfo，用来接收拆出来的manId。
				Map<String, String> manIdInfo = new HashMap<>();
				manIdInfo.put("manId", manIdArray[i]);
				// 将接收到数据的map添加到manIdInfo列表中。
				manIdList.add(manIdInfo);
			}
			// 处理lenght<1，即当获取的值为一个值的情况。
		}else if(StringUtils.isNotBlank(manId)){
			// 实例化一个Map<String,String>类型的idInfo，用来接收单独的manId。
			Map<String, String> manIdInfo = new HashMap<>();
			manIdInfo.put("manId", manId);
			// 将接收到数据的map添加到manIdInfo列表中。
			manIdList.add(manIdInfo);
		}
		/*
		 * 3、新建一个map用来存放获取的数据.
		 */
		Map<String, Object> map = new HashMap<>();
		map.put("manIdList", manIdList);
		map.put("statusCode", statusCode);
		/*
		 * 4、以map作为参数执行 DMXW01.batchUpdateStatusCode 进行状态的批量更新,更新人员入住信息表的状态.
		 */
		dao.update("DMXW01.batchUpdateStatusCode", map);
		/*
		 * 5、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.set("manId", manId);
		outInfo.setMsg("操作成功");
		return outInfo;
	}

	/**
	 * 更新人员信息表关于评价的内容
	 * 1、获取inInfo传来的参数.
	 * 2、新建一个map用来存放获取的数据.
	 * 3、以map作为参数执行 DMXW01.updateAboutEval 进行更新人员入住信息表的评价状态和评价内容和评价时间.
	 * 4、返回一个EiInfo.
	 *
	 * @Title: updateAboutEval
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	public EiInfo updateAboutEval(EiInfo inInfo) {
		/*
		 * 1、获取inInfo传来的参数.
		 */
		String manId = inInfo.getString("manId");
		String evalStatus = inInfo.getString("evalStatus");
		String evalContent = inInfo.getString("evalContent");
		String evalTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 评价时间*/
		/*
		 * 2、新建一个map用来存放获取的数据.
		 */
		Map<String, String> map = new HashMap<>();

		map.put("manId", manId);
		map.put("evalStatus", evalStatus);
		map.put("evalContent", evalContent);
		map.put("evalTime", evalTime);
		/*
		 * 3、以map作为参数执行 DMXW01.updateAboutEval 进行更新人员入住信息表的评价状态和评价内容和评价时间
		 */
		dao.update("DMXW01.updateAboutEval", map);
		/*
		 * 4、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.set("manId", manId);
		outInfo.setMsg("操作成功");
		return outInfo;
	}


	/**
	 * 宿舍操作流程历史表保存接口.
	 * 对参数处理，然后保存到数据库.
	 * 1、获取inInfo传来的参数.
	 * 2、新建一个map用来存放获取的数据.
	 * 3、以map作为参数执行 DMXW01.insertDMLCTable 将操作流程信息插入到宿舍操作流程历史表 .
	 * 4、返回一个EiInfo.
	 *
	 * @Title: insertLCInfo
	 * @param:  @param inInfo
	 *      id ：主键
	 *      manId  : 人员id
	 *      statusCode  : 状态编码
	 *      statusCodeMeaning : 状态含义
	 *      operator ： 操作人工号
	 *      operationName : 操作人
	 *      operationTime : 操作时间
	 * @return: EiInfo
	 * @throws
	 */
	public EiInfo insertLCInfo(EiInfo inInfo) {
		/*
		 * 1、获取inInfo传来的参数.
		 */
		String id = UUID.randomUUID().toString();   /*主键*/
		String manId = inInfo.get("manId") == null ? "" : inInfo.getString("manId");     /* 人员id */
		String statusCode = inInfo.get("statusCode") == null ? "" : inInfo.getString("statusCode");     /* 状态编码 */
		List<Map<String, String>> list = dao.query("DMRZ03.queryStatusCodeMeaning", statusCode);
		String statusCodeMeaning = list.get(0).get("codeName");     /* 状态编码 */
		String isCurrent = "1";		/* 是否当前状态：1是0否*/
		String operator = inInfo.get("operator") == null ? UserSession.getUser().getUsername() : inInfo.getString("operator");        /* 操作人工号*/
		Map<String, Object> operaUserInfo = DMUtils.getUserInfo(operator);
		String operationName =  operaUserInfo== null ? "" : operaUserInfo.get("name").toString(); /*操作人名称*/
		String operationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 操作时间*/
		/*
		 * 2、新建一个map用来存放获取的数据.
		 */
		Map<String, String> map = new HashMap<>();

		map.put("id",id);
		map.put("manId",manId);
		map.put("statusCode",statusCode);
		map.put("statusCodeMeaning",statusCodeMeaning);
		map.put("isCurrent",isCurrent);
		map.put("operator",operator);
		map.put("operationName",operationName);
		map.put("operationTime",operationTime);
		/*
		 * 3、以map作为参数执行 DMXW01.insertDMLCTable 将操作流程信息插入到宿舍操作流程历史表 .
		 */
		dao.insert("DMXW01.insertDMLCTable", map);
		/*
		 * 4、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.set("id", id);
		return outInfo;
	}


	/**
	 * 宿舍操作流程历史表保存接口.
	 * 对参数处理，然后保存到数据库.
	 * 1、获取inInfo传来的参数.
	 * 2、对接收的参数 manId字符串 进行一个处理.
	 * 	接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作.
	 * 3、以map作为参数执行 DMXW01.batchInsertDMLCTable 将操作流程信息批量插入到宿舍操作流程历史表 .
	 * 4、返回一个EiInfo.
	 *
	 * @Title: batchInsertLCInfo
	 * @param:  @param inInfo
	 *      id ：主键
	 *      manId  : 人员id
	 *      statusCode  : 状态编码
	 *      statusCodeMeaning : 状态含义
	 *      operator ： 操作人工号
	 *      operationName : 操作人
	 *      operationTime : 操作时间
	 * @return: EiInfo
	 * @throws
	 */
	public EiInfo batchInsertLCInfo(EiInfo inInfo) {
		/*
		 * 1、获取inInfo传来的参数.
		 */
		String statusCode = inInfo.get("statusCode") == null ? "" : inInfo.getString("statusCode");     /* 状态编码 */
		List<Map<String, String>> list = dao.query("DMRZ03.queryStatusCodeMeaning", statusCode);
		String statusCodeMeaning = list.get(0).get("codeName");     /* 状态编码 */
		String isCurrent = "1";		/* 是否当前状态：1是0否*/
		String operator = inInfo.get("operator") == null ? UserSession.getUser().getUsername() : inInfo.getString("operator");        /* 操作人工号*/
		Map<String, Object> operaUserInfo = DMUtils.getUserInfo(operator);
		String operationName =  operaUserInfo== null ? "" : operaUserInfo.get("name").toString(); /*操作人名称*/
		String operationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 操作时间*/
		/*
		 * 2、对接收的参数 manId字符串 进行一个处理.
		 *  接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作.
		 */
		// 先实例化 manIdList。
		List<Map<String, String>> manIdList = new LinkedList<>();
		// 获取参数的值。
		String manId = inInfo.getString("manIdList"); /* 人员id */
		// 接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作。
		if (StringUtils.isNotBlank(manId) && manId.split(",").length > 1) {
			// 以一个数组去存分割后的字符串。
			String[] manIdArray = manId.split(",");
			// 遍历该数组的长度。
			for (int i = 0; i < manIdArray.length; i++) {
				// 实例化一个Map<String,String>类型的manIdInfo，用来接收拆出来的manId。
				Map<String, String> manIdInfo = new HashMap<>();
				manIdInfo.put("id",UUID.randomUUID().toString());
				manIdInfo.put("manId", manIdArray[i]);
				manIdInfo.put("statusCode",statusCode);
				manIdInfo.put("statusCodeMeaning",statusCodeMeaning);
				manIdInfo.put("isCurrent",isCurrent);
				manIdInfo.put("operator",operator);
				manIdInfo.put("operationName",operationName);
				manIdInfo.put("operationTime",operationTime);
				// 将接收到数据的map添加到manIdInfo列表中。
				manIdList.add(manIdInfo);
			}
		// 处理lenght<1，即当获取的值为一个值的情况。
		}else if(StringUtils.isNotBlank(manId)){
			// 实例化一个Map<String,String>类型的idInfo，用来接收单独的manId。
			Map<String, String> manIdInfo = new HashMap<>();
			manIdInfo.put("id",UUID.randomUUID().toString());
			manIdInfo.put("manId", manId);
			manIdInfo.put("statusCode",statusCode);
			manIdInfo.put("statusCodeMeaning",statusCodeMeaning);
			manIdInfo.put("isCurrent",isCurrent);
			manIdInfo.put("operator",operator);
			manIdInfo.put("operationName",operationName);
			manIdInfo.put("operationTime",operationTime);
			// 将接收到数据的map添加到manIdInfo列表中。
			manIdList.add(manIdInfo);
		}
		/*
		 * 3、以map作为参数执行 DMXW01.batchInsertDMLCTable 将操作流程信息批量插入到宿舍操作流程历史表 .
		 */
		dao.insert("DMXW01.batchInsertDMLCTable", manIdList);
		/*
		 * 4、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.set("manId", manId);
		return outInfo;
	}


	/**
	 * 宿舍流程更新接口
	 * @Title: updateLCStatusCode
	 * @param:  @param inInfo
	 * 		manId : 人员Id
	 * 		statusCode : 状态
	 * @param:  @return
	 * @return: EiInfo  ：无
	 * @throws
	 */
	public EiInfo updateLCStatusCode(EiInfo inInfo) {
		/* 人员Id*/
		String manId = DMUtils.isEmpty(inInfo.get("manId"));
		/* 工单状态*/
		String statusCode = DMUtils.isEmpty(inInfo.get("statusCode"));
		//参数封装
		Map<String, String> map = new HashMap<>();
		map.put("manId",manId);
		map.put("statusCode",statusCode);
		//数据更新
		dao.update("DMXW01.updateLCStatusCode", map);
		//页面返回
		EiInfo outInfo = new EiInfo();
		outInfo.set("manId", manId);
		return outInfo;
	}


	/**
	 * 宿舍流程批量更新接口
	 * 1、获取inInfo传来的参数.
	 * 2、对接收的参数 manId字符串 进行一个处理.
	 * 	 接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作.
	 * 3、新建一个map用来存放获取的数据.
	 * 4、以map作为参数执行 DMXW01.batchUpdateLCStatusCode 进行流程状态的批量更新.
	 * 5、返回一个EiInfo.
	 *
	 * @Title: batchUpdateLCStatusCode
	 * @param:  @param inInfo
	 * 		manId : 人员Id
	 * 		statusCode : 状态
	 * @param:  @return
	 * @return: EiInfo  ：无
	 * @throws
	 */
	public EiInfo batchUpdateLCStatusCode(EiInfo inInfo) {
		/*
		 * 1、获取inInfo传来的参数.
		 */
		/* 工单状态*/
		String statusCode = DMUtils.isEmpty(inInfo.get("statusCode"));
		/*
		 * 2、对接收的参数 manId字符串 进行一个处理.
		 *  接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作.
		 */
		// 先实例化 manIdList。
		List<Map<String, String>> manIdList = new LinkedList<>();
		// 获取参数的值。
		String manId = inInfo.getString("manIdList"); /* 人员Id*/
		// 接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作。
		if (StringUtils.isNotBlank(manId) && manId.split(",").length > 1) {
			// 以一个数组去存分割后的字符串。
			String[] manIdArray = manId.split(",");
			// 遍历该数组的长度。
			for (int i = 0; i < manIdArray.length; i++) {
				// 实例化一个Map<String,String>类型的manIdInfo，用来接收拆出来的manId。
				Map<String, String> manIdInfo = new HashMap<>();
				manIdInfo.put("manId", manIdArray[i]);
				// 将接收到数据的map添加到manIdInfo列表中。
				manIdList.add(manIdInfo);
			}
			// 处理lenght<1，即当获取的值为一个值的情况。
		}else if(StringUtils.isNotBlank(manId)){
			// 实例化一个Map<String,String>类型的idInfo，用来接收单独的manId。
			Map<String, String> manIdInfo = new HashMap<>();
			manIdInfo.put("manId", manId);
			// 将接收到数据的map添加到manIdInfo列表中。
			manIdList.add(manIdInfo);
		}
		/*
		 * 3、新建一个map用来存放获取的数据.
		 */
		//参数封装
		Map<String, Object> map = new HashMap<>();
		map.put("manIdList",manIdList);
		map.put("statusCode",statusCode);
		/*
		 * 4、以map作为参数执行 DMXW01.batchUpdateLCStatusCode 进行流程状态的批量更新.
		 */
		//数据更新
		dao.update("DMXW01.batchUpdateLCStatusCode", map);
		/*
		 * 5、返回一个EiInfo.
		 */
		//页面返回
		EiInfo outInfo = new EiInfo();
		outInfo.set("manId", manId);
		return outInfo;
	}


	/**
	 * 选择人员（有分页）.
	 *  1、获取页面传过来的参数.
	 *  2、调用微服务接口S_AC_FW_01，获取所有人员信息.
	 *
	 * @Title: queryPersonnelRZ
	 * @Description:
	 * @param:  @param inInfo
	 *     userName： 姓名
	 *     workNo ： 工号
	 *     workNoEQ ： 工号（精确查询）
	 *     wgroupNum ： 科室编码
	 * @param:  @return
	 * @return: EiInfo
	 *      workNo      ：   员工工号
	 *      name        ：   员工姓名
	 *      gender      ：   员工性别
	 *      contactTel  ：   联系电话
	 *      deptNum     :   科室编码
	 *      deptName    :   科室名称
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo queryPersonnelRZ(EiInfo inInfo) {
		/*
		 * 1、获取页面传过来的参数.
		 */
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status","person");
		/*
		 * 2、调用微服务接口S_AC_FW_01，获取所有人员信息.
		 */
		EiInfo personnel = DMBaseDockingUtils.queryPersonnelPage(map);
		personnel.setBlockInfoValue("person", "showCount", "true");
		return personnel;
	}


	/**
	 * 获取人员信息（无分页）.
	 * 1、获取页面传过来的参数(无分页).
	 * 2、调用微服务接口S_AC_FW_01，获取所有人员信息.
	 *
	 * @Title: queryPersonnelList
	 * @param:  @param inInfo
	 *      guaranteeNum：   员工工号
	 *      name        ：   员工姓名
	 * @param:  @return
	 * @return: EiInfo
	 *      workNo      ：   员工工号
	 *      name        ：   员工姓名
	 *      gender      ：   员工性别
	 *      contactTel  ：   联系电话
	 *      deptNum     :   科室编码
	 *      deptName    :   科室名称
	 * @throws
	 */
	public EiInfo queryPersonnelList(EiInfo inInfo) {
		/*
		 * 1、获取页面传过来的参数(无分页).
		 */
		Map<String, Object> map = CommonUtils.buildParamterNoPage(inInfo,
				Arrays.asList(new String[]{"dataGroupCode","userName","manNo"}));
		/*
		 * 2、调用微服务接口S_AC_FW_01，获取所有人员信息.
		 */
		return DMBaseDockingUtils.queryPersonnelNoPage(map);
	}

	/**
	 * 科室查询.
	 *  1、获取页面传过来的参数(有分页).
	 *  2、调用微服务接口S_AC_FW_05，获取科室信息.
	 *
	 * @Title: queryDept
	 * @param:  @param inInfo
	 *      deptNum：科室编码
	 *      deptName：科室名称
	 *      dataGroupCode： 账套（院区）
	 * @param:  @return
	 * @return: EiInfo
	 *      deptNum：科室编码
	 *      deptName：科室名称
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo queryDept(EiInfo inInfo) {
		/*
		 * 1、获取页面传过来的参数(有分页).
		 */
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", "dept");
		map.put("dataGroupCode", inInfo.getString("dataGroupCode"));
		/*
		 * 2、调用微服务接口S_AC_FW_05，获取科室信息.
		 */
		EiInfo queryDept = DMBaseDockingUtils.queryDept(map);
		queryDept.setBlockInfoValue("dept", "showCount", "true");
		return queryDept;
	}

	/**
	 * 此方法用于删除校外入住登记信息
	 *
	 * @Title: deleteInfo
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	public EiInfo deleteInfo(EiInfo inInfo) {
		String infoId = inInfo.getString("infoId");
		dao.delete("DMXW01.deleteInfo", infoId);
		EiInfo outInfo = new EiInfo();
		outInfo.set("infoId", infoId);
		return outInfo;
	}

	/**
	 * 宿舍详情查询.
	 * 根据宿舍id，查询指定的宿舍.
	 * 1、获取前端传来的roomId值.
	 * 2、将roomId值放入map给DMXX01.queryRoomInfo 做参数去查询宿舍详情信息.
	 * 3、判断是否取得数据.
	 *
	 * @Title: queryRoomInfo
	 * @param:  @param inInfo
	 *      roomId： 宿舍id
	 * @param:  @return
	 * @return: EiInfo
	 *      id ：主键
	 *      building  : 宿舍楼
	 *      floor  : 宿舍层
	 *      roomNo  : 宿舍号
	 *      roomName : 宿舍总称(楼+层+宿舍号)
	 *      bedNum  : 床位数
	 *      remainingBedNum ： 剩余床位数
	 *      typeCode : 房间类型(1男生宿舍/0女生宿舍)
	 *      dormPosition : 宿舍位置：院内、院外
	 *      dormArea  : 房屋面积："<50㎡"、"50-100㎡"、">100㎡"
	 *      priBathroom : 独立卫生间：有、没有
	 *      rent  : 房租
	 *      manageFee : 管理费
	 *      note : 备注信息
	 *
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo queryXWApplyInfo(EiInfo inInfo) {
		/*
		 * 1、获取前端传来的id值.
		 */
		String id = "";
		if(inInfo.get("id") != null || !"".equals(inInfo.get("id"))) {
			id = inInfo.getString("id");
		}
		/*
		 * 2、将manId值放入map给DMRZ01.queryRZApplyInfo 做参数去查询入住申请的详情信息.
		 */
		Map<String, String> map = new HashMap<>();
		map.put("manId", id);
		EiInfo outInfo = new EiInfo();
		List<Map<String, String>> list = dao.query("DMXW01.queryXWApplyInfo", map);
		/*
		 * 3、判断是否取得数据.
		 */
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}
		outInfo.setAttr(list.get(0));
		outInfo.setRows("ApplyDetailInfo",list);
		return outInfo;
	}
	
}

