/**
* Generate time : 2022-03-17 23:39:35
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.sq.common;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* SqSmsConfig
* 
*/
public class SqSmsConfig extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private Integer id = new Integer(0);		/* 满意度短信主键*/
    private String configType = " ";		/* 短信配置类型*/
    private String configTypeName = " ";		/* 短信配置名称*/
    private String smsTemp = " ";		/* 短信配置模板*/
    private String day = " ";		/* 天*/
    private String time = " ";		/* 小时*/
    private Integer isRunning = new Integer(0);		/* 是否执行,0-未执行，1-执行*/
    private String smsRecvCode = " ";		/* 角色编码*/
    private String datagroupCode = " ";		/* 账套*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("满意度短信主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("configType");
        eiColumn.setDescName("短信配置类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("configTypeName");
        eiColumn.setDescName("短信配置名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("smsTemp");
        eiColumn.setDescName("短信配置模板");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("day");
        eiColumn.setDescName("天");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("time");
        eiColumn.setDescName("小时");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isRunning");
        eiColumn.setDescName("是否执行,0-未执行，1-执行");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("smsRecvCode");
        eiColumn.setDescName("角色编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("datagroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SqSmsConfig() {
        initMetaData();
    }

    /**
     * get the id - 满意度短信主键
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * set the id - 满意度短信主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * get the configType - 短信配置类型
     * @return the configType
     */
    public String getConfigType() {
        return this.configType;
    }

    /**
     * set the configType - 短信配置类型
     */
    public void setConfigType(String configType) {
        this.configType = configType;
    }

    /**
     * get the configTypeName - 短信配置名称
     * @return the configTypeName
     */
    public String getConfigTypeName() {
        return this.configTypeName;
    }

    /**
     * set the configTypeName - 短信配置名称
     */
    public void setConfigTypeName(String configTypeName) {
        this.configTypeName = configTypeName;
    }

    /**
     * get the smsTemp - 短信配置模板
     * @return the smsTemp
     */
    public String getSmsTemp() {
        return this.smsTemp;
    }

    /**
     * set the smsTemp - 短信配置模板
     */
    public void setSmsTemp(String smsTemp) {
        this.smsTemp = smsTemp;
    }

    /**
     * get the day - 天
     * @return the day
     */
    public String getDay() {
        return this.day;
    }

    /**
     * set the day - 天
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * get the time - 小时
     * @return the time
     */
    public String getTime() {
        return this.time;
    }

    /**
     * set the time - 小时
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * get the isRunning - 是否执行,0-未执行，1-执行
     * @return the isRunning
     */
    public Integer getIsRunning() {
        return this.isRunning;
    }

    /**
     * set the isRunning - 是否执行,0-未执行，1-执行
     */
    public void setIsRunning(Integer isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * get the smsRecvCode - 角色编码
     * @return the smsRecvCode
     */
    public String getSmsRecvCode() {
        return this.smsRecvCode;
    }

    /**
     * set the smsRecvCode - 角色编码
     */
    public void setSmsRecvCode(String smsRecvCode) {
        this.smsRecvCode = smsRecvCode;
    }

    /**
     * get the datagroupCode - 账套
     * @return the datagroupCode
     */
    public String getDatagroupCode() {
        return this.datagroupCode;
    }

    /**
     * set the datagroupCode - 账套
     */
    public void setDatagroupCode(String datagroupCode) {
        this.datagroupCode = datagroupCode;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(NumberUtils.toInteger(StringUtils.toString(map.get("id")), id));
        setConfigType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("configType")), configType));
        setConfigTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("configTypeName")), configTypeName));
        setSmsTemp(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("smsTemp")), smsTemp));
        setDay(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("day")), day));
        setTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("time")), time));
        setIsRunning(NumberUtils.toInteger(StringUtils.toString(map.get("isRunning")), isRunning));
        setSmsRecvCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("smsRecvCode")), smsRecvCode));
        setDatagroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datagroupCode")), datagroupCode));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("configType",StringUtils.toString(configType, eiMetadata.getMeta("configType")));
        map.put("configTypeName",StringUtils.toString(configTypeName, eiMetadata.getMeta("configTypeName")));
        map.put("smsTemp",StringUtils.toString(smsTemp, eiMetadata.getMeta("smsTemp")));
        map.put("day",StringUtils.toString(day, eiMetadata.getMeta("day")));
        map.put("time",StringUtils.toString(time, eiMetadata.getMeta("time")));
        map.put("isRunning",StringUtils.toString(isRunning, eiMetadata.getMeta("isRunning")));
        map.put("smsRecvCode",StringUtils.toString(smsRecvCode, eiMetadata.getMeta("smsRecvCode")));
        map.put("datagroupCode",StringUtils.toString(datagroupCode, eiMetadata.getMeta("datagroupCode")));
        return map;
    }
}