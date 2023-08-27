/**
* Generate time : 2021-08-27 9:47:53
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.si.kc.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* SiStorgeRecord ： 库存操作履历表
* 
*/
public class SiStorgeRecord extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreator = " ";		/* 记录创建责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String id = " ";		/* 主键*/
    private String originBillNo = " ";		/* 来源单据号*/
    private String originBillType = " ";		/* 来源单据类型*/
    private String originBillTypeName = " ";		/* 来源单据类型名称*/
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
    private Double price = new Double(0);		/* 单价*/
    private Double beforeNum = new Double(0);		/* 操作前数量*/
    private Double beforeAmount = new Double(0);		/* 操作前总价*/
    private Double afterNum = new Double(0);		/* 操作后数量*/
    private Double afterAmount = new Double(0.00);		/* 操作后总价*/
    private String batchNo = " ";		/* 批次号*/

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

        eiColumn = new EiColumn("originBillNo");
        eiColumn.setDescName("来源单据号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("originBillType");
        eiColumn.setDescName("来源单据类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("originBillTypeName");
        eiColumn.setDescName("来源单据类型名称");
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

        eiColumn = new EiColumn("price");
        eiColumn.setDescName("单价");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("beforeNum");
        eiColumn.setDescName("操作前数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("beforeAmount");
        eiColumn.setDescName("操作前总价");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("afterNum");
        eiColumn.setDescName("操作后数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("afterAmount");
        eiColumn.setDescName("操作后总价");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("batchNo");
        eiColumn.setDescName("批次号");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SiStorgeRecord() {
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
     * get the originBillTypeName - 来源单据类型名称
     * @return the originBillTypeName
     */
    public String getOriginBillTypeName() {
        return this.originBillTypeName;
    }

    /**
     * set the originBillTypeName - 来源单据类型名称
     */
    public void setOriginBillTypeName(String originBillTypeName) {
        this.originBillTypeName = originBillTypeName;
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
     * get the price - 单价
     * @return the price
     */
    public Double getPrice() {
        return this.price;
    }

    /**
     * set the price - 单价
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * get the beforeNum - 操作前数量
     * @return the beforeNum
     */
    public Double getBeforeNum() {
        return this.beforeNum;
    }

    /**
     * set the beforeNum - 操作前数量
     */
    public void setBeforeNum(Double beforeNum) {
        this.beforeNum = beforeNum;
    }

    /**
     * get the beforeAmount - 操作前总价
     * @return the beforeAmount
     */
    public Double getBeforeAmount() {
        return this.beforeAmount;
    }

    /**
     * set the beforeAmount - 操作前总价
     */
    public void setBeforeAmount(Double beforeAmount) {
        this.beforeAmount = beforeAmount;
    }

    /**
     * get the afterNum - 操作后数量
     * @return the afterNum
     */
    public Double getAfterNum() {
        return this.afterNum;
    }

    /**
     * set the afterNum - 操作后数量
     */
    public void setAfterNum(Double afterNum) {
        this.afterNum = afterNum;
    }

    /**
     * get the afterAmount - 操作后总价
     * @return the afterAmount
     */
    public Double getAfterAmount() {
        return this.afterAmount;
    }

    /**
     * set the afterAmount - 操作后总价
     */
    public void setAfterAmount(Double afterAmount) {
        this.afterAmount = afterAmount;
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
        setOriginBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("originBillNo")), originBillNo));
        setOriginBillType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("originBillType")), originBillType));
        setOriginBillTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("originBillTypeName")), originBillTypeName));
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
        setPrice(NumberUtils.toDouble(StringUtils.toString(map.get("price")), price));
        setBeforeNum(NumberUtils.toDouble(StringUtils.toString(map.get("beforeNum")), beforeNum));
        setBeforeAmount(NumberUtils.toDouble(StringUtils.toString(map.get("beforeAmount")), beforeAmount));
        setAfterNum(NumberUtils.toDouble(StringUtils.toString(map.get("afterNum")), afterNum));
        setAfterAmount(NumberUtils.toDouble(StringUtils.toString(map.get("afterAmount")), afterAmount));
        setBatchNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("batchNo")), batchNo));
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
        map.put("originBillNo",StringUtils.toString(originBillNo, eiMetadata.getMeta("originBillNo")));
        map.put("originBillType",StringUtils.toString(originBillType, eiMetadata.getMeta("originBillType")));
        map.put("originBillTypeName",StringUtils.toString(originBillTypeName, eiMetadata.getMeta("originBillTypeName")));
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
        map.put("price",StringUtils.toString(price, eiMetadata.getMeta("price")));
        map.put("beforeNum",StringUtils.toString(beforeNum, eiMetadata.getMeta("beforeNum")));
        map.put("beforeAmount",StringUtils.toString(beforeAmount, eiMetadata.getMeta("beforeAmount")));
        map.put("afterNum",StringUtils.toString(afterNum, eiMetadata.getMeta("afterNum")));
        map.put("afterAmount",StringUtils.toString(afterAmount, eiMetadata.getMeta("afterAmount")));
        map.put("batchNo",StringUtils.toString(batchNo, eiMetadata.getMeta("batchNo")));
        return map;
    }
}