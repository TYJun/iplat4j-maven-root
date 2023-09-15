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
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 宿舍分配管理子页面批量分配service
 * 一、页面加载.
 *
 * @Title: ServiceDMFP0201.java
 * @ClassName: ServiceDMFP0201
 * @Package：com.baosight.wilp.dm.fp.service
 * @author: fangzekai
 * @date: 2022年02月08日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMFP0201 extends ServiceBase{

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
		return inInfo;
	}

	/**
	 * 对多个人员进行整体分配宿舍。
	 * 1、获取登陆相关信息以及前端传来的数据。
	 * 2、对前端传来的列表数据进行解析，先分挨个人，再对挨个人进行多个宿舍分配。
	 * 	接着调用DMFP0101.insertBeSelect 将可选房间信息插入到宿舍临时表中。
	 * 3、调用本地服务DMRZ01.batchUpdateStatusCode批量更新人员信息表状态，使状态进入待选房状态.
	 * 4、调用本地服务DMRZ01.batchUpdateLCStatusCode批量更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
	 * 5、调用本地服务DMRZ01.batchInsertLCInfo将分配流程批量插入宿舍操作流程历史表中.
	 *
	 * @Title: batchInsert
	 * @Param EiInfo
	 * @return:  EiInfo
	 */
	public EiInfo batchInsert(EiInfo inInfo) {
		/*
		 * 1、获取登陆相关信息以及前端传来的数据。
		 */
		// 获取登录人和操作时间
		String loginNum = UserSession.getLoginName();
		String loginName = UserSession.getLoginCName();
		String operationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		// 获取前端传来的数据
		/*
		 * 2、对前端传来的列表数据进行解析，先分挨个人，再对挨个人进行多个宿舍分配。
		 *    接着调用DMFP0101.insertBeSelect 将可选房间信息插入到宿舍临时表中。
		 */
		String manId = (String) inInfo.get("manIdList");// 前端拿到的manId 申请人id
		String keepRoomDays = inInfo.getString("keepRoomDays");// 前端拿到的keepRoomDays 保留天数
		// 取出宿舍选择列表
		List<Map<String, String>> roomSelectList = (List<Map<String, String>>)inInfo.get("roomSelectList");
		if (roomSelectList.isEmpty()) {
			inInfo.setMsg("未添加宿舍选择信息");
			inInfo.setStatus(-1);
			return inInfo;
		}
		// 对获取的值进行判空和以逗号隔开做长度判断，当为lenght>1是则该值为多个数组组成。
		if (StringUtils.isNotBlank(manId) && manId.split(",").length > 1) {
			// 以一个数组去存分割后的字符串。
			String[] manIdTotal = manId.split(",");
			// 遍历该数组的长度。
			for (int i = 0; i < manIdTotal.length; i++) {
				// 循环插入分配的房间信息
				List<Map<String, Object>> allItems = new LinkedList<>();
				int z = i;
				for (int y = 0; y < roomSelectList.size(); y++) {
					Map<String,Object> pMap = new HashMap<>();
					pMap.put("id", UUID.randomUUID().toString());
					pMap.put("manId", manIdTotal[z]);
					pMap.put("roomId",roomSelectList.get(y).get("roomId"));
					pMap.put("keepRoomDays", keepRoomDays);
					pMap.put("recCreator", loginNum);
					pMap.put("recCreateName", loginName);
					pMap.put("recCreateTime", operationTime);
					allItems.add(pMap);
				}
				dao.insert("DMFP0101.insertBeSelect", allItems);
			}
		}else if(StringUtils.isNotBlank(manId)){
			// 循环插入分配的房间信息
			List<Map<String, Object>> allItems = new LinkedList<>();
			for (int y = 0; y < roomSelectList.size(); y++) {
				Map<String,Object> pMap = new HashMap<>();
				pMap.put("id", UUID.randomUUID().toString());
				pMap.put("manId", manId);
				pMap.put("roomId",roomSelectList.get(y).get("roomId"));
				pMap.put("keepRoomDays", keepRoomDays);
				pMap.put("recCreator", loginNum);
				pMap.put("recCreateName", loginName);
				pMap.put("recCreateTime", operationTime);
				allItems.add(pMap);
			}
			dao.insert("DMFP0101.insertBeSelect", allItems);
		}
		/*
		 * 3、调用本地服务DMRZ01.batchUpdateStatusCode批量更新人员信息表状态，使状态进入待选房状态.
		 */
		inInfo.set("statusCode", "02");
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "batchUpdateStatusCode");
		EiInfo outInfo = XLocalManager.call(inInfo);
		inInfo.set("operator", loginNum);
		/*
		 * 4、调用本地服务DMRZ01.batchUpdateLCStatusCode批量更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
		 */
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "batchUpdateLCStatusCode");
		outInfo = XLocalManager.call(inInfo);
		/*
		 * 5、调用本地服务DMRZ01.batchInsertLCInfo将分配流程批量插入宿舍操作流程历史表中.
		 */
		// 将分配流程批量插入宿舍操作流程历史表中
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "batchInsertLCInfo");
		outInfo = XLocalManager.call(inInfo);
		/*
		 * 6、返回操作结果。
		 */
		outInfo.setMsgKey("200");
		outInfo.addMsg("分配成功");
		return outInfo;
	}

}

