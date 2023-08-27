package com.baosight.wilp.dm.xx.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;

/**
 * 宿舍信息管理查询宿舍详情子页面service
 * 一、页面加载.
 * 
 * @Title: ServiceDMXX0103.java
 * @ClassName: ServiceDMXX0103
 * @Package：com.baosight.wilp.dm.xx.service
 * @author: fangzekai
 * @date: 2022年02月08日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMXX0103 extends ServiceBase {
	
	/**
	 * 一、页面加载.
	 *
	 * @Title: initLoad
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		// 调用本地服务DMXX01.queryRoomInfo 查询宿舍的详情信息.
        inInfo.set(EiConstant.serviceName, "DMXX01");
        inInfo.set(EiConstant.methodName, "queryRoomInfo");
        EiInfo outInfo =XLocalManager.call(inInfo);
        return outInfo;
    }
	
}
