package com.baosight.wilp.rm.xq.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 需求计划申请流程打印Service
 * @ClassName: ServiceRMXQ0501
 * @Package com.baosight.wilp.rm.xq.service
 * @date: 2023年10月13日 16:11
 */
public class ServiceRMXQ0501 extends ServiceBase {

    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }

    /**
     * 查询需求计划流程
     * @Title: queryRequireFlow
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryRequireFlow(EiInfo inInfo) {
        List<Map<String, String>> list = dao.query("RMLJ01.queryRequireFlow", inInfo.getString("planNo"));
        inInfo.set("parameters", list.get(0));
        return inInfo;
    }
}
