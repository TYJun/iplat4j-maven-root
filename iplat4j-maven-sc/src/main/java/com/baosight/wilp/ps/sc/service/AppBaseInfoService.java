package com.baosight.wilp.ps.sc.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Holder;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.ss.bm.utils.LocalServiceUtil;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import com.baosight.wilp.ss.bm.utils.ResultData;


/**
 * 
 *   * 职工登录基础信息
 * 
 * @Title: AppBaseInfoService.java
 * @ClassName: AppBaseInfoService
 * @Package：com.baosight.wilp.ps.sc.service
 * @author: liutao
 * @date: 2021年9月9日 下午1:49:41
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class AppBaseInfoService {


	/**
	 * 
	 * 获取生效的公告信息
	 *
	 * @Title: getEffectiveNotice 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:50:08
	 */
	public ResultData getEffectiveNotice(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String typeCode = request.getParameter("typeCode");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("typeCode",typeCode);
		try {
			//调用服务接口
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "getEffectiveNotice", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.getEffectiveNotice失败！");
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
	 * 获取食堂对应的微信公众号
	 *
	 * @Title: getWeChatAppId 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:50:36
	 */
	public ResultData getWeChatAppId(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String canteenNum = request.getParameter("canteenNum");
		String dataGroupCode = request.getParameter("dataGroupCode");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("canteenNum",canteenNum);
		paramMap.put("dataGroupCode",dataGroupCode);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "getWeChatAppId", paramMap);
			String respMsg = StringUtil.toString(call.getAttr().get("respMsg"));
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.getWeChatAppId失败！");
				resultData.setRespCode("201");
				resultData.setRespMsg(respMsg);
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
	 * 获取微信支付凭证
	 *
	 * @Title: getWeChatOpenId 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:50:51
	 */
	public ResultData getWeChatOpenId(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String canteenNum = request.getParameter("canteenNum");
		String dataGroupCode = request.getParameter("dataGroupCode");
		//授权码
		String code = request.getParameter("code");
		//食堂名称
		String canteenName = request.getParameter("canteenName");
		String flag = request.getParameter("flag");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("canteenNum",canteenNum);
		paramMap.put("dataGroupCode",dataGroupCode);
		paramMap.put("code",code);
		paramMap.put("canteenName",canteenName);
		paramMap.put("flag",flag);
		try {
			EiInfo call = LocalServiceUtil.call("PSPCDCXX01", "getWeChatOpenId", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.getWeChatOpenId失败！");
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
		System.out.println(resultData);
		return resultData;
	}
	
	/**
	 * 
	 * 获取订单详情
	 *
	 * @Title: getBillDetail 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:51:04
	 */
	public ResultData getBillDetail(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String billNo = request.getParameter("billNo");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("billNo",billNo);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "getBillDetail", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.getBillDetail失败！");
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
		System.out.println(resultData);
		return resultData;
	}
	

	/**
	 * 
	 * 获取订单信息
	 *
	 * @Title: getBillInfo 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:51:29
	 */
	public ResultData getBillInfo(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String userCode = request.getParameter("userCode");
		String userId = request.getParameter("userId");
		String page = request.getParameter("page");
		String patientCode = request.getParameter("patientCode");
		String deptName = request.getParameter("deptName");
		String mealNum = request.getParameter("mealNum");
		String needDate = request.getParameter("needDate");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String deptNum = request.getParameter("deptNum");
		String rows = request.getParameter("rows");
		String dataGroupCode = request.getParameter("dataGroupCode");
		String pboCode = request.getParameter("pboCode");
		String archiveFlag = request.getParameter("archiveFlag");
		String statusCode = request.getParameter("statusCode");
		String userTelNumber = request.getParameter("userTelNumber");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userCode",userCode);
		paramMap.put("userId",userId);
		paramMap.put("page",page);
		paramMap.put("patientCode",patientCode);
		paramMap.put("deptName",deptName);
		paramMap.put("mealNum",mealNum);
		paramMap.put("needDate",needDate);
		paramMap.put("startDate",startDate);
		paramMap.put("endDate",endDate);
		paramMap.put("deptNum",deptNum);
		paramMap.put("rows",rows);
		paramMap.put("dataGroupCode",dataGroupCode);
		paramMap.put("pboCode",pboCode);
		paramMap.put("archiveFlag",archiveFlag);
		paramMap.put("statusCode",statusCode);
		paramMap.put("userTelNumber",userTelNumber);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "getBillInfo", paramMap);
			String respMsg = StringUtil.toString(call.getAttr().get("respMsg"));
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.getBillInfo失败！");
				resultData.setRespCode("201");
				resultData.setRespMsg(respMsg);
				resultData.setSuccess(false);
			}else {
				resultData.setObj(call.getAttr().get("obj"));
				resultData.setRespCode("200");
				resultData.setRespMsg(respMsg);
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
	 * 病患订餐根据单据创建人查询科室列表
	 *
	 * @Title: getMealBillDeptList 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:52:55
	 */
	public ResultData getMealBillDeptList(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String userCode = request.getParameter("userCode");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userCode",userCode);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "getMealBillDeptList", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.getMealBillDeptList失败！");
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
	 * 查询层号
	 *
	 * @Title: queryQrFloor 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:53:11
	 */
	public ResultData queryQrFloor(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String building = request.getParameter("building");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("building",building);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "queryQrFloor", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.queryQrFloor失败！");
				resultData.setRespCode("201");
				resultData.setRespMsg("查询失败");
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
	 * 查询地点信息
	 *
	 * @Title: queryQrAddr 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:53:25
	 */
	public ResultData queryQrAddr(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String addNum = request.getParameter("addNum");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("addNum",addNum);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "queryQrAddr", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.queryQrAddr失败！");
				resultData.setRespCode("201");
				resultData.setRespMsg("查询失败");
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
	 * 查询科室
	 *
	 * @Title: queryQrDept 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:54:15
	 */
	public ResultData queryQrDept(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String building = request.getParameter("building");
		String floor = request.getParameter("floor");
		String areaCode = request.getParameter("areaCode");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("building",building);
		paramMap.put("floor",floor);
		paramMap.put("areaCode",areaCode);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "queryQrDept", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.queryQrDept失败！");
				resultData.setRespCode("201");
				resultData.setRespMsg("查询失败");
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
	 * 查询层号
	 *
	 * @Title: queryFloor 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:54:46
	 */
	public ResultData queryFloor(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String floorNo = request.getParameter("floorNo");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("floorNo",floorNo);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "queryFloor", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.queryFloor失败！");
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
	 * 查询病区
	 *
	 * @Title: queryDeptNum 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:54:59
	 */
	public ResultData queryDeptNum(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String floor = request.getParameter("floorNo");
		String building = request.getParameter("layerNo");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("floor",floor);
		paramMap.put("building",building);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "queryDeptNum", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.queryDeptNum失败！");
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
	 *  查询食堂支付类型
	 *
	 * @Title: queryMealPayType 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:55:12
	 */
	public ResultData queryMealPayType(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		//食堂类型
		String canteenType = request.getParameter("canteenType");
		//入口类型
		String orally = request.getParameter("orally");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("canteenType",canteenType);
		paramMap.put("orally",orally);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "queryMealPayType", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.queryMealPayType失败！");
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
	 * 查询菜品详情
	 *
	 * @Title: getMenuDetail 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:55:24
	 */
	public ResultData getMenuDetail(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		//菜品编码
		String menuNum = request.getParameter("menuNum");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuNum",menuNum);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "getMenuDetail", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.getMenuDetail失败！");
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
	 * 
	 * 查询食堂菜品信息 , 无分页, 图片格式为地址
	 *
	 * @Title: queryMenuInfo 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:55:37
	 */
	public ResultData queryMenuInfo(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		//餐品分类
		String mealType = request.getParameter("mealType");
		//今日、明日（D:今日，Y:明日，yyyy-MM-dd:指定日期）
		String dayFlag = request.getParameter("dayFlag");
		//早、中、晚
		String mealNum = request.getParameter("mealNum");
		//食堂
		String canteenNum = request.getParameter("canteenNum");
		//入口类型
		String orally = request.getParameter("orally");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mealType",mealType);
		paramMap.put("dayFlag",dayFlag);
		paramMap.put("mealNum",mealNum);
		paramMap.put("canteenNum",canteenNum);
		paramMap.put("orally",orally);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "queryMenuInfo", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.queryMenuInfo失败！");
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
		System.out.println(resultData);
		return resultData;
	}
	

	/**
	 * 
	 * 加载菜品数据
	 *
	 * @Title: queryPaginationMenu 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:55:54
	 */
	public ResultData queryPaginationMenu(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String dayFlag=request.getParameter("dayFlag");
		String mealNum=request.getParameter("mealNum");
		String canteenNum=request.getParameter("canteenNum");
		String mealType=request.getParameter("mealType");
		String page=request.getParameter("page");
		String deviceType=request.getParameter("deviceType");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dayFlag",dayFlag);
		paramMap.put("mealNum",mealNum);
		paramMap.put("canteenNum",canteenNum);
		paramMap.put("mealType",mealType);
		paramMap.put("page",page);
		paramMap.put("deviceType",deviceType);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "queryPaginationMenu", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.queryPaginationMenu失败！");
				resultData.setRespCode("201");
				resultData.setRespMsg("操作失败");
				resultData.setSuccess(false);
			}else {
				resultData.setObj(call.getAttr().get("result"));
				resultData.setTotal(Integer.parseInt(StringUtil.toString(call.getAttr().get("total"))));
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
	 * 查询菜品评价
	 *
	 * @Title: getEvalInfo 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:56:10
	 */
	public ResultData getEvalInfo(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String curPage=request.getParameter("curPage");
		String rows = request.getParameter("rows");
		String menuNum = request.getParameter("menuNum");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("curPage",curPage);
		paramMap.put("rows",rows);
		paramMap.put("menuNum",menuNum);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "getEvalInfo", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.getEvalInfo失败！");
				resultData.setRespCode("201");
				resultData.setRespMsg("操作失败");
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
	 *
	 * 查询食堂评价类型
	 *
	 * @Title: getEvalTypeByCanteenNum
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:56:10
	 */
	public ResultData getEvalTypeByCanteenNum(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String canteenNum=request.getParameter("canteenNum");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("canteenNum",canteenNum);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "getEvalTypeByCanteenNum", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.getEvalTypeByCanteenNum！");
				resultData.setRespCode("201");
				resultData.setRespMsg("操作失败");
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
	 * 
	 * 获取可订餐日期
	 *
	 * @Title: getDateList 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:56:51
	 */
	public ResultData getDateList(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String dataGroupCode=request.getParameter("dataGroupCode");
		// 食堂类型（zgst/职工食堂，bhst/病患食堂）
		String canteenType = request.getParameter("canteenTypeNum");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dataGroupCode",dataGroupCode);
		paramMap.put("canteenType",canteenType);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "getDateList", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.getDateList失败！");
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
	 *  获取餐次
	 *
	 * @Title: getMealType 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:57:24
	 */
	public ResultData getMealType(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String canteenNum=request.getParameter("canteenNum");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("canteenNum",canteenNum);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "getMealType", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.getMealType失败！");
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
	 *  获取食堂列表
	 *
	 * @Title: getCanteen 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:57:39
	 */
    public ResultData getCanteen(HttpServletRequest request, HttpServletResponse response){
        ResultData resultData = new ResultData();
        String canteenTypeNum=request.getParameter("canteenTypeNum");
        String dataGroupCode=request.getParameter("dataGroupCode");
        
        HashMap<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("canteenTypeNum",canteenTypeNum);
        paramMap.put("dataGroupCode",dataGroupCode);
        try {
        	EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "getCanteen", paramMap);
        	//调用结果
        	if(call.getStatus() < 0){
        		System.out.println("调用PSPCDCXX01.getCanteen失败！");
        		resultData.setRespCode("201");
        		resultData.setRespMsg("操作失败");
        		resultData.setSuccess(false);
        	}else {
				System.out.println("@@@@@@@@@");
				System.out.println(call.getAttr().get("obj"));
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
     * 获取食堂列表
     *
     * @Title: getCanteenList 
     * @param request
     * @param response
     * @return 
     * @return: ResultData 
     * @author: liutao
     * @date: 2021年9月9日 下午1:57:55
     */
    public ResultData getCanteenList(HttpServletRequest request, HttpServletResponse response){
    	ResultData resultData = new ResultData();
    	String wardCode=request.getParameter("wardCode");
    	System.out.println(wardCode);
    	HashMap<String,Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("wardCode",wardCode);
    	try {
    		EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "getCanteenList", paramMap);
    		//调用结果
    		if(call.getStatus() < 0){
    			System.out.println("调用PSPCDCXX01.getCanteenList失败！");
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
    	System.out.println(resultData);
    	return resultData;
    }

	/**
	 *
	 * 查询该角色是否在次餐次点过餐
	 *
	 * @Title: queryBilld
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2021年9月9日 下午1:54:46
	 */
	public ResultData queryBilld(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();

		String userName = request.getParameter("userName");
		String needDate = request.getParameter("needDate");
		String bedNo = request.getParameter("bedNo");
		String roleNum = request.getParameter("roleNum");
		String mealNum = request.getParameter("mealNum");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName",userName);
		paramMap.put("needDate",needDate);
		paramMap.put("bedNo",bedNo);
		paramMap.put("roleNum",roleNum);
		paramMap.put("mealNum",mealNum);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "queryBilld", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.queryBilld失败！");
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
	 * 查询该科室所属地址
	 *
	 * @Title: queryBuildAndFloor
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2021年9月9日 下午1:54:46
	 */
	public ResultData queryBuildAndFloor(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();

		String deptNum = request.getParameter("deptNum");


		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("deptNum",deptNum);

		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "queryBuildAndFloor", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.queryBuildAndFloor失败！");
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

	public ResultData getLimitNum(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String canteenNum = request.getParameter("canteenCode");
		String dataGroup = request.getParameter("dataGroupCode");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("canteenNum",canteenNum);
		paramMap.put("dataGroup",dataGroup);

		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "getLimitNum", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.getLimitNum！");
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

	public ResultData getShipFee(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String canteenNum = request.getParameter("canteenCode");
		String dataGroup = request.getParameter("dataGroupCode");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("canteenNum",canteenNum);
		paramMap.put("dataGroup",dataGroup);

		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "getShipFee", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.getShipFee！");
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
	 * 查找该病患是否存在
	 *
	 * @Title: checkCardInfo
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2022年8月17日 下午1:54:46
	 */
	public ResultData checkCardInfo(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String cardNum = request.getParameter("cardNum");
		String name = request.getParameter("name");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cardNum",cardNum);
		paramMap.put("name",name);

		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "checkCardInfo", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.checkCardInfo！");
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
	 * 查找该病患是否为住院病人、获取床位号、病区
	 *
	 * @Title: checkCardInfo
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2022年8月17日 下午1:54:46
	 */
	public ResultData inPatientInfoQuery(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String fileNum = request.getParameter("fileNum");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fileNum",fileNum);


		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCDCXX01", "inPatientInfoQuery", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCDCXX01.inPatientInfoQuery！");
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
