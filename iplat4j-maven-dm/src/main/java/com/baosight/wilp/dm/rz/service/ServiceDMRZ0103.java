package com.baosight.wilp.dm.rz.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 宿舍入住申请信息详情子页面service
 * 一、页面加载.
 * 
 * @Title: ServiceDMRZ0103.java
 * @ClassName: ServiceDMRZ0103
 * @Package：com.baosight.wilp.dm.rz.service
 * @author: fangzekai
 * @date: 2022年02月08日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMRZ0103 extends ServiceBase {
	
	/**
	 * 一、页面加载.
	 *
	 * 调用本地服务DMRZ01.queryRZApplyInfo 查询入住申请的详情信息.
	 *
	 * @Title: initLoad
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		// 调用本地服务DMRZ01.queryRZApplyInfo 查询入住申请的详情信息.
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "queryRZApplyInfo");
        EiInfo outInfo =XLocalManager.call(inInfo);
        return outInfo;
    }
}
