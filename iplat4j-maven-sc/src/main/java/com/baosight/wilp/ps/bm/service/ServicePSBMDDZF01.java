package com.baosight.wilp.ps.bm.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ps.bm.domain.PSBMDdzf01;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOrderBillPatient;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import com.baosight.wilp.ss.bm.utils.RespResult;
//import com.bonawise.smp.alipay.hessian.AliPaySender;


/**
 * 
 * ServicePSBMDDZF01病员订单作废service
 * 
 * @Title: ServicePSBMDDZF01.java
 * @ClassName: ServicePSBMDDZF01
 * @Package：com.baosight.wilp.ps.bm.service
 * @author: liutao
 * @date: 2021年9月9日 上午10:15:44
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePSBMDDZF01 extends ServiceBase{
	

    /**
     * 
     * initLoad 初始化方法
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new PSBMDdzf01());
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		//canteenData:食堂名称
		paramMap.put("userId", UserSession.getLoginName());
		paramMap.put("canteenTypeNum","bhst");
		List<HashMap<String,Object>> listCanteenType = dao.query("SSBMStxx01.getCanteenData", paramMap);
		initLoad.addBlock("canteenData").addRows(listCanteenType);
		//mealNum:餐次类型
		paramMap.put("mealgroupTypeCode", "mealNum");
		List<HashMap<String,Object>> listMealNum = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("mealNum").addRows(listMealNum);
		//deptData:部门数据
		List<HashMap<String,Object>> listDeptData = dao.query("PSBMDdgl01.findDept", paramMap);
		initLoad.addBlock("deptData").addRows(listDeptData);
		//floorData:楼层数据
		List<HashMap<String,Object>> listFloorData = dao.query("PSBMDdgl01.getFloors", paramMap);
		initLoad.addBlock("floorData").addRows(listFloorData);
		//buildingData:楼栋数据
		List<HashMap<String,Object>> listBuildingData = dao.query("PSBMDdgl01.getBuildings", paramMap);
		initLoad.addBlock("buildingData").addRows(listBuildingData);
		
		//增加主表 resultBlock 块
		EiBlock resultBlock = initLoad.addBlock(EiConstant.resultBlock);
		resultBlock.addBlockMeta(new PSBMDdzf01().eiMetadata);
		//增加明细表 result2 块
		EiBlock result2 = initLoad.addBlock("result2");
		result2.addBlockMeta(new PSBMDdzf01().eiMetadata);
		return initLoad;
	}


	/**
	 * 
     * query 查询待处理数据
	 * @param inInfo
	 * @return
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "PSBMDdzf01.query", new PSBMDdzf01());
		return outInfo;
	}

	/**
	 * 
     * query2 查询已处理数据
	 *
	 * @Title: query2 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:21:56
	 */
	public EiInfo query2(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "PSBMDdzf01.query2", new PSBMDdzf01(),false, null, EiConstant.queryBlock, "result2", "result2");
		return outInfo;
	}
	
	/**
	 * 
     * confirmCancelOrder 确认作废方法
     * 入参：订单主键id，作废原因rejectReason
     * 出餐：EiInfo(status：状态，msg：信息)
     *  1.获得提交的订单id查询订单信息，若未查询到信息则返回status=-1,msg=未查询到订单信息
        2.将订单作废状态变更为2(确认作废)状态，订单状态变更为99(已结束)
	 *
	 * @Title: confirmCancelOrder 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 上午10:22:12
	 */
	public EiInfo confirmCancelOrder(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			//接收数据
			String id = StringUtil.toString(inInfo.getAttr().get("id"));
			String rejectReason = StringUtil.toString(inInfo.getAttr().get("rejectReason"));
			
			if(StringUtil.isNotEmpty(id)) {
				HashMap<String,String> param = new HashMap<String, String>();
				param.put("id", id);
				param.put("rejectReason", rejectReason);
				/**1.查询订单信息*/
				List<PSPCTmealOrderBillPatient> list = dao.query("PSPCTmealOrderBillPatient.query", param);
				if(list.size() > 0) {
					//订单存在
					EiInfo info = new EiInfo();
					param.put("rejectCode","2");
					param.put("statusCode","99");
					info.addBlock("result").addRow(param);
					/**2.更新订单作废状态*/
					outInfo = super.update(info, "PSPCTmealOrderBillPatient.confirmCancelOrder");
					outInfo.setMsg("更新成功！");
				}else {
					inInfo.setMsg("查询订单信息失败！");
					inInfo.setStatus(-1);
					return inInfo;
				}
			}else {
				inInfo.setMsg("数据提交失败！");
				inInfo.setStatus(-1);
				return inInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			inInfo.setMsg("更新异常！");
			inInfo.setStatus(-1);
			return inInfo;
		}
		return outInfo;
	}
	
	/*
	public EiInfo test(EiInfo inInfo) {
		//Hessian查询支付宝订单支付结果
		ResponseResult aliResult = AliPaySender.singleTradeQuery( "86czeygz28",
																  "PATIENT_MEAL",
																  "e2aa7004-1b7e-4fcd-a37f-5c925fb0b9d6" );
		System.out.println("###########支付结果："+aliResult.toString());
		return inInfo;
	}
	public EiInfo test1(EiInfo inInfo) {
		//httpClient调用iplat服务
		String returnValue = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		try {
			//HttpClient
			httpClient = HttpClients.createDefault();
			//httpPost连接
			HttpPost httpPost = new HttpPost("http://127.0.0.1:8083/WILP/service/S_SS_BM_ALI_01");
			//httpPostJSON传参
			Map<String,String> param = new HashMap<String, String>();
			param.put("billNo", "BH2021041300918");
			StringEntity requestEntity = new StringEntity(JSONObject.toJSONString(param), "utf-8");
			requestEntity.setContentEncoding("UTF-8");
			httpPost.setHeader("Content-type", "application/json");
			httpPost.setEntity(requestEntity);
			//HttpPost执行
			returnValue = httpClient.execute(httpPost, responseHandler);
			JSONObject jsonObject = JSONObject.parseObject(returnValue);
			inInfo.set("billNo", jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return inInfo;
	}*/
}
