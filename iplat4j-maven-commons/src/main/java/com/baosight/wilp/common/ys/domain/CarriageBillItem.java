/**
* Generate time : 2021-02-09 9:13:21
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.ys.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CarriageBillItem
* 
*/
public class CarriageBillItem extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 运送条目表主键（UUID）*/
    private String billId = " ";		/* 运送主表主键*/
    private String dectedClass = " ";		/* 运送地址编号*/
    private String dectedClassName = " ";		/* 运送地址*/
    private String addressOrderNo = " ";		/* 运送地址排序*/
    private String executeUserNo = " ";		/* 运送执行人工号*/
    private String freight = " ";		/* 运送物品*/
    private String acceptTime = " ";		/* 运送接单时间*/
    private String finishTime = " ";		/* 运送完成时间*/
    private String elapsedTime = " ";		/* 运送耗时*/
    private String dispathNo = " ";		/* 运送派工人工单*/
    private String dispathTime = " ";		/* 运送派单时间*/
    private String billStatus = " ";		/* 状态*/
    private String billItemNo = " ";		/* 批次号*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("运送条目表主键（UUID）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billId");
        eiColumn.setDescName("运送主表主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dectedClass");
        eiColumn.setDescName("运送地址编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dectedClassName");
        eiColumn.setDescName("运送地址");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("addressOrderNo");
        eiColumn.setDescName("运送地址排序");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("executeUserNo");
        eiColumn.setDescName("运送执行人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("freight");
        eiColumn.setDescName("运送物品");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("acceptTime");
        eiColumn.setDescName("运送接单时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finishTime");
        eiColumn.setDescName("运送完成时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("elapsedTime");
        eiColumn.setDescName("运送耗时");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dispathNo");
        eiColumn.setDescName("运送派工人工单");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dispathTime");
        eiColumn.setDescName("运送派单时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billStatus");
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billItemNo");
        eiColumn.setDescName("批次号");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CarriageBillItem() {
        initMetaData();
    }

    /**
     * get the id - 运送条目表主键（UUID）
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 运送条目表主键（UUID）
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the billId - 运送主表主键
     * @return the billId
     */
    public String getBillId() {
        return this.billId;
    }

    /**
     * set the billId - 运送主表主键
     */
    public void setBillId(String billId) {
        this.billId = billId;
    }

    /**
     * get the dectedClass - 运送地址编号
     * @return the dectedClass
     */
    public String getDectedClass() {
        return this.dectedClass;
    }

    /**
     * set the dectedClass - 运送地址编号
     */
    public void setDectedClass(String dectedClass) {
        this.dectedClass = dectedClass;
    }

    /**
     * get the dectedClassName - 运送地址
     * @return the dectedClassName
     */
    public String getDectedClassName() {
        return this.dectedClassName;
    }

    /**
     * set the dectedClassName - 运送地址
     */
    public void setDectedClassName(String dectedClassName) {
        this.dectedClassName = dectedClassName;
    }

    /**
     * get the addressOrderNo - 运送地址排序
     * @return the addressOrderNo
     */
    public String getAddressOrderNo() {
        return this.addressOrderNo;
    }

    /**
     * set the addressOrderNo - 运送地址排序
     */
    public void setAddressOrderNo(String addressOrderNo) {
        this.addressOrderNo = addressOrderNo;
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
     * get the acceptTime - 运送接单时间
     * @return the acceptTime
     */
    public String getAcceptTime() {
        return this.acceptTime;
    }

    /**
     * set the acceptTime - 运送接单时间
     */
    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    /**
     * get the finishTime - 运送完成时间
     * @return the finishTime
     */
    public String getFinishTime() {
        return this.finishTime;
    }

    /**
     * set the finishTime - 运送完成时间
     */
    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
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
     * get the dispathNo - 运送派工人工单
     * @return the dispathNo
     */
    public String getDispathNo() {
        return this.dispathNo;
    }

    /**
     * set the dispathNo - 运送派工人工单
     */
    public void setDispathNo(String dispathNo) {
        this.dispathNo = dispathNo;
    }

    /**
     * get the dispathTime - 运送派单时间
     * @return the dispathTime
     */
    public String getDispathTime() {
        return this.dispathTime;
    }

    /**
     * set the dispathTime - 运送派单时间
     */
    public void setDispathTime(String dispathTime) {
        this.dispathTime = dispathTime;
    }

    /**
     * get the billStatus - 状态
     * @return the billStatus
     */
    public String getBillStatus() {
        return this.billStatus;
    }

    /**
     * set the billStatus - 状态
     */
    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    /**
     * get the billItemNo - 批次号
     * @return the billItemNo
     */
    public String getBillItemNo() {
        return this.billItemNo;
    }

    /**
     * set the billItemNo - 批次号
     */
    public void setBillItemNo(String billItemNo) {
        this.billItemNo = billItemNo;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setBillId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billId")), billId));
        setDectedClass(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dectedClass")), dectedClass));
        setDectedClassName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dectedClassName")), dectedClassName));
        setAddressOrderNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("addressOrderNo")), addressOrderNo));
        setExecuteUserNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("executeUserNo")), executeUserNo));
        setFreight(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("freight")), freight));
        setAcceptTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("acceptTime")), acceptTime));
        setFinishTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("finishTime")), finishTime));
        setElapsedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("elapsedTime")), elapsedTime));
        setDispathNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dispathNo")), dispathNo));
        setDispathTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dispathTime")), dispathTime));
        setBillStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billStatus")), billStatus));
        setBillItemNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billItemNo")), billItemNo));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("billId",StringUtils.toString(billId, eiMetadata.getMeta("billId")));
        map.put("dectedClass",StringUtils.toString(dectedClass, eiMetadata.getMeta("dectedClass")));
        map.put("dectedClassName",StringUtils.toString(dectedClassName, eiMetadata.getMeta("dectedClassName")));
        map.put("addressOrderNo",StringUtils.toString(addressOrderNo, eiMetadata.getMeta("addressOrderNo")));
        map.put("executeUserNo",StringUtils.toString(executeUserNo, eiMetadata.getMeta("executeUserNo")));
        map.put("freight",StringUtils.toString(freight, eiMetadata.getMeta("freight")));
        map.put("acceptTime",StringUtils.toString(acceptTime, eiMetadata.getMeta("acceptTime")));
        map.put("finishTime",StringUtils.toString(finishTime, eiMetadata.getMeta("finishTime")));
        map.put("elapsedTime",StringUtils.toString(elapsedTime, eiMetadata.getMeta("elapsedTime")));
        map.put("dispathNo",StringUtils.toString(dispathNo, eiMetadata.getMeta("dispathNo")));
        map.put("dispathTime",StringUtils.toString(dispathTime, eiMetadata.getMeta("dispathTime")));
        map.put("billStatus",StringUtils.toString(billStatus, eiMetadata.getMeta("billStatus")));
        map.put("billItemNo",StringUtils.toString(billItemNo, eiMetadata.getMeta("billItemNo")));
        return map;
    }
}