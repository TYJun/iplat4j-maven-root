package com.baosight.wilp.ms.common.service;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.common.ed.domain.TEDCM01;
import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.ms.common.domain.TagTypeEnum;
import com.baosight.wilp.ms.common.web.GatherServer;
import com.baosight.wilp.mx.al.domain.MXAL01;
import com.baosight.wilp.mx.ps.domain.Tag;
import com.baosight.wilp.mx.web.WebSocket;
import com.baosight.wilp.ms.common.domain.RtDTO;
import com.baosight.wilp.ms.common.util.UUIDUtils;
import com.baosight.wilp.ms.pa.domain.MSPA01;
import com.baosight.wilp.ms.pl.domain.MSPL01;
import groovy.inspect.TextTreeNodeMaker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * 告警处理服务类（执行数据推送客户端、处理通讯量、处理枚举量、处理开关量、取消指定的定时器、打开定时器处理短信发送、发送短信、告警恢复、更新最新告警时间、插入日志信息）
 *
 * @author: panlingfeng
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @createDate: 2021/8/13 3:12 下午
 */
@Service
public class WebSocketService {

    @Resource
    private MsMessageService msMessageService;

    public static final Map<String, ScheduledFuture> TIMER_MAP = new HashMap<>(); //定时器容器

    public static final ScheduledThreadPoolExecutor POOL = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(6);

    public static final Map<String, String> SNAPSHOT_MAP = new HashMap<>(); //快照，记录上一次参数的告警类型

    public final static Map<String, Tag> TAG_CONFIG_CACHE = new HashMap<>();

