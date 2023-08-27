package com.baosight.wilp.dm.rz.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.wilp.dm.common.domain.DMConstant;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 宿舍入住申请子界面Service.
 * 一、页面加载.
 * 
 * @Title: ServiceDMRZ0101.java
 * @ClassName: ServiceDMRZ0101
 * @Package：com.baosight.wilp.dm.rz.service
 * @author: fangzekai
 * @date: 2022年02月09日 下午1:56:48
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMRZ0101 extends ServiceBase {
	
	/**
	 * 一、页面加载.
	 * @Title: initLoad
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo initLoad(EiInfo inInfo) {
    	// 获取当前人信息
    	Map<String, Object> userInfo = DMUtils.getUserInfo(null);
		if(userInfo !=null){
			inInfo.set("manNo", userInfo.get("workNo"));
			inInfo.set("manName", userInfo.get("workNo"));
			inInfo.set("manName_textField", userInfo.get("name"));
			inInfo.set("deptName", userInfo.get("deptNum"));
			inInfo.set("deptName_textField", userInfo.get("deptName"));
		}
        return inInfo;
    }


	/**
	 * 二、宿舍入住申请登记.
	 *  1、判断当前申请入住的工号是否在宿舍入住申请表中已存在相应信息，是的话则返回操作失败，否的话则执行数据录入。
	 *  2、获取当前用户信息.
	 * 	3、调用本地服务DMRZ0101.insertDMRZInfoTable将入住申请信息插入人员入住信息表中.
	 * 	4、调用本地服务DMRZ01.insertLCInfo将申请流程插入宿舍操作流程历史表中.
	 *  5、返回操作结果.
	 *
	 * @Title: insert
	 * @param inInfo
	 * @return
	 * @see ServiceBase#insert(EiInfo)
	 */
	public EiInfo insert(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		/*
		 * 1、判断当前申请入住的工号是否在宿舍入住申请表中已存在相应信息，是的话则返回操作失败，否的话则执行数据录入。
		 */
		// 获得前端传来的申请入住工号manNo
		String manNo = inInfo.get("manNo") == null ? "" : inInfo.getString("manNo");     /* 工号 */
		boolean flag = false;
		// 调用DMRZ01.queryRZApplyManNoList获取入住申请表中已存在入住信息的所有人（工号列表） 流程结束(状态为99)的人除外
		List<Map<String, String>> manNolist = dao.query("DMRZ01.queryRZApplyManNoList", null);
		if(manNolist != null && manNolist.size() > 0){
			for (int i = 0;i<manNolist.size();i++){
				if (manNo.equals(manNolist.get(i).get("manNo"))){
					flag = true;
				}
			}
		}
		if (flag){
			outInfo.addMsg("操作失败，该工号已存在入住信息");
			outInfo.setMsgKey("201");
		}else {
			/*
			 2、将申请的人之前的数据信息进行归档。
			 */
			dao.update("DMRZ0101.updateArchiveFlag",manNo);
			/*
			 * 2、获取当前用户信息.
			 */
			// 获取当前登陆工号
			String loginName= StringUtils.isBlank((String)inInfo.get("workNo"))?
					UserSession.getUser().getUsername():(String)inInfo.get("workNo");
			Map<String, Object> userInfo = DMUtils.getUserInfo(loginName);
			// 生成宿舍对应id
			String id = UUID.randomUUID().toString();
			inInfo.set("id", id);
			// 创建人工号
			inInfo.set("recCreator", loginName);
			inInfo.set("operator", loginName);
			/*
			 * 3、调用本地服务DMRZ0101.insertDMRZInfoTable将入住申请信息插入人员入住信息表中.
			 */
			// 将申请信息插入宿舍信息表中
			inInfo.set(EiConstant.serviceName, "DMRZ0101");
			inInfo.set(EiConstant.methodName, "insertDMRZInfoTable");
			outInfo = XLocalManager.call(inInfo);
			/*
			 * 4、调用本地服务DMRZ01.insertLCInfo将申请流程插入宿舍操作流程历史表中.
			 */
			// 将申请流程插入宿舍操作流程历史表中
			inInfo.set("manId", id);
			inInfo.set(EiConstant.serviceName, "DMRZ01");
			inInfo.set(EiConstant.methodName, "insertLCInfo");
			outInfo = XLocalManager.call(inInfo);
			/*
			 * 5、返回操作结果.
			 */
			outInfo.addMsg("操作成功");
			outInfo.setMsgKey("200");
		}
		return outInfo;
	}


	/**
	 * 人员入住信息表保存接口.
	 * 对参数处理，然后保存到数据库.
	 * 1、获取inInfo传来的参数.
	 * 2、新建一个map用来存放获取的数据.
	 * 3、以map作为参数执行 DMRZ0101.insertDMRZInfoTable 进行数据的插入，插入人员入住信息表.
	 * 4、返回一个EiInfo.
	 *
	 * @Title: insertDMRZInfoTable
	 * @param:  @param inInfo
	 *      id ：主键
	 *      statusCode  : 工单状态(00待审核，01待分配，02待选房，03待入住，04已入住，10申请换宿，98申请退宿，99流程结束)
	 *      manNo  : 工号
	 *      manName  : 姓名
	 *      sex : 性别
	 *      age  : 员工年龄
	 *      phone : 电话
	 *      identityCard : 身份证
	 *      deptName  : 部门科室名称
	 *      deptNum : 部门科室编码
	 *      maritalStatus  : 婚否:是，否
	 *      educationBackground : 学历
	 *      academicYear  : 学年
	 *      employmentNature  : 职工属性
	 *      isNetwork : 是否联网
	 *      isStay  : 是否办暂住证
	 *      permanentResidence : 户口所在地
	 *      hiredate : 入职时间
	 *      estimatedInDate  : 预计入住时间
	 *      estimatedOutDate : 预计退房时间
	 *      note : 备注信息
	 *      recCreator ： 创建人工号
	 *      recCreateName : 创建人
	 *      recCreateTime : 创建时间
	 * @return: EiInfo
	 * @throws
	 */
	public EiInfo insertDMRZInfoTable(EiInfo inInfo) {
		/*
		 * 1、获取inInfo传来的参数.
		 */
		String id = inInfo.get("id") == null ? "" : inInfo.getString("id");   /*主键*/
		String statusCode = inInfo.get("statusCode") == null ? "" : inInfo.getString("statusCode");     /* 状态代码*/
		String manNo = inInfo.get("manNo") == null ? "" : inInfo.getString("manNo");     /* 工号 */
		String manName = inInfo.get("manName") == null ? "" : inInfo.getString("manName");     /* 姓名*/
		String sex = inInfo.get("sex") == null ? "" : inInfo.getString("sex");     /* 性别 */
		String age = inInfo.get("age") == null ? "0" : inInfo.getString("age");       /* 年龄*/
		String phone = inInfo.get("phone") == null ? "" : inInfo.getString("phone");     /* 电话*/
		String identityCard = inInfo.get("identityCard") == null ? "" : inInfo.getString("identityCard");        /* 身份证号*/
		String deptName = inInfo.get("deptName") == null ? "" : inInfo.getString("deptName");     /* 科室名称*/
		String deptNum = inInfo.get("deptNum") == null ? "" : inInfo.getString("deptNum");      /* 科室编码*/
		String maritalStatus = inInfo.get("maritalStatus") == null ? "" : inInfo.getString("maritalStatus");       /* 婚否*/
		String spouseName = inInfo.get("spouseName") == null ? "" : inInfo.getString("spouseName");       /* 婚否*/
		String educationBackground = inInfo.get("educationBackground") == null ? "" : inInfo.getString("educationBackground");        /* 学历*/
		String academicYear = inInfo.get("academicYear") == null ? "" : inInfo.getString("academicYear");     /* 学年*/
		String employmentNature = inInfo.get("employmentNature") == null ? "" : inInfo.getString("employmentNature");      /* 职工属性*/
		String isNetwork = inInfo.get("isNetwork") == null ? "" : inInfo.getString("isNetwork");       /* 是否联网*/
		String isStay = inInfo.get("isStay") == null ? "" : inInfo.getString("isStay");        /* 是否办理暂住证*/
		String permanentResidence = inInfo.get("permanentResidence") == null ? "" : inInfo.getString("permanentResidence");     /* 户口所在地*/
		String hiredate = inInfo.get("hiredate") == null ? "" : inInfo.getString("hiredate");      /* 入职时间*/
		String estimatedInDate = inInfo.get("estimatedInDate") == null ? "" : inInfo.getString("estimatedInDate");       /* 预计入住时间*/
		String estimatedOutDate = inInfo.get("estimatedOutDate") == null ? "" : inInfo.getString("estimatedOutDate");        /* 预计退宿时间*/
		String note = inInfo.get("note") == null ? "" : inInfo.getString("note");     /* 备注信息*/
		String recCreator = inInfo.get("recCreator") == null ? UserSession.getUser().getUsername() : inInfo.getString("recCreator");        /* 创建人工号*/
		Map<String, Object> createUserInfo = DMUtils.getUserInfo(recCreator);
		String recCreateName =  createUserInfo== null ? "" : createUserInfo.get("name").toString(); /*创建人名称*/
		String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 创建时间*/
		/*
		 * 2、新建一个map用来存放获取的数据.
		 */
		Map<String, String> map = new HashMap<>();

		map.put("id",id);
		map.put("statusCode",statusCode);
		map.put("manNo",manNo);
		map.put("manName",manName);
		map.put("sex",sex);
		map.put("age",age);
		map.put("phone",phone);
		map.put("identityCard",identityCard);
		map.put("deptName",deptName);
		map.put("deptNum",deptNum);
		map.put("maritalStatus",maritalStatus);
		map.put("spouseName",spouseName);
		map.put("educationBackground",educationBackground);
		map.put("academicYear",academicYear);
		map.put("employmentNature",employmentNature);
		map.put("isNetwork",isNetwork);
		map.put("isStay",isStay);
		map.put("permanentResidence",permanentResidence);
		map.put("hiredate",hiredate);
		map.put("estimatedInDate",estimatedInDate);
		map.put("estimatedOutDate",estimatedOutDate);
		map.put("note",note);
		map.put("recCreator",recCreator);
		map.put("recCreateName",recCreateName);
		map.put("recCreateTime",recCreateTime);
		/*
		 * 3、以map作为参数执行 DMRZ0101.insertDMRZInfoTable 进行数据的插入，插入人员入住信息表.
		 */
		dao.insert("DMRZ0101.insertDMRZInfoTable", map);

		//获取app编码
		String appCode = "AP00002";

		Map<String,Object> map1 = new HashMap<>();

		List<Map<String, String>> manList = dao.query("DMRZ0101.queryPopSshly",null);
		List<String> paramList = new ArrayList<String>();
		List<String> workNoList = new ArrayList<>();
		for (Map<String, String> workNo : manList) {
			workNoList.add(workNo.get("workNo"));
		}
		workNoList = workNoList.stream().distinct().collect(Collectors.toList());
		String smsTemp = "";
		smsTemp = "您收到一条来自"+manName+"入住申请，请您及时去系统上审批！";
		paramList.add(smsTemp);
		System.out.println("ren"+workNoList);
		//发送企业微信
		BaseDockingUtils.pushWxMsg(workNoList, paramList, "TP00001", appCode);

		/*
		 * 4、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.set("id", id);
		return outInfo;
	}


//	/**
//	 * 定时任务 -- 学生自动分配住宿。
//	 *
//	 * @Title: studentAutoFPDorms
//	 * @Param EiInfo
//	 * @return:  EiInfo
//	 */
//	public EiInfo studentAutoFPDorms(EiInfo inInfo){
//		/*
//		 * 1、查询住宿申请人信息中状态为待分配的人员列表。 01:待分配
//		 */
//		Map<String,Object> map = new HashMap<>();
//		List manNatureList = new ArrayList();
//		String [] XSLX = DMConstant.XSLX;
//		manNatureList = Arrays.asList(XSLX);
//		map.put("manNatureList",manNatureList);
//		List<Map<String,Object>> manList = dao.query("DMRZ0101.queryStudentWaitFPList",map);
//		/*
//		 * 2、判断学生宿舍类型空闲的宿舍以及对应的床位数。
//		 */
//		List<Map<String,Object>> freeDormsList = dao.query("DMRZ0101.queryStudentWaitFPList",null);
//		/*
//		 * 3、对空闲的床位数分配对应床号，并与当前人员数据进行一对一数据绑定。
//		 */
//		return inInfo;
//	}

	/**
	 * 定时任务 -- 离职人员在自动申请离职信息。
	 *
	 * @Title: workAutoFPDorms
	 * @Param EiInfo
	 * @return:  EiInfo
	 */
	public EiInfo workAutoFPDorms(EiInfo inInfo){

		Map<String,Object> map = new HashMap<>();

		List<Map<String,Object>> manList = dao.query("DMRZ0101.workAutoFPDorms",null);
		System.out.println("ren1"+manList);
		dao.update("DMRZ0101.updateOutInfo",manList);

		return inInfo;
	}

	/**
	 * 定时任务 -- 通知一个月内快到时间的人员发消息通知。
	 *
	 * @Title: studentAutoFPDorms
	 * @Param EiInfo
	 * @return:  EiInfo
	 */
	public EiInfo pushOutInfo(EiInfo inInfo){

		//获取app编码
		String appCode = "AP00002";

		Map<String,Object> map = new HashMap<>();

		List<String> manList = dao.query("DMRZ0101.queryPop",null);
		List<String> paramList = new ArrayList<String>();
		String smsTemp = "";
		smsTemp = "您将到退宿时间，请您近期去物业管理科办理退宿";
		paramList.add(smsTemp);
		System.out.println("ren"+manList);
		//发送企业微信
		BaseDockingUtils.pushWxMsg(manList, paramList, "TP00001", appCode);
		return inInfo;
	}

}
