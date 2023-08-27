package com.baosight.wilp.mp.fp.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.lj.domain.MpPurchaseOrder;
import com.baosight.wilp.mp.lj.domain.MpPurchaseOrderDetail;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 发票管理采购计划明细选择子页面Service
 * @ClassName: ServiceMPFP0101
 * @Package com.baosight.wilp.mp.fp.service
 * @date: 2022年10月14日 14:50
 *
 * 1.页面加载
 * 2.查询采购计划
 * 3.查询采购计划明细
 */
public class ServiceMPFP0102 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "statusCode", MpConstant.ORDER_STATUS_ENTER);
        return inInfo;
    }

    /**
     * 查询采购计划
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        return super.query(inInfo, "MPLJ03.query", new MpPurchaseOrder());
    }

    /**
     * 查询采购计划明细
     * @Title: queryDetail
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryDetail(EiInfo inInfo) {
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "hasInvoice", 0);
        return super.query(inInfo, "MPLJ03.queryDetail", new MpPurchaseOrderDetail(), false, null,null, "detail","detail");
    }
}
