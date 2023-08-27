package com.baosight.wilp.dm.zh.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 宿舍综合查询详情信息子页面service
 * 一、页面加载.
 * 
 * @Title: ServiceDMZH0101.java
 * @ClassName: ServiceDMZH0101
 * @Package：com.baosight.wilp.dm.zh.service
 * @author: fangzekai
 * @date: 2022年02月15日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMZH0101 extends ServiceBase {
	
	/**
	 * 一、页面加载.
	 *
	 * @Title: initLoad
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
    }

	/**
	 * 宿舍综合查询详情信息查询方法
	 * @Title: query
	 * @param inInfo
	 * @return
	 */
	public EiInfo query(EiInfo inInfo) {
		// 调用本地服务DMZH0101.queryDetailInfo 查询宿舍的详情信息.
		inInfo.set(EiConstant.serviceName, "DMZH0101");
		inInfo.set(EiConstant.methodName, "queryDetailInfo");
		EiInfo outInfo =XLocalManager.call(inInfo);
		return outInfo;
	}

	/**
	 * 宿舍详情查询.
	 * 根据宿舍id，查询指定的宿舍.
	 * 1、获取前端传来的roomId值.
	 * 2、将roomId值放入map给DMXX01.queryRoomInfo 做参数去查询宿舍详情信息.
	 * 3、判断是否取得数据.
	 *
	 * @Title: queryDetailInfo
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
	public EiInfo queryDetailInfo(EiInfo inInfo) {
		/*
		 * 1、获取前端传来的manId值.
		 */
		String manId = "";
		if(inInfo.get("manId") != null || !"".equals(inInfo.get("manId"))) {
			manId = inInfo.getString("manId");
		}
		/*
		 * 2、将manId值放入map给DMRZ01.queryRZApplyInfo 做参数去查询入住申请的详情信息.
		 */
		Map<String, String> map = new HashMap<>();
		map.put("manId", manId);
		EiInfo outInfo = new EiInfo();
		List<Map<String, String>> list = dao.query("DMZH01.queryDetailInfo", map);
		/*
		 * 3、判断是否取得数据.
		 */
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}
		outInfo.setAttr(list.get(0));
		outInfo.setRows("detailInfo",list);
		return outInfo;
	}

	/**
	 * 查询宿舍综合查询子页面查看操作履历详情
	 * 1、获取前端传来的manId值.
	 * 2、将manId值放入map给DMRZ01.queryDetailLCInfo 做参数去查询宿舍综合查询子页面查看操作履历详情sql.
	 * 3、判断是否取得数据.
	 *
	 * @Title: queryDetailLCInfo
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	public EiInfo queryDetailLCInfo(EiInfo inInfo) {
		/*
		 * 1、获取前端传来的manId值.
		 */
		String manId = "";
		if(inInfo.get("manId") != null || !"".equals(inInfo.get("manId"))) {
			manId = inInfo.getString("manId");
		}
		/*
		 * 2、将manId值放入map给DMRZ01.queryDetailLCInfo 做参数去查询宿舍综合查询子页面查看操作履历详情sql.
		 */
		Map<String, String> map = new HashMap<>();
		map.put("manId", manId);
		EiInfo outInfo = new EiInfo();
		List<Map<String, String>> list = dao.query("DMZH01.queryDetailLCInfo", map);
		/*
		 * 3、判断是否取得数据.
		 */
		if(list !=null && list.size() > 0){
			return CommonUtils.BuildOutEiInfo(inInfo, "operationItem", CommonUtils.createBlockMeta(list.get(0)), list, list.size());
		} else {
			inInfo.setMsg("未获取到数据");
			return inInfo;
		}
	}
	
}
