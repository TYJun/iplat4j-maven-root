package com.baosight.wilp.ps.sc.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOrderBillPatient;
import com.baosight.wilp.ss.ac.domain.SSACTscWorkOrderBill;
import com.baosight.wilp.ss.bm.utils.LocalServiceUtil;
import com.baosight.wilp.ss.bm.utils.ResultData;


/**
 * 
 * 移动端订单中转Service
 * 
 * @Title: AppOrderService.java
 * @ClassName: AppOrderService
 * @Package：com.baosight.wilp.ps.sc.service
 * @author: liutao
 * @date: 2021年9月9日 下午2:00:14
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class AppOrderService {
	

    /**
     * 
     * 取消订单
     *
     * @Title: cancelOrder 
     * @param request
     * @param response
     * @return 
     * @return: ResultData 
     * @author: liutao
     * @date: 2021年9月9日 下午2:00:40
     */
	public ResultData cancelOrder(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String pBillNo = request.getParameter("billNo");
		String userCode = request.getParameter("userCode");
		String rejectReason = request.getParameter("rejectReason");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pBillNo",pBillNo);
		paramMap.put("userCode",userCode);
		paramMap.put("rejectReason",rejectReason);
		try {
			
			EiInfo call = LocalServiceUtil.call("PSPCDDGL01", "cancelOrder", paramMap);
			//调用结果
			resultData = (ResultData) call.getAttr().get("result");
			if(call.getStatus() < 0){
				//Status < 0 时事务回滚
				System.out.println("调用PSPCDDGL01.cancelOrder失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultData.setRespCode("201");
			resultData.setRespMsg("程序异常");
			resultData.setSuccess(false);
		}
		System.out.println(resultData);
		return resultData;
	}


	/**
	 * 
	 * 保存订单
	 *
	 * @Title: saveAll 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午2:00:55
	 */
	public ResultData saveAll(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String json=request.getParameter("json");
		String archiveFlag=request.getParameter("archiveFlag");
		String saveInfoFlag=request.getParameter("saveInfoFlag");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("json",json);
		paramMap.put("archiveFlag",archiveFlag);
		paramMap.put("saveInfoFlag",saveInfoFlag);
		try {
			EiInfo call = LocalServiceUtil.call("PSPCDDGL01", "saveAll", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				//Status < 0 时事务回滚
				System.out.println("调用PSPCDDGL01.saveAll失败！");
				resultData.setRespCode("201");
				resultData.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultData.setRespCode("201");
			resultData.setRespMsg("程序异常");
			resultData.setSuccess(false);
		}
		System.out.println(resultData);
		return resultData;
	}

	/**
	 *
	 * 保存订单评价
	 *
	 * @Title: savePatientEval
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:56:10
	 */
	public ResultData savePatientEval(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String json=request.getParameter("json");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("json",json);
		try {
			EiInfo call = LocalServiceUtil.call("PSPCDDGL01", "savePatientEval", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.getEvalTypeByCanteenNum！");
				resultData.setRespCode("201");
				resultData.setSuccess(false);
			}else {
				resultData.setRespCode("200");
				resultData.setRespMsg("操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultData.setRespCode("201");
			resultData.setRespMsg("程序异常");
			resultData.setSuccess(false);
		}
		return resultData;
	}

	/**
	 * 订单再次支付校验
	 *
	 * @Title: billRepayCheck
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:27:40
	 */
	public ResultData billRepayCheck(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String billNo=request.getParameter("billNo");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("billNo",billNo);
		String message = "";
		try {
			//查询订单信息
			EiInfo callBillInfo = LocalServiceUtil.callNoTx("PSPCDCXX01", "getBillDetail", paramMap);
			List<PSPCTmealOrderBillPatient> result = (List<PSPCTmealOrderBillPatient>) callBillInfo.get("obj");
			//判断是否超时执行订单操作
			if(result.size() > 0){

				if ("001".equals(result.get(0).getMealNum())) {
					message += result.get(0).getId() + "," + result.get(0).getBillNo() + "," + result.get(0).getBillTotalPrice() + ";";
				}else{
					message += "0,0,0;";
				}
				if ("002".equals(result.get(0).getMealNum())) {
					message += result.get(0).getId() + "," + result.get(0).getBillNo() + "," + result.get(0).getBillTotalPrice() + ";";
				}else{
					message += "0,0,0;";
				}
				if ("003".equals(result.get(0).getMealNum())) {
					message += result.get(0).getId() + "," + result.get(0).getBillNo() + "," + result.get(0).getBillTotalPrice() + ";";
				}else{
					message += "0,0,0;";
				}

				if(message.endsWith(";")){
					message = message.substring(0, message.length()-1);
				}

				EiInfo call = LocalServiceUtil.callNewTx("PSPCTimer01", "billHandle", result.get(0).toMap());
				resultData.setRespCode(call.getMsgKey());
				resultData.setRespMsg(call.getMsg());
				//调用结果
				if(call.getStatus() < 0){
					//Status < 0 时事务回滚
					System.out.println("调用PSPCTimer01.billHandle失败！");
					resultData.setRespCode("210");
					resultData.setRespMsg("调用PSPCTimer01.billHandle失败");
					resultData.setSuccess(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultData.setRespCode("201");
			resultData.setRespMsg("程序异常");
			resultData.setSuccess(false);
		}
		System.out.println(resultData);
		resultData.setRespMsg(message);
		return resultData;
	}

	/**
	 * 订单支付状态校验
	 *
	 * @Title: billPayStatusCheck
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2022年9月2日 下午3:27:40
	 */
	public ResultData billPayStatusCheck(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String billNo=request.getParameter("billNo");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("billNo",billNo);
		try {
			//查询订单信息
			EiInfo callBillInfo = LocalServiceUtil.callNoTx("PSPCDCXX01", "getBillDetail", paramMap);
			List<PSPCTmealOrderBillPatient> result = (List<PSPCTmealOrderBillPatient>) callBillInfo.get("obj");
			//判断是否超时执行订单操作
			if(result.size() > 0){
				EiInfo call = LocalServiceUtil.callNewTx("PSPCDDGL01", "billPayStatusCheck", result.get(0).toMap());
				resultData.setRespCode(call.getMsgKey());
				resultData.setRespMsg(call.getMsg());
				//调用结果
				if(call.getStatus() < 0){
					//Status < 0 时事务回滚
					resultData.setRespCode("210");
					resultData.setRespMsg("调用PSPCDDGL01.billPayStatusCheck失败");
					resultData.setSuccess(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultData.setRespCode("199");
			resultData.setRespMsg("程序异常");
			resultData.setSuccess(false);
		}
		System.out.println(resultData);
		return resultData;
	}
}
