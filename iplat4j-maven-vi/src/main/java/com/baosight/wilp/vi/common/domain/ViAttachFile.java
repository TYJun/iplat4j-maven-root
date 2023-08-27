/**
* Generate time : 2023-07-17 16:53:17
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.vi.common.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* ViAttachFile
* 
*/
public class ViAttachFile extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = "";		/* 主键*/
    private String fileName = "";		/* 原始文件名*/
    private String fileDesc = "";		/* 原始的描述说明*/
    private String mimeType = "";		/* 文件的MIME类型*/
    private Integer fileSize = new Integer(0);		/* 文件大小*/
    private String fileContent = "";		/* 附件内容，BASE64格式*/
    private Timestamp uploadTime ;		/* 上传时间*/
    private String uploadIp = "";		/* 上传的IP地址*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileName");
        eiColumn.setDescName("原始文件名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileDesc");
        eiColumn.setDescName("原始的描述说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mimeType");
        eiColumn.setDescName("文件的MIME类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileSize");
        eiColumn.setDescName("文件大小");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileContent");
        eiColumn.setDescName("附件内容，BASE64格式");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("uploadTime");
        eiColumn.setDescName("上传时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("uploadIp");
        eiColumn.setDescName("上传的IP地址");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ViAttachFile() {
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
     * get the fileName - 原始文件名
     * @return the fileName
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * set the fileName - 原始文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * get the fileDesc - 原始的描述说明
     * @return the fileDesc
     */
    public String getFileDesc() {
        return this.fileDesc;
    }

    /**
     * set the fileDesc - 原始的描述说明
     */
    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    /**
     * get the mimeType - 文件的MIME类型
     * @return the mimeType
     */
    public String getMimeType() {
        return this.mimeType;
    }

    /**
     * set the mimeType - 文件的MIME类型
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * get the fileSize - 文件大小
     * @return the fileSize
     */
    public Integer getFileSize() {
        return this.fileSize;
    }

    /**
     * set the fileSize - 文件大小
     */
    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * get the fileContent - 附件内容，BASE64格式
     * @return the fileContent
     */
    public String getFileContent() {
        return this.fileContent;
    }

    /**
     * set the fileContent - 附件内容，BASE64格式
     */
    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    /**
     * get the uploadTime - 上传时间
     * @return the uploadTime
     */
    public Timestamp getUploadTime() {
        return this.uploadTime;
    }

    /**
     * set the uploadTime - 上传时间
     */
    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * get the uploadIp - 上传的IP地址
     * @return the uploadIp
     */
    public String getUploadIp() {
        return this.uploadIp;
    }

    /**
     * set the uploadIp - 上传的IP地址
     */
    public void setUploadIp(String uploadIp) {
        this.uploadIp = uploadIp;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setFileName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileName")), fileName));
        setFileDesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileDesc")), fileDesc));
        setMimeType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mimeType")), mimeType));
        setFileSize(NumberUtils.toInteger(StringUtils.toString(map.get("fileSize")), fileSize));
        setFileContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileContent")), fileContent));
        setUploadTime(DateUtils.toTimestamp(StringUtils.toString(map.get("uploadTime"))));
        setUploadIp(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("uploadIp")), uploadIp));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("fileName",StringUtils.toString(fileName, eiMetadata.getMeta("fileName")));
        map.put("fileDesc",StringUtils.toString(fileDesc, eiMetadata.getMeta("fileDesc")));
        map.put("mimeType",StringUtils.toString(mimeType, eiMetadata.getMeta("mimeType")));
        map.put("fileSize",StringUtils.toString(fileSize, eiMetadata.getMeta("fileSize")));
        map.put("fileContent",StringUtils.toString(fileContent, eiMetadata.getMeta("fileContent")));
        map.put("uploadTime",StringUtils.toString(uploadTime, eiMetadata.getMeta("uploadTime")));
        map.put("uploadIp",StringUtils.toString(uploadIp, eiMetadata.getMeta("uploadIp")));
        return map;
    }
}