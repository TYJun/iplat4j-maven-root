/**
* Generate time : 2021-05-13 14:27:13
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ps.bm.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import java.math.BigDecimal;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* PSBMPosZf01 POS订单作废实体类，涉及表：
* sc_order_bill_patient
* sc_order_time
* tbmbd02
* sc_canteen_liaison_conf
* sc_patient_card
* sc_operation
* sc_operation_status
*/
public class PSBMPosZf01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String billNo = " ";		/* 订单号*/
    private String userName = " ";		
    private String openId = " ";		/* 微信ID*/
    private String userTelNumber = " ";		/* 用户电话*/
    private String deptName = " ";		/* 病区名称*/
    private String bedNo = " ";		/* 床位号*/
    private String address = " ";		/* 订餐地点*/
    private String building = " ";		/* 所属大楼*/
    private String buildingName = " ";		
    private String reqSendTime = " ";		/* 需求送达时间*/
    private BigDecimal billTotalPrice = new BigDecimal("0");		/* 订单总价*/
    private String billRemark = " ";		/* 订单备注*/
    private String statusCode = " ";		/* 状态代码*/
    private String rejectCode = " ";		/* 作废标记。null/1/2，正常/审核作废/确定作废*/
    private String operationTime1 = " ";		/* 操作时间*/
    private String operationTime = " ";		/* 操作时间*/
    private String canteenName = " ";		/* 食堂名称*/
    private String printFlag = " ";		/* 打印标记[已打印()Y/未打印(N)]*/
    private String recReviseTime = " ";		/* 记录修改时间*/
    private String rejectReason = " ";		/* 作废原因*/
    private String recCreator = " ";		
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String mealName = " ";		
    private String statusName = " ";		/* 状态名称*/
    private String payType = " ";		/* 状态名称*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName("订单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("openId");
        eiColumn.setDescName("微信ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userTelNumber");
        eiColumn.setDescName("用户电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("病区名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bedNo");
        eiColumn.setDescName("床位号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("address");
        eiColumn.setDescName("订餐地点");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName("所属大楼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("buildingName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reqSendTime");
        eiColumn.setDescName("需求送达时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billTotalPrice");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("订单总价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billRemark");
        eiColumn.setDescName("订单备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态代码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rejectCode");
        eiColumn.setDescName("作废标记。null/1/2，正常/审核作废/确定作废");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operationTime1");
        eiColumn.setDescName("操作时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operationTime");
        eiColumn.setDescName("操作时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenName");
        eiColumn.setDescName("食堂名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("printFlag");
        eiColumn.setDescName("打印标记[已打印()Y/未打印(N)]");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("记录修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rejectReason");
        eiColumn.setDescName("作废原因");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("记录创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusName");
        eiColumn.setDescName("状态名称");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("payType");
        eiColumn.setDescName("支付类型");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PSBMPosZf01() {
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
     * get the billNo - 订单号
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo - 订单号
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * get the userName 
     * @return the userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * set the userName 
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * get the openId - 微信ID
     * @return the openId
     */
    public String getOpenId() {
        return this.openId;
    }

    /**
     * set the openId - 微信ID
     */
    public void setOpenId(String openId) {
        this.openId = openId;
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
     * get the address - 订餐地点
     * @return the address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * set the address - 订餐地点
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * get the building - 所属大楼
     * @return the building
     */
    public String getBuilding() {
        return this.building;
    }

    /**
     * set the building - 所属大楼
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * get the buildingName 
     * @return the buildingName
     */
    public String getBuildingName() {
        return this.buildingName;
    }

    /**
     * set the buildingName 
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    /**
     * get the reqSendTime - 需求送达时间
     * @return the reqSendTime
     */
    public String getReqSendTime() {
        return this.reqSendTime;
    }

    /**
     * set the reqSendTime - 需求送达时间
     */
    public void setReqSendTime(String reqSendTime) {
        this.reqSendTime = reqSendTime;
    }

    /**
     * get the billTotalPrice - 订单总价
     * @return the billTotalPrice
     */
    public BigDecimal getBillTotalPrice() {
        return this.billTotalPrice;
    }

    /**
     * set the billTotalPrice - 订单总价
     */
    public void setBillTotalPrice(BigDecimal billTotalPrice) {
        this.billTotalPrice = billTotalPrice;
    }

    /**
     * get the billRemark - 订单备注
     * @return the billRemark
     */
    public String getBillRemark() {
        return this.billRemark;
    }

    /**
     * set the billRemark - 订单备注
     */
    public void setBillRemark(String billRemark) {
        this.billRemark = billRemark;
    }

    /**
     * get the statusCode - 状态代码
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态代码
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
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
     * get the operationTime1 - 操作时间
     * @return the operationTime1
     */
    public String getOperationTime1() {
        return this.operationTime1;
    }

    /**
     * set the operationTime1 - 操作时间
     */
    public void setOperationTime1(String operationTime1) {
        this.operationTime1 = operationTime1;
    }

    /**
     * get the operationTime - 操作时间
     * @return the operationTime
     */
    public String getOperationTime() {
        return this.operationTime;
    }

    /**
     * set the operationTime - 操作时间
     */
    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    /**
     * get the canteenName - 食堂名称
     * @return the canteenName
     */
    public String getCanteenName() {
        return this.canteenName;
    }

    /**
     * set the canteenName - 食堂名称
     */
    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
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
     * get the recCreator 
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator 
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
     * get the mealName 
     * @return the mealName
     */
    public String getMealName() {
        return this.mealName;
    }

    /**
     * set the mealName 
     */
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    /**
     * get the statusName - 状态名称
     * @return the statusName
     */
    public String getStatusName() {
        return this.statusName;
    }

    /**
     * set the statusName - 状态名称
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userName")), userName));
        setOpenId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("openId")), openId));
        setUserTelNumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userTelNumber")), userTelNumber));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setBedNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bedNo")), bedNo));
        setAddress(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("address")), address));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setBuildingName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("buildingName")), buildingName));
        setReqSendTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reqSendTime")), reqSendTime));
        setBillTotalPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("billTotalPrice")), billTotalPrice));
        setBillRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billRemark")), billRemark));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setRejectCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rejectCode")), rejectCode));
        setOperationTime1(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operationTime1")), operationTime1));
        setOperationTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operationTime")), operationTime));
        setCanteenName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenName")), canteenName));
        setPrintFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("printFlag")), printFlag));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setRejectReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rejectReason")), rejectReason));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setMealName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealName")), mealName));
        setStatusName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusName")), statusName));
        setStatusName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payType")), payType));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("userName",StringUtils.toString(userName, eiMetadata.getMeta("userName")));
        map.put("openId",StringUtils.toString(openId, eiMetadata.getMeta("openId")));
        map.put("userTelNumber",StringUtils.toString(userTelNumber, eiMetadata.getMeta("userTelNumber")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("bedNo",StringUtils.toString(bedNo, eiMetadata.getMeta("bedNo")));
        map.put("address",StringUtils.toString(address, eiMetadata.getMeta("address")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("buildingName",StringUtils.toString(buildingName, eiMetadata.getMeta("buildingName")));
        map.put("reqSendTime",StringUtils.toString(reqSendTime, eiMetadata.getMeta("reqSendTime")));
        map.put("billTotalPrice",StringUtils.toString(billTotalPrice, eiMetadata.getMeta("billTotalPrice")));
        map.put("billRemark",StringUtils.toString(billRemark, eiMetadata.getMeta("billRemark")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("rejectCode",StringUtils.toString(rejectCode, eiMetadata.getMeta("rejectCode")));
        map.put("operationTime1",StringUtils.toString(operationTime1, eiMetadata.getMeta("operationTime1")));
        map.put("operationTime",StringUtils.toString(operationTime, eiMetadata.getMeta("operationTime")));
        map.put("canteenName",StringUtils.toString(canteenName, eiMetadata.getMeta("canteenName")));
        map.put("printFlag",StringUtils.toString(printFlag, eiMetadata.getMeta("printFlag")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("rejectReason",StringUtils.toString(rejectReason, eiMetadata.getMeta("rejectReason")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("mealName",StringUtils.toString(mealName, eiMetadata.getMeta("mealName")));
        map.put("statusName",StringUtils.toString(statusName, eiMetadata.getMeta("statusName")));
        map.put("payType",StringUtils.toString(payType, eiMetadata.getMeta("payType")));
        return map;
    }
}