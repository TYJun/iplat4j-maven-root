package com.baosight.wilp.ms.common.web;


import com.alibaba.fastjson.JSON;
import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.common.ed.domain.TEDCM01;
import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.data.ibatis.dao.SqlMapDaoLogProxy;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.ms.common.domain.*;
import com.baosight.wilp.ms.common.domain.sms.MsAlarmTypeEnum;
import com.baosight.wilp.ms.common.service.InfluxDBService;
import com.baosight.wilp.ms.common.service.WebSocketService;
import com.baosight.wilp.ms.pa.domain.MSPA01;
import com.baosight.wilp.ms.pl.domain.MSPL01;
import com.baosight.wilp.mx.common.Result;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.influxdb.dto.QueryResult;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * 采集数据下行接口（点表推送下行接口、数据推动下行接口、获取参数对象、事件推送下行接口、查询所有参数、趋势图统计）
 *
 * @author: panlingfeng
 * @createDate: 2021/8/4 6:19 下午
 */
@RestController
@RequestMapping("sym")
public class GatherServer extends ServiceBase {

    public final static String ON = "1";

    public final static String BOX_CFG_KEY = "devices";
    public final static String RT_DATA_KEY = "rts";
    public final static String PARAM_MEASUREMENT = "param_measurement";
    public final static String PARAM_DATA_MEASUREMENT = "param_data_measurement";
    public static Boolean INIT_TAG_CONFIG_CACHE = false;
    public static Boolean SMS_SEND_FLAG = false;
    public static Map<String, MSPL01> TAG_CONFIG_CACHE = new HashMap<>();
    public static List<RtDTO> currentRtDTOList; //当前
    public final static Map<String, String> MESSAGE_SUSS_MAP = new HashMap<>();

    @Autowired
    private InfluxDBService influxDBService;
    @Autowired
    private WebSocketService webSocketService;

    private static EiInfo ei = new EiInfo();

    static {
        ei.set(EiConstant.serviceId, "S_ED_36");
        ei.set("codesetCode", "znjk.ms.sms.conf");
    }

    public final static Map<String, Boolean> CFG_CONFIG_CACHE = new HashMap<>();

    /**
     * 清理缓存
     *
     * @return
     * @author panlingfeng
     * @date 2021/8/5 5:53 下午
     * @params map 采集数据
     */
    @GetMapping("clean")
    @CrossOrigin
    public synchronized void clean() {
        TAG_CONFIG_CACHE.clear();
        INIT_TAG_CONFIG_CACHE = false;
        currentRtDTOList = null;
        CFG_CONFIG_CACHE.clear();
        WebSocketService.TAG_CONFIG_CACHE.clear();
    }

    /**
     * 点表推送下行接口
     *
     * @return
     * @author panlingfeng
     * @date 2021/8/5 5:53 下午
     * @params map 采集数据
     * 1.获取前缀
     * 2.收集符合要求的设备
     * 3.删除原来的，增加新的
     */
    @PostMapping("boxcfg")
    @CrossOrigin
    public void boxCfg(@RequestBody HashMap<String, List<DeviceDTO>> map) {
        synchronized (this) {
            List<List<SensorDTO>> dbList = new ArrayList<>();
            List<DeviceDTO> devices = map.get(BOX_CFG_KEY);
            if (devices != null && devices.size() > 0) {
                EiInfo out = XServiceManager.call(ei);
                ArrayList<TEDCM01> result = (ArrayList<TEDCM01>) out.getAttr().get("result");
                if (CollectionUtils.isNotEmpty(result) && result.size() >= 3) {
                    String prefixStr = result.get(2).getItemCode();
                    List<String> prefixList = Arrays.asList(prefixStr.split(","));
                    for (DeviceDTO deviceDTO : devices) {
                        if (prefixList.contains(deviceDTO.getName())) {
                            dbList.add(deviceDTO.getSensors());
                        }
                    }
                    if (CollectionUtils.isNotEmpty(dbList)) {
                        dbList.forEach(sensorDTOS -> {
                            List<SensorDTO> collect = sensorDTOS.stream().filter(sensorDTO -> {
                                if (!CFG_CONFIG_CACHE.containsKey(sensorDTO.getName()) || CFG_CONFIG_CACHE.get(sensorDTO.getName())) {
                                    QueryResult queryResult = influxDBService.queryCount(PARAM_MEASUREMENT, sensorDTO.getName());
                                    if (queryResult != null) {
                                        List<QueryResult.Result> results = queryResult.getResults();
                                        if (CollectionUtils.isNotEmpty(results)) {
                                            List<QueryResult.Series> series = results.get(0).getSeries();
                                            if (CollectionUtils.isNotEmpty(series)) {
                                                List<List<Object>> values = series.get(0).getValues();
                                                if (CollectionUtils.isEmpty(values)) {
                                                    return true;
                                                } else {
                                                    CFG_CONFIG_CACHE.put(sensorDTO.getName(), false);
                                                    return false;
                                                }
                                            } else {
                                                return true;
                                            }
                                        } else {
                                            return true;
                                        }
                                    } else {
                                        return true;
                                    }
                                } else {
                                    return false;
                                }
                            }).collect(Collectors.toList());
                            if (CollectionUtils.isNotEmpty(collect)) {
                                influxDBService.insertBatchParams(collect, PARAM_MEASUREMENT);
                                collect.forEach(sensorDTO -> {
                                    CFG_CONFIG_CACHE.put(sensorDTO.getName(), false);
                                });
                            }
                        });
                    }
                }
            }
        }
    }

