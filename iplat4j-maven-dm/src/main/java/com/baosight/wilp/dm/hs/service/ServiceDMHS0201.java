package com.baosight.wilp.dm.hs.service;

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
 * 宿舍换宿审核子页面直接分配service
 * 一、页面加载.
 *
 * @Title: ServiceDMHS0201.java
 * @ClassName: ServiceDMHS0201
 * @Package：com.baosight.wilp.dm.hs.service
 * @author: fangzekai
 * @date: 2022年03月15日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMHS0201 extends ServiceBase{

	/**
	 * 一、宿舍换宿审核子页面直接分配页面加载.
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
	 * 对当前换宿对象直接指派一个房间进行入住，不提供选房。
	 * 1、获取当前用户信息.
	 * 2、调用本地服务DMHS02.insertHistoryTable将换宿前的相应房间编号以及床位、换宿申请时间、换宿备注插入到人员换宿历史表中进行一个记录。
	 * 3、换宿用户被直接分配房间之后，原住对应的宿舍床位数+1
	 * 4.取出宿舍选择列表，从分配房间这个只有一个元素的列表中把新分配的roomId获取。
	 * 5.调用本地服务DMXX01.decreaseDormBedNum，将分配完的宿舍床位数-1
	 * 6、调用本地服务DMRZ01.updateStatusCode更新人员信息表状态03.
	 * 7、调用本地服务DMRZ01.updateLCStatusCode更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
	 * 8、调用本地服务DMRZ01.insertLCInfo将申请流程插入宿舍操作流程历史表中.
	 *
	 * @Title: directInsert
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	public EiInfo directInsert(EiInfo inInfo) {
		/*
		 * 1、获取当前用户信息.
		 */
		// 获取当前登陆工号
		String loginName= StringUtils.isBlank((String)inInfo.get("workNo"))?
				UserSession.getUser().getUsername():(String)inInfo.get("workNo");
		Map<String, Object> userInfo = DMUtils.getUserInfo(loginName);
		EiInfo outInfo = new EiInfo();
		// 操作人工号
		inInfo.set("operator", loginName);
		inInfo.set("statusCode", "03");

// ----- 分配前的处理流程开始↓
		/*
		 * 2、调用本地服务DMHS02.insertHistoryTable将换宿前的相应房间编号以及床位、换宿申请时间、换宿备注插入到人员换宿历史表中进行一个记录。
		 */
		inInfo.set("drmId", inInfo.getString("roomManId"));
		inInfo.set(EiConstant.serviceName, "DMHS02");
		inInfo.set(EiConstant.methodName, "insertHistoryTable");
		outInfo = XLocalManager.call(inInfo);
		/*
		 * 3、换宿用户被直接分配房间之后，原住对应的宿舍床位数+1
		 */
		inInfo.set(EiConstant.serviceName, "DMXX01");
		inInfo.set(EiConstant.methodName, "increaseDormBedNum");
		outInfo = XLocalManager.call(inInfo);
// ----- 分配前的处理流程结束↑


// ----- 分配后的处理流程开始↓
		/**
		 * 4.取出宿舍选择列表，从分配房间这个只有一个元素的列表中把新分配的roomId获取。
		 */
		List<Map<String, String>> roomSelectList = (List<Map<String, String>>)inInfo.get("roomSelectList");
		if (roomSelectList.isEmpty()) {
			inInfo.setMsg("未添加宿舍选择信息");
			inInfo.setStatus(-1);
			return inInfo;
		}
		// 从分配房间这个只有一个元素的列表中把新分配的roomId获取。
		String roomId = roomSelectList.get(0).get("roomId");
		// 获取新的宿舍的床位号，对inInfo的数据部分进行数据重设。
		String bedNo = (getBedNo(roomId)).toString();     /* 床位号*/
		inInfo.set("id", inInfo.getString("roomManId"));
		inInfo.set("bedNo", bedNo);
		inInfo.set("roomId", roomId);
		inInfo.set("actualInDate", "");
		inInfo.set("changeRoomStatus", "1");
		inInfo.set("evalStatus", "0");
		inInfo.set("actualRent", roomSelectList.get(0).get("rent"));
		inInfo.set("actualManageFee", roomSelectList.get(0).get("manageFee"));
		inInfo.set("annualFee", roomSelectList.get(0).get("annualFee"));
		inInfo.set(EiConstant.serviceName, "DMQR0101");
		inInfo.set(EiConstant.methodName, "updateDormRoomMan");
		outInfo = XLocalManager.call(inInfo);
		/*
		 * 直接分配完之后，对应的宿舍床位数-1
		 */
		String remainingBedNum = roomSelectList.get(0).get("remainingBedNum");
		inInfo.set("remainingBedNum", remainingBedNum);

		/**
		 * 5.调用本地服务DMXX01.decreaseDormBedNum，将分配完的宿舍床位数-1
		 */
		inInfo.set(EiConstant.serviceName, "DMXX01");
		inInfo.set(EiConstant.methodName, "decreaseDormBedNum");
		outInfo = XLocalManager.call(inInfo);
		/*
		 * 6、调用本地服务DMRZ01.updateStatusCode更新人员信息表状态03.
		 */
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "updateStatusCode");
		outInfo = XLocalManager.call(inInfo);
		/*
		 * 7、调用本地服务DMRZ01.updateLCStatusCode更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
		 */
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "updateLCStatusCode");
		outInfo = XLocalManager.call(inInfo);
		/*
		 * 8、调用本地服务DMRZ01.insertLCInfo将申请流程插入宿舍操作流程历史表中.
		 */
		// 将待入住流程插入宿舍操作流程历史表中
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "insertLCInfo");
		outInfo = XLocalManager.call(inInfo);
// ----- 分配后的处理流程结束↑

		outInfo.setMsgKey("200");
		outInfo.addMsg("直接分配成功");
		return outInfo;
	}


	/**
	 * 自动分配床位号
	 * @Title: getBedNo
	 * @param roomId
	 * @return 0(该房间床位已分配完)
	 */
	private Integer getBedNo(String roomId) {
		Map<String, String> map=new HashMap<>();
		map.put("roomId", roomId);
		// 获取该房间已使用床位编号
		List<Map<String, String>> list = dao.query("DMXX01.countUsedBed",map);
		// 获取该房间总床位数
		List<Map<String, Integer>> bedNumList = dao.query("DMXX01.getBedNumByRoomId",map);
		Integer bedNum = bedNumList.get(0).get("bedNum");
		int step = 1;
		for (int i = 0; i < list.size(); i++,step++) {
			if((Integer.valueOf(list.get(i).get("usedBed"))) == step) {
				continue;
			}else {
				break;
			}
		}
		if(step <= bedNum) {
			return step;
		}else {
			return 0;
		}
	}

}

