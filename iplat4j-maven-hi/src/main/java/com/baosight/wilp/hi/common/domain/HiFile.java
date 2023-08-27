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
* HiFile
* 
*/
public class HiFile extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String relationId = " ";		/* 关联id*/
    private String fileName = " ";		/* 附件名称*/
    private String fileSize = " ";		/* 附件大小*/
    private String docId = " ";		/* iplat库附件ID(下载使用)*/
    private String remark = " ";		/* 附件说明*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private Timestamp recReviseTime ;		/* 修改时间*/
    private String dataGroupCode = " ";		/* 账套*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String filePath = " ";
    private String fileStatus = " ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("relationId");
        eiColumn.setDescName("关联id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileName");
        eiColumn.setDescName("附件名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileSize");
        eiColumn.setDescName("附件大小");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("docId");
        eiColumn.setDescName("iplat库附件ID(下载使用)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("附件说明");
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

        eiColumn = new EiColumn("filePath");
        eiColumn.setDescName("附件路径");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileStatus");
        eiColumn.setDescName("附件所属状态");
        eiMetadata.addMeta(eiColumn);



    }

    /**
     * the constructor
     */
    public HiFile() {
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
     * get the relationId - 关联id
     * @return the relationId
     */
    public String getRelationId() {
        return this.relationId;
    }

    /**
     * set the relationId - 关联id
     */
    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    /**
     * get the fileName - 附件名称
     * @return the fileName
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * set the fileName - 附件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * get the fileSize - 附件大小
     * @return the fileSize
     */
    public String getFileSize() {
        return this.fileSize;
    }

    /**
     * set the fileSize - 附件大小
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * get the docId - iplat库附件ID(下载使用)
     * @return the docId
     */
    public String getDocId() {
        return this.docId;
    }

    /**
     * set the docId - iplat库附件ID(下载使用)
     */
    public void setDocId(String docId) {
        this.docId = docId;
    }

    /**
     * get the remark - 附件说明
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark - 附件说明
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
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(String  recCreateTime) {
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


    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileStatus() {
        return this.fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }






    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRelationId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("relationId")), relationId));
        setFileName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileName")), fileName));
        setFileSize(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileSize")), fileSize));
        setDocId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("docId")), docId));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")),recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(DateUtils.toTimestamp(StringUtils.toString(map.get("recReviseTime"))));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setFilePath(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("filePath")), filePath));
        setFileStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileStatus")), fileStatus));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("relationId",StringUtils.toString(relationId, eiMetadata.getMeta("relationId")));
        map.put("fileName",StringUtils.toString(fileName, eiMetadata.getMeta("fileName")));
        map.put("fileSize",StringUtils.toString(fileSize, eiMetadata.getMeta("fileSize")));
        map.put("docId",StringUtils.toString(docId, eiMetadata.getMeta("docId")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("filePath",StringUtils.toString(filePath, eiMetadata.getMeta("filePath")));
        map.put("fileStatus",StringUtils.toString(fileStatus, eiMetadata.getMeta("fileStatus")));
        return map;
    }
}