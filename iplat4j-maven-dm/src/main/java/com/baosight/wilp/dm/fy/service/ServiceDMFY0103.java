package com.baosight.wilp.dm.fy.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 个人费用详情页面service
 *
 *@ClassName: ServiceDMFY0103
 *@Package: com.baosight.wilp.dm.fy.service
 */
public class ServiceDMFY0103 extends ServiceBase {
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
	 * 此方法用于查询住宿人的入住详情信息以及费用信息
	 *
	 * 逻辑处理：
	 * 1.调用本地服务DMXX01.queryRoomInfo 查询宿舍的详情信息.
	 *
	 * @Title: query
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	public EiInfo query(EiInfo inInfo) {
		// 调用本地服务DMXX01.queryRoomInfo 查询宿舍的详情信息.
		inInfo.set(EiConstant.serviceName, "DMZH0101");
		inInfo.set(EiConstant.methodName, "queryDetailInfo");
		EiInfo outInfo =XLocalManager.call(inInfo);
		return outInfo;
	}

}