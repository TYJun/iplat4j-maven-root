package com.baosight.wilp.ms.ar.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.ms.common.service.WebSocketService;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
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
 * <desc>    // 新增编辑页面
 */
@SuppressWarnings("unchecked")
public class ServiceMSAR0101 extends ServiceBase {

    /**
     * 页面初始化方法
     * &lt;p&gt;Title: initLoad&lt;/p&gt;
     * &lt;p&gt;Description: &lt;/p&gt;
     *
     * @param inInfo
     * @return 1、通过inInfo获取查询的参数
     * 2、通过dao.query方法把查询参数传入
     * 3、获取sql语句查询参数封装进inInfo
     * 4、返回inInfo参数
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        WebSocketService.TAG_CONFIG_CACHE.clear();
        if (!StringUtils.isNotEmpty(inInfo.getAttr().get("id").toString())) {
            return inInfo;
        }
        Map<String, String> map = new HashMap<>();
        map.put("id", inInfo.getAttr().get("id").toString());
        String type = inInfo.getAttr().get("type").toString();
        List<Map<String, String>> list = dao.query("MSAR01.queryById", map);
        if (!CollectionUtils.isEmpty(list)) {
            list.get(0).put("type", type);
            inInfo.setAttr(list.get(0));
        }
        return inInfo;
    }

    /**
     * @description: 查询计量量纲
     * @param inInfo
     * @return inInfo
     * @throws
     * @author znl
     * @date 2021/8/6 16:42
     * 1、根据小代码配置的常量ms.ar.dimension进行查询，
     * 其余参数根据框架实例配置
     * 2、把查询结果封装进outInfo返回给前端
     *@Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     */
    public EiInfo getMap(EiInfo inInfo) {
        inInfo.set(EiConstant.serviceId, "S_ED_02");
        inInfo.set("codeset", "ms.ar.dimension");
        EiInfo outInfo = XServiceManager.call(inInfo);
        List<HashMap> list = (List) outInfo.get("list");
        outInfo.addBlock("parentMap").addRows(list);
        return outInfo;
    }

    /**
     * @description: 根据计量量纲查询计量单位
     * @param inInfo
     * @return EiInfo
     * @throws
     * @author znl
     * @date 2021/8/6 16:42
     * 1、根据小代码配置的常量ms.ar.dimension+常量的value值进行查询，
     * 其余参数根据框架实例配置
     * 2、把查询结果封装进outInfo返回给前端
     *@Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     */
    public EiInfo getChildMap(EiInfo inInfo) {
        Map map = inInfo.getAttr();
        String dimension = map.get("dimension").toString();
        inInfo.set(EiConstant.serviceId, "S_ED_02");
        inInfo.set("codeset", "ms.ar.dimension." + dimension);
        EiInfo outInfo = XServiceManager.call(inInfo);
        List<HashMap> list = (List) outInfo.get("list");
        outInfo.addBlock("childMap").addRows(list);
        return outInfo;
    }
}
