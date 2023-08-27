package com.baosight.wilp.cs.pj.service;


import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;

/**
 * 保洁评价查询详情子页面Service.
 * 一、页面加载.
 * 
 * @Title: ServiceCSPJ0102.java
 * @ClassName: ServiceCSPJ0102
 * @Package：com.baosight.wilp.cs.pj.service
 * @author: fangzekai
 * @date: 2021年11月26日 上午10:29:19
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCSPJ0102 extends ServiceBase {

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
