package com.baosight.wilp.mx.web;

import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.ms.common.service.InfluxDBService;
import com.baosight.wilp.ms.common.web.GatherServer;
import com.baosight.wilp.ms.pl.domain.MSPL01;
import com.baosight.wilp.ms.pl.domain.MSPL02;
import com.baosight.wilp.mx.common.Result;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 移动端接口服务
 *
 * @author: panlingfeng
 * @createDate: 2021/9/2 11:38 上午
 */
@RestController
@RequestMapping("mobile")
public class MobileServer {

    @Autowired
    private InfluxDBService influxDBService;

    public static List childrenAll = new ArrayList();

    /**
     * 查询所有参数
     *
     * @return Result
     * @author panlingfeng
     * @date 2021/8/10 6:12 下午
     * @params 1.封装分页参数
     * 2.查询指定范围的数据
     */
    @GetMapping("query/messages")
    @CrossOrigin
    public Result queryMessages(@RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        Integer start = (current - 1) * size;
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("pageSize", size);
        hashMap.put("offset", start);
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        return Result.ok(dao.query("MXAL01.selectAll", hashMap));
    }

    /**
     * 监控页面，静态数据
     *
     * @return Result
     * @author panlingfeng
     * @date 2021/9/7 3:33 下午
     * @params 1.查询设备数
     * 2.查询参数数
     */
    @GetMapping("query/static")
    @CrossOrigin
    public Result queryStatic() {
        Map<String, Integer> map = new HashMap<>();
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        List<Integer> query1 = dao.query("MSDC01.count", null);
        List<Integer> query2 = dao.query("MSPL01.count", null);
        if (CollectionUtils.isNotEmpty(query1)) {
            map.put("device_count", query1.get(0));
        } else {
            map.put("device_count", 0);
        }
        if (CollectionUtils.isNotEmpty(query2)) {
            map.put("point_count", query2.get(0));
        } else {
            map.put("point_count", 0);
        }
        return Result.ok(map);
    }

