package com.baosight.wilp.cs.sx.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.cs.common.CSUtils;
import com.baosight.wilp.cs.common.CsEiUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 保洁事项管理主页面Service.
 * 一、保洁事项页面的初始化方法.
 * 二、保洁事项页面查询方法.
 * 三、获取保洁事项的分类信息：树形菜单.
 * 四、插入节点.
 * 五、更新项目分类信息.
 * 六、删除节点.
 * 七、递归获取节点id.
 * 八、删除事项信息.
 * 九、PC端查询保洁事项分类.
 * 十、App查询保洁事项.
 *
 * @Title: ServiceCSSX01.java
 * @ClassName: ServiceCSSX01
 * @Package：com.baosight.wilp.cs.sx.service
 * @author: fangzekai
 * @date: 2021年11月19日 下午4:57:46
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
@SuppressWarnings("all")
public class ServiceCSSX01 extends ServiceBase {

    private static Lock lock = new ReentrantLock();

    /**
     * 一、保洁事项页面的初始化方法.
     *
     * @Title initLoad
     * @Param inInfo
     * @return
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return this.query(inInfo);
    }

    /**
     * 二、 保洁事项页面查询方法.
     * 1、将参数进行封装（包含分页）.
     * 2、对接收的额外参数 itemCodeList字符串 进行一个处理.
     *    接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作.
     * 3、进行数据的查询并返回.
     *
     * @Title query
     * @param inInfo typeName 事项分类
     *               itemName 保洁事项
     *               typeId 保洁事项id
     *        额外参数： itemCodeList 事项编码列
     * @return itemId 主键
     * itemCode 事项编码
     * itemName 事项名称
     * moduleId 事项分类id
     * serviceDeptNum 事项绑定的科室编码
     * serviceDeptName 事项绑定的科室名称
     * comments 备注
     * typeCode 分类编码
     * typeName 分类名称
     * @see ServiceBase#initLoad(EiInfo)
     */
    public EiInfo query(EiInfo inInfo) {
        /*
         * 1、将参数进行封装（包含分页）.
         */
        String[] param = {"itemName", "typeName", "typeId"};
        Map<String, Object> map = CsEiUtils.buildParam(inInfo, Arrays.asList(param), "result");
        /*
         * 2、对接收的额外参数 itemCodeList字符串 进行一个处理.
         *  接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作.
         */
        // 先实例化 itemCodeList。
        List<Map<String, String>> itemCodeList = new LinkedList<>();
        // 获取参数的值。
        String itemCode = (String) inInfo.get("itemCodeList");
        // 对获取的值进行判空和以逗号隔开做长度判断，当为lenght>1是则该值为多个数组组成。
        if (StringUtils.isNotBlank(itemCode) && itemCode.split(",").length > 1) {
            // 以一个数组去存分割后的字符串。
            String[] itemCodeTotal = itemCode.split(",");
            // 遍历该数组的长度。
            for (int i = 0; i < itemCodeTotal.length; i++) {
                // 实例化一个Map<String,String>类型的item，用来接收拆出来的itemCode。
                Map<String, String> item = new HashMap<>();
                item.put("itemCode", itemCodeTotal[i]);
                // 将接收到数据的map添加到itemCodeList列表中。
                itemCodeList.add(item);
            }
        // 处理lenght<1，即当获取的值为一个值的情况。
        }else if(StringUtils.isNotBlank(itemCode)){
            // 实例化一个Map<String,String>类型的item，用来接收拆出来的itemCode。
            Map<String, String> item = new HashMap<>();
            item.put("itemCode", itemCode);
            // 将接收到数据的map添加到itemCodeList列表中。
            itemCodeList.add(item);
        }
        // 将存有itemCode的 itemCodeList 列表再用一个map接收。
        map.put("itemCodeList",itemCodeList);
        /*
         * 3、进行数据的查询并返回.
         */
        // 将map作为参数传到"CSSX01.queryItem" 进行数据列表的查询。
        List<Map<String, String>> list = dao.query("CSSX01.queryItem", map);
        // 查询数据列表的数据数量。
        int count = dao.count("CSSX01.countItem", map);
        // 返回结果。
        return CsEiUtils.buildResult(inInfo, list, count, "result");
    }

    /**
     * 三、获取保洁事项的分类信息：树形菜单.
     * 1、查询保洁事项分类.
     * 2、通过该节点信息获取其子节点信息.
     * 3、将子节点信息返回.
     *
     * @Title queryTypeTree
     * @param inInfo
     * @return id    节点id
     * @return: EiInfo
     */
    public EiInfo queryTypeTree(EiInfo inInfo) {
        inInfo.set("result-limit", 1000);
        /*
         * 1、查询保洁事项分类.
         */
        EiInfo outInfo = super.query(inInfo, "CSSX01.queryClass");
        /*
         * 2、通过该节点信息获取其子节点信息.
         */
        String pEname = outInfo.getCellStr(EiConstant.queryBlock, 0, "node");
        /*
         * 3、将子节点信息返回.
         */
        outInfo.getBlocks().put(pEname, outInfo.getBlock(EiConstant.resultBlock));
        outInfo.getBlocks().remove(EiConstant.resultBlock);
        return outInfo;
    }

