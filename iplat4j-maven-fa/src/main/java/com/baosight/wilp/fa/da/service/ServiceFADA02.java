package com.baosight.wilp.fa.da.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

/**
 * 固定资产档案逻辑
 * 1.固定资产档案初始化方法.
 * @author zhaowei
 * @date 2022年06月29日 15:14
 */
public class ServiceFADA02 extends ServiceBase {
	/**
	 * 固定资产档案初始化方法.
	 * @author zhaowei
	 * @date 2022/10/9 17:32
	 * @param info 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo info){
		return info;
	}
}