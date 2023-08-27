/**
* Generate time : 2021-08-18 16:17:59
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ci.pk.domain;

import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CiInvenDetail ： 盘库明细表实体
* 
*/
public class CiInvenDetail extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreator = " ";		/* 记录创建责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String id = " ";		/* 主键*/
    private String invenBillDetailNo = " ";		/* 盘点单明细号*/
    private String invenBillNo = " ";		/* 盘点单号*/
    private String matNum = " ";		/* 物资编码*/
    private String matName = " ";		/* 物资名称*/
    private String matModel = " ";		/* 物资型号*/
    private String matSpec = " ";		/* 物资规格*/
    private String unit = " ";		/* 计量单位编码*/
    private String unitName = " ";		/* 计量单位名称*/
    private Double beforeInvenNum = new Double(0);		/* 盘点前数量*/
    private Double beforeInvenAmount = new Double(0);		/* 盘点前金额*/
    private Double afterInvenNum = new Double(0);		/* 盘点后数量*/
    private Double afterInvenAmount = new Double(0);		/* 盘点后金额*/
    private String invenDate = " ";	/* 盘点日期*/
    private String invenTime = " ";		/* 盘点时间*/
    private Double price = new Double(0);		/* 单价*/

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

        eiColumn = new EiColumn("invenBillDetailNo");
        eiColumn.setDescName("盘点单明细号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invenBillNo");
        eiColumn.setDescName("盘点单号");
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

        eiColumn = new EiColumn("beforeInvenNum");
        eiColumn.setDescName("盘点前数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("beforeInvenAmount");
        eiColumn.setDescName("盘点前金额");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("afterInvenNum");
        eiColumn.setDescName("盘点后数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("afterInvenAmount");
        eiColumn.setDescName("盘点后金额");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invenDate");
        eiColumn.setDescName("盘点日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invenTime");
        eiColumn.setDescName("盘点时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("price");
        eiColumn.setDescName("单价");
        eiColumn.setScaleLength(2);
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
    public CiInvenDetail() {
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
     * get the invenBillDetailNo - 盘点单明细号
     * @return the invenBillDetailNo
     */
    public String getInvenBillDetailNo() {
        return this.invenBillDetailNo;
    }

    /**
     * set the invenBillDetailNo - 盘点单明细号
     */
    public void setInvenBillDetailNo(String invenBillDetailNo) {
        this.invenBillDetailNo = invenBillDetailNo;
    }

    /**
     * get the invenBillNo - 盘点单号
     * @return the invenBillNo
     */
    public String getInvenBillNo() {
        return this.invenBillNo;
    }

    /**
     * set the invenBillNo - 盘点单号
     */
    public void setInvenBillNo(String invenBillNo) {
        this.invenBillNo = invenBillNo;
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
     * get the beforeInvenNum - 盘点前数量
     * @return the beforeInvenNum
     */
    public Double getBeforeInvenNum() {
        return this.beforeInvenNum;
    }

    /**
     * set the beforeInvenNum - 盘点前数量
     */
    public void setBeforeInvenNum(Double beforeInvenNum) {
        this.beforeInvenNum = beforeInvenNum;
    }

    /**
     * get the beforeInvenAmount - 盘点前金额
     * @return the beforeInvenAmount
     */
    public Double getBeforeInvenAmount() {
        return this.beforeInvenAmount;
    }

    /**
     * set the beforeInvenAmount - 盘点前金额
     */
    public void setBeforeInvenAmount(Double beforeInvenAmount) {
        this.beforeInvenAmount = beforeInvenAmount;
    }

    /**
     * get the afterInvenNum - 盘点后数量
     * @return the afterInvenNum
     */
    public Double getAfterInvenNum() {
        return this.afterInvenNum;
    }

    /**
     * set the afterInvenNum - 盘点后数量
     */
    public void setAfterInvenNum(Double afterInvenNum) {
        this.afterInvenNum = afterInvenNum;
    }

    /**
     * get the afterInvenAmount - 盘点后金额
     * @return the afterInvenAmount
     */
    public Double getAfterInvenAmount() {
        return this.afterInvenAmount;
    }

    /**
     * set the afterInvenAmount - 盘点后金额
     */
    public void setAfterInvenAmount(Double afterInvenAmount) {
        this.afterInvenAmount = afterInvenAmount;
    }

    /**
     * get the invenDate - 盘点日期
     * @return the invenDate
     */
    public String getInvenDate() {
        return this.invenDate;
    }

    /**
     * set the invenDate - 盘点日期
     */
    public void setInvenDate(String invenDate) {
        this.invenDate = invenDate;
    }

    /**
     * get the invenTime - 盘点时间
     * @return the invenTime
     */
    public String getInvenTime() {
        return this.invenTime;
    }

    /**
     * set the invenTime - 盘点时间
     */
    public void setInvenTime(String invenTime) {
        this.invenTime = invenTime;
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
        setInvenBillDetailNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("invenBillDetailNo")), invenBillDetailNo));
        setInvenBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("invenBillNo")), invenBillNo));
        setMatNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matNum")), matNum));
        setMatName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matName")), matName));
        setMatModel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matModel")), matModel));
        setMatSpec(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matSpec")), matSpec));
        setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
        setUnitName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unitName")), unitName));
        setBeforeInvenNum(NumberUtils.toDouble(StringUtils.toString(map.get("beforeInvenNum")), beforeInvenNum));
        setBeforeInvenAmount(NumberUtils.toDouble(StringUtils.toString(map.get("beforeInvenAmount")), beforeInvenAmount));
        setAfterInvenNum(NumberUtils.toDouble(StringUtils.toString(map.get("afterInvenNum")), afterInvenNum));
        setAfterInvenAmount(NumberUtils.toDouble(StringUtils.toString(map.get("afterInvenAmount")), afterInvenAmount));
        setInvenDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("invenDate")), invenDate));
        setInvenTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("invenTime")), invenTime));
        setPrice(NumberUtils.toDouble(StringUtils.toString(map.get("price")), price));
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
        map.put("invenBillDetailNo",StringUtils.toString(invenBillDetailNo, eiMetadata.getMeta("invenBillDetailNo")));
        map.put("invenBillNo",StringUtils.toString(invenBillNo, eiMetadata.getMeta("invenBillNo")));
        map.put("matNum",StringUtils.toString(matNum, eiMetadata.getMeta("matNum")));
        map.put("matName",StringUtils.toString(matName, eiMetadata.getMeta("matName")));
        map.put("matModel",StringUtils.toString(matModel, eiMetadata.getMeta("matModel")));
        map.put("matSpec",StringUtils.toString(matSpec, eiMetadata.getMeta("matSpec")));
        map.put("unit",StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("unitName",StringUtils.toString(unitName, eiMetadata.getMeta("unitName")));
        map.put("beforeInvenNum",StringUtils.toString(beforeInvenNum, eiMetadata.getMeta("beforeInvenNum")));
        map.put("beforeInvenAmount",StringUtils.toString(beforeInvenAmount, eiMetadata.getMeta("beforeInvenAmount")));
        map.put("afterInvenNum",StringUtils.toString(afterInvenNum, eiMetadata.getMeta("afterInvenNum")));
        map.put("afterInvenAmount",StringUtils.toString(afterInvenAmount, eiMetadata.getMeta("afterInvenAmount")));
        map.put("invenDate",StringUtils.toString(invenDate, eiMetadata.getMeta("invenDate")));
        map.put("invenTime",StringUtils.toString(invenTime, eiMetadata.getMeta("invenTime")));
        map.put("price",StringUtils.toString(price, eiMetadata.getMeta("price")));
        map.put("shelfLife",StringUtils.toString(shelfLife, eiMetadata.getMeta("shelfLife")));
        map.put("expirationDate",StringUtils.toString(expirationDate, eiMetadata.getMeta("expirationDate")));
        return map;
    }
}