package com.baosight.wilp.mx.aa.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ms.common.util.PackageOarameters;
import com.baosight.wilp.mx.al.domain.MXAL01;
import org.apache.commons.collections.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 报警信息页面（页面初始化方法、多功能查询方法）
 * @author Wzy
 * @title: ServiceCMAL01
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @date 2021/8/129:56
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceMXAA01 extends ServiceBase {

    /**
     *  报警信息页面 的页面初始化方法
     *
     * @param inInfo
     * @return
     * @author Wzy
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return super.initLoad(inInfo);
    }
}
