/**
* Generate time : 2022-05-18 13:29:40
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.df.common.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DfClean
* 
*/
public class DfClean extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String cleanNo = " ";		/* 保洁单号*/
    private String machineId = " ";		/* 设备ID*/
    private String machineCode = " ";		/* 设备编码*/
    private String machineName = " ";		/* 设备名称*/
    private String fixedPlace = " ";		/* 安装地点*/
    private String cleanDeptNum = " ";		/* 负责单位编码*/
    private String cleanDeptName = " ";		/* 负责单位名称*/
    private String deptManageNo = " ";		/* 负责人编码*/
    private String deptManageName = " ";		/* 负责人姓名*/
    private String cleanDate = " ";		/* 保洁日期*/
    private String remark = " ";		/* 作业说明*/
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
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cleanNo");
        eiColumn.setDescName("保洁单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("machineId");
        eiColumn.setDescName("设备ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("machineCode");
        eiColumn.setDescName("设备编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("machineName");
        eiColumn.setDescName("设备名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fixedPlace");
        eiColumn.setDescName("安装地点");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cleanDeptNum");
        eiColumn.setDescName("负责单位编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cleanDeptName");
        eiColumn.setDescName("负责单位名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptManageNo");
        eiColumn.setDescName("负责人编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptManageName");
        eiColumn.setDescName("负责人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cleanDate");
        eiColumn.setDescName("保洁日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("作业说明");
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
    public DfClean() {
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
     * get the cleanNo - 保洁单号
     * @return the cleanNo
     */
    public String getCleanNo() {
        return this.cleanNo;
    }

    /**
     * set the cleanNo - 保洁单号
     */
    public void setCleanNo(String cleanNo) {
        this.cleanNo = cleanNo;
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
     * get the machineCode - 设备编码
     * @return the machineCode
     */
    public String getMachineCode() {
        return this.machineCode;
    }

    /**
     * set the machineCode - 设备编码
     */
    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    /**
     * get the machineName - 设备名称
     * @return the machineName
     */
    public String getMachineName() {
        return this.machineName;
    }

    /**
     * set the machineName - 设备名称
     */
    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    /**
     * get the fixedPlace - 安装地点
     * @return the fixedPlace
     */
    public String getFixedPlace() {
        return this.fixedPlace;
    }

    /**
     * set the fixedPlace - 安装地点
     */
    public void setFixedPlace(String fixedPlace) {
        this.fixedPlace = fixedPlace;
    }

    /**
     * get the cleanDeptNum - 负责单位编码
     * @return the cleanDeptNum
     */
    public String getCleanDeptNum() {
        return this.cleanDeptNum;
    }

    /**
     * set the cleanDeptNum - 负责单位编码
     */
    public void setCleanDeptNum(String cleanDeptNum) {
        this.cleanDeptNum = cleanDeptNum;
    }

    /**
     * get the cleanDeptName - 负责单位名称
     * @return the cleanDeptName
     */
    public String getCleanDeptName() {
        return this.cleanDeptName;
    }

    /**
     * set the cleanDeptName - 负责单位名称
     */
    public void setCleanDeptName(String cleanDeptName) {
        this.cleanDeptName = cleanDeptName;
    }

    /**
     * get the deptManageNo - 负责人编码
     * @return the deptManageNo
     */
    public String getDeptManageNo() {
        return this.deptManageNo;
    }

    /**
     * set the deptManageNo - 负责人编码
     */
    public void setDeptManageNo(String deptManageNo) {
        this.deptManageNo = deptManageNo;
    }

    /**
     * get the deptManageName - 负责人姓名
     * @return the deptManageName
     */
    public String getDeptManageName() {
        return this.deptManageName;
    }

    /**
     * set the deptManageName - 负责人姓名
     */
    public void setDeptManageName(String deptManageName) {
        this.deptManageName = deptManageName;
    }

    /**
     * get the cleanDate - 保洁日期
     * @return the cleanDate
     */
    public String getCleanDate() {
        return this.cleanDate;
    }

    /**
     * set the cleanDate - 保洁日期
     */
    public void setCleanDate(String cleanDate) {
        this.cleanDate = cleanDate;
    }

    /**
     * get the remark - 作业说明
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark - 作业说明
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
        setCleanNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cleanNo")), cleanNo));
        setMachineId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineId")), machineId));
        setMachineCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineCode")), machineCode));
        setMachineName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineName")), machineName));
        setFixedPlace(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fixedPlace")), fixedPlace));
        setCleanDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cleanDeptNum")), cleanDeptNum));
        setCleanDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cleanDeptName")), cleanDeptName));
        setDeptManageNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptManageNo")), deptManageNo));
        setDeptManageName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptManageName")), deptManageName));
        setCleanDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cleanDate")), cleanDate));
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
        map.put("cleanNo",StringUtils.toString(cleanNo, eiMetadata.getMeta("cleanNo")));
        map.put("machineId",StringUtils.toString(machineId, eiMetadata.getMeta("machineId")));
        map.put("machineCode",StringUtils.toString(machineCode, eiMetadata.getMeta("machineCode")));
        map.put("machineName",StringUtils.toString(machineName, eiMetadata.getMeta("machineName")));
        map.put("fixedPlace",StringUtils.toString(fixedPlace, eiMetadata.getMeta("fixedPlace")));
        map.put("cleanDeptNum",StringUtils.toString(cleanDeptNum, eiMetadata.getMeta("cleanDeptNum")));
        map.put("cleanDeptName",StringUtils.toString(cleanDeptName, eiMetadata.getMeta("cleanDeptName")));
        map.put("deptManageNo",StringUtils.toString(deptManageNo, eiMetadata.getMeta("deptManageNo")));
        map.put("deptManageName",StringUtils.toString(deptManageName, eiMetadata.getMeta("deptManageName")));
        map.put("cleanDate",StringUtils.toString(cleanDate, eiMetadata.getMeta("cleanDate")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        return map;
    }
}