    /**
     * 点表推送下行接口 简单数据数据模型
     *
     * @return
     * @author panlingfeng
     * @date 2021/8/5 5:54 下午
     * @params map 采集数据
     */
    @PostMapping("rtdata_simple")
    @CrossOrigin
    public synchronized void rtDataSimple(@RequestBody HashMap<String, List<RtDTOSimple>> map) {
           List<RtDTOSimple> rts = map.get(RT_DATA_KEY);
           //vt 3 为String 即只取数值不是字符串类型 并且 值不等于0的， 且只取今年的数据
           List<RtDTO> collect = rts.stream()
                   .filter(rtDTOSimple -> {
                       if (rtDTOSimple.getVt() != 3 && rtDTOSimple.getVq() == 0) {
                           LocalDate now = LocalDate.now();
                           long time = Long.parseLong(rtDTOSimple.getTss()) * 1000L + Long.parseLong(rtDTOSimple.getTsm());
                           Date date = new Date(time);
                           LocalDate localDate = date.toInstant()
                                   .atZone(ZoneId.systemDefault())
                                   .toLocalDate();
                           if (localDate.getYear() == now.getYear()) {
                               return true;
                           } else {
                               return false;
                           }
                       } else {
                           return false;
                       }
                   })
                   .map(rtDTOSimple -> {
                       RtDTO rtDTO = new RtDTO();
                       rtDTO.setBsn(rtDTOSimple.getBsn());
                       rtDTO.setBnm(rtDTOSimple.getBnm());
                       rtDTO.setTnm(rtDTOSimple.getTnm());
                       rtDTO.setTss(rtDTOSimple.getTss());
                       rtDTO.setTsm(rtDTOSimple.getTsm());
                       rtDTO.setVt(rtDTOSimple.getVt());
                       Object val = rtDTOSimple.getVal();
                       if (val instanceof String) {
                           String valStr = (String) val;
                           if (isNumber(valStr))
                               rtDTO.setVal(Double.valueOf(valStr));
                       } else if (val instanceof Double) {
                           rtDTO.setVal((Double) rtDTOSimple.getVal());
                       } else if (val instanceof Integer) {
                           rtDTO.setVal(((Integer) rtDTOSimple.getVal()).doubleValue());
                       }
                       return rtDTO;
                   }).collect(Collectors.toList());

           handleDataList(collect);
    }

    /**
     * 判断字符串是否是数字类型，该正则表达式可以匹配所有的数字 包括负数
     *
     * @return
     * @author panlingfeng
     * @date 2021/8/5 5:54 下午
     * @params str 判断目标
     */
    public boolean isNumber(String str) {
        if (StringUtils.isNotEmpty(str)) {
            String reg = "^-?[0-9]+(\\.[0-9]+)?$";
            return str.matches(reg);
        }
        return false;
    }

    /**
     * 数据推动下行接口
     *
     * @return
     * @author panlingfeng
     * @date 2021/8/5 5:54 下午
     * @params map 采集数据容器
     */
    @PostMapping("rtdata")
    @CrossOrigin
    public synchronized void rtData(@RequestBody HashMap<String, List<RtDTO>> map) {
        List<RtDTO> rts = map.get(RT_DATA_KEY);
        handleDataList(rts);  //调用数据处理方法
    }

