package com.baosight.wilp.dm.fm.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;

/**
 * TODO(这里用一句话描述这个方法的作用)
 * 
 * @Title: ServiceDMFM10.java
 * @ClassName: ServiceDMFM10
 * @Package：com.baosight.wilp.dm.fm.service
 * @author: 辉
 * @date: 2021年11月24日 下午3:58:45
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDMFM10 extends ServiceBase{
	
	private static final Lock lock = new ReentrantLock();
	
	public EiInfo initLoad(EiInfo info) {
		return info;
	}

	// 地点(公用)
	public EiInfo querySpot(EiInfo info) {
		//获取参数
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "spot");
		//数据查询
		List<Map<String, Object>> list = dao.query("DMFM10.querySpot", map, (Integer)map.get("offset"), (Integer)map.get("limit"));
		int count = dao.count("DMFM10.querySpot", map);
		//返回
		if(list != null && list.size() > 0){
			return CommonUtils.BuildOutEiInfo(info, "spot", CommonUtils.createBlockMeta(list.get(0)), list, count);
		} else { 
			return info; 
		}
	}
	
	
}
