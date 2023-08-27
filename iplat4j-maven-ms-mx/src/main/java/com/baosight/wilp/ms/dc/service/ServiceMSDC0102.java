package com.baosight.wilp.ms.dc.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ms.common.util.PackageOarameters;
import com.baosight.wilp.ms.common.util.UUIDUtils;
import com.baosight.wilp.ms.dc.domain.MSDC01;
import com.baosight.wilp.ms.dc.domain.MSDC0102;
import com.baosight.wilp.ms.dc.domain.MSDC02;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @title: 查看参数页面 (页面初始化方法、删除方法、删除方法、查询方法)
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @author 孔帅博
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @date 2021-09-08 15:05
 */
@SuppressWarnings("unchecked")
public class ServiceMSDC0102 extends ServiceBase {
    
    /**
     * @title: 页面初始化方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:05
     * 1、获取前端传过来的参数     2、把参数作为条件传入sql语句    3、返回inInfo
     */
    public EiInfo initLoad(EiInfo inInfo) {
        /**
         *获取前端传过来的inInfo对象
         */
        Map<String, String> map = new HashMap<>();
        MSDC0102 msdc0102 = new MSDC0102();
        if (inInfo.getAttr().get("group_id") != null) {
            map.put("group_id", inInfo.getAttr().get("group_id").toString());
            //      msdc01.setGroup_id(inInfo.getAttr().get("group_id").toString());
        }
        //    map.put("group_id", inInfo.getAttr().get("group_id").toString());
        map.put("id", inInfo.getAttr().get("id").toString()); //获取前端的id字段
        String type1 = inInfo.getAttr().get("type1").toString(); //获取前端的请求类型
        List<Map<String, String>> list = dao.query("MSDC01.queryById", map);
        PackageOarameters.setRows(inInfo, map);
        //     EiInfo outInfo = super.query(inInfo, "MSDC02.queryRelevance0102", Relevance());

        List<Map<String, String>> list2 = dao.query("MSDC02.queryRelevance0102", map);
        Map blocks = inInfo.getBlocks();
        EiBlock result = (EiBlock) blocks.get("result");
        result.setRows(list2);
        if (!CollectionUtils.isEmpty(list)) {
            list.get(0).put("type1", type1);
            inInfo.setAttr(list.get(0));
        }
        return inInfo;
    }
    /**  
     * @title: 删除方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:06 
     */
    public EiInfo RemoveRelevance(EiInfo inInfo) {
        EiBlock result = (EiBlock) inInfo.getBlocks().get("result");
        EiInfo eiInfo = super.update(inInfo, "MSDC02.remove");
        eiInfo.setMsg("删除成功");
        return eiInfo;
    }
    /**  
     * @title: 查询方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:06 
     */
    public EiInfo query(EiInfo inInfo) {
        Map<String, String> map = new HashMap<>();
        MSDC01 msdc01 = new MSDC01();
        //inInfo传递过来的参数
        EiBlock inqu_status = (EiBlock) inInfo.getBlocks().get("inqu_status");
        if (inqu_status == null) {
            map.put("id", (String) inInfo.getAttr().get("DC_id"));
            //      msdc01.setGroup_id((String) inInfo.getAttr().get("DC_id"));
            //      PackageOarameters.setRows(inInfo, msdc01);
            List<Map<String, String>> list2 = dao.query("MSDC02.queryRelevance0102", map);
            Map blocks = inInfo.getBlocks();
            EiBlock result = (EiBlock) blocks.get("result");
            result.setRows(list2);
            result.getAttr().put("count", list2.size());
            return inInfo;
        }
        HashMap o = (HashMap) inqu_status.getRows().get(0);
        msdc01.setGroup_id((String) o.get("parentId"));
        System.out.println(o.get("parentId"));
        inInfo.set("inqu_status-0-dataGroupCode", PackageOarameters.getDataGroupCode());
        /**
         * 当他是根节点使执行
         */
        if (o.get("parentId").toString().equals("root") &&
                o.get("code").toString() != "" &&
                o.get("param").toString() != "" &&
                o.get("name").toString() != "") {
            EiInfo outInfo = super.query(inInfo, "MSDC02.queryTreeRoot", new MSDC01());
            return outInfo;
        }
        EiInfo outInfo = super.query(inInfo, "MSDC01.query", new MSDC01());
        return outInfo;
    }
}