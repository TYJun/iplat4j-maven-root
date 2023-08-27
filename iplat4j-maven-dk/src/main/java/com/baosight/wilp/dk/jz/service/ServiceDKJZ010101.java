package com.baosight.wilp.dk.jz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.dk.common.util.DeviceEiUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 保养基准新增选择设备：设备页面初始化、设备查询
 * <p>1.设备页面初始化 initLoad
 * <p>2.设备查询 query
 * 
 * @Title: ServiceDKJZ010101.java
 * @ClassName: ServiceDKJZ010101
 * @Package：com.baosight.wilp.dk.jz.service
 * @author: LENOVO
 * @date: 2021年9月14日 下午2:54:42
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDKJZ010101 extends ServiceBase{

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
     * <p>1.将传递参数分装map
     * <p>2.使用map参数查询获取保养设备list和count
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
	@Override
	public EiInfo query(EiInfo inInfo) {
		//1.将传递参数分装map
		String[] param = {"machineName","machineCode"};
		Map<String, Object> buildParam = DeviceEiUtil.buildParam(inInfo, Arrays.asList(param), "result");
		//2.使用map参数查询获取保养设备list和count
		List list = dao.query("DKJZ0101.queryMachine", buildParam);
		//3.获取设备总数
		int count = dao.count("DKJZ0101.countMachine", buildParam);
		return DeviceEiUtil.buildResult(inInfo, list, count, "result");
	}
	
}
