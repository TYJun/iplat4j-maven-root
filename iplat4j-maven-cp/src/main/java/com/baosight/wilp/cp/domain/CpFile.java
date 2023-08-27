/**
* Generate time : 2022-03-21 17:22:39
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cp.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CpFile
* 
*/
public class CpFile extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String billNo = " ";		/* 投诉单号*/
    private String createTime = " ";		/* 创建时间*/
    private String fileId = " ";		/* 文件id*/
    private String creator = " ";		/* 创建人工号*/
    private String fileUrl = " ";		/* 文件url*/
    private String createName = " ";/* 创建人*/
    private String archiveFlag = " ";
    private String recRevisor = " ";
    private String recReviseTime = " ";
    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName("投诉单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileId");
        eiColumn.setDescName("文件id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setDescName("创建人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileUrl");
        eiColumn.setDescName("文件url");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createName");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public CpFile() {
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
     * get the billNo - 投诉单号
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo - 投诉单号
     */
    public void setBillNo(String cpdj0101) {
        this.billNo = billNo;
    }

    /**
     * get the createTime - 创建时间
     * @return the createTime
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime - 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * get the fileId - 文件id
     * @return the fileId
     */
    public String getFileId() {
        return this.fileId;
    }

    /**
     * set the fileId - 文件id
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * get the creator - 创建人工号
     * @return the creator
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * set the creator - 创建人工号
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * get the fileUrl - 文件url
     * @return the fileUrl
     */
    public String getFileUrl() {
        return this.fileUrl;
    }

    /**
     * set the fileUrl - 文件url
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * get the createName - 创建人
     * @return the createName
     */
    public String getCreateName() {
        return this.createName;
    }

    /**
     * set the createName - 创建人
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }



    public String getArchiveFlag() {
        return this.archiveFlag;
    }

    @Override
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }


    public String getRecRevisor() {
        return this.recRevisor;
    }
    @Override
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }


    public String getRecReviseTime() {
        return this.recReviseTime;
    }
    @Override
    public void setRecReviseTime(String recReviseTime ) {
        this.recReviseTime = recReviseTime;
    }
    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        /*setBillNo(einInfo, "CPDJ0101", StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));*/
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createTime")), createTime));
        setFileId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileId")), fileId));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setFileUrl(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileUrl")), fileUrl));
        setCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createName")), createName));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("fileId",StringUtils.toString(fileId, eiMetadata.getMeta("fileId")));
        map.put("creator",StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("fileUrl",StringUtils.toString(fileUrl, eiMetadata.getMeta("fileUrl")));
        map.put("createName",StringUtils.toString(createName, eiMetadata.getMeta("createName")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        return map;
    }
}