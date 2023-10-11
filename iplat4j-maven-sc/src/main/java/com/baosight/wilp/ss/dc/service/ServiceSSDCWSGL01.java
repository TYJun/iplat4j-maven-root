package com.baosight.wilp.ss.dc.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ps.pc.domain.PSPCBillStatusInfo;
import com.baosight.wilp.ss.ac.domain.SSACTscWorkOrderBill;
import com.baosight.wilp.ss.bm.domain.SSBMCpxx02;
import com.baosight.wilp.ss.bm.utils.LocalServiceUtil;
import com.baosight.wilp.ss.bm.utils.RespResult;
import com.baosight.wilp.ss.bm.utils.SqlUtils;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import com.baosight.wilp.ss.dc.domain.SSDCWSGL01;
import com.baosight.wilp.ss.dc.domain.SSDCZQGL01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * ServiceSSDCZQGL01职工自取餐订单管理service
 * 
 * @Title: ServiceSSDCWSGL01.java
 * @ClassName: ServiceSSDCWSGL01
 * @Package：com.baosight.wilp.ss.dc.service
 * @author: liutao
 * @date: 2021年9月9日 下午3:31:03
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSDCWSGL01 extends ServiceBase{
	
	
    /**
     * 
     * initLoad 初始化方法
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSDCZQGL01());
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
		resultBlock.addBlockMeta(new SSDCZQGL01().eiMetadata);
		//增加明细表 result2 块
		EiBlock result2 = initLoad.addBlock("result2");
		result2.addBlockMeta(new SSDCZQGL01().eiMetadata);
		//获取小代码小票报表地址配置
		EiInfo callCode = LocalServiceUtil.callCode1("S_ED_02", "wilp.sc.jc.frUrl", "wsxp");
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)callCode.get("list");
		initLoad.addBlock("defaultDishesNum").addRows(list);
		return initLoad;
	}


	/**
	 * 
     * query 查询待处理数据的方法
	 * @param inInfo
	 * @return
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "SSDCWSGL01.query", new SSDCWSGL01());
		return outInfo;
	}

	/**
	 * 
     * query2 查询已处理数据的方法
	 *
	 * @Title: query2 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:31:40
	 */
	public EiInfo query2(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "SSDCWSGL01.query2", new SSDCWSGL01(),false, null, EiConstant.queryBlock, "result2", "result2");
		return outInfo;
	}

	/**
	 * Todo(查询订单子表菜品信息)
	 *
	 * @Title: queryMenus
	 * @author xiajunqing
	 * @date: 2022/2/10 13:50
	 * @Param inInfo
	 * @return: inInfo
	 */
	public EiInfo queryMenus(EiInfo inInfo) {
		EiInfo query = new EiInfo();
		//获取传参
		Map attr = inInfo.getAttr();
		String billNo = null;
		try {
			if(attr != null && attr.size() > 0) {
				if(attr.containsKey("billNo")) {
					billNo = StringUtil.toString(attr.get("billNo"));
				}
			}
			if(!StringUtil.isBlank(billNo)) {
				List<HashMap<String,Object>> listMenus = SqlUtils.queryForListByString(
						"SSACTscWorkOrderBillDetail.queryDetailByBillNo",billNo);
				query.addBlock("menus").addRows(listMenus);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}

	/**
	 * 
     * confirmSend 开始送餐的方法
     *  入参：订单主键id
     * 出餐：EiInfo(status：状态，msg：信息)
     *  1.获得提交的订单id查询订单信息，若未查询到信息则返回status=-1,msg=未查询到订单信息
        2.将订单状态变更为03(已完成)状态，若状态变更失败则返回status=-1,msg=订单状态变更失败
	 *
	 * @Title: confirmSend 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:31:55
	 */
	public EiInfo confirmSend(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			//接收数据
			String id = StringUtil.toString(inInfo.getAttr().get("id"));
			
			if(StringUtil.isNotEmpty(id)) {
				HashMap<String,Object> param = new HashMap<String, Object>();
				param.put("id", id);
				/**1.查询订单信息*/
				List<SSACTscWorkOrderBill> list = dao.query("SSACTscWorkOrderBill.query", param);
				if(list.size() > 0) {
					SSACTscWorkOrderBill bill = list.get(0);
					if(!"02".equals(bill.getStatusCode())) {
						inInfo.setMsg("订单状态不是已支付！");
						inInfo.setStatus(-1);
						return inInfo;
					}
					/**2.开始送餐直接更改状态*/
					PSPCBillStatusInfo billInfo = new PSPCBillStatusInfo();
					billInfo.setPboTbName("sc_work_order_bill");
					billInfo.setBeforeStatus(bill.getStatusCode());
					billInfo.setAfterStatus("03");
					billInfo.setCurrentDealer(bill.getCurrentDealer());
					billInfo.setBillId(bill.getId());
					//登录信息
					String loginName = UserSession.getLoginName();
					billInfo.setCreatorId(loginName);
					billInfo.setCreatorName("订餐信息");
					billInfo.setPboCode("MEAL");
					billInfo.setHandleAdvice("开始送餐");
					billInfo.setOprationRoute("开始送餐");
					billInfo.setRejectReason("开始送餐");
					//变更订单状态
					param.put("billInfo", billInfo);
					EiInfo callStatusChange = LocalServiceUtil.call("PSPCStatusChange", "statusChange", param);
					RespResult billStatusChangeResult = (RespResult) callStatusChange.get("result");
					System.out.println("开始送餐：" + bill.getBillNo() + billStatusChangeResult.getRespMsg());
					
					String respCode = billStatusChangeResult.getRespCode();
					if (!respCode.equals("200")) {
						//状态变更失败
						inInfo.setMsg("订单状态变更失败,"+billStatusChangeResult.getRespMsg());
					}
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
			inInfo.setMsg("更新失败！");
			inInfo.setStatus(-1);
			return inInfo;
		}
		return inInfo;
	}
	
}
