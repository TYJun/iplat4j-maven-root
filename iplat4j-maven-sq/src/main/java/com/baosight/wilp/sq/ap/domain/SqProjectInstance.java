/**
* Generate time : 2021-08-02 14:31:43
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.sq.ap.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.DateUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* SqProjectInstance
* 
*/
public class SqProjectInstance extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String projectId = " ";		/* 项目外键*/
    private String projectCode = " ";		/* 项目编码*/
    private String instanceCode = " ";		/* 检查标准编码*/
    private String instanceName = " ";		/* 检查标准名称*/
    private String instanceRemark = " ";		/* 检查标准备注*/
    private BigDecimal point = new BigDecimal(0);		/* 分数*/
    private String creator = " ";		/* 记录创建人*/
    private Timestamp createTime ;		/* 创建时间*/
    private String modifier = " ";		/* 记录修改人*/
    private Timestamp modiftTime ;		/* 修改时间*/
    private String pointType = " ";		/* 计分方式(0/打分 1/选择)*/
    private Integer orderNumber = new Integer(0);		/* 排序*/
    private List<?> radioList = null;
    

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectId");
        eiColumn.setDescName("项目外键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectCode");
        eiColumn.setDescName("项目编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("instanceCode");
        eiColumn.setDescName("检查标准编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("instanceName");
        eiColumn.setDescName("检查标准名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("instanceRemark");
        eiColumn.setDescName("检查标准备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("point");
        eiColumn.setDescName("分数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setDescName("记录创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifier");
        eiColumn.setDescName("记录修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modiftTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("pointType");
        eiColumn.setDescName("计分方式(0/打分 1/选择)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderNumber");
        eiColumn.setDescName("排序");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SqProjectInstance() {
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
     * get the projectId - 项目外键
     * @return the projectId
     */
    public String getProjectId() {
        return this.projectId;
    }

    /**
     * set the projectId - 项目外键
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * get the projectCode - 项目编码
     * @return the projectCode
     */
    public String getProjectCode() {
        return this.projectCode;
    }

    /**
     * set the projectCode - 项目编码
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    /**
     * get the instanceCode - 检查标准编码
     * @return the instanceCode
     */
    public String getInstanceCode() {
        return this.instanceCode;
    }

    /**
     * set the instanceCode - 检查标准编码
     */
    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    /**
     * get the instanceName - 检查标准名称
     * @return the instanceName
     */
    public String getInstanceName() {
        return this.instanceName;
    }

    /**
     * set the instanceName - 检查标准名称
     */
    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    /**
     * get the instanceRemark - 检查标准备注
     * @return the instanceRemark
     */
    public String getInstanceRemark() {
        return this.instanceRemark;
    }

    /**
     * set the instanceRemark - 检查标准备注
     */
    public void setInstanceRemark(String instanceRemark) {
        this.instanceRemark = instanceRemark;
    }

    /**
     * get the point - 分数
     * @return the point
     */
    public BigDecimal getPoint() {
        return this.point;
    }

    /**
     * set the point - 分数
     */
    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    /**
     * get the creator - 记录创建人
     * @return the creator
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * set the creator - 记录创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * get the createTime - 创建时间
     * @return the createTime
     */
    public Timestamp getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime - 创建时间
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * get the modifier - 记录修改人
     * @return the modifier
     */
    public String getModifier() {
        return this.modifier;
    }

    /**
     * set the modifier - 记录修改人
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * get the modiftTime - 修改时间
     * @return the modiftTime
     */
    public Timestamp getModiftTime() {
        return this.modiftTime;
    }

    /**
     * set the modiftTime - 修改时间
     */
    public void setModiftTime(Timestamp modiftTime) {
        this.modiftTime = modiftTime;
    }

    /**
     * get the pointType - 计分方式(0/打分 1/选择)
     * @return the pointType
     */
    public String getPointType() {
        return this.pointType;
    }

    /**
     * set the pointType - 计分方式(0/打分 1/选择)
     */
    public void setPointType(String pointType) {
        this.pointType = pointType;
    }

    /**
     * get the orderNumber - 排序
     * @return the orderNumber
     */
    public Integer getOrderNumber() {
        return this.orderNumber;
    }

    /**
     * set the orderNumber - 排序
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<?> getRadioList() {
        return radioList;
    }

    public void setRadioList(List<?> radioList) {
        this.radioList = radioList;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setProjectId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectId")), projectId));
        setProjectCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectCode")), projectCode));
        setInstanceCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("instanceCode")), instanceCode));
        setInstanceName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("instanceName")), instanceName));
        setInstanceRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("instanceRemark")), instanceRemark));
        setPoint(NumberUtils.toBigDecimal(StringUtils.toString(map.get("point")), point));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("createTime"))));
        setModifier(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifier")), modifier));
        setModiftTime(DateUtils.toTimestamp(StringUtils.toString(map.get("modiftTime"))));
        setPointType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("pointType")), pointType));
        setOrderNumber(NumberUtils.toInteger(StringUtils.toString(map.get("orderNumber")), orderNumber));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("projectId",StringUtils.toString(projectId, eiMetadata.getMeta("projectId")));
        map.put("projectCode",StringUtils.toString(projectCode, eiMetadata.getMeta("projectCode")));
        map.put("instanceCode",StringUtils.toString(instanceCode, eiMetadata.getMeta("instanceCode")));
        map.put("instanceName",StringUtils.toString(instanceName, eiMetadata.getMeta("instanceName")));
        map.put("instanceRemark",StringUtils.toString(instanceRemark, eiMetadata.getMeta("instanceRemark")));
        map.put("point",StringUtils.toString(point, eiMetadata.getMeta("point")));
        map.put("creator",StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("modifier",StringUtils.toString(modifier, eiMetadata.getMeta("modifier")));
        map.put("modiftTime",StringUtils.toString(modiftTime, eiMetadata.getMeta("modiftTime")));
        map.put("pointType",StringUtils.toString(pointType, eiMetadata.getMeta("pointType")));
        map.put("orderNumber",StringUtils.toString(orderNumber, eiMetadata.getMeta("orderNumber")));
        return map;
    }
}