/**
* Generate time : 2021-07-09 9:27:03
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.ac.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
* SSACZQGL01自取餐管理
* 
*/
public class SSACZQGL01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String recCreator = " ";		/* 创建人工号*/
    private String recCreateName = " ";		/* 创建人姓名*/
    private String recReviseTime = " ";		/* 修改时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String archiveFlag = " ";		/* 来源：APP    POS*/
    private String billNo = " ";		/* 订单号：ZQ12020042700005 规则 ： ZQ+年月日+5位递增*/
    private String openId = " ";		/* 微信的openId*/
    private String userName = " ";		/* 用户姓名*/
    private String userTelNumber = " ";		/* 联系电话*/
    private String mealNum = " ";		/* 餐次编码：001 早餐；002 中餐；003 晚餐*/
    private String mealNumName = " ";		/* 餐次名称*/
    private String address = " ";		/* 取餐地址，下单时选择的取餐地址*/
    private String reqSendTime = " ";		/* 取餐时间*/
    private BigDecimal billTotalPrice = new BigDecimal("0");		/* 订单总价*/
    private String canteenNum = " ";		/* 食堂编码*/
    private String canteenName = " ";		/* 食堂名称*/
    private String billRemark = " ";		/* 备注*/
    private String payType = " ";		/* 支付方式：0000-现金支付；0101-支付宝；0201-微信*/
    private String payTypeName = " ";		/* 支付方式名称*/
    private String statusCode = " ";		/* 状态：00-草稿；02-待食堂确认；03-待制作；04-待取餐；05-待评价；99-已结束*/
    private String selfCode = " ";		/* 长度4位，由0-9，A-Z，随机生成4为数*/
    private String currentDealer = " ";		/* 当前处理人*/
    private String processInstId = " ";		/* 流程实例ID*/
    private String printFlag = " ";		/* 打印标记*/
    private String orderWay = " ";		
    private String needDate = " ";		/* 取餐日期*/
    private String rejectCode = " ";		/* 作废状态*/
    private String rejectReason = " ";		/* 作废原因*/
    private String payNo = " ";		/* 支付宝userId或微信的openId*/
    private String cardId = " ";		
    private String deptName = " ";		/* 科室*/
    private String deptNum = " ";		/* 科室编码*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateName");
        eiColumn.setDescName("创建人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("来源：APP    POS");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName("订单号：ZQ12020042700005 规则 ： ZQ+年月日+5位递增");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("openId");
        eiColumn.setDescName("微信的openId");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userName");
        eiColumn.setDescName("用户姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userTelNumber");
        eiColumn.setDescName("联系电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealNum");
        eiColumn.setDescName("餐次编码：001 早餐；002 中餐；003 晚餐");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealNumName");
        eiColumn.setDescName("餐次名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("address");
        eiColumn.setDescName("取餐地址，下单时选择的取餐地址");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reqSendTime");
        eiColumn.setDescName("取餐时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billTotalPrice");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("订单总价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName("食堂编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenName");
        eiColumn.setDescName("食堂名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billRemark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payType");
        eiColumn.setDescName("支付方式：0000-现金支付；0101-支付宝；0201-微信");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payTypeName");
        eiColumn.setDescName("支付方式名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态：00-草稿；02-待食堂确认；03-待制作；04-待取餐；05-待评价；99-已结束");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("selfCode");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("长度4位，由0-9，A-Z，随机生成4为数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currentDealer");
        eiColumn.setDescName("当前处理人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("processInstId");
        eiColumn.setDescName("流程实例ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("printFlag");
        eiColumn.setDescName("打印标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderWay");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("needDate");
        eiColumn.setDescName("取餐日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rejectCode");
        eiColumn.setDescName("作废状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rejectReason");
        eiColumn.setDescName("作废原因");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payNo");
        eiColumn.setDescName("支付宝userId或微信的openId");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cardId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSACZQGL01() {
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
     * get the recCreator - 创建人工号
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建人工号
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateName - 创建人姓名
     * @return the recCreateName
     */
    public String getRecCreateName() {
        return this.recCreateName;
    }

    /**
     * set the recCreateName - 创建人姓名
     */
    public void setRecCreateName(String recCreateName) {
        this.recCreateName = recCreateName;
    }

    /**
     * get the recReviseTime - 修改时间
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the recRevisor - 修改人
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 修改人
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recCreateTime - 创建时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the archiveFlag - 来源：APP    POS
     * @return the archiveFlag
     */
    public String getArchiveFlag() {
        return this.archiveFlag;
    }

    /**
     * set the archiveFlag - 来源：APP    POS
     */
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    /**
     * get the billNo - 订单号：ZQ12020042700005 规则 ： ZQ+年月日+5位递增
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo - 订单号：ZQ12020042700005 规则 ： ZQ+年月日+5位递增
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * get the openId - 微信的openId
     * @return the openId
     */
    public String getOpenId() {
        return this.openId;
    }

    /**
     * set the openId - 微信的openId
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
     * get the userTelNumber - 联系电话
     * @return the userTelNumber
     */
    public String getUserTelNumber() {
        return this.userTelNumber;
    }

    /**
     * set the userTelNumber - 联系电话
     */
    public void setUserTelNumber(String userTelNumber) {
        this.userTelNumber = userTelNumber;
    }

    /**
     * get the mealNum - 餐次编码：001 早餐；002 中餐；003 晚餐
     * @return the mealNum
     */
    public String getMealNum() {
        return this.mealNum;
    }

    /**
     * set the mealNum - 餐次编码：001 早餐；002 中餐；003 晚餐
     */
    public void setMealNum(String mealNum) {
        this.mealNum = mealNum;
    }

    /**
     * get the mealNumName - 餐次名称
     * @return the mealNumName
     */
    public String getMealNumName() {
        return this.mealNumName;
    }

    /**
     * set the mealNumName - 餐次名称
     */
    public void setMealNumName(String mealNumName) {
        this.mealNumName = mealNumName;
    }

    /**
     * get the address - 取餐地址，下单时选择的取餐地址
     * @return the address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * set the address - 取餐地址，下单时选择的取餐地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * get the reqSendTime - 取餐时间
     * @return the reqSendTime
     */
    public String getReqSendTime() {
        return this.reqSendTime;
    }

    /**
     * set the reqSendTime - 取餐时间
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
     * get the billRemark - 备注
     * @return the billRemark
     */
    public String getBillRemark() {
        return this.billRemark;
    }

    /**
     * set the billRemark - 备注
     */
    public void setBillRemark(String billRemark) {
        this.billRemark = billRemark;
    }

    /**
     * get the payType - 支付方式：0000-现金支付；0101-支付宝；0201-微信
     * @return the payType
     */
    public String getPayType() {
        return this.payType;
    }

    /**
     * set the payType - 支付方式：0000-现金支付；0101-支付宝；0201-微信
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * get the payTypeName - 支付方式名称
     * @return the payTypeName
     */
    public String getPayTypeName() {
        return this.payTypeName;
    }

    /**
     * set the payTypeName - 支付方式名称
     */
    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    /**
     * get the statusCode - 状态：00-草稿；02-待食堂确认；03-待制作；04-待取餐；05-待评价；99-已结束
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态：00-草稿；02-待食堂确认；03-待制作；04-待取餐；05-待评价；99-已结束
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the selfCode - 长度4位，由0-9，A-Z，随机生成4为数
     * @return the selfCode
     */
    public String getSelfCode() {
        return this.selfCode;
    }

    /**
     * set the selfCode - 长度4位，由0-9，A-Z，随机生成4为数
     */
    public void setSelfCode(String selfCode) {
        this.selfCode = selfCode;
    }

    /**
     * get the currentDealer - 当前处理人
     * @return the currentDealer
     */
    public String getCurrentDealer() {
        return this.currentDealer;
    }

    /**
     * set the currentDealer - 当前处理人
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
     * get the printFlag - 打印标记
     * @return the printFlag
     */
    public String getPrintFlag() {
        return this.printFlag;
    }

    /**
     * set the printFlag - 打印标记
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
     * get the needDate - 取餐日期
     * @return the needDate
     */
    public String getNeedDate() {
        return this.needDate;
    }

    /**
     * set the needDate - 取餐日期
     */
    public void setNeedDate(String needDate) {
        this.needDate = needDate;
    }

    /**
     * get the rejectCode - 作废状态
     * @return the rejectCode
     */
    public String getRejectCode() {
        return this.rejectCode;
    }

    /**
     * set the rejectCode - 作废状态
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
     * get the payNo - 支付宝userId或微信的openId
     * @return the payNo
     */
    public String getPayNo() {
        return this.payNo;
    }

    /**
     * set the payNo - 支付宝userId或微信的openId
     */
    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    /**
     * get the cardId 
     * @return the cardId
     */
    public String getCardId() {
        return this.cardId;
    }

    /**
     * set the cardId 
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * get the deptName - 科室
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 科室
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the deptNum - 科室编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 科室编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateName")), recCreateName));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setOpenId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("openId")), openId));
        setUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userName")), userName));
        setUserTelNumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userTelNumber")), userTelNumber));
        setMealNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealNum")), mealNum));
        setMealNumName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealNumName")), mealNumName));
        setAddress(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("address")), address));
        setReqSendTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reqSendTime")), reqSendTime));
        setBillTotalPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("billTotalPrice")), billTotalPrice));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
        setCanteenName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenName")), canteenName));
        setBillRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billRemark")), billRemark));
        setPayType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payType")), payType));
        setPayTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payTypeName")), payTypeName));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setSelfCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("selfCode")), selfCode));
        setCurrentDealer(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("currentDealer")), currentDealer));
        setProcessInstId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("processInstId")), processInstId));
        setPrintFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("printFlag")), printFlag));
        setOrderWay(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("orderWay")), orderWay));
        setNeedDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("needDate")), needDate));
        setRejectCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rejectCode")), rejectCode));
        setRejectReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rejectReason")), rejectReason));
        setPayNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payNo")), payNo));
        setCardId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cardId")), cardId));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateName",StringUtils.toString(recCreateName, eiMetadata.getMeta("recCreateName")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("openId",StringUtils.toString(openId, eiMetadata.getMeta("openId")));
        map.put("userName",StringUtils.toString(userName, eiMetadata.getMeta("userName")));
        map.put("userTelNumber",StringUtils.toString(userTelNumber, eiMetadata.getMeta("userTelNumber")));
        map.put("mealNum",StringUtils.toString(mealNum, eiMetadata.getMeta("mealNum")));
        map.put("mealNumName",StringUtils.toString(mealNumName, eiMetadata.getMeta("mealNumName")));
        map.put("address",StringUtils.toString(address, eiMetadata.getMeta("address")));
        map.put("reqSendTime",StringUtils.toString(reqSendTime, eiMetadata.getMeta("reqSendTime")));
        map.put("billTotalPrice",StringUtils.toString(billTotalPrice, eiMetadata.getMeta("billTotalPrice")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        map.put("canteenName",StringUtils.toString(canteenName, eiMetadata.getMeta("canteenName")));
        map.put("billRemark",StringUtils.toString(billRemark, eiMetadata.getMeta("billRemark")));
        map.put("payType",StringUtils.toString(payType, eiMetadata.getMeta("payType")));
        map.put("payTypeName",StringUtils.toString(payTypeName, eiMetadata.getMeta("payTypeName")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("selfCode",StringUtils.toString(selfCode, eiMetadata.getMeta("selfCode")));
        map.put("currentDealer",StringUtils.toString(currentDealer, eiMetadata.getMeta("currentDealer")));
        map.put("processInstId",StringUtils.toString(processInstId, eiMetadata.getMeta("processInstId")));
        map.put("printFlag",StringUtils.toString(printFlag, eiMetadata.getMeta("printFlag")));
        map.put("orderWay",StringUtils.toString(orderWay, eiMetadata.getMeta("orderWay")));
        map.put("needDate",StringUtils.toString(needDate, eiMetadata.getMeta("needDate")));
        map.put("rejectCode",StringUtils.toString(rejectCode, eiMetadata.getMeta("rejectCode")));
        map.put("rejectReason",StringUtils.toString(rejectReason, eiMetadata.getMeta("rejectReason")));
        map.put("payNo",StringUtils.toString(payNo, eiMetadata.getMeta("payNo")));
        map.put("cardId",StringUtils.toString(cardId, eiMetadata.getMeta("cardId")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        return map;
    }
}