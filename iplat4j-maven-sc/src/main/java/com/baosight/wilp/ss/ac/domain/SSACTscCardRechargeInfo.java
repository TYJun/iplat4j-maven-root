/**
* Generate time : 2021-08-10 10:30:39
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
* SSACTscCardRechargeInfo 充值
* 
*/
public class SSACTscCardRechargeInfo extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String createTime ;		/* 创建时间*/
    private String workNo = " ";		/* 工号*/
    private String cardId = " ";		/* 卡片物理ID*/
    private String cardNo = " ";		/* 卡片业务编码*/
    private BigDecimal cardBalanceBefore = new BigDecimal("0");		/* 充值前金额*/
    private BigDecimal cardRechargeMoney = new BigDecimal("0");		/* 充值金额*/
    private BigDecimal cardBalanceAfter = new BigDecimal("0");		/* 充值后金额*/
    private String resviseTime ;		/* 回掉时间*/
    private String flag = " ";		/* 状态  /00--待充值/01--充值成功/99--结束*/
    private String billId = " ";		/* 单据ID*/
    private String msg = " ";		/* 信息*/
    private String canteenNum = " ";		/* 食堂编码*/
    private String hosptialNo = " ";		/* 支付宝商号*/
    private String rechargeType = " ";		/* 充值类型*/
    private String billType = " ";		/* 单据类型  /work -- 职工/patient -- 病患/*/
    private String tradeNo = " ";		/* 交易号*/
    private String outTradeNo = " ";		/* 商户号*/
    private String buyerEmail = " ";		/* 付款账户*/
    private String sellerEmail = " ";		/* 收款账户*/
    private String gmtPayment = " ";		/* 交易完成时间*/
    private String gmtCreate = " ";		/* 交易创建时间*/
    private String transactionId = " ";		/* 交易流水号(微信或支付宝)*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName("工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cardId");
        eiColumn.setDescName("卡片物理ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cardNo");
        eiColumn.setDescName("卡片业务编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cardBalanceBefore");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("充值前金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cardRechargeMoney");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("充值金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cardBalanceAfter");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("充值后金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("resviseTime");
        eiColumn.setDescName("回掉时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("flag");
        eiColumn.setDescName("状态  /00--待充值/01--充值成功/99--结束");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billId");
        eiColumn.setDescName("单据ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("msg");
        eiColumn.setDescName("信息");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName("食堂编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hosptialNo");
        eiColumn.setDescName("支付宝商号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rechargeType");
        eiColumn.setDescName("充值类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billType");
        eiColumn.setDescName("单据类型  /work -- 职工/patient -- 病患/");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tradeNo");
        eiColumn.setDescName("交易号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outTradeNo");
        eiColumn.setDescName("商户号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("buyerEmail");
        eiColumn.setDescName("付款账户");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sellerEmail");
        eiColumn.setDescName("收款账户");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("gmtPayment");
        eiColumn.setDescName("交易完成时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("gmtCreate");
        eiColumn.setDescName("交易创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("transactionId");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSACTscCardRechargeInfo() {
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
     * get the createTime - 创建时间
     * @return the createTime
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime - 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * get the workNo - 工号
     * @return the workNo
     */
    public String getWorkNo() {
        return this.workNo;
    }

    /**
     * set the workNo - 工号
     */
    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    /**
     * get the cardId - 卡片物理ID
     * @return the cardId
     */
    public String getCardId() {
        return this.cardId;
    }

    /**
     * set the cardId - 卡片物理ID
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * get the cardNo - 卡片业务编码
     * @return the cardNo
     */
    public String getCardNo() {
        return this.cardNo;
    }

    /**
     * set the cardNo - 卡片业务编码
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * get the cardBalanceBefore - 充值前金额
     * @return the cardBalanceBefore
     */
    public BigDecimal getCardBalanceBefore() {
        return this.cardBalanceBefore;
    }

    /**
     * set the cardBalanceBefore - 充值前金额
     */
    public void setCardBalanceBefore(BigDecimal cardBalanceBefore) {
        this.cardBalanceBefore = cardBalanceBefore;
    }

    /**
     * get the cardRechargeMoney - 充值金额
     * @return the cardRechargeMoney
     */
    public BigDecimal getCardRechargeMoney() {
        return this.cardRechargeMoney;
    }

    /**
     * set the cardRechargeMoney - 充值金额
     */
    public void setCardRechargeMoney(BigDecimal cardRechargeMoney) {
        this.cardRechargeMoney = cardRechargeMoney;
    }

    /**
     * get the cardBalanceAfter - 充值后金额
     * @return the cardBalanceAfter
     */
    public BigDecimal getCardBalanceAfter() {
        return this.cardBalanceAfter;
    }

    /**
     * set the cardBalanceAfter - 充值后金额
     */
    public void setCardBalanceAfter(BigDecimal cardBalanceAfter) {
        this.cardBalanceAfter = cardBalanceAfter;
    }

    /**
     * get the resviseTime - 回掉时间
     * @return the resviseTime
     */
    public String getResviseTime() {
        return this.resviseTime;
    }

    /**
     * set the resviseTime - 回掉时间
     */
    public void setResviseTime(String resviseTime) {
        this.resviseTime = resviseTime;
    }

    /**
     * get the flag - 状态  /00--待充值/01--充值成功/99--结束
     * @return the flag
     */
    public String getFlag() {
        return this.flag;
    }

    /**
     * set the flag - 状态  /00--待充值/01--充值成功/99--结束
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * get the billId - 单据ID
     * @return the billId
     */
    public String getBillId() {
        return this.billId;
    }

    /**
     * set the billId - 单据ID
     */
    public void setBillId(String billId) {
        this.billId = billId;
    }

    /**
     * get the msg - 信息
     * @return the msg
     */
    public String getMsg() {
        return this.msg;
    }

    /**
     * set the msg - 信息
     */
    public void setMsg(String msg) {
        this.msg = msg;
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
     * get the hosptialNo - 支付宝商号
     * @return the hosptialNo
     */
    public String getHosptialNo() {
        return this.hosptialNo;
    }

    /**
     * set the hosptialNo - 支付宝商号
     */
    public void setHosptialNo(String hosptialNo) {
        this.hosptialNo = hosptialNo;
    }

    /**
     * get the rechargeType - 充值类型
     * @return the rechargeType
     */
    public String getRechargeType() {
        return this.rechargeType;
    }

    /**
     * set the rechargeType - 充值类型
     */
    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }

    /**
     * get the billType - 单据类型  /work -- 职工/patient -- 病患/
     * @return the billType
     */
    public String getBillType() {
        return this.billType;
    }

    /**
     * set the billType - 单据类型  /work -- 职工/patient -- 病患/
     */
    public void setBillType(String billType) {
        this.billType = billType;
    }

    /**
     * get the tradeNo - 交易号
     * @return the tradeNo
     */
    public String getTradeNo() {
        return this.tradeNo;
    }

    /**
     * set the tradeNo - 交易号
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * get the outTradeNo - 商户号
     * @return the outTradeNo
     */
    public String getOutTradeNo() {
        return this.outTradeNo;
    }

    /**
     * set the outTradeNo - 商户号
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * get the buyerEmail - 付款账户
     * @return the buyerEmail
     */
    public String getBuyerEmail() {
        return this.buyerEmail;
    }

    /**
     * set the buyerEmail - 付款账户
     */
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    /**
     * get the sellerEmail - 收款账户
     * @return the sellerEmail
     */
    public String getSellerEmail() {
        return this.sellerEmail;
    }

    /**
     * set the sellerEmail - 收款账户
     */
    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    /**
     * get the gmtPayment - 交易完成时间
     * @return the gmtPayment
     */
    public String getGmtPayment() {
        return this.gmtPayment;
    }

    /**
     * set the gmtPayment - 交易完成时间
     */
    public void setGmtPayment(String gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    /**
     * get the gmtCreate - 交易创建时间
     * @return the gmtCreate
     */
    public String getGmtCreate() {
        return this.gmtCreate;
    }

    /**
     * set the gmtCreate - 交易创建时间
     */
    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createTime")),createTime));
        setWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNo")), workNo));
        setCardId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cardId")), cardId));
        setCardNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cardNo")), cardNo));
        setCardBalanceBefore(NumberUtils.toBigDecimal(StringUtils.toString(map.get("cardBalanceBefore")), cardBalanceBefore));
        setCardRechargeMoney(NumberUtils.toBigDecimal(StringUtils.toString(map.get("cardRechargeMoney")), cardRechargeMoney));
        setCardBalanceAfter(NumberUtils.toBigDecimal(StringUtils.toString(map.get("cardBalanceAfter")), cardBalanceAfter));
        setResviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("resviseTime")),resviseTime));
        setFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("flag")), flag));
        setBillId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billId")), billId));
        setMsg(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("msg")), msg));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
        setHosptialNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("hosptialNo")), hosptialNo));
        setRechargeType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rechargeType")), rechargeType));
        setBillType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billType")), billType));
        setTradeNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("tradeNo")), tradeNo));
        setOutTradeNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outTradeNo")), outTradeNo));
        setBuyerEmail(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("buyerEmail")), buyerEmail));
        setSellerEmail(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sellerEmail")), sellerEmail));
        setGmtPayment(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("gmtPayment")), gmtPayment));
        setGmtCreate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("gmtCreate")), gmtCreate));
        setTransactionId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("transactionId")), transactionId));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        map.put("cardId",StringUtils.toString(cardId, eiMetadata.getMeta("cardId")));
        map.put("cardNo",StringUtils.toString(cardNo, eiMetadata.getMeta("cardNo")));
        map.put("cardBalanceBefore",StringUtils.toString(cardBalanceBefore, eiMetadata.getMeta("cardBalanceBefore")));
        map.put("cardRechargeMoney",StringUtils.toString(cardRechargeMoney, eiMetadata.getMeta("cardRechargeMoney")));
        map.put("cardBalanceAfter",StringUtils.toString(cardBalanceAfter, eiMetadata.getMeta("cardBalanceAfter")));
        map.put("resviseTime",StringUtils.toString(resviseTime, eiMetadata.getMeta("resviseTime")));
        map.put("flag",StringUtils.toString(flag, eiMetadata.getMeta("flag")));
        map.put("billId",StringUtils.toString(billId, eiMetadata.getMeta("billId")));
        map.put("msg",StringUtils.toString(msg, eiMetadata.getMeta("msg")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        map.put("hosptialNo",StringUtils.toString(hosptialNo, eiMetadata.getMeta("hosptialNo")));
        map.put("rechargeType",StringUtils.toString(rechargeType, eiMetadata.getMeta("rechargeType")));
        map.put("billType",StringUtils.toString(billType, eiMetadata.getMeta("billType")));
        map.put("tradeNo",StringUtils.toString(tradeNo, eiMetadata.getMeta("tradeNo")));
        map.put("outTradeNo",StringUtils.toString(outTradeNo, eiMetadata.getMeta("outTradeNo")));
        map.put("buyerEmail",StringUtils.toString(buyerEmail, eiMetadata.getMeta("buyerEmail")));
        map.put("sellerEmail",StringUtils.toString(sellerEmail, eiMetadata.getMeta("sellerEmail")));
        map.put("gmtPayment",StringUtils.toString(gmtPayment, eiMetadata.getMeta("gmtPayment")));
        map.put("gmtCreate",StringUtils.toString(gmtCreate, eiMetadata.getMeta("gmtCreate")));
        map.put("transactionId",StringUtils.toString(transactionId, eiMetadata.getMeta("transactionId")));
        return map;
    }
}