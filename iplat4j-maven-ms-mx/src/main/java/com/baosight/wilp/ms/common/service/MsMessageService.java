package com.baosight.wilp.ms.common.service;

import com.alibaba.fastjson.JSON;
import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.common.ed.domain.TEDCM01;
import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.ms.common.domain.RtDTO;
import com.baosight.wilp.ms.common.domain.TagTypeEnum;
import com.baosight.wilp.ms.common.domain.sms.MsAlarmSleepingDto;
import com.baosight.wilp.ms.common.domain.sms.MsSmsConfig;
import com.baosight.wilp.ms.common.domain.sms.MsAlarmTypeEnum;
import com.baosight.wilp.ms.common.util.UUIDUtils;
import com.baosight.wilp.ms.common.web.GatherServer;
import com.baosight.wilp.ms.pa.domain.MSPA01;
import com.baosight.wilp.ms.pl.domain.MSPL01;
import com.baosight.wilp.mx.al.domain.MXAL01;
import com.baosight.wilp.mx.ps.domain.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;


/**
 * 智能监控短信 通知 重新开发 替换原来的webSocketService中的 这块功能
 *
 * @author chenYang
 */
@Service
public class MsMessageService {


    @Resource
    private WebSocketService webSocketService;

    /**
     * 获取dao
     */
    private static final Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    /**
     * 短信配置
     */
    private static MsSmsConfig msSmsConfig = new MsSmsConfig();

    /**
     * 短信接收人配置内容
     * key：classifyId 点位分类分项id
     * value：短信接收人实体
     */
    private static Map<String, List<MSPA01>> smsReceiverConfigMap = new ConcurrentHashMap<>();

    /**
     * 上一次 有效报警的 点位
     * key：tag
     * value：报警类型 不包含恢复
     */
    private static final Map<String, MsAlarmTypeEnum> prevAlarmTagMap = new ConcurrentHashMap<>();

    /**
     * 上一次 点位状态发生变更的 记录
     * key：tag
     * value：点位变更的记录， 记录变更值以及时间
     */
    private static final Map<String, MsAlarmSleepingDto> tagValueSwitchMap = new ConcurrentHashMap<>();

    /**
     * 线程池 用来处理死区时间
     */
    public static final ScheduledThreadPoolExecutor POOL = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(6);

    /**
     * 企业微信推送 应用编码
     */
    private static final String APP_CODE = "AP00002";

    /**
     * 使用静态代码块初始化报警数据
     */
    static {
        List<Map<String, String>> alarms = dao.query("MXAL01.queryAlarmTag", null);
        alarms.forEach(alarm -> {
            prevAlarmTagMap.put(alarm.get("tag"), MsAlarmTypeEnum.getAlarmTypeByDesc(alarm.get("description")));
        });
    }

    /**
     * 新的 handleRvalue 重新梳理了逻辑 替换 WebSocketService 中的同名方法
     *
     * @param rts   过滤过的tag点集合
     * @return 需要后面处理的集合
     */
    public HashMap<String, RtDTO> handleRvalue(List<RtDTO> rts) {
        //加载配置
        getSmsConfig();
        getSmsReceiveConfig();

        HashMap<String, RtDTO> rValueHashMap = new HashMap<>();
        rts.forEach(rtDTO -> {
            RtDTO rtDTOClone = rtDTO.clone();
            MSPL01 mspl011 = GatherServer.getMspl01(rtDTO, true);
            if (mspl011 != null) {
                Tag tag = webSocketService.getTag(rtDTO);
                if (tag != null) {
                    if (TagTypeEnum.KG.getValue().equals(mspl011.getType())) { //开关量
                        handleKG(tag, mspl011, rtDTO, rtDTOClone);
                    } else if (TagTypeEnum.MJ.getValue().equals(mspl011.getType())) { //枚举量
                        webSocketService.handleMJ(rtDTOClone, mspl011);
                    } else { //通讯量
                        handleTX(tag, mspl011, rtDTO, rtDTOClone);
                    }
                    rValueHashMap.put(tag.getName(), rtDTOClone);
                }
            }
        });
        return rValueHashMap;
    }

