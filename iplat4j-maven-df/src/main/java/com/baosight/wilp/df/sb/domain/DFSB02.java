/**
* Generate time : 2021-07-15 17:26:50
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.df.sb.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DfSpecialDevicePart
* 
*/
public class DFSB02 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 设备零部件ID*/
    private String machineCode = " ";		/* 设备零部件编码*/
    private String machineNames = " ";		/* 设备零部件名称*/
    private String machineId = " ";		/* 设备ID*/
    private String models = " ";		/* 规格型号*/
    private String statusCode = " ";		/* 状态代码（0=新建 1=启用，-1=停用）*/
    private String certNo = " ";		/* 合格证编号*/
    private String thisCheckDate = " ";		/* 本次检验日*/
    private String nextCheckDate = " ";		/* 下次检验日*/
    private Integer annualinspcycle = new Integer(0);		/* 周期(月)*/
    private String remark = " ";		/* 备注*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String recReviseTime = " ";		/* 修改时间*/
    private String dataGroupCode = " ";		/* 账套*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("设备零部件ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("machineCode");
        eiColumn.setDescName("设备零部件编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("machineNames");
        eiColumn.setDescName("设备零部件名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("machineId");
        eiColumn.setDescName("设备ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("models");
        eiColumn.setDescName("规格型号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态代码（0=新建 1=启用，-1=停用）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("certNo");
        eiColumn.setDescName("合格证编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("thisCheckDate");
        eiColumn.setDescName("本次检验日");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nextCheckDate");
        eiColumn.setDescName("下次检验日");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("annualinspcycle");
        eiColumn.setDescName("周期(月)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
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


    }

    /**
     * the constructor
     */
    public DFSB02() {
        initMetaData();
    }

    /**
     * get the id - 设备零部件ID
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 设备零部件ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the machineCode - 设备零部件编码
     * @return the machineCode
     */
    public String getMachineCode() {
        return this.machineCode;
    }

    /**
     * set the machineCode - 设备零部件编码
     */
    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    /**
     * get the machineName - 设备零部件名称
     * @return the machineName
     */
    public String getMachineNames() {
        return this.machineNames;
    }

    /**
     * set the machineName - 设备零部件名称
     */
    public void setMachineNames(String machineNames) {
        this.machineNames = machineNames;
    }

    /**
     * get the machineId - 设备ID
     * @return the machineId
     */
    public String getMachineId() {
        return this.machineId;
    }

    /**
     * set the machineId - 设备ID
     */
    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    /**
     * get the models - 规格型号
     * @return the models
     */
    public String getModels() {
        return this.models;
    }

    /**
     * set the models - 规格型号
     */
    public void setModels(String models) {
        this.models = models;
    }

    /**
     * get the statusCode - 状态代码（0=新建 1=启用，-1=停用）
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态代码（0=新建 1=启用，-1=停用）
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the certNo - 合格证编号
     * @return the certNo
     */
    public String getCertNo() {
        return this.certNo;
    }

    /**
     * set the certNo - 合格证编号
     */
    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    /**
     * get the thisCheckDate - 本次检验日
     * @return the thisCheckDate
     */
    public String getThisCheckDate() {
        return this.thisCheckDate;
    }

    /**
     * set the thisCheckDate - 本次检验日
     */
    public void setThisCheckDate(String thisCheckDate) {
        this.thisCheckDate = thisCheckDate;
    }

    /**
     * get the nextCheckDate - 下次检验日
     * @return the nextCheckDate
     */
    public String getNextCheckDate() {
        return this.nextCheckDate;
    }

    /**
     * set the nextCheckDate - 下次检验日
     */
    public void setNextCheckDate(String nextCheckDate) {
        this.nextCheckDate = nextCheckDate;
    }

    /**
     * get the annualinspcycle - 周期(月)
     * @return the annualinspcycle
     */
    public Integer getAnnualinspcycle() {
        return this.annualinspcycle;
    }

    /**
     * set the annualinspcycle - 周期(月)
     */
    public void setAnnualinspcycle(Integer annualinspcycle) {
        this.annualinspcycle = annualinspcycle;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setMachineCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineCode")), machineCode));
        setMachineNames(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineNames")), machineNames));
        setMachineId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineId")), machineId));
        setModels(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("models")), models));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setCertNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("certNo")), certNo));
        setThisCheckDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("thisCheckDate")), thisCheckDate));
        setNextCheckDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nextCheckDate")), nextCheckDate));
        setAnnualinspcycle(NumberUtils.toInteger(StringUtils.toString(map.get("annualinspcycle")), annualinspcycle));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("machineCode",StringUtils.toString(machineCode, eiMetadata.getMeta("machineCode")));
        map.put("machineNames",StringUtils.toString(machineNames, eiMetadata.getMeta("machineNames")));
        map.put("machineId",StringUtils.toString(machineId, eiMetadata.getMeta("machineId")));
        map.put("models",StringUtils.toString(models, eiMetadata.getMeta("models")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("certNo",StringUtils.toString(certNo, eiMetadata.getMeta("certNo")));
        map.put("thisCheckDate",StringUtils.toString(thisCheckDate, eiMetadata.getMeta("thisCheckDate")));
        map.put("nextCheckDate",StringUtils.toString(nextCheckDate, eiMetadata.getMeta("nextCheckDate")));
        map.put("annualinspcycle",StringUtils.toString(annualinspcycle, eiMetadata.getMeta("annualinspcycle")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        return map;
    }
}