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
* CiOut : 出库主表实体
* 
*/
public class CiOut extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreator = " ";		/* 记录创建责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/
    private String dataGroupCode = " ";		/* 院区（账套）*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String id = " ";		/* 主键*/
    private String outBillNo = " ";		/* 出库单号*/
    private String outType = " ";		/* 出库类别*/
    private String outTypeName = " ";		/* 出库类型名称*/
    private String originBillNo = " ";		/* 来源单据号*/
    private String originBillType = " ";		/* 来源单据类型*/
    private String wareHouseNo = " ";		/* 仓库号*/
    private String wareHouseName = " ";		/* 仓库名称*/
    private String storageNo = " ";		/* 库位号*/
    private String stackNo = " ";		/* 货位号*/
    private String billMakeTime ;		/* 制单日期*/
    private String billMaker = " ";		/* 制单人员*/
    private String billMakerName = " ";		/* 制单人名字*/
    private String billCheckTime ;		/* 审核日期*/
    private String billChecker = " ";		/* 审核人员*/
    private String billCheckerName = " ";		/* 审核人名字*/
    private String userWorkerNo = " ";		/* 领用人*/
    private String userWorkerName = " ";		/* 领用人名称*/
    private String userDeptNum = " ";		/* 领用科室*/
    private String userDeptName = " ";		/* 领用科室名称*/
    private Integer isCheck = new Integer(0);		/* 是否已经审核(0/否,1/是)*/
    private String applySign;/*审核状态中文*/

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

        eiColumn = new EiColumn("outBillNo");
        eiColumn.setDescName("出库单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outType");
        eiColumn.setDescName("出库类别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outTypeName");
        eiColumn.setDescName("出库类型名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("originBillNo");
        eiColumn.setDescName("来源单据号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("originBillType");
        eiColumn.setDescName("来源单据类型");
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
        eiColumn.setDescName("制单人名字");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billCheckTime");
        eiColumn.setDescName("审核日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billChecker");
        eiColumn.setDescName("审核人员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billCheckerName");
        eiColumn.setDescName("审核人名字");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userWorkerNo");
        eiColumn.setDescName("领用人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userWorkerName");
        eiColumn.setDescName("领用人名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userDeptNum");
        eiColumn.setDescName("领用科室");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userDeptName");
        eiColumn.setDescName("领用科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isCheck");
        eiColumn.setDescName("是否已经审核(0/否,1/是)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applySign");
        eiColumn.setDescName("申请状态");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CiOut() {
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
     * get the billMakerName - 制单人名字
     * @return the billMakerName
     */
    public String getBillMakerName() {
        return this.billMakerName;
    }

    /**
     * set the billMakerName - 制单人名字
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
     * get the billCheckerName - 审核人名字
     * @return the billCheckerName
     */
    public String getBillCheckerName() {
        return this.billCheckerName;
    }

    /**
     * set the billCheckerName - 审核人名字
     */
    public void setBillCheckerName(String billCheckerName) {
        this.billCheckerName = billCheckerName;
    }

    /**
     * get the userWorkerNo - 领用人
     * @return the userWorkerNo
     */
    public String getUserWorkerNo() {
        return this.userWorkerNo;
    }

    /**
     * set the userWorkerNo - 领用人
     */
    public void setUserWorkerNo(String userWorkerNo) {
        this.userWorkerNo = userWorkerNo;
    }

    /**
     * get the userWorkerName - 领用人名称
     * @return the userWorkerName
     */
    public String getUserWorkerName() {
        return this.userWorkerName;
    }

    /**
     * set the userWorkerName - 领用人名称
     */
    public void setUserWorkerName(String userWorkerName) {
        this.userWorkerName = userWorkerName;
    }

    /**
     * get the userDeptNum - 领用科室
     * @return the userDeptNum
     */
    public String getUserDeptNum() {
        return this.userDeptNum;
    }

    /**
     * set the userDeptNum - 领用科室
     */
    public void setUserDeptNum(String userDeptNum) {
        this.userDeptNum = userDeptNum;
    }

    /**
     * get the userDeptName - 领用科室名称
     * @return the userDeptName
     */
    public String getUserDeptName() {
        return this.userDeptName;
    }

    /**
     * set the userDeptName - 领用科室名称
     */
    public void setUserDeptName(String userDeptName) {
        this.userDeptName = userDeptName;
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
     * get the applySign - 申请状态
     * @return the applySign
     */
    public String getApplySign() {
        return this.applySign;
    }

    /**
     * set the applySign - 申请状态
     */
    public void setApplySign(String applySign) {
        this.applySign = applySign;
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
        setOutBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outBillNo")), outBillNo));
        setOutType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outType")), outType));
        setOutTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outTypeName")), outTypeName));
        setOriginBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("originBillNo")), originBillNo));
        setOriginBillType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("originBillType")), originBillType));
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
        setUserWorkerNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userWorkerNo")), userWorkerNo));
        setUserWorkerName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userWorkerName")), userWorkerName));
        setUserDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userDeptNum")), userDeptNum));
        setUserDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userDeptName")), userDeptName));
        setIsCheck(NumberUtils.toInteger(StringUtils.toString(map.get("isCheck")), isCheck));
        setApplySign(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applySign")), applySign));
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
        map.put("outBillNo",StringUtils.toString(outBillNo, eiMetadata.getMeta("outBillNo")));
        map.put("outType",StringUtils.toString(outType, eiMetadata.getMeta("outType")));
        map.put("outTypeName",StringUtils.toString(outTypeName, eiMetadata.getMeta("outTypeName")));
        map.put("originBillNo",StringUtils.toString(originBillNo, eiMetadata.getMeta("originBillNo")));
        map.put("originBillType",StringUtils.toString(originBillType, eiMetadata.getMeta("originBillType")));
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
        map.put("userWorkerNo",StringUtils.toString(userWorkerNo, eiMetadata.getMeta("userWorkerNo")));
        map.put("userWorkerName",StringUtils.toString(userWorkerName, eiMetadata.getMeta("userWorkerName")));
        map.put("userDeptNum",StringUtils.toString(userDeptNum, eiMetadata.getMeta("userDeptNum")));
        map.put("userDeptName",StringUtils.toString(userDeptName, eiMetadata.getMeta("userDeptName")));
        map.put("isCheck",StringUtils.toString(isCheck, eiMetadata.getMeta("isCheck")));
        map.put("applySign",StringUtils.toString(applySign, eiMetadata.getMeta("applySign")));
        return map;
    }
}