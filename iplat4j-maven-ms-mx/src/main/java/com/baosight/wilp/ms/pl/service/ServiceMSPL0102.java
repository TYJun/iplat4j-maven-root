package com.baosight.wilp.ms.pl.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ms.common.service.WebSocketService;
import com.baosight.wilp.ms.common.web.GatherServer;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: 报警规则弹框相关服务（页面初始化方法）
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @author 孔帅博
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @date 2021-09-08 15:32
 */
@SuppressWarnings("unchecked")
public class ServiceMSPL0102 extends ServiceBase {

    /**
     * @title: 页面初始化方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @date 2021-09-08 15:32
     * 1、通过inInfo获取查询的参数id
     * 2、通过dao.query方法把查询参数传入
     * 3、获取sql语句查询参数封装进inInfo
     * 4、返回inInfo参数
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        WebSocketService.TAG_CONFIG_CACHE.clear();
        /**
         * 通过inInfo获取查询的参数id
         */
        Map<String, String> map = new HashMap<>();
        map.put("id", inInfo.getAttr().get("id").toString());
        List<Map<String, String>> list = dao.query("MSPL01.queryById", map); //通过dao.query方法把查询参数传入
        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, String> mapTag = list.get(0);
            if (mapTag.get("tmsar01Id")!=null){
                map.put("tmsar01_Id",mapTag.get("tmsar01Id"));
                List<Map<String, String>> list2 = dao.query("MSPL01.select_ArCode", map); //通过dao.query方法把查询参数传入
                GatherServer.TAG_CONFIG_CACHE.remove(mapTag.get("tag")); //清楚参数设置缓存
                mapTag.put("tmsar_Code",list2.get(0).get("code_"));
                inInfo.setAttr(mapTag);
            }else{
                inInfo.setAttr(mapTag);
            }
        }
        return inInfo;
    }

}