    /**
     * 处理开关量
     *
     * <p> 1. 判断需不需要处理报警 不需要直接返回
     * <p> 2. 判断当前的报警等级以及报警类型 处理拷贝对象
     * <p> 3. 调用消息处理办法
     *
     * @param tagInfo       tag的信息
     * @param tagConfig     tag的配置
     * @param tag           tag
     * @param tagClone      tag的拷贝对象
     */
    private void handleKG(Tag tagInfo, MSPL01 tagConfig, RtDTO tag, RtDTO tagClone) {
        //如果报警配置项关闭， 不处理该点位的报警
        if (tagConfig.getAlarmEnableStatus() != null && !GatherServer.ON.equals(tagConfig.getAlarmEnableStatus())) {
            tagClone.setGrade("");
            return;
        }

        //配置项开启的时候判断当前 报警类型
        MsAlarmTypeEnum currentAlarmType;
        //发生报警时的值
        String alarmValue = tagConfig.getAlarmValue();
        //获取报警值
        String warningValue = tagClone.getVal() == 1 ? tagConfig.getTrueValueLabel() : tagConfig.getFalseValueLabel();

        //判断报警等级
        if (StringUtils.isNotBlank(alarmValue) && Double.parseDouble(alarmValue) == tagClone.getVal()) {
            //开关量发生报警
            if (Double.parseDouble(alarmValue) == 1) {
                currentAlarmType = MsAlarmTypeEnum.TRUE;
            } else {
                currentAlarmType = MsAlarmTypeEnum.FALSE;
            }
        } else {
            //开光量未发生报警 即恢复正常
            currentAlarmType = MsAlarmTypeEnum.RECOVER;
        }

        //插入展示文字信息
        if (currentAlarmType == MsAlarmTypeEnum.RECOVER) {
            tagClone.setGrade("");
            tagClone.setValText(warningValue);
        } else {
            tagClone.setGrade("<span class=\"error\">" + currentAlarmType.getAlarmLevelDesc() + "</span>");
            tagClone.setValText("<span class=\"error\">" + warningValue + "</span>");
        }

        // 发送消息 以及 报警
        dealMessage(tagInfo, tagConfig, tag, currentAlarmType, warningValue);
    }

    /**
     * 处理通讯量
     *
     * <p> 1. 判断需不需要处理报警 不需要直接返回
     * <p> 2. 判断当前的报警等级以及报警类型 处理拷贝对象
     * <p> 3. 调用消息处理办法
     *
     * @param tagInfo       tag的信息
     * @param tagConfig     tag的配置
     * @param tag           tag
     * @param tagClone      tag的拷贝对象
     */
    private void handleTX(Tag tagInfo, MSPL01 tagConfig, RtDTO tag, RtDTO tagClone) {
        //如果报警配置项关闭， 不处理该点位的报警
        if (tagConfig.getAlarmEnableStatus() != null && !GatherServer.ON.equals(tagConfig.getAlarmEnableStatus())) {
            tagClone.setGrade("");
            return;
        }

        //配置项开启的时候判断当前 报警类型
        MsAlarmTypeEnum currentAlarmType;
        //报警值
        String warningValue = String.valueOf(tagClone.getVal());

        //判断报警等级
        if (tagInfo.getLowerLower() != null && tagClone.getVal() < tagInfo.getLowerLower()) {
            currentAlarmType = MsAlarmTypeEnum.LOW_LOW;
        } else if (tagInfo.getUpperUpper() != null && tagClone.getVal() > tagInfo.getUpperUpper()) {
            currentAlarmType = MsAlarmTypeEnum.HIGH_HIGH;
        } else if (tagInfo.getLower() != null && tagClone.getVal() < tagInfo.getLower()) {
            currentAlarmType = MsAlarmTypeEnum.LOW;
        } else if (tagInfo.getUpper() != null && tagClone.getVal() > tagInfo.getUpper()) {
            currentAlarmType = MsAlarmTypeEnum.HIGH;
        } else {
            currentAlarmType = MsAlarmTypeEnum.RECOVER;
        }

        //插入展示文字信息
        if (currentAlarmType == MsAlarmTypeEnum.RECOVER) {
            tagClone.setGrade("");
            tagClone.setValText(warningValue);
        } else {
            tagClone.setGrade("<span class=\"error\">" + currentAlarmType.getAlarmLevelDesc() + "</span>");
            tagClone.setValText("<span class=\"error\">" + warningValue + "</span>");
        }

        // 发送消息 以及 报警
        dealMessage(tagInfo, tagConfig, tag, currentAlarmType, warningValue);
    }

