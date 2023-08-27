package com.baosight.wilp.ps.pc.service;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ps.pc.domain.*;
import com.baosight.wilp.ss.ac.domain.SSACTscCardRechargeInfo;
import com.baosight.wilp.ss.bm.config.CommGlobalConfig;
import com.baosight.wilp.ss.bm.config.MealCommonConfig;
import com.baosight.wilp.ss.bm.domain.SSBMStxx01;
import com.baosight.wilp.ss.bm.utils.*;
import com.bonawise.domain.base.BusinessInfoDTO;
import com.bonawise.domain.base.OrderRecord;
import com.bonawise.domain.base.ResultEntry;
import com.bonawise.smp.ResponseResult;
import com.bonawise.smp.alipay.hessian.AliPaySender;
import com.bonawise.smp.config.MealGlobalConfig;
import com.bonawise.smp.weChat.hessian.WeChatPayImpl;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * ServicePSPCPAY01 病员支付service
 *    订单选择现金支付 cashPosAll()
 *    POS机扫码线上支付 支付类型调用相应的支付方法 posPayByScanOnline()
 *    现金支付 payByCash()
 *    微信支付payByWechat()
 *    支付宝支付payByAli()
 * @Title: ServicePSPCPAY01.java
 * @ClassName: ServicePSPCPAY01
 * @Package：com.baosight.wilp.ps.pc.service
 * @author: liutao
 * @date: 2021年9月9日 下午1:32:32
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePSPCPAY01 extends ServiceBase {


    /**
     * cashPosAll订单选择现金支付
     * *入参：订单参数billNos
     * *出参：EiInfo(ResultData)
     * 1.查询订单信息
     * 2.更新订单支付类型，若更新失败则返回false
     * 3.调用现金支付的方法payByCash
     *
     * @Title: cashPosAll 
     * @param inInfo
     * @return 
     * @return: EiInfo 
     * @author: liutao
     * @date: 2021年9月9日 下午1:33:12
     */
	public EiInfo cashPosAll(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		EiInfo outInfo = new EiInfo();
		// 食堂编号
		String billNos = StringUtil.toString(attr.get("billNos"));
		String payType = StringUtil.toString(attr.get("payType"));
		ResultData j = new ResultData();
		if(StringUtils.isNotEmpty(billNos)){
			try {
				String[] sp = billNos.split(",");
				for (String billNo : sp) {
					/**1.查询订单信息*/
					HashMap<String,Object> paramMap = new HashMap<String, Object>();
					paramMap.put("sql","PSPCTmealOrderBillPatient.queryBillByBillNo");
					paramMap.put("str",billNo);
					EiInfo callQueryPatient = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
					List<PSPCTmealOrderBillPatient> bill = (List<PSPCTmealOrderBillPatient>) callQueryPatient.get("result");
					
					if(!bill.get(0).getStatusCode().equals("00")){
						continue;
					}
					PSPCTmealOrderBillPatient entity = new PSPCTmealOrderBillPatient();
					if(bill!=null&&bill.size()>0){
						if (StringUtil.isBlank(payType)){
							entity.setPayType("0000");
						}else {
							entity.setPayType(payType);
						}
						entity.setBillNo(billNo);
						
						/**2.更新订单支付类型*/
						Map<String, Object> payMap = new HashMap<String, Object>();
						payMap.put("billNo", billNo);
						payMap.put("payType", entity.getPayType());
						paramMap.put("sql","PSPCTmealOrderBillPatient.updatePayType");
						paramMap.put("map",payMap);
						EiInfo callUpdatePayType = LocalServiceUtil.call("SSBMTy", "updateSqlByMap", paramMap);
						
						if(!Boolean.parseBoolean(StringUtil.toString(callUpdatePayType.get("success")))){
							j.setSuccess(false);
							j.setRespMsg("更新订单支付类型失败！");
							outInfo.set("result", j);
							outInfo.setStatus(-1);
							return outInfo;
						}
						entity.setOpenId(bill.get(0).getOpenId());
						entity.setId(bill.get(0).getId());
						
						PSPCTmealOrderBillPatient patientBill = bill.get(0);
						/**3.调用现金支付的方法payByCash*/
						j = payByCash(patientBill);
						System.out.println(j.getRespMsg());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				j.setSuccess(false);
				j.setRespMsg("程序异常"+e.getMessage());
				outInfo.set("result", j);
				outInfo.setStatus(-1);
			}
		}else{
			j.setRespMsg("参数billNos缺失!");
			j.setSuccess(false);
			outInfo.set("result", j);
			outInfo.setStatus(-1);
			return outInfo;
		}
		outInfo.setStatus(j.isSuccess() ? 1 : -1);
		System.out.println(j.getRespMsg());
		outInfo.set("result", j);
		return outInfo;
	}
	

	/**
	 * 
     * payByCash现金支付
     * *入参：订单实体类patientBill
     * *出餐：ResultData
     * 1.判断订单状态是否为99，同时rejectCode为3，若同时满足则该订单为系统关闭订单，取消撤单状态
     * 2.变更订单状态为02已支付，rejectCode为null
     * 3.保存PSPCTmealOperation操作记录
	 *
	 * @Title: payByCash 
	 * @param patientBill
	 * @return
	 * @throws Exception 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:35:34
	 */
	private ResultData payByCash(PSPCTmealOrderBillPatient patientBill) throws Exception{
		ResultData result = new ResultData();
		
		PSPCBillStatusInfo billInfo = new PSPCBillStatusInfo();
		billInfo.setPboTbName("sc_order_bill_patient");
		billInfo.setBeforeStatus("00");
		billInfo.setAfterStatus("02");
		//查询食堂联络人
		String liaisonId = "";
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sql","SSBMStxx01.queryCanteenByCanteenNum");
		paramMap.put("str",patientBill.getCanteenNum());
		EiInfo call = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);
		List<SSBMStxx01> list = (List<SSBMStxx01>) call.get("result");
		
		if(!list.isEmpty()) {
			liaisonId = list.get(0).getLiaisonId();
		}else {
			result.setSuccess(false);
			result.setRespMsg("未查询到食堂联络人信息！");
			return result;
		}
		billInfo.setCurrentDealer(liaisonId);
		billInfo.setBillId(patientBill.getId());
		billInfo.setCreatorId(patientBill.getRecCreator());
		billInfo.setCreatorName(patientBill.getUserName());
		billInfo.setPboCode("patient_cash");
		billInfo.setHandleAdvice("提交");
		billInfo.setOprationRoute("支付成功确认状态");
		System.out.println(billInfo.toString());
		/**1.判断rejectCode是否为3,是的话表示订单因为异常情况被关闭，status_code:99->02,rejectcode:null,rejectReason:null*/
		String rejectCode = patientBill.getRejectCode();
		//设置IsReject为0，支付成功的订单撤销作废状态
		billInfo.setIsReject("0");
		if(!StringUtils.isEmpty(rejectCode) && "3".equals(rejectCode)) {
			billInfo.setBeforeStatus("99");
		}
		/**2.变更订单状态为02已支付，rejectCode为null*/
		paramMap.put("billInfo", billInfo);
		EiInfo callStatusChange = LocalServiceUtil.call("PSPCStatusChange", "statusChange", paramMap);
		RespResult billStatusChangeResult = (RespResult) callStatusChange.get("result");
		System.out.println("状态变更:"+billStatusChangeResult.getRespCode()+billStatusChangeResult.getRespMsg());
		if("200".equals(billStatusChangeResult.getRespCode())) {
			//更新状态变更时间
			HashMap<String,String> map = new HashMap<String, String>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map.put("billId", patientBill.getId());
			map.put("revisor", patientBill.getRecCreator());
			map.put("reviseTime", sdf.format(new Date()));
			//更新订单主表
			paramMap.put("sql","PSPCTmealOrderBillPatient.updateReviseTime");
			paramMap.put("map",map);
			EiInfo callUpdateReviseTime = LocalServiceUtil.call("SSBMTy", "updateSqlByMap", paramMap);
			
			System.out.println("更新订单状态变更时间" + callUpdateReviseTime.get("success"));
			
			/**3.保存PSPCTmealOperation操作记录表*/
			PSPCTmealOperation operateRecord = new PSPCTmealOperation();
			operateRecord.setId(UUIDUtils.getUUID32());
			operateRecord.setOperationTime(CalendarUtils.dateTimeFormat(new Date()));
			operateRecord.setBillNo(patientBill.getBillNo());
			operateRecord.setCreatorId(patientBill.getRecCreator());
			operateRecord.setCreatorName(patientBill.getUserName());
			operateRecord.setOperationRoute("02");
			paramMap.put("sql","PSPCTmealOperation.insert");
			paramMap.put("domain",operateRecord);
			EiInfo callInsertOperation = LocalServiceUtil.call("SSBMTy", "insertSqlByDomain", paramMap);
			System.out.println("订单消费记录保存Operation:"+Boolean.parseBoolean(StringUtil.toString(callInsertOperation.get("success"))));
			if(!Boolean.parseBoolean(StringUtil.toString(callInsertOperation.get("success")))) {
				result.setSuccess(false);
				result.setRespCode("201");
				result.setRespMsg("订单消费记录Operation保存失败！");
			}
			
		}else {
			result.setSuccess(false);
			result.setRespCode(billStatusChangeResult.getRespCode());
			result.setRespMsg(billStatusChangeResult.getRespMsg());
		}
		return result;
	}
	

	/**
	 * 
     * posPayByScanOnline POS机扫码线上支付
     * *入参：JSON订单数据json，clinetIp提交终端ip地址
     * *出餐：EiInfo(ResultData)
     * 1.查询订单信息
     * 2.校验订单状态，若状态为02，则表示重复操作，返回false，提示该订单已支付
     * 3.更新订单支付类型
     * 4.构建订单明细
     * 5.根据不同的支付类型调用相应的支付方法：微信支付payByWechat，支付宝支付payByAli，一卡通payByCard
	 *
	 * @Title: posPayByScanOnline 
	 * @param inInfo
	 * @return
	 * @throws Exception 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:36:12
	 */
	public EiInfo posPayByScanOnline(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		EiInfo outInfo = new EiInfo();
		// 食堂编号
		String json = StringUtil.toString(attr.get("json"));
		String clinetIp = StringUtil.toString(attr.get("clinetIp"));
		ResultData j = new ResultData();
		if (StringUtils.isBlank(json)) {
			j.setSuccess(false);
			j.setRespMsg("参数json为空，请检查!");
			outInfo.set("result", j);
			return outInfo;
		}
		JSONObject ja = JSONObject.fromObject(json);

		// 用户付款码
		String openId = ja.getString("openId");
		String authCode = ja.getString("authCode");
		String userId = StringUtil.toString(attr.get("userId"));
		// String billId = ja.getString("billId");
		String str = ja.getString("str");
		double totalPrice = ja.getDouble("billTotalPrice");
		System.out.println("扫码收款参数:openId:" + openId + ",authCode:" + authCode + ",str:" + str);
		String canteenNum = "";
		String canteenName = "";
		String payDetailName = "";
		String billNos = "";
		PSPCTmealOrderBillPatient mainOrder = null;
		// 统一支付代码
		String payNo = "";
		// 总金额
		BigDecimal billTotalPrice = new BigDecimal("0.00");
		String archiveFlag = "";
		String ip = clinetIp;
		try {
		    for (String billInfo : str.split(";")) {
		        if (StringUtils.isNotBlank(billInfo)) {
		            String[] bill = billInfo.split(",");
		            billNos += bill[1] + ",";
		            /**1.查询订单信息*/
		            HashMap<String,Object> paramMap = new HashMap<String, Object>();
		            paramMap.put("sql","PSPCTmealOrderBillPatient.queryBillByBillNo");
		            paramMap.put("str",bill[1]);
		            EiInfo callQueryPatient = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);
		            List<PSPCTmealOrderBillPatient> lstResult = (List<PSPCTmealOrderBillPatient>) callQueryPatient.get("result");
		            if (lstResult.isEmpty()) {
		                j.setSuccess(false);
		                j.setRespMsg("订单不存在，请检查参数!");
		                outInfo.set("result", j);
		                return outInfo;
		            }
		            /**2.校验订单状态，若状态为02，则表示重复操作，返回false，提示该订单已支付*/
		            System.out.println("状态：" + lstResult.get(0).getStatusCode());
		            if (!"00".equals(lstResult.get(0).getStatusCode())) {
						if("99".equals(lstResult.get(0).getStatusCode()) && lstResult.get(0).getRejectCode() != null){
							j.setSuccess(false);
							j.setRespMsg("该订单超时未支付已被退单，请返回重新下单！");
							outInfo.set("result", j);
							return outInfo;
						}
		                j.setSuccess(false);
		                j.setRespMsg("该订单已支付过，无需再次发起支付！");
		                outInfo.set("result", j);
		                return outInfo;
		            }

					/**校验订餐时间*/
					HashMap<String,Object> map2 = new HashMap<String, Object>();
					if(!"POS".equals(lstResult.get(0).getArchiveFlag())) {
						map2.put("canteenNum",lstResult.get(0).getCanteenNum());
						map2.put("mealNum",lstResult.get(0).getMealNum());
						map2.put("needDate",lstResult.get(0).getNeedDate());
						String reqSendTime = lstResult.get(0).getReqSendTime();
						if (reqSendTime != null && reqSendTime.length() >= 5){
							map2.put("reqSendTime",reqSendTime.substring(reqSendTime.length() - 5));
						}else {
							map2.put("reqSendTime","00:00");
						}

						EiInfo callSendTime = LocalServiceUtil.call("PSPCDDJY01", "checkSendTime", map2);
						boolean success = (boolean) callSendTime.get("success");
						System.out.println("支付时校验送餐时间"+lstResult.get(0).getBillNo()+":" + success);
						if (!success) {
							j.setSuccess(success);
							j.setRespMsg(StringUtil.toString(callSendTime.get("respMsg")));
							outInfo.set("result", j);
							return outInfo;
						}
					}

					mainOrder = lstResult.get(0);
		            System.out.println("订单信息"+mainOrder);
		            //订单标识
		            archiveFlag = mainOrder.getArchiveFlag();
		            //获取支付类型
		            Map<String, Object> payMap = new HashMap<String, Object>();
		            payMap.put("billNo", bill[1]);
		            payMap.put("payType", ja.getString("payType"));
		            List<Map<String, String>> appPayTypeList = CommGlobalConfig.getAppPayTypeList();
		            for (Map<String, String> map : appPayTypeList) {
		                if (ja.getString("payType").equals(map.get("payTypeNum"))
		                    && "bhst".equals(map.get("canteenType"))) {
		                    payMap.put("payTypeName", map.get("payTypeName"));
		                }
		            }
		            /**3.更新订单支付类型*/
		            paramMap.put("sql","PSPCTmealOrderBillPatient.updatePayType");
		            paramMap.put("map",payMap);
		            EiInfo callUpdatePayType = null;
		            try {
		                callUpdatePayType = LocalServiceUtil.call("SSBMTy", "updateSqlByMap", paramMap);
		            } catch (Exception e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            }
		            
		            if(!Boolean.parseBoolean(StringUtil.toString(callUpdatePayType.get("success")))){
		                j.setSuccess(false);
		                j.setRespMsg("更新订单支付类型失败！");
		                outInfo.set("result", j);
		                return outInfo;
		            }
		            
		            payNo = lstResult.get(0).getPayNo();
		            canteenNum = lstResult.get(0).getCanteenNum();
		            canteenName = lstResult.get(0).getCanteenName();
		            // 订单总金额
		            billTotalPrice = billTotalPrice.add(lstResult.get(0).getBillTotalPrice());
		            /** 4.构建订单明细*/
		            if (lstResult.size() > 0) {
		                paramMap.put("sql","PSPCTmealOrderMenu.getMenuInfoByBillNo");
		                paramMap.put("str",lstResult.get(0).getBillNo());
		                EiInfo callQueryMenuInfo = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);
		                List<PSPCTmealOrderMenu02> billMenuInfo = (List<PSPCTmealOrderMenu02>) callQueryMenuInfo.get("result");
		                for (PSPCTmealOrderMenu02 li : billMenuInfo) {
		                    payDetailName += (li.getMenuName() + " " + li.getMenuNumber() + "份，" + li.getMenuTotalPrice() + "元；");
		                }
		            }
					 //一卡通支持三单一起下，拆分的订单每一笔交易扣款一次
					 if("0301".equals(ja.getString("payType"))) {
						//刷卡支付
						j = payByCard(mainOrder,ja.getString("cardId"));
					}
		        }
		    }
			/**5.根据不同的支付类型调用相应的支付方法*/
			if ("0201".equals(ja.getString("payType"))) {
				//微信支付
				j = payByWechat(canteenNum, canteenName, payNo, payDetailName, billNos, ip, openId, billTotalPrice,archiveFlag);
			} else if ("0101".equals(ja.getString("payType"))) {
				//支付宝支付
				j = payByAli( canteenNum, canteenName,  payNo, billTotalPrice,  payDetailName, authCode,archiveFlag,userId);
			}

        } catch (Exception e) {
            e.printStackTrace();
            j.setSuccess(false);
            j.setRespMsg("出现异常"+e.getMessage());
        }
		// 在需要回滚的场景中设置传出 EiInfo 的 status=-1
		outInfo.setStatus(j.isSuccess() ? 1 : -1);
		System.out.println(j);
		outInfo.set("result", j);
		
		return outInfo;
	}


	/**
	 * 
     * payByAli支付宝支付
     * 1.组织订单信息,通过hessian提交到支付节点完成扣款
     * 2.接收支付节点返回的信息
	 *
	 * @Title: payByAli 
	 * @param canteenNum
	 * @param canteenName
	 * @param payNo
	 * @param billTotalPrice
	 * @param payDetailName
	 * @param authCode
	 * @param archiveFlag
	 * @param userId
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:37:21
	 */
	private ResultData payByAli(String canteenNum,String canteenName, String payNo,BigDecimal billTotalPrice,
								String payDetailName,String authCode, String archiveFlag,String userId) {
		ResultData j = new ResultData();
		//组装支付信息
		Map<String, String> param = new HashMap<String, String>();
		param.put("hospital_code", MealCommonConfig.getSmpConfig().getHospitalCodeAli());
		param.put("modul_code", "patient_ali");
		param.put("used_unit", canteenNum);
		param.put("used_unit_name", canteenName);
		param.put("bill_no", payNo);
		param.put("total_fee", billTotalPrice.toString());
		param.put("body", payDetailName);
		param.put("modul_type", "patientMeal");
		param.put("authCode", authCode);
		System.out.println("支付宝支付AliPaySender.createPosScanPay:" + param);
		//Hessian调起支付宝支付
		ResponseResult  result = null;
		System.out.println(archiveFlag + "支付宝支付AliPaySender.createPosScanPay:" + param);
		//判断支付方式是否为二维码
		if("POS".equals(archiveFlag)) {
			param.put("subject", "POS机病患订餐");
			result = AliPaySender.createPosScanPay(param);
		}else {
			if(!StringUtil.isBlank(userId)) {
				param.put("userId", userId);
			}
			param.put("subject", "ALI病患订餐");
			result = AliPaySender.createDirectPay(param);
		}
		System.out.println("getRespCode:" + result.getRespCode() + ",getRespMsg():" + result.getRespMsg());
		if (!"200".equals(result.getRespCode())) {
			j.setSuccess(false);
			j.setRespMsg(result.getRespMsg());
		} else {
			j.setSuccess(true);
			j.setRespMsg(result.getRespMsg());
		}
		return j;
	}
	
	/**
	 * 
     * payByWechat微信支付
     * 1.组织订单信息,通过hessian提交到支付节点完成扣款
     * 2.接收支付节点返回的信息
	 *
	 * @Title: payByWechat 
	 * @param canteenNum
	 * @param canteenName
	 * @param payNo
	 * @param payDetailName
	 * @param billNos
	 * @param ip
	 * @param openId
	 * @param totalPrice
	 * @param archiveFlag
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:37:39
	 */
	private ResultData payByWechat(String canteenNum, String canteenName, String payNo, 
			String payDetailName, String billNos, String ip, String openId, BigDecimal totalPrice,String archiveFlag) {
		ResultData j = new ResultData();
		// 构建支付信息
		BusinessInfoDTO busInfoDTO = new BusinessInfoDTO();
		busInfoDTO.setHospitalCode(MealCommonConfig.getSmpConfig().getHospitalCodeWechat());// 医院编码
		busInfoDTO.setModulCode("patient_wechat");// 模块编码
		busInfoDTO.setUsedUnitCode(canteenNum);// 食堂编码
		busInfoDTO.setUserUnitName(canteenName);// 食堂名称

		OrderRecord order = new OrderRecord();
		order.setOrderId(payNo);// 商品ID
		
		// bigdecimal,不可以直接用double类型new出来,会丢失精度 例如0.03的时候,new出来的是0.029999999 
		order.setTotalFee(new BigDecimal("100").multiply(new BigDecimal(totalPrice + "")).intValue());// 订单总价
		order.setProductDetail(payDetailName);// 订单详细描述（非必需）
		order.setBillNo(billNos.substring(0, billNos.length() - 1));// 订单号

		// Hessian调取微信支付
		System.out.println("POS微信支付WeChatPaySender.unifiedOrderWithJSAPI:" + order + busInfoDTO);
		System.out.println("ip:" + ip + "openId" + openId);
		ResultEntry resultEntry = null;
		if("POS".equals(archiveFlag) ) {
			order.setTradeType("MICROPAY");
			order.setProductDescription("POS机病患订餐");// 商品描述
		}else {
			order.setProductDescription("WECHAT病患订餐");// 商品描述
		}
		resultEntry = WeChatPayImpl.unifiedOrderWithJSAPI(order, busInfoDTO, ip, openId);
		System.out.println("RespCode:" + resultEntry.getRespCode() + ",RespMsg:" + resultEntry.getRespMsg());
		if ("500".equals(resultEntry.getRespCode() + "")) {
			j.setSuccess(false);
			j.setRespMsg("发起支付失败！");
			return j;
		}

		if ("1000".equals(resultEntry.getRespCode() + "")) {
			j.setSuccess(false);
			j.setRespMsg("重复支付！");
			return j;
		}
		if ("200".equals(resultEntry.getRespCode() + "")) {
			j.setSuccess(true);
			j.setRespMsg(resultEntry.getRespMsg());
			j.setObj(resultEntry.getData());
		} else {
			j.setSuccess(false);
			j.setRespMsg(j.getRespMsg());
			return j;
		}
		return j;
	}
	

    /**
     * payByCard一卡通支付
     *
     * @Title: payByCard 
     * @param mainOrder
     * @throws Exception 
     * @return: ResultData
	 * 1.查询电子账户信息
     * 2.组织订单信息,通过XLocalManager.call提交到一卡通服务完成扣款
     * 3.接收一卡通返回的信息，电子账户支付成功直接变更订单状态(草稿:00–>已确认:02)
     * 4.支付成功，保存订单操作记录
     */
    private ResultData payByCard(PSPCTmealOrderBillPatient mainOrder,String cardId) throws Exception {
        ResultData result = new ResultData();
        HashMap<String,Object> paramMap = new HashMap<String,Object>();
        /**1.查询电子账户信息*/
		if(StringUtil.isEmpty(cardId)){
			paramMap.put("cardUserCode", mainOrder.getOpenId());
		}else{
			//传递实体卡片信息的是刷卡机,查询物理卡编码获得住院号
			paramMap.put("cardBaseCode", cardId);
		}
        EiInfo callCardInfo = LocalServiceUtil.callNoTx("PSAEKPXX", "queryCardInfo", paramMap);
        List cardInfos = (List) callCardInfo.getAttr().get("cardInfo");
        if(cardInfos == null || cardInfos.size() < 1) {
            result.setSuccess(false);
            result.setRespMsg("未查询到开卡信息！");
            return result;
        }
        JSONObject json = JSONObject.fromObject(cardInfos.get(0));
        paramMap.put("cardBaseCode",json.getString("cardBaseCode"));
        paramMap.put("consumeBillId",mainOrder.getBillNo());
        paramMap.put("consumeType","0");
        BigDecimal bignum2 = new BigDecimal("100");  
        paramMap.put("consumeMoney",mainOrder.getBillTotalPrice().multiply(bignum2).toBigInteger().toString());
        
        paramMap.put("consumeDeviceCode","app");
        paramMap.put("canteenNo",mainOrder.getCanteenNum());
        paramMap.put("canteenName",mainOrder.getCanteenName());
        /**2.组织订单信息,通过XLocalManager.call提交到一卡通服务完成扣款*/
        EiInfo callCardPay = LocalServiceUtil.call("PSAEConsumeRecord", "handleOrderFoot", paramMap);
		result = (ResultData) LocalServiceUtil.beanCastByJson(callCardPay.get("result"),ResultData.class);
		if(result.isSuccess()) {
            /**3.接收一卡通返回的信息，电子账户支付成功直接变更订单状态(草稿:00–>已确认:02)*/
            PSPCBillStatusInfo billInfo = new PSPCBillStatusInfo();
            billInfo.setPboTbName("sc_order_bill_patient");
            billInfo.setBeforeStatus("00");
            billInfo.setAfterStatus("02");
            //查询食堂联络人
            String liaisonId = "";
            paramMap.put("sql","SSBMStxx01.queryCanteenByCanteenNum");
            paramMap.put("str",mainOrder.getCanteenNum());
            EiInfo call = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);
            List<SSBMStxx01> list = (List<SSBMStxx01>) call.get("result");
            
            if(!list.isEmpty()) {
                liaisonId = list.get(0).getLiaisonId();
            }else {
                result.setSuccess(false);
                result.setRespMsg("未查询到食堂联络人信息！");
                return result;
            }
            billInfo.setCurrentDealer(liaisonId);
            billInfo.setBillId(mainOrder.getId());
            billInfo.setCreatorId(mainOrder.getRecCreator());
            billInfo.setCreatorName(mainOrder.getUserName());
            billInfo.setPboCode("patient_card");
            billInfo.setHandleAdvice("提交");
            billInfo.setOprationRoute("支付成功确认状态");
            System.out.println(billInfo.toString());
            //判断rejectCode是否为3,是的话表示订单因为异常情况被关闭，status_code:99->02,rejectcode:null,rejectReason:null
            String rejectCode = mainOrder.getRejectCode();
            //设置IsReject为0，支付成功的订单撤销作废状态
            billInfo.setIsReject("0");
            if(!StringUtils.isEmpty(rejectCode) && "3".equals(rejectCode)) {
                billInfo.setBeforeStatus("99");
            }
            //变更订单状态(草稿:00–>已确认:02)
            paramMap.put("billInfo", billInfo);
            EiInfo callStatusChange = LocalServiceUtil.call("PSPCStatusChange", "statusChange", paramMap);
            RespResult billStatusChangeResult = (RespResult) callStatusChange.get("result");
            System.out.println("状态变更:"+billStatusChangeResult.getRespCode()+billStatusChangeResult.getRespMsg());
            if("200".equals(billStatusChangeResult.getRespCode())) {
                //更新状态变更时间
                HashMap<String,String> map = new HashMap<String, String>();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                map.put("billId", mainOrder.getId());
                map.put("revisor", mainOrder.getRecCreator());
                map.put("reviseTime", sdf.format(new Date()));
				if(StringUtil.isNotEmpty(cardId)){
					//有卡片ID需要记录付款卡的住院号
					map.put("payId", json.getString("cardUserCode"));
				}else{
					//没有卡片ID需要记录下单人的住院号
					map.put("payId", mainOrder.getOpenId());
				}
				//更新订单主表
				paramMap.put("sql","PSPCTmealOrderBillPatient.updateReviseTimeAndPayId");
                paramMap.put("map",map);
                EiInfo callUpdateReviseTime = LocalServiceUtil.call("SSBMTy", "updateSqlByMap", paramMap);
                
                System.out.println("更新订单状态变更时间" + callUpdateReviseTime.get("success"));
                
                /**4.支付成功，保存订单操作记录表PSPCTmealOperation*/
                PSPCTmealOperation operateRecord = new PSPCTmealOperation();
                operateRecord.setId(UUIDUtils.getUUID32());
                operateRecord.setOperationTime(CalendarUtils.dateTimeFormat(new Date()));
                operateRecord.setBillNo(mainOrder.getBillNo());
                operateRecord.setCreatorId(mainOrder.getRecCreator());
                operateRecord.setCreatorName(mainOrder.getUserName());
                operateRecord.setOperationRoute("02");
                paramMap.put("sql","PSPCTmealOperation.insert");
                paramMap.put("domain",operateRecord);
                EiInfo callInsertOperation = LocalServiceUtil.call("SSBMTy", "insertSqlByDomain", paramMap);
                System.out.println("订单消费记录保存Operation:"+Boolean.parseBoolean(StringUtil.toString(callInsertOperation.get("success"))));
                if(!Boolean.parseBoolean(StringUtil.toString(callInsertOperation.get("success")))) {
                    result.setSuccess(false);
                    result.setRespCode("201");
                    result.setRespMsg("订单消费记录Operation保存失败！");
                }
                
            }else {
                result.setSuccess(false);
                result.setRespCode(billStatusChangeResult.getRespCode());
                result.setRespMsg(billStatusChangeResult.getRespMsg());
            }
        }
        return result;
    }

	/**
	 *
	 * patientAppRecharge APP线上充值
	 * *入参：userNo充值账号，consumeMoney充值金额，clinetIp提交终端ip地址
	 * *出餐：EiInfo(ResultData)
	 * 1.查询卡片信息
	 * 2.校验卡片状态，若状态为处于使用状态中1030才可以充值
	 * 3.构建充值明细
	 * 4.根据不同的支付类型调用相应的支付方法：一卡通支付payByCard，微信支付payByWechat，支付宝支付payByAli
	 * 5.保存充值记录到sc_card_recharge_info充值记录表
	 *
	 * @Title: workAppRecharge
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:12:15
	 */
	public EiInfo patientAppRecharge(EiInfo inInfo){
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		EiInfo outInfo = new EiInfo();
		// 订单编号
		String userNo = StringUtil.toString(attr.get("userNo"));
		System.out.println("----------------------工号" + userNo + "发起充值");
		log("----------------------工号" + userNo + "发起充值");
		//支付类型
		String payType = StringUtil.toString(attr.get("payType"));
		String userId = StringUtil.toString(attr.get("userId"));
		String openId = StringUtil.toString(attr.get("openId"));
		String ip = StringUtil.toString(attr.get("clinetIp"));
		String consumeMoney = StringUtil.toString(attr.get("consumeMoney"));
		String archiveFlag = "";
		ResultData j = new ResultData();
		if (StringUtils.isBlank(userNo)) {
			j.setSuccess(false);
			j.setRespMsg("参数userNo为空，请检查!");
			outInfo.set("result", j);
			return outInfo;
		}else if(StringUtils.isBlank(consumeMoney)) {
			j.setSuccess(false);
			j.setRespMsg("参数充值金额为空，请检查!");
			outInfo.set("result", j);
			return outInfo;
		}
		//充值金额 -- 格式化
		BigDecimal consumeBig = new BigDecimal("0");
		BigDecimal num = new BigDecimal("0.01");
		try {
			consumeBig = new BigDecimal(consumeMoney);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setRespMsg("充值金额格式错误！");
			outInfo.set("result", j);
			return outInfo;
		}

		/**1.查询卡片信息**/
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("cardUserCode", userNo);
		EiInfo callCardInfo = LocalServiceUtil.callNoTx("PSAEKPXX", "queryCardInfo", paramMap);
		List<DaoEPBase> cardInfos = (List<DaoEPBase>) callCardInfo.getAttr().get("cardInfo");
		if(cardInfos == null || cardInfos.size() < 1) {
			j.setSuccess(false);
			j.setRespMsg("未查询到开卡信息！");
			outInfo.set("result", j);
			return outInfo;
		}
		//List<HashMap> parseArray = JSON.parseArray(cardInfos.toString(),HashMap.class);
		Map<String,Object> cardInfo = cardInfos.get(0).toMap();
		/**2.校验卡片状态，若状态为处于使用状态中1030才可以充值**/
		if(!("1030").equals(StringUtil.toString(cardInfo.get("cardStatus")))) {
			j.setSuccess(false);
			j.setRespMsg("卡片不在使用状态,请检查！");
			outInfo.set("result", j);
			return outInfo;
		}
		try {
			/**3.构建充值明细**/
			String dateTime = CalendarUtils.dateTimeFormat(new Date());
			String payDetailName = dateTime + "APP充值" + consumeMoney;
			SSACTscCardRechargeInfo rechargeInfo = new SSACTscCardRechargeInfo();
			rechargeInfo.setId(UUIDUtils.getUUID32());
			rechargeInfo.setTradeNo(UUIDUtils.getUUID32());
			rechargeInfo.setCreateTime(dateTime);
			rechargeInfo.setCardId(StringUtil.toString(cardInfo.get("cardBaseCode")));
			rechargeInfo.setCardNo(StringUtil.toString(cardInfo.get("cardBusinessCode")));
			rechargeInfo.setCanteenNum(MealCommonConfig.getSmpConfig().getPatientCanteenNum());
			rechargeInfo.setCardBalanceBefore(new BigDecimal(StringUtil.toString(cardInfo.get("cardBalance"))).multiply(num));
			rechargeInfo.setHosptialNo(MealCommonConfig.getSmpConfig().getHospitalCodeAli());
			rechargeInfo.setBillType("patient");
			rechargeInfo.setRechargeType(payType);
			rechargeInfo.setCardRechargeMoney(consumeBig);
			rechargeInfo.setWorkNo(userNo);
			rechargeInfo.setFlag("00");
			//保存充值记录，状态为00待充值
			paramMap.put("sql","SSACTscCardRechargeInfo.insert");
			paramMap.put("domain",rechargeInfo);
			EiInfo call = LocalServiceUtil.call("SSBMTy", "insertSqlByDomain", paramMap);
			if(!Boolean.parseBoolean(StringUtil.toString(call.get("success")))){
				j.setRespCode("201");
				j.setSuccess(false);
				j.setRespMsg("保存充值记录失败！");
				outInfo.set("result", j);
				return outInfo;
			}
			/**4.根据不同的支付类型调用相应的支付方法**/
			if ("0101".equals(payType)) {
				//支付宝充值
				j = rechargeByAli( rechargeInfo, payDetailName,userId, archiveFlag);
			} else if ("0201".equals(payType)) {
				//微信充值
				j = rechargeByWechat(rechargeInfo, payDetailName,ip,openId,archiveFlag);
			}
		} catch (Exception e) {
			j.setSuccess(false);
			j.setRespMsg(e.getMessage());
			e.printStackTrace();
		}
		// 在需要回滚的场景中设置传出 EiInfo 的 status=-1
		outInfo.setStatus(j.isSuccess() ? 1 : -1);
		System.out.println(j);
		outInfo.set("result", j);
		return outInfo;
	}


	/**
	 *
	 * rechargeByAli支付宝充值
	 * 1.组织订单信息,通过hessian提交到支付节点完成扣款
	 * 2.接收支付节点返回的信息
	 *
	 * @Title: rechargeByAli
	 * @param rechargeInfo
	 * @param payDetailName
	 * @param archiveFlag
	 * @return
	 * @throws Exception
	 * @return: ResultData
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:12:30
	 */
	public ResultData rechargeByAli(SSACTscCardRechargeInfo rechargeInfo, String payDetailName,String userId, String archiveFlag) throws Exception {
		ResultData j = new ResultData();
		Map<String,String> param = new HashMap<String, String>();
		/**1.组织订单信息,通过hessian提交到支付节点完成扣款*/
		param.put("subject", "病员卡充值");
		param.put("hospital_code", MealCommonConfig.getSmpConfig().getHospitalCodeAli());
		param.put("modul_code", "patient_recharge_ali");
		param.put("used_unit", rechargeInfo.getCanteenNum());
		param.put("used_unit_name", "在线充值");
		param.put("bill_no", rechargeInfo.getTradeNo());
		param.put("total_fee", Double.toString(rechargeInfo.getCardRechargeMoney().doubleValue()));
		param.put("body", payDetailName);
		param.put("userId", userId);
		System.out.println("---------------------------发起支付："+param);
		//调用hessian支付接口
		ResponseResult result = AliPaySender.createDirectPay(param);
		/**2.接收支付节点返回的信息*/
		System.out.println("getRespCode:" + result.getRespCode() + ",getRespMsg:" + result.getRespMsg());
		if (!"200".equals(result.getRespCode())) {
			j.setSuccess(false);
			j.setRespMsg(result.getRespMsg());
		} else {
			j.setSuccess(true);
			j.setRespMsg(result.getRespMsg());
		}
		return j;
	}



	/**
	 *
	 * rechargeByWechat微信充值
	 * 1.组织订单信息,通过hessian提交到支付节点完成扣款
	 * 2.接收支付节点返回的信息
	 *
	 * @Title: rechargeByWechat
	 * @param rechargeInfo
	 * @param payDetail
	 * @param ip
	 * @param archiveFlag
	 * @return
	 * @throws Exception
	 * @return: ResultData
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:12:51
	 */
	public ResultData rechargeByWechat(SSACTscCardRechargeInfo rechargeInfo,String payDetail,String ip,String openId,String archiveFlag) throws Exception{
		ResultData j = new ResultData();
		/**1.组织订单信息,通过hessian提交到支付节点完成扣款*/
		BusinessInfoDTO busInfoDTO = new BusinessInfoDTO();
		busInfoDTO.setHospitalCode(MealCommonConfig.getSmpConfig().getHospitalCodeWechat());//医院编码
		busInfoDTO.setModulCode("patient_recharge_wechat");//模块编码
		busInfoDTO.setUsedUnitCode(rechargeInfo.getCanteenNum());//食堂编码
		busInfoDTO.setUserUnitName("在线充值");//食堂名称
		System.out.println("busInfoDTO:"+ busInfoDTO.toString());

		OrderRecord order = new OrderRecord();
		order.setOrderId(rechargeInfo.getTradeNo());//商品ID
		order.setProductDescription("在线充值");//商品描述
		order.setTotalFee(new BigDecimal("100").multiply(new BigDecimal(rechargeInfo.getCardRechargeMoney() + "")).intValue());//订单总价
		order.setProductDetail(payDetail);//订单详细描述（非必需）
		order.setBillNo(rechargeInfo.getId());//订单号
		System.out.println("order:" + order.toString() );
		System.out.println(MealGlobalConfig.getSmpConfig().getCommonNodeUrlWechat() + MealGlobalConfig.getSmpConfig().getHessianPatternWechat());
		ResultEntry resultEntry = WeChatPayImpl.unifiedOrderWithJSAPI(order, busInfoDTO, ip, openId);
		/**2.接收支付节点返回的信息*/
		System.out.println("RespCode:" + resultEntry.getRespCode() + ",RespMsg:" + resultEntry.getRespMsg());
		if ("500".equals(resultEntry.getRespCode() + "")) {
			j.setSuccess(false);
			j.setRespMsg("发起支付失败！");
			return j;
		}

		if ("1000".equals(resultEntry.getRespCode() + "")) {
			j.setSuccess(false);
			j.setRespMsg("重复支付！");
			return j;
		}
		if ("200".equals(resultEntry.getRespCode() + "")) {
			j.setSuccess(true);
			j.setRespMsg(resultEntry.getRespMsg());
			j.setObj(resultEntry.getData());
		} else {
			j.setSuccess(false);
			j.setRespMsg(j.getRespMsg());
			return j;
		}
		return j;
	}

	/**
	 *
	 * 病员电子账户支付校验支付密码
	 *
	 * @Title: checkPayPassword
	 * @param inInfo
	 * @return
	 * @return: ResultData
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:28:11
	 */
	public EiInfo checkPayPassword(EiInfo inInfo){
		EiInfo outInfo = new EiInfo();
		ResultData result = new ResultData();
		try {
			HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
			// 食堂类型
			String userNo = StringUtil.toString(attr.get("userNo"));
			String passWord = StringUtil.toString(attr.get("passWord"));

			//查询登录人
			HashMap<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sql","PSPCTmealPatientCard.queryPatientInfoPrecise");
			paramMap.put("str",userNo);
			EiInfo callQueryPatient = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);

			List<PSPCTmealPatientCard> patients = (List<PSPCTmealPatientCard>) callQueryPatient.get("result");

			if(patients.isEmpty()){
				result.setRespMsg("该用户未注册！");
				result.setSuccess(false);
				return outInfo;
			} else {
				PSPCTmealPatientCard tMealPati = patients.get(0);

				String idenNo = tMealPati.getIdenNo();

				//校验密码
				if(StringUtil.isEmpty(idenNo)){
					result.setRespMsg("身份证信息缺失，请联系管理员！");
					result.setSuccess(false);
					result.setObj(tMealPati);
				} else if(passWord.equals(idenNo.substring(idenNo.length() - 6))){
					//默认支付密码为身份证后6位
					result.setRespMsg("验证成功！");
					result.setSuccess(true);
					result.setObj(tMealPati);
				} else {
					result.setRespMsg("密码错误！");
					result.setSuccess(false);
				}
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setRespCode("201");
			result.setRespMsg("程序异常"+e.getMessage());
		}
		outInfo.set("result", result);
		outInfo.setStatus(result.isSuccess() ? 1 : -1);
		System.out.println(result);
		return outInfo;
	}

//	public EiInfo getLoginMap(EiInfo inInfo) {
//		Map attr = inInfo.getAttr();
//		ResultData result = new ResultData();
//		String name = StringUtil.toString(attr.get("name"));
//		String contactTel = StringUtil.toString(attr.get("contactTel"));
//		String deptCode = StringUtil.toString(attr.get("deptCode"));
//		String deptName = StringUtil.toString(attr.get("deptName"));
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("name",name);
//		map.put("contactTel",contactTel);
//		map.put("deptCode",deptCode);
//		map.put("deptName",deptName);
////		System.out.println("8884");
////		System.out.println(mobile);
////		Map<String, Object> map= (Map<String, Object>) attr.get("map");
//		result.setObj(map);
//		System.out.println("8886");
//		System.out.println(map);
//		System.out.println(StringUtil.toString(attr.get("name")));
//		return inInfo;
//	}
}
