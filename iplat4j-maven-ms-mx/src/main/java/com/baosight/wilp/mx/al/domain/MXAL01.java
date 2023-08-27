package com.baosight.wilp.mx.al.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wzy
 * @title: T_Alarm_Log
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @date 2021/8/129:39
 */
public class MXAL01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id =" ";                                 //id
    private String area_name = " ";                        //区域名称
    private String device_name = " ";                     //设备名称
    private String item = " ";                           //告警项
    private String tag = " ";                           //tag
    private String start_time = " ";                    //告警开始时间
    private String value_ = " ";                       //告警值
    private String end_time =  " ";                   //告警结束（恢复）时间
    private String end_value =  " ";                 //结束值
    private String grade="";                        //告警等级
    private String description_="";                //告警说明
    private String process_start_user="";                   //处理开始操作人
    private String process_end_user="";                    //处理结束操作人
    private String process_start_time="";                 //处理开始时间
    private String process_end_time="";                  //处理结束时间
    private String group_id="";                         //院区标识
    private String t_area_classify_id="";               //区域分类
    private String classify_; //系统分类

    //VO 属性
    private String lower;
    private String lower_lower;
    private String upper;
    private String upper_upper;
    private String process_value;


    public MXAL01() {
        initMetaData();
    }

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("t_area_classify_id");
        eiColumn.setDescName("区域分类");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("area_name");
        eiColumn.setDescName("区域名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("device_name");
        eiColumn.setDescName("设备名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("item");
        eiColumn.setDescName("告警项");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tag");
        eiColumn.setDescName("tag");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("start_time");
        eiColumn.setDescName("告警开始时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("value_");
        eiColumn.setDescName("告警值");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("end_time");
        eiColumn.setDescName("告警结束（恢复）时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("end_value");
        eiColumn.setDescName("结束值");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("grade");
        eiColumn.setDescName("告警等级");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("description_");
        eiColumn.setDescName("告警说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("process_start_user");
        eiColumn.setDescName("处理开始操作人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("process_end_user");
        eiColumn.setDescName("处理结束操作人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("process_start_time");
        eiColumn.setDescName("处理开始时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("process_end_time");
        eiColumn.setDescName("处理结束时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classify_");
        eiColumn.setDescName("系统分类");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("group_id");
        eiColumn.setDescName("院区标识");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lower");
        eiColumn.setDescName("下限");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lower_lower");
        eiColumn.setDescName("下下限");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("upper");
        eiColumn.setDescName("上限");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("upper_upper");
        eiColumn.setDescName("上上限");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("process_value");
        eiColumn.setDescName("处理说明");
        eiMetadata.addMeta(eiColumn);


    }

    public String getT_area_classify_id() {
        return t_area_classify_id;
    }

    public void setT_area_classify_id(String t_area_classify_id) {
        this.t_area_classify_id = t_area_classify_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getValue_() {
        return value_;
    }

    public void setValue_(String value_) {
        this.value_ = value_;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getEnd_value() {
        return end_value;
    }

    public void setEnd_value(String end_value) {
        this.end_value = end_value;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDescription_() {
        return description_;
    }

    public void setDescription_(String description_) {
        this.description_ = description_;
    }

    public String getProcess_start_user() {
        return process_start_user;
    }

    public void setProcess_start_user(String process_start_user) {
        this.process_start_user = process_start_user;
    }

    public String getProcess_end_user() {
        return process_end_user;
    }

    public void setProcess_end_user(String process_end_user) {
        this.process_end_user = process_end_user;
    }

    public String getProcess_start_time() {
        return process_start_time;
    }

    public void setProcess_start_time(String process_start_time) {
        this.process_start_time = process_start_time;
    }

    public String getProcess_end_time() {
        return process_end_time;
    }

    public void setProcess_end_time(String process_end_time) {
        this.process_end_time = process_end_time;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getLower() {
        return lower;
    }

    public void setLower(String lower) {
        this.lower = lower;
    }

    public String getLower_lower() {
        return lower_lower;
    }

    public void setLower_lower(String lower_lower) {
        this.lower_lower = lower_lower;
    }

    public String getUpper() {
        return upper;
    }

    public void setUpper(String upper) {
        this.upper = upper;
    }

    public String getUpper_upper() {
        return upper_upper;
    }

    public void setUpper_upper(String upper_upper) {
        this.upper_upper = upper_upper;
    }

    public String getClassify_() {
        return classify_;
    }

    public void setClassify_(String classify_) {
        this.classify_ = classify_;
    }

    public String getProcess_value() {
        return process_value;
    }

    public void setProcess_value(String process_value) {
        this.process_value = process_value;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setArea_name(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("area_name")), area_name));
        setDevice_name(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("device_name")), device_name));
        setItem(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("item")), item));
        setTag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("tag")), tag));
        setStart_time(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("start_time")), start_time));
        setValue_(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("value_")), value_));
        setEnd_time(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("end_time")), end_time));
        setEnd_value(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("end_value")), end_value));
        setGrade(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("grade")), grade));
        setDescription_(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("description_")), description_));
        setProcess_start_user(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("process_start_user")), process_start_user));
        setProcess_end_user(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("process_end_user")), process_end_user));
        setProcess_start_time(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("process_start_time")), process_start_time));
        setProcess_end_time(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("process_end_time")), process_end_time));
        setGroup_id(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("group_id")), group_id));
        setT_area_classify_id(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("t_area_classify_id")), t_area_classify_id));
        setClassify_(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classify_")), classify_));

        setLower(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lower")), lower));
        setLower_lower(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lower_lower")), lower_lower));
        setUpper(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("upper")), upper));
        setUpper_upper(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("upper_upper")), upper_upper));
        setProcess_value(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("process_value")), process_value));
    }

    /**
     * 把所有字段全部放进map  然后页面映射
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("area_name",StringUtils.toString(area_name, eiMetadata.getMeta("area_name")));
        map.put("device_name",StringUtils.toString(device_name, eiMetadata.getMeta("device_name")));
        map.put("item",StringUtils.toString(item, eiMetadata.getMeta("item")));
        map.put("tag",StringUtils.toString(tag, eiMetadata.getMeta("tag")));
        map.put("start_time",StringUtils.toString(start_time, eiMetadata.getMeta("start_time")));
        map.put("value_",StringUtils.toString(value_, eiMetadata.getMeta("value_")));
        map.put("end_time",StringUtils.toString(end_time, eiMetadata.getMeta("end_time")));
        map.put("end_value",StringUtils.toString(end_value, eiMetadata.getMeta("end_value")));
        map.put("grade",StringUtils.toString(grade, eiMetadata.getMeta("grade")));
        map.put("description_",StringUtils.toString(description_, eiMetadata.getMeta("description_")));
        map.put("process_start_user",StringUtils.toString(process_start_user, eiMetadata.getMeta("process_start_user")));
        map.put("process_end_user",StringUtils.toString(process_end_user, eiMetadata.getMeta("process_end_user")));
        map.put("process_start_time",StringUtils.toString(process_start_time, eiMetadata.getMeta("process_start_time")));
        map.put("process_end_time",StringUtils.toString(process_end_time, eiMetadata.getMeta("process_end_time")));
        map.put("group_id",StringUtils.toString(group_id, eiMetadata.getMeta("group_id")));
        map.put("t_area_classify_id",StringUtils.toString(t_area_classify_id, eiMetadata.getMeta("t_area_classify_id")));
        map.put("classify_",StringUtils.toString(classify_, eiMetadata.getMeta("classify_")));

        map.put("lower",StringUtils.toString(lower, eiMetadata.getMeta("lower")));
        map.put("lower_lower",StringUtils.toString(lower_lower, eiMetadata.getMeta("lower_lower")));
        map.put("upper",StringUtils.toString(upper, eiMetadata.getMeta("upper")));
        map.put("upper_upper",StringUtils.toString(upper_upper, eiMetadata.getMeta("upper_upper")));
        map.put("process_value",StringUtils.toString(process_value, eiMetadata.getMeta("process_value")));
        return map;
    }

    private Date date; //日期

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
