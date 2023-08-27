package com.baosight.wilp.ps.sc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.ss.bm.utils.LocalServiceUtil;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import com.baosight.wilp.ss.bm.utils.ResultData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * 
 * 用户信息中转Service
 * 
 * @Title: AppUserInfoService.java
 * @ClassName: AppUserInfoService
 * @Package：com.baosight.wilp.ps.sc.service
 * @author: liutao
 * @date: 2021年9月9日 下午2:03:34
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class AppUserInfoService {

	
    /**
     * 
     * 修改联系电话
     *
     * @Title: changePhoneNo 
     * @param request
     * @param response
     * @return 
     * @return: ResultData 
     * @author: liutao
     * @date: 2021年9月9日 下午2:03:50
     */
	public ResultData changePhoneNo(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String patientNum = request.getParameter("patientNum");
		String newTel = request.getParameter("newTel");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("patientNum",patientNum);
		paramMap.put("newTel",newTel);
		try {
			//调用服务接口
			EiInfo call = LocalServiceUtil.call("PSPCUser", "changePhoneNo", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCUser.changePhoneNo失败！");
				resultData.setRespCode("201");
				resultData.setRespMsg(call.getMsg());
				resultData.setSuccess(false);
			}else {
				resultData.setObj(call.getAttr().get("obj"));
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
	
	

	/**
	 * 
	 * 根据员工工号获取员工信息
	 *
	 * @Title: getWorkInfoByWorkNo 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午2:04:00
	 */
	public ResultData getWorkInfoByWorkNo(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String workNo = request.getParameter("workNo");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("workNo",workNo);
		try {
			//调用服务接口
			EiInfo inInfo = new EiInfo();
			inInfo.set(EiConstant.serviceId, "S_AC_FW_02");
			inInfo.set("workNo", workNo); 
			EiInfo outInfo = XServiceManager.call(inInfo);
			if(outInfo.getStatus() < 0){
				resultData.setRespCode("201");
				resultData.setRespMsg(outInfo.getMsg());
				resultData.setSuccess(false);
			}else {
				Map<String,String> result= (Map<String, String>) outInfo.get("result");
				resultData.setObj(result);
				resultData.setRespCode("200");
				resultData.setRespMsg("操作成功");
			}
//			
//			EiInfo call = LocalServiceUtil.callNoTx("PSPCUser", "getWorkInfoByWorkNo", paramMap);
//			String respMsg = StringUtil.toString(call.getAttr().get("respMsg"));
//			//调用结果
//			if(call.getStatus() < 0){
//				System.out.println("调用PSPCUser.getWeChatAppId失败！");
//				resultData.setRespCode("201");
//				resultData.setRespMsg(respMsg);
//				resultData.setSuccess(false);
//			}else {
//				resultData.setObj(call.getAttr().get("obj"));
//				resultData.setRespCode("200");
//				resultData.setRespMsg("操作成功");
//			}
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
	 * 根据住院号模糊查询所有符合的病员
	 *
	 * @Title: queryPatientInfo 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午2:04:13
	 */
	public ResultData queryPatientInfo(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String patientCode = request.getParameter("patientCode");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("patientCode",patientCode);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCUser", "queryPatientInfo", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCUser.queryPatientInfo失败！");
				resultData.setRespCode("201");
				resultData.setRespMsg("操作失败");
				resultData.setSuccess(false);
			}else {
				resultData.setObj(call.getAttr().get("obj"));
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
	 * 
	 * 根据地点编码获取病员信息
	 *
	 * @Title: queryPatientInfoByAddNum 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午2:04:27
	 */
	public ResultData queryPatientInfoByAddNum(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String addNum = request.getParameter("addNum");
		String patientCode = request.getParameter("patientCode");
		String canteenNum = request.getParameter("canteenNum");
		String userInfoFlag = request.getParameter("userInfoFlag");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("addNum",addNum);
		paramMap.put("patientCode",patientCode);
		paramMap.put("canteenNum",canteenNum);
		paramMap.put("registerType",userInfoFlag);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCUser", "queryPatientInfoByAddNum", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCUser.queryPatientInfoByAddNum失败！");
				resultData.setRespCode("201");
				resultData.setRespMsg("操作失败");
				resultData.setSuccess(false);
			}else {
				resultData.setParam(call.getAttr().get("type"));
				resultData.setObj(call.getAttr().get("obj"));
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
	 * 
	 * 根据住院号模糊查询所有符合的病员
	 *
	 * @Title: queryPatientInfoByHisNo 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午2:04:39
	 */
	public ResultData queryPatientInfoByHisNo(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String patientCode = request.getParameter("patientCode");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("patientCode",patientCode);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCUser", "queryPatientInfoByHisNo", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCUser.queryPatientInfoByHisNo失败！");
				resultData.setRespCode("201");
				resultData.setRespMsg("操作失败");
				resultData.setSuccess(false);
			}else {
				resultData.setObj(call.getAttr().get("obj"));
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
	 * 
	 * 扫码获取病员信息
	 *
	 * @Title: queryPatientInfoByID 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午2:04:50
	 */
	public ResultData queryPatientInfoByID(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String cardBaseCode=request.getParameter("cardBaseCode");
		String patientNum=request.getParameter("patientCode");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cardBaseCode",cardBaseCode);
		paramMap.put("patientNum",patientNum);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCUser", "queryPatientInfoByID", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCUser.queryPatientInfoByID失败！");
				resultData.setRespCode("201");
				resultData.setRespMsg("操作失败");
				resultData.setSuccess(false);
			}else {
				resultData.setObj(call.getAttr().get("obj"));
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
	 *
	 * 用户登录第一次绑卡后，获取当前用户的openid保存到我们的数据库中
	 *
	 * @Title: saveOpenId
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2022年9月1日 下午2:04:50
	 */
	public ResultData saveOpenId(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String openId=request.getParameter("openId");
		String name=request.getParameter("name");

		String phone=request.getParameter("phone");
		String cardNum=request.getParameter("cardNum");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("openId",openId);
		paramMap.put("name",name);
		paramMap.put("phone",phone);
		paramMap.put("cardNum",cardNum);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCUser", "saveOpenId", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCUser.saveOpenId失败！");
				resultData.setRespCode("201");
				resultData.setRespMsg("操作失败");
				resultData.setSuccess(false);
			}else {
				resultData.setObj(call.getAttr().get("obj"));
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
	 *
	 * 获取当前用户的openid
	 *
	 * @Title: queryOpenId
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2022年9月1日 下午2:04:50
	 */
	public ResultData queryOpenId(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String openId=request.getParameter("openId");


		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("openId",openId);

		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCUser", "queryOpenId", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCUser.queryOpenId失败！");
				resultData.setRespCode("201");
				resultData.setRespMsg("操作失败");
				resultData.setSuccess(false);
			}else {
				resultData.setObj(call.getAttr().get("obj"));
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



}
