package com.baosight.wilp.im.jz.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.im.common.util.DeviceEiUtil;

/**
 * 巡检新增人员：跳转人员初始化、人员查询
 * <p>1.跳转人员初始化 initLoad
 * <p>2.人员查询 query
 * @Title: ServiceDIJZ010104.java
 * @ClassName: ServiceDIJZ010104
 * @Package：com.baosight.wilp.di.jz.service
 * @author: LENOVO
 * @date: 2021年8月10日 下午5:15:45
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceIMJZ010104 extends ServiceBase {
	
    /**
     * 跳转人员初始化
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
	 * 人员查询
	 * &lt;p&gt;Title: query&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * <p>数据对接获取人员信息
	 * @param inInfo
	 * workNo   人员编号
	 * userName 人员名称
	 * deptName 科室名称
	 * @return
	 * workNo   人员编号
	 * name     人员名称
	 * deptName 科室名称
	 * deptNum  科室编号
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	public EiInfo query(EiInfo inInfo) {
		String[] param = {"workNo","userName","deptName"};
		Map<String, Object> buildParam = DeviceEiUtil.buildParam(inInfo, Arrays.asList(param), "result");
//		List list = dao.query("DIJZ0101.queryMan", buildParam);
//		int count = dao.count("DIJZ0101.countMan", buildParam);
//		return DeviceEiUtil.buildResult(inInfo, list, count, "result");
		//数据对接获取人员信息
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		int count=0;
		EiInfo outInfo=BaseDockingUtils.getStaffAllPage(buildParam, "result");
		if(outInfo.getBlock("result")!=null) {
		  list=outInfo.getBlock("result").getRows();
		  count=(int)outInfo.getBlock("result").getAttr().get("count");
		     return DeviceEiUtil.buildResult(inInfo, list, count, "result");
		}else {
		     return DeviceEiUtil.buildResult(inInfo, list, count, "result");
		}
	}

}