    /**
     * 数据处理方法
     *
     * @author panlingfeng
     * @date 2021/8/5 5:54 下午
     * @params rts 采集数据容器
     * 1.获取采集到的数据集合
     * 2.执行采集数据推送和日志操作
     * 3.插入数据到时序数据库中
     */
    private void handleDataList(List<RtDTO> rts) {
            //获取短信发送配置
            getMessageSendFlag();

            EiInfo out = XServiceManager.call(ei);
            ArrayList<TEDCM01> result = (ArrayList<TEDCM01>) out.getAttr().get("result");
            if (CollectionUtils.isNotEmpty(result) && result.size() >= 3) {
                //取出小字典重配置的前缀，按照前缀（bnm网关设备名称）过滤出当前医院当前模块需要的点位数据
                String prefixStr = result.get(2).getItemCode();
                List<String> prefixList = Arrays.asList(prefixStr.split(","));
                List<RtDTO> collect = rts.stream().filter(rtDTO -> prefixList.contains(rtDTO.getBnm())).collect(Collectors.toList());

                if (CollectionUtils.isNotEmpty(collect)) {
                    //将点位最新的值 保存到 静态list currentRtDTOList 中
                    handleCurrentRtDTOCache(collect);
                    //处理消息推送以及短信
                    webSocketService.executeAsync(collect);

                    //根据isWriteLog字段 将配置为1的数据 批量写入到influxDb数据库
                    List<RtDTO> list = new ArrayList<>();
                    for (RtDTO rtDTO : collect) {
                        MSPL01 mspl01 = getMspl01(rtDTO, false);
                        String isWriteLog = mspl01.getIsWriteLog();
                        if (StringUtils.isNotBlank(isWriteLog) && ON.equals(isWriteLog)) {
                            list.add(rtDTO);
                        }
                    }
                    if (CollectionUtils.isNotEmpty(list)) {
                        influxDBService.insertBatchDatasSimple(list, PARAM_DATA_MEASUREMENT);
                    }
                }
            }
    }

    /**
     * 处理参数当前最新的值
     *
     * @return
     * @author panlingfeng
     * @date 2021/11/23 4:01 下午
     * @params collect 采集的数据
     */
    private void handleCurrentRtDTOCache(List<RtDTO> collect) {
        if (CollectionUtils.isEmpty(currentRtDTOList)) {
            currentRtDTOList = collect;
        } else {
            collect.forEach(rtDTO -> {
                AtomicBoolean flag = new AtomicBoolean(true);
                currentRtDTOList.forEach(currentRtDTO -> {
                    if (currentRtDTO.getTnm().equals(rtDTO.getTnm())) {
                        try {
                            BeanUtils.copyProperties(currentRtDTO, rtDTO);
                            flag.set(false);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
                if (flag.get()) {
                    currentRtDTOList.add(rtDTO);
                }
            });
        }
    }

    /**
     * 获取参数对象
     *
     * @return flag true 开启中断，当缓存中没有，并且数据库中没有时返回null
     * @author panlingfeng
     * @date 2021/9/3 2:17 下午
     * @params rtDTO 实时数据对象
     * 1.判读缓存中是否有标签
     * 2.有则取出
     * 3.没有则查询出来
     */
    public static MSPL01 getMspl01(RtDTO rtDTO, boolean flag) {
        //初始化缓存
        initTagCache();

        MSPL01 mspl01;
        if (TAG_CONFIG_CACHE.containsKey(rtDTO.getTnm())) {
            mspl01 = TAG_CONFIG_CACHE.get(rtDTO.getTnm());
        } else {
            mspl01 = new MSPL01();
            mspl01.setTag(rtDTO.getTnm());
            Dao dao = (Dao) PlatApplicationContext.getBean("dao");
            List<MSPL01> mspl01s = dao.query("MSPL01.selectByTag", mspl01);
            if (CollectionUtils.isNotEmpty(mspl01s)) {
                mspl01 = mspl01s.get(0);
            } else {
                if (flag) {
                    return null;
                }
            }

            TAG_CONFIG_CACHE.put(rtDTO.getTnm(), mspl01);
        }
        return mspl01;
    }

    public static void initTagCache() {
        //判断有没有初始化过数据 ， 如果有 不处理， 如果没有 将所有的tag点数据写入倒缓存中
        if (INIT_TAG_CONFIG_CACHE) {
            return;
        }
        SqlMapDaoLogProxy dao = (SqlMapDaoLogProxy) PlatApplicationContext.getBean("dao");
        dao.setMaxQueryCount(10_000);

        // 查询已报警点位
        List<Map<String, String>> alarmList = dao.query("MSPL01.queryAlarmIsNull", new HashMap<>());
        for (Map<String, String> map : alarmList) {
            MESSAGE_SUSS_MAP.put(map.get("tag"),map.get("tag"));
        }

        List<MSPL01> mspl01s = dao.query("MSPL01.selectAllTag", new HashMap<>());
        mspl01s.forEach(item -> {
            TAG_CONFIG_CACHE.put(item.getTag(), item);
        });
        INIT_TAG_CONFIG_CACHE = true;
    }

    public static void getMessageSendFlag() {
        SqlMapDaoLogProxy dao = (SqlMapDaoLogProxy) PlatApplicationContext.getBean("dao");
        List<Map<String, String>> query = dao.query("MSPL01.selectMessageConfig", new HashMap<>());
        boolean sendFlag = false;
        if (query != null && query.size() > 0 && "Y".equals(query.get(0).get("value"))) {
            sendFlag = true;
        }
        SMS_SEND_FLAG = sendFlag;
    }

    /**
     * 事件推送下行接口
     *
     * @return
     * @author panlingfeng
     * @date 2021/8/5 5:54 下午
     * @params
     */
    @PostMapping("event")
    @CrossOrigin
    public Object event(@RequestBody HashMap<String, List<EventDTO>> map) {
        return null;
    }

    /**
     * 查询所有参数
     *
     * @return
     * @author panlingfeng
     * @date 2021/8/10 6:12 下午
     * @params
     */
    @PostMapping("query/params")
    @CrossOrigin
    public Object queryParams() {
        return influxDBService.queryMeasurement(PARAM_MEASUREMENT);
    }

    /**
     * 趋势图统计
     *
     * @return Result
     * 1.接收的时间进行转换
     * 2.计算时间差值
     * 3.封装查询参数
     * 4.执行查询并返回结果
     * @author panlingfeng
     * @date 2021/9/14 8:04 下午
     * @params tagName 参数名称
     * @params start 开始时间
     * @params end 结束数据
     */
    @GetMapping("query/stats")
    @CrossOrigin
    public Result queryStats(@RequestParam("tagName") String tagName, @RequestParam("start") String start, @RequestParam("end") String end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startDate = Date.from(LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).minusHours(8).atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).minusHours(8).atZone(ZoneId.systemDefault()).toInstant());

        Interval interval = new Interval(startDate.getTime(), endDate.getTime());
        Period p = interval.toPeriod();

        QueryResult queryResult = null;
        HashMap<String, String> params = new HashMap<>();
        String startStr = (sdf.format(startDate)).replace(" ", "T") + ":00Z";
        String endStr = (sdf.format(endDate.getTime())).replace(" ", "T") + ":00Z", particle = null;
        params.put("measurement", GatherServer.PARAM_DATA_MEASUREMENT);
        params.put("tagName", tagName);
        params.put("start", startStr);

        if (p.getYears() > 0 || p.getMonths() >= 3 || p.getMonths() < 0) {
            //一律按开始时间向前推进 3个月 颗粒天
            particle = "1d";
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.MONTH, 3);
            endStr = (sdf.format(calendar.getTime())).replace(" ", "T") + ":00Z";
        } else if (p.getMonths() >= 0) {
            if (p.getMonths() == 0 && p.getDays() >= 1 && p.getDays() <= 8) {
                //颗粒1h
                particle = "1h";
            } else if (p.getDays() > 8 || p.getMonths() > 0) {
                //一律按开始时间向前推进8天计算
                particle = "1h";
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);
                calendar.add(Calendar.DAY_OF_MONTH, 8);
                endStr = (sdf.format(calendar.getTime())).replace(" ", "T") + ":00Z";
            } else {
                if (p.getHours() > 3 && p.getHours() < 24) {
                    //颗粒分
                    particle = "5m";
                } else {
                    particle = "1s";
                    if (p.getMinutes() < 1) {
                        //一律按开始时间向前推进1分钟计算
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(startDate);
                        calendar.add(Calendar.MINUTE, 1);
                        endStr = (sdf.format(calendar.getTime())).replace(" ", "T") + ":00Z";
                    }
                }
            }
        }
        params.put("end", endStr);
        params.put("particle", particle);
        queryResult = influxDBService.queryDynamic(params);
        return Result.ok(queryResult, particle);
    }

