package com.baosight.wilp.rm.ly.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 录入领用单子页面Service
 * @ClassName: ServiceRMLY0201
 * @Package com.baosight.wilp.rm.ly.service
 * @date: 2022年09月16日 16:37
 * <p>
 * 1.页面加载
 * 2.保存领用单
 */
public class ServiceRMLY0201 extends ServiceBase {

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
        return inInfo;
    }

    /**
     * 保存领用单
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: save
     **/
    public EiInfo save(EiInfo inInfo) {
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "statusCode", RmConstant.CLAIM_STATUS_UN_OUT);
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "statusName", CommonUtils.getDataCodeItemName("wilp.rm.claim.status", RmConstant.CLAIM_STATUS_UN_OUT));
        return RmUtils.invoke(inInfo, "RMLY0101", "save");
    }
}
