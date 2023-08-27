/**
* Generate time : 2021-05-13 15:27:38
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
* PSBMPosCx01 POS机订单查询实体类，涉及表：
* sc_order_bill_patient
* sc_order_bill_detail
* tbmbd02
* sc_operation_status
* sc_order_time
* sc_canteen_liaison_conf
* sc_patient_card
*/
public class PSBMPosCx01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String billNo = " ";		/* 订单号*/
    private String openId = " ";		/* 微信ID*/
    private String userName = " ";		
    private String userTelNumber = " ";		/* 用户电话*/
    private String deptNum = " ";		
    private String deptName = " ";		/* 病区名称*/
    private String bedNo = " ";		/* 床位号*/
    private String mealNum = " ";		/* 餐次编码*/
    private String address = " ";		/* 订餐地点*/
    private String mealName = " ";		/* 餐次名称*/
    private String canteenName = " ";		/* 食堂名称*/
    private String reqSendTime = " ";		
    private String building = " ";		/* 所属大楼*/
    private String buildingName = " ";		
    private BigDecimal billTotalPrice = new BigDecimal("0");		/* 订单总价*/
    private String billRemark = " ";		/* 订单备注*/
    private String payType = " ";		
    private String statusCode = " ";		/* 状态代码*/
    private String currentDealer = " ";		/* 当前操作人*/
    private String processInstId = " ";		/* 流程实例ID*/
    private String printFlag = " ";		/* 打印标记[已打印()Y/未打印(N)]*/
    private String orderWay = " ";		/* 下单方式(医护下单-01,病患/电话下单-02)*/
    private String canteenNum = " ";		/* 食堂编码*/
    private String statusName = " ";		/* 状态名称*/
    private String recCreator = " ";		
    private String recCreateTime = " ";		/* 记录创建时间*/
    private BigDecimal menuNumber = new BigDecimal("0");		

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

        eiColumn = new EiColumn("openId");
        eiColumn.setDescName("微信ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userTelNumber");
        eiColumn.setDescName("用户电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("病区名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bedNo");
        eiColumn.setDescName("床位号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealNum");
        eiColumn.setDescName("餐次编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("address");
        eiColumn.setDescName("订餐地点");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealName");
        eiColumn.setDescName("餐次名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenName");
        eiColumn.setDescName("食堂名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reqSendTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("building");
        eiColumn.setDescName("所属大楼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("buildingName");
        eiColumn.setDescName(" ");
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

        eiColumn = new EiColumn("payType");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态代码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currentDealer");
        eiColumn.setDescName("当前操作人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("processInstId");
        eiColumn.setDescName("流程实例ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("printFlag");
        eiColumn.setDescName("打印标记[已打印()Y/未打印(N)]");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderWay");
        eiColumn.setDescName("下单方式(医护下单-01,病患/电话下单-02)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName("食堂编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusName");
        eiColumn.setDescName("状态名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("记录创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuNumber");
        eiColumn.setType("N");
        eiColumn.setScaleLength(0);
        eiColumn.setFieldLength(32);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PSBMPosCx01() {
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
     * get the mealNum - 餐次编码
     * @return the mealNum
     */
    public String getMealNum() {
        return this.mealNum;
    }

    /**
     * set the mealNum - 餐次编码
     */
    public void setMealNum(String mealNum) {
        this.mealNum = mealNum;
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
     * get the mealName - 餐次名称
     * @return the mealName
     */
    public String getMealName() {
        return this.mealName;
    }

    /**
     * set the mealName - 餐次名称
     */
    public void setMealName(String mealName) {
        this.mealName = mealName;
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
     * get the currentDealer - 当前操作人
     * @return the currentDealer
     */
    public String getCurrentDealer() {
        return this.currentDealer;
    }

    /**
     * set the currentDealer - 当前操作人
     */
    public void setCurrentDealer(String currentDealer) {
        this.currentDealer = currentDealer;
    }

    /**
     * get the processInstId - 流程实例ID
     * @return the processInstId
     */
    public String getProcessInstId() {
        return this.processInstId;
    }

    /**
     * set the processInstId - 流程实例ID
     */
    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
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
     * get the orderWay - 下单方式(医护下单-01,病患/电话下单-02)
     * @return the orderWay
     */
    public String getOrderWay() {
        return this.orderWay;
    }

    /**
     * set the orderWay - 下单方式(医护下单-01,病患/电话下单-02)
     */
    public void setOrderWay(String orderWay) {
        this.orderWay = orderWay;
    }

    /**
     * get the canteenNum - 食堂编码
     * @return the canteenNum
     */
    public String getCanteenNum() {
        return this.canteenNum;
    }

    /**
     * set the canteenNum - 食堂编码
     */
    public void setCanteenNum(String canteenNum) {
        this.canteenNum = canteenNum;
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
     * get the menuNumber 
     * @return the menuNumber
     */
    public BigDecimal getMenuNumber() {
        return this.menuNumber;
    }

    /**
     * set the menuNumber 
     */
    public void setMenuNumber(BigDecimal menuNumber) {
        this.menuNumber = menuNumber;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setOpenId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("openId")), openId));
        setUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userName")), userName));
        setUserTelNumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userTelNumber")), userTelNumber));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setBedNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("bedNo")), bedNo));
        setMealNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealNum")), mealNum));
        setAddress(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("address")), address));
        setMealName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealName")), mealName));
        setCanteenName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenName")), canteenName));
        setReqSendTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reqSendTime")), reqSendTime));
        setBuilding(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("building")), building));
        setBuildingName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("buildingName")), buildingName));
        setBillTotalPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("billTotalPrice")), billTotalPrice));
        setBillRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billRemark")), billRemark));
        setPayType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payType")), payType));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setCurrentDealer(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("currentDealer")), currentDealer));
        setProcessInstId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("processInstId")), processInstId));
        setPrintFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("printFlag")), printFlag));
        setOrderWay(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("orderWay")), orderWay));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
        setStatusName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusName")), statusName));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setMenuNumber(NumberUtils.toBigDecimal(StringUtils.toString(map.get("menuNumber")), menuNumber));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("openId",StringUtils.toString(openId, eiMetadata.getMeta("openId")));
        map.put("userName",StringUtils.toString(userName, eiMetadata.getMeta("userName")));
        map.put("userTelNumber",StringUtils.toString(userTelNumber, eiMetadata.getMeta("userTelNumber")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("bedNo",StringUtils.toString(bedNo, eiMetadata.getMeta("bedNo")));
        map.put("mealNum",StringUtils.toString(mealNum, eiMetadata.getMeta("mealNum")));
        map.put("address",StringUtils.toString(address, eiMetadata.getMeta("address")));
        map.put("mealName",StringUtils.toString(mealName, eiMetadata.getMeta("mealName")));
        map.put("canteenName",StringUtils.toString(canteenName, eiMetadata.getMeta("canteenName")));
        map.put("reqSendTime",StringUtils.toString(reqSendTime, eiMetadata.getMeta("reqSendTime")));
        map.put("building",StringUtils.toString(building, eiMetadata.getMeta("building")));
        map.put("buildingName",StringUtils.toString(buildingName, eiMetadata.getMeta("buildingName")));
        map.put("billTotalPrice",StringUtils.toString(billTotalPrice, eiMetadata.getMeta("billTotalPrice")));
        map.put("billRemark",StringUtils.toString(billRemark, eiMetadata.getMeta("billRemark")));
        map.put("payType",StringUtils.toString(payType, eiMetadata.getMeta("payType")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("currentDealer",StringUtils.toString(currentDealer, eiMetadata.getMeta("currentDealer")));
        map.put("processInstId",StringUtils.toString(processInstId, eiMetadata.getMeta("processInstId")));
        map.put("printFlag",StringUtils.toString(printFlag, eiMetadata.getMeta("printFlag")));
        map.put("orderWay",StringUtils.toString(orderWay, eiMetadata.getMeta("orderWay")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        map.put("statusName",StringUtils.toString(statusName, eiMetadata.getMeta("statusName")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("menuNumber",StringUtils.toString(menuNumber, eiMetadata.getMeta("menuNumber")));
        return map;
    }
}