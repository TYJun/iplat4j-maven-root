package com.baosight.wilp.hr.sp.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.hr.dp.domain.HrJobChangeBill;

/**
 *人员配置页面
 * <p>1.初始化查询 initLoad
 * <p>2.项目查询 query
 *
 * @Title: ServiceHRSP02.java
 * @ClassName: ServiceHRSP02
 * @Package：com.baosight.wilp.hr.sp.service
 * @author: gcc
 * @date: 2022年4月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceHRSP02 extends ServiceBase {
	/**
	 * 初始化查询
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param info
	 * @return info
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo initLoad(EiInfo info) {
		return this.query(info);
	}

	/**
	 * 页面查询
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param info
	 * flag sp 审批页面
	 * @return
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo query(EiInfo info) {
		// 设置flag值表示审批页面
		info.set("inqu_status-0-flag","sp");
		EiInfo outInfo = super.query(info,"HRDP01.query",new HrJobChangeBill());
		return outInfo;
	}

}
