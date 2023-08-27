package com.baosight.wilp.ms.dc.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ms.common.util.PackageOarameters;
import com.baosight.wilp.ms.common.service.WebSocketService;
import com.baosight.wilp.ms.common.util.UUIDUtils;
import com.baosight.wilp.ms.dc.domain.MSDC01;
import com.baosight.wilp.ms.dc.domain.MSDC0102;
import com.baosight.wilp.ms.dc.domain.MSDC02;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: 新增编辑页面 (页面初始化方法、新增/修改页面、初始化字典
 * 封装返回类、查询类)
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @author 孔帅博
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @date 2021-09-08 14:33
 *
 */
@SuppressWarnings("unchecked")
public class ServiceMSDC0101 extends ServiceBase {

    /**
     * @title:  页面初始化方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 14:33
     * 1、获取前端传过来的参数 2、把参数作为条件传入sql语句    3、返回inInfo
     */
    public EiInfo initLoad(EiInfo inInfo) {
        WebSocketService.TAG_CONFIG_CACHE.clear();
        /**
         * 获取前端传过来的参数inInfo
         */
        Map<String, String> map = new HashMap<>();
        if (inInfo.getAttr().get("group_id") != null) {
            map.put("group_id", inInfo.getAttr().get("group_id").toString());
            //      msdc01.setGroup_id(inInfo.getAttr().get("group_id").toString());
        }
        //获取前端的id字段
        map.put("id", inInfo.getAttr().get("id").toString());
        //获取前端的请求类型
        String type1 = inInfo.getAttr().get("type1").toString();
        if (type1.equals("edit")) {
            //通过id查询信息
            List<Map<String, String>> list = dao.query("MSDC01.queryById", map);
            EiInfo outInfo = super.query(inInfo, "MSDC01.queryRelevance", Relevance());
            //将查询信息储存在outInfo下的Attr对象内
            if (!CollectionUtils.isEmpty(list)) {
                list.get(0).put("type1", type1);
                outInfo.setAttr(list.get(0));
            }
            return outInfo;
        }
        EiInfo outInfo = super.query(inInfo, "MSDC01.queryRelevance", Relevance());
        return outInfo;
    }

    /**
     * @title: 新增/修改页面
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 14:33
     * 1、判断传入参数是否为空 2、如果不为空获取传入参数   3、通过字段判断传入类型
     * 4、通过传入类型执行不同的sql语句   5、返回参数
     */
    @Override
    public EiInfo insert(EiInfo inInfo) {
        EiBlock result = (EiBlock) inInfo.getBlocks().get("result");
        List rows = result.getRows();
        MSDC01 msdc01 = new MSDC01();
        MSDC02 msdc02 = new MSDC02();
        Map<String, String> map = new HashMap<>();
        //判断传入参数是否为空防止空指针报错
        msdc01.setName(inInfo.getAttr().get("name").toString());
        if (inInfo.getAttr().get("DC_id").toString() != null) {
            msdc01.setDC_id(inInfo.getAttr().get("DC_id").toString());
        }
        if (inInfo.getAttr().get("code") != null) {
            msdc01.setCode(inInfo.getAttr().get("code").toString());
        }
        if (inInfo.getAttr().get("name") != null) {
            msdc01.setName(inInfo.getAttr().get("name").toString());
        }
        if (inInfo.getAttr().get("type") != null) {
            msdc01.setType(inInfo.getAttr().get("type").toString());
        }
        if (inInfo.getAttr().get("weight") != null) {
            msdc01.setWeight(inInfo.getAttr().get("weight").toString());
        }
        if (inInfo.getAttr().get("t_area_classify_id") != null) {
            msdc01.setT_area_classify_id(inInfo.getAttr().get("t_area_classify_id").toString());
        }
        if (inInfo.getAttr().get("pid") != null) {
            msdc01.setGroup_id(inInfo.getAttr().get("pid").toString());
        }
        //如果获取type1类型为edit则是修改执行修改类型
        if (inInfo.getAttr().get("type1").toString().equals("edit")) {
            if (inInfo.getAttr().get("DC_id").toString() != null) {
                msdc01.setDC_id(inInfo.getAttr().get("DC_id").toString());
            }
            //将传入参数塞入inInfo对象
            PackageOarameters.setRows(inInfo, msdc01);
            EiInfo outInfo = super.update(inInfo, "MSDC01.update");
            //修改之前先查询是否拥有呢个权限
            if (rows.size() > 0) {
                Map o1 = (Map) rows.get(0);
                msdc02.setParent_id((String) o1.get("RetDCid"));
                msdc02.setParent_id(msdc01.getDC_id());
                System.out.println(msdc02.getParent_id());
                if (msdc02.getParent_id() != null && msdc02.getParent_id() != "") {
                    //循环查询是否拥有绑定参数
                    for (int j = 0; j < rows.size(); j++) {
                        Map o = (Map) rows.get(j);
                        msdc02.setId((String) o.get("Reid"));
                        PackageOarameters.setRows(inInfo, msdc02);
                        super.update(inInfo, "MSDC02.relevance");
                    }
                }
            }
            return outInfo;
        }
        //如果获取ype1类型为adddept则是修改执行增加类型
        if (inInfo.getAttr().get("type1").toString().equals("adddept")) {
//            String dc_id= UUIDUtils.getUUID().replace("-","");
            //生成随机id并且添加进实体类
            msdc01.setDC_id(UUIDUtils.getUUID().replace("-", ""));
            PackageOarameters.setRows(inInfo, msdc01);
            Map<String, String> map2 = new HashMap<>();
            map2.put("code", msdc01.getCode());
            List list = dao.query("MSDC01.query", map2);
            System.out.println(list);
            if (list.size() > 0) {
                inInfo.setStatus(-1);
                inInfo.setMsg("增加失败");
                return inInfo;
            }
            if (inInfo.getAttr().get("pid") != null) {
                EiInfo outInfo = super.update(inInfo, "MSDC01.insert2");
                outInfo.setMsg("增加成功");
                msdc02.setParent_id(msdc01.getDC_id());
                if (msdc02.getParent_id() != null && msdc02.getParent_id() != "") {
                    //如果有绑定权限则修改
                    for (int i = 0; i < rows.size(); i++) {
                        Map o = (Map) rows.get(i);
                        msdc02.setId((String) o.get("Reid"));
                        PackageOarameters.setRows(inInfo, msdc02);
                        super.update(inInfo, "MSDC02.relevance");
                    }
                }
                return outInfo;
            }
            EiInfo outInfo = super.update(inInfo, "MSDC01.insert");
            outInfo.setMsg("增加成功");
            return outInfo;

        }
        return inInfo;
    }
    
