package com.baosight.wilp.ms.dc.service;

import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ms.common.util.PrUtils;
import com.baosight.wilp.ms.dc.domain.*;

import java.util.*;

/**
   * @title: 设备配置页面(页面初始化,查询方法,删除方法,初始化数树状图方法,自定义封装返回类,自定义封装返回类)
   * @projectName iplat_v5_monitor
   * @description: TODO
   * @author 孔帅博
   * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
   * @date 2021-09-08 13:58
   */
@SuppressWarnings("unchecked")
public class ServiceMSDC01 extends ServiceBase {
    /**
       * @title: 页面初始化
       * @projectName iplat_v5_monitor
       * @description: TODO
       * @author 孔帅博
       * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
       * @date 2021-09-08 13:57
       */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {

        return super.initLoad(inInfo, new MSDC01());
    }

    /**
       * @title: 查询方法
       * @projectName iplat_v5_monitor
       * @description: TODO
       * @author 孔帅博
       * 1、通过inInfo获取查询的参数
       * 2、通过super.query方法把查询参数传入
       * 3、获取sql语句查询参数封装进outInfo
       * 4、返回outInfo参数*
       * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
       * @date 2021-09-08 13:59
       */
    @Override
    public EiInfo query(EiInfo inInfo) {
        //判断查询参数是否为空或者是否为""
        EiBlock inqu_status = (EiBlock) inInfo.getBlocks().get("inqu_status");
        HashMap o = (HashMap) inqu_status.getRows().get(0);
        //判断设备代码是否为空
        if (o.get("code") != null) {
            String code = o.get("code").toString();
            //判断设备代码长度是否为0
            if (code.trim().length() == 0) {
                o.remove("code");
            }
        }
        //判断参数类型Tag是否为空
        if (o.get("param") != null) {
            String param = o.get("param").toString();
            //判断参数类型Tag长度是否为0
            if (param.trim().length() == 0) {
                o.remove("param");
            }
        }
        //设备名称是否为空
        if (o.get("name") != null) {
            String name = o.get("name").toString();
            //判断设备名称长度是否为0
            if (name.trim().length() == 0) {
                o.remove("name");
            }
        }
        MSDC01 msdc01 = new MSDC01();
        msdc01.setGroup_id((String) o.get("parentId"));
        /**
         * 通过判断树节点执行xml中不同的SQL语句
         */
        //判断查询树是否为根节点
        if (o.get("parentId").toString().equals("root")) {
            EiInfo outInfo = super.query(inInfo, "MSDC02.queryTreeRoot", new MSDC01());
            return outInfo;
        }
        EiInfo outInfo = super.query(inInfo, "MSDC01.query", new MSDC01());
        Map blocks = outInfo.getBlocks();
        EiBlock inqu_status1 = (EiBlock) blocks.get("inqu_status");
        inqu_status1.getAttr();
        EiBlock result1 = (EiBlock) blocks.get("result");
        result1.getAttr().put("count", "inqu_status1.getAttr()");
        return outInfo;
    }


