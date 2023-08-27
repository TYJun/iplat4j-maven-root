package com.baosight.wilp.ps.pc.service;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ps.pc.domain.*;
import com.baosight.wilp.ss.ac.domain.SSACTscWorkOrderBill;
import com.baosight.wilp.ss.ac.service.ServiceSSACTimer01;
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
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * ServicePSPCDDGL01 订单管理service 
  * 包含 :
  *      保存订单  saveAll()
  *      取消订单  cancelOrder()
 * @Title: ServicePSPCDDGL01.java
 * @ClassName: ServicePSPCDDGL01
 * @Package：com.baosight.wilp.ps.pc.service
 * @author: liutao
 * @date: 2021年9月6日 下午2:02:48
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePSPCDDGL01 extends ServiceBase {


    /**
     * 
     * 撤销订单
     * *入参：订单编号pBillNo，用户编号userCode，撤单原因rejectReason
     * *出参：EiInfo(result:respMsg操作信息，respCode状态码200成功，201失败)
     * 此函数逻辑
     * 1.查询订单信息
     * 2.校验订单状态是否需要执行退单，校验不通过则返回校验结果
     * 3.判断订单的支付类型，不同支付类型的订单进入不同的退单方法
     * 4.判断食堂是否开启在线退款配置，若开启则进入退款环节
     * 5.查询订单是否支付成功，已支付的订单通知支付节点发起退款
     * 6.变更订单状态，statusCode->99订单结束，rejectCode->2确认退单
     * 7.返回菜品数量
     * 8.保存操作记录
     *
     * @Title: cancelOrder 
     * @param inInfo
     * @return 
     * @return: EiInfo 
     * @author: liutao
     * @date: 2021年9月9日 上午11:01:05
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
		EiInfo outInfo = new EiInfo();
		ResultData j = new ResultData();

		String message = "";
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		System.out.println(StringUtil.separator + "cancelOrder！start! " + attr);
		if (StringUtil.isNotEmpty(pBillNo)) {
			try {
				/**1.查询订单信息*/
				HashMap<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("sql","PSPCTmealOrderBillPatient.queryBillByBillNo");
				paramMap.put("str",pBillNo);
				EiInfo callQueryPatient = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
				List<PSPCTmealOrderBillPatient> bills = (List<PSPCTmealOrderBillPatient>) callQueryPatient.get("result");
				PSPCTmealOrderBillPatient bill = bills.get(0);

				String curDealer = bill.getCurrentDealer();
				String billNo = bill.getBillNo();
				//查询订单明细表
				paramMap.put("sql","PSPCTmealOrderMenu.getMenuInfoByBillNo");
				paramMap.put("str",billNo);
				EiInfo callQueryMenu = LocalServiceUtil.call("SSBMTy", "querySqlForListByString", paramMap);
				
				List<PSPCTmealOrderMenu02> orderMenuList = (List<PSPCTmealOrderMenu02>)callQueryMenu.get("result");
				List<String> tempList = Arrays.asList(curDealer.split(","));

				String sdate = "";
				//查询餐次时间配置
				Map<String,Object> sendTimeMap = new HashMap<String, Object>();
				sendTimeMap.put("mealNum",bill.getMealNum());
				sendTimeMap.put("canteenNum",bill.getCanteenNum());
				List<SSBMDcsj01> sendTimeList = dao.query("SSBMDcsj01.query", sendTimeMap);
				//pos机查询送餐时间
				if("pos".equalsIgnoreCase(bill.getArchiveFlag())){
					sdate = bill.getNeedDate() + " " +sendTimeList.get(0).getSendTime() +":00";
				}else{
					//扫码点餐
					sdate = bill.getReqSendTime()+":00";
				}
                //餐次提前时间
				Integer advanceTime = sendTimeList.get(0).getAdvanceTime();

				//判断当前时间是否超过送餐时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date start = sdf.parse(sdate);
				long startTime = start.getTime() - advanceTime*60*1000;
				Date end = new Date();
				//判断当前时间与需求送餐时间的差值
				long between = (end.getTime() - startTime)/1000;
				//long between = (end.getTime() - start.getTime())/1000;
				long min = between/60;

				/**2.校验订单状态是否需要执行退单，校验不通过则返回校验结果*/
				if (StringUtil.isNotEmpty(userCode) && !userCode.equals(bill.getRecCreator())
						&& !tempList.contains(userCode) ) {
					j.setSuccess(false);
					j.setRespMsg("非单据创建人或食堂管理员不可取消订单！");
				} else if ("99".equals(bill.getStatusCode())) {
					j.setSuccess(false);
					j.setRespMsg("该订单已结束，不可进行作废操作");
				} else if (!"POS".equals(bill.getArchiveFlag()) && !"0101".equals(bill.getPayType())
						&& !"0201".equals(bill.getPayType()) /*&& StringUtils.isBlank(cardId)*/) {
					j.setSuccess(false);
					j.setRespMsg("此单据未查询到消费卡号，请检查");
				}
				else if(min > 1 && !tempList.contains(userCode)){
					j.setSuccess(false);
					j.setRespMsg("订单已超出退单时间");
				}
				else {
					//组织订单信息,等待更改状态
					PSPCBillStatusInfo billInfo = new PSPCBillStatusInfo();
					billInfo.setPboTbName("sc_order_bill_patient");
					billInfo.setBeforeStatus(bill.getStatusCode());
					billInfo.setAfterStatus("99");
					billInfo.setCurrentDealer(curDealer);
					billInfo.setBillId(bill.getId());
					billInfo.setCreatorId(bill.getRecCreator());
					billInfo.setCreatorName(bill.getUserName());
					billInfo.setPboCode("patient_ali");
					billInfo.setHandleAdvice("退款");
					billInfo.setIsReject("1");
					billInfo.setRejectCode("2");
					billInfo.setOprationRoute("订单退款");
					rejectReason = StringUtil.isBlank(rejectReason) ? "订单退款" : rejectReason;
					billInfo.setRejectReason(rejectReason);
					RespResult billStatusChangeResult = new RespResult("201","failed");
					System.out.println("billInfo:" + billInfo.toString());
					// 草稿状态不可退款其余状态都可退款
					int sc = Integer.parseInt(bill.getStatusCode());
					if (sc >= 0) {
						/**3.判断订单的支付类型，不同支付类型的订单进入不同的退单方法*/
						String backMoney = "onLineBack";
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("mealgroupTypeCode", backMoney);
						List<HashMap<String,Object>> list = dao.query("SSBMStxx01.getMealTypeData", map);
						String typeCode = StringUtil.toString(list.get(0).get("typeCode"));
						System.out.println("-----------正在进入配置typeCode-----------" + typeCode);
						/**4.判断是否有在线退款配置，且处于开启状态*/
						if (list.size() > 0 && "1".equals(typeCode)) {
							String totalPrice = bill.getBillTotalPrice().toString();
							billNo = bill.getBillNo();
							String payType1 = bill.getPayType().substring(0, 2);
							System.out.println("-----------正在进入退款环节payType-----------" + payType1);
							if ("01".equals(payType1)) {
								// 支付宝退款
								Map<String,String> aiReMap = new HashMap<String,String>();
								String hospitalCodeAli = MealCommonConfig.getSmpConfig().getHospitalCodeAli();
								aiReMap.put("bill_no", bill.getPayNo());
								aiReMap.put("fee", totalPrice);
								aiReMap.put("hospital_code", hospitalCodeAli);
								aiReMap.put("modul_code", "patient_ali");
								aiReMap.put("memo", "");
								aiReMap.put("outRequestNo", bill.getBillNo());
								System.out.println("--------------开始支付宝退款,查询支付宝交易HospitalCode:" + hospitalCodeAli + ",payNo:" + bill.getPayNo());
								ResponseResult payresult = AliPaySender.singleTradeQuery(hospitalCodeAli, "patient_ali", bill.getPayNo());
								System.out.println("-----------支付宝交易结果" + payresult.getRespCode() + "交易信息:" + payresult.getRespMsg());
								/** 5.判断是否已支付完成*/
								if ("200".equals(payresult.getRespCode())) {
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
										/**6退款成功变更订单状态*/
										paramMap.put("billInfo", billInfo);
										//启用新事务,保证退款成功时状态变更不会被整体事务回滚
										EiInfo callStatusChange = LocalServiceUtil.callNewTx("PSPCStatusChange", "statusChange", paramMap);
										billStatusChangeResult = (RespResult) callStatusChange.get("result");
										System.out.println("订单退款：" + bill.getBillNo() + billStatusChangeResult.getRespMsg());
									}
								} else {
									if(sc == 0) {
										//未支付且状态为草稿变更订单状态
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
							} else if ("02".equals(payType1)) {
								// 微信退款
								String hospitalCodeWx = MealCommonConfig.getSmpConfig().getHospitalCodeWechat();
								Integer price = bill.getBillTotalPrice().multiply(new BigDecimal(100)).intValue();
								BusinessInfoDTO busInfoDTO = new BusinessInfoDTO();
								busInfoDTO.setHospitalCode(hospitalCodeWx);
								busInfoDTO.setModulCode("patient_wechat");
								busInfoDTO.setUsedUnitCode(bill.getCanteenNum());
								busInfoDTO.setUserUnitName(bill.getCanteenName());
								System.out.println("--------------开始微信退款,查询微信交易HospitalCode:" + hospitalCodeWx + ",payNo():" + bill.getPayNo());
								//微信查询支付状态
								ResultEntry weChatResult = WeChatPayImpl.OrderQuery(bill.getPayNo(), busInfoDTO);
								System.out.println("-----------微信交易结果" + weChatResult.getRespCode() + "交易信息:" + weChatResult.getRespMsg());
								/** 5.判断是否已支付完成*/
								if(weChatResult.getRespCode() == 200) {
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
										/**6退款成功变更订单状态*/
										paramMap.put("billInfo", billInfo);
										//启用新事务,保证退款成功时状态变更不会被整体事务回滚
										EiInfo callStatusChange = LocalServiceUtil.callNewTx("PSPCStatusChange", "statusChange", paramMap);
										billStatusChangeResult = (RespResult) callStatusChange.get("result");
										System.out.println("订单退款：" + bill.getBillNo() + billStatusChangeResult.getRespMsg());
									}
								}else {
									if(sc == 0) {
										//未支付且状态为草稿变更订单状态
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
							}else if ("00".equals(payType1)){
								System.out.println(payType1+StringUtil.separator);
								//现金支付直接变更订单状态
								paramMap.put("billInfo", billInfo);
								//启用新事务,保证退款成功时状态变更不会被整体事务回滚
								EiInfo callStatusChange = LocalServiceUtil.callNewTx("PSPCStatusChange", "statusChange", paramMap);
								billStatusChangeResult = (RespResult) callStatusChange.get("result");
								System.out.println("订单退款：" + bill.getBillNo() + billStatusChangeResult.getRespMsg());
							}else if ("03".equals(payType1)){
								if(sc == 0) {
									//未支付且状态为草稿变更订单状态
									paramMap.put("billInfo", billInfo);
									billInfo.setRejectCode("4");
									//启用新事务,保证退款成功时状态变更不会被整体事务回滚
									EiInfo callStatusChange = LocalServiceUtil.callNewTx("PSPCStatusChange", "statusChange", paramMap);
									billStatusChangeResult = (RespResult) callStatusChange.get("result");
									System.out.println("订单退款：" + bill.getBillNo() + billStatusChangeResult.getRespMsg());
								}else{
									//电子账户退款
									paramMap.put("cardUserCode", bill.getAlipayid());
									/**5查询电子账户信息*/
									EiInfo callCardInfo = LocalServiceUtil.callNoTx("PSAEKPXX", "queryCardInfo", paramMap);
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
									//执行退款
									EiInfo callCardBack = LocalServiceUtil.call("PSAEConsumeRecord", "handleOrderFoot", paramMap);
									//ResultData resultEntry = (ResultData) callCardBack.get("result");
									//转换返回类型
									ResultData resultEntry = (ResultData) LocalServiceUtil.beanCastByJson(callCardBack.get("result"),ResultData.class);
									if (!resultEntry.isSuccess()) {
										//退款失败
										j.setSuccess(false);
										j.setRespCode((String)resultEntry.getRespCode());
										j.setRespMsg(resultEntry.getRespMsg());
										outInfo.set("result", j);
										outInfo.setStatus(j.isSuccess() ? 1 : -1);
										return outInfo;
									}else {
										/**6退款成功变更订单状态*/
										paramMap.put("billInfo", billInfo);
										//启用新事务,保证退款成功时状态变更不会被整体事务回滚
										EiInfo callStatusChange = LocalServiceUtil.callNewTx("PSPCStatusChange", "statusChange", paramMap);
										billStatusChangeResult = (RespResult) callStatusChange.get("result");
										System.out.println("订单退款：" + bill.getBillNo() + billStatusChangeResult.getRespMsg());
										paramMap.put("cardUserCode", bill.getOpenId());
										/**5查询电子账户信息*/
										callCardInfo = LocalServiceUtil.callNoTx("PSAEKPXX", "queryCardInfo", paramMap);
										cardInfos = (List) callCardInfo.getAttr().get("cardInfo");
										String moneyLeft = "无法获取余额";
										if(cardInfos.size() > 0){
											JSONObject jsonObject = JSONObject.fromObject(cardInfos.get(0));
											moneyLeft = jsonObject.getString("cardBalance");
										}
										returnMap.put("moneyLeft", moneyLeft);
									}
								}
							}
						}
					}
					
					String respCode = billStatusChangeResult.getRespCode();
					//判断状态修改是否成功
					if (!respCode.equals("200")) {
						j.setSuccess(false);
						message = billStatusChangeResult.getRespMsg();
					} else {
						/**7.返回菜品数量*/
						updateMenuNumCancel(bill,orderMenuList);
						
						/**8.添加操作记录时间*/
						String username = bill.getUserName();
						String recCreator = bill.getRecCreator();
						PSPCTmealOperation operateRecord = new PSPCTmealOperation();
						operateRecord.setId(UUIDUtils.getUUID32());
						operateRecord.setOperationTime(CalendarUtils.dateTimeFormat(new Date()));
						operateRecord.setBillNo(billNo);
						operateRecord.setCreatorName(username);
						operateRecord.setOperationRoute("06");
						operateRecord.setCreatorId(recCreator);
						//新增记录到操作记录表
						paramMap.put("sql","PSPCTmealOperation.insert");
						paramMap.put("domain",operateRecord);
						EiInfo callInsertOperation = LocalServiceUtil.callNewTx("SSBMTy", "insertSqlByDomain", paramMap);
						System.out.println("订单消费记录保存Operation:"+Boolean.parseBoolean(StringUtil.toString(callInsertOperation.get("success"))));
						
						operateRecord.setId(UUIDUtils.getUUID32());
						operateRecord.setOperationTime(CalendarUtils.dateTimeFormat(new Date()));
						operateRecord.setOperationRoute("07");
						paramMap.put("sql","PSPCTmealOperation.insert");
						paramMap.put("domain",operateRecord);
						LocalServiceUtil.callNewTx("SSBMTy", "insertSqlByDomain", paramMap);
					}
					//拼接返回信息
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
         * 保存订单(三餐一起下)
         * 入参：用户名userName,联系电话userTelNumber,住院号hospitalNo,总价billTotalPrice,
         *  食堂编码canteenNum,餐次编码mealNum,需要时间needDate
         *  早餐明细billDetailAM,午餐明细billDetailPM,晚餐明细billDetailMM
         * 出餐：是否成功success,返回信息respMsg
     *       1.获得提交的订单信息，校验参数，校验不通过返回参数校验信息
     *       2.将json字符串组装成实体类，失败时返回出错信息
     *       3.查询病患信息，若存在则更新其信息，不存在则新增病患信息，操作失败，返回失败信息
     *       4.校验菜品排班，校验失败时返回失败信息
     *       5.保存订单主表和订单详情信息，保存失败，执行回滚，返回失败信息
	 *
	 * @Title: saveAll 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo   success=true 成功 ;  success=false 失败 ;  错误信息在getRespMsg中
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public EiInfo saveAll(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		ResultData j = new ResultData();
		/**1.获得提交的订单信息，校验参数，校验不通过返回参数校验信息*/
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 订餐参数
		String jsonStr = StringUtil.toString(attr.get("json"));
		//标识
		String archiveFlag = StringUtil.toString(attr.get("archiveFlag"));
		//保存病员信息标识
		String saveInfoFlag = StringUtil.toString(attr.get("saveInfoFlag"));
		archiveFlag = StringUtil.isEmpty(archiveFlag) ? "POS" : archiveFlag;
		if (StringUtils.isBlank(jsonStr)) {
			j.setSuccess(false);
			j.setRespMsg("参数json为空，请检查!");
			outInfo.set("result", j);
			return outInfo;
		}
		try {
			System.out.println("下单时json: " + jsonStr);
			JSONObject json = JSONObject.fromObject(jsonStr);
			PSPCTmealOrderBillPatient mealOrderBill = new PSPCTmealOrderBillPatient();
			mealOrderBill.setArchiveFlag(archiveFlag);
			/**2. 构建订单主表实体类*/
			mealOrderBill = buildOrderInfo(json, mealOrderBill, j);
			if (!j.isSuccess()) {
				outInfo.set("result", j);
				outInfo.setStatus(-1);
				return outInfo;
			}

			//组装病患信息
			PSPCTscOrderPatientInfo patientInfo = new PSPCTscOrderPatientInfo();
			patientInfo.setPatientNum(json.getString("hospitalNo"));
			patientInfo.setPatientName(json.getString("userName"));
			patientInfo.setPatientTel(json.getString("userTelNumber"));
			patientInfo.setBuilding(json.getString("building"));
			patientInfo.setBuildingNum(json.getString("buildingNum"));
			patientInfo.setFloor(json.getString("floor"));
			patientInfo.setFloorNum(json.getString("floorNum"));
			patientInfo.setDeptNum(json.getString("deptNum"));
			patientInfo.setDeptName(json.getString("deptName"));
			patientInfo.setBedNo(json.getString("bedNo"));
			patientInfo.setWardCode(json.getString("wardCode"));
			patientInfo.setWardName(json.getString("wardName"));
			patientInfo.setDataGroupCode(json.getString("dataGroupCode"));
			patientInfo.setHospitalName(json.getString("hospitalName"));
			patientInfo.setHospitalNo(json.getString("areaCode"));
			patientInfo.setRoleNum(json.getString("roleNum"));
			if (json.has("userId") && StringUtil.isNotEmpty(json.getString("userId"))) {
				patientInfo.setPatientCode(json.getString("userId"));
			}
			/**3.查询病患信息，若存在则更新其信息，不存在则新增病患信息，操作失败，返回失败信息*/
			boolean flag = saveOrUpdatePatientInfo(saveInfoFlag,patientInfo);
			
			if(!flag) {
				j.setSuccess(false);
				j.setRespMsg("更新病人信息失败");
				outInfo.set("result", j);
				//在需要回滚的场景中设置传出 EiInfo 的 status=-1
				outInfo.setStatus(j.isSuccess() ? 1 : -1);
				return outInfo;
			}
			// 早餐订单明细
			//List<PSPCTmealOrderBillDetail> listsAM = JSONArray.toList((JSONArray) json.get("billDetailAM"),
			//		PSPCTmealOrderBillDetail.class);
			List<PSPCTmealOrderBillDetail> listsAM = JSON.parseArray(json.get("billDetailAM").toString(),PSPCTmealOrderBillDetail.class); 
			
			// 午餐订单明细
			List<PSPCTmealOrderBillDetail> listsPM = JSON.parseArray(json.get("billDetailPM").toString(),PSPCTmealOrderBillDetail.class); 
			// 晚餐订单明细
			List<PSPCTmealOrderBillDetail> listsMM = JSON.parseArray(json.get("billDetailMM").toString(),PSPCTmealOrderBillDetail.class);
			
			/** 4.校验菜品*/
			HashMap<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("listsAM",listsAM);
			paramMap.put("listsPM",listsPM);
			paramMap.put("listsMM",listsMM);
			EiInfo call = LocalServiceUtil.call("PSPCDDJY01", "checkSchema", paramMap);
			flag = (boolean) call.get("success");
			if (!flag) {
				j.setSuccess(false);
				j.setRespMsg("所选菜品不在排班范围之内，请重新选择!");
				outInfo.set("result", j);
				return outInfo;
			}
			//保存订单主表和订单详情信息，保存失败，执行回滚，返回失败信息
			if (listsAM != null || listsPM != null || listsMM != null) {
				/** 5.保存订单主表和订单详情信息*/
				j = dealBussAllOrder(mealOrderBill, listsAM, listsPM, listsMM);
			} else {
				j.setRespMsg("订单为空！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setRespMsg("程序异常" + e.getMessage());
		}
		//在需要回滚的场景中设置传出 EiInfo 的 status=-1
		outInfo.setStatus(j.isSuccess() ? 1 : -1);
		System.out.println(j.getRespMsg());
		outInfo.set("result", j);
		return outInfo;
	}


	/**
	 * 
     * 保存订单主表信息和订单详情
     * 入参：订单主表实体类mealOrderBill
     *  早餐明细listsAM,午餐明细listsPM,晚餐明细listsMM
     * 出餐：ResultData(是否成功success,返回信息respMsg)
     *  1.查询食堂联络人，获取信息失败，返回失败信息
        2.组装订单信息，生成订单号与统一支付ID，初始订单为草稿状态00
        3.校验订单金额和菜品数量，校验失败，返回失败信息
        4.保存操作记录oeration信息，保存失败，执行回滚，还原缓存中的订单号，返回失败信息
        5.保存订单主表，保存失败，执行回滚，还原缓存中的订单号，返回失败信息
        6.保存订单详情，保存失败，执行回滚，还原缓存中的订单号，返回失败信息
        7.扣除菜品余量，更新失败，执行回滚，还原缓存中的订单号，返回失败信息
	 *
	 * @Title: dealBussAllOrder 
	 * @param mealOrderBill
	 * @param listsAM
	 * @param listsPM
	 * @param listsMM
	 * @return
	 * @throws Exception 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 上午11:01:43
	 */
	public ResultData dealBussAllOrder(PSPCTmealOrderBillPatient mealOrderBill, 
									List<PSPCTmealOrderBillDetail> listsAM,
									List<PSPCTmealOrderBillDetail> listsPM, 
									List<PSPCTmealOrderBillDetail> listsMM) throws Exception{
		ResultData j = new ResultData();

		List<PSPCTmealOrderBillPatient> mainList = new ArrayList<PSPCTmealOrderBillPatient>();
		List<PSPCTmealOrderBillDetail> detailList = new ArrayList<PSPCTmealOrderBillDetail>();
		String message = "";
		String current = "";
		/**1.查询食堂联络人，获取信息失败，返回失败信息*/
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sql","SSBMStxx01.queryCanteenByCanteenNum");
		paramMap.put("str",mealOrderBill.getCanteenNum());
		EiInfo call = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
		List<SSBMStxx01> list = (List<SSBMStxx01>) call.get("result");
		
		if(!list.isEmpty()) {
			current = list.get(0).getLiaisonId();
		}else {
			j.setSuccess(false);
			j.setRespMsg("未查询到食堂联络人信息！");
			return j;
		}
		//设置食堂联络人
		mealOrderBill.setCurrentDealer(current);
		//同步锁
		synchronized (CommGlobalConfig.getBhBillNoMap()) {
			try {
				/**2.组装订单信息，设置订单号和订餐统一支付ID，初始订单为草稿状态00*/
				String payNo = UUIDUtils.getUUID32();
				if (listsAM != null && listsAM.size() > 0) {
					mealOrderBill.setMealNum("001");
					message += handedParam(listsAM,mealOrderBill,mainList,detailList);
				}else{
					message += "0,0,0;";
				}
				if (listsPM != null && listsPM.size() > 0) {
					mealOrderBill.setMealNum("002");
					message += handedParam(listsPM,mealOrderBill,mainList,detailList);
				}else{
					message += "0,0,0;";
				}
				if (listsMM != null && listsMM.size() > 0) {
					mealOrderBill.setMealNum("003");
					message += handedParam(listsMM,mealOrderBill,mainList,detailList);
				}else{
					message += "0,0,0;";
				}

				if(message.endsWith(";")){
					message = message.substring(0, message.length()-1);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for(PSPCTmealOrderBillPatient mainOrder :mainList) {
					mainOrder.setPayNo(payNo);
					//订单创建时间
					mainOrder.setRecCreateTime(sdf.format(new Date()));
					//获取当前订单对应的detail
					List<PSPCTmealOrderBillDetail> details = new ArrayList<PSPCTmealOrderBillDetail>();
					for (int i = 0; i < detailList.size(); i++) {
						if(mainOrder.getBillNo().equals(detailList.get(i).getBillNo())) {
							details.add(detailList.get(i));
						}
					}
					HashMap<String,Object> map = new HashMap<String, Object>();
					if(!"POS".equals(mainOrder.getArchiveFlag())) {
						//校验送餐时间
						map.put("canteenNum",mainOrder.getCanteenNum());
						map.put("mealNum",mainOrder.getMealNum());
						map.put("needDate",mainOrder.getNeedDate());
						map.put("reqSendTime",mainOrder.getReqSendTime());
						EiInfo callSendTime = LocalServiceUtil.call("PSPCDDJY01", "checkSendTime", map);
						boolean success = (boolean) callSendTime.get("success");
						System.out.println("校验送餐时间"+mainOrder.getBillNo()+":" + success);
						if (!success) {
							j.setSuccess(success);
							j.setRespMsg(StringUtil.toString(callSendTime.get("respMsg")));
							return j;
						}
					}
					System.out.println(mainOrder);
					/**3.校验订单金额和菜品数量*/
					map.put("mainOrder",mainOrder);
					map.put("details",details);
					EiInfo callParam = LocalServiceUtil.call("PSPCDDJY01", "checkPrice", map);
					boolean success = (boolean) callParam.get("success");
					System.out.println("校验订单金额和菜品数量"+mainOrder.getBillNo()+":" + success);
					if (!success) {
						j.setSuccess(success);
						j.setRespMsg(StringUtil.toString(callParam.get("respMsg")));
						return j;
					}
					
					/**4.记录操作oeration信息*/
					String username = mainOrder.getUserName();
					System.out.println("移动端点餐下单时间状态username："+username);;
					System.out.println(username);
					String recCreator = mainOrder.getRecCreator();
					PSPCTmealOperation operateRecord = new PSPCTmealOperation();
					operateRecord.setId(UUIDUtils.getUUID32());
					operateRecord.setOperationTime(CalendarUtils.dateTimeFormat(new Date()));;
					operateRecord.setOperationRoute("01");
					operateRecord.setBillNo(mainOrder.getBillNo());
					operateRecord.setCreatorName(mainOrder.getUserName());
					operateRecord.setCreatorId(mainOrder.getRecCreator());
					System.out.println(mainOrder.getBillNo()+",recCreator:"+recCreator);
					//保存operation记录表
					paramMap.put("sql","PSPCTmealOperation.insert");
					paramMap.put("domain",operateRecord);
					EiInfo callOption = LocalServiceUtil.call("SSBMTy", "insertSqlByDomain", paramMap);
					if(!Boolean.parseBoolean(StringUtil.toString(callOption.get("success")))){
						j.setSuccess(false);
						j.setRespMsg("保存oeration记录失败！");
						return j;
					}
					/**5.保存订单主表*/
					paramMap.put("sql","PSPCTmealOrderBillPatient.insert");
					paramMap.put("domain",mainOrder);
					EiInfo callMainOrder = LocalServiceUtil.call("SSBMTy", "insertSqlByDomain", paramMap);
					if(!Boolean.parseBoolean(StringUtil.toString(callMainOrder.get("success")))){
						j.setSuccess(false);
						j.setRespMsg("保存订单主表失败！");
						return j;
					}
					//保存到中间表
					PSPCTmealOrderMiddle orderMiddle = new PSPCTmealOrderMiddle();
					orderMiddle.setId(UUIDUtils.getUUID32());
					orderMiddle.setBillId(mainOrder.getId());
					orderMiddle.setBillNo(mainOrder.getBillNo());
					orderMiddle.setPayNo(mainOrder.getPayNo());
					orderMiddle.setCanteenNum(mainOrder.getCanteenNum());
					orderMiddle.setMealNum(mainOrder.getMealNum());
					orderMiddle.setOpenId(mainOrder.getOpenId());
					orderMiddle.setNeedDate(mainOrder.getNeedDate());
					orderMiddle.setRecCreateTime(mainOrder.getRecCreateTime());
					orderMiddle.setRecCreator(mainOrder.getRecCreator());
					paramMap.put("sql","PSPCTmealOrderMiddle.insert");
					paramMap.put("domain",orderMiddle);
					//开启新事务，中间表保存失败不影响主表下单
					EiInfo callInsertMiddle = LocalServiceUtil.callNewTx("SSBMTy", "insertSqlByDomain", paramMap);
					System.out.println("保存订单中间表：" + callInsertMiddle.get("success"));
				}
				for(PSPCTmealOrderBillDetail detailOrder :detailList) {
					detailOrder.setRecCreateTime(CalendarUtils.dateTimeFormat(new Date()));
					/**6.保存订单详情 */
					paramMap.put("sql","PSPCTmealOrderBillDetail.insert");
					paramMap.put("domain",detailOrder);
					EiInfo callMainOrder = LocalServiceUtil.call("SSBMTy", "insertSqlByDomain", paramMap);
					if(!Boolean.parseBoolean(StringUtil.toString(callMainOrder.get("success")))){
						j.setSuccess(false);
						j.setRespMsg("保存订单详情失败！");
						return j;
					}
					/**7.扣除菜品数量*/
					paramMap.put("sql","SSBMCpsl01.updateMenuSche");
					HashMap<String,Object> map = new HashMap<String, Object>();
					map.put("surplus","surplus - " + detailOrder.getMenuNumber());
					map.put("id", detailOrder.getScheId());
					paramMap.put("map",map);
					//更新菜品排班表
					EiInfo callMenu = LocalServiceUtil.call("SSBMTy", "updateSqlByMap", paramMap);
					if(!Boolean.parseBoolean(StringUtil.toString(callMenu.get("success")))){
						j.setSuccess(false);
						j.setRespMsg("更新菜品数量失败！");
						return j;
					}
				}
				j.setRespMsg(message);
				j.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				j.setSuccess(false);
				j.setRespMsg(e.getMessage());
				try {
					//出错时还原缓存最大订单编号
					String gc = CommGlobalConfig.getBhBillNoByDate(mealOrderBill.getNeedDate());
					int maxIndex = Integer.parseInt(gc) - mainList.size();
					CommGlobalConfig.syncBhBillNoMap(mealOrderBill.getNeedDate(), maxIndex+"");
				} catch (Exception e1) {
					e1.printStackTrace();
					j.setSuccess(false);
					j.setRespMsg(e1.getMessage());
				}
			}
		}
		return j;
	}
	
	
	/**
	 * 
	 *  saveOrUpdatePatientInfo保存或更新病员信息
	 * 1.判断病患信息是否存在
	 *  1.1 不存在， 插入病患信息
	 *  1.2 存在， 更新病患信息
	 * @Title: saveOrUpdatePatientInfo 
	 * @param saveInfoFlag
	 * @param patientInfo
	 * @return 
	 * @return: boolean 
	 * @author: liutao
	 * @date: 2021年9月9日 上午11:02:28
	 */
	private boolean saveOrUpdatePatientInfo(String saveInfoFlag ,PSPCTscOrderPatientInfo patientInfo) {
		//判断保存病员信息表的表示是否存在分别保存不同渠道的用户信息
		if(StringUtil.isNotEmpty(saveInfoFlag)) {
			//存在保存信息标识则更新病员订单表
			patientInfo.setRegisterType(saveInfoFlag);
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("addNum" , patientInfo.getWardCode());
			map.put("bedNo" , patientInfo.getBedNo());
			map.put("registerType" , patientInfo.getBedNo());
			List<PSPCTscOrderPatientInfo> list = dao.query("PSPCTscOrderPatientInfo.queryPatientInfoByAddNum", map);
			/**1.判断病患信息是否存在*/
			if (list.isEmpty()) {
				/**1.1不存在， 插入病患信息*/
				patientInfo.setId(UUIDUtils.getUUID32());
				patientInfo.setCreator("admin");
				patientInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				boolean insert = insertBySql("PSPCTscOrderPatientInfo.insert",patientInfo);
				return insert;
			} else {
				/**1.2存在， 更新病患信息*/
				patientInfo.setResvisor(patientInfo.getPatientNum());
				patientInfo.setReviseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				boolean update = updateBySql("PSPCTscOrderPatientInfo.update",patientInfo);
				return update;
			}
		}else{
			//病员同步表查询住院号在注册表中是否存在
			List<PSPCTmealPatientCard> list = dao.query("PSPCTmealPatientCard.queryPatientInfoPrecise", patientInfo.getPatientNum());
			/**1.判断病患信息是否存在*/
			if (list.isEmpty()) {
				/**1.1不存在， 插入病患信息*/
				patientInfo.setId(UUIDUtils.getUUID32());
				patientInfo.setPatientCode(patientInfo.getPatientNum());
				patientInfo.setHospitalNo(patientInfo.getDataGroupCode());
				patientInfo.setCreator("admin");
				patientInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				boolean insert = insertBySql("PSPCTmealPatientCard.insert",patientInfo);
				return insert;
			} else {
				PSPCTmealPatientCard patientCard = list.get(0);
				/**1.2存在， 更新病患信息*/
				patientInfo.setId(patientCard.getId());
				patientInfo.setResvisor(patientInfo.getPatientNum());
				patientInfo.setPatientCode(patientInfo.getPatientNum());
				patientInfo.setHospitalNo(patientInfo.getDataGroupCode());
				patientInfo.setReviseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				boolean update = updateBySql("PSPCTmealPatientCard.update",patientInfo);
				return update;
			}
		}
	}
	
	/**
	 * 
	 * 该方法是用于组装参数为实体类
	 * 1.克隆主单据信息
	 * 2.构建单号
	 * 3.计算总价
	 * 4.同步最大单号
	 * @Title: handedParam 
	 * @param lst
	 * @param mealOrderBillInfo
	 * @param mainList
	 * @param detailList
	 * @return
	 * @throws Exception 
	 * @return: String 
	 * @author: liutao
	 * @date: 2021年9月9日 上午11:04:45
	 */
	private String handedParam(List<PSPCTmealOrderBillDetail> lst,
			PSPCTmealOrderBillPatient mealOrderBillInfo ,
			List<PSPCTmealOrderBillPatient> mainList,
			List<PSPCTmealOrderBillDetail> detailList) throws Exception {

		String num = "";
		/** 1.克隆主单据信息*/
		PSPCTmealOrderBillPatient mealOrderBill = (PSPCTmealOrderBillPatient)mealOrderBillInfo.deepClone();
		//UUID
		mealOrderBill.setId(UUIDUtils.getUUID32());
		QryBillNo(mealOrderBill.getNeedDate()); 
		String gc = CommGlobalConfig.getBhBillNoByDate(mealOrderBill.getNeedDate());

		int maxIndex = Integer.parseInt(gc) + 1;
		num = StringUtil.getNumberToString(maxIndex, 10, 5);
		String da = CalendarUtils.shortDateFormat(CalendarUtils.toShortDate(mealOrderBill.getNeedDate()));
		/**2.构建单号*/
		String billNo = "BH" + da + num ;
		mealOrderBill.setBillNo(billNo);
		BigDecimal allprice = new BigDecimal(0);
		/**3.计算总价*/
		allprice = handedDetailOrder(lst, mealOrderBill, detailList);
		String message = mealOrderBill.getId() + "," + mealOrderBill.getBillNo() + "," + allprice + ";";
		//设置总价
		mealOrderBill.setBillTotalPrice(allprice);
		mainList.add(mealOrderBill);
		/**4.同步最大单号*/
		CommGlobalConfig.syncBhBillNoMap(mealOrderBill.getNeedDate(), num);
		return message;
	}
	
	/**
	 * 
	 * 计算订单总价
	 *
	 * @Title: handedDetailOrder 
	 * @param lst
	 * @param mealOrderBill
	 * @param detailList
	 * @return 
	 * @return: BigDecimal 
	 * @author: liutao
	 * @date: 2021年9月9日 上午11:06:27
	 */
	private BigDecimal handedDetailOrder(List<PSPCTmealOrderBillDetail> lst, 
			PSPCTmealOrderBillPatient mealOrderBill, 
			List<PSPCTmealOrderBillDetail> detailList){
		int i = 0;
		BigDecimal allprice = new BigDecimal(0);
		//获取配送费
		BigDecimal transFee = mealOrderBill.getTransFee();
		//遍历订单明细
		for (PSPCTmealOrderBillDetail list : lst) {
			PSPCTmealOrderBillDetail saveInfo = new PSPCTmealOrderBillDetail();
			saveInfo.setBillNo(mealOrderBill.getBillNo());
			saveInfo.setId(UUID.randomUUID().toString());
			saveInfo.setBillDetailNo(mealOrderBill.getBillNo() + i);
			saveInfo.setMenuNum(list.getMenuNum());
			saveInfo.setMenuNumber(list.getMenuNumber());
			saveInfo.setMenuPrice(list.getMenuPrice());
			saveInfo.setMenuTotalPrice(list.getMenuTotalPrice());
			saveInfo.setScheId(list.getId());
			//计算价格
			allprice=allprice.add(list.getMenuTotalPrice());
			// 订单详情信息添加到集合
			detailList.add(saveInfo);
			i++;
		}
		allprice = allprice.add(transFee);
		return allprice;
	}
	
	/**
	 * 
	 * 查询订单最大单号
	 *
	 * @Title: QryBillNo 
	 * @param date
	 * @throws Exception 
	 * @return: void 
	 * @author: liutao
	 * @date: 2021年9月9日 上午11:07:12
	 */
	public void QryBillNo(String date) throws Exception{
		if(CommGlobalConfig.getBhBillNoMap().containsKey(date)){
			return;
		}
		List<HashMap<String,Object>> li = getBillMaxDate();
		CommGlobalConfig.setBhBillNoMap(buildPoBillNoMap(li));
	}
	
	/**
	 * 
	 * 构造单据号 map集合
	 *
	 * @Title: buildPoBillNoMap 
	 * @param li
	 * @return 
	 * @return: Map<String,String> 
	 * @author: liutao
	 * @date: 2021年9月9日 上午11:07:43
	 */
	private Map<String, String> buildPoBillNoMap(List<HashMap<String,Object>> li){
		if (null == li || li.isEmpty())
			return new HashMap<String, String>();
		String key = "";
		String value = "";
		Map<String, String> map = new HashMap<String, String>();
		for (int i=0,length=li.size(); i<length; i++){
			key = StringUtil.toString((li.get(i)).get("needDate"));
			value = StringUtil.toString((li.get(i)).get("indx"));
			map.put(key, value) ;
		}
		return map;
	}
	
	/**
	 * 
	 * 获取单据订单号及日期用于计算最大单号
	 *
	 * @Title: getBillMaxDate 
	 * @return
	 * @throws Exception 
	 * @return: List<HashMap<String,Object>> 
	 * @author: liutao
	 * @date: 2021年9月9日 上午11:08:21
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> getBillMaxDate() throws Exception{
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		//查询病患订单主表
		paramMap.put("sql","PSPCTmealOrderBillPatient.getBillMaxDate");
		paramMap.put("str","");
		EiInfo call = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
		return (List<HashMap<String,Object>>) call.get("result");
	}
	
	
	/**
	 * 
	 *  组装订单信息
	 *
	 * @Title: buildOrderInfo 
	 * @param ja
	 * @param mealOrderBill
	 * @param j
	 * @return
	 * @throws Exception 
	 * @return: PSPCTmealOrderBillPatient 
	 * @author: liutao
	 * @date: 2021年9月9日 上午11:11:07
	 */
	public PSPCTmealOrderBillPatient buildOrderInfo(JSONObject ja, 
							PSPCTmealOrderBillPatient mealOrderBill, 
							ResultData j)  throws Exception{
		// 校验订单必需参数
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("json",ja);
		if(!"POS".equals(mealOrderBill.getArchiveFlag())){
			EiInfo call = LocalServiceUtil.call("PSPCDDJY01", "checkParam", paramMap);
			boolean flag = (boolean) call.get("success");
			if(!flag) {
				j.setSuccess(flag);
				j.setRespMsg(StringUtil.toString(call.get("respMsg")));
				return mealOrderBill;
			}
		}
		// 为主表实体类赋值
		mealOrderBill.setId(UUIDUtils.getUUID32());
		mealOrderBill.setUserTelNumber(ja.getString("userTelNumber"));
		mealOrderBill.setUserName(ja.getString("userName"));
		//对json对象中的参数进行校验
		if (ja.has("payType") && StringUtil.isNotEmpty(ja.getString("payType"))) {
			mealOrderBill.setPayType(ja.getString("payType"));
		} else {
			mealOrderBill.setPayType("03");
		}
		if (ja.has("deptNum") && StringUtil.isNotEmpty(ja.getString("deptNum"))) {
			String deptNum = ja.getString("deptNum");
			mealOrderBill.setDeptNum(deptNum);
		}
		if (ja.has("building") && StringUtil.isNotEmpty(ja.getString("building"))) {
			mealOrderBill.setBuilding(ja.getString("building"));
		}
		if (ja.has("buildingNum") && StringUtil.isNotEmpty(ja.getString("buildingNum"))) {
			mealOrderBill.setBuildingName(ja.getString("buildingNum"));
		}
		if (ja.has("floor") && StringUtil.isNotEmpty(ja.getString("floor"))) {
			mealOrderBill.setFloor(ja.getString("floor"));
		}
		if (ja.has("floorNum") && StringUtil.isNotEmpty(ja.getString("floorNum"))) {
			mealOrderBill.setFloorName(ja.getString("floorNum"));
		}
		if (ja.has("address") && StringUtil.isNotEmpty(ja.getString("address"))) {
			mealOrderBill.setAddress(ja.getString("address"));
		}
		if (ja.has("userId") && StringUtil.isNotEmpty(ja.getString("userId"))) {
			mealOrderBill.setAlipayid(ja.getString("userId"));
		}
		mealOrderBill.setBedNo(ja.getString("bedNo"));
		mealOrderBill.setDeptName(ja.getString("deptName"));
		mealOrderBill.setRoleNum(ja.getString("roleNum"));
		try {
			mealOrderBill.setWardCode(ja.getString("wardCode"));
			mealOrderBill.setWardName(ja.getString("wardName"));
		} catch (Exception e) {
			e.printStackTrace();
			mealOrderBill.setWardCode("1");
			mealOrderBill.setWardName("1");
			System.out.println("pos下单机未传参数wardCode,wardName");
		}
		mealOrderBill.setRecCreator(ja.getString("recCreator"));

		mealOrderBill.setMealNum(ja.getString("mealNum"));
		mealOrderBill.setReqSendTime(ja.getString("reqSendTime"));
		mealOrderBill.setBillTotalPrice(new BigDecimal(ja.getString("billTotalPrice")));
		mealOrderBill.setTransFee(new BigDecimal(ja.getString("transFee")));
		mealOrderBill.setBillRemark(ja.getString("billRemark"));
		mealOrderBill.setPrintFlag("N");
		mealOrderBill.setCanteenNum(ja.getString("canteenNum"));
		// 存储病患住院号
		mealOrderBill.setOpenId(ja.getString("hospitalNo"));
		mealOrderBill.setStatusCode("00");
		//格式化日期
		Date nowDate = new Date();
		String today = CalendarUtils.dateFormat(nowDate);
		Calendar cd = Calendar.getInstance();
		String date = ja.getString("needDate");
		if (!StringUtils.isBlank(date)) {
			if (("Y").equals(date)) {
				cd.add(cd.DATE, 1);
				date = CalendarUtils.dateFormat(cd.getTime());
			} else if (("D").equals(date)) {
				date = today;
			} else if ("N".equals(date)) {
				cd.add(cd.DATE, 2);
				date = CalendarUtils.dateFormat(cd.getTime());
			} else if (date.length() == 10) {
				mealOrderBill.setNeedDate(date);
			} else {
				j.setSuccess(false);
				j.setRespMsg("needDate格式异常，请检查!");
			}
		}
		return mealOrderBill;
	}

	
	/**
	 * 
	 * 修改病人信息
	 *
	 * @Title: updatePatientInfo 
	 * @param json
	 * @return 
	 * @return: boolean 
	 * @author: liutao
	 * @date: 2021年9月9日 上午11:11:47
	 */
	public boolean updatePatientInfo(JSONObject json){
		boolean flag = false;
		if(!json.isEmpty()){
			PSPCTmealPatientCard patientInfo = new PSPCTmealPatientCard();
			try{
				//组装实体类信息
				patientInfo.setBedNo(json.getString("bedNo"));
				patientInfo.setBuilding(json.getString("building"));
				patientInfo.setBuildingNum(json.getString("buildingNum"));
				patientInfo.setDeptNum(json.getString("deptNum"));
				patientInfo.setDpetName(json.getString("deptName"));
				patientInfo.setFloor(json.getString("floor"));
				patientInfo.setFloorNum(json.getString("floorNum"));
				patientInfo.setPatientName(json.getString("userName"));
				patientInfo.setPatientNum(json.getString("hospitalNo"));
				patientInfo.setPatientTel(json.getString("userTelNumber"));
				patientInfo.setResvisor(json.getString("recCreator"));
				patientInfo.setReviseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				patientInfo.setDataGroupCode(json.getString("dataGroupCode"));
				//执行update语句更新病员同步表
				flag = updateBySql("PSPCTmealPatientCard.update",patientInfo);
			}catch(Exception e){
				e.printStackTrace();
				return flag;
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * update数据
	 *
	 * @Title: updateBySql 
     * @param sql : 执行的update语句
     * @param bean : 要保存的
	 * @return 
	 * @return: boolean 
	 * @author: liutao
	 * @date: 2021年9月9日 上午11:12:11
	 */
	public boolean updateBySql(String sql,DaoEPBase bean){
		boolean flag = false;
		try{
			EiInfo info = new EiInfo();
			info.addBlock("result").addRow(bean);
			//updateAPI
			EiInfo update = super.update(info, sql);
			if(update.getStatus() > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 
	 * insert数据
	 *
	 * @Title: insertBySql 
     * @param sql : 执行的insert语句
     * @param bean : 要保存的
	 * @return 
	 * @return: boolean 
	 * @author: liutao
	 * @date: 2021年9月9日 上午11:13:10
	 */
	public boolean insertBySql(String sql,DaoEPBase bean){
		boolean flag = false;
		try{
			EiInfo info = new EiInfo();
			info.addBlock("result").addRow(bean);
			EiInfo insert = super.insert(info, sql);
			if(insert.getStatus() > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	

	/**
	 * 
	 * insert数据List
	 *
	 * @Title: insertListBySql 
     * @param sql : 执行的insert语句
     * @param list : 要保存的List<DaoEPBase>数据
	 * @return 
	 * @return: boolean 
	 * @author: liutao
	 * @date: 2021年9月9日 上午11:13:26
	 */
	public boolean insertListBySql(String sql,List<DaoEPBase> list){
		boolean flag = false;
		try{
			EiInfo info = new EiInfo();
			info.addBlock("result").addRows(list);
			EiInfo insert = super.insert(info, sql);
			if(insert.getStatus() > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
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
	 * @date: 2021年9月9日 上午11:14:00
	 */
	public void updateMenuNumCancel(PSPCTmealOrderBillPatient bill, List<PSPCTmealOrderMenu02> orderMenuList){
		//用餐时间 餐次 菜品编码 菜品数量 食堂
		String mealDate = bill.getNeedDate();
		String mealNum = bill.getMealNum();
		String canteenNum= bill.getCanteenNum();
		
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("canteenNum", canteenNum);
		map.put("mealDate", mealDate);
		map.put("mealNum", mealNum);
		
		for (PSPCTmealOrderMenu02 orderMenu : orderMenuList) {
			String menuNum = orderMenu.getMenuNum();
			String num = orderMenu.getMenuNumber();

			map.put("menuNum", menuNum);
			map.put("num", num);
			try {
				//更新菜品数量
				HashMap<String,Object> paramMap = new HashMap<String, Object>();
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

	/**
	 * 保存订单评价
	 * 1.查询订单信息
	 * 2.转换评价记录集合
	 * 3.新增记录到订单评价表
	 * 4.更新订单明细表的评价信息
	 * 5.评价成功直接变更订单状态99已结束
	 *
	 * @Title: savePatientEval
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:43:41
	 */
	public EiInfo savePatientEval(EiInfo inInfo) {
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
				inInfo.setStatus(result.isSuccess() ? 1 : -1);
				inInfo.set("result", result);
				return inInfo;
			}
			if(StringUtils.isBlank(billNo)||StringUtils.isBlank(userCode)) {
				result.setSuccess(false);
				result.setRespMsg("参数错误，请检查");
				inInfo.setStatus(result.isSuccess() ? 1 : -1);
				inInfo.set("result", result);
				return inInfo;
			}

			/**1.查询订单信息**/
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sql","PSPCTmealOrderBillPatient.queryBillByBillNo");
			paramMap.put("str",billNo);
			EiInfo callQueryBillInfo = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);
			List<PSPCTmealOrderBillPatient> bills = (List<PSPCTmealOrderBillPatient>) callQueryBillInfo.get("result");
			if(bills == null || bills.size() == 0) {
				result.setSuccess(false);
				result.setRespMsg("该参数未查询到相关单据信息，请检查参数!");
				inInfo.setStatus(result.isSuccess() ? 1 : -1);
				inInfo.set("result", result);
				return inInfo;
			}
			PSPCTmealOrderBillPatient mainOrder = bills.get(0);

			/**2.转换评价记录集合*/
			List<PSPCTevalType> evalTypes = LocalServiceUtil.objectCastListByJson(ja.get("evalList"),PSPCTevalType.class);
			PSPCTorderEval entity = new PSPCTorderEval();
			entity.setMenuNum(billNo);
			entity.setEvalUserName(userCode);
			entity.setEvalTime(CalendarUtils.dateTimeFormat(new Date()));
			entity.setSendDate(ja.getString("sendDate"));
			entity.setCanteenNum(ja.getString("canteenNum"));
			//List<PSPCTorderEval> entitys = null;
			for (int i = 0; i < evalTypes.size(); i++) {
				PSPCTevalType evalType = evalTypes.get(i);
				entity.setId(Sid.nextShort());
				entity.setEvalType(evalType.getEvalType());
				entity.setEvalTypeName(evalType.getEvalTypeName());
				entity.setEvalLevel(evalType.getEvalLevel());
				entity.setEvalContent(evalType.getEvalContent());
				/**3.新增记录到订单评价表*/
				dao.insert("PSPCTorderEval.insert", entity);
			}
			List<PSPCTorderEval> orderEvals = LocalServiceUtil.objectCastListByJson(ja.get("billMenuList"),PSPCTorderEval.class);
			for (PSPCTorderEval list : orderEvals) {
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("evalLevel", list.getEvalLevel());
				map.put("billNo", ja.getString("billNo"));
				map.put("menuNum", list.getMenuNum());
				map.put("recRevisor", ja.getString("userCode"));
				map.put("recReviseTime", CalendarUtils.dateTimeFormat(new Date()));
				map.put("evalContent", list.getEvalContent());
				/**4.更新订单明细表的评价信息*/
				dao.update("PSPCTorderEval.updateBillDetailLevel", map);
			}
			/**5.评价成功直接变更订单状态99已结束*/
			PSPCBillStatusInfo billInfo = new PSPCBillStatusInfo();
			billInfo.setPboTbName("sc_order_bill_patient");
			billInfo.setBeforeStatus("03");
			billInfo.setAfterStatus("99");
			billInfo.setCurrentDealer(mainOrder.getCurrentDealer());
			billInfo.setBillId(mainOrder.getId());
			billInfo.setCreatorId(mainOrder.getRecCreator());
			billInfo.setCreatorName(mainOrder.getUserName());
			billInfo.setPboCode("patient_meal");
			billInfo.setHandleAdvice("提交");
			billInfo.setOprationRoute("评价成功确认状态");
			System.out.println(billInfo.toString());
			//变更订单状态
			paramMap.put("billInfo", billInfo);
			EiInfo callStatusChange = LocalServiceUtil.call("PSPCStatusChange", "statusChange", paramMap);
			RespResult billStatusChangeResult = (RespResult) callStatusChange.get("result");
			System.out.println("状态变更:"+billStatusChangeResult.getRespCode()+billStatusChangeResult.getRespMsg());
			if("200".equals(billStatusChangeResult.getRespCode())) {
				result.setSuccess(true);
			}else {
				result.setRespMsg("变更订单状态失败");
				result.setSuccess(false);
			}
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
			PSPCTmealOrderBillPatient bill = new PSPCTmealOrderBillPatient();
			bill.fromMap(hashMap);
			/**1.判断订单有没有生成payNo*/
			if(!StringUtil.isBlank(payNo)) {
				/**2.查询支付节点获取支付结果，为false表示该订单支付失败*/
				//获取支付结果
				boolean flag = new ServicePSPCTimer01().getPayStatus(payType,payNo,canteenNum);
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

}
