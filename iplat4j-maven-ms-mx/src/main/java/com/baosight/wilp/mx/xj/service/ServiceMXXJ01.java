package com.baosight.wilp.mx.xj.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;
import java.util.*;

/**
 * 智能监控点位数据与巡检手抄数据联动页面Service。
 *
 * @Title: ServiceMXXJ01.java
 * @ClassName: ServiceMXXJ01
 * @Package：com.baosight.wilp.mx.xj.service
 * @author: fangzekai
 * @date: 2022年10月28日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceMXXJ01 extends ServiceBase{

	/**
	 * 一、智能监控点位数据与巡检手抄数据查询页面加载.
	 *
	 * @Title: initLoad
	 * @param inInfo
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}


	@SuppressWarnings("unchecked")
	@Override
	public EiInfo query(EiInfo inInfo) {
		// 调用本地服务MXXJ01.queryDataInfoList()方法进行列表数据查询.
		inInfo.set(EiConstant.serviceName, "MXXJ01");
		inInfo.set(EiConstant.methodName, "queryDataInfoList");
		EiInfo outInfo = XLocalManager.call(inInfo);
		return outInfo;
	}

	public EiInfo queryDataInfoList(EiInfo inInfo) {
		/*
		 * 1、将要查询的参数组成数组并调用工具类转换参数
		 */
		String[] param = {"startTime", "endTime","typeChoose","spotName"};
		List<String> plist = Arrays.asList(param);
		// 调用工具类转换参数
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);

		/*
		 * 2、将构建好的map传入MXXJ01.queryDataInfoList进行查询并分页，同时查询列表数量.
		 *    判断列表对象是否存在，存在则构建返回对象.
		 */
		//查询
		List<Map<String, Object>> list = dao.query("MXXJ01.queryDataInfoList",map,
				Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		int count = super.count("MXXJ01.queryDataInfoListCount",map);
		// 判断是否存在，存在则构建返回对象
		if(list != null && list.size() > 0){
			return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			inInfo.setMsg("没有查询到数据。");
			return inInfo;
		}
	}

}

