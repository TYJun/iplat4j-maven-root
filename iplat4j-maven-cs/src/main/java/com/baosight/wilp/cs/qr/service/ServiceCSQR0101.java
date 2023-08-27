package com.baosight.wilp.cs.qr.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.cs.common.CSUtils;
import com.baosight.wilp.cs.re.domain.CsConstant;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 保洁确认子页面Service
 * 一、页面加载.
 * 二、保洁工单的确认并指派执行人.
 * 
 * @Title: ServiceCSQR0101.java
 * @ClassName: ServiceCSQR0101
 * @Package：com.baosight.wilp.cs.qr.service
 * @author: fangzekai
 * @date: 2021年11月24日 下午7:40:59
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCSQR0101 extends ServiceBase {

    /**
     * 一、页面加载.
     *
     * @Title: initLoad
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
    public EiInfo initLoad(EiInfo inInfo) {
        // 调用本地服务CSRE01.queryTaskInfo加载页面。
        inInfo.set(EiConstant.serviceName, "CSRE01");
        inInfo.set(EiConstant.methodName, "queryTaskInfo");
        EiInfo outInfo = XLocalManager.call(inInfo);
        return outInfo;
    }
    
    
    /**
     * 二、保洁工单的确认并指派执行人.
     *  1、获取inInfo中的参数并对参数进行处理.
     *  2、以map为参数执行CSRE01.queryGDStatus语句获取当前工单的状态.
     *     判断当前工单的状态是否符合确认流程.
     *     若符合确认流程，则遍历登记的所有保洁事项，为他们分配前端指派的执行人.
     *     对有指派执行人的保洁事项进行“先增后删”的操作，一一匹配对保洁事项插入指派的执行人.
     *     之后执行CSRE01.updateCSGD更改保洁主表的工单状态为确认工单状态.
     *     执行CSRE01.updateCSGDMX更改保洁工单明细表的工单状态为确认工单状态.
     *     调用本地服务CSRE01.insertCSGDLC插入确认流程于保洁工单流程表中.
     *     最后返回操作结果.
     *
     * @Title: comfirmMethod 
     * @param inInfo
     * @return 
     * @return: EiInfo
     */
    @SuppressWarnings("unchecked")
    public EiInfo comfirmMethod(EiInfo inInfo) {
        /*
         *  1、获取inInfo中的参数并对参数进行处理.
         */
        EiInfo outInfo = new EiInfo();
        Map<String, String> map = new HashMap<>();
        List<Map<String, String>> itemList = null;
        // 参数处理
        String loginName = StringUtils.isBlank((String)inInfo.get("workNo")) ? UserSession.getUser().getUsername()
            : (String)inInfo.get("workNo");
        Map<String, Object> userInfo = CSUtils.getUserInfo(loginName);
        String taskId = inInfo.getString("taskId");
        String statusCode = inInfo.getString("statusCode");
        map.put("taskId", taskId);
        map.put("statusCode", statusCode);
        // 判断inInfo中的dataItems保洁事项列表是否为空。
        if (inInfo.get("dataItems") != null) {
            itemList = (List<Map<String, String>>)inInfo.get("dataItems");
        } else {
            itemList = (List<Map<String, String>>)inInfo.getAttr().get("dataItems");
        }
        // 操作人
        inInfo.set("operationUserNo", loginName);
        inInfo.set("operationUserName", userInfo == null ? "" : userInfo.get("name"));
        /*
         * 2、以map为参数执行CSRE01.queryGDStatus语句获取当前工单的状态.
         *    判断当前工单的状态是否符合确认流程.
         *    若符合确认流程，则遍历登记的所有保洁事项，为他们分配前端指派的执行人.
         *    对有指派执行人的保洁事项进行“先增后删”的操作，一一匹配对保洁事项插入指派的执行人.
         *    之后执行CSRE01.updateCSGD更改保洁主表的工单状态为确认工单状态.
         *    执行CSRE01.updateCSGDMX更改保洁工单明细表的工单状态为确认工单状态.
         *    调用本地服务CSRE01.insertCSGDLC插入确认流程于保洁工单流程表中.
         *    最后返回操作结果。
         */
        String status = "";
        List result = dao.query("CSRE01.queryGDStatus", map);
        //获取配置：每天任务量短信模板
        String dayWorkTemplate = CommonUtils.getDataCodeItemName("wilp.cs.config", "05");
        //获取app编码
        String appNo = "AP00001";
        if (result != null && result.size() > 0) {
            status = (String)result.get(0);
        }
        if (status.equals(CsConstant.STATUS_UN_CONFIRM)) {
            // 遍历事项信息集合，添加属性
            itemList.forEach(e -> {
                String executeUserNo = e.get("executeUserNo");
                String executeUserName = e.get("executeUserName");
                String itemId = e.get("itemId");

                List<String> recvList = Arrays.asList(new String[]{e.get("executeUserNo").toString()});
                //3.2 构建参数
                String taskCount = "您有一条新的保洁单，请及时在app中查看！";
                List<String> paramList = Arrays.asList(new String[]{taskCount});
                System.out.println("接收人信息："+recvList);
                //确定执行人后将消息推送过去
                BaseDockingUtils.pushMsg(recvList,paramList,dayWorkTemplate,appNo,null);

                // 对参数 executeUserNo 和 executeUserName字符串 进行一个处理。
                // 接收来的数据有一个数值(**)或多个数值并逗号隔开(**,**)两种形式,所以要分别进行判断操作。
                if (StringUtils.isNotBlank(executeUserNo) && executeUserNo.split(",").length > 1) {
                    // 声明一个 allItems 的列表用来存储遍历挨个挨个登记的保洁事项。
                    List<Map<String, String>> allItems = new LinkedList<>();
                    String[] executroNolist = executeUserNo.split(",");
                    String[] executorlist = executeUserName.split(",");

                    for (int i = 0; i < executroNolist.length; i++) {
                        Map<String, String> item = new HashMap<>();
                        item.put("taskId", inInfo.getString("taskId"));
                        item.put("statusCode", inInfo.getString("statusCodeY"));
                        item.put("itemTypeCode", e.get("typeCode"));
                        item.put("itemTypeName", e.get("typeName"));
                        item.put("itemCode", e.get("itemCode"));
                        item.put("itemName", e.get("itemName"));
                        item.put("itemComments", e.get("comments"));
                        item.put("serviceDeptNum", e.get("serviceDeptNum"));
                        item.put("serviceDeptName", e.get("serviceDeptName"));
                        item.put("executeUserNo", executroNolist[i]);
                        item.put("executeUserName", executorlist[i]);
                        item.put("id", UUID.randomUUID().toString());
                        allItems.add(item);
                    }
                    // 以 allItems 列表为参数执行CSRE01.insertCSGDZXR 保存工单事项对应的执行人信息。
                    dao.insert("CSRE01.insertCSGDZXR", allItems);
                    // 以 itemId 为参数执行CSRE01.deleteCSGDMXById 删除工单已经指派执行人的原事项数据。
                    dao.insert("CSRE01.deleteCSGDMXById", itemId);
                }else if(StringUtils.isNotBlank(executeUserNo)){
                    // 声明一个 allItemSingle 的列表用来存储遍历挨个挨个登记的保洁事项。
                    List<Map<String, String>> allItemSingle = new LinkedList<>();
                    Map<String, String> item = new HashMap<>();
                    item.put("taskId", inInfo.getString("taskId"));
                    item.put("statusCode", inInfo.getString("statusCodeY"));
                    item.put("itemTypeCode", e.get("typeCode"));
                    item.put("itemTypeName", e.get("typeName"));
                    item.put("itemCode", e.get("itemCode"));
                    item.put("itemName", e.get("itemName"));
                    item.put("itemComments", e.get("comments"));
                    item.put("serviceDeptNum", e.get("serviceDeptNum"));
                    item.put("serviceDeptName", e.get("serviceDeptName"));
                    item.put("executeUserNo", executeUserNo);
                    item.put("executeUserName", executeUserName);
                    item.put("id", UUID.randomUUID().toString());
                    allItemSingle.add(item);
                    // 以 allItemSingle 列表为参数执行CSRE01.insertCSGDZXR 保存工单事项对应的执行人信息。
                    dao.insert("CSRE01.insertCSGDZXR", allItemSingle); // 保存工单事项对应的执行人信息。
                    // 以 itemId 为参数执行CSRE01.deleteCSGDMXById 删除工单已经指派执行人的原事项数据。
                    dao.insert("CSRE01.deleteCSGDMXById", itemId); // 删除工单已经指派执行人的原事项数据。
                }
            });
            // 将主表的确认工单人与确认时间赋入。
            map.put("confirmUserNo", loginName);
            map.put("confirmUserName", userInfo == null ? "" : (String)userInfo.get("name"));
            map.put("confirmTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            // 执行CSRE01.updateCSGD更改保洁主表的工单状态为确认工单状态。
            dao.update("CSRE01.updateCSGD", map);
            // 执行CSRE01.updateCSGDMX更改保洁工单明细表的工单状态为确认工单状态。
            dao.update("CSRE01.updateCSGDMX", map);
            // 调用本地服务CSRE01.insertCSGDLC插入确认流程于保洁工单流程表中。
            inInfo.set(EiConstant.serviceName, "CSRE01");
            inInfo.set(EiConstant.methodName, "insertCSGDLC");
            outInfo = XLocalManager.call(inInfo);

            outInfo.addMsg("操作成功");
            outInfo.setMsgKey("200");
        } else {
            outInfo.addMsg("操作失败");
            outInfo.setMsgKey("201");
        }

        return outInfo;

    }

}
