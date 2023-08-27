package com.baosight.wilp.mp.ht.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.lj.domain.MpContInvoice;



/**
 * 采购付款选择发票子页面
 * <p>1.初始化查询 initLoad
 * @Title: ServiceMPHT0302.java
 * @ClassName: ServiceMPHT0302
 * @Package：com.baosight.wilp.mp.ht.service
 * @author: lyf
 * @date: 2022年10月19日 上午9:41:58
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 * 1.页面加载
 * 2.页面数据查询
 */

public class ServiceMPHT0302 extends ServiceBase {

	/**
	 *
	 * @Title: initLoad
	 * @Description: 页面初始化
	 * @param
	 * @return info
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		return this.query(info);
	}

	/**
	 * 页面数据查询
	 * @Title: query
	 * @param info info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	@Override
	public EiInfo query(EiInfo info) {
		info.setCell(MpConstant.QUERY_BLOCK,0,"statusCode", MpConstant.INVOICE_STATUS_SUBMIT);
		return super.query(info, "MPLJ05.query", new MpContInvoice());
	}

}
