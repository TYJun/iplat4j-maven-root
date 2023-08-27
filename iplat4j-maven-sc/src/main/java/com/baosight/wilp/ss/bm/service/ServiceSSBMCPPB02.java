package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ss.bm.domain.SSBMCppb02;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 菜品排班管理service
 * 
 * @Title: ServiceSSBMCPPB02.java
 * @ClassName: ServiceSSBMCPPB02
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午3:51:05
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSBMCPPB02 extends ServiceBase {


    /**
     * 初始化查询
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSBMCppb02());
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		// canteenData:食堂名称
		paramMap.put("userId", UserSession.getLoginName());
		List<HashMap<String, Object>> listCanteenType = dao.query("SSBMStxx01.getCanteenData", paramMap);
		initLoad.addBlock("canteenData").addRows(listCanteenType);

		//menuType:菜品类型
		List<HashMap<String,Object>> listMenuType = dao.query("SSBMCpfl01.getMenuTypeData", paramMap);
		initLoad.addBlock("menuType").addRows(listMenuType);
		return initLoad;
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		// grid数据集查询
		EiInfo outInfo = super.query(inInfo, "SSBMCppb02.query", new SSBMCppb02());
		return outInfo;
	}


	/**
	 * 
     * 查询菜品类型
	 *
	 * @Title: queryMenuInfo 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:52:19
	 */
	public EiInfo queryMenuInfo(EiInfo inInfo) {
		Map attr = inInfo.getAttr();
		//menuType:菜品类型
		List<HashMap<String,Object>> listMenuType = dao.query("SSBMCpfl01.getMenuTypeData", attr);
		inInfo.addBlock("menuType").addRows(listMenuType);
		return inInfo;
	}
	
}
