package com.baosight.wilp.si.bf.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.si.common.SiConstant;
import com.baosight.wilp.si.common.SiUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 物资报废明细表
 * SiScrapDetail
 * @author fangjian
 */
public class SiScrapDetail extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private String id ;

    /**报废表ID*/
    private String scrapId ;

    /**报废单号*/
    private String scrapNo ;

    /**批次号*/
    private String batchNo ;

    /**仓库号*/
    private String wareHouseNo ;

    /**仓库名称*/
    private String wareHouseName ;

    /**物资分类编码*/
    private String matTypeNum ;

    /**物资分类名称*/
    private String matTypeName ;

    /**物资编码*/
    private String matNum ;

    /**物资名称*/
    private String matName ;

    /**物资型号*/
    private String matModel ;

    /**物资规格*/
    private String matSpec ;

    /**计量单位编码*/
    private String unit ;

    /**计量单位名称*/
    private String unitName ;

    /**入库数量*/
    private Double enterNum = new Double(0);

    /**入库单价*/
    private Double enterPrice = new Double(0);

    /**报废数量*/
    private Double scrapNum = new Double(0);

    /**报废总价*/
    private BigDecimal scrapAmount = new BigDecimal("0.00");

    /**入库日期*/
    private String enterDate ;

    /**入库时间*/
    private String enterTime ;

    /**供应商编码*/
    private String surpNum ;

    /**供应商名称*/
    private String surpName ;

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scrapId");
        eiColumn.setDescName("报废表ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scrapNo");
        eiColumn.setDescName("报废单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("batchNo");
        eiColumn.setDescName("批次号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wareHouseNo");
        eiColumn.setDescName("仓库号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wareHouseName");
        eiColumn.setDescName("仓库名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeNum");
        eiColumn.setDescName("物资分类编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeName");
        eiColumn.setDescName("物资分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matNum");
        eiColumn.setDescName("物资编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matName");
        eiColumn.setDescName("物资名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matModel");
        eiColumn.setDescName("物资型号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matSpec");
        eiColumn.setDescName("物资规格");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unit");
        eiColumn.setDescName("计量单位编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unitName");
        eiColumn.setDescName("计量单位名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterNum");
        eiColumn.setDescName("入库数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scrapNum");
        eiColumn.setDescName("报废数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("enterPrice");
        eiColumn.setDescName("入库单价");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scrapAmount");
        eiColumn.setDescName("报废总价");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterDate");
        eiColumn.setDescName("入库日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterTime");
        eiColumn.setDescName("入库时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surpNum");
        eiColumn.setDescName("供应商编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surpName");
        eiColumn.setDescName("供应商名称");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SiScrapDetail() {
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
     * get the scrapId - 报废表ID
     * @return the scrapId
     */
    public String getScrapId() {
        return this.scrapId;
    }

    /**
     * set the scrapId - 报废表ID
     */
    public void setScrapId(String scrapId) {
        this.scrapId = scrapId;
    }

    /**
     * get the scrapId - 报废单号
     * @return the scrapId
     */
    public String getScrapNo() {
        return this.scrapNo;
    }

    /**
     * set the scrapId - 报废单号
     */
    public void setScrapNo(String scrapNo) {
        this.scrapNo = scrapNo;
    }

    /**
     * get the batchNo - 批次号
     * @return the batchNo
     */
    public String getBatchNo() {
        return this.batchNo;
    }

    /**
     * set the batchNo - 批次号
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * get the wareHouseNo - 仓库号
     * @return the wareHouseNo
     */
    public String getWareHouseNo() {
        return this.wareHouseNo;
    }

    /**
     * set the wareHouseNo - 仓库号
     */
    public void setWareHouseNo(String wareHouseNo) {
        this.wareHouseNo = wareHouseNo;
    }

    /**
     * get the wareHouseName - 仓库名称
     * @return the wareHouseName
     */
    public String getWareHouseName() {
        return this.wareHouseName;
    }

    /**
     * set the wareHouseName - 仓库名称
     */
    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    /**
     * get the matTypeNum - 物资分类编码
     * @return the matTypeNum
     */
    public String getMatTypeNum() {
        return this.matTypeNum;
    }

    /**
     * set the matTypeNum - 物资分类编码
     */
    public void setMatTypeNum(String matTypeNum) {
        this.matTypeNum = matTypeNum;
    }

    /**
     * get the matTypeName - 物资分类名称
     * @return the matTypeName
     */
    public String getMatTypeName() {
        return this.matTypeName;
    }

    /**
     * set the matTypeName - 物资分类名称
     */
    public void setMatTypeName(String matTypeName) {
        this.matTypeName = matTypeName;
    }

    /**
     * get the matNum - 物资编码
     * @return the matNum
     */
    public String getMatNum() {
        return this.matNum;
    }

    /**
     * set the matNum - 物资编码
     */
    public void setMatNum(String matNum) {
        this.matNum = matNum;
    }

    /**
     * get the matName - 物资名称
     * @return the matName
     */
    public String getMatName() {
        return this.matName;
    }

    /**
     * set the matName - 物资名称
     */
    public void setMatName(String matName) {
        this.matName = matName;
    }

    /**
     * get the matModel - 物资型号
     * @return the matModel
     */
    public String getMatModel() {
        return this.matModel;
    }

    /**
     * set the matModel - 物资型号
     */
    public void setMatModel(String matModel) {
        this.matModel = matModel;
    }

    /**
     * get the matSpec - 物资规格
     * @return the matSpec
     */
    public String getMatSpec() {
        return this.matSpec;
    }

    /**
     * set the matSpec - 物资规格
     */
    public void setMatSpec(String matSpec) {
        this.matSpec = matSpec;
    }

    /**
     * get the unit - 计量单位编码
     * @return the unit
     */
    public String getUnit() {
        return this.unit;
    }

    /**
     * set the unit - 计量单位编码
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * get the unitName - 计量单位名称
     * @return the unitName
     */
    public String getUnitName() {
        return this.unitName;
    }

    /**
     * set the unitName - 计量单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * get the enterNum - 入库数量
     * @return the enterNum
     */
    public Double getEnterNum() {
        return this.enterNum;
    }

    /**
     * set the enterNum - 入库数量
     */
    public void setEnterNum(Double enterNum) {
        this.enterNum = enterNum;
    }

    /**
     * get the enterPrice - 入库单价
     * @return the enterPrice
     */
    public Double getEnterPrice() {
        return this.enterPrice;
    }

    /**
     * set the enterPrice - 入库单价
     */
    public void setEnterPrice(Double enterPrice) {
        this.enterPrice = enterPrice;
    }

    /**
     * get the scrapNum - 报废数量
     * @return the scrapNum
     */
    public Double getScrapNum() {
        return this.scrapNum;
    }

    /**
     * set the scrapNum - 报废数量
     */
    public void setScrapNum(Double scrapNum) {
        this.scrapNum = scrapNum;
        this.setScrapAmount(BigDecimal.valueOf(SiUtils.cal(scrapNum, enterPrice, SiConstant.MATH_MULTI)));
    }


    /**
     * get the scrapAmount - 报废总价
     * @return the scrapAmount
     */
    public BigDecimal getScrapAmount() {
        return this.scrapAmount;
    }

    /**
     * set the scrapAmount - 报废总价
     */
    public void setScrapAmount(BigDecimal scrapAmount) {
        this.scrapAmount = scrapAmount;
    }

    /**
     * get the enterDate - 入库日期
     * @return the enterDate
     */
    public String getEnterDate() {
        return this.enterDate;
    }

    /**
     * set the enterDate - 入库日期
     */
    public void setEnterDate(String enterDate) {
        this.enterDate = enterDate;
    }

    /**
     * get the enterTime - 入库时间
     * @return the enterTime
     */
    public String getEnterTime() {
        return this.enterTime;
    }

    /**
     * set the enterTime - 入库时间
     */
    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    /**
     * get the surpNum - 供应商编码
     * @return the surpNum
     */
    public String getSurpNum() {
        return this.surpNum;
    }

    /**
     * set the surpNum - 供应商编码
     */
    public void setSurpNum(String surpNum) {
        this.surpNum = surpNum;
    }

    /**
     * get the surpName - 供应商名称
     * @return the surpName
     */
    public String getSurpName() {
        return this.surpName;
    }

    /**
     * set the surpName - 供应商名称
     */
    public void setSurpName(String surpName) {
        this.surpName = surpName;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(SiUtils.isEmpty(map.get("id"), id));
        setScrapId(SiUtils.isEmpty(map.get("scrapId"), scrapId));
        setScrapNo(SiUtils.isEmpty(map.get("scrapNo"), scrapNo));
        setBatchNo(SiUtils.isEmpty(map.get("batchNo"), batchNo));
        setWareHouseNo(SiUtils.isEmpty(map.get("wareHouseNo"), wareHouseNo));
        setWareHouseName(SiUtils.isEmpty(map.get("wareHouseName"), wareHouseName));
        setMatTypeNum(SiUtils.isEmpty(map.get("matTypeNum"), matTypeNum));
        setMatTypeName(SiUtils.isEmpty(map.get("matTypeName"), matTypeName));
        setMatNum(SiUtils.isEmpty(map.get("matNum"), matNum));
        setMatName(SiUtils.isEmpty(map.get("matName"), matName));
        setMatModel(SiUtils.isEmpty(map.get("matModel"), matModel));
        setMatSpec(SiUtils.isEmpty(map.get("matSpec"), matSpec));
        setUnit(SiUtils.isEmpty(map.get("unit"), unit));
        setUnitName(SiUtils.isEmpty(map.get("unitName"), unitName));
        setEnterNum(NumberUtils.toDouble(StringUtils.toString(map.get("enterNum")), enterNum));
        setEnterPrice(NumberUtils.toDouble(StringUtils.toString(map.get("enterPrice")), enterPrice));
        setScrapNum(NumberUtils.toDouble(StringUtils.toString(map.get("scrapNum")), scrapNum));
        setScrapAmount(NumberUtils.toBigDecimal(StringUtils.toString(map.get("scrapAmount")), scrapAmount));
        setEnterDate(SiUtils.isEmpty(map.get("enterDate"), enterDate));
        setEnterTime(SiUtils.isEmpty(map.get("enterTime"), enterTime));
        setSurpNum(SiUtils.isEmpty(map.get("surpNum"), surpNum));
        setSurpName(SiUtils.isEmpty(map.get("surpName"), surpName));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(32);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("scrapId",StringUtils.toString(scrapId, eiMetadata.getMeta("scrapId")));
        map.put("scrapNo",StringUtils.toString(scrapNo, eiMetadata.getMeta("scrapNo")));
        map.put("batchNo",StringUtils.toString(batchNo, eiMetadata.getMeta("batchNo")));
        map.put("wareHouseNo",StringUtils.toString(wareHouseNo, eiMetadata.getMeta("wareHouseNo")));
        map.put("wareHouseName",StringUtils.toString(wareHouseName, eiMetadata.getMeta("wareHouseName")));
        map.put("matTypeNum",StringUtils.toString(matTypeNum, eiMetadata.getMeta("matTypeNum")));
        map.put("matTypeName",StringUtils.toString(matTypeName, eiMetadata.getMeta("matTypeName")));
        map.put("matNum",StringUtils.toString(matNum, eiMetadata.getMeta("matNum")));
        map.put("matName",StringUtils.toString(matName, eiMetadata.getMeta("matName")));
        map.put("matModel",StringUtils.toString(matModel, eiMetadata.getMeta("matModel")));
        map.put("matSpec",StringUtils.toString(matSpec, eiMetadata.getMeta("matSpec")));
        map.put("unit",StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("unitName",StringUtils.toString(unitName, eiMetadata.getMeta("unitName")));
        map.put("enterNum",StringUtils.toString(enterNum, eiMetadata.getMeta("enterNum")));
        map.put("enterPrice",StringUtils.toString(enterPrice, eiMetadata.getMeta("enterPrice")));
        map.put("scrapNum",StringUtils.toString(scrapNum, eiMetadata.getMeta("scrapNum")));
        map.put("scrapAmount",StringUtils.toString(scrapAmount, eiMetadata.getMeta("scrapAmount")));
        map.put("enterDate",StringUtils.toString(enterDate, eiMetadata.getMeta("enterDate")));
        map.put("enterTime",StringUtils.toString(enterTime, eiMetadata.getMeta("enterTime")));
        map.put("surpNum",StringUtils.toString(surpNum, eiMetadata.getMeta("surpNum")));
        map.put("surpName",StringUtils.toString(surpName, eiMetadata.getMeta("surpName")));
        return map;
    }
}