/**
* Generate time : 2021-08-18 14:19:00
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.si.kc.domain;

import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* SiStorgeDetail : 库存明细实体
* 
*/
public class SiStorgeDetail extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreator = " ";		/* 记录创建责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String id = " ";		/* 主键*/
    private String wareHouseNo = " ";		/* 仓库号*/
    private String wareHouseName = " ";		/* 仓库名称*/
    private String storageNo = " ";		/* 库位号*/
    private String stackNo = " ";		/* 货位号*/
    private String matTypeNum = " ";		/* 物资分类编码*/
    private String matTypeName = " ";		/* 物资分类名称*/
    private String matNum = " ";		/* 物资编码*/
    private String matName = " ";		/* 物资名称*/
    private String matModel = " ";		/* 物资型号*/
    private String matSpec = " ";		/* 物资规格*/
    private String unit = " ";		/* 计量单位编码*/
    private String unitName = " ";		/* 计量单位名称*/
    private String enterBillNo = " ";		/* 入库单号*/
    private String enterBillDetailNo = " ";		/* 业务单明细号*/
    private String enterBillType = " ";		/* 入库类型*/
    private String enterBillTypeName = " ";		/* 入库类型名称*/
    private String enterDate;	/* 入库日期*/
    private String enterTime ;		/* 入库时间*/
    private String originBillNo = " ";		/* 来源单据号*/
    private String originBillType = " ";		/* 来源单据类型*/
    private Double enterNum = new Double(0);		/* 入库数量*/
    private Double enterPrice = new Double(0);		/* 入库单价*/
    private Double enterAmount = new Double(0);		/* 入库总价*/
    private Double outBeforeNum = new Double(0);		/* 本次出库前数量*/
    private String barCode = " ";		/* 条码号*/
    private Double minNum = new Double(0.00);		/* 最低存量*/
    private String batchNo = " ";		/* 批次号*/
    private String surpNum = " ";		/* 供应商编码*/
    private String surpName = " ";		/* 供应商名称*/
    private String validBegin = " ";		/* 未知*/
    private String validEnd = " ";		/* 未知*/
    private String contNo = " ";		/* 合同号（未用）*/
    private String purcDemandDetailNo = " ";		/* 采购计划明细号（未用）*/
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
        eiColumn.setDescName("业务单明细号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterBillNo");
        eiColumn.setDescName("入库单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterBillType");
        eiColumn.setDescName("入库类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterBillTypeName");
        eiColumn.setDescName("入库类型名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("originBillNo");
        eiColumn.setDescName("来源单据号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("originBillType");
        eiColumn.setDescName("来源单据类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contNo");
        eiColumn.setDescName("合同号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("purcDemandDetailNo");
        eiColumn.setDescName("采购计划明细号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wareHouseNo");
        eiColumn.setDescName("仓库号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wareHouseName");
        eiColumn.setDescName("仓库名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("storageNo");
        eiColumn.setDescName("库位号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("stackNo");
        eiColumn.setDescName("货位号");
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

        eiColumn = new EiColumn("enterPrice");
        eiColumn.setDescName("入库单价");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterAmount");
        eiColumn.setDescName("入库总价");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("outBeforeNum");
        eiColumn.setDescName("本次出库前数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterDate");
        eiColumn.setDescName("入库日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterTime");
        eiColumn.setDescName("入库时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("barCode");
        eiColumn.setDescName("条码号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("minNum");
        eiColumn.setDescName("最低存量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("batchNo");
        eiColumn.setDescName("批次号");
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


    }

    /**
     * the constructor
     */
    public SiStorgeDetail() {
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
     * get the enterBillDetailNo - 业务单明细号
     * @return the enterBillDetailNo
     */
    public String getEnterBillDetailNo() {
        return this.enterBillDetailNo;
    }

    /**
     * set the enterBillDetailNo - 业务单明细号
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
     * get the enterBillType - 入库类型
     * @return the enterBillType
     */
    public String getEnterBillType() {
        return this.enterBillType;
    }

    /**
     * set the enterBillType - 入库类型
     */
    public void setEnterBillType(String enterBillType) {
        this.enterBillType = enterBillType;
    }

    /**
     * get the enterBillTypeName - 入库类型名称
     * @return the enterBillTypeName
     */
    public String getEnterBillTypeName() {
        return this.enterBillTypeName;
    }

    /**
     * set the enterBillTypeName - 入库类型名称
     */
    public void setEnterBillTypeName(String enterBillTypeName) {
        this.enterBillTypeName = enterBillTypeName;
    }

    /**
     * get the originBillNo - 来源单据号
     * @return the originBillNo
     */
    public String getOriginBillNo() {
        return this.originBillNo;
    }

    /**
     * set the originBillNo - 来源单据号
     */
    public void setOriginBillNo(String originBillNo) {
        this.originBillNo = originBillNo;
    }

    /**
     * get the originBillType - 来源单据类型
     * @return the originBillType
     */
    public String getOriginBillType() {
        return this.originBillType;
    }

    /**
     * set the originBillType - 来源单据类型
     */
    public void setOriginBillType(String originBillType) {
        this.originBillType = originBillType;
    }

    /**
     * get the contNo - 合同号
     * @return the contNo
     */
    public String getContNo() {
        return this.contNo;
    }

    /**
     * set the contNo - 合同号
     */
    public void setContNo(String contNo) {
        this.contNo = contNo;
    }

    /**
     * get the purcDemandDetailNo - 采购计划明细号
     * @return the purcDemandDetailNo
     */
    public String getPurcDemandDetailNo() {
        return this.purcDemandDetailNo;
    }

    /**
     * set the purcDemandDetailNo - 采购计划明细号
     */
    public void setPurcDemandDetailNo(String purcDemandDetailNo) {
        this.purcDemandDetailNo = purcDemandDetailNo;
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
     * get the storageNo - 库位号
     * @return the storageNo
     */
    public String getStorageNo() {
        return this.storageNo;
    }

    /**
     * set the storageNo - 库位号
     */
    public void setStorageNo(String storageNo) {
        this.storageNo = storageNo;
    }

    /**
     * get the stackNo - 货位号
     * @return the stackNo
     */
    public String getStackNo() {
        return this.stackNo;
    }

    /**
     * set the stackNo - 货位号
     */
    public void setStackNo(String stackNo) {
        this.stackNo = stackNo;
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
     * get the outBeforeNum - 本次出库前数量
     * @return the outBeforeNum
     */
    public Double getOutBeforeNum() {
        return this.outBeforeNum;
    }

    /**
     * set the outBeforeNum - 本次出库前数量
     */
    public void setOutBeforeNum(Double outBeforeNum) {
        this.outBeforeNum = outBeforeNum;
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
     * get the barCode - 条码号
     * @return the barCode
     */
    public String getBarCode() {
        return this.barCode;
    }

    /**
     * set the barCode - 条码号
     */
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    /**
     * get the minNum - 最低存量
     * @return the minNum
     */
    public Double getMinNum() {
        return this.minNum;
    }

    /**
     * set the minNum - 最低存量
     */
    public void setMinNum(Double minNum) {
        this.minNum = minNum;
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
        setEnterBillType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterBillType")), enterBillType));
        setEnterBillTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterBillTypeName")), enterBillTypeName));
        setOriginBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("originBillNo")), originBillNo));
        setOriginBillType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("originBillType")), originBillType));
        setContNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contNo")), contNo));
        setPurcDemandDetailNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("purcDemandDetailNo")), purcDemandDetailNo));
        setWareHouseNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wareHouseNo")), wareHouseNo));
        setWareHouseName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wareHouseName")), wareHouseName));
        setStorageNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("storageNo")), storageNo));
        setStackNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("stackNo")), stackNo));
        setMatTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matTypeNum")), matTypeNum));
        setMatTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matTypeName")), matTypeName));
        setMatNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matNum")), matNum));
        setMatName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matName")), matName));
        setMatModel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matModel")), matModel));
        setMatSpec(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matSpec")), matSpec));
        setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
        setUnitName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unitName")), unitName));
        setEnterNum(NumberUtils.toDouble(StringUtils.toString(map.get("enterNum")), enterNum));
        setEnterPrice(NumberUtils.toDouble(StringUtils.toString(map.get("enterPrice")), enterPrice));
        setEnterAmount(NumberUtils.toDouble(StringUtils.toString(map.get("enterAmount")), enterAmount));
        setEnterDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterDate")), enterDate));
        setEnterTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterTime")), enterTime));
        setOutBeforeNum(NumberUtils.toDouble(StringUtils.toString(map.get("outBeforeNum")), outBeforeNum));
        setBarCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("barCode")), barCode));
        setMinNum(NumberUtils.toDouble(StringUtils.toString(map.get("minNum")), minNum));
        setBatchNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("batchNo")), batchNo));
        setSurpNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("surpNum")), surpNum));
        setSurpName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("surpName")), surpName));
        setValidBegin(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("validBegin")), validBegin));
        setValidEnd(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("validEnd")), validEnd));
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
        map.put("enterBillType",StringUtils.toString(enterBillType, eiMetadata.getMeta("enterBillType")));
        map.put("enterBillTypeName",StringUtils.toString(enterBillTypeName, eiMetadata.getMeta("enterBillTypeName")));
        map.put("originBillNo",StringUtils.toString(originBillNo, eiMetadata.getMeta("originBillNo")));
        map.put("originBillType",StringUtils.toString(originBillType, eiMetadata.getMeta("originBillType")));
        map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
        map.put("purcDemandDetailNo",StringUtils.toString(purcDemandDetailNo, eiMetadata.getMeta("purcDemandDetailNo")));
        map.put("wareHouseNo",StringUtils.toString(wareHouseNo, eiMetadata.getMeta("wareHouseNo")));
        map.put("wareHouseName",StringUtils.toString(wareHouseName, eiMetadata.getMeta("wareHouseName")));
        map.put("storageNo",StringUtils.toString(storageNo, eiMetadata.getMeta("storageNo")));
        map.put("stackNo",StringUtils.toString(stackNo, eiMetadata.getMeta("stackNo")));
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
        map.put("enterAmount",StringUtils.toString(enterAmount, eiMetadata.getMeta("enterAmount")));
        map.put("enterDate",StringUtils.toString(enterDate, eiMetadata.getMeta("enterDate")));
        map.put("enterTime",StringUtils.toString(enterTime, eiMetadata.getMeta("enterTime")));
        map.put("outBeforeNum",StringUtils.toString(outBeforeNum, eiMetadata.getMeta("outBeforeNum")));
        map.put("barCode",StringUtils.toString(barCode, eiMetadata.getMeta("barCode")));
        map.put("minNum",StringUtils.toString(minNum, eiMetadata.getMeta("minNum")));
        map.put("batchNo",StringUtils.toString(batchNo, eiMetadata.getMeta("batchNo")));
        map.put("surpNum",StringUtils.toString(surpNum, eiMetadata.getMeta("surpNum")));
        map.put("surpName",StringUtils.toString(surpName, eiMetadata.getMeta("surpName")));
        map.put("validBegin",StringUtils.toString(validBegin, eiMetadata.getMeta("validBegin")));
        map.put("validEnd",StringUtils.toString(validEnd, eiMetadata.getMeta("validEnd")));
        return map;
    }
}