package com.baosight.wilp.df.cs.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.df.common.domain.ParamKey;
import com.baosight.wilp.df.common.util.ParamKeyCode;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/**
 * 
 * 设备参数界面：初始化查询、设备参数添加
 * <p>1.初始化查询 initLoad
 * <p>2.设备参数添加 insert
 * 
 * @Title: ServiceDFCS0101.java
 * @ClassName: ServiceDFCS0101
 * @Package：com.baosight.wilp.df.cs.service
 * @author: zhaow
 * @date: 2021年8月10日 下午5:03:22
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFCS0101 extends ServiceBase{
    /**
     * 
     * @Title: initLoad
     * @Description: 初始化查询
     * @param info
     * @return info
     */
	@Override
	public EiInfo initLoad(EiInfo info) {
	    // 返回
		return info;
	}
	
	/**
	 * 
	 * @Title: insert
	 * @Description: 设备参数添加
	 * @param info
	 * id : 设备分类参数id
	 * moduleId : 参数所属分类ID
	 * paramName : 参数名称
	 * paramKey : 参数编码
	 * paramValue : 参数值
	 * paramUnit : 参数单位
	 * memo : 备注
	 * @return info
	 */
	@Override
	public EiInfo insert(EiInfo info) {
	    // 实例化map
		Map<String,String> map = new HashMap<String,String>();
		// 生产uuid
		String id = UUID.randomUUID().toString();
		// 获取参数所属分类id
		String moduleId = (String) info.get("moduleId");
		// 获取参数名称
		String paramName = (String) info.get("paramName");
		// 获取参数编码
		String paramKey = ParamKeyCode.geneCode(ParamKey.DF_CLASSFYPARAM);
		// 获取参数值
		String paramValue = (String) info.get("paramValue");
		// 获取参数单位
		String paramUnit = (String) info.get("paramUnit");
		// 获取备注
		String memo = (String) info.get("memo");
		// map赋值id
		map.put("id", id);
		// map赋值moduleId
		map.put("moduleId", moduleId);
		// map赋值paramName
		map.put("paramName", paramName);
		// map赋值paramKey
		map.put("paramKey", paramKey);
		// map赋值paramValue
		map.put("paramValue", paramValue);
		// map赋值paramUnit
		map.put("paramUnit", paramUnit);
		// map赋值memo
		map.put("memo", memo);
		// 接口调用，参数为map
		dao.insert("DFCS0101.insert",map);
		// 返回
		return info;
	}

	
}
