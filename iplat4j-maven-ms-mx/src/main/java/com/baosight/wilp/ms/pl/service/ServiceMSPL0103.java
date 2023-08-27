package com.baosight.wilp.ms.pl.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.Map;

/**  
 * @title: 移动弹窗（页面初始化）
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @author 孔帅博
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @date 2021-09-08 15:34 
 */
@SuppressWarnings("unchecked")
public class ServiceMSPL0103 extends ServiceBase {
    
    /**
     * @title: 页面初始化方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * 1、通过inInfo获取查询的参数id
     * 2、返回inInfo参数
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:33
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        Map<String, String> map = new HashMap<>();
        map.put("id", inInfo.getAttr().get("id").toString());
        inInfo.setAttr(map);
        return inInfo;
    }

}
