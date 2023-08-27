/**
* Generate time : 2021-07-13 20:36:05
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.ac.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* AeWorkCardOrderFood
* 
*/
public class SSACTscCardOrderFood extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* uuid*/
    private String datagroupCode = " ";
    private String recCreator = " ";		/* 创建人*/
    private String cardBaseCode = " ";		/* 卡号*/
    private String cardUserName = " ";		/* 持卡人姓名*/
    private String cardUserCode = " ";		/* 住院号*/
    private String consumeBillId = " ";		/* 订单号*/
    private String consumeDeviceCode = " ";		/* 设备号*/
    private String consumeAddressCode = " ";		/* 消费地点：02/食堂,01/网上*/
    private String qrType = " ";		/* 刷码消费:10/是刷二维码消费,空或者0不是*/
    private String consumeType = " ";		/* 消费类型： 付款/退款     0/1*/
    private Integer processFee = new Integer(0);		/* 手续费*/
    private Integer consumeMoney = new Integer(0);		/* 消费金额*/
    private String consumeTime ;		/* 消费时间*/
    private String canteenCode = " ";		/* 食堂编码*/
    private String canteenName = " ";		/* 食堂名称*/
    private String status = " ";		/* 订单状态码：订单初始状态 00，订单处理失败 01，订单已处理 02， 订单等待订餐确认结果 03，订单结束 04，订单的参数有问题 05*/
    private String memo = " ";		/* 备注*/
    private String randomNo = " ";		/* 消费机消费随机码*/
    private Integer cash = new Integer(0);		/* 现金账户待扣除金额*/
    private Integer subsidy = new Integer(0);		/* 现金账户待扣除金额*/
    private String updateTime ;		/* 支付宝,微信支付回调时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("uuid");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("datagroupCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cardBaseCode");
        eiColumn.setDescName("卡号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cardUserName");
        eiColumn.setDescName("持卡人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cardUserCode");
        eiColumn.setDescName("住院号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("consumeBillId");
        eiColumn.setDescName("订单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("consumeDeviceCode");
        eiColumn.setDescName("设备号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("consumeAddressCode");
        eiColumn.setDescName("消费地点：02/食堂,01/网上");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("qrType");
        eiColumn.setDescName("刷码消费:10/是刷二维码消费,空或者0不是");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("consumeType");
        eiColumn.setDescName("消费类型： 付款/退款     0/1");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("processFee");
        eiColumn.setDescName("手续费");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("consumeMoney");
        eiColumn.setDescName("消费金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("consumeTime");
        eiColumn.setDescName("消费时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenCode");
        eiColumn.setDescName("食堂编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenName");
        eiColumn.setDescName("食堂名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("订单状态码：订单初始状态 00，订单处理失败 01，订单已处理 02， 订单等待订餐确认结果 03，订单结束 04，订单的参数有问题 05");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("memo");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("randomNo");
        eiColumn.setDescName("消费机消费随机码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cash");
        eiColumn.setDescName("现金账户待扣除金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("subsidy");
        eiColumn.setDescName("现金账户待扣除金额");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSACTscCardOrderFood() {
        initMetaData();
    }

    /**
     * get the id - uuid
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - uuid
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the datagroupCode 
     * @return the datagroupCode
     */
    public String getDatagroupCode() {
        return this.datagroupCode;
    }

    /**
     * set the datagroupCode 
     */
    public void setDatagroupCode(String datagroupCode) {
        this.datagroupCode = datagroupCode;
    }

    /**
     * get the recCreator - 创建人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建人
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the cardBaseCode - 卡号
     * @return the cardBaseCode
     */
    public String getCardBaseCode() {
        return this.cardBaseCode;
    }

    /**
     * set the cardBaseCode - 卡号
     */
    public void setCardBaseCode(String cardBaseCode) {
        this.cardBaseCode = cardBaseCode;
    }

    /**
     * get the cardUserName - 持卡人姓名
     * @return the cardUserName
     */
    public String getCardUserName() {
        return this.cardUserName;
    }

    /**
     * set the cardUserName - 持卡人姓名
     */
    public void setCardUserName(String cardUserName) {
        this.cardUserName = cardUserName;
    }

    /**
     * get the cardUserCode - 住院号
     * @return the cardUserCode
     */
    public String getCardUserCode() {
        return this.cardUserCode;
    }

    /**
     * set the cardUserCode - 住院号
     */
    public void setCardUserCode(String cardUserCode) {
        this.cardUserCode = cardUserCode;
    }

    /**
     * get the consumeBillId - 订单号
     * @return the consumeBillId
     */
    public String getConsumeBillId() {
        return this.consumeBillId;
    }

    /**
     * set the consumeBillId - 订单号
     */
    public void setConsumeBillId(String consumeBillId) {
        this.consumeBillId = consumeBillId;
    }

    /**
     * get the consumeDeviceCode - 设备号
     * @return the consumeDeviceCode
     */
    public String getConsumeDeviceCode() {
        return this.consumeDeviceCode;
    }

    /**
     * set the consumeDeviceCode - 设备号
     */
    public void setConsumeDeviceCode(String consumeDeviceCode) {
        this.consumeDeviceCode = consumeDeviceCode;
    }

    /**
     * get the consumeAddressCode - 消费地点：02/食堂,01/网上
     * @return the consumeAddressCode
     */
    public String getConsumeAddressCode() {
        return this.consumeAddressCode;
    }

    /**
     * set the consumeAddressCode - 消费地点：02/食堂,01/网上
     */
    public void setConsumeAddressCode(String consumeAddressCode) {
        this.consumeAddressCode = consumeAddressCode;
    }

    /**
     * get the qrType - 刷码消费:10/是刷二维码消费,空或者0不是
     * @return the qrType
     */
    public String getQrType() {
        return this.qrType;
    }

    /**
     * set the qrType - 刷码消费:10/是刷二维码消费,空或者0不是
     */
    public void setQrType(String qrType) {
        this.qrType = qrType;
    }

    /**
     * get the consumeType - 消费类型： 付款/退款     0/1
     * @return the consumeType
     */
    public String getConsumeType() {
        return this.consumeType;
    }

    /**
     * set the consumeType - 消费类型： 付款/退款     0/1
     */
    public void setConsumeType(String consumeType) {
        this.consumeType = consumeType;
    }

    /**
     * get the processFee - 手续费
     * @return the processFee
     */
    public Integer getProcessFee() {
        return this.processFee;
    }

    /**
     * set the processFee - 手续费
     */
    public void setProcessFee(Integer processFee) {
        this.processFee = processFee;
    }

    /**
     * get the consumeMoney - 消费金额
     * @return the consumeMoney
     */
    public Integer getConsumeMoney() {
        return this.consumeMoney;
    }

    /**
     * set the consumeMoney - 消费金额
     */
    public void setConsumeMoney(Integer consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    /**
     * get the consumeTime - 消费时间
     * @return the consumeTime
     */
    public String getConsumeTime() {
        return this.consumeTime;
    }

    /**
     * set the consumeTime - 消费时间
     */
    public void setConsumeTime(String consumeTime) {
        this.consumeTime = consumeTime;
    }

    /**
     * get the canteenCode - 食堂编码
     * @return the canteenCode
     */
    public String getCanteenCode() {
        return this.canteenCode;
    }

    /**
     * set the canteenCode - 食堂编码
     */
    public void setCanteenCode(String canteenCode) {
        this.canteenCode = canteenCode;
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
     * get the status - 订单状态码：订单初始状态 00，订单处理失败 01，订单已处理 02， 订单等待订餐确认结果 03，订单结束 04，订单的参数有问题 05
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status - 订单状态码：订单初始状态 00，订单处理失败 01，订单已处理 02， 订单等待订餐确认结果 03，订单结束 04，订单的参数有问题 05
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * get the memo - 备注
     * @return the memo
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * set the memo - 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * get the randomNo - 消费机消费随机码
     * @return the randomNo
     */
    public String getRandomNo() {
        return this.randomNo;
    }

    /**
     * set the randomNo - 消费机消费随机码
     */
    public void setRandomNo(String randomNo) {
        this.randomNo = randomNo;
    }

    /**
     * get the cash - 现金账户待扣除金额
     * @return the cash
     */
    public Integer getCash() {
        return this.cash;
    }

    /**
     * set the cash - 现金账户待扣除金额
     */
    public void setCash(Integer cash) {
        this.cash = cash;
    }

    /**
     * get the subsidy - 现金账户待扣除金额
     * @return the subsidy
     */
    public Integer getSubsidy() {
        return this.subsidy;
    }

    /**
     * set the subsidy - 现金账户待扣除金额
     */
    public void setSubsidy(Integer subsidy) {
        this.subsidy = subsidy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setDatagroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datagroupCode")), datagroupCode));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setCardBaseCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cardBaseCode")), cardBaseCode));
        setCardUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cardUserName")), cardUserName));
        setCardUserCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cardUserCode")), cardUserCode));
        setConsumeBillId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("consumeBillId")), consumeBillId));
        setConsumeDeviceCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("consumeDeviceCode")), consumeDeviceCode));
        setConsumeAddressCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("consumeAddressCode")), consumeAddressCode));
        setQrType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("qrType")), qrType));
        setConsumeType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("consumeType")), consumeType));
        setProcessFee(NumberUtils.toInteger(StringUtils.toString(map.get("processFee")), processFee));
        setConsumeMoney(NumberUtils.toInteger(StringUtils.toString(map.get("consumeMoney")), consumeMoney));
        setConsumeTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("consumeTime")), consumeTime));
        setCanteenCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenCode")), canteenCode));
        setCanteenName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenName")), canteenName));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
        setRandomNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("randomNo")), randomNo));
        setCash(NumberUtils.toInteger(StringUtils.toString(map.get("cash")), cash));
        setSubsidy(NumberUtils.toInteger(StringUtils.toString(map.get("subsidy")), subsidy));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("datagroupCode",StringUtils.toString(datagroupCode, eiMetadata.getMeta("datagroupCode")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("cardBaseCode",StringUtils.toString(cardBaseCode, eiMetadata.getMeta("cardBaseCode")));
        map.put("cardUserName",StringUtils.toString(cardUserName, eiMetadata.getMeta("cardUserName")));
        map.put("cardUserCode",StringUtils.toString(cardUserCode, eiMetadata.getMeta("cardUserCode")));
        map.put("consumeBillId",StringUtils.toString(consumeBillId, eiMetadata.getMeta("consumeBillId")));
        map.put("consumeDeviceCode",StringUtils.toString(consumeDeviceCode, eiMetadata.getMeta("consumeDeviceCode")));
        map.put("consumeAddressCode",StringUtils.toString(consumeAddressCode, eiMetadata.getMeta("consumeAddressCode")));
        map.put("qrType",StringUtils.toString(qrType, eiMetadata.getMeta("qrType")));
        map.put("consumeType",StringUtils.toString(consumeType, eiMetadata.getMeta("consumeType")));
        map.put("processFee",StringUtils.toString(processFee, eiMetadata.getMeta("processFee")));
        map.put("consumeMoney",StringUtils.toString(consumeMoney, eiMetadata.getMeta("consumeMoney")));
        map.put("consumeTime",StringUtils.toString(consumeTime, eiMetadata.getMeta("consumeTime")));
        map.put("canteenCode",StringUtils.toString(canteenCode, eiMetadata.getMeta("canteenCode")));
        map.put("canteenName",StringUtils.toString(canteenName, eiMetadata.getMeta("canteenName")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
        map.put("randomNo",StringUtils.toString(randomNo, eiMetadata.getMeta("randomNo")));
        map.put("cash",StringUtils.toString(cash, eiMetadata.getMeta("cash")));
        map.put("subsidy",StringUtils.toString(subsidy, eiMetadata.getMeta("subsidy")));
        return map;
    }
}