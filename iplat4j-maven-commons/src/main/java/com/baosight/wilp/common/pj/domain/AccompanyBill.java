/**
* Generate time : 2021-03-23 14:36:26
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.pj.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* AccompanyBill
* 
*/
public class AccompanyBill extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String accompanyNo = " ";		/* 陪检单号*/
    private String inpatientWard = " ";		/* 病区*/
    private String bedNo = " ";		/* 床号*/
    private String patient = " ";		/* 病人*/
    private String accompanyProjectNo = " ";		/* 陪检项目编号*/
    private String accompanyProject = " ";		/* 陪检项目*/
    private String accompanyStatus = " ";		/* 工单状态*/
    private String recCreateNo = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String isReturn = " ";		/* 是否自回*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("accompanyNo");
        eiColumn.setDescName("陪检单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("inpatientWard");
        eiColumn.setDescName("病区");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bedNo");
        eiColumn.setDescName("床号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("patient");
        eiColumn.setDescName("病人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("accompanyProjectNo");
        eiColumn.setDescName("陪检项目编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("accompanyProject");
        eiColumn.setDescName("陪检项目");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("accompanyStatus");
        eiColumn.setDescName("工单状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateNo");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isReturn");
        eiColumn.setDescName("是否自回");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public AccompanyBill() {
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
     * get the accompanyNo - 陪检单号
     * @return the accompanyNo
     */
    public String getAccompanyNo() {
        return this.accompanyNo;
    }

    /**
     * set the accompanyNo - 陪检单号
     */
    public void setAccompanyNo(String accompanyNo) {
        this.accompanyNo = accompanyNo;
    }

    /**
     * get the inpatientWard - 病区
     * @return the inpatientWard
     */
    public String getInpatientWard() {
        return this.inpatientWard;
    }

    /**
     * set the inpatientWard - 病区
     */
    public void setInpatientWard(String inpatientWard) {
        this.inpatientWard = inpatientWard;
    }

    /**
     * get the bedNo - 床号
     * @return the bedNo
     */
    public String getBedNo() {
        return this.bedNo;
    }

    /**
     * set the bedNo - 床号
     */
    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    /**
     * get the patient - 病人
     * @return the patient
     */
    public String getPatient() {
        return this.patient;
    }

    /**
     * set the patient - 病人
     */
    public void setPatient(String patient) {
        this.patient = patient;
    }

    /**
     * get the accompanyProjectNo - 陪检项目编号
     * @return the accompanyProjectNo
     */
    public String getAccompanyProjectNo() {
        return this.accompanyProjectNo;
    }

    /**
     * set the accompanyProjectNo - 陪检项目编号
     */
    public void setAccompanyProjectNo(String accompanyProjectNo) {
        this.accompanyProjectNo = accompanyProjectNo;
    }

    /**
     * get the accompanyProject - 陪检项目
     * @return the accompanyProject
     */
    public String getAccompanyProject() {
        return this.accompanyProject;
    }

    /**
     * set the accompanyProject - 陪检项目
     */
    public void setAccompanyProject(String accompanyProject) {
        this.accompanyProject = accompanyProject;
    }

    /**
     * get the accompanyStatus - 工单状态
     * @return the accompanyStatus
     */
    public String getAccompanyStatus() {
        return this.accompanyStatus;
    }

    /**
     * set the accompanyStatus - 工单状态
     */
    public void setAccompanyStatus(String accompanyStatus) {
        this.accompanyStatus = accompanyStatus;
    }

    /**
     * get the recCreateNo - 创建人
     * @return the recCreateNo
     */
    public String getRecCreateNo() {
        return this.recCreateNo;
    }

    /**
     * set the recCreateNo - 创建人
     */
    public void setRecCreateNo(String recCreateNo) {
        this.recCreateNo = recCreateNo;
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
     * get the isReturn - 是否自回
     * @return the isReturn
     */
    public String getIsReturn() {
        return this.isReturn;
    }

    /**
     * set the isReturn - 是否自回
     */
    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setAccompanyNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("accompanyNo")), accompanyNo));
        setInpatientWard(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("inpatientWard")), inpatientWard));
        setBedNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bedNo")), bedNo));
        setPatient(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("patient")), patient));
        setAccompanyProjectNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("accompanyProjectNo")), accompanyProjectNo));
        setAccompanyProject(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("accompanyProject")), accompanyProject));
        setAccompanyStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("accompanyStatus")), accompanyStatus));
        setRecCreateNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateNo")), recCreateNo));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setIsReturn(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isReturn")), isReturn));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("accompanyNo",StringUtils.toString(accompanyNo, eiMetadata.getMeta("accompanyNo")));
        map.put("inpatientWard",StringUtils.toString(inpatientWard, eiMetadata.getMeta("inpatientWard")));
        map.put("bedNo",StringUtils.toString(bedNo, eiMetadata.getMeta("bedNo")));
        map.put("patient",StringUtils.toString(patient, eiMetadata.getMeta("patient")));
        map.put("accompanyProjectNo",StringUtils.toString(accompanyProjectNo, eiMetadata.getMeta("accompanyProjectNo")));
        map.put("accompanyProject",StringUtils.toString(accompanyProject, eiMetadata.getMeta("accompanyProject")));
        map.put("accompanyStatus",StringUtils.toString(accompanyStatus, eiMetadata.getMeta("accompanyStatus")));
        map.put("recCreateNo",StringUtils.toString(recCreateNo, eiMetadata.getMeta("recCreateNo")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("isReturn",StringUtils.toString(isReturn, eiMetadata.getMeta("isReturn")));
        return map;
    }
}