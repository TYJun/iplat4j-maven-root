package com.baosight.wilp.dm.pj.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 宿舍满意度评价子页面service
 * 一、页面加载.
 * 
 * @Title: ServiceDMPJ0101.java
 * @ClassName: ServiceDMPJ0101
 * @Package：com.baosight.wilp.dm.pj.service
 * @author: fangzekai
 * @date: 2022年03月16日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMPJ0101 extends ServiceBase {
	
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
     * 此方法用于满意度评价子页面信息查询
     *
     * 逻辑处理：
     * 1.调用本地服务DMPJ0101.queryPJDetailInfo 查询宿舍的详情信息.
     *
     * @Title: query
     * @param: EiInfo inInfo
     * @return: EiInfo inInfo
     */
	public EiInfo query(EiInfo inInfo) {
		// 调用本地服务DMPJ0101.queryPJDetailInfo 查询宿舍的详情信息.
		inInfo.set(EiConstant.serviceName, "DMPJ0101");
		inInfo.set(EiConstant.methodName, "queryPJDetailInfo");
		EiInfo outInfo =XLocalManager.call(inInfo);
		return outInfo;
	}

	/**
	 * 满意度评价宿舍详情查询.
	 * 根据宿舍人员绑定关系表id，查询指定的id.
	 * 1、获取前端传来的id值.
	 * 2、将id值放入map给DMPJ0101.queryPJDetailInfo 查询宿舍的详情信息.
	 * 3、判断是否取得数据.
	 *
	 * @Title: queryDetailInfo
	 * @param:  @param inInfo
	 *      roomId： 宿舍id
	 * @param:  @return
	 * @return: EiInfo
	 *      id ：主键
	 *      building  : 宿舍楼
	 *      floor  : 宿舍层
	 *      roomNo  : 宿舍号
	 *      roomName : 宿舍总称(楼+层+宿舍号)
	 *      bedNum  : 床位数
	 *      remainingBedNum ： 剩余床位数
	 *      typeCode : 房间类型(1男生宿舍/0女生宿舍)
	 *      dormPosition : 宿舍位置：院内、院外
	 *      dormArea  : 房屋面积："<50㎡"、"50-100㎡"、">100㎡"
	 *      priBathroom : 独立卫生间：有、没有
	 *      rent  : 房租
	 *      manageFee : 管理费
	 *      note : 备注信息
	 */
	@SuppressWarnings("unchecked")
	public EiInfo queryPJDetailInfo(EiInfo inInfo) {
		/*
		 * 1、获取前端传来的id值.
		 */
		String id = "";
		if(inInfo.get("id") != null || !"".equals(inInfo.get("id"))) {
			id = inInfo.getString("id");
		}
		/*
		 * 2、将id值放入map给DMPJ01.queryPJDetailInfo 做参数去查询满意度评价的宿舍的详情信息.
		 */
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		EiInfo outInfo = new EiInfo();
		List<Map<String, String>> list = dao.query("DMPJ01.queryPJDetailInfo", map);
		/*
		 * 3、判断是否取得数据.
		 */
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}
		outInfo.setAttr(list.get(0));
		outInfo.setRows("pjDetailInfo",list);
		return outInfo;
	}

	/**
	 * 将评价结果保存到宿舍满意度评价结果表中.
	 * 1、调用本地接口DMPJ0101.insertEvalResult将评价结果内容以及对应时间节点插入宿舍满意度评价结果表中.
	 * 2、调用本地接口DMPJ0101.insertEvalResultItem将评价结果明细插入宿舍满意度评价结果明细表中.
	 * 3、调用本地接口DMRZ01.updateAboutEval更新人员入住信息表的评价状态和评价内容和评价时间.
	 * 4、返回一个EiInfo.
	 *
	 * @Title: saveEvaluateResult
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	public EiInfo saveEvaluateResult(EiInfo inInfo) {
		String id = UUID.randomUUID().toString(); // 生成自定义的UUID，用那个来存储该条评论的结果。
		inInfo.set("resultId",id);
		/*
		 * 1、调用本地接口DMPJ0101.insertEvalResult将评价结果内容以及对应时间节点插入宿舍满意度评价结果表中.
		 */
		inInfo.set(EiConstant.serviceName, "DMPJ0101");
		inInfo.set(EiConstant.methodName, "insertEvalResult");
		EiInfo outInfo = XLocalManager.call(inInfo);
		/*
		 * 2、调用本地接口DMPJ0101.insertEvalResultItem将评价结果明细插入宿舍满意度评价结果明细表中.
		 */
		inInfo.set(EiConstant.serviceName, "DMPJ0101");
		inInfo.set(EiConstant.methodName, "insertEvalResultItem");
		outInfo = XLocalManager.call(inInfo);
		/*
		 * 3、调用本地接口DMRZ01.updateAboutEval更新人员入住信息表的评价状态和评价内容和评价时间.
		 */
		inInfo.set("evalStatus","1");
		inInfo.set(EiConstant.serviceName, "DMRZ01");
		inInfo.set(EiConstant.methodName, "updateAboutEval");
		outInfo = XLocalManager.call(inInfo);
		/*
		 * 4、返回一个EiInfo.
		 */
		outInfo.setMsg("操作成功！");
		outInfo.setMsgKey("200");
		return outInfo;
	}

	/**
	 * 宿舍满意度评价结果表保存接口.
	 * 对参数处理，然后保存到数据库.
	 * 1、获取inInfo传来的参数.
	 * 2、循环每个结果项目，并进行重新封装到result.
	 * 3、执行DMPJ01.insertEvalResult 进行数据的批量插入.
	 * 4、返回一个EiInfo.
	 *
	 * @Title: insertEvalResult
	 * @param:  @param inInfo
	 *      id ：主键
	 *      manId  : 人员入住信息id
	 *      roomId  : 宿舍信息id
	 *      evalContent : 评价内容
	 *      evalTime : 评价时间
	 *      evalMonth : 当前月份
	 * @return: EiInfo
	 * @throws
	 */
	public EiInfo insertEvalResult(EiInfo inInfo) {
		/*
		 * 1、获取inInfo传来的参数.
		 */
		String id = inInfo.getString("resultId");
		String manId = inInfo.get("manId") == null ? "" : inInfo.getString("manId");     /* 人员入住信息id */
		String roomId = inInfo.get("roomId") == null ? "" : inInfo.getString("roomId");     /* 宿舍信息id */
		String evalContent = inInfo.getString("evalContent"); // 评价内容
		String evalTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); // 评价时间
		String evalMonth = new SimpleDateFormat("yyyy-MM").format(new Date());      /* 当前月份 */

		Map<String,Object> map = new HashMap<>();
		map.put("id",id);
		map.put("manId",manId);
		map.put("roomId",roomId);
		map.put("evalContent",evalContent);
		map.put("evalTime",evalTime);
		map.put("evalMonth",evalMonth);
		/*
		 * 3、执行DMPJ01.insertEvalResult 进行数据的批量插入.
		 */
		dao.insert("DMPJ01.insertEvalResult", map);
		/*
		 * 4、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.set("id", id);
		return outInfo;
	}

	/**
	 * 宿舍满意度评价结果明细表保存接口.
	 * 对参数处理，然后保存到数据库.
	 * 1、获取inInfo传来的参数.
	 * 2、循环每个结果项目，并进行重新封装到result.
	 * 3、执行DMPJ01.insertEvalResultItem 进行数据的批量插入.
	 * 4、返回一个EiInfo.
	 *
	 * @Title: insertEvalResultItem
	 * @param:  @param inInfo
	 *      id ：主键
	 *      manId  : 人员id
	 *      list : 结果列表
	 * @return: EiInfo
	 * @throws
	 */
	public EiInfo insertEvalResultItem(EiInfo inInfo) {
		/*
		 * 1、获取inInfo传来的参数.
		 */
		String resultId = inInfo.getString("resultId");     /* 宿舍满意度评价结果表主键id */
		List<Map<String, Object>> list = (List<Map<String, Object>>) inInfo.get("list"); /* 获取结果列表 */
		List<Map<String, Object>> result = new LinkedList<>();
		/*
		 * 2、循环每个结果项目，并进行重新封装到result.
		 */
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> item = new HashMap<>();
			item.put("id",UUID.randomUUID().toString());
			item.put("resultId",resultId);
			item.put("itemCode",list.get(i).get("code"));
			item.put("evalGrade",list.get(i).get("evalLevel"));
			result.add(item);
		}
		/*
		 * 3、执行DMPJ01.insertEvalResultItem 进行数据的批量插入.
		 */
		dao.insert("DMPJ01.insertEvalResultItem", result);
		/*
		 * 4、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.set("resultId", resultId);
		return outInfo;
	}

	/**
	 * 此方法用于查询满意度分类的项目
	 *
	 * 逻辑处理：
	 * 1.获取满意度评价项目分类
	 * 2.循环遍历每个分类，将分类的中的具体项目查出
	 *
	 * @Title: getSatisfactionList
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	public EiInfo getSatisfactionList(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		/*
		 * 1.获取满意度评价项目分类
		 */
		List<Map<String, Object>> classList = dao.query("DMPJ02.queryAllItemClass", null);
		if (CollectionUtils.isEmpty(classList)) {
			outInfo.setMsg("未查到满意度评价分类数据");
			outInfo.setStatus(-1);
			return outInfo;
		}
		/*
		 * 2.循环遍历每个分类，将分类的中的具体项目查出。
		 */
		for (int i = 0; i < classList.size(); i++) {
			String moduleId = (String) classList.get(i).get("classId");
			List<Map<String, Object>> itemList = dao.query("DMPJ02.queryAllItem", moduleId);
			if (CollectionUtils.isEmpty(itemList)) {
				continue;
			}
			classList.get(i).put("itemList",itemList);
		}
		outInfo.setRows("list",classList);
		return outInfo;
	}

	/**
	 * 此方法用于查询满意度评价项目
	 *
	 * 逻辑处理：
	 * 1.1.调用sql方法DMPJ02.queryAllItemCode查询满意度评价项目
	 *
	 * @Title: queryItemCode
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	public EiInfo queryItemCode(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		/**
		 * 1.调用sql方法DMPJ02.queryAllItemCode查询满意度评价项目
		 */
		List<Map<String, Object>> classList = dao.query("DMPJ02.queryAllItemCode", null);
		if (CollectionUtils.isEmpty(classList)) {
			outInfo.setMsg("未查到宿舍满意度评价项目");
			outInfo.setStatus(-1);
			return outInfo;
		}
		outInfo.setMsg("查询宿舍满意度评价项目成功，正在执行提交，请稍后！");
		outInfo.setRows("itemCodelist",classList);
		return outInfo;
	}

}
