/**
* Generate time : 2022-12-06 11:17:20
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.fa.bg.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* FaModificationRecordVO
* 资产变更记录表
*/
public class FaModificationRecordVO extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id;		/* 资产变更记录表主键*/
    private String batchId;		/* 资产批次表主键*/
    private String faInfoId;		/* 资产信息表主键*/
    private String fieldCode;		/* 变更字段*/
    private String fieldName;		/* 变更字段*/
    private String before;		/* 变更前*/
    private String after;		/* 变更后*/
    private String status;		/* 状态(000-大类变更,100-信息变更,200-价值变更)*/
    private String type;		/* 类型(201-升值,202-贬值)*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("资产变更记录表主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("batchId");
        eiColumn.setDescName("资产批次表主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fainfoId");
        eiColumn.setDescName("资产信息表主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fieldCode");
        eiColumn.setDescName("变更字段");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fieldName");
        eiColumn.setDescName("变更字段");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("before");
        eiColumn.setDescName("变更前");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("after");
        eiColumn.setDescName("变更后");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("状态(000-大类变更,100-信息变更,200-价值变更)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("type");
        eiColumn.setDescName("类型(201-升值,202-贬值)");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public FaModificationRecordVO() {
        initMetaData();
    }

    /**
     * get the id - 资产变更记录表主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 资产变更记录表主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the batchId - 资产批次表主键
     * @return the batchId
     */
    public String getBatchId() {
        return this.batchId;
    }

    /**
     * set the batchId - 资产批次表主键
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    /**
     * get the fainfoId - 资产信息表主键
     * @return the fainfoId
     */
    public String getFaInfoId() {
        return this.faInfoId;
    }

    /**
     * set the fainfoId - 资产信息表主键
     */
    public void setFaInfoId(String faInfoId) {
        this.faInfoId = faInfoId;
    }

    /**
     * get the fieldCode - 变更字段
     * @return the fieldCode
     */
    public String getFieldCode() {
        return this.fieldCode;
    }

    /**
     * set the fieldCode - 变更字段
     */
    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    /**
     * get the fieldName - 变更字段
     * @return the fieldName
     */
    public String getFieldName() {
        return this.fieldName;
    }

    /**
     * set the fieldName - 变更字段
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * get the before - 变更前
     * @return the before
     */
    public String getBefore() {
        return this.before;
    }

    /**
     * set the before - 变更前
     */
    public void setBefore(String before) {
        this.before = before;
    }

    /**
     * get the after - 变更后
     * @return the after
     */
    public String getAfter() {
        return this.after;
    }

    /**
     * set the after - 变更后
     */
    public void setAfter(String after) {
        this.after = after;
    }

    /**
     * get the status - 状态(000-大类变更,100-信息变更,200-价值变更)
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status - 状态(000-大类变更,100-信息变更,200-价值变更)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * get the type - 类型(201-升值,202-贬值)
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * set the type - 类型(201-升值,202-贬值)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setBatchId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("batchId")), batchId));
        setFaInfoId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("faInfoId")), faInfoId));
        setFieldCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fieldCode")), fieldCode));
        setFieldName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fieldName")), fieldName));
        setBefore(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("before")), before));
        setAfter(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("after")), after));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("type")), type));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("batchId",StringUtils.toString(batchId, eiMetadata.getMeta("batchId")));
        map.put("faInfoId",StringUtils.toString(faInfoId, eiMetadata.getMeta("faInfoIdFaModificationBatchVO.java")));
        map.put("fieldCode",StringUtils.toString(fieldCode, eiMetadata.getMeta("fieldCode")));
        map.put("fieldName",StringUtils.toString(fieldName, eiMetadata.getMeta("fieldName")));
        map.put("before",StringUtils.toString(before, eiMetadata.getMeta("before")));
        map.put("after",StringUtils.toString(after, eiMetadata.getMeta("after")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("type",StringUtils.toString(type, eiMetadata.getMeta("type")));
        return map;
    }
}