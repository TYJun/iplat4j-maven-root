/**
* Generate time : 2021-08-18 16:17:59
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ci.pk.domain;

import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CiInven ： 盘库主表实体
* 
*/
public class CiInven extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreator = " ";		/* 记录创建责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/
    private String dataGroupCode = " ";		/* 院区（账套）*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String id = " ";		/* 主键*/
    private String invenBillNo = " ";		/* 盘点单号*/
    private String invenStatus = " ";		/* 状态 */
    private String wareHouseNo = " ";		/* 仓库号*/
    private String wareHouseName = " ";		/* 仓库名称*/
    private String storageNo = " ";		/* 库位号*/
    private String stackNo = " ";		/* 货位号*/
    private String billMakeTime ;		/* 制单日期*/
    private String billMaker = " ";		/* 制单人员*/
    private String billMakerName = " ";		/* 制单人姓名*/
    private String billCheckTime ;		/* 审核日期*/
    private String billChecker = " ";		/* 审核人员*/
    private String billCheckerName = " ";		/* 审核人姓名*/

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

        eiColumn = new EiColumn("invenBillNo");
        eiColumn.setDescName("盘点单号");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("invenStatus");
        eiColumn.setDescName("状态");
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

        eiColumn = new EiColumn("billMakerName");
        eiColumn.setDescName("制单人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billCheckTime");
        eiColumn.setDescName("审核日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billChecker");
        eiColumn.setDescName("审核人员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billCheckerName");
        eiColumn.setDescName("审核人姓名");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CiInven() {
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
     * get the dataGroupCode - 院区（账套）
     * @return the recReviseTime
     */
    public String getDataGroupCode() {
		return dataGroupCode;
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
     * get the invenStatus - 状态
     * @return the invenBillNo
     */
    public String getInvenStatus() {
		return invenStatus;
	}

    /**
     * set the invenStatus - 状态
     */
	public void setInvenStatus(String invenStatus) {
		this.invenStatus = invenStatus;
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
     * get the billMakerName - 制单人姓名
     * @return the billMakerName
     */
    public String getBillMakerName() {
        return this.billMakerName;
    }

    /**
     * set the billMakerName - 制单人姓名
     */
    public void setBillMakerName(String billMakerName) {
        this.billMakerName = billMakerName;
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
     * get the billCheckerName - 审核人姓名
     * @return the billCheckerName
     */
    public String getBillCheckerName() {
        return this.billCheckerName;
    }

    /**
     * set the billCheckerName - 审核人姓名
     */
    public void setBillCheckerName(String billCheckerName) {
        this.billCheckerName = billCheckerName;
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
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setInvenBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("invenBillNo")), invenBillNo));
        setInvenStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("invenStatus")), invenStatus));
        setWareHouseNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wareHouseNo")), wareHouseNo));
        setWareHouseName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wareHouseName")), wareHouseName));
        setStorageNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("storageNo")), storageNo));
        setStackNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("stackNo")), stackNo));
        setBillMakeTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billMakeTime")), billMakeTime));
        setBillMaker(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billMaker")), billMaker));
        setBillMakerName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billMakerName")), billMakerName));
        setBillCheckTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billCheckTime")), billCheckTime));
        setBillChecker(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billChecker")), billChecker));
        setBillCheckerName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billCheckerName")), billCheckerName));
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
        map.put("invenBillNo",StringUtils.toString(invenBillNo, eiMetadata.getMeta("invenBillNo")));
        map.put("invenStatus",StringUtils.toString(invenStatus, eiMetadata.getMeta("invenStatus")));
        map.put("wareHouseNo",StringUtils.toString(wareHouseNo, eiMetadata.getMeta("wareHouseNo")));
        map.put("wareHouseName",StringUtils.toString(wareHouseName, eiMetadata.getMeta("wareHouseName")));
        map.put("storageNo",StringUtils.toString(storageNo, eiMetadata.getMeta("storageNo")));
        map.put("stackNo",StringUtils.toString(stackNo, eiMetadata.getMeta("stackNo")));
        map.put("billMakeTime",StringUtils.toString(billMakeTime, eiMetadata.getMeta("billMakeTime")));
        map.put("billMaker",StringUtils.toString(billMaker, eiMetadata.getMeta("billMaker")));
        map.put("billMakerName",StringUtils.toString(billMakerName, eiMetadata.getMeta("billMakerName")));
        map.put("billCheckTime",StringUtils.toString(billCheckTime, eiMetadata.getMeta("billCheckTime")));
        map.put("billChecker",StringUtils.toString(billChecker, eiMetadata.getMeta("billChecker")));
        map.put("billCheckerName",StringUtils.toString(billCheckerName, eiMetadata.getMeta("billCheckerName")));
        return map;
    }
}