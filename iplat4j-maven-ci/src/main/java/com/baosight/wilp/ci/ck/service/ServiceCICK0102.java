package com.baosight.wilp.ci.ck.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ci.ck.domain.CiOutDetail;
import com.baosight.wilp.common.util.CommonUtils;

/**
 * 红冲出库子页面Service
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCICK01.java
 * @ClassName:  ServiceCICK01
 * @Package com.baosight.wilp.ci.ck.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月24日 下午2:08:03 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCICK0102 extends ServiceBase {

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
		Map<String,String> pMap = new HashMap<>();
		pMap.put("outBillNo", inInfo.getString("outBillNo"));
		List<CiOutDetail> list = dao.query("CICK0101.query", pMap);
        return CommonUtils.BuildOutEiInfo(inInfo, "result", new CiOutDetail().eiMetadata, list, list.size());
    }
}
