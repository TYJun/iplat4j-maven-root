package com.baosight.wilp.pm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.pm.utils.PMUtils;
/**
 * 
 * 工程项目：初始化查询、人员查询（接口改造）
 * <p>1.初始化查询 initLoad
 * <p>2.人员查询（接口改造） query
 * 
 * @Title: ServicePM0104.java
 * @ClassName: ServicePM0104
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月28日 下午2:40:15
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM0104 extends ServiceBase {

    /**
     * 
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
	 * 2021-08-26:人员查询（接口改造）
	 * @Title: queryDept
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo query(EiInfo info) {
        // 调用分页接口构建map
        Map<String, Object> map = PMUtils.buildParamter(info, "inqu_status", "result");
        // 设置userName
        map.put("userName", map.get("name"));
        // 实例化list
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        // 初始化查询总数为0
        int count = 0;
        // 调用改造人员接口并返回
        EiInfo outInfo = BaseDockingUtils.getStaffAllPage(map, "result");
        // 如果存在人员信息
        if(outInfo.getBlock("result") != null) {
            // 获取人员信息
            list = outInfo.getBlock("result").getRows();
            // 如果list为空
            if(list.isEmpty()) {
                // 返回封装的方法：构建返回结果EiInfo
                return info;
            }
            // 增强循环
            for (Map<String, Object> map2 : list) {
                // 设置phone
                map2.put("phone", map2.get("contactTel"));
            }
            // 获取人员信息总数
            count = (int)outInfo.getBlock("result").getAttr().get("count");
            // 返回封装的方法：构建返回结果EiInfo
            return PMUtils.BuildOutEiInfo(info, "result", PMUtils.createBlockMeta(list.get(0)), list, count);
         }else {
            // 返回封装的方法：构建返回结果EiInfo
            return info;
         }
    }
	
	
}
