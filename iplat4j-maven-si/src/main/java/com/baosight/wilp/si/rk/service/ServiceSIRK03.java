package com.baosight.wilp.si.rk.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.WareHouseDataSplitUtils;
import com.baosight.xservices.xs.util.UserSession;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: TODO
 * @ClassName: ServiceSIRK03
 * @Package com.baosight.wilp.si.rk.service
 * @date: 2022年11月18日 16:20
 *
 * 1.页面加载
 * 2.页面数据查询
 */
public class ServiceSIRK03 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        SiUtils.initQueryTime(inInfo, false);
        inInfo.addBlock(EiConstant.resultBlock).set(EiConstant.limitStr, 50);
        return query(inInfo);
    }

    /**
     * 页面数据查询
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        inInfo.set("inqu_status-0-wareHouseNos", WareHouseDataSplitUtils.getWareHouseNos(UserSession.getUser().getUsername()));
        return super.query(inInfo, "SIRK03.query");
    }
}
