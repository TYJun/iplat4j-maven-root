/**
* Generate time : 2021-05-28 16:23:00
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ps.pc.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* PSPCTmealPayType 支付类型实体类
* 
*/
public class PSPCTmealPayType extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String orally = " ";		/* APP/PC,入口*/
    private String canteenType = " ";		/* zgst/bhst,职工食堂/病患食堂*/
    private String payTypeNum = " ";		/* 支付方式编码*/
    private String payTypeName = " ";		/* 支付方式名称*/
    private String status = " ";		/* 状态,00停用/01在用*/
    private String registerTime = " ";		/* 登记时间*/
    private Integer orderNo = new Integer(0);		/* 排序,1最靠前*/
    private String datagroupecode = " ";		/* 账套编码*/
    private String datagroupe = " ";		/* 账套*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orally");
        eiColumn.setDescName("APP/PC,入口");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenType");
        eiColumn.setDescName("zgst/bhst,职工食堂/病患食堂");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payTypeNum");
        eiColumn.setDescName("支付方式编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payTypeName");
        eiColumn.setDescName("支付方式名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("状态,00停用/01在用");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("registerTime");
        eiColumn.setDescName("登记时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderNo");
        eiColumn.setDescName("排序,1最靠前");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("datagroupecode");
        eiColumn.setDescName("账套编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("datagroupe");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PSPCTmealPayType() {
        initMetaData();
    }

    /**
     * get the id 
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the orally - APP/PC,入口
     * @return the orally
     */
    public String getOrally() {
        return this.orally;
    }

    /**
     * set the orally - APP/PC,入口
     */
    public void setOrally(String orally) {
        this.orally = orally;
    }

    /**
     * get the canteenType - zgst/bhst,职工食堂/病患食堂
     * @return the canteenType
     */
    public String getCanteenType() {
        return this.canteenType;
    }

    /**
     * set the canteenType - zgst/bhst,职工食堂/病患食堂
     */
    public void setCanteenType(String canteenType) {
        this.canteenType = canteenType;
    }

    /**
     * get the payTypeNum - 支付方式编码
     * @return the payTypeNum
     */
    public String getPayTypeNum() {
        return this.payTypeNum;
    }

    /**
     * set the payTypeNum - 支付方式编码
     */
    public void setPayTypeNum(String payTypeNum) {
        this.payTypeNum = payTypeNum;
    }

    /**
     * get the payTypeName - 支付方式名称
     * @return the payTypeName
     */
    public String getPayTypeName() {
        return this.payTypeName;
    }

    /**
     * set the payTypeName - 支付方式名称
     */
    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    /**
     * get the status - 状态,00停用/01在用
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status - 状态,00停用/01在用
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * get the registerTime - 登记时间
     * @return the registerTime
     */
    public String getRegisterTime() {
        return this.registerTime;
    }

    /**
     * set the registerTime - 登记时间
     */
    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * get the orderNo - 排序,1最靠前
     * @return the orderNo
     */
    public Integer getOrderNo() {
        return this.orderNo;
    }

    /**
     * set the orderNo - 排序,1最靠前
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * get the datagroupecode - 账套编码
     * @return the datagroupecode
     */
    public String getDatagroupecode() {
        return this.datagroupecode;
    }

    /**
     * set the datagroupecode - 账套编码
     */
    public void setDatagroupecode(String datagroupecode) {
        this.datagroupecode = datagroupecode;
    }

    /**
     * get the datagroupe - 账套
     * @return the datagroupe
     */
    public String getDatagroupe() {
        return this.datagroupe;
    }

    /**
     * set the datagroupe - 账套
     */
    public void setDatagroupe(String datagroupe) {
        this.datagroupe = datagroupe;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setOrally(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("orally")), orally));
        setCanteenType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenType")), canteenType));
        setPayTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payTypeNum")), payTypeNum));
        setPayTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payTypeName")), payTypeName));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setRegisterTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("registerTime")), registerTime));
        setOrderNo(NumberUtils.toInteger(StringUtils.toString(map.get("orderNo")), orderNo));
        setDatagroupecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datagroupecode")), datagroupecode));
        setDatagroupe(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datagroupe")), datagroupe));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("orally",StringUtils.toString(orally, eiMetadata.getMeta("orally")));
        map.put("canteenType",StringUtils.toString(canteenType, eiMetadata.getMeta("canteenType")));
        map.put("payTypeNum",StringUtils.toString(payTypeNum, eiMetadata.getMeta("payTypeNum")));
        map.put("payTypeName",StringUtils.toString(payTypeName, eiMetadata.getMeta("payTypeName")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("registerTime",StringUtils.toString(registerTime, eiMetadata.getMeta("registerTime")));
        map.put("orderNo",StringUtils.toString(orderNo, eiMetadata.getMeta("orderNo")));
        map.put("datagroupecode",StringUtils.toString(datagroupecode, eiMetadata.getMeta("datagroupecode")));
        map.put("datagroupe",StringUtils.toString(datagroupe, eiMetadata.getMeta("datagroupe")));
        return map;
    }
}