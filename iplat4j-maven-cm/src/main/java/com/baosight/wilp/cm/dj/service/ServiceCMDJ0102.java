package com.baosight.wilp.cm.dj.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
/**
 * 该页面为合同登记管理
 * 主要包含对合同登记的新增、删除、编辑、查看、审批功能
 * 合同模块：数据回显和加载、查询合同条款定义
 * <p>1.数据回显和加载 initLoad
 * <p>2.查询合同条款定义 query
 * @Title: ServiceCMDJ0102.java
 * @ClassName: ServiceCMDJ0102
 * @Package：com.baosight.wilp.cm.dj.service
 * @author: zhaow
 * @date: 2021年8月30日 下午2:31:13
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMDJ0102 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 数据回显和加载
     * 该页面为合同登记管理
     * 通过合同条款名称条件查询
     * 回显合同条款、条款内容
     * @param EiInfo
     * contTypeName 合同条款名称
     * workNo 工号
     * @return EiInfo
     * contTypeName 合同条款名称
     * contTermContent 合同条款内容
     */
	public EiInfo initLoad(EiInfo inInfo) {
	    // 返回参数
		return inInfo;
	}
	
	/**
     * @Title: query
     * @Description: 数据回显和加载
	 * <p>1.调用封装方法创建map
	 * <p>2.调用查询方法
	 * <p>3.如果查询信息不为空
	 * <p>4.返回参数
     * 该页面为合同登记管理
     * 通过合同条款名称条件查询
     * 回显合同条款、条款内容
     * @param EiInfo
     * contTypeName 合同条款名称
     * workNo 工号
     * @return EiInfo
     * contTypeName 合同条款名称
     * contTermContent 合同条款内容
     */
	public EiInfo query(EiInfo inInfo) {
	    // 设置数组
		String[] parameters = {"contTypeName"};
		// 数组转化成集合
		List<String> plist = Arrays.asList(parameters);
		// 1.调用封装方法创建map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
		// 2.调用查询方法
		List<Map<String, Object>> result = dao.query("CMTK01.query", map, Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		// 设置查询信息总数
		int count = dao.count("CMTK01.query", map);
		// 3.如果查询信息不为空
		if(result != null && result.size() > 0){
		    // 调用封装方法返回
			return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(result.get(0)), result, count);
		} else { 
		    // 4.返回参数
			return inInfo; 
		}
	}
}