package com.baosight.wilp.si.rk.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.rk.domain.SiEnter;
import com.baosight.xservices.xs.util.UserSession;

/**
 * 仓库入库管理页面Service
 * 
 * <p>页面加载</p>
 * <p>页面查询</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSIRK01.java
 * @ClassName:  ServiceSIRK01
 * @Package com.baosight.wilp.si.rk.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月23日 下午1:37:58 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSIRK02 extends ServiceBase {
	
	/**
	 * 页面加载
	 * <p>Title: initLoad</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * @return   
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
    public EiInfo initLoad(EiInfo inInfo) {
		SiUtils.initQueryTime(inInfo, true);
		inInfo.addBlock(EiConstant.resultBlock).set(EiConstant.limitStr, 20);
		return query(inInfo);
    }
	
	/**
	 * 页面查询
	 * <p>Title: query</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * 		enterBillNo:入库单号
	 * 		enterType:入库类别
	 * 		beginTime:制单日期起（>=）
	 * 		endTime:制单日期止（<=）
	 * @return   
	 * 		id ：主键
	 *		enterBillNo : 入库单号
	 *		enterType : 入库类别
	 *		originBillNo : 来源单据号
	 *		wareHouseName : 仓库
	 *		billMakeTime : 制单日期
	 *		billMakerName : 制单人员
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
    	inInfo.set("inqu_status-0-dataGroupCode", SiUtils.getDataGroupCode(UserSession.getUser().getUsername()));
        EiInfo outInfo = super.query(inInfo, "SIRK01.query", new SiEnter());
        return outInfo;
    }

}
