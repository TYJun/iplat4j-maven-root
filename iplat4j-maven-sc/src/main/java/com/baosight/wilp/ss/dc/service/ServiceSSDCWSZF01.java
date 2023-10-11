package com.baosight.wilp.ss.dc.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ss.ac.domain.SSACTscWorkOrderBill;
import com.baosight.wilp.ss.bm.utils.LocalServiceUtil;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import com.baosight.wilp.ss.dc.domain.SSDCWSZF01;
import com.baosight.wilp.ss.dc.domain.SSDCZQZF01;

import java.util.HashMap;
import java.util.List;


/**
 * 
 * ServicePSBMDDZF01病员订单作废service
 * 
 * @Title: ServiceSSDCWSZF01.java
 * @ClassName: ServiceSSDCWSZF01
 * @Package：com.baosight.wilp.ss.dc.service
 * @author: liutao
 * @date: 2021年9月9日 下午3:32:18
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSDCWSZF01 extends ServiceBase{
	

    /**
     * 
     * initLoad 初始化方法
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSDCZQZF01());
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
		
		//增加主表 resultBlock 块
		EiBlock resultBlock = initLoad.addBlock(EiConstant.resultBlock);
		resultBlock.addBlockMeta(new SSDCZQZF01().eiMetadata);
		//增加明细表 result2 块
		EiBlock result2 = initLoad.addBlock("result2");
		result2.addBlockMeta(new SSDCZQZF01().eiMetadata);
		return initLoad;
	}


	/**
	 * query 查询待处理数据
	 * @param inInfo
	 * @return
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "SSDCWSZF01.query", new SSDCWSZF01());
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
	 * @date: 2021年9月9日 下午3:36:12
	 */
	public EiInfo query2(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "SSDCWSZF01.query2", new SSDCWSZF01(),false, null, EiConstant.queryBlock, "result2", "result2");
		return outInfo;
	}
	
	
	/**
	 * 
     * confirmCancelOrder 确认作废方法
     * 入参：订单主键id，作废原因rejectReason
     * 出餐：EiInfo(status：状态，msg：信息)
     *  1.获得提交的订单id查询订单信息，若未查询到信息则返回status=-1,msg=未查询到订单信息
        2.将订单作废状态变更为2(确认作废)状态，订单状态变更为99(已结束)
	 *
	 * @Title: confirmCancelOrder 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:36:37
	 */
	public EiInfo confirmCancelOrder(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			//接收数据
			String id = StringUtil.toString(inInfo.getAttr().get("id"));
			String rejectReason = StringUtil.toString(inInfo.getAttr().get("rejectReason"));
			
			if(StringUtil.isNotEmpty(id)) {
				HashMap<String,String> param = new HashMap<String, String>();
				param.put("id", id);
				param.put("rejectReason", rejectReason);
				/**1.查询订单信息*/
				HashMap<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("sql","SSACTscWorkOrderBill.queryByBillId");
				paramMap.put("str",id);
				EiInfo callQueryPatient = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
				List<SSACTscWorkOrderBill> list = (List<SSACTscWorkOrderBill>) callQueryPatient.get("result");
				if(list.size() > 0) {
					//订单存在
					EiInfo info = new EiInfo();
					param.put("rejectCode","2");
					param.put("statusCode","99");
					info.addBlock("result").addRow(param);
					/**2.更新订单作废状态*/
					outInfo = super.update(info, "SSACTscWorkOrderBill.confirmCancelOrder");
					outInfo.setMsg("更新成功！");
				}else {
					inInfo.setMsg("查询订单信息失败！");
					inInfo.setStatus(-1);
					return inInfo;
				}
			}else {
				inInfo.setMsg("数据提交失败！");
				inInfo.setStatus(-1);
				return inInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			inInfo.setMsg("更新异常！");
			inInfo.setStatus(-1);
			return inInfo;
		}
		return outInfo;
	}
}
