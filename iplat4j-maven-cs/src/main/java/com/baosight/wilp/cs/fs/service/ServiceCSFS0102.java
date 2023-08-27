package com.baosight.wilp.cs.fs.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;

/**
 * 保洁完工查询详情子页面service
 * 一、页面加载.
 * 
 * @Title: ServiceCSFS0102.java
 * @ClassName: ServiceCSFS0102
 * @Package：com.baosight.wilp.cs.fs.service
 * @author: fangzekai
 * @date: 2021年11月25日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCSFS0102 extends ServiceBase {
	
	/**
	 * 一、页面加载.
	 *
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
