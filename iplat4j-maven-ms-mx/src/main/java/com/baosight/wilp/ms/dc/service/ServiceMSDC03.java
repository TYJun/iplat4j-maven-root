package com.baosight.wilp.ms.dc.service;

import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ms.common.util.PackageOarameters;
import com.baosight.wilp.ms.common.util.PrUtils;
import com.baosight.wilp.ms.common.util.UUIDUtils;
import com.baosight.wilp.ms.dc.domain.MSDC01;
import com.baosight.wilp.ms.dc.domain.MSDC03;
import com.baosight.wilp.ms.pl.domain.MSPL01;
import com.baosight.wilp.ms.pl.domain.MSPL02;
import com.baosight.wilp.ms.pl.domain.MSPL0202;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @title: 区域配置页面(页面初始化方法,查询参数分类列表,参数分类删除,
  * 判断是否可以删除类,参数分类树状菜单封装,封装树返回类,添加数据
  * 查询方法)
  * @projectName iplat_v5_monitor
  * @description: TODO
  * @author 孔帅博
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
  * @date 2021-09-08 14:25
  */
public class ServiceMSDC03 extends ServiceBase {
    /**
        * @title: 页面初始化方法
        * @projectName iplat_v5_monitor
        * @description: TODO
        * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
        * @date 2021-09-08 14:19
        */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return super.initLoad(inInfo, new MSDC03());
    }

    /**
        * @title: 查询参数分类列表
        * @projectName iplat_v5_monitor
        * @description: TODO
        * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
        * @date 2021-09-08 14:19
        * 1、通过inInfo携带参数
        * 2、获取参数作为筛选条件执行sql
        * 3、返回结果
        */
    @Override
    public EiInfo query(EiInfo inInfo) {
        /**
         * 通过inInfo对象获取下属分类名称作为查询参数
         */
        EiBlock inqu_status = (EiBlock) inInfo.getBlocks().get("inqu_status");
        HashMap o = (HashMap) inqu_status.getRows().get(0);
        //判断树节点是否为空
        if (o.get("parentId") != null) {
            String parentId = o.get("parentId").toString();
            //判断长度是否为0
            if (parentId.trim().length() == 0) {
                o.remove("parentId");
            }
        }
        //判断下属分类名称是否为空
        if (o.get("classifyname") != null) {
            String classifyname = o.get("classifyname").toString();
            //判断下属分类名称长度是否为0
            if (classifyname.trim().length() == 0) {
                o.remove("classifyname");
            }
        }
        EiInfo outInfo = super.query(inInfo, "MSDC03.query", new MSDC03());
        return outInfo;
    }

    /**
        * @title: 参数分类删除
        * @projectName iplat_v5_monitor
        * @description: TODO
        * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
        * @date 2021-09-08 14:20
        * 1、获取info获取id跳转getTrueOrFalse方法    2、通过方法获取返回值对比   3、判断是否有子节点
        */
    @Override
    public EiInfo delete(EiInfo inInfo) {
        List<?> rows = inInfo.getBlock("result").getRows();
        //判断row是否为空
        if (CollectionUtils.isNotEmpty(rows)) {
            Map<String, String> map = (Map<String, String>) rows.get(0);
            //执行getTrueOrFalse方法传入id
            if (!getTrueOrFalse(map.get("id").toString(), 2)) {
                inInfo.setStatus(-1);
                inInfo.setMsg("先删除此参数分类下的子参数分类再删除此参数分类！！！");
                return inInfo;
            } else if (!getTrueOrFalse(map.get("id").toString(), 1)) {
                inInfo.setStatus(-1);
                inInfo.setMsg("参数分类已绑定参数，不可删除");
                return inInfo;
            } else if (!getTrueOrFalse(map.get("id").toString(), 3)) {
                inInfo.setStatus(-1);
                inInfo.setMsg("参数分类已绑定分组，不可删除");
                return inInfo;
            } else {
                EiInfo outInfo = super.delete(inInfo, "MSDC03.delete");
                outInfo.setMsg("删除成功");
                return outInfo;

            }
        } else {
            inInfo.setStatus(-1);
            inInfo.setMsg("请先选择一条记录！");
            return inInfo;
        }


    }

    /**
      * @title: 判断是否可以删除类
      * @projectName iplat_v5_monitor
      * @description: TODO
      * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
      * @date 2021-09-08 14:26
      * 1：查询是否有子类；2：查询是否关联点位参数表: 3、查询是否关联组
      */
    public Boolean getTrueOrFalse(String str, Integer i) {
        /**
         * 通过判断有i执行那个sql
         * str传入的id
         */
        if (i.equals(1)) {     //i=1判断 参数分类下的子参数分类再删除此参数分类
            Map<String, String> map = new HashMap<>();
            map.put("tParamClassifyId", str);
            List<MSPL01> list = dao.query("MSDC01.queryById", map);
            if (CollectionUtils.isNotEmpty(list)) {
                return false;
            }
        } else if (i.equals(2)) {  //i=2判断 参数分类已绑定参数
            Map<String, String> map = new HashMap<>();
            map.put("parentId", str);
            List<MSPL02> list = dao.query("MSDC03.queryById", map);
            if (CollectionUtils.isNotEmpty(list)) {
                return false;
            }
        } else if (i.equals(3)) {  //i=3判断 参数分类已绑定分组
            Map<String, String> map = new HashMap<>();
            map.put("tparamid", str);
            List<Map> list = dao.query("MSPL0201.queryById", map);
            if (CollectionUtils.isNotEmpty(list)) {
                return false;
            }
        }
        return true;
    }

    /**
      * @title: 参数分类树状菜单封装
      * @projectName iplat_v5_monitor
      * @description: TODO
      * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
      * @date 2021-09-08 14:23
      * 通过点击数列表显示下级节点
      */
    public EiInfo queryTree(EiInfo inInfo) {
        /**
         * 通过inInfo对象获取传入参数
         */
        Map blocks = inInfo.getBlocks();
        EiBlock status = (EiBlock) blocks.get("inqu_status");
        List<Map> rows = status.getRows();
        String a = rows.get(0).get("node").toString();
        /**
         * 获取下级子节点
         */
        Map map = new HashMap();
        map.put("node", a);
        //通过sql查询下级子节点
        List<Map> list = dao.query("MSDC03.queryTree", map);
        PrUtils.BuildOutEiInfo(inInfo, "result", initMetaData(), list, list.size());
        String pEname = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
        inInfo.getBlocks().put(pEname, inInfo.getBlock(EiConstant.resultBlock));
        inInfo.getBlocks().remove(EiConstant.resultBlock);
        return inInfo;
    }

    /**
      * @title: 封装树返回类
      * @projectName iplat_v5_monitor
      * @description: TODO
      * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
      * @date 2021-09-08 14:23
      */
    private EiBlockMeta initMetaData() {
        EiBlockMeta eiMetadata = new EiBlockMeta();
        EiColumn eiColumn = new EiColumn("label");
        eiColumn.setDescName("英文名");
        eiColumn.setNullable(false);
        eiColumn.setPrimaryKey(true);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("text");
        eiColumn.setDescName("中文名");
        eiColumn.setNullable(false);
        eiMetadata.addMeta(eiColumn);

        // 作为kendo.data.Model 不能出现id，parent列
        eiColumn = new EiColumn("pId");
        eiColumn.setDescName("父级英文名");
        eiColumn.setNullable(true);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("leaf");
        eiColumn.setDescName("0存在 1不存在");
        eiColumn.setNullable(true);
        eiMetadata.addMeta(eiColumn);

        return eiMetadata;

    }
    
    /**
      * @title: 添加数据
      * @projectName iplat_v5_monitor
      * @description: TODO
      * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
      * @date 2021-09-08 14:24
      */
    @Override
    public EiInfo insert(EiInfo inInfo) {
        /**
         * 通过inInfo对象获取参数
         */
        Map<String, String> map = new HashMap<>();
        MSPL02 Associate = new MSPL02();
        MSDC03 mspl02 = new MSDC03();
        //获取分类名称
        mspl02.setClassifyName(inInfo.getAttr().get("classifyName").toString());
        //获取所属节点
        mspl02.setParentId(inInfo.getAttr().get("parentId").toString());
        //获取排序编号
        mspl02.setClassifyCode(inInfo.get("classifyCode").toString());
        map.put("parentId", mspl02.getParentId());
        map.put("classifyName", mspl02.getClassifyName());
        //如果是根节点判断一级节点是否含有分类
        if (mspl02.getParentId().equals("root")) {
            List<MSDC03> list = dao.query("MSDC02.queryById", map);
            if (list.size() != 0) {    //判断一级节点是否含有该分类名称
                inInfo.setMsg("此分类下存在此分类名称，不可再次添加");
                inInfo.setStatus(0);
                return inInfo;
            }
        }
        /**
         * 根据type判断是增加还是更新
         * see新增
         */
        if (inInfo.getAttr().get("type").toString().equals("add") || inInfo.getAttr().get("type").toString().equals("addType")) {
            mspl02.setId(UUIDUtils.getUUID());
            PackageOarameters.setRows(inInfo, mspl02);
            EiInfo outInfo = super.insert(inInfo, "MSDC03.insert");
            if (!inInfo.getAttr().get("usergroupId").toString().trim().equals("")) {
                Associate.setId(mspl02.getId());
                Associate.setUsergroupid(inInfo.getAttr().get("usergroupId").toString());
                Associate.setTparamid(mspl02.getId());
                PackageOarameters.setRows(inInfo, Associate);
                super.insert(inInfo, "MSPL0201.groupinsert");
            }
            outInfo.setMsg("新增成功");
            return outInfo;
        } else {
            if (StringUtil.isNotEmpty(inInfo.getAttr().get("id").toString())) {
                mspl02.setId(inInfo.getAttr().get("id").toString());
            }
            if (!inInfo.getAttr().get("usergroupId").toString().trim().equals("")) {
                map.put("tparamid", inInfo.getAttr().get("id").toString());
                map.put("usergroupid", inInfo.getAttr().get("usergroupId").toString());
                List<MSPL0202> list4 = dao.query("MSPL0201.queryById", map);
                if (list4.size() == 0) {
                    Associate.setId(UUIDUtils.getUUID());
                    Associate.setUsergroupid(inInfo.getAttr().get("usergroupId").toString());
                    Associate.setTparamid(inInfo.getAttr().get("id").toString());
                    PackageOarameters.setRows(inInfo, Associate);
                    super.insert(inInfo, "MSPL0201.groupinsert");
                }
            }
            PackageOarameters.setRows(inInfo, mspl02);
            EiInfo outInfo = super.update(inInfo, "MSDC03.update");
            outInfo.setMsg("修改成功");
            return outInfo;
        }
    }
    /**  
      * @title: 查询方法
      * @projectName iplat_v5_monitor
      * @description: TODO
      * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
      * @date 2021-09-08 14:25 
      */
    public EiInfo QueryParameters(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "MSDC01.query", new MSDC01());
        return outInfo;
    }
}
