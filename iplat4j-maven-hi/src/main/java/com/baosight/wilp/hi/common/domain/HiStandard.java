/**
* Generate time : 2022-07-04 16:32:15
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.hi.common.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
* HiStandard
* 
*/
public class HiStandard extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String standardNum = " ";		/* 标准编号*/
    private String standardName = " ";		/* 标准名称*/
    private String classifyId = " ";		/* 标识分类表ID*/
    private String classifyName = " ";		/* 标识分类名称*/
    private String remark = " ";		/* 备注*/
    private String recCreator = " ";		/* 创建人*/
    private Timestamp recCreateTime ;		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private Timestamp recReviseTime ;		/* 修改时间*/
    private String dataGroupCode = " ";		/* 账套*/
    private String archiveFlag = " ";		/* 归档标记*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("standardNum");
        eiColumn.setDescName("标准编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("standardName");
        eiColumn.setDescName("标准名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classifyId");
        eiColumn.setDescName("标识分类表ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classifyName");
        eiColumn.setDescName("标识分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public HiStandard() {
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
     * get the standardNum - 标准编号
     * @return the standardNum
     */
    public String getStandardNum() {
        return this.standardNum;
    }

    /**
     * set the standardNum - 标准编号
     */
    public void setStandardNum(String standardNum) {
        this.standardNum = standardNum;
    }

    /**
     * get the standardName - 标准名称
     * @return the standardName
     */
    public String getStandardName() {
        return this.standardName;
    }

    /**
     * set the standardName - 标准名称
     */
    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    /**
     * get the classifyId - 标识分类表ID
     * @return the classifyId
     */
    public String getClassifyId() {
        return this.classifyId;
    }

    /**
     * set the classifyId - 标识分类表ID
     */
    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    /**
     * get the classifyName - 标识分类名称
     * @return the classifyName
     */
    public String getClassifyName() {
        return this.classifyName;
    }

    /**
     * set the classifyName - 标识分类名称
     */
    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    /**
     * get the remark - 备注
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark - 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
    public Timestamp getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(Timestamp recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevisor - 修改人
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 修改人
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseTime - 修改时间
     * @return the recReviseTime
     */
    public Timestamp getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTime(Timestamp recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the dataGroupCode - 账套
     * @return the dataGroupCode
     */
    public String getDataGroupCode() {
        return this.dataGroupCode;
    }

    /**
     * set the dataGroupCode - 账套
     */
    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

    /**
     * get the archiveFlag - 归档标记
     * @return the archiveFlag
     */
    public String getArchiveFlag() {
        return this.archiveFlag;
    }

    /**
     * set the archiveFlag - 归档标记
     */
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setStandardNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("standardNum")), standardNum));
        setStandardName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("standardName")), standardName));
        setClassifyId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyId")), classifyId));
        setClassifyName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyName")), classifyName));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("recCreateTime"))));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(DateUtils.toTimestamp(StringUtils.toString(map.get("recReviseTime"))));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("standardNum",StringUtils.toString(standardNum, eiMetadata.getMeta("standardNum")));
        map.put("standardName",StringUtils.toString(standardName, eiMetadata.getMeta("standardName")));
        map.put("classifyId",StringUtils.toString(classifyId, eiMetadata.getMeta("classifyId")));
        map.put("classifyName",StringUtils.toString(classifyName, eiMetadata.getMeta("classifyName")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        return map;
    }
}