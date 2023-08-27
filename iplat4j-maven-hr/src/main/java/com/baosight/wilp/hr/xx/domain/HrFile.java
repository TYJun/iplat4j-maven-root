
package com.baosight.wilp.hr.xx.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * HrFile
 *
 */
public class HrFile extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键（存文件用）*/
    private String manId = " ";		/* 设备id*/
    private String fileId = " ";		/* 文件id*/
    private String fileName = " ";		/* 文件名称*/
    private String fileSize = " ";		/* 文件大小*/
    private String fileDesc = " ";		/* 文件说明*/
    private String fileNum = " ";		/* 文件排序*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键（存文件用）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manId");
        eiColumn.setDescName("人员id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileId");
        eiColumn.setDescName("文件id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileName");
        eiColumn.setDescName("文件名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileSize");
        eiColumn.setDescName("文件大小");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileDesc");
        eiColumn.setDescName("文件说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileNum");
        eiColumn.setDescName("文件排序");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public HrFile() {
        initMetaData();
    }

    /**
     * get the id - 主键（存文件用）
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 主键（存文件用）
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the machineid - 设备id
     * @return the machineid
     */
    public String getManId() {
        return this.manId;
    }

    /**
     * set the machineid - 设备id
     */
    public void setManId(String manId) {
        this.manId = manId;
    }

    /**
     * get the fileid - 文件id
     * @return the fileid
     */
    public String getFileId() {
        return this.fileId;
    }

    /**
     * set the fileid - 文件id
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * get the filename - 文件名称
     * @return the filename
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * set the filename - 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * get the fileSize - 文件大小
     * @return the filesize
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
     * get the fileDesc - 文件说明
     * @return the fileDesc
     */
    public String getFileDesc() {
        return this.fileDesc;
    }

    /**
     * set the fileDesc - 文件说明
     */
    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    /**
     * get the fileNum - 文件排序
     * @return the fileNum
     */
    public String getFileNum() {
        return this.fileNum;
    }

    /**
     * set the fileNum - 文件排序
     */
    public void setFileNum(String fileNum) {
        this.fileNum = fileNum;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setManId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manId")), manId));
        setFileId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileId")), fileId));
        setFileName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileName")), fileName));
        setFileSize(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileSize")), fileSize));
        setFileDesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileDesc")), fileDesc));
        setFileNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileNum")), fileNum));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("manId",StringUtils.toString(manId, eiMetadata.getMeta("manId")));
        map.put("fileId",StringUtils.toString(fileId, eiMetadata.getMeta("fileId")));
        map.put("fileName",StringUtils.toString(fileName, eiMetadata.getMeta("fileName")));
        map.put("fileSize",StringUtils.toString(fileSize, eiMetadata.getMeta("fileSize")));
        map.put("fileDesc",StringUtils.toString(fileDesc, eiMetadata.getMeta("fileDesc")));
        map.put("fileNum",StringUtils.toString(fileNum, eiMetadata.getMeta("fileNum")));
        return map;
    }
}