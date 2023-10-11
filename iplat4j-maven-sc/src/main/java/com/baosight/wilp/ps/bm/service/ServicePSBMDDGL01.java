package com.baosight.wilp.ps.bm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ps.bm.domain.PSBMDdgl01;
import com.baosight.wilp.ps.pc.domain.PSPCBillStatusInfo;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOperation;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOrderBillPatient;
import com.baosight.wilp.ss.bm.utils.*;


/**
 * 
 * ServicePSBMDDGL01病员订单管理service
 * 
 * @Title: ServicePSBMDDGL01.java
 * @ClassName: ServicePSBMDDGL01
 * @Package：com.baosight.wilp.ps.bm.service
 * @author: liutao
 * @date: 2021年9月9日 上午10:14:12
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePSBMDDGL01 extends ServiceBase{
	
	
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
		EiInfo initLoad = super.initLoad(inInfo, new PSBMDdgl01());
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
		resultBlock.addBlockMeta(new PSBMDdgl01().eiMetadata);
		//增加明细表 result2 块
		EiBlock result2 = initLoad.addBlock("result2");
		result2.addBlockMeta(new PSBMDdgl01().eiMetadata);
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
		EiInfo outInfo = super.query(inInfo, "PSBMDdgl01.query", new PSBMDdgl01());
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
	 * @date: 2021年9月9日 上午10:15:06
	 */
	public EiInfo query2(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "PSBMDdgl01.query2", new PSBMDdgl01(),false, null, EiConstant.queryBlock, "result2", "result2");
		return outInfo;
	}
	
	/**
	 * 
     * confirmSend 开始送餐的方法
     *  入参：订单主键id
     * 出餐：EiInfo(status：状态，msg：信息)
     *  1.获得提交的订单id查询订单信息，若未查询到信息则返回status=-1,msg=未查询到订单信息
        2.将订单状态变更为03(已完成)状态，若状态变更失败则返回status=-1,msg=订单状态变更失败
        3.保存操作记录
	 *
	 * @Title: confirmSend 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:15:23
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
				List<PSPCTmealOrderBillPatient> list = dao.query("PSPCTmealOrderBillPatient.query", param);
				if(list.size() > 0) {
					PSPCTmealOrderBillPatient bill = list.get(0);
					if(!"02".equals(bill.getStatusCode())) {
						inInfo.setMsg("订单状态不是已支付！");
						inInfo.setStatus(-1);
						return inInfo;
					}
					/**2.开始送餐直接更改状态*/
					PSPCBillStatusInfo billInfo = new PSPCBillStatusInfo();
					billInfo.setPboTbName("sc_order_bill_patient");
					billInfo.setBeforeStatus(bill.getStatusCode());
					billInfo.setAfterStatus("03");
					billInfo.setCurrentDealer(bill.getCurrentDealer());
					billInfo.setBillId(bill.getId());
					//登录信息
					String loginName = UserSession.getLoginName();
					billInfo.setCreatorId(loginName);
					billInfo.setCreatorName("订餐信息");
					billInfo.setPboCode("PATIENT_MEAL");
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
					}else{
						/**3.添加（病患）送餐操作记录时间*/
						String billNo = bill.getBillNo();
						String username = bill.getUserName();
						String recCreator = bill.getRecCreator();
						PSPCTmealOperation operateRecord = new PSPCTmealOperation();
						operateRecord.setId(UUIDUtils.getUUID32());
						operateRecord.setOperationTime(CalendarUtils.dateTimeFormat(new Date()));
						operateRecord.setCreatorName(username);
						operateRecord.setCreatorId(recCreator);
						operateRecord.setBillNo(billNo);
						operateRecord.setOperationRoute("04");
						param.put("sql","PSPCTmealOperation.insert");
						param.put("domain",operateRecord);
						EiInfo callInsertOperation = LocalServiceUtil.call("SSBMTy", "insertSqlByDomain", param);
						System.out.println("订单消费记录保存Operation:"+Boolean.parseBoolean(StringUtil.toString(callInsertOperation.get("success"))));
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

	/**
	 * Todo(查询订单子表菜品信息)
	 *
	 * @Title: queryMenus
	 * @author daiaoxing
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
						"PSBMDdgl01.queryDetailByBillNo",billNo);
				query.addBlock("menus").addRows(listMenus);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}


}
