package com.baosight.wilp.si.rk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.rk.domain.SiEnterDetail;

/**
 * 红冲入库子页面Service
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSIRK0103.java
 * @ClassName:  ServiceSIRK0103
 * @Package com.baosight.wilp.si.rk.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月23日 下午4:44:48 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSIRK0103 extends ServiceBase {

	private static final String  enterTypeName = "直入直出";

	/**
	 * 页面加载
	 * <p>Title: initLoad</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * @return   
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	@SuppressWarnings("unchecked")
    public EiInfo initLoad(EiInfo inInfo) {
		Map<String,String> pMap = new HashMap<>(4);
		pMap.put("enterBillNo", inInfo.getString("enterBillNo"));
		pMap.put("showRedRush", "Y");
		List<SiEnterDetail> list = dao.query("SIRK0101.query", pMap);
		if(enterTypeName.equals(inInfo.getString("enterTypeName"))) {
			list.forEach(d -> d.setRedRushNum(d.getEnterNum()));
		}
        return CommonUtils.BuildOutEiInfo(inInfo, "result", new SiEnterDetail().eiMetadata, list, list.size());
    }
}
