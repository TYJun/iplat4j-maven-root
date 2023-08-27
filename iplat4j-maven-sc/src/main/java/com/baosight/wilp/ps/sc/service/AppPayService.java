package com.baosight.wilp.ps.sc.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.ss.bm.utils.HttpClientUtil;
import com.baosight.wilp.ss.bm.utils.LocalServiceUtil;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import com.baosight.wilp.ss.bm.utils.ResultData;



/**
 * 
 * 订单支付中转Service
 * 
 * @Title: AppPayService.java
 * @ClassName: AppPayService
 * @Package：com.baosight.wilp.ps.sc.service
 * @author: liutao
 * @date: 2021年9月9日 下午2:01:25
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class AppPayService {


    /**
     * 
     * 农行掌银支付
     *
     * @Title: abcPay 
     * @param request
     * @param response
     * @return 
     * @return: ResultData 
     * @author: liutao
     * @date: 2021年9月9日 下午2:01:51
     */
	public ResultData abcPay(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String consumeMoney = request.getParameter("consumeMoney");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("consumeMoney",consumeMoney);
		try {
			
			EiInfo call = LocalServiceUtil.call("PSPCABCPay", "abcPay", paramMap);
			String result = (String) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCPAY01.abcPay失败！");
			}else {
				System.out.println("调用PSPCPAY01.abcPay成功！");
			}
			System.out.println(result);
		} catch (Exception e) {
			resultData.setSuccess(false);
			resultData.setRespCode("201");
			resultData.setRespMsg("程序异常");
		}
		
		return resultData;
	}
	

	/**
	 * 
	 * POS机扫码支付
	 *
	 * @Title: posPayByScanOnline 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午2:02:13
	 */
	public ResultData posPayByScanOnline(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String json = request.getParameter("json");
		String userId = request.getParameter("userId");
		String clinetIp = HttpClientUtil.getClinetIp(request);
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("json",json);
		paramMap.put("userId",userId);
		paramMap.put("clinetIp",clinetIp);
		try {
			
			EiInfo call = LocalServiceUtil.call("PSPCPAY01", "posPayByScanOnline", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCPAY01.posPayByScanOnline失败！");
			}else {
				System.out.println("调用PSPCPAY01.posPayByScanOnline成功！");
			}
		} catch (Exception e) {
			resultData.setSuccess(false);
			resultData.setRespCode("201");
			resultData.setRespMsg("程序异常");
		}
		System.out.println(resultData);
		return resultData;
	}
	
	/**
	 *
	 * 病员APP充值
	 *
	 * @Title: patientAppRecharge
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:28:23
	 */
	public ResultData patientAppRecharge(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();

		String userNo = request.getParameter("userNo");
		String consumeMoney = request.getParameter("consumeMoney");
		String payType = request.getParameter("payType");
		String userId = request.getParameter("userId");
		String openId = request.getParameter("openId");
		String clinetIp = HttpClientUtil.getClinetIp(request);
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userNo",userNo);
		paramMap.put("payType",payType);
		paramMap.put("consumeMoney",consumeMoney);
		paramMap.put("userId",userId);
		paramMap.put("openId",openId);
		paramMap.put("clinetIp",clinetIp);
		try {

			EiInfo call = LocalServiceUtil.call("PSPCPAY01", "patientAppRecharge", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSACPAY01.patientAppRecharge失败！");
			}else {
				System.out.println("调用PSACPAY01.patientAppRecharge成功！");
				/*if("0101".equals(payType)) {
					// 支付宝支付直接将完整的表单html输出到页面
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().write(resultData.getRespMsg());
					response.getWriter().flush();
					response.getWriter().close();
					return null;
				}else if("0201".equals(payType)) {
					response.sendRedirect((String) resultData.getObj());
				}*/
			}
		} catch (Exception e) {
			resultData.setSuccess(false);
			resultData.setRespCode("201");
			resultData.setRespMsg("程序异常"+e.getMessage());
		}
		System.out.println(resultData);
		return resultData;
	}

	/**
	 * 
	 * 现金支付
	 *
	 * @Title: cashPosAll 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午2:02:01
	 */
	public ResultData cashPosAll(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String billNos = request.getParameter("billNos");
		String payType = request.getParameter("payType");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("billNos",billNos);
		paramMap.put("payType",payType);
		try {
			EiInfo call = LocalServiceUtil.call("PSPCPAY01", "cashPosAll", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCPAY01.cashPosAll失败！");
			}else {
				resultData.setRespMsg("支付成功");
				System.out.println("调用PSPCPAY01.cashPosAll成功！");
			}
		} catch (Exception e) {
			resultData.setSuccess(false);
			resultData.setRespCode("201");
			resultData.setRespMsg("程序异常");
		}
		return resultData;
	}

	/**
	 *
	 * 病员支付密码校验
	 *
	 * @Title: patientLogin
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:59:46
	 */
	public ResultData checkPayPassword(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();

		String userNo = request.getParameter("userNo");
		String passWord = request.getParameter("passWord");
		String dataGroupCode = request.getParameter("dataGroupCode");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userNo",userNo);
		paramMap.put("passWord",passWord);
		paramMap.put("dataGroupCode",dataGroupCode);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCPAY01", "checkPayPassword", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("PSPCPAY01.checkPayPassword！");
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
		System.out.println(resultData);
		return resultData;
	}
}
