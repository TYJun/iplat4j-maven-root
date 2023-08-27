/**
* Generate time : 2021-08-19 15:16:50
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ci.rk.domain;

import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CiEnterDetail ： 入库明细表实体
* 
*/
public class CiEnterDetail extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreator = " ";		/* 记录创建责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String id = " ";		/* 主键*/
    private String enterBillDetailNo = " ";		/* 入库单号*/
    private String enterBillNo = " ";		/* 入库单号*/
    private String enterType = " ";		/* 入库类别*/
    private String enterTypeName = " ";		/* 入库类型名称*/
    private String matNum = " ";		/* 物资编码*/
    private String matName = " ";		/* 物资名称*/
    private String matModel = " ";		/* 物资型号*/
    private String matSpec = " ";		/* 物资规格*/
    private String unit = " ";		/* 计量单位编码*/
    private String unitName = " ";		/* 计量单位名称*/
    private Double minNum = new Double(0);		/* 最低库存量*/
    private Double enterNum = new Double(0);		/* 入库数量*/
    private Double enterPrice = new Double(0);		/* 入库单价*/
    private Double enterAmount = new Double(0);		/* 入库总价*/
    private String enterDate;	/* 入库日期*/
    private String enterTime ;		/* 入库时间*/
    private String batchNo = " ";		/* 物资条码号13*/
    private String batchNum = " ";		/* 物资条码号12*/
    private String surpNum = " ";		/* 供应商编码*/
    private String surpName = " ";		/* 供应商名称*/
    private String validBegin = " ";		/* 未知*/
    private String validEnd = " ";		/* 未知*/
    private Double redRushNum = new Double(0);		/* 红冲数量*/
    private String shelfLife = " ";		/* 保质期*/
    private String productionDate = " ";		/* 记录创建时间*/

    private String expirationDate = " ";		/* 到期时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("记录创建责任者");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("记录创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("记录修改责任者");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("记录修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterBillDetailNo");
        eiColumn.setDescName("入库单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterBillNo");
        eiColumn.setDescName("入库单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterType");
        eiColumn.setDescName("入库类别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterTypeName");
        eiColumn.setDescName("入库类型名称");
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

        eiColumn = new EiColumn("minNum");
        eiColumn.setDescName("最低库存量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterNum");
        eiColumn.setDescName("入库数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterPrice");
        eiColumn.setDescName("入库单价");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterAmount");
        eiColumn.setDescName("入库总价");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterDate");
        eiColumn.setDescName("入库日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterTime");
        eiColumn.setDescName("入库时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("batchNo");
        eiColumn.setDescName("物资条码号13");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("batchNum");
        eiColumn.setDescName("物资条码号12");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surpNum");
        eiColumn.setDescName("供应商编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surpName");
        eiColumn.setDescName("供应商名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("validBegin");
        eiColumn.setDescName("未知");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("validEnd");
        eiColumn.setDescName("未知");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("redRushNum");
        eiColumn.setDescName("红冲数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("shelfLife");
        eiColumn.setDescName("保质期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("productionDate");
        eiColumn.setDescName("生产日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("expirationDate");
        eiColumn.setDescName("到期时间");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public CiEnterDetail() {
        initMetaData();
    }

    /**
     * get the recCreator - 记录创建责任者
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 记录创建责任者
     */
    @Override
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateTime - 记录创建时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 记录创建时间
     */
    @Override
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevisor - 记录修改责任者
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 记录修改责任者
     */
    @Override
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseTime - 记录修改时间
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 记录修改时间
     */
    @Override
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the archiveFlag - 归档标记
     * @return the archiveFlag
     */
    public String getArchiveFlag() {
        return this.archiveFlag;
    }

    /**
     * set the archiveFlag - 归档标记
     */
    @Override
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
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
     * get the enterBillDetailNo - 入库单号
     * @return the enterBillDetailNo
     */
    public String getEnterBillDetailNo() {
        return this.enterBillDetailNo;
    }

    /**
     * set the enterBillDetailNo - 入库单号
     */
    public void setEnterBillDetailNo(String enterBillDetailNo) {
        this.enterBillDetailNo = enterBillDetailNo;
    }

    /**
     * get the enterBillNo - 入库单号
     * @return the enterBillNo
     */
    public String getEnterBillNo() {
        return this.enterBillNo;
    }

    /**
     * set the enterBillNo - 入库单号
     */
    public void setEnterBillNo(String enterBillNo) {
        this.enterBillNo = enterBillNo;
    }

    /**
     * get the enterType - 入库类别
     * @return the enterType
     */
    public String getEnterType() {
        return this.enterType;
    }

    /**
     * set the enterType - 入库类别
     */
    public void setEnterType(String enterType) {
        this.enterType = enterType;
    }

    /**
     * get the enterTypeName - 入库类型名称
     * @return the enterTypeName
     */
    public String getEnterTypeName() {
        return this.enterTypeName;
    }

    /**
     * set the enterTypeName - 入库类型名称
     */
    public void setEnterTypeName(String enterTypeName) {
        this.enterTypeName = enterTypeName;
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
     * get the minNum - 最低库存量
     * @return the minNum
     */
    public Double getMinNum() {
        return this.minNum;
    }

    /**
     * set the minNum - 最低库存量
     */
    public void setMinNum(Double minNum) {
        this.minNum = minNum;
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
     * get the enterAmount - 入库总价
     * @return the enterAmount
     */
    public Double getEnterAmount() {
        return this.enterAmount;
    }

    /**
     * set the enterAmount - 入库总价
     */
    public void setEnterAmount(Double enterAmount) {
        this.enterAmount = enterAmount;
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
     * get the batchNo - 物资条码号13
     * @return the batchNo
     */
    public String getBatchNo() {
        return this.batchNo;
    }

    /**
     * set the batchNo - 物资条码号13
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * get the batchNum - 物资条码号12
     * @return the batchNum
     */
    public String getBatchNum() {
        return this.batchNum;
    }

    /**
     * set the batchNum - 物资条码号12
     */
    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
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
     * get the validBegin - 未知
     * @return the validBegin
     */
    public String getValidBegin() {
        return this.validBegin;
    }

    /**
     * set the validBegin - 未知
     */
    public void setValidBegin(String validBegin) {
        this.validBegin = validBegin;
    }

    /**
     * get the validEnd - 未知
     * @return the validEnd
     */
    public String getValidEnd() {
        return this.validEnd;
    }

    /**
     * set the validEnd - 未知
     */
    public void setValidEnd(String validEnd) {
        this.validEnd = validEnd;
    }
    
    /**
     * get the redRushNum - 红冲数量
     * @return the redRushNum
     */
    public Double getRedRushNum() {
        return this.redRushNum;
    }

    /**
     * set the redRushNum - 红冲数量
     */
    public void setRedRushNum(Double redRushNum) {
        this.redRushNum = redRushNum;
    }


    /**
     * get the shelfLife - 保质期
     * @return the shelfLife
     */
    public String getShelfLife() {
        return shelfLife;
    }

    /**
     * set the shelfLife - 保质期
     */
    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    /**
     * get the productionDate - 生产日期
     * @return the productionDate
     */
    public String getProductionDate() {
        return productionDate;
    }
    /**
     * set the productionDate - 生产日期
     */
    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    /**
     * get the value from Map
     */
    @Override
    @SuppressWarnings("rawtypes")
	public void fromMap(Map map) {

        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setEnterBillDetailNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterBillDetailNo")), enterBillDetailNo));
        setEnterBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterBillNo")), enterBillNo));
        setEnterType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterType")), enterType));
        setEnterTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterTypeName")), enterTypeName));
        setMatNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matNum")), matNum));
        setMatName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matName")), matName));
        setMatModel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matModel")), matModel));
        setMatSpec(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matSpec")), matSpec));
        setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
        setUnitName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unitName")), unitName));
        setMinNum(NumberUtils.toDouble(StringUtils.toString(map.get("minNum")), minNum));
        setEnterNum(NumberUtils.toDouble(StringUtils.toString(map.get("enterNum")), enterNum));
        setEnterPrice(NumberUtils.toDouble(StringUtils.toString(map.get("enterPrice")), enterPrice));
        setEnterAmount(NumberUtils.toDouble(StringUtils.toString(map.get("enterAmount")), enterAmount));
        setEnterDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterDate")), enterDate));
        setEnterTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterTime")), enterTime));
        setBatchNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("batchNo")), batchNo));
        setBatchNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("batchNum")), batchNum));
        setSurpNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("surpNum")), surpNum));
        setSurpName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("surpName")), surpName));
        setValidBegin(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("validBegin")), validBegin));
        setValidEnd(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("validEnd")), validEnd));
        setRedRushNum(NumberUtils.toDouble(StringUtils.toString(map.get("redRushNum")), redRushNum));
        setShelfLife(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("shelfLife")), shelfLife));
        setProductionDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("productionDate")), productionDate));
        setExpirationDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("expirationDate")), expirationDate));
    }

    /**
     * set the value to Map
     */
    @SuppressWarnings("all")
	public Map toMap() {
        Map map = new HashMap();
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("enterBillDetailNo",StringUtils.toString(enterBillDetailNo, eiMetadata.getMeta("enterBillDetailNo")));
        map.put("enterBillNo",StringUtils.toString(enterBillNo, eiMetadata.getMeta("enterBillNo")));
        map.put("enterType",StringUtils.toString(enterType, eiMetadata.getMeta("enterType")));
        map.put("enterTypeName",StringUtils.toString(enterTypeName, eiMetadata.getMeta("enterTypeName")));
        map.put("matNum",StringUtils.toString(matNum, eiMetadata.getMeta("matNum")));
        map.put("matName",StringUtils.toString(matName, eiMetadata.getMeta("matName")));
        map.put("matModel",StringUtils.toString(matModel, eiMetadata.getMeta("matModel")));
        map.put("matSpec",StringUtils.toString(matSpec, eiMetadata.getMeta("matSpec")));
        map.put("unit",StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("unitName",StringUtils.toString(unitName, eiMetadata.getMeta("unitName")));
        map.put("minNum",StringUtils.toString(minNum, eiMetadata.getMeta("minNum")));
        map.put("enterNum",StringUtils.toString(enterNum, eiMetadata.getMeta("enterNum")));
        map.put("enterPrice",StringUtils.toString(enterPrice, eiMetadata.getMeta("enterPrice")));
        map.put("enterAmount",StringUtils.toString(enterAmount, eiMetadata.getMeta("enterAmount")));
        map.put("enterDate",StringUtils.toString(enterDate, eiMetadata.getMeta("enterDate")));
        map.put("enterTime",StringUtils.toString(enterTime, eiMetadata.getMeta("enterTime")));
        map.put("batchNo",StringUtils.toString(batchNo, eiMetadata.getMeta("batchNo")));
        map.put("batchNum",StringUtils.toString(batchNum, eiMetadata.getMeta("batchNum")));
        map.put("surpNum",StringUtils.toString(surpNum, eiMetadata.getMeta("surpNum")));
        map.put("surpName",StringUtils.toString(surpName, eiMetadata.getMeta("surpName")));
        map.put("validBegin",StringUtils.toString(validBegin, eiMetadata.getMeta("validBegin")));
        map.put("validEnd",StringUtils.toString(validEnd, eiMetadata.getMeta("validEnd")));
        map.put("redRushNum",StringUtils.toString(redRushNum, eiMetadata.getMeta("redRushNum")));
        map.put("shelfLife",StringUtils.toString(shelfLife, eiMetadata.getMeta("shelfLife")));
        map.put("productionDate",StringUtils.toString(productionDate, eiMetadata.getMeta("productionDate")));
        map.put("expirationDate",StringUtils.toString(expirationDate, eiMetadata.getMeta("expirationDate")));
        return map;
    }
}