/**
* Generate time : 2021-02-23 13:32:26
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* SHGG01
* sc_notice
*/
public class SSBMStgg01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String noticeCode = " ";		
    private String noticeContent = " ";		
    private Date inputDate;	
    private Date effectiveDate;	
    private String isEffective = "0";		/* 是否生效 。0－不生效；1-生效；*/
    private String noticeTitle = " ";		
    private String recCreateTime = " ";		
    private String typeCode = " ";		/* 类型编码*/
    private String typeName = " ";		/* 类型名称*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("noticeCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("noticeContent");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("inputDate");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("effectiveDate");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isEffective");
        eiColumn.setDescName("是否生效 。0－不生效；1-生效；");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("noticeTitle");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typeCode");
        eiColumn.setDescName("类型编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typeName");
        eiColumn.setDescName("类型名称");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBMStgg01() {
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
     * get the noticeCode 
     * @return the noticeCode
     */
    public String getNoticeCode() {
        return this.noticeCode;
    }

    /**
     * set the noticeCode 
     */
    public void setNoticeCode(String noticeCode) {
        this.noticeCode = noticeCode;
    }

    /**
     * get the noticeContent 
     * @return the noticeContent
     */
    public String getNoticeContent() {
        return this.noticeContent;
    }

    /**
     * set the noticeContent 
     */
    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    /**
     * get the inputDate 
     * @return the inputDate
     */
    public Date getInputDate() {
        return this.inputDate;
    }

    /**
     * set the inputDate 
     */
    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    /**
     * get the effectiveDate 
     * @return the effectiveDate
     */
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    /**
     * set the effectiveDate 
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * get the isEffective - 是否生效 。0－不生效；1-生效；
     * @return the isEffective
     */
    public String getIsEffective() {
        return this.isEffective;
    }

    /**
     * set the isEffective - 是否生效 。0－不生效；1-生效；
     */
    public void setIsEffective(String isEffective) {
        this.isEffective = isEffective;
    }

    /**
     * get the noticeTitle 
     * @return the noticeTitle
     */
    public String getNoticeTitle() {
        return this.noticeTitle;
    }

    /**
     * set the noticeTitle 
     */
    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    /**
     * get the recCreateTime 
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime 
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the typeCode - 类型编码
     * @return the typeCode
     */
    public String getTypeCode() {
        return this.typeCode;
    }

    /**
     * set the typeCode - 类型编码
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * get the typeName - 类型名称
     * @return the typeName
     */
    public String getTypeName() {
        return this.typeName;
    }

    /**
     * set the typeName - 类型名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setNoticeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("noticeCode")), noticeCode));
        setNoticeContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("noticeContent")), noticeContent));
        setInputDate(DateUtils.toDate(StringUtils.toString(map.get("inputDate"))));
        setEffectiveDate(DateUtils.toDate(StringUtils.toString(map.get("effectiveDate"))));
        setIsEffective(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isEffective")), isEffective));
        setNoticeTitle(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("noticeTitle")), noticeTitle));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setTypeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeCode")), typeCode));
        setTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeName")), typeName));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("noticeCode",StringUtils.toString(noticeCode, eiMetadata.getMeta("noticeCode")));
        map.put("noticeContent",StringUtils.toString(noticeContent, eiMetadata.getMeta("noticeContent")));
        map.put("inputDate",StringUtils.toString(inputDate, eiMetadata.getMeta("inputDate")));
        map.put("effectiveDate",StringUtils.toString(effectiveDate, eiMetadata.getMeta("effectiveDate")));
        map.put("isEffective",StringUtils.toString(isEffective, eiMetadata.getMeta("isEffective")));
        map.put("noticeTitle",StringUtils.toString(noticeTitle, eiMetadata.getMeta("noticeTitle")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("typeCode",StringUtils.toString(typeCode, eiMetadata.getMeta("typeCode")));
        map.put("typeName",StringUtils.toString(typeName, eiMetadata.getMeta("typeName")));
        return map;
    }
}