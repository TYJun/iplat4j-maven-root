/**
* Generate time : 2021-01-27 16:18:19
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.ys.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* Tbmbd07
* 
*/
public class Tbmbd07 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String recReviseTime = " ";		/* 修改时间*/
    private String achiveFlag = " ";		/* 归档标记*/
    private String matTypeNum = " ";		/* 物资分类编码*/
    private String matTypeName = " ";		/* 物资分类名称*/
    private String superMatTypeNum = " ";		/* 上级物资分类编码*/
    private String superMatTypeName = " ";		/* 上级物资分类名称*/
    private String parentId = " ";		/* 上级物资分类ID*/
    private String matClassTypeNum = " ";		/* 物资大类编码*/
    private String matClassTypeName = " ";		/* 物资大类名称*/
    private String jpMatTypeName = " ";		/* 物资分类名简拼*/
    private String stopFlag = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
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

        eiColumn = new EiColumn("achiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeNum");
        eiColumn.setDescName("物资分类编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeName");
        eiColumn.setDescName("物资分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("superMatTypeNum");
        eiColumn.setDescName("上级物资分类编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("superMatTypeName");
        eiColumn.setDescName("上级物资分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("parentId");
        eiColumn.setDescName("上级物资分类ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matClassTypeNum");
        eiColumn.setDescName("物资大类编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matClassTypeName");
        eiColumn.setDescName("物资大类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("jpMatTypeName");
        eiColumn.setDescName("物资分类名简拼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("stopFlag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tbmbd07() {
        initMetaData();
    }

    /**
     * get the id 
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id 
     */
    public void setId(String id) {
        this.id = id;
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
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(String recCreateTime) {
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
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the achiveFlag - 归档标记
     * @return the achiveFlag
     */
    public String getAchiveFlag() {
        return this.achiveFlag;
    }

    /**
     * set the achiveFlag - 归档标记
     */
    public void setAchiveFlag(String achiveFlag) {
        this.achiveFlag = achiveFlag;
    }

    /**
     * get the matTypeNum - 物资分类编码
     * @return the matTypeNum
     */
    public String getMatTypeNum() {
        return this.matTypeNum;
    }

    /**
     * set the matTypeNum - 物资分类编码
     */
    public void setMatTypeNum(String matTypeNum) {
        this.matTypeNum = matTypeNum;
    }

    /**
     * get the matTypeName - 物资分类名称
     * @return the matTypeName
     */
    public String getMatTypeName() {
        return this.matTypeName;
    }

    /**
     * set the matTypeName - 物资分类名称
     */
    public void setMatTypeName(String matTypeName) {
        this.matTypeName = matTypeName;
    }

    /**
     * get the superMatTypeNum - 上级物资分类编码
     * @return the superMatTypeNum
     */
    public String getSuperMatTypeNum() {
        return this.superMatTypeNum;
    }

    /**
     * set the superMatTypeNum - 上级物资分类编码
     */
    public void setSuperMatTypeNum(String superMatTypeNum) {
        this.superMatTypeNum = superMatTypeNum;
    }

    /**
     * get the superMatTypeName - 上级物资分类名称
     * @return the superMatTypeName
     */
    public String getSuperMatTypeName() {
        return this.superMatTypeName;
    }

    /**
     * set the superMatTypeName - 上级物资分类名称
     */
    public void setSuperMatTypeName(String superMatTypeName) {
        this.superMatTypeName = superMatTypeName;
    }

    /**
     * get the parentId - 上级物资分类ID
     * @return the parentId
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * set the parentId - 上级物资分类ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * get the matClassTypeNum - 物资大类编码
     * @return the matClassTypeNum
     */
    public String getMatClassTypeNum() {
        return this.matClassTypeNum;
    }

    /**
     * set the matClassTypeNum - 物资大类编码
     */
    public void setMatClassTypeNum(String matClassTypeNum) {
        this.matClassTypeNum = matClassTypeNum;
    }

    /**
     * get the matClassTypeName - 物资大类名称
     * @return the matClassTypeName
     */
    public String getMatClassTypeName() {
        return this.matClassTypeName;
    }

    /**
     * set the matClassTypeName - 物资大类名称
     */
    public void setMatClassTypeName(String matClassTypeName) {
        this.matClassTypeName = matClassTypeName;
    }

    /**
     * get the jpMatTypeName - 物资分类名简拼
     * @return the jpMatTypeName
     */
    public String getJpMatTypeName() {
        return this.jpMatTypeName;
    }

    /**
     * set the jpMatTypeName - 物资分类名简拼
     */
    public void setJpMatTypeName(String jpMatTypeName) {
        this.jpMatTypeName = jpMatTypeName;
    }

    /**
     * get the stopFlag 
     * @return the stopFlag
     */
    public String getStopFlag() {
        return this.stopFlag;
    }

    /**
     * set the stopFlag 
     */
    public void setStopFlag(String stopFlag) {
        this.stopFlag = stopFlag;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setAchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("achiveFlag")), achiveFlag));
        setMatTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matTypeNum")), matTypeNum));
        setMatTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matTypeName")), matTypeName));
        setSuperMatTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("superMatTypeNum")), superMatTypeNum));
        setSuperMatTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("superMatTypeName")), superMatTypeName));
        setParentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parentId")), parentId));
        setMatClassTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matClassTypeNum")), matClassTypeNum));
        setMatClassTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matClassTypeName")), matClassTypeName));
        setJpMatTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("jpMatTypeName")), jpMatTypeName));
        setStopFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("stopFlag")), stopFlag));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("achiveFlag",StringUtils.toString(achiveFlag, eiMetadata.getMeta("achiveFlag")));
        map.put("matTypeNum",StringUtils.toString(matTypeNum, eiMetadata.getMeta("matTypeNum")));
        map.put("matTypeName",StringUtils.toString(matTypeName, eiMetadata.getMeta("matTypeName")));
        map.put("superMatTypeNum",StringUtils.toString(superMatTypeNum, eiMetadata.getMeta("superMatTypeNum")));
        map.put("superMatTypeName",StringUtils.toString(superMatTypeName, eiMetadata.getMeta("superMatTypeName")));
        map.put("parentId",StringUtils.toString(parentId, eiMetadata.getMeta("parentId")));
        map.put("matClassTypeNum",StringUtils.toString(matClassTypeNum, eiMetadata.getMeta("matClassTypeNum")));
        map.put("matClassTypeName",StringUtils.toString(matClassTypeName, eiMetadata.getMeta("matClassTypeName")));
        map.put("jpMatTypeName",StringUtils.toString(jpMatTypeName, eiMetadata.getMeta("jpMatTypeName")));
        map.put("stopFlag",StringUtils.toString(stopFlag, eiMetadata.getMeta("stopFlag")));
        return map;
    }
}