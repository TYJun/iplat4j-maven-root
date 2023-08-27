/**
* Generate time : 2022-07-04 16:32:15
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.hi.common.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
* HiClassify
* 
*/
public class HiClassify extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String classifyNum = " ";		/* 标识分类编码*/
    private String classifyName = " ";		/* 标识分类名称*/
    private String parentId = " ";		/* 上级分类id(根节点root)*/
    private String level = " ";		/* 等级*/
    private String isLeaf = " ";		/* 是否是叶子节点（N=不是，Y=是）*/
    private String remark = " ";		/* 备注*/
    private Integer sortNo = new Integer(0);		/* 排序字段*/
    private String recCreator = " ";		/* 创建人*/
    private Timestamp recCreateTime ;		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private Timestamp recReviseTime ;		/* 修改时间*/
    private String dataGroupCode = " ";		/* 账套*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String superClassifyNum = " ";
    private String superClassifyName = " ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classifyNum");
        eiColumn.setDescName("标识分类编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classifyName");
        eiColumn.setDescName("标识分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("parentId");
        eiColumn.setDescName("上级分类id(根节点root)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("level");
        eiColumn.setDescName("等级");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isLeaf");
        eiColumn.setDescName("是否是叶子节点（N=不是，Y=是）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sortNo");
        eiColumn.setDescName("排序字段");
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

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("superClassifyNum");
        eiColumn.setDescName("上级分类编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("superClassifyName");
        eiColumn.setDescName("上级分类名称");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public HiClassify() {
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
     * get the classifyNum - 标识分类编码
     * @return the classifyNum
     */
    public String getClassifyNum() {
        return this.classifyNum;
    }

    /**
     * set the classifyNum - 标识分类编码
     */
    public void setClassifyNum(String classifyNum) {
        this.classifyNum = classifyNum;
    }

    /**
     * get the classifyName - 标识分类名称
     * @return the classifyName
     */
    public String getClassifyName() {
        return this.classifyName;
    }

    /**
     * set the classifyName - 标识分类名称
     */
    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    /**
     * get the parentId - 上级分类id(根节点root)
     * @return the parentId
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * set the parentId - 上级分类id(根节点root)
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * get the level - 等级
     * @return the level
     */
    public String getLevel() {
        return this.level;
    }

    /**
     * set the level - 等级
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * get the isLeaf - 是否是叶子节点（N=不是，Y=是）
     * @return the isLeaf
     */
    public String getIsLeaf() {
        return this.isLeaf;
    }

    /**
     * set the isLeaf - 是否是叶子节点（N=不是，Y=是）
     */
    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * get the remark - 备注
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark - 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * get the sortNo - 排序字段
     * @return the sortNo
     */
    public Integer getSortNo() {
        return this.sortNo;
    }

    /**
     * set the sortNo - 排序字段
     */
    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
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



    public String getSuperClassifyNum() {
        return this.superClassifyNum;
    }
    public void setSuperClassifyNum(String superClassifyNum) {
        this.superClassifyNum = superClassifyNum;
    }


    public String getSuperClassifyName() {
        return this.superClassifyName;
    }
    public void setSuperClassifyName(String superClassifyName) {
        this.superClassifyName = superClassifyName;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setClassifyNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyNum")), classifyNum));
        setClassifyName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyName")), classifyName));
        setParentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parentId")), parentId));
        setLevel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("level")), level));
        setIsLeaf(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isLeaf")), isLeaf));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setSortNo(NumberUtils.toInteger(StringUtils.toString(map.get("sortNo")), sortNo));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("recCreateTime"))));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(DateUtils.toTimestamp(StringUtils.toString(map.get("recReviseTime"))));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setSuperClassifyNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("superClassifyNum")), superClassifyNum));
        setSuperClassifyName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("superClassifyName")), superClassifyName));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("classifyNum",StringUtils.toString(classifyNum, eiMetadata.getMeta("classifyNum")));
        map.put("classifyName",StringUtils.toString(classifyName, eiMetadata.getMeta("classifyName")));
        map.put("parentId",StringUtils.toString(parentId, eiMetadata.getMeta("parentId")));
        map.put("level",StringUtils.toString(level, eiMetadata.getMeta("level")));
        map.put("isLeaf",StringUtils.toString(isLeaf, eiMetadata.getMeta("isLeaf")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("sortNo",StringUtils.toString(sortNo, eiMetadata.getMeta("sortNo")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("superClassifyNum",StringUtils.toString(superClassifyNum, eiMetadata.getMeta("superClassifyNum")));
        map.put("superClassifyName",StringUtils.toString(superClassifyName, eiMetadata.getMeta("superClassifyName")));
        return map;
    }
}