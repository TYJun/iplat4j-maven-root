package com.baosight.wilp.cp.pz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2023年06月16日 19:14
 */
public class ServiceCPPZ0101 extends ServiceBase {
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return super.initLoad(inInfo);
	}

	public EiInfo saveDeptInfo(EiInfo info){
		dao.insert("CPPZ01.saveDeptInfo",info.getAttr());
		return info;
	}
}
