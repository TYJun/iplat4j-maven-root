package com.baosight.wilp.dm.xw.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 宿舍入住申请子界面Service.
 * 一、页面加载.
 *
 * @Title: ServiceDMXW0101.java
 * @ClassName: ServiceDMXW0101
 * @Package：com.baosight.wilp.dm.xw.service
 * @author: xiezilong
 * @date: 2022年04月29日 上午11:00:00
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMXW0101 extends ServiceBase {
	
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
		// 调用DMXW01.queryRZApplyManNoList获取入住申请表中已存在入住信息的所有人（工号列表）
		List<Map<String, String>> manNolist = dao.query("DMXW01.queryXWApplyManNoList", null);
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
			 * 3、调用本地服务DMXW0101.insertDMRZInfoTable将入住申请信息插入人员入住信息表中.
			 */
			// 将申请信息插入宿舍信息表中
			inInfo.set(EiConstant.serviceName, "DMXW0101");
			inInfo.set(EiConstant.methodName, "insertDMXWInfoTable");
			outInfo = XLocalManager.call(inInfo);
			/*
			 * 4、返回操作结果.
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
	 * 3、以map作为参数执行 DMXW0101.insertDMXWInfoTable 进行数据的插入，插入人员入住信息表.
	 * 4、返回一个EiInfo.
	 *
	 * @Title: insertDMXWInfoTable
	 * @param:  @param inInfo
	 *      id ：主键
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
	public EiInfo insertDMXWInfoTable(EiInfo inInfo) {
		/*
		 * 1、获取inInfo传来的参数.
		 */
		String id = inInfo.get("id") == null ? "" : inInfo.getString("id");   /*主键*/
		String manNo = inInfo.get("manNo") == null ? "" : inInfo.getString("manNo");     /* 工号 */
		String manName = inInfo.get("manName") == null ? "" : inInfo.getString("manName");     /* 姓名*/
		String sex = inInfo.get("sex") == null ? "" : inInfo.getString("sex");     /* 性别 */
		String age = inInfo.get("age") == null ? "" : inInfo.getString("age");       /* 年龄*/
		String phone = inInfo.get("phone") == null ? "" : inInfo.getString("phone");     /* 电话*/
		String note = inInfo.get("note") == null ? "" : inInfo.getString("note");     /* 当前居住地*/
		String recCreator = inInfo.get("recCreator") == null ? UserSession.getUser().getUsername() : inInfo.getString("recCreator");        /* 创建人工号*/
		Map<String, Object> createUserInfo = DMUtils.getUserInfo(recCreator);
		String recCreateName =  createUserInfo== null ? "" : createUserInfo.get("name").toString(); /*创建人名称*/
		String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 创建时间*/
		/*
		 * 2、新建一个map用来存放获取的数据.
		 */
		Map<String, String> map = new HashMap<>();

		map.put("id",id);
		map.put("manNo",manNo);
		map.put("manName",manName);
		map.put("sex",sex);
		map.put("age",age);
		map.put("phone",phone);
		map.put("note",note);
		map.put("recCreator",recCreator);
		map.put("recCreateName",recCreateName);
		map.put("recCreateTime",recCreateTime);
		/*
		 * 3、以map作为参数执行 DMXW0101.insertDMRZInfoTable 进行数据的插入，插入人员入住信息表.
		 */
		dao.insert("DMXW0101.insertDMXWInfoTable", map);
		/*
		 * 4、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.set("id", id);
		return outInfo;
	}

}