    /**
     * 四. 插入节点.
     *
     * @Title insert
     * @param inInfo id   分类id
     *               classifyCode  分类编码
     *               classifyName  分类名称
     *               parentId      父节点id
     *               memo          备注
     * @return 新增节点成功，失败则执行回滚操作。
     * @see ServiceBase#insert(EiInfo)
     */
    public EiInfo insert(EiInfo inInfo) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", UUID.randomUUID().toString());
        // 调用CSUtils.generationSerialNo 指定参数生成事项的编码。
        map.put("classifyCode", CSUtils.generationSerialNo("clean_sx_num", "SX", "1"));
        map.put("classifyName", inInfo.getAttr().get("classifyName").toString());
        map.put("parentId", inInfo.getAttr().get("parentId").toString());
        map.put("memo", inInfo.getAttr().get("memo").toString());
        // 执行插入操作。
        dao.update("CSSX01.insertClass", map);
        return inInfo;
    }


    /**
     * 五、更新项目分类信息.
     *
     * @Title update
     * @param inInfo id   分类id
     *               classifyName  分类名称
     *               memo          备注
     * @return 跟新分类成功，失败则执行回滚操作。
     * @see ServiceBase#update(EiInfo)
     */
    public EiInfo update(EiInfo inInfo) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", inInfo.getAttr().get("id").toString());
        map.put("classifyName", inInfo.getAttr().get("classifyName").toString());
        map.put("memo", inInfo.getAttr().get("memo").toString());
        // 执行更新操作。
        dao.update("CSSX01.updateClass", map);
        return inInfo;
    }

    /**
     * 六、删除节点.
     * 1. 获取id值，根据该id执行递归方法获取其子节点id.
     * 2. 判断分类及子分类下是否有保洁事项.
     * 3. 获取到所有id再执行删除操作.
     *
     * @Title: deleteBatch
     * @param inInfo id  节点id
     * @return 删除成功，失败则执行回滚操作
     * @return: EiInfo
     */
    public EiInfo deleteBatch(EiInfo inInfo) {
        inInfo.set("result-limit", 1000);
        List<String> list = new ArrayList<String>();
        lock.lock();
        try {
            /*
             * 1. 获取id值，根据该id执行递归方法获取其子节点id.
             */
            list.add(inInfo.getAttr().get("id").toString());

            getParentId(inInfo.getAttr().get("id").toString(), list);
            /*
             * 2. 判断分类及子分类下是否有保洁事项.
             */
            int count = super.count("CSSX01.isExistItem", list);
            if (count > 0) {
                inInfo.setStatus(-1);
                inInfo.setMsg("该事项分类或其子分类下有保洁事项信息，无法删除");
                return inInfo;
            }
            /*
             * 3. 获取到所有id再执行删除操作.
             */
            dao.delete("CSSX01.deleteClass", list);
            return inInfo;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 七、递归获取节点id.
     * 1. 获取保洁分类id列表.
     * 2. 根据该id执行递归方法获取其子节点id.
     *
     * @Title: getParentId
     * @param id         节点id
     * @param resultList 子节点id
     */
    public void getParentId(String id, List<String> resultList) {
        /*
         * 1. 获取保洁分类id列表.
         */
        List<String> list = dao.query("CSSX01.getChildId", id);
        if (list.isEmpty()) {
            return;
        }
        resultList.addAll(list);
        /*
         * 2. 根据该id执行递归方法获取其子节点id.
         */
        list.forEach(e -> {
            getParentId(e, resultList);
        });
    }

    /**
     * 八、删除事项信息.
     * 1. 获取选中事项id并执行批量删除操作.
     * 2. 执行删除事项操作.
     *
     * @Title: deleteItem
     * @param info list  选中行id
     * @return 删除成功，删除失败则执行回滚操作
     * @return: EiInfo
     */
    public EiInfo deleteItem(EiInfo info) {
        /*
         * 1. 获取选中事项id并执行批量删除操作.
         */
        List<String> list = (List<String>) info.get("list");
        /*
         * 2. 执行删除事项操作.
         */
        dao.delete("CSSX01.deleteItem", list);
        EiInfo outInfo = new EiInfo();
        outInfo.setMsg("操作成功");
        return outInfo;
    }

    /**
     * 九、PC端查询保洁事项分类.
     * 1. 获取参数，从前端获取节点值.
     * 2. 查询数据，查询保洁事项分类.
     * 3. 增加节点 block 块.
     *
     * @Title: queryTree
     * @throws
     * @param: @param inInfo
     * node ： 选中节点的id
     * @param: @return
     * @return: EiInfo
     * id : 节点id（分类id）,
     * classifyName :  节点名称（分类名称）
     * parentId : 父节点id(上级分类id)
     */
    @SuppressWarnings("rawtypes")
    public EiInfo queryTree(EiInfo inInfo) {
        /*
         * 1. 获取参数，从前端获取节点值.
         */
        String node = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
        Map<String, String> queryMap = new HashMap<>(16);
        queryMap.put("node", node);
//       queryMap.put("dataGroupCode", inInfo.getString("dataGroupCode"));
        /*
         * 2. 查询数据，查询保洁事项分类.
         */
        List rows = dao.query("CSSX01.queryClass", queryMap);
        /*
         * 3. 增加节点 block 块.
         */
        EiInfo outInfo = new EiInfo();
        EiBlock outBlock = outInfo.addBlock(node);
        outBlock.addRows(rows);
        return outInfo;
    }

    /**
     * 十、App查询保洁事项.
     *
     * @Title selectItemToApp
     * @param inInfo
     * @return
     */
    public EiInfo selectItemToApp(EiInfo inInfo) {
        HashMap<Object, Object> map = new HashMap<>();
        // 事项名称
        map.put("itemName", inInfo.getString("itemName"));
        // 事项编码
        map.put("itemCode", inInfo.get("itemCode"));
        // 事项分类名称
        map.put("typeName", inInfo.getString("typeName"));
        // 执行CSSX01.queryItem查询保洁事项列表
        List<Map<String,Object>> list = dao.query("CSSX01.queryItem", map);
        inInfo.addBlock("clean_item_list").addRows(list);
        return inInfo;
    }

}
