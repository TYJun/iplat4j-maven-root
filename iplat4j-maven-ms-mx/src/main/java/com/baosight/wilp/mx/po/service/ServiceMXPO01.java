package com.baosight.wilp.mx.po.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

/**
 * 供配电系统（初始化调用、数据查询）
 *
 * @author: panlingfeng
 * @createDate: 2021/8/11 6:46 下午
 */
public class ServiceMXPO01 extends ServiceBase {

    /**
     * 初始化调用
     *
     * @return
     * @author panlingfeng
     * @date 2021/8/10 4:14 下午
     * @params
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }
}