    /**
        * @title: 删除方法
        * @projectName iplat_v5_monitor
        * @description: TODO
        * @author 孔帅博
        * @date 2021-09-08 14:00
        * 1、传入inInfo获取查询的参数
        * 2、获取参数判断是否关联参数
        * 3、执行sql语句
        * 4、返回outInfo参数*
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
        */
    public EiInfo delete(EiInfo inInfo) {
        /**
         * 查询是否含有关联关系
         */
        EiBlock result = (EiBlock) inInfo.getBlocks().get("result");
        List rows = result.getRows();
        //循环删除关联关系
        for (int i = 0; i < rows.size(); i++) {
            Map o = (Map) rows.get(i);
            if (o.get("t_area_classify_id") != null && o.get("t_area_classify_id") != "") {
                //调用sql语句执行删除操作
                super.delete(inInfo, "MSDC01.deleteExternal");
            }
        }
        EiInfo outInfo = super.delete(inInfo, "MSDC01.delete");
        return outInfo;
    }
    /**
        * @title: 初始化数树状图方法
        * @projectName iplat_v5_monitor
        * @description: TODO
        * @author 孔帅博
        * @date 2021-09-08 14:02
        * 1、传入inInfo获取查询的参数
        * 2、获取登录ID
        * 3、通过ID获取调用组信息
        * 4、判断组信息是否存在管理员组有则返回无则将组查询的信息储存到arrayList中
        * 5、通过组信息循环查询关联树信息并储存在SelectSet
        * 6、通过关联树信息查询树信息储存在List2中
        * 7、把查询树信息通过inInfo传入
        * 8、返回对象
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
        */
    public EiInfo queryTree(EiInfo inInfo) {
        /**
         * 传入inInfo对象获取参数
         */
        //树状存储(防止储存重复)
        Set List2 = new HashSet<>();
        //关联树信息储存
        Set SelectSet = new LinkedHashSet<>();
        //管理员权限默认关闭
        Boolean bean = false;
        //存放组信息
        List arrayList = new ArrayList<>();
        Map<Object, Object> hashMap = new HashMap<>();
        //获取登录ID
        String userUuid = UserSession.getUserUuid();
        hashMap.put("id", userUuid);
        //通过ID调用组信息
        List<Map> list2 = dao.query("MSPL02.relevance22", hashMap);
        //循环判断组信息有没有管理员组
//        for (int i = 0; i < list2.size(); i++) {
//            if (list2.get(i).get("groupCname").toString().equals("管理员组")) {
//                //开启管理员权限
//                bean = true;
//                break;
//            } else {
//                //将获取的组信息存入arrayList中
//                arrayList.add(list2.get(i).get("groupId").toString());
//            }
//        }
        bean = true;
        Map blocks = inInfo.getBlocks();
        EiBlock status = (EiBlock) blocks.get("inqu_status");
        List<Map> rows = status.getRows();
        String a = rows.get(0).get("node").toString();
        Map map = new HashMap();
        map.put("node", a);
        //表明不具有管理员权限
        if (bean.equals(false)) {
            //所具有分组大于0执行
            if (arrayList.size() > 0) {
                if (map.get("node").toString().equals("root")) {
                    //循环分组信息
                    for (int i = 0; i < arrayList.size(); i++) {
                        hashMap.put("id", arrayList.get(i));
                        //通过SQL查询关联树信息
                        List<Map> list = dao.query("MSPL02.relevanceSelect", hashMap);
                        if (list.size() > 0) {
                            for (int j = 0; j < list.size(); j++) {
                                //将关联树信息储存在SelectSet中
                                SelectSet.add(list.get(j).get("t_param_id"));
                            }
                        }
                    }
                }
            }
        }
        int k = 0;
        if (SelectSet.size() > 0) {
            //循环关联树信息
            for (Object hash : SelectSet) {
                //通过id和parent_id查询数据
                map.put("usergroupid", hash);
                //获取根节点
                List<Map> list3 = dao.query("MSDC02.queryTree", map);
                if (list3.size() > 0) {
                    //把根节点循环增加进List2
                    for (int i = 0; i < list3.size(); i++) {
                        List2.add(list3.get(i));
                    }
                }
            }
            //查询实体类
            ArrayList<Object> list1 = new ArrayList<>();
            //将查询出来的信息放入list1中
            for (Object hash : List2) {
                list1.add(hash);
            }
            //将list1放入inInfo对象中
            PrUtils.BuildOutEiInfo(inInfo, "result", initMetaData(), list1, list1.size());
            String pEname = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
            inInfo.getBlocks().put(pEname, inInfo.getBlock(EiConstant.resultBlock));
            inInfo.getBlocks().remove(EiConstant.resultBlock);
            return inInfo;
        }
        //如果具有管理员权限则直接走这个sql查询所有树
        List<Map> list = dao.query("MSDC02.queryTree", map);
        PrUtils.BuildOutEiInfo(inInfo, "result", initMetaData(), list, list.size());
        String pEname = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
        inInfo.getBlocks().put(pEname, inInfo.getBlock(EiConstant.resultBlock));
        inInfo.getBlocks().remove(EiConstant.resultBlock);
        return inInfo;
    }

    /**  
        * @title: 自定义封装返回类
        * @projectName iplat_v5_monitor
        * @description: TODO
        * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
        * @date 2021-09-08 14:05
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
        * @title: 修改方法
        * @projectName iplat_v5_monitor
        * @description: TODO
        * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
        * @date 2021-09-08 14:06
        * 1、传入DC_id
        * 2、执行sql语句
        * 3、返回结果
        */
    public EiInfo update(EiInfo inInfo) {
        EiInfo outInfo = super.delete(inInfo, "MSDC01.update");
        return outInfo;
    }
}
