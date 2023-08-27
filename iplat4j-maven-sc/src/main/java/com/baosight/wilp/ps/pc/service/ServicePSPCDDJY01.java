package com.baosight.wilp.ps.pc.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ps.bm.domain.PSBMDdgl01;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOrderBillDetail;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOrderBillPatient;
import com.baosight.wilp.ss.ac.domain.SSACTscWorkOrderBillDetail;
import com.baosight.wilp.ss.bm.domain.SSBMCppb03;
import com.baosight.wilp.ss.bm.domain.SSBMDcsj01;
import com.baosight.wilp.ss.bm.utils.CalendarUtils;
import com.baosight.wilp.ss.bm.utils.LocalServiceUtil;
import com.baosight.wilp.ss.bm.utils.ResultData;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 
 *  ServicePSPCDDJY01 订单校验service
 * 
 * @Title: ServicePSPCDDJY01.java
 * @ClassName: ServicePSPCDDJY01
 * @Package：com.baosight.wilp.ps.pc.service
 * @author: liutao
 * @date: 2021年9月9日 上午11:16:01
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePSPCDDJY01 extends ServiceBase {

    /**
     * 查询订单信息
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "PSBMDdgl01.query", new PSBMDdgl01());

		return outInfo;
	}


	/**
	 * 
	 * 菜品展示校验
	 *
	 * @Title: checkAbleMenu 
	 * @param inInfo
	 * @return
	 * @throws Exception 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 上午11:19:54
	 */
	public EiInfo checkAbleMenu(EiInfo inInfo) throws Exception{
		ResultData resultData = new ResultData();
		
		return inInfo;
	}
	

	/**
	 * 
	 * 校验金额
	 * 1.计算总价
	 * 2.查询食堂是否启用最低价配置  1表示该食堂启用此配置项,0表示不启用
	 * 3.当食堂开启配置项,且订单不满足最低价时才会在订单价格的基础上加上配送费,使之成为订单总价
	 * @Title: checkPrice 
	 * @param inInfo
	 * @return
	 * @throws Exception 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 上午11:21:14
	 */
	public EiInfo checkPrice(EiInfo inInfo) throws Exception{
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		PSPCTmealOrderBillPatient mealOrderBill = (PSPCTmealOrderBillPatient) attr.get("mainOrder");
		List<PSPCTmealOrderBillDetail> details = (List<PSPCTmealOrderBillDetail>) attr.get("details");
		EiInfo outInfo = new EiInfo();
		if (details.size() == 0) {
			outInfo.set("success", false);
			outInfo.set("respMsg", "菜品未选择，请重新选择!");
			return outInfo;
		} else {
			String canteenNum = mealOrderBill.getCanteenNum();
			/**1.计算总价*/
			BigDecimal bd = machMenuTotalPrice(details,mealOrderBill.getNeedDate(),mealOrderBill.getMealNum(),canteenNum);
			//精确到小数点后两位
			DecimalFormat df = new DecimalFormat("#0.00");
			String format = df.format(bd);
			BigDecimal totalPriceParam = new BigDecimal(format);
			BigDecimal detailDecimal = new BigDecimal(0);
			for (PSPCTmealOrderBillDetail detail : details) {
				SSBMCppb03 mti = getMenuInfoByMenuNum(detail.getMenuNum(),mealOrderBill.getNeedDate(),mealOrderBill.getMealNum(),canteenNum);
				
				if( mti.getSurplus()==0 || mti.getSurplus()<detail.getMenuNumber() ){
					outInfo.set("success", false);
					outInfo.set("respMsg", "所选菜品还剩 " + mti.getSurplus() +"份，请重新选择!");
					return outInfo;
				}
				//菜品明细价格的总和
				detailDecimal = detailDecimal.add(detail.getMenuTotalPrice());
			}

			/**2.查询食堂是否启用最低价配置  1表示该食堂启用此配置项,0表示不启用*/
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("typeGroupCode", "canteenMinPrice");
			map.put("typename", canteenNum);
			map.put("typecode", "1");
			//查询字典表获取配置项
			HashMap<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sql","PSPCTmealType.getTypeCodeFromDictionary");
			paramMap.put("map",map);
			EiInfo call = LocalServiceUtil.call("SSBMTy", "querySqlByMap", paramMap);
			List<HashMap<String, String>> list = (List<HashMap<String, String>>) call.get("result");
			//默认食堂不启用最低价配置
			boolean minCof =false;
			String minPrice="0";
			String transFee="0";

			if(!list.isEmpty()){
				minCof=true;
				//此食堂的订单最低价
				minPrice = list.get(0).get("paramValue1");
				//配送费用
				transFee = list.get(0).get("paramValue2");
			}
			BigDecimal fe1 = new BigDecimal(minPrice);
			BigDecimal fe2 = new BigDecimal(transFee);
			/**3.当食堂开启配置项,且订单不满足最低价时才会在订单价格的基础上加上配送费,使之成为订单总价*/
			if(totalPriceParam.subtract(fe1).compareTo(new BigDecimal(0))<0 && minCof){
				mealOrderBill.setTransFee(fe2);
				detailDecimal = detailDecimal.add(fe2);
				if(detailDecimal.subtract(totalPriceParam).compareTo(new BigDecimal(0)) != 0 ){
					outInfo.set("success", false);
					outInfo.set("respMsg", "总价与菜品详情金额不符，请检查！");
					return inInfo;
				}
			}else{
				if(detailDecimal.subtract(totalPriceParam).compareTo(new BigDecimal(0)) != 0 ){
					outInfo.set("success", false);
					outInfo.set("respMsg", "总价与菜品详情金额不符，请检查！");
					return outInfo;
				}
			}
			System.out.println("totalPriceParam:"+totalPriceParam+"detailDecimal:"+detailDecimal);
		}
		outInfo.set("success", true);
		outInfo.set("respMsg", "金额校验通过！");
		return outInfo;
	}
	
	/**
	 * 
	 *计算菜品总价
	 *
	 * @Title: machMenuTotalPrice 
	 * @param details
	 * @param needDate
	 * @param mealNum
	 * @param canteenNum
	 * @return 
	 * @return: BigDecimal 
	 * @author: liutao
	 * @date: 2021年9月9日 上午11:27:03
	 */
	public BigDecimal machMenuTotalPrice(List<PSPCTmealOrderBillDetail> details,String needDate,String mealNum,String canteenNum) {
		//菜品总价
		BigDecimal totalPrice_1 = new BigDecimal("0");
		for (int i = 0; i < details.size(); i++) {
			PSPCTmealOrderBillDetail detail = details.get(i);
			SSBMCppb03 mti = getMenuInfoByMenuNum(detail.getMenuNum(),needDate,mealNum,canteenNum);
			if(!StringUtils.isEmpty(mti.getId())){
				//菜品折后价
				BigDecimal priceAfterDiscount = mti.getPriceAfterDiscount();
				//数量
				BigDecimal bigNum = new BigDecimal(detail.getMenuNumber());
				//总价
				BigDecimal totalPrice = priceAfterDiscount.multiply(bigNum);
				totalPrice_1 = totalPrice_1.add(totalPrice);
			}
		}
		return totalPrice_1;
	}
	
	/**
	 *   获取餐次信息
	 *
	 * @Title: getMenuInfoByMenuNum 
	 * @param menuNum
	 * @param needDate
	 * @param mealNum
	 * @param canteenNum
	 * @return 
	 * @return: SSBMCppb03 
	 * @author: liutao
	 * @date: 2021年9月9日 上午11:27:24
	 */
	public SSBMCppb03 getMenuInfoByMenuNum(String menuNum,String needDate,String mealNum,String canteenNum) {
		SSBMCppb03 info = null;
		try {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("menuNum", menuNum);
			map.put("mealDate", needDate);
			map.put("mealNum", mealNum);
			map.put("canteenNum", canteenNum);
			System.out.println(menuNum + ","+needDate+","+mealNum+","+canteenNum);
			//查询菜品排班表
			List<SSBMCppb03> list = dao.query("SSBMCppb01.queryMenuSche",map);
			if(!list.isEmpty()) {
				info = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(info == null){
			info = new SSBMCppb03();
		}
		return info;
	}
	
	
	/**
	 * 
	 *  checkWorkSelfSchema校验职工自取餐菜品餐次排班
	 *
	 * @Title: checkWorkSelfSchema 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo  success true:有排班 false:无排班
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:20:18
	 */
	public EiInfo checkWorkSelfSchema(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		SSACTscWorkOrderBillDetail[] listDetail = (SSACTscWorkOrderBillDetail[]) attr.get("listDetail");
		EiInfo outInfo = new EiInfo();
		boolean flag = false;
		
		if (listDetail != null && listDetail.length > 0) {
			//校验菜品的排班信息
			flag = validateWorkMenuInfo(listDetail);
		}
		outInfo.set("success", flag);
		return outInfo;
	}
	
	/**
	 * 
	 * 校验菜品的排班信息
	 *
	 * @Title: validateWorkMenuInfo 
	 * @param lists
	 * @return 
	 * @return: boolean 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:20:42
	 */
	public boolean validateWorkMenuInfo(SSACTscWorkOrderBillDetail[] lists) {
		boolean flag = true;
		try {
			for (SSACTscWorkOrderBillDetail detail : lists) {
				System.out.println("菜品:" + detail.getMenuName() + ",排班id:" + detail.getId());

				if (detail.getId() != null) {
					HashMap<String, Object> paramMap = new HashMap<String, Object>();
					//通过id查询菜品排班表
					paramMap.put("sql", "SSBMCppb01.queryMenuScheById");
					paramMap.put("str", detail.getId());
					EiInfo call = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
					List<SSBMCppb03> list = (List<SSBMCppb03>) call.get("result");
					SSBMCppb03 menuSche = new SSBMCppb03();
					if (!list.isEmpty()) {
						menuSche = list.get(0);
					}
					System.out.println("menuScheID:" + menuSche.getId());

					if (menuSche.getId() == null) {
						flag = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 
	 * 校验菜品餐次排班
	 *
	 * @Title: checkSchema 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo   success true:有排版 false:无排版
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:22:16
	 */
	public EiInfo checkSchema(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		List<PSPCTmealOrderBillDetail> listsAM = (List<PSPCTmealOrderBillDetail>) attr.get("listsAM");
		List<PSPCTmealOrderBillDetail> listsPM = (List<PSPCTmealOrderBillDetail>) attr.get("listsPM");
		List<PSPCTmealOrderBillDetail> listsMM = (List<PSPCTmealOrderBillDetail>) attr.get("listsMM");
		EiInfo outInfo = new EiInfo();
		boolean flag = false;
		String msg = "缺少参数";
		if (listsAM != null && listsAM.size() > 0) {
			flag = validateMenuInfo(listsAM);
		}
		if (listsPM != null && listsPM.size() > 0) {
			flag = validateMenuInfo(listsPM);
		}
		if (listsMM != null && listsMM.size() > 0) {
			flag = validateMenuInfo(listsMM);
		}
		outInfo.set("success", flag);
		outInfo.setMsg(flag ? "校验通过" : msg);
		return outInfo;
	}

	/**
	 * 
	 * 校验菜品的排班信息
	 *
	 * @Title: validateMenuInfo 
	 * @param lists
	 * @return 
	 * @return: boolean 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:22:57
	 */
	public boolean validateMenuInfo(List<PSPCTmealOrderBillDetail> lists) {
		boolean flag = true;
		try {
			for (PSPCTmealOrderBillDetail posPatiBillDetail : lists) {
				System.out.println("菜品:" + posPatiBillDetail.getMenuName() + ",排班id:" + posPatiBillDetail.getId());

				if (posPatiBillDetail.getId() != null) {
					HashMap<String, Object> paramMap = new HashMap<String, Object>();
					//查询菜品排班表
					paramMap.put("sql", "SSBMCppb01.queryMenuScheById");
					paramMap.put("str", posPatiBillDetail.getId());
					EiInfo call = LocalServiceUtil.call("SSBMTy", "querySqlByString", paramMap);
					List<SSBMCppb03> list = (List<SSBMCppb03>) call.get("result");
					SSBMCppb03 menuSche = new SSBMCppb03();
					if (!list.isEmpty()) {
						menuSche = list.get(0);
					}
					System.out.println("menuScheID:" + menuSche.getId());

					if (menuSche.getId() == null) {
						flag = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 
	 * 订单参数校验
	 *
	 * @Title: checkParam 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:23:36
	 */
	public EiInfo checkParam(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		JSONObject ja = (JSONObject) attr.get("json");
		// 检查参数非空项
		String userName = ja.getString("userName");
		if (StringUtils.isBlank(userName)) {
			inInfo.set("success", false);
			inInfo.set("respMsg", "参数userName为空，请检查!");
			return inInfo;
		}
		String deptName = ja.getString("deptName");
		if (StringUtils.isBlank(deptName)) {
			inInfo.set("success", false);
			inInfo.set("respMsg", "参数deptName为空，请检查!");
			return inInfo;
		}
		// 住院号
		String deptNum = ja.getString("deptNum");
		if (StringUtils.isBlank(deptNum)) {
			inInfo.set("success", false);
			inInfo.set("respMsg", "参数deptNum为空，请检查!");
			return inInfo;
		}
		// 医院编码
		String hospitalNo = ja.getString("hospitalNo");
		if (StringUtils.isBlank(hospitalNo)) {
			inInfo.set("success", false);
			inInfo.set("respMsg", "参数hospitalNo为空，请检查!");
			return inInfo;
		}
		// 食堂编码
		String canteenNum = ja.getString("canteenNum");
		if (StringUtils.isBlank(canteenNum)) {
			inInfo.set("success", false);
			inInfo.set("respMsg", "参数canteenNum为空，请检查!");
			return inInfo;
		}
		// 餐次
		String mealNum = ja.getString("mealNum");
		/*if (StringUtils.isBlank(mealNum)) {
			inInfo.set("success", false);
			inInfo.set("respMsg", "参数mealNum为空，请检查!");
			return inInfo;
		}*/
		// 电话号码
		String userTelNumber = ja.getString("userTelNumber");
		if (StringUtils.isBlank(userTelNumber)) {
			inInfo.set("success", false);
			inInfo.set("respMsg", "参数userTelNumber为空，请检查!");
			return inInfo;
		}
		//需求送达时间
		String reqSendTime = ja.getString("reqSendTime");
		if (StringUtils.isBlank(reqSendTime)) {
			inInfo.set("success", false);
			inInfo.set("respMsg", "参数reqSendTime为空，请检查!");
			return inInfo;
		}
		//需要时间
		String needDate = ja.getString("needDate");
		if (StringUtils.isBlank(needDate)) {
			inInfo.set("success", false);
			inInfo.set("respMsg", "参数needDate为空，请检查!");
			return inInfo;
		}
		inInfo.set("success", true);
		return inInfo;
	}

	
	/**
	 * 
	 * 校验送餐时间
	 *
	 * @Title: checkSendTime 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:23:53
	 */
	public EiInfo checkSendTime(EiInfo inInfo) {
		HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		String mealNum = StringUtil.toString(attr.get("mealNum"));
		String needDate = StringUtil.toString(attr.get("needDate"));
		String reqSendTime = StringUtil.toString(attr.get("reqSendTime"));
		//校验订餐时间
		ResultData result = checkCanteenTimes(canteenNum, mealNum, needDate, reqSendTime);
		if ("201".equals(result.getRespCode())) {
			inInfo.set("success", false);
			inInfo.set("respMsg", result.getRespMsg());
			return inInfo;
		}
		inInfo.set("respMsg", result.getRespMsg());
		inInfo.set("success", true);
		return inInfo;
	}
	
	/**
	 * 
	 * 根据订餐配置规则校验当前时间和下单时间和送餐时间
	 * 1.获取订单配置时间
	 * 2.当天允许订餐时间
	 * 3.明天允许订餐时间
	 * @Title: checkCanteenTimes 
	 * @param canteenNum 食堂编码
	 * @param mealNum 餐次
	 * @param needDate 用餐时间
	 * @param sendTime 送达时间
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:24:11
	 */
	public ResultData checkCanteenTimes(String canteenNum, String mealNum, String needDate, String sendTime) {
		ResultData result = new ResultData();
		try {
			/**1.获取订单配置时间*/
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("canteenNum", canteenNum);
			paramMap.put("mealNum", mealNum);
			List<SSBMDcsj01> canteenTimes = dao.query("SSBMDcsj01.query", paramMap);
			if(!canteenTimes.isEmpty()) {
				SSBMDcsj01 canteenTime = canteenTimes.get(0);
				/** 2.当天允许订餐时间*/
				String todayOrderTime = canteenTime.getTodayOrderTime();
				String today = CalendarUtils.getDay(false);
				// 今天
				if (today.equals(needDate) || "D".equals(needDate)) {
					// 限制下单时间
					String noOrderTime = today + " " + todayOrderTime + ":00";
					long noOrderTimeLong;
					
						noOrderTimeLong = CalendarUtils.dateTimeFormat.parse(noOrderTime).getTime();
					
					// 送达时间
					String noSendTime = today + " " + sendTime + ":00";
					long noSendTimeLong = CalendarUtils.dateTimeFormat.parse(noSendTime).getTime();
					int advanceTime = canteenTime.getAdvanceTime();
					// 当前时间大于允许点餐最大时间,或则当前时间大于送餐时间+提前量
					System.out.println("当前时间为：" + CalendarUtils.dateTimeFormat.format(new Date()) 
							+ "，允许订餐时间：" + noOrderTime);
					if (new Date().getTime() > noOrderTimeLong) {
						result.setRespMsg("当前时间为：" + CalendarUtils.dateTimeFormat.format(new Date()) 
							+ "，已超出允许订餐时间：" + noOrderTime);
					} 
					/*else if (new Date().getTime() > (noSendTimeLong - (advanceTime * 60 * 1000))) {
						result.setRespMsg("当前时间为：" + CalendarUtils.dateTimeFormat.format(new Date()) 
							+ "，已超出送餐提前时间：" + CalendarUtils.dateTimeFormat.format(new Date(noSendTimeLong - (advanceTime * 60 * 1000))));
					}*/
				}
				/** 3.明天允许订餐时间*/
				String tomorrowOrderTime = canteenTime.getTomorrowOrderTime();
				String tomorrow = CalendarUtils.getDay(true);
				if (tomorrow.equals(needDate) || "Y".equals(needDate)) {
					// 限制下单时间
					String notomorrowTime = today + " " + tomorrowOrderTime + ":00";
					long noOrderTimeLong = CalendarUtils.dateTimeFormat.parse(notomorrowTime).getTime();
					// 送达时间
					String noSendTime = tomorrow + " " + sendTime + ":00";
					long noSendTimeLong = CalendarUtils.dateTimeFormat.parse(noSendTime).getTime();
					int advanceTime = canteenTime.getAdvanceTime();
					// 当前时间大于允许点餐最大时间,或则当前时间大于送餐时间+提前量
					if (new Date().getTime() > noOrderTimeLong) {
						result.setRespMsg("当前时间为：" + CalendarUtils.dateTimeFormat.format(new Date()) 
								+ "，已超出允许订餐时间：" + notomorrowTime);
					} 
					/*else if (new Date().getTime() > (noSendTimeLong - (advanceTime * 60 * 1000))) {
						result.setRespMsg("当前时间为：" + CalendarUtils.dateTimeFormat.format(new Date()) 
								+ "，已超出送餐提前时间：" + CalendarUtils.dateTimeFormat.format(new Date(noSendTimeLong - (advanceTime * 60 * 1000))));
					}*/
				}
			}else {
				result.setRespMsg("食堂：" + canteenNum + ",餐次：" + mealNum + "订餐时间配置有误！");
			}
		} catch (ParseException e) {
			e.printStackTrace();
			result.setRespMsg("日期格式有误！");
		}
		if (StringUtil.isNotEmpty(result.getRespMsg())) {
			//存在msg报错信息的状态设置为201
			result.setRespCode("201");
		}
		return result;
	}

}
