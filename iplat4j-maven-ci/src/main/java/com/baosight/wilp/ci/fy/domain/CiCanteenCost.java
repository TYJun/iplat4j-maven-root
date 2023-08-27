/**
* Generate time : 2022-06-10 15:22:22
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ci.fy.domain;


import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CiCanteenCost
* 
*/
public class CiCanteenCost extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* id*/
    private String date = " " ;		/* 日期*/
    private String canteenNum = " ";		/* 食堂编码*/
    private String canteenName = " ";		/* 食堂名称*/
    private String canteenType = " ";		/* 食堂类型*/
    private BigDecimal water = new BigDecimal("0");		/* 水费*/
    private BigDecimal electricity = new BigDecimal("0");		/* 电费*/
    private BigDecimal gas = new BigDecimal("0");		/* 气费*/
    private BigDecimal labour = new BigDecimal("0");		/* 人工费*/
    private BigDecimal additionalCosts = new BigDecimal("0");		/* 额外费用*/
    private String memo = " ";		/* 备注*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("date");
        eiColumn.setDescName("日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName("食堂编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenName");
        eiColumn.setDescName("食堂名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenType");
        eiColumn.setDescName("食堂类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("water");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(16);
        eiColumn.setDescName("水费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("electricity");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("电费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("gas");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(16);
        eiColumn.setDescName("气费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("labour");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(16);
        eiColumn.setDescName("人工费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("additionalCosts");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(16);
        eiColumn.setDescName("额外费用");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("memo");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CiCanteenCost() {
        initMetaData();
    }

    /**
     * get the id - id
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the date - 日期
     * @return the date
     */
    public String getDate() {
        return this.date;
    }

    /**
     * set the date - 日期
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * get the canteenNum - 食堂编码
     * @return the canteenNum
     */
    public String getCanteenNum() {
        return this.canteenNum;
    }

    /**
     * set the canteenNum - 食堂编码
     */
    public void setCanteenNum(String canteenNum) {
        this.canteenNum = canteenNum;
    }

    /**
     * get the canteenName - 食堂名称
     * @return the canteenName
     */
    public String getCanteenName() {
        return this.canteenName;
    }

    /**
     * set the canteenName - 食堂名称
     */
    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    /**
     * get the canteenType - 食堂类型
     * @return the canteenType
     */
    public String getCanteenType() {
        return this.canteenType;
    }

    /**
     * set the canteenType - 食堂类型
     */
    public void setCanteenType(String canteenType) {
        this.canteenType = canteenType;
    }

    /**
     * get the water - 水费
     * @return the water
     */
    public BigDecimal getWater() {
        return this.water;
    }

    /**
     * set the water - 水费
     */
    public void setWater(BigDecimal water) {
        this.water = water;
    }

    /**
     * get the electricity - 电费
     * @return the electricity
     */
    public BigDecimal getElectricity() {
        return this.electricity;
    }

    /**
     * set the electricity - 电费
     */
    public void setElectricity(BigDecimal electricity) {
        this.electricity = electricity;
    }

    /**
     * get the gas - 气费
     * @return the gas
     */
    public BigDecimal getGas() {
        return this.gas;
    }

    /**
     * set the gas - 气费
     */
    public void setGas(BigDecimal gas) {
        this.gas = gas;
    }

    /**
     * get the labour - 人工费
     * @return the labour
     */
    public BigDecimal getLabour() {
        return this.labour;
    }

    /**
     * set the labour - 人工费
     */
    public void setLabour(BigDecimal labour) {
        this.labour = labour;
    }

    /**
     * get the additionalCosts - 额外费用
     * @return the additionalCosts
     */
    public BigDecimal getAdditionalCosts() {
        return this.additionalCosts;
    }

    /**
     * set the additionalCosts - 额外费用
     */
    public void setAdditionalCosts(BigDecimal additionalCosts) {
        this.additionalCosts = additionalCosts;
    }

    /**
     * get the memo - 备注
     * @return the memo
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * set the memo - 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("date")), date));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
        setCanteenName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenName")), canteenName));
        setCanteenType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenType")), canteenType));
        setWater(NumberUtils.toBigDecimal(StringUtils.toString(map.get("water")), water));
        setElectricity(NumberUtils.toBigDecimal(StringUtils.toString(map.get("electricity")), electricity));
        setGas(NumberUtils.toBigDecimal(StringUtils.toString(map.get("gas")), gas));
        setLabour(NumberUtils.toBigDecimal(StringUtils.toString(map.get("labour")), labour));
        setAdditionalCosts(NumberUtils.toBigDecimal(StringUtils.toString(map.get("additionalCosts")), additionalCosts));
        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("date",StringUtils.toString(date, eiMetadata.getMeta("date")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        map.put("canteenName",StringUtils.toString(canteenName, eiMetadata.getMeta("canteenName")));
        map.put("canteenType",StringUtils.toString(canteenType, eiMetadata.getMeta("canteenType")));
        map.put("water",StringUtils.toString(water, eiMetadata.getMeta("water")));
        map.put("electricity",StringUtils.toString(electricity, eiMetadata.getMeta("electricity")));
        map.put("gas",StringUtils.toString(gas, eiMetadata.getMeta("gas")));
        map.put("labour",StringUtils.toString(labour, eiMetadata.getMeta("labour")));
        map.put("additionalCosts",StringUtils.toString(additionalCosts, eiMetadata.getMeta("additionalCosts")));
        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
        return map;
    }
}