package com.baosight.wilp.ps.bm.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.wilp.ss.bm.utils.*;
import com.bonawise.smp.ResponseResult;
import com.bonawise.smp.alipay.hessian.AliPaySender;
import com.bonawise.smp.weChat.hessian.WeChatPayImpl;
import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ps.bm.domain.PSBMDdcx01;
import com.baosight.wilp.ps.pc.domain.PSPCBillStatusInfo;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOperation;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOrderBillPatient;
import com.baosight.wilp.ss.bm.config.MealCommonConfig;
import com.bonawise.domain.base.BusinessInfoDTO;
import com.bonawise.domain.base.ResultEntry;
import com.bonawise.smp.ResponseResult;
import com.bonawise.smp.alipay.hessian.AliPaySender;
import com.bonawise.smp.weChat.hessian.WeChatPayImpl;

/**
 * 
 * ServicePSBMDDCX01 病员订单综合查询service
 * 
 * @Title: ServicePSBMDDCX01.java
 * @ClassName: ServicePSBMDDCX01
 * @Package：com.baosight.wilp.ps.bm.service
 * @author: liutao
 * @date: 2021年9月9日 上午10:09:56
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePSBMDDCX01 extends ServiceBase{
	
    
    /**
     * initLoad初始化方法
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new PSBMDdcx01());
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
		return initLoad;
	}


	/**
	 * query 查询方法
	 * @param inInfo
	 * @return
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "PSBMDdcx01.query", new PSBMDdcx01());
		return outInfo;
	}
	
	/**
	 * 
	 * cancel 撤单方法(暂未使用，目前撤单退款使用ServicePSPCDDGL01.cancelOrder方法)
	 * 1.查询订单信息
	 * 2.判断登录账号是否有权限作废订单
	 * 3.订餐专项改进,退款成功直接更改状态,不通过工作流
	 * 4.支付宝 微信退款
	 * 5.添加操作记录时间
	 * @Title: cancel 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:11:45
	 */
	public EiInfo cancel(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			String userId = UserSession.getLoginName();
			//订单id
			String id = StringUtil.toString(inInfo.getAttr().get("id"));
			//作废原因
			String comments = StringUtil.toString(inInfo.getAttr().get("comments"));
			
			if(StringUtils.isBlank(comments)){
				outInfo.setMsg("作废原因不能为空！");
				outInfo.setStatus(-1);
				return outInfo;
			}
			if(StringUtils.isBlank(id)){
				outInfo.setMsg("单号ID不能为空！");
				outInfo.setStatus(-1);
				return outInfo;
			}
			if(StringUtil.isNotEmpty(id)){
				//1.查询订单信息
				HashMap<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("sql","PSPCTmealOrderBillPatient.queryBillById");
				paramMap.put("str",id);
				EiInfo callQueryPatient = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
				List<PSPCTmealOrderBillPatient> bills = (List<PSPCTmealOrderBillPatient>) callQueryPatient.get("result");
				
				PSPCTmealOrderBillPatient bill = bills.get(0);
				//当前处理人
				String curDealer = bill.getCurrentDealer();
				//订单状态
				String status = bill.getStatusCode();
				//支付类型
				String payType = bill.getPayType();
				
				String currentDealer = bill.getCurrentDealer();
				
				//2.判断登录账号是否有权限作废订单
				String[] currentDealers = currentDealer.split(",");
				List<String> list = Arrays.asList(currentDealers);
				if(Collections.binarySearch(list, userId) < 0){
					outInfo.setMsg("你没有权限作废此订单，请联系食堂管理人员！");
					outInfo.setStatus(-1);
					return outInfo;
				} else if(!("00".equals(status) || "01".equals(status) || "02".equals(status) || "03".equals(status))) {
					outInfo.setMsg("该单据号不处在可作废状态，无法作废！");
					outInfo.setStatus(-1);
					return outInfo;
				} else {
					//3.订餐专项改进,退款成功直接更改状态,不通过工作流
					PSPCBillStatusInfo billInfo = new PSPCBillStatusInfo();
					billInfo.setPboTbName("sc_order_bill_patient");
					billInfo.setBeforeStatus(bill.getStatusCode());
					billInfo.setAfterStatus("99");
					billInfo.setCurrentDealer(curDealer);
					billInfo.setBillId(bill.getId());
					billInfo.setCreatorId(bill.getRecCreator());
					billInfo.setCreatorName(bill.getUserName());
					billInfo.setPboCode("PATIENT_MEAL");
					billInfo.setHandleAdvice("确定作废");
					billInfo.setIsReject("1");
					billInfo.setRejectCode("2");
					billInfo.setOprationRoute("确定作废");
					billInfo.setRejectReason("确定作废");
					System.out.println("billInfo:" + billInfo.toString());
					EiInfo callStatusChange = null;
					// 草稿状态不可退款，其余状态都可退款
					int sc = Integer.parseInt(status);
					if (sc > 0) {
						String backMoney = "onLineBack";
						HashMap<String,String> map = new HashMap<String,String>();
						map.put("mealgroupTypeCode", backMoney);
						List<HashMap<String,Object>> listType = dao.query("SSBMStxx01.getMealTypeData", map);
						String typeCode = StringUtil.toString(listType.get(0).get("typecode"));
						System.out.println("-----------正在进入配置typecode-----------" + typeCode);
						//判断是否有在线退款配置，且处于开启状态
						if (list.size() > 0 && "1".equals(typeCode)) {
							String totalPrice = bill.getBillTotalPrice().toString();
							String billNo = bill.getBillNo();
							//统一支付编号
							String payNo = bill.getPayNo();
							String payType1 = bill.getPayType().substring(0, 2);
							System.out.println("-----------正在进入付款流程payType-----------" + payType1);
							//支付宝退款
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
								System.out.println("--------------开始支付宝退款,查询支付宝交易HospitalCode:" + hospitalCodeAli + ",payNo:" + payNo);
								ResponseResult payresult = AliPaySender.singleTradeQuery(hospitalCodeAli, "patient_ali", payNo);
								System.out.println("-----------支付宝支付结果" + payresult.getRespCode() + "支付信息:" + payresult.getRespMsg());
								// 判断是否已支付完成
								if ("200".equals(payresult.getRespCode())) {
									ResponseResult resultEntry = AliPaySender.refundFastPay(aiReMap);
									if (!"200".equals(resultEntry.getRespCode())) {
										outInfo.setMsg("支付宝退款失败！");
										outInfo.setStatus(-1);
										return outInfo;
									}else {
										//变更订单状态
										paramMap.put("billInfo", billInfo);
										callStatusChange = LocalServiceUtil.callNewTx("PSPCStatusChange", "statusChange", paramMap);
									}
								} 
							} else if ("02".equals(payType1)) {
								// 微信退款
								String hospitalCodeWx = MealCommonConfig.getSmpConfig().getHospitalCodeWechat();
								Integer price = bill.getBillTotalPrice().multiply(new BigDecimal(100)).intValue();
								BusinessInfoDTO busInfoDTO = new BusinessInfoDTO();
								busInfoDTO.setHospitalCode(hospitalCodeWx);
								busInfoDTO.setModulCode("patient_wechat");
								busInfoDTO.setUsedUnitCode(bill.getCanteenNum());
								busInfoDTO.setUserUnitName(bill.getCanteenName());
								System.out.println("--------------开始微信退款,查询微信交易HospitalCode:" + hospitalCodeWx + ",payNo:" + payNo);
								//微信查询支付状态
								ResultEntry weChatResult = WeChatPayImpl.OrderQuery(bill.getPayNo(), busInfoDTO);
								System.out.println("-----------微信支付结果" + weChatResult.getRespCode() + "支付信息:" + weChatResult.getRespMsg());
								if(weChatResult.getRespCode() == 200) {
									//订单已支付，调用支付节点退款方法
									ResultEntry resultEntry = WeChatPayImpl.refund(busInfoDTO, payNo, price);
									if (!"200".equals(resultEntry.getRespCode().toString())) {
										//退款失败
										outInfo.setMsg("微信退款失败！");
										outInfo.setStatus(-1);
										return outInfo;
									}else {
										//变更订单状态
										paramMap.put("billInfo", billInfo);
										callStatusChange = LocalServiceUtil.callNewTx("PSPCStatusChange", "statusChange", paramMap);
									}
								}
							}
						}
					}
					
					RespResult billStatusChangeResult = (RespResult) callStatusChange.get("result");
					System.out.println("订单状态变更：" + bill.getBillNo() + billStatusChangeResult.getRespMsg());

					String respCode = billStatusChangeResult.getRespCode();
					if(!respCode.equals("200")){
						outInfo.setMsg("变更订单状态失败！"+billStatusChangeResult.getRespMsg());
						outInfo.setStatus(-1);
						return outInfo;
					}else{
						//5.添加操作记录时间
						String username = bill.getUserName();
						String recCreator = bill.getRecCreator();
						PSPCTmealOperation operateRecord = new PSPCTmealOperation();
						operateRecord.setId(UUIDUtils.getUUID32());
						operateRecord.setOperationTime(CalendarUtils.dateTimeFormat(new Date()));
						operateRecord.setBillNo(bill.getBillNo());
						operateRecord.setCreatorName(username);
						operateRecord.setOperationRoute("06");
						operateRecord.setCreatorId(recCreator);
						paramMap.put("sql","PSPCTmealOperation.insert");
						paramMap.put("domain",operateRecord);
						EiInfo callInsertOperation = LocalServiceUtil.call("SSBMTy", "insertSqlByDomain", paramMap);
						System.out.println("订单消费记录保存Operation:"+Boolean.parseBoolean(StringUtil.toString(callInsertOperation.get("success"))));
						
						operateRecord.setId(UUIDUtils.getUUID32());
						operateRecord.setOperationTime(CalendarUtils.dateTimeFormat(new Date()));
						operateRecord.setOperationRoute("07");
						paramMap.put("sql","PSPCTmealOperation.insert");
						paramMap.put("domain",operateRecord);
						LocalServiceUtil.call("SSBMTy", "insertSqlByDomain", paramMap);
					}
				}
			}else{
				outInfo.setMsg("该订单不存在");
				outInfo.setStatus(-1);
				return outInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			outInfo.setMsg("操作失败！");
		}
		return outInfo;
	}



}
