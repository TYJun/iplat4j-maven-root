/**
* Generate time : 2021-07-26 10:27:05
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.hi.common.util;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* MtPz
* 
*/
public class HiPz extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String configCode = " ";		/* 配置项编码*/
    private String configName = " ";		/* 配置项名称*/
    private String configValueRedio = " ";		/* 配置项值（单选框的值）*/
    private String configValueText = " ";		/* 配置项值（多选框、输入框的值）*/
    private String dataGroupCode = " ";		/* 账套*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("configCode");
        eiColumn.setDescName("配置项编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("configName");
        eiColumn.setDescName("配置项名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("configValueRedio");
        eiColumn.setDescName("配置项值（单选框的值）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("configValueText");
        eiColumn.setDescName("配置项值（多选框、输入框的值）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public HiPz() {
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
     * get the configCode - 配置项编码
     * @return the configCode
     */
    public String getConfigCode() {
        return this.configCode;
    }

    /**
     * set the configCode - 配置项编码
     */
    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    /**
     * get the configName - 配置项名称
     * @return the configName
     */
    public String getConfigName() {
        return this.configName;
    }

    /**
     * set the configName - 配置项名称
     */
    public void setConfigName(String configName) {
        this.configName = configName;
    }

    /**
     * get the configValueRedio - 配置项值（单选框的值）
     * @return the configValueRedio
     */
    public String getConfigValueRedio() {
        return this.configValueRedio;
    }

    /**
     * set the configValueRedio - 配置项值（单选框的值）
     */
    public void setConfigValueRedio(String configValueRedio) {
        this.configValueRedio = configValueRedio;
    }

    /**
     * get the configValueText - 配置项值（多选框、输入框的值）
     * @return the configValueText
     */
    public String getConfigValueText() {
        return this.configValueText;
    }

    /**
     * set the configValueText - 配置项值（多选框、输入框的值）
     */
    public void setConfigValueText(String configValueText) {
        this.configValueText = configValueText;
    }

    /**
     * get the dataGroupCode - 账套
     * @return the dataGroupCode
     */
    public String getDataGroupCode() {
        return this.dataGroupCode;
    }

    /**
     * set the dataGroupCode - 账套
     */
    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

    /**
     * get the value from Map
     */
    @SuppressWarnings("all")
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setConfigCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("configCode")), configCode));
        setConfigName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("configName")), configName));
        setConfigValueRedio(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("configValueRedio")), configValueRedio));
        setConfigValueText(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("configValueText")), configValueText));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
    }

    /**
     * set the value to Map
     */
    @SuppressWarnings("all")
	public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("configCode",StringUtils.toString(configCode, eiMetadata.getMeta("configCode")));
        map.put("configName",StringUtils.toString(configName, eiMetadata.getMeta("configName")));
        map.put("configValueRedio",StringUtils.toString(configValueRedio, eiMetadata.getMeta("configValueRedio")));
        map.put("configValueText",StringUtils.toString(configValueText, eiMetadata.getMeta("configValueText")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        return map;
    }
}