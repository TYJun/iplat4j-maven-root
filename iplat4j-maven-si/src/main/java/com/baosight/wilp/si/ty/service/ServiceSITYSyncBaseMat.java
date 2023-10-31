package com.baosight.wilp.si.ty.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 将物资的出库次数同步到基础档案
 * @ClassName: ServiceSITYSyncBaseMat
 * @Package com.baosight.wilp.si.ty.service
 * @date: 2023年09月12日 9:44
 */
public class ServiceSITYSyncBaseMat extends ServiceBase {

    /**
     * 同步物资出库次数到物资基础档案
     * @Title: syncMatOutFrequency
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo syncMatOutFrequency(EiInfo inInfo) {
        dao.update("SITYSync.syncMat", PlatApplicationContext.getProperty("projectSchema"));
        return inInfo;
    }

}
