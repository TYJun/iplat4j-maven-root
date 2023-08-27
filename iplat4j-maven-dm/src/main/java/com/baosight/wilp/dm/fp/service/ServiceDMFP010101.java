/**
 *@Name ServiceDMWI0101.java
 *@Description TODO
 *@Date 2021年5月5日 下午8:27:27
 *@Version 1.0
 **/

package com.baosight.wilp.dm.fp.service;


import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.common.util.PrUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 宿舍分配管理子页面选择宿舍service
 * 一、页面加载.
 *
 * @Title: ServiceDMFP010101.java
 * @ClassName: ServiceDMFP010101
 * @Package：com.baosight.wilp.dm.fp.service
 * @author: fangzekai
 * @date: 2022年02月08日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMFP010101 extends ServiceBase{
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 查询宿舍列表
	 * 1.将要查询的参数组成数组并调用工具类转换参数
	 * 2.将构建好的map传入DMXX01.queryRoomList进行查询并分页，同时查询列表数量
	 * 	判断列表对象是否存在，存在则构建返回对象。
	 *
	 * @Title: query
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		/*
		 * 1.将要查询的参数组成数组并调用工具类转换参数
		 */
		String[] param = {"building", "floor", "roomNo", "typeCode", "dormProperties","roomName"};
		List<String> plist = Arrays.asList(param);
		// 调用工具类转换参数
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
		// 先实例化 roomIdList。
		List<Map<String, String>> roomIdList = new LinkedList<>();
		// 获取参数的值。
		String getroomIdList = (String) inInfo.get("roomIdList");
		// 对获取的值进行判空和以逗号隔开做长度判断，当为lenght>1是则该值为多个数组组成。
		if (StringUtils.isNotBlank(getroomIdList) && getroomIdList.split(",").length > 1) {
			// 以一个数组去存分割后的字符串。
			String[] roomIdTotal = getroomIdList.split(",");
			// 遍历该数组的长度。
			for (int i = 0; i < roomIdTotal.length; i++) {
				// 实例化一个Map<String,String>类型的item，用来接收拆出来的roomId。
				Map<String, String> item = new HashMap<>();
				item.put("roomId", roomIdTotal[i]);
				// 将接收到数据的map添加到roomIdList列表中。
				roomIdList.add(item);
			}
			// 处理lenght<1，即当获取的值为一个值的情况。
		}else if(StringUtils.isNotBlank(getroomIdList)){
			// 实例化一个Map<String,String>类型的item，用来接收拆出来的roomId。
			Map<String, String> item = new HashMap<>();
			item.put("roomId", getroomIdList);
			// 将接收到数据的map添加到schemeCodeList列表中。
			roomIdList.add(item);
		}
		// 将存有roomId的 roomIdList 列表再用一个map接收。
		map.put("roomIdList",roomIdList);
		/*
		 * 2.将构建好的map传入DMXX01.queryRoomList进行查询并分页，同时查询列表数量
		 *    判断列表对象是否存在，存在则构建返回对象。
		 */
		// 执行DMXX01.queryRoomList查询宿舍列表
		List<Map<String, Object>> list = dao.query("DMXX01.queryLeftRoom",map,
				Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		int count = super.count("DMXX01.leftRoomListCount",map);
		// 判断是否存在，存在则构建返回对象
		if(list != null && list.size() > 0){
			return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return inInfo;
		}
	}
}