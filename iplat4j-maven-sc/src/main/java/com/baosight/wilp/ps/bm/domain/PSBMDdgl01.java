/**
* Generate time : 2021-03-16 16:49:48
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
* PSBMDdgl01 病员订单管理实体类，涉及表
* sc_order_bill_patient
* sc_order_bill_detail
* tbmbd02
* sc_canteen_liaison_conf
* sc_operation_status
*/
public class PSBMDdgl01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String needDate = " ";		
    private String billNo = " ";		
    private String openId = " ";		
    private String userName = " ";		
    private String userTelNumber = " ";		
    private String deptNum = " ";		
    private String deptName = " ";		
    private String bedNo = " ";		
    private String mealNum = " ";		
    private String address = " ";		
    private String canteenName = " ";		
    private String reqSendTime = " ";		
    private BigDecimal billTotalPrice = new BigDecimal("0");		
    private String billRemark = " ";		
    private String payType = " ";		
    private String statusCode = " ";		
    private String currentDealer = " ";		
    private String processInstId = " ";		
    private String printFlag = " ";		
    private String orderWay = " ";		
    private String canteenNum = " ";		
    private String building = " ";		
    private String buildingName = " ";		
    private String recCreator = " ";		
    private String recCreateTime = " ";		
    private String mealName = " ";		
    private String menuNumber = " ";		
    private String statusName = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("needDate");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("openId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userTelNumber");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bedNo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealNum");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("address");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reqSendTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billTotalPrice");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billRemark");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payType");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currentDealer");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("processInstId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("printFlag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderWay");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("buildingName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuNumber");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isDelete");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PSBMDdgl01() {
        initMetaData();
    }

    /**
     * get the id 
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the needDate 
     * @return the needDate
     */
    public String getNeedDate() {
        return this.needDate;
    }

    /**
     * set the needDate 
     */
    public void setNeedDate(String needDate) {
        this.needDate = needDate;
    }

    /**
     * get the billNo 
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo 
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * get the openId 
     * @return the openId
     */
    public String getOpenId() {
        return this.openId;
    }

    /**
     * set the openId 
     */
    public void setOpenId(String openId) {
        this.openId = openId;
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
     * get the userTelNumber 
     * @return the userTelNumber
     */
    public String getUserTelNumber() {
        return this.userTelNumber;
    }

    /**
     * set the userTelNumber 
     */
    public void setUserTelNumber(String userTelNumber) {
        this.userTelNumber = userTelNumber;
    }

    /**
     * get the deptNum 
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum 
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptName 
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName 
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the bedNo 
     * @return the bedNo
     */
    public String getBedNo() {
        return this.bedNo;
    }

    /**
     * set the bedNo 
     */
    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    /**
     * get the mealNum 
     * @return the mealNum
     */
    public String getMealNum() {
        return this.mealNum;
    }

    /**
     * set the mealNum 
     */
    public void setMealNum(String mealNum) {
        this.mealNum = mealNum;
    }

    /**
     * get the address 
     * @return the address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * set the address 
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * get the canteenName 
     * @return the canteenName
     */
    public String getCanteenName() {
        return this.canteenName;
    }

    /**
     * set the canteenName 
     */
    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    /**
     * get the reqSendTime 
     * @return the reqSendTime
     */
    public String getReqSendTime() {
        return this.reqSendTime;
    }

    /**
     * set the reqSendTime 
     */
    public void setReqSendTime(String reqSendTime) {
        this.reqSendTime = reqSendTime;
    }

    /**
     * get the billTotalPrice 
     * @return the billTotalPrice
     */
    public BigDecimal getBillTotalPrice() {
        return this.billTotalPrice;
    }

    /**
     * set the billTotalPrice 
     */
    public void setBillTotalPrice(BigDecimal billTotalPrice) {
        this.billTotalPrice = billTotalPrice;
    }

    /**
     * get the billRemark 
     * @return the billRemark
     */
    public String getBillRemark() {
        return this.billRemark;
    }

    /**
     * set the billRemark 
     */
    public void setBillRemark(String billRemark) {
        this.billRemark = billRemark;
    }

    /**
     * get the payType 
     * @return the payType
     */
    public String getPayType() {
        return this.payType;
    }

    /**
     * set the payType 
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * get the statusCode 
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode 
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the currentDealer 
     * @return the currentDealer
     */
    public String getCurrentDealer() {
        return this.currentDealer;
    }

    /**
     * set the currentDealer 
     */
    public void setCurrentDealer(String currentDealer) {
        this.currentDealer = currentDealer;
    }

    /**
     * get the processInstId 
     * @return the processInstId
     */
    public String getProcessInstId() {
        return this.processInstId;
    }

    /**
     * set the processInstId 
     */
    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

    /**
     * get the printFlag 
     * @return the printFlag
     */
    public String getPrintFlag() {
        return this.printFlag;
    }

    /**
     * set the printFlag 
     */
    public void setPrintFlag(String printFlag) {
        this.printFlag = printFlag;
    }

    /**
     * get the orderWay 
     * @return the orderWay
     */
    public String getOrderWay() {
        return this.orderWay;
    }

    /**
     * set the orderWay 
     */
    public void setOrderWay(String orderWay) {
        this.orderWay = orderWay;
    }

    /**
     * get the canteenNum 
     * @return the canteenNum
     */
    public String getCanteenNum() {
        return this.canteenNum;
    }

    /**
     * set the canteenNum 
     */
    public void setCanteenNum(String canteenNum) {
        this.canteenNum = canteenNum;
    }

    /**
     * get the building 
     * @return the building
     */
    public String getBuilding() {
        return this.building;
    }

    /**
     * set the building 
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
     * get the recCreateTime 
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime 
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
     * get the menuNumber 
     * @return the menuNumber
     */
    public String getMenuNumber() {
        return this.menuNumber;
    }

    /**
     * set the menuNumber 
     */
    public void setMenuNumber(String menuNumber) {
        this.menuNumber = menuNumber;
    }

    /**
     * get the statusName 
     * @return the statusName
     */
    public String getStatusName() {
        return this.statusName;
    }

    /**
     * set the statusName 
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setNeedDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("needDate")), needDate));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setOpenId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("openId")), openId));
        setUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userName")), userName));
        setUserTelNumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userTelNumber")), userTelNumber));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setBedNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bedNo")), bedNo));
        setMealNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealNum")), mealNum));
        setAddress(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("address")), address));
        setCanteenName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenName")), canteenName));
        setReqSendTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reqSendTime")), reqSendTime));
        setBillTotalPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("billTotalPrice")), billTotalPrice));
        setBillRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billRemark")), billRemark));
        setPayType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payType")), payType));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setCurrentDealer(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("currentDealer")), currentDealer));
        setProcessInstId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("processInstId")), processInstId));
        setPrintFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("printFlag")), printFlag));
        setOrderWay(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("orderWay")), orderWay));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setBuildingName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("buildingName")), buildingName));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setMealName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealName")), mealName));
        setMenuNumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuNumber")), menuNumber));
        setStatusName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusName")), statusName));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("needDate",StringUtils.toString(needDate, eiMetadata.getMeta("needDate")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("openId",StringUtils.toString(openId, eiMetadata.getMeta("openId")));
        map.put("userName",StringUtils.toString(userName, eiMetadata.getMeta("userName")));
        map.put("userTelNumber",StringUtils.toString(userTelNumber, eiMetadata.getMeta("userTelNumber")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("bedNo",StringUtils.toString(bedNo, eiMetadata.getMeta("bedNo")));
        map.put("mealNum",StringUtils.toString(mealNum, eiMetadata.getMeta("mealNum")));
        map.put("address",StringUtils.toString(address, eiMetadata.getMeta("address")));
        map.put("canteenName",StringUtils.toString(canteenName, eiMetadata.getMeta("canteenName")));
        map.put("reqSendTime",StringUtils.toString(reqSendTime, eiMetadata.getMeta("reqSendTime")));
        map.put("billTotalPrice",StringUtils.toString(billTotalPrice, eiMetadata.getMeta("billTotalPrice")));
        map.put("billRemark",StringUtils.toString(billRemark, eiMetadata.getMeta("billRemark")));
        map.put("payType",StringUtils.toString(payType, eiMetadata.getMeta("payType")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("currentDealer",StringUtils.toString(currentDealer, eiMetadata.getMeta("currentDealer")));
        map.put("processInstId",StringUtils.toString(processInstId, eiMetadata.getMeta("processInstId")));
        map.put("printFlag",StringUtils.toString(printFlag, eiMetadata.getMeta("printFlag")));
        map.put("orderWay",StringUtils.toString(orderWay, eiMetadata.getMeta("orderWay")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("buildingName",StringUtils.toString(buildingName, eiMetadata.getMeta("buildingName")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("mealName",StringUtils.toString(mealName, eiMetadata.getMeta("mealName")));
        map.put("menuNumber",StringUtils.toString(menuNumber, eiMetadata.getMeta("menuNumber")));
        map.put("statusName",StringUtils.toString(statusName, eiMetadata.getMeta("statusName")));
        return map;
    }
}