package com.baosight.wilp.ss.sc.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.ss.bm.domain.WechatUser;
import com.baosight.wilp.ss.bm.utils.LocalServiceUtil;
import com.baosight.wilp.ss.bm.utils.ResultData;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import com.baosight.wilp.ss.bm.utils.WxConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 
 *移动端订单中转Service
 * 
 * @Title: AppWorkBaseInfoService.java
 * @ClassName: AppWorkBaseInfoService
 * @Package：com.baosight.wilp.ss.sc.service
 * @author: liutao
 * @date: 2021年9月9日 下午3:18:03
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
	public class AppWorkBaseInfoService {


    /**
     * 
     * 查询用户信息
     *
     * @Title: queryUser 
     * @param request
     * @param response
     * @return 
     * @return: ResultData 
     * @author: liutao
     * @date: 2021年9月9日 下午3:18:25
     */
	public ResultData queryUser(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		// 工号
		String userCode = request.getParameter("userCode");
		String userId = request.getParameter("userId");
		// 食堂编码
		String canteenNum = request.getParameter("canteenNum");
		String dataGroupCode = request.getParameter("dataGroupCode");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userCode",userCode);
		paramMap.put("userId",userId);
		paramMap.put("canteenNum",canteenNum);
		paramMap.put("dataGroupCode",dataGroupCode);
		try {
			//调用服务接口
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "queryUser", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryUser失败！");
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
	 * 查询用户优惠额度
	 *
	 * @Title: queryDetailByBillNoYh
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2022年5月9日 下午3:18:25
	 */
	public ResultData queryDetailByBillNoYh(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();

		// 工号
		String workNo = request.getParameter("workNo");
		//餐次
		String mealNum = request.getParameter("mealNum");
		String menuName = request.getParameter("menuName");
		String dataGroupCode = request.getParameter("dataGroupCode");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("workNo",workNo);
		paramMap.put("mealNum",mealNum);
		paramMap.put("menuName",menuName);
		paramMap.put("dataGroupCode",dataGroupCode);
		try {
			//调用服务接口
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "queryDetailByBillNoYh", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryUser失败！");
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
	 * 查询用户是否使用补贴
	 *
	 * @Title: querySubsidy
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2022年5月9日 下午3:18:25
	 */
	public ResultData querySubsidy(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();

		// 工号
		String workNo = request.getParameter("workNo");
		//餐次
		String mealNum = request.getParameter("mealNum");
		String needDate = request.getParameter("needDate");
		String dataGroupCode = request.getParameter("dataGroupCode");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("workNo",workNo);
		paramMap.put("mealNum",mealNum);
		paramMap.put("dataGroupCode",dataGroupCode);
		paramMap.put("needDate",needDate);
		try {
			//调用服务接口
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "querySubsidy", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryUser失败！");
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
	 * 查询用户点餐次数
	 *
	 * @Title: querySubsidy
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2022年5月9日 下午3:18:25
	 */
	public ResultData queryOrderTimes(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();

		// 工号
		String workNo = request.getParameter("workNo");
		//餐次
		String mealNum = request.getParameter("mealNum");
		String needDate = request.getParameter("needDate");
		String dataGroupCode = request.getParameter("dataGroupCode");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("workNo",workNo);
		paramMap.put("mealNum",mealNum);
		paramMap.put("dataGroupCode",dataGroupCode);
		paramMap.put("needDate",needDate);
		try {
			//调用服务接口
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "queryOrderTimes", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryUser失败！");
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
	 * 获取订单列表
	 *
	 * @Title: queryPaginationWorkBillInfo 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:18:37
	 */
	public ResultData queryPaginationWorkBillInfo(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String userCode = request.getParameter("userCode");
		String userId = request.getParameter("userId");
		String deptNum = request.getParameter("deptNum");
		String mealNum = request.getParameter("mealNum");
		String needDate = request.getParameter("needDate");
		String page = request.getParameter("page");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userCode",userCode);
		paramMap.put("deptNum",deptNum);
		paramMap.put("page",page);
		paramMap.put("userId",userId);
		paramMap.put("mealNum",mealNum);
		paramMap.put("needDate",needDate);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "queryPaginationWorkBillInfo", paramMap);
			String respMsg = StringUtil.toString(call.getAttr().get("respMsg"));
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryPaginationWorkBillInfo失败！");
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
	 * 获取科室订单列表
	 *
	 * @Title: queryPaginationWorkBillInfoDept
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: kwr
	 * @date: 2022年8月9日 下午3:18:37
	 */
	public ResultData queryPaginationWorkBillInfoDept(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();



		String deptNum = request.getParameter("deptNum");
		String mealNum = request.getParameter("mealNum");
		String needDate = request.getParameter("needDate");
		String page = request.getParameter("page");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();

		paramMap.put("deptNum",deptNum);
		paramMap.put("page",page);
		paramMap.put("needDate",needDate);
		paramMap.put("mealNum",mealNum);

		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "queryPaginationWorkBillInfoDept", paramMap);
			String respMsg = StringUtil.toString(call.getAttr().get("respMsg"));
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryPaginationWorkBillInfoDept失败！");
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
	 * 获取指定订单信息
	 *
	 * @Title: queryWorkBill 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:18:52
	 */
	public ResultData queryWorkBill(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String billNo = request.getParameter("billNo");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("billNo",billNo);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "queryWorkBill", paramMap);
			String respMsg = StringUtil.toString(call.getAttr().get("respMsg"));
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryWorkBill失败！");
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
	 * 获取指定订单明细
	 *
	 * @Title: queryWorkBillDetail
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: ker
	 * @date: 2022年8月9日
	 */
	public ResultData queryWorkBillDetail(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();

		String billNo = request.getParameter("billNo");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("billNo",billNo);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "queryWorkBillDetail", paramMap);
			String respMsg = StringUtil.toString(call.getAttr().get("respMsg"));
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryWorkBillDetail失败！");
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
	 * @date: 2021年9月9日 下午3:19:05
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
	 * @Title: queryFloor 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:19:22
	 */
	public ResultData queryFloor(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String building = request.getParameter("building");
		String dataGroupCode = request.getParameter("dataGroupCode");
		String canteenNum = request.getParameter("canteenNum");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("building",building);
		paramMap.put("dataGroupCode",dataGroupCode);
		paramMap.put("canteenNum",canteenNum);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "queryFloor", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryFloor失败！");
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

	public ResultData queryBuild(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();

//		String building = request.getParameter("building");
//		String dataGroupCode = request.getParameter("dataGroupCode");
//		String canteenNum = request.getParameter("canteenNum");
//
//		HashMap<String,Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("building",building);
//		paramMap.put("dataGroupCode",dataGroupCode);
//		paramMap.put("canteenNum",canteenNum);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "queryBuild",null);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryBuild失败！");
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
	 * 查询房间
	 *
	 * @Title: queryRoom 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:19:41
	 */
	public ResultData queryRoom(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
//		String deptNum = request.getParameter("deptNum");
		String building = request.getParameter("building");
		String floor = request.getParameter("floor");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("building",building);
		paramMap.put("floor",floor);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "queryRoom", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryRoom失败！");
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
	 * 检查该地址是否在表中
	 *
	 * @Title: querycheckRoomName
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:19:41
	 */
	public ResultData querycheckRoomName(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();

//		String deptNum = request.getParameter("deptNum");
		String roomName = request.getParameter("roomName");


		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roomName",roomName);

		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "querycheckRoomName", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.querycheckRoomName失败！");
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
	 * 查询科室
	 *
	 * @Title: queryDept 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:19:59
	 */
	public ResultData queryDept(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String workNo = request.getParameter("workNo");
//		String building = request.getParameter("building");
//		String floor = request.getParameter("floor");
//		String deptFlag = request.getParameter("deptFlag");
//		String canteenNum = request.getParameter("canteenNum");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("building",building);
//		paramMap.put("floor",floor);
//		paramMap.put("deptFlag",deptFlag);
//		paramMap.put("canteenNum",canteenNum);
		paramMap.put("workNo",workNo);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "queryDept", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryDept失败！");
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
	 * @date: 2021年9月9日 下午3:20:12
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
	 * @date: 2021年9月9日 下午3:20:27
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
	 * @date: 2021年9月9日 下午3:24:40
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
	 * @date: 2021年9月9日 下午3:24:54
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
	 * @date: 2021年9月9日 下午3:25:06
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
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "getEvalInfo", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("SSACDCXX01.getEvalInfo失败！");
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
	 * @date: 2021年9月9日 下午3:25:19
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
	 * 获取餐次
	 *
	 * @Title: getMealType 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:25:40
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
	 * 获取食堂列表
	 *
	 * @Title: getCanteen 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:26:02
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
     * @date: 2021年9月9日 下午3:26:55
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
	 * 职工app获取食堂日销售额
	 * @Title  getCanteenDailySales
	 * @author liu
	 * @date 2022-02-18 10:26
	 * @param request
	 * @param response
	 * @return com.baosight.wilp.ss.bm.utils.ResultData
	 */
	public ResultData getCanteenDailySales(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String needDate=request.getParameter("needDate");
		System.out.println(needDate);
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("needDate",needDate);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "getCanteenDailySales", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.getCanteenDailySales！");
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
	 * 职工app报表菜品销量排行统计报表
	 * @Title  getFoodSales
	 * @author liu
	 * @date 2022-02-18 13:59
	 * @param request
	 * @param response
	 * @return com.baosight.wilp.ss.bm.utils.ResultData
	 */
	public ResultData getFoodSales(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String needDate=request.getParameter("needDate");
		String canteenNum=request.getParameter("canteenNum");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("needDate",needDate);
		paramMap.put("canteenNum",canteenNum);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "getFoodSales", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.getFoodSales！");
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
	 * 职工app报表菜品评价报表
	 * @Title  getFoodSales
	 * @author liu
	 * @date 2022-02-18 13:59
	 * @param request
	 * @param response
	 * @return com.baosight.wilp.ss.bm.utils.ResultData
	 */
	public ResultData getFoodEvaluation(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String needDate=request.getParameter("needDate");
		String canteenNum=request.getParameter("canteenNum");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("needDate",needDate);
		paramMap.put("canteenNum",canteenNum);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "getFoodEvaluation", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.getFoodEvaluation！");
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
	 *  查询学生所属订单信息
	 *
	 * @Title: queryStuMeal
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2022年10月2日 下午1:55:12
	 */
	public ResultData queryStuMeal(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		//人员工号
		String recCreator = request.getParameter("recCreator");
		//订单号
		String billNo = request.getParameter("billNo");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("recCreator",recCreator);
		paramMap.put("billNo",billNo);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "queryStuMeal", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryStuMeal失败！");
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
	 *  更新订单状态
	 *
	 * @Title: queryStuType
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2022年10月2日 下午1:55:12
	 */
	public ResultData queryStuType(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		//人员工号
		String recCreator = request.getParameter("recCreator");
		//订单号
		String billNo = request.getParameter("billNo");
		String statusCode = request.getParameter("statusCode");
		//餐次
		String mealNum = request.getParameter("mealNum");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("recCreator",recCreator);
		paramMap.put("billNo",billNo);
		paramMap.put("statusCode",statusCode);
		paramMap.put("mealNum",mealNum);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "queryStuType", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryStuType失败！");
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
	 *  查询手持机登录人信息所属角色
	 *
	 * @Title: queryUserRole
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2022年10月2日 下午1:55:12
	 */
	public ResultData queryUserRole(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		//人员工号
		String recCreator = request.getParameter("recCreator");
		//订单号

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("recCreator",recCreator);

		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "queryUserRole", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryUserRole失败！");
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
	 * 企业微信待开发应用的登录
	 *
	 * @param
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @Title workLogin
	 * @author keke
	 * @date 2022-10-2 13:57
	 */
	public ResultData workLogin(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		WechatUser wuser = new WechatUser();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		String userId = wuser.getUserId();
		paramMap.put("userId", wuser.getUserId());
		System.out.println("userId"+paramMap);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSSC01", "wecahtLogin", paramMap);
			if (call.getStatus() < 0) {
				System.out.println("调用PSSC01.wecahtLogin失败！");
			} else {
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
		System.out.println("resultData"+resultData);
		return resultData;
	}

	/**
	 *
	 *  查询登录人是否存在不为科室名称为空的
	 *
	 * @Title: queryUserDeptName
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2023年06月15日 下午1:55:12
	 */
	public ResultData queryUserDeptName(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		//人员工号
		String recCreator = request.getParameter("recCreator");
		//订单号

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("recCreator",recCreator);

		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "queryUserDeptName", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryUserDeptName失败！");
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
	 *  查询登录人是否存在不为科室名称为空的
	 *
	 * @Title: queryUserDeptNameNew
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2023年06月15日 下午1:55:12
	 */
	public ResultData queryUserDeptNameNew(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		//人员工号
		String recCreator = request.getParameter("recCreator");
		//订单号

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("recCreator",recCreator);

		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "queryUserDeptNameNew", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryUserDeptNameNew失败！");
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
	 *  查询登录人是否存在不为科室名称为空的
	 *
	 * @Title: queryUserDeptNameNew
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2023年06月15日 下午1:55:12
	 */
	public ResultData queryMeauNum(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		//人员工号
		String menuName = request.getParameter("menuName");
		//订单号

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuName",menuName);

		try {
			EiInfo call = LocalServiceUtil.callNoTx("SSACDCXX01", "queryMeauNum", paramMap);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDCXX01.queryMeauNum失败！");
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
