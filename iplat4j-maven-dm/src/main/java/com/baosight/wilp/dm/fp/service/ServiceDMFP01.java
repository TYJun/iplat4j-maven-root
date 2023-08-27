/**
 *@Name ServiceDHRM01.java
 *@Description 宿舍入住申请
 *@Date 2021年5月2日 下午7:19:55
 *@Version 1.0
 **/

package com.baosight.wilp.dm.fp.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.dm.common.DMUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 宿舍分配管理页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMFP01.java
 * @ClassName: ServiceDMFP01
 * @Package：com.baosight.wilp.dm.fp.service
 * @author: fangzekai
 * @date: 2022年02月08日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMFP01 extends ServiceBase{

	/**
	 * 一、宿舍分配管理查询页面加载.
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
	 * 宿舍分配页面查询人员信息
	 * 逻辑处理
	 *   1.获取当前登录人信息，如果登录人不存在，提示错误信息
	 *   2.获取当前登录人的用户角色，根据用户角色对view赋予它的角色值，以便后续查工单列表的时候做数据显示角色区分
	 *   3.获取前端的人员大类值并判断
	 *   4.调用本地服务DMRZ01.queryRZInfoList()方法进行列表数据查询
	 *
	 * @Title: query
	 * @param inInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo query(EiInfo inInfo) {
		Map<String, Object> userInfo = DMUtils.getUserInfo(null);
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
				manNature = "职工";
			}
			role = "DMADMIN";
		}else if(role.contains("DMSPR_XSSS")){
			// 为学生审批人时，查询属性隐藏，只查为学生的相关信息。
			manNature = "学生";
			role = "DMSPR_XSSS";
		}else if(role.contains("DMSPR_ZGSS")){
			// 首次加载的时候过滤条件会为null，所以得进行判定。
			if (manNature == null){
				manNature = "职工";
			}
			role = "DMSPR_ZGSS";
		} else {
			if (manNature == null){
				manNature = "职工";
			}
			role = "DMZSR";
		}
		inInfo.set("role",role);
		inInfo.set("manNature", manNature);
		// 调用本地服务DMRZ01.queryRZInfoList()方法进行列表数据查询.
		inInfo.set("statusCode", "01");
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "queryRZInfoList");
		EiInfo outInfo = XLocalManager.call(inInfo);
		return outInfo;
	}


}

