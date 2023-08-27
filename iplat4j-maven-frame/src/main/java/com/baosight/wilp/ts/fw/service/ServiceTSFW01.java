package com.baosight.wilp.ts.fw.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 对外服务接口.
 * <p>
 * 查询员工列表, 通过员工ID或者员工工号查询员工详情, 通过科室ID或者科室编码查询科室下员工, 通过科室ID或者科室编码查询科室下员工数量
 * 查询科室列表, 通过科室ID或者科室编码查询科室详情, 通过员工ID或者员工工号查询所属科室详情, 通过地点获取科室信息, 通过科室查询地点信息...
 * </p>
 *
 * @Title ServiceACFW01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 */
@SuppressWarnings("unchecked")
public class ServiceTSFW01 extends ServiceBase {
    String projectSchema = PrUtils.getConfigure();

    public EiInfo saveTempData(EiInfo inInfo) {
        /**
         * 1.从入参中获取参数存入map中
         */
        List rows = (List) inInfo.get("rows");
//        if (rows.isEmpty()) {
//            inInfo.setStatus(-1);
//            inInfo.setMsg("没有可保存的数据");
//            return inInfo;
//        }

        String data = JSON.toJSONString(rows);
        String userId = UserSession.getUser().getUsername();
        String pageId = inInfo.get("pageId") == null ? "" : inInfo.getString("pageId");
        String controlId = inInfo.get("controlId") == null ? "" : inInfo.getString("controlId");
        String lastUpdateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("pageId", pageId);
        map.put("controlId", controlId);
        map.put("lastUpdateTime", lastUpdateTime);
        map.put("data", data);
        map.put("projectSchema", projectSchema);
        dao.delete("TSFW01.deleteLastTempData", map);
        if (!rows.isEmpty()) {
            dao.insert("TSFW01.insertTempData", map);
        }

        /**
         *  4.返回 EiInfo
         */
        inInfo.setMsg("保存成功");
        return inInfo;
    }

    public EiInfo loadTempData(EiInfo inInfo) {
        String userId = UserSession.getUser().getUsername();
        String pageId = inInfo.get("efCurFormEname") == null ? "" : inInfo.getString("efCurFormEname");
        String controlId = inInfo.get("controlId") == null ? "" : inInfo.getString("controlId");
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("pageId", pageId);
        map.put("controlId", controlId);
        map.put("projectSchema", projectSchema);
        List<Map<String, String>> resultList = dao.query("TSFW01.queryTempData", map);
        String data = resultList.get(0).get("data");
        JSONArray jsonArr = JSON.parseArray(data);
        List<Map<String, Object>> list = new ArrayList<>();
        if (jsonArr != null && jsonArr.size() > 0) {
            for (int i = 0; i < jsonArr.size(); i++) {
                JSONObject jsonObj = (JSONObject) jsonArr.get(i);
                Map<String, Object> map1 = new HashMap<>();
                Iterator iterator = jsonObj.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    String value = jsonObj.get(key).toString();
                    map1.put(key, value);
                }
                list.add(map1);
            }
        }

        if (!CollectionUtils.isEmpty(list)) {
            EiInfo eiInfo=new EiInfo();
            EiBlock resultBlock = new EiBlock("result");
            resultBlock.setBlockMeta( PrUtils.createBlockMeta(list.get(0)));
            resultBlock.addRows(list);
            eiInfo.addBlock(resultBlock);
            return eiInfo;
        } else {
            return inInfo;
        }
    }
}
