package com.baosight.wilp.mx.pe.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

/**
 * 供配电系统（初始化调用）
 *
 * @author: panlingfeng
 * @createDate: 2021/8/11 6:46 下午
 */
public class ServiceMXPE01 extends ServiceBase {

    /**
     * 初始化调用
     *
     * @return
     * @author panlingfeng
     * @date 2021/8/10 4:14 下午
     * @params
     * 1、分别查询出 实时报警数量，今日解除报警数量，已确认的报警数量，未确认的报警数量
     * 2、封装到EiInfo对象的attr中返回前端
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }
}
