/**
* Generate time : 2022-12-19 15:14:28
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.fa.qr.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* FaConfirmDO
* 
*/
public class FaConfirmVO extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 固定资产待确认表主键*/
    private String contNo = " ";		/* 合同号*/
    private String purchaseVouch = " ";		/* 采购凭证 资产采购的订单号,大于零的自然数*/
    private String purchaseStaffName = " ";		/* 采购人 资产采购项目执行负责人姓名,*/
    private String fundingsourceNum = " ";		/* 资金来源编码*/
    private String fundingsourceName = " ";		/* 资金来源名称*/
    private String enterBillNo = " ";		/* 入库单号 资产入库的唯一标识码,大于零的自然数*/
    private String enterType = " ";		/* 入库类别*/
    private String enterTypeName = " ";		/* 入库类型名称*/
    private String matNum = " ";		/* 物资编码*/
    private String matName = " ";		/* 物资名称*/
    private String matSpec = " ";		/* 物资规格*/
    private String model = " ";		/* 型号*/
    private String batchNo = " ";		/* 物资条码号13*/
    private String batchNum = " ";		/* 物资条码号12*/
    private String unitNum = " ";		/* 计量单位编码*/
    private String unitName = " ";		/* 计量单位名称*/
    private Integer enterNum = new Integer(0);		/* 入库数量*/
    private BigDecimal enterPrice = new BigDecimal("0");		/* 入库单价（元）*/
    private BigDecimal enterAmount = new BigDecimal("0");		/* 入库总价（元）*/
    private String invoiceNo = " ";		/* 发票号*/
    private String manufacturer = " ";		/* 制造商*/
    private String manufacturerCode = " ";		/* 制造商国别*/
    private String surpNum = " ";		/* 供应商编码*/
    private String surpName = " ";		/* 供应商*/
    private Date checkDate;	/* 验收日期 设备到达医院后进行验收的日期,*/
    private Date acquitvDate;	/* 购置日期 购置资产的合同或协议日期,*/
    private String acquitvYear = " ";		/* 购置年度 获得资产所有权的年度,*/
    private Date recCreateTime;	/* 创建时间（入库时间）*/
    private String recCreateName = " ";		/* 创建人*/
    private String dataGroupCode = " ";		/* 账套*/
    private String transferType = " ";		/* 对接类型(00-博纳确认,10-外部系统确认)*/
    private String confirmName = " ";		/* 确认人*/
    private Date confirmDate;	/* 确认时间*/
    private String confirmStatus = " ";		/* 确认状态(00-待确认,10-已确认,20-生成卡片)*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("固定资产待确认表主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contNo");
        eiColumn.setDescName("合同号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("purchaseVouch");
        eiColumn.setDescName("采购凭证 资产采购的订单号,大于零的自然数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("purchaseStaffName");
        eiColumn.setDescName("采购人 资产采购项目执行负责人姓名,");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fundingsourceNum");
        eiColumn.setDescName("资金来源编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fundingsourceName");
        eiColumn.setDescName("资金来源名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterBillNo");
        eiColumn.setDescName("入库单号 资产入库的唯一标识码,大于零的自然数");
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

        eiColumn = new EiColumn("matSpec");
        eiColumn.setDescName("物资规格");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("model");
        eiColumn.setDescName("型号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("batchNo");
        eiColumn.setDescName("物资条码号13");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("batchNum");
        eiColumn.setDescName("物资条码号12");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unitNum");
        eiColumn.setDescName("计量单位编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unitName");
        eiColumn.setDescName("计量单位名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterNum");
        eiColumn.setDescName("入库数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterPrice");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("入库单价（元）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterAmount");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName("入库总价（元）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceNo");
        eiColumn.setDescName("发票号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manufacturer");
        eiColumn.setDescName("制造商");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manufacturerCode");
        eiColumn.setDescName("制造商国别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surpNum");
        eiColumn.setDescName("供应商编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surpName");
        eiColumn.setDescName("供应商");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("checkDate");
        eiColumn.setDescName("验收日期 设备到达医院后进行验收的日期,");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("acquitvDate");
        eiColumn.setDescName("购置日期 购置资产的合同或协议日期,");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("acquitvYear");
        eiColumn.setDescName("购置年度 获得资产所有权的年度,");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间（入库时间）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateName");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("transferType");
        eiColumn.setDescName("对接类型(00-博纳确认,10-外部系统确认)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("confirmName");
        eiColumn.setDescName("确认人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("confirmDate");
        eiColumn.setDescName("确认时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("confirmStatus");
        eiColumn.setDescName("确认状态(00-待确认,10-已确认,20-生成卡片)");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public FaConfirmVO() {
        initMetaData();
    }

    /**
     * get the id - 固定资产待确认表主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 固定资产待确认表主键
     */
    public void setId(String id) {
        this.id = id;
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
     * get the purchaseVouch - 采购凭证 资产采购的订单号,大于零的自然数
     * @return the purchaseVouch
     */
    public String getPurchaseVouch() {
        return this.purchaseVouch;
    }

    /**
     * set the purchaseVouch - 采购凭证 资产采购的订单号,大于零的自然数
     */
    public void setPurchaseVouch(String purchaseVouch) {
        this.purchaseVouch = purchaseVouch;
    }

    /**
     * get the purchaseStaffName - 采购人 资产采购项目执行负责人姓名,
     * @return the purchaseStaffName
     */
    public String getPurchaseStaffName() {
        return this.purchaseStaffName;
    }

    /**
     * set the purchaseStaffName - 采购人 资产采购项目执行负责人姓名,
     */
    public void setPurchaseStaffName(String purchaseStaffName) {
        this.purchaseStaffName = purchaseStaffName;
    }

    /**
     * get the fundingsourceNum - 资金来源编码
     * @return the fundingsourceNum
     */
    public String getFundingsourceNum() {
        return this.fundingsourceNum;
    }

    /**
     * set the fundingsourceNum - 资金来源编码
     */
    public void setFundingsourceNum(String fundingsourceNum) {
        this.fundingsourceNum = fundingsourceNum;
    }

    /**
     * get the fundingsourceName - 资金来源名称
     * @return the fundingsourceName
     */
    public String getFundingsourceName() {
        return this.fundingsourceName;
    }

    /**
     * set the fundingsourceName - 资金来源名称
     */
    public void setFundingsourceName(String fundingsourceName) {
        this.fundingsourceName = fundingsourceName;
    }

    /**
     * get the enterBillNo - 入库单号 资产入库的唯一标识码,大于零的自然数
     * @return the enterBillNo
     */
    public String getEnterBillNo() {
        return this.enterBillNo;
    }

    /**
     * set the enterBillNo - 入库单号 资产入库的唯一标识码,大于零的自然数
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
     * get the model - 型号
     * @return the model
     */
    public String getModel() {
        return this.model;
    }

    /**
     * set the model - 型号
     */
    public void setModel(String model) {
        this.model = model;
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
     * get the unitNum - 计量单位编码
     * @return the unitNum
     */
    public String getUnitNum() {
        return this.unitNum;
    }

    /**
     * set the unitNum - 计量单位编码
     */
    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
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
    public Integer getEnterNum() {
        return this.enterNum;
    }

    /**
     * set the enterNum - 入库数量
     */
    public void setEnterNum(Integer enterNum) {
        this.enterNum = enterNum;
    }

    /**
     * get the enterPrice - 入库单价（元）
     * @return the enterPrice
     */
    public BigDecimal getEnterPrice() {
        return this.enterPrice;
    }

    /**
     * set the enterPrice - 入库单价（元）
     */
    public void setEnterPrice(BigDecimal enterPrice) {
        this.enterPrice = enterPrice;
    }

    /**
     * get the enterAmount - 入库总价（元）
     * @return the enterAmount
     */
    public BigDecimal getEnterAmount() {
        return this.enterAmount;
    }

    /**
     * set the enterAmount - 入库总价（元）
     */
    public void setEnterAmount(BigDecimal enterAmount) {
        this.enterAmount = enterAmount;
    }

    /**
     * get the invoiceNo - 发票号
     * @return the invoiceNo
     */
    public String getInvoiceNo() {
        return this.invoiceNo;
    }

    /**
     * set the invoiceNo - 发票号
     */
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    /**
     * get the manufacturer - 制造商
     * @return the manufacturer
     */
    public String getManufacturer() {
        return this.manufacturer;
    }

    /**
     * set the manufacturer - 制造商
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * get the manufacturerCode - 制造商国别
     * @return the manufacturerCode
     */
    public String getManufacturerCode() {
        return this.manufacturerCode;
    }

    /**
     * set the manufacturerCode - 制造商国别
     */
    public void setManufacturerCode(String manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
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
     * get the surpName - 供应商
     * @return the surpName
     */
    public String getSurpName() {
        return this.surpName;
    }

    /**
     * set the surpName - 供应商
     */
    public void setSurpName(String surpName) {
        this.surpName = surpName;
    }

    /**
     * get the checkDate - 验收日期 设备到达医院后进行验收的日期,
     * @return the checkDate
     */
    public Date getCheckDate() {
        return this.checkDate;
    }

    /**
     * set the checkDate - 验收日期 设备到达医院后进行验收的日期,
     */
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * get the acquitvDate - 购置日期 购置资产的合同或协议日期,
     * @return the acquitvDate
     */
    public Date getAcquitvDate() {
        return this.acquitvDate;
    }

    /**
     * set the acquitvDate - 购置日期 购置资产的合同或协议日期,
     */
    public void setAcquitvDate(Date acquitvDate) {
        this.acquitvDate = acquitvDate;
    }

    /**
     * get the acquitvYear - 购置年度 获得资产所有权的年度,
     * @return the acquitvYear
     */
    public String getAcquitvYear() {
        return this.acquitvYear;
    }

    /**
     * set the acquitvYear - 购置年度 获得资产所有权的年度,
     */
    public void setAcquitvYear(String acquitvYear) {
        this.acquitvYear = acquitvYear;
    }

    /**
     * get the recCreateTime - 创建时间（入库时间）
     * @return the recCreateTime
     */
    public Date getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间（入库时间）
     */
    public void setRecCreateTime(Date recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recCreateName - 创建人
     * @return the recCreateName
     */
    public String getRecCreateName() {
        return this.recCreateName;
    }

    /**
     * set the recCreateName - 创建人
     */
    public void setRecCreateName(String recCreateName) {
        this.recCreateName = recCreateName;
    }

    /**
     * get the dataGroupCode - 账套
     * @return the dataGroupCode
     */
    public String getDataGroupCode() {
        return this.dataGroupCode;
    }

    /**
     * set the dataGroupCode - 账套
     */
    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

    /**
     * get the transferType - 对接类型(00-博纳确认,10-外部系统确认)
     * @return the transferType
     */
    public String getTransferType() {
        return this.transferType;
    }

    /**
     * set the transferType - 对接类型(00-博纳确认,10-外部系统确认)
     */
    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    /**
     * get the confirmName - 确认人
     * @return the confirmName
     */
    public String getConfirmName() {
        return this.confirmName;
    }

    /**
     * set the confirmName - 确认人
     */
    public void setConfirmName(String confirmName) {
        this.confirmName = confirmName;
    }

    /**
     * get the confirmDate - 确认时间
     * @return the confirmDate
     */
    public Date getConfirmDate() {
        return this.confirmDate;
    }

    /**
     * set the confirmDate - 确认时间
     */
    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    /**
     * get the confirmStatus - 确认状态(00-待确认,10-已确认,20-生成卡片)
     * @return the confirmStatus
     */
    public String getConfirmStatus() {
        return this.confirmStatus;
    }

    /**
     * set the confirmStatus - 确认状态(00-待确认,10-已确认,20-生成卡片)
     */
    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setContNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contNo")), contNo));
        setPurchaseVouch(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("purchaseVouch")), purchaseVouch));
        setPurchaseStaffName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("purchaseStaffName")), purchaseStaffName));
        setFundingsourceNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fundingsourceNum")), fundingsourceNum));
        setFundingsourceName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fundingsourceName")), fundingsourceName));
        setEnterBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterBillNo")), enterBillNo));
        setEnterType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterType")), enterType));
        setEnterTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterTypeName")), enterTypeName));
        setMatNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matNum")), matNum));
        setMatName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matName")), matName));
        setMatSpec(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matSpec")), matSpec));
        setModel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("model")), model));
        setBatchNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("batchNo")), batchNo));
        setBatchNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("batchNum")), batchNum));
        setUnitNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unitNum")), unitNum));
        setUnitName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unitName")), unitName));
        setEnterNum(NumberUtils.toInteger(StringUtils.toString(map.get("enterNum")), enterNum));
        setEnterPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("enterPrice")), enterPrice));
        setEnterAmount(NumberUtils.toBigDecimal(StringUtils.toString(map.get("enterAmount")), enterAmount));
        setInvoiceNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("invoiceNo")), invoiceNo));
        setManufacturer(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manufacturer")), manufacturer));
        setManufacturerCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manufacturerCode")), manufacturerCode));
        setSurpNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("surpNum")), surpNum));
        setSurpName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("surpName")), surpName));
        setCheckDate(DateUtils.toDate(StringUtils.toString(map.get("checkDate"))));
        setAcquitvDate(DateUtils.toDate(StringUtils.toString(map.get("acquitvDate"))));
        setAcquitvYear(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("acquitvYear")), acquitvYear));
        setRecCreateTime(DateUtils.toDate(StringUtils.toString(map.get("recCreateTime"))));
        setRecCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateName")), recCreateName));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setTransferType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("transferType")), transferType));
        setConfirmName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("confirmName")), confirmName));
        setConfirmDate(DateUtils.toDate(StringUtils.toString(map.get("confirmDate"))));
        setConfirmStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("confirmStatus")), confirmStatus));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
        map.put("purchaseVouch",StringUtils.toString(purchaseVouch, eiMetadata.getMeta("purchaseVouch")));
        map.put("purchaseStaffName",StringUtils.toString(purchaseStaffName, eiMetadata.getMeta("purchaseStaffName")));
        map.put("fundingsourceNum",StringUtils.toString(fundingsourceNum, eiMetadata.getMeta("fundingsourceNum")));
        map.put("fundingsourceName",StringUtils.toString(fundingsourceName, eiMetadata.getMeta("fundingsourceName")));
        map.put("enterBillNo",StringUtils.toString(enterBillNo, eiMetadata.getMeta("enterBillNo")));
        map.put("enterType",StringUtils.toString(enterType, eiMetadata.getMeta("enterType")));
        map.put("enterTypeName",StringUtils.toString(enterTypeName, eiMetadata.getMeta("enterTypeName")));
        map.put("matNum",StringUtils.toString(matNum, eiMetadata.getMeta("matNum")));
        map.put("matName",StringUtils.toString(matName, eiMetadata.getMeta("matName")));
        map.put("matSpec",StringUtils.toString(matSpec, eiMetadata.getMeta("matSpec")));
        map.put("model",StringUtils.toString(model, eiMetadata.getMeta("model")));
        map.put("batchNo",StringUtils.toString(batchNo, eiMetadata.getMeta("batchNo")));
        map.put("batchNum",StringUtils.toString(batchNum, eiMetadata.getMeta("batchNum")));
        map.put("unitNum",StringUtils.toString(unitNum, eiMetadata.getMeta("unitNum")));
        map.put("unitName",StringUtils.toString(unitName, eiMetadata.getMeta("unitName")));
        map.put("enterNum",StringUtils.toString(enterNum, eiMetadata.getMeta("enterNum")));
        map.put("enterPrice",StringUtils.toString(enterPrice, eiMetadata.getMeta("enterPrice")));
        map.put("enterAmount",StringUtils.toString(enterAmount, eiMetadata.getMeta("enterAmount")));
        map.put("invoiceNo",StringUtils.toString(invoiceNo, eiMetadata.getMeta("invoiceNo")));
        map.put("manufacturer",StringUtils.toString(manufacturer, eiMetadata.getMeta("manufacturer")));
        map.put("manufacturerCode",StringUtils.toString(manufacturerCode, eiMetadata.getMeta("manufacturerCode")));
        map.put("surpNum",StringUtils.toString(surpNum, eiMetadata.getMeta("surpNum")));
        map.put("surpName",StringUtils.toString(surpName, eiMetadata.getMeta("surpName")));
        map.put("checkDate",StringUtils.toString(checkDate, eiMetadata.getMeta("checkDate")));
        map.put("acquitvDate",StringUtils.toString(acquitvDate, eiMetadata.getMeta("acquitvDate")));
        map.put("acquitvYear",StringUtils.toString(acquitvYear, eiMetadata.getMeta("acquitvYear")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recCreateName",StringUtils.toString(recCreateName, eiMetadata.getMeta("recCreateName")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("transferType",StringUtils.toString(transferType, eiMetadata.getMeta("transferType")));
        map.put("confirmName",StringUtils.toString(confirmName, eiMetadata.getMeta("confirmName")));
        map.put("confirmDate",StringUtils.toString(confirmDate, eiMetadata.getMeta("confirmDate")));
        map.put("confirmStatus",StringUtils.toString(confirmStatus, eiMetadata.getMeta("confirmStatus")));
        return map;
    }
}