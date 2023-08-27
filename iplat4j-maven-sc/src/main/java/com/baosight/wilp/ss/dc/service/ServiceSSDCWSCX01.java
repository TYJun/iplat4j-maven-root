package com.baosight.wilp.ss.dc.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ss.dc.domain.SSDCWSCX01;
import com.baosight.wilp.ss.dc.domain.SSDCZQCX01;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * ServicePSBMDDCX01 病员订单综合查询service
 * 
 * @Title: ServiceSSDCWSCX01.java
 * @ClassName: ServiceSSDCWSCX01
 * @Package：com.baosight.wilp.ss.dc.service
 * @author: liutao
 * @date: 2021年9月9日 下午3:30:09
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSDCWSCX01 extends ServiceBase{
	

    /**
     * 
     * initLoad初始化方法
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSDCZQCX01());
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		//canteenData:食堂名称
		paramMap.put("userId", UserSession.getLoginName());
		paramMap.put("canteenTypeNum","zgst");
		List<HashMap<String,Object>> listCanteenType = dao.query("SSBMStxx01.getCanteenData", paramMap);
		initLoad.addBlock("canteenData").addRows(listCanteenType);
		//mealNum:餐次类型
		paramMap.put("mealgroupTypeCode", "mealNum");
		List<HashMap<String,Object>> listMealNum = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("mealNum").addRows(listMealNum);
		//deptData:部门数据
		List<HashMap<String,Object>> listDeptData = dao.query("PSBMDdgl01.findDept", paramMap);
		initLoad.addBlock("deptData").addRows(listDeptData);
		//floorData:楼层数据
		List<HashMap<String,Object>> listFloorData = dao.query("PSBMDdgl01.getFloors", paramMap);
		initLoad.addBlock("floorData").addRows(listFloorData);
		//buildingData:楼栋数据
		List<HashMap<String,Object>> listBuildingData = dao.query("PSBMDdgl01.getBuildings", paramMap);
		initLoad.addBlock("buildingData").addRows(listBuildingData);
		return initLoad;
	}


	/**
	 * 
     * query 查询方法
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "SSDCWSCX01.query", new SSDCWSCX01());
		return outInfo;
	}
	

}
