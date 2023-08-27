/**
* Generate time : 2021-04-06 14:18:42
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cm.domain;

import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * 
 * 合同主表附件实体类
 * 
 * @Title: ContractBillFile.java
 * @ClassName: ContractBillFile
 * @Package：com.baosight.wilp.cm.domain
 * @author: zhaow
 * @date: 2021年8月30日 下午2:41:36
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ContractBillFile extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 项目附件主键*/
    private String contPk = " ";		/* 项目主键*/
    private String attachId = " ";		/* 附件ID*/
    private String attachName = " ";		/* 附件名称*/
    private String attachPath = " ";		/* 附件路径*/
    private Double attachSize = new Double(0);		/* 附件大小*/
    private String attachDesc = " ";		/* 附件说明*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String datagroupCode = " ";		/* 账套*/
    private String recCreator = " ";		/* 记录创建责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("项目附件主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contPk");
        eiColumn.setDescName("项目主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("attachId");
        eiColumn.setDescName("附件ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("attachName");
        eiColumn.setDescName("附件名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("attachPath");
        eiColumn.setDescName("附件路径");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("attachSize");
        eiColumn.setDescName("附件大小");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("attachDesc");
        eiColumn.setDescName("附件说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("datagroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("记录创建责任者");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("记录创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("记录修改责任者");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("记录修改时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ContractBillFile() {
        initMetaData();
    }

    /**
     * get the id - 项目附件主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 项目附件主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the contPk - 项目主键
     * @return the contPk
     */
    public String getContPk() {
        return this.contPk;
    }

    /**
     * set the contPk - 项目主键
     */
    public void setContPk(String contPk) {
        this.contPk = contPk;
    }

    /**
     * get the attachId - 附件ID
     * @return the attachId
     */
    public String getAttachId() {
        return this.attachId;
    }

    /**
     * set the attachId - 附件ID
     */
    public void setAttachId(String attachId) {
        this.attachId = attachId;
    }

    /**
     * get the attachName - 附件名称
     * @return the attachName
     */
    public String getAttachName() {
        return this.attachName;
    }

    /**
     * set the attachName - 附件名称
     */
    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    /**
     * get the attachPath - 附件路径
     * @return the attachPath
     */
    public String getAttachPath() {
        return this.attachPath;
    }

    /**
     * set the attachPath - 附件路径
     */
    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }

    /**
     * get the attachSize - 附件大小
     * @return the attachSize
     */
    public Double getAttachSize() {
        return this.attachSize;
    }

    /**
     * set the attachSize - 附件大小
     */
    public void setAttachSize(Double attachSize) {
        this.attachSize = attachSize;
    }

    /**
     * get the attachDesc - 附件说明
     * @return the attachDesc
     */
    public String getAttachDesc() {
        return this.attachDesc;
    }

    /**
     * set the attachDesc - 附件说明
     */
    public void setAttachDesc(String attachDesc) {
        this.attachDesc = attachDesc;
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
     * get the datagroupCode - 账套
     * @return the datagroupCode
     */
    public String getDatagroupCode() {
        return this.datagroupCode;
    }

    /**
     * set the datagroupCode - 账套
     */
    public void setDatagroupCode(String datagroupCode) {
        this.datagroupCode = datagroupCode;
    }

    /**
     * get the recCreator - 记录创建责任者
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 记录创建责任者
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
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
     * get the recRevisor - 记录修改责任者
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 记录修改责任者
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
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
        setContPk(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contPk")), contPk));
        setAttachId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("attachId")), attachId));
        setAttachName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("attachName")), attachName));
        setAttachPath(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("attachPath")), attachPath));
        setAttachSize(NumberUtils.toDouble(StringUtils.toString(map.get("attachSize")), attachSize));
        setAttachDesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("attachDesc")), attachDesc));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setDatagroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datagroupCode")), datagroupCode));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("contPk",StringUtils.toString(contPk, eiMetadata.getMeta("contPk")));
        map.put("attachId",StringUtils.toString(attachId, eiMetadata.getMeta("attachId")));
        map.put("attachName",StringUtils.toString(attachName, eiMetadata.getMeta("attachName")));
        map.put("attachPath",StringUtils.toString(attachPath, eiMetadata.getMeta("attachPath")));
        map.put("attachSize",StringUtils.toString(attachSize, eiMetadata.getMeta("attachSize")));
        map.put("attachDesc",StringUtils.toString(attachDesc, eiMetadata.getMeta("attachDesc")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("datagroupCode",StringUtils.toString(datagroupCode, eiMetadata.getMeta("datagroupCode")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        return map;
    }
}