    /**
     * 监控页面，动态数据
     *
     * @return Result
     * @author panlingfeng
     * @date 2021/9/7 3:54 下午
     * @params 1.查询一级告警记录数
     * 2.查询二级告警记录数
     * 3.查询告警已完成记录数
     */
    @GetMapping("query/dynamic")
    @CrossOrigin
    public Result queryDynamic() {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("grade", "1");
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        List<Integer> query1 = dao.query("MXAL01.count", paramsMap);
        paramsMap.put("grade", "2");
        List<Integer> query2 = dao.query("MXAL01.count", paramsMap);
        List<Integer> query3 = dao.query("MXAL01.count", null);
        Map<String, Integer> resultMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(query1)) {
            resultMap.put("error_count", query1.get(0));
        } else {
            resultMap.put("error_count", 0);
        }
        if (CollectionUtils.isNotEmpty(query2)) {
            resultMap.put("warning_count", query2.get(0));
        } else {
            resultMap.put("warning_count", 0);
        }
        if (CollectionUtils.isNotEmpty(query3)) {
            resultMap.put("alarm_end_count", query3.get(0));
        } else {
            resultMap.put("alarm_end_count", 0);
        }
        return Result.ok(resultMap);
    }

    /**
     * 查询系统分类
     *
     * @return Result
     * @author panlingfeng
     * @date 2021/9/8 11:01 上午
     * @params
     */
    @GetMapping("query/classify")
    @CrossOrigin
    public Result queryClassify() {
        listValue=new ArrayList();
        childrenAll=new ArrayList();
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        //查出所有一级菜单
        List<MSPL02> list2 = dao.query("MSPL02.getOneLevel", null);
//        for (int i = 0; i < list2.size(); i++) {
//            select_Children_All(list2.get(i).getId());
//        }
//        return Result.ok(childrenAll);
        return Result.ok(list2);
    }
    /**
     * 查询系统分类
     *
     * @return Result
     * @author panlingfeng
     * @date 2021/9/8 11:01 上午
     * @params
     */
    @GetMapping("query/classify2")
    @CrossOrigin
    public Result queryClassify2() {
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        List<MSPL02> list = dao.query("MSSP01.getLevel", null);
        return Result.ok(list);
    }


    /**
     *  递归查询树状图
     * @param id 一级菜单的id
     * @return0
     */
    int a=0;
    public void select_Children_All(String id){
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        Map maps = new HashMap();
        maps.put("id", id);
        List<MSPL02> list = dao.query("MSPL02.select_Value", maps);
        List<MSPL02> list2 = dao.query("MSPL02.select_classify_All", new HashMap<>());
        if (CollectionUtils.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                List<MSPL02> childTree = list2.stream().filter(mspl02 -> mspl02.getParentId().equals(id)).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(childTree)) {
                    for (int j = 0; j < childTree.size(); j++) {
                        Map map = list.get(i).toMap();
                        map.put("childrenList",childTree.get(j).toMap());
                        childrenAll.add(map);
                        a=1;
                        select_Children_All(childTree.get(j).getId());
                    }
                }else{
                    if (a==1){

                    }else{
                        childrenAll.add(list.get(i).toMap());
                        a=0;
                    }
                }
            }
        }
    }

    List<Map> listValue=new ArrayList();
    Dao dao = (Dao) PlatApplicationContext.getBean("dao");
    /**
     * 递归查询  系统分类配置的树状图
     *
     * @param
     * @return
     */
    @GetMapping("query/classfyAll")
    @CrossOrigin
    public Result select_Classfy_All(@RequestParam("id") String id) {
        Map maps = new HashMap();
        maps.put("id", id);
        List<MSPL02> list = dao.query("MSPL02.select_Value", maps);
        List<MSPL02> list2 = dao.query("MSPL02.select_classify_All", new HashMap<>());
        if (CollectionUtils.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Map map = new HashMap();
                map.put("id", list.get(i).getId());
                List<MSPL02> childTree = list2.stream().filter(aa -> aa.getParentId().equals(id)).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(childTree)) {
                    for (int j = 0; j < childTree.size(); j++) {
                        Map map1 = new HashMap();
                        map1.put("text", childTree.get(j).getClassifyName());
                        map1.put("value", childTree.get(j).getId());
                        listValue.add(map1);
                        List<MSPL02> list1 = new ArrayList();
                        list1.add(childTree.get(j));
                        select_Classfy_All2(childTree.get(j).getId());
                    }
                }
            }
        }
        for (int i = 0; i < listValue.size(); i++) {
            if (listValue.get(i).size()==2){
//                List l=new ArrayList();
//                Map m=new HashMap();
//                m.put("text", "");
//                m.put("value", "");
//                l.add(m);
//                listValue.get(i).put("children",l);
            }
        }
        return Result.ok(listValue,"调用成功");
    }


    public void select_Classfy_All2(@RequestParam("id") String id) {
        Map maps = new HashMap();
        maps.put("id", id);
        List<MSPL02> list = dao.query("MSPL02.select_Value", maps);
        List<MSPL02> list2 = dao.query("MSPL02.select_classify_All", new HashMap<>());
        List l=new ArrayList();
        if (CollectionUtils.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Map map = new HashMap();
                map.put("id", list.get(i).getId());
                List<MSPL02> childTree = list2.stream().filter(aa -> aa.getParentId().equals(id)).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(childTree)) {
                    for (int j = 0; j < childTree.size(); j++) {
                        for (int k = 0; k < listValue.size(); k++) {
                            if (listValue.get(k).get("value").equals(id)){
                                Map m=new HashMap();
                                m.put("text", childTree.get(j).getClassifyName());
                                m.put("value", childTree.get(j).getId());
                                l.add(m);
                                if (j == childTree.size()-1){
                                    listValue.get(k).put("children",l);
                                }
                            }

                        }

                        List<MSPL02> list1 = new ArrayList();
                        list1.add(childTree.get(j));
                        select_Classfy_All2(childTree.get(j).getId());
                    }
                }
            }
        }
    }


    /**
     * 分页查询参数和参数值
     *
     * @return Result
     * @author panlingfeng
     * @date 2021/9/8 4:38 下午
     * @params 1.封装查询参数
     * 2.纳入实时值
     * 3.封装返回
     */
    @GetMapping("query/point")
    @CrossOrigin
    public Result queryPoint(@RequestParam("current") Integer current, @RequestParam("size") Integer size
            , @RequestParam("active") String active, @RequestParam("id") String id) {
        Integer start = (current - 1) * size;
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageSize", size);
        paramsMap.put("offset", start);
        paramsMap.put("tParamClassifyId", id);
        paramsMap.put("active", active);
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        List<MSPL01> list = dao.query("MSPL01.selectByParamClassifyId", paramsMap);
        if (CollectionUtils.isNotEmpty(GatherServer.currentRtDTOList) && CollectionUtils.isNotEmpty(list)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (MSPL01 mspl01 : list) {
                if (StringUtils.isNotBlank(mspl01.getTag())) {
                    GatherServer.currentRtDTOList.forEach(rtDTO -> {
                        if (StringUtils.isNotBlank(rtDTO.getTnm())) {
                            if (mspl01.getTag().equals(rtDTO.getTnm())) {
                                mspl01.setrValue(String.valueOf(rtDTO.getVal()));
                                Date date = new Date(Long.parseLong(rtDTO.getTss() + rtDTO.getTsm()));
                                mspl01.setrTime(sdf.format(date));
                            }
                        }
                    });
                }

                //获取点位的读数，如果没有读数 不处理
                String rValue = mspl01.getrValue();
                if (StringUtil.isEmpty(rValue)) continue;

                //如果读数不是数字（转Double失败的情况下 也不处理）
                if (!rValue.matches("-?[0-9]+.?[0-9]*")) continue;

                //有读数判断是否为开关量 和 枚举量
                if ("1".equals(mspl01.getType())) {
                    //处理开关量
                    rValue = Double.valueOf(rValue) == 1 ? mspl01.getTrueValueLabel() : mspl01.getFalseValueLabel();
                    if (StringUtil.isNotEmpty(rValue)) mspl01.setrValue(rValue);
                } else if ("2".equals(mspl01.getType())) {
                    //处理枚举量
                    String[] enumArr = new String[]{mspl01.getEnumValue01(), mspl01.getEnumValue02(),
                            mspl01.getEnumValue03(), mspl01.getEnumValue04(), mspl01.getEnumValue05(), mspl01.getEnumValue06()};
                    String[] enumLabelArr = new String[]{mspl01.getEnumValue01Label(), mspl01.getEnumValue02Label(),
                            mspl01.getEnumValue03Label(), mspl01.getEnumValue04Label(), mspl01.getEnumValue05Label(), mspl01.getEnumValue06Label()};
                    for (int i = 0; i < enumArr.length; i++) {
                        String enumValue = enumArr[i];
                        if (Double.valueOf(rValue) == Double.valueOf(enumValue)) {
                            rValue = enumLabelArr[i];
                            break;
                        }
                    }
                    if (StringUtil.isNotEmpty(rValue)) mspl01.setrValue(rValue);
                }
            }
        }
        return Result.ok(list);
    }

    /**
     * 查询24小时内的实时值
     *
     * @return Result
     * @author panlingfeng
     * @date 2021/9/10 10:35 上午
     * @params
     */
    @GetMapping("query/24h")
    @CrossOrigin
    public Result query24h(@RequestParam("tagName") String tagName) {
        return Result.ok(influxDBService.query24h(tagName, GatherServer.PARAM_DATA_MEASUREMENT));
    }
}
