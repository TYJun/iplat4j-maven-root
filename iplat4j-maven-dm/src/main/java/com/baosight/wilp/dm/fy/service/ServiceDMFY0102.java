package com.baosight.wilp.dm.fy.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;

/**
 * 本月费用录入页面service
 *
 *@ClassName: ServiceDMFY0102
 *@Package: com.baosight.wilp.dm.fy.service
 */
public class ServiceDMFY0102 extends ServiceBase {
	/**
	 * 此方法用于页面初始化加载
	 *
	 * 逻辑处理：
	 * 1.调用query方法
	 *
	 * @Title: initLoad
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 此方法用于查询住宿人的入住信息
	 *
	 * 逻辑处理：
	 * 1.调用本地服务DMFY01.queryDMChooseManInfo 查询人员住宿相关详情.
	 *
	 * @Title: query
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	public EiInfo query(EiInfo inInfo) {
		// 调用本地服务DMFY01.queryDMChooseManInfo 查询人员住宿相关详情.
		inInfo.set(EiConstant.serviceName, "DMFY01");
		inInfo.set(EiConstant.methodName, "queryDMChooseManInfo");
		EiInfo outInfo =XLocalManager.call(inInfo);
		return outInfo;
	}

}