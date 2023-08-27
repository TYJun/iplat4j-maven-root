package com.baosight.wilp.cs.cx.service;


import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;

/**
 * 保洁综合查询详情子页面Service
 * 一、页面加载.
 * 
 * @Title: ServiceCSCX0101.java
 * @ClassName: ServiceCSCX0101
 * @Package：com.baosight.wilp.cs.cx.service
 * @author: fangzekai
 * @date: 2021年11月26日 下午2:14:08
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCSCX0101 extends ServiceBase {
	
	/**
	 * 一、页面加载.
	 * @Title: initLoad
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		// 调用本地服务CSRE01.queryTaskInfo 查询工单的详情信息.
        inInfo.set(EiConstant.serviceName, "CSRE01");
        inInfo.set(EiConstant.methodName, "queryTaskInfo");
        EiInfo outInfo =XLocalManager.call(inInfo);
        return outInfo;
    }
	
}
