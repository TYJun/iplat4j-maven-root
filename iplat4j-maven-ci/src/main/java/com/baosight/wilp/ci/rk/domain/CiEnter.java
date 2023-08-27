/**
* Generate time : 2021-08-19 15:16:50
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ci.rk.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CiEnter : 入库主表实体
* 
*/
public class CiEnter extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreator = " ";		/* 记录创建责任者*/
    private String recCreateTime =" ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recReviseTime = "";		/* 记录修改时间*/
    private String dataGroupCode = " ";		/* 院区（账套）*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String id = " ";		/* 主键*/
    private String enterBillNo = " ";		/* 入库单号*/
    private String enterType = " ";		/* 入库类别*/
    private String enterTypeName = " ";		/* 入库类型名称*/
    private String originBillNo = " ";		/* 来源单据号*/
    private String originBillType = " ";		/* 来源单据类型*/
    private String originBillTypeName = " ";		/* 来源单据类型名称*/
    private String contNo = " ";		/* 合同号*/
    private String purcDemandDetailNo = " ";		/* 采购计划明细号*/
    private String wareHouseNo = " ";		/* 仓库号*/
    private String wareHouseName = " ";		/* 仓库名称*/
    private String storageNo = " ";		/* 库位号*/
    private String stackNo = " ";		/* 货位号*/
    private String billMakeTime = " ";		/* 制单日期*/
    private String billMaker = " ";		/* 制单人员*/
    private String billCheckTime = " ";		/* 审核日期*/
    private String billChecker = " ";		/* 审核人员*/
    private String invoiceNum = " ";		/* 发票流水号*/
    private Integer isCheck = new Integer(0);		/* 是否已经审核(0/否,1/是)*/
    private String billCheckerName = " ";		/* 审核人员姓名*/
    private String billMakerName = " ";		/* 制单人员姓名*/
    private String batchNo = " ";		/* 批次号*/

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

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("院区（账套）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
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

        eiColumn = new EiColumn("originBillNo");
        eiColumn.setDescName("来源单据号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("originBillType");
        eiColumn.setDescName("来源单据类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("originBillTypeName");
        eiColumn.setDescName("来源单据类型名称");
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

        eiColumn = new EiColumn("billMakeTime");
        eiColumn.setDescName("制单日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billMaker");
        eiColumn.setDescName("制单人员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billCheckTime");
        eiColumn.setDescName("审核日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billChecker");
        eiColumn.setDescName("审核人员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceNum");
        eiColumn.setDescName("发票流水号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isCheck");
        eiColumn.setDescName("是否已经审核(0/否,1/是)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billCheckerName");
        eiColumn.setDescName("审核人员姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billMakerName");
        eiColumn.setDescName("制单人员姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("batchNo");
        eiColumn.setDescName("批次号");
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
    public CiEnter() {
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
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the dataGroupCode - 院区（账套）
     * @return the dataGroupCode
     */
    public String getDataGroupCode() {
        return this.dataGroupCode;
    }

    /**
     * set the dataGroupCode - 院区（账套）
     */
    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
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
     * get the billMakeTime - 制单日期
     * @return the billMakeTime
     */
    public String getBillMakeTime() {
        return this.billMakeTime;
    }

    /**
     * set the billMakeTime - 制单日期
     */
    public void setBillMakeTime(String billMakeTime) {
        this.billMakeTime = billMakeTime;
    }

    /**
     * get the billMaker - 制单人员
     * @return the billMaker
     */
    public String getBillMaker() {
        return this.billMaker;
    }

    /**
     * set the billMaker - 制单人员
     */
    public void setBillMaker(String billMaker) {
        this.billMaker = billMaker;
    }

    /**
     * get the billCheckTime - 审核日期
     * @return the billCheckTime
     */
    public String getBillCheckTime() {
        return this.billCheckTime;
    }

    /**
     * set the billCheckTime - 审核日期
     */
    public void setBillCheckTime(String billCheckTime) {
        this.billCheckTime = billCheckTime;
    }

    /**
     * get the billChecker - 审核人员
     * @return the billChecker
     */
    public String getBillChecker() {
        return this.billChecker;
    }

    /**
     * set the billChecker - 审核人员
     */
    public void setBillChecker(String billChecker) {
        this.billChecker = billChecker;
    }

    /**
     * get the invoiceNum - 发票流水号
     * @return the invoiceNum
     */
    public String getInvoiceNum() {
        return this.invoiceNum;
    }

    /**
     * set the invoiceNum - 发票流水号
     */
    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    /**
     * get the isCheck - 是否已经审核(0/否,1/是)
     * @return the isCheck
     */
    public Integer getIsCheck() {
        return this.isCheck;
    }

    /**
     * set the isCheck - 是否已经审核(0/否,1/是)
     */
    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    /**
     * get the billCheckerName - 审核人员姓名
     * @return the billCheckerName
     */
    public String getBillCheckerName() {
        return this.billCheckerName;
    }

    /**
     * set the billCheckerName - 审核人员姓名
     */
    public void setBillCheckerName(String billCheckerName) {
        this.billCheckerName = billCheckerName;
    }

    /**
     * get the billMakerName - 制单人员姓名
     * @return the billMakerName
     */
    public String getBillMakerName() {
        return this.billMakerName;
    }

    /**
     * set the billMakerName - 制单人员姓名
     */
    public void setBillMakerName(String billMakerName) {
        this.billMakerName = billMakerName;
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
    @SuppressWarnings("rawtypes")
	public void fromMap(Map map) {

        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setEnterBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterBillNo")), enterBillNo));
        setEnterType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterType")), enterType));
        setEnterTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("enterTypeName")), enterTypeName));
        setOriginBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("originBillNo")), originBillNo));
        setOriginBillType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("originBillType")), originBillType));
        setOriginBillTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("originBillTypeName")), originBillTypeName));
        setContNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contNo")), contNo));
        setPurcDemandDetailNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("purcDemandDetailNo")), purcDemandDetailNo));
        setWareHouseNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wareHouseNo")), wareHouseNo));
        setWareHouseName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wareHouseName")), wareHouseName));
        setStorageNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("storageNo")), storageNo));
        setStackNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("stackNo")), stackNo));
        setBillMakeTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billMakeTime")), billMakeTime));
        setBillMaker(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billMaker")), billMaker));
        setBillCheckTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billCheckTime")), billCheckTime));
        setBillChecker(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billChecker")), billChecker));
        setInvoiceNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("invoiceNum")), invoiceNum));
        setIsCheck(NumberUtils.toInteger(StringUtils.toString(map.get("isCheck")), isCheck));
        setBillCheckerName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billCheckerName")), billCheckerName));
        setBillMakerName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billMakerName")), billMakerName));
        setBatchNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("batchNo")), batchNo));
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
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("enterBillNo",StringUtils.toString(enterBillNo, eiMetadata.getMeta("enterBillNo")));
        map.put("enterType",StringUtils.toString(enterType, eiMetadata.getMeta("enterType")));
        map.put("enterTypeName",StringUtils.toString(enterTypeName, eiMetadata.getMeta("enterTypeName")));
        map.put("originBillNo",StringUtils.toString(originBillNo, eiMetadata.getMeta("originBillNo")));
        map.put("originBillType",StringUtils.toString(originBillType, eiMetadata.getMeta("originBillType")));
        map.put("originBillTypeName",StringUtils.toString(originBillTypeName, eiMetadata.getMeta("originBillTypeName")));
        map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
        map.put("purcDemandDetailNo",StringUtils.toString(purcDemandDetailNo, eiMetadata.getMeta("purcDemandDetailNo")));
        map.put("wareHouseNo",StringUtils.toString(wareHouseNo, eiMetadata.getMeta("wareHouseNo")));
        map.put("wareHouseName",StringUtils.toString(wareHouseName, eiMetadata.getMeta("wareHouseName")));
        map.put("storageNo",StringUtils.toString(storageNo, eiMetadata.getMeta("storageNo")));
        map.put("stackNo",StringUtils.toString(stackNo, eiMetadata.getMeta("stackNo")));
        map.put("billMakeTime",StringUtils.toString(billMakeTime, eiMetadata.getMeta("billMakeTime")));
        map.put("billMaker",StringUtils.toString(billMaker, eiMetadata.getMeta("billMaker")));
        map.put("billCheckTime",StringUtils.toString(billCheckTime, eiMetadata.getMeta("billCheckTime")));
        map.put("billChecker",StringUtils.toString(billChecker, eiMetadata.getMeta("billChecker")));
        map.put("invoiceNum",StringUtils.toString(invoiceNum, eiMetadata.getMeta("invoiceNum")));
        map.put("isCheck",StringUtils.toString(isCheck, eiMetadata.getMeta("isCheck")));
        map.put("billCheckerName",StringUtils.toString(billCheckerName, eiMetadata.getMeta("billCheckerName")));
        map.put("billMakerName",StringUtils.toString(billMakerName, eiMetadata.getMeta("billMakerName")));
        map.put("batchNo",StringUtils.toString(batchNo, eiMetadata.getMeta("batchNo")));
        map.put("shelfLife",StringUtils.toString(shelfLife, eiMetadata.getMeta("shelfLife")));
        map.put("expirationDate",StringUtils.toString(expirationDate, eiMetadata.getMeta("expirationDate")));
        return map;
    }
}