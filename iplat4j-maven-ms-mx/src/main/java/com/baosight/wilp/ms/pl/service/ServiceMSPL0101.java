package com.baosight.wilp.ms.pl.service;

import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ms.common.service.InfluxDBService;
import com.baosight.wilp.ms.common.web.GatherServer;
import com.baosight.wilp.utils.ResultUtil;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import com.baosight.wilp.ms.common.util.PrUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author 15479
 * @title: ServiceMSPL0101
 * @projectName iplat_v5_monitor
 * @description: 点位参数新增页面（页面初始化方法、查询
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @date 2021/8/11 9:07
 */
public class ServiceMSPL0101 extends ServiceBase {

    @Autowired
    private InfluxDBService influxDBService;

    /**
     * @title: 页面初始化方法
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @date 2021-09-08 15:29
     * 1、通过inInfo获取查询的参数
     * 2、通过super.initLoad方法把查询参数传入
     * 3、获取sql语句查询参数封装进inInfo
     * 4、返回inInfo参数
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        /**
         * 通过inInfo获取查询的参数
         */
        String id = inInfo.getAttr().get("id").toString();
        String type = inInfo.getAttr().get("type").toString();
        String parentId = inInfo.getAttr().get("parentId").toString();
        QueryResult queryResult = (QueryResult) queryParams();
        List<QueryResult.Result> resultList = queryResult.getResults();
        QueryResult.Result result = resultList.get(0);
        List<QueryResult.Series> seriesList = result.getSeries();
        if (seriesList == null) {
            return inInfo;
        }
        QueryResult.Series series = seriesList.get(0);
        List<List<Object>> map = series.getValues();
        List<Map<String,Object>> reslist = new ArrayList();
        List<Map<String,Object>> mysqlParam  = dao.query("MSPL01.selectParam",null);
        for (List<Object> list : map) {
        Map<String,Object> resMap = new HashMap();
        if (list.get(3) != null || list.get(6) != null){
            String name = (String) list.get(3);
            resMap.put("name", name);
            resMap.put("value", list.get(6).toString());
            reslist.add(resMap);
        }
    }
        List<Map<String, Object>> newList = reslist.stream().filter(
                (mapItem) -> !mysqlParam.stream().map(item -> item.get("value")
                ).collect(Collectors.toList()).contains(mapItem.get("value"))
        ).collect(Collectors.toList());

        PrUtils.BuildOutEiInfo(inInfo, "result", initMetaData(), newList, newList.size());
        Map resMaps = new HashMap();
        resMaps.put("id", id);
        resMaps.put("parentId", parentId);
        resMaps.put("types", type);
        inInfo.setAttr(resMaps);
        return inInfo;
    }

    /**
     * @title: 查询
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021-09-08 15:29
     * 1、获取查询参数
     * 2、通过参数查询influxDB内的数据
     * 3、将数据存入inInfo下的result中
     * 4、返回inInfo对象
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        /**
         * 获取查询参数
         */
        EiBlock result1 = (EiBlock) inInfo.getBlocks().get("result");
        Map attr = result1.getAttr();
        EiBlock inqu_status = (EiBlock) inInfo.getBlocks().get("inqu_status");
        HashMap o = (HashMap) inqu_status.getRows().get(0);
        Integer limit = 0;
        Integer offset = 0;
        QueryResult queryResult1 = null;
        String value = null;
        String id = inInfo.getAttr().get("id").toString();
        inInfo.getAttr().put("type", "add");
        String type = inInfo.getAttr().get("type").toString();
        String parentId = inInfo.getAttr().get("parentId").toString();
        Map map = inInfo.getBlocks();
        EiBlock eiBlock2 = (EiBlock) map.get("result");
        Map m = eiBlock2.getAttr();
        List<Map<String,Object>> mysqlParam  = dao.query("MSPL01.selectParam",null);
        /**
         * 判断传入分页是否为空
         */
        if (m.get("limit") != null) {
            limit = (Integer) m.get("limit");
        }
        if (m.get("offset") != null) {
            offset = (Integer) m.get("offset");
        }

        EiBlock eiBlock = (EiBlock) map.get("inqu_status");
        List<Map> list = eiBlock.getRows();
        value = list.get(0).get("value").toString().trim();

        QueryResult count = influxDBService.queryCount(GatherServer.PARAM_MEASUREMENT, value);
        List<Object> list1 = count.getResults().get(0).getSeries().get(0).getValues().get(0);
        Double o1 = (Double) list1.get(2);
        int count1 = (int) Math.round(o1);
        Integer count2 = Integer.valueOf(count1);  //查询计数
        queryResult1 = influxDBService.queryMeasurementX(GatherServer.PARAM_MEASUREMENT, value, count2, 0);
        if (queryResult1.getResults().get(0).getSeries() != null) {
            List<QueryResult.Result> resultList = queryResult1.getResults();

            QueryResult.Result result = resultList.get(0);
            List<QueryResult.Series> seriesList = result.getSeries();
            QueryResult.Series series = seriesList.get(0);
            List<List<Object>> map2 = series.getValues();
            List<Map<String,Object>> reslist = new ArrayList();
            for (List<Object> list2 : map2) {   //循环查询结果放到reslist中
                Map resMap = new HashMap();
                String name = (String) list2.get(3);
                String value2 = (String) list2.get(6);
                resMap.put("name", name);
                resMap.put("value", value2);
                reslist.add(resMap);
            }
            List<Map<String, Object>> newList = reslist.stream().filter(
                    (mapItem) -> !mysqlParam.stream().map(item -> item.get("value")
                    ).collect(Collectors.toList()).contains(mapItem.get("value"))
            ).collect(Collectors.toList());
            if (limit+offset>newList.size()){
                PrUtils.BuildOutEiInfo(inInfo, "result", initMetaData(), newList.subList(offset,newList.size()), newList.size());
            }else{
                PrUtils.BuildOutEiInfo(inInfo, "result", initMetaData(), newList.subList(offset,offset+limit), newList.size());
            }
            Map resMaps = new HashMap();
            resMaps.put("id", id);
            resMaps.put("parentId", parentId);
            resMaps.put("types", type);
            inInfo.setAttr(resMaps);
        }

        return inInfo;
    }
    /**
     * 查询所有参数
     *
     * @return
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/10 6:12 下午
     * @params
     */
    @PostMapping("query/params")
    @CrossOrigin
    public Object queryParams() {
        return influxDBService.queryMeasurement(GatherServer.PARAM_MEASUREMENT);
    }

    /**
     * @title: 封装树返回类
     * @projectName iplat_v5_monitor
     * @description: TODO
     * @author 孔帅博
     * @date 2021-09-08 15:31
     */
    private EiBlockMeta initMetaData() {
        EiBlockMeta eiMetadata = new EiBlockMeta();
        EiColumn eiColumn = new EiColumn("name");
        eiColumn.setDescName("名称");
        eiColumn.setNullable(false);
        eiColumn.setPrimaryKey(true);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("value");
        eiColumn.setDescName("值");
        eiColumn.setNullable(true);
        eiMetadata.addMeta(eiColumn);
        return eiMetadata;

    }
}
