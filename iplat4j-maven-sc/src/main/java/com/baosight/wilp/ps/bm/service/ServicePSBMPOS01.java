package com.baosight.wilp.ps.bm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ps.bm.domain.PSBMPosGl01;


/**
 * 
 * ServicePSBMPOS01 病员POS订单管理service
 * 
 * @Title: ServicePSBMPOS01.java
 * @ClassName: ServicePSBMPOS01
 * @Package：com.baosight.wilp.ps.bm.service
 * @author: liutao
 * @date: 2021年9月9日 上午10:22:37
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePSBMPOS01 extends ServiceBase{
	

    /**
     * 
     * initLoad 初始化方法
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new PSBMPosGl01());
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		//canteenData:食堂名称
		paramMap.put("userId", UserSession.getLoginName());
		paramMap.put("canteenTypeNum","bhst");
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
		//增加主表 resultBlock 块
		EiBlock resultBlock = initLoad.addBlock(EiConstant.resultBlock);
		resultBlock.addBlockMeta(new PSBMPosGl01().eiMetadata);
		//增加明细表 result2 块
		EiBlock result2 = initLoad.addBlock("result2");
		result2.addBlockMeta(new PSBMPosGl01().eiMetadata);
		return initLoad;
	}

	/**
	 * 
     * query 查询待处理数据
	 * @param inInfo
	 * @return
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "PSBMPosGl01.query", new PSBMPosGl01());
		return outInfo;
	}
	
	/**
	 * 
     * query2 查询已处理数据
	 *
	 * @Title: query2 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:23:57
	 */
	public EiInfo query2(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "PSBMPosGl01.query2", new PSBMPosGl01(),false, null, EiConstant.queryBlock, "result2", "result2");
		return outInfo;
	}
}
