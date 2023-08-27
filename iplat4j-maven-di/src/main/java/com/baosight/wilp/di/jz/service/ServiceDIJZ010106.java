package com.baosight.wilp.di.jz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.log.Logger;
import com.baosight.iplat4j.core.log.LoggerFactory;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.di.jz.domain.DiCard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基准增加卡片：基准新增跳转卡片
 * 
 * @Title: ServiceDIJZ010106.java
 * @ClassName: ServiceDIJZ010106
 * @Package：com.baosight.wilp.di.jz.service
 * @author: LENOVO
 * @date: 2021年8月13日 下午2:09:52
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDIJZ010106 extends ServiceBase{
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(ServiceDIJZ010106.class);
	
	/**
	 * 跳转卡片初始化
	 * &lt;p&gt;Title: initLoad&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * @param info
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		
		 return this.query(info);
	}

	/**
	 * 查询卡片
	 * &lt;p&gt;Title: query&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
	    EiInfo outInfo = super.query(inInfo, "DIJZ010106.query", new DiCard());
        return outInfo;
	}

	public EiInfo queryProjectInfo(EiInfo inInfo) {
		Map<String,String> parm=new HashMap<>();
		String cardId=(String)inInfo.get("cardid");
		parm.put("id", cardId);
		List<Map<String,Object>> itemList=dao.query("DIJZ010106.getProjectInfo",parm);
		inInfo.set("param", itemList);
		return inInfo;
	}

}
