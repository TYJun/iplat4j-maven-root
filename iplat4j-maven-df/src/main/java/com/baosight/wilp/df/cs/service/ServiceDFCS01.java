package com.baosight.wilp.df.cs.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 
 * 设备系统参数：初始化查询、设备参数查询、设备参数删除
 * <p>1.初始化查询 initLoad
 * <p>2.设备参数查询 query
 * <p>3.设备参数删除 deleteItem
 * 
 * @Title: ServiceDFCS01.java
 * @ClassName: ServiceDFCS01
 * @Package：com.baosight.wilp.df.cs.service
 * @author: zhaow
 * @date: 2021年8月10日 下午2:56:11
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFCS01 extends ServiceBase{
    /**
     * 
     * @Title: initLoad
     * @Description: 初始化查询
     * @param info
     * moduleId : 参数所属分类ID
     * classifyName : 设备分类名称
     * offset : 起始页
     * limit : 分页
     * @return info
     * id : 设备分类参数id
     * paramKey : 参数编码
     * paramName : 参数名称
     * paramValue : 参数值
     * paramUnit : 参数单位
     * memo : 备注
     * classifyCode : 设备分类编码
     * classifyName : 设备分类名称
     */
	@Override
	public EiInfo initLoad(EiInfo info) {
	    // 调用设备参数查询方法
		return query(info);
	}
	
	/**
	 * 
	 * @Title: query
	 * @Description: 设备参数查询
	 * @param info
	 * moduleId : 参数所属分类ID
	 * classifyName : 设备分类名称
	 * offset : 起始页
	 * limit : 分页
	 * @return info
	 * id : 设备分类参数id
	 * paramKey : 参数编码
	 * paramName : 参数名称
	 * paramValue : 参数值
	 * paramUnit : 参数单位
	 * memo : 备注
	 * classifyCode : 设备分类编码
	 * classifyName : 设备分类名称
	 */
	@Override
	public EiInfo query(EiInfo info) {
	    // 调用分页接口
		Map<String, Object> map = CommonUtils.buildParamter(info, "result", new ArrayList<String>());
		// 获取参数所属分类id
		String moduleId = (String) info.getAttr().get("queryModuleId");
		// 获取设备参数名称
		String classifyName = (String) info.getAttr().get("queryClassifyName");
		// map存值
		map.put("moduleId", moduleId);
		// map存值
		map.put("classifyName", classifyName);
		// 调用查询接口查询参数，条件为map
		List<Map<String,String>> list = dao.query("DFCS01.query", map);
		// 调用接口查询数量，条件为map
		int count = dao.count("DFCS01.count", map);
		// 如果返回list为空
		if(CollectionUtils.isEmpty(list)) {
		    // 返回结果
            return info;
        }
		// 调用接口并返回
		return CommonUtils.BuildOutEiInfo(info, null, null, list, count);
	}
	
	/**
	 * 
	 * @Title: delete
	 * @Description: 设备参数删除(支持多个)
	 * @param info
	 * @return info
	 */
	@Override
	public EiInfo delete(EiInfo info) {
	    // 获取前端传来的id集合
		List<String> list = (List<String>)info.get("list");
		// 调用删除接口
	    dao.delete("DFCS01.deleteItem",list);
	    // 返回
		return info;
	}
	
}
