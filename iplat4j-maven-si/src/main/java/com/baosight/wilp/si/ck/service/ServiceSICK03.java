package com.baosight.wilp.si.ck.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.si.common.SiUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 出库查询页面Service
 * @ClassName: ServiceSICK03
 * @Package com.baosight.wilp.si.ck.service
 * @date: 2022年11月18日 16:15
 *
 * 1.页面加载
 * 2.页面数据查询
 */
public class ServiceSICK03 extends ServiceBase {

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
        Map<String,Object> map = inInfo.getBlock("result").getAttr();
        inInfo.setCell("result",0,"orderBy",map.get("orderBy"));
        return super.query(inInfo, "SICK03.query");
    }
}
