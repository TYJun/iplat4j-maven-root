package com.baosight.wilp.rm.tk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.rm.common.RmConstant;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资退库录入页面Service
 * @ClassName: ServiceRMTK0203
 * @Package com.baosight.wilp.rm.tk.service
 * @date: 2022年12月21日 18:27
 */
public class ServiceRMTK0203 extends ServiceBase {

    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "recCreator", UserSession.getLoginName());
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "recCreatorName", UserSession.getLoginCName());
        return inInfo;
    }
}
