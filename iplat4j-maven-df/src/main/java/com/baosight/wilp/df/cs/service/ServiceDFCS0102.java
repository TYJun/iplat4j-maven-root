package com.baosight.wilp.df.cs.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * 设备参数界面：初始化查询、设备参数查询、设备参数更新
 * <p>1.初始化查询 initLoad
 * <p>2.设备参数查询 queryParam
 * <p>3.设备参数更新 update
 * 
 * @Title: ServiceDFCS0102.java
 * @ClassName: ServiceDFCS0102
 * @Package：com.baosight.wilp.df.cs.service
 * @author: zhaow
 * @date: 2021年8月10日 下午5:11:19
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFCS0102 extends ServiceBase{
	/**
	 * 
	 * @Title: initLoad
	 * @Description: 初始化查询
	 * @param info
	 * paramKey : 参数编码
	 * @return info
	 * id : 设备分类参数id
     * moduleId : 参数所属分类ID
     * paramName : 参数名称
     * paramKey : 参数编码
     * paramValue : 参数值
     * paramUnit : 参数单位
     * memo : 备注
     * classifyName : 设备分类名称
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
	    // 获取参数编码
		String paramKey = (String)info.getAttr().get("paramKey");
		// info赋值参数
		info.set("paramKey",paramKey);
		// 调用设备参数查询
        return queryParam(info);
	}
	
	/**
	 * 
	 * @Title: queryParam 
	 * @Description: 设备参数查询
	 * @param info
	 * paramKey : 参数编码
	 * @return info
	 * id : 设备分类参数id
     * moduleId : 参数所属分类ID
     * paramName : 参数名称
     * paramKey : 参数编码
     * paramValue : 参数值
     * paramUnit : 参数单位
     * memo : 备注
     * classifyName : 设备分类名称
	 */
	public EiInfo queryParam(EiInfo info) {
	    // 获取参数编码
		String paramKey = (String)info.getAttr().get("paramKey");
		// 实例化map
        Map<String, String> map = new HashMap<>(2);
        // map赋值paramKey
        map.put("paramKey", paramKey);
        // 调用查询方法，参数map
        List<Map<String,String>> list = dao.query("DFCS0102.queryParam",map);
        // 如果list为空
        if(CollectionUtils.isEmpty(list)) {
            // 返回
            return info;
        }
        // info赋值list第一个下标
		info.setAttr(list.get(0));
		// 返回
		return info;
	}
	
	/**
	 * 
	 * @Title: update
	 * @Description: 设备参数更新
	 * @param info
	 * paramKey : 参数编码
	 * paramName : 参数名称
	 * paramValue : 参数值
	 * paramUnit : 参数单位
	 * memo : 备注
	 * @return info
	 * 
	 */
	@Override
	public EiInfo update(EiInfo info) {
	    // 实例化map
		Map<String, String> map = new HashMap<String, String>(8);
		// 获取参数编码
		String paramKey = (String)info.getAttr().get("paramKey");
		// 获取参数名称
		String paramName = (String)info.getAttr().get("paramName");
		// 获取参数值
		String paramValue = (String)info.getAttr().get("paramValue");
		// 获取参数单位
		String paramUnit = (String)info.getAttr().get("paramUnit");
		// 获取备注
		String memo = (String)info.getAttr().get("memo");
		// map赋值paramKey
		map.put("paramKey", paramKey);
		// map赋值paramName
		map.put("paramName", paramName);
		// map赋值paramValue
		map.put("paramValue", paramValue);
		// map赋值paramUnit
		map.put("paramUnit", paramUnit);
		// map赋值memo
		map.put("memo", memo);
		// 调用更新接口
		dao.update("DFCS0102.update",map);
		// 返回
		return info;
	}
}
