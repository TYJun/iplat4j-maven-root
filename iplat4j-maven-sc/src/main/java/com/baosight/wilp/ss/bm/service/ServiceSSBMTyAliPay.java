package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ss.bm.utils.RespResult;

import java.util.Map;

/**
 * 支付hessian回调接口
 * 
 * @declaration
 * @author ldz
 * @datetime 2016年3月28日 下午2:47:19
 * @version 2016
 */
public class ServiceSSBMTyAliPay extends ServiceBase {


	public RespResult createPosScanPay(Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
     * 1. EDXM01微服务配置S_SS_BM_ALI_01,服务:SSBMTyAliPay,方法:mealNotify
     * 2. EDCC03配置信息管理配置名称:iplat.security.anonymous.url
     *    配置内容加入:,/service/S_SS_BM_ALI_01/**
     * 3. httpPost请求路径http://127.0.0.1:8083/WILP/service/S_SS_BM_ALI_01
	 *
	 * @Title: mealNotify 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午4:30:51
	 */
	public EiInfo mealNotify(EiInfo inInfo) {

		Object object = inInfo.getAttr().get("billNo");
		String loginName = UserSession.getLoginName();
		System.out.println(loginName);
		EiInfo eiInfo = new EiInfo();
		eiInfo.set("billNo", object);
		eiInfo.set("respCode", "200");
		eiInfo.set("respMsg", "操作成功");
		return eiInfo;
	}
}
