package com.baosight.wilp.mp.dd.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.lj.domain.MpPurchaseOrder;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 采购订单入库管理页面Service
 * @ClassName: ServiceMPDD02
 * @Package com.baosight.wilp.mp.dd.service
 * @date: 2022年10月13日 13:15
 *
 * 1.页面加载
 * 2.数据查询
 */
public class ServiceMPDD02 extends ServiceBase {

    /**
     * 页面加载
     * <p>Title: initLoad</p>
     * <p>Description: </p>
     * @param info
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
    @Override
    public EiInfo initLoad(EiInfo info){
        MpUtils.initQueryTime(info, "beginTime", "endTime");
        return this.query(info);
    }

    /**
     * 页面数据查询
     * @Title: query
     * @param info info
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo info){
        info.setCell(MpConstant.QUERY_BLOCK, 0, "statusCodes", new String[]{"01","10"});
        return super.query(info, "MPLJ03.query", new MpPurchaseOrder());
    }
}
