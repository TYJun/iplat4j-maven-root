/**
* Generate time : 2021-05-31 18:50:17
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.im.jz.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DfClassfyparam
* 
*/
public class ImClassfyparam extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String moduleId = " ";		/* 参数所属分类ID*/
    private String paramName = " ";		/* 参数名称*/
    private String paramKey = " ";		/* 参数key 规则：所属模块CODE-0001/9999(序列号)*/
    private String paramValue = " ";		/* 参数值*/
    private String paramUnit = " ";		/* 参数单位*/
    private String memo = " ";		/* 备注*/
    private String classifyName = " ";		/* 安装地点*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("moduleId");
        eiColumn.setDescName("参数所属分类ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramName");
        eiColumn.setDescName("参数名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramKey");
        eiColumn.setDescName("参数key 规则：所属模块CODE-0001/9999(序列号)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramValue");
        eiColumn.setDescName("参数值");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramUnit");
        eiColumn.setDescName("参数单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("memo");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("classifyName");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ImClassfyparam() {
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
     * get the moduleId - 参数所属分类ID
     * @return the moduleId
     */
    public String getModuleId() {
        return this.moduleId;
    }

    /**
     * set the moduleId - 参数所属分类ID
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * get the paramName - 参数名称
     * @return the paramName
     */
    public String getParamName() {
        return this.paramName;
    }

    /**
     * set the paramName - 参数名称
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * get the paramKey - 参数key 规则：所属模块CODE-0001/9999(序列号)
     * @return the paramKey
     */
    public String getParamKey() {
        return this.paramKey;
    }

    /**
     * set the paramKey - 参数key 规则：所属模块CODE-0001/9999(序列号)
     */
    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    /**
     * get the paramValue - 参数值
     * @return the paramValue
     */
    public String getParamValue() {
        return this.paramValue;
    }

    /**
     * set the paramValue - 参数值
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * get the paramUnit - 参数单位
     * @return the paramUnit
     */
    public String getParamUnit() {
        return this.paramUnit;
    }

    /**
     * set the paramUnit - 参数单位
     */
    public void setParamUnit(String paramUnit) {
        this.paramUnit = paramUnit;
    }

    /**
     * get the memo - 备注
     * @return the memo
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * set the memo - 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setModuleId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("moduleId")), moduleId));
        setParamName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramName")), paramName));
        setParamKey(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramKey")), paramKey));
        setParamValue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramValue")), paramValue));
        setParamUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramUnit")), paramUnit));
        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
        setClassifyName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyName")), classifyName));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("moduleId",StringUtils.toString(moduleId, eiMetadata.getMeta("moduleId")));
        map.put("paramName",StringUtils.toString(paramName, eiMetadata.getMeta("paramName")));
        map.put("paramKey",StringUtils.toString(paramKey, eiMetadata.getMeta("paramKey")));
        map.put("paramValue",StringUtils.toString(paramValue, eiMetadata.getMeta("paramValue")));
        map.put("paramUnit",StringUtils.toString(paramUnit, eiMetadata.getMeta("paramUnit")));
        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
        map.put("classifyName",StringUtils.toString(classifyName, eiMetadata.getMeta("classifyName")));
        return map;
    }
}