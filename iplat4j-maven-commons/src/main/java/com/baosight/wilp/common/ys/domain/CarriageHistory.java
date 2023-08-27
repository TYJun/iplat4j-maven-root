/**
* Generate time : 2021-02-09 9:14:04
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.ys.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CarriageHistory
* 
*/
public class CarriageHistory extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 运送主表主键（UUID）*/
    private String billId = " ";		/* 运送单编号*/
    private String carriageClassName = " ";		/* 运送类型名称*/
    private String toolNo = " ";		/* 工具编号*/
    private String fromDeptNo = " ";		/* 取件科室编号*/
    private String executeUserNo = " ";		/* 运送执行人工号*/
    private String executeTime = " ";		/* 运送执行时间*/
    private String recCreatorNo = " ";		/* 下单人工号*/
    private String recCreateTime = " ";		/* 下单时间*/
    private String totalExcuteNo = " ";		/* 最终确认人工号*/
    private String totalExcuteTime = " ";		/* 最终确认时间*/
    private String carriageAddressNo = " ";		/* 运送地址编号*/
    private String carriageAddressAcount = " ";		/* 运送地址数量*/
    private String urgent = " ";		/* 是否加急*/
    private String billType = " ";		/* 单据类型APP/PC*/
    private String replenishment = " ";		/* 是否补单*/
    private String freight = " ";		/* 运送物品*/
    private String elapsedTime = " ";		/* 运送耗时*/
    private String reason = " ";		/* 备注回退原因*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("运送主表主键（UUID）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billId");
        eiColumn.setDescName("运送单编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("carriageClassName");
        eiColumn.setDescName("运送类型名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("toolNo");
        eiColumn.setDescName("工具编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fromDeptNo");
        eiColumn.setDescName("取件科室编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("executeUserNo");
        eiColumn.setDescName("运送执行人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("executeTime");
        eiColumn.setDescName("运送执行时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreatorNo");
        eiColumn.setDescName("下单人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("下单时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("totalExcuteNo");
        eiColumn.setDescName("最终确认人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("totalExcuteTime");
        eiColumn.setDescName("最终确认时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("carriageAddressNo");
        eiColumn.setDescName("运送地址编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("carriageAddressAcount");
        eiColumn.setDescName("运送地址数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("urgent");
        eiColumn.setDescName("是否加急");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billType");
        eiColumn.setDescName("单据类型APP/PC");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("replenishment");
        eiColumn.setDescName("是否补单");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("freight");
        eiColumn.setDescName("运送物品");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("elapsedTime");
        eiColumn.setDescName("运送耗时");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reason");
        eiColumn.setDescName("备注回退原因");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CarriageHistory() {
        initMetaData();
    }

    /**
     * get the id - 运送主表主键（UUID）
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 运送主表主键（UUID）
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the billId - 运送单编号
     * @return the billId
     */
    public String getBillId() {
        return this.billId;
    }

    /**
     * set the billId - 运送单编号
     */
    public void setBillId(String billId) {
        this.billId = billId;
    }

    /**
     * get the carriageClassName - 运送类型名称
     * @return the carriageClassName
     */
    public String getCarriageClassName() {
        return this.carriageClassName;
    }

    /**
     * set the carriageClassName - 运送类型名称
     */
    public void setCarriageClassName(String carriageClassName) {
        this.carriageClassName = carriageClassName;
    }

    /**
     * get the toolNo - 工具编号
     * @return the toolNo
     */
    public String getToolNo() {
        return this.toolNo;
    }

    /**
     * set the toolNo - 工具编号
     */
    public void setToolNo(String toolNo) {
        this.toolNo = toolNo;
    }

    /**
     * get the fromDeptNo - 取件科室编号
     * @return the fromDeptNo
     */
    public String getFromDeptNo() {
        return this.fromDeptNo;
    }

    /**
     * set the fromDeptNo - 取件科室编号
     */
    public void setFromDeptNo(String fromDeptNo) {
        this.fromDeptNo = fromDeptNo;
    }

    /**
     * get the executeUserNo - 运送执行人工号
     * @return the executeUserNo
     */
    public String getExecuteUserNo() {
        return this.executeUserNo;
    }

    /**
     * set the executeUserNo - 运送执行人工号
     */
    public void setExecuteUserNo(String executeUserNo) {
        this.executeUserNo = executeUserNo;
    }

    /**
     * get the executeTime - 运送执行时间
     * @return the executeTime
     */
    public String getExecuteTime() {
        return this.executeTime;
    }

    /**
     * set the executeTime - 运送执行时间
     */
    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    /**
     * get the recCreatorNo - 下单人工号
     * @return the recCreatorNo
     */
    public String getRecCreatorNo() {
        return this.recCreatorNo;
    }

    /**
     * set the recCreatorNo - 下单人工号
     */
    public void setRecCreatorNo(String recCreatorNo) {
        this.recCreatorNo = recCreatorNo;
    }

    /**
     * get the recCreateTime - 下单时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 下单时间
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the totalExcuteNo - 最终确认人工号
     * @return the totalExcuteNo
     */
    public String getTotalExcuteNo() {
        return this.totalExcuteNo;
    }

    /**
     * set the totalExcuteNo - 最终确认人工号
     */
    public void setTotalExcuteNo(String totalExcuteNo) {
        this.totalExcuteNo = totalExcuteNo;
    }

    /**
     * get the totalExcuteTime - 最终确认时间
     * @return the totalExcuteTime
     */
    public String getTotalExcuteTime() {
        return this.totalExcuteTime;
    }

    /**
     * set the totalExcuteTime - 最终确认时间
     */
    public void setTotalExcuteTime(String totalExcuteTime) {
        this.totalExcuteTime = totalExcuteTime;
    }

    /**
     * get the carriageAddressNo - 运送地址编号
     * @return the carriageAddressNo
     */
    public String getCarriageAddressNo() {
        return this.carriageAddressNo;
    }

    /**
     * set the carriageAddressNo - 运送地址编号
     */
    public void setCarriageAddressNo(String carriageAddressNo) {
        this.carriageAddressNo = carriageAddressNo;
    }

    /**
     * get the carriageAddressAcount - 运送地址数量
     * @return the carriageAddressAcount
     */
    public String getCarriageAddressAcount() {
        return this.carriageAddressAcount;
    }

    /**
     * set the carriageAddressAcount - 运送地址数量
     */
    public void setCarriageAddressAcount(String carriageAddressAcount) {
        this.carriageAddressAcount = carriageAddressAcount;
    }

    /**
     * get the urgent - 是否加急
     * @return the urgent
     */
    public String getUrgent() {
        return this.urgent;
    }

    /**
     * set the urgent - 是否加急
     */
    public void setUrgent(String urgent) {
        this.urgent = urgent;
    }

    /**
     * get the billType - 单据类型APP/PC
     * @return the billType
     */
    public String getBillType() {
        return this.billType;
    }

    /**
     * set the billType - 单据类型APP/PC
     */
    public void setBillType(String billType) {
        this.billType = billType;
    }

    /**
     * get the replenishment - 是否补单
     * @return the replenishment
     */
    public String getReplenishment() {
        return this.replenishment;
    }

    /**
     * set the replenishment - 是否补单
     */
    public void setReplenishment(String replenishment) {
        this.replenishment = replenishment;
    }

    /**
     * get the freight - 运送物品
     * @return the freight
     */
    public String getFreight() {
        return this.freight;
    }

    /**
     * set the freight - 运送物品
     */
    public void setFreight(String freight) {
        this.freight = freight;
    }

    /**
     * get the elapsedTime - 运送耗时
     * @return the elapsedTime
     */
    public String getElapsedTime() {
        return this.elapsedTime;
    }

    /**
     * set the elapsedTime - 运送耗时
     */
    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    /**
     * get the reason - 备注回退原因
     * @return the reason
     */
    public String getReason() {
        return this.reason;
    }

    /**
     * set the reason - 备注回退原因
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setBillId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billId")), billId));
        setCarriageClassName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("carriageClassName")), carriageClassName));
        setToolNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("toolNo")), toolNo));
        setFromDeptNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fromDeptNo")), fromDeptNo));
        setExecuteUserNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("executeUserNo")), executeUserNo));
        setExecuteTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("executeTime")), executeTime));
        setRecCreatorNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreatorNo")), recCreatorNo));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setTotalExcuteNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("totalExcuteNo")), totalExcuteNo));
        setTotalExcuteTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("totalExcuteTime")), totalExcuteTime));
        setCarriageAddressNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("carriageAddressNo")), carriageAddressNo));
        setCarriageAddressAcount(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("carriageAddressAcount")), carriageAddressAcount));
        setUrgent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("urgent")), urgent));
        setBillType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billType")), billType));
        setReplenishment(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("replenishment")), replenishment));
        setFreight(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("freight")), freight));
        setElapsedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("elapsedTime")), elapsedTime));
        setReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reason")), reason));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("billId",StringUtils.toString(billId, eiMetadata.getMeta("billId")));
        map.put("carriageClassName",StringUtils.toString(carriageClassName, eiMetadata.getMeta("carriageClassName")));
        map.put("toolNo",StringUtils.toString(toolNo, eiMetadata.getMeta("toolNo")));
        map.put("fromDeptNo",StringUtils.toString(fromDeptNo, eiMetadata.getMeta("fromDeptNo")));
        map.put("executeUserNo",StringUtils.toString(executeUserNo, eiMetadata.getMeta("executeUserNo")));
        map.put("executeTime",StringUtils.toString(executeTime, eiMetadata.getMeta("executeTime")));
        map.put("recCreatorNo",StringUtils.toString(recCreatorNo, eiMetadata.getMeta("recCreatorNo")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("totalExcuteNo",StringUtils.toString(totalExcuteNo, eiMetadata.getMeta("totalExcuteNo")));
        map.put("totalExcuteTime",StringUtils.toString(totalExcuteTime, eiMetadata.getMeta("totalExcuteTime")));
        map.put("carriageAddressNo",StringUtils.toString(carriageAddressNo, eiMetadata.getMeta("carriageAddressNo")));
        map.put("carriageAddressAcount",StringUtils.toString(carriageAddressAcount, eiMetadata.getMeta("carriageAddressAcount")));
        map.put("urgent",StringUtils.toString(urgent, eiMetadata.getMeta("urgent")));
        map.put("billType",StringUtils.toString(billType, eiMetadata.getMeta("billType")));
        map.put("replenishment",StringUtils.toString(replenishment, eiMetadata.getMeta("replenishment")));
        map.put("freight",StringUtils.toString(freight, eiMetadata.getMeta("freight")));
        map.put("elapsedTime",StringUtils.toString(elapsedTime, eiMetadata.getMeta("elapsedTime")));
        map.put("reason",StringUtils.toString(reason, eiMetadata.getMeta("reason")));
        return map;
    }
}