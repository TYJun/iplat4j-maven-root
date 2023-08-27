package com.baosight.wilp.ir.sy.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.ir.sy.util.EiInfoUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackageName com.baosight.wilp.ir.sy.service
 * @ClassName ServiceIRSY03
 * @Description 首页待办需求服务接口
 * @Author chunchen2
 * @Date 2022/1/13 13:44
 * @Version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2022/1/13 13:44
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class ServiceIRSY03 extends ServiceEPBase {

    /**
     * @Title queryToDoList
     * @Author chunchen2
     * @Description // 查询待办的任务列表
     *              1. 从小代码wilp.ir.sy.todoService 里面获取要对接的业务服务信息，参数的值className:methodName
     *              2. 依次调用服务，获取最新的数据，将数据封装成指定格式返回页面渲染
     * 首页待办各模块返回的数据格式,以维修为例
     * {
     * 	moduleName:'维修', //模块名称
     * 	todoCount: '100',  // 模块待办的总数量
     * 	todoDetailUrl: 'MTCQ01', // 更多链接展示的详情页面
     * 	todoList:[// 待办列表,这里后台最多只返回最新的10条
     *        {
     * 			jumpUrl: 'MTAC01?taskno=123',
     * 			itemMsg: '您有一条工单号MT220106014待受理！',
     *        }，
     *        {
     * 			jumpUrl: 'MTAC01?taskno=xxx',
     * 			itemMsg: '工单号MT220106015等待您的派工！',
     *        }，
     *        {...}
     * 	 ]
     * }
     * @Date 13:59 2022/1/14
     * @param inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryToDoList(EiInfo inInfo){

        // [{"label": "维修", "value": "ServiceMTAC01:query"}, {...}]
        List<Map<String, String>> todoServiceLists = CommonUtils.getDataCode("wilp.ir.sy.todoService");

        if(todoServiceLists.size() < 1){ // 没有配置要对接的服务
            // 展示一个默认的展示信息
            EiInfoUtils.buildOutEiInfo(inInfo, "result", null, null, 0);
        } else {
            List<Map<String, Object>> retLists = new ArrayList<>();
            for(Map<String, String> todoServiceItem : todoServiceLists){
                String moduleName = todoServiceItem.get("label");

                String moduleParams = todoServiceItem.get("value");
                String moduleClassName = moduleParams.split(":")[0];
                String moduleMethodName = moduleParams.split(":")[1];

                EiInfo eiInfo = new EiInfo();
                eiInfo.set(EiConstant.serviceName, moduleClassName);
                eiInfo.set(EiConstant.methodName, moduleMethodName);
                XLocalManager.call(eiInfo);

                retLists.add((Map<String, Object>) eiInfo.get("todoDetail"));
            }

            EiInfoUtils.buildOutEiInfo(inInfo, "result", EiInfoUtils.createBlockMeta(retLists.get(0)),
                    retLists, retLists.size());

        }

        return inInfo;
    }

    public static int count = 0;

    public EiInfo mtTodoList(EiInfo inInfo){

        int zsCount = 10;
        count++;

        if(count % 2 == 0){
            zsCount += count;
        }

        Map<String, Object> buildMap = new HashMap<>();
        buildMap.put("moduleName", "维修");
        buildMap.put("todoCount", zsCount);
        buildMap.put("todoDetailUrl", "EDCC03");

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("jumpUrl", "IRSY0202?id=19");
        jsonObject1.put("itemMsg", "您有一条工单号MT220106014待受理！");
        jsonArray.add(jsonObject1);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("jumpUrl", "IRSY0202?id=17");
        jsonObject2.put("itemMsg", "工单号MT220106015等待您的派工！");
        jsonArray.add(jsonObject2);

        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("jumpUrl", "IRSY0202?id=17");
        jsonObject3.put("itemMsg", "工单号MT220106015等待您的派工！");
        jsonArray.add(jsonObject3);

        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("jumpUrl", "IRSY0202?id=17");
        jsonObject4.put("itemMsg", "工单号MT220106015等待您的派工！");
        jsonArray.add(jsonObject4);

        if(count % 2 == 0){
            JSONObject jsonObject5 = new JSONObject();
            jsonObject5.put("jumpUrl", "IRSY0202?id=17");
            jsonObject5.put("itemMsg", "工单号MT220106015等待您的派工！");
            jsonArray.add(jsonObject5);

            JSONObject jsonObject6 = new JSONObject();
            jsonObject6.put("jumpUrl", "IRSY0202?id=19");
            jsonObject6.put("itemMsg", "您有一条工单号MT220106014待受理！");
            jsonArray.add(jsonObject6);

            JSONObject jsonObject7 = new JSONObject();
            jsonObject7.put("jumpUrl", "IRSY0202?id=17");
            jsonObject7.put("itemMsg", "工单号MT220106015等待您的派工！");
            jsonArray.add(jsonObject7);

            JSONObject jsonObject8 = new JSONObject();
            jsonObject8.put("jumpUrl", "IRSY0202?id=17");
            jsonObject8.put("itemMsg", "工单号MT220106015等待您的派工！");
            jsonArray.add(jsonObject8);

            JSONObject jsonObject9 = new JSONObject();
            jsonObject9.put("jumpUrl", "IRSY0202?id=17");
            jsonObject9.put("itemMsg", "工单号MT220106015等待您的派工！");
            jsonArray.add(jsonObject9);

            JSONObject jsonObject10 = new JSONObject();
            jsonObject10.put("jumpUrl", "IRSY0202?id=17");
            jsonObject10.put("itemMsg", "工单号MT220106015等待您的派工！");
            jsonArray.add(jsonObject10);
        }



        buildMap.put("todoList", jsonArray);
        inInfo.set("todoDetail", buildMap);

        return inInfo;
    }

    public EiInfo xjTodoList(EiInfo inInfo){

        Map<String, Object> buildMap = new HashMap<>();
        buildMap.put("moduleName", "巡检");
        buildMap.put("todoCount", "6");
        buildMap.put("todoDetailUrl", "IRSY02");

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("jumpUrl", "IRSY0202?id=19");
        jsonObject1.put("itemMsg", "您有一条工单号MT220106014待受理！");
        jsonArray.add(jsonObject1);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("jumpUrl", "IRSY0202?id=17");
        jsonObject2.put("itemMsg", "工单号MT220106015等待您的派工！");
        jsonArray.add(jsonObject2);

        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("jumpUrl", "IRSY0202?id=17");
        jsonObject3.put("itemMsg", "工单号MT220106015等待您的派工！");
        jsonArray.add(jsonObject3);

        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("jumpUrl", "IRSY0202?id=17");
        jsonObject4.put("itemMsg", "工单号MT220106015等待您的派工！");
        jsonArray.add(jsonObject4);

        JSONObject jsonObject5 = new JSONObject();
        jsonObject5.put("jumpUrl", "IRSY0202?id=17");
        jsonObject5.put("itemMsg", "工单号MT220106015等待您的派工！");
        jsonArray.add(jsonObject5);

        JSONObject jsonObject6 = new JSONObject();
        jsonObject6.put("jumpUrl", "IRSY0202?id=19");
        jsonObject6.put("itemMsg", "您有一条工单号MT220106014待受理！");
        jsonArray.add(jsonObject6);


        buildMap.put("todoList", jsonArray);
        inInfo.set("todoDetail", buildMap);

        return inInfo;
    }

}
