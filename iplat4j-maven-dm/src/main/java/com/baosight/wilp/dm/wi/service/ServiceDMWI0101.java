package com.baosight.wilp.dm.wi.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.PrUtils;

/**
 * TODO(这里用一句话描述这个方法的作用)
 * 
 *      1.编辑分配床位的操作
 *      2.查询宿舍床位数量
 *      
 * @Title: ServiceDMWI0101.java
 * @ClassName: ServiceDMWI0101
 * @Package：com.baosight.wilp.dm.wi.service
 * @author: 辉
 * @date: 2021年11月24日 下午2:51:39
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDMWI0101 extends ServiceBase{
    
    /**
     * TODO(编辑分配床位的操作)
     * @title getBedNo
     * @param resquest 请求入参 {Id-主键，type-按钮类型}
     * @return outInfo
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		Map<String, String> map = new HashMap<>();
		map.put("id", inInfo.getAttr().get("id").toString());
		String type = inInfo.getAttr().get("type").toString();
		if("edit".equals(type)) {
			List<Map<String, Object>> list = dao.query("dMHM01.query", map);
			if (!CollectionUtils.isEmpty(list)) {
				list.get(0).put("type", type);
				// 解决DatePicker 回显问题
				list.get(0).put("workTime", list.get(0).get("workTime").toString());
				// 转换数据
		    	list.forEach(e->{
		    		if("1".equals(e.get("isNetwork").toString())) {
		    			e.put("isNetwork", "是");
		    		}else {
		    			e.put("isNetwork", "否");
		    		}
		    		
		    		if("1".equals(e.get("isStay").toString())) {
		    			e.put("isStay", "是");
		    		}else {
		    			e.put("isStay", "否");
		    		}
		    	});
				inInfo.setAttr(list.get(0));
			}
		}
		return inInfo;
	}
	
	 /**
     * TODO(查询宿舍床位数量)
     * @title queryRoom
     * @param resquest 请求入参 {}
     * @return outInfo
     */
	public EiInfo queryRoom(EiInfo inInfo) {
		//获取参数
		Map<String, Object> map = PrUtils.buildParamter(inInfo, "inqu_status", "rooms");
		//数据查询
		@SuppressWarnings("unchecked")
		//查询宿舍信息表
		List<Map<String, Object>> list = dao.query("dMRM01.queryRoom", map, (Integer)map.get("offset"), (Integer)map.get("limit"));
		int count = dao.count("dMRM01.queryRoom", map);
		//返回
		if(list != null && list.size() > 0){
			// 过滤 剩余床位为0的宿舍 {或者去数据库里过滤}
			list.removeIf(e -> "0".equals(e.get("remainBed").toString()));
			
			return PrUtils.BuildOutEiInfo(inInfo, "rooms", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else { 
			return inInfo; 
		}
	}
}

