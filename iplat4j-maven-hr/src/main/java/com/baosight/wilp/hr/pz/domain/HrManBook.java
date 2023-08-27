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
* HrManBook
* 
*/
public class HrManBook extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键ID*/
    private String manId = " ";		/* 人员信息表ID*/
    private String bookCode = " ";		/* 证件类型的code*/
    private String bookName = " ";		/* 证书类型的名称*/
    private String bookNum = " ";		/* 证件编号*/
    private String bookValidateTime = " ";		/* 证书有效期*/
    private String fileId = " ";		/* 证书的JPG文件附件ID*/
    private Timestamp createDate ;		/* 创建日期*/
    private String creatorName = " ";		/* 创建人*/
    private String creatorId = " ";		/* 创建人ID */
    private Timestamp updateDate ;		/* 更改时间*/
    private String updatorName = " ";		/* 更改人*/
    private String updatorId = " ";		/* 更改人ID*/
    private String operatorType = " ";		/* 最后操作类型*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manId");
        eiColumn.setDescName("人员信息表ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bookCode");
        eiColumn.setDescName("证件类型的code");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bookName");
        eiColumn.setDescName("证书类型的名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bookNum");
        eiColumn.setDescName("证件编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bookValidateTime");
        eiColumn.setDescName("证书有效期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileId");
        eiColumn.setDescName("证书的JPG文件附件ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createDate");
        eiColumn.setDescName("创建日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorName");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorId");
        eiColumn.setDescName("创建人ID ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updateDate");
        eiColumn.setDescName("更改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatorName");
        eiColumn.setDescName("更改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatorId");
        eiColumn.setDescName("更改人ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operatorType");
        eiColumn.setDescName("最后操作类型");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public HrManBook() {
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
     * get the manId - 人员信息表ID
     * @return the manId
     */
    public String getManId() {
        return this.manId;
    }

    /**
     * set the manId - 人员信息表ID
     */
    public void setManId(String manId) {
        this.manId = manId;
    }

    /**
     * get the bookCode - 证件类型的code
     * @return the bookCode
     */
    public String getBookCode() {
        return this.bookCode;
    }

    /**
     * set the bookCode - 证件类型的code
     */
    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    /**
     * get the bookName - 证书类型的名称
     * @return the bookName
     */
    public String getBookName() {
        return this.bookName;
    }

    /**
     * set the bookName - 证书类型的名称
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * get the bookNum - 证件编号
     * @return the bookNum
     */
    public String getBookNum() {
        return this.bookNum;
    }

    /**
     * set the bookNum - 证件编号
     */
    public void setBookNum(String bookNum) {
        this.bookNum = bookNum;
    }

    /**
     * get the bookValidateTime - 证书有效期
     * @return the bookValidateTime
     */
    public String getBookValidateTime() {
        return this.bookValidateTime;
    }

    /**
     * set the bookValidateTime - 证书有效期
     */
    public void setBookValidateTime(String bookValidateTime) {
        this.bookValidateTime = bookValidateTime;
    }

    /**
     * get the fileId - 证书的JPG文件附件ID
     * @return the fileId
     */
    public String getFileId() {
        return this.fileId;
    }

    /**
     * set the fileId - 证书的JPG文件附件ID
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * get the createDate - 创建日期
     * @return the createDate
     */
    public Timestamp getCreateDate() {
        return this.createDate;
    }

    /**
     * set the createDate - 创建日期
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * get the creatorName - 创建人
     * @return the creatorName
     */
    public String getCreatorName() {
        return this.creatorName;
    }

    /**
     * set the creatorName - 创建人
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * get the creatorId - 创建人ID 
     * @return the creatorId
     */
    public String getCreatorId() {
        return this.creatorId;
    }

    /**
     * set the creatorId - 创建人ID 
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * get the updateDate - 更改时间
     * @return the updateDate
     */
    public Timestamp getUpdateDate() {
        return this.updateDate;
    }

    /**
     * set the updateDate - 更改时间
     */
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * get the updatorName - 更改人
     * @return the updatorName
     */
    public String getUpdatorName() {
        return this.updatorName;
    }

    /**
     * set the updatorName - 更改人
     */
    public void setUpdatorName(String updatorName) {
        this.updatorName = updatorName;
    }

    /**
     * get the updatorId - 更改人ID
     * @return the updatorId
     */
    public String getUpdatorId() {
        return this.updatorId;
    }

    /**
     * set the updatorId - 更改人ID
     */
    public void setUpdatorId(String updatorId) {
        this.updatorId = updatorId;
    }

    /**
     * get the operatorType - 最后操作类型
     * @return the operatorType
     */
    public String getOperatorType() {
        return this.operatorType;
    }

    /**
     * set the operatorType - 最后操作类型
     */
    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setManId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manId")), manId));
        setBookCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bookCode")), bookCode));
        setBookName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bookName")), bookName));
        setBookNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bookNum")), bookNum));
        setBookValidateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bookValidateTime")), bookValidateTime));
        setFileId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileId")), fileId));
        setCreateDate(DateUtils.toTimestamp(StringUtils.toString(map.get("createDate"))));
        setCreatorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorName")), creatorName));
        setCreatorId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorId")), creatorId));
        setUpdateDate(DateUtils.toTimestamp(StringUtils.toString(map.get("updateDate"))));
        setUpdatorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatorName")), updatorName));
        setUpdatorId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatorId")), updatorId));
        setOperatorType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operatorType")), operatorType));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("manId",StringUtils.toString(manId, eiMetadata.getMeta("manId")));
        map.put("bookCode",StringUtils.toString(bookCode, eiMetadata.getMeta("bookCode")));
        map.put("bookName",StringUtils.toString(bookName, eiMetadata.getMeta("bookName")));
        map.put("bookNum",StringUtils.toString(bookNum, eiMetadata.getMeta("bookNum")));
        map.put("bookValidateTime",StringUtils.toString(bookValidateTime, eiMetadata.getMeta("bookValidateTime")));
        map.put("fileId",StringUtils.toString(fileId, eiMetadata.getMeta("fileId")));
        map.put("createDate",StringUtils.toString(createDate, eiMetadata.getMeta("createDate")));
        map.put("creatorName",StringUtils.toString(creatorName, eiMetadata.getMeta("creatorName")));
        map.put("creatorId",StringUtils.toString(creatorId, eiMetadata.getMeta("creatorId")));
        map.put("updateDate",StringUtils.toString(updateDate, eiMetadata.getMeta("updateDate")));
        map.put("updatorName",StringUtils.toString(updatorName, eiMetadata.getMeta("updatorName")));
        map.put("updatorId",StringUtils.toString(updatorId, eiMetadata.getMeta("updatorId")));
        map.put("operatorType",StringUtils.toString(operatorType, eiMetadata.getMeta("operatorType")));
        return map;
    }
}