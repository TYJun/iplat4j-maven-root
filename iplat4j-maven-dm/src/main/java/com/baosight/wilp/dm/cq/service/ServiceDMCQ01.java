package com.baosight.wilp.dm.cq.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

/**
 * TODO(宿舍综合查询页面-DMCQ01)
 * 
 * @Title: ServiceDMCQ01.java
 * @ClassName: ServiceDMCQ01
 * @Package：com.baosight.wilp.dm.cq.service
 * @author: 辉
 * @date: 2021年11月24日 下午3:13:15
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDMCQ01 extends ServiceBase{
	
	
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
	    return query(inInfo);
	}
	

    @Override
    public EiInfo query(EiInfo inInfo) {
        return inInfo;
    }
    
}

