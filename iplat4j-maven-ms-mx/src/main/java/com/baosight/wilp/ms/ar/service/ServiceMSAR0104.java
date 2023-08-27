package com.baosight.wilp.ms.ar.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ms.common.util.PackageOarameters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author znl
 * @title: ServiceMSAR01
 * @projectName iplat_v5_monitor
 * @description: 查看参数页面
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @date 2021/8/2 13:58
 */
@SuppressWarnings("unchecked")
public class ServiceMSAR0104 extends ServiceBase {

    /**
     * @title: 页面初始化方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @param inInfo
     * @author znl
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-24 17:52
     * 1、获取前端传过来的参数
     * 2、把参数作为条件传入sql语句
     * 3、返回inInfo
     */
    public EiInfo initLoad(EiInfo inInfo) {
        Map<String, String> map = new HashMap<>();
        map.put("tmsar01Id", inInfo.getAttr().get("id").toString()); //获取前端的id字段
        List<Map<String, String>> list = dao.query("MSPL01.queryById", map);
        PackageOarameters.setRows(inInfo, map);
        Map blocks = inInfo.getBlocks();
        EiBlock result = (EiBlock) blocks.get("result");
        result.setRows(list);
        return inInfo;
    }

    /**
     * @description: 删除报警规则关联
     * @param inInfo
     * @return eiInfo
     * @throws
     * @author znl
     * @date 2021/8/19 9:42
     * 1、从inInfo获取传入的参数
     * 2、使用super.update进行数据清空删除关联
     * 3、返回eiInfo
     *@Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     */
    public EiInfo RemoveRelevance(EiInfo inInfo) {
        EiBlock result = (EiBlock) inInfo.getBlocks().get("result");
        EiInfo eiInfo = super.update(inInfo, "MSPL01.remove");
        eiInfo.setMsg("删除成功");
        return eiInfo;
    }

    /**
     * @title: 页面初始化方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @author znl
     * @date 2021-09-24 17:53
     * 1、获取前端传过来的参数
     * 2、把参数作为条件传入sql语句
     * 3、返回inInfo
     */
    public EiInfo query(EiInfo inInfo) {
        Map blocks = inInfo.getBlocks();
        Map<String, String> map = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        String ides = inInfo.getAttr().get("id").toString();
        map2.put("ides", ides);
        List<Map<String, String>> list3 = dao.query("MSPL01.selectAll2", map2);
        if (list3.size() == 0) {
            map.put("tmsar01Id", inInfo.getAttr().get("id").toString()); //获取前端的id字段
        } else {
            map.put("tmsar01Id", list3.get(0).get("tmsar01_id")); //获取前端的id字段
        }
        List<Map<String, String>> list2 = dao.query("MSPL01.queryById", map);
        EiBlock result = (EiBlock) blocks.get("result");
        result.setRows(list2);       //放入返回数据
        result.getAttr().put("count", list2.size());    //分页
        return inInfo;
    }
}