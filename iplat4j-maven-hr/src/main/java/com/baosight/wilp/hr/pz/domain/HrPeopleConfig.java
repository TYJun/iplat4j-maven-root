/**
* Generate time : 2022-03-18 12:44:05
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.hr.pz.domain;

import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* HrPeopleConfig
* 
*/
public class HrPeopleConfig extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键ID*/
    private String creatorId = " ";		/* 创建人工号*/
    private String creatorName = " ";		/* 创建人名称*/
    private Timestamp createTime ;		/* 创建时间*/
    private String surpNum = " ";		/* 物业公司编码*/
    private String surpName = " ";		/* 物业公司名称*/
    private String serviceDeptName = " ";		/* 服务科室名称*/
    private String serviceDeptNum = " ";		/* 服务科室编码*/
    private String position = " ";		/* 岗位*/
    private String peopleLines = " ";		/* 招标配额*/
    private String peopleLinesStable = " ";		/* 稳定配额*/
    private String memo = " ";		/* 备注*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorId");
        eiColumn.setDescName("创建人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorName");
        eiColumn.setDescName("创建人名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surpNum");
        eiColumn.setDescName("物业公司编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surpName");
        eiColumn.setDescName("物业公司名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("serviceDeptName");
        eiColumn.setDescName("服务科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("serviceDeptNum");
        eiColumn.setDescName("服务科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("position");
        eiColumn.setDescName("岗位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("peopleLines");
        eiColumn.setDescName("招标配额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("peopleLinesStable");
        eiColumn.setDescName("稳定配额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("memo");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public HrPeopleConfig() {
        initMetaData();
    }

    /**
     * get the id - 主键ID
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 主键ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the creatorId - 创建人工号
     * @return the creatorId
     */
    public String getCreatorId() {
        return this.creatorId;
    }

    /**
     * set the creatorId - 创建人工号
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * get the creatorName - 创建人名称
     * @return the creatorName
     */
    public String getCreatorName() {
        return this.creatorName;
    }

    /**
     * set the creatorName - 创建人名称
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * get the createTime - 创建时间
     * @return the createTime
     */
    public Timestamp getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime - 创建时间
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * get the surpNum - 物业公司编码
     * @return the surpNum
     */
    public String getSurpNum() {
        return this.surpNum;
    }

    /**
     * set the surpNum - 物业公司编码
     */
    public void setSurpNum(String surpNum) {
        this.surpNum = surpNum;
    }

    /**
     * get the surpName - 物业公司名称
     * @return the surpName
     */
    public String getSurpName() {
        return this.surpName;
    }

    /**
     * set the surpName - 物业公司名称
     */
    public void setSurpName(String surpName) {
        this.surpName = surpName;
    }

    /**
     * get the serviceDeptName - 服务科室名称
     * @return the serviceDeptName
     */
    public String getServiceDeptName() {
        return this.serviceDeptName;
    }

    /**
     * set the serviceDeptName - 服务科室名称
     */
    public void setServiceDeptName(String serviceDeptName) {
        this.serviceDeptName = serviceDeptName;
    }

    /**
     * get the serviceDeptNum - 服务科室编码
     * @return the serviceDeptNum
     */
    public String getServiceDeptNum() {
        return this.serviceDeptNum;
    }

    /**
     * set the serviceDeptNum - 服务科室编码
     */
    public void setServiceDeptNum(String serviceDeptNum) {
        this.serviceDeptNum = serviceDeptNum;
    }

    /**
     * get the position - 岗位
     * @return the position
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * set the position - 岗位
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * get the peopleLines - 招标配额
     * @return the peopleLines
     */
    public String getPeopleLines() {
        return this.peopleLines;
    }

    /**
     * set the peopleLines - 招标配额
     */
    public void setPeopleLines(String peopleLines) {
        this.peopleLines = peopleLines;
    }

    /**
     * get the peopleLinesStable - 稳定配额
     * @return the peopleLinesStable
     */
    public String getPeopleLinesStable() {
        return this.peopleLinesStable;
    }

    /**
     * set the peopleLinesStable - 稳定配额
     */
    public void setPeopleLinesStable(String peopleLinesStable) {
        this.peopleLinesStable = peopleLinesStable;
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

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCreatorId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorId")), creatorId));
        setCreatorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorName")), creatorName));
        setCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("createTime"))));
        setSurpNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("surpNum")), surpNum));
        setSurpName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("surpName")), surpName));
        setServiceDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("serviceDeptName")), serviceDeptName));
        setServiceDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("serviceDeptNum")), serviceDeptNum));
        setPosition(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("position")), position));
        setPeopleLines(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("peopleLines")), peopleLines));
        setPeopleLinesStable(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("peopleLinesStable")), peopleLinesStable));
        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("creatorId",StringUtils.toString(creatorId, eiMetadata.getMeta("creatorId")));
        map.put("creatorName",StringUtils.toString(creatorName, eiMetadata.getMeta("creatorName")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("surpNum",StringUtils.toString(surpNum, eiMetadata.getMeta("surpNum")));
        map.put("surpName",StringUtils.toString(surpName, eiMetadata.getMeta("surpName")));
        map.put("serviceDeptName",StringUtils.toString(serviceDeptName, eiMetadata.getMeta("serviceDeptName")));
        map.put("serviceDeptNum",StringUtils.toString(serviceDeptNum, eiMetadata.getMeta("serviceDeptNum")));
        map.put("position",StringUtils.toString(position, eiMetadata.getMeta("position")));
        map.put("peopleLines",StringUtils.toString(peopleLines, eiMetadata.getMeta("peopleLines")));
        map.put("peopleLinesStable",StringUtils.toString(peopleLinesStable, eiMetadata.getMeta("peopleLinesStable")));
        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
        return map;
    }
}