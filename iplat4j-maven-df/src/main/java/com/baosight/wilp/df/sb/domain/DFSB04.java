/**
* Generate time : 2021-07-15 17:26:50
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.df.sb.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DfSpecialPlan
* 
*/
public class DFSB04 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 检校计划主键ID*/
    private String taskNo = " ";		/* 工单号*/
    private String machineId = " ";		/* 设备id*/
    private String machineCode = " ";		/* 检验设备编码*/
    private String machineName = " ";		/* 检验设备名称*/
    private String innerMachineCode = " ";		/* 内部设备编码*/
    private String checkType = " ";		/* 检校类别编码*/
    private String thisCheckDate ;		/* 本次检验日*/
    private String thisFinishDate ;		/* 本次检验完工日*/
    private String nextCheckDate ;		/* 下次检验日*/
    private String statusCode = " ";		/* 工单状态(0=新建，1=待审核，1=已审核)*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改作人*/
    private String recReviseTime = " ";		/* 修改时间*/
    private String dataGroupCode = " ";		/* 账套*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("检校计划主键ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taskNo");
        eiColumn.setDescName("工单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("machineId");
        eiColumn.setDescName("设备id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("machineCode");
        eiColumn.setDescName("检验设备编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("machineName");
        eiColumn.setDescName("检验设备名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("innerMachineCode");
        eiColumn.setDescName("内部设备编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("checkType");
        eiColumn.setDescName("检校类别编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("thisCheckDate");
        eiColumn.setDescName("本次检验日");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("thisFinishDate");
        eiColumn.setDescName("本次检验完工日");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nextCheckDate");
        eiColumn.setDescName("下次检验日");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("工单状态(0=新建，1=待审核，1=已审核)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改作人");
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
    public DFSB04() {
        initMetaData();
    }

    /**
     * get the id - 检校计划主键ID
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 检校计划主键ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the taskNo - 工单号
     * @return the taskNo
     */
    public String getTaskNo() {
        return this.taskNo;
    }

    /**
     * set the taskNo - 工单号
     */
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * get the machineId - 设备id
     * @return the machineId
     */
    public String getMachineId() {
        return this.machineId;
    }

    /**
     * set the machineId - 设备id
     */
    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    /**
     * get the machineCode - 检验设备编码
     * @return the machineCode
     */
    public String getMachineCode() {
        return this.machineCode;
    }

    /**
     * set the machineCode - 检验设备编码
     */
    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    /**
     * get the machineName - 检验设备名称
     * @return the machineName
     */
    public String getMachineName() {
        return this.machineName;
    }

    /**
     * set the machineName - 检验设备名称
     */
    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    /**
     * get the innerMachineCode - 内部设备编码
     * @return the innerMachineCode
     */
    public String getInnerMachineCode() {
        return this.innerMachineCode;
    }

    /**
     * set the innerMachineCode - 内部设备编码
     */
    public void setInnerMachineCode(String innerMachineCode) {
        this.innerMachineCode = innerMachineCode;
    }

    /**
     * get the checkType - 检校类别编码
     * @return the checkType
     */
    public String getCheckType() {
        return this.checkType;
    }

    /**
     * set the checkType - 检校类别编码
     */
    public void setCheckType(String checkType) {
        this.checkType = checkType;
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
     * get the thisFinishDate - 本次检验完工日
     * @return the thisFinishDate
     */
    public String getThisFinishDate() {
        return this.thisFinishDate;
    }

    /**
     * set the thisFinishDate - 本次检验完工日
     */
    public void setThisFinishDate(String thisFinishDate) {
        this.thisFinishDate = thisFinishDate;
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
     * get the statusCode - 工单状态(0=新建，1=待审核，1=已审核)
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 工单状态(0=新建，1=待审核，1=已审核)
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
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
     * get the recRevisor - 修改作人
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 修改作人
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
        setTaskNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskNo")), taskNo));
        setMachineId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineId")), machineId));
        setMachineCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineCode")), machineCode));
        setMachineName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineName")), machineName));
        setInnerMachineCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("innerMachineCode")), innerMachineCode));
        setCheckType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("checkType")), checkType));
        setThisCheckDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("thisCheckDate")), thisCheckDate));
        setThisFinishDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("thisFinishDate")), thisFinishDate));
        setNextCheckDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nextCheckDate")), nextCheckDate));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
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
        map.put("taskNo",StringUtils.toString(taskNo, eiMetadata.getMeta("taskNo")));
        map.put("machineId",StringUtils.toString(machineId, eiMetadata.getMeta("machineId")));
        map.put("machineCode",StringUtils.toString(machineCode, eiMetadata.getMeta("machineCode")));
        map.put("machineName",StringUtils.toString(machineName, eiMetadata.getMeta("machineName")));
        map.put("innerMachineCode",StringUtils.toString(innerMachineCode, eiMetadata.getMeta("innerMachineCode")));
        map.put("checkType",StringUtils.toString(checkType, eiMetadata.getMeta("checkType")));
        map.put("thisCheckDate",StringUtils.toString(thisCheckDate, eiMetadata.getMeta("thisCheckDate")));
        map.put("thisFinishDate",StringUtils.toString(thisFinishDate, eiMetadata.getMeta("thisFinishDate")));
        map.put("nextCheckDate",StringUtils.toString(nextCheckDate, eiMetadata.getMeta("nextCheckDate")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        return map;
    }
}