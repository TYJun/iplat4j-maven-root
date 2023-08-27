package com.baosight.wilp.dm.fm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.dm.fm.domain.DMFM01;


/**
 * TODO(这里用一句话描述这个方法的作用)
 * 
 * @Title: ServiceDMFM03.java
 * @ClassName: ServiceDMFM03
 * @Package：com.baosight.wilp.dm.fm.service
 * @author: 辉
 * @date: 2021年11月24日 下午3:59:02
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDMFM03 extends ServiceBase{
	
	
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
	    return query(inInfo);
	}
	

    @Override
    public EiInfo query(EiInfo inInfo) {
    	EiInfo outInfo = super.query(inInfo, "DMFM03.query", new DMFM01());
		return outInfo;
    }
    

}

