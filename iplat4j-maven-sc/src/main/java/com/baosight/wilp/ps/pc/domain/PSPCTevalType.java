/**
* Generate time : 2021-11-11 11:12:36
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ps.pc.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* PSPCTevalType
* 
*/
public class PSPCTevalType extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime ;		/* 创建时间*/
    private String recRevisor = " ";		/* 更新人*/
    private String recReviseTime ;		/* 更新时间*/
    private String evalType = " ";		/* 评价类型*/
    private String evalTypeName = " ";		/* 类型名称*/
    private String statusCode = " ";		/* 状态1/启用;0/停用*/
    private String canteenNum = " ";		/* 食堂编码*/
    private String canteenName = " ";		/* 食堂名称*/
    private Integer orderNo = new Integer(0);		/* 排序*/
    private int evalLevel;
    private String evalContent;
    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("更新人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("更新时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalType");
        eiColumn.setDescName("评价类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalTypeName");
        eiColumn.setDescName("类型名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态1/启用;0/停用");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName("食堂编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenName");
        eiColumn.setDescName("食堂名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderNo");
        eiColumn.setDescName("排序");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PSPCTevalType() {
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
     * get the recCreator - 创建人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建人
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
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
     * get the recRevisor - 更新人
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 更新人
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseTime - 更新时间
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 更新时间
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the evalType - 评价类型
     * @return the evalType
     */
    public String getEvalType() {
        return this.evalType;
    }

    /**
     * set the evalType - 评价类型
     */
    public void setEvalType(String evalType) {
        this.evalType = evalType;
    }

    /**
     * get the evalTypeName - 类型名称
     * @return the evalTypeName
     */
    public String getEvalTypeName() {
        return this.evalTypeName;
    }

    /**
     * set the evalTypeName - 类型名称
     */
    public void setEvalTypeName(String evalTypeName) {
        this.evalTypeName = evalTypeName;
    }

    /**
     * get the statusCode - 状态1/启用;0/停用
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态1/启用;0/停用
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
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
     * get the canteenName - 食堂名称
     * @return the canteenName
     */
    public String getCanteenName() {
        return this.canteenName;
    }

    /**
     * set the canteenName - 食堂名称
     */
    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    /**
     * get the orderNo - 排序
     * @return the orderNo
     */
    public Integer getOrderNo() {
        return this.orderNo;
    }

    /**
     * set the orderNo - 排序
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public int getEvalLevel() {
        return evalLevel;
    }

    public void setEvalLevel(int evalLevel) {
        this.evalLevel = evalLevel;
    }

    public String getEvalContent() {
        return evalContent;
    }

    public void setEvalContent(String evalContent) {
        this.evalContent = evalContent;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")),recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")),recReviseTime));
        setEvalType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalType")), evalType));
        setEvalTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("evalTypeName")), evalTypeName));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
        setCanteenName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenName")), canteenName));
        setOrderNo(NumberUtils.toInteger(StringUtils.toString(map.get("orderNo")), orderNo));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("evalType",StringUtils.toString(evalType, eiMetadata.getMeta("evalType")));
        map.put("evalTypeName",StringUtils.toString(evalTypeName, eiMetadata.getMeta("evalTypeName")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        map.put("canteenName",StringUtils.toString(canteenName, eiMetadata.getMeta("canteenName")));
        map.put("orderNo",StringUtils.toString(orderNo, eiMetadata.getMeta("orderNo")));
        return map;
    }
}