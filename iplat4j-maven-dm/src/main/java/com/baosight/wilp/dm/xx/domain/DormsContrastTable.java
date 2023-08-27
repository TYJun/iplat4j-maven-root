/**
* Generate time : 2022-05-23 10:48:23
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.xx.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DormsContrastTable
* 
*/
public class DormsContrastTable extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String dormProperties = " ";		/* 宿舍属性(学生宿舍/职工宿舍)*/
    private String employmentNature = " ";		/* 职工性质*/
    private String recCreator = " ";		/* 创建人工号*/
    private String recCreateName = " ";		/* 创建人姓名*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人工号*/
    private String recReviseName = " ";		/* 修改人姓名*/
    private String recReviseTime = " ";		/* 修改时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dormProperties");
        eiColumn.setDescName("宿舍属性(学生宿舍/职工宿舍)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("employmentNature");
        eiColumn.setDescName("职工性质");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateName");
        eiColumn.setDescName("创建人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseName");
        eiColumn.setDescName("修改人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DormsContrastTable() {
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
     * get the dormProperties - 宿舍属性(学生宿舍/职工宿舍)
     * @return the dormProperties
     */
    public String getDormProperties() {
        return this.dormProperties;
    }

    /**
     * set the dormProperties - 宿舍属性(学生宿舍/职工宿舍)
     */
    public void setDormProperties(String dormProperties) {
        this.dormProperties = dormProperties;
    }

    /**
     * get the employmentNature - 职工性质
     * @return the employmentNature
     */
    public String getEmploymentNature() {
        return this.employmentNature;
    }

    /**
     * set the employmentNature - 职工性质
     */
    public void setEmploymentNature(String employmentNature) {
        this.employmentNature = employmentNature;
    }

    /**
     * get the recCreator - 创建人工号
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建人工号
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateName - 创建人姓名
     * @return the recCreateName
     */
    public String getRecCreateName() {
        return this.recCreateName;
    }

    /**
     * set the recCreateName - 创建人姓名
     */
    public void setRecCreateName(String recCreateName) {
        this.recCreateName = recCreateName;
    }

    /**
     * get the recCreateTime - 创建时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevisor - 修改人工号
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 修改人工号
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseName - 修改人姓名
     * @return the recReviseName
     */
    public String getRecReviseName() {
        return this.recReviseName;
    }

    /**
     * set the recReviseName - 修改人姓名
     */
    public void setRecReviseName(String recReviseName) {
        this.recReviseName = recReviseName;
    }

    /**
     * get the recReviseTime - 修改时间
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setDormProperties(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dormProperties")), dormProperties));
        setEmploymentNature(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("employmentNature")), employmentNature));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateName")), recCreateName));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseName")), recReviseName));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("dormProperties",StringUtils.toString(dormProperties, eiMetadata.getMeta("dormProperties")));
        map.put("employmentNature",StringUtils.toString(employmentNature, eiMetadata.getMeta("employmentNature")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateName",StringUtils.toString(recCreateName, eiMetadata.getMeta("recCreateName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseName",StringUtils.toString(recReviseName, eiMetadata.getMeta("recReviseName")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        return map;
    }
}