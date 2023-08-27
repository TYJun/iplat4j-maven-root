package com.baosight.wilp.cs.re.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;

/**
 * 保洁登记的子页面中"添加保洁事项"按钮带出的保洁事项查询的Service.
 * 一、页面加载.
 * 二、保洁事项的查询.
 *
 * @Title: ServiceCSRE010101
 * @ClassName: ServiceCSRE010101.java
 * @Package：com.baosight.wilp.cs.re.service
 * @author: fangzekai
 * @date: 2021/11/20 12:41
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCSRE010101 extends ServiceBase {

    /**
     * 一、页面加载.
     *
     * @Title: initLoad
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
	public EiInfo initLoad(EiInfo inInfo) {
        return this.query(inInfo);
    }


    /**
     * 二、保洁事项的查询.
     *
     * @Title: query
     * @param inInfo
     * @return EiInfo
     */
    public EiInfo query(EiInfo inInfo) {
        // 调用本地服务CSSX01.query查询保洁事项。
        inInfo.set(EiConstant.serviceName, "CSSX01");
        inInfo.set(EiConstant.methodName, "query");
        EiInfo outInfo = XLocalManager.call(inInfo);
        return outInfo;
    }



}