    /**
     * @throws
     * @title 光缆测量实施温度状态显示
     * @description
     * @author Wzy
     * @updateTime 2022/1/1 15:00
     */
    @PostMapping("query/temperature")
    @CrossOrigin
    public Result queryTemperature() {
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        /**
         * 查询出所有模拟量的点位参数
         */
        List<Map> list1 = dao.query("MSPL03.selectByParam3", new HashMap<>());
        List lists = new ArrayList();
        /**
         * tag为通道及位置
         * 模拟量的实时数据为实时温度
         * name为安装地址
         * 是否报警为是否正常
         */
        //判断是否有模拟量
        if (CollectionUtils.isNotEmpty(list1)) {
            for (int i = 0; i < list1.size(); i++) {
                Map m = new HashMap();
                m.put("tag", list1.get(i).get("tag"));
                //去报警的页面查数据
                List<Map> list = dao.query("MSPL03.selectByParam4", m);
                if (CollectionUtils.isNotEmpty(list)) {
                    m.put("tag", list1.get(i).get("description_"));
                    m.put("name", list1.get(i).get("name_"));
                    if (StringUtils.isNotEmpty(list.get(0).get("value_").toString())) {
                        m.put("value", list.get(0).get("value_"));
                    } else {
                        m.put("value", 0);
                    }
                    if (list.get(0).get("grade") == null) {
                        m.put("isAlarm", "正常");
                    } else {
                        if (list.get(0).get("grade").equals("0")) {
                            m.put("isAlarm", "不正常");
                        } else if (list.get(0).get("grade").equals("1")) {
                            m.put("isAlarm", "不正常");
                        } else if (list.get(0).get("grade").equals("2")) {
                            m.put("isAlarm", "不正常");
                        } else if (list.get(0).get("grade").equals("3")) {
                            m.put("isAlarm", "不正常");
                        } else {
                            m.put("isAlarm", "正常");
                        }
                    }
                } else {
                    //拼接influxdb  sql语句
                    String command = String.format("SELECT val FROM %s WHERE tag_name = '%s'  order by time desc LIMIT 1", GatherServer.PARAM_DATA_MEASUREMENT, m.get("tag"));
                    //调用sql查出数据
                    QueryResult queryResult = influxDBService.query(command);
                    List<QueryResult.Series> series1 = queryResult.getResults().get(0).getSeries();
                    if (series1 == null) {
                        m.put("value", 0);
                    } else {
                        Double v = (Double) series1.get(0).getValues().get(0).get(1);
                        m.put("value", insertDouble(v));
                    }
                    m.put("tag", list1.get(i).get("description_"));
                    m.put("name", list1.get(i).get("name_"));
                    m.put("isAlarm", "正常");
                }
                lists.add(m);
            }
        } else {
            Map m = new HashMap();
            m.put("tag", "");
            m.put("name", "");
            m.put("value", 0);
            m.put("isAlarm", "正常");
            lists.add(m);
        }
        return Result.ok(lists);
    }

