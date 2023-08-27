package com.baosight.wilp.dm.xs.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.wilp.dm.common.domain.DMConstant;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 宿舍费用管理页面service
 * @Title: ServiceDMFY01.java
 * @ClassName: ServiceDMFP01
 * @Package：com.baosight.wilp.dm.fy.service
 * @author:
 * @date:
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMXSFY01 extends ServiceBase{

	/**
	 * 此方法用于宿舍费用管理页面初始化加载
	 *
	 * 逻辑处理：
	 * 1.调用query()方法进行列表数据查询
	 *
	 * @Title: initLoad
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
	    return query(inInfo);
	}

	/**
	 * 此方法用于宿舍费用管理页面列表数据查询
	 *
	 * 逻辑处理：
	 * 1.获取当前登录人信息，如果登录人不存在，提示错误信息.
	 * 2.获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
	 * 3.调用本地服务DMFY01.queryRZInfoList()方法查询费用信息.
	 *
	 * @Title: query
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
		/*
		 * 1. 获取当前登录人信息，如果登录人不存在，提示错误信息.
		 */
		// 调用工具类DMUtils查登陆人的用户信息
		Map<String, Object> userInfo = DMUtils.getUserInfo(null);
		// 判断账号是否为空，为空则提示。
		if(userInfo == null || userInfo.isEmpty()){
			inInfo.setMsg("您的账号存在问题，请联系管理员");
			return inInfo;
		}
		/*
		 * 2、获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分.
		 */
		String role = (String) userInfo.get("role");
		// 获取前端的人员大类值（manNature）
		String manNature = inInfo.getString("manNature");
		if(role.contains("ADMIN")){
			// 首次加载的时候过滤条件会为null，所以得进行判定。
			if (manNature == null){
				manNature = "学生";
			}
			role = "DMADMIN";
		}else if(role.contains("DMSPR_XSSS")){
			// 为学生审批人时，查询属性隐藏，只查为学生的相关信息。
			manNature = "学生";
			role = "DMSPR_XSSS";
		}else if(role.contains("DMSPR_ZGSS")){
			// 首次加载的时候过滤条件会为null，所以得进行判定。
			if (manNature == null){
				manNature = "学生";
			}
			role = "DMSPR_ZGSS";
		}else {
			// 首次加载的时候过滤条件会为null，所以得进行判定。
			if (manNature == null){
				manNature = "学生";
			}
			role = "DMZSR";
		}
		inInfo.set("manNature", manNature);
		inInfo.set("workNo", UserSession.getUser().getUsername());
		inInfo.set("role",role);
		/*
		 * 3、调用本地服务DMFY01.queryRZInfoList()方法进行列表数据查询.
		 */
		inInfo.set(EiConstant.serviceName, "DMFY01");
		inInfo.set(EiConstant.methodName, "queryFYInfoList");
		EiInfo outInfo = XLocalManager.call(inInfo);
		//返回查询结果
		return outInfo;

    }

	/**
	 * 此方法用于入住人员费用信息查询
	 *
	 * 逻辑处理：
	 * 1.将要查询的参数组成数组并调用工具类转换参数
	 * 2. 对额外角色参数进行处理
	 * 3.将构建好的map传入DMFY01.queryRYFeeList进行查询并分页，同时查询列表数量。
	 * 		判断列表对象是否存在，存在则构建返回对象。
	 * 4.执行DMFY01.queryRYFeeList查询宿舍住宿人员列表
	 *
	 * @Title: queryFYInfoList
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	public EiInfo queryFYInfoList(EiInfo inInfo) {
		/*
		 * 1. 将要查询的参数组成数组并调用工具类转换参数
		 */
		String[] param = {"manNo", "manName","deptName", "employmentNature","roomName","currentMonth"};
		List<String> plist = Arrays.asList(param);
		// 调用工具类转换参数
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
		// 判断人员大类的选择
		List manNatureList = new ArrayList();
		String manNature = inInfo.getString("manNature");
		//人员大类为学生
		if (manNature!=null && manNature.equals("学生")){
			String [] XSLX = DMConstant.XSLX;
			manNatureList = Arrays.asList(XSLX);
			//人员大类为职工
		}else if (manNature!=null && manNature.equals("职工")){
			String [] ZGLX = DMConstant.ZGLX;
			manNatureList = Arrays.asList(ZGLX);
		}
		/*
		 * 2、额外角色参数进行处理
		 */
		String role = inInfo.getString("role");
		if(StringUtils.isNotBlank(role) && role.contains("DMADMIN")) {
			map.put("role", role);
			map.put("manNatureList",manNatureList);
		}else if(StringUtils.isNotBlank(role) && role.contains("DMSPR")){
			map.put("role", "DMSPR");
			map.put("manNatureList", manNatureList);
		}else if(StringUtils.isNotBlank(role)){
			map.put("role", role);
			map.put("workNo",inInfo.getString("workNo"));
		}
		/*
		 * 3. 将构建好的map传入DMFY01.queryRYFeeList进行查询并分页，同时查询列表数量
		 *    判断列表对象是否存在，存在则构建返回对象。
		 */
		// 执行DMFY01.queryRYFeeList查询宿舍住宿人员列表
		List<Map<String, Object>> list = dao.query("DMFY01.queryRYFeeList",map,
				Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		int count = super.count("DMFY01.countRYFeeList",map);
		// 判断是否存在，存在则构建返回对象
		if(list != null && list.size() > 0){
			return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return inInfo;
		}
	}

	/**
	 * 人员住宿相关费用详情查询.
	 * 根据人员住宿相关费用表主键id，查询人员住宿相关费用详情.
	 * 1、获取前端传来的人员住宿相关费用表主键id.
	 * 2、将id值放入map给DMFY01.queryDMChooseManInfo 做参数去查询人员住宿相关详情.
	 * 3、判断是否取得数据.
	 *
	 * @Title: queryDMChooseManInfo
	 * @param:  @param inInfo
	 *      id： 人员住宿相关费用表主键id
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
	public EiInfo queryDMChooseManInfo(EiInfo inInfo) {
		/*
		 * 1、获取前端传来的宿舍人员绑定关系表主键id.
		 */
		String id = "";
		if(inInfo.get("id") != null || !"".equals(inInfo.get("id"))) {
			id = inInfo.getString("id");
		}
		/*
		 * 2、将id值放入map给DMFY01.queryDMChooseManInfo 做参数去查询人员住宿相关详情.
		 */
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		EiInfo outInfo = new EiInfo();
		List<Map<String, String>> list = dao.query("DMFY01.queryDMChooseManInfo", map);
		/*
		 * 3、判断是否取得数据.
		 */
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}
		//查询到数据，存入outInfo中
		outInfo.setAttr(list.get(0));
		outInfo.setRows("DetailInfo",list);
		//返回查询结果
		return outInfo;
	}


	/**
	 * 人员住宿相关费用信息插入接口.
	 * 对参数处理，然后保存到数据库.
	 * 1、获取当前用户信息.
	 * 2、获取inInfo传来的参数.
	 * 3、新建一个map用来存放获取的数据.
	 * 4、查询该用户当前月份是否存在的相关费用信息
	 * 判断是否存在，如果存在则直接返回插入结果失败，
	 * 如果不存在，则以map作为参数执行 DMFY01.insertDormsRoomFeeInfo 进行数据的插入，插入本月费用信息到人员住宿相关费用表.
	 * 5、返回操作结果.
	 *
	 * @Title: insertDormsRoomFeeInfo
	 * @param:  @param inInfo
	 *      roomId  : 房间id
	 *      manId  : 人员id
	 *      currentRent  : 本月实际月租（元）
	 *      currentManageFee : 本月实际管理费（元）
	 *      elecPriece : 本月电费
	 *      waterPriece : 本月水费
	 *      elecDegree  : 本月电表度数
	 *      waterDegree : 本月水表度数
	 *      remark : 备注
	 *      operator ： 操作人工号
	 *      operationName : 操作人
	 *      currentMonth : 当前年月份
	 *      operationTime : 操作时间
	 * @return: EiInfo
	 * @throws
	 */
	public EiInfo insertDormsRoomFeeInfo(EiInfo inInfo) {
		/*
		 * 1、获取当前用户信息.
		 */
		// 获取当前登陆工号
		String loginName= StringUtils.isBlank((String)inInfo.get("workNo"))?
				UserSession.getUser().getUsername():(String)inInfo.get("workNo");
		Map<String, Object> userInfo = DMUtils.getUserInfo(loginName);
		inInfo.set("operator", loginName);
		/*
		 * 2、获取inInfo传来的参数.
		 */
//		String id = UUID.randomUUID().toString();   /*主键*/
		String roomId = inInfo.get("roomId") == null ? "" : inInfo.getString("roomId");     /* 房间id */
		String manId = inInfo.get("manId") == null ? "" : inInfo.getString("manId");     /* 人员id */
		String currentMonth = inInfo.get("currentMonth") == null ? "" : inInfo.getString("currentMonth");     /* 前端传来的年月份*/
		String currentRent = inInfo.get("currentRent") == null ? "0" : inInfo.getString("currentRent");     /* 本月实际月租（元）*/
		String currentManageFee = inInfo.get("currentManageFee") == null ? "0" : inInfo.getString("currentManageFee");       /* 本月实际管理费（元） */
		String waterPriece = inInfo.get("waterPriece") == null ? "0" : inInfo.getString("waterPriece");     /* 本月水费*/
		String elecPriece = inInfo.get("elecPriece") == null ? "0" : inInfo.getString("elecPriece");        /* 本月电费*/
		String waterDegree = "".equals(inInfo.getString("waterDegree")) ? "0" : inInfo.getString("waterDegree");       /* 本月用水量（吨）*/
		String elecDegree = "".equals(inInfo.getString("elecDegree")) ? "0" : inInfo.getString("elecDegree");      /* 本月用电量（度）*/
		String remark = inInfo.get("remark") == null ? "" : inInfo.getString("remark");     /* 备注*/
		String operator = inInfo.get("operator") == null ? UserSession.getUser().getUsername() : inInfo.getString("operator");        /* 操作人工号*/
		Map<String, Object> createUserInfo = DMUtils.getUserInfo(operator);
		String operationName =  createUserInfo== null ? "" : createUserInfo.get("name").toString(); /*操作人名称*/
		String getCurrentMonth = new SimpleDateFormat("yyyy-MM").format(new Date());      /* 当前年月份 */
		String operationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 操作时间*/
		/*
		 * 3、新建一个map用来存放获取的数据.
		 */
		Map<String, Object> map = new HashMap<>();
//		map.put("id",id);
		map.put("roomId",roomId);
		map.put("manId", manId);
		map.put("currentRent",NumberUtils.createBigDecimal(currentRent));
		map.put("currentManageFee",NumberUtils.createBigDecimal(currentManageFee));
		map.put("waterPriece",NumberUtils.createBigDecimal(waterPriece));
		map.put("elecPriece",NumberUtils.createBigDecimal(elecPriece));
		map.put("elecDegree",Double.parseDouble(elecDegree));
		map.put("waterDegree",Double.parseDouble(waterDegree));
		map.put("remark",remark);
		map.put("operator",operator);
		map.put("operationName",operationName);
		map.put("currentMonth",currentMonth);
		map.put("operationTime",operationTime);
		/*
		 * 4、查询该用户当前月份是否存在的相关费用信息
		 * 判断是否存在，如果存在则直接返回插入结果失败，
		 * 如果不存在，则以map作为参数执行 DMFY01.insertDormsRoomFeeInfo 进行数据的插入，插入本月费用信息到人员住宿相关费用表.
		 */
		EiInfo outInfo = new EiInfo();
		if (!currentMonth.equals(getCurrentMonth)){
			int isExits = super.count("DMFY01.queryIsExistFee",map);
			if (isExits>0){
				outInfo.setMsg("操作失败,该用户已存在"+currentMonth+"的费用信息,无法修改往月费用信息，请联系管理员");
			}else {
				dao.insert("DMFY01.insertDormsRoomFeeInfo", map);
				outInfo.setMsg("操作成功");
			}
		}else{
			dao.insert("DMFY01.insertDormsRoomFeeInfo", map);
			outInfo.setMsg("操作成功");
		}

		/*
		 * 5、返回操作结果.
		 */
		return outInfo;
	}

	/**
	 * 更新费用信息表中的额外费用
	 * 1、获取inInfo传来的参数.
	 * 2、新建一个map用来存放获取的数据.
	 * 3、以map作为参数执行 DMFY01.updateExtraCharges 进行数据的更新，更新所选月份的费用信息中的额外费用到人员住宿相关费用表.
	 * 4、返回一个EiInfo.
	 *
	 * @Title:updateExtraCharges
	 * @Param EiInfo
	 * @return:EiInfo
	 */
	public EiInfo updateExtraCharges(EiInfo inInfo) {
		/*
		 * 1、获取inInfo传来的参数.
		 */
		String roomId = inInfo.getString("roomId");   /*宿舍id*/
		String manId = inInfo.getString("manId");   /*人员id*/
		String currentMonth = inInfo.getString("currentMonth");   /*当前年月*/
		String extraCharges = inInfo.getString("extraCharges");     /* 额外费用 */
		/*
		 * 2、新建一个map用来存放获取的数据.
		 */
		Map<String, Object> map = new HashMap<>();
		map.put("roomId",roomId);
		map.put("manId",manId);
		map.put("currentMonth",currentMonth);
		map.put("extraCharges",NumberUtils.createBigDecimal(extraCharges));
		/*
		 * 3、以map作为参数执行 DMFY01.updateExtraCharges 进行数据的更新，更新所选月份的费用信息中的额外费用到人员住宿相关费用表.
		 */
		dao.update("DMFY01.updateExtraCharges", map);
		/*
		 * 4、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.setMsg("操作成功");
		return outInfo;
	}


	/**
	 * 查看是否存在该用户，若存在则查出改用户的所有入住相关信息
	 * 1、 获取传来的工号。
	 * 2、新建一个map用来存放获取的数据.
	 * 3、调用DMFY01.queryIsExistManInfo  查询是否存在该用户的相关入住信息。
	 * 4、返回操作结果
	 *
	 * @Title:isExistManInfo
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	public EiInfo isExistManInfo(EiInfo inInfo) {
		/*
		 * 1、 获取传来的工号。
		 */
		String manNo = inInfo.getString("manNo");
		/*
		 * 2、新建一个map用来存放获取的数据.
		 */
		Map<String, Object> map = new HashMap<>();
		map.put("manNo",manNo);
		//数据查询
		/*
		 * 3、调用DMFY01.queryIsExistManInfo  查询是否存在该用户的相关入住信息。
		 */
		List<Map<String, Object>> list = dao.query("DMFY01.queryIsExistManInfo", map);
		// 判断是否存在，存在则返回对象
		/*
		 * 4、返回操作结果
		 */
		if(list != null && list.size() > 0){
			inInfo.setRows("manInfo", list);
			return inInfo;
		} else {
			return inInfo;
		}
	}

	/**
	 * 查询该用户当前月份是否存在的相关费用信息.
	 * 1、获取传来的参数值。
	 * 2、新建一个map用来存放获取的数据.
	 * 3、调用DMFY01.queryIsExistFee  查询该用户当前月份是否存在的相关费用信息。
	 * 4、返回操作结果
	 *
	 * @Title:queryIsExistFee
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	public EiInfo queryIsExistFee(EiInfo inInfo) {
		/*
		 * 1、获取传来的参数值。
		 */
		String roomId = inInfo.getString("roomId");
		String manId = inInfo.getString("manId");
		String currentMonth = inInfo.getString("currentMonth");
		/*
		 * 2、新建一个map用来存放获取的数据.
		 */
		Map<String, Object> map = new HashMap<>();
		map.put("roomId",roomId);
		map.put("manId",manId);
		map.put("currentMonth",currentMonth);
		//数据查询
		/*
		 * 3、调用DMFY01.queryIsExistFee  查询该用户当前月份是否存在的相关费用信息。
		 */
		int count = super.count("DMFY01.queryIsExistFee", map);
		// 判断是否存在，存在则返回对象
		/*
		 * 4、返回操作结果
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.set("count",count);
		return outInfo;
	}

	/**
	 * 通过excel批量插入费用信息
	 * @Title: saveBatch
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo saveBatch(EiInfo inInfo){
		List list = (List) inInfo.get("list");
		dao.insert("DMFY01.batchInsertByExcel", list);
		return inInfo;
	}


	/**
	 * 定时任务：每月月初自动新增上个月产生的宿舍费用。
	 * @Title: autoInsertFee
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo autoInsertFee(EiInfo inInfo){
		// 调用DMFY01.queryNoFeeManList sql查询在上个月是否存在费用数据的宿舍人员列表。          且年费为0的职工费用记录。（年费不为0为学生费用记录）
		List<Map<String,String>> listInfo = dao.query("DMFY01.queryNoFeeManList",null);

		if(listInfo != null && listInfo.size() > 0){

		}
		return inInfo;
	}

}

