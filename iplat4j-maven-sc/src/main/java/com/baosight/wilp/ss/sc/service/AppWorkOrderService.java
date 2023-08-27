package com.baosight.wilp.ss.sc.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.ss.ac.domain.SSACTscWorkOrderBill;
import com.baosight.wilp.ss.bm.utils.LocalServiceUtil;
import com.baosight.wilp.ss.bm.utils.ResultData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;


/**
 * 
 * 移动端订单中转Service
 * 
 * @Title: AppWorkOrderService.java
 * @ClassName: AppWorkOrderService
 * @Package：com.baosight.wilp.ss.sc.service
 * @author: liutao
 * @date: 2021年9月9日 下午3:27:11
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class AppWorkOrderService {
	

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
     * @date: 2021年9月9日 下午3:27:28
     */
	public ResultData cancelOrder(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String pBillNo = request.getParameter("billNo");
		String userCode = request.getParameter("userCode");
		String rejectReason = request.getParameter("rejectReason");
		String workNo = request.getParameter("openId");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pBillNo",pBillNo);
		paramMap.put("userCode",userCode);
		paramMap.put("workNo",workNo);
		paramMap.put("rejectReason",rejectReason);
		try {
			
			EiInfo call = LocalServiceUtil.call("SSACDDGL01", "cancelOrder", paramMap);
			//调用结果
			resultData = (ResultData) call.getAttr().get("result");
			if(call.getStatus() < 0){
				//Status < 0 时事务回滚
				System.out.println("调用SSACDDGL01.cancelOrder失败！");
			}else {
				resultData.setRespMsg("操作成功！");
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
	 * @Title: saveWork 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:27:40
	 */
	public ResultData saveWork(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String json=request.getParameter("json");
		String flag=request.getParameter("billType");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("json",json);
		paramMap.put("flag",flag);
		try {
			EiInfo call = LocalServiceUtil.call("SSACDDGL01", "saveWork", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				//Status < 0 时事务回滚
				System.out.println("调用SSACDDGL01.save失败！");
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
		try {
			//查询订单信息
			EiInfo callBillInfo = LocalServiceUtil.callNoTx("SSACDCXX01", "queryWorkBill", paramMap);
			List<SSACTscWorkOrderBill> result = (List<SSACTscWorkOrderBill>) callBillInfo.get("obj");
			//判断是否超时执行订单操作
			if(result.size() > 0){
				EiInfo call = LocalServiceUtil.callNewTx("SSACTimer01", "billHandle", result.get(0).toMap());
				resultData.setRespCode(call.getMsgKey());
				resultData.setRespMsg(call.getMsg());
				//调用结果
				if(call.getStatus() < 0){
					//Status < 0 时事务回滚
					System.out.println("调用SSACTimer01.billHandle失败！");
					resultData.setRespCode("210");
					resultData.setRespMsg("调用SSACTimer01.billHandle失败");
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
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:27:40
	 */
	public ResultData billPayStatusCheck(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String billNo=request.getParameter("billNo");

		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("billNo",billNo);
		try {
			//查询订单信息
			EiInfo callBillInfo = LocalServiceUtil.callNoTx("SSACDCXX01", "queryWorkBill", paramMap);
			List<SSACTscWorkOrderBill> result = (List<SSACTscWorkOrderBill>) callBillInfo.get("obj");
			//判断是否超时执行订单操作
			if(result.size() > 0){
				EiInfo call = LocalServiceUtil.callNewTx("SSACDDGL01", "billPayStatusCheck", result.get(0).toMap());
				resultData.setRespCode(call.getMsgKey());
				resultData.setRespMsg(call.getMsg());
				//调用结果
				if(call.getStatus() < 0){
					//Status < 0 时事务回滚
					resultData.setRespCode("210");
					resultData.setRespMsg("调用SSACDDGL01.billPayStatusCheck失败");
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

	/**
	 *
	 * 保存订单评价
	 *
	 * @Title: saveWorkEval
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:56:10
	 */
	public ResultData saveWorkEval(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String json=request.getParameter("json");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("json",json);
		try {
			EiInfo call = LocalServiceUtil.call("SSACDDGL01", "saveWorkEval", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDDGL01.saveWorkEval！");
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
	 * 检查该学生订餐是否属于该餐次的，不属于返回404属于返回200
	 *
	 * @Title: checkStuBill
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2021年9月9日 下午1:56:10
	 */
	public ResultData checkStuBill(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String billNo=request.getParameter("billNo");
		String mealNum=request.getParameter("mealNum");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("billNo",billNo);
		paramMap.put("mealNum",mealNum);
		try {
			EiInfo call = LocalServiceUtil.call("SSACDDGL01", "checkStuBill", paramMap);
//			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDDGL01.checkStuBill！");
				resultData.setRespCode("201");
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
	 * 查询登录人所属科室下单状态为待配送的数量。入参：科室名称，用餐时间,餐次
	 *
	 * @Title: queryBillNum
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2021年11月23日 下午1:56:10
	 */
	public ResultData queryBillNum(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String deptName=request.getParameter("deptName");
		String needDate=request.getParameter("needDate");
		String mealNum=request.getParameter("mealNum");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("deptName",deptName);
		paramMap.put("needDate",needDate);
		paramMap.put("mealNum",mealNum);
		try {
			EiInfo call = LocalServiceUtil.call("SSACDDGL01", "queryBillNum", paramMap);
//			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDDGL01.queryBillNum！");
				resultData.setRespCode("201");
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
	 * 查询限制订餐人数的是否启用（1启用，2不启用）
	 *
	 * @Title: queryStatus
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2021年11月23日 下午1:56:10
	 */
	public ResultData queryStatus(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();

		try {
			EiInfo call = LocalServiceUtil.call("SSACDDGL01", "queryStatus", null);
//			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDDGL01.queryStatus！");
				resultData.setRespCode("201");
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
	 * 查询当前科室所限制的订餐人数
	 *
	 * @Title: queryLimitNum
	 * @param request
	 * @param response
	 * @return
	 * @return: ResultData
	 * @author: keke
	 * @date: 2021年11月23日 下午1:56:10
	 */
	public ResultData queryLimitNum(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		String deptName=request.getParameter("deptName");
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("deptName",deptName);
		try {
			EiInfo call = LocalServiceUtil.call("SSACDDGL01", "queryLimitNum", paramMap);
//			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用SSACDDGL01.queryLimitNum！");
				resultData.setRespCode("201");
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
