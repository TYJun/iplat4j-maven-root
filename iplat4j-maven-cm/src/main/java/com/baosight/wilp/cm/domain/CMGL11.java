/**
* Generate time : 2021-04-18 11:24:34
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cm.domain;

import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * 
 * 合同实体类
 * 
 * @Title: CMGL11.java
 * @ClassName: CMGL11
 * @Package：com.baosight.wilp.cm.domain
 * @author: zhaow
 * @date: 2021年8月30日 下午2:40:12
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class CMGL11 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreator = " ";		
    private String recCreateTime = " ";		
    private String recRevsior = " ";		
    private String recReviseTime = " ";		
    private String archiveFlag = " ";		/* 归档标记*/
    private String id = " ";		/* 主键*/
    private String contNo = " ";		/* 合同号*/
    private String subNo = " ";		
    private String content = " ";		
    private String fileId = " ";		/* 文件ID*/
    private String fileName = " ";		/* 文件名*/
    private String fileSize = " ";		/* 文件大小*/
    private String fileType = " ";		/* 文件类型*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevsior");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contNo");
        eiColumn.setDescName("合同号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("subNo");
        eiColumn.setDescName("子项号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("content");
        eiColumn.setDescName("内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileId");
        eiColumn.setDescName("文件ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileName");
        eiColumn.setDescName("文件名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileSize");
        eiColumn.setDescName("文件大小");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileType");
        eiColumn.setDescName("文件类型");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CMGL11() {
        initMetaData();
    }

    /**
     * get the recCreator 
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator 
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
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
     * get the recRevsior 
     * @return the recRevsior
     */
    public String getRecRevsior() {
        return this.recRevsior;
    }

    /**
     * set the recRevsior 
     */
    public void setRecRevsior(String recRevsior) {
        this.recRevsior = recRevsior;
    }

    /**
     * get the recReviseTime 
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime 
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
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
     * get the contNo - 合同号
     * @return the contNo
     */
    public String getContNo() {
        return this.contNo;
    }

    /**
     * set the contNo - 合同号
     */
    public void setContNo(String contNo) {
        this.contNo = contNo;
    }

    /**
     * get the subNo 
     * @return the subNo
     */
    public String getSubNo() {
        return this.subNo;
    }

    /**
     * set the subNo 
     */
    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }

    /**
     * get the content 
     * @return the content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * set the content 
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * get the fileId - 文件ID
     * @return the fileId
     */
    public String getFileId() {
        return this.fileId;
    }

    /**
     * set the fileId - 文件ID
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * get the fileName - 文件名
     * @return the fileName
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * set the fileName - 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * get the fileSize - 文件大小
     * @return the fileSize
     */
    public String getFileSize() {
        return this.fileSize;
    }

    /**
     * set the fileSize - 文件大小
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * get the fileType - 文件类型
     * @return the fileType
     */
    public String getFileType() {
        return this.fileType;
    }

    /**
     * set the fileType - 文件类型
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevsior(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevsior")), recRevsior));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setContNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contNo")), contNo));
        setSubNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("subNo")), subNo));
        setContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("content")), content));
        setFileId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileId")), fileId));
        setFileName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileName")), fileName));
        setFileSize(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileSize")), fileSize));
        setFileType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileType")), fileType));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevsior",StringUtils.toString(recRevsior, eiMetadata.getMeta("recRevsior")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
        map.put("subNo",StringUtils.toString(subNo, eiMetadata.getMeta("subNo")));
        map.put("content",StringUtils.toString(content, eiMetadata.getMeta("content")));
        map.put("fileId",StringUtils.toString(fileId, eiMetadata.getMeta("fileId")));
        map.put("fileName",StringUtils.toString(fileName, eiMetadata.getMeta("fileName")));
        map.put("fileSize",StringUtils.toString(fileSize, eiMetadata.getMeta("fileSize")));
        map.put("fileType",StringUtils.toString(fileType, eiMetadata.getMeta("fileType")));
        return map;
    }
}