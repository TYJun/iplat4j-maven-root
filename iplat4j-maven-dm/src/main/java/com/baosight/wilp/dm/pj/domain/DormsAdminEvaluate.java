/**
* Generate time : 2022-05-04 23:33:13
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.pj.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DormsAdminEvaluate
* 
*/
public class DormsAdminEvaluate extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String manId = " ";		/* 住宿人员id*/
    private String manBehavior = " ";		/* 住宿人员行为等级评价(0-5)*/
    private String evalContent = " ";		/* 评价备注内容*/
    private String evalCreator = " ";		/* 评价人工号*/
    private String evalCreateName = " ";		/* 评价人姓名*/
    private String evalTime = " ";		/* 评价时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manId");
        eiColumn.setDescName("住宿人员id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manBehavior");
        eiColumn.setDescName("住宿人员行为等级评价(0-5)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalContent");
        eiColumn.setDescName("评价备注内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalCreator");
        eiColumn.setDescName("评价人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalCreateName");
        eiColumn.setDescName("评价人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalTime");
        eiColumn.setDescName("评价时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DormsAdminEvaluate() {
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
     * get the manId - 住宿人员id
     * @return the manId
     */
    public String getManId() {
        return this.manId;
    }

    /**
     * set the manId - 住宿人员id
     */
    public void setManId(String manId) {
        this.manId = manId;
    }

    /**
     * get the manBehavior - 住宿人员行为等级评价(0-5)
     * @return the manBehavior
     */
    public String getManBehavior() {
        return this.manBehavior;
    }

    /**
     * set the manBehavior - 住宿人员行为等级评价(0-5)
     */
    public void setManBehavior(String manBehavior) {
        this.manBehavior = manBehavior;
    }

    /**
     * get the evalContent - 评价备注内容
     * @return the evalContent
     */
    public String getEvalContent() {
        return this.evalContent;
    }

    /**
     * set the evalContent - 评价备注内容
     */
    public void setEvalContent(String evalContent) {
        this.evalContent = evalContent;
    }

    /**
     * get the evalCreator - 评价人工号
     * @return the evalCreator
     */
    public String getEvalCreator() {
        return this.evalCreator;
    }

    /**
     * set the evalCreator - 评价人工号
     */
    public void setEvalCreator(String evalCreator) {
        this.evalCreator = evalCreator;
    }

    /**
     * get the evalCreateName - 评价人姓名
     * @return the evalCreateName
     */
    public String getEvalCreateName() {
        return this.evalCreateName;
    }

    /**
     * set the evalCreateName - 评价人姓名
     */
    public void setEvalCreateName(String evalCreateName) {
        this.evalCreateName = evalCreateName;
    }

    /**
     * get the evalTime - 评价时间
     * @return the evalTime
     */
    public String getEvalTime() {
        return this.evalTime;
    }

    /**
     * set the evalTime - 评价时间
     */
    public void setEvalTime(String evalTime) {
        this.evalTime = evalTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setManId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manId")), manId));
        setManBehavior(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manBehavior")), manBehavior));
        setEvalContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalContent")), evalContent));
        setEvalCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalCreator")), evalCreator));
        setEvalCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalCreateName")), evalCreateName));
        setEvalTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalTime")), evalTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("manId",StringUtils.toString(manId, eiMetadata.getMeta("manId")));
        map.put("manBehavior",StringUtils.toString(manBehavior, eiMetadata.getMeta("manBehavior")));
        map.put("evalContent",StringUtils.toString(evalContent, eiMetadata.getMeta("evalContent")));
        map.put("evalCreator",StringUtils.toString(evalCreator, eiMetadata.getMeta("evalCreator")));
        map.put("evalCreateName",StringUtils.toString(evalCreateName, eiMetadata.getMeta("evalCreateName")));
        map.put("evalTime",StringUtils.toString(evalTime, eiMetadata.getMeta("evalTime")));
        return map;
    }
}