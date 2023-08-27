/**
* Generate time : 2021-07-15 17:26:50
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.df.sb.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DfSpecialFile
* 
*/
public class DFSB03 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 设备零部件ID*/
    private String docId = " ";		/* 平台附件表ID*/
    private String relateId = " ";		/* 关联id(设备id或检验单id)*/
    private String fileModule = " ";		/* 附件所属模块(0=设备附件,1=检验附件)*/
    private String fileType = " ";		/* 附件类型(0=图片,1=文档)*/
    private String fileName = " ";		/* 附件名称*/
    private String filePath = " ";		/* 附件存路径*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String recReviseTime = " ";		/* 修改时间*/
    private String dataGroupCode = " ";		/* 账套*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("设备零部件ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("docId");
        eiColumn.setDescName("平台附件表ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("relateId");
        eiColumn.setDescName("关联id(设备id或检验单id)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileModule");
        eiColumn.setDescName("附件所属模块(0=设备附件,1=检验附件)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileType");
        eiColumn.setDescName("附件类型(0=图片,1=文档)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileName");
        eiColumn.setDescName("附件名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("filePath");
        eiColumn.setDescName("附件存路径");
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


    }

    /**
     * the constructor
     */
    public DFSB03() {
        initMetaData();
    }

    /**
     * get the id - 设备零部件ID
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 设备零部件ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the docId - 平台附件表ID
     * @return the docId
     */
    public String getDocId() {
        return this.docId;
    }

    /**
     * set the docId - 平台附件表ID
     */
    public void setDocId(String docId) {
        this.docId = docId;
    }

    /**
     * get the relateId - 关联id(设备id或检验单id)
     * @return the relateId
     */
    public String getRelateId() {
        return this.relateId;
    }

    /**
     * set the relateId - 关联id(设备id或检验单id)
     */
    public void setRelateId(String relateId) {
        this.relateId = relateId;
    }

    /**
     * get the fileModule - 附件所属模块(0=设备附件,1=检验附件)
     * @return the fileModule
     */
    public String getFileModule() {
        return this.fileModule;
    }

    /**
     * set the fileModule - 附件所属模块(0=设备附件,1=检验附件)
     */
    public void setFileModule(String fileModule) {
        this.fileModule = fileModule;
    }

    /**
     * get the fileType - 附件类型(0=图片,1=文档)
     * @return the fileType
     */
    public String getFileType() {
        return this.fileType;
    }

    /**
     * set the fileType - 附件类型(0=图片,1=文档)
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
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
     * get the filePath - 附件存路径
     * @return the filePath
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * set the filePath - 附件存路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setDocId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("docId")), docId));
        setRelateId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("relateId")), relateId));
        setFileModule(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileModule")), fileModule));
        setFileType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileType")), fileType));
        setFileName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileName")), fileName));
        setFilePath(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("filePath")), filePath));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("docId",StringUtils.toString(docId, eiMetadata.getMeta("docId")));
        map.put("relateId",StringUtils.toString(relateId, eiMetadata.getMeta("relateId")));
        map.put("fileModule",StringUtils.toString(fileModule, eiMetadata.getMeta("fileModule")));
        map.put("fileType",StringUtils.toString(fileType, eiMetadata.getMeta("fileType")));
        map.put("fileName",StringUtils.toString(fileName, eiMetadata.getMeta("fileName")));
        map.put("filePath",StringUtils.toString(filePath, eiMetadata.getMeta("filePath")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        return map;
    }
}