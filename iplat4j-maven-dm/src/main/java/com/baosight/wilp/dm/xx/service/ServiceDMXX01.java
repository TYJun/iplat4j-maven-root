/**
 *@Name ServiceDMRM.java
 *@Description TODO
 *@Date 2021年5月2日 下午7:19:55
 *@Version 1.0
 **/

package com.baosight.wilp.dm.xx.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 宿舍信息管理页面service
 *
 * @Title: ServiceDMXX01.java
 * @ClassName: ServiceDMXX01
 * @Package：com.baosight.wilp.dm.xx.service
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMXX01 extends ServiceBase{
	/**
	 * 页面初始化加载
	 * @Title: initLoad
	 * @param inInfo
	 * @return
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
	    return query(inInfo);
	}

	/**
	 * 宿舍信息管理页面查询方法
	 * 逻辑处理
	 *    1.获取当前登录人信息，如果登录人不存在，提示错误信息
	 *    2.将要查询的参数组成数组并调用工具类转换参数
	 *    3.调用DMXX01.queryRoomList查询宿舍列表
	 *    4.判断是否存在，存在则构建返回对象 并设置状态和返回信息
	 *
	 * @Title: query
	 * @param inInfo
	 * @return
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
		 * 2. 将要查询的参数组成数组并调用工具类转换参数
		 */
		String[] param = {"building", "floor", "roomNo", "typeCode" ,"dormProperties","openRoom"};
		List<String> plist = Arrays.asList(param);
		// 调用工具类转换参数
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
		/*
		 * 3. 将构建好的map传入DMXX01.queryRoomList进行查询并分页，同时查询列表数量
		 *    判断列表对象是否存在，存在则构建返回对象。
		 */
		// 执行DMXX01.queryRoomList查询宿舍列表
		List<Map<String, Object>> list = dao.query("DMXX01.queryRoomList",map,
				Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		int count = super.count("DMXX01.roomListCount",map);
		// 判断是否存在，存在则构建返回对象
		if(list != null && list.size() > 0){
			return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return inInfo;
		}
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
	public EiInfo queryRoomInfo(EiInfo inInfo) {

		// 初始化页面总数
		int offset, limit;
		// 判断是否分页
		if (inInfo.getBlocks().isEmpty()) {
			offset = 0;
			limit = 10;
		} else {
			EiBlock result = (EiBlock) inInfo.getBlocks().get("results");
			offset = (Integer) result.getAttr().get("offset");
			limit = (Integer) result.getAttr().get("limit");
		}

		/*
		 * 1、获取前端传来的roomId值.
		 */
		String roomId = "";
		if(inInfo.get("roomId") != null || !"".equals(inInfo.get("roomId"))) {
			roomId = inInfo.getString("roomId");
		}
		/*
		 * 2、将roomId值放入map给DMXX01.queryRoomInfo 做参数去查询宿舍详情信息.
		 */
		Map<String, String> map = new HashMap<>();
		map.put("roomId", roomId);
		EiInfo outInfo = new EiInfo();
		List<Map<String, String>> list = dao.query("DMXX01.queryRoomInfo", map);
		// 执行DMXX01.queryRoomInfoMat查询宿舍物资列表
		List list1 = dao.query("DMXX01.queryRoomInfoMat",map,offset, limit);
		int count = dao.count("DMXX01.roomMatCount",map);


		/*
		 * 3、判断是否取得数据.
		 */
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}
		inInfo.setRows("results", list1);
		outInfo.setAttr(list.get(0));
		outInfo.setRows("roomDetailInfo",list);
		outInfo.setRows("results", list1);
		// 处理分页
		EiBlock result = (EiBlock) inInfo.getBlocks().get("results");
		result.setAttr(new HashMap<>());
		if(result.getAttr().isEmpty()){
			Map<String,Object> rAttr = new HashMap<>();
			rAttr.put("count", count);
			rAttr.put("offset", offset);
			rAttr.put("limit", limit);
			rAttr.put("orderBy", "");
			rAttr.put("showCount", "true");
			result.setAttr(rAttr);
		} else {
			result.getAttr().put("count", count);
		}
		return outInfo;
	}

	/**
	 * 删除宿舍信息
	 * @param inInfo
	 * @return
	 */
    public EiInfo deleteDrom(EiInfo inInfo) {
		String roomId = inInfo.getString("roomId");
		dao.delete("DMXX01.deleteDrom",roomId);
		EiInfo outInfo = new EiInfo();
		outInfo.set("roomId", roomId);
		return outInfo;
	}

	/**
	 * 减少宿舍剩余床位操作 -1.
	 * 根据指定id主键更新剩余床位.
	 * 1、获取inInfo传来的参数.
	 * 2、新建一个map用来存放获取的数据.
	 * 3、以map作为参数执行 DMXX0102.updateDMInfoTable 更新宿舍的剩余床位.
	 * 4、返回一个EiInfo.
	 *
	 * @Title: decreaseDormBedNum
	 * @param:  @param inInfo
	 *      roomId : 宿舍信息表ID
	 *
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 *
	 */
	public EiInfo decreaseDormBedNum(EiInfo inInfo) {
		/*
		 * 1、获取inInfo传来的参数.
		 */
		String roomId = inInfo.getString("roomId");
		Integer remainingBedNum = inInfo.getInt("remainingBedNum") - 1;
		/*
		 * 2、新建一个map用来存放获取的数据.
		 */
		Map<String, Object> map = new HashMap<>();

		map.put("roomId", roomId);
		map.put("remainingBedNum", remainingBedNum);
		/*
		 * 3、以map作为参数执行 DMXX0102.updateDMInfoTable 更新宿舍的剩余床位.
		 */
		dao.update("DMXX0102.updateDMInfoTable", map);
		/*
		 * 4、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.set("roomId", roomId);
		outInfo.setMsg("操作成功");
		return outInfo;
	}

	/**
	 * 增加宿舍剩余床位操作 +1.
	 * 根据指定id主键更新剩余床位.
	 * 1、获取inInfo传来的参数.
	 * 2、新建一个map用来存放获取的数据.
	 * 3、以map作为参数执行 DMXX0102.updateDMInfoTable 更新宿舍的剩余床位.
	 * 4、返回一个EiInfo.
	 *
	 * @Title: increaseDormBedNum
	 * @param:  @param inInfo
	 *      roomId : 宿舍信息表ID
	 *
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 *
	 */
	public EiInfo increaseDormBedNum(EiInfo inInfo) {
		/*
		 * 1、获取inInfo传来的参数.
		 */
		String roomId = inInfo.getString("roomId");
		Integer remainingBedNum = inInfo.getInt("remainingBedNum") + 1;
		/*
		 * 2、新建一个map用来存放获取的数据.
		 */
		Map<String, Object> map = new HashMap<>();

		map.put("roomId", roomId);
		map.put("remainingBedNum", remainingBedNum);
		/*
		 * 3、以map作为参数执行 DMXX0102.updateDMInfoTable 更新宿舍的剩余床位.
		 */
		dao.update("DMXX0102.updateDMInfoTable", map);
		/*
		 * 4、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.set("roomId", roomId);
		outInfo.setMsg("操作成功");
		return outInfo;
	}


	/**
	 * 增加宿舍剩余床位操作.
	 * 根据指定id主键更新剩余床位.
	 * 1、对接收的参数 roomId字符串 进行一个处理.
	 * 	接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作.
	 * 2、以map作为参数执行 DMXX0102.batchUpdateDormBedNum 更新宿舍的剩余床位.
	 * 3、返回一个EiInfo.
	 *
	 * @Title: batchIncreaseDormBedNum
	 * @param:  @param inInfo
	 *      roomId : 宿舍信息表ID
	 *
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 *
	 */
	public EiInfo batchIncreaseDormBedNum(EiInfo inInfo) {
		/*
		 * 1、对接收的参数 roomId字符串 进行一个处理.
		 *  接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作.
		 */
		// 先实例化 roomIdList。
		List<Map<String, Object>> roomIdList = new LinkedList<>();
		// 获取参数的值。
		String roomId = inInfo.getString("roomIdList");
		// 接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作。
		if (StringUtils.isNotBlank(roomId) && roomId.split(",").length > 1) {
			// 以一个数组去存分割后的字符串。
			String[] roomIdArray = roomId.split(",");
			//实例化一个Map<String,String>类型的roomIdInfo，用来接收拆出来的id。
			Map<String, Integer> roomIdInfo = new HashMap<String, Integer>();
			// 遍历该数组的长度,寻找重复的roomId个数。
			for (int i = 0; i < roomIdArray.length; i++) {
				if (roomIdInfo.get(roomIdArray[i]) != null) {
					Integer num = (Integer) roomIdInfo.get(roomIdArray[i]);
					num++;
					roomIdInfo.put(roomIdArray[i], num);
				} else {
					roomIdInfo.put(roomIdArray[i], 1);
				}
			}
			for (Map.Entry entry : roomIdInfo.entrySet()){
				// 实例化一个Map<String,String>类型的idInfo，用来接收拆出来的id。
				Map<String, Object> aboutInfo = new HashMap<>();
				aboutInfo.put("id",entry.getKey());
				aboutInfo.put("num",entry.getValue());
				// 将接收到数据的map添加到roomIdList列表中。
				roomIdList.add(aboutInfo);
			}
		// 处理lenght<1，即当获取的值为一个值的情况。
		}else if(StringUtils.isNotBlank(roomId)){
			// 实例化一个Map<String,String>类型的idInfo，用来接收单独的id。
			Map<String, Object> roomIdInfo = new HashMap<>();
			roomIdInfo.put("id", roomId);
			roomIdInfo.put("num", 1);
			// 将接收到数据的map添加到roomIdList列表中。
			roomIdList.add(roomIdInfo);
		}
		/*
		 * 2、以map作为参数执行 DMXX0102.batchUpdateDormBedNum 更新宿舍的剩余床位.
		 */
		dao.update("DMXX0102.batchUpdateDormBedNum", roomIdList);
		/*
		 * 3、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.set("roomId", roomId);
		outInfo.setMsg("操作成功");
		return outInfo;
	}



	/**
	 * 获取用户角色信息
	 * 1、获取inInfo传来的参数，同时放入pMap中.
	 * 2、以pMap作为参数执行 DMXX01.getUserRole 进行工单状态信息的查询.
	 * 3、将获取的list信息作为role置于 EiInfo 中并返回.
	 *
	 * @Title: getUserRole
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	public EiInfo getUserRole(EiInfo info) {
		/*
		 * 1、获取inInfo传来的参数，同时放入pMap中.
		 */
		Map<String, String> pMap = new HashMap<>();
		// 通过PlatApplicationContext 获取 platSchema 属性的值。
		String platSchema = PlatApplicationContext.getProperty("platSchema");
		pMap.put("workNo",info.getString("workNo"));
		pMap.put("dataGroupCode",info.getString("dataGroupCode"));
		pMap.put("platSchema",platSchema);
		/*
		 * 2、以pMap作为参数执行 DMXX01.getUserRole 进行工单状态信息的查询.
		 */
		List<Map<String, String>> list = dao.query("DMXX01.getUserRole",pMap);
		EiInfo outInfo = new EiInfo();
		/*
		 * 3、将获取的list信息作为role置于 EiInfo 中并返回.
		 */
		outInfo.set("role", list == null || list.size() == 0 ? null : list);
		return outInfo;
	}


	/**
	 * 此方法用于宿舍大屏查询所有宿舍相关信息 --- 楼
	 *
	 * @Title: queryRoomBuildingInfo
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	public EiInfo queryRoomBuildingInfo(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		Map<String,String> map = new HashMap<>();
		String building = inInfo.getString("building");
		map.put("building",building);
		List<Map<String, String>> list = dao.query("DMXX01.queryRoomBuildingInfo", map);
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}
		outInfo.setAttr(list.get(0));
		outInfo.setRows("buildingInfo",list);
		return outInfo;
	}

	/**
	 * 此方法用于宿舍大屏查询所有宿舍相关信息 --- 层
	 *
	 * @Title: queryFloorByBuilding
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	public EiInfo queryFloorByBuilding(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		Map<String,String> map = new HashMap<>();
		String building = inInfo.getString("building");
		map.put("building",building);
		List<Map<String, String>> list = dao.query("DMXX01.queryFloorByBuilding", map);
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}
		outInfo.setAttr(list.get(0));
		outInfo.setRows("floorInfo",list);
		return outInfo;
	}


	/**
	 * 此方法用于宿舍大屏查询所有宿舍相关信息 --- 宿舍信息
	 *
	 * @Title: queryFloorRoom
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	public EiInfo queryFloorRoom(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		Map<String,String> map = new HashMap<>();
		String building = inInfo.getString("building");
		String floor = inInfo.getString("floor");
		map.put("building",building);
		map.put("floor",floor);
		List<Map<String, String>> list = dao.query("DMXX01.queryFloorRoom", map);
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}
		outInfo.setAttr(list.get(0));
		outInfo.setRows("roomInfo",list);
		return outInfo;
	}


	/**
	 * 此方法用于宿舍大屏查询所有宿舍相关信息 --- 入住信息
	 *
	 * @Title: queryDetailRZInfo
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	public EiInfo queryDetailRZInfo(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		Map<String,String> map = new HashMap<>();
		String roomId = inInfo.getString("roomId");
		map.put("roomId",roomId);
		List<Map<String, String>> list = dao.query("DMXX01.queryDetailRZInfo", map);
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}
		outInfo.setAttr(list.get(0));
		outInfo.setRows("detailInfo",list);
		return outInfo;
	}

}

