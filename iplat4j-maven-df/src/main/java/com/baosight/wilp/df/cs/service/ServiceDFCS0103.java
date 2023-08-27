package com.baosight.wilp.df.cs.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
/**
 * 
 * 设备系统分类：初始化查询
 * <p>1.初始化查询 initLoad
 * 
 * @Title: ServiceDFCS0103.java
 * @ClassName: ServiceDFCS0103
 * @Package：com.baosight.wilp.df.cs.service
 * @author: zhaow
 * @date: 2021年8月10日 下午6:48:45
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFCS0103 extends ServiceBase{
	/**
	 * 
	 * @Title: initLoad
	 * @Description: 初始化查询
	 * @param info
	 * paramKey : 参数编码
	 * @return info
	 * id : 设备分类参数id
     * moduleId : 参数所属分类ID
     * paramName : 参数名称
     * paramKey : 参数编码
     * paramValue : 参数值
     * paramUnit : 参数单位
     * memo : 备注
     * classifyName : 设备分类名称
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
	    // 获取参数编码
		String paramKey = (String)info.getAttr().get("paramKey");
		// 设置参数编码
		info.set("paramKey",paramKey);
		// 调用本地服务类
		info.set(EiConstant.serviceName, "DFCS0102");
		// 调用本地方法
		info.set(EiConstant.methodName, "queryParam");
        // 获得info返回
		EiInfo outInfo =XLocalManager.call(info);
        // 返回
		return outInfo;
	}
}
