package com.baosight.wilp.ss.ac.service;


import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ps.pc.domain.PSPCBillStatusInfo;
import com.baosight.wilp.ss.ac.domain.SSACTscCardOrderFood;
import com.baosight.wilp.ss.ac.domain.SSACTscCardRechargeInfo;
import com.baosight.wilp.ss.ac.domain.SSACTscWorkOrderBill;
import com.baosight.wilp.ss.bm.config.MealCommonConfig;
import com.baosight.wilp.ss.bm.domain.SSBMStxx01;
import com.baosight.wilp.ss.bm.utils.*;
import com.bonawise.domain.base.BusinessInfoDTO;
import com.bonawise.domain.base.ResultEntry;
import com.bonawise.smp.weChat.hessian.WeChatPayImpl;
import com.bonawise.domain.base.OrderRecord;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * 
 * 病员支付回调接口
 * 
 * @Title: ServiceSSACWorkNotify.java
 * @ClassName: ServiceSSACWorkNotify
 * @Package：com.baosight.wilp.ss.ac.service
 * @author: liutao
 * @date: 2021年9月9日 下午3:13:15
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSACWorkNotify extends ServiceBase {
	

    /**
     * 
     * workMealNotifyAli支付宝职工订单回调
     * *入参：订单编号billNo
     * *出餐：EiInfo(ResultData:respCode状态码，respMsg操作信息)
     * 1.查询订单信息
     * 2.判断订单状态是否为99，rejectCode是否为3，若同时满足则该订单为系统关闭订单，取消撤单状态
     * 3.变更订单状态为02已支付，rejectCode为null
     *
     * @Title: workMealNotifyAli 
     * @param inInfo
     * @return 
     * @return: EiInfo 
     * @author: liutao
     * @date: 2021年9月9日 下午3:13:26
     */
	public EiInfo workMealNotifyAli(EiInfo inInfo){
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String billId = StringUtil.toString(attr.get("billNo"));
		EiInfo outInfo = new EiInfo();
		
		System.out.println(StringUtil.separator+"workMealNotifyAli!start!---------");
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
				paramMap.put("sql","SSACTscWorkOrderBill.queryByBillId");
				paramMap.put("str",billId);
				EiInfo callQueryPatient = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
				List<SSACTscWorkOrderBill> lstResult = (List<SSACTscWorkOrderBill>) callQueryPatient.get("result");
				System.out.println(StringUtil.separator+"查询到的订单数量 : " + lstResult.size());
				if(lstResult.size() > 0) {
					for(SSACTscWorkOrderBill billinfo : lstResult) {
						System.out.println("patientBillinfo.getStatusCode : " + billinfo.getStatusCode());
						if(!("00").equals(billinfo.getStatusCode()) 
								&& !("90").equals(billinfo.getStatusCode())
								&& !("99").equals(billinfo.getStatusCode())) {
							billBack += billinfo.getId() + ";";
							break;
						}
						
						/**2.支付成功的订单变更订单状态(草稿:00–>已确认:02)*/
						PSPCBillStatusInfo billInfo = new PSPCBillStatusInfo();
						billInfo.setPboTbName("sc_work_order_bill");
						billInfo.setBeforeStatus("00");
						billInfo.setAfterStatus("02");
						//查询食堂联络人
						paramMap.put("sql","SSBMStxx01.queryCanteenByCanteenNum");
						paramMap.put("str",billinfo.getCanteenNum());
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
						billInfo.setCurrentDealer(liaisonId);
						billInfo.setBillId(billinfo.getId());
						billInfo.setCreatorId(billinfo.getRecCreator());
						billInfo.setCreatorName(billinfo.getUserName());
						billInfo.setPboCode("MEAL");
						billInfo.setHandleAdvice("提交");
						billInfo.setOprationRoute("支付成功确认状态");
						System.out.println(billInfo.toString());
						//订单被关闭，status_code:99->02,rejectcode:null,rejectReason:null
						String rejectCode = billinfo.getRejectCode();
						//判断订单撤销状态
						if(!StringUtils.isEmpty(rejectCode)) {
							if("2".equals(rejectCode)){
								continue;
							}
							//系统作废，更改rejectCode->null,rejectReason:null
							billInfo.setIsReject("0");
							billInfo.setBeforeStatus("99");
						}
						/**3.变更订单状态*/
						paramMap.put("billInfo", billInfo);
						EiInfo callStatusChange = LocalServiceUtil.callNewTx("PSPCStatusChange", "statusChange", paramMap);
						RespResult billStatusChangeResult = (RespResult) callStatusChange.get("result");
						System.out.println(StringUtil.separator+"状态变更:"+billStatusChangeResult.getRespCode()+billStatusChangeResult.getRespMsg());
						if("200".equals(billStatusChangeResult.getRespCode())) {
							//更新状态变更时间
							HashMap<String,String> map = new HashMap<String, String>();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							map.put("billId", billinfo.getId());
							map.put("revisor", billinfo.getRecCreator());
							map.put("reviseTime", sdf.format(new Date()));
							//更新订单主表
							paramMap.put("sql","SSACTscWorkOrderBill.updateReviseTime");
							paramMap.put("map",map);
							EiInfo callUpdateReviseTime = LocalServiceUtil.call("SSBMTy", "updateSqlByMap", paramMap);
							
							System.out.println(StringUtil.separator+"更新订单状态变更时间" + callUpdateReviseTime.get("success"));
							//删除订单中间表记录
							paramMap.put("sql","SSACTworkOrderPayMiddle.deleteByBillId");
							paramMap.put("str",billinfo.getId());
							EiInfo callInsertMiddle = LocalServiceUtil.callNewTx("SSBMTy", "deleteSqlByString", paramMap);
						}else {
							result.setRespCode(billStatusChangeResult.getRespCode());
							result.setRespMsg(billStatusChangeResult.getRespMsg());
							System.out.println(result.getRespMsg());
							outInfo.set("result", result);
							outInfo.setStatus(-1);
							return outInfo;
						}
					}
				}
				//查询堂食支付宝支付的订单
				paramMap.put("sql","SSACTscWorkOrderBill.queryCardConsumeByBillId");
				paramMap.put("str",billId);
				EiInfo callCard = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
				List<SSACTscCardOrderFood> lstCardResult = (List<SSACTscCardOrderFood>) callCard.get("result");

				if(lstCardResult.size()>0){
					System.out.println("堂食支付宝扫码支付回调："+billId);

					HashMap<String,Object> map = new HashMap<>();
					//堂食支付成功更新订单状态位99已结束
					map.put("id",billId);
					map.put("status","99");
					map.put("updateTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					dao.update("SSACTscWorkOrderBill.updateConsumeDeviceBillInfo",map);

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
     * workMealNotifyWechat微信职工订单回调
     * *入参：订单编号billNo
     * *出餐：EiInfo(ResultData:respCode状态码，respMsg操作信息)
     * 1.查询订单信息
     * 2.判断订单状态是否为99，rejectCode是否为3，若同时满足则该订单为系统关闭订单，取消撤单状态
     * 3.变更订单状态为02已支付，rejectCode为null
	 *
	 * @Title: workMealNotifyWechat 
	 * @param inInfo
	 * @return
	 * @throws Exception 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:13:39
	 */
	public EiInfo workMealNotifyWechat(EiInfo inInfo)  throws Exception{
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
			paramMap.put("sql","SSACTscWorkOrderBill.queryByBillId");
			paramMap.put("str",billId);
			EiInfo callQueryPatient = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
			List<SSACTscWorkOrderBill> lstResult = (List<SSACTscWorkOrderBill>) callQueryPatient.get("result");
			if(!lstResult.isEmpty()) {
				for(SSACTscWorkOrderBill billinfo : lstResult) {
					List listHistory = dao.query("PSPCStatusChange.getOperationHistory", billinfo.getId());
					if(!("00").equals(billinfo.getStatusCode()) 
							&& !("90").equals(billinfo.getStatusCode())
							&& !("99").equals(billinfo.getStatusCode()) && CollectionUtils.isNotEmpty(listHistory)){
						billBack += billinfo.getId() + ";";
						break;
					}
					
					//支付成功的订单变更订单状态(已支付:00–>已确认:02)
					PSPCBillStatusInfo billInfo = new PSPCBillStatusInfo();
					billInfo.setPboTbName("sc_work_order_bill");
					billInfo.setBeforeStatus("00");
					billInfo.setAfterStatus("02");
					//查询食堂联络人
					paramMap.put("sql","SSBMStxx01.queryCanteenByCanteenNum");
					paramMap.put("str",billinfo.getCanteenNum());
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
					billInfo.setCurrentDealer(liaisonId);
					billInfo.setBillId(billinfo.getId());
					billInfo.setCreatorId(billinfo.getRecCreator());
					billInfo.setCreatorName(billinfo.getUserName());
					billInfo.setPboCode("MEAL");
					billInfo.setHandleAdvice("提交");
					billInfo.setOprationRoute("支付成功确认状态");
					System.out.println(billInfo.toString());
					//订单被关闭，status_code:99->02,rejectcode:null,rejectReason:null
					String rejectCode = billinfo.getRejectCode();
					/**2.判断订单状态是否为99，rejectCode是否为3，若同时满足则该订单为系统关闭订单，取消撤单状态*/
					if(!StringUtils.isEmpty(rejectCode)) {
						if("2".equals(rejectCode)){
							continue;
						}
						//系统作废，更改rejectCode ->null,rejectReason:null
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
						map.put("billId", billinfo.getId());
						map.put("revisor", billinfo.getRecCreator());
						map.put("reviseTime", sdf.format(new Date()));

						/**2.查询订单支付详情*/
						BusinessInfoDTO busInfoDTO = new BusinessInfoDTO();
						busInfoDTO.setHospitalCode(MealCommonConfig.getSmpConfig().getHospitalCodeWechat());//医院编码
						//之前的模块编码
//						busInfoDTO.setModulCode("work_recharge_wechat");//模块编码
						busInfoDTO.setModulCode("work_wechat");
						busInfoDTO.setUsedUnitCode(billinfo.getCanteenNum());//食堂编码
						//之前的食堂名称的获取方式
//						busInfoDTO.setUserUnitName("在线充值");//食堂名称
						busInfoDTO.setUserUnitName(billinfo.getCanteenName());//食堂名称
						ResultEntry resultEntry = WeChatPayImpl.OrderQuery(billinfo.getPayNo(), busInfoDTO);
						if("200".equals(resultEntry.getRespCode()) || resultEntry.getRespCode() == 200){
							OrderRecord orderDetail = (OrderRecord)resultEntry.getData();
							map.put("transactionId", orderDetail.getTransactionId());
						}

						/**3.变更订单状态为02已支付，rejectCode为null*/
						paramMap.put("sql","SSACTscWorkOrderBill.updateReviseTime");
						paramMap.put("map",map);
						EiInfo callUpdateReviseTime = LocalServiceUtil.call("SSBMTy", "updateSqlByMap", paramMap);
						
						System.out.println(StringUtil.separator+"更新订单状态变更时间" + callUpdateReviseTime.get("success"));

						//删除订单中间表记录
						paramMap.put("sql","SSACTworkOrderPayMiddle.deleteByBillId");
						paramMap.put("str",billinfo.getId());
						EiInfo callInsertMiddle = LocalServiceUtil.callNewTx("SSBMTy", "deleteSqlByString", paramMap);
					}else {
						result.setRespCode(billStatusChangeResult.getRespCode());
						result.setRespMsg(billStatusChangeResult.getRespMsg());
						System.out.println(result.getRespMsg());
						outInfo.set("result", result);
						return outInfo;
					}
				}
			}
			//查询堂食微信支付的订单
			paramMap.put("sql","SSACTscWorkOrderBill.queryCardConsumeByBillId");
			paramMap.put("str",billId);
			EiInfo callCard = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
			List<SSACTscCardOrderFood> lstCardResult = (List<SSACTscCardOrderFood>) callCard.get("result");

			if(lstCardResult.size()>0){
				System.out.println("堂食微信扫码支付回调："+billId);

				HashMap<String,Object> map = new HashMap<>();
				//堂食支付成功更新订单状态位99已结束
				map.put("id",billId);
				map.put("status","99");
				map.put("updateTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				dao.update("SSACTscWorkOrderBill.updateConsumeDeviceBillInfo",map);

			}
			else {
				result.setRespCode("201");
				result.setRespMsg("未找到此单据，请检查单据ID是否正确!");
				outInfo.set("result", result);
				return outInfo;
			}
		}
		System.out.println(StringUtil.separator+"weChat patient_meal !end! -----------------------------");
		result.setRespCode("200");
		outInfo.set("result", result);
		return outInfo;
	}
	

	/**
	 * 
     * workRechargeNotifyAli支付宝职工充值回调
     * *入参：订单编号billNo
     * *出餐：EiInfo(ResultData:respCode状态码，respMsg操作信息)
     * 1.查询充值信息
	 * 2.调用一卡通充值接口,充值失败返回失败信息
	 * 3.充值成功变更订单状态(待充值:00–>充值成功:99),变更失败则返回失败信息
	 *
	 * @Title: workRechargeNotifyAli 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:13:53
	 */
	public EiInfo workRechargeNotifyAli(EiInfo inInfo){
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String billId = StringUtil.toString(attr.get("billNo"));
		EiInfo outInfo = new EiInfo();
		
		System.out.println(StringUtil.separator+"workRechargeNotifyAli!start!---------");
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

						/**2.调用一卡通充值接口,充值失败返回失败信息*/
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
						EiInfo callCardPay = LocalServiceUtil.call("SSAECZ01", "rechargeCardInfo", paramMap);

						if(callCardPay.getStatus() < 0) {
							result.setSuccess(false);
							result.setRespMsg("一卡通充值失败:" + callCardPay.getMsg());
							result.setRespCode("201");
							outInfo.set("result", result);
							outInfo.setStatus(-1);
							return outInfo;
						}
						System.out.println("****************************************************"+callCardPay.getMsgKey());
						/**3.充值成功变更订单状态(待充值:00–>充值成功:99),变更失败则返回失败信息*/
						rechargeinfo.setFlag("99");
						rechargeinfo.setBillId(callCardPay.getMsgKey());
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
     * workRechargeNotifyWechat微信职工充值回调
     * *入参：订单编号billNo
     * *出餐：EiInfo(ResultData:respCode状态码，respMsg操作信息)
     * 1.查询充值信息
     * 2.判断订单状态是否为99，rejectCode是否为3，若同时满足则该订单为系统关闭订单，取消撤单状态
     * 3.调用一卡通充值接口,充值失败返回失败信息
	 *
	 * @Title: workRechargeNotifyWechat 
	 * @param inInfo
	 * @return
	 * @throws Exception 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:14:05
	 */
	public EiInfo workRechargeNotifyWechat(EiInfo inInfo)  throws Exception{
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
						/**2.查询订单支付详情*/
						BusinessInfoDTO busInfoDTO = new BusinessInfoDTO();
						busInfoDTO.setHospitalCode(MealCommonConfig.getSmpConfig().getHospitalCodeWechat());//医院编码
						busInfoDTO.setModulCode("work_recharge_wechat");//模块编码
						busInfoDTO.setUsedUnitCode(rechargeinfo.getCanteenNum());//食堂编码
						busInfoDTO.setUserUnitName("在线充值");//食堂名称
						ResultEntry resultEntry = WeChatPayImpl.OrderQuery(rechargeinfo.getTradeNo(), busInfoDTO);
						OrderRecord orderDetail = (OrderRecord)resultEntry.getData();
						if(orderDetail != null){
							rechargeinfo.setTransactionId(orderDetail.getTransactionId());
						}

						/**3.调用一卡通充值接口,充值失败返回失败信息*/
						String rechargeType = rechargeinfo.getRechargeType();
						String MoneyType = "20";
						System.out.println( StringUtil.separator + rechargeType + StringUtil.separator);
						if("0201".equals(rechargeType)){
							MoneyType = "30";
						}
						paramMap.put("cardBaseCode",rechargeinfo.getCardId());
						paramMap.put("cardRechargePrice",rechargeinfo.getCardRechargeMoney().multiply(new BigDecimal("100")).intValue());
						paramMap.put("moneyType",MoneyType);
						paramMap.put("username",rechargeinfo.getWorkNo());
						paramMap.put("rechargeType",rechargeinfo.getRechargeType());
						paramMap.put("billNo",rechargeinfo.getId());
						UserSession.setProperty("iplat4j_loginName",rechargeinfo.getWorkNo());
						EiInfo callCardPay = LocalServiceUtil.call("SSAECZ01", "rechargeCardInfo", paramMap);
						
						if(callCardPay.getStatus() < 0) {
							result.setSuccess(false);
							result.setRespMsg("一卡通充值失败:" + callCardPay.getMsg());
							result.setRespCode("201");
							outInfo.set("result", result);
							outInfo.setStatus(-1);
							return outInfo;
						}
						System.out.println("****************************************************"+callCardPay.getMsgKey());

						rechargeinfo.setFlag("99");
						rechargeinfo.setBillId(callCardPay.getMsgKey());
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
