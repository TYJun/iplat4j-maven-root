/**
* Generate time : 2022-12-17 23:33:17
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.fa.cf.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import java.math.BigDecimal;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.fa.utils.OneSelfUtils;

/**
* FaSplitDetailVO
* 
*/
public class FaSplitDetailVO extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 拆分主键*/
    private String splitNo = " ";		/* 拆分单号*/
    private String fainfoId = " ";		/* 资产主键*/
    private String goodsNum = " ";		/* 资产编码*/
    private String goodsName = " ";		/* 资产名称*/
    private BigDecimal buycost = new BigDecimal("0");		/* 资产原值*/
    private BigDecimal totalDepreciation = new BigDecimal("0");		/* 累计折旧*/
    private BigDecimal netAssetValue = new BigDecimal("0");		/* 资产净值*/
    private Timestamp splitTime;
    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("拆分主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("splitNo");
        eiColumn.setDescName("拆分单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fainfoId");
        eiColumn.setDescName("资产主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("goodsNum");
        eiColumn.setDescName("资产编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("goodsName");
        eiColumn.setDescName("资产名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("buycost");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("资产原值");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("totalDepreciation");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("累计折旧");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("netAssetValue");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("资产净值");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("splitTime");
        eiColumn.setDescName("拆分时间");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public FaSplitDetailVO() {
        initMetaData();
    }

    /**
     * get the id - 拆分主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 拆分主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the splitNo - 拆分单号
     * @return the splitNo
     */
    public String getSplitNo() {
        return this.splitNo;
    }

    /**
     * set the splitNo - 拆分单号
     */
    public void setSplitNo(String splitNo) {
        this.splitNo = splitNo;
    }

    /**
     * get the fainfoId - 资产主键
     * @return the fainfoId
     */
    public String getFainfoId() {
        return this.fainfoId;
    }

    /**
     * set the fainfoId - 资产主键
     */
    public void setFainfoId(String fainfoId) {
        this.fainfoId = fainfoId;
    }

    /**
     * get the goodsNum - 资产编码
     * @return the goodsNum
     */
    public String getGoodsNum() {
        return this.goodsNum;
    }

    /**
     * set the goodsNum - 资产编码
     */
    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    /**
     * get the goodsName - 资产名称
     * @return the goodsName
     */
    public String getGoodsName() {
        return this.goodsName;
    }

    /**
     * set the goodsName - 资产名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * get the buycost - 资产原值
     * @return the buycost
     */
    public BigDecimal getBuycost() {
        return this.buycost;
    }

    /**
     * set the buycost - 资产原值
     */
    public void setBuycost(BigDecimal buycost) {
        this.buycost = buycost;
    }

    /**
     * get the totalDepreciation - 累计折旧
     * @return the totalDepreciation
     */
    public BigDecimal getTotalDepreciation() {
        return this.totalDepreciation;
    }

    /**
     * set the totalDepreciation - 累计折旧
     */
    public void setTotalDepreciation(BigDecimal totalDepreciation) {
        this.totalDepreciation = totalDepreciation;
    }

    /**
     * get the netAssetValue - 资产净值
     * @return the netAssetValue
     */
    public BigDecimal getNetAssetValue() {
        return this.netAssetValue;
    }

    /**
     * set the netAssetValue - 资产净值
     */
    public void setNetAssetValue(BigDecimal netAssetValue) {
        this.netAssetValue = netAssetValue;
    }

    public Timestamp getSplitTime() {
        return splitTime;
    }

    public void setSplitTime(Timestamp splitTime) {
        this.splitTime = splitTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setSplitNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("splitNo")), splitNo));
        setFainfoId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fainfoId")), fainfoId));
        setGoodsNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsNum")), goodsNum));
        setGoodsName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsName")), goodsName));
        setBuycost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("buycost")), buycost));
        setTotalDepreciation(NumberUtils.toBigDecimal(StringUtils.toString(map.get("totalDepreciation")), totalDepreciation));
        setNetAssetValue(NumberUtils.toBigDecimal(StringUtils.toString(map.get("netAssetValue")), netAssetValue));
        setSplitTime(OneSelfUtils.defaultTimestamp(map.get("splitTime")));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("splitNo",StringUtils.toString(splitNo, eiMetadata.getMeta("splitNo")));
        map.put("fainfoId",StringUtils.toString(fainfoId, eiMetadata.getMeta("fainfoId")));
        map.put("goodsNum",StringUtils.toString(goodsNum, eiMetadata.getMeta("goodsNum")));
        map.put("goodsName",StringUtils.toString(goodsName, eiMetadata.getMeta("goodsName")));
        map.put("buycost",StringUtils.toString(buycost, eiMetadata.getMeta("buycost")));
        map.put("totalDepreciation",StringUtils.toString(totalDepreciation, eiMetadata.getMeta("totalDepreciation")));
        map.put("netAssetValue",StringUtils.toString(netAssetValue, eiMetadata.getMeta("netAssetValue")));
        map.put("splitTime",StringUtils.toString(splitTime, eiMetadata.getMeta("splitTime")));
        return map;
    }
}