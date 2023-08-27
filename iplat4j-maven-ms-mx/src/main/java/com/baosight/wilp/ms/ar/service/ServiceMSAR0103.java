package com.baosight.wilp.ms.ar.service;


import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ms.common.service.WebSocketService;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO(报警规则弹框相关服务)
 *
 * @Title: ServiceBMBD0201.java
 * @ClassName: ServiceMSAR0101
 * @Package：com.baosight.wilp.bm.bd.service
 * @author: znl
 * @date: 2021/8/6 16:42
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 规则绑定参数页面
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
@SuppressWarnings("unchecked")
public class ServiceMSAR0103 extends ServiceBase {

    /**
     * @description: 页面初始化方法
     * @param inInfo
     * @return EiInfo
     * @throws
     * @author znl
     * @date 2021/8/12 15:50
     * 1、通过inInfo获取查询的参数
     * 2、返回inInfo参数
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     *
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        WebSocketService.TAG_CONFIG_CACHE.clear();
        Map<String, String> map = new HashMap<>();
        map.put("parentIds", inInfo.getAttr().get("id").toString());
        inInfo.setAttr(map);
        return inInfo;
    }
}
