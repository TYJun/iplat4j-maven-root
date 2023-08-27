package com.baosight.wilp.ms.pl.service;

import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;

import com.baosight.wilp.ms.common.util.UUIDUtils;
import com.baosight.wilp.ms.pl.domain.MSPL01;
import com.baosight.wilp.ms.pl.domain.MSPL02;
import com.baosight.wilp.ms.pl.domain.MSPL0202;
import org.apache.commons.collections.CollectionUtils;
import com.baosight.wilp.ms.common.util.PrUtils;
import com.baosight.wilp.ms.common.util.PackageOarameters;

import java.util.*;

/**
 * @author znl
 * @title: ServiceMSPL02
 * @projectName iplat_v5_monitor
 * @description: 系统分类页面（页面初始化方法  查询参数分类列表  参数分类删除
 * 判断是否可以删除分类   判断是否可以删除分类  参数分类树状菜单封装
 * 封装树返回类   添加数据    ）
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @date 2021/8/9 15:21
 */
public class ServiceMSPL02 extends ServiceBase {

    /**
     * @title: 页面初始化方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:19
     * 1、通过inInfo获取查询的参数   2、通过super.initLoad方法把查询参数传入
     * 3、获取sql语句查询参数封装进inInfo 4、返回inInfo参数
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return super.initLoad(inInfo, new MSPL02());
    }

    /**
     * @title: 查询参数分类列表
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:20
     * 1、通过inInfo获取查询的参数 2、通过super.query方法把查询参数传入
     * 3、获取sql语句查询参数封装进inInfo 4、返回outInfo参数
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        EiBlock inqu_status = (EiBlock) inInfo.getBlocks().get("inqu_status");
        HashMap o = (HashMap) inqu_status.getRows().get(0);
        String classifyName = o.get("classifyName").toString();
        String parentId = o.get("parentId").toString();
        if (classifyName.trim().length() == 0) {
            o.remove("classifyName");
        }
        EiInfo outInfo = super.query(inInfo, "MSPL02.query", new MSPL02());
        return outInfo;
    }
    /**
     * @title: 参数分类删除
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:20
     * 1、从inInfo获取需要删除的数据id
     * 2、判断此分类下是否绑定其他参数如果绑定了则先删除子分类
     * 3、判断参数分类是否绑定点位参数，绑定了则不可删除
     *  4、判断参数分类是否绑定分组，绑定了则不可删除
     */
    @Override
    public EiInfo delete(EiInfo inInfo) {
        List<?> rows = inInfo.getBlock("result").getRows();
        if (CollectionUtils.isNotEmpty(rows)) {
            Map<String, String> map = (Map<String, String>) rows.get(0);
            //传入的i表示走getTrueOrFalse那个判断
            if (!getTrueOrFalse(map.get("id").toString(), 2)) {
                inInfo.setStatus(-1);
                inInfo.setMsg("先删除此参数分类下的子参数分类再删除此参数分类！！！");
                return inInfo;
            } else if (!getTrueOrFalse(map.get("id").toString(), 1)) {
                inInfo.setStatus(-1);
                inInfo.setMsg("参数分类已绑定点位参数，不可删除");
                return inInfo;
            }else {
                EiInfo outInfo = super.delete(inInfo, "MSPL02.delete");
                super.delete(inInfo, "MSPL02.delete2");
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
     * @title: 判断是否可以删除分类
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:21
     * 1：查询是否有子类
     * 2：查询是否关联点位参数表;
     * 3:查询是否关联分组
     */
    public Boolean getTrueOrFalse(String str, Integer i) {
        if (i.equals(1)) {      //i=1 判断是否含有子类
            Map<String, String> map = new HashMap<>();
            map.put("tParamClassifyId", str);
            List<MSPL01> list = dao.query("MSPL01.queryById", map);
            if (CollectionUtils.isNotEmpty(list)) {
                return false;
            }
        } else if (i.equals(2)) {   //i=2 判断是否关联点位参数表
            Map<String, String> map = new HashMap<>();
            map.put("parentId", str);
            List<MSPL02> list = dao.query("MSPL02.queryById", map);
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
     * @date 2021-09-08 15:21
     * 1、传入inInfo获取查询的参数
     * 2、获取登录ID
     * 3、通过ID获取调用组信息
     * 4、判断组信息是否存在管理员组有则返回无则将组查询的信息储存到arrayList中
     * 5、通过组信息循环查询关联树信息并储存在SelectSet
     * 6、通过关联树信息查询树信息储存在List2中
     * 7、根据 dao.query查询出数据进行组装
     * 8、把数据封装进inInfo返回到前端
     */
    public EiInfo queryTree(EiInfo inInfo) {
        Set List2 = new HashSet<>();  //树状存储
        Set SelectSet = new LinkedHashSet<>();
        Boolean bean = false;
        List arrayList = new ArrayList<>();
        Map<Object, Object> hashMap = new HashMap<>();
        String userUuid = UserSession.getUserUuid();  //获取登录ID
        hashMap.put("id", userUuid);
        List<Map> list2 = dao.query("MSPL02.relevance22", hashMap);   //通过ID调用组信息
//        for (int i = 0; i < list2.size(); i++) {    //判断是否含有管理员组
//            if (list2.get(i).get("groupCname").toString().equals("管理员组")) {
//                bean = true;
//                break;
//            } else {
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
        if (bean.equals(false)) {
            //    List SelectList = new ArrayList<>();
            if (arrayList.size() > 0) {
                if (map.get("node").toString().equals("root")) {
                    for (int i = 0; i < arrayList.size(); i++) {    //查询关联关联
                        hashMap.put("id", arrayList.get(i));
                        List<Map> list = dao.query("MSPL02.relevanceSelect", hashMap);
                        if (list.size() > 0) {
                            for (int j = 0; j < list.size(); j++) {
                                SelectSet.add(list.get(j).get("t_param_id"));
                            }
                        }
                    }
                }
            }
        }
        int k = 0;
        if (SelectSet.size() > 0) {
            for (Object hash : SelectSet) { //通过关联关系查询树
                map.put("usergroupid", hash);     //通过id和parent_id查询数据
                List<Map> list3 = dao.query("MSPL0201.queryTree", map);    //获取根节点
                if (list3.size() > 0) {
                    for (int i = 0; i < list3.size(); i++) {
                        List2.add(list3.get(i));
                    }
                }
            }
            ArrayList<Object> list1 = new ArrayList<>();
            for (Object hash : List2) { //将树放入list1
                list1.add(hash);
            }
            PrUtils.BuildOutEiInfo(inInfo, "result", initMetaData(), list1, list1.size());
            String pEname = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
            inInfo.getBlocks().put(pEname, inInfo.getBlock(EiConstant.resultBlock));
            inInfo.getBlocks().remove(EiConstant.resultBlock);
            return inInfo;
        }
        List<Map> list = dao.query("MSPL0201.queryTree", map);
        PrUtils.BuildOutEiInfo(inInfo, "result", initMetaData(), list, list.size());
        String pEname = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
        inInfo.getBlocks().put(pEname, inInfo.getBlock(EiConstant.resultBlock));
        inInfo.getBlocks().remove(EiConstant.resultBlock);
        return inInfo;
    }

    /**
     * @title:  封装树返回类
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:23
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

        eiColumn = new EiColumn("pId"); // 作为kendo.data.Model 不能出现id，parent列
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
     * @date 2021-09-08 15:24
     * 1、通过inInfo获取提交的参数
     * 2、查询此分类下是否已存在此分类编号，如果存在不允许添加
     * 3、通过判断前端传进来的type是否是add
     * 4、如果是add新增数据否则修改数据
     */
    @Override
    public EiInfo insert(EiInfo inInfo) {
        /**
         * 通过inInfo获取提交的参数
         */
        Map<String, String> map = new HashMap<>();
        MSPL02 mspl02 = new MSPL02();
        String classifyName = inInfo.getAttr().get("classifyName").toString().trim();
        mspl02.setClassifyName(inInfo.getAttr().get("classifyName").toString());
        mspl02.setParentId(inInfo.getAttr().get("parentId").toString());
        mspl02.setClassifyCode(inInfo.get("classifyCode").toString());
        /**
         * 判断传入参数是否为空
         */
        if (inInfo.getAttr().get("id") != "" && inInfo.getAttr().get("id").toString() != null) {
            mspl02.setTparamid(inInfo.getAttr().get("id").toString());
        }
        if (inInfo.getAttr().get("usergroupId").toString() != null) {
            mspl02.setUsergroupid(inInfo.getAttr().get("usergroupId").toString());
            map.put("usergroupid", mspl02.getUsergroupid());
            map.put("tparamid", inInfo.getAttr().get("id").toString());
        }
        map.put("classifyCode", mspl02.getClassifyCode());
        map.put("parentId", mspl02.getParentId());
        List<Map> list3 = dao.query("MSPL0201.select2", map);
        List<Map> list = dao.query("MSPL02.queryById", map);   //获取parrntId是否为root

        /**
         * 根据type判断是增加还是更新
         * see新增
         */
        if (inInfo.getAttr().get("type").toString().equals("add") || inInfo.getAttr().get("type").toString().equals("addType")) {
            for (int i = 0; i < list3.size(); i++) {
                if (list3.get(i).get("parent_id").toString().equals("root")) {
                    if (list3.get(i).get("classify_name").equals(classifyName)) {
                        inInfo.setMsg("此分类下存在此名称，不可再次添加");
                        return inInfo;
                    }
                }
            }

            if (mspl02.getUsergroupid().equals("")) {
                Map<Object, Object> mapGroup = new HashMap<>();
                mapGroup.put("tparamid", mspl02.getParentId());
                List<Map> listGroup = dao.query("MSPL0201.queryById", mapGroup);
                if(!listGroup.isEmpty()){
                    mspl02.setUsergroupid(listGroup.get(0).get("user_group_id").toString());
                }
            }
            String uuid = UUIDUtils.getUUID();   //通过工具类生成id
            mspl02.setId(uuid);
            mspl02.setTparamid(uuid);
            PackageOarameters.setRows(inInfo, mspl02);
            EiInfo outInfo = super.insert(inInfo, "MSPL0201.insert");
            if (!mspl02.getUsergroupid().equals("")) {
                super.insert(inInfo, "MSPL0201.groupinsert");
            }
            return outInfo;
        } else {
            if (list3 != null && list3.size() > 0) {
                for (int i = 0; i < list3.size(); i++) {
                    String classify_name = (String) list3.get(i).get("classify_name");
                    classify_name.trim();
                    if (classify_name.equals(classifyName)) {
                        String Tparamid = inInfo.getAttr().get("id").toString();
                        if (list3.get(i).get("id").toString().equals(Tparamid)) {
                            break;
                        } else {
                            inInfo.setMsg("此分类下存在此名称，不可再次添加");
                            return inInfo;
                        }
                    }
                }
            }
            if (StringUtil.isNotEmpty(inInfo.getAttr().get("id").toString())) {
                mspl02.setId(inInfo.getAttr().get("id").toString());
            }
            //用tparamid获取数据库中的usergroupid与mspl02中的usergroupid进行判断是否含有
            //有的话就不用添加没有就添加一个tmparamid不变
            Map<Object, Object> map1 = new HashMap<>();
            map1.put("tparamid", mspl02.getTparamid());
            //    map1.put("usergroupid",mspl02.getUsergroupid());
            if (!inInfo.getAttr().get("usergroupId").toString().trim().equals("")) {
                map.put("usergroupId", inInfo.getAttr().get("usergroupId").toString());
                List<MSPL0202> list4 = dao.query("MSPL0201.queryById", map);
                if (list4.size() <= 0) {
                    if (list.size() > 0) {
                            PackageOarameters.setRows(inInfo, mspl02);
                            super.insert(inInfo, "MSPL0201.groupinsert");
                    }
                } else {
                    inInfo.setMsg("这块区域已经关联了该部门");
                    return inInfo;
                }
            }
            PackageOarameters.setRows(inInfo, mspl02);
            EiInfo outInfo = super.update(inInfo, "MSPL0201.update");
            return outInfo;
        }
    }
}
