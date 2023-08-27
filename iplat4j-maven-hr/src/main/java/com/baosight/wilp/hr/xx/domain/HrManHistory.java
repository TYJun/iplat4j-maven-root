/**
* Generate time : 2022-03-18 12:44:05
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.hr.xx.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
* HrManHistory
* 
*/
public class HrManHistory extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键ID*/
    private String manId = " ";		/* 人员信息表ID*/
    private String operatorType = " ";		/* 类型（入职、调岗、离职...）*/
    private String firstTime = " ";		/* 开始时间*/
    private String lastTime = " ";		/* 结束时间*/
    private String operatormanId = " ";		/* 操作人ID*/
    private String operatormanName = " ";		/* 操作人姓名*/
    private Timestamp operatorTime ;		/* 操作时间*/
    private Integer successFlag = new Integer(0);		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manId");
        eiColumn.setDescName("人员信息表ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operatorType");
        eiColumn.setDescName("类型（入职、调岗、离职...）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("firstTime");
        eiColumn.setDescName("开始时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastTime");
        eiColumn.setDescName("结束时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operatormanId");
        eiColumn.setDescName("操作人ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operatormanName");
        eiColumn.setDescName("操作人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operatorTime");
        eiColumn.setDescName("操作时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("successFlag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public HrManHistory() {
        initMetaData();
    }

    /**
     * get the id - 主键ID
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 主键ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the manId - 人员信息表ID
     * @return the manId
     */
    public String getManId() {
        return this.manId;
    }

    /**
     * set the manId - 人员信息表ID
     */
    public void setManId(String manId) {
        this.manId = manId;
    }

    /**
     * get the operatorType - 类型（入职、调岗、离职...）
     * @return the operatorType
     */
    public String getOperatorType() {
        return this.operatorType;
    }

    /**
     * set the operatorType - 类型（入职、调岗、离职...）
     */
    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    /**
     * get the firstTime - 开始时间
     * @return the firstTime
     */
    public String getFirstTime() {
        return this.firstTime;
    }

    /**
     * set the firstTime - 开始时间
     */
    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    /**
     * get the lastTime - 结束时间
     * @return the lastTime
     */
    public String getLastTime() {
        return this.lastTime;
    }

    /**
     * set the lastTime - 结束时间
     */
    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * get the operatormanId - 操作人ID
     * @return the operatormanId
     */
    public String getOperatormanId() {
        return this.operatormanId;
    }

    /**
     * set the operatormanId - 操作人ID
     */
    public void setOperatormanId(String operatormanId) {
        this.operatormanId = operatormanId;
    }

    /**
     * get the operatormanName - 操作人姓名
     * @return the operatormanName
     */
    public String getOperatormanName() {
        return this.operatormanName;
    }

    /**
     * set the operatormanName - 操作人姓名
     */
    public void setOperatormanName(String operatormanName) {
        this.operatormanName = operatormanName;
    }

    /**
     * get the operatorTime - 操作时间
     * @return the operatorTime
     */
    public Timestamp getOperatorTime() {
        return this.operatorTime;
    }

    /**
     * set the operatorTime - 操作时间
     */
    public void setOperatorTime(Timestamp operatorTime) {
        this.operatorTime = operatorTime;
    }

    /**
     * get the successFlag 
     * @return the successFlag
     */
    public Integer getSuccessFlag() {
        return this.successFlag;
    }

    /**
     * set the successFlag 
     */
    public void setSuccessFlag(Integer successFlag) {
        this.successFlag = successFlag;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setManId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manId")), manId));
        setOperatorType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operatorType")), operatorType));
        setFirstTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("firstTime")), firstTime));
        setLastTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lastTime")), lastTime));
        setOperatormanId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operatormanId")), operatormanId));
        setOperatormanName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operatormanName")), operatormanName));
        setOperatorTime(DateUtils.toTimestamp(StringUtils.toString(map.get("operatorTime"))));
        setSuccessFlag(NumberUtils.toInteger(StringUtils.toString(map.get("successFlag")), successFlag));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("manId",StringUtils.toString(manId, eiMetadata.getMeta("manId")));
        map.put("operatorType",StringUtils.toString(operatorType, eiMetadata.getMeta("operatorType")));
        map.put("firstTime",StringUtils.toString(firstTime, eiMetadata.getMeta("firstTime")));
        map.put("lastTime",StringUtils.toString(lastTime, eiMetadata.getMeta("lastTime")));
        map.put("operatormanId",StringUtils.toString(operatormanId, eiMetadata.getMeta("operatormanId")));
        map.put("operatormanName",StringUtils.toString(operatormanName, eiMetadata.getMeta("operatormanName")));
        map.put("operatorTime",StringUtils.toString(operatorTime, eiMetadata.getMeta("operatorTime")));
        map.put("successFlag",StringUtils.toString(successFlag, eiMetadata.getMeta("successFlag")));
        return map;
    }
}