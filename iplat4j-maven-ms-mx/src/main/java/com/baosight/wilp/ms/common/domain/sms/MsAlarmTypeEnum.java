package com.baosight.wilp.ms.common.domain.sms;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 短信类型枚举
 *
 * @chenYang
 */
public enum MsAlarmTypeEnum {

    RECOVER("恢复正常", "", ""),
    LOW("低报警", "2", "二级报警"),
    HIGH("高报警", "2", "二级报警"),
    LOW_LOW("低低报警", "1", "一级报警"),
    HIGH_HIGH("高高报警", "1", "一级报警"),
    TRUE("TRUE", "2", "二级报警"),
    FALSE("FALSE", "2", "二级报警");

    /**
     * 报警类型 描述
     */
    private String desc;

    /**
     * 报警等级
     */
    private String alarmLevel;

    /**
     * 报警等级中文描述
     */
    private String alarmLevelDesc;

    private MsAlarmTypeEnum(String desc, String alarmLevel, String alarmLevelDesc) {
        this.desc = desc;
        this.alarmLevel = alarmLevel;
        this.alarmLevelDesc = alarmLevelDesc;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getAlarmLevel() {
        return this.alarmLevel;
    }

    public String getAlarmLevelDesc() {
        return this.alarmLevelDesc;
    }

    public static MsAlarmTypeEnum getAlarmTypeByDesc(String desc) {
        List<MsAlarmTypeEnum> type =
                Arrays.stream(MsAlarmTypeEnum.values()).filter(k -> k.desc.equals(desc)).collect(Collectors.toList());
        return type.size() > 0 ? type.get(0) : MsAlarmTypeEnum.RECOVER;
    }

}
