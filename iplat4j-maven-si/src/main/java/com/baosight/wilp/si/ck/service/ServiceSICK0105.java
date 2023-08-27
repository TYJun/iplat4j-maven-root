package com.baosight.wilp.si.ck.service;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.kc.domain.SiStorge;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 随机红冲出库
 * @ClassName: ServiceSICK0104
 * @Package com.baosight.wilp.si.ck.service
 * @date: 2022年12月09日 17:07
 *
 * 1.页面加载
 * 2.查询库存物资
 */
public class ServiceSICK0105 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        inInfo.addBlock("mat").set(EiConstant.limitStr, 20);
        return queryMat(inInfo);
    }

    /**
     * 查询库存物资
     * @Title: queryMat
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryMat(EiInfo inInfo) {
        EiInfo query = super.query(inInfo, "SICK0105.query", (DaoEPBase) null, false, null,null, "mat", "mat");
        return query;
    }
}
