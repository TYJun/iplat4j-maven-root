package com.baosight.wilp.di.jz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.log.Logger;
import com.baosight.iplat4j.core.log.LoggerFactory;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.di.common.util.DeviceEiUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 巡检新增项目：跳转项目初始化、项目查询
 * <p>1.项目初始化 initLoad
 * <p>2.项目查询 query
 * 
 * @Title: ServiceDIJZ010103.java
 * @ClassName: ServiceDIJZ010103
 * @Package：com.baosight.wilp.di.jz.service
 * @author: LENOVO
 * @date: 2021年8月10日 下午5:08:04
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDIJZ010103 extends ServiceBase {
	
	protected static final Logger logger = LoggerFactory.getLogger(ServiceDIJZ010103.class);
	
	/**
	 * 跳转项目初始化
	 * &lt;p&gt;Title: initLoad&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * <p>调用下面query方法
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return this.query(inInfo);
	}
	
	/**
	 * 项目查询
	 * &lt;p&gt;Title: query&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * @param inInfo
	 * itemName 项目名称
	 * @return
	 * itemId    项目id
	 * content   巡检项目
	 * referenceValue  巡检项目参考值
	 * projectDesc     项目描述
	 * actualValueUnit  实际值单位
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		String[] param = {"itemName"};
		Map<String, Object> buildParam = DeviceEiUtil.buildParam(inInfo, Arrays.asList(param), "result");
		List list = dao.query("DIXM01.queryItem", buildParam);
		int count = dao.count("DIXM01.countItem", buildParam);
		return DeviceEiUtil.buildResult(inInfo, list, count, "result");
	}

}
