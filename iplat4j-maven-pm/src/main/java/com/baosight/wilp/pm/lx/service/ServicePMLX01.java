package com.baosight.wilp.pm.lx.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

/**
 *
 */
public class ServicePMLX01 extends ServiceBase {
	/**
	 * 初始化方法
	 * @param info
	 * @return
	 */
	public EiInfo initLoad(EiInfo info){
		return this.query(info);
	}

	/**
	 * 查询工程项目
	 * @param info
	 * @return
	 */
	public EiInfo query(EiInfo info){
		return info;
	}

}
