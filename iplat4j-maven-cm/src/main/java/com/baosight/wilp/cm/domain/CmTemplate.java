/**
* Generate time : 2022-01-21 16:39:13
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
* CmTemplate
* 
*/
public class CmTemplate extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 模板主键*/
    private String filePath = " ";		/* 文件路径*/
    private String fileName = " ";		/* 文件名称*/
    private String fileRemark = " ";		/* 文件备注*/
    private BigDecimal fileSize = new BigDecimal("0");		/* 文件大小*/
    private Integer fileStatus = new Integer(0);		/* 文件状态*/
    private String recCreatorNo = " ";		/* 创建人工号*/
    private String recCreator = " ";		/* 创建人*/
    private Timestamp recCreateTime ;		/* 创建时间*/
    private String recRevisorNo = " ";		/* 修改人工号*/
    private String recRevisor = " ";		/* 修改人*/
    private Timestamp recReviseTime ;		/* 修改时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("模板主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("filePath");
        eiColumn.setDescName("文件路径");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileName");
        eiColumn.setDescName("文件名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileRemark");
        eiColumn.setDescName("文件备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileSize");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("文件大小");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileStatus");
        eiColumn.setDescName("文件状态");
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

        eiColumn = new EiColumn("recRevisorNo");
        eiColumn.setDescName("修改人工号");
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
    public CmTemplate() {
        initMetaData();
    }

    /**
     * get the id - 模板主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 模板主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the filePath - 文件路径
     * @return the filePath
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * set the filePath - 文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * get the fileName - 文件名称
     * @return the fileName
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * set the fileName - 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * get the fileRemark - 文件备注
     * @return the fileRemark
     */
    public String getFileRemark() {
        return this.fileRemark;
    }

    /**
     * set the fileRemark - 文件备注
     */
    public void setFileRemark(String fileRemark) {
        this.fileRemark = fileRemark;
    }

    /**
     * get the fileSize - 文件大小
     * @return the fileSize
     */
    public BigDecimal getFileSize() {
        return this.fileSize;
    }

    /**
     * set the fileSize - 文件大小
     */
    public void setFileSize(BigDecimal fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * get the fileStatus - 文件状态
     * @return the fileStatus
     */
    public Integer getFileStatus() {
        return this.fileStatus;
    }

    /**
     * set the fileStatus - 文件状态
     */
    public void setFileStatus(Integer fileStatus) {
        this.fileStatus = fileStatus;
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
     * get the recRevisorNo - 修改人工号
     * @return the recRevisorNo
     */
    public String getRecRevisorNo() {
        return this.recRevisorNo;
    }

    /**
     * set the recRevisorNo - 修改人工号
     */
    public void setRecRevisorNo(String recRevisorNo) {
        this.recRevisorNo = recRevisorNo;
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
     * get the value from Map
     */
    public void fromMap(Map map) {
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setFilePath(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("filePath")), filePath));
        setFileName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileName")), fileName));
        setFileRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileRemark")), fileRemark));
        setFileSize(NumberUtils.toBigDecimal(StringUtils.toString(map.get("fileSize")), fileSize));
        setFileStatus(NumberUtils.toInteger(StringUtils.toString(map.get("fileStatus")), fileStatus));
        setRecCreatorNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreatorNo")), recCreatorNo));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("recCreateTime"))));
        setRecRevisorNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisorNo")), recRevisorNo));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(DateUtils.toTimestamp(StringUtils.toString(map.get("recReviseTime"))));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("filePath",StringUtils.toString(filePath, eiMetadata.getMeta("filePath")));
        map.put("fileName",StringUtils.toString(fileName, eiMetadata.getMeta("fileName")));
        map.put("fileRemark",StringUtils.toString(fileRemark, eiMetadata.getMeta("fileRemark")));
        map.put("fileSize",StringUtils.toString(fileSize, eiMetadata.getMeta("fileSize")));
        map.put("fileStatus",StringUtils.toString(fileStatus, eiMetadata.getMeta("fileStatus")));
        map.put("recCreatorNo",StringUtils.toString(recCreatorNo, eiMetadata.getMeta("recCreatorNo")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisorNo",StringUtils.toString(recRevisorNo, eiMetadata.getMeta("recRevisorNo")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        return map;
    }
}