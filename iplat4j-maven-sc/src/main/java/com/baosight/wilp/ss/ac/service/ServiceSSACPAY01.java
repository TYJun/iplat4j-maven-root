package com.baosight.wilp.ss.ac.service;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ps.pc.domain.PSPCBillStatusInfo;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOrderBillPatient;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOrderMenu02;
import com.baosight.wilp.ss.ac.domain.SSACTscCardRechargeInfo;
import com.baosight.wilp.ss.ac.domain.SSACTscWorkOrderBill;
import com.baosight.wilp.ss.ac.domain.SSACTscWorkOrderBillDetail;
import com.baosight.wilp.ss.bm.config.CommGlobalConfig;
import com.baosight.wilp.ss.bm.config.MealCommonConfig;
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
 * ServiceSSACPAY01 职工支付service
 * 
 * @Title: ServiceSSACPAY01.java
 * @ClassName: ServiceSSACPAY01
 * @Package：com.baosight.wilp.ss.ac.service
 * @author: liutao
 * @date: 2021年9月9日 下午3:10:59
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSACPAY01 extends ServiceBase {

	
    /**
     * 
     * workAppPay 职工APP线上支付
     * *入参：JSON订单数据json，clinetIp提交终端ip地址
     * *出餐：EiInfo(ResultData)
     * 1.查询订单信息
     * 2.校验订单状态，若状态为02，则表示重复操作，返回false，提示该订单已支付
     * 3.构建订单明细
     * 4.根据不同的支付类型调用相应的支付方法：一卡通支付payByCard，微信支付payByWechat，支付宝支付payByAli
     *
     * @Title: workAppPay 
     * @param inInfo
     * @return 
     * @return: EiInfo 
     * @author: liutao
     * @date: 2021年9月9日 下午3:11:16
     */
	public EiInfo workAppPay(EiInfo inInfo){
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		EiInfo outInfo = new EiInfo();
		// 订单编号
		String billNo = StringUtil.toString(attr.get("billNo"));
		System.out.println("----------------------订单" + billNo + "发起支付");
		//支付类型
		String payType = StringUtil.toString(attr.get("payType"));
		String ip = StringUtil.toString(attr.get("clinetIp"));
		String openId = StringUtil.toString(attr.get("openId"));
		String archiveFlag = "";
		ResultData j = new ResultData();
		if (StringUtils.isBlank(billNo)) {
			j.setSuccess(false);
			j.setRespMsg("参数billNo为空，请检查!");
			outInfo.set("result", j);
			return outInfo;
		}
		
		/**1.查询订单信息**/
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sql","SSACTscWorkOrderBill.queryByBillNo");
		paramMap.put("str",billNo);
		EiInfo callQueryBillInfo = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);
		List<SSACTscWorkOrderBill> result = (List<SSACTscWorkOrderBill>) callQueryBillInfo.get("result");
		if(result == null || result.size() == 0) {
			j.setSuccess(false);
			j.setRespMsg("该参数未查询到相关单据信息，请检查参数!");
			outInfo.set("result", j);
			return outInfo;
		}
		SSACTscWorkOrderBill mainOrder = result.get(0);
		/**2.校验订单状态**/
		if(!("00").equals(mainOrder.getStatusCode())) {
            j.setSuccess(false);
            j.setRespMsg("此单据已支付成功，无需再次请求！");
            outInfo.set("result", j);
			return outInfo;
        }
		if (StringUtils.isNotBlank(payType) && !payType.equals(mainOrder.getPayType())) {
			j.setSuccess(false);
			j.setRespMsg("支付方式不匹配，请检查!");
			outInfo.set("result", j);
			return outInfo;
		}
		try {
			/**3.构建订单明细**/
			paramMap.put("sql","SSACTscWorkOrderBillDetail.queryDetailByBillNo");
			paramMap.put("str",mainOrder.getBillNo());
			EiInfo callQueryMenuInfo = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);
			List<SSACTscWorkOrderBillDetail> billMenuInfos = (List<SSACTscWorkOrderBillDetail>) callQueryMenuInfo.get("result");
			String payDetailName = "";
			for (SSACTscWorkOrderBillDetail li : billMenuInfos) {
				payDetailName += (li.getMenuName() + " " + li.getMenuNumber() + "份，" + li.getMenuTotalPrice() + "元；");
			}
			
			/**4.根据不同的支付类型调用相应的支付方法**/
			if ("0201".equals(payType)) {
				//微信支付
				j = payByWechat(mainOrder, payDetailName,ip,archiveFlag, openId);
			} else if ("0101".equals(payType)) {
				//支付宝支付
				j = payByAli( mainOrder, payDetailName, archiveFlag);
			}else if("0401".equals(payType)) {
				j = payByCard(mainOrder);
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
		String str = ja.getString("str");
		double totalPrice = ja.getDouble("billTotalPrice");
		System.out.println("扫码收款参数:openId:" + openId + ",authCode:" + authCode + ",str:" + str);
		String canteenNum = "";
		String canteenName = "";
		String payDetailName = "";
		String billNos = "";
		SSACTscWorkOrderBill mainOrder = null;
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
					paramMap.put("sql","SSACTscWorkOrderBill.queryByBillNo");
					paramMap.put("str",bill[1]);
					EiInfo callQueryPatient = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);
					List<SSACTscWorkOrderBill> lstResult = (List<SSACTscWorkOrderBill>) callQueryPatient.get("result");
					if (lstResult.isEmpty()) {
						j.setSuccess(false);
						j.setRespMsg("订单不存在，请检查参数!");
						outInfo.set("result", j);
						return outInfo;
					}
					/**2.校验订单状态，若状态为02，则表示重复操作，返回false，提示该订单已支付*/
					System.out.println("状态：" + lstResult.get(0).getStatusCode());
					if (!"00".equals(lstResult.get(0).getStatusCode())) {
						j.setSuccess(false);
						j.setRespMsg("该订单已支付过，无需再次发起支付！");
						outInfo.set("result", j);
						return outInfo;
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
						paramMap.put("sql","SSACTscWorkOrderBillDetail.queryDetailByBillNo");
						paramMap.put("str",lstResult.get(0).getBillNo());
						EiInfo callQueryMenuInfo = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);
						List<SSACTscWorkOrderBillDetail> billMenuInfo = (List<SSACTscWorkOrderBillDetail>) callQueryMenuInfo.get("result");
						for (SSACTscWorkOrderBillDetail li : billMenuInfo) {
							payDetailName += (li.getMenuName() + " " + li.getMenuNumber() + "份，" + li.getMenuTotalPrice() + "元；");
						}
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
		busInfoDTO.setModulCode("work_wechat");// 模块编码
		busInfoDTO.setUsedUnitCode(canteenNum);// 食堂编码
		busInfoDTO.setUserUnitName(canteenName);// 食堂名称

		OrderRecord order = new OrderRecord();
		order.setOrderId(payNo);// 商品ID

		// bigdecimal,不可以直接用double类型new出来,会丢失精度 例如0.03的时候,new出来的是0.029999999
		order.setTotalFee(new BigDecimal("100").multiply(new BigDecimal(totalPrice + "")).intValue());// 订单总价
		order.setProductDetail(payDetailName);// 订单详细描述（非必需）
		order.setBillNo(billNos.substring(0, billNos.length() - 1));// 订单号

		// Hessian调取微信支付
		System.out.println("微信支付WeChatPaySender.unifiedOrderWithJSAPI:" + order + busInfoDTO);
		System.out.println("ip:" + ip + "openId" + openId);
		order.setProductDescription("职工APP订餐");// 商品描述
		ResultEntry resultEntry = WeChatPayImpl.unifiedOrderWithJSAPI(order, busInfoDTO, ip, openId);
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
		param.put("modul_code", "work_ali");
		param.put("used_unit", canteenNum);
		param.put("used_unit_name", canteenName);
		param.put("bill_no", payNo);
		param.put("total_fee", billTotalPrice.toString());
		param.put("body", payDetailName);
		param.put("modul_type", "workMeal");
		param.put("authCode", authCode);
		System.out.println("支付宝支付AliPaySender.createPosScanPay:" + param);
		//Hessian调起支付宝支付
		ResponseResult  result = null;
		System.out.println(archiveFlag + "支付宝支付AliPaySender.createPosScanPay:" + param);
		if(!StringUtil.isBlank(userId)) {
			param.put("userId", userId);
		}
		param.put("subject", "ALI职工订餐");
		result = AliPaySender.createDirectPay(param);

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
     * payByCard一卡通支付
	 * 1.查询电子账户信息
     * 2.组织订单信息,通过XLocalManager.call提交到一卡通服务完成扣款
     * 3.接收一卡通返回的信息，电子账户支付成功直接变更订单状态(草稿:00–>已确认:02)
	 * 4.变更订单状态
	 * @Title: payByCard 
	 * @param mainOrder
	 * @return
	 * @throws Exception 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:11:32
	 */
	private ResultData payByCard(SSACTscWorkOrderBill mainOrder) throws Exception {
		ResultData j = new ResultData();
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		/**1.查询电子账户信息*/
		paramMap.put("cardUserCode", mainOrder.getOpenId());
		EiInfo callCardInfo = LocalServiceUtil.callNoTx("SSAEKPXX", "queryCardInfo", paramMap);
		List cardInfos = (List) callCardInfo.getAttr().get("cardInfo");
		if(cardInfos == null || cardInfos.size() < 1) {
			j.setSuccess(false);
			j.setRespMsg("未查询到开卡信息！");
			return j;
		}
		JSONObject json = JSONObject.fromObject(cardInfos.get(0));
		paramMap.put("cardBaseCode",json.getString("cardBaseCode"));
		paramMap.put("consumeBillId",mainOrder.getBillNo());
		paramMap.put("consumeType","0");
		BigDecimal bignum2 = new BigDecimal("100");  
		paramMap.put("consumeMoney",mainOrder.getBillTotalPrice().multiply(bignum2).toBigInteger().toString());
		paramMap.put("consumeDeviceCode","app");
		//paramMap.put("consumeDeviceCode",consumeDeviceCode);
		paramMap.put("canteenNo",mainOrder.getCanteenNum());
		paramMap.put("canteenName",mainOrder.getCanteenName());
		/**2.组织订单信息,通过XLocalManager.call提交到一卡通服务完成扣款*/
		EiInfo callCardPay = LocalServiceUtil.call("SSAEConsumeRecord", "handleOrderFoot", paramMap);
		j = (ResultData) LocalServiceUtil.beanCastByJson(callCardPay.get("result"),ResultData.class);
		//j = (ResultData) callCardPay.get("result");
		if(j.isSuccess()) {
			/**3.接收一卡通返回的信息，电子账户支付成功直接变更订单状态(草稿:00–>已确认:02)*/
			PSPCBillStatusInfo billInfo = new PSPCBillStatusInfo();
			billInfo.setPboTbName("sc_work_order_bill");
			billInfo.setBeforeStatus("00");
			billInfo.setAfterStatus("02");
			billInfo.setCurrentDealer(mainOrder.getCurrentDealer());
			billInfo.setBillId(mainOrder.getId());
			billInfo.setCreatorId(mainOrder.getRecCreator());
			billInfo.setCreatorName(mainOrder.getUserName());
			billInfo.setPboCode("MEAL");
			billInfo.setHandleAdvice("提交");
			billInfo.setOprationRoute("支付成功确认状态");
			System.out.println(billInfo.toString());
			//订单被关闭，status_code:99->02,rejectcode:null,rejectReason:null
			String rejectCode = mainOrder.getRejectCode();
			//判断订单撤销状态
			if(!StringUtils.isEmpty(rejectCode) && "3".equals(rejectCode)) {
				//草稿状态作废，更改rejectCode为3->1
				billInfo.setIsReject("1");
				billInfo.setRejectCode("1");
				billInfo.setRejectReason(mainOrder.getRejectReason());
				billInfo.setBeforeStatus("99");
			}else if(!StringUtils.isEmpty(rejectCode) && "4".equals(rejectCode)) {
				//系统作废，更改rejectCode为4->null,rejectReason:null
				billInfo.setIsReject("0");
				billInfo.setBeforeStatus("99");
			}
			/**4.变更订单状态*/
			paramMap.put("billInfo", billInfo);
			EiInfo callStatusChange = LocalServiceUtil.call("PSPCStatusChange", "statusChange", paramMap);
			RespResult billStatusChangeResult = (RespResult) callStatusChange.get("result");
			System.out.println(StringUtil.separator+"状态变更:"+billStatusChangeResult.getRespCode()+billStatusChangeResult.getRespMsg());
			if("200".equals(billStatusChangeResult.getRespCode())) {
				//更新状态变更时间
				HashMap<String,String> map = new HashMap<String, String>();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				map.put("billId", mainOrder.getId());
				map.put("revisor", mainOrder.getRecCreator());
				map.put("reviseTime", sdf.format(new Date()));
				map.put("cardId", json.getString("cardBaseCode"));
				//更新订单主表
				paramMap.put("sql","SSACTscWorkOrderBill.updateReviseTime");
				paramMap.put("map",map);
				EiInfo callUpdateReviseTime = LocalServiceUtil.call("SSBMTy", "updateSqlByMap", paramMap);
				
				System.out.println(StringUtil.separator+"更新订单状态变更时间" + callUpdateReviseTime.get("success"));
			}else {
				j.setRespCode(billStatusChangeResult.getRespCode());
				j.setRespMsg(billStatusChangeResult.getRespMsg());
				j.setSuccess(false);
			}
		}
		return j;
	}
	

	/**
	 * 
     * payByAli支付宝支付
     * 1.组织订单信息,通过hessian提交到支付节点完成扣款
     * 2.接收支付节点返回的信息
	 *
	 * @Title: payByAli 
	 * @param mainOrder
	 * @param payDetailName
	 * @param archiveFlag
	 * @return
	 * @throws Exception 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:11:50
	 */
	private ResultData payByAli(SSACTscWorkOrderBill mainOrder, String payDetailName, String archiveFlag) throws Exception {
		ResultData j = new ResultData();
		Map<String,String> param = new HashMap<String, String>();
		/**1.组织订单信息,通过hessian提交到支付节点完成扣款*/
		String billNo = mainOrder.getBillNo();
		param.put("subject", "职工订餐");
		param.put("modul_type", "meal");
        param.put("hospital_code", MealCommonConfig.getSmpConfig().getHospitalCodeAli());
        param.put("modul_code", "work_ali");
        param.put("used_unit", mainOrder.getCanteenNum());
        param.put("used_unit_name", mainOrder.getCanteenName());
        param.put("bill_no", mainOrder.getPayNo());
        param.put("total_fee", Double.toString(mainOrder.getBillTotalPrice().doubleValue()));
        param.put("body", payDetailName);
        System.out.println("---------------------------发起支付："+param);
        ResponseResult result = AliPaySender.createWapPay(param);
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
     * payByWechat微信支付
     * 1.组织订单信息,通过hessian提交到支付节点完成扣款
     * 2.接收支付节点返回的信息
	 *
	 * @Title: payByWechat 
	 * @param mainOrder
	 * @param payDetail
	 * @param ip
	 * @param archiveFlag
	 * @return
	 * @throws Exception 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:12:02
	 */
	private ResultData payByWechat(SSACTscWorkOrderBill mainOrder,String payDetail,String ip,String archiveFlag, String openId) throws Exception{
		ResultData j = new ResultData();
		/**1.组织订单信息,通过hessian提交到支付节点完成扣款*/
        BusinessInfoDTO busInfoDTO = new BusinessInfoDTO();
        busInfoDTO.setHospitalCode(MealCommonConfig.getSmpConfig().getHospitalCodeWechat());//医院编码
        busInfoDTO.setModulCode("work_wechat");//模块编码
        busInfoDTO.setUsedUnitCode(mainOrder.getCanteenNum());//食堂编码
        busInfoDTO.setUserUnitName(mainOrder.getCanteenName());//食堂名称
        System.out.println("busInfoDTO:"+ busInfoDTO.toString());

        OrderRecord order = new OrderRecord();
        order.setOrderId(mainOrder.getPayNo());//商品ID
        order.setProductDescription("职工APP订餐");//商品描述
        order.setTotalFee(new BigDecimal("100").multiply(new BigDecimal(mainOrder.getBillTotalPrice() + "")).intValue());//订单总价
        order.setProductDetail(payDetail);//订单详细描述（非必需）
        order.setBillNo(mainOrder.getBillNo());//订单号
        System.out.println("order:" + order.toString() );
        System.out.println(MealGlobalConfig.getSmpConfig().getCommonNodeUrlWechat() + MealGlobalConfig.getSmpConfig().getHessianPatternWechat());
		ResultEntry resultEntry = WeChatPayImpl.unifiedOrderWithH5(order, busInfoDTO, ip);
		//ResultEntry resultEntry = WeChatPayImpl.unifiedOrderWithJSAPI(order, busInfoDTO, ip, openId);
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
     * workAppRecharge 职工APP线上充值
     * *入参：workNo充值工号，consumeMoney充值金额，clinetIp提交终端ip地址
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
	public EiInfo workAppRecharge(EiInfo inInfo){
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		EiInfo outInfo = new EiInfo();
		// 订单编号
		String workNo = StringUtil.toString(attr.get("workNo"));
		System.out.println("----------------------工号" + workNo + "发起充值");
		//支付类型
		String payType = StringUtil.toString(attr.get("payType"));
		String ip = StringUtil.toString(attr.get("clinetIp"));
		String consumeMoney = StringUtil.toString(attr.get("consumeMoney"));
		String archiveFlag = "";
		ResultData j = new ResultData();
		if (StringUtils.isBlank(workNo)) {
			j.setSuccess(false);
			j.setRespMsg("参数工号为空，请检查!");
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
		//查询电子账户信息
		paramMap.put("cardUserCode", workNo);
		EiInfo callCardInfo = LocalServiceUtil.callNoTx("SSAEKPXX", "queryCardInfo", paramMap);
		List<DaoEPBase> cardInfos = (List<DaoEPBase>) callCardInfo.getAttr().get("cardInfo");
		if(cardInfos == null || cardInfos.size() < 1) {
			j.setSuccess(false);
			j.setRespMsg("未查询到开卡信息！");
			outInfo.set("result", j);
			return outInfo;
		}
		//List<HashMap> parseArray = JSON.parseArray(cardInfos.toString(),HashMap.class);
		Map<String,Object> cardInfo = cardInfos.get(0).toMap();
		/**2.校验卡片状态**/
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
			rechargeInfo.setCanteenNum(MealCommonConfig.getSmpConfig().getCanteenNum());
			rechargeInfo.setCardBalanceBefore(new BigDecimal(StringUtil.toString(cardInfo.get("cardBalance"))).multiply(num));
			rechargeInfo.setHosptialNo(MealCommonConfig.getSmpConfig().getHospitalCodeAli());
			rechargeInfo.setBillType("work");
			rechargeInfo.setRechargeType(payType);
			rechargeInfo.setCardRechargeMoney(consumeBig);
			rechargeInfo.setWorkNo(workNo);
			rechargeInfo.setFlag("00");
			/**5.保存充值记录到sc_card_recharge_info充值记录表，状态为00待充值-*/
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
				j = rechargeByAli( rechargeInfo, payDetailName, archiveFlag);
			} else if ("0201".equals(payType)) {
				//微信充值
				j = rechargeByWechat(rechargeInfo, payDetailName,ip,archiveFlag);
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
	public ResultData rechargeByAli(SSACTscCardRechargeInfo rechargeInfo, String payDetailName, String archiveFlag) throws Exception {
		ResultData j = new ResultData();
		/**1.组织订单信息,通过hessian提交到支付节点完成扣款*/
		Map<String,String> param = new HashMap<String, String>();
		param.put("subject", "职工卡充值");
		param.put("hospital_code", MealCommonConfig.getSmpConfig().getHospitalCodeAli());
		param.put("modul_code", "work_recharge_ali");
		param.put("used_unit", rechargeInfo.getCanteenNum());
		param.put("used_unit_name", "在线充值");
		param.put("bill_no", rechargeInfo.getTradeNo());
		param.put("total_fee", Double.toString(rechargeInfo.getCardRechargeMoney().doubleValue()));
		param.put("body", payDetailName);
		System.out.println("---------------------------发起支付："+param);
		ResponseResult result = AliPaySender.createWapPay(param);
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
	public ResultData rechargeByWechat(SSACTscCardRechargeInfo rechargeInfo,String payDetail,String ip,String archiveFlag) throws Exception{
		ResultData j = new ResultData();
		/**1.组织订单信息,通过hessian提交到支付节点完成扣款*/
		BusinessInfoDTO busInfoDTO = new BusinessInfoDTO();
		busInfoDTO.setHospitalCode(MealCommonConfig.getSmpConfig().getHospitalCodeWechat());//医院编码
		busInfoDTO.setModulCode("work_recharge_wechat");//模块编码
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
		ResultEntry resultEntry = WeChatPayImpl.unifiedOrderWithH5(order, busInfoDTO, ip);
		/***2.接收支付节点返回的信息*/
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

}
