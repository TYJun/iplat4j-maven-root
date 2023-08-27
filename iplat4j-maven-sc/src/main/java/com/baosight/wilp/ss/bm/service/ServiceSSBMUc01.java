package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.utils.StringUtil;

import java.util.Map;

/**
 * 
 * USER-CARD对接service
 * */
public class ServiceSSBMUc01 extends ServiceBase{
	
	/**
	 * S_SS_AE_UC_01
	 * userCardBind:绑定
	 * */
	public EiInfo userCardBind(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		Map attr = inInfo.getAttr();
		outInfo.set("接收到的参数attr", attr);
		System.out.println("接收到的参数attr:" + attr);
		String workNo = StringUtil.toString(attr.get("workNo"));
		if(StringUtil.isBlank(workNo)) {
			outInfo.set("respCode", "201");
			outInfo.set("respMsg", "workNo为空");
		}else {
			outInfo.set("respCode", "200");
			outInfo.set("respMsg", "操作成功");
		}
		return outInfo;
	}
}
