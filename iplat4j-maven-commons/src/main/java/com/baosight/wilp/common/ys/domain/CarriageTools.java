/**
* Generate time : 2021-02-09 9:14:27
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.ys.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CarriageTools
* 
*/
public class CarriageTools extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 运送工具主键（UUID）*/
    private String toolNo = " ";		/* 工具编号*/
    private String toolName = " ";		/* 工具名称*/
    private String toolType = " ";		/* 工具类型*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("运送工具主键（UUID）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("toolNo");
        eiColumn.setDescName("工具编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("toolName");
        eiColumn.setDescName("工具名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("toolType");
        eiColumn.setDescName("工具类型");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CarriageTools() {
        initMetaData();
    }

    /**
     * get the id - 运送工具主键（UUID）
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 运送工具主键（UUID）
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the toolNo - 工具编号
     * @return the toolNo
     */
    public String getToolNo() {
        return this.toolNo;
    }

    /**
     * set the toolNo - 工具编号
     */
    public void setToolNo(String toolNo) {
        this.toolNo = toolNo;
    }

    /**
     * get the toolName - 工具名称
     * @return the toolName
     */
    public String getToolName() {
        return this.toolName;
    }

    /**
     * set the toolName - 工具名称
     */
    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    /**
     * get the toolType - 工具类型
     * @return the toolType
     */
    public String getToolType() {
        return this.toolType;
    }

    /**
     * set the toolType - 工具类型
     */
    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setToolNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("toolNo")), toolNo));
        setToolName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("toolName")), toolName));
        setToolType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("toolType")), toolType));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("toolNo",StringUtils.toString(toolNo, eiMetadata.getMeta("toolNo")));
        map.put("toolName",StringUtils.toString(toolName, eiMetadata.getMeta("toolName")));
        map.put("toolType",StringUtils.toString(toolType, eiMetadata.getMeta("toolType")));
        return map;
    }
}