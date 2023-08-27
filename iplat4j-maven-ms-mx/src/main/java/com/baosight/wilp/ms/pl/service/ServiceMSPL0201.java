package com.baosight.wilp.ms.pl.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.ms.pl.domain.MSPL0201;
import com.baosight.wilp.ms.pl.domain.MSPL0202;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @title: 系统分类页面新增编辑
  * @projectName iplat_v5_monitor
  * @description: TODO
  * @author 孔帅博
  * 1、通过inInfo获取查询的参数id
  * 2、通过dao.query方法把查询参数传入
  * 3、获取sql语句查询参数封装进inInfo
  * 4、返回inInfo参数
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
  * @date 2021-09-08 15:35
  */

public class ServiceMSPL0201 extends ServiceBase {

    /**
     * @title: 页面初始化方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @date 2021-09-08 15:35
     * 1、通过inInfo获取查询的参数id
     * 2、通过dao.query方法把查询参数传入
     * 3、获取sql语句查询参数封装进inInfo
     * 4、返回inInfo参数
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        /**
         * 通过inInfo获取查询的参数id
         */
        String type = inInfo.getAttr().get("type").toString();
        String parentId = inInfo.getAttr().get("parentId").toString();
        Map<String, String> map = new HashMap<>();
        map.put("id", inInfo.getAttr().get("id").toString());
        if (StringUtils.isNotEmpty(inInfo.getAttr().get("id").toString())) {
            List<Map<String, String>> list = dao.query("MSPL02.queryById", map); //通过dao.query方法把查询参数传入
            List<MSPL0202> list2 = dao.query("MSPL02.relevance", map);
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

    /**
     * @title: 查询分组列表
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:37
     */
    public EiInfo queryWorktype(EiInfo inInfo) {
        HashMap<Object, Object> attr = new HashMap<>();
        EiInfo eiInfo = new EiInfo();
        eiInfo.set(EiConstant.serviceId, "S_XS_01");    //调用用户组信息
        EiInfo outInfo = XServiceManager.call(eiInfo);
        eiInfo.set("limit", outInfo.getAttr().get("size"));
        XServiceManager.call(eiInfo);
        EiInfo outInfo2 = XServiceManager.call(eiInfo);
        List result = (List) outInfo2.getAttr().get("result");
        inInfo.addBlock("worktypeName").addRows(result);
        inInfo.getBlock("worktypeName").setBlockMeta(new MSPL0201().eiMetadata);
        return inInfo;
    }
    /**
     * @title: 查询方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:37
     */
    public EiInfo query(EiInfo inInfo) {
        HashMap<Object, Object> map = new HashMap<>();
        HashMap<Object, Object> map2 = new HashMap<>();
        MSPL0202 mspl0202 = new MSPL0202();
        if (inInfo.getAttr().get("id") != null) {    //inqu_status
            map2.put("id", inInfo.getAttr().get("id").toString());
        }
        List<MSPL0202> list3 = dao.query("MSPL02.relevance", map2);
        List<Map> list2 = dao.query("MSPL02.relevance24", map);
        if (list3.size() > 0) {
            for (int i = 0; i < list3.size(); i++) {    //查询拥有参数
                for (int j = 0; j < list2.size(); j++) {    //查询参数关联的树
                    if (list3.get(i).getUsergroupid().trim().equals("") || list2.get(j).get("groupId").toString().trim().equals("")) {
                    } else {
                        if (list3.get(i).getUsergroupid().equals(list2.get(j).get("groupId"))) {
                            list3.remove(j);
                        }
                    }
                }
            }
        }
        EiInfo outInfo2 = super.query(inInfo, "MSPL02.relevance24");
        return outInfo2;
    }
}
