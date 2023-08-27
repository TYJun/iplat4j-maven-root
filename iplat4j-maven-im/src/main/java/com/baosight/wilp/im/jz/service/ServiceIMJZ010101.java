package com.baosight.wilp.im.jz.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.im.common.util.DeviceEiUtil;

/**
 * 巡检基准新增选择设备：设备页面初始化、设备查询
 * <p>1.设备页面初始化 initLoad
 * <p>2.设备查询 query
 * @Title: ServiceDIJZ010101.java
 * @ClassName: ServiceDIJZ010101
 * @Package：com.baosight.wilp.di.jz.service
 * @author: LENOVO
 * @date: 2021年8月10日 下午4:47:39
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceIMJZ010101 extends ServiceBase {

    /**
     * 设备页面初始化
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
	 * 设备查询
	 * &lt;p&gt;Title: query&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * @param inInfo
	 * machineName 设备名称
	 * machineCode 设备编号
	 * @return
	 * machineCode 设备编号
	 * machineName 设备名称
	 * fixedPlace  设备地址
	 * models      规格型号
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	public EiInfo query(EiInfo inInfo) {
		String[] param = {"objName","spotName"};
		Map<String, Object> buildParam = DeviceEiUtil.buildParam(inInfo, Arrays.asList(param), "result");
		List list = dao.query("IMJZ0101.queryMachine", buildParam);
		int count = dao.count("IMJZ0101.countMachine", buildParam);
		return DeviceEiUtil.buildResult(inInfo, list, count, "result");
	}
	
}
