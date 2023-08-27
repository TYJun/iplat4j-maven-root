/**
* Generate time : 2022-01-18 13:57:24
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* CmSchedule
* 
*/
public class CmSchedule extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 合同进度主键*/
    private String scheduleAutoNo = " ";		/* 合同进度编码*/
    private String scheduleName = " ";		/* 合同进度名称*/
    private String scheduleRemark = " ";		/* 合同进度备注*/
    private String nodeAutoNos = " ";		/* 节点编码多*/
    private String nodeNames = " ";		/* 节点名称多*/
    private String nodeRemarks = " ";		/* 节点备注多*/
    private String recCreatorNo = " ";		/* 创建人工号*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisorNo = " ";		/* 修改人工号*/
    private String recRevisor = " ";		/* 修改人*/
    private String recReviseTime = " ";		/* 修改时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("合同进度主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scheduleAutoNo");
        eiColumn.setDescName("合同进度编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scheduleName");
        eiColumn.setDescName("合同进度名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scheduleRemark");
        eiColumn.setDescName("合同进度备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nodeAutoNos");
        eiColumn.setDescName("节点编码多");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nodeNames");
        eiColumn.setDescName("节点名称多");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nodeRemarks");
        eiColumn.setDescName("节点备注多");
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
    public CmSchedule() {
        initMetaData();
    }

    /**
     * get the id - 合同进度主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 合同进度主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the scheduleAutoNo - 合同进度编码
     * @return the scheduleAutoNo
     */
    public String getScheduleAutoNo() {
        return this.scheduleAutoNo;
    }

    /**
     * set the scheduleAutoNo - 合同进度编码
     */
    public void setScheduleAutoNo(String scheduleAutoNo) {
        this.scheduleAutoNo = scheduleAutoNo;
    }

    /**
     * get the scheduleName - 合同进度名称
     * @return the scheduleName
     */
    public String getScheduleName() {
        return this.scheduleName;
    }

    /**
     * set the scheduleName - 合同进度名称
     */
    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    /**
     * get the scheduleRemark - 合同进度备注
     * @return the scheduleRemark
     */
    public String getScheduleRemark() {
        return this.scheduleRemark;
    }

    /**
     * set the scheduleRemark - 合同进度备注
     */
    public void setScheduleRemark(String scheduleRemark) {
        this.scheduleRemark = scheduleRemark;
    }

    /**
     * get the nodeAutoNos - 节点编码多
     * @return the nodeAutoNos
     */
    public String getNodeAutoNos() {
        return this.nodeAutoNos;
    }

    /**
     * set the nodeAutoNos - 节点编码多
     */
    public void setNodeAutoNos(String nodeAutoNos) {
        this.nodeAutoNos = nodeAutoNos;
    }

    /**
     * get the nodeNames - 节点名称多
     * @return the nodeNames
     */
    public String getNodeNames() {
        return this.nodeNames;
    }

    /**
     * set the nodeNames - 节点名称多
     */
    public void setNodeNames(String nodeNames) {
        this.nodeNames = nodeNames;
    }

    /**
     * get the nodeRemarks - 节点备注多
     * @return the nodeRemarks
     */
    public String getNodeRemarks() {
        return this.nodeRemarks;
    }

    /**
     * set the nodeRemarks - 节点备注多
     */
    public void setNodeRemarks(String nodeRemarks) {
        this.nodeRemarks = nodeRemarks;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setScheduleAutoNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scheduleAutoNo")), scheduleAutoNo));
        setScheduleName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scheduleName")), scheduleName));
        setScheduleRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scheduleRemark")), scheduleRemark));
        setNodeAutoNos(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nodeAutoNos")), nodeAutoNos));
        setNodeNames(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nodeNames")), nodeNames));
        setNodeRemarks(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nodeRemarks")), nodeRemarks));
        setRecCreatorNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreatorNo")), recCreatorNo));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisorNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisorNo")), recRevisorNo));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("scheduleAutoNo",StringUtils.toString(scheduleAutoNo, eiMetadata.getMeta("scheduleAutoNo")));
        map.put("scheduleName",StringUtils.toString(scheduleName, eiMetadata.getMeta("scheduleName")));
        map.put("scheduleRemark",StringUtils.toString(scheduleRemark, eiMetadata.getMeta("scheduleRemark")));
        map.put("nodeAutoNos",StringUtils.toString(nodeAutoNos, eiMetadata.getMeta("nodeAutoNos")));
        map.put("nodeNames",StringUtils.toString(nodeNames, eiMetadata.getMeta("nodeNames")));
        map.put("nodeRemarks",StringUtils.toString(nodeRemarks, eiMetadata.getMeta("nodeRemarks")));
        map.put("recCreatorNo",StringUtils.toString(recCreatorNo, eiMetadata.getMeta("recCreatorNo")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisorNo",StringUtils.toString(recRevisorNo, eiMetadata.getMeta("recRevisorNo")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        return map;
    }
}