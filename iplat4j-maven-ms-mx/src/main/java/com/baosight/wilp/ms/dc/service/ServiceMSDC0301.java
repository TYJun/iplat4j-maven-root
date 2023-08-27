package com.baosight.wilp.ms.dc.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: 区域配置新增区域弹窗
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @author 孔帅博
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @date 2021-09-08 15:09
 */
public class ServiceMSDC0301 extends ServiceBase {

    /**
     * @title: 页面初始化方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:10
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        /**
         * 通过inInfo获取参数
         */
        String type = inInfo.getAttr().get("type").toString();
        String parentId = inInfo.getAttr().get("parentId").toString();
        Map<String, String> map = new HashMap<>();
        map.put("id", inInfo.getAttr().get("id").toString());
        if (StringUtils.isNotEmpty(inInfo.getAttr().get("id").toString())) {
            List<Map<String, String>> list = dao.query("MSDC03.queryById", map);
            if (!CollectionUtils.isEmpty(list)) {
                list.get(0).put("type", type);
                list.get(0).put("parentId", parentId);
                inInfo.setAttr(list.get(0));
            }
            return inInfo;
        }
        map.put("type", type);
        map.put("parentId", parentId);
        inInfo.setAttr(map);
        return inInfo;
    }
}
