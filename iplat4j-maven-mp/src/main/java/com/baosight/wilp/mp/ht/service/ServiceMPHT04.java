package com.baosight.wilp.mp.ht.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.lj.domain.MpContract;

/**
 * 合同管理台账页面
 * <p>1.初始化查询 initLoad
 * @Title: ServiceMPHT04.java
 * @ClassName: ServiceMPHT04
 * @Package：com.baosight.wilp.mp.ht.service
 * @author: lyf
 * @date: 2022年10月19日 上午9:41:58
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 * 1.页面加载
 * 2.页面数据查询
 */
public class ServiceMPHT04 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * @param inInfo
     */
    @Override
	public EiInfo initLoad(EiInfo inInfo) {
		// 设置info中的id
		MpUtils.initQueryTime(inInfo,"recCreateTimeS","recCreateTimeE");
		return this.query(inInfo);
	}

	/**
	 * 页面数据查询
	 * @Title: query
	 * @param info info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	@Override
	public EiInfo query(EiInfo info){
		return super.query(info, "MPLJ02.query", new MpContract());
	}
}
