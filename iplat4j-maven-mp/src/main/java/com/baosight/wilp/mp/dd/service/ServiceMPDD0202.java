package com.baosight.wilp.mp.dd.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.lj.domain.MpPurchaseOrderDetail;
import com.baosight.wilp.mp.lj.service.MpPurchaseOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 订单直入直出子页面Service
 * @ClassName: ServiceMPDD0202
 * @Package com.baosight.wilp.mp.dd.service
 * @date: 2022年10月13日 15:13
 *
 * 1.页面加载
 * 2.直入直出
 */
public class ServiceMPDD0202 extends ServiceBase {

    @Autowired
    private MpPurchaseOrderService orderService;

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return MpUtils.invoke(inInfo, "MPDD0102", "initLoad");
    }
}
