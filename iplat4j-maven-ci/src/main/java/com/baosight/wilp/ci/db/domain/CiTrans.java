/**
* Generate time : 2021-08-20 9:42:39
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ci.db.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CiTrans ： 库存调拨主表实体
* 
*/
public class CiTrans extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreator = " ";		/* 记录创建责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/
    private String dataGroupCode = " ";		/* 院区（账套）*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String id = " ";		/* 主键*/
    private String transBillNo = " ";		/* 调拨单号*/
    private String transType = " ";		/* 调拨类别*/
    private String outDeptNum = " ";		/* 调出部门编码*/
    private String outDeptName = " ";		/* 调出部门名称*/
    private String outWareHouseNo = " ";		/* 调出仓库*/
    private String outWareHouseName = " ";		/* 调出仓库*/
    private String outStorageNo = " ";		/* 调出库位*/
    private String outStackNo = " ";		/* 调出货位*/
    private String inDeptNum = " ";		/* 调入部门*/
    private String inDeptName = " ";		/* 调入名称*/
    private String inWareHouseNo = " ";		/* 调入仓库*/
    private String inWareHouseName = " ";		/* 调入仓库*/
    private String inStorageNo = " ";		/* 调入库位*/
    private String inStackNo = " ";		/* 调入货位*/
    private String billMakeTime = " ";		/* 制单日期*/
    private String billMaker = " ";		/* 制单人员*/
    private String billMakerNo = " ";		/* 制单人*/
    private String billCheckTime = " ";		/* 审核日期*/
    private String billChecker = " ";		/* 审核人员*/
    private String receiver = " ";		/* 接收人*/
    private Integer isCheck = new Integer(0);		/* 是否已经审核(0/否,1/是)*/

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

        eiColumn = new EiColumn("transBillNo");
        eiColumn.setDescName("调拨单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("transType");
        eiColumn.setDescName("调拨类别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outDeptNum");
        eiColumn.setDescName("调出部门编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outDeptName");
        eiColumn.setDescName("调出部门名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outWareHouseNo");
        eiColumn.setDescName("调出仓库");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outWareHouseName");
        eiColumn.setDescName("调出仓库");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outStorageNo");
        eiColumn.setDescName("调出库位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outStackNo");
        eiColumn.setDescName("调出货位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("inDeptNum");
        eiColumn.setDescName("调入部门");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("inDeptName");
        eiColumn.setDescName("调入名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("inWareHouseNo");
        eiColumn.setDescName("调入仓库");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("inWareHouseName");
        eiColumn.setDescName("调入仓库");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("inStorageNo");
        eiColumn.setDescName("调入库位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("inStackNo");
        eiColumn.setDescName("调入货位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billMakeTime");
        eiColumn.setDescName("制单日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billMaker");
        eiColumn.setDescName("制单人员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billMakerNo");
        eiColumn.setDescName("制单人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billCheckTime");
        eiColumn.setDescName("审核日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billChecker");
        eiColumn.setDescName("审核人员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("receiver");
        eiColumn.setDescName("接收人");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("isCheck");
        eiColumn.setDescName("审核标记");
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
    public CiTrans() {
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
     * get the transBillNo - 调拨单号
     * @return the transBillNo
     */
    public String getTransBillNo() {
        return this.transBillNo;
    }

    /**
     * set the transBillNo - 调拨单号
     */
    public void setTransBillNo(String transBillNo) {
        this.transBillNo = transBillNo;
    }

    /**
     * get the transType - 调拨类别
     * @return the transType
     */
    public String getTransType() {
        return this.transType;
    }

    /**
     * set the transType - 调拨类别
     */
    public void setTransType(String transType) {
        this.transType = transType;
    }

    /**
     * get the outDeptNum - 调出部门编码
     * @return the outDeptNum
     */
    public String getOutDeptNum() {
        return this.outDeptNum;
    }

    /**
     * set the outDeptNum - 调出部门编码
     */
    public void setOutDeptNum(String outDeptNum) {
        this.outDeptNum = outDeptNum;
    }

    /**
     * get the outDeptName - 调出部门名称
     * @return the outDeptName
     */
    public String getOutDeptName() {
        return this.outDeptName;
    }

    /**
     * set the outDeptName - 调出部门名称
     */
    public void setOutDeptName(String outDeptName) {
        this.outDeptName = outDeptName;
    }

    /**
     * get the outWareHouseNo - 调出仓库
     * @return the outWareHouseNo
     */
    public String getOutWareHouseNo() {
        return this.outWareHouseNo;
    }

    /**
     * set the outWareHouseNo - 调出仓库
     */
    public void setOutWareHouseNo(String outWareHouseNo) {
        this.outWareHouseNo = outWareHouseNo;
    }

    /**
     * get the outWareHouseName - 调出仓库
     * @return the outWareHouseName
     */
    public String getOutWareHouseName() {
        return this.outWareHouseName;
    }

    /**
     * set the outWareHouseName - 调出仓库
     */
    public void setOutWareHouseName(String outWareHouseName) {
        this.outWareHouseName = outWareHouseName;
    }

    /**
     * get the outStorageNo - 调出库位
     * @return the outStorageNo
     */
    public String getOutStorageNo() {
        return this.outStorageNo;
    }

    /**
     * set the outStorageNo - 调出库位
     */
    public void setOutStorageNo(String outStorageNo) {
        this.outStorageNo = outStorageNo;
    }

    /**
     * get the outStackNo - 调出货位
     * @return the outStackNo
     */
    public String getOutStackNo() {
        return this.outStackNo;
    }

    /**
     * set the outStackNo - 调出货位
     */
    public void setOutStackNo(String outStackNo) {
        this.outStackNo = outStackNo;
    }

    /**
     * get the inDeptNum - 调入部门
     * @return the inDeptNum
     */
    public String getInDeptNum() {
        return this.inDeptNum;
    }

    /**
     * set the inDeptNum - 调入部门
     */
    public void setInDeptNum(String inDeptNum) {
        this.inDeptNum = inDeptNum;
    }

    /**
     * get the inDeptName - 调入名称
     * @return the inDeptName
     */
    public String getInDeptName() {
        return this.inDeptName;
    }

    /**
     * set the inDeptName - 调入名称
     */
    public void setInDeptName(String inDeptName) {
        this.inDeptName = inDeptName;
    }

    /**
     * get the inWareHouseNo - 调入仓库
     * @return the inWareHouseNo
     */
    public String getInWareHouseNo() {
        return this.inWareHouseNo;
    }

    /**
     * set the inWareHouseNo - 调入仓库
     */
    public void setInWareHouseNo(String inWareHouseNo) {
        this.inWareHouseNo = inWareHouseNo;
    }

    /**
     * get the inWareHouseName - 调入仓库
     * @return the inWareHouseName
     */
    public String getInWareHouseName() {
        return this.inWareHouseName;
    }

    /**
     * set the inWareHouseName - 调入仓库
     */
    public void setInWareHouseName(String inWareHouseName) {
        this.inWareHouseName = inWareHouseName;
    }

    /**
     * get the inStorageNo - 调入库位
     * @return the inStorageNo
     */
    public String getInStorageNo() {
        return this.inStorageNo;
    }

    /**
     * set the inStorageNo - 调入库位
     */
    public void setInStorageNo(String inStorageNo) {
        this.inStorageNo = inStorageNo;
    }

    /**
     * get the inStackNo - 调入货位
     * @return the inStackNo
     */
    public String getInStackNo() {
        return this.inStackNo;
    }

    /**
     * set the inStackNo - 调入货位
     */
    public void setInStackNo(String inStackNo) {
        this.inStackNo = inStackNo;
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
     * get the billMakerNo - 制单人
     * @return the billMakerNo
     */
    public String getBillMakerNo() {
        return this.billMakerNo;
    }

    /**
     * set the billMakerNo - 制单人
     */
    public void setBillMakerNo(String billMakerNo) {
        this.billMakerNo = billMakerNo;
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
     * get the receiver - 接收人
     * @return the receiver
     */
    public String getReceiver() {
        return this.receiver;
    }

    /**
     * set the receiver - 接收人
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
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
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setTransBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("transBillNo")), transBillNo));
        setTransType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("transType")), transType));
        setOutDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outDeptNum")), outDeptNum));
        setOutDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outDeptName")), outDeptName));
        setOutWareHouseNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outWareHouseNo")), outWareHouseNo));
        setOutWareHouseName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outWareHouseName")), outWareHouseName));
        setOutStorageNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outStorageNo")), outStorageNo));
        setOutStackNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outStackNo")), outStackNo));
        setInDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("inDeptNum")), inDeptNum));
        setInDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("inDeptName")), inDeptName));
        setInWareHouseNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("inWareHouseNo")), inWareHouseNo));
        setInWareHouseName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("inWareHouseName")), inWareHouseName));
        setInStorageNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("inStorageNo")), inStorageNo));
        setInStackNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("inStackNo")), inStackNo));
        setBillMakeTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billMakeTime")), billMakeTime));
        setBillMaker(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billMaker")), billMaker));
        setBillMakerNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billMakerNo")), billMakerNo));
        setBillCheckTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billCheckTime")), billCheckTime));
        setBillChecker(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billChecker")), billChecker));
        setReceiver(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("receiver")), receiver));
        setIsCheck(NumberUtils.toInteger(StringUtils.toString(map.get("isCheck")), isCheck));
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
        map.put("transBillNo",StringUtils.toString(transBillNo, eiMetadata.getMeta("transBillNo")));
        map.put("transType",StringUtils.toString(transType, eiMetadata.getMeta("transType")));
        map.put("outDeptNum",StringUtils.toString(outDeptNum, eiMetadata.getMeta("outDeptNum")));
        map.put("outDeptName",StringUtils.toString(outDeptName, eiMetadata.getMeta("outDeptName")));
        map.put("outWareHouseNo",StringUtils.toString(outWareHouseNo, eiMetadata.getMeta("outWareHouseNo")));
        map.put("outWareHouseName",StringUtils.toString(outWareHouseName, eiMetadata.getMeta("outWareHouseName")));
        map.put("outStorageNo",StringUtils.toString(outStorageNo, eiMetadata.getMeta("outStorageNo")));
        map.put("outStackNo",StringUtils.toString(outStackNo, eiMetadata.getMeta("outStackNo")));
        map.put("inDeptNum",StringUtils.toString(inDeptNum, eiMetadata.getMeta("inDeptNum")));
        map.put("inDeptName",StringUtils.toString(inDeptName, eiMetadata.getMeta("inDeptName")));
        map.put("inWareHouseNo",StringUtils.toString(inWareHouseNo, eiMetadata.getMeta("inWareHouseNo")));
        map.put("inWareHouseName",StringUtils.toString(inWareHouseName, eiMetadata.getMeta("inWareHouseName")));
        map.put("inStorageNo",StringUtils.toString(inStorageNo, eiMetadata.getMeta("inStorageNo")));
        map.put("inStackNo",StringUtils.toString(inStackNo, eiMetadata.getMeta("inStackNo")));
        map.put("billMakeTime",StringUtils.toString(billMakeTime, eiMetadata.getMeta("billMakeTime")));
        map.put("billMaker",StringUtils.toString(billMaker, eiMetadata.getMeta("billMaker")));
        map.put("billMakerNo",StringUtils.toString(billMakerNo, eiMetadata.getMeta("billMakerNo")));
        map.put("billCheckTime",StringUtils.toString(billCheckTime, eiMetadata.getMeta("billCheckTime")));
        map.put("billChecker",StringUtils.toString(billChecker, eiMetadata.getMeta("billChecker")));
        map.put("receiver",StringUtils.toString(receiver, eiMetadata.getMeta("receiver")));
        map.put("isCheck",StringUtils.toString(isCheck, eiMetadata.getMeta("isCheck")));
        map.put("shelfLife",StringUtils.toString(shelfLife, eiMetadata.getMeta("shelfLife")));
        map.put("expirationDate",StringUtils.toString(expirationDate, eiMetadata.getMeta("expirationDate")));
        return map;
    }
}