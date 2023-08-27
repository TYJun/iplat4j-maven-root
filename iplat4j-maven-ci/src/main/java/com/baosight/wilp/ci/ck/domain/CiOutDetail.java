/**
* Generate time : 2021-08-19 15:16:06
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ci.ck.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CiOutDetail : 出库明细
* 
*/
public class CiOutDetail extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreator = " ";		/* 记录创建责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String id = " ";		/* 主键*/
    private String outBillDetailNo = " ";		/* 出库单明细号*/
    private String outBillNo = " ";		/* 出库单号*/
    private String outType = " ";		/* 出库类别*/
    private String outTypeName = " ";		/* 出库类型名称*/
    private String matNum = " ";		/* 物资编码*/
    private String matName = " ";		/* 物资名称*/
    private String matModel = " ";		/* 物资型号*/
    private String matSpec = " ";		/* 物资规格*/
    private String unit = " ";		/* 计量单位编码*/
    private String unitName = " ";		/* 计量单位名称*/
    private Double outNum = new Double(0);		/* 出库数量*/
    private Double outPrice = new Double(0);		/* 出库单价*/
    private Double outAmount = new Double(0);		/* 出库明细*/
    private String outDate;	/* 出库日期*/
    private String outTime ;		/* 出库时间*/
    private String batchNo = " ";		/* 物资条码号13*/
    private String batchNum = " ";		/* 物资条码号12*/

    private String shelfLife = " ";		/* 保质期*/
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

        eiColumn = new EiColumn("outBillDetailNo");
        eiColumn.setDescName("出库单明细号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outBillNo");
        eiColumn.setDescName("出库单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outType");
        eiColumn.setDescName("出库类别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outTypeName");
        eiColumn.setDescName("出库类型名称");
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

        eiColumn = new EiColumn("outNum");
        eiColumn.setDescName("出库数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outPrice");
        eiColumn.setDescName("出库单价");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outAmount");
        eiColumn.setDescName("出库明细");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outDate");
        eiColumn.setDescName("出库日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outTime");
        eiColumn.setDescName("出库时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("batchNo");
        eiColumn.setDescName("物资条码号13");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("batchNum");
        eiColumn.setDescName("物资条码号12");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("shelfLife");
        eiColumn.setDescName("保质期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("expirationDate");
        eiColumn.setDescName("到期时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CiOutDetail() {
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
     * get the outBillDetailNo - 出库单明细号
     * @return the outBillDetailNo
     */
    public String getOutBillDetailNo() {
        return this.outBillDetailNo;
    }

    /**
     * set the outBillDetailNo - 出库单明细号
     */
    public void setOutBillDetailNo(String outBillDetailNo) {
        this.outBillDetailNo = outBillDetailNo;
    }

    /**
     * get the outBillNo - 出库单号
     * @return the outBillNo
     */
    public String getOutBillNo() {
        return this.outBillNo;
    }

    /**
     * set the outBillNo - 出库单号
     */
    public void setOutBillNo(String outBillNo) {
        this.outBillNo = outBillNo;
    }

    /**
     * get the outType - 出库类别
     * @return the outType
     */
    public String getOutType() {
        return this.outType;
    }

    /**
     * set the outType - 出库类别
     */
    public void setOutType(String outType) {
        this.outType = outType;
    }

    /**
     * get the outTypeName - 出库类型名称
     * @return the outTypeName
     */
    public String getOutTypeName() {
        return this.outTypeName;
    }

    /**
     * set the outTypeName - 出库类型名称
     */
    public void setOutTypeName(String outTypeName) {
        this.outTypeName = outTypeName;
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
     * get the outNum - 出库数量
     * @return the outNum
     */
    public Double getOutNum() {
        return this.outNum;
    }

    /**
     * set the outNum - 出库数量
     */
    public void setOutNum(Double outNum) {
        this.outNum = outNum;
    }

    /**
     * get the outPrice - 出库单价
     * @return the outPrice
     */
    public Double getOutPrice() {
        return this.outPrice;
    }

    /**
     * set the outPrice - 出库单价
     */
    public void setOutPrice(Double outPrice) {
        this.outPrice = outPrice;
    }

    /**
     * get the outAmount - 出库明细
     * @return the outAmount
     */
    public Double getOutAmount() {
        return this.outAmount;
    }

    /**
     * set the outAmount - 出库明细
     */
    public void setOutAmount(Double outAmount) {
        this.outAmount = outAmount;
    }

    /**
     * get the outDate - 出库日期
     * @return the outDate
     */
    public String getOutDate() {
        return this.outDate;
    }

    /**
     * set the outDate - 出库日期
     */
    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    /**
     * get the outTime - 出库时间
     * @return the outTime
     */
    public String getOutTime() {
        return this.outTime;
    }

    /**
     * set the outTime - 出库时间
     */
    public void setOutTime(String outTime) {
        this.outTime = outTime;
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

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
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
        setOutBillDetailNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outBillDetailNo")), outBillDetailNo));
        setOutBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outBillNo")), outBillNo));
        setOutType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outType")), outType));
        setOutTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outTypeName")), outTypeName));
        setMatNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matNum")), matNum));
        setMatName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matName")), matName));
        setMatModel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matModel")), matModel));
        setMatSpec(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matSpec")), matSpec));
        setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
        setUnitName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unitName")), unitName));
        setOutNum(NumberUtils.toDouble(StringUtils.toString(map.get("outNum")), outNum));
        setOutPrice(NumberUtils.toDouble(StringUtils.toString(map.get("outPrice")), outPrice));
        setOutAmount(NumberUtils.toDouble(StringUtils.toString(map.get("outAmount")), outAmount));
        setOutDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outDate")), outDate));
        setOutTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outTime")), outTime));
        setBatchNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("batchNo")), batchNo));
        setBatchNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("batchNum")), batchNum));
        setShelfLife(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("shelfLife")), shelfLife));
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
        map.put("outBillDetailNo",StringUtils.toString(outBillDetailNo, eiMetadata.getMeta("outBillDetailNo")));
        map.put("outBillNo",StringUtils.toString(outBillNo, eiMetadata.getMeta("outBillNo")));
        map.put("outType",StringUtils.toString(outType, eiMetadata.getMeta("outType")));
        map.put("outTypeName",StringUtils.toString(outTypeName, eiMetadata.getMeta("outTypeName")));
        map.put("matNum",StringUtils.toString(matNum, eiMetadata.getMeta("matNum")));
        map.put("matName",StringUtils.toString(matName, eiMetadata.getMeta("matName")));
        map.put("matModel",StringUtils.toString(matModel, eiMetadata.getMeta("matModel")));
        map.put("matSpec",StringUtils.toString(matSpec, eiMetadata.getMeta("matSpec")));
        map.put("unit",StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("unitName",StringUtils.toString(unitName, eiMetadata.getMeta("unitName")));
        map.put("outNum",StringUtils.toString(outNum, eiMetadata.getMeta("outNum")));
        map.put("outPrice",StringUtils.toString(outPrice, eiMetadata.getMeta("outPrice")));
        map.put("outAmount",StringUtils.toString(outAmount, eiMetadata.getMeta("outAmount")));
        map.put("outDate",StringUtils.toString(outDate, eiMetadata.getMeta("outDate")));
        map.put("outTime",StringUtils.toString(outTime, eiMetadata.getMeta("outTime")));
        map.put("batchNo",StringUtils.toString(batchNo, eiMetadata.getMeta("batchNo")));
        map.put("batchNum",StringUtils.toString(batchNum, eiMetadata.getMeta("batchNum")));
        map.put("shelfLife",StringUtils.toString(shelfLife, eiMetadata.getMeta("shelfLife")));
        map.put("expirationDate",StringUtils.toString(expirationDate, eiMetadata.getMeta("expirationDate")));
        return map;
    }
}