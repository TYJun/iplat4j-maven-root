package com.baosight.wilp.ss.ac.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ei.json.EiInfo2Json;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOrderBillDetail;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOrderBillPatient;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOrderMenu;
import com.baosight.wilp.ss.ac.domain.*;
import com.baosight.wilp.ss.bm.domain.*;
import com.baosight.wilp.ss.bm.utils.*;
import com.bonawise.smp.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.runtime.directive.Foreach;

import java.util.*;

/**
 * 
 * ServiceSSACDCXX01 职工订餐信息service
 * 
 * @Title: ServiceSSACDCXX01.java
 * @ClassName: ServiceSSACDCXX01
 * @Package：com.baosight.wilp.ss.ac.service
 * @author: liutao
 * @date: 2021年9月9日 下午2:57:46
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSACDCXX01 extends ServiceBase {

    /**
     * queryUser查询用户帐套
     * *入参：工号userCode，用户id userId，食堂编码canteenNum
     * *出餐：EiInfo(obj:用户信息)
     *
     * @Title: queryUser 
     * @param inInfo
     * @return 
     * @return: EiInfo 
     * @author: liutao
     * @date: 2021年9月9日 下午2:58:00
     */
	public EiInfo queryUserData(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 用户工号
		String userCode = StringUtil.toString(attr.get("userCode"));
		String userId = StringUtil.toString(attr.get("userId"));
		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		//院区
		String dataGroupCode = StringUtil.toString(attr.get("dataGroupCode"));
		List<SSACTscOrderWorkerInfo> result = null;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if(StringUtils.isBlank(canteenNum)){
			inInfo.setStatus(-1);
			inInfo.setMsg("食堂编码参数错误，请检查");
		}else if(StringUtils.isBlank(userCode)&&StringUtils.isBlank(userId)){
			inInfo.setStatus(-1);
			inInfo.setMsg("用户参数错误，请检查");
		}else{
			Map<String,String> map = new HashMap<String,String>();
			map.put("canteenNum", canteenNum);
			//扫码支付获取上一单地址时是根据用户id(支付宝userid或者微信openid)获取的
			if(StringUtils.isNotEmpty(userCode)){
				map.put("userCode", userCode);
			}
			if(StringUtils.isNotEmpty(userId)){
				map.put("openId", userId);
			}
			result = dao.query("SSACTscOrderWorkerInfo.query",map);
			if(result.size()<1){
				//没有上一单记录,查询职工科室地点
				paramMap.put("workNo", userCode);
				//调用服务接口通过员工ID或者员工工号查询所属科室详情
				EiInfo callDept = XServiceUtil.call("S_AC_FW_07", paramMap);
				if(callDept.getStatus() < 0){
					//没查到
					inInfo.setStatus(-1);
					inInfo.set("obj", null);
					inInfo.setMsg("未查询到科室信息");
				}else {
					Map<String,String> dept = (Map<String, String>) callDept.get("result");
					String deptNum = StringUtil.toString(dept.get("deptNum"));
					//通过科室查询地点信息
					paramMap.clear();
					paramMap.put("deptNum", deptNum);
					//调用服务接口
					EiInfo callSpot = XServiceUtil.call("S_AC_FW_09", paramMap);
					if(callSpot.getStatus() < 0){
						//没查到
						inInfo.setStatus(-1);
						inInfo.set("obj", null);
						inInfo.setMsg("未查询到地点信息");
					}else {
						List<Map<String,String>> spot = (ArrayList) callSpot.get("result");
						if(spot.size() > 0 ) {
							SSACTscOrderWorkerInfo work = new SSACTscOrderWorkerInfo();
							//添加地点信息
							work.setBuilding(spot.get(0).get("spotNum"));
							work.setBuildingName(spot.get(0).get("building"));
						}
					}
				}
				
			}else {
				inInfo.setStatus(1);
				inInfo.set("obj", result);
			}
		}
		return inInfo;
	}

    /**
     * queryUser查询用户信息
     * *入参：工号userCode，用户id userId，食堂编码canteenNum
     * *出餐：EiInfo(obj:用户信息)
     *
     * @Title: queryUser
     * @param inInfo
     * @return
     * @return: EiInfo
     * @author: liutao
     * @date: 2021年9月9日 下午2:58:00
     */
	public EiInfo queryUser(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 用户工号
		String userCode = StringUtil.toString(attr.get("userCode"));
		String userId = StringUtil.toString(attr.get("userId"));
		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		//院区
		String dataGroupCode = StringUtil.toString(attr.get("dataGroupCode"));
		List<SSACTscOrderWorkerInfo> result = null;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if(StringUtils.isBlank(canteenNum)){
			inInfo.setStatus(-1);
			inInfo.setMsg("食堂编码参数错误，请检查");
		}else if(StringUtils.isBlank(userCode)&&StringUtils.isBlank(userId)){
			inInfo.setStatus(-1);
			inInfo.setMsg("用户参数错误，请检查");
		}else{
			Map<String,String> map = new HashMap<String,String>();
			map.put("canteenNum", canteenNum);
			//扫码支付获取上一单地址时是根据用户id(支付宝userid或者微信openid)获取的
			if(StringUtils.isNotEmpty(userCode)){
				map.put("userCode", userCode);
			}
			if(StringUtils.isNotEmpty(userId)){
				map.put("openId", userId);
			}
			result = dao.query("SSACTscOrderWorkerInfo.query",map);
			if(result.size()<1){
				//没有上一单记录,查询职工科室地点
				paramMap.put("workNo", userCode);
				//调用服务接口通过员工ID或者员工工号查询所属科室详情
				EiInfo callDept = XServiceUtil.call("S_AC_FW_07", paramMap);
				if(callDept.getStatus() < 0){
					//没查到
					inInfo.setStatus(-1);
					inInfo.set("obj", null);
					inInfo.setMsg("未查询到科室信息");
				}else {
					Map<String,String> dept = (Map<String, String>) callDept.get("result");
					String deptNum = StringUtil.toString(dept.get("deptNum"));
					//通过科室查询地点信息
					paramMap.clear();
					paramMap.put("deptNum", deptNum);
					//调用服务接口
					EiInfo callSpot = XServiceUtil.call("S_AC_FW_09", paramMap);
					if(callSpot.getStatus() < 0){
						//没查到
						inInfo.setStatus(-1);
						inInfo.set("obj", null);
						inInfo.setMsg("未查询到地点信息");
					}else {
						List<Map<String,String>> spot = (ArrayList) callSpot.get("result");
						if(spot.size() > 0 ) {
							SSACTscOrderWorkerInfo work = new SSACTscOrderWorkerInfo();
							//添加地点信息
							work.setBuilding(spot.get(0).get("spotNum"));
							work.setBuildingName(spot.get(0).get("building"));
							inInfo.setStatus(1);
							inInfo.set("obj", work);
						}
					}
				}

			}else {
				inInfo.setStatus(1);
				inInfo.set("obj", result);
			}
		}
		return inInfo;
	}


	/**
	 * queryUser查询用户优惠额度
	 * *入参：工号workNo，餐次：mealNum
	 * *出餐：EiInfo(obj:用户信息)
	 *
	 * @Title: queryDetailByBillNoYh
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 * @author: kwr
	 * @date:
	 */
	public EiInfo queryDetailByBillNoYh(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 用户工号
		String workNo = StringUtil.toString(attr.get("workNo"));
		String mealNum = StringUtil.toString(attr.get("mealNum"));
		String menuName = StringUtil.toString(attr.get("menuName"));
		List<SSACTscOrderWorkerInfo> result = null;
//		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		Map<String,String> map = new HashMap<String,String>();
		map.put("workNo", workNo);
		map.put("mealNum", mealNum);
		map.put("menuName", menuName);
		//获取优惠额度
		result = dao.query("SSACTscWorkOrderBillDetail.queryDetailByBillNoYh",map);
		inInfo.setStatus(1);
		inInfo.set("obj", result);
		return inInfo;
	}

	/**
	 * queryUser查询用户是否使用补贴
	 * *入参：工号workNo，餐次：mealNum
	 * *出餐：EiInfo(obj:用户信息)
	 *
	 * @Title: queryDetailByBillNoYh
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 * @author: kwr
	 * @date:
	 */
	public EiInfo querySubsidy(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 用户工号
		String workNo = StringUtil.toString(attr.get("workNo"));
		String mealNum = StringUtil.toString(attr.get("mealNum"));
		String needDate = StringUtil.toString(attr.get("needDate"));
		List<SSACMealSubsidyCount> result = null;
//		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		Map<String,String> map = new HashMap<String,String>();
		map.put("workNo", workNo);
		map.put("mealNum", mealNum);
		map.put("needDate", needDate);
		//获取优惠额度
		result = dao.query("SSACTscWorkOrderBillDetail.querySubsidy",map);
		if(result.isEmpty()){
			inInfo.setStatus(0);
			inInfo.set("obj", "0");
			return inInfo;
		}
		inInfo.setStatus(1);
		inInfo.set("obj", "1");
		return inInfo;
	}

	/**
	 * queryUser查询用户是否存在订单
	 * *入参：工号workNo，餐次：mealNum
	 * *出餐：EiInfo(obj:用户信息)
	 *
	 * @Title: queryDetailByBillNoYh
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 * @author: kwr
	 * @date:
	 */
	public EiInfo queryOrderTimes(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 用户工号
		String workNo = StringUtil.toString(attr.get("workNo"));
		String mealNum = StringUtil.toString(attr.get("mealNum"));
		String needDate = StringUtil.toString(attr.get("needDate"));
		List<SSACMealSubsidyCount> result = null;
//		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		Map<String,String> map = new HashMap<String,String>();
		map.put("workNo", workNo);
		map.put("mealNum", mealNum);
		map.put("needDate", needDate);
		//获取优惠额度
		result = dao.query("SSACTscWorkOrderBillDetail.queryOrderTimes",map);
		if(result.isEmpty()){
			inInfo.setStatus(0);
			inInfo.set("obj", "0");
			return inInfo;
		}
		inInfo.setStatus(1);
		inInfo.set("obj", "1");
		return inInfo;
	}


	/**
	 * 
	 *  queryPaginationSelfBillInfo获取自取餐订单列表
	 *
	 * @Title: queryPaginationWorkBillInfo 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午2:58:35
	 */
	public EiInfo queryPaginationWorkBillInfo(EiInfo inInfo){
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		ResultData j = new ResultData();
		//接收参数
		String userCode = StringUtil.toString(attr.get("userCode"));
		String deptNum = StringUtil.toString(attr.get("deptNum"));
		String userId = StringUtil.toString(attr.get("userId"));
		String billNo = StringUtil.toString(attr.get("billNo"));
		String sPage = StringUtil.toString(attr.get("page"));
		String mealNum = StringUtil.toString(attr.get("mealNum"));
		String needDate = StringUtil.toString(attr.get("needDate"));
		HashMap<String,Object> mapParam = new HashMap<String, Object>();
		String temp = "";
		if(StringUtil.isNotEmpty(billNo)){
			mapParam.put("billNo", billNo);
		}
		/**当前页数 */
	     int page = 1;
		/** 每页条数 */
		int rows = 10;
		int startRow = 0;
		//判断参数是否为空
		if(StringUtils.isBlank(sPage)) {
			j.setSuccess(false);
			j.setRespMsg("参数page为空，请检查!");
			inInfo.setStatus(j.isSuccess() ? 1 : -1);
			inInfo.set("result", j);
			return inInfo;
		}else{
			page = Integer.parseInt(sPage);
		}
		if(StringUtils.isBlank(userCode)&&StringUtils.isBlank(userId)) {
			j.setSuccess(false);
			j.setRespMsg("参数userCode或者userId至少提供一个，请检查!");
			inInfo.setStatus(j.isSuccess() ? 1 : -1);
			inInfo.set("result", j);
			return inInfo;
		}

		startRow = (page-1)*rows;
		mapParam.put("deptNum", deptNum);
		mapParam.put("openId", userCode);
		mapParam.put("startRow", startRow);
		mapParam.put("rows", rows);
		mapParam.put("mealNum", mealNum);
		mapParam.put("needDate", needDate);
		//查询职工订单表
		List<SSACTscWorkOrderBill> billList = dao.query("SSACTscWorkOrderBill.queryPaginationSelfBillInfo", mapParam);
		if(billList.size() > 0){
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			for (SSACTscWorkOrderBill res : billList) {
				billNo = res.getBillNo();
				//查询订单明细表
				paramMap.put("sql","SSACTscWorkOrderBillDetail.queryDetailByBillNo");
				paramMap.put("str",res.getBillNo());
				EiInfo callQueryMenuInfo = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);
				List<SSACTscWorkOrderBillDetail> billMenuInfos = (List<SSACTscWorkOrderBillDetail>) callQueryMenuInfo.get("result");
				if (billMenuInfos.size() > 0) {
					for (SSACTscWorkOrderBillDetail detail : billMenuInfos) {
						//获取图片
						temp = FileUtil.GetImageStr(detail.getMenuPicUrl());
						detail.setMenuPicUrl(temp);
					}
				}
				SSACTscWorkOrderBillDetail[] arr = billMenuInfos.toArray(new SSACTscWorkOrderBillDetail[0]);
				res.setBillDetail(arr);
				//判断订单状态返回不同的文本
				if("99".equals(res.getStatusCode()) && res.getRejectCode()!=null && "1".equals(res.getRejectCode())){
					res.setStatusName("退款处理中");
				}else if("99".equals(res.getStatusCode()) && res.getRejectCode()!=null && "2".equals(res.getRejectCode())){
					res.setStatusName("结束（已取消）");
				}else if("99".equals(res.getStatusCode()) && res.getRejectCode()!=null && "3".equals(res.getRejectCode())){
					res.setStatusName("已取消");
				}
			}
		}
		inInfo.set("obj", billList);
		return inInfo;
	}

	/**
	 *
	 *  queryPaginationWorkBillInfoDept获取科室的订单列表
	 *
	 * @Title: queryPaginationWorkBillInfoDept
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 * @author: kwr
	 * @date: 2022年8月9日 下午2:58:35
	 */
	public EiInfo queryPaginationWorkBillInfoDept(EiInfo inInfo){
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		ResultData j = new ResultData();
		//接收参数

		String deptNum = StringUtil.toString(attr.get("deptNum"));

		String billNo = StringUtil.toString(attr.get("billNo"));
		String sPage = StringUtil.toString(attr.get("page"));
		String mealNum = StringUtil.toString(attr.get("mealNum"));
		String needDate = StringUtil.toString(attr.get("needDate"));
		HashMap<String,Object> mapParam = new HashMap<String, Object>();
		String temp = "";
		if(StringUtil.isNotEmpty(billNo)){
			mapParam.put("billNo", billNo);
		}
		/**当前页数 */
		int page = 1;
		/** 每页条数 */
		int rows = 10;
		int startRow = 0;
		//判断参数是否为空
		if(StringUtils.isBlank(sPage)) {
			j.setSuccess(false);
			j.setRespMsg("参数page为空，请检查!");
			inInfo.setStatus(j.isSuccess() ? 1 : -1);
			inInfo.set("result", j);
			return inInfo;
		}else{
			page = Integer.parseInt(sPage);
		}
		if(StringUtils.isBlank(deptNum)) {
			j.setSuccess(false);
			j.setRespMsg("参数deptNum为空，请检查!");
			inInfo.setStatus(j.isSuccess() ? 1 : -1);
			inInfo.set("result", j);
			return inInfo;
		}

		startRow = (page-1)*rows;
		mapParam.put("deptNum", deptNum);
		mapParam.put("needDate", needDate);
		mapParam.put("startRow", startRow);
		mapParam.put("rows", rows);
		mapParam.put("mealNum", mealNum);

		//查询职工订单表
		List<SSACTscWorkOrderBill> billList = dao.query("SSACTscWorkOrderBill.queryPaginationWorkBillInfoDept", mapParam);
		inInfo.set("obj", billList);
		return inInfo;
	}
	

	/**
	 * 
	 * getBillDetail查询根据单号查询订单信息
	 *
	 * @Title: getBillDetail 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午2:58:53
	 */
	public EiInfo getBillDetail(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String billNo = StringUtil.toString(attr.get("billNo"));
		//查询订单主表
		List<PSPCTmealOrderBillPatient> bills = dao.query("PSPCTmealOrderBillPatient.queryBillByBillNo", billNo);
		String temp = "";
		for (int i = 0; i < bills.size(); i++) {
			//查询订单明细表
			List<PSPCTmealOrderBillDetail> billDetails = dao.query("PSPCTmealOrderBillDetail.queryBillDetailByBillNo", billNo);
			for (PSPCTmealOrderBillDetail detail : billDetails) {
				temp = FileUtil.GetImageStr(detail.getMenuPicUrl());
				detail.setMenuPicUrl(temp);
			}
			bills.get(i).setBillDetail(billDetails);
		}
		
		inInfo.set("obj", bills);
		return inInfo;
	}
	

	/**
	 * 
	 * getBillInfo获取订单信息
	 *
	 * @Title: queryWorkBill 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午2:59:04
	 */
	public EiInfo queryWorkBill(EiInfo inInfo){
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		String billNo = StringUtil.toString(attr.get("billNo"));

		ResultData j = new ResultData();
		List<SSACTscWorkOrderBill> result = null;
		if (StringUtils.isBlank(billNo)) {
			inInfo.set("success", false);
			inInfo.set("respMsg", "参数billNo为空，请检查!");
			return inInfo;
		}
		try {
			//查询订单信息
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sql","SSACTscWorkOrderBill.queryByBillNo");
			paramMap.put("str",billNo);
			EiInfo callQueryBillInfo = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);
			result = (List<SSACTscWorkOrderBill>) callQueryBillInfo.get("result");
			String temp = "";
			if (result.size() > 0) {
				for (SSACTscWorkOrderBill res : result) {
					//查询订单明细
					paramMap.put("sql","SSACTscWorkOrderBillDetail.queryDetailByBillNo");
//					paramMap.put("str",res.getBillNo());
					paramMap.put("str",billNo);
					EiInfo callQueryMenuInfo = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);
					List<SSACTscWorkOrderBillDetail> billMenuInfos = (List<SSACTscWorkOrderBillDetail>) callQueryMenuInfo.get("result");
					if (billMenuInfos.size() > 0) {
						for (SSACTscWorkOrderBillDetail detail : billMenuInfos) {
							//获取图片
							temp = FileUtil.GetImageStr(detail.getMenuPicUrl());
							detail.setMenuPicUrl(temp);
						}
					}
					SSACTscWorkOrderBillDetail[] arr = billMenuInfos.toArray(new SSACTscWorkOrderBillDetail[0]);
					res.setBillDetail(arr);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		inInfo.set("obj", result);

		return inInfo;
	}
	/**
	 *
	 * getBillInfo获取订单信息
	 *
	 * @Title: queryWorkBill
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 * @author: kwr
	 * @date: 2022年8月9日
	 */
//	public EiInfo queryWorkBill(EiInfo inInfo){
//		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
//
//		String billNo = StringUtil.toString(attr.get("billNo"));
//
//		ResultData j = new ResultData();
//		List<SSACTscWorkOrderBill> result = null;
//		if (StringUtils.isBlank(billNo)) {
//			inInfo.set("success", false);
//			inInfo.set("respMsg", "参数billNo为空，请检查!");
//			return inInfo;
//		}
//		List<SSACTscWorkOrderBill> billList = dao.query("SSACTscWorkOrderBill.queryByBillNo", billNo);
//		inInfo.set("obj", billList);
//		return inInfo;
//	}


	/**
	 *
	 * getBillInfo获取订单信息详情
	 *
	 * @Title: queryWorkBillDetail
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 * @author: kwr
	 * @date: 2022年8月9日
	 */
	public EiInfo queryWorkBillDetail(EiInfo inInfo){
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		String billNo = StringUtil.toString(attr.get("billNo"));

		ResultData j = new ResultData();
		List<SSACTscWorkOrderBill> result = null;
		if (StringUtils.isBlank(billNo)) {
			inInfo.set("success", false);
			inInfo.set("respMsg", "参数billNo为空，请检查!");
			return inInfo;
		}
		List<SSACTscWorkOrderBillDetail> billListDetail = dao.query("SSACTscWorkOrderBillDetail.queryDetailByBillNo", billNo);

//		}
		inInfo.set("obj", billListDetail);
		return inInfo;
	}

	/**
	 * 
	 * queryFloor查询层信息
	 *
	 * @Title: queryFloor 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午2:59:19
	 */
//	public EiInfo queryFloor(EiInfo inInfo) {
//		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
//
//		// 楼号
//		String building = StringUtil.toString(attr.get("building"));
//		// 院区
//		String dataGroupCode = StringUtil.toString(attr.get("dataGroupCode"));
//		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
//		// 构建查询条件
//		HashMap<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("building",building);
//		paramMap.put("canteenNum",canteenNum);
//		//查询送餐地点配置
//		List<HashMap<String,Object>> result = dao.query("SSBMSCDD01.queryFloorByCanteen", paramMap);
//		if(result == null || result.size() < 1) {
//			//没查到从字典表获取
//			paramMap.put("mealgroupTypeCode", "floor");
////			paramMap.put("paramValue1", dataGroupCode);
//			result = dao.query("SSBMStxx01.getMealTypeData", paramMap);
//			for (int i = 0; i < result.size(); i++) {
//				HashMap<String, Object> hashMap = result.get(i);
//				hashMap.put("floor", hashMap.get("typeCode"));
//				hashMap.put("floorName", hashMap.get("typeName"));
//			}
//		}
//		inInfo.set("obj", result);
//		return inInfo;
//	}

	public EiInfo queryFloor(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		// 楼号
		String building = StringUtil.toString(attr.get("building"));
		// 院区
		String dataGroupCode = StringUtil.toString(attr.get("dataGroupCode"));
		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("building",building);
//		paramMap.put("canteenNum",canteenNum);
		//查询送餐地点配置
		List<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
		//调用服务接口通过科室查询地点信息
		result = dao.query("SSACWorkBaseInfo.queryFloorByBu", paramMap);
		if(result == null || result.size() < 1){
			//都没查到
			inInfo.setStatus(-1);
			inInfo.set("obj", null);
			inInfo.setMsg("未查询到楼层信息");
		}
		inInfo.set("obj", result);
		return inInfo;
	}

	public EiInfo queryBuild(EiInfo inInfo) {
//		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		// 楼号
//		String building = StringUtil.toString(attr.get("building"));
		// 院区
//		String dataGroupCode = StringUtil.toString(attr.get("dataGroupCode"));
//		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("takeEffect","1");
//		paramMap.put("canteenNum",canteenNum);
		//查询送餐地点配置
		List<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
		//调用服务接口通过科室查询地点信息
		result = dao.query("SSACWorkBaseInfo.queryBuildByBu",paramMap);
		if(result == null || result.size() < 1){
			//都没查到
			inInfo.setStatus(-1);
			inInfo.set("obj", null);
			inInfo.setMsg("未查询到楼信息");
		}
		inInfo.set("obj", result);
		return inInfo;
	}

	public EiInfo querycheckRoomName(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		// 楼号
		String roomName = StringUtil.toString(attr.get("roomName"));
		// 院区
//		String dataGroupCode = StringUtil.toString(attr.get("dataGroupCode"));
//		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roomName",roomName);
//		paramMap.put("canteenNum",canteenNum);
		//查询送餐地点配置
		List<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
		//调用服务接口通过科室查询地点信息
		result = dao.query("SSACWorkBaseInfo.querycheckRoomName",paramMap);
		if(result.isEmpty()){
			inInfo.setStatus(0);
			inInfo.set("obj", "0");
			return inInfo;
		}
		inInfo.setStatus(1);
		inInfo.set("obj", "1");
		return inInfo;
	}
	

	/**
	 * 
     * queryDept查询科室信息
	 * 1.获取传参
	 * 2.优先查询送餐地点配置，送餐地点配置没查到，则通过档案中心接口获取
	 * 3.查询档案中心接口S_AC_FW_15获取楼和层对应的地点，档案中心接口没查到，则通过视图获取
	 * 4.通过地点信息调用档案中心接口S_AC_FW_08获取科室信息
	 * 5.档案中心获取的科室信息会存在很多重复，进行去重处理
	 * @Title: queryDept 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:00:12
	 */
//	public EiInfo queryDept(EiInfo inInfo) {
//		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
//
//		/** 1.获取传参楼和层*/
//		String building = StringUtil.toString(attr.get("building"));
//		String floor = StringUtil.toString(attr.get("floor"));
//		//科室查询标记
//		String deptFlag = StringUtil.toString(attr.get("deptFlag"));
//		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
//		// 构建查询条件
//		HashMap<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("building",building);
//		paramMap.put("floor",floor);
//		paramMap.put("canteenNum",canteenNum);
//		List<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
//		/**2.优先查询送餐地点配置，送餐地点配置没查到，则通过档案中心接口获取*/
//		result = dao.query("SSBMSCDD01.queryDeptByCanteen", paramMap);
//		if(result == null || result.size() < 1){
//			/**3.查询档案中心接口S_AC_FW_15获取楼和层对应的地点，档案中心接口没查到，则通过视图获取*/
//			EiInfo callSpot = XServiceUtil.call("S_AC_FW_15", paramMap);
//			if(callSpot.getStatus() < 0){
//				//查询失败,调用视图
//				result = dao.query("SSACWorkBaseInfo.queryDeptByBuildAndFloor", paramMap);
//			}else {
//				result = (List<HashMap<String, Object>>) callSpot.get("result");
//				HashMap<String,Object> m = new HashMap<String,Object>();
//				for (int i = 0; i < result.size(); i++) {
//					HashMap<String, Object> hashMap = result.get(i);
//					String spot_num = StringUtil.toString(hashMap.get("spot_num"));
//					String spot_name = StringUtil.toString(hashMap.get("spot_name"));
//					if(StringUtil.isNotEmpty(deptFlag)){
//						paramMap.put("spotNum",spot_num);
//						paramMap.put("spotName",spot_name);
//						/***4.通过地点信息调用档案中心接口S_AC_FW_08获取科室信息*/
//						EiInfo callDept = XServiceUtil.call("S_AC_FW_08", paramMap);
//						HashMap<String,Object> resultDept = new HashMap<String,Object>();
//						if(callDept.getStatus() < 0){
//							//查询失败
//							result = dao.query("SSACWorkBaseInfo.queryDeptByBuildAndFloor", paramMap);
//						}else {
//							resultDept = (HashMap<String, Object>) callDept.get("result");
//							m.put(StringUtil.toString(resultDept.get("deptNum")),resultDept.get("deptName"));
//						}
//					}else{
//						hashMap.put("dept_num", spot_num);
//						hashMap.put("dept_name", spot_name);
//					}
//				}
//				if(StringUtil.isNotEmpty(deptFlag)){
//					/**5.档案中心获取的科室信息会存在很多重复，进行去重处理*/
//					if(m.size() > 0){
//						HashMap<String, Object> hashMap = new HashMap<String, Object>();
//						for(Map.Entry<String, Object> s : m.entrySet()){
//							hashMap.put("dept_num", s.getKey());
//							hashMap.put("dept_name", s.getValue());
//						}
//						result.clear();
//						result.add(hashMap);
//					}
//				}
//			}
//		}
//		inInfo.set("obj", result);
//		if(result == null || result.size() < 1) {
//			//都没查到
//			inInfo.setStatus(-1);
//			inInfo.set("obj", null);
//			inInfo.setMsg("未查询到科室信息");
//		}
//		return inInfo;
//	}

	/**
	 *
	 * queryDept查询科室信息
	 * 1.获取传参
	 * 2.优先查询送餐地点配置，送餐地点配置没查到，则通过档案中心接口获取
	 * 3.查询档案中心接口S_AC_FW_15获取楼和层对应的地点，档案中心接口没查到，则通过视图获取
	 * 4.通过地点信息调用档案中心接口S_AC_FW_08获取科室信息
	 * 5.档案中心获取的科室信息会存在很多重复，进行去重处理
	 * @Title: queryDept
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:00:12
	 */
	public EiInfo queryDept(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		/** 1.获取传参楼和层*/
		String workNo = StringUtil.toString(attr.get("workNo"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("workNo",workNo);

		List<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
		/**2.优先查询送餐地点配置，送餐地点配置没查到，则通过档案中心接口获取*/
		result = dao.query("SSACWorkBaseInfo.queryDeptByPer", paramMap);
		if(result == null || result.size() < 1){
			//都没查到
			inInfo.setStatus(-1);
			inInfo.set("obj", null);
			inInfo.setMsg("未查询到科室信息");
		}
		inInfo.set("obj", result);
		return inInfo;
	}
	

	/**
	 * 
	 * queryRoom查询房间
	 *
	 * @Title: queryRoom 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:00:30
	 */
//	public EiInfo queryRoom(EiInfo inInfo) {
//		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
//
//		// 楼和层
//		String deptNum = StringUtil.toString(attr.get("deptNum"));
//		// 构建查询条件
//		HashMap<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("deptNum",deptNum);
//		//调用服务接口通过科室查询地点信息
//		EiInfo callDept = XServiceUtil.call("S_AC_FW_09", paramMap);
//		if(callDept.getStatus() < 0){
//			//没查到
//			inInfo.setStatus(-1);
//			inInfo.set("obj", null);
//			inInfo.setMsg("未查询到科室信息");
//		}else {
//			List<Map<String,String>> result = (List<Map<String,String>>) callDept.get("result");
//			inInfo.set("obj", result);
//		}
//		return inInfo;
//	}

	/**
	 *
	 * queryRoom查询房间
	 *
	 * @Title: queryRoom
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 * @author: kk
	 * @date: 2022年6月17日 下午3:00:30
	 */
	public EiInfo queryRoom(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		// 楼和层
		String building = StringUtil.toString(attr.get("building"));
		String floor = StringUtil.toString(attr.get("floor"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("building",building);
		paramMap.put("floor",floor);

		List<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
		//调用服务接口通过科室查询地点信息
		result = dao.query("SSACWorkBaseInfo.queryRoomByBu", paramMap);
		if(result == null || result.size() < 1){
			//都没查到
			inInfo.setStatus(-1);
			inInfo.set("obj", null);
			inInfo.setMsg("未查询到科室信息");
		}
		inInfo.set("obj", result);
		return inInfo;
	}
	
	/**
	 * 
	 * queryMealPayType查询食堂支付类型
	 *
	 * @Title: queryMealPayType 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:00:50
	 */
	public EiInfo queryMealPayType(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		
		// 食堂编号
		String canteenType = StringUtil.toString(attr.get("canteenType"));
		String orally = StringUtil.toString(attr.get("orally"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("canteenType",canteenType);
		paramMap.put("orally",orally);
		List<HashMap<String,Object>> result = dao.query("PSPCTmealPayType.queryMealPayType", paramMap);
		inInfo.set("obj", result);
		return inInfo;
	}
	

	/**
	 * 
	 * getEvalInfo查询菜品评价
	 *
	 * @Title: getEvalInfo 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:01:14
	 */
	public EiInfo getEvalInfo(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		ResultData result = new ResultData();
		String curPage = StringUtil.toString(attr.get("curPage"));
		String rows = StringUtil.toString(attr.get("rows"));
		String menuNum = StringUtil.toString(attr.get("menuNum"));
		//组装查询条件
		boolean message = false;
		
		int page = StringUtil.isEmpty(curPage) ? 1 : Integer.parseInt(curPage);
		int row = StringUtil.isEmpty(rows) ? 5 : Integer.parseInt(rows);
		
		int starts = (page-1) * row;
		int ends = page * row;
		try {
			HashMap<String, Object> pMap = new HashMap<String, Object>();
			pMap.put("menuNum", menuNum);
			pMap.put("ends", ends);
			pMap.put("starts", starts);
			//查询评价内容
			List<SSACTscWorkOrderBillDetail> detail = dao.query("SSACTscWorkOrderBillDetail.getEvalContent", pMap);
			message = true;
			result.setObj(detail);
			result.setSuccess(message);
		} catch (Exception e) {
			e.printStackTrace();
			message = false;
		}
		inInfo.set("result", result);
		return inInfo;
	}
	

	/**
	 * 
	 * getMenuDetail查询菜品详情
	 *
	 * @Title: getMenuDetail 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:01:34
	 */
	public EiInfo getMenuDetail(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		ResultData resultData = new ResultData();
		// 菜品编码
		String menuNum = StringUtil.toString(attr.get("menuNum"));
		
		List<SSBMCpxx02> result2 = null;
		List<SSBMTyTmealPic> result3 = null;
		String c = "";
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		boolean message = false;
		// 菜品编码
		List<PSPCTmealOrderMenu> result = dao.query("PSPCTmealOrderMenu.queryMenuDetail", menuNum);
		if(result.size()>0) {
			for(PSPCTmealOrderMenu mt : result) {
				map.put("mealId",mt.getMenuId());
				result3 = dao.query("SSBMTyTmealPic.query", map);
				if(result3!=null && result3.size()>0) {
					for(SSBMTyTmealPic pl : result3) {
						pl.setFileUrl(pl.getFileUrl().substring(1, pl.getFileUrl().length()));
					}
					mt.setPicList(result3);
				}
				
			}
			//查询菜品构成表
			result2 = dao.query("SSBMCpxx02.getdescptionInfo", result.get(0).getMenuId());
			//构建菜品详情信息
			for(SSBMCpxx02 li : result2){
				//判断查询结果是否位空
				if(li.getMaterial()!=null&&(!("").equals(li.getMaterial()))){
					c += li.getMaterial();
					if(("").equals(li.getQuantum())&&("").equals(li.getDescription())){
						c += ";";                                                                                            
					}
				}
				//份量
				if(li.getQuantum()!=null&&(!("").equals(li.getQuantum()))){
					c += li.getQuantum();
					if(li.getDescription()==null||(("").equals(li.getDescription()))){
						c = c +";";
					}
				}
				//描述
				if(li.getDescription()!=null&&(!(("").equals(li.getDescription())))){
					c += li.getDescription()+";";
				}
			}
			result.get(0).setComprise(c);
			message = true;
		}
		resultData.setObj(result);
		resultData.setSuccess(message);
		inInfo.set("result", resultData);
		return inInfo;
	}
	
	

	/**
	 * 
	 * queryMenuInfo查询食堂菜品信息 , 无分页, 图片格式为地址
	 *
	 * @Title: queryMenuInfo 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:02:26
	 */
	public EiInfo queryMenuInfo(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		
		ResultData resultData = new ResultData();
		// 获取传参
		String mealType = StringUtil.toString(attr.get("mealType"));
		String dayFlag = StringUtil.toString(attr.get("dayFlag"));
		String mealNum = StringUtil.toString(attr.get("mealNum"));
		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		String orally = StringUtil.toString(attr.get("orally"));
		
		Map<String, Object> paramMap = new HashMap<String ,Object>();
		paramMap.put("mealNum", mealNum);
		paramMap.put("canteenNum", canteenNum);
		paramMap.put("mealType", mealType);
		paramMap.put("dayFlag", dayFlag);
		
		if(StringUtils.isBlank(dayFlag)||StringUtils.isBlank(mealNum)||StringUtils.isBlank(canteenNum)){
			resultData.setSuccess(false);
			resultData.setRespMsg("查询菜品时参数错误");
			resultData.setObj(null);
			resultData.setTotal(0);
		}else{
			List<PSPCTmealOrderMenu> result = null;
			int total = 0;
			Date nowDate = new Date();
			
			String today = CalendarUtils.dateFormat(nowDate);
			Calendar cd = Calendar.getInstance();
			
			paramMap.put("operateCode", "meal");//菜品所属类型
			// 如果是当前日期做餐类查询限制
			if(dayFlag.equals("D") || dayFlag.equals(CalendarUtils.getDateByFormat(CalendarUtils.DateyMd))) {
				Map<String, String> pMap = new HashMap<String, String>();
				pMap.put("canteenNum", canteenNum);
				List<SSBMDcsj01> canteenTimesInfo = dao.query("SSBMDcsj01.query", pMap);
				if(!canteenTimesInfo.isEmpty()) {
					for(SSBMDcsj01 canteenTime : canteenTimesInfo) {
						if(paramMap.get("mealNum").toString().equals(canteenTime.getMealNum())) {
							String todayMealTypeCode = canteenTime.getTodayMealTypeCode();
							if(!StringUtils.isEmpty(todayMealTypeCode)){
								paramMap.put("todayMealTypeCode", canteenTime.getTodayMealTypeCode());//今日可点菜品分类
							}
						}
					}
				}
			}
			//判断订餐标识
			if(dayFlag.equals("D")){
				paramMap.put("operateName", "普餐");
				paramMap.put("mealData", today);
				//查询订单明细表
				result = dao.query("PSPCTmealOrderMenu.queryMenuTimeInfoByApp", paramMap);
				total = dao.count("PSPCTmealOrderMenu.queryCount", paramMap);
				resultData.setSuccess(true);
			}else if(dayFlag.equals("Y")) {
				try {
					paramMap.put("operateName", "普餐");
					cd.add(cd.DATE, 1);
					paramMap.put("mealData", CalendarUtils.dateFormat(cd.getTime()));
					//查询订单明细表
					result = dao.query("PSPCTmealOrderMenu.queryMenuTimeInfoByApp", paramMap);
					total = dao.count("PSPCTmealOrderMenu.queryCount", paramMap);
					resultData.setSuccess(true);
				} catch (Exception e) {
					e.printStackTrace();
					resultData.setSuccess(false);
					resultData.setRespMsg(e.getMessage());
				}
			} else {
				paramMap.put("mealData",dayFlag);
				//查询订单明细表
				result = dao.query("PSPCTmealOrderMenu.queryMenuTimeInfoByApp", paramMap);
				total = dao.count("PSPCTmealOrderMenu.queryCount", paramMap);
				resultData.setSuccess(true);
			}
			
			if(null != result && result.size()>0){
				for(PSPCTmealOrderMenu mt : result){
					paramMap.put("mealId", mt.getMenuId());
					//查询图片表
					List<SSBMTyTmealPic> list = dao.query("SSBMTyTmealPic.query", paramMap);
					if(list!=null&&list.size()>0) {
						for(SSBMTyTmealPic pl : list) {
							if(StringUtil.isNotEmpty(pl.getFileUrl())) {
								//获取图片
								mt.setMenuPicUrl(pl.getFileUrl().substring(1, pl.getFileUrl().length()));
								break;
							}
						}
					}
				}
			}
			resultData.setRespMsg("查询成功");
			resultData.setObj(result);
			resultData.setTotal(total);
		}
		inInfo.setStatus(resultData.isSuccess() ? 1 : -1);
		inInfo.set("result", resultData);
		return inInfo;
	}
	

	/**
	 * 
	 * queryPaginationMenu分页加载菜品数据
	 *
	 * @Title: queryPaginationMenu 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:02:51
	 */
	public EiInfo queryPaginationMenu(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 食堂编号
		String dayFlag = StringUtil.toString(attr.get("dayFlag"));
		String mealNum = StringUtil.toString(attr.get("mealNum"));
		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		String mealType = StringUtil.toString(attr.get("mealType"));
		String deviceType = StringUtil.toString(attr.get("deviceType"));
		ResultData resultData = new ResultData();
		List<PSPCTmealOrderMenu> result = null;
		String temp = "";
		Map<String,Object> map0 = new HashMap<String,Object>();
		int total = 0;
		/**当前页数 */
	     int page = 1;
		/** 每页条数 */
		 int rows = 10;
		 int startRow = 0;
		//当前页数
		if(StringUtil.isNotEmpty(StringUtil.toString(attr.get("page")))) {
			page = Integer.parseInt(StringUtil.toString(attr.get("page")));
		}
		
		if("pos".equals(deviceType)){
			rows = 1000;
		}
		//判断查询参数
		if(StringUtil.isBlank(dayFlag)
				||StringUtil.isBlank(mealNum)
				||StringUtil.isBlank(canteenNum)
				||StringUtil.isBlank(StringUtil.toString(attr.get("page"))) ){
			resultData.setSuccess(false);
			resultData.setRespMsg("查询菜品时参数错误");
			resultData.setObj("");
		}else{
			startRow = (page-1)*rows;
			Date nowDate = new Date();
			
			String today = CalendarUtils.dateFormat(nowDate);
			Calendar cd = Calendar.getInstance();
			//组装查询条件
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("mealNum", mealNum);
			map.put("canteenNum", canteenNum);
			map.put("mealType", mealType);
			map.put("startRow", startRow);
			map.put("rows", rows);
			map.put("operateCode", "meal");
			
			if(dayFlag.equals("D")) {
				map.put("operateName", "普餐");
				map.put("mealData", today);
			} else if(dayFlag.equals("Y")) {
				map.put("operateName", "普餐");
				cd.add(cd.DATE, 1);
				map.put("mealData", CalendarUtils.dateFormat(cd.getTime()));
			} else {
				if(dayFlag.length() == 5){
					map.put("mealData", CalendarUtils.getYear(true)+"-"+dayFlag);
				} else {
					map.put("mealData", dayFlag);
				}
			}
			
			//前端查询菜品信息时，根据类型进行区分，APP端查询的单价为折后价，POS的为原价
			if(StringUtils.isNotBlank(deviceType) && "pos".equals(deviceType)) {
				//POS订餐菜品查询
				result = dao.query("PSPCTmealOrderMenu.queryMenuTimeInfoByPos", map);
			}else {
				//默认菜品数据查询
				result = dao.query("PSPCTmealOrderMenu.queryMenuTimeInfoByApp", map);
			}
			
			total = dao.count("PSPCTmealOrderMenu.queryCount", map);
			//POS机查询时不传图片 
			if("pos".equals(deviceType)){
				
			} else {
				if(null != result && result.size()>0){
					for(PSPCTmealOrderMenu mt : result){
						map.put("mealId",mt.getMenuId());
						//查询菜品图片
						List<SSBMTyTmealPic> list = dao.query("SSBMTyTmealPic.query", map);
						if(list!=null&&list.size()>0) {
							for(SSBMTyTmealPic pl : list) {
								if(StringUtil.isNotEmpty(pl.getFileUrl())) {
									//获取图片
									temp = FileUtil.GetImageStr(pl.getFileUrl());
									mt.setMenuPicUrl(temp);
									break;
								}
							}
						}
					}
				}
			}
		}
		inInfo.setStatus(resultData.isSuccess() ? 1 : -1);
		inInfo.set("result", result);
		inInfo.set("total", total);
		return inInfo;
	}
	

	/**
	 * 
	 * getDateList获取可订餐日期
	 *
	 * @Title: getDateList 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:03:04
	 */
	public EiInfo getDateList(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 食堂编号
		String dataGroupCode = StringUtil.toString(attr.get("dataGroupCode"));
		String canteenType = StringUtil.toString(attr.get("canteenType"));
		
		int mealMaxDay = 5;
		String dataGroup = "";
		// 获取数据字典中订餐的最大订餐天数
		if(StringUtil.isNotEmpty(canteenType) && "zgst".equals(canteenType)) {
			dataGroup = "workNumDays";
		}else {
			dataGroup = "patiNumDays";
		}
		List<SSBMSjzd02> types = dao.query("SSBMSjzd02.queryTypeValue", dataGroup);
		if(!types.isEmpty()) {
			SSBMSjzd02 type = types.get(0);
			String objType = type.getTypecode();
			if(StringUtil.isNotEmpty(objType)) {
				mealMaxDay = Integer.parseInt(objType.split("#")[0]);
			}
		}
		List<String> dateCalender = CalendarUtils.getDateCalender(mealMaxDay, CalendarUtils.DateyMd, true);
		
		inInfo.set("obj", dateCalender);
		return inInfo;
	}
	

	/**
	 * 
	 * getMealType获取菜品分类
	 *
	 * @Title: getMealType 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:03:18
	 */
	public EiInfo getMealType(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 食堂编号
		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("spuerMealTypeNum", canteenNum);
		List<SSBMCpfl01> patient = dao.query("SSBMCpfl01.query", paramMap);
		inInfo.set("obj", patient);
		return inInfo;
	}
	
	/**
	 * 
	 * getCanteen获取食堂列表
	 *
	 * @Title: getCanteen 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:07:20
	 */
	public EiInfo getCanteen(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 食堂类型
		String canteenTypeNum = StringUtil.toString(attr.get("canteenTypeNum"));
		// 账套
		String dataGroupCode = StringUtil.toString(attr.get("dataGroupCode"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("canteenTypeNum", canteenTypeNum);
		paramMap.put("dataGroupCode", dataGroupCode);
		List<SSBMStxx01> canteens = dao.query("SSBMStxx01.queryCanteenInfoGd", paramMap);
		List<SSBMDcsj01> canteenTimesInfo = null;
		List<SSBMCpfl01> resultType = null;
		List<SSBMDcsj01> resultSend = null;
		List<HashMap<String,String>> buildConfig = new ArrayList<HashMap<String,String>>();
		//查询楼信息配置
		if("bhst".equals(canteenTypeNum)) {
			//病患食堂 获取床头码配置
			paramMap.put("areaCode", dataGroupCode);
			buildConfig = dao.query("SSBMCtm01.getQrBuilding",paramMap);
		}else if("zgst".equals(canteenTypeNum)) {
			//职工食堂 根据院区获取地点信息
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("dataGroupCode", dataGroupCode);
			//调用服务接口
			EiInfo callUser = XServiceUtil.call("S_AC_FW_13", paramMap);
			if(callUser.getStatus() < 0){
				//没查到,获取数据字典配置
				paramMap.put("mealgroupTypeCode", "building");
				paramMap.put("paramValue1", dataGroupCode);
				buildConfig = dao.query("SSBMStxx01.getMealTypeData", paramMap);
			}else {
				
			}
		}
		//订餐日期
		List<String> patiOrderDate = canteenOrderDate();
		for (int i = 0; i < canteens.size(); i++) {
			SSBMStxx01 canteenInfo = canteens.get(i);
			canteenInfo.setPatiOrderDate(patiOrderDate);
			//食堂楼信息
			canteenInfo.setBuildConfig(buildConfig);
			paramMap.put("canteenNum",canteenInfo.getCanteenNum());
			//查询餐类
			resultType = dao.query("SSBMCpfl01.query",paramMap);
			canteenInfo.setMealType(resultType);
			
			//查询食堂订餐时间表
			paramMap.put("canteenNum", canteenInfo.getCanteenNum());
			canteenTimesInfo = dao.query("SSBMDcsj01.query", paramMap);
			canteenInfo.setCanteenTimesInfo(canteenTimesInfo);
			//格式化送餐时间
			resultSend = new ArrayList<SSBMDcsj01>();
			if(canteenTimesInfo.size()>0){
				for (SSBMDcsj01 ms : canteenTimesInfo) {
					String sendTime = ms.getSendTime();
					String mealTime = ms.getMealNum();
					if(sendTime.contains(",")){
						//拆分送餐时间字符串
						String[] st = sendTime.split(",");
						for (String string : st) {
							SSBMDcsj01 menuSendTime = new SSBMDcsj01(mealTime,string);
							resultSend.add(menuSendTime);
						}
					}else{
						resultSend.add(ms);
					}
				}
			}
			canteenInfo.setSendTime(resultSend);
		}
		
		inInfo.set("obj", canteens);
		return inInfo;
	}


	/**
	 * 
	 * canteenOrderDate获取食堂订餐日期
	 *
	 * @Title: canteenOrderDate 
	 * @return 
	 * @return: List<String> 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:07:59
	 */
	public List<String> canteenOrderDate() {
		List<String> lst  = new ArrayList<String>();
		List<String> lst2  = new ArrayList<String>();
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		//canteenData:食堂名称
		paramMap.put("mealgroupTypeCode", "patiNumDays");
		List<HashMap<String,Object>> listNumDays = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		
		String strDays    = "";
		int  numDay       = 0;
		int  startDay     = 0;
		
		//加载默认天数
		if(listNumDays == null || listNumDays.size() == 0) {
			numDay   = 3;
			startDay = 0;
		} else {
			strDays = StringUtil.toString(listNumDays.get(0).get("typeCode"));
			String[] bnArray = strDays.split("#");
			if(bnArray.length == 1) {
				numDay   = Integer.parseInt(bnArray[0]);
				startDay = 0;
			} else if(bnArray.length == 2) {
				numDay   = Integer.parseInt(bnArray[0]);
				startDay = Integer.parseInt(bnArray[1]);
			} else {
				numDay   = 3;
				startDay = 0;
			}
		}
		lst = CalendarUtils.dateDataListAndYear(numDay,startDay);
		for (String str : lst) {
			String s = str.replace("/", "-");
			lst2.add(s);
		}
		return lst2;
	}
	

	/**
	 * 
	 * getCanteenList获取食堂列表
	 *
	 * @Title: getCanteenList 
	 * @param eiInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午3:08:11
	 */
	public EiInfo getCanteenList(EiInfo eiInfo){
		EiInfo inInfo = new EiInfo();
		HashMap<String,Object> attr = (HashMap<String, Object>) eiInfo.getAttr().get("paramObject");
		String wardCode = StringUtil.toString(attr.get("wardCode"));
		System.out.println(wardCode);
		
		List<HashMap<String,String>> lst = dao.query("SSBMStxx01.queryCanteenFromWardCode",wardCode);
		inInfo.addBlock("canteenList").addRows(lst);
		
		inInfo.set("obj", lst);
		return inInfo;
	}

	/**
	 * 职工app报表食堂日销售额表
	 * @Title  getCanteenDailySales
	 * @author liu
	 * @date 2022-02-18 13:57
	 * @param inInfo 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo getCanteenDailySales(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		List<HashMap<String,String>> lst = dao.query("SSBMStxx01.getCanteenDailySales",attr);
		inInfo.set("obj", lst);
		return inInfo;
	}

	/**
	 * 职工app报表菜品销量排行统计报表
	 * @Title  getCanteenDailySales
	 * @author liu
	 * @date 2022-02-18 13:57
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo getFoodSales(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		List<HashMap<String,String>> lst = dao.query("SSBMStxx01.getFoodSales",attr);
		inInfo.set("obj", lst);
		return inInfo;
	}

	/**
	 * 职工app报表菜品评价报表
	 * @Title  getCanteenDailySales
	 * @author liu
	 * @date 2022-02-18 13:57
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo getFoodEvaluation(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		List<HashMap<String,String>> lst = dao.query("SSBMStxx01.getFoodEvaluation",attr);
		inInfo.set("obj", lst);
		return inInfo;
	}

	/**
	 * 学生app订餐查询订单
	 * @Title  queryStuMeal
	 * @author keke
	 * @date 2022-10-2 13:57
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo queryStuMeal(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String billNo = StringUtil.toString(attr.get("billNo"));
		String recCreator = StringUtil.toString(attr.get("recCreator"));
		Map map = new HashMap<>();
		map.put("recCreator",recCreator);
		List<HashMap<String,String>> list = dao.query("SSACTscWorkOrderBill.queryStuMeal",map);
		inInfo.set("obj", list);
		return inInfo;
	}

	/**
	 * 更改学生取餐后的状态
	 * @Title  queryStuType
	 * @author keke
	 * @date 2022-10-2 13:57
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo queryStuType(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String billNo = StringUtil.toString(attr.get("billNo"));
		String recCreator = StringUtil.toString(attr.get("recCreator"));
		String statusCode = StringUtil.toString(attr.get("statusCode"));
		String mealNum = StringUtil.toString(attr.get("mealNum"));
			Map map = new HashMap<>();
		    Map map1 = new HashMap<>();
			map.put("recCreator",recCreator);
			map.put("billNo",billNo);
			map.put("statusCode","99");
			map.put("mealNum",mealNum);
		    map1.put("recCreator",recCreator);
		    map1.put("billNo",billNo);
		    map1.put("mealNum",mealNum);
			List<HashMap<String,String>> list = dao.query("SSACTscWorkOrderBill.checkStuBill",map1);
			if(list.isEmpty()){
				inInfo.setStatus(1);
				inInfo.addMsg("404");
				inInfo.set("obj", "未找到结果");
				return inInfo;
			}else {
				dao.update("SSACTscWorkOrderBill.queryStuType",map);
				inInfo.set("obj", "更新成功");
				return inInfo;
			}

	}

	/**
	 * 学生订餐登录手持机时订餐人角色
	 * @Title  queryUserRole
	 * @author keke
	 * @date 2022-10-2 13:57
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo queryUserRole(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		String workNo = StringUtil.toString(attr.get("recCreator"));

		Map map = new HashMap<>();
		map.put("workNo",workNo);

		List<HashMap<String,String>> list = dao.query("SSACTscWorkOrderBill.queryUserRole",map);
		inInfo.set("obj", list);
		return inInfo;
	}

	/**
	 * 查询人员科室名称不为空
	 * @Title  queryUserDeptName
	 * @author keke
	 * @date 2022-10-2 13:57
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo queryUserDeptName(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		String workNo = StringUtil.toString(attr.get("recCreator"));

		Map map = new HashMap<>();
		map.put("workNo",workNo);

		List<HashMap<String,String>> list = dao.query("SSACTscWorkOrderBill.queryUserDeptName",map);
		System.out.println("list:"+list);
		if(list.get(0).get("deptName").isEmpty()){
			inInfo.setStatus(1);
			inInfo.addMsg("404");
			inInfo.set("obj", "该人员科室有误，请联系管理员");
			return inInfo;
		}else {
			inInfo.setStatus(1);
			inInfo.addMsg("200");
			inInfo.set("obj", "成功");
			return inInfo;
		}
	}

	/**
	 * 查询人员科室名称不为空
	 * @Title  queryUserDeptNameNew
	 * @author keke
	 * @date 2022-10-2 13:57
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo queryUserDeptNameNew(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		String workNo = StringUtil.toString(attr.get("recCreator"));

		Map map = new HashMap<>();
		map.put("workNo",workNo);

		List<HashMap<String,String>> list = dao.query("SSACTscWorkOrderBill.queryUserDeptNameNew",map);
//		System.out.println("list:"+list);
		if(list.get(0).get("deptName").isEmpty()){
			inInfo.setStatus(1);
			inInfo.addMsg("404");
			inInfo.set("obj", "该人员科室为空，请联系管理员");
			return inInfo;
		}else {
			inInfo.setStatus(1);
			inInfo.addMsg("200");
			inInfo.set("obj", list);
			return inInfo;
		}
	}

	/**
	 * 查询是否该楼层存在限制套餐
	 * @Title  queryMeauNum
	 * @author keke
	 * @date 2022-10-2 13:57
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo queryMeauNum(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		String menuName = StringUtil.toString(attr.get("menuName"));

		Map map = new HashMap<>();
		map.put("menuName",menuName);

		List<HashMap<String,String>> list = dao.query("SSACTscWorkOrderBill.queryMeauNum",map);
			inInfo.setStatus(1);
			inInfo.addMsg("200");
			inInfo.set("obj", list);
			return inInfo;

	}


}
