package com.baosight.wilp.ps.pc.service;


import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ps.pc.domain.*;
import com.baosight.wilp.ss.ac.domain.SSACTscCardRechargeInfo;
import com.baosight.wilp.ss.bm.config.MealCommonConfig;
import com.baosight.wilp.ss.bm.domain.SSBMStxx01;
import com.baosight.wilp.ss.bm.utils.*;
import com.bonawise.smp.base.domain.MealOrderTypeEntity;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * 病员支付回调接口
 *  支付宝回调 :patientMealNotifyAli ,  微信回调 :patientMealNotifyWechat
 * 
 * @Title: ServicePSPCPatientNotify.java
 * @ClassName: ServicePSPCPatientNotify
 * @Package：com.baosight.wilp.ps.pc.service
 * @author: liutao
 * @date: 2021年9月9日 下午1:30:10
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePSPCPatientNotify extends ServiceBase {
	
    
    /**
     * 
     * patientMealNotifyAli支付宝病员回调
     * *入参：订单编号billNo
     * *出餐：EiInfo(ResultData:respCode状态码，respMsg操作信息)
     * 1.查询订单信息
     * 2.判断订单状态是否为99，rejectCode是否为3，若同时满足则该订单为系统关闭订单，取消撤单状态
     * 3.变更订单状态为02已支付，rejectCode为null
     * 4.保存PSPCTmealOperation操作记录
     * 5.保存在线支付流转记录PSPCTpayMealTimetask
     *
     * @Title: patientMealNotifyAli 
     * @param inInfo
     * @return 
     * @return: EiInfo 
     * @author: liutao
     * @date: 2021年9月9日 下午1:31:14
     */
	public EiInfo patientMealNotifyAli(EiInfo inInfo){
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String billId = StringUtil.toString(attr.get("billNo"));
		EiInfo outInfo = new EiInfo();
		
		System.out.println(StringUtil.separator+"patientMealNotifyAli!start!---------");
		ResultData result = new ResultData();
		
		//已回调单据号 
		String billBack = "已回调单据ID:";
		if(StringUtils.isBlank(billId)) {
			result.setRespCode("201");
			result.setRespMsg("billId为空，请检查");
			outInfo.set("result", result);
			outInfo.setStatus(-1);
			return outInfo;
		} else {
			try {
				System.out.println(StringUtil.separator+"billId----------------"+billId);
				/**1.查询订单信息*/
				HashMap<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("sql","PSPCTmealOrderBillPatient.queryBillById");
				paramMap.put("str",billId);
				EiInfo callQueryPatient = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
				List<PSPCTmealOrderBillPatient> lstResult = (List<PSPCTmealOrderBillPatient>) callQueryPatient.get("result");
				System.out.println(StringUtil.separator+"查询到的订单数量 : " + lstResult.size());
				List<PSPCTpayMealTimetask> lstAlipay = new ArrayList<PSPCTpayMealTimetask>();
				if(lstResult.size() > 0) {
					for(PSPCTmealOrderBillPatient patientBillinfo : lstResult) {
						System.out.println("patientBillinfo.getStatusCode : " + patientBillinfo.getStatusCode());
						if(!("00").equals(patientBillinfo.getStatusCode()) 
								&& !("90").equals(patientBillinfo.getStatusCode())
								&& !("99").equals(patientBillinfo.getStatusCode())) {
							billBack += patientBillinfo.getId() + ";";
							break;
						}
						
						//支付成功的订单变更订单状态(草稿:00–>已确认:02)
						PSPCBillStatusInfo billInfo = new PSPCBillStatusInfo();
						billInfo.setPboTbName("sc_order_bill_patient");
						billInfo.setBeforeStatus("00");
						billInfo.setAfterStatus("02");
						//查询食堂联络人
						paramMap.put("sql","SSBMStxx01.queryCanteenByCanteenNum");
						paramMap.put("str",patientBillinfo.getCanteenNum());
						EiInfo call = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
						List<SSBMStxx01> liaison = (List<SSBMStxx01>) call.get("result");
						String liaisonId = "";
						if(!liaison.isEmpty()) {
							liaisonId = liaison.get(0).getLiaisonId();
						}else {
							result.setSuccess(false);
							result.setRespMsg("未查询到食堂联络人信息！");
							outInfo.set("result", result);
							outInfo.setStatus(-1);
							return outInfo;
						}
						/**2.判断订单状态是否为99，rejectCode是否为3，若同时满足则该订单为系统关闭订单，取消撤单状态*/
						billInfo.setCurrentDealer(liaisonId);
						billInfo.setBillId(patientBillinfo.getId());
						billInfo.setCreatorId(patientBillinfo.getRecCreator());
						billInfo.setCreatorName(patientBillinfo.getUserName());
						billInfo.setPboCode("PATIENT_MEAL");
						billInfo.setHandleAdvice("提交");
						billInfo.setOprationRoute("支付成功确认状态");
						System.out.println(billInfo.toString());
						//订单被关闭，status_code:99->02,rejectcode:null,rejectReason:null
						String rejectCode = patientBillinfo.getRejectCode();
						//判断订单撤销状态
						if(!StringUtils.isEmpty(rejectCode)) {
							//系统作废，更改rejectCod->null,rejectReason:null
							billInfo.setIsReject("0");
							billInfo.setBeforeStatus("99");
						}
						/**3.变更订单状态为02已支付，rejectCode为null*/
						paramMap.put("billInfo", billInfo);
						EiInfo callStatusChange = LocalServiceUtil.callNewTx("PSPCStatusChange", "statusChange", paramMap);
						RespResult billStatusChangeResult = (RespResult) callStatusChange.get("result");
						System.out.println(StringUtil.separator+"状态变更:"+billStatusChangeResult.getRespCode()+billStatusChangeResult.getRespMsg());
						if("200".equals(billStatusChangeResult.getRespCode())) {
							//更新状态变更时间
							HashMap<String,String> map = new HashMap<String, String>();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							map.put("billId", patientBillinfo.getId());
							map.put("revisor", patientBillinfo.getRecCreator());
							map.put("reviseTime", sdf.format(new Date()));
							//更新订单主表的修改时间
							paramMap.put("sql","PSPCTmealOrderBillPatient.updateReviseTime");
							paramMap.put("map",map);
							EiInfo callUpdateReviseTime = LocalServiceUtil.call("SSBMTy", "updateSqlByMap", paramMap);
							
							System.out.println(StringUtil.separator+"更新订单状态变更时间" + callUpdateReviseTime.get("success"));
						}else {
							result.setRespCode(billStatusChangeResult.getRespCode());
							result.setRespMsg(billStatusChangeResult.getRespMsg());
							System.out.println(result.getRespMsg());
							outInfo.set("result", result);
							outInfo.setStatus(-1);
							return outInfo;
						}
						PSPCTpayMealTimetask alipayInfo = new PSPCTpayMealTimetask();
						//查询食堂消费编码
						alipayInfo.setId(UUIDUtils.getUUID32());
						alipayInfo.setCreateTime(CalendarUtils.dateTimeFormat(new Date()));
						alipayInfo.setBillId(patientBillinfo.getId());
						alipayInfo.setBillNo(patientBillinfo.getBillNo());
						//判断是否为POS机订单
						if("POS".equals(patientBillinfo.getArchiveFlag())){
							alipayInfo.setBillType(MealOrderTypeEntity.MEAL_PATIENT_POS);
						} else {
							alipayInfo.setBillType(MealOrderTypeEntity.MEAL_PATIENT_APP);
						}
						alipayInfo.setBillStatus(patientBillinfo.getStatusCode());
						alipayInfo.setCreator(patientBillinfo.getOpenId());
						alipayInfo.setPayType(patientBillinfo.getPayType());
						alipayInfo.setFlag("99");
						
						lstAlipay.add(alipayInfo);
					}
					for (PSPCTmealOrderBillPatient patientBillinfo : lstResult) {
						/**4.保存PSPCTmealOperation操作记录表*/
						PSPCTmealOperation operateRecord = new PSPCTmealOperation();
						operateRecord.setId(UUIDUtils.getUUID32());
						operateRecord.setOperationTime(CalendarUtils.dateTimeFormat(new Date()));
						operateRecord.setBillNo(patientBillinfo.getBillNo());
						operateRecord.setCreatorId(patientBillinfo.getRecCreator());
						operateRecord.setCreatorName(patientBillinfo.getUserName());
						operateRecord.setOperationRoute("02");
						paramMap.put("sql","PSPCTmealOperation.insert");
						paramMap.put("domain",operateRecord);
						EiInfo callInsertOperation = LocalServiceUtil.call("SSBMTy", "insertSqlByDomain", paramMap);
						System.out.println(StringUtil.separator+"订单消费记录保存Operation:"+callInsertOperation.get("success"));
					}
					
					if(!lstAlipay.isEmpty()) {
						/**5.保存在线支付流转记录表PSPCTpayMealTimetask*/
						paramMap.put("sql","PSPCTpayMealTimetask.insert");
						paramMap.put("list",lstAlipay);
						EiInfo callInsertMealTimeTask = LocalServiceUtil.call("SSBMTy", "insertSqlByList", paramMap);
						System.out.println("订单消费记录alipayMealTimetask保存" + callInsertMealTimeTask.get("success"));
					} else {
						System.out.println(StringUtil.separator+"PatientMeal:" + billBack + "此单据已回调成功，无需再次请求！ ------------------");
						result.setRespCode("200");
						System.out.println(StringUtil.separator+"");
						outInfo.set("result", result);
						return outInfo;
					}
				}
				//查询堂食支付宝支付的订单
				paramMap.put("sql","PSPCTmealOrderBillPatient.queryCardConsumeByBillId");
				paramMap.put("str",billId);
				EiInfo callCard = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
				List<PSPCTscCardOrderFood> lstCardResult = (List<PSPCTscCardOrderFood>) callCard.get("result");

				if(lstCardResult.size()>0){
					System.out.println("堂食支付宝扫码支付回调："+billId);

					HashMap<String,Object> map = new HashMap<>();
					//堂食消费完成直接结束订单状态99
					map.put("id",billId);
					map.put("status","99");
					map.put("updateTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					//更新订单主表
					dao.update("PSPCTmealOrderBillPatient.updateConsumeDeviceBillInfo",map);
				}
				else {
					result.setRespCode("201");
					result.setRespMsg("未找到此单据，请检查单据ID是否正确!");
					outInfo.set("result", result);
					outInfo.setStatus(-1);
					return outInfo;
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setRespCode("201");
				result.setRespMsg("程序异常");
				outInfo.set("result", result);
				outInfo.setStatus(-1);
				return outInfo;
			}
			
		}
		
		outInfo.set("result", result);
		return outInfo;
	}
	
	/**
	 * 
     * patientMealNotifyWechat微信病员回调
     * *入参：订单编号billNo
     * *出餐：EiInfo(ResultData:respCode状态码，respMsg操作信息)
     * 1.查询订单信息
     * 2.判断订单状态是否为99，rejectCode是否为3，若同时满足则该订单为系统关闭订单，取消撤单状态
     * 3.变更订单状态为02已支付，rejectCode为null
     * 4.保存PSPCTmealOperation操作记录
     * 5.保存在线支付流转记录PSPCTpayMealTimetask
	 *
	 * @Title: patientMealNotifyWechat 
	 * @param inInfo
	 * @return
	 * @throws Exception 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:31:37
	 */
	public EiInfo patientMealNotifyWechat(EiInfo inInfo)  throws Exception{
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String billId = StringUtil.toString(attr.get("billNo"));
		EiInfo outInfo = new EiInfo();
		
		ResultData result = new ResultData();
		System.out.println(StringUtil.separator+"patientMealNotifyWechat!start! -----------------------------");
		
		//已回调单据号 
		String billBack = "已回调单据ID:";
		
		if(StringUtils.isBlank(billId)){
			result.setRespCode("201");
			result.setRespMsg("billId为空，请检查");
			outInfo.set("respCode", result.getRespCode());
			outInfo.set("respMsg", result.getRespMsg());
			outInfo.setStatus(-1);
			return outInfo;
		} else {
			System.out.println(StringUtil.separator+"billId  ----------------"+billId);
			/**1.查询订单信息*/
			HashMap<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sql","PSPCTmealOrderBillPatient.queryBillById");
			paramMap.put("str",billId);
			EiInfo callQueryPatient = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
			List<PSPCTmealOrderBillPatient> lstResult = (List<PSPCTmealOrderBillPatient>) callQueryPatient.get("result");
			List<PSPCTpayMealTimetask> lstAlipay = new ArrayList<PSPCTpayMealTimetask>();
			if(!lstResult.isEmpty()) {
				for(PSPCTmealOrderBillPatient patientBillinfo : lstResult) {
					//判断订单状态
					if(!("00").equals(patientBillinfo.getStatusCode())
							&& !("90").equals(patientBillinfo.getStatusCode())
							&& !("99").equals(patientBillinfo.getStatusCode())){
						billBack += patientBillinfo.getId() + ";";
						break;
					}
					/** 2.判断订单状态是否为99，rejectCode是否为3，若同时满足则该订单为系统关闭订单，取消撤单状态*/
					PSPCBillStatusInfo billInfo = new PSPCBillStatusInfo();
					billInfo.setPboTbName("sc_order_bill_patient");
					billInfo.setBeforeStatus("00");
					billInfo.setAfterStatus("02");
					//查询食堂联络人
					paramMap.put("sql","SSBMStxx01.queryCanteenByCanteenNum");
					paramMap.put("str",patientBillinfo.getCanteenNum());
					EiInfo call = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
					List<SSBMStxx01> liaison = (List<SSBMStxx01>) call.get("result");
					String liaisonId = "";
					if(!liaison.isEmpty()) {
						liaisonId = liaison.get(0).getLiaisonId();
					}else {
						result.setSuccess(false);
						result.setRespMsg("未查询到食堂联络人信息！");
						outInfo.set("respCode", result.getRespCode());
						outInfo.set("respMsg", result.getRespMsg());
						return outInfo;
					}
					/**3.变更订单状态为02已支付，rejectCode为null*/
					billInfo.setCurrentDealer(liaisonId);
					billInfo.setBillId(patientBillinfo.getId());
					billInfo.setCreatorId(patientBillinfo.getRecCreator());
					billInfo.setCreatorName(patientBillinfo.getUserName());
					billInfo.setPboCode("PATIENT_MEAL");
					billInfo.setHandleAdvice("提交");
					billInfo.setOprationRoute("支付成功确认状态");
					System.out.println(billInfo.toString());
					//订单被关闭，status_code:99->02,rejectcode:null,rejectReason:null
					String rejectCode = patientBillinfo.getRejectCode();
					//判断订单撤销状态
					if(!StringUtils.isEmpty(rejectCode)) {
						//系统作废，更改rejectCod->null,rejectReason:null
						billInfo.setIsReject("0");
						billInfo.setBeforeStatus("99");
					}
					//变更订单状态
					paramMap.put("billInfo", billInfo);
					EiInfo callStatusChange = LocalServiceUtil.call("PSPCStatusChange", "statusChange", paramMap);
					RespResult billStatusChangeResult = (RespResult) callStatusChange.get("result");
					System.out.println(StringUtil.separator+"状态变更:"+billStatusChangeResult.getRespCode()+billStatusChangeResult.getRespMsg());
					if("200".equals(billStatusChangeResult.getRespCode())) {
						//更新状态变更时间
						HashMap<String,String> map = new HashMap<String, String>();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						map.put("billId", patientBillinfo.getId());
						map.put("revisor", patientBillinfo.getRecCreator());
						map.put("reviseTime", sdf.format(new Date()));
						//更新订单主表
						paramMap.put("sql","PSPCTmealOrderBillPatient.updateReviseTime");
						paramMap.put("map",map);
						EiInfo callUpdateReviseTime = LocalServiceUtil.call("SSBMTy", "updateSqlByMap", paramMap);
						
						System.out.println(StringUtil.separator+"更新订单状态变更时间" + callUpdateReviseTime.get("success"));
					}else {
						result.setRespCode(billStatusChangeResult.getRespCode());
						result.setRespMsg(billStatusChangeResult.getRespMsg());
						System.out.println(result.getRespMsg());
						outInfo.set("result", result);
						return outInfo;
					}

					PSPCTpayMealTimetask alipayInfo = new PSPCTpayMealTimetask();
					alipayInfo.setId(UUIDUtils.getUUID32());
					alipayInfo.setCreateTime(CalendarUtils.dateTimeFormat(new Date()));
					alipayInfo.setBillId(patientBillinfo.getId());
					alipayInfo.setBillNo(patientBillinfo.getBillNo());
					if("POS".equals(patientBillinfo.getArchiveFlag())){
						alipayInfo.setBillType(MealOrderTypeEntity.MEAL_PATIENT_POS);
					} else {
						alipayInfo.setBillType(MealOrderTypeEntity.MEAL_PATIENT_APP);
					}
					alipayInfo.setBillStatus(patientBillinfo.getStatusCode());
					alipayInfo.setCreator(patientBillinfo.getOpenId());
					alipayInfo.setPayType(patientBillinfo.getPayType());
					alipayInfo.setFlag("99");
					
					lstAlipay.add(alipayInfo);
				}
				for (PSPCTmealOrderBillPatient patientBillinfo : lstResult) {
					/***4.保存PSPCTmealOperation操作记录*/
					PSPCTmealOperation operateRecord = new PSPCTmealOperation();
					operateRecord.setId(UUIDUtils.getUUID32());
					operateRecord.setOperationTime(CalendarUtils.dateTimeFormat(new Date()));
					operateRecord.setBillNo(patientBillinfo.getBillNo());
					operateRecord.setCreatorId(patientBillinfo.getRecCreator());
					operateRecord.setCreatorName(patientBillinfo.getUserName());
					operateRecord.setOperationRoute("02");
					paramMap.put("sql","PSPCTmealOperation.insert");
					paramMap.put("domain",operateRecord);
					EiInfo callInsertOperation = LocalServiceUtil.call("SSBMTy", "insertSqlByDomain", paramMap);
					System.out.println(StringUtil.separator+"订单消费记录保存Operation:"+callInsertOperation.get("success"));
				}
				if(!lstAlipay.isEmpty()) {
					/**5.保存在线支付流转记录PSPCTpayMealTimetask*/
					paramMap.put("sql","PSPCTpayMealTimetask.insert");
					paramMap.put("list",lstAlipay);
					EiInfo callInsertMealTimeTask = LocalServiceUtil.call("SSBMTy", "insertSqlByList", paramMap);
					System.out.println(StringUtil.separator+"订单消费记录alipayMealTimetask保存:"+callInsertMealTimeTask.get("success"));
				} else {
					System.out.println(StringUtil.separator+"---------- BackPatientMeal:" + billBack + "此单据已回调成功，无需再次请求！");
					result.setRespCode("200");
					result.setRespMsg("此单据已回调成功，无需再次请求！");
					outInfo.set("result", result);
					return outInfo;
				}
			}

			//查询堂食微信支付的订单
			paramMap.put("sql","PSPCTmealOrderBillPatient.queryCardConsumeByBillId");
			paramMap.put("str",billId);
			EiInfo callCard = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
			List<PSPCTscCardOrderFood> lstCardResult = (List<PSPCTscCardOrderFood>) callCard.get("result");

			if(lstCardResult.size()>0){
				System.out.println("堂食微信扫码支付回调："+billId);

				HashMap<String,Object> map = new HashMap<>();
				//堂食消费完成订单状态直接变更为结束99
				map.put("id",billId);
				map.put("status","99");
				map.put("updateTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				//更新订单主表
				dao.update("PSPCTmealOrderBillPatient.updateConsumeDeviceBillInfo",map);
			}
			else {
				result.setRespCode("201");
				result.setRespMsg("未找到此单据，请检查单据ID是否正确!");
				outInfo.set("result", result);
				return outInfo;
			}
		}
		System.out.println(StringUtil.separator+"weChat patient_meal !end! -----------------------------");
		outInfo.set("result", result);
		return outInfo;
	}
	/**
	 *
	 * patientRechargeNotifyAli支付宝职工充值回调
	 * *入参：订单编号billNo
	 * *出餐：EiInfo(ResultData:respCode状态码，respMsg操作信息)
	 * 1.查询充值信息
	 * 2.充值成功变更订单状态(待充值:00–>充值成功:99),变更失败则返回失败信息
	 * 3.调用一卡通充值接口,充值失败返回失败信息
	 *
	 * @Title: workRechargeNotifyAli
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:13:53
	 */
	public synchronized EiInfo patientRechargeNotifyAli(EiInfo inInfo){
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String billId = StringUtil.toString(attr.get("billNo"));
		EiInfo outInfo = new EiInfo();

		System.out.println(StringUtil.separator+"patientRechargeNotifyAli!start!---------");
		ResultData result = new ResultData();

		//已回调单据号
		String billBack = "已回调单据ID:";
		if(StringUtils.isBlank(billId)) {
			result.setRespCode("201");
			result.setRespMsg("billId为空，请检查");
			outInfo.set("result", result);
			outInfo.setStatus(-1);
			return outInfo;
		} else {
			try {
				System.out.println(StringUtil.separator+"billId----------------"+billId);
				/**1.查询充值信息*/
				HashMap<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("sql","SSACTscCardRechargeInfo.queryByBillId");
				paramMap.put("str",billId);
				EiInfo callQueryPatient = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
				List<SSACTscCardRechargeInfo> lstResult = (List<SSACTscCardRechargeInfo>) callQueryPatient.get("result");
				System.out.println(StringUtil.separator+"查询到的订单数量 : " + lstResult.size());
				if(lstResult.size() > 0) {
					for(SSACTscCardRechargeInfo rechargeinfo : lstResult) {
						if("99".equals(rechargeinfo.getFlag())){
							System.out.println("重复回调" + rechargeinfo.getTradeNo());
							//充值记录已更新，表示重复回调
							result.setSuccess(true);
							result.setRespMsg("重复回调");
							result.setRespCode("201");
							outInfo.set("result", result);
							outInfo.setStatus(1);
							return outInfo;
						}
						System.out.println("cardRechargeInfo.flag : " + rechargeinfo.getFlag());
						if(!("00").equals(rechargeinfo.getFlag())) {
							billBack += rechargeinfo.getId() + ";";
							break;
						}

						/**2.充值成功变更订单状态(待充值:00–>充值成功:99)*/
						rechargeinfo.setFlag("99");
						rechargeinfo.setResviseTime(CalendarUtils.dateTimeFormat(new Date()));
						paramMap.put("sql", "SSACTscCardRechargeInfo.update");
						paramMap.put("domain", rechargeinfo);
						EiInfo callUpdate = LocalServiceUtil.call("SSBMTy", "updateSqlByDomain", paramMap);
						boolean flag = Boolean.parseBoolean(StringUtil.toString(callUpdate.get("success")));
						System.out.println("更新充值记录表:"+flag);
						if(!flag) {
							result.setSuccess(flag);
							result.setRespMsg("更新充值记录失败");
							result.setRespCode("201");
							outInfo.set("result", result);
							outInfo.setStatus(-1);
							return outInfo;
						}

						/**3.调用一卡通充值接口*/
						String rechargeType = rechargeinfo.getRechargeType();
						String MoneyType = "30";
						System.out.println( StringUtil.separator + rechargeType + StringUtil.separator);
						if("0101".equals(rechargeType)){
							MoneyType = "20";
						}
						paramMap.put("cardBaseCode",rechargeinfo.getCardId());
						paramMap.put("cardRechargePrice",rechargeinfo.getCardRechargeMoney().multiply(new BigDecimal("100")).intValue());
						paramMap.put("moneyType",MoneyType);
						paramMap.put("username",rechargeinfo.getWorkNo());
						paramMap.put("rechargeType",rechargeinfo.getRechargeType());
						paramMap.put("billNo",rechargeinfo.getId());
						paramMap.put("canteenNum",rechargeinfo.getCanteenNum());
						paramMap.put("canteenName", MealCommonConfig.getSmpConfig().getPatientCanteenName());
						paramMap.put("payNo",rechargeinfo.getTradeNo());
						EiInfo callCardPay = LocalServiceUtil.call("PSAECZ01", "rechargeCardInfo", paramMap);

						if(callCardPay.getStatus() < 0) {
							result.setSuccess(false);
							result.setRespMsg("一卡通充值失败:" + callCardPay.getMsg());
							result.setRespCode("201");
							outInfo.set("result", result);
							outInfo.setStatus(-1);
							return outInfo;
						}
					}
				} else {
					result.setRespCode("201");
					result.setRespMsg("未找到此单据，请检查单据ID是否正确!");
					outInfo.set("result", result);
					outInfo.setStatus(-1);
					return outInfo;
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setRespCode("201");
				result.setRespMsg("程序异常");
				outInfo.set("result", result);
				outInfo.setStatus(-1);
				return outInfo;
			}

		}
		result.setRespCode("200");
		outInfo.set("result", result);
		return outInfo;
	}


	/**
	 *
	 * patientRechargeNotifyWechat微信职工充值回调
	 * *入参：订单编号billNo
	 * *出餐：EiInfo(ResultData:respCode状态码，respMsg操作信息)
	 * 1.查询充值信息
	 * 2.判断订单状态是否为99，rejectCode是否为3，若同时满足则该订单为系统关闭订单，取消撤单状态
	 * 3.变更订单状态为02已支付，rejectCode为null
	 *
	 * @Title: patientRechargeNotifyWechat
	 * @param inInfo
	 * @return
	 * @throws Exception
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:14:05
	 */
	public synchronized EiInfo patientRechargeNotifyWechat(EiInfo inInfo)  throws Exception{
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String billId = StringUtil.toString(attr.get("billNo"));
		EiInfo outInfo = new EiInfo();

		ResultData result = new ResultData();
		System.out.println(StringUtil.separator+"workRechargeNotifyWechat!start! -----------------------------");

		//已回调单据号
		String billBack = "已回调单据ID:";

		if(StringUtils.isBlank(billId)){
			result.setRespCode("201");
			result.setRespMsg("billId为空，请检查");
			outInfo.set("respCode", result.getRespCode());
			outInfo.set("respMsg", result.getRespMsg());
			outInfo.setStatus(-1);
			return outInfo;
		} else {
			try {
				System.out.println(StringUtil.separator+"billId----------------"+billId);
				/**1.查询充值信息*/
				HashMap<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("sql","SSACTscCardRechargeInfo.queryByBillId");
				paramMap.put("str",billId);
				EiInfo callQueryPatient = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
				List<SSACTscCardRechargeInfo> lstResult = (List<SSACTscCardRechargeInfo>) callQueryPatient.get("result");
				System.out.println(StringUtil.separator+"查询到的订单数量 : " + lstResult.size());
				if(lstResult.size() > 0) {
					for(SSACTscCardRechargeInfo rechargeinfo : lstResult) {
						if("99".equals(rechargeinfo.getFlag())){
							System.out.println("重复回调" + rechargeinfo.getTradeNo());
							//充值记录已更新，表示重复回调
							result.setSuccess(true);
							result.setRespMsg("重复回调");
							result.setRespCode("201");
							outInfo.set("result", result);
							outInfo.setStatus(1);
							return outInfo;
						}
						System.out.println("cardRechargeInfo.flag : " + rechargeinfo.getFlag());
						if(!("00").equals(rechargeinfo.getFlag())) {
							billBack += rechargeinfo.getId() + ";";
							break;
						}

						/**2.充值成功变更订单状态(待充值:00–>充值成功:99)*/
						rechargeinfo.setFlag("99");
						rechargeinfo.setResviseTime(CalendarUtils.dateTimeFormat(new Date()));
						paramMap.put("sql", "SSACTscCardRechargeInfo.update");
						paramMap.put("domain", rechargeinfo);
						EiInfo callUpdate = LocalServiceUtil.call("SSBMTy", "updateSqlByDomain", paramMap);
						boolean flag = Boolean.parseBoolean(StringUtil.toString(callUpdate.get("success")));
						System.out.println("更新充值记录表:"+flag);
						if(!flag) {
							result.setSuccess(flag);
							result.setRespMsg("更新充值记录失败");
							result.setRespCode("201");
							outInfo.set("result", result);
							outInfo.setStatus(-1);
							return outInfo;
						}

						String rechargeType = rechargeinfo.getRechargeType();
						String MoneyType = "20";
						System.out.println( StringUtil.separator + rechargeType + StringUtil.separator);
						if("0201".equals(rechargeType)){
							MoneyType = "30";
						}
						/**3.调用一卡通充值接口*/
						paramMap.put("cardBaseCode",rechargeinfo.getCardId());
						paramMap.put("cardRechargePrice",rechargeinfo.getCardRechargeMoney().multiply(new BigDecimal("100")).intValue());
						paramMap.put("moneyType",MoneyType);
						paramMap.put("username",rechargeinfo.getWorkNo());
						paramMap.put("rechargeType",rechargeinfo.getRechargeType());
						paramMap.put("billNo",rechargeinfo.getId());
						paramMap.put("canteenNum",rechargeinfo.getCanteenNum());
						paramMap.put("canteenName", MealCommonConfig.getSmpConfig().getPatientCanteenName());
						paramMap.put("payNo",rechargeinfo.getTradeNo());
						EiInfo callCardPay = LocalServiceUtil.call("PSAECZ01", "rechargeCardInfo", paramMap);

						if(callCardPay.getStatus() < 0) {
							result.setSuccess(false);
							result.setRespMsg("一卡通充值失败:" + callCardPay.getMsg());
							result.setRespCode("201");
							outInfo.set("result", result);
							outInfo.setStatus(-1);
							return outInfo;
						}
					}
				} else {
					result.setRespCode("201");
					result.setRespMsg("未找到此单据，请检查单据ID是否正确!");
					outInfo.set("result", result);
					outInfo.setStatus(-1);
					return outInfo;
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setRespCode("201");
				result.setRespMsg("程序异常");
				outInfo.set("result", result);
				outInfo.setStatus(-1);
				return outInfo;
			}
		}
		System.out.println(StringUtil.separator+"weChat patient_meal !end! -----------------------------");
		result.setRespCode("200");
		outInfo.set("result", result);
		return outInfo;
	}
}
