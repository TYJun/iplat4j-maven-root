package com.baosight.wilp.hi.xx.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 该页面为标识申请列表页面
 * <p>1.初始化查询 initLoad
 * <p>2.数据查询 query
 * @Title: ServiceHIXX0102.java
 * @ClassName: ServiceHIXX0102
 * @Package：com.baosight.wilp.hi.xx.service
 * @author: liangyongfei
 * @date: 2022年8月21日 下午1:29:57
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceHIXX0102 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * 标识申请列表界面
     * @param inInfo
     * applyNo 申请单号
     * iconName 标识名称
     * @return EiInfo
     * id 主键
     * applyNo 申请单号
     * iconName 标识名称
     * applyDeptName 申请科室
     * iconZhContent 标识中文内容
     * iconEnContent 标识英文内容
     * status 状态
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
	    // 调用查询方法
		return this.query(inInfo);
	}
	
	 /**
     * @Title: query
     * @Description: 合同数据查询
	 * <p>1.创建数组并赋值
	  * <p>2.调用接口创建map
	  * <p>3.调用查询方法
	  * <p>4.如果获取查询信息不为空
	  * <p>5.返回封装方法
	  * 标识申请列表界面
	  * @param inInfo
	  * applyNo 申请单号
	  * iconName 标识名称
	  * @return EiInfo
	  * id 主键
	  * applyNo 申请单号
	  * iconName 标识名称
	  * applyDeptName 申请科室
	  * iconZhContent 标识中文内容
	  * iconEnContent 标识英文内容
	  * status 状态
     */
	public EiInfo query(EiInfo inInfo) {
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 1.创建数组并赋值
		String[] parameter = {"applyNo","iconName"};
		// 数组转list
		List<String> plist = Arrays.asList(parameter);
		// 2.调用接口创建map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
//		map.put("contDeptNum",staffByUserId.get("deptNum"));
		// 3.调用查询方法
		List<Map<String, ?>> result = dao.query("HIXX01.queryApplicationSuccessful", map,Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		// 获取查询信息总数
		int count = dao.count("HIXX01.queryApplicationSuccessful", map);
		// 4.如果获取查询信息不为空
		if(result != null && result.size() > 0){
		    // 5.返回封装方法
			return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(result.get(0)), result, count);
		} else { 
		    // 返回参数
			return inInfo; 
		}
	}






}
