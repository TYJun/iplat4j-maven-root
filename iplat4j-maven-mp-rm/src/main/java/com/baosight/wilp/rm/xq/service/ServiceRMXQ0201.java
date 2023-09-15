package com.baosight.wilp.rm.xq.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 月度需求计划子页面Service
 * @ClassName: ServiceRMXQ0101
 * @Package com.baosight.wilp.rm.xq.service
 * @date: 2022年09月13日 10:37
 * <p>
 * 1.页面加载
 * 2.保存月度计划
 */
public class ServiceRMXQ0201 extends ServiceBase {

    /**
     * 页面加载
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: initLoad
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "planTime", DateUtils.curDateStr("yyyy-MM"));
        return RmUtils.invoke(inInfo, "RMXQ0101", "initLoad");
    }

    /**
     * 保存月度需求计划
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: save
     **/
    public EiInfo save(EiInfo inInfo) {
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "planType", RmConstant.PLAN_TYPE_MONTH);
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "planTypeName",
                CommonUtils.getDataCodeItemName("wilp.rm.require.planType", RmConstant.PLAN_TYPE_MONTH));
        return RmUtils.invoke(inInfo, "RMXQ0101", "save");
    }
}
