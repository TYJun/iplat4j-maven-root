/**
* Generate time : 2022-03-24 1:57:39
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.fj.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DormsRoomAttachment
* 
*/
public class DormsRoomAttachment extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 附件主键*/
    private String manId = " ";		/* 人员入住信息表人员id*/
    private String attachmentId = " ";		/* 附件ID*/
    private String attachmentName = " ";		/* 附件名称*/
    private String attachmentPath = " ";		/* 附件路径*/
    private Double attachmentSize = new Double(0);		/* 附件大小*/
    private String attachmentDesc = " ";		/* 附件说明*/
    private String attachmentFlag = " ";		/* 归档标记*/
    private String recCreator = " ";		/* 记录创建责任者工号*/
    private String recCreateName = " ";		/* 记录创建责任者姓名*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改责任者工号*/
    private String recReviseName = " ";		/* 记录修改责任者姓名*/
    private String recReviseTime = " ";		/* 记录修改时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("附件主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manId");
        eiColumn.setDescName("人员入住信息表人员id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("attachmentId");
        eiColumn.setDescName("附件ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("attachmentName");
        eiColumn.setDescName("附件名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("attachmentPath");
        eiColumn.setDescName("附件路径");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("attachmentSize");
        eiColumn.setDescName("附件大小");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("attachmentDesc");
        eiColumn.setDescName("附件说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("attachmentFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("记录创建责任者工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateName");
        eiColumn.setDescName("记录创建责任者姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("记录创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("记录修改责任者工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseName");
        eiColumn.setDescName("记录修改责任者姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("记录修改时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DormsRoomAttachment() {
        initMetaData();
    }

    /**
     * get the id - 附件主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 附件主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the manId - 人员入住信息表人员id
     * @return the manId
     */
    public String getManId() {
        return this.manId;
    }

    /**
     * set the manId - 人员入住信息表人员id
     */
    public void setManId(String manId) {
        this.manId = manId;
    }

    /**
     * get the attachmentId - 附件ID
     * @return the attachmentId
     */
    public String getAttachmentId() {
        return this.attachmentId;
    }

    /**
     * set the attachmentId - 附件ID
     */
    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    /**
     * get the attachmentName - 附件名称
     * @return the attachmentName
     */
    public String getAttachmentName() {
        return this.attachmentName;
    }

    /**
     * set the attachmentName - 附件名称
     */
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    /**
     * get the attachmentPath - 附件路径
     * @return the attachmentPath
     */
    public String getAttachmentPath() {
        return this.attachmentPath;
    }

    /**
     * set the attachmentPath - 附件路径
     */
    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    /**
     * get the attachmentSize - 附件大小
     * @return the attachmentSize
     */
    public Double getAttachmentSize() {
        return this.attachmentSize;
    }

    /**
     * set the attachmentSize - 附件大小
     */
    public void setAttachmentSize(Double attachmentSize) {
        this.attachmentSize = attachmentSize;
    }

    /**
     * get the attachmentDesc - 附件说明
     * @return the attachmentDesc
     */
    public String getAttachmentDesc() {
        return this.attachmentDesc;
    }

    /**
     * set the attachmentDesc - 附件说明
     */
    public void setAttachmentDesc(String attachmentDesc) {
        this.attachmentDesc = attachmentDesc;
    }

    /**
     * get the attachmentFlag - 归档标记
     * @return the attachmentFlag
     */
    public String getAttachmentFlag() {
        return this.attachmentFlag;
    }

    /**
     * set the attachmentFlag - 归档标记
     */
    public void setAttachmentFlag(String attachmentFlag) {
        this.attachmentFlag = attachmentFlag;
    }

    /**
     * get the recCreator - 记录创建责任者工号
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 记录创建责任者工号
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateName - 记录创建责任者姓名
     * @return the recCreateName
     */
    public String getRecCreateName() {
        return this.recCreateName;
    }

    /**
     * set the recCreateName - 记录创建责任者姓名
     */
    public void setRecCreateName(String recCreateName) {
        this.recCreateName = recCreateName;
    }

    /**
     * get the recCreateTime - 记录创建时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 记录创建时间
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevisor - 记录修改责任者工号
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 记录修改责任者工号
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseName - 记录修改责任者姓名
     * @return the recReviseName
     */
    public String getRecReviseName() {
        return this.recReviseName;
    }

    /**
     * set the recReviseName - 记录修改责任者姓名
     */
    public void setRecReviseName(String recReviseName) {
        this.recReviseName = recReviseName;
    }

    /**
     * get the recReviseTime - 记录修改时间
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 记录修改时间
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setManId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manId")), manId));
        setAttachmentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("attachmentId")), attachmentId));
        setAttachmentName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("attachmentName")), attachmentName));
        setAttachmentPath(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("attachmentPath")), attachmentPath));
        setAttachmentSize(NumberUtils.toDouble(StringUtils.toString(map.get("attachmentSize")), attachmentSize));
        setAttachmentDesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("attachmentDesc")), attachmentDesc));
        setAttachmentFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("attachmentFlag")), attachmentFlag));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateName")), recCreateName));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseName")), recReviseName));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("manId",StringUtils.toString(manId, eiMetadata.getMeta("manId")));
        map.put("attachmentId",StringUtils.toString(attachmentId, eiMetadata.getMeta("attachmentId")));
        map.put("attachmentName",StringUtils.toString(attachmentName, eiMetadata.getMeta("attachmentName")));
        map.put("attachmentPath",StringUtils.toString(attachmentPath, eiMetadata.getMeta("attachmentPath")));
        map.put("attachmentSize",StringUtils.toString(attachmentSize, eiMetadata.getMeta("attachmentSize")));
        map.put("attachmentDesc",StringUtils.toString(attachmentDesc, eiMetadata.getMeta("attachmentDesc")));
        map.put("attachmentFlag",StringUtils.toString(attachmentFlag, eiMetadata.getMeta("attachmentFlag")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateName",StringUtils.toString(recCreateName, eiMetadata.getMeta("recCreateName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseName",StringUtils.toString(recReviseName, eiMetadata.getMeta("recReviseName")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        return map;
    }
}