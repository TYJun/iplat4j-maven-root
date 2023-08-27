/**
* Generate time : 2022-09-21 21:01:02
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.fa.zj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
* FaDepreciation
* 
*/
public class FaDepreciation extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 固定资产折旧表主键*/
    private String goodId = " ";		/* 固资Id*/
    private String deptNum = "";
    private String deptName = "";
    private String goodsNum = "";
    private String goodsName = "";
    private String model = "";
    private String spec = "";
    private BigDecimal buyCost = new BigDecimal("0");;
    private String depreciationMonth = " ";		/* 折旧月份*/
    private Integer usedMonth = new Integer(0);		/* 已使用时间*/
    private BigDecimal depreciationValue = new BigDecimal("0");		/* 本月折旧*/
    private BigDecimal totalDepreciation = new BigDecimal("0");		/* 累计折旧*/
    private BigDecimal netAssetValue = new BigDecimal("0");		/* 资产净值*/
    private BigDecimal monthDepreciation = new BigDecimal("0"); /* 平均月折旧*/
    private String fundingSourceNum = "";
    private String fundingSourceName = "";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("固定资产折旧表主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("goodsNum");
        eiColumn.setDescName("资产编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("goodsName");
        eiColumn.setDescName("资产名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("model");
        eiColumn.setDescName("规格");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spec");
        eiColumn.setDescName("型号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("buyCost");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(18);
        eiColumn.setDescName("资产原值");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("depreciationMonth");
        eiColumn.setDescName("折旧月份");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("usedMonth");
        eiColumn.setDescName("已使用时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("depreciationValue");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(18);
        eiColumn.setDescName("本月折旧");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("totalDepreciation");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(18);
        eiColumn.setDescName("累计折旧");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("netAssetValue");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(18);
        eiColumn.setDescName("资产净值");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monthDepreciation");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(18);
        eiColumn.setDescName("平均月折旧");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fundingSourceNum");
        eiColumn.setDescName("资金来源编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fundingSourceName");
        eiColumn.setDescName("资金来源");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public FaDepreciation() {
        initMetaData();
    }

    /**
     * get the id - 固定资产折旧表主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 固定资产折旧表主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the goodId - 固资Id
     * @return the goodId
     */
    public String getGoodId() {
        return this.goodId;
    }

    /**
     * set the goodId - 固资Id
     */
    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getDeptNum() {
        return deptNum;
    }

    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public BigDecimal getBuyCost() {
        return buyCost;
    }

    public void setBuyCost(BigDecimal buyCost) {
        this.buyCost = buyCost;
    }

    /**
     * get the depreciationMonth - 折旧月份
     * @return the depreciationMonth
     */
    public String getDepreciationMonth() {
        return this.depreciationMonth;
    }

    /**
     * set the depreciationMonth - 折旧月份
     */
    public void setDepreciationMonth(String depreciationMonth) {
        this.depreciationMonth = depreciationMonth;
    }

    /**
     * get the usedMonth - 已使用时间
     * @return the usedMonth
     */
    public Integer getUsedMonth() {
        return this.usedMonth;
    }

    /**
     * set the usedMonth - 已使用时间
     */
    public void setUsedMonth(Integer usedMonth) {
        this.usedMonth = usedMonth;
    }

    /**
     * get the depreciationValue - 本月折旧
     * @return the depreciationValue
     */
    public BigDecimal getDepreciationValue() {
        return this.depreciationValue;
    }

    /**
     * set the depreciationValue - 本月折旧
     */
    public void setDepreciationValue(BigDecimal depreciationValue) {
        this.depreciationValue = depreciationValue;
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

    /**
     * get the monthDepreciation - 平均月折旧
     * @return the monthDepreciation
     */
    public BigDecimal getMonthDepreciation() {
        return this.monthDepreciation;
    }

    /**
     * set the monthDepreciation - 平均月折旧
     */
    public void setMonthDepreciation(BigDecimal monthDepreciation) {
        this.monthDepreciation = monthDepreciation;
    }

    public String getFundingSourceNum() {
        return fundingSourceNum;
    }

    public void setFundingSourceNum(String fundingSourceNum) {
        this.fundingSourceNum = fundingSourceNum;
    }

    public String getFundingSourceName() {
        return fundingSourceName;
    }

    public void setFundingSourceName(String fundingSourceName) {
        this.fundingSourceName = fundingSourceName;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setGoodId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodId")), goodId));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setGoodsNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsNum")), goodsNum));
        setGoodsName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("goodsName")), goodsName));
        setModel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("model")), model));
        setSpec(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spec")), spec));
        setBuyCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("buyCost")), buyCost));
        setDepreciationMonth(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("depreciationMonth")), depreciationMonth));
        setUsedMonth(NumberUtils.toInteger(StringUtils.toString(map.get("usedMonth")), usedMonth));
        setDepreciationValue(NumberUtils.toBigDecimal(StringUtils.toString(map.get("depreciationValue")), depreciationValue));
        setTotalDepreciation(NumberUtils.toBigDecimal(StringUtils.toString(map.get("totalDepreciation")), totalDepreciation));
        setNetAssetValue(NumberUtils.toBigDecimal(StringUtils.toString(map.get("netAssetValue")), netAssetValue));
        setMonthDepreciation(NumberUtils.toBigDecimal(StringUtils.toString(map.get("monthDepreciation")), monthDepreciation));
        setFundingSourceNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fundingSourceNum")), fundingSourceNum));
        setFundingSourceName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fundingSourceName")), fundingSourceName));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("goodId",StringUtils.toString(goodId, eiMetadata.getMeta("goodId")));
        map.put("deptNum",StringUtils.toString(goodId, eiMetadata.getMeta("goodId")));
        map.put("deptName",StringUtils.toString(goodId, eiMetadata.getMeta("goodId")));
        map.put("goodsNum",StringUtils.toString(goodId, eiMetadata.getMeta("goodId")));
        map.put("goodsName",StringUtils.toString(goodId, eiMetadata.getMeta("goodId")));
        map.put("model",StringUtils.toString(goodId, eiMetadata.getMeta("goodId")));
        map.put("spec",StringUtils.toString(goodId, eiMetadata.getMeta("goodId")));
        map.put("buyCost",StringUtils.toString(goodId, eiMetadata.getMeta("goodId")));
        map.put("depreciationMonth",StringUtils.toString(depreciationMonth, eiMetadata.getMeta("depreciationMonth")));
        map.put("usedMonth",StringUtils.toString(usedMonth, eiMetadata.getMeta("usedMonth")));
        map.put("depreciationValue",StringUtils.toString(depreciationValue, eiMetadata.getMeta("depreciationValue")));
        map.put("totalDepreciation",StringUtils.toString(totalDepreciation, eiMetadata.getMeta("totalDepreciation")));
        map.put("netAssetValue",StringUtils.toString(netAssetValue, eiMetadata.getMeta("netAssetValue")));
        map.put("monthDepreciation",StringUtils.toString(monthDepreciation, eiMetadata.getMeta("monthDepreciation")));
        map.put("fundingSourceNum",StringUtils.toString(fundingSourceNum, eiMetadata.getMeta("fundingSourceNum")));
        map.put("fundingSourceName",StringUtils.toString(fundingSourceName, eiMetadata.getMeta("fundingSourceName")));
        return map;
    }
}