    /**
     * 发生报警时的处理数据方法 （包含短信 报警表）
     *
     * @param tagInfo       点位详细信息
     * @param tagConfig     点位配置实体
     * @param tag           点位上传的数据
     * @param alarmType     报警类型
     * @param warningValue  报警值， 由于开关量和枚举量的报警值 不是val 所以各自处理完之后单独传入
     */
    private synchronized void dealMessage(Tag tagInfo, MSPL01 tagConfig, RtDTO tag, MsAlarmTypeEnum alarmType, String warningValue) {
        // 判断当前的报警等级 和上一次的报警等级 是不是一致 只有不一致 才会发送消息以及短信
        if (currentAlarmEqualsPrevAlarm(tag.getTnm(), alarmType) && tagValueSwitchMap.get(tag.getTnm()) == null) return;

        //当前时间为报警时间
        LocalDateTime dateTime = LocalDateTime.now();
        //点位的死区时间设置
        String deadTime = tagConfig.getDeadTime();

        //获取上一次保存的记录， 判断和当前的报警状态是否一致， 如果一致不处理 不一致 需要发起新的定时器
        if (currentAlarmEqualsPrevSwitch(tag.getTnm(), alarmType)) {
            return;
        }

        //如果死区时间未配置， 直接发送
        if (StringUtil.isEmpty(deadTime) || Double.parseDouble(deadTime) == 0d) {
            messageAction(tagInfo, tagConfig, tag, alarmType, warningValue, dateTime);
            prevAlarmTagMap.put(tag.getTnm(), alarmType);
            return;
        }

        if (tagValueSwitchMap.containsKey(tag.getTnm())) tagValueSwitchMap.get(tag.getTnm()).getFuture().cancel(true);
        POOL.setRemoveOnCancelPolicy(true);
        //不一致的情况 1.变更上一次的缓存(关闭之前的定时器) 2.发起新的定时器（判断是否需要发送（短信 警报信息等操作）， 移除上一次的缓存 将这次的警报信息保存）
        ScheduledFuture<?> scheduledFuture = POOL.schedule(() -> {
            if (!currentAlarmEqualsPrevAlarm(tag.getTnm(), alarmType)) {
                //当前的报警状态和 上一次缓存的不一样的才需要发送短信，
                messageAction(tagInfo, tagConfig, tag, alarmType, warningValue, dateTime);
                prevAlarmTagMap.put(tag.getTnm(), alarmType);
            }
            tagValueSwitchMap.remove(tag.getTnm());
        }, Long.parseLong(deadTime), TimeUnit.SECONDS);
        tagValueSwitchMap.put(tag.getTnm(), new MsAlarmSleepingDto(tag.getTnm(), dateTime, alarmType, scheduledFuture));
    }

