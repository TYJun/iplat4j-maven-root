package com.baosight.wilp.ps.pc.service;


import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOrderMiddle;
import com.baosight.wilp.ss.ac.domain.SSACTworkOrderPayMiddle;
import com.baosight.wilp.ss.bm.config.MealCommonConfig;
import com.baosight.wilp.ss.bm.utils.*;
import com.bonawise.domain.base.BusinessInfoDTO;
import com.bonawise.domain.base.OrderRecord;
import com.bonawise.domain.base.ResultEntry;
import com.bonawise.smp.config.MealGlobalConfig;
import com.bonawise.smp.weChat.hessian.WeChatPayImpl;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 支付hessian回调接口
 * 支付宝
 * 1. EDXM01微服务配置S_PS_PC_ALI_01,服务:PSPCPayNotify,方法:aliMealNotify
 * 2. EDCC03配置信息管理配置名称:iplat.security.anonymous.url
 *    配置内容加入:,/service/S_PS_PC_ALI_01/**
 * 3. httpPost请求路径http://127.0.0.1:8083/WILP/service/S_PS_PC_ALI_01
 * 微信
 * 1. EDXM01微服务配置S_PS_PC_WECHAT_01,服务:PSPCPayNotify,方法:wxMealNotify
 * 2. EDCC03配置信息管理配置名称:iplat.security.anonymous.url
 *    配置内容加入:,/service/S_PS_PC_WECHAT_01/**
 * 3. httpPost请求路径http://127.0.0.1:8083/WILP/service/S_PS_PC_ALI_01
 * 
 * @Title: ServicePSPCPAYNotify.java
 * @ClassName: ServicePSPCPAYNotify
 * @Package：com.baosight.wilp.ps.pc.service
 * @author: liutao
 * @date: 2021年9月9日 下午1:40:13
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePSPCPAYNotify extends ServiceBase {
	/**
	 * log4j日志
	 */
	private static Logger logger = Logger.getLogger(ServicePSPCPAYNotify.class);
	
	/**
	 * 测试方法注册服务名S_SS_BM_ALI_01
	 * */
	public EiInfo test(EiInfo inInfo) {
		Map attr = inInfo.getAttr();
		System.out.println("接收到的参数attr:" + attr);
		System.out.println("测试定时器" + CalendarUtils.dateTimeFormat(new Date()));
		inInfo.set("result", "aaa");

		/**1.组织订单信息,通过hessian提交到支付节点完成扣款*/
		BusinessInfoDTO busInfoDTO = new BusinessInfoDTO();
		busInfoDTO.setHospitalCode(MealCommonConfig.getSmpConfig().getHospitalCodeWechat());//医院编码
		busInfoDTO.setModulCode("work_wechat");//模块编码
		busInfoDTO.setUsedUnitCode("ST000009");//食堂编码
		busInfoDTO.setUserUnitName("在线充值");//食堂名称
		System.out.println("busInfoDTO:"+ busInfoDTO.toString());

//		OrderRecord order = new OrderRecord();
//		order.setOrderId("26abec1383024d80a58bd952bb4a9cf6");//商品ID
//		order.setProductDescription("在线充值");//商品描述
//		order.setTotalFee(new BigDecimal("100").multiply(new BigDecimal("0.01")).intValue());//订单总价
//		order.setProductDetail("");//订单详细描述（非必需）
//		order.setBillNo("d5d6bf138a91476c9887d25155ae796d");//订单号
//		System.out.println("order:" + order.toString() );
//		System.out.println(MealGlobalConfig.getSmpConfig().getCommonNodeUrlWechat() + MealGlobalConfig.getSmpConfig().getHessianPatternWechat());
		ResultEntry resultEntry = WeChatPayImpl.OrderQuery("d3a80299bbfb4b289e208b9c037a520f", busInfoDTO);
		System.out.println(resultEntry);
//		SecurityUtils.test();
		return inInfo;
	}

	
	/**
	 * 
     * 支付宝接收支付节点回调
     * 1.接收回调参数：modulCode,billNo,校验参数是否为空，失败则返回校验结果
     * 2.调用回调分发的方法执行回调
     * 3.成功则返回状态码200，支付节点将不再回调，失败则返回状态码201，等待支付节点下次回调
	 *
	 * @Title: aliMealNotify 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:40:40
	 */
	public EiInfo aliMealNotify(EiInfo inInfo) {
		/**1.接收回调参数：modulCode,billNo,校验参数是否为空，失败则返回校验结果*/
		EiInfo eiInfo = new EiInfo();
		Map attr = inInfo.getAttr();
		eiInfo.set("接收到的参数attr", attr);
		System.out.println("接收到的参数attr:" + attr);
		String moduleCode = StringUtil.toString(attr.get("modulCode"));
		moduleCode = moduleCode.toLowerCase();
		//写作billNo,读作billId
		String billNo = StringUtil.toString(attr.get("billNo"));
		if(StringUtil.isBlank(moduleCode) || StringUtil.isBlank(billNo) ) {
			eiInfo.set("respCode", "201");
			eiInfo.set("respMsg", "modulCode或billNo为空");
			return eiInfo;
		}else {
			/**2.调用回调分发的方法执行回调*/
			eiInfo = doNotify( moduleCode , billNo) ;
			return eiInfo;
		}
	}
	
	
	/**
	 * 
     * 微信接收支付节点回调
     * 1.接收回调参数：modulCode,billNo,校验参数是否为空，失败则返回校验结果
     * 2.调用回调分发的方法执行回调
     * 3.成功则返回状态码200，支付节点将不再回调，失败则返回状态码201，等待支付节点下次回调
	 *
	 * @Title: wxMealNotify 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:40:56
	 */
	public EiInfo wxMealNotify(EiInfo inInfo) {
		/**1.接收回调参数：modulCode,billNo,校验参数是否为空，失败则返回校验结果*/
		EiInfo eiInfo = new EiInfo();
		Map attr = inInfo.getAttr();
		eiInfo.set("接收到的参数attr", attr);
		System.out.println("接收到的参数attr:" + attr);
		String moduleCode = StringUtil.toString(attr.get("modulCode"));
		moduleCode = moduleCode.toLowerCase();
		//订单编号
		String billNo = StringUtil.toString(attr.get("billNo"));
		if(StringUtil.isBlank(moduleCode) || StringUtil.isBlank(billNo) ) {
			eiInfo.set("respCode", "201");
			eiInfo.set("respMsg", "modulCode或billNo为空");
			return eiInfo;
		}else {
//			int andIncrement = at.getAndIncrement();
			/**2.调用回调分发的方法执行回调*/
			eiInfo = doNotify( moduleCode , billNo) ;
			return eiInfo;
		}
	}

	/**
	 * 锁
	 **/
	private static final Lock lock = new ReentrantLock();
	private static AtomicInteger at = new AtomicInteger(0);
	/**
	 * 
     * 回调分发
     * 1.将回调信息同步到订单中间表，若存在中间表信息则更新，若不存在则新增记录
     * 2.根据properties配置中，不同模块的回调方法执行回调
     * 3.若回调方法执行失败同时中间表操作也失败，则返回失败信息，有一种执行成功则返回成功
     * 4.成功则返回状态码200，支付节点将不再回调，失败则返回状态码201，等待支付节点下次回调
	 *
	 * @Title: doNotify 
	 * @param moduleCode
	 * @param billNo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:41:11
	 */
	public EiInfo doNotify(String moduleCode , String billNo) {
		EiInfo eiInfo = new EiInfo();
		eiInfo.set("respCode", "200");
		eiInfo.set("respMsg", "回调成功");
		lock.lock();
		boolean flag = true;
		try {
			/**1.将回调信息保存到中间表*/
			if(moduleCode.contains("patient")){
				//病患回调信息保存到中间表
				flag = patientNotifyOrderMiddle(moduleCode, billNo);
			}else {
				//职工回调信息保存到中间表
				flag = workNotifyOrderMiddle(moduleCode, billNo);
			}
			/**2.根据文件mealGlobalConfig.properties中的配置，不同模块的回调方法分别执行回调*/
			Map<String, String> notifyMap = MealCommonConfig.getSmpConfig().getNotifyMap();
			String notifyService = notifyMap.get(moduleCode+"_service");
			String notifyMethod = notifyMap.get(moduleCode+"_method");
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("billNo", billNo);
			//调用对应的方法,开启新事务，即使失败了也不影响保存中间表
			EiInfo callNotify = LocalServiceUtil.callNewTx(notifyService, notifyMethod, paramMap);
			ResultData result = (ResultData)callNotify.get("result");
			System.out.println(result.getRespCode() + result.getRespMsg());
			/**3.若回调方法执行失败同时中间表操作也失败，则返回失败信息，有一种执行成功则返回成功*/
			if(callNotify.getStatus() < 0 && !flag) {
				eiInfo.set("respCode", "201");
				eiInfo.set("respMsg", "更新订单状态失败，且中间表操作失败！");
				return eiInfo;
			}else {
				/**4.成功则返回状态码200，支付节点将不再回调，失败则返回状态码201，等待支付节点下次回调*/
				eiInfo.set("respCode", "200");
				return eiInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			eiInfo.set("respCode", "201");
			eiInfo.set("respMsg", "回调方法出现异常");
			return eiInfo;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 病患回调信息保存到中间表
	 * @Title  patientNotifyOrderMiddle
	 * @author liu
	 * @date 2022-09-07 14:50
	 * @param moduleCode
	 * @param billNo
	 * @return boolean
	 */
	private boolean patientNotifyOrderMiddle(String moduleCode, String billNo) throws Exception {
		boolean flag;
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("sql","PSPCTmealOrderMiddle.queryByBillId");
		paramMap.put("str", billNo);
		EiInfo callOrderMiddle = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);
		List<PSPCTmealOrderMiddle> cardConsumeRecordList = (List<PSPCTmealOrderMiddle>) callOrderMiddle.get("result");
		if(cardConsumeRecordList.size() > 0) {
			//存在中间表记录
			//更新中间表的支付状态payStatus

			HashMap<String,String> pMap = new HashMap<String, String>();
			pMap.put("payStatus", "success");
			pMap.put("moduleCode", moduleCode);
			pMap.put("payNo", billNo);

			paramMap.put("sql", "PSPCTmealOrderMiddle.updatePayStatus");
			paramMap.put("map", pMap);
			EiInfo callUpdate = LocalServiceUtil.callNewTx("SSBMTy", "updateSqlByMap", paramMap);
			System.out.println("病患更新订单中间表:"+callUpdate.get("success"));
			flag = Boolean.parseBoolean(StringUtil.toString(callUpdate.get("success")));
		}else {
			//不存在中间表记录
			PSPCTmealOrderMiddle pspcTmealOrderMiddle = new PSPCTmealOrderMiddle();
			pspcTmealOrderMiddle.setId(UUIDUtils.getUUID32());
			pspcTmealOrderMiddle.setBillId(billNo);
			pspcTmealOrderMiddle.setModuleCode(moduleCode);
			pspcTmealOrderMiddle.setPayStatus("success");
			//插入中间表记录
			paramMap.put("sql", "PSPCTmealOrderMiddle.insert");
			paramMap.put("domain", pspcTmealOrderMiddle);
			EiInfo callInsert = LocalServiceUtil.callNewTx("SSBMTy", "insertSqlByDomain", paramMap);
			System.out.println("病患新增订单中间表:"+callInsert.get("success"));
			flag = Boolean.parseBoolean(StringUtil.toString(callInsert.get("success")));
		}
		return flag;
	}

	/**
	 * 职工回调信息保存到中间表
	 * @Title  workNotifyOrderMiddle
	 * @author liu
	 * @date 2022-09-07 14:50
	 * @param moduleCode
	 * @param billNo
	 * @return boolean
	 */
	private boolean workNotifyOrderMiddle(String moduleCode, String billNo) throws Exception {
		boolean flag;
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("sql","SSACTworkOrderPayMiddle.queryById");
		paramMap.put("str", billNo);
		EiInfo callOrderMiddle = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);
		List<SSACTworkOrderPayMiddle> cardConsumeRecordList = (List<SSACTworkOrderPayMiddle>) callOrderMiddle.get("result");
		if(cardConsumeRecordList.size() > 0) {
			//存在中间表记录
			HashMap<String,String> pMap = new HashMap<String, String>();
			pMap.put("payStatus", "success");
			pMap.put("moduleCode", moduleCode);
			pMap.put("payNo", billNo);
			//更新中间表的支付状态payStatus
			paramMap.put("sql", "SSACTworkOrderPayMiddle.updatePayStatus");
			paramMap.put("map", pMap);
			EiInfo callUpdate = LocalServiceUtil.callNewTx("SSBMTy", "updateSqlByMap", paramMap);
			System.out.println("职工更新订单中间表:"+callUpdate.get("success"));
			flag = Boolean.parseBoolean(StringUtil.toString(callUpdate.get("success")));
		}else {
			//不存在中间表记录
			SSACTworkOrderPayMiddle ssacTmealOrderMiddle = new SSACTworkOrderPayMiddle();
			ssacTmealOrderMiddle.setId(UUIDUtils.getUUID32());
			ssacTmealOrderMiddle.setBillId(billNo);
			ssacTmealOrderMiddle.setModuleCode(moduleCode);
			ssacTmealOrderMiddle.setPayStatus("success");
			//插入中间表记录
			paramMap.put("sql", "SSACTworkOrderPayMiddle.insert");
			paramMap.put("domain", ssacTmealOrderMiddle);
			EiInfo callInsert = LocalServiceUtil.callNewTx("SSBMTy", "insertSqlByDomain", paramMap);
			System.out.println("职工新增订单中间表:"+callInsert.get("success"));
			flag = Boolean.parseBoolean(StringUtil.toString(callInsert.get("success")));
		}
		return flag;
	}
}
