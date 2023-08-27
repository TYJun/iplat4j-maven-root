package com.baosight.wilp.ms.dc.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.Map;

/**
 * @title: 显示参数页面 (初始化页面)
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @author 孔帅博
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @date 2021-09-08 15:08
 */
@SuppressWarnings("unchecked")
public class ServiceMSDC0103 extends ServiceBase {

    /**
     * @title: 初始化页面
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:09
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        Map<String, String> map = new HashMap<>();
        map.put("parentIds", inInfo.getAttr().get("id").toString());
        inInfo.setAttr(map);
        return inInfo;
    }
}
