package com.baosight.wilp.si.ck.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.ck.domain.SiOutDetail;

/**
 * 红冲出库子页面Service
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSICK01.java
 * @ClassName:  ServiceSICK01
 * @Package com.baosight.wilp.si.ck.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月24日 下午2:08:03 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSICK0102 extends ServiceBase {

	/**
	 * 页面加载
	 * <p>Title: initLoad</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	@SuppressWarnings("unchecked")
    public EiInfo initLoad(EiInfo inInfo) {
		return this.query(inInfo);
	}

	public EiInfo query(EiInfo inInfo) {
		Map<String,String> pMap = new HashMap<>();
		//页面排序
		if (inInfo.getBlock("result")!=null){
			pMap.put("orderBy",(String) inInfo.getBlock("result").getAttr().get("orderBy"));
		}

		pMap.put("outBillNo", inInfo.getString("outBillNo"));
		pMap.put("showRedRush", "Y");
		List<SiOutDetail> list = dao.query("SICK0101.query", pMap);
		return CommonUtils.BuildOutEiInfo(inInfo, "result", new SiOutDetail().eiMetadata, list, list.size());
	}
}
