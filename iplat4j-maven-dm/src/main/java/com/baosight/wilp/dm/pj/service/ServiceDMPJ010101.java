package com.baosight.wilp.dm.pj.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 宿舍满意度评价查看子页面service
 * 一、页面加载.
 * 
 * @Title: ServiceDMPJ010101.java
 * @ClassName: ServiceDMPJ010101
 * @Package：com.baosight.wilp.dm.pj.service
 * @author: fangzekai
 * @date: 2022年03月16日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMPJ010101 extends ServiceBase {
	
	/**
	 * 一、页面加载.
	 *
	 * @Title: initLoad
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
    }

    /**
     * 此方法用于查询满意度历史评价详情信息
     *
     * 逻辑处理：
     * 1.调用本地服务DMPJ010101.querySelfDetailInfo 查询自身部分详情信息.
     *
     * @Title: query
     * @param: EiInfo inInfo
     * @return: EiInfo inInfo
     */
	public EiInfo query(EiInfo inInfo) {
		// 调用本地服务DMPJ010101.querySelfDetailInfo 查询自身部分详情信息.
		inInfo.set(EiConstant.serviceName, "DMPJ010101");
		inInfo.set(EiConstant.methodName, "querySelfDetailInfo");
		EiInfo outInfo =XLocalManager.call(inInfo);
		return outInfo;
	}

	/**
	 * 此方法用于查询满意度历史评价详情信息
	 *
	 * 逻辑处理：
	 * 1、获取前端传来的id值.
	 * 2、将id值放入map给DMPJ01.queryPJDetailInfo 做参数去查询满意度评价的宿舍的详情信息.
	 * 3、判断是否取得数据.
	 *
	 * @Title: querySelfDetailInfo
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	public EiInfo querySelfDetailInfo(EiInfo inInfo) {
		/*
		 * 1、获取前端传来的id值.
		 */
		String manId = "";
		String evalMonth = "";
		if(inInfo.get("manId") != null || !"".equals(inInfo.get("manId"))) {
			manId = inInfo.getString("manId");
		}
		if(inInfo.get("evalMonth") != null || !"".equals(inInfo.get("evalMonth"))) {
			evalMonth = inInfo.getString("evalMonth");
		}
		/*
		 * 2、将id值放入map给DMPJ01.queryPJDetailInfo 做参数去查询满意度评价的宿舍的详情信息.
		 */
		Map<String, String> map = new HashMap<>();
		map.put("manId", manId);
		map.put("evalMonth", evalMonth);
		EiInfo outInfo = new EiInfo();
		List<Map<String, String>> list = dao.query("DMPJ01.querySelfDetailInfo", map);
		/*
		 * 3、判断是否取得数据.
		 */
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}
		outInfo.setAttr(list.get(0));
		outInfo.setRows("selfDetailInfo",list);
		return outInfo;
	}


	/**
	 * 页面数据回显查询
	 *
	 * <p>查询宿舍满意度评价结果dorms_satisfaction_result_item表中对应人员id  manId的评价记录</p>
	 *
	 * <p>Title: queryEvalResultByResultId</p>
	 * @param inInfo ： manId
	 * @return
	 * 		id ： 主键
	 *		manId ：  人员id
	 *		itemCode ： 项目编码
	 *		evalGrade ： 评价等级(1-5) (1:不清楚；2:不满意；3:合格；4:比较满意；5:满意)
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	public EiInfo queryEvalResultByResultId(EiInfo inInfo) {
		Map<Object, String> pMap = new HashMap<>();
		pMap.put("resultId", inInfo.getString("resultId"));
		List list = dao.query("DMPJ01.queryEvalResultByResultId", pMap);
		inInfo.set("list", list);
		return inInfo;
	}

	/**
	 * 每月一次的定时任务 **********
	 * 更新人员信息表的评价状态为未评价
	 * 1、执行 DMPJ01.updateEvalStatus0 进行更新人员入住信息表的评价状态为未评价。
	 * 2、返回一个EiInfo.
	 *
	 * @Title: updateEvalStatus0
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	public EiInfo updateEvalStatus0(EiInfo inInfo) {
		/*
		 * 1、执行 DMPJ01.updateEvalStatus0 进行更新人员入住信息表的评价状态为未评价。
		 */
		dao.update("DMPJ01.updateEvalStatus0", null);
		/*
		 * 2、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.setMsg("操作成功");
		return outInfo;
	}
}
