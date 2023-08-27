/**
* Generate time : 2021-07-12 13:58:45
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.ac.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
* SSACTscRechargeConsume 消费记录
* 
*/
public class SSACTscRechargeConsume extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String staffNo = " ";		/* 职工工号*/
    private String staffName = " ";		/* 职工姓名*/
    private String cardNo = " ";		/* 就餐卡号*/
    private String cardPwd = " ";		/* 就餐卡密码*/
    private String idNo = " ";		/* 身份证号*/
    private String tradeNo = " ";		/* 支付宝交易号*/
    private String businessNo = " ";		/* 商户订单号*/
    private String buyerAlipayNo = " ";		/* 买家支付宝账号*/
    private String sellerAlipayNo = " ";		/* 卖家支付宝账号*/
    private BigDecimal orderMoney = new BigDecimal("0");		/* 订单金额*/
    private BigDecimal receiveMoney = new BigDecimal("0");		/* 实收金额*/
    private BigDecimal invoiceMoney = new BigDecimal("0");		/* 开票金额*/
    private BigDecimal payMoney = new BigDecimal("0");		/* 付款金额*/
    private Timestamp tradeCreateTime ;		/* 交易创建时间*/
    private Timestamp tradePayTime ;		/* 交易付款时间*/
    private Timestamp tradeRefundTime ;		/* 交易退款时间*/
    private Timestamp tradeEndTime ;		/* 交易结束时间*/
    private String rechargeType = " ";		/* 充值方式--支付宝/微信*/
    private String billNo = " ";		/* 订单单号*/
    private String statusCode = " ";		/* -1/取消订单；1/支付；2/一卡通扣款成功；3/工作流调用成功，下单成功；4/调用工作流失败；5/一卡通扣款失败; 9/支付异常*/
    private String operateType = " ";		/* A/充值；B/订餐消费；C/团购消费*/
    private Timestamp recCreateTime ;		/* 创建时间*/
    private Timestamp recReviseTime ;		/* 修改时间*/
    private String consumeLot = " ";		/* 批次*/
    private String remark = " ";		
    private String message = " ";		/* 扣款返回的消息*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("staffNo");
        eiColumn.setDescName("职工工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("staffName");
        eiColumn.setDescName("职工姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cardNo");
        eiColumn.setDescName("就餐卡号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cardPwd");
        eiColumn.setDescName("就餐卡密码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("idNo");
        eiColumn.setDescName("身份证号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tradeNo");
        eiColumn.setDescName("支付宝交易号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("businessNo");
        eiColumn.setDescName("商户订单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("buyerAlipayNo");
        eiColumn.setDescName("买家支付宝账号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sellerAlipayNo");
        eiColumn.setDescName("卖家支付宝账号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderMoney");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("订单金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("receiveMoney");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("实收金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invoiceMoney");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("开票金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payMoney");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("付款金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tradeCreateTime");
        eiColumn.setDescName("交易创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tradePayTime");
        eiColumn.setDescName("交易付款时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tradeRefundTime");
        eiColumn.setDescName("交易退款时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tradeEndTime");
        eiColumn.setDescName("交易结束时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rechargeType");
        eiColumn.setDescName("充值方式--支付宝/微信");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName("订单单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("-1/取消订单；1/支付；2/一卡通扣款成功；3/工作流调用成功，下单成功；4/调用工作流失败；5/一卡通扣款失败; 9/支付异常");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operateType");
        eiColumn.setDescName("A/充值；B/订餐消费；C/团购消费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("consumeLot");
        eiColumn.setDescName("批次");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("message");
        eiColumn.setDescName("扣款返回的消息");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSACTscRechargeConsume() {
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
     * get the staffNo - 职工工号
     * @return the staffNo
     */
    public String getStaffNo() {
        return this.staffNo;
    }

    /**
     * set the staffNo - 职工工号
     */
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    /**
     * get the staffName - 职工姓名
     * @return the staffName
     */
    public String getStaffName() {
        return this.staffName;
    }

    /**
     * set the staffName - 职工姓名
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /**
     * get the cardNo - 就餐卡号
     * @return the cardNo
     */
    public String getCardNo() {
        return this.cardNo;
    }

    /**
     * set the cardNo - 就餐卡号
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * get the cardPwd - 就餐卡密码
     * @return the cardPwd
     */
    public String getCardPwd() {
        return this.cardPwd;
    }

    /**
     * set the cardPwd - 就餐卡密码
     */
    public void setCardPwd(String cardPwd) {
        this.cardPwd = cardPwd;
    }

    /**
     * get the idNo - 身份证号
     * @return the idNo
     */
    public String getIdNo() {
        return this.idNo;
    }

    /**
     * set the idNo - 身份证号
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    /**
     * get the tradeNo - 支付宝交易号
     * @return the tradeNo
     */
    public String getTradeNo() {
        return this.tradeNo;
    }

    /**
     * set the tradeNo - 支付宝交易号
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * get the businessNo - 商户订单号
     * @return the businessNo
     */
    public String getBusinessNo() {
        return this.businessNo;
    }

    /**
     * set the businessNo - 商户订单号
     */
    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    /**
     * get the buyerAlipayNo - 买家支付宝账号
     * @return the buyerAlipayNo
     */
    public String getBuyerAlipayNo() {
        return this.buyerAlipayNo;
    }

    /**
     * set the buyerAlipayNo - 买家支付宝账号
     */
    public void setBuyerAlipayNo(String buyerAlipayNo) {
        this.buyerAlipayNo = buyerAlipayNo;
    }

    /**
     * get the sellerAlipayNo - 卖家支付宝账号
     * @return the sellerAlipayNo
     */
    public String getSellerAlipayNo() {
        return this.sellerAlipayNo;
    }

    /**
     * set the sellerAlipayNo - 卖家支付宝账号
     */
    public void setSellerAlipayNo(String sellerAlipayNo) {
        this.sellerAlipayNo = sellerAlipayNo;
    }

    /**
     * get the orderMoney - 订单金额
     * @return the orderMoney
     */
    public BigDecimal getOrderMoney() {
        return this.orderMoney;
    }

    /**
     * set the orderMoney - 订单金额
     */
    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    /**
     * get the receiveMoney - 实收金额
     * @return the receiveMoney
     */
    public BigDecimal getReceiveMoney() {
        return this.receiveMoney;
    }

    /**
     * set the receiveMoney - 实收金额
     */
    public void setReceiveMoney(BigDecimal receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    /**
     * get the invoiceMoney - 开票金额
     * @return the invoiceMoney
     */
    public BigDecimal getInvoiceMoney() {
        return this.invoiceMoney;
    }

    /**
     * set the invoiceMoney - 开票金额
     */
    public void setInvoiceMoney(BigDecimal invoiceMoney) {
        this.invoiceMoney = invoiceMoney;
    }

    /**
     * get the payMoney - 付款金额
     * @return the payMoney
     */
    public BigDecimal getPayMoney() {
        return this.payMoney;
    }

    /**
     * set the payMoney - 付款金额
     */
    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    /**
     * get the tradeCreateTime - 交易创建时间
     * @return the tradeCreateTime
     */
    public Timestamp getTradeCreateTime() {
        return this.tradeCreateTime;
    }

    /**
     * set the tradeCreateTime - 交易创建时间
     */
    public void setTradeCreateTime(Timestamp tradeCreateTime) {
        this.tradeCreateTime = tradeCreateTime;
    }

    /**
     * get the tradePayTime - 交易付款时间
     * @return the tradePayTime
     */
    public Timestamp getTradePayTime() {
        return this.tradePayTime;
    }

    /**
     * set the tradePayTime - 交易付款时间
     */
    public void setTradePayTime(Timestamp tradePayTime) {
        this.tradePayTime = tradePayTime;
    }

    /**
     * get the tradeRefundTime - 交易退款时间
     * @return the tradeRefundTime
     */
    public Timestamp getTradeRefundTime() {
        return this.tradeRefundTime;
    }

    /**
     * set the tradeRefundTime - 交易退款时间
     */
    public void setTradeRefundTime(Timestamp tradeRefundTime) {
        this.tradeRefundTime = tradeRefundTime;
    }

    /**
     * get the tradeEndTime - 交易结束时间
     * @return the tradeEndTime
     */
    public Timestamp getTradeEndTime() {
        return this.tradeEndTime;
    }

    /**
     * set the tradeEndTime - 交易结束时间
     */
    public void setTradeEndTime(Timestamp tradeEndTime) {
        this.tradeEndTime = tradeEndTime;
    }

    /**
     * get the rechargeType - 充值方式--支付宝/微信
     * @return the rechargeType
     */
    public String getRechargeType() {
        return this.rechargeType;
    }

    /**
     * set the rechargeType - 充值方式--支付宝/微信
     */
    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }

    /**
     * get the billNo - 订单单号
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo - 订单单号
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * get the statusCode - -1/取消订单；1/支付；2/一卡通扣款成功；3/工作流调用成功，下单成功；4/调用工作流失败；5/一卡通扣款失败; 9/支付异常
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - -1/取消订单；1/支付；2/一卡通扣款成功；3/工作流调用成功，下单成功；4/调用工作流失败；5/一卡通扣款失败; 9/支付异常
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the operateType - A/充值；B/订餐消费；C/团购消费
     * @return the operateType
     */
    public String getOperateType() {
        return this.operateType;
    }

    /**
     * set the operateType - A/充值；B/订餐消费；C/团购消费
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * get the recCreateTime - 创建时间
     * @return the recCreateTime
     */
    public Timestamp getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(Timestamp recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recReviseTime - 修改时间
     * @return the recReviseTime
     */
    public Timestamp getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTime(Timestamp recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the consumeLot - 批次
     * @return the consumeLot
     */
    public String getConsumeLot() {
        return this.consumeLot;
    }

    /**
     * set the consumeLot - 批次
     */
    public void setConsumeLot(String consumeLot) {
        this.consumeLot = consumeLot;
    }

    /**
     * get the remark 
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark 
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * get the message - 扣款返回的消息
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * set the message - 扣款返回的消息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setStaffNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("staffNo")), staffNo));
        setStaffName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("staffName")), staffName));
        setCardNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cardNo")), cardNo));
        setCardPwd(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cardPwd")), cardPwd));
        setIdNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("idNo")), idNo));
        setTradeNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("tradeNo")), tradeNo));
        setBusinessNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("businessNo")), businessNo));
        setBuyerAlipayNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("buyerAlipayNo")), buyerAlipayNo));
        setSellerAlipayNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sellerAlipayNo")), sellerAlipayNo));
        setOrderMoney(NumberUtils.toBigDecimal(StringUtils.toString(map.get("orderMoney")), orderMoney));
        setReceiveMoney(NumberUtils.toBigDecimal(StringUtils.toString(map.get("receiveMoney")), receiveMoney));
        setInvoiceMoney(NumberUtils.toBigDecimal(StringUtils.toString(map.get("invoiceMoney")), invoiceMoney));
        setPayMoney(NumberUtils.toBigDecimal(StringUtils.toString(map.get("payMoney")), payMoney));
        setTradeCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("tradeCreateTime"))));
        setTradePayTime(DateUtils.toTimestamp(StringUtils.toString(map.get("tradePayTime"))));
        setTradeRefundTime(DateUtils.toTimestamp(StringUtils.toString(map.get("tradeRefundTime"))));
        setTradeEndTime(DateUtils.toTimestamp(StringUtils.toString(map.get("tradeEndTime"))));
        setRechargeType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rechargeType")), rechargeType));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setOperateType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operateType")), operateType));
        setRecCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("recCreateTime"))));
        setRecReviseTime(DateUtils.toTimestamp(StringUtils.toString(map.get("recReviseTime"))));
        setConsumeLot(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("consumeLot")), consumeLot));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setMessage(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("message")), message));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("staffNo",StringUtils.toString(staffNo, eiMetadata.getMeta("staffNo")));
        map.put("staffName",StringUtils.toString(staffName, eiMetadata.getMeta("staffName")));
        map.put("cardNo",StringUtils.toString(cardNo, eiMetadata.getMeta("cardNo")));
        map.put("cardPwd",StringUtils.toString(cardPwd, eiMetadata.getMeta("cardPwd")));
        map.put("idNo",StringUtils.toString(idNo, eiMetadata.getMeta("idNo")));
        map.put("tradeNo",StringUtils.toString(tradeNo, eiMetadata.getMeta("tradeNo")));
        map.put("businessNo",StringUtils.toString(businessNo, eiMetadata.getMeta("businessNo")));
        map.put("buyerAlipayNo",StringUtils.toString(buyerAlipayNo, eiMetadata.getMeta("buyerAlipayNo")));
        map.put("sellerAlipayNo",StringUtils.toString(sellerAlipayNo, eiMetadata.getMeta("sellerAlipayNo")));
        map.put("orderMoney",StringUtils.toString(orderMoney, eiMetadata.getMeta("orderMoney")));
        map.put("receiveMoney",StringUtils.toString(receiveMoney, eiMetadata.getMeta("receiveMoney")));
        map.put("invoiceMoney",StringUtils.toString(invoiceMoney, eiMetadata.getMeta("invoiceMoney")));
        map.put("payMoney",StringUtils.toString(payMoney, eiMetadata.getMeta("payMoney")));
        map.put("tradeCreateTime",StringUtils.toString(tradeCreateTime, eiMetadata.getMeta("tradeCreateTime")));
        map.put("tradePayTime",StringUtils.toString(tradePayTime, eiMetadata.getMeta("tradePayTime")));
        map.put("tradeRefundTime",StringUtils.toString(tradeRefundTime, eiMetadata.getMeta("tradeRefundTime")));
        map.put("tradeEndTime",StringUtils.toString(tradeEndTime, eiMetadata.getMeta("tradeEndTime")));
        map.put("rechargeType",StringUtils.toString(rechargeType, eiMetadata.getMeta("rechargeType")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("operateType",StringUtils.toString(operateType, eiMetadata.getMeta("operateType")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("consumeLot",StringUtils.toString(consumeLot, eiMetadata.getMeta("consumeLot")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("message",StringUtils.toString(message, eiMetadata.getMeta("message")));
        return map;
    }
}