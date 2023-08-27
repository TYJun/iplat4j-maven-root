package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ss.bm.domain.SSBMCppb02;
import com.baosight.wilp.ss.bm.domain.SSBMSCDD01;
import com.baosight.wilp.ss.bm.utils.ACServiceUtils;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import com.baosight.wilp.ss.bm.utils.XServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 送餐地点选择service
 * 
 * @Title: ServiceSSBMSCDD02.java
 * @ClassName: ServiceSSBMSCDD02
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
public class ServiceSSBMSCDD02 extends ServiceBase {


    /**
     * 初始化查询
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSBMSCDD01());
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		return initLoad;
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		//grid数据集查询
		EiBlock block = inInfo.getBlock("inqu_status");
		EiBlock resultBlock = inInfo.getBlock("result");
		String building = "";
		String floor = "";
		String room = "";
		try {
			if(block != null) {
				building = block.getCellStr(0, "building");
				floor = block.getCellStr(0, "floor");
				room = block.getCellStr(0, "room");
			}
			// 构建查询条件
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("building",building);
			paramMap.put("floor",floor);
			paramMap.put("room",room);
			List<HashMap<String,Object>> callResult = new ArrayList<HashMap<String,Object>>();
			List<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
			//调用服务接口通过楼和层查询地点信息
			EiInfo callSpot = ACServiceUtils.getRoom(building, floor,room);
			if(callSpot.getStatus() < 0){
				//查询失败,调用视图
				callResult = dao.query("SSBMSCDD01.queryDeptByBuildAndFloor", paramMap);
			}else {
				callResult = (List<HashMap<String, Object>>) callSpot.get("result");

				for (int i = 0; i < callResult.size(); i++) {
					HashMap<String, Object> hashMap = callResult.get(i);
					String spot_num = StringUtil.toString(hashMap.get("spot_num"));
					String spot_name = StringUtil.toString(hashMap.get("spot_name"));
					room = StringUtil.toString(hashMap.get("room"));
					paramMap.put("spotNum",spot_num);
					paramMap.put("spotName",spot_name);
					//查询科室标识生效
					EiInfo callDept = ACServiceUtils.queryDeptInfoBySpot(spot_num, spot_name);
					HashMap<String,Object> resultDept = new HashMap<String,Object>();
					if(callDept.getStatus() < 0){
						//查询失败
						callResult = dao.query("SSBMSCDD01.queryDeptByBuildAndFloor", paramMap);
					}else {
						resultDept = (HashMap<String, Object>) callDept.get("result");
						HashMap<String,Object> m = new HashMap<String,Object>();
						m.put("spotNum",spot_num);
						m.put("spotName",spot_name);
						m.put("room",room);
						m.put("deptNum",StringUtil.toString(resultDept.get("deptNum")));
						m.put("deptName",StringUtil.toString(resultDept.get("deptName")));
						result.add(m);
					}
				}
			}
			outInfo.addBlock("result").addRows(result);
			outInfo.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
