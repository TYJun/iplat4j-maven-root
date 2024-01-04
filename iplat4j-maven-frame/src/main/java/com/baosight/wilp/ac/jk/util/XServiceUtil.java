package com.baosight.wilp.ac.we.utils;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XServiceManager;

import java.util.Map;
import java.util.Set;


/**
 * Todo(远程service工具类)
 *
 * @Title: XServiceUtil
 * @ClassName: XServiceUtil.java
 * @Package：com.baosight.wilp.ss.bm.utils
 * @author: xiajunqing
 * @date: 2021/11/20 11:31
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class XServiceUtil {

	
	/**
	 * call:调用方法
	 * @param  serviceId:服务名
	 * @param  paramMap:传入参数
	 * @return EiInfo outInfo
	 * */
	public static EiInfo call(String serviceId,Map<String,Object> paramMap){
		EiInfo ei = new EiInfo();
		ei.set(EiConstant.serviceId, serviceId);
		Set<String> keySet = paramMap.keySet();
		//处理参数
		for (String key : keySet) {
			ei.set(key, paramMap.get(key)); 
		}
		//调用服务接口
		return XServiceManager.call(ei);
	}

}