    /**
     * 短信以及报警信息的处理
     *
     * <p> 1. 发送短信
     * <p> 2. 首次触发不发送短信
     * <p> 3. 更新之前的报警信息的状态
     * <p> 4. 如果不是恢复短信 插入新的报警信息
     *
     * @param tagInfo           点位详细信息
     * @param tagConfig         点位配置实体
     * @param tag               点位上传的数据
     * @param alarmType         报警类型
     * @param warningValue      报警值， 由于开关量和枚举量的报警值 不是val 所以各自处理完之后单独传入
     * @param dateTime          报警时间
     */
    private void messageAction(Tag tagInfo, MSPL01 tagConfig, RtDTO tag, MsAlarmTypeEnum alarmType, String warningValue, LocalDateTime dateTime) {
        sendSms(alarmType, tag.getTnm(), tagConfig, warningValue, tagConfig.gettParamClassifyId(),
                dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        //更新之前的结果， 如果是不是恢复短信 需要插入一条新记录
        MXAL01 mxal01 = new MXAL01();
        mxal01.setId(UUIDUtils.getUUID());
        mxal01.setT_area_classify_id(tagInfo.getAreaId());
        mxal01.setArea_name(tagInfo.getArea());
        mxal01.setDevice_name(tagInfo.getDevice());
        mxal01.setValue_(String.valueOf(tag.getVal()));
        mxal01.setItem(tagInfo.getLabel());
        mxal01.setDate(Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));
        mxal01.setTag(tagInfo.getName());
        mxal01.setDescription_(alarmType.getDesc());
        mxal01.setClassify_(tagInfo.getClassify());
        mxal01.setGrade(alarmType == MsAlarmTypeEnum.RECOVER ? prevAlarmTagMap.get(tag.getTnm()).getAlarmLevel() : alarmType.getAlarmLevel());

        List<MXAL01> list = dao.query("MXAL01.selectByTag", mxal01);
        list.forEach(x -> {
            x.setDate(Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));
            x.setGrade(alarmType == MsAlarmTypeEnum.RECOVER ? prevAlarmTagMap.get(tag.getTnm()).getAlarmLevel() : alarmType.getAlarmLevel());
            dao.update("MXAL01.updateEndTime", x);
        });
        if (alarmType != MsAlarmTypeEnum.RECOVER) {
            dao.insert("MXAL01.insert", mxal01);
        }
    }

    /**
     * 判断当前的报警值 和 缓存中的上一次报警值是否一致
     *
     * @param tag                   需要进行判断的tag
     * @param currentAlarmType      当前的报警值
     * @return 是否一致 TRUE一致 FALSE 不一致
     */
    private boolean currentAlarmEqualsPrevSwitch(String tag, MsAlarmTypeEnum currentAlarmType) {
        //如果缓存中没有值， 说明没有发生新的报警状态切换， 返回不同
        if (tagValueSwitchMap.get(tag) == null) {
            return false;
        }
        //返回 当前报警状态 和 缓存中的报警状态的比较
        return currentAlarmType == tagValueSwitchMap.get(tag).getAlarmTypeEnum();
    }

    /**
     * 判断当前的报警值 和 缓存上一次成功发出的报警是否一致
     *
     * @param tag                   需要进行判断的tag
     * @param currentAlarmType      当前的报警值
     * @return 是否一致 TRUE一致 FALSE 不一致
     */
    private boolean currentAlarmEqualsPrevAlarm(String tag, MsAlarmTypeEnum currentAlarmType) {
        //上一次保存的
        MsAlarmTypeEnum prevAlarmType = prevAlarmTagMap.get(tag);

        //如果是恢复短信 并且之前是null 返回true
        if (prevAlarmType == null && currentAlarmType == MsAlarmTypeEnum.RECOVER) {
            return true;
        }
        //否则 只有相等的时候返回true
        return prevAlarmType == currentAlarmType;
    }

    /**
     * 发送短信
     *
     * <p> 1. 判断小代码中短信配置是否配置 以及开关是否打开
     * <p> 2. 查询配置的短信接收人 获取需要发送短信的接收人工号集合
     * <p> 3. 整合短信需要的参数 发送短信
     *
     * @param alarmType     报警类型
     * @param tag           点位tag
     * @param tagConfig     点位实体类
     * @param value         点位值
     * @param classifyId    点位分类id
     * @param alarmTime     报警时间 yyyy-MM-dd HH:mm
     */
    private void sendSms(MsAlarmTypeEnum alarmType, String tag,
                         MSPL01 tagConfig, String value, String classifyId, String alarmTime) {
        String name = tagConfig.getName();
        //获取短信配置 判断配置项是否已经都配置 并且短信开关打开
        if (StringUtil.isEmpty(msSmsConfig.getTemplateCode())
                || StringUtil.isEmpty(msSmsConfig.getOnOff()) || !GatherServer.ON.equals(msSmsConfig.getOnOff())) {
            return;
        }

        //根据分类id以及报警等级 获取 短信的接收人, 如果过滤之后的短信接收人集合长度为0 不发送短信
        List<MSPA01> receiverConfig = smsReceiverConfigMap.get(classifyId);
        if (receiverConfig == null) return;
        List<MSPA01> receivers = receiverConfig.stream().filter(mspa01 -> {
            String gradeFilter = mspa01.getGrade_filter();
            return ifSmsSendByGradeFilter(gradeFilter,
                    alarmType == MsAlarmTypeEnum.RECOVER ? prevAlarmTagMap.get(tag).getAlarmLevel() : alarmType.getAlarmLevel());
        }).collect(Collectors.toList());
        if (receivers.size() == 0) return;
        //从过滤出的接收人集合中 取出他们的工号集合
        List<String> workNos = receivers.stream().map(MSPA01::getStaffid).collect(Collectors.toList());

        // 报警短信格式，恢复短信、开关量报警短信、其他报警短信
        StringBuilder sb = new StringBuilder();
        if (alarmType == MsAlarmTypeEnum.RECOVER) {
            sb.append("【恢复通知】")
                    .append(StringUtil.isNotEmpty(name) ? name : tag)
                    .append(" 于")
                    .append(alarmTime)
                    .append("恢复正常。");
        } else {
            sb.append("【报警通知】")
                    .append(StringUtil.isNotEmpty(name) ? name : tag)
                    .append("于")
                    .append(alarmTime);
            if ("TRUE".equals(alarmType.getDesc())) {
                sb.append("发生状态切换：")
                        .append(tagConfig.getTrueValueLabel())
                        .append("。");
            } else if ("FALSE".equals(alarmType.getDesc())) {
                sb.append("发生状态切换：")
                        .append(tagConfig.getFalseValueLabel())
                        .append("。");
            } else {
                sb.append("发生")
                        .append(alarmType.getDesc())
                        .append("，报警值：")
                        .append(value)
                        .append("。");
            }
        }

        //整合目前短信模板用到的参数
        List<String> paramList = new ArrayList<String>() {{
            add(sb.toString());
        }};

        //发送短信
        EiInfo eiInfo = new EiInfo();
        eiInfo.set(EiConstant.serviceId, "S_MC_FW_01");
        eiInfo.set("templateCode", msSmsConfig.getTemplateCode());
        eiInfo.set("paramList", paramList);
        workNos.forEach(workNo -> {
            List<String> oneList = new ArrayList<String>() {{
                add(workNo);
            }};
            eiInfo.set("workNoList", oneList);

            dao.insert("MSPA01.saveRecord", new HashMap<String, String>() {{
                put("param", JSON.toJSONString(paramList));
                put("man", JSON.toJSONString(oneList));
            }});

            // 只有短信配置打开的时候 才会发送短信
            if (GatherServer.SMS_SEND_FLAG) {
                XServiceManager.call(eiInfo);
            }
        });
        // 发送企业微信推送
        BaseDockingUtils.pushWxMsg(workNos, paramList, msSmsConfig.getTemplateCode(), APP_CODE);
    }

    /**
     * 根据报警接受人中的配置 判断短信发布发送
     *
     * @param gradeFilter   接收人配置中的等级 13为二级报警 24为一级报警
     * @param grade         当前点位的报警等级 1为一级报警 2为二级报警
     * @return 是否发送 true发送 false不发送
     */
    private boolean ifSmsSendByGradeFilter(String gradeFilter, String grade) {
        //如果配置和短信等级 有一个是空值 不发送
        if (StringUtils.isEmpty(grade) || StringUtils.isEmpty(gradeFilter)) {
            return false;
        }
        //如果配置的是二级报警， 则只有等级是2的时候发送
        if (gradeFilter.contains("1") || gradeFilter.contains("3")) {
            if ("2".equals(grade)) {
                return true;
            }
        }
        //如果配置的是一级报警，则只有等级是1的时候发送
        if (gradeFilter.contains("2") || gradeFilter.contains("4")) {
            return "1".equals(grade);
        }
        //其余情况不发送
        return false;
    }

    /**
     * 获取短信接收人的配置
     */
    private void getSmsReceiveConfig() {
        MSPA01 mspa01 = new MSPA01();
        mspa01.setT_param_classify_id(null);
        List<MSPA01> paramHumanConfig = dao.query("MSPA01.queryByClassifyId", mspa01);

        Map<String, List<MSPA01>> receiveMap = new HashMap<>();
        paramHumanConfig.forEach(x -> {
            if (receiveMap.containsKey(x.getT_param_classify_id())) {
                receiveMap.get(x.getT_param_classify_id()).add(x);
            } else {
                receiveMap.put(x.getT_param_classify_id(), new ArrayList<MSPA01>() {{
                    add(x);
                }});
            }
        });
        smsReceiverConfigMap = receiveMap;
    }

    /**
     * 获取短信配置内容
     */
    private void getSmsConfig() {
        EiInfo ei = new EiInfo();
        ei.set(EiConstant.serviceId, "S_ED_36");
        ei.set("codesetCode", "znjk.ms.sms.conf");
        EiInfo out = XServiceManager.call(ei);
        ArrayList<TEDCM01> result = (ArrayList<TEDCM01>) out.getAttr().get("result");
        if (result == null || result.size() == 0 || result.size() < 2) {
            msSmsConfig = new MsSmsConfig();
            return;
        }
        String templateCode = result.get(0).getItemCode();
        String onOff = result.get(1).getItemCode();
        msSmsConfig = new MsSmsConfig(templateCode, onOff);
    }

}
