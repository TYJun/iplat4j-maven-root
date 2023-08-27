package com.baosight.wilp.ms.common.domain;

import java.io.Serializable;

/**
 * 运转图展示数据模型对象
 *
 * @author: panlingfeng
 * @createDate: 2022/3/10 6:19 下午
 */
public class FlowchartVO implements Serializable {

    private String tag; // tag
    private String tagName; // tag点名称
    private String tagDescription; // tag点描述
    private String tagClassify; // tag点所属系统
    private String tagArea; // tag点位置
    private String tagRValue; // tag实时值
    private String tagVQ; // 质量戳
    private String tagTransferTime; // tag传输时间
    private String alarmScope; // 报警阈值范围
    private String alarmValue; // 报警值
    private String alarmGrade; // 报警等级
    private String alarmTime; // 报警时间

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public String getTagClassify() {
        return tagClassify;
    }

    public void setTagClassify(String tagClassify) {
        this.tagClassify = tagClassify;
    }

    public String getTagArea() {
        return tagArea;
    }

    public void setTagArea(String tagArea) {
        this.tagArea = tagArea;
    }

    public String getTagRValue() {
        return tagRValue;
    }

    public void setTagRValue(String tagRValue) {
        this.tagRValue = tagRValue;
    }

    public String getTagVQ() {
        return tagVQ;
    }

    public void setTagVQ(String tagVQ) {
        this.tagVQ = tagVQ;
    }

    public String getTagTransferTime() {
        return tagTransferTime;
    }

    public void setTagTransferTime(String tagTransferTime) {
        this.tagTransferTime = tagTransferTime;
    }

    public String getAlarmScope() {
        return alarmScope;
    }

    public void setAlarmScope(String alarmScope) {
        this.alarmScope = alarmScope;
    }

    public String getAlarmValue() {
        return alarmValue;
    }

    public void setAlarmValue(String alarmValue) {
        this.alarmValue = alarmValue;
    }

    public String getAlarmGrade() {
        return alarmGrade;
    }

    public void setAlarmGrade(String alarmGrade) {
        this.alarmGrade = alarmGrade;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }
}
