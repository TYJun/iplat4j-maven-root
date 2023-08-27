/**
* Generate time : 2021-11-11 14:19:00
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ps.pc.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* PSPCTorderEval
* 
*/
public class PSPCTorderEval extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String menuNum = " ";		/* 菜品编码*/
    private String evalTypeName = " ";		/* 评价类目名称*/
    private String evalType = " ";		/* 评价类目编码*/
    private Integer evalLevel = new Integer(0);		/* 评价等级(0-10)*/
    private String evalContent = " ";		/* 评价内容*/
    private String evalUserName = " ";		/* 评价用户名*/
    private String evalTime = " ";		/* 评价时间*/
    private String sendDate = " ";		/* 送餐时间*/
    private String canteenNum = " ";		/* 食堂编码*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuNum");
        eiColumn.setDescName("菜品编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalTypeName");
        eiColumn.setDescName("评价类目名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalType");
        eiColumn.setDescName("评价类目编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalLevel");
        eiColumn.setDescName("评价等级(0-10)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalContent");
        eiColumn.setDescName("评价内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalUserName");
        eiColumn.setDescName("评价用户名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalTime");
        eiColumn.setDescName("评价时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sendDate");
        eiColumn.setDescName("送餐时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName("食堂编码");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PSPCTorderEval() {
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
     * get the menuNum - 菜品编码
     * @return the menuNum
     */
    public String getMenuNum() {
        return this.menuNum;
    }

    /**
     * set the menuNum - 菜品编码
     */
    public void setMenuNum(String menuNum) {
        this.menuNum = menuNum;
    }

    /**
     * get the evalTypeName - 评价类目名称
     * @return the evalTypeName
     */
    public String getEvalTypeName() {
        return this.evalTypeName;
    }

    /**
     * set the evalTypeName - 评价类目名称
     */
    public void setEvalTypeName(String evalTypeName) {
        this.evalTypeName = evalTypeName;
    }

    /**
     * get the evalType - 评价类目编码
     * @return the evalType
     */
    public String getEvalType() {
        return this.evalType;
    }

    /**
     * set the evalType - 评价类目编码
     */
    public void setEvalType(String evalType) {
        this.evalType = evalType;
    }

    /**
     * get the evalLevel - 评价等级(0-10)
     * @return the evalLevel
     */
    public Integer getEvalLevel() {
        return this.evalLevel;
    }

    /**
     * set the evalLevel - 评价等级(0-10)
     */
    public void setEvalLevel(Integer evalLevel) {
        this.evalLevel = evalLevel;
    }

    /**
     * get the evalContent - 评价内容
     * @return the evalContent
     */
    public String getEvalContent() {
        return this.evalContent;
    }

    /**
     * set the evalContent - 评价内容
     */
    public void setEvalContent(String evalContent) {
        this.evalContent = evalContent;
    }

    /**
     * get the evalUserName - 评价用户名
     * @return the evalUserName
     */
    public String getEvalUserName() {
        return this.evalUserName;
    }

    /**
     * set the evalUserName - 评价用户名
     */
    public void setEvalUserName(String evalUserName) {
        this.evalUserName = evalUserName;
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
     * get the sendDate - 送餐时间
     * @return the sendDate
     */
    public String getSendDate() {
        return this.sendDate;
    }

    /**
     * set the sendDate - 送餐时间
     */
    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    /**
     * get the canteenNum - 食堂编码
     * @return the canteenNum
     */
    public String getCanteenNum() {
        return this.canteenNum;
    }

    /**
     * set the canteenNum - 食堂编码
     */
    public void setCanteenNum(String canteenNum) {
        this.canteenNum = canteenNum;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setMenuNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuNum")), menuNum));
        setEvalTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalTypeName")), evalTypeName));
        setEvalType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalType")), evalType));
        setEvalLevel(NumberUtils.toInteger(StringUtils.toString(map.get("evalLevel")), evalLevel));
        setEvalContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalContent")), evalContent));
        setEvalUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalUserName")), evalUserName));
        setEvalTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalTime")), evalTime));
        setSendDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sendDate")), sendDate));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("menuNum",StringUtils.toString(menuNum, eiMetadata.getMeta("menuNum")));
        map.put("evalTypeName",StringUtils.toString(evalTypeName, eiMetadata.getMeta("evalTypeName")));
        map.put("evalType",StringUtils.toString(evalType, eiMetadata.getMeta("evalType")));
        map.put("evalLevel",StringUtils.toString(evalLevel, eiMetadata.getMeta("evalLevel")));
        map.put("evalContent",StringUtils.toString(evalContent, eiMetadata.getMeta("evalContent")));
        map.put("evalUserName",StringUtils.toString(evalUserName, eiMetadata.getMeta("evalUserName")));
        map.put("evalTime",StringUtils.toString(evalTime, eiMetadata.getMeta("evalTime")));
        map.put("sendDate",StringUtils.toString(sendDate, eiMetadata.getMeta("sendDate")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        return map;
    }
}