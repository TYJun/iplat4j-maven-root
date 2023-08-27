package com.baosight.wilp.ss.ac.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ps.pc.domain.PSPCBillStatusInfo;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOrderMiddle;
import com.baosight.wilp.ps.pc.domain.PSPCTorderEval;
import com.baosight.wilp.ss.ac.domain.*;
import com.baosight.wilp.ss.bm.config.CommGlobalConfig;
import com.baosight.wilp.ss.bm.config.MealCommonConfig;
import com.baosight.wilp.ss.bm.domain.SSBMDcsj01;
import com.baosight.wilp.ss.bm.domain.SSBMStxx01;
import com.baosight.wilp.ss.bm.utils.*;
import com.bonawise.domain.base.BusinessInfoDTO;
import com.bonawise.domain.base.ResultEntry;
import com.bonawise.smp.ResponseResult;
import com.bonawise.smp.alipay.hessian.AliPaySender;
import com.bonawise.smp.weChat.hessian.WeChatPayImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 
ServiceSSACDDGL01 职工订单管理service
 * 
 * @Title: ServiceSSACDDGL01.java
 * @ClassName: ServiceSSACDDGL01
 * @Package：com.baosight.wilp.ss.ac.service
 * @author: liutao
 * @date: 2021年9月9日 下午3:08:33
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSACDDGL01 extends ServiceBase {
	

    /**
     * 
     * 撤销订单
     * *入参：订单编号pBillNo，用户编号userCode，撤单原因rejectReason
     * *出餐：EiInfo(result:respMsg操作信息，respCode状态码)
     * 1.查询订单信息
     * 2.校验订单状态是否需要执行退单，校验不通过则返回校验结果
     * 3.判断食堂是否开启在线退款配置，若开启则进入退款环节
     * 4.判断订单的支付类型，不同支付类型的订单进入不同的退单方法
     * 5.查询订单是否支付成功，已支付的订单通知支付节点发起退款
     * 6.变更订单状态，statusCode->99订单结束，rejectCode->2确认退单
     * 7.返回菜品数量
     *
     * @Title: cancelOrder 
     * @param inInfo
     * @return 
     * @return: EiInfo 
     * @author: liutao
     * @date: 2021年9月9日 下午3:08:46
     */
	@SuppressWarnings("unchecked")
	public EiInfo cancelOrder(EiInfo inInfo){
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		if(attr == null || attr.isEmpty()) {
			//iplat PC端页面传参直接获取
			attr =  (HashMap<String, Object>) inInfo.getAttr();
		}
		String pBillNo = StringUtil.toString(attr.get("pBillNo"));
		String userCode = StringUtil.toString(attr.get("userCode"));
		String rejectReason = StringUtil.toString(attr.get("rejectReason"));
        String workNo = StringUtil.toString(attr.get("workNo"));
		dao.update("SSACTscWorkOrderBill.updateCancelSubsidy",workNo);
		//作废状态，1：作废待确认，2：确认作废，3：系统作废
		String rejectCode = StringUtil.toString(attr.get("rejectCode"));
		rejectCode = StringUtil.isBlank(rejectCode) ? "2" : rejectCode;
		EiInfo outInfo = new EiInfo();
		ResultData j = new ResultData();

		String message = "";
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		System.out.println(StringUtil.separator + "cancelOrder！start! " + attr);
		if (StringUtil.isNotEmpty(pBillNo)) {
			try {
				/**1.查询订单信息*/
				HashMap<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("sql","SSACTscWorkOrderBill.queryByBillNo");
				paramMap.put("str",pBillNo);
				EiInfo callQueryPatient = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
				List<SSACTscWorkOrderBill> bills = (List<SSACTscWorkOrderBill>) callQueryPatient.get("result");
				SSACTscWorkOrderBill bill = bills.get(0);
				
				String curDealer = bill.getCurrentDealer();
				String billNo = bill.getBillNo();
				//查询card_code
				paramMap.put("sql","SSACTscWorkOrderBillDetail.queryDetailByBillNo");
				paramMap.put("str",billNo);
				EiInfo callQueryDetail = LocalServiceUtil.call("SSBMTy", "querySqlForListByString", paramMap);
				
				List<SSACTscWorkOrderBillDetail> orderMenuList = (List<SSACTscWorkOrderBillDetail>)callQueryDetail.get("result");
				String cardId = bill.getCardId();
				String currentDealer = bill.getCurrentDealer();
				List<String> tempList = Arrays.asList(currentDealer.split(","));

				//判断当前时间是否超过送餐时间
				String sdate = "";
				//查询餐次时间配置
				Map<String,Object> sendTimeMap = new HashMap<String, Object>();
				sendTimeMap.put("mealNum",bill.getMealNum());
				sendTimeMap.put("canteenNum",bill.getCanteenNum());
				List<SSBMDcsj01> sendTimeList = dao.query("SSBMDcsj01.query", sendTimeMap);
				//拼接订单送餐时间
				sdate = bill.getNeedDate() + " " +bill.getReqSendTime()+":00";
				//餐次提前时间
				Integer advanceTime = sendTimeList.get(0).getAdvanceTime();
				//判断当前时间是否超过送餐时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date start = sdf.parse(sdate);
				long startTime = start.getTime() - advanceTime*60*1000;
				Date end = new Date();
				//判断当前时间与需求送餐时间的差值
				long between = (end.getTime() - startTime)/1000;
				long min = between/60;

				/**2.校验订单状态是否需要执行退单，校验不通过则返回校验结果*/
				if (StringUtil.isNotEmpty(userCode) 
						&& !userCode.equals(bill.getRecCreator()) 
						&& !tempList.contains(userCode) && !"admin".equals(userCode)) {
					j.setSuccess(false);
					j.setRespMsg("非单据创建人或食堂管理员不可取消订单！");
				} else if ("99".equals(bill.getStatusCode())) {
					j.setSuccess(false);
					j.setRespMsg("该订单已结束，不可进行作废操作");
				}
//				else if (!"POS".equals(bill.getArchiveFlag()) && !"0101".equals(bill.getPayType())
//						&& !"0201".equals(bill.getPayType()) && StringUtil.isBlank(cardId)) {
//					j.setSuccess(false);
//					j.setRespMsg("此单据未查询到消费卡号，请检查");
//				}
				else if("03".equals(bill.getPayType()) && !tempList.contains(userCode)) {
					//03状态正在配送，只能联系食堂管理员撤单
					j.setSuccess(false);
					j.setRespMsg("正在配送，无法取消订单！");
				}
				else if(min > 1 && !tempList.contains(userCode)){
					//不是管理员且超过送餐时间
					j.setSuccess(false);
					j.setRespMsg("订单已超出退单时间");
				}
				else {
					//组织订单信息,等待更改状态
					PSPCBillStatusInfo billInfo = new PSPCBillStatusInfo();
					billInfo.setPboTbName("sc_work_order_bill");
					billInfo.setBeforeStatus(bill.getStatusCode());
					billInfo.setAfterStatus("99");
					billInfo.setCurrentDealer(curDealer);
					billInfo.setBillId(bill.getId());
					billInfo.setCreatorId(bill.getRecCreator());
					billInfo.setCreatorName(bill.getUserName());
					billInfo.setPboCode("MEAL");
					billInfo.setHandleAdvice("退款");
					billInfo.setIsReject("1");
					billInfo.setRejectCode(rejectCode);
					billInfo.setOprationRoute("订单退款");
					rejectReason = StringUtil.isBlank(rejectReason) ? "订单退款" : rejectReason;
					billInfo.setRejectReason(rejectReason);
					RespResult billStatusChangeResult = new RespResult("201","failed");
					System.out.println("billInfo:" + billInfo.toString());
					//2草稿状态不可退款其余状态都可退款
					int sc = Integer.parseInt(bill.getStatusCode());
					if (sc >= 0) {
						/**3.判断是否有在线退款配置，且处于开启状态*/
						String backMoney = "onLineBack";
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("mealgroupTypeCode", backMoney);
						List<HashMap<String,Object>> list = dao.query("SSBMStxx01.getMealTypeData", map);
						String onLineBack = StringUtil.toString(list.get(0).get("typeCode"));
						System.out.println("-----------正在进入配置typeCode-----------" + onLineBack);
						if (list.size() > 0 ) {
							String totalPrice = bill.getBillTotalPrice().toString();
							billNo = bill.getBillNo();
							String payType1 = bill.getPayType().substring(0, 2);
							System.out.println("-----------正在进入退款环节payType-----------" + payType1 + "00".equals(payType1));
							/**4.判断订单的支付类型，不同支付类型的订单进入不同的退单方法*/
							if("04".equals(payType1)) {
								if(sc == 0) {
									//未支付且状态为草稿变更订单状态，4:草稿退单
									billInfo.setRejectCode("4");
									paramMap.put("billInfo", billInfo);
									//启用新事务,保证退款成功时状态变更不会被整体事务回滚
									EiInfo callStatusChange = LocalServiceUtil.call("PSPCStatusChange", "statusChange", paramMap);
									billStatusChangeResult = (RespResult) callStatusChange.get("result");
									System.out.println("订单退款：" + bill.getBillNo() + billStatusChangeResult.getRespMsg());
								}else{
									//一卡通退款
									paramMap.put("cardUserCode", bill.getOpenId());
									/**5.查询订单是否支付成功，已支付的订单执行退款*/
									EiInfo callCardInfo = LocalServiceUtil.callNoTx("SSAEKPXX", "queryCardInfo", paramMap);
									List cardInfos = (List) callCardInfo.getAttr().get("cardInfo");
									JSONObject json = JSONObject.fromObject(cardInfos.get(0));
									paramMap.put("cardBaseCode",json.getString("cardBaseCode"));
									paramMap.put("consumeBillId",bill.getBillNo());
									paramMap.put("consumeType","1");
									BigDecimal bignum2 = new BigDecimal("100");
									paramMap.put("consumeMoney",bill.getBillTotalPrice().multiply(bignum2).toBigInteger().toString());

									//paramMap.put("consumeDeviceCode",consumeDeviceCode);
									paramMap.put("canteenNo",bill.getCanteenNum());
									paramMap.put("canteenName",bill.getCanteenName());
									EiInfo callCardBack = LocalServiceUtil.call("SSAEConsumeRecord", "handleOrderFoot", paramMap);
									//ResultData resultEntry = (ResultData) callCardBack.get("result");
									ResultData resultEntry = (ResultData) LocalServiceUtil.beanCastByJson(callCardBack.get("result"),ResultData.class);
									if (!resultEntry.isSuccess()) {
										//退款失败
										j.setSuccess(false);
										j.setRespCode(resultEntry.getRespCode().toString());
										j.setRespMsg(resultEntry.getRespMsg());
										outInfo.set("result", j);
										outInfo.setStatus(j.isSuccess() ? 1 : -1);
										return outInfo;
									}else {
										/**6.变更订单状态，statusCode->99订单结束，rejectCode->2确认退单*/
										paramMap.put("billInfo", billInfo);
										//启用新事务,保证退款成功时状态变更不会被整体事务回滚
										EiInfo callStatusChange = LocalServiceUtil.callNewTx("PSPCStatusChange", "statusChange", paramMap);
										billStatusChangeResult = (RespResult) callStatusChange.get("result");
										System.out.println("订单退款：" + bill.getBillNo() + billStatusChangeResult.getRespMsg());
									}
								}
							}else if ("00".equals(payType1)){
								/**6.变更订单状态，statusCode->99订单结束，rejectCode->2确认退单*/
								System.out.println(payType1+StringUtil.separator);
								//现金支付直接变更订单状态
								paramMap.put("billInfo", billInfo);
								//启用新事务,保证退款成功时状态变更不会被整体事务回滚
								EiInfo callStatusChange = LocalServiceUtil.callNewTx("PSPCStatusChange", "statusChange", paramMap);
								billStatusChangeResult = (RespResult) callStatusChange.get("result");
								System.out.println("订单退款：" + bill.getBillNo() + billStatusChangeResult.getRespMsg());
							}else if ("01".equals(payType1) && "1".equals(onLineBack)) {
								// 支付宝退款
								Map<String,String> aiReMap = new HashMap<String,String>();
								String hospitalCodeAli = MealCommonConfig.getSmpConfig().getHospitalCodeAli();
								aiReMap.put("bill_no", bill.getPayNo());
								aiReMap.put("fee", totalPrice);
								aiReMap.put("hospital_code", hospitalCodeAli);
								aiReMap.put("modul_code", "work_ali");
								aiReMap.put("memo", "");
								aiReMap.put("outRequestNo", bill.getBillNo());
								System.out.println("--------------开始支付宝退款,查询支付宝交易HospitalCode:" + hospitalCodeAli + ",payNo:" + bill.getPayNo());
								ResponseResult payresult = AliPaySender.singleTradeQuery(hospitalCodeAli, "work_ali", bill.getPayNo());
								System.out.println("-----------支付宝交易结果" + payresult.getRespCode() + "交易信息:" + payresult.getRespMsg());
								/**5.查询订单是否支付成功，已支付的订单通知支付节点发起退款*/
								if ("200".equals(payresult.getRespCode())) {
									updateGetOperationHistory(billInfo.toMap());
									System.out.println("aiReMap:" + aiReMap);
									//订单已支付,调用支付节点退款方法
									ResponseResult resultEntry = AliPaySender.refundFastPay(aiReMap);
									System.out.println("退款结果:" + resultEntry);
									if (!"200".equals(resultEntry.getRespCode())) {
										
										j.setSuccess(false);
										j.setRespCode(resultEntry.getRespCode());
										j.setRespMsg(resultEntry.getRespMsg());
										outInfo.set("result", j);
										outInfo.setStatus(j.isSuccess() ? 1 : -1);
										return outInfo;
									}else {
										/**6.变更订单状态，statusCode->99订单结束，rejectCode->2确认退单*/
										paramMap.put("billInfo", billInfo);
										//启用新事务,保证退款成功时状态变更不会被整体事务回滚
										EiInfo callStatusChange = LocalServiceUtil.callNewTx("PSPCStatusChange", "statusChange", paramMap);
										billStatusChangeResult = (RespResult) callStatusChange.get("result");
										System.out.println("订单退款：" + bill.getBillNo() + billStatusChangeResult.getRespMsg());
									}
								} else {
									if(sc == 0) {
										//未支付且状态为草稿变更订单状态，4:草稿退单
										billInfo.setRejectCode("4");
										paramMap.put("billInfo", billInfo);
										//启用新事务,保证退款成功时状态变更不会被整体事务回滚
										EiInfo callStatusChange = LocalServiceUtil.call("PSPCStatusChange", "statusChange", paramMap);
										billStatusChangeResult = (RespResult) callStatusChange.get("result");
										System.out.println("订单退款：" + bill.getBillNo() + billStatusChangeResult.getRespMsg());
									}
								}
								String moneyLeft = "无法获取余额";
								returnMap.put("moneyLeft", moneyLeft);
							} else if ("02".equals(payType1) && "1".equals(onLineBack)) {
								// 微信退款
								String hospitalCodeWx = MealCommonConfig.getSmpConfig().getHospitalCodeWechat();
								Integer price = bill.getBillTotalPrice().multiply(new BigDecimal(100)).intValue();
								BusinessInfoDTO busInfoDTO = new BusinessInfoDTO();
								busInfoDTO.setHospitalCode(hospitalCodeWx);
								busInfoDTO.setModulCode("work_wechat");
								busInfoDTO.setUsedUnitCode(bill.getCanteenNum());
								busInfoDTO.setUserUnitName(bill.getCanteenName());
								System.out.println("--------------开始微信退款,查询微信交易HospitalCode:" + hospitalCodeWx + ",payNo():" + bill.getPayNo());
								//微信查询支付状态
								ResultEntry weChatResult = WeChatPayImpl.OrderQuery(bill.getPayNo(), busInfoDTO);
								System.out.println("-----------微信交易结果" + weChatResult.getRespCode() + "交易信息:" + weChatResult.getRespMsg());
								/**5.查询订单是否支付成功，已支付的订单通知支付节点发起退款*/
								if(weChatResult.getRespCode() == 200) {
									updateGetOperationHistory(billInfo.toMap());
									//订单已支付,调用支付节点退款方法
									ResultEntry resultEntry = WeChatPayImpl.refund(busInfoDTO, bill.getPayNo(), price);
									if (!"200".equals(resultEntry.getRespCode().toString())) {
										//退款失败
										j.setSuccess(false);
										j.setRespCode(resultEntry.getRespCode().toString());
										j.setRespMsg(resultEntry.getRespMsg());
										outInfo.set("result", j);
										outInfo.setStatus(j.isSuccess() ? 1 : -1);
										return outInfo;
									}else {
										/**6.变更订单状态，statusCode->99订单结束，rejectCode->2确认退单*/
										paramMap.put("billInfo", billInfo);
										//启用新事务,保证退款成功时状态变更不会被整体事务回滚
										EiInfo callStatusChange = LocalServiceUtil.callNewTx("PSPCStatusChange", "statusChange", paramMap);
										billStatusChangeResult = (RespResult) callStatusChange.get("result");
										System.out.println("订单退款：" + bill.getBillNo() + billStatusChangeResult.getRespMsg());
									}
								}else {
									if(sc == 0) {
										//未支付且状态为草稿变更订单状态，4:草稿退单
										billInfo.setRejectCode("4");
										paramMap.put("billInfo", billInfo);
										//启用新事务,保证退款成功时状态变更不会被整体事务回滚
										EiInfo callStatusChange = LocalServiceUtil.callNewTx("PSPCStatusChange", "statusChange", paramMap);
										billStatusChangeResult = (RespResult) callStatusChange.get("result");
										System.out.println("订单退款：" + bill.getBillNo() + billStatusChangeResult.getRespMsg());
									}
								}
								String moneyLeft = "无法获取余额";
								returnMap.put("moneyLeft", moneyLeft);
							}
						}
					}
					
					String respCode = billStatusChangeResult.getRespCode();
					if (!respCode.equals("200")) {
						j.setSuccess(false);
						message = billStatusChangeResult.getRespMsg();
					} else {
						/**7.返回菜品数量*/
						updateMenuNumCancel(bill,orderMenuList);
					}
					message = bill.getId() + "," + billNo + "," + bill.getBillTotalPrice();
					j.setRespMsg(message);
				}
	
				returnMap.put("canteenNum", bill.getCanteenNum());
				returnMap.put("mealNum", bill.getMealNum());
				j.setObj(returnMap);
			} catch (Exception e) {
				e.printStackTrace();
				j.setSuccess(false);
				j.setRespMsg("程序异常！");
				outInfo.setStatus(j.isSuccess() ? 1 : -1);
				outInfo.set("result", j);
				return outInfo;
			}
		} else {
			j.setSuccess(false);
			j.setRespMsg("该订单不存在");
			outInfo.setStatus(j.isSuccess() ? 1 : -1);
			outInfo.set("result", j);
			return outInfo;
		}
		outInfo.setMsg(j.getRespMsg());
		outInfo.setStatus(j.isSuccess() ? 1 : -1);
		outInfo.set("result", j);
		return outInfo;
	}
	

	/**
	 * 
     * 退单时返回菜品剩余数量
	 *
	 * @Title: updateMenuNumCancel 
	 * @param bill
	 * @param orderMenuList 
	 * @return: void 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:09:05
	 */
	public void updateMenuNumCancel(SSACTscWorkOrderBill bill, List<SSACTscWorkOrderBillDetail> orderMenuList){

		//用餐时间 餐次 菜品编码 菜品数量 食堂
		String mealDate = bill.getNeedDate();
		String mealNum = bill.getMealNum();
		String canteenNum= bill.getCanteenNum();
		//构建更新参数
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("canteenNum", canteenNum);
		map.put("mealDate", mealDate);
		map.put("mealNum", mealNum);
		for (SSACTscWorkOrderBillDetail orderMenu : orderMenuList) {
			String menuNum = orderMenu.getMenuNum();
			Integer num = orderMenu.getMenuNumber();
			map.put("menuNum", menuNum);
			map.put("num", num);
			try {
				HashMap<String,Object> paramMap = new HashMap<String, Object>();
				//更新菜品排班表
				paramMap.put("sql", "SSBMCpsl01.updateMenuScheSurplus");
				paramMap.put("map", map);
				System.out.println("订单菜品信息:"+paramMap);
				EiInfo call = LocalServiceUtil.call("SSBMTy", "updateSqlByMap", paramMap);
				System.out.println("取消订单菜品数量:"+Boolean.parseBoolean(StringUtil.toString(call.get("success"))));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Autowired
	private SpringValidatorAdapter validator;

	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *
	 * @Title: validate校验参数
	 * @Param form
	 * @return:
	 */
	private void validate(Object form) {
        Set<?> violations = validator.validate(form);
        if (violations.size() > 0) {
            throw new ConstraintViolationException((Set<ConstraintViolation<?>>) violations);
        }
    }
	

	/**
	 * 
     * 保存订单
     * 入参：用户名userName,联系电话userTelNumber,住院号hospitalNo,总价billTotalPrice,
     *  食堂编码canteenNum,餐次编码mealNum,需要时间needDate
     *  早餐明细billDetailAM,午餐明细billDetailPM,晚餐明细billDetailMM
     * 出餐：是否成功success,返回信息respMsg
     *  1.获得提交的订单信息，校验参数，校验不通过返回参数校验信息
        2.将json字符串组装成实体类，失败时返回出错信息
        3.查询职工信息，若存在则更新其信息，不存在则新增信息，操作失败，返回失败信息
        4.校验菜品排班，校验失败时返回失败信息
        5.保存订单主表和订单详情信息，保存失败，执行回滚，返回失败信息
	 *
	 * @Title: saveWork 
	 * @param inInfo
	 * @return
	 * @throws Exception 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:09:18
	 */
	@SuppressWarnings({ "unchecked" })
	public EiInfo saveWork(EiInfo inInfo) throws Exception {
		EiInfo outInfo = new EiInfo();
		ResultData j = new ResultData();
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 订餐参数
		String jsonStr = StringUtil.toString(attr.get("json"));
		System.out.println("保存职工订单jsonStr:"+jsonStr);
		//标识
		String flag = StringUtil.toString(attr.get("flag"));
		flag = StringUtil.isEmpty(flag) ? "ZQ" : flag;
		/**1.获得提交的订单信息，校验参数，校验不通过返回参数校验信息*/
		if (StringUtil.isBlank(jsonStr)) {
			j.setSuccess(false);
			j.setRespMsg("参数json为空，请检查!");
			j.setRespCode("201");
			outInfo.setStatus(j.isSuccess() ? 1 : -1);
			outInfo.set("result", j);
			return outInfo;
		}
		System.out.println("下单时json: " + jsonStr);
		JSONObject json = JSONObject.fromObject(jsonStr);

		SSACTscWorkOrderBill mainOrder = (SSACTscWorkOrderBill) JSONObject.toBean(json, SSACTscWorkOrderBill.class);
		validate(mainOrder);
		
		// 拼接needDate
		String today = CalendarUtils.dateFormat(new Date());
		Calendar cd = Calendar.getInstance();
		String str = mainOrder.getNeedDate();
		String date = "";
		if(("Y").equals(str)) {
			cd.add(cd.DATE, 1);
			date = CalendarUtils.dateFormat(cd.getTime());
		} else if(("D").equals(str)) {
			date = today;
		}else{
			date=str;
		}
		mainOrder.setNeedDate(date);
		mainOrder.setRoom(mainOrder.getBedNo());
		//mainOrder.setAddress(mainOrder.getBuildingName());
		/** 2.将json字符串组装成实体类，失败时返回出错信息*/
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("canteenNum",mainOrder.getCanteenNum());
		map.put("mealNum",mainOrder.getMealNum());
		map.put("needDate",mainOrder.getNeedDate());
		map.put("reqSendTime",mainOrder.getReqSendTime());
		map.put("workNo",mainOrder.getOpenId());
		map.put("reqSendTime",mainOrder.getReqSendTime());
		EiInfo callSendTime = LocalServiceUtil.call("PSPCDDJY01", "checkSendTime", map);
		boolean success = (boolean) callSendTime.get("success");
		System.out.println("校验送餐时间"+mainOrder.getBillNo()+":" + success);
		if (!success) {
			j.setSuccess(success);
			j.setRespMsg(StringUtil.toString(callSendTime.get("respMsg")));
			j.setRespCode("201");
			outInfo.setStatus(j.isSuccess() ? 1 : -1);
			outInfo.set("result", j);
			return outInfo;
		}
		
		HashMap<String,Object> queryMap = new HashMap<>();
		queryMap.put("mainOrder",mainOrder);
		SSACTscWorkOrderBillDetail[] detailArr = mainOrder.getBillDetail();

		SSACTscOrderWorkerInfo userInfo = new SSACTscOrderWorkerInfo();
		/**3.查询职工信息，若存在则更新其信息，不存在则新增信息，操作失败，返回失败信息*/
	    j = buildUserParam(mainOrder,userInfo);
	    /**4.校验菜品排班，校验失败时返回失败信息*/
	    HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("listDetail",detailArr);
		EiInfo call = LocalServiceUtil.call("PSPCDDJY01", "checkWorkSelfSchema", paramMap);
		boolean b = (boolean) call.get("success");  
		if (!b) {
			j.setSuccess(false);
			j.setRespMsg("所选菜品不在排班范围之内，请重新选择!");
			j.setRespCode("201");
			outInfo.setStatus(j.isSuccess() ? 1 : -1);
			outInfo.set("result", j);
			return outInfo;
		}

		HashMap<String, String> ma = new HashMap<String,String>();
		ma.put("canteenNum", mainOrder.getCanteenNum());
		ma.put("mealNum", mainOrder.getMealNum());

		//支付类型
		String payType = mainOrder.getPayType();
		//查询食堂联络人
		paramMap.put("sql","SSBMStxx01.queryCanteenByCanteenNum");
		paramMap.put("str",mainOrder.getCanteenNum());
		EiInfo callDeler = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
		List<SSBMStxx01> list = (List<SSBMStxx01>) callDeler.get("result");
		String current = "";
		if(!list.isEmpty()) {
			current = list.get(0).getLiaisonId();
		}else {
			j.setSuccess(false);
			j.setRespMsg("未查询到食堂联络人信息！");
			outInfo.set("result", j);
			outInfo.setStatus(j.isSuccess() ? 1 : -1);
			return outInfo;
		}
		//设置食堂联络人
		mainOrder.setCurrentDealer(current);
		/**5.保存订单主表和订单详情信息，保存失败，执行回滚，返回失败信息*/
		List<SSACTscRechargeConsume> consumeEntityList = new ArrayList<SSACTscRechargeConsume>();
		j = dealOrder(j,mainOrder,consumeEntityList,flag);
		if(!"200".equals(j.getRespCode())){
			outInfo.set("result", j);
			outInfo.setStatus(j.isSuccess() ? 1 : -1);
			return outInfo;
		}
		if(payType.equals("0000")) {
			// 现金支付直接变更订单状态
		}
		//在需要回滚的场景中设置传出 EiInfo 的 status=-1
		outInfo.setStatus(j.isSuccess() ? 1 : -1);
		System.out.println(j.getRespMsg());
		outInfo.set("result", j);
		return outInfo;
	}


	/**
	 * 
     * buildUserParam组装用户信息的方法
	 *
	 * @Title: buildUserParam 
	 * @param orderEntity
	 * @param workInfo
	 * @return
	 * @throws Exception 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:09:33
	 */
	 private ResultData buildUserParam(SSACTscWorkOrderBill orderEntity,SSACTscOrderWorkerInfo workInfo) throws Exception{
		ResultData result = new ResultData();
		//将相关信息保存到workInfo
		workInfo.setId(UUIDUtils.getUUID32());
		workInfo.setOpenId(orderEntity.getOpenId());
		workInfo.setDeptName(orderEntity.getDeptName());
		workInfo.setFloor(orderEntity.getFloor());
		workInfo.setDeptNum(orderEntity.getDeptNum());
		workInfo.setUserNo(orderEntity.getUserCode());
		workInfo.setUserName(orderEntity.getUserName());
		workInfo.setAddress(orderEntity.getAddress());
		workInfo.setUserTelNumber(orderEntity.getUserTelNumber());
		workInfo.setBuilding(orderEntity.getBuilding());
		workInfo.setBuildingName(orderEntity.getBuildingName());
		workInfo.setRoom(orderEntity.getRoom());
		workInfo.setBedNo(orderEntity.getRoom());
		workInfo.setCanteenNum(orderEntity.getCanteenNum());
		workInfo.setRecReviseTime(CalendarUtils.dateTimeFormat(new Date()));
        //查询用户是否下过订单
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userNo", orderEntity.getUserCode());
        map.put("canteenNum", orderEntity.getCanteenNum());
        
        List<SSACTscOrderWorkerInfo> listWork = dao.query("SSACTscOrderWorkerInfo.query", map);
        HashMap<String,Object> paramMap = new HashMap<String, Object>();
        EiInfo call = null;
        String msg = "新增职工下单记录";
        if(!listWork.isEmpty()){
			//更新下单信息表
            paramMap.put("sql","SSACTscOrderWorkerInfo.updateByUserNo");
			paramMap.put("domain",workInfo);
			call = LocalServiceUtil.call("SSBMTy", "updateSqlByDomain", paramMap);
			msg = "更新职工下单记录";
        }else if(listWork.isEmpty()){
			//新增下单信息表
        	paramMap.put("sql","SSACTscOrderWorkerInfo.insert");
 			paramMap.put("domain",workInfo);
 			call = LocalServiceUtil.call("SSBMTy", "insertSqlByDomain", paramMap);
        }
        if(!Boolean.parseBoolean(StringUtil.toString(call.get("success")))){
        	result.setSuccess(false);
        	result.setRespCode("201");
        	result.setRespMsg(msg + "失败！");
			return result;
		}
        result.setSuccess(true);
        result.setRespCode("200");
        result.setRespMsg(msg + "成功！");
        return result;
    }
	

	 /**
	  * 
	  *  buildBillParam组装订单主体的方法
	  *
	  * @Title: buildBillParam 
	  * @param mealOrderBill
	  * @param consumeList 
	  * @return: void 
	  * @author: liutao
	  * @date: 2021年9月9日 下午3:09:46
	  */
	private void buildBillParam(SSACTscWorkOrderBill mealOrderBill,List<SSACTscRechargeConsume> consumeList)
		{
			SSACTscRechargeConsume billRecord = new SSACTscRechargeConsume();
			String spot = "";
			String id = UUIDUtils.getUUID32();
			//封装消费历史记录信息
			billRecord.setBillNo(mealOrderBill.getBillNo());
			//billRecord.setOrderMoney(mealOrderBill.getBillTotalPrice());
			//卡片优惠
	        HashMap<String ,String> map=new HashMap<String ,String>();
			map.put("mealgroupTypeCode", "billDiscount");
			List<HashMap<String,Object>> listTypeGroup = dao.query("SSBMStxx01.getMealTypeData", map);
			String typeCode = StringUtil.toString(listTypeGroup.get(0).get("typeCode"));
	        
	        if(StringUtil.isBlank(typeCode)){
	        	//没有优惠
	            //billRecord.setOrderMoney(mealOrderBill.getBillTotalPrice());
	        }else{
	            //有优惠mealOrderBill.getBillDiscount()
	            //BigDecimal disTotalPrice = mealOrderBill.getBillTotalPrice().multiply(new BigDecimal(mealOrderBill.getBillDiscount()));
	            //四舍五入
	            //billRecord.setOrderMoney(disTotalPrice.setScale(2, BigDecimal.ROUND_HALF_UP)); 
	        }
	        billRecord.setOrderMoney(mealOrderBill.getBillTotalPrice());
	        
			billRecord.setCardNo(mealOrderBill.getCardId());
			billRecord.setId(UUIDUtils.getUUID32());
			billRecord.setRecCreateTime(CalendarUtils.dateTimeFormat(new Date()));
			billRecord.setRecCreator(mealOrderBill.getRecCreator());
			billRecord.setRemark(mealOrderBill.getBillRemark());
			billRecord.setStatusCode("00");
			billRecord.setConsumeLot("1");
			consumeList.add(billRecord);
		}
	 

	/**
	 * 
     * dealOrder自取下单处理的方法
     * synchronized同步锁
     * 1.获取缓存中的最大订单号
     * 2.保存订单
	 * 3.保存订单详情
     * 4.更新菜品数量
	 * 5.保存消费记录
	 * @Title: dealOrder 
	 * @param j
	 * @param orderEntity
	 * @param consumeEntityList
	 * @param flag
	 * @return
	 * @throws Exception 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:09:58
	 */
    public synchronized ResultData dealOrder(ResultData j, SSACTscWorkOrderBill orderEntity,
    		List<SSACTscRechargeConsume> consumeEntityList,String flag) throws Exception {

        String num = "";
        /**1.获取缓存中的最大订单号*/
        QryBillNo(orderEntity.getNeedDate());
        String gc = CommGlobalConfig.getZqBillNoMapByDate(orderEntity.getNeedDate());
        System.out.println("职工最大订单号集合：" + CommGlobalConfig.getZqBillNoMap());
        int maxIndex = Integer.parseInt(gc) + 1;
        num = StringUtil.getNumberToString(maxIndex, 10, 5);
        String da = CalendarUtils.shortDateFormat(CalendarUtils.toShortDate(orderEntity.getNeedDate()));
        String billNo = "ZG" +da + num;
        if("ZQ".equals(flag)) {
        	//获取随机四位数取餐码
        	orderEntity.setSelfCode(UUIDUtils.fixedStirng(4));
        }else {
        	orderEntity.setSelfCode(null);
        }
        orderEntity.setRecCreateName(orderEntity.getUserName());
        orderEntity.setRecCreator(orderEntity.getOpenId());
        orderEntity.setBillNo(billNo);
        orderEntity.setId(UUIDUtils.getUUID32());
        orderEntity.setStatusCode("00");
        orderEntity.setPayNo(UUIDUtils.getUUID32());
        orderEntity.setRecCreateTime(CalendarUtils.dateTimeFormat(new Date()));
        j.setRespMsg(billNo);
        /**2.保存订单主表*/
        HashMap<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sql","SSACTscWorkOrderBill.insert");
		paramMap.put("domain",orderEntity);
		EiInfo callBill = LocalServiceUtil.call("SSBMTy", "insertSqlByDomain", paramMap);
		if(!Boolean.parseBoolean(StringUtil.toString(callBill.get("success")))){
			j.setSuccess(false);
			j.setRespMsg("保存订单主表失败！");
			j.setRespCode("201");
			return j;
		}
		
		SSACTscWorkOrderBillDetail[] billDetail = orderEntity.getBillDetail();
        /**3.保存订单详情*/
        for (int i = 0; i < billDetail.length; i++) {
        	SSACTscWorkOrderBillDetail detail = billDetail[i];
			//排班id赋值
			if(!StringUtil.isBlank(detail.getId())){
				//外送餐的排班id在id中，自取餐的排班id在scheId中
				detail.setScheId(detail.getId());
			}
        	detail.setId(UUIDUtils.getUUID32());
        	detail.setRecCreator(orderEntity.getRecCreator());
        	detail.setRecCreateTime(orderEntity.getRecCreateTime());
        	detail.setBillNo(billNo);
        	detail.setBillDetailNo(billNo+i);
            detail.setMenuTotalPrice(detail.getMenuPrice().multiply(new BigDecimal(detail.getMenuNumber())));
            
            paramMap.put("sql","SSACTscWorkOrderBillDetail.insert");
    		paramMap.put("domain",detail);
    		EiInfo callDetail = LocalServiceUtil.call("SSBMTy", "insertSqlByDomain", paramMap);
    		if(!Boolean.parseBoolean(StringUtil.toString(callDetail.get("success")))){
    			j.setSuccess(false);
    			j.setRespMsg("保存订单详情失败！");
    			j.setRespCode("201");
    			return j;
    		}

    		/**4.扣除菜品数量*/
			paramMap.put("sql","SSBMCpsl01.updateMenuSche");
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("surplus","surplus - " + detail.getMenuNumber());
			map.put("id", detail.getScheId());
			paramMap.put("map",map);
			EiInfo callMenu = LocalServiceUtil.call("SSBMTy", "updateSqlByMap", paramMap);
			if(!Boolean.parseBoolean(StringUtil.toString(callMenu.get("success")))){
				j.setSuccess(false);
				j.setRespMsg("更新菜品数量失败！");
				j.setRespCode("201");
				return j;
			}
		}
        CommGlobalConfig.syncZqBillNoMap(orderEntity.getNeedDate(), num);
        /** 5.保存消费记录*/
        for (SSACTscRechargeConsume mealRechargeConsume : consumeEntityList) {
        	mealRechargeConsume.setBillNo(billNo);
        }
        paramMap.put("sql","SSACTscRechargeConsume.insert");
		paramMap.put("list",consumeEntityList);
		EiInfo callDetail = LocalServiceUtil.call("SSBMTy", "insertSqlByList", paramMap);
		if(!Boolean.parseBoolean(StringUtil.toString(callDetail.get("success")))){
			j.setSuccess(false);
			j.setRespMsg("保存消费记录失败！");
			j.setRespCode("201");
			return j;
		}
		//保存到中间表
		SSACTworkOrderPayMiddle orderMiddle = new SSACTworkOrderPayMiddle();
		orderMiddle.setId(UUIDUtils.getUUID32());
		orderMiddle.setBillId(orderEntity.getId());
		orderMiddle.setBillNo(orderEntity.getBillNo());
		orderMiddle.setPayNo(orderEntity.getPayNo());
		orderMiddle.setCanteenNum(orderEntity.getCanteenNum());
		orderMiddle.setCanteenName(orderEntity.getCanteenName());
		orderMiddle.setArchiveFlag(orderEntity.getArchiveFlag());
		orderMiddle.setCurrentDealer(orderEntity.getCurrentDealer());
		orderMiddle.setPayType(orderEntity.getPayType());
		orderMiddle.setMealNum(orderEntity.getMealNum());
		orderMiddle.setOpenId(orderEntity.getOpenId());
		orderMiddle.setMealNum(orderEntity.getMealNum());
		orderMiddle.setNeedDate(orderEntity.getNeedDate());
		orderMiddle.setRecCreateTime(orderEntity.getRecCreateTime());
		orderMiddle.setRecCreator(orderEntity.getRecCreator());
		paramMap.put("sql","SSACTworkOrderPayMiddle.insert");
		paramMap.put("domain",orderMiddle);
		//开启新事务，中间表保存失败不影响主表下单
		EiInfo callInsertMiddle = LocalServiceUtil.callNewTx("SSBMTy", "insertSqlByDomain", paramMap);
		paramMap.put("sql","SSACTscWorkOrderBill.saveSubsidy");
		EiInfo callSaveSubsidy = LocalServiceUtil.callNewTx("SSBMTy", "insertSqlByDomain", paramMap);
		System.out.println("保存订单中间表：" + callInsertMiddle.get("success"));
        return j;
    }

	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *
	 * @Title: QryBillNo 计算最大单号
	 * @Param date
	 * @return:
	 */
    private void QryBillNo(String date) throws Exception{
        if(CommGlobalConfig.getZqBillNoMap().containsKey(date)){
            return ;
        }
        List<?> li = getBillObject();
        CommGlobalConfig.setZqBillNoMap(buildBillNoMap(li));
    }
    

    /**
     * 
     *  获取单据订单号及日期用于计算最大单号
     *
     * @Title: getBillObject 
     * @return
     * @throws Exception 
     * @return: List<?> 
     * @author: liutao
     * @date: 2021年9月9日 下午3:10:16
     */
   private List<?> getBillObject() throws Exception {
	   HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sql","SSACTscWorkOrderBill.getWorkBillMaxBillNo");
		paramMap.put("str","");
		EiInfo call = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
		return (List<HashMap<String,Object>>) call.get("result");
   }
    
   /**
    * 
    * 构造单据号 map集合
    *
    * @Title: buildBillNoMap 
    * @param li
    * @return 
    * @return: Map<String,String> 
    * @author: liutao
    * @date: 2021年9月9日 下午3:10:37
    */
  @SuppressWarnings("rawtypes")
  private Map<String, String> buildBillNoMap(List li){
      if (null == li || li.isEmpty())
          return new HashMap<String, String>();
      String needDate = "";
      String index = "";
      Map<String, String> map = new HashMap<String, String>();
      for (int iCyc = 0; iCyc < li.size(); iCyc++){
          needDate = (String) ((Map)li.get(iCyc)).get("needDate");
          index = (String) ((Map)li.get(iCyc)).get("index");
          map.put(needDate, index) ;
      }
      return map;
  }

	/**
	 *
	 * 保存订单评价
	 * 1.查询订单信息
	 * 2.保存评价记录
	 * 3.更新订单明细菜品评价
	 * 4.修改订单状态为99已结束
	 * @Title: saveWorkEval
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:43:41
	 */
	public EiInfo saveWorkEval(EiInfo inInfo) {
		ResultData result = new ResultData();
		try{
			//获取传参
			HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
			String json = StringUtil.toString(attr.get("json"));
			JSONObject ja = JSONObject.fromObject(json);

			String userCode = ja.getString("userCode");
			String billNo = ja.getString("billNo");

			if(StringUtils.isBlank(json)) {
				result.setSuccess(false);
				result.setRespMsg("参数json为空，请检查!");
				inInfo.set("result", result);
				return inInfo;
			}
			if(StringUtils.isBlank(billNo)||StringUtils.isBlank(userCode)) {
				result.setSuccess(false);
				result.setRespMsg("参数错误，请检查");
				inInfo.set("result", result);
				return inInfo;
			}
			/**1.查询订单信息**/
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sql","SSACTscWorkOrderBill.queryByBillNo");
			paramMap.put("str",billNo);
			EiInfo callQueryBillInfo = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);
			List<SSACTscWorkOrderBill> list = (List<SSACTscWorkOrderBill>) callQueryBillInfo.get("result");
			if(result == null || list.size() == 0) {
				result.setSuccess(false);
				result.setRespMsg("该参数未查询到相关单据信息，请检查参数!");
				inInfo.set("result", result);
				return inInfo;
			}
			SSACTscWorkOrderBill mainOrder = list.get(0);

			/**2.保存评价记录*/
			PSPCTorderEval ee = new PSPCTorderEval();
			ee.setId(Sid.nextShort());
			ee.setMenuNum(billNo);
			ee.setEvalUserName(userCode);
			ee.setEvalLevel(Integer.parseInt(ja.getString("evalLevel")));
			ee.setEvalContent(ja.getString("evalContent"));
			ee.setEvalTime(CalendarUtils.dateTimeFormat(new Date()));
			ee.setSendDate(ja.getString("sendDate"));
			ee.setCanteenNum(ja.getString("canteenNum"));
			dao.insert("PSPCTorderEval.insert", ee);
			/**3.更新订单明细菜品评价*/
			List<PSPCTorderEval> orderEvals = LocalServiceUtil.objectCastListByJson(ja.get("billMenuList"),PSPCTorderEval.class);
			for (PSPCTorderEval eval : orderEvals) {
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("evalLevel", eval.getEvalLevel());
				map.put("billNo", ja.getString("billNo"));
				map.put("menuNum", eval.getMenuNum());
				map.put("recRevisor", ja.getString("userCode"));
				map.put("recReviseTime", CalendarUtils.dateTimeFormat(new Date()));
				map.put("evalContent", eval.getEvalContent());
				dao.update("SSACTscWorkOrderBillDetail.updateBillDetailLevel", map);
			}

			/**4.修改订单状态为99已结束*/
			PSPCBillStatusInfo billInfo = new PSPCBillStatusInfo();
			billInfo.setPboTbName("sc_work_order_bill");
			billInfo.setBeforeStatus("03");
			billInfo.setAfterStatus("99");
			billInfo.setCurrentDealer(mainOrder.getCurrentDealer());
			billInfo.setBillId(mainOrder.getId());
			billInfo.setCreatorId(mainOrder.getRecCreator());
			billInfo.setCreatorName(mainOrder.getUserName());
			billInfo.setPboCode("MEAL");
			billInfo.setHandleAdvice("提交");
			billInfo.setOprationRoute("评价成功确认状态");
			System.out.println(billInfo.toString());
			//变更订单状态
			paramMap.put("billInfo", billInfo);
			EiInfo callStatusChange = LocalServiceUtil.call("PSPCStatusChange", "statusChange", paramMap);
			RespResult billStatusChangeResult = (RespResult) callStatusChange.get("result");
			System.out.println(StringUtil.separator+"状态变更:"+billStatusChangeResult.getRespCode()+billStatusChangeResult.getRespMsg());

			result.setSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
			result.setSuccess(false);
		}
		inInfo.setStatus(result.isSuccess() ? 1 : -1);
		inInfo.set("result", result);
		return inInfo;
	}

	/**
	 * Todo(获取订单支付状态)
	 * 1.判断订单有没有生成payNo
	 * 2.查询支付节点获取支付结果，为false表示该订单支付失败
	 * 3.判断订单状态和支付状态，订单状态为true，订单状态为00，返回提示，订单支付状态确认中，请稍后再试
	 * @Title: getBillPayType
	 * @author xiajunqing
	 * @date: 2022/2/15 10:56
	 * @Param inInfo
	 * @return: EiInfo
	 * respCode：200正常订单，201用户取消支付，202订单状态确认中
	 */
	public EiInfo billPayStatusCheck(EiInfo inInfo) {
		HashMap<String,Object> hashMap = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		if(hashMap == null || hashMap.isEmpty()) {
			//iplat 服务传参直接获取
			hashMap =  (HashMap<String, Object>) inInfo.getAttr();
		}
		String msg = "订单状态正常";
		String respCode = "200";
		String billNo = StringUtil.toString(hashMap.get("billNo"));
		String payNo = StringUtil.toString(hashMap.get("payNo"));
		String canteenNum = StringUtil.toString(hashMap.get("canteenNum"));
		String payType = StringUtil.toString(hashMap.get("payType"));
		try {
			//把参数转为实体类
			SSACTscWorkOrderBill bill = new SSACTscWorkOrderBill();
			bill.fromMap(hashMap);
			/**1.判断订单有没有生成payNo*/
			if(!StringUtil.isBlank(payNo)) {
				/**2.查询支付节点获取支付结果，为false表示该订单支付失败*/
				//获取支付结果
				boolean flag = new ServiceSSACTimer01().getPayStatus(payType,payNo,canteenNum);
				System.out.println(payType+"订单"+billNo+"支付结果flag："+flag);
				/**3.判断订单状态和支付状态，订单状态为true，订单状态为00，返回提示，订单支付状态确认中，请稍后再试*/
				if("00".equals(bill.getStatusCode()) && flag == false) {
					//订单状态00且支付为false，表示用户点了支付，但没有付款
					msg = "用户取消支付";
					respCode = "201";
					inInfo.setMsgKey(respCode);
					inInfo.setMsg(msg);
					return inInfo;
				}else if("00".equals(bill.getStatusCode()) && flag == true){
					//订单状态00且支付为true，表示用户付款成功，但没有回调
					msg = "订单状态确认中，请稍后再试";
					respCode = "202";
					inInfo.setMsgKey(respCode);
					inInfo.setMsg(msg);
					return inInfo;
				}else{
					msg = "订单状态正常";
					respCode = "200";
					inInfo.setMsgKey(respCode);
					inInfo.setMsg(msg);
					return inInfo;
				}
			}
		} catch (Exception e) {
			inInfo.setStatus(-1);
			e.printStackTrace();
		}
		inInfo.setMsgKey(respCode);
		inInfo.setMsg(msg);
		return inInfo;
	}

	/**
	 * Todo(检查该学生订餐是否和扫码后返回过来的订单号和餐次匹配)

	 * @Title: checkStuBill
	 * @author keke
	 * @date: 2022/11/4 10:56
	 * @Param inInfo
	 * @return: EiInfo
	 * respCode：
	 */
	public EiInfo checkStuBill(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String billNo = StringUtil.toString(attr.get("billNo"));
		String mealNum = StringUtil.toString(attr.get("mealNum"));
		Map map = new HashMap<>();
		map.put("billNo",billNo);
		map.put("mealNum",mealNum);
		List<HashMap<String,String>> list = dao.query("SSACTscWorkOrderBill.checkStuBill",map);
		if(list.isEmpty()){
			inInfo.setStatus(1);
			inInfo.addMsg("404");
			inInfo.set("obj", "未找到结果");
			return inInfo;
		}else {
			inInfo.setStatus(1);
			inInfo.addMsg("200");
			inInfo.set("obj", list);
			return inInfo;
		}

	}

	/**
	 * Todo(查询登录人所属科室下单状态为待配送的数量。入参：科室名称，用餐时间,餐次)

	 * @Title: queryBillNum
	 * @author keke
	 * @date: 2022/11/23 10:56
	 * @Param inInfo
	 * @return: EiInfo
	 * respCode：
	 */
	public EiInfo queryBillNum(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String deptName = StringUtil.toString(attr.get("deptName"));
		String needDate = StringUtil.toString(attr.get("needDate"));
		String mealNum = StringUtil.toString(attr.get("mealNum"));
		Map map = new HashMap<>();
		map.put("deptName",deptName);
		map.put("needDate",needDate);
		map.put("mealNum",mealNum);
		List<HashMap<String,String>> list = dao.query("SSACTscWorkOrderBill.queryBillNum",map);
		if(list.isEmpty()){
			inInfo.setStatus(1);
			inInfo.addMsg("404");
			inInfo.set("obj", "未找到结果");
			return inInfo;
		}else {
			inInfo.setStatus(1);
			inInfo.addMsg("200");
			inInfo.set("obj", list);
			return inInfo;
		}

	}
	/**
	 * Todo(查询限制订餐人数的是否启用（1启用，2不启用）)

	 * @Title: queryStatus
	 * @author keke
	 * @date: 2022/11/23 10:56
	 * @Param inInfo
	 * @return: EiInfo
	 * respCode：
	 */
	public EiInfo queryStatus(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		List<HashMap<String,String>> list = dao.query("SSACTscWorkOrderBill.queryStatus",null);
		if(list.isEmpty()){
			inInfo.setStatus(1);
			inInfo.addMsg("404");
			inInfo.set("obj", "未找到结果");
			return inInfo;
		}else {
			inInfo.setStatus(1);
			inInfo.addMsg("200");
			inInfo.set("obj", list);
			return inInfo;
		}

	}
	/**
	 * Todo(查询当前科室所限制的订餐人数)

	 * @Title: queryLimitNum
	 * @author keke
	 * @date: 2022/11/23 10:56
	 * @Param inInfo
	 * @return: EiInfo
	 * respCode：
	 */
	public EiInfo queryLimitNum(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String deptName = StringUtil.toString(attr.get("deptName"));
		Map map = new HashMap<>();
		map.put("deptName",deptName);
		List<HashMap<String,String>> list = dao.query("SSACTscWorkOrderBill.queryLimitNum",map);
		if(list.isEmpty()){
			inInfo.setStatus(1);
			inInfo.addMsg("404");
			inInfo.set("obj", "未找到结果");
			return inInfo;
		}else {
			inInfo.setStatus(1);
			inInfo.addMsg("200");
			inInfo.set("obj", list);
			return inInfo;
		}

	}


	public void updateGetOperationHistory(Map billInfo){

		List listHistory = dao.query("PSPCStatusChange.getOperationHistory", billInfo.get("billId"));
		if(CollectionUtils.isEmpty(listHistory)){
			billInfo.put("pboCode", "MEAL");
			billInfo.put("handleAdvice", "提交");
			billInfo.put("oprationRoute" ,"支付成功确认状态");
			dao.insert("PSPCStatusChange.savePboHistory", billInfo);
		}
	}

}
