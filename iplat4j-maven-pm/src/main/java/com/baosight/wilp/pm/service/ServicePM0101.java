package com.baosight.wilp.pm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.pm.utils.PMUtils;
/**
 * 
 * 工程项目：初始化查询、查询科室（接口改造）
 * <p>1.初始化查询 initLoad
 * <p>2.工程项目数据查询 query
 * 
 * @Title: ServicePM0101.java
 * @ClassName: ServicePM0101
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月27日 上午9:43:45
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM0101 extends ServiceBase {

    /**
     * 
     * @Title: initLoad
     * @Description: 初始化查询
     * @param info
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
	public EiInfo initLoad(EiInfo inInfo) {
	    // 调用初始化方法
		return super.initLoad(inInfo);
	}
	
	/**
	 * 2021-08-26：查询科室（接口改造）
	 * @Title: queryDept
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
    public EiInfo queryDept(EiInfo info) {
        // 调用分页接口构建map
        Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "dept");
        // 实例化List
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        // 初始化总数为0
        int count = 0;
        // 调用远程服务获取改造科室接口
        EiInfo outInfo = BaseDockingUtils.getDeptAllPage(map, "dept");
        // 如果存在科室信息
        if(outInfo.getBlock("dept") != null) {
            // 科室信息存储在list
            list = outInfo.getBlock("dept").getRows();
            // 如果list为空
            if(list.isEmpty()) {
                // 返回封装的方法：构建返回结果EiInfo
                return info;
            }
            // 科室信息总数存在count
            count = (int)outInfo.getBlock("dept").getAttr().get("count");
            // 返回封装的方法：构建返回结果EiInfo
            return PMUtils.BuildOutEiInfo(info, "dept", PMUtils.createBlockMeta(list.get(0)), list, count);
        }else {
            // 返回封装的方法：构建返回结果EiInfo
            return info;
        }
    }
}
