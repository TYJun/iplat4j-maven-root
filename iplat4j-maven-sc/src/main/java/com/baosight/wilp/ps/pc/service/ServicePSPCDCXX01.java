package com.baosight.wilp.ps.pc.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.data.ibatis.support.SqlMapClientDaoSupport;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ps.pc.domain.*;
import com.baosight.wilp.ss.bm.config.MealCommonConfig;
import com.baosight.wilp.ss.bm.domain.*;
import com.baosight.wilp.ss.bm.utils.*;
import com.baosight.xservices.xs.authentication.SecurityBridgeFactory;
import com.baosight.xservices.xs.util.MD5Util;
import com.bonawise.domain.base.BusinessInfoDTO;
import com.bonawise.domain.base.ResultEntry;
import com.bonawise.smp.weChat.hessian.WeChatPayImpl;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 
 * ServicePSPCDCXX01 病员订餐信息service
 * 
 * @Title: ServicePSPCDCXX01.java
 * @ClassName: ServicePSPCDCXX01
 * @Package：com.baosight.wilp.ps.pc.service
 * @author: liutao
 * @date: 2021年9月9日 上午10:28:45
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePSPCDCXX01 extends ServiceBase {

	public static final  String GET_PA_INFO = PlatApplicationContext.getProperty("sc_pa_info");



	/**
	 * getEffectiveNotice获取生效的公告信息
	 * *入参：公告类型编码typeCode
	 * *出餐：EiInfo(obj:食堂公告)
	 *
	 * @param inInfo
	 * @return
	 * @Title: getEffectiveNotice
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:28:56
	 */
	public EiInfo getEffectiveNotice(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 类型编码
		String typeCode = StringUtil.toString(attr.get("typeCode"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("typeCode", typeCode);
		paramMap.put("isEffective", "1");
		if (StringUtils.isBlank(typeCode)) {
			inInfo.setStatus(-1);
			inInfo.set("respMsg", "参数typeCode为空！");
			return inInfo;
		}
		//查询食堂公告表
		List<SSBMStgg01> stgg = dao.query("SSBMStgg01.query", paramMap);
		if (stgg != null && stgg.size() > 0) {
			inInfo.setStatus(1);
		} else {
			inInfo.setStatus(-1);
			inInfo.setMsg("查询失败");
		}
		inInfo.set("obj", stgg);

		return inInfo;
	}

	/**
	 * getWeChatAppId 获取食堂对应的微信公众号appId
	 * *入参：食堂编号canteenNum，帐套编号dataGroupCode
	 * *出餐：EiInfo(obj：支付凭证)
	 * 1. 构建查询条件
	 * 2.获取微信支付凭证
	 *
	 * @param inInfo
	 * @return
	 * @Title: getWeChatAppId
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:29:20
	 */
	public EiInfo getWeChatAppId(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 食堂
		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		// 账套
		String dataGroupCode = StringUtil.toString(attr.get("dataGroupCode"));
		/**1. 构建查询条件*/
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("canteenNum", canteenNum);
		paramMap.put("dataGroupCode", dataGroupCode);
		if (StringUtils.isBlank(canteenNum)) {
			inInfo.setStatus(-1);
			inInfo.set("respMsg", "参数canteenNum为空！");
			return inInfo;
		}
		//组装hessian查询条件
		BusinessInfoDTO busInfoDTO = new BusinessInfoDTO();
		busInfoDTO.setHospitalCode(MealCommonConfig.getSmpConfig().getHospitalCodeWechat());
		busInfoDTO.setModulCode("patient_wechat");
		busInfoDTO.setUsedUnitCode(canteenNum);
		System.out.println("busInfoDTO.toString() : " + busInfoDTO.toString());
		/**2.获取微信支付凭证AppId*/
		ResultEntry result = WeChatPayImpl.getHospitalAccount(busInfoDTO);
		if (!"200".equals(result.getRespCode() + "")) {
			inInfo.setStatus(-1);
			inInfo.set("respMsg", "获取appId失败！");
			System.out.println("result.getRespMsg() : " + result.getData() + result.getRespMsg());
			return inInfo;
		}
		inInfo.set("obj", result.getData());

		return inInfo;
	}

	/**
	 * getWeChatOpenId获取微信支付凭证
	 * *入参：食堂编号canteenNum，微信代码code，食堂名称canteenName
	 * *出餐：EiInfo(ResultData:obj支付凭证)
	 *
	 * @param inInfo
	 * @return
	 * @throws Exception
	 * @Title: getWeChatOpenId
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:30:11
	 */
	public EiInfo getWeChatOpenId(EiInfo inInfo) throws Exception {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		String dataGroupCode = StringUtil.toString(attr.get("dataGroupCode"));
		String code = StringUtil.toString(attr.get("code"));
		String canteenName = StringUtil.toString(attr.get("canteenName"));
		String flag = StringUtil.toString(attr.get("flag"));
		String modulCode = "patient_wechat";
		ResultData resultData = new ResultData();

		if (StringUtils.isBlank(code)) {
			resultData.setSuccess(false);
			resultData.setRespMsg("参数code为空！");
			inInfo.setStatus(resultData.isSuccess() ? 1 : -1);
			inInfo.set("result", resultData);
			return inInfo;
		}

		// 病员充值获取配置的食堂编码
		if (StringUtils.isNotBlank(flag) && "patientRecharge".equals(flag)) {
			canteenNum = MealCommonConfig.getSmpConfig().getPatientCanteenNum();
			canteenName = MealCommonConfig.getSmpConfig().getPatientCanteenName();
			modulCode = "patient_recharge_wechat";
		}
		//判断参数是否为空
		if (StringUtils.isBlank(canteenNum)) {
			resultData.setSuccess(false);
			resultData.setRespMsg("参数canteenNum为空！");
			inInfo.setStatus(resultData.isSuccess() ? 1 : -1);
			inInfo.set("result", resultData);
			return inInfo;
		}

		if (StringUtils.isBlank(canteenName)) {
			resultData.setSuccess(false);
			resultData.setRespMsg("参数canteenName为空！");
			inInfo.setStatus(resultData.isSuccess() ? 1 : -1);
			inInfo.set("result", resultData);
			return inInfo;
		}
		//构建hessian查询实体类
		BusinessInfoDTO busInfoDTO = new BusinessInfoDTO();
		busInfoDTO.setHospitalCode(MealCommonConfig.getSmpConfig().getHospitalCodeWechat());
		busInfoDTO.setModulCode(modulCode);
		busInfoDTO.setUsedUnitCode(canteenNum);
		busInfoDTO.setUserUnitName(canteenName);

		//获取微信支付凭证OpenId
		ResultEntry result = WeChatPayImpl.getOpenId(code, busInfoDTO);
		if (result.getRespCode() != 200) {
			resultData.setSuccess(false);
			resultData.setRespMsg(result.getRespMsg());
			inInfo.setStatus(resultData.isSuccess() ? 1 : -1);
			inInfo.set("result", resultData);
			return inInfo;
		}
		System.out.println(result);
		resultData.setSuccess(true);
		resultData.setRespMsg("获取成功！");
		resultData.setObj(result.getData());
		inInfo.set("result", resultData);
		return inInfo;
	}


	/**
	 * getBillDetail查询根据单号查询订单信息
	 *
	 * @param inInfo
	 * @return
	 * @Title: getBillDetail
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:40:42
	 */
	public EiInfo getBillDetail(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
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
	 * getBillInfo获取订单信息
	 *
	 * @param inInfo
	 * @return
	 * @Title: getBillInfo
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:40:58
	 */
	public EiInfo getBillInfo(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		String openId = StringUtil.toString(attr.get("userCode"));
		String userId = StringUtil.toString(attr.get("userId"));
		//分页
		String sPage = StringUtil.toString(attr.get("page"));
		//住院号
		String patientCode = StringUtil.toString(attr.get("patientCode"));
		//科室
		String deptName = StringUtil.toString(attr.get("deptName"));
		//餐次
		String mealNum = StringUtil.toString(attr.get("mealNum"));
		//需求日期
		String needDate = StringUtil.toString(attr.get("needDate"));
		//起始日期 
		String startDate = StringUtil.toString(attr.get("startDate"));
		//终止日期 
		String endDate = StringUtil.toString(attr.get("endDate"));
		//科室编码 
		String deptNum = StringUtil.toString(attr.get("deptNum"));
		//状态码
		String pboCode = StringUtil.toString(attr.get("pboCode"));
		String statusCode = StringUtil.toString(attr.get("statusCode"));
		//行号
		String rows1 = StringUtil.toString(attr.get("rows"));
		//设备标识
		String archiveFlag = StringUtil.toString(attr.get("archiveFlag"));
		//手机号
		String userTelNumber = StringUtil.toString(attr.get("userTelNumber"));

		ResultData j = new ResultData();
		List<PSPCTmealOrderBillPatient> result = null;
		String temp = "";
		/** 当前页数 */
		int page = 1;
		/** 每页条数 */
		int rows = 10;
		int startRow = 0;

		if (rows1 != null) {
			rows = Integer.parseInt(rows1);
		}
		if (StringUtils.isBlank(sPage)) {
			inInfo.set("success", false);
			inInfo.set("respMsg", "参数Page为空，请检查!");
			return inInfo;
		} else {
			page = Integer.parseInt(sPage);
		}
		if (StringUtils.isBlank(openId) && StringUtils.isBlank(userId)) {
			inInfo.set("success", false);
			inInfo.set("respMsg", "参数用户编码为空，请检查!");
			return inInfo;
		}
		startRow = (page - 1) * rows;
		//构建查询条件
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("openId", openId);
		map.put("userId", userId);
		map.put("startRow", startRow);
		map.put("rows", rows);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("deptName", deptName);
		map.put("mealNum", mealNum);
		map.put("needDate", needDate);
		map.put("deptNum", deptNum);
		map.put("patientCode", patientCode);
		map.put("pboCode", pboCode);
		map.put("archiveFlag", archiveFlag);
		map.put("statusCode", statusCode);
		map.put("userTelNumber", userTelNumber);
		try {
			//查询订单信息表
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sql", "PSPCTmealOrderBillPatient.getBillInfoByUserCode");
			paramMap.put("map", map);
			EiInfo callQueryBillInfo = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByMap", paramMap);
			result = (List<PSPCTmealOrderBillPatient>) callQueryBillInfo.get("result");

			if (result.size() > 0) {
				for (PSPCTmealOrderBillPatient res : result) {
					String billNo = res.getBillNo();
					//查询订单明细
					paramMap.put("sql", "PSPCTmealOrderMenu.getMenuInfoByBillNo");
					paramMap.put("str", billNo);
					//查询病员信息同步表
					String card_code = (String) ((SqlMapClientDaoSupport) dao).getSqlMapClient().queryForObject("PSPCTmealPatientCard.queryCardId", billNo);
					System.out.println(card_code + "---------------card_code-----------------------");
					EiInfo callQueryMenuInfo = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);
					List<PSPCTmealOrderMenu02> billMenuInfos = (List<PSPCTmealOrderMenu02>) callQueryMenuInfo.get("result");
					if (billMenuInfos.size() > 0) {
						for (PSPCTmealOrderMenu02 detail : billMenuInfos) {
							//处理图片url
							temp = FileUtil.GetImageStr(detail.getMenuPicUrl());
							detail.setMenuPicUrl(temp);
						}
					}
					res.setBillDetail(billMenuInfos);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		inInfo.set("obj", result);
		return inInfo;
	}

	/**
	 * queryKeepAccounts查询挂账订单
	 *
	 * @param inInfo
	 * @return
	 * @Title: queryKeepAccounts
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:40:58
	 */
	public EiInfo queryKeepAccounts(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		String openId = StringUtil.toString(attr.get("openId"));
		String userId = StringUtil.toString(attr.get("userId"));
		//结算流水号
		String feeFn = StringUtil.toString(attr.get("feeFn"));
		String RecordFlow = StringUtil.toString(attr.get("RecordFlow"));

		ResultData j = new ResultData();
		List<PSPCTmealOrderBillPatient> result = null;
		String temp = "";
		if (StringUtils.isBlank(openId) && StringUtils.isBlank(userId)) {
			inInfo.set("success", false);
			inInfo.set("respMsg", "参数用户编码为空，请检查!");
			return inInfo;
		}
		//构建查询条件
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("openId", openId);
		map.put("userId", userId);
		try {
			//查询订单信息表
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sql", "PSPCTmealOrderBillPatient.queryKeepAccounts");
			paramMap.put("map", map);
			EiInfo callQueryBillInfo = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByMap", paramMap);
			result = (List<PSPCTmealOrderBillPatient>) callQueryBillInfo.get("result");
			inInfo.set("obj", result);
			inInfo.setStatus(1);
			//保存结算记录
			if (result != null && result.size() > 0) {
				/*PSPCTmealOrderBillPatient pspcTmealOrderBillPatient = result.get(0);
				PSTorderPatientKeepAccounts accounts = (PSTorderPatientKeepAccounts)LocalServiceUtil.beanCastByJson(result.get(0),PSTorderPatientKeepAccounts.class);
				accounts.setId(StringUtil.isBlank(RecordFlow) ? UUIDUtils.getUUID32() : RecordFlow);
				accounts.setRecCreator("admin");
				accounts.setRecCreateTime(CalendarUtils.dateTimeFormat(new Date()));
				accounts.setFeeFn(feeFn);
				paramMap.put("sql","PSTorderPatientKeepAccounts.insert");
				paramMap.put("domain",accounts);
				EiInfo callInsertOperation = LocalServiceUtil.call("SSBMTy", "insertSqlByDomain", paramMap);*/
			}
		} catch (Exception e) {
			e.printStackTrace();
			inInfo.setStatus(-1);
			inInfo.set("obj", null);
		}
		return inInfo;
	}

	/**
	 * getMealBillDeptList根据单据创建人查询科室列表
	 *
	 * @param inInfo
	 * @return
	 * @Title: getMealBillDeptList
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:41:30
	 */
	public EiInfo getMealBillDeptList(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String userCode = StringUtil.toString(attr.get("userCode"));
		//查询订单主表
		List<HashMap<String, Object>> queryMealBillDept = dao.query("PSPCTmealOrderBillPatient.queryMealBillDept", userCode);
		inInfo.set("obj", queryMealBillDept);
		return inInfo;
	}


	/**
	 * queryFloor查询层信息
	 *
	 * @param inInfo
	 * @return
	 * @Title: queryFloor
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:41:43
	 */
	public EiInfo queryFloor(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		// 楼号
		String building = StringUtil.toString(attr.get("building"));
		// 院区
		String dataGroupCode = StringUtil.toString(attr.get("dataGroupCode"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("building", building);
		List<HashMap<String, Object>> result = dao.query("PSPCVdepartment01.queryFloor", building);
		if (result == null || result.size() < 1) {
			//没查到从字典表获取
			paramMap.put("mealgroupTypeCode", "floor");
//			/paramMap.put("paramValue1", dataGroupCode);
			result = dao.query("SSBMStxx01.getMealTypeData", paramMap);
			for (int i = 0; i < result.size(); i++) {
				HashMap<String, Object> hashMap = result.get(i);
				hashMap.put("floor", hashMap.get("typeCode"));
				hashMap.put("floorName", hashMap.get("typeName"));
			}
		}
		inInfo.set("obj", result);
		return inInfo;
	}

	/**
	 * queryQrFloor查询二维码层信息
	 *
	 * @param inInfo
	 * @return
	 * @Title: queryQrFloor
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:41:58
	 */
	public EiInfo queryQrFloor(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		// 食堂编号
		String building = StringUtil.toString(attr.get("building"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("building", building);
		List<HashMap<String, Object>> result = dao.query("SSBMCtm01.getQrFloor", paramMap);
		if (result == null || result.size() < 1) {
			//没查到从字典表获取
			paramMap.put("mealgroupTypeCode", "floor");
//			paramMap.put("paramValue1", dataGroupCode);
			result = dao.query("SSBMStxx01.getMealTypeData", paramMap);
			for (int i = 0; i < result.size(); i++) {
				HashMap<String, Object> hashMap = result.get(i);
				hashMap.put("floor", hashMap.get("typeCode"));
				hashMap.put("floorName", hashMap.get("typeName"));
			}
		}
		inInfo.set("obj", result);
		return inInfo;
	}

	/**
	 * queryQrAddr查询二维码地点信息
	 *
	 * @param inInfo
	 * @return
	 * @Title: queryQrAddr
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:42:16
	 */
	public EiInfo queryQrAddr(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		String addNum = StringUtil.toString(attr.get("addNum"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("addNum", addNum);
		//查询二维码地点表
		List<SSBMCtm01> result = dao.query("SSBMCtm01.query", paramMap);
		inInfo.set("obj", result);
		return inInfo;
	}

	/**
	 * queryQrDept查询二维码病区信息
	 *
	 * @param inInfo
	 * @return
	 * @Title: queryQrDept
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:42:43
	 */
	public EiInfo queryQrDept(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		String building = StringUtil.toString(attr.get("building"));
		String floor = StringUtil.toString(attr.get("floor"));
		String areaCode = StringUtil.toString(attr.get("areaCode"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("building", building);
		paramMap.put("floor", floor);
		List<HashMap<String, Object>> result = dao.query("SSBMCtm01.getQrDept", paramMap);
		if (result == null || result.size() < 1) {
			//没查到根据area_code获取
			paramMap.put("areaCode", areaCode);
			//查询床头码地点登记表
			result = dao.query("SSBMCtm01.getQrDeptByAreaCode", paramMap);
		}
		inInfo.set("obj", result);
		return inInfo;
	}

	/**
	 * queryDeptNum查询科室信息
	 *
	 * @param inInfo
	 * @return
	 * @Title: queryDeptNum
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:43:12
	 */
	public EiInfo queryDeptNum(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		// 食堂编号
		String floorNo = StringUtil.toString(attr.get("floorNo"));
		String layerNo = StringUtil.toString(attr.get("layerNo"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("floorNo", floorNo);
		paramMap.put("layerNo", layerNo);
		//通过楼和层查询科室信息视图
		List<HashMap<String, Object>> result = dao.query("PSPCVdepartment01.queryDeptByBuildAndFloor", paramMap);
		inInfo.set("obj", result);
		return inInfo;
	}

	/**
	 * queryMealPayType查询食堂支付类型
	 *
	 * @param inInfo
	 * @return
	 * @Title: queryMealPayType
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:43:29
	 */
	public EiInfo queryMealPayType(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 食堂编号
		String canteenType = StringUtil.toString(attr.get("canteenType"));
		String orally = StringUtil.toString(attr.get("orally"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("canteenType", canteenType);
		paramMap.put("orally", orally);
		//查询支付类型表
		List<HashMap<String, Object>> result = dao.query("PSPCTmealPayType.queryMealPayType", paramMap);
		inInfo.set("obj", result);
		return inInfo;
	}


	/**
	 * getEvalInfo查询菜品评价
	 *
	 * @param inInfo
	 * @return
	 * @Title: getEvalInfo
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:43:41
	 */
	public EiInfo getEvalInfo(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		ResultData result = new ResultData();
		String curPage = StringUtil.toString(attr.get("curPage"));
		String rows = StringUtil.toString(attr.get("rows"));
		String menuNum = StringUtil.toString(attr.get("menuNum"));

		boolean message = false;
		//组装查询条件
		int page = StringUtil.isEmpty(curPage) ? 1 : Integer.parseInt(curPage);
		int row = StringUtil.isEmpty(rows) ? 5 : Integer.parseInt(rows);

		int starts = (page - 1) * row;
		int ends = page * row;
		try {
			HashMap<String, Object> pMap = new HashMap<String, Object>();
			pMap.put("menuNum", menuNum);
			pMap.put("ends", ends);
			pMap.put("starts", starts);
			//查询订单明细表评价内容
			List<PSPCTmealOrderBillDetail> detail = dao.query("PSPCTmealOrderBillDetail.getEvalContent", pMap);
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
	 * 查询食堂评价类型
	 *
	 * @param inInfo
	 * @return
	 * @Title: getEvalTypeByCanteenNum
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:43:41
	 */
	public EiInfo getEvalTypeByCanteenNum(EiInfo inInfo) {
		ResultData result = new ResultData();
		try {
			HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
			//查询评价类型表
			List<PSPCTevalType> detail = dao.query("PSPCTevalType.query", attr);
			result.setObj(detail);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		inInfo.set("result", result);
		return inInfo;
	}

	/**
	 * getMenuDetail查询菜品详情
	 * 1.查询菜品信息
	 * 2.构建菜品详情信息
	 *
	 * @param inInfo
	 * @return
	 * @Title: getMenuDetail
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:47:18
	 */
	public EiInfo getMenuDetail(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		ResultData resultData = new ResultData();
		// 菜品编码
		String menuNum = StringUtil.toString(attr.get("menuNum"));

		List<SSBMCpxx02> result2 = null;
		List<SSBMTyTmealPic> result3 = null;
		String c = "";
		HashMap<String, Object> map = new HashMap<String, Object>();

		boolean message = false;
		/** 1.查询菜品信息*/
		List<PSPCTmealOrderMenu> result = dao.query("PSPCTmealOrderMenu.queryMenuDetail", menuNum);
		if (result.size() > 0) {
			for (PSPCTmealOrderMenu mt : result) {
				map.put("mealId", mt.getMenuId());
				//查询图片
				result3 = dao.query("SSBMTyTmealPic.query", map);
				if (result3 != null && result3.size() > 0) {
					for (SSBMTyTmealPic pl : result3) {
						pl.setFileUrl(FileUtil.fileToBase64(pl.getFileUrl()));
					}
					mt.setPicList(result3);
				}
			}
			//查询菜品明细
			result2 = dao.query("SSBMCpxx02.getdescptionInfo", result.get(0).getMenuId());
			/**2.构建菜品详情信息*/
			for (SSBMCpxx02 li : result2) {
				//判断菜品材料是否为空
				if (li.getMaterial() != null && (!("").equals(li.getMaterial()))) {
					c += li.getMaterial();
					if (("").equals(li.getQuantum()) && ("").equals(li.getDescription())) {
						c += ";";
					}
				}
				//判断菜品份量是否为空
				if (li.getQuantum() != null && (!("").equals(li.getQuantum()))) {
					c += li.getQuantum();
					if (li.getDescription() == null || (("").equals(li.getDescription()))) {
						c = c + ";";
					}
				}
				//判断菜品描述是否为空
				if (li.getDescription() != null && (!(("").equals(li.getDescription())))) {
					c += li.getDescription() + ";";
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
	 * queryMenuInfo查询食堂菜品信息 , 无分页, 图片格式为地址
	 * 1.获取传参
	 * 2.如果是当前日期做餐类查询限制
	 * 3.获取图片地址
	 *
	 * @param inInfo
	 * @return
	 * @Title: queryMenuInfo
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:48:35
	 */
	public EiInfo queryMenuInfo(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		ResultData resultData = new ResultData();
		try {
			//真实路径
			String savaPath = request.getSession().getServletContext().getRealPath("") + "mealPicUpload" + File.separatorChar;
			FileUtil.createDir(savaPath);

			/** 1.获取传参*/
			String mealType = StringUtil.toString(attr.get("mealType"));
			String dayFlag = StringUtil.toString(attr.get("dayFlag"));
			String mealNum = StringUtil.toString(attr.get("mealNum"));
			String canteenNum = StringUtil.toString(attr.get("canteenNum"));
			String orally = StringUtil.toString(attr.get("orally"));

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mealNum", mealNum);
			paramMap.put("canteenNum", canteenNum);
			paramMap.put("mealType", mealType);
			paramMap.put("dayFlag", dayFlag);
			//校验查询条件是否为空
			if (StringUtils.isBlank(dayFlag) || StringUtils.isBlank(mealNum) || StringUtils.isBlank(canteenNum)) {
				resultData.setSuccess(false);
				resultData.setRespMsg("查询菜品时参数错误");
				resultData.setObj(null);
				resultData.setTotal(0);
			} else {
				List<PSPCTmealOrderMenu> result = null;
				int total = 0;
				Date nowDate = new Date();

				String today = CalendarUtils.dateFormat(nowDate);
				Calendar cd = Calendar.getInstance();

				paramMap.put("operateCode", "meal");//菜品所属类型
				/** 2.如果是当前日期做餐类查询限制*/
				if (dayFlag.equals("D") || dayFlag.equals(CalendarUtils.getDateByFormat(CalendarUtils.DateyMd))) {
					Map<String, String> pMap = new HashMap<String, String>();
					pMap.put("canteenNum", canteenNum);
					//查询订餐时间表
					List<SSBMDcsj01> canteenTimesInfo = dao.query("SSBMDcsj01.query", pMap);
					if (!canteenTimesInfo.isEmpty()) {
						for (SSBMDcsj01 canteenTime : canteenTimesInfo) {
							if (paramMap.get("mealNum").toString().equals(canteenTime.getMealNum())) {
								String todayMealTypeCode = canteenTime.getTodayMealTypeCode();
								if (!StringUtils.isEmpty(todayMealTypeCode)) {
									paramMap.put("todayMealTypeCode", canteenTime.getTodayMealTypeCode());//今日可点菜品分类
								}
							}
						}
					}
				}
				//默认菜品数据查询
				if (dayFlag.equals("D")) {
					paramMap.put("operateName", "普餐");
					paramMap.put("mealData", today);
					//查询订单明细表
					result = dao.query("PSPCTmealOrderMenu.queryMenuTimeInfoByApp", paramMap);
					total = dao.count("PSPCTmealOrderMenu.queryCount", paramMap);
					resultData.setSuccess(true);
				} else if (dayFlag.equals("Y")) {
					paramMap.put("operateName", "普餐");
					cd.add(cd.DATE, 1);
					paramMap.put("mealData", CalendarUtils.dateFormat(cd.getTime()));
					//查询订单明细表
					result = dao.query("PSPCTmealOrderMenu.queryMenuTimeInfoByApp", paramMap);
					total = dao.count("PSPCTmealOrderMenu.queryCount", paramMap);
					resultData.setSuccess(true);
				} else {
					paramMap.put("mealData", dayFlag);
					//查询订单明细表
					result = dao.query("PSPCTmealOrderMenu.queryMenuTimeInfoByApp", paramMap);
					total = dao.count("PSPCTmealOrderMenu.queryCount", paramMap);
					resultData.setSuccess(true);
				}
				//图片查询
				if (null != result && result.size() > 0) {
					for (PSPCTmealOrderMenu mt : result) {
						paramMap.put("mealId", mt.getMenuId());
						List<SSBMTyTmealPic> list = dao.query("SSBMTyTmealPic.query", paramMap);
						if (list != null && list.size() > 0) {
							for (SSBMTyTmealPic pl : list) {
								if (StringUtil.isNotEmpty(pl.getFileUrl())) {
									/**3.获取图片地址*/
									//移动端加载图片会比较慢，把图片文件复制到项目路径下，以便APP通过url地址直接展示，而不用通过base64
									File file = new File(pl.getFileUrl());
									String copyPic = FileUtil.copyPic(savaPath + pl.getFileId(), file);
									if (!StringUtil.isBlank(copyPic)) {
										mt.setMenuPicUrl("mealPicUpload" + File.separator + pl.getFileId());
									} else {
										mt.setMenuPicUrl("");
									}
									//mt.setMenuPicUrl(FileUtil.fileToBase64(pl.getFileUrl()));
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
		} catch (Exception e) {
			e.printStackTrace();
			resultData.setSuccess(false);
			resultData.setRespMsg(e.getMessage());
		}
		inInfo.setStatus(resultData.isSuccess() ? 1 : -1);
		inInfo.set("result", resultData);
		return inInfo;
	}

	/**
	 * queryPaginationMenu分页加载菜品数据
	 * 1.前端查询菜品信息时，根据类型进行区分，APP端查询的单价为折后价，POS的为原价
	 * 2.POS机查询时不传图片
	 *
	 * @param inInfo
	 * @return
	 * @Title: queryPaginationMenu
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:50:01
	 */
	public EiInfo queryPaginationMenu(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 食堂编号
		String dayFlag = StringUtil.toString(attr.get("dayFlag"));
		String mealNum = StringUtil.toString(attr.get("mealNum"));
		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		String mealType = StringUtil.toString(attr.get("mealType"));
		String deviceType = StringUtil.toString(attr.get("deviceType"));
		ResultData resultData = new ResultData();
		List<PSPCTmealOrderMenu> result = null;
		String temp = "";
		Map<String, Object> map0 = new HashMap<String, Object>();
		int total = 0;
		/**当前页数 */
		int page = 1;
		/** 每页条数 */
		int rows = 10;
		int startRow = 0;
		//当前页数
		if (StringUtil.isNotEmpty(StringUtil.toString(attr.get("page")))) {
			page = Integer.parseInt(StringUtil.toString(attr.get("page")));
		}

		if ("pos".equals(deviceType)) {
			rows = 1000;
		}
		if (StringUtil.isBlank(dayFlag)
				|| StringUtil.isBlank(mealNum)
				|| StringUtil.isBlank(canteenNum)
				|| StringUtil.isBlank(StringUtil.toString(attr.get("page")))) {
			resultData.setSuccess(false);
			resultData.setRespMsg("查询菜品时参数错误");
			resultData.setObj("");
		} else {
			startRow = (page - 1) * rows;
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
			//判断订餐类型
			if (dayFlag.equals("D")) {
				map.put("operateName", "普餐");
				map.put("mealData", today);
			} else if (dayFlag.equals("Y")) {
				map.put("operateName", "普餐");
				cd.add(cd.DATE, 1);
				map.put("mealData", CalendarUtils.dateFormat(cd.getTime()));
			} else {
				if (dayFlag.length() == 5) {
					map.put("mealData", CalendarUtils.getYear(true) + "-" + dayFlag);
				} else {
					map.put("mealData", dayFlag);
				}
			}

			/**前端查询菜品信息时，根据类型进行区分，APP端查询的单价为折后价，POS的为原价*/
			if (StringUtils.isNotBlank(deviceType) && "pos".equals(deviceType)) {
				//POS订餐菜品查询
				result = dao.query("PSPCTmealOrderMenu.queryMenuTimeInfoByPos", map);
			} else {
				//默认菜品数据查询
				result = dao.query("PSPCTmealOrderMenu.queryMenuTimeInfoByApp", map);
			}

			total = dao.count("PSPCTmealOrderMenu.queryCount", map);
			/**2.POS机查询时不传图片*/
			if ("pos".equals(deviceType)) {

			} else {
				//其他查询获取图片
				if (null != result && result.size() > 0) {
					for (PSPCTmealOrderMenu mt : result) {
						map.put("mealId", mt.getMenuId());
						//查询菜品图片表
						List<SSBMTyTmealPic> list = dao.query("SSBMTyTmealPic.query", map);
						if (list != null && list.size() > 0) {
							for (SSBMTyTmealPic pl : list) {
								if (StringUtil.isNotEmpty(pl.getFileUrl())) {
									//构建图片字符串
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
	 * getDateList获取可订餐日期
	 * 获取数据字典中订餐的最大订餐天数
	 *
	 * @param inInfo
	 * @return
	 * @Title: getDateList
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:51:10
	 */
	public EiInfo getDateList(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 食堂编号
		String dataGroupCode = StringUtil.toString(attr.get("dataGroupCode"));
		String canteenType = StringUtil.toString(attr.get("canteenType"));

		int mealMaxDay = 5;
		String dataGroup = "";
		// 获取数据字典中订餐的最大订餐天数
		if (StringUtil.isNotEmpty(canteenType) && "zgst".equals(canteenType)) {
			dataGroup = "workNumDays";
		} else {
			dataGroup = "patiNumDays";
		}
		List<SSBMSjzd02> types = dao.query("SSBMSjzd02.queryTypeValue", dataGroup);
		if (!types.isEmpty()) {
			SSBMSjzd02 type = types.get(0);
			String objType = type.getTypecode();
			if (StringUtil.isNotEmpty(objType)) {
				mealMaxDay = Integer.parseInt(objType.split("#")[0]);
			}
		}
		//按日期长度获取一段时间内的日期集合
		List<String> dateCalender = CalendarUtils.getDateCalender(mealMaxDay, CalendarUtils.DateyMd, true);

		inInfo.set("obj", dateCalender);
		return inInfo;
	}

	/**
	 * getMealType获取菜品分类
	 *
	 * @param inInfo
	 * @return
	 * @Title: getMealType
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:52:11
	 */
	public EiInfo getMealType(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 食堂编号
		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("spuerMealTypeNum", canteenNum);
		//查询菜品分类表
		List<SSBMCpfl01> patient = dao.query("SSBMCpfl01.query", paramMap);
		inInfo.set("obj", patient);
		return inInfo;
	}

	/**
	 * getMealType根据医嘱获取菜品分类
	 *
	 * @param inInfo
	 * @return
	 * @Title: getMealType
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:52:11
	 */
	public EiInfo getMealTypeByDiet(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 食堂编号
		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		String diet = StringUtil.toString(attr.get("diet"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("spuerMealTypeNum", canteenNum);
		paramMap.put("diet", diet);
		//查询菜品分类表
		List<SSBMCpfl01> patient = dao.query("SSBMCpfl01.queryByDiet", paramMap);
		inInfo.set("obj", patient);
		return inInfo;
	}


	/**
	 * getCanteen获取食堂列表
	 * 1.根据食堂类型获取楼信息配置
	 * 1.1 病患食堂bhst 获取床头码配置 ;没查到,则获取数据字典配置
	 * 1.2 职工食堂zgst 从送餐地点配置获取楼信息 ;没查到,获取数据字典配置
	 * 2.获取订餐日期
	 * 2.1 查询餐类
	 * 2.2 查询食堂订餐时间限制, 格式化送餐时间
	 * 3.从小代码查询食堂最低起送费
	 * 4.从小代码查询食堂每单配送费
	 *
	 * @param inInfo
	 * @return
	 * @Title: getCanteen
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:52:27
	 */
	public EiInfo getCanteen(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 食堂类型
		String canteenTypeNum = StringUtil.toString(attr.get("canteenTypeNum"));
		// 账套
		String dataGroupCode = StringUtil.toString(attr.get("dataGroupCode"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("canteenTypeNum", canteenTypeNum);
		paramMap.put("dataGroupCode", dataGroupCode);
		//查询食堂表
		List<SSBMStxx01> canteens = dao.query("SSBMStxx01.queryCanteenInfoGd", paramMap);
		List<SSBMDcsj01> canteenTimesInfo = null;
		List<SSBMCpfl01> resultType = null;
		List<SSBMDcsj01> resultSend = null;
		List<HashMap<String, String>> buildConfig = new ArrayList<HashMap<String, String>>();
		/**1.查询楼信息配置*/
		if ("bhst".equals(canteenTypeNum)) {
			/** 1.1病患食堂 获取床头码配置*/
			paramMap.put("areaCode", dataGroupCode);
			buildConfig = dao.query("SSBMCtm01.getQrBuilding", paramMap);
			if (buildConfig.isEmpty()) {
				//没查到,获取数据字典配置
				paramMap.put("mealgroupTypeCode", "building");
				paramMap.put("paramValue1", dataGroupCode);
				buildConfig = dao.query("SSBMStxx01.getMealTypeData", paramMap);
			}
		} else if ("zgst".equals(canteenTypeNum)) {
			/**1.2职工食堂 从送餐地点配置获取楼信息*/
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("dataGroupCode", dataGroupCode);
			//没查到,获取数据字典配置
			paramMap.put("mealgroupTypeCode", "building");
			paramMap.put("paramValue1", dataGroupCode);
			//根据院区获取地点信息已关闭，从送餐地点配置表查询
			//buildConfig = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		}
		/**2.获取订餐日期*/
		List<String> patiOrderDate = canteenOrderDate();
		for (int i = 0; i < canteens.size(); i++) {
			SSBMStxx01 canteenInfo = canteens.get(i);
			canteenInfo.setPatiOrderDate(patiOrderDate);
			//食堂楼信息,从送餐地点配置表查询
			paramMap.put("canteenNum", canteenInfo.getCanteenNum());
			buildConfig = dao.query("SSBMSCDD01.queryBuildingByCanteen", paramMap);
			canteenInfo.setBuildConfig(buildConfig);
			paramMap.put("canteenNum", canteenInfo.getCanteenNum());
			/**2.1查询餐类*/
			resultType = dao.query("SSBMCpfl01.query", paramMap);
			canteenInfo.setMealType(resultType);
			/*if(resultType == null || resultType.isEmpty()){
				canteens.remove(i);
				i--;
				continue;
			}*/

			/**2.2查询食堂订餐时间限制*/
			paramMap.put("canteenNum", canteenInfo.getCanteenNum());
			canteenTimesInfo = dao.query("SSBMDcsj01.query", paramMap);
			canteenInfo.setCanteenTimesInfo(canteenTimesInfo);

			//没有配置送餐时间的食堂直接跳过
			if (canteenTimesInfo == null || canteenTimesInfo.isEmpty()) {
				canteens.remove(i);
				i--;
				continue;
			}
			//格式化送餐时间
			resultSend = new ArrayList<SSBMDcsj01>();
			if (canteenTimesInfo.size() > 0) {
				for (SSBMDcsj01 ms : canteenTimesInfo) {
					String sendTime = ms.getSendTime();
					String mealTime = ms.getMealNum();
					if (sendTime.contains(",")) {
						//list.remove(ms);
						//拼接送餐时间
						String[] st = sendTime.split(",");
						for (String string : st) {
							SSBMDcsj01 menuSendTime = new SSBMDcsj01(mealTime, string);
							resultSend.add(menuSendTime);
						}
					} else {
						resultSend.add(ms);
					}
				}
			}
			canteenInfo.setSendTime(resultSend);
			/***3.从小代码查询食堂最低起送费*/
			EiInfo callSendFee = LocalServiceUtil.callCode1("S_ED_02", "wilp.sc.st.sendFee", canteenInfo.getCanteenNum());
			List<HashMap<String, Object>> listSendFee = (List<HashMap<String, Object>>) callSendFee.get("list");
			if (listSendFee != null && listSendFee.size() > 0) {
				HashMap<String, Object> hashMap = listSendFee.get(0);
				String lable = StringUtil.toString(hashMap.get("label"));
				canteenInfo.setSendFee(Integer.parseInt(lable));
			}
			/**4.从小代码查询食堂每单配送费*/
			EiInfo callShipFee = LocalServiceUtil.callCode1("S_ED_02", "wilp.sc.st.shipFee", canteenInfo.getCanteenNum());
			List<HashMap<String, Object>> listShipFee = (List<HashMap<String, Object>>) callShipFee.get("list");
			if (listShipFee != null && listShipFee.size() > 0) {
				HashMap<String, Object> hashMap = listShipFee.get(0);
				String lable = StringUtil.toString(hashMap.get("label"));
				canteenInfo.setShipFee(Integer.parseInt(lable));
			}
		}

		inInfo.set("obj", canteens);
		return inInfo;
	}

	/**
	 * canteenOrderDate获取食堂订餐日期
	 *
	 * @return
	 * @Title: canteenOrderDate
	 * @return: List<String>
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:55:18
	 */
	public List<String> canteenOrderDate() {
		List<String> lst = new ArrayList<String>();
		List<String> lst2 = new ArrayList<String>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		//patiNumDays:订餐时间
		paramMap.put("mealgroupTypeCode", "patiNumDays");
		//查询字典表
		List<HashMap<String, Object>> listNumDays = dao.query("SSBMStxx01.getMealTypeData", paramMap);

		String strDays = "";
		int numDay = 0;
		int startDay = 0;

		//默认天数
		if (listNumDays == null || listNumDays.size() == 0) {
			numDay = 3;
			startDay = 0;
		} else {
			strDays = StringUtil.toString(listNumDays.get(0).get("typeCode"));
			String[] bnArray = strDays.split("#");
			if (bnArray.length == 1) {
				numDay = Integer.parseInt(bnArray[0]);
				startDay = 0;
			} else if (bnArray.length == 2) {
				numDay = Integer.parseInt(bnArray[0]);
				startDay = Integer.parseInt(bnArray[1]);
			} else {
				numDay = 3;
				startDay = 0;
			}
		}
		//整理日期集合
		lst = CalendarUtils.dateDataListAndYear(numDay, startDay);
		for (String str : lst) {
			String s = str.replace("/", "-");
			lst2.add(s);
		}
		return lst2;
	}

	/**
	 * getCanteenList获取食堂列表
	 *
	 * @param eiInfo
	 * @return
	 * @Title: getCanteenList
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:57:33
	 */
	public EiInfo getCanteenList(EiInfo eiInfo) {
		EiInfo inInfo = new EiInfo();
		HashMap<String, Object> attr = (HashMap<String, Object>) eiInfo.getAttr().get("paramObject");
		String wardCode = StringUtil.toString(attr.get("wardCode"));
		System.out.println(wardCode);
		//从食堂信息表中查询数据
		List<HashMap<String, String>> lst = dao.query("SSBMStxx01.queryCanteenFromWardCode", wardCode);
		inInfo.addBlock("canteenList").addRows(lst);

		inInfo.set("obj", lst);
		return inInfo;
	}

	/**
	 * queryBilld查询该角色是否在次餐次下过单
	 *
	 * @param inInfo
	 * @return
	 * @Title: queryBilld
	 * @return: EiInfo
	 * @author: keke
	 * @date: 2022年6月30日 上午10:41:43
	 */
	public EiInfo queryBilld(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		// 楼号
		String userName = StringUtil.toString(attr.get("userName"));
		// 院区
		String needDate = StringUtil.toString(attr.get("needDate"));
		String bedNo = StringUtil.toString(attr.get("bedNo"));
		String roleNum = StringUtil.toString(attr.get("roleNum"));
		String mealNum = StringUtil.toString(attr.get("mealNum"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", userName);
		paramMap.put("needDate", needDate);
		paramMap.put("bedNo", bedNo);
		paramMap.put("roleNum", roleNum);
		paramMap.put("mealNum", mealNum);
		List<HashMap<String, Object>> result = dao.query("PSPCVdepartment01.queryBilld", paramMap);
		if (result.isEmpty()) {
			inInfo.setStatus(0);
			inInfo.set("obj", "0");
			return inInfo;
		}
		inInfo.setStatus(1);
		inInfo.set("obj", "1");
		return inInfo;
	}

	/**
	 * queryBuildAndFloor查询层信息
	 *
	 * @param inInfo
	 * @return
	 * @Title: queryBuildAndFloor
	 * @return: EiInfo
	 * @author: keke
	 * @date: 2021年9月9日 上午10:41:43
	 */
	public EiInfo queryBuildAndFloor(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

		// 楼号
		String deptNum = StringUtil.toString(attr.get("deptNum"));
		// 院区
//		String dataGroupCode = StringUtil.toString(attr.get("dataGroupCode"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("deptNum", deptNum);
		List<HashMap<String, Object>> result = dao.query("PSPCVdepartment01.queryBuildAndFloor", deptNum);
		if (result == null || result.size() < 1) {
			inInfo.setStatus(-1);
			inInfo.set("obj", "-1");
		}
		inInfo.set("obj", result);
		return inInfo;
	}

	/**
	 * @description：获取食堂菜品限制数
	 * @author:李俊俊
	 * @parms:canteenCode 食堂编码
	 * @time：2022-8-17 return eiInfo
	 */
	public EiInfo getLimitNum(EiInfo info) {
		HashMap<String, Object> attr = (HashMap<String, Object>) info.getAttr().get("paramObject");
		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		String dataGroup = StringUtil.toString(attr.get("dataGroup"));
		Map<String, Object> map = new HashMap<>();
		map.put("canteenCode", canteenNum);
		map.put("dataGroup", dataGroup);
		List<Map<String, Object>> list = dao.query("PSPCDCXX01.queryLimitNum", map);
		if (list == null || list.size() < 1) {
			info.setStatus(-1);
			info.set("obj", "-1");
		}
		info.set("obj", list);
		return info;
	}

	/**
	 * @description：获取配送费
	 * @author:李俊俊
	 * @parms:canteenCode 食堂编码
	 * @time：2022-8-17 return eiInfo
	 */
	public EiInfo getShipFee(EiInfo info) {
		HashMap<String, Object> attr = (HashMap<String, Object>) info.getAttr().get("paramObject");
		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		String dataGroup = StringUtil.toString(attr.get("dataGroup"));
		Map<String, Object> map = new HashMap<>();
		map.put("canteenCode", canteenNum);
		map.put("dataGroup", dataGroup);
		List<Map<String, Object>> list = dao.query("PSPCDCXX01.queryShipFee", map);
		if (list == null || list.size() < 1) {
			info.setStatus(-1);
			info.set("obj", "-1");
		}
		info.set("obj", list);
		return info;
	}

	/**
	 * @description：检查该病患是否存在
	 * @author:kwr
	 * @parms:
	 * @time：2022-8-17 return eiInfo
	 */
	public EiInfo checkCardInfo(EiInfo info) throws DocumentException {
		HashMap<String, Object> attr = (HashMap<String, Object>) info.getAttr().get("paramObject");
		String cardNum = StringUtil.toString(attr.get("cardNum"));
		String name = StringUtil.toString(attr.get("name"));

		Map<String, Object> map = new HashMap<>();
		map.put("cardNum", cardNum);
		map.put("name", name);

		dao.insert("PSPCWeChat.insert",map);


		String url  = GET_PA_INFO;

		String xml = getXML(cardNum, name);

		Map<String, String> pMap = new HashMap<>();
		pMap.put("msg", xml);

		HttpClient client = new HttpClient();
		PostMethod httpPost = new PostMethod(url);
		httpPost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		String result = null;
		for (Map.Entry<String, String> entry : pMap.entrySet()) {
			httpPost.addParameter(entry.getKey(), entry.getValue());
		}
		try {
			client.executeMethod(httpPost);
			result = new String(httpPost.getResponseBody(), StandardCharsets.UTF_8);
			System.out.println("HttpClient中的result：" + new String(httpPost.getResponseBody(), StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
			((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown();
		}

		//对于特殊字符去转义
		result = StringEscapeUtils.unescapeHtml(result);
		//json类型数据
		Document doc = DocumentHelper.parseText(result.toString());
		JSONObject jsonObject = new JSONObject();
		dom4j2Json(doc.getRootElement(), jsonObject);
		System.out.println("jsonObject = " + jsonObject);
		info.set("obj",jsonObject);
		return info;
	}

	/**
	 * 根据就诊卡号、姓名来获取该患者是否属于该医院
	 * @param
	 * @return
	 */
	public static String getXML(String cardNum,String name){

		String soapXML ="<Message id='161' code='S0004-001' name='CheckCardInfo' appid='70'>"
				+"<REQUEST>"
				+"<CARD_NUMBER>"+cardNum+"</CARD_NUMBER>"
				+"<PATIENT_NAME>"+name+"</PATIENT_NAME>"
				+"</REQUEST>"
				+"</Message>";
		return soapXML;
	}

	/**
	 * @description：检查该病患是否是住院病人，是的话获取床位号等信息
	 * @author:kwr
	 * @parms:
	 * @time：2022-8-17 return eiInfo
	 */
	public EiInfo inPatientInfoQuery(EiInfo info) throws DocumentException {
		HashMap<String, Object> attr = (HashMap<String, Object>) info.getAttr().get("paramObject");
		String fileNum = StringUtil.toString(attr.get("fileNum"));


//		String url = "http://199.168.200.136/esbsvc.asmx/EsbApi";
//		String url = "https://his.api.mzrmyy.com/api.asmx/EsbApi";
//		String url = "http://192.168.200.135:1330/esbsvc.asmx/EsbApi";
		String url  = GET_PA_INFO;

		String xml = getXMLs(fileNum);

		Map<String, String> pMap = new HashMap<>();
		pMap.put("msg", xml);

		HttpClient client = new HttpClient();
		PostMethod httpPost = new PostMethod(url);
		httpPost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		String result = null;
		for (Map.Entry<String, String> entry : pMap.entrySet()) {
			httpPost.addParameter(entry.getKey(), entry.getValue());
		}
		try {
			client.executeMethod(httpPost);
			result = new String(httpPost.getResponseBody(), StandardCharsets.UTF_8);
			System.out.println("HttpClient中的result：" + new String(httpPost.getResponseBody(), StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
			((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown();
		}

		//对于特殊字符去转义
		result = StringEscapeUtils.unescapeHtml(result);
		//json类型数据
		Document doc = DocumentHelper.parseText(result.toString());
		JSONObject jsonObject = new JSONObject();
		dom4j2Json(doc.getRootElement(), jsonObject);
		System.out.println("jsonObject = " + jsonObject);
		info.set("obj",jsonObject);
		return info;
	}

	/**
	 * 根据就诊卡号、姓名来获取该患者是否属于该医院
	 * @param
	 * @return
	 */
	public static String getXMLs(String fileNum){

		String soapXML ="<Message id='308' code='S0028-001' name='InPatientInfoQuery-Visiting' appid='70'>"
				+"<REQUEST>"
				+"<FILE_NUMBER>"+fileNum+"</FILE_NUMBER>"
				+"<CARD_NUMBER></CARD_NUMBER>"
				+"<REG_NUMBER></REG_NUMBER>"
				+"</REQUEST>"
				+"</Message>";
		return soapXML;
	}

	/**
	 * xml转json
	 *
	 * @param element
	 * @param json
	 */
	public static void dom4j2Json(Element element, JSONObject json) {
		//如果是属性
		for (Object o : element.attributes()) {
			Attribute attr = (Attribute) o;
			if (!attr.getValue().isEmpty()) {
				json.put("@" + attr.getName(), attr.getValue());
			}
		}
		List<Element> chdEl = element.elements();
		if (chdEl.isEmpty() && !element.getText().isEmpty()) {
			//如果没有子元素,只有一个值
			json.put(element.getName(), element.getText());
		}

		for (Element e : chdEl) {
			//有子元素
			if (!e.elements().isEmpty()) {
				//子元素也有子元素
				JSONObject chdjson = new JSONObject();
				dom4j2Json(e, chdjson);
				Object o = json.get(e.getName());
				if (o != null) {
					JSONArray jsona = null;
					if (o instanceof JSONObject) {
						//如果此元素已存在,则转为jsonArray
						JSONObject jsono = (JSONObject) o;
						json.remove(e.getName());
						jsona = new JSONArray();
						jsona.add(jsono);
						jsona.add(chdjson);
					}
					if (o instanceof JSONArray) {
						jsona = (JSONArray) o;
						jsona.add(chdjson);
					}
					json.put(e.getName(), jsona);
				} else {
					if (!chdjson.isEmpty()) {
						json.put(e.getName(), chdjson);
					}
				}
			} else {
				//子元素没有子元素
				for (Object o : element.attributes()) {
					Attribute attr = (Attribute) o;
					if (!attr.getValue().isEmpty()) {
						json.put("@" + attr.getName(), attr.getValue());
					}
				}
				if (!e.getText().isEmpty()) {
					json.put(e.getName(), e.getText());
				}
			}
		}
	}


}
