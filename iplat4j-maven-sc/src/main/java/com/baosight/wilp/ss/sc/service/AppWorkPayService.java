package com.baosight.wilp.ss.sc.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.ss.bm.utils.HttpClientUtil;
import com.baosight.wilp.ss.bm.utils.LocalServiceUtil;
import com.baosight.wilp.ss.bm.utils.ResultData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;


/**
 * 
 * 职工移动端支付中转Service
 * 
 * @Title: AppWorkPayService.java
 * @ClassName: AppWorkPayService
 * @Package：com.baosight.wilp.ss.sc.service
 * @author: liutao
 * @date: 2021年9月9日 下午3:27:58
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class AppWorkPayService {


    /**
     * 
     * 职工APP支付校验支付密码
     *
     * @Title: checkPayPassword 
     * @param request
     * @param response
     * @return 
     * @return: ResultData 
     * @author: liutao
     * @date: 2021年9月9日 下午3:28:11
     */
	public ResultData checkPayPassword(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		try {
			EiInfo eiInfo = new EiInfo();
	        eiInfo.set(EiConstant.serviceId,"S_XS_08");
	        String workNo=request.getParameter("workNo");
	        String passWord=request.getParameter("passWord");
	        eiInfo.set("loginName",workNo);
	        eiInfo.set("password",passWord);
	        EiInfo outInfo = XServiceManager.call(eiInfo);
	        int status=outInfo.getStatus();
	        String msg=outInfo.getMsg();
	        if(status < 0) {
	        	System.out.println(msg);
	        	resultData.setSuccess(false);
	        	resultData.setRespCode("201");
	        	resultData.setRespMsg("用户名或密码不正确");
	        }else {
	        	resultData.setSuccess(true);
	        	resultData.setRespCode("200");
	        	resultData.setRespMsg("校验通过");
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
	 * 职工APP充值
	 *
	 * @Title: workAppRecharge 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:28:23
	 */
	public ResultData workAppRecharge(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String workNo = request.getParameter("workNo");
		String consumeMoney = request.getParameter("consumeMoney");
		String payType = request.getParameter("payType");
		String clinetIp = HttpClientUtil.getClinetIp(request);
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("workNo",workNo);
		paramMap.put("payType",payType);
		paramMap.put("consumeMoney",consumeMoney);
		paramMap.put("clinetIp",clinetIp);
		try {
			
			EiInfo call = LocalServiceUtil.call("SSACPAY01", "workAppRecharge", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACPAY01.workAppRecharge失败！");
			}else {
				System.out.println("调用SSACPAY01.workAppRecharge成功！");
				if("0101".equals(payType)) {
					// 支付宝支付直接将完整的表单html输出到页面
					response.setContentType("text/html;charset=utf-8");
			        response.getWriter().write(resultData.getRespMsg());
			        response.getWriter().flush();
			        response.getWriter().close();
			        return null;
				}else if("0201".equals(payType)) {
					response.sendRedirect((String) resultData.getObj());
				}
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
	 * 职工APP支付
	 *
	 * @Title: workAppPay 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:28:39
	 */
	public ResultData workAppPay(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();

		/*String baseUrl = request.getRequestURL().toString();
		int index = baseUrl.indexOf("/workmeal");
		baseUrl = baseUrl.substring(0,index);
		System.out.println("baseUrl = " + baseUrl);*/

		String billNo = request.getParameter("billNo");
		String payType = request.getParameter("payType");
		String openId = request.getParameter("openId");
		//企业微信标识
		String appFlag = request.getParameter("appFlag");

		String clinetIp = HttpClientUtil.getClinetIp(request);
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("billNo",billNo);
		paramMap.put("payType",payType);
		paramMap.put("openId",openId);
		paramMap.put("clinetIp",clinetIp);
		System.out.println("----------------------");
		System.out.println(clinetIp);
		try {
			
			EiInfo call = LocalServiceUtil.call("SSACPAY01", "workAppPay", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACPAY01.workAppPay失败！");
			}else {
				System.out.println("调用SSACPAY01.workAppPay成功！");
				//企业微信不重定向
				if(!"workWX".equals(appFlag)){
					if(payType.startsWith("01")) {
						// 支付宝支付直接将完整的表单html输出到页面
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().write(resultData.getRespMsg());
						response.getWriter().flush();
						response.getWriter().close();
						return null;
					}else if(payType.startsWith("02")) {
						response.sendRedirect((String) resultData.getObj());
					}
				}else{
					System.out.println("old:"+resultData);
					String url = (String) resultData.getObj();
					//正式环境地址
					String newUrl=url.replace("http%3A%2F%2Fwxpaytest.yyhq365.cn%2FtestPay%2FpaySuccess.html","http://mzsrmyy.yyhq365.cn/www/html/meal/meal_order.html");
					//本地环境地址
//					String newUrl=url.replace("http%3A%2F%2Fwxpaytest.yyhq365.cn%2FtestPay%2FpaySuccess.html","http://lch520.nat200.top/www/html/meal/meal_order.html");
					resultData.setObj(newUrl);
				}
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

			EiInfo call = LocalServiceUtil.call("SSACPAY01", "posPayByScanOnline", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACPAY01.posPayByScanOnline失败！");
			}else {
				System.out.println("调用SSACPAY01.posPayByScanOnline成功！");
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
	 * 现金支付
	 *
	 * @Title: cashPosAll 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:28:52
	 */
	public ResultData cashPosAll(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String billNos = request.getParameter("billNos");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("billNos",billNos);
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
}
