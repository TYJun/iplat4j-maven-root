package com.baosight.wilp.dm.xx.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 宿舍信息管理宿舍编辑子页面service
 * 一、页面加载.
 * 
 * @Title: ServiceDMXX0102.java
 * @ClassName: ServiceDMXX0102
 * @Package：com.baosight.wilp.dm.xx.service
 * @author: fangzekai
 * @date: 2022年02月08日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMXX0102 extends ServiceBase {
	
	/**
	 * 一、页面加载.
	 *
	 * @Title: initLoad
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo initLoad(EiInfo inInfo) {
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
		List<Map<String, String>> list = dao.query("DMXX01.queryRoomInfo", map);
		List list1 = dao.query("DMXX01.queryRoomInfoMat",map);
		inInfo.setAttr(list.get(0));
		inInfo.setRows("roomDetailInfo",list);
		inInfo.setRows("results", list1);
		return inInfo;
    }

	public EiInfo query(EiInfo inInfo){
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
		// 执行DMXX01.queryRoomInfoMat查询宿舍物资列表
		List list1 = dao.query("DMXX01.queryRoomInfoMat",map,offset, limit);
		int count = dao.count("DMXX01.roomMatCount",map);
		inInfo.setRows("results", list1);
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
		return inInfo;
	}

	/**
	 * 	宿舍信息编辑.
	 *  1、获取当前用户信息.
	 * 	2、调用本地服务DMXX0102.updateDMInfoTable将宿舍信息更新到宿舍信息表中.
	 * 	3、调用DMXX0101.deletePicture将原宿舍照片删除.
	 * 	4、调用本地服务DMXX0101.savePicture将宿舍照片插入宿舍的登记图片中.
	 *  5、返回操作结果.
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
		EiInfo outInfo = new EiInfo();
		// 获取宿舍对应id
		String roomId = inInfo.getString("roomId");
		inInfo.set("id", roomId);
		// 修改人人工号
		inInfo.set("recRevisorNo", loginName);
		// 查询当前编辑宿舍的床位数和剩余床位数量.
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("roomId", roomId);
		List<Map<String, Integer>> bedNumList = dao.query("DMXX01.getBedNumByRoomId",queryMap);
		// 获取总床位数.
		Integer bedNum = bedNumList.get(0).get("bedNum");
		// 获取剩余床位数.
		Integer remainingBedNum = bedNumList.get(0).get("remainingBedNum");
		// 获得前端编辑的床位数量
		String editBedNum = inInfo.get("bedNum") == null ? "" : inInfo.getString("bedNum");       /* 床位数*/
		Integer editBedNumInt = Integer.parseInt(editBedNum);
		// 前端编辑的床位数 - 原来的床位数 的差值。 （等于0为无编辑该床位数字段.）
		int differenceValue = editBedNumInt - bedNum;
		// 查询已经入住的人员数量.
		int inRoomValue = bedNum - remainingBedNum;
		queryMap.put("differenceValue", differenceValue);
		// 判断前端编辑的数量不能小于已经入住的人员数
		if (((editBedNumInt - inRoomValue) >= 0)) {
			// 更新该编辑宿舍的剩余床位数
			dao.update("DMXX0102.updateRemainingBedNum",queryMap);
		}else {
			outInfo.addMsg("操作失败，当前宿舍所编辑的床位数不能少于已经入住的人员数量.");
			outInfo.setMsgKey("201");
			return outInfo;
		}
		/*
		 * 2、调用本地服务DMXX0102.updateDMInfoTable将宿舍信息更新到宿舍信息表中.
		 */
		// 将工单信息插入宿舍信息表中
		inInfo.set(EiConstant.serviceName, "DMXX0102");
		inInfo.set(EiConstant.methodName, "updateDMInfoTable");
		outInfo = XLocalManager.call(inInfo);
		/*
		 * 3、调用DMXX0101.deletePicture将原宿舍照片删除.
		 */
//		dao.delete("DMXX0101.deletePicture", roomId); //删除原宿舍照片
		/*
		 * 4、调用本地服务DMXX0101.savePicture将宿舍照片插入宿舍的登记图片中.
		 */
		inInfo.set(EiConstant.serviceName, "DMXX0101");
		inInfo.set(EiConstant.methodName, "savePicture");
		outInfo = XLocalManager.call(inInfo);
		/*
		 * 5、返回操作结果.
		 */
		outInfo.addMsg("操作成功");
		outInfo.setMsgKey("200");

		return outInfo;
	}

	/**
	 * 宿舍信息表更新接口.
	 * 对参数处理，然后保存到数据库.
	 * 1、获取inInfo传来的参数.
	 * 2、新建一个map用来存放获取的数据.
	 * 3、以map作为参数执行 DMXX0102.updateDMInfoTable 进行数据的更新，更新宿舍信息表.
	 * 4、返回一个EiInfo.
	 *
	 * @Title: updateDMInfoTable
	 * @param:  @param inInfo
	 *      roomId ：主键
	 *      building  : 宿舍楼
	 *      floor  : 宿舍层
	 *      roomNo  : 宿舍号
	 *      roomName : 宿舍总称(楼+层+宿舍号)
	 *      bedNum  : 床位数
	 *      typeCode : 房间类型(1男生宿舍/0女生宿舍)
	 *      dormPosition : 宿舍位置：院内、院外
	 *      dormArea  : 房屋面积："<50㎡"、"50-100㎡"、">100㎡"
	 *      priBathroom : 独立卫生间：有、没有
	 *      rent  : 房租
	 *      manageFee : 管理费
	 *      annualFee : 年费
	 *      note : 备注信息
	 *      recRevisorNo ： 修改人工号
	 *      recRevisorName : 修改人
	 *      recReviseTime : 修改时间
	 * @return: EiInfo
	 * @throws
	 */
	public EiInfo updateDMInfoTable(EiInfo inInfo) {
		/*
		 * 1、获取inInfo传来的参数.
		 */
		String roomId = inInfo.get("roomId") == null ? "" : inInfo.getString("roomId");   /*主键*/
		String building = inInfo.get("building") == null ? "" : inInfo.getString("building");     /* 宿舍楼*/
		String floor = inInfo.get("floor") == null ? "" : inInfo.getString("floor");     /* 宿舍层 */
		String roomNo = inInfo.get("roomNo") == null ? "" : inInfo.getString("roomNo");     /* 宿舍号*/
		String roomName = inInfo.get("roomName") == null ? "" : inInfo.getString("roomName");     /* 宿舍总称(楼+层+宿舍号) */
		String bedNum = inInfo.get("bedNum") == null ? "" : inInfo.getString("bedNum");       /* 床位数*/
		String typeCode = inInfo.get("typeCode") == null ? "" : inInfo.getString("typeCode");     /* 宿舍类型（男/女生宿舍）*/
		String openRoom = inInfo.get("openRoom") == null ? "" : inInfo.getString("openRoom");     /* 是否开放选房(0: 未开放，1:已开放)*/
		String dormProperties = inInfo.get("dormProperties") == null ? "" : inInfo.getString("dormProperties");     /* 宿舍属性（学生宿舍/职工宿舍）*/
		String elevatorRoom = inInfo.get("elevatorRoom") == null ? "" : inInfo.getString("elevatorRoom");        /* 是否为电梯房*/
		String priBathroom = inInfo.get("priBathroom") == null ? "" : inInfo.getString("priBathroom");        /* 是否有独立卫生间*/
		String dormPosition = inInfo.get("dormPosition") == null ? "" : inInfo.getString("dormPosition");     /* 宿舍位置*/
		String dormArea = inInfo.get("dormArea") == null ? "" : inInfo.getString("dormArea");      /* 宿舍大概面积*/
		String rent = inInfo.get("rent") == null ? "0" : inInfo.getString("rent");       /* 房租*/
		String manageFee = inInfo.get("manageFee") == null ? "0" : inInfo.getString("manageFee");        /* 管理费*/
		String annualFee = inInfo.get("annualFee") == null ? "0" : inInfo.getString("annualFee");        /* 年费*/
		String note = inInfo.get("note") == null ? "" : inInfo.getString("note");     /* 宿舍备注信息*/
		String recRevisorNo = inInfo.get("recRevisorNo") == null ? UserSession.getUser().getUsername() : inInfo.getString("recRevisorNo");        /* 修改人工号*/
		Map<String, Object> createUserInfo = DMUtils.getUserInfo(recRevisorNo);
		String recRevisorName =  createUserInfo== null ? "" : createUserInfo.get("name").toString(); /*修改人名称*/
		String recReviseTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 修改时间*/
		/*
		 * 2、新建一个map用来存放获取的数据.
		 */
		Map<String, String> map = new HashMap<>();

		map.put("roomId",roomId);
		map.put("building",building);
		map.put("floor",floor);
		map.put("roomNo",roomNo);
		map.put("roomName",roomName);
		map.put("bedNum",bedNum);
		map.put("typeCode",typeCode);
		map.put("openRoom",openRoom);
		map.put("dormProperties",dormProperties);
		map.put("elevatorRoom",elevatorRoom);
		map.put("priBathroom",priBathroom);
		map.put("dormPosition",dormPosition);
		map.put("dormArea",dormArea);
		map.put("rent",rent);
		map.put("manageFee",manageFee);
		map.put("annualFee",annualFee);
		map.put("note",note);
		map.put("recRevisorNo",recRevisorNo);
		map.put("recRevisorName",recRevisorName);
		map.put("recReviseTime",recReviseTime);
		/*
		 * 3、以map作为参数执行 DMXX0102.updateDMInfoTable 进行数据的更新，更新宿舍信息表.
		 */
		dao.insert("DMXX0102.updateDMInfoTable", map);

		//获取列表数据
		List<Map<String,String>> rows =(List<Map<String,String>>)inInfo.get("htb");
		for (Map<String,String> map1:rows) {
			if (StringUtils.isBlank(map1.get("id"))){
				//赋值map
				map1.put("dormId", roomId);
				//插入项目
				dao.insert("DMXX0102.insertdormMarInsert", map1);
			}else{
				//赋值map
				map1.put("dormId", roomId);
				//插入项目
				dao.insert("DMXX0102.updatedormMarInsert", map1);
			}
		}

		/*
		 * 4、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.set("roomId", roomId);
		return outInfo;
	}
	
}
