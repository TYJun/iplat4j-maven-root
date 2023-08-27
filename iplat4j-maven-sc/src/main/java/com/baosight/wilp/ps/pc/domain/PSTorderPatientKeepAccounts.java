/**
* Generate time : 2021-12-30 21:12:24
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ps.pc.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* SCTorderPatientKeepAccounts
* 
*/
public class PSTorderPatientKeepAccounts extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String recCreator = " ";		/* 记录创建责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String feeFn = " ";		/* 费用结算流水号*/
    private String openId = " ";		/* 住院号*/
    private String userName = " ";		/* 用户姓名*/
    private String userTelNumber = " ";		/* 用户电话*/
    private String building = " ";		/* 楼号*/
    private String buildingName = " ";		/* 楼*/
    private String floor = " ";		/* 层号*/
    private String floorName = " ";		/* 层*/
    private String deptNum = " ";		/* 病区编码*/
    private String deptName = " ";		/* 病区名称*/
    private String wardCode = " ";		/* 病区编码*/
    private String wardName = " ";		/* 病区名称*/
    private String bedNo = " ";		/* 床位号*/
    private String billTotalPrice = " ";		/* 订单总价*/
    private String payType = " ";		/* 支付类型*/
    private String printFlag = " ";		/* 打印标记[已打印()Y/未打印(N)]*/
    private String rejectCode = " ";		/* 作废标记。null/1/2，正常/审核作废/确定作废*/
    private String rejectReason = " ";		/* 作废原因*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("记录创建责任者");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("记录修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("记录修改责任者");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("记录创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("feeFn");
        eiColumn.setDescName("费用结算流水号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("openId");
        eiColumn.setDescName("住院号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userName");
        eiColumn.setDescName("用户姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userTelNumber");
        eiColumn.setDescName("用户电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName("楼号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("buildingName");
        eiColumn.setDescName("楼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floor");
        eiColumn.setDescName("层号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("floorName");
        eiColumn.setDescName("层");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("病区编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("病区名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wardCode");
        eiColumn.setDescName("病区编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wardName");
        eiColumn.setDescName("病区名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bedNo");
        eiColumn.setDescName("床位号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billTotalPrice");
        eiColumn.setDescName("订单总价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payType");
        eiColumn.setDescName("支付类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("printFlag");
        eiColumn.setFieldLength(1);
        eiColumn.setDescName("打印标记[已打印()Y/未打印(N)]");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rejectCode");
        eiColumn.setDescName("作废标记。null/1/2，正常/审核作废/确定作废");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rejectReason");
        eiColumn.setDescName("作废原因");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PSTorderPatientKeepAccounts() {
        initMetaData();
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
     * get the feeFn - 费用结算流水号
     * @return the feeFn
     */
    public String getFeeFn() {
        return this.feeFn;
    }

    /**
     * set the feeFn - 费用结算流水号
     */
    public void setFeeFn(String feeFn) {
        this.feeFn = feeFn;
    }

    /**
     * get the openId - 住院号
     * @return the openId
     */
    public String getOpenId() {
        return this.openId;
    }

    /**
     * set the openId - 住院号
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * get the userName - 用户姓名
     * @return the userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * set the userName - 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * get the userTelNumber - 用户电话
     * @return the userTelNumber
     */
    public String getUserTelNumber() {
        return this.userTelNumber;
    }

    /**
     * set the userTelNumber - 用户电话
     */
    public void setUserTelNumber(String userTelNumber) {
        this.userTelNumber = userTelNumber;
    }

    /**
     * get the building - 楼号
     * @return the building
     */
    public String getBuilding() {
        return this.building;
    }

    /**
     * set the building - 楼号
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * get the buildingName - 楼
     * @return the buildingName
     */
    public String getBuildingName() {
        return this.buildingName;
    }

    /**
     * set the buildingName - 楼
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    /**
     * get the floor - 层号
     * @return the floor
     */
    public String getFloor() {
        return this.floor;
    }

    /**
     * set the floor - 层号
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * get the floorName - 层
     * @return the floorName
     */
    public String getFloorName() {
        return this.floorName;
    }

    /**
     * set the floorName - 层
     */
    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    /**
     * get the deptNum - 病区编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 病区编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptName - 病区名称
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 病区名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the wardCode - 病区编码
     * @return the wardCode
     */
    public String getWardCode() {
        return this.wardCode;
    }

    /**
     * set the wardCode - 病区编码
     */
    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    /**
     * get the wardName - 病区名称
     * @return the wardName
     */
    public String getWardName() {
        return this.wardName;
    }

    /**
     * set the wardName - 病区名称
     */
    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    /**
     * get the bedNo - 床位号
     * @return the bedNo
     */
    public String getBedNo() {
        return this.bedNo;
    }

    /**
     * set the bedNo - 床位号
     */
    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    /**
     * get the billTotalPrice - 订单总价
     * @return the billTotalPrice
     */
    public String getBillTotalPrice() {
        return this.billTotalPrice;
    }

    /**
     * set the billTotalPrice - 订单总价
     */
    public void setBillTotalPrice(String billTotalPrice) {
        this.billTotalPrice = billTotalPrice;
    }

    /**
     * get the payType - 支付类型
     * @return the payType
     */
    public String getPayType() {
        return this.payType;
    }

    /**
     * set the payType - 支付类型
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * get the printFlag - 打印标记[已打印()Y/未打印(N)]
     * @return the printFlag
     */
    public String getPrintFlag() {
        return this.printFlag;
    }

    /**
     * set the printFlag - 打印标记[已打印()Y/未打印(N)]
     */
    public void setPrintFlag(String printFlag) {
        this.printFlag = printFlag;
    }

    /**
     * get the rejectCode - 作废标记。null/1/2，正常/审核作废/确定作废
     * @return the rejectCode
     */
    public String getRejectCode() {
        return this.rejectCode;
    }

    /**
     * set the rejectCode - 作废标记。null/1/2，正常/审核作废/确定作废
     */
    public void setRejectCode(String rejectCode) {
        this.rejectCode = rejectCode;
    }

    /**
     * get the rejectReason - 作废原因
     * @return the rejectReason
     */
    public String getRejectReason() {
        return this.rejectReason;
    }

    /**
     * set the rejectReason - 作废原因
     */
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setFeeFn(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("feeFn")), feeFn));
        setOpenId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("openId")), openId));
        setUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userName")), userName));
        setUserTelNumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userTelNumber")), userTelNumber));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setBuildingName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("buildingName")), buildingName));
        setFloor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floor")), floor));
        setFloorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("floorName")), floorName));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setWardCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wardCode")), wardCode));
        setWardName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wardName")), wardName));
        setBedNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bedNo")), bedNo));
        setBillTotalPrice(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billTotalPrice")), billTotalPrice));
        setPayType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payType")), payType));
        setPrintFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("printFlag")), printFlag));
        setRejectCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rejectCode")), rejectCode));
        setRejectReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rejectReason")), rejectReason));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("feeFn",StringUtils.toString(feeFn, eiMetadata.getMeta("feeFn")));
        map.put("openId",StringUtils.toString(openId, eiMetadata.getMeta("openId")));
        map.put("userName",StringUtils.toString(userName, eiMetadata.getMeta("userName")));
        map.put("userTelNumber",StringUtils.toString(userTelNumber, eiMetadata.getMeta("userTelNumber")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("buildingName",StringUtils.toString(buildingName, eiMetadata.getMeta("buildingName")));
        map.put("floor",StringUtils.toString(floor, eiMetadata.getMeta("floor")));
        map.put("floorName",StringUtils.toString(floorName, eiMetadata.getMeta("floorName")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("wardCode",StringUtils.toString(wardCode, eiMetadata.getMeta("wardCode")));
        map.put("wardName",StringUtils.toString(wardName, eiMetadata.getMeta("wardName")));
        map.put("bedNo",StringUtils.toString(bedNo, eiMetadata.getMeta("bedNo")));
        map.put("billTotalPrice",StringUtils.toString(billTotalPrice, eiMetadata.getMeta("billTotalPrice")));
        map.put("payType",StringUtils.toString(payType, eiMetadata.getMeta("payType")));
        map.put("printFlag",StringUtils.toString(printFlag, eiMetadata.getMeta("printFlag")));
        map.put("rejectCode",StringUtils.toString(rejectCode, eiMetadata.getMeta("rejectCode")));
        map.put("rejectReason",StringUtils.toString(rejectReason, eiMetadata.getMeta("rejectReason")));
        return map;
    }
}