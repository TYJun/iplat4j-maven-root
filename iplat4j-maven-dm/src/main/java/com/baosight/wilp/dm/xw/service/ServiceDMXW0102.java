package com.baosight.wilp.dm.xw.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 宿舍入住申请信息编辑子页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMXW0102.java
 * @ClassName: ServiceDMXW0102
 * @Package：com.baosight.wilp.dm.xw.service
 * @author: xiezilong
 * @date: 2022年04月30日 上午10:00:00
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMXW0102 extends ServiceBase {
	
	/**
	 * 一、页面加载.
	 *
	 * @Title: initLoad
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		// 调用本地服务DMXW01.queryXWApplyInfo 查询入住申请的详情信息.
        inInfo.set(EiConstant.serviceName, "DMXW01");
        inInfo.set(EiConstant.methodName, "queryXWApplyInfo");
        EiInfo outInfo =XLocalManager.call(inInfo);
        return outInfo;
    }

	/**
	 * 	宿舍入住申请信息编辑.
	 *  1、获取当前用户信息.
	 * 	2、调用本地服务DMRZ0102.updateRZApplyInfo将入住申请信息更新到人员入住申请表中.
	 *  3、返回操作结果.
	 *
	 * @Title: update
	 * @param inInfo
	 * @return
	 * @see ServiceBase#update(EiInfo)
	 */
	public EiInfo update(EiInfo inInfo) {
		/*
		 * 1、获取当前用户信息.
		 */
		// 获取当前登陆工号
		String loginName= StringUtils.isBlank((String)inInfo.get("workNo"))?
				UserSession.getUser().getUsername():(String)inInfo.get("workNo");
		Map<String, Object> userInfo = DMUtils.getUserInfo(loginName);
		// 获取人员入住申请表对应id
		String manId = inInfo.getString("manId");
		inInfo.set("id", manId);
		// 修改人人工号
		inInfo.set("recRevisor", loginName);
		/*
		 * 2、调用本地服务DMRZ0102.updateRZApplyInfo将入住申请信息更新到人员入住申请表中.
		 */
		// 将工单信息插入宿舍信息表中
		inInfo.set(EiConstant.serviceName, "DMXW0102");
		inInfo.set(EiConstant.methodName, "updateXWApplyInfo");
		EiInfo outInfo = XLocalManager.call(inInfo);
		/*
		 * 3、返回操作结果.
		 */
		outInfo.addMsg("操作成功");
		outInfo.setMsgKey("200");
		return outInfo;
	}

	/**
	 * 宿舍入住信息更新接口.
	 * 对参数处理，然后保存到数据库.
	 * 1、获取inInfo传来的参数.
	 * 2、新建一个map用来存放获取的数据.
	 * 3、以map作为参数执行 DMRZ0102.updateRZApplyInfo 进行数据的更新，更新人员入住申请表.
	 * 4、返回一个EiInfo.
	 *
	 * @Title: updateRZApplyInfo
	 * @param:  @param inInfo
	 *      manId ：主键
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
	 *      recRevisor ： 修改人工号
	 *      recReviseName : 修改人
	 *      recReviseTime : 修改时间
	 * @return: EiInfo
	 * @throws
	 */
	public EiInfo updateXWApplyInfo(EiInfo inInfo) {
		/*
		 * 1、获取inInfo传来的参数.
		 */
		String manId = inInfo.get("manId") == null ? "" : inInfo.getString("manId");   /*主键*/
		String manNo = inInfo.get("manNo") == null ? "" : inInfo.getString("manNo");     /* 工号 */
		String manName = inInfo.get("manName") == null ? "" : inInfo.getString("manName");     /* 姓名*/
		String sex = inInfo.get("sex") == null ? "" : inInfo.getString("sex");     /* 性别 */
		String age = inInfo.get("age") == null ? "" : inInfo.getString("age");       /* 年龄*/
		String phone = inInfo.get("phone") == null ? "" : inInfo.getString("phone");     /* 电话*/
		String note = inInfo.get("note") == null ? "" : inInfo.getString("note");     /* 备注信息*/
		String recRevisor = inInfo.get("recRevisor") == null ? UserSession.getUser().getUsername() : inInfo.getString("recRevisor");        /* 修改人工号*/
		Map<String, Object> createUserInfo = DMUtils.getUserInfo(recRevisor);
		String recRevisorName =  createUserInfo== null ? "" : createUserInfo.get("name").toString(); /*修改人名称*/
		String recReviseTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 修改时间*/
		/*
		 * 2、新建一个map用来存放获取的数据.
		 */
		Map<String, String> map = new HashMap<>();

		map.put("manId",manId);
		map.put("manNo",manNo);
		map.put("manName",manName);
		map.put("sex",sex);
		map.put("age",age);
		map.put("phone",phone);
		map.put("note",note);
		map.put("recRevisor",recRevisor);
		map.put("recRevisorName",recRevisorName);
		map.put("recReviseTime",recReviseTime);
		/*
		 * 3、以map作为参数执行 DMXW0102.updateXWApplyInfo 进行数据的更新，更新人员入住申请表.
		 */
		dao.insert("DMXW0102.updateXWApplyInfo", map);
		/*
		 * 4、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.set("manId", manId);
		return outInfo;
	}
	
}