    /**
     * @author keke
     */
    @PostMapping("query/queryScreenTag")
    @CrossOrigin
    public Map<String, Map<String, Object>> queryScreenTag(String areaName) {
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");

        // 查询所有点位的实时数据
        Map<String, Double> tagValMap = new HashMap<>();
        if (currentRtDTOList != null) {
            tagValMap =
                    currentRtDTOList.stream().collect(Collectors.toMap(RtDTO::getTnm, RtDTO::getVal, (k1, k2) -> k2));
        }

        // 查询出需要再大屏上展示的点位
        List<Map<String, String>> tagList = dao.query("MSPL03.queryScreenTag", areaName);

        // 查询正在报警的点位信息
        List<Map<String, String>> alarmPoints = dao.query("MSPL03.queryAlarmTag", null);
        Map<String, Map<String, String>> alarmMap =
                alarmPoints.stream().collect(Collectors.toMap(k -> k.get("tag"), k -> k, (k1, k2) -> k2));

        Map<String, Map<String, Object>> returnMap = new LinkedHashMap<>();
        for (Map<String, String> tagObj : tagList) {
            if (!returnMap.containsKey(tagObj.get("group"))) {
                returnMap.put(tagObj.get("group"), new LinkedHashMap<>());
            }

            String name = tagObj.get("name");
            if ((!name.endsWith("电压") && !name.endsWith("电流") && !name.endsWith("温度") && !name.equals("状态")) || name.endsWith("平均电流")) {
                continue;
            }

            Map<String, Object> tagValObj = new HashMap<>();
            tagValObj.put("tag", tagObj.get("tag"));
            tagValObj.put("name", tagObj.get("name"));
            tagValObj.put("pointType", tagObj.get("tag"));
            tagValObj.put("value", insertDouble(tagValMap.get(tagObj.get("tag"))));
            if (alarmMap.containsKey(tagObj.get("tag"))) {
                tagValObj.put("alarmGrade", alarmMap.get(tagObj.get("tag")).get("grade"));
                tagValObj.put("alarmText", alarmMap.get(tagObj.get("tag")).get("description"));
                tagValObj.put("alarmTime", alarmMap.get(tagObj.get("tag")).get("time"));
            } else {
                tagValObj.put("alarmGrade", "0");
                tagValObj.put("alarmText", "");
                tagValObj.put("alarmTime", "");
            }
            returnMap.get(tagObj.get("group")).put(tagObj.get("tag"), tagValObj);
        }

        return returnMap;
    }

    /**
     * @throws
     * @title 光缆测量近七天平均温度曲线图
     * @description
     * @author Wzy
     * @updateTime 2022/1/1 15:01
     */
    @PostMapping("query/temperature_sevenDay")
    @CrossOrigin
    public Result queryTemperatureSevenDay() {
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        /**
         * 查找出所有的点位参数的温度
         * 1.模拟量的才有温度
         * 2.然后计算出前7天的平均温度
         * 3.拿当天温度的总数/当天的点位数量=当天的平均温度
         */
        List<Map> list1 = dao.query("MSPL03.selectByParam3", new HashMap<>());
        List list = returnDay();
        List list2 = selectInflux(list1, list);
        return Result.ok(list2);
    }