    /**
     * 执行数据推送客户端
     *
     * @return
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/16 3:33 下午
     * @params rts 采集到的原始数据
     * 1.判断客户端是否有需要推送的参数项
     * 2.遍历参数项适配采集到的数据，将适配到的数据与参数纳入推送的结果集合中
     * 3.判断当前采集到的值是否在报警等级范围中，以此划分一级报警和二级报警
     * 4.将收集到的记过推送到客户端中
     * 5.记录到告警历史记录中
     */
    //@Async("taskExecutor")
    public void executeAsync(List<RtDTO> rts) {
        try {
            //HashMap<String, RtDTO> rValueHashMap = handleRvalue(rts);
            HashMap<String, RtDTO> rValueHashMap = msMessageService.handleRvalue(rts);
            Map<String, WebSocket> clients = WebSocket.clients;
            clients.forEach((s, webSocket) -> {
                HashMap<String, RtDTO> hashMap = new HashMap<>();
                List<Tag> tags = webSocket.getTags();
                if (tags != null && tags.size() > 0) {
                    tags.forEach(tag -> {
                        if (rValueHashMap.containsKey(tag.getName())) {
                            hashMap.put(tag.getName(), rValueHashMap.get(tag.getName()));
                        }
                    });
                    try {
                        webSocket.sendMessage(hashMap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理数据告警逻辑
     *
     * @return rValueHashMap 返回参数和参数的采集映射对象容器
     * @author panlingfeng
     * @date 2021/11/5 1:55 下午
     * @params rts 采集到的原始数据
     */
    private HashMap<String, RtDTO> handleRvalue(List<RtDTO> rts) {
        HashMap<String, RtDTO> rValueHashMap = new HashMap<>();
        rts.forEach(rtDTO -> {
            RtDTO rtDTOClone = rtDTO.clone();
            MSPL01 mspl011 = GatherServer.getMspl01(rtDTO, true);
            if (mspl011 != null) {
                Tag tag = getTag(rtDTO);
                if (tag != null) {
                    if (TagTypeEnum.KG.getValue().equals(mspl011.getType())) { //开关量
                        handleKG(tag, rtDTO, rtDTOClone, mspl011);
                    } else if (TagTypeEnum.MJ.getValue().equals(mspl011.getType())) { //枚举量
                        handleMJ(rtDTOClone, mspl011);
                    } else { //通讯量
                        handleTX(tag, rtDTO, rtDTOClone, mspl011);
                    }
                    rValueHashMap.put(tag.getName(), rtDTOClone);
                }
            }
        });
        return rValueHashMap;
    }

    /**
     * 查询Tag对象
     *
     * @return tag 返回Tag对象
     * 从缓存中获取，没有获取到就查询
     * @author panlingfeng
     * @date 2021/11/5 3:43 下午
     * @params rts 采集到的原始数据
     */
    public Tag getTag(RtDTO rtDTO) {
        Tag tag = null;
        if (TAG_CONFIG_CACHE.containsKey(rtDTO.getTnm())) {
            tag = TAG_CONFIG_CACHE.get(rtDTO.getTnm());
        } else {
            HashMap<String, String> params = new HashMap<>();
            params.put("tag", rtDTO.getTnm());
            Dao dao = (Dao) PlatApplicationContext.getBean("dao");
            List<Tag> list = dao.query("MXPS01.queryByTag", params);
            if (list != null && list.size() > 0) {
                tag = list.get(0);
            }
            TAG_CONFIG_CACHE.put(rtDTO.getTnm(), tag);
        }
        return tag;
    }

    /**
     * 处理通讯量
     *
     * @return
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/24 3:49 下午
     * @params tag 标签
     * @params rtDTO 采集数据对象
     * @params rtDTOClone 采集数据克隆对象
     * @params mspl011 参数持久对象
     * 1.判断是否开启报警功能
     * 2.判断报警等级
     * 3.开启或者取消定时器，并做相应的内容颜色设置
     */
    private void handleTX(Tag tag, RtDTO rtDTO, RtDTO rtDTOClone, MSPL01 mspl011) {
        if (GatherServer.ON.equals(mspl011.getAlarmEnableStatus()) || mspl011.getAlarmEnableStatus() == null) {
            boolean flag = true; //默认开启报警
            double val = rtDTOClone.getVal();
            int grade = 0;
            String alarmType = "恢复正常";
            if (tag.getLower() != null && val < tag.getLower()) {
                grade = 2;
                alarmType = "低报警";
            }
            if (tag.getUpper() != null && val > tag.getUpper()) {
                grade = 2;
                alarmType = "高报警";
            }
            if (tag.getLowerLower() != null && val < tag.getLowerLower()) {
                grade = 1;
                alarmType = "低低报警";
            }
            if (tag.getUpperUpper() != null && val > tag.getUpperUpper()) {
                grade = 1;
                alarmType = "高高报警";
            }
            if (SNAPSHOT_MAP.containsKey(tag.getName())) {
                if (SNAPSHOT_MAP.get(tag.getName()).equals(alarmType)) {
                    flag = false; //关闭报警
                }
            } else {
                if (StringUtils.isNotBlank(alarmType)) {
                    SNAPSHOT_MAP.put(tag.getName(), alarmType);
                }
            }
            switch (grade) {
                case 0:
                    rtDTOClone.setValText(String.valueOf(val));
                    rtDTOClone.setGrade("");
                    if (SNAPSHOT_MAP.containsKey(tag.getName()) && flag) {
                        cancelTimer(rtDTO);
                        handleDB(tag, rtDTO, rtDTOClone, null, mspl011, alarmType);
                    }
                    break;
                case 1:
                    rtDTOClone.setValText("<span class=\"error\">" + val + "</span>");
                    rtDTOClone.setGrade("<span class=\"error\">一级报警</span>");
                    if (flag) {
                        openTimer(mspl011, rtDTO, String.valueOf(grade), alarmType);
                        handleDB(tag, rtDTO, rtDTOClone, String.valueOf(grade), null, alarmType);
                    }
                    break;
                case 2:
                    rtDTOClone.setValText("<span class=\"warning\">" + val + "</span>");
                    rtDTOClone.setGrade("<span class=\"warning\">二级报警</span>");
                    if (flag) {
                        openTimer(mspl011, rtDTO, String.valueOf(grade), alarmType);
                        handleDB(tag, rtDTO, rtDTOClone, String.valueOf(grade), null, alarmType);
                    }
                    break;
            }
        } else {
            handleDB(tag, rtDTO, rtDTOClone, null, mspl011, null);
            rtDTOClone.setGrade("");
        }
    }

    /**
     * 处理枚举量
     *
     * @return
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/24 3:46 下午
     * @params rtDTOClone 采集数据克隆对象
     * @params mspl011 参数持久对象
     * 1.根据值与枚举适配标签显示
     */
    public void handleMJ(RtDTO rtDTOClone, MSPL01 mspl011) {
        if (rtDTOClone.getVal() != null) {
            if (StringUtils.isNotBlank(mspl011.getEnumValue01())
                    && rtDTOClone.getVal() == Double.parseDouble(mspl011.getEnumValue01())) {
                rtDTOClone.setValText(mspl011.getEnumValue01Label());
            } else if (StringUtils.isNotBlank(mspl011.getEnumValue02())
                    && rtDTOClone.getVal() == Double.parseDouble(mspl011.getEnumValue02())) {
                rtDTOClone.setValText(mspl011.getEnumValue02Label());
            } else if (StringUtils.isNotBlank(mspl011.getEnumValue03())
                    && rtDTOClone.getVal() == Double.parseDouble(mspl011.getEnumValue03())) {
                rtDTOClone.setValText(mspl011.getEnumValue03Label());
            } else if (StringUtils.isNotBlank(mspl011.getEnumValue04())
                    && rtDTOClone.getVal() == Double.parseDouble(mspl011.getEnumValue04())) {
                rtDTOClone.setValText(mspl011.getEnumValue04Label());
            } else if (StringUtils.isNotBlank(mspl011.getEnumValue05())
                    && rtDTOClone.getVal() == Double.parseDouble(mspl011.getEnumValue05())) {
                rtDTOClone.setValText(mspl011.getEnumValue05Label());
            }
        }
    }

    /**
     * 处理开关量
     *
     * @return
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/7/24 3:43 下午
     * @params tag 标签
     * @params rtDTO 采集数据对象
     * @params rtDTOClone 采集数据克隆对象
     * @params mspl011 参数持久对象
     * 1.判断是否开启报警，是否值与报警值相同
     * 2.相同走报警流程
     * 3.不同走正常流程
     */
    private void handleKG(Tag tag, RtDTO rtDTO, RtDTO rtDTOClone, MSPL01 mspl011) {
        String alarmValue = mspl011.getAlarmValue(); //报警值
        if ((GatherServer.ON.equals(mspl011.getAlarmEnableStatus()) || mspl011.getAlarmEnableStatus() == null)
                && StringUtils.isNotBlank(alarmValue) && Double.parseDouble(alarmValue) == rtDTOClone.getVal()) {
            boolean flag = true; //默认开启报警
            String alarmType = "";
            if (1 == rtDTOClone.getVal()) {
                rtDTOClone.setValText("<span class=\"error\">" + mspl011.getTrueValueLabel() + "</span>");
                alarmType = "TRUE";
            } else {
                rtDTOClone.setValText(mspl011.getFalseValueLabel());
                alarmType = "FALSE";
            }
            if (SNAPSHOT_MAP.containsKey(tag.getName())) {
                if (SNAPSHOT_MAP.get(tag.getName()).equals(alarmType)) {
                    flag = false; //关闭报警
                }
            } else {
                SNAPSHOT_MAP.put(tag.getName(), alarmType);
            }
            rtDTOClone.setGrade("<span class=\"error\">二级报警</span>");
            if (flag) {
                openTimer(mspl011, rtDTO, String.valueOf(2), alarmType);
                handleDB(tag, rtDTO, rtDTOClone, String.valueOf(2));
            }
        } else {
            if (1 == rtDTOClone.getVal()) {
                rtDTOClone.setValText(mspl011.getTrueValueLabel());
            } else {
                rtDTOClone.setValText(mspl011.getFalseValueLabel());
            }

            //判断是不是恢复短信
            String alarmType = null;
            //短信配置打开 并且 报警值不为空 并且 当前情况不报警 并且 缓存的上次点位报警值
            if((GatherServer.ON.equals(mspl011.getAlarmEnableStatus()) || mspl011.getAlarmEnableStatus() == null)
                    && StringUtils.isNotBlank(alarmValue)
                    && Double.parseDouble(alarmValue) != rtDTOClone.getVal()
                    && SNAPSHOT_MAP.containsKey(tag.getName())) {
                String tagPrevStatus = SNAPSHOT_MAP.get(tag.getName());
                String parseDouble = tagPrevStatus.equalsIgnoreCase("TRUE") ? "1" : "0";
                if (Double.valueOf(parseDouble).doubleValue() == Double.valueOf(alarmValue).doubleValue()) alarmType = "恢复正常";
            }

            handleDB(tag, rtDTO, rtDTOClone, null, mspl011, alarmType);
            cancelTimer(rtDTO);
            rtDTOClone.setGrade("");
        }
    }

    /**
     * 取消指定的定时器
     *
     * @return
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/23 2:16 下午
     * @params tag 标签
     * 1.清理报警定时器
     * 2.删除取消的定时器
     */
    private void cancelTimer(RtDTO rtDTO) {
        if (TIMER_MAP.containsKey(rtDTO.getTnm())) {
            ScheduledFuture<?> future = TIMER_MAP.get(rtDTO.getTnm());
            if (future != null && !future.isDone()) {
                future.cancel(true);
            } else {
                TIMER_MAP.remove(rtDTO.getTnm());
            }
            POOL.setRemoveOnCancelPolicy(true);
        }
    }

    /**
     * 打开定时器处理短信发送
     *
     * @return
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/23 1:57 下午
     * @params mspl011 参数对象
     * @params rtDTO
     * @params grade
     * @params oldGrade 新增加的参数，由于恢复短信出现不区分一二级报警人都发送的问题，
     *                  这里增加参数oldGrade 代表恢复之前的报警等级（和grade一样）
     * 1.获取持久化对象
     * 2.根据tag获取查询的参数对象
     * 3.开启定时器，执行发送短信任务
     * 4.添加定时器任务到缓存容器中
     */
    private void openTimer(MSPL01 mspl011, RtDTO rtDTO, String grade, String alarmType, String oldGrade) {
        String tnm = rtDTO.getTnm();
        if ("恢复正常".equals(alarmType)) {
            tnm += "恢复正常";
        }
        if (!TIMER_MAP.containsKey(tnm)) {
            if (StringUtils.isNotBlank(mspl011.getDeadTime())) {
                String finalTnm = tnm;
                ScheduledFuture<?> schedule = POOL.schedule(() -> {
                    handleSMS(mspl011, rtDTO, grade, alarmType, oldGrade);
                    TIMER_MAP.remove(finalTnm);
                }, Long.parseLong(mspl011.getDeadTime()), TimeUnit.SECONDS);
                TIMER_MAP.put(tnm, schedule);
            } else {
                handleSMS(mspl011, rtDTO, grade, alarmType, oldGrade);
            }
        }
    }

    /**
     * 打开定时器处理短信发送
     * 重载方法， 新的方法增加参数， 保留老的方法调用
     *
     * @author chenYang
     *
     * @param mspl011
     * @param rtDTO
     * @param grade
     * @param alarmType
     */
    private void openTimer(MSPL01 mspl011, RtDTO rtDTO, String grade, String alarmType) {
        this.openTimer(mspl011, rtDTO, grade, alarmType, null);
    }

    /**
     * 发送短信
     *
     * @return
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/23 1:42 下午
     * @params mspl011 参数持久对象
     * @params rtDTO 采集数据对象
     * @params grade 报警等级
     * 1.查询短信模版
     * 2.查询参数关联的报警人列表，添加到短信参数中
     * 3.发送短信
     */
    private void handleSMS(MSPL01 mspl011, RtDTO rtDTO, String grade, String alarmType, String oldGrade) {
        EiInfo ei = new EiInfo();
        ei.set(EiConstant.serviceId, "S_ED_36");
        ei.set("codesetCode", "znjk.ms.sms.conf");
        EiInfo out = XServiceManager.call(ei);
        ArrayList<TEDCM01> result = (ArrayList<TEDCM01>) out.getAttr().get("result");
        if (result != null && result.size() > 0 && result.size() >= 2) {
            String templateCode = result.get(0).getItemCode();
            String onOff = result.get(1).getItemCode();
            if (StringUtils.isNotBlank(templateCode) && StringUtils.isNotBlank(onOff)) {
                if (GatherServer.ON.equals(onOff)) {
                    Dao dao = (Dao) PlatApplicationContext.getBean("dao");
                    MSPA01 mspa01 = new MSPA01();
                    mspa01.setT_param_classify_id(mspl011.gettParamClassifyId());
                    List<MSPA01> mspa01List = dao.query("MSPA01.queryByClassifyId", mspa01);
                    mspa01List = mspa01List.stream().filter(mspa011 -> {
                        String grade_filter = mspa011.getGrade_filter();
                        if ("恢复正常".equals(alarmType)) {
                            //如果是恢复正常的短信 则要判断恢复正常之前的报警等级是什么， 否则会一二级接收人都会发送
                            return ifSmsSendByGrade(grade_filter, oldGrade);
                        } else {
                            return ifSmsSendByGrade(grade_filter, grade);
                        }
                    }).collect(Collectors.toList());
                    if (mspa01List.size() > 0) {
                        List<String> paramList = new ArrayList<>();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        String dateFormat = simpleDateFormat.format(new Date());
                        String unitName = "";
                        if (result.size() >= 4) {
                            unitName = result.get(3).getItemCode();
                        }
                        //paramList.add(unitName); //使用单位
                        if(alarmType.equals("恢复正常")){
                            paramList.add("恢复通知");//恢复通知
                        }else {
                            paramList.add("报警通知");//报警通知
                        }
                        paramList.add(dateFormat); //日期
                        if (StringUtils.isNotBlank(mspl011.getName())) {
                            paramList.add(mspl011.getName()); //参数名称
                        } else {
                            paramList.add(rtDTO.getTnm()); //参数TAG
                        }
                        paramList.add(alarmType); //报警类型
                        //paramList.add(grade); //报警等级
                        //报警类型如果是开关量， 替换报警值为中文
                        String warningVal = rtDTO.getVal().toString();
                        if("1".equals(mspl011.getType())) {
                            if (Double.valueOf(warningVal) == 0) {
                                warningVal = mspl011.getFalseValueLabel();
                            } else if (Double.valueOf(warningVal) == 1) {
                                warningVal = mspl011.getTrueValueLabel();
                            }
                        }
                        if(alarmType.equals("恢复正常")){
                            paramList.add("恢复值："+warningVal); //恢复值
                        }else {
                            paramList.add("报警值"+warningVal); //报警值
                        }
                        List<String> workNoList = mspa01List.stream().map(mspa011 -> mspa011.getStaffid()).collect(Collectors.toList());
                        EiInfo eiInfo = new EiInfo();
                        eiInfo.set(EiConstant.serviceId, "S_MC_FW_01");
                        eiInfo.set("templateCode", templateCode);
                        eiInfo.set("paramList", paramList);
                        AtomicBoolean flag = new AtomicBoolean(false);
                        workNoList.forEach(workNo -> {
                            List<String> oneList = new ArrayList<>();
                            oneList.add(workNo);
                            eiInfo.set("workNoList", oneList);

                            dao.insert("MSPA01.saveRecord", new HashMap<String, String>() {{
                                put("param", JSON.toJSONString(paramList));
                                put("man", JSON.toJSONString(oneList));
                            }});

                            //只有短信配置打开的时候 才会发送短信
                            if (GatherServer.SMS_SEND_FLAG) {
                                EiInfo outInfo = XServiceManager.call(eiInfo);
                                //0短信发送失败，重新发送
                                if ("0".equals(outInfo.get("isSuccess"))) {
                                    flag.set(true);
                                }
                            }
                        });
                        if (flag.get()) {
                            SNAPSHOT_MAP.remove(rtDTO.getTnm());
                        } else {
                            if (!"恢复正常".equals(alarmType)) {
                                SNAPSHOT_MAP.put(rtDTO.getTnm(), alarmType);
                            }
                        }
                    }
                }
            } else {
                throw new PlatException("请先配置短信模版和短信开关字典！");
            }
        } else {
            throw new PlatException("请先配置短信模版和短信开关字典！");
        }
    }

    /**
     * 根据报警接收人配置 和 报警等级判断短信发送不发送
     *
     * @param config 接收人配置中的等级 13为二级报警 24为一级报警
     * @param grade 当前点位的报警等级 1为一级报警 2为二级报警
     * @return
     */
    private boolean ifSmsSendByGrade(String config, String grade) {
        //如果配置和短信等级 有一个是空值 不发送
        if (StringUtils.isEmpty(grade) || StringUtils.isEmpty(config)) {
            return false;
        }
        //如果配置的是二级报警， 则只有等级是2的时候发送
        if (config.contains("1") || config.contains("3")) {
            if ("2".equals(grade)) {
                return true;
            }
        }
        //如果配置的是一级报警，则只有等级是1的时候发送
        if (config.contains("2") || config.contains("4")) {
            if ("1".equals(grade)) {
                return true;
            }
        }
        //其余情况不发送
        return false;
    }

    /**
     * 操作数据库 重载
     *
     * @return
     * @author panlingfeng
     * @date 2021/9/30 2:54 下午
     * @params tag 标签对象
     * @params rtDTO 采集数据对象
     * @params rtDTOClone 采集数据克隆对象
     * @params grade 报警等级
     */
    private void handleDB(Tag tag, RtDTO rtDTO, RtDTO rtDTOClone, String grade) {
        handleDB(tag, rtDTO, rtDTOClone, grade, null, null);
    }

    /**
     * 操作数据库
     *
     * @return 1.根据参数tag查询结果
     * 2.不报警则无需持久化
     * 3.报警则持久化
     * 4.更加是否有报警等级来执行恢复还是更新报警时间
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/18 3:04 下午
     * @params tag 标签对象
     * @params rtDTO 采集数据对象
     * @params rtDTOClone 采集数据克隆对象
     * @params grade 报警等级
     *
     * *grade为null的时候发送恢复短信
     */
    private void handleDB(Tag tag, RtDTO rtDTO, RtDTO rtDTOClone, String grade, MSPL01 mspl011, String alarmType) {
        List<MXAL01> list = selectByTag(tag);
        if ((list == null || list.size() == 0) && grade != null) {
            insertLog(tag, rtDTOClone, rtDTO, alarmType);
        } else {
            for (MXAL01 cmal01 : list) {
                cmal01.setGrade(grade);  //告警等级
                cmal01.setDate(new Date());  //日期
                if (grade == null) {
                    //发送恢复短信的时候先获取之前的报警等级
                    String oldGrade = SNAPSHOT_MAP.get(tag.getName());
                    //开光量保存的值为 TRUE或者FALSE 将报警等级转化为2
                    if ("TRUE".equalsIgnoreCase(oldGrade) || "FALSE".equalsIgnoreCase(oldGrade)) {
                        oldGrade = "2";
                    }
                    //如果是低低报警 高高报警为 1 低报警高报警为2
                    if ("低低报警".equalsIgnoreCase(oldGrade) || "高高报警".equalsIgnoreCase(oldGrade)) {
                        oldGrade = "1";
                    }
                    if ("低报警".equalsIgnoreCase(oldGrade) || "高报警".equalsIgnoreCase(oldGrade)) {
                        oldGrade = "2";
                    }
                    SNAPSHOT_MAP.remove(tag.getName());
                    openTimer(mspl011, rtDTO, null, alarmType, oldGrade);
                    recovery(cmal01);
                } else {
                    if (StringUtils.isNotBlank(alarmType)) {
                        if (SNAPSHOT_MAP.containsKey(tag.getName())) {
                            if (!SNAPSHOT_MAP.get(tag.getName()).equals(alarmType)) {
                                updateEndTime(cmal01); //更新原来报警级别的结束时间
                                insertLog(tag, rtDTOClone, rtDTO, alarmType); //插入最新的报警级别数据
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 更新结束告警时间
     *
     * @return
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/18 3:23 下午
     * @params cmal01 业务对象
     * 1.获取持久化对象
     * 2.执行更新
     */
    private void updateEndTime(MXAL01 cmal01) {
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        dao.update("MXAL01.updateEndTime", cmal01);
    }

    /**
     * 告警恢复
     *
     * @return
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/18 3:23 下午
     * @params cmal01 业务对象
     * 1.获取持久化对象
     * 2.执行更新
     */
    private void recovery(MXAL01 cmal01) {
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        dao.update("MXAL01.recovery", cmal01);
    }

    /**
     * 根据tag查询结果
     *
     * @return List 返回的查询结果集合
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/18 2:13 下午
     * @params tag 标签对象
     * 1.创建参数对象
     * 2.获取数据库操作对象
     * 3.执行查询
     */
    private List<MXAL01> selectByTag(Tag tag) {
        MXAL01 cmal01 = new MXAL01();
        cmal01.setTag(tag.getName());   //tag
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        return dao.query("MXAL01.selectByTag", cmal01);
    }

    /**
     * 插入日志信息
     *
     * @return
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/17 4:20 下午
     * @params Tag tag 标记对象
     * @params RtDTO rtDTO 实时数据对象
     * @params alarmType 报警类型
     * 1.创建对象
     * 2.封装数据
     * 3.获取数据库操作对象
     * 4.持久化数据
     */
    private void insertLog(Tag tag, RtDTO rtDTOClone, RtDTO rtDTO, String alarmType) {
        MXAL01 cmal01 = new MXAL01();
        cmal01.setId(UUIDUtils.getUUID());  //id
        cmal01.setT_area_classify_id(tag.getAreaId());  //区域分类
        cmal01.setArea_name(tag.getArea());             //区域名称
        cmal01.setDevice_name(tag.getDevice());         //设备名称
        cmal01.setValue_(String.valueOf(rtDTO.getVal()));    //告警值
        cmal01.setItem(tag.getLabel());                      //告警项
        cmal01.setDate(new Date(/*Long.parseLong(rtDTO.getTss() + rtDTO.getTsm())*/));  //日期
        cmal01.setTag(tag.getName());                   //tag
        cmal01.setDescription_(alarmType);      //说明，报警类型
        cmal01.setClassify_(tag.getClassify());     //分类
        if (rtDTOClone.getGrade().contains("一级报警")) {
            cmal01.setGrade("1");      //一级报警
        } else {
            cmal01.setGrade("2");      //二级报警
        }
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        dao.insert("MXAL01.insert", cmal01);
    }

}
