

package com.baosight.wilp.dm.rm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

/**
 * TODO(这里用一句话描述这个方法的作用)
 * 
 * @Title: ServiceDMRM0101.java
 * @ClassName: ServiceDMRM0101
 * @Package：com.baosight.wilp.dm.rm.service
 * @author: 辉
 * @date: 2021年11月24日 下午4:00:06
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDMRM0101 extends ServiceBase{
	
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		Map<String, String> map = new HashMap<>();
		map.put("id", inInfo.getAttr().get("id").toString());
		String type = inInfo.getAttr().get("type").toString();
		if("edit".equals(type)) {
			List<Map<String, Object>> list = dao.query("dMRM01.query", map);
			if (!CollectionUtils.isEmpty(list)) {
				list.get(0).put("type", type);
				inInfo.setAttr(list.get(0));
			}
		}
		return inInfo;
	}
}

