/**
* Generate time : 2022-05-17 9:33:37
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.df.common.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DfScrap
* 
*/
public class DfScrap extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String scrapNo = " ";		/* 报废单号*/
    private String machineId = " ";		/* 设备ID*/
    private String machineCode = " ";		/* 设备编码*/
    private String machineName = " ";		/* 设备名称*/
    private String fixedPlace = " ";		/* 安装地点*/
    private String status = " ";		/* 状态*/
    private String scrapWay = " ";		/* 报废方式*/
    private String scrapDate = " ";		/* 报废日期*/
    private String scrapReason = " ";		/* 报废原因*/
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

        eiColumn = new EiColumn("scrapNo");
        eiColumn.setDescName("报废单号");
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

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scrapWay");
        eiColumn.setDescName("报废方式");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scrapDate");
        eiColumn.setDescName("报废日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scrapReason");
        eiColumn.setDescName("报废原因");
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
    public DfScrap() {
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
     * get the scrapNo - 报废单号
     * @return the scrapNo
     */
    public String getScrapNo() {
        return this.scrapNo;
    }

    /**
     * set the scrapNo - 报废单号
     */
    public void setScrapNo(String scrapNo) {
        this.scrapNo = scrapNo;
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
     * get the status - 状态
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status - 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * get the scrapWay - 报废方式
     * @return the scrapWay
     */
    public String getScrapWay() {
        return this.scrapWay;
    }

    /**
     * set the scrapWay - 报废方式
     */
    public void setScrapWay(String scrapWay) {
        this.scrapWay = scrapWay;
    }

    /**
     * get the scrapDate - 报废日期
     * @return the scrapDate
     */
    public String getScrapDate() {
        return this.scrapDate;
    }

    /**
     * set the scrapDate - 报废日期
     */
    public void setScrapDate(String scrapDate) {
        this.scrapDate = scrapDate;
    }

    /**
     * get the scrapReason - 报废原因
     * @return the scrapReason
     */
    public String getScrapReason() {
        return this.scrapReason;
    }

    /**
     * set the scrapReason - 报废原因
     */
    public void setScrapReason(String scrapReason) {
        this.scrapReason = scrapReason;
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
        setScrapNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scrapNo")), scrapNo));
        setMachineId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineId")), machineId));
        setMachineCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineCode")), machineCode));
        setMachineName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineName")), machineName));
        setFixedPlace(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fixedPlace")), fixedPlace));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setScrapWay(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scrapWay")), scrapWay));
        setScrapDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scrapDate")), scrapDate));
        setScrapReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scrapReason")), scrapReason));
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
        map.put("scrapNo",StringUtils.toString(scrapNo, eiMetadata.getMeta("scrapNo")));
        map.put("machineId",StringUtils.toString(machineId, eiMetadata.getMeta("machineId")));
        map.put("machineCode",StringUtils.toString(machineCode, eiMetadata.getMeta("machineCode")));
        map.put("machineName",StringUtils.toString(machineName, eiMetadata.getMeta("machineName")));
        map.put("fixedPlace",StringUtils.toString(fixedPlace, eiMetadata.getMeta("fixedPlace")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("scrapWay",StringUtils.toString(scrapWay, eiMetadata.getMeta("scrapWay")));
        map.put("scrapDate",StringUtils.toString(scrapDate, eiMetadata.getMeta("scrapDate")));
        map.put("scrapReason",StringUtils.toString(scrapReason, eiMetadata.getMeta("scrapReason")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        return map;
    }
}