    /**
     * @throws
     * @title 告警信息分布
     * @description
     * @author Wzy
     * @updateTime 2022/1/1 15:09
     */
    @PostMapping("query/alarm")
    @CrossOrigin
    public Result queryAlarm() {
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        List<Map> list = new ArrayList();
        /**
         * 查找所有系统
         */
        List<Map> list1 = dao.query("MSPL03.selectParamClassify", new HashMap<>());
        int counts = 0;
        int x1 = 0;
        int x2 = 0;
        int x3 = 0;
        int x4 = 0;
        if (CollectionUtils.isNotEmpty(list1)) {
            for (int i = 0; i < list1.size(); i++) {
                Map map = new HashMap();
                map.put("id", list1.get(i).get("id"));
                /**
                 * 查找系统下面所对应的点位
                 */
                List<Map> query = dao.query("MSPL03.selectParams", map);
                int count = 0;
                if (query.get(0).get("count") != null) {
                    count = Integer.parseInt(query.get(0).get("count").toString());
                }
                //如果等于1的话代表里面没数据 不需要查 如果大于1  有数据 需要查
                if (count > 0) {
                    map = new HashMap();
                    if (StringUtils.isNotEmpty(list1.get(i).get("classify_name").toString())) {
                        map.put("name", list1.get(i).get("classify_name"));   //系统名称
                    } else {
                        map.put("name", "");   //系统名称
                    }
                    map.put("value", count);                             //监测点位
                    /**
                     * 查询一级告警、二级告警、正常、通讯故障
                     */
                    Map map1 = new HashMap();
                    if (StringUtils.isNotEmpty(list1.get(i).get("id").toString())) {
                        map1.put("id", list1.get(i).get("id"));//系统id
                    } else {
                        map1.put("id", 0);   //系统名称
                    }
                    List<Map> list3 = dao.query("MSPL03.selectByTag", map1);
                    int v1 = 0;
                    int v2 = 0;
                    int v3 = 0;
                    int v5 = 0;
                    if (CollectionUtils.isNotEmpty(list3)) {
                        for (int j = 0; j < list3.size(); j++) {
                            if (list3.get(j).get("grade") == null) {
                                v5++;
                            } else {
                                if (list3.get(j).get("grade").equals("0")) {
                                    v1++;
                                } else if (list3.get(j).get("grade").equals("1")) {
                                    v2++;
                                } else if (list3.get(j).get("grade").equals("2")) {
                                    v3++;
                                } else if (list3.get(j).get("grade").equals("3")) {

                                } else {
                                    v5++;
                                }
                            }
                        }
                    }
                    if (v2 == 0 && v3 == 0 && v5 == 0 && v1 == 0) {
                        v5 = count;
                    }
                    int v = v2 + v3 + v1;
                    if (v != count) {
                        if (v > count) {
                            v5 = 0;
                        } else {
                            v5 = count - v;
                        }
                    }
                    map.put("oneAlarm", v2);                                     //一级告警
                    map.put("twoAlarm", v3);                                    //二级告警
                    map.put("goodAlarm", v5);                                 //正常
                    map.put("badAlarm", v1);                                 //通讯故障
                    list.add(map);
                    counts += count;
                    x1 += v2;
                    x2 += v3;
                    x3 += v5;
                    x4 += v1;
                } else {
                    map = new HashMap();
                    map.put("name", list1.get(i).get("classify_name"));   //系统名称
                    map.put("value", count);                             //监测点位
                    map.put("oneAlarm", 0);                              //一级告警
                    map.put("twoAlarm", 0);                              //二级告警
                    map.put("goodAlarm", count);                                 //正常
                    map.put("badAlarm", 0);                                 //通讯故障
                    list.add(map);
                }

            }
        }
        Map map = new HashMap();
        /**
         * 总共的
         */
        map.put("name", "总共");   //系统名称
        map.put("value", counts);                             //监测点位
        map.put("oneAlarm", x1);                              //一级告警
        map.put("twoAlarm", x2);                              //二级告警
        map.put("goodAlarm", x3);                                 //正常
        map.put("badAlarm", x4);                                 //通讯故障
        list.add(map);
        return Result.ok(list);
    }


