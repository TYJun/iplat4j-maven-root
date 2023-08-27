/**
 * Generate time : 2022-02-14 15:05:43
 * Version : 6.0.731.201809200158
 */
package com.baosight.wilp.ci.sq.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * CiApply
 *
 */
public class CiReceive extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreator = " ";		/* 记录创建责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/
    private String dataGroupCode = " ";		/* 院区（账套）*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String id = " ";		/* 主键*/
    private String applyBillNo = " ";		/* 申请单号*/
    private String applyTypeNum = " ";		/* 领用类型编码*/
    private String applyTypeName = " ";		/* 领用类型名称*/
    private String applyCanteenNum = " ";		/* 申请科室编码*/
    private String applyCanteenName = " ";		/* 申请科室名称*/
    private String applyStaffId = " ";		/* 申请人员*/
    private String billMakeTime = " ";		/* 制单日期*/
    private String billMaker = " ";		/* 制单人员*/
    private String billMakerName = " ";		/* 制单人员*/
    private String billCheckTime = " ";		/* 审核日期*/
    private String billChecker = " ";		/* 审核人员*/
    private String billCheckerName = " ";		/* 审核人员*/
    private String applySign = " ";		/* 申请状态*/
    private String wareHouseNo = " ";		/* 仓库号*/
    private String wareHouseName = " ";		/* 仓库名称*/
    private String emo = " ";		/* 驳回理由*/
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

        eiColumn = new EiColumn("applyBillNo");
        eiColumn.setDescName("申请单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applyTypeNum");
        eiColumn.setDescName("领用类型编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applyTypeName");
        eiColumn.setDescName("领用类型名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applyCanteenNum");
        eiColumn.setDescName("申请科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applyCanteenName");
        eiColumn.setDescName("申请科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applyStaffId");
        eiColumn.setDescName("申请人员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billMakeTime");
        eiColumn.setDescName("制单日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billMaker");
        eiColumn.setDescName("制单人员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billMakerName");
        eiColumn.setDescName("制单人员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billCheckTime");
        eiColumn.setDescName("审核日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billChecker");
        eiColumn.setDescName("审核人员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billCheckerName");
        eiColumn.setDescName("审核人员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("applySign");
        eiColumn.setDescName("申请状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wareHouseNo");
        eiColumn.setDescName("仓库号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wareHouseName");
        eiColumn.setDescName("仓库名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("emo");
        eiColumn.setDescName("驳回理由");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CiReceive() {
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
     * get the applyBillNo - 申请单号
     * @return the applyBillNo
     */
    public String getApplyBillNo() {
        return this.applyBillNo;
    }

    /**
     * set the applyBillNo - 申请单号
     */
    public void setApplyBillNo(String applyBillNo) {
        this.applyBillNo = applyBillNo;
    }

    /**
     * get the applyTypeNum - 领用类型编码
     * @return the applyTypeNum
     */
    public String getApplyTypeNum() {
        return this.applyTypeNum;
    }

    /**
     * set the applyTypeNum - 领用类型编码
     */
    public void setApplyTypeNum(String applyTypeNum) {
        this.applyTypeNum = applyTypeNum;
    }

    /**
     * get the applyTypeName - 领用类型名称
     * @return the applyTypeName
     */
    public String getApplyTypeName() {
        return this.applyTypeName;
    }

    /**
     * set the applyTypeName - 领用类型名称
     */
    public void setApplyTypeName(String applyTypeName) {
        this.applyTypeName = applyTypeName;
    }

    /**
     * get the applyDeptNum - 申请科室编码
     * @return the applyDeptNum
     */
    public String getApplyCanteenNum() {
        return this.applyCanteenNum;
    }

    /**
     * set the applyDeptNum - 申请科室编码
     */
    public void setApplyCanteenNum(String applyCanteenNum) {
        this.applyCanteenNum = applyCanteenNum;
    }

    /**
     * get the applyDeptName - 申请科室名称
     * @return the applyDeptName
     */
    public String getApplyCanteenName() {
        return this.applyCanteenName;
    }

    /**
     * set the applyDeptName - 申请科室名称
     */
    public void setApplyCanteenName(String applyCanteenName) {
        this.applyCanteenName = applyCanteenName;
    }

    /**
     * get the applyStaffId - 申请人员
     * @return the applyStaffId
     */
    public String getApplyStaffId() {
        return this.applyStaffId;
    }

    /**
     * set the applyStaffId - 申请人员
     */
    public void setApplyStaffId(String applyStaffId) {
        this.applyStaffId = applyStaffId;
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
     * get the billMakerName - 制单人员
     * @return the billMakerName
     */
    public String getBillMakerName() {
        return this.billMakerName;
    }

    /**
     * set the billMakerName - 制单人员
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
     * get the billCheckerName - 审核人员
     * @return the billCheckerName
     */
    public String getBillCheckerName() {
        return this.billCheckerName;
    }

    /**
     * set the billCheckerName - 审核人员
     */
    public void setBillCheckerName(String billCheckerName) {
        this.billCheckerName = billCheckerName;
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
     * set the wareHouseName - 仓库名称
     */
    public void setEmo(String emo) {
        this.emo = emo;
    }
    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setApplyBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyBillNo")), applyBillNo));
        setApplyTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyTypeNum")), applyTypeNum));
        setApplyTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyTypeName")), applyTypeName));
        setApplyCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyCanteenNum")), applyCanteenNum));
        setApplyCanteenName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyCanteenName")), applyCanteenName));
        setApplyStaffId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applyStaffId")), applyStaffId));
        setBillMakeTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billMakeTime")), billMakeTime));
        setBillMaker(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billMaker")), billMaker));
        setBillMakerName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billMakerName")), billMakerName));
        setBillCheckTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billCheckTime")), billCheckTime));
        setBillChecker(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billChecker")), billChecker));
        setBillCheckerName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billCheckerName")), billCheckerName));
        setApplySign(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("applySign")), applySign));
        setWareHouseNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wareHouseNo")), wareHouseNo));
        setWareHouseName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wareHouseName")), wareHouseName));
        setEmo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("emo")), emo));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("applyBillNo",StringUtils.toString(applyBillNo, eiMetadata.getMeta("applyBillNo")));
        map.put("applyTypeNum",StringUtils.toString(applyTypeNum, eiMetadata.getMeta("applyTypeNum")));
        map.put("applyTypeName",StringUtils.toString(applyTypeName, eiMetadata.getMeta("applyTypeName")));
        map.put("applyCanteenNum",StringUtils.toString(applyCanteenNum, eiMetadata.getMeta("applyCanteenNum")));
        map.put("applyCanteenName",StringUtils.toString(applyCanteenName, eiMetadata.getMeta("applyCanteenName")));
        map.put("applyStaffId",StringUtils.toString(applyStaffId, eiMetadata.getMeta("applyStaffId")));
        map.put("billMakeTime",StringUtils.toString(billMakeTime, eiMetadata.getMeta("billMakeTime")));
        map.put("billMaker",StringUtils.toString(billMaker, eiMetadata.getMeta("billMaker")));
        map.put("billMakerName",StringUtils.toString(billMakerName, eiMetadata.getMeta("billMakerName")));
        map.put("billCheckTime",StringUtils.toString(billCheckTime, eiMetadata.getMeta("billCheckTime")));
        map.put("billChecker",StringUtils.toString(billChecker, eiMetadata.getMeta("billChecker")));
        map.put("billCheckerName",StringUtils.toString(billCheckerName, eiMetadata.getMeta("billCheckerName")));
        map.put("applySign",StringUtils.toString(applySign, eiMetadata.getMeta("applySign")));
        map.put("wareHouseNo",StringUtils.toString(wareHouseNo, eiMetadata.getMeta("wareHouseNo")));
        map.put("wareHouseName",StringUtils.toString(wareHouseName, eiMetadata.getMeta("wareHouseName")));
        map.put("emo",StringUtils.toString(emo, eiMetadata.getMeta("emo")));
        return map;
    }
}