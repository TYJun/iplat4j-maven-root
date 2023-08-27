package com.baosight.wilp.pm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.pm.utils.PMUtils;
/**
 * 工程项目：初始化查询、物资查询（接口改造）
 * <p>1.初始化查询 initLoad
 * <p>2.物资查询（接口改造） query
 * 
 * @Title: ServicePM0202.java
 * @ClassName: ServicePM0202
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月28日 下午3:34:14
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM0202 extends ServiceBase {

	/**
	 * @Title: initLoad
     * @Description: 初始化查询
     * @param inInfo
     * @return inInfo
     * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo initLoad(EiInfo inInfo) {
	    // 调用初始化方法
		return super.initLoad(inInfo);
	}
	
	/**
     * @Title: query
     * @Description: 物资查询（接口改造）
     * @param:  @param inInfo
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
	public EiInfo query(EiInfo info) {
	    //获取参数
	    Map<String, Object> map = PMUtils.buildParamter(info, "inqu_status", "result");
	    // 转化参数
	    map.put("matCode", map.get("matNum"));
	    // 调用远程服务获取材料信息接口
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        // 初始化查询总数为0
	    int count = 0;
	    // 调用改造物资接口并返回
        EiInfo outInfo = BaseDockingUtils.getMateriaAllPage(map, "result");
        // 如果存在物资信息
        if(outInfo.getBlock("result") != null) {
            // 获取物资信息
            list = outInfo.getBlock("result").getRows();
            // 获取物资信息总数
            count = (int)outInfo.getBlock("result").getAttr().get("count");
            // 增强循环
            for (Map<String, Object> map2 : list) {
                // 设置参数
                map2.put("matNum", map2.get("matCode"));
                map2.put("spec", map2.get("matSpec"));
                map2.put("unitName", map2.get("unit"));
                map2.put("supType", map2.get("supplier"));
            }
            // 返回封装的方法：构建返回结果EiInfo
            return PMUtils.BuildOutEiInfo(info, "result", PMUtils.createBlockMeta(list.get(0)), list, count);
        }else {
            // 返回封装的方法：构建返回结果EiInfo
            return info;
        }
	}
}
