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
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.dm.common.DMUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 宿舍分配管理子页面分配service
 * 一、页面加载.
 *
 * @Title: ServiceDMFP0101.java
 * @ClassName: ServiceDMFP0101
 * @Package：com.baosight.wilp.dm.fp.service
 * @author: fangzekai
 * @date: 2022年02月08日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMFP0101 extends ServiceBase{

	/**
	 * 一、宿舍分配管理子页面分配查询页面加载.
	 *
	 * @Title: initLoad
	 * @param inInfo
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo){
		// 调用本地服务DMRZ01.queryRZApplyInfo 查询入住申请的详情信息.
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "queryRZApplyInfo");
		EiInfo outInfo =XLocalManager.call(inInfo);
		return outInfo;
	}

	/**
	 * 宿舍分配管理新增
	 * 逻辑处理
	 *    1.获取登录人和操作时间和前端数据
	 *    2.取出宿舍选择列表 循环插入分配的房间信息
	 *    3.调用本地服务DMRZ01.updateLCStatusCode更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态
	 *    4.调用本地服务DMRZ01.insertLCInfo将申请流程插入宿舍操作流程历史表中
	 *    5.将申请流程插入宿舍操作流程历史表中
	 *    6.设置状态和返回信息 返回inInfo
	 *
	 * @Title: insert
	 * @param inInfo
	 * @return
	 */
	public EiInfo insert(EiInfo inInfo) {
		// 获取登录人和操作时间
		String loginNum = UserSession.getLoginName();
		String loginName = UserSession.getLoginCName();
		String operationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		// 获取前端传来的数据
		String manId = (String)inInfo.get("manId");// 前端拿到的manId 申请人id
		String keepRoomDays = inInfo.getString("keepRoomDays");// 前端拿到的keepRoomDays 保留天数
		// 取出宿舍选择列表
		List<Map<String, String>> roomSelectList = (List<Map<String, String>>)inInfo.get("roomSelectList");
		if (roomSelectList.isEmpty()) {
			inInfo.setMsg("未添加宿舍选择信息");
			inInfo.setStatus(-1);
			return inInfo;
		}
		// 循环插入分配的房间信息
		List<Map<String, String>> allItems = new LinkedList<>();
		roomSelectList.forEach(roomSelect -> {
			roomSelect.put("id", UUID.randomUUID().toString());
			roomSelect.put("manId", manId);
			roomSelect.put("keepRoomDays", keepRoomDays);
			roomSelect.put("recCreator", loginNum);
			roomSelect.put("recCreateName", loginName);
			roomSelect.put("recCreateTime", operationTime);
			allItems.add(roomSelect);
		});
		dao.insert("DMFP0101.insertBeSelect", allItems);
		Map<String, String> map = new HashMap<>();
		map.put("manId",manId);
		map.put("statusCode","02");
		dao.update("DMRZ01.updateStatusCode", map);
		inInfo.set("operator", loginNum);
		inInfo.set("statusCode", "02");
		/*
		 * 调用本地服务DMRZ01.updateLCStatusCode更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
		 */
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "updateLCStatusCode");
		EiInfo outInfo = XLocalManager.call(inInfo);
		/*
		 * 调用本地服务DMRZ01.insertLCInfo将申请流程插入宿舍操作流程历史表中.
		 */
		// 将申请流程插入宿舍操作流程历史表中
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "insertLCInfo");
		outInfo = XLocalManager.call(inInfo);
		outInfo.setMsgKey("200");
		outInfo.addMsg("分配成功");
		return outInfo;
	}

}