    /**
     * @throws
     * @title 算出7天的数据
     * @description
     * @author Wzy
     * @updateTime 2022/1/2 15:12
     */
    public List returnDay() {
        Date date = new Date();
        SimpleDateFormat se = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat si = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        String format = se.format(date);
        String s = " 23:59:59.9";
        format = format + s;
        Date date1 = null;
        try {
            date1 = si.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(date1);
        calendar3.add(Calendar.DATE, -1);       //昨日
        String before2 = si.format(calendar3.getTime());
        before2 = before2.replace(" ", "T") + "Z";      //influxdb数据库时间的格式

        Calendar calendar4 = Calendar.getInstance();
        calendar4.setTime(date1);
        calendar4.add(Calendar.DATE, -2);       //前日
        String before3 = si.format(calendar4.getTime());
        before3 = before3.replace(" ", "T") + "Z";      //influxdb数据库时间的格式

        calendar3.setTime(date1);
        calendar3.add(Calendar.DATE, -3);       //大前日
        String before4 = si.format(calendar3.getTime());
        before4 = before4.replace(" ", "T") + "Z";      //influxdb数据库时间的格式

        calendar3.setTime(date1);
        calendar3.add(Calendar.DATE, -4);       //大大前日
        String before5 = si.format(calendar3.getTime());
        before5 = before5.replace(" ", "T") + "Z";      //influxdb数据库时间的格式

        calendar3.setTime(date1);
        calendar3.add(Calendar.DATE, -5);       //大大大前日
        String before6 = si.format(calendar3.getTime());
        before6 = before6.replace(" ", "T") + "Z";      //influxdb数据库时间的格式

        calendar3.setTime(date1);
        calendar3.add(Calendar.DATE, -6);       //大大大大前日
        String before7 = si.format(calendar3.getTime());
        before7 = before7.replace(" ", "T") + "Z";      //influxdb数据库时间的格式

        calendar3.setTime(date1);
        calendar3.add(Calendar.DATE, -7);       //大大大大大前日
        String before8 = si.format(calendar3.getTime());
        before8 = before8.replace(" ", "T") + "Z";      //influxdb数据库时间的格式

        calendar3.setTime(date1);
        calendar3.add(Calendar.DATE, -8);       //大大大大大前日
        String before9 = si.format(calendar3.getTime());
        before9 = before9.replace(" ", "T") + "Z";      //influxdb数据库时间的格式

        List list = new ArrayList();
        list.add(before2);
        list.add(before3);
        list.add(before4);
        list.add(before5);
        list.add(before6);
        list.add(before7);
        list.add(before8);
        list.add(before9);
        return list;
    }

    /**
     * 进入influxdb数据库查数据
     *
     * @param list1
     * @param list2
     * @return
     */
    public List selectInflux(List<Map> list1, List list2) {
        QueryResult queryResult = null;
        String command = "";
        List list = new ArrayList();
        Double v1 = 0.0;
        Double v2 = 0.0;
        Double v3 = 0.0;
        Double v4 = 0.0;
        Double v5 = 0.0;
        Double v6 = 0.0;
        Double v7 = 0.0;

        String vs1 = "";
        String vs2 = "";
        String vs3 = "";
        String vs4 = "";
        String vs5 = "";
        String vs6 = "";
        String vs7 = "";
        for (int i = 0; i < list1.size(); i++) {
            //拼接influxdb  sql语句
            command = String.format("SELECT val FROM %s WHERE tag_name = '%s' and  time >= '%s' and time <= '%s' order by time desc LIMIT 1", GatherServer.PARAM_DATA_MEASUREMENT, list1.get(i).get("tag"), list2.get(1), list2.get(0));
            //调用sql查出数据
            queryResult = influxDBService.query(command);
            List<QueryResult.Series> series1 = queryResult.getResults().get(0).getSeries();

            if (series1 == null) {

            } else {
                Double v = 0.0;
                if (series1.get(0).getValues().get(0).get(1) != null) {
                    v = (Double) series1.get(0).getValues().get(0).get(1);
                }

                v1 += v;
                vs1 = series1.get(0).getValues().get(0).get(0).toString();
            }
            //拼接influxdb  sql语句
            command = String.format("SELECT val FROM %s WHERE tag_name = '%s' and  time >= '%s' and time <= '%s' order by time desc LIMIT 1", GatherServer.PARAM_DATA_MEASUREMENT, list1.get(i).get("tag"), list2.get(2), list2.get(1));
            //调用sql查出数据
            queryResult = influxDBService.query(command);
            List<QueryResult.Series> series2 = queryResult.getResults().get(0).getSeries();

            if (series2 == null) {

            } else {
                Double v = 0.0;
                if (series2.get(0).getValues().get(0).get(1) != null) {
                    v = (Double) series2.get(0).getValues().get(0).get(1);
                }
                v2 += v;
                vs2 = series2.get(0).getValues().get(0).get(0).toString();
            }
            //拼接influxdb  sql语句
            command = String.format("SELECT val FROM %s WHERE tag_name = '%s' and  time >= '%s' and time <= '%s' order by time desc LIMIT 1", GatherServer.PARAM_DATA_MEASUREMENT, list1.get(i).get("tag"), list2.get(3), list2.get(2));
            //调用sql查出数据
            queryResult = influxDBService.query(command);
            List<QueryResult.Series> series3 = queryResult.getResults().get(0).getSeries();

            if (series3 == null) {

            } else {
                Double v = 0.0;
                if (series3.get(0).getValues().get(0).get(1) != null) {
                    v = (Double) series3.get(0).getValues().get(0).get(1);
                }
                v3 += v;
                vs3 = series3.get(0).getValues().get(0).get(0).toString();
            }
            //拼接influxdb  sql语句
            command = String.format("SELECT val FROM %s WHERE tag_name = '%s' and  time >= '%s' and time <= '%s' order by time desc LIMIT 1", GatherServer.PARAM_DATA_MEASUREMENT, list1.get(i).get("tag"), list2.get(4), list2.get(3));
            //调用sql查出数据
            queryResult = influxDBService.query(command);
            List<QueryResult.Series> series4 = queryResult.getResults().get(0).getSeries();

            if (series4 == null) {

            } else {
                Double v = 0.0;
                if (series4.get(0).getValues().get(0).get(1) != null) {
                    v = (Double) series4.get(0).getValues().get(0).get(1);
                }
                v4 += v;
                vs4 = series4.get(0).getValues().get(0).get(0).toString();
            }
            //拼接influxdb  sql语句
            command = String.format("SELECT val FROM %s WHERE tag_name = '%s' and  time >= '%s' and time <= '%s' order by time desc LIMIT 1", GatherServer.PARAM_DATA_MEASUREMENT, list1.get(i).get("tag"), list2.get(5), list2.get(4));
            //调用sql查出数据
            queryResult = influxDBService.query(command);
            List<QueryResult.Series> series5 = queryResult.getResults().get(0).getSeries();

            if (series5 == null) {

            } else {
                Double v = 0.0;
                if (series5.get(0).getValues().get(0).get(1) != null) {
                    v = (Double) series5.get(0).getValues().get(0).get(1);
                }
                v5 += v;
                vs5 = series5.get(0).getValues().get(0).get(0).toString();
            }

            //拼接influxdb  sql语句
            command = String.format("SELECT val FROM %s WHERE tag_name = '%s' and  time >= '%s' and time <= '%s' order by time desc LIMIT 1", GatherServer.PARAM_DATA_MEASUREMENT, list1.get(i).get("tag"), list2.get(6), list2.get(5));
            //调用sql查出数据
            queryResult = influxDBService.query(command);
            List<QueryResult.Series> series6 = queryResult.getResults().get(0).getSeries();

            if (series6 == null) {

            } else {
                Double v = 0.0;
                if (series6.get(0).getValues().get(0).get(1) != null) {
                    v = (Double) series6.get(0).getValues().get(0).get(1);
                }
                v6 += v;
                vs6 = series6.get(0).getValues().get(0).get(0).toString();
            }

            //拼接influxdb  sql语句
            command = String.format("SELECT val FROM %s WHERE tag_name = '%s' and  time >= '%s' and time <= '%s' order by time desc LIMIT 1", GatherServer.PARAM_DATA_MEASUREMENT, list1.get(i).get("tag"), list2.get(7), list2.get(6));
            //调用sql查出数据
            queryResult = influxDBService.query(command);
            List<QueryResult.Series> series7 = queryResult.getResults().get(0).getSeries();

            if (series7 == null) {

            } else {
                Double v = 0.0;
                if (series7.get(0).getValues().get(0).get(1) != null) {
                    v = (Double) series7.get(0).getValues().get(0).get(1);
                }
                v7 += v;
                vs7 = series7.get(0).getValues().get(0).get(0).toString();
            }
        }
        Double size = Double.valueOf(list1.size());
        if (size == 0.0) {
            list.add(0);
            list.add(selectName(vs1));
            list.add(0);
            list.add(selectName(vs2));
            list.add(0);
            list.add(selectName(vs3));
            list.add(0);
            list.add(selectName(vs4));
            list.add(0);
            list.add(selectName(vs5));
            list.add(0);
            list.add(selectName(vs6));
            list.add(0);
            list.add(selectName(vs7));
        } else {
            list.add(insertDouble(v1 / size));
            if (vs1.equals("")) {
                list.add("00-00");
            } else {
                list.add(selectName(vs1));
            }
            list.add(insertDouble(v2 / size));
            if (vs2.equals("")) {
                list.add("00-00");
            } else {
                list.add(selectName(vs2));
            }
            list.add(insertDouble(v3 / size));
            if (vs3.equals("")) {
                list.add("00-00");
            } else {
                list.add(selectName(vs3));
            }
            list.add(insertDouble(v4 / size));
            if (vs4.equals("")) {
                list.add("00-00");
            } else {
                list.add(selectName(vs4));
            }
            list.add(insertDouble(v5 / size));
            if (vs5.equals("")) {
                list.add("00-00");
            } else {
                list.add(selectName(vs5));
            }
            list.add(insertDouble(v6 / size));
            if (vs6.equals("")) {
                list.add("00-00");
            } else {
                list.add(selectName(vs6));
            }
            list.add(insertDouble(v7 / size));
            if (vs7.equals("")) {
                list.add("00-00");
            } else {
                list.add(selectName(vs7));
            }
        }
        return list;
    }


    public String selectName(String name) {
        String[] ts = name.split("T");
        String[] ts2 = ts[0].split("-");
        return ts2[1] + "-" + ts2[2];
    }

    /**
     * 小数点保留两位
     *
     */
    public String insertDouble(Double value) {
        DecimalFormat df = new DecimalFormat("######0.00");
        if (value == null) {
            value = 0d;
        }
        return df.format(value);
    }
}