    /**
     * @title: 初始化字典
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 14:38
     */
    public EiInfo queryWorktype(EiInfo inInfo) {
        List<MSDC01> list = dao.query("MSDC01.queryWorktype", new HashMap<>());
        inInfo.addBlock("name").addRows(list);
        inInfo.getBlock("name").setBlockMeta(new MSDC01().eiMetadata);
        return inInfo;
    }
    /**  
     * @title: 封装返回类
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 14:38
     *
     */
    private EiBlockMeta Relevance() {
        EiBlockMeta eiMetadata = new EiBlockMeta();

        EiColumn eiColumn = new EiColumn("Reid");// 作为kendo.data.Model 不能出现id，parent列
        eiColumn.setDescName("所属设备主键");
        eiColumn.setNullable(true);
        eiColumn.setPrimaryKey(true);

        eiColumn = new EiColumn("RetDCid");
        eiColumn.setDescName("关联参数id");
        eiColumn.setNullable(false);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("ReName");
        eiColumn.setDescName("参数类型");
        eiColumn.setNullable(false);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("Retag");
        eiColumn.setDescName("参数项Tag");
        eiColumn.setNullable(false);
        eiMetadata.addMeta(eiColumn);

        eiMetadata.addMeta(eiColumn);

        return eiMetadata;

    }
    /**  
     * @title: 查询类
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:00 
     */
    public EiInfo query(EiInfo inInfo) {
        //inInfo传递过来的参数
        EiBlock inqu_status = (EiBlock) inInfo.getBlocks().get("inqu_status");
        HashMap o = (HashMap) inqu_status.getRows().get(0);
        if (o.get("param") != null) {
            String param = o.get("param").toString();
            if (param.trim().length() == 0) {
                o.remove("param");
            }
        }
//        MSDC01 msdc01 = new MSDC01();
//        msdc01.setGroup_id((String) o.get("parentId"));
//        System.out.println(o.get("parentId"));
//        inInfo.set("inqu_status-0-dataGroupCode", PackageOarameters.getDataGroupCode());
//        if (o.get("parentId").toString().equals("root") &&
//                o.get("code").toString()!="" &&
//                o.get("param").toString()!="" &&
//                o.get("name").toString()!=""){
//            EiInfo outInfo = super.query(inInfo, "MSDC02.queryTreeRoot", new MSDC01());
//            return outInfo;
//        }
        EiInfo outInfo = super.query(inInfo, "MSDC01.queryRelevance", new MSDC0102());
        return outInfo;
    }
}