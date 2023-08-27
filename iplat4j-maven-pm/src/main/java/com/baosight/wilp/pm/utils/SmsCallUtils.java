package com.baosight.wilp.pm.utils;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.service.soa.XLocalManager;

public class SmsCallUtils {
	
	/**
	 * 调用短信发送方法
	 * @Title: smsCall
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param configType
	 * @param:  @param projectId
	 * @return: void  
	 * @throws
	 */
	public static void smsCall(String configType, String projectId) {
		EiInfo inInfo = new EiInfo();
		//设置服务名
		inInfo.set(EiConstant.serviceName, "PMSms");
		//设置方法名
		inInfo.set(EiConstant.methodName, "sendMessage");
		//设置参数
		inInfo.set("configType", configType);
		inInfo.set("projectId", projectId);
		//服务调用
		EiInfo outInfo = XLocalManager.call(inInfo);
		if(outInfo.getStatus()<0){
			throw new PlatException(outInfo.getMsg());
		}
	}

}
