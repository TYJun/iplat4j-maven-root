/**
* Generate time : 2022-01-28 16:52:47
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
* CmBidFile
* 
*/
public class CmBidFile extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 合同招标文件主键*/
    private String bidPk = " ";		/* 招标表主键*/
    private String fileRemark = " ";		/* 文件说明*/
    private String filePath = " ";		/* 招标文件路径*/
    private String fileName = " ";		/* 招标文件名称*/
    private BigDecimal fileSize = new BigDecimal("0");		/* 招标文件大小*/
    private String recCreatorNo = " ";		/* 创建人工号*/
    private String recCreator = " ";		/* 创建人*/
    private Timestamp recCreateTime ;		/* 创建时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("合同招标文件主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bidPk");
        eiColumn.setDescName("招标表主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileRemark");
        eiColumn.setDescName("文件说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("filePath");
        eiColumn.setDescName("招标文件路径");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileName");
        eiColumn.setDescName("招标文件名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileSize");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("招标文件大小");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreatorNo");
        eiColumn.setDescName("创建人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CmBidFile() {
        initMetaData();
    }

    /**
     * get the id - 合同招标文件主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 合同招标文件主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the bidPk - 招标表主键
     * @return the bidPk
     */
    public String getBidPk() {
        return this.bidPk;
    }

    /**
     * set the bidPk - 招标表主键
     */
    public void setBidPk(String bidPk) {
        this.bidPk = bidPk;
    }

    /**
     * get the fileRemark - 文件说明
     * @return the fileRemark
     */
    public String getFileRemark() {
        return this.fileRemark;
    }

    /**
     * set the fileRemark - 文件说明
     */
    public void setFileRemark(String fileRemark) {
        this.fileRemark = fileRemark;
    }

    /**
     * get the filePath - 招标文件路径
     * @return the filePath
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * set the filePath - 招标文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * get the fileName - 招标文件名称
     * @return the fileName
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * set the fileName - 招标文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * get the fileSize - 招标文件大小
     * @return the fileSize
     */
    public BigDecimal getFileSize() {
        return this.fileSize;
    }

    /**
     * set the fileSize - 招标文件大小
     */
    public void setFileSize(BigDecimal fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * get the recCreatorNo - 创建人工号
     * @return the recCreatorNo
     */
    public String getRecCreatorNo() {
        return this.recCreatorNo;
    }

    /**
     * set the recCreatorNo - 创建人工号
     */
    public void setRecCreatorNo(String recCreatorNo) {
        this.recCreatorNo = recCreatorNo;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setBidPk(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bidPk")), bidPk));
        setFileRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileRemark")), fileRemark));
        setFilePath(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("filePath")), filePath));
        setFileName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileName")), fileName));
        setFileSize(NumberUtils.toBigDecimal(StringUtils.toString(map.get("fileSize")), fileSize));
        setRecCreatorNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreatorNo")), recCreatorNo));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("recCreateTime"))));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("bidPk",StringUtils.toString(bidPk, eiMetadata.getMeta("bidPk")));
        map.put("fileRemark",StringUtils.toString(fileRemark, eiMetadata.getMeta("fileRemark")));
        map.put("filePath",StringUtils.toString(filePath, eiMetadata.getMeta("filePath")));
        map.put("fileName",StringUtils.toString(fileName, eiMetadata.getMeta("fileName")));
        map.put("fileSize",StringUtils.toString(fileSize, eiMetadata.getMeta("fileSize")));
        map.put("recCreatorNo",StringUtils.toString(recCreatorNo, eiMetadata.getMeta("recCreatorNo")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        return map;
    }
}