package com.baosight.wilp.ms.pl.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author znl
 * @title: MSPL01
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @date 2021/8/9 10:08
 */
public class MSPL01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private String id;

    /**
     * 参数类型（通讯量 0，开关量 1，枚举量 2）
     */
    private String type;

    /**
     * 参数项Tag
     */
    private String tag;

    /**
     * 是否同步到大屏
     */
    private String isSync;

    /**
     * 参数项名称
     */
    private String name;

    /**
     * 参数项描述
     */
    private String description;

    /**
     * 真值标签（1的替换文字）
     */
    private String trueValueLabel;

    /**
     * 假值标签（0的替换文字）
     */
    private String falseValueLabel;

    /**
     * 报警值（指定0报警还是1报警）
     */
    private String alarmValue;

    /**
     * 实时值
     */
    private String rValue;

    /**
     * 计量单位
     */
    private String unit;

    /*计量量纲*/
    private String dimension;
    /*计量量纲名称*/
    private String dimensionName;

    /**
     * 计量单位名称
     */
    private String unitName;

    /**
     * 报警启用状态
     */
    private String alarmEnableStatus;

    /**
     * 参数启用状态
     */
    private String paramEnableStatus;

    /**
     * 死区时间（指定时间范围内不报警，单位秒）
     */
    private String deadTime;

    /**
     * 枚举值01
     */
    private String enumValue01;

    /**
     * 枚举值01显示文本
     */
    private String enumValue01Label;

    /**
     * 枚举值01的报警级别
     */
    private String enumValue01Grade;

    /**
     * 枚举值02
     */
    private String enumValue02;

    /**
     * 枚举值02显示文本
     */
    private String enumValue02Label;

    /**
     * 枚举值02的报警级别
     */
    private String enumValue02Grade;

    /**
     * 枚举值03
     */
    private String enumValue03;

    /**
     * 枚举值03显示文本
     */
    private String enumValue03Label;

    /**
     * 枚举值03的报警级别
     */
    private String enumValue03Grade;

    /**
     * 枚举值04
     */
    private String enumValue04;

    /**
     * 枚举值04显示文本
     */
    private String enumValue04Label;

    /**
     * 枚举值04的报警级别
     */
    private String enumValue04Grade;

    /**
     * 枚举值05
     */
    private String enumValue05;

    /**
     * 枚举值05显示文本
     */
    private String enumValue05Label;

    /**
     * 枚举值05的报警级别
     */
    private String enumValue05Grade;

    /**
     * 枚举值06
     */
    private String enumValue06;

    /**
     * 枚举值06显示文本
     */
    private String enumValue06Label;

    /**
     * 枚举值06的报警级别
     */
    private String enumValue06Grade;

    /**
     * 数据格式
     */
    private String dataFormat;

    /**
     * 报警规则主键
     */
    private String tmsar01Id;

    /**
     * 参数所属分类主键
     */
    private String tParamClassifyId;

    /**
     * 电子邮件发送启用状态
     */
    private String emailEnableStatus;

    /**
     * 短信息发送启用状态
     */
    private String smsEnableStatus;

    /**
     * 限止时间段
     */
    private String limitTimeValue;

    /**
     * 限止时间段单位
     */
    private String limitTimeUnit;

    /**
     * 限止次数
     */
    private String limitRepeats;

    /**
     * 报警短信限止启用状态
     */
    private String limitRepeatsEnable;

    /**
     * 所属设备主键
     */
    private String tmsdc01Id;

    /**
     * 院区标识
     */
    private String groupId;

    /**
     * 关联报警规则名称
     */
    private String tmsarName;

    /**
     * 关联设备名称
     */
    private String tmsdcName;

    /**
     * 是否记录日志
     */
    private String isWriteLog;

    //vo 设备名称
    private String deviceName;

    //vo 实时数据时间
    private String rTime;

    private String significant_bit;

    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("type");
        eiColumn.setDescName("参数类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tag");
        eiColumn.setDescName("参数项Tag");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isSync");
        eiColumn.setDescName("是否同步到大屏");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("name");
        eiColumn.setDescName("参数项名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("description");
        eiColumn.setDescName("参数项描述");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("trueValueLabel");
        eiColumn.setDescName("真值标签（1的替换文字）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("falseValueLabel");
        eiColumn.setDescName("假值标签（0的替换文字）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("alarmValue");
        eiColumn.setDescName("报警值（指定0报警还是1报警）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rValue");
        eiColumn.setDescName("实时值");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unit");
        eiColumn.setDescName("计量单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unitName");
        eiColumn.setDescName("计量单位名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dimension");
        eiColumn.setDescName("计量量纲");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dimensionName");
        eiColumn.setDescName("计量量纲名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("alarmEnableStatus");
        eiColumn.setDescName("报警启用状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramEnableStatus");
        eiColumn.setDescName("参数启用状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deadTime");
        eiColumn.setDescName("死区时间（指定时间范围内不报警，单位秒）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue01");
        eiColumn.setDescName("枚举值01");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue01Label");
        eiColumn.setDescName("枚举值01显示文本");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue01Grade");
        eiColumn.setDescName("枚举值01的报警级别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue02");
        eiColumn.setDescName("枚举值02");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue02Label");
        eiColumn.setDescName("枚举值02显示文本");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue02Grade");
        eiColumn.setDescName("枚举值02的报警级别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue03");
        eiColumn.setDescName("枚举值03");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue03Label");
        eiColumn.setDescName("枚举值03显示文本");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue03Grade");
        eiColumn.setDescName("枚举值03的报警级别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue04");
        eiColumn.setDescName("枚举值04");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue04Label");
        eiColumn.setDescName("枚举值04显示文本");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue04Grade");
        eiColumn.setDescName("枚举值04的报警级别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue05");
        eiColumn.setDescName("枚举值05");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue05Label");
        eiColumn.setDescName("枚举值05显示文本");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue05Grade");
        eiColumn.setDescName("枚举值05的报警级别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue06");
        eiColumn.setDescName("枚举值06");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue06Label");
        eiColumn.setDescName("枚举值06显示文本");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enumValue06Grade");
        eiColumn.setDescName("枚举值06的报警级别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataFormat");
        eiColumn.setDescName("数据格式");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tmsar01Id");
        eiColumn.setDescName("报警规则主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tParamClassifyId");
        eiColumn.setDescName("参数所属分类主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("emailEnableStatus");
        eiColumn.setDescName("电子邮件发送启用状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("smsEnableStatus");
        eiColumn.setDescName("短信息发送启用状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("limitTimeValue");
        eiColumn.setDescName("限止时间段");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("limitTimeUnit");
        eiColumn.setDescName("限止时间段单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("limitRepeats");
        eiColumn.setDescName("限止次数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("limitRepeatsEnable");
        eiColumn.setDescName("报警短信限止启用状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tmsdc01Id");
        eiColumn.setDescName("所属设备主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("groupId");
        eiColumn.setDescName("院区标识");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tmsarName");
        eiColumn.setDescName("关联报警规则名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tmsdcName");
        eiColumn.setDescName("关联设备名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isWriteLog");
        eiColumn.setDescName("是否记录日志");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deviceName");
        eiColumn.setDescName("设备名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rTime");
        eiColumn.setDescName("采集时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("significant_bit");
        eiColumn.setDescName("");
        eiMetadata.addMeta(eiColumn);
    }

    public MSPL01() {
        initMetaData();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getIsSync() {
        return isSync;
    }

    public void setIsSync(String isSync) {
        this.isSync = isSync;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrueValueLabel() {
        return trueValueLabel;
    }

    public void setTrueValueLabel(String trueValueLabel) {
        this.trueValueLabel = trueValueLabel;
    }

    public String getFalseValueLabel() {
        return falseValueLabel;
    }

    public void setFalseValueLabel(String falseValueLabel) {
        this.falseValueLabel = falseValueLabel;
    }

    public String getAlarmValue() {
        return alarmValue;
    }

    public void setAlarmValue(String alarmValue) {
        this.alarmValue = alarmValue;
    }

    public String getrValue() {
        return rValue;
    }

    public void setrValue(String rValue) {
        this.rValue = rValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }


    public String getAlarmEnableStatus() {
        return alarmEnableStatus;
    }

    public void setAlarmEnableStatus(String alarmEnableStatus) {
        this.alarmEnableStatus = alarmEnableStatus;
    }

    public String getParamEnableStatus() {
        return paramEnableStatus;
    }

    public void setParamEnableStatus(String paramEnableStatus) {
        this.paramEnableStatus = paramEnableStatus;
    }

    public String getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(String deadTime) {
        this.deadTime = deadTime;
    }

    public String getEnumValue01() {
        return enumValue01;
    }

    public void setEnumValue01(String enumValue01) {
        this.enumValue01 = enumValue01;
    }

    public String getEnumValue01Label() {
        return enumValue01Label;
    }

    public void setEnumValue01Label(String enumValue01Label) {
        this.enumValue01Label = enumValue01Label;
    }

    public String getEnumValue01Grade() {
        return enumValue01Grade;
    }

    public void setEnumValue01Grade(String enumValue01Grade) {
        this.enumValue01Grade = enumValue01Grade;
    }

    public String getEnumValue02() {
        return enumValue02;
    }

    public void setEnumValue02(String enumValue02) {
        this.enumValue02 = enumValue02;
    }

    public String getEnumValue02Label() {
        return enumValue02Label;
    }

    public void setEnumValue02Label(String enumValue02Label) {
        this.enumValue02Label = enumValue02Label;
    }

    public String getEnumValue02Grade() {
        return enumValue02Grade;
    }

    public void setEnumValue02Grade(String enumValue02Grade) {
        this.enumValue02Grade = enumValue02Grade;
    }

    public String getEnumValue03() {
        return enumValue03;
    }

    public void setEnumValue03(String enumValue03) {
        this.enumValue03 = enumValue03;
    }

    public String getEnumValue03Label() {
        return enumValue03Label;
    }

    public void setEnumValue03Label(String enumValue03Label) {
        this.enumValue03Label = enumValue03Label;
    }

    public String getEnumValue03Grade() {
        return enumValue03Grade;
    }

    public void setEnumValue03Grade(String enumValue03Grade) {
        this.enumValue03Grade = enumValue03Grade;
    }

    public String getEnumValue04() {
        return enumValue04;
    }

    public void setEnumValue04(String enumValue04) {
        this.enumValue04 = enumValue04;
    }

    public String getEnumValue04Label() {
        return enumValue04Label;
    }

    public void setEnumValue04Label(String enumValue04Label) {
        this.enumValue04Label = enumValue04Label;
    }

    public String getEnumValue04Grade() {
        return enumValue04Grade;
    }

    public void setEnumValue04Grade(String enumValue04Grade) {
        this.enumValue04Grade = enumValue04Grade;
    }

    public String getEnumValue05() {
        return enumValue05;
    }

    public void setEnumValue05(String enumValue05) {
        this.enumValue05 = enumValue05;
    }

    public String getEnumValue05Label() {
        return enumValue05Label;
    }

    public void setEnumValue05Label(String enumValue05Label) {
        this.enumValue05Label = enumValue05Label;
    }

    public String getEnumValue05Grade() {
        return enumValue05Grade;
    }

    public void setEnumValue05Grade(String enumValue05Grade) {
        this.enumValue05Grade = enumValue05Grade;
    }

    public String getEnumValue06() {
        return enumValue06;
    }

    public void setEnumValue06(String enumValue06) {
        this.enumValue06 = enumValue06;
    }

    public String getEnumValue06Label() {
        return enumValue06Label;
    }

    public void setEnumValue06Label(String enumValue06Label) {
        this.enumValue06Label = enumValue06Label;
    }

    public String getEnumValue06Grade() {
        return enumValue06Grade;
    }

    public void setEnumValue06Grade(String enumValue06Grade) {
        this.enumValue06Grade = enumValue06Grade;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public String getTmsar01Id() {
        return tmsar01Id;
    }

    public void setTmsar01Id(String tmsar01Id) {
        this.tmsar01Id = tmsar01Id;
    }

    public String gettParamClassifyId() {
        return tParamClassifyId;
    }

    public void settParamClassifyId(String tParamClassifyId) {
        this.tParamClassifyId = tParamClassifyId;
    }

    public String getEmailEnableStatus() {
        return emailEnableStatus;
    }

    public void setEmailEnableStatus(String emailEnableStatus) {
        this.emailEnableStatus = emailEnableStatus;
    }

    public String getSmsEnableStatus() {
        return smsEnableStatus;
    }

    public void setSmsEnableStatus(String smsEnableStatus) {
        this.smsEnableStatus = smsEnableStatus;
    }

    public String getLimitTimeValue() {
        return limitTimeValue;
    }

    public void setLimitTimeValue(String limitTimeValue) {
        this.limitTimeValue = limitTimeValue;
    }

    public String getLimitTimeUnit() {
        return limitTimeUnit;
    }

    public void setLimitTimeUnit(String limitTimeUnit) {
        this.limitTimeUnit = limitTimeUnit;
    }

    public String getLimitRepeats() {
        return limitRepeats;
    }

    public void setLimitRepeats(String limitRepeats) {
        this.limitRepeats = limitRepeats;
    }

    public String getLimitRepeatsEnable() {
        return limitRepeatsEnable;
    }

    public void setLimitRepeatsEnable(String limitRepeatsEnable) {
        this.limitRepeatsEnable = limitRepeatsEnable;
    }

    public String getTmsdc01Id() {
        return tmsdc01Id;
    }

    public void setTmsdc01Id(String tmsdc01Id) {
        this.tmsdc01Id = tmsdc01Id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTmsarName() {
        return tmsarName;
    }

    public void setTmsarName(String tmsarName) {
        this.tmsarName = tmsarName;
    }

    public String getTmsdcName() {
        return tmsdcName;
    }

    public void setTmsdcName(String tmsdcName) {
        this.tmsdcName = tmsdcName;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getIsWriteLog() {
        return isWriteLog;
    }

    public void setIsWriteLog(String isWriteLog) {
        this.isWriteLog = isWriteLog;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getrTime() {
        return rTime;
    }

    public void setrTime(String rTime) {
        this.rTime = rTime;
    }

    public String getSignificant_bit() {
        return significant_bit;
    }

    public void setSignificant_bit(String significant_bit) {
        this.significant_bit = significant_bit;
    }

    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("type")), type));
        setTag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("tag")), tag));
        setIsSync(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isSync")), isSync));
        setName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("name")), name));
        setDescription(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("description")), description));
        setTrueValueLabel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("trueValueLabel")), trueValueLabel));
        setFalseValueLabel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("falseValueLabel")), falseValueLabel));
        setAlarmValue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("alarmValue")), alarmValue));
        setrValue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rValue")), rValue));
        setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
        setUnitName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unitName")), unitName));
        setDimension(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dimension")), dimension));
        setDimensionName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dimensionName")), dimensionName));
        setAlarmEnableStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("alarmEnableStatus")), alarmEnableStatus));
        setParamEnableStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramEnableStatus")), paramEnableStatus));
        setDeadTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deadTime")), deadTime));
        setEnumValue01(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue01")), enumValue01));
        setEnumValue01Label(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue01Label")), enumValue01Label));
        setEnumValue01Grade(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue01Grade")), enumValue01Grade));
        setEnumValue02(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue02")), enumValue02));
        setEnumValue02Label(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue02Label")), enumValue02Label));
        setEnumValue02Grade(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue02Grade")), enumValue02Grade));
        setEnumValue03(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue03")), enumValue03));
        setEnumValue03Label(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue03Label")), enumValue03Label));
        setEnumValue03Grade(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue03Grade")), enumValue03Grade));
        setEnumValue04(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue04")), enumValue04));
        setEnumValue04Label(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue04Label")), enumValue04Label));
        setEnumValue04Grade(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue04Grade")), enumValue04Grade));
        setEnumValue05(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue05")), enumValue05));
        setEnumValue05Label(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue05Label")), enumValue05Label));
        setEnumValue05Grade(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue05Grade")), enumValue05Grade));
        setEnumValue06(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue06")), enumValue06));
        setEnumValue06Label(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue06Label")), enumValue06Label));
        setEnumValue06Grade(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enumValue06Grade")), enumValue06Grade));
        setDataFormat(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataFormat")), dataFormat));
        setTmsar01Id(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("tmsar01Id")), tmsar01Id));
        settParamClassifyId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("tParamClassifyId")), tParamClassifyId));
        setEmailEnableStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("emailEnableStatus")), emailEnableStatus));
        setSmsEnableStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("smsEnableStatus")), smsEnableStatus));
        setLimitTimeValue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("limitTimeValue")), limitTimeValue));
        setLimitTimeUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("limitTimeUnit")), limitTimeUnit));
        setLimitRepeats(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("limitRepeats")), limitRepeats));
        setLimitRepeatsEnable(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("limitRepeatsEnable")), limitRepeatsEnable));
        setTmsdc01Id(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("tmsdc01Id")), tmsdc01Id));
        setGroupId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("groupId")), groupId));
        setTmsarName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("tmsarName")), tmsarName));
        setTmsdcName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("tmsdcName")), tmsdcName));
        setIsWriteLog(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isWriteLog")), isWriteLog));
        setDeviceName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deviceName")), deviceName));
        setrTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rTime")), rTime));
        setSignificant_bit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("significant_bit")), significant_bit));

    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("type", StringUtils.toString(type, eiMetadata.getMeta("type")));
        map.put("tag", StringUtils.toString(tag, eiMetadata.getMeta("tag")));
        map.put("isSync", StringUtils.toString(isSync, eiMetadata.getMeta("isSync")));
        map.put("name", StringUtils.toString(name, eiMetadata.getMeta("name")));
        map.put("description", StringUtils.toString(description, eiMetadata.getMeta("description")));
        map.put("trueValueLabel", StringUtils.toString(trueValueLabel, eiMetadata.getMeta("trueValueLabel")));
        map.put("falseValueLabel", StringUtils.toString(falseValueLabel, eiMetadata.getMeta("falseValueLabel")));
        map.put("alarmValue", StringUtils.toString(alarmValue, eiMetadata.getMeta("alarmValue")));
        map.put("rValue", StringUtils.toString(rValue, eiMetadata.getMeta("rValue")));
        map.put("unit", StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("unitName", StringUtils.toString(unitName, eiMetadata.getMeta("unitName")));
        map.put("dimension", StringUtils.toString(dimension, eiMetadata.getMeta("dimension")));
        map.put("dimensionName", StringUtils.toString(dimensionName, eiMetadata.getMeta("dimensionName")));
        map.put("alarmEnableStatus", StringUtils.toString(alarmEnableStatus, eiMetadata.getMeta("alarmEnableStatus")));
        map.put("paramEnableStatus", StringUtils.toString(paramEnableStatus, eiMetadata.getMeta("paramEnableStatus")));
        map.put("deadTime", StringUtils.toString(deadTime, eiMetadata.getMeta("deadTime")));
        map.put("enumValue01", StringUtils.toString(enumValue01, eiMetadata.getMeta("enumValue01")));
        map.put("enumValue01Label", StringUtils.toString(enumValue01Label, eiMetadata.getMeta("enumValue01Label")));
        map.put("enumValue01Grade", StringUtils.toString(enumValue01Grade, eiMetadata.getMeta("enumValue01Grade")));
        map.put("enumValue02", StringUtils.toString(enumValue02, eiMetadata.getMeta("enumValue02")));
        map.put("enumValue02Label", StringUtils.toString(enumValue02Label, eiMetadata.getMeta("enumValue02Label")));
        map.put("enumValue02Grade", StringUtils.toString(enumValue02Grade, eiMetadata.getMeta("enumValue02Grade")));
        map.put("enumValue03", StringUtils.toString(enumValue03, eiMetadata.getMeta("enumValue03")));
        map.put("enumValue03Label", StringUtils.toString(enumValue02Label, eiMetadata.getMeta("enumValue03Label")));
        map.put("enumValue03Grade", StringUtils.toString(enumValue02Grade, eiMetadata.getMeta("enumValue03Grade")));
        map.put("enumValue04", StringUtils.toString(enumValue04, eiMetadata.getMeta("enumValue04")));
        map.put("enumValue04Label", StringUtils.toString(enumValue04Label, eiMetadata.getMeta("enumValue04Label")));
        map.put("enumValue04Grade", StringUtils.toString(enumValue04Grade, eiMetadata.getMeta("enumValue04Grade")));
        map.put("enumValue05", StringUtils.toString(enumValue05, eiMetadata.getMeta("enumValue05")));
        map.put("enumValue05Label", StringUtils.toString(enumValue05Label, eiMetadata.getMeta("enumValue05Label")));
        map.put("enumValue05Grade", StringUtils.toString(enumValue05Grade, eiMetadata.getMeta("enumValue05Grade")));
        map.put("enumValue06", StringUtils.toString(enumValue06, eiMetadata.getMeta("enumValue06")));
        map.put("enumValue06Label", StringUtils.toString(enumValue06Label, eiMetadata.getMeta("enumValue06Label")));
        map.put("enumValue06Grade", StringUtils.toString(enumValue06Grade, eiMetadata.getMeta("enumValue06Grade")));
        map.put("dataFormat", StringUtils.toString(dataFormat, eiMetadata.getMeta("dataFormat")));
        map.put("tmsar01Id", StringUtils.toString(tmsar01Id, eiMetadata.getMeta("tmsar01Id")));
        map.put("tParamClassifyId", StringUtils.toString(tParamClassifyId, eiMetadata.getMeta("tParamClassifyId")));
        map.put("emailEnableStatus", StringUtils.toString(emailEnableStatus, eiMetadata.getMeta("emailEnableStatus")));
        map.put("smsEnableStatus", StringUtils.toString(smsEnableStatus, eiMetadata.getMeta("smsEnableStatus")));
        map.put("limitTimeValue", StringUtils.toString(limitTimeValue, eiMetadata.getMeta("limitTimeValue")));
        map.put("limitTimeUnit", StringUtils.toString(limitTimeUnit, eiMetadata.getMeta("limitTimeUnit")));
        map.put("limitRepeats", StringUtils.toString(limitRepeats, eiMetadata.getMeta("limitRepeats")));
        map.put("limitRepeatsEnable", StringUtils.toString(limitRepeatsEnable, eiMetadata.getMeta("limitRepeatsEnable")));
        map.put("tmsdc01Id", StringUtils.toString(tmsdc01Id, eiMetadata.getMeta("tmsdc01Id")));
        map.put("groupId", StringUtils.toString(groupId, eiMetadata.getMeta("groupId")));
        map.put("tmsarName", StringUtils.toString(tmsarName, eiMetadata.getMeta("tmsarName")));
        map.put("tmsdcName", StringUtils.toString(tmsdcName, eiMetadata.getMeta("tmsdcName")));
        map.put("isWriteLog", StringUtils.toString(isWriteLog, eiMetadata.getMeta("isWriteLog")));
        map.put("deviceName", StringUtils.toString(deviceName, eiMetadata.getMeta("deviceName")));
        map.put("rTime", StringUtils.toString(rTime, eiMetadata.getMeta("rTime")));
        map.put("significant_bit", StringUtils.toString(significant_bit, eiMetadata.getMeta("significant_bit")));
        return map;
    }
}
