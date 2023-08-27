/**
* Generate time : 2022-03-16 23:14:27
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.dx.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DormsRoomMessagePz
* 
*/
public class DormsRoomMessagePz extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String configType = " ";		/* 配置类型*/
    private String configTypeName = " ";		/* 配置类型名称*/
    private String smsTemp = " ";		/* 短信模板*/
    private String isRunning = " ";		/* 是否启用: 1-启用，0-未启用*/
    private String smsRecvCode = " ";		/* 短信接收人代码*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("configType");
        eiColumn.setDescName("配置类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("configTypeName");
        eiColumn.setDescName("配置类型名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("smsTemp");
        eiColumn.setDescName("短信模板");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isRunning");
        eiColumn.setDescName("是否启用: 1-启用，0-未启用");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("smsRecvCode");
        eiColumn.setDescName("短信接收人代码");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DormsRoomMessagePz() {
        initMetaData();
    }

    /**
     * get the id - 主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the configType - 配置类型
     * @return the configType
     */
    public String getConfigType() {
        return this.configType;
    }

    /**
     * set the configType - 配置类型
     */
    public void setConfigType(String configType) {
        this.configType = configType;
    }

    /**
     * get the configTypeName - 配置类型名称
     * @return the configTypeName
     */
    public String getConfigTypeName() {
        return this.configTypeName;
    }

    /**
     * set the configTypeName - 配置类型名称
     */
    public void setConfigTypeName(String configTypeName) {
        this.configTypeName = configTypeName;
    }

    /**
     * get the smsTemp - 短信模板
     * @return the smsTemp
     */
    public String getSmsTemp() {
        return this.smsTemp;
    }

    /**
     * set the smsTemp - 短信模板
     */
    public void setSmsTemp(String smsTemp) {
        this.smsTemp = smsTemp;
    }

    /**
     * get the isRunning - 是否启用: 1-启用，0-未启用
     * @return the isRunning
     */
    public String getIsRunning() {
        return this.isRunning;
    }

    /**
     * set the isRunning - 是否启用: 1-启用，0-未启用
     */
    public void setIsRunning(String isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * get the smsRecvCode - 短信接收人代码
     * @return the smsRecvCode
     */
    public String getSmsRecvCode() {
        return this.smsRecvCode;
    }

    /**
     * set the smsRecvCode - 短信接收人代码
     */
    public void setSmsRecvCode(String smsRecvCode) {
        this.smsRecvCode = smsRecvCode;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setConfigType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("configType")), configType));
        setConfigTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("configTypeName")), configTypeName));
        setSmsTemp(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("smsTemp")), smsTemp));
        setIsRunning(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isRunning")), isRunning));
        setSmsRecvCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("smsRecvCode")), smsRecvCode));
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
        map.put("isRunning",StringUtils.toString(isRunning, eiMetadata.getMeta("isRunning")));
        map.put("smsRecvCode",StringUtils.toString(smsRecvCode, eiMetadata.getMeta("smsRecvCode")));
        return map;
    }
}