/**
* Generate time : 2022-12-17 23:33:17
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.fa.cf.domain;

import com.baosight.iplat4j.core.util.DateUtils;
import java.util.Date;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* FaSplitVO
* 
*/
public class FaSplitVO extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 拆分主键*/
    private String splitNo = " ";		/* 拆分单号*/
    private String splitWay = " ";		/* 拆分方式(00-价值拆分,10-数量拆分)*/
    private String statusCode = " ";		/* 状态(00-草稿,100-通过,20-驳回)*/
    private String splitReason = " ";		/* 拆分理由*/
    private String recCreateName = " ";		/* 创建人*/
    private Date recCreateTime;	/* 创建时间*/
    private String auditPerson = " ";		/* 确认人*/
    private Date auditTime;	/* 确认时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("拆分主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("splitNo");
        eiColumn.setDescName("拆分单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("splitWay");
        eiColumn.setDescName("拆分方式(00-价值拆分,10-数量拆分)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态(00-草稿,100-通过,20-驳回)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("splitReason");
        eiColumn.setDescName("拆分理由");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateName");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditPerson");
        eiColumn.setDescName("确认人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditTime");
        eiColumn.setDescName("确认时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public FaSplitVO() {
        initMetaData();
    }

    /**
     * get the id - 拆分主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 拆分主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the splitNo - 拆分单号
     * @return the splitNo
     */
    public String getSplitNo() {
        return this.splitNo;
    }

    /**
     * set the splitNo - 拆分单号
     */
    public void setSplitNo(String splitNo) {
        this.splitNo = splitNo;
    }

    /**
     * get the splitWay - 拆分方式(00-价值拆分,10-数量拆分)
     * @return the splitWay
     */
    public String getSplitWay() {
        return this.splitWay;
    }

    /**
     * set the splitWay - 拆分方式(00-价值拆分,10-数量拆分)
     */
    public void setSplitWay(String splitWay) {
        this.splitWay = splitWay;
    }

    /**
     * get the statusCode - 状态(00-草稿,100-通过,20-驳回)
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态(00-草稿,100-通过,20-驳回)
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the splitReason - 拆分理由
     * @return the splitReason
     */
    public String getSplitReason() {
        return this.splitReason;
    }

    /**
     * set the splitReason - 拆分理由
     */
    public void setSplitReason(String splitReason) {
        this.splitReason = splitReason;
    }

    /**
     * get the recCreateName - 创建人
     * @return the recCreateName
     */
    public String getRecCreateName() {
        return this.recCreateName;
    }

    /**
     * set the recCreateName - 创建人
     */
    public void setRecCreateName(String recCreateName) {
        this.recCreateName = recCreateName;
    }

    /**
     * get the recCreateTime - 创建时间
     * @return the recCreateTime
     */
    public Date getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(Date recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the auditPerson - 确认人
     * @return the auditPerson
     */
    public String getAuditPerson() {
        return this.auditPerson;
    }

    /**
     * set the auditPerson - 确认人
     */
    public void setAuditPerson(String auditPerson) {
        this.auditPerson = auditPerson;
    }

    /**
     * get the auditTime - 确认时间
     * @return the auditTime
     */
    public Date getAuditTime() {
        return this.auditTime;
    }

    /**
     * set the auditTime - 确认时间
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setSplitNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("splitNo")), splitNo));
        setSplitWay(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("splitWay")), splitWay));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setSplitReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("splitReason")), splitReason));
        setRecCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateName")), recCreateName));
        setRecCreateTime(DateUtils.toDate(StringUtils.toString(map.get("recCreateTime"))));
        setAuditPerson(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditPerson")), auditPerson));
        setAuditTime(DateUtils.toDate(StringUtils.toString(map.get("auditTime"))));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("splitNo",StringUtils.toString(splitNo, eiMetadata.getMeta("splitNo")));
        map.put("splitWay",StringUtils.toString(splitWay, eiMetadata.getMeta("splitWay")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("splitReason",StringUtils.toString(splitReason, eiMetadata.getMeta("splitReason")));
        map.put("recCreateName",StringUtils.toString(recCreateName, eiMetadata.getMeta("recCreateName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("auditPerson",StringUtils.toString(auditPerson, eiMetadata.getMeta("auditPerson")));
        map.put("auditTime",StringUtils.toString(auditTime, eiMetadata.getMeta("auditTime")));
        return map;
    }
}