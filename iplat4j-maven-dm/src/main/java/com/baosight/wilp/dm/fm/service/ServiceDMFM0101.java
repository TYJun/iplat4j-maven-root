package com.baosight.wilp.dm.fm.service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.collections.CollectionUtils;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.dm.fm.common.PackageOarameters;
import com.baosight.wilp.dm.fm.domain.DMFM01;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

/**
 * TODO(新增操作：新增一条宿舍费用管理信息)
 * 
 *     1.初始化页面加载已有的宿舍的费用管理
 *     2.新增一条宿舍费用管理信息
 *     3.在页面加载过程中加载宿舍信息
 * 
 * @Title: ServiceDMFM0101.java
 * @ClassName: ServiceDMFM0101
 * @Package：com.baosight.wilp.dm.fm.service
 * @author: 辉
 * @date: 2021年11月24日 下午3:42:29
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDMFM0101 extends ServiceBase{
    
    /**
     * TODO(初始化页面加载已有的宿舍的费用管理)
     * @title initLoad
     * @param resquest 请求入参 {id-主键，type-按钮类型}
     * @return inInfo
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		Map<String, String> map = new HashMap<>();
		//传入数据id-主键，type-按钮类型
		map.put("id", inInfo.getAttr().get("id").toString());
		String type = inInfo.getAttr().get("type").toString();
		//查询宿舍费用管理信息表
		List<Map<String, String>> list = dao.query("DMFM01.queryById", map);
		//判断集合是否为空
		if (!CollectionUtils.isEmpty(list)) {
			list.get(0).put("type", type);
			inInfo.setAttr(list.get(0));
		}
		return inInfo;
		
//		inInfo.addBlock(EiConstant.resultBlock);
//		inInfo.getBlock(EiConstant.resultBlock).addBlockMeta(new DMFM01().eiMetadata);
//		return inInfo;
	
	}
	
	/**
     * TODO(新增一条宿舍费用管理信息)
     * @title insert
     * @param resquest 请求入参 {id-主键，type-按钮类型}
     * @return inInfo
     */
	@Override
	public EiInfo insert(EiInfo inInfo) {
		DMFM01 dmfm01 = new DMFM01();
		String loginName = StringUtils.isNotBlank((String) inInfo.get("loginName"))?(String) inInfo.get("loginName"): com.baosight.iplat4j.core.web.threadlocal.UserSession.getLoginName();
		Map<String,Object> map = new HashMap<>(10);
		map.put("id", UUID.randomUUID().toString());	
		dmfm01.setRoomId(inInfo.getAttr().get("roomId").toString());
		dmfm01.setRoomName(inInfo.getAttr().get("roomName").toString());
		dmfm01.setRoomNo(inInfo.getAttr().get("roomNo").toString());
		//dmfm01.setBedNum(inInfo.getAttr().get("bedNum").toString());
		dmfm01.setBuildingCode(inInfo.getAttr().get("buildingCode").toString());
		dmfm01.setFloorCode(inInfo.getAttr().get("floorCode").toString());
		//dmfm01.setDormitoryNo(inInfo.getAttr().get("dormitoryNo").toString());
		String roomId = (String) inInfo.getAttr().get("roomId");
		map.put("roomId", roomId);
		map.put("roomName", inInfo.get("roomName"));
		map.put("roomNo", inInfo.get("roomNo"));
		//map.put("bedNum", inInfo.get("bedNum"));
		map.put("buildingCode", inInfo.get("buildingCode"));
		map.put("floorCode", inInfo.get("floorCode"));
		map.put("dormitoryNo", inInfo.get("dormitoryNo"));
		map.put("rent", inInfo.get("rent"));
    	map.put("lastElec", inInfo.get("lastElec"));
    	map.put("lastWater", inInfo.get("lastWater"));
    	map.put("lastWaterfee", inInfo.get("lastWaterfee"));
    	map.put("lastElecfee",inInfo.get("lastElecfee"));
    	map.put("waterFee", inInfo.get("waterFee"));
    	map.put("elecFee", inInfo.get("elecFee"));
    	map.put("monthElec", inInfo.get("monthElec"));
    	map.put("monthWater", inInfo.get("monthWater"));
    	map.put("remark", inInfo.get("remark"));
    	map.put("createrTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    	map.put("creator", loginName);
//		dmfm01.setMonthRent(inInfo.getAttr().get("monthRent").toString());
//		dmfm01.setLastElec(inInfo.getAttr().get("lastElec").toString());
//		dmfm01.setLastWater(inInfo.getAttr().get("lastWater").toString());
//		dmfm01.setLastWaterfee(inInfo.getAttr().get("lastWaterfee").toString());
//		dmfm01.setLastElecfee(inInfo.getAttr().get("lastElecfee").toString());
//		dmfm01.setWaterFee(inInfo.getAttr().get("waterFee").toString());
//		dmfm01.setElecFee(inInfo.getAttr().get("elecFee").toString());
//		dmfm01.setMonthElec(inInfo.getAttr().get("monthElec").toString());
//		dmfm01.setMonthWater(inInfo.getAttr().get("monthWater").toString());
		dmfm01.setCreaterTime(PackageOarameters.getData());
		dmfm01.setCreator(inInfo.getAttr().get("creator").toString());
//		dmfm01.setBuiidtime(PackageOarameters.getData());
		dmfm01.setRemark(inInfo.getAttr().get("remark").toString());
		map.put("hospitalManageFee", inInfo.get("hospitalManageFee"));
		map.put("propertyManageFee", inInfo.get("propertyManageFee"));
		map.put("networkFee", inInfo.get("networkFee"));
		if (inInfo.getAttr().get("type").toString().equals("add")) {
			dmfm01.setCreaterTime(PackageOarameters.getData());
			dmfm01.setCreator(UserSession.getUser().getUsername());
			dmfm01.setId(PackageOarameters.getUUID());
			PackageOarameters.setRows(inInfo, dmfm01);
			//EiInfo outInfo = super.insert(inInfo, "DMFM01.insert");
			EiInfo outInfo = new EiInfo();
			if(roomId.equals("")) {
				outInfo.setMsg("请检查是否还有未填信息");
				return outInfo;
			} else {
				List<Map<String, String>> list = dao.query("DMFM01.quryRoomName", map);
				if (list.size() > 0) {
					outInfo.setMsg("该宿舍已存在");
					return outInfo;
				} else {
					dao.insert("DMFM01.insert", map);
					System.out.println("插入成功");
					outInfo.setMsg("插入成功");
					return outInfo;
				}
			}
		} else {
			//dmfm01.setId(inInfo.getAttr().get("id").toString());
			map.put("id", inInfo.getAttr().get("id").toString());
			PackageOarameters.setRows(inInfo, dmfm01);
			//EiInfo outInfo = super.update(inInfo, "DMFM01.update");
			EiInfo outInfo = new EiInfo();
			dao.update("DMFM01.update",map);
			outInfo.setMsg("更新成功");
			return outInfo;
		}

	}
	
	/**
     * TODO(在页面加载过程中加载宿舍信息)
     * @title queryBuildingAndFloor
     * @param resquest 请求入参 {roomId-房间信息表ID}
     * @return inInfo
     */
	public EiInfo queryBuildingAndFloor(EiInfo info) {
		Map<String, String> map = new HashMap<String, String>();
		//传入数据roomId-房间信息表ID
		map.put("roomId",info.getString("roomId"));
		//数据查询宿舍信息表
		List<Map<String, Object>> list = dao.query("DMFM10.querySpot", map);
		//获取数据roomName-宿舍名称
		info.set("roomName", list.get(0).get("roomName"));
		//床位数
		info.set("bedNum", list.get(0).get("bedNum"));
		//楼栋
		info.set("buildingCode", list.get(0).get("buildingCode"));
		//楼层
		info.set("floorCode", list.get(0).get("floorCode"));
		//房间编号
		info.set("roomNo", list.get(0).get("roomNo"));
		//租金
		info.set("rent", list.get(0).get("rent"));

		info.set("elecPriece", list.get(0).get("elecPriece"));

		info.set("waterPriece", list.get(0).get("waterPriece"));

		return info;
	}
	
}

