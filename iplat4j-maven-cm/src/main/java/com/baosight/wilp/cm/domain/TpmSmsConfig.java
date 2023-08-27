/**
* Generate time : 2021-02-22 14:01:41
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 项目短信配置表
 * 
 * @Title: TpmSmsConfig.java
 * @ClassName: TpmSmsConfig
 * @Package：com.baosight.wilp.pm.domain
 * @author: zhangjiaqian
 * @date: 2021年8月30日 下午6:22:52
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@SuppressWarnings("all")
public class TpmSmsConfig extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String configType = " ";		/* 配置类型*/
    private String configTypeName = " ";		/* 配置类型名称*/
    private String smsTemp = " ";		/* 短信模板*/
    private String statusCode = " ";		/* 状态*/
    private Integer lateDays = new Integer(0);		/* 超期天数*/
    private String isRuning = " ";		/* 是否启用: 1-启用，0-未启用*/
    private String smsRecvCode = " ";		/* 短信接收人代码*/
    private String dataGroupCode = " ";		/* 账套*/
    private String time = " ";		/* 几点发送*/
    private String time1 = " ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
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

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lateDays");
        eiColumn.setDescName("超期天数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isRuning");
        eiColumn.setDescName("是否启用: 1-启用，0-未启用");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("smsRecvCode");
        eiColumn.setDescName("短信接收人代码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("time");
        eiColumn.setDescName("几点发送");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("time1");
        eiColumn.setDescName("几点发送");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public TpmSmsConfig() {
        initMetaData();
    }

    /**
     * get the id 
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id 
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
     * get the statusCode - 状态
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the lateDays - 超期天数
     * @return the lateDays
     */
    public Integer getLateDays() {
        return this.lateDays;
    }

    /**
     * set the lateDays - 超期天数
     */
    public void setLateDays(Integer lateDays) {
        this.lateDays = lateDays;
    }

    /**
     * get the isRuning - 是否启用: 1-启用，0-未启用
     * @return the isRuning
     */
    public String getIsRuning() {
        return this.isRuning;
    }

    /**
     * set the isRuning - 是否启用: 1-启用，0-未启用
     */
    public void setIsRuning(String isRuning) {
        this.isRuning = isRuning;
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
    
    public String getDataGroupCode() {
  		return dataGroupCode;
  	}

  	public void setDataGroupCode(String dataGroupCode) {
  		this.dataGroupCode = dataGroupCode;
  	}

    /**
     * set the smsRecvCode - 几点发送
     */
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setConfigType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("configType")), configType));
        setConfigTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("configTypeName")), configTypeName));
        setSmsTemp(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("smsTemp")), smsTemp));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setLateDays(NumberUtils.toInteger(StringUtils.toString(map.get("lateDays")), lateDays));
        setIsRuning(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isRuning")), isRuning));
        setSmsRecvCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("smsRecvCode")), smsRecvCode));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("time")), time));
        setTime1(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("time1")), time1));
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
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("lateDays",StringUtils.toString(lateDays, eiMetadata.getMeta("lateDays")));
        map.put("isRuning",StringUtils.toString(isRuning, eiMetadata.getMeta("isRuning")));
        map.put("smsRecvCode",StringUtils.toString(smsRecvCode, eiMetadata.getMeta("smsRecvCode")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("time",StringUtils.toString(time, eiMetadata.getMeta("time")));
        map.put("time1",StringUtils.toString(time1, eiMetadata.getMeta("time1")));
        return map;